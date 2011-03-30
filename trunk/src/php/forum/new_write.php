<?
$str_id=$n0;
// Добавляем заголовок
   $str_head=mysql_real_escape_string($_POST['NHEAD']);
   $query="
   	INSERT INTO titles 
   		(auth, head, reg, lposttime, lpostnick)
   	VALUES 
   		('$str_id', 
   		'$str_head', 
   		'".$threadTime->toString("Y-m-d H:i:s")."', 
   		'".$threadTime->toString("Y-m-d H:i:s")."', 
   		'".$_POST['AUT']."')";
   fd_query($query, $conn, "");
   $str_idd=mysql_insert_id();
   $str_body=mysql_real_escape_string($_POST['A2']);
// Добавляем Сообщение
	$queryPostTbl = "
		SELECT
			curret_body_table,
			current_body_head_table
		FROM
			fd_setup
	";
	$rsltPostTbl = fd_query($queryPostTbl, $conn, "");
	$currentBodyTable = mysql_result($rsltPostTbl, 0, 'curret_body_table');
	$currentHeadTable = mysql_result($rsltPostTbl, 0, 'current_body_head_table');
   $query="INSERT INTO body
           	(head, fd_state, table_post, table_head)
           VALUES
           	('$str_idd',
           	1,
           	'$currentBodyTable',
           	'$currentHeadTable')";
      $result=0;
      $result=fd_query($query, $conn, ""); 
      $idPost=mysql_insert_id($conn);
   $query="INSERT INTO ".$currentHeadTable."
           	(id, id_post, auth, thread_id, tilte, fd_post_time, ip, domen)
           VALUES
           	('$idPost',
           	'$idPost',
           	'$str_id', 
           	'$str_idd',
           	'$str_head',
           	".$threadTime->getTimestamp().",
           	'$str_ip',
           	'$str_dom')";
      $result=0;
      $result=fd_query($query, $conn, ""); 
	$queryInsBody = "
		INSERT INTO 
			".$currentBodyTable." 
				(id, id_post, body) 
			VALUES 
				('$idPost', '$idPost', '$str_body')
			";
	$rsltInsBody = fd_query($queryInsBody, $conn, "");
		$query="UPDATE 
					titles 
				SET 
					id_last_post=".$idPost."  
				WHERE 
					id=".$str_idd;
      $result=fd_query($query, $conn, ""); 
/* Записываем данные для поиска*/
/* Автор*/
//	fd_new_search_user($str_id, $_POST['AUT'], $idPost, $threadTime, $conn);			
	if ($is_search){
	/* Заголовок*/
		addTopic2Search(stripslashes($str_head), $idPost, $rgtime, $conn, "", "");	
	/* Тело*/
		addTopBody2Search(stripslashes($str_body),$idPost, $rgtime, $conn, "", "");
	}
	
// Подготавливаем текст поста.          
//bbcode
      $str_body=fd_bbcode($_POST['A2']);
// смайлики      
      $str_body=stripslashes(fd_smiles_mail($str_body));
//цензура      
//      $str_body=fd_cenz($str_body);
// Заголовок      
      $str_head="<div style='font-family: Arial; font-size: 12pt; font-weight: bold;'>".stripslashes($str_head)."</div><br><br>";
// Вступление.
      $strMailHead="<html><head><title></title></head><body bgcolor=#EFEFEF>";
      $strMailHead=$strMailHead."Вы получили это сообщение, потому что подписаны на рассылку сообщений на форуме <a href='http://www.diletant.com.ua/forum'>Дилетант</a>. <br>\r\n";
      $strMailHead=$strMailHead."<a href='http://www.diletant.com.ua/forum/site.php?id=".$str_idd."&post=".$idPost."'>Ссылка на форму</a><br><br><br>\r\n";      
      $strMailFoot="</p></td></tr></table></body></html>";
// Собираем шапку
      $strPostHead="<table border='0' cellpadding='2' cellspacing='0' width='100%'>\r\n";
      $strPostHead=$strPostHead."<tr style='background-color:#D1D7DC'>";
      $strPostHead=$strPostHead."<td style='border:1px ridge; border-collapse: collapse; padding: 3px; border-color:#f1f7fC;'>\r\n";
      $strPostHead=$strPostHead."<span style='font-family: Verdana; font-size: 8pt;'>Автор:&nbsp;</span><span style='font-family: Arial; font-size: 12pt; font-weight: bold;'>".stripslashes(htmlspecialchars($_SESSION['autor']))."</span>\r\n";
      $strPostHead=$strPostHead."<span style='font-family: Verdana; font-size: 8pt;'>&nbsp;".chr(149)."&nbsp;Дата:&nbsp;</span>";
      $strPostHead=$strPostHead."<span style='font-family: Verdana; font-size: 10pt;'>".substr($rgtime,8,2).".".substr($rgtime,5,2).".".substr($rgtime,2,2)."&nbsp;".substr($rgtime,11,2).":".substr($rgtime,14,2)."&nbsp;</span>".chr(149)."\r\n";      
      $strPostHead=$strPostHead."<span style='font-family: Verdana; font-size: 8pt;'>&nbsp;Хост:&nbsp;</span><span style='font-family: Verdana; font-size: 10pt;'>".$str_dom."</span>\r\n";
      $strPostHead=$strPostHead."</td></tr><tr><td><p style='font-family: Verdana; font-size: 10pt;'>\r\n";
      $str_body=nl2br($str_body);
      $strMailAll=$strMailHead.$strPostHead.$str_head.$str_body.$strMailFoot;
// Вставляем код.         
         $server="smtp.freehost.com.ua";
         $from="diletant@diletant.com.ua";
         $subject="Тест";
         $headers='Content-type: text/html; charset="windows-1251"';
         $headers="From: ".$from."\nSubject: ".$subject."\nX-Mailer: Diletant\n".$headers;
         mail("an.diletant@mail.ru", $subject, $strMailAll, $headers);
         mail("andrew@sunbay.com", $subject, $strMailAll, $headers);
      mysql_close($conn);
// Отправляем в форум
   echo "<html>";
   echo "<head>";
   echo "<meta http-equiv='Refresh' content='10; url=index.php'>";
   echo "<title>";
   echo "</title>";
   echo "</head>";
   echo "<body>";
   echo "</body>";
   echo "</html>";
?>