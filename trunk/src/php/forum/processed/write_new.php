<?
// Записываем новый пост
// Вносим в базу
include_once 'classes/ua/com/diletant/sql/connection/mysql/connectionmysql.php';
include_once 'classes/ua/com/diletant/dao/searchdao.php';
$queryPostTbl = "
	SELECT
		curret_body_table,
		current_body_head_table
	FROM
		fd_setup
";
$rsltPostTbl = fd_query($queryPostTbl, $conn, "");
$currentBodyTable = mysql_result($rsltPostTbl, 0, 'curret_body_table');
$currentHeadPostTable = mysql_result($rsltPostTbl, 0, 'current_body_head_table');
// Записываем пост
$query="
	INSERT INTO 
		body 
			(head, table_post, table_head) 
		VALUES 
			('$str_idd', '$currentBodyTable', '$currentHeadPostTable')
		";
$result2 = fd_query($query, $conn, "");
$idPost = mysql_insert_id();

$query="
	INSERT INTO 
		$currentHeadPostTable 
			(id, id_post, auth, thread_id, tilte, fd_post_time, ip, domen) 
		VALUES 
			($idPost, '$idPost','$str_id', '$str_idd', '$str_head', ".$postTime->getTimestamp().", '$str_ip', '$str_dom')
		";
$result2 = fd_query($query, $conn, "");
$queryInsBody = "
	INSERT INTO 
		".$currentBodyTable." 
			(id, id_post, body) 
		VALUES 
			('$idPost', '$idPost', '$str_body')
		";
$rsltInsBody = fd_query($queryInsBody, $conn, "");
/* Обновляем заголовок*/
/*выбираем минусы игнора*/
$sqlIgnor="
   SELECT
      ignor.ignor
   FROM 
      ignor
   WHERE
      ignor.user=95
      AND ignor.ignor=".$str_id."
      AND ignor.end > now() 
	";
$rsltIgnor=fd_query($sqlIgnor,$conn, "");
if (mysql_num_rows($rsltIgnor)==0){
	$query="UPDATE 
				titles 
			SET 
				lposttime='".$lptime."', 
				lpostuser=".$str_id.", 
				lpostnick='".$n1."', 
				npost=npost+1,
				id_last_post=".$idPost."  
			WHERE 
				id=".$str_idd;
}else{
	$query="UPDATE 
				titles 
			SET 
				npost=npost+1,
				id_last_post=".$idPost."  
			WHERE 
				id=".$str_idd;
}
$result=fd_query($query, $conn, "");
/* Записываем данные для поиска*/
/* Автор*/
if ($is_search){
$connection = new ConnectionMySQL($host, $user, $pass, $data, 1, "search");
$searchDao = new SearchDao($connection, $str_id);
$searchDao->addUser2Search($str_id, $idPost, $postTime);
	if (isset($_POST['ISQUEST'])){
		/* Заголовок*/
		$searchDao->addQuestHead2Search(stripslashes($str_head), $idPost, $postTime);
		/* Тело*/
		$searchDao->addQuestBody2Search(stripslashes($str_body),$idPost, $postTime);
	}else{
		/* Заголовок*/
		$searchDao->addHead2Search(stripslashes($str_head), $idPost, $postTime);
		/* Тело*/
		$searchDao->addBody2Search(stripslashes($str_body),$idPost, $postTime);
	}
}

/* Выбираем подписки на ветку.*/
$sql_subs="
SELECT
   fd_subscribe.user,
   users.mail,
   fd_subscribe.kod
FROM
   fd_subscribe
   LEFT JOIN users ON fd_subscribe.user=users.id
WHERE 
title=".$str_idd;
$rslt_subs=fd_query($sql_subs,$conn, "");
// Подготавливаем текст поста.
//bbcode
$str_body=fd_bbcode($_POST['A2']);
// смайлики
$str_body=fd_smiles_mail($str_body);
//цензура
$str_body=stripslashes(fd_cenz($str_body));
// Заголовок
$str_head="<div style='font-family: Arial; font-size: 12pt; font-weight: bold;'>".stripslashes(stripslashes($str_head))."</div><br><br>";
// Вступление.
$strMailHead="<html><head><title></title></head><body bgcolor=#EFEFEF>";
$strMailHead=$strMailHead."Вы получили это сообщение, потому что подписаны на рассылку сообщений на форуме <a href='http://www.diletant.com.ua/forum'>Дилетант</a>. <br>\r\n";
$strMailHead=$strMailHead."Отказаться от подписки на эту ветку вы можете, нажав на эту ссылку: <a href='http://www.diletant.com.ua/forum/delonesubsbymail.php?id='>Отказаться от подписки на ветку</a><br><br><br>\r\n";
$strMailFoot="</p></td></tr></table></body></html>";
// Собираем шапку
$strPostHead="<table border='0' cellpadding='2' cellspacing='0' width='100%'>\r\n";
$strPostHead=$strPostHead."<tr style='background-color:#D1D7DC'>";
$strPostHead=$strPostHead."<td style='border:1px ridge; border-collapse: collapse; padding: 3px; border-color:#f1f7fC;'>\r\n";
$strPostHead=$strPostHead."<span style='font-family: Verdana; font-size: 8pt;'>Автор:&nbsp;</span><span style='font-family: Arial; font-size: 12pt; font-weight: bold;'>".stripslashes(htmlspecialchars($_SESSION['autor']))."</span>\r\n";
$strPostHead=$strPostHead."<span style='font-family: Verdana; font-size: 8pt;'>&nbsp;".chr(149)."&nbsp;Дата:&nbsp;</span>";
$strPostHead=$strPostHead."<span style='font-family: Verdana; font-size: 10pt;'>".substr($lptime,8,2).".".substr($lptime,5,2).".".substr($lptime,2,2)."&nbsp;".substr($lptime,11,2).":".substr($lptime,14,2)."&nbsp;</span>".chr(149)."\r\n";
if (trim($str_ip)==trim($str_dom)){
	$str_dom=substr($str_dom, 0, strrpos($str_dom, '.')+1).'---';
}
else {
	$str_dom='---'.substr($str_dom, strpos($str_dom, '.'));
}
$strPostHead=$strPostHead."</td></tr><tr><td><p style='font-family: Verdana; font-size: 10pt;'>\r\n";
// Пробуем пошинковать...

$strMailCut="";
while (strlen($str_body)>900){
	$strMailCut=$strMailCut.substr($str_body, 0, 900)."\r\n";
	$str_body=substr($str_body, 900);
}
$str_body=$strMailCut.$str_body;
$str_body=nl2br($str_body);

$strMailAll=$strMailHead.$strPostHead.$str_head.$str_body.$strMailFoot;
for ($x=0;$x < mysql_num_rows($rslt_subs);$x++){
	// Вставляем код.
	$strMailAll=str_replace("http://www.diletant.com.ua/forum/delonesubsbymail.php?id=","http://127.0.0.1/forum/delonesubsbymail.php?id=".mysql_result($rslt_subs, $x, 'kod'),$strMailAll);
	$server="smtp.freehost.com.ua";
	$from="diletant@diletant.com.ua";
	$subject="Рассылка форума Дилетант";
	$headers='Content-type: text/html; charset="windows-1251"';
	$headers="From: ".$from."\nSubject: ".$subject."\nX-Mailer: Diletant\n".$headers;
	// Отправляем
	mail(mysql_result($rslt_subs, $x, 'mail'), $subject, $strMailAll, $headers);
}
$sql_watch="
SELECT
   user
FROM
   fd_watch" .
" WHERE user=".$str_id;
//echo $sql_watch;
$rslt_watch=fd_query($sql_watch,$conn, "");
if (mysql_num_rows($rslt_watch)>0){
	//   $strMailAll=str_replace("http://www.diletant.com.ua/forum/delonesubsbymail.php?id=","http://127.0.0.1/forum/delonesubsbymail.php?id=".mysql_result($rslt_subs, $x, 'kod'),$strMailAll);
	$server="smtp.freehost.com.ua";
	$from="diletant@diletant.com.ua";
	$subject="Watched nicks from diletant";
	$headers='Content-type: text/html; charset="windows-1251"';
	$headers="From: ".$from."\nSubject: ".$subject."\nX-Mailer: Diletant\n".$headers;
	// Отправляем
	mail("andrew@sunbay.com", $subject, $strMailAll, $headers);
	mail("an.home@mail.ru", $subject, $strMailAll, $headers);
}
?>