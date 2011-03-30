<?
//Обработка добавление нового юзера
session_start();
   include("query.php");
//Предотвращаем кеширование
include("cache.php");
//Соединяемся
include('setup.php');
//Собираем статистику
$action=8;
include("stat.php");
//
//Хакеры :)
$xr1=stripslashes($_POST['R1']);
$xr2=stripslashes($_POST['R2']);
$xr3=stripslashes($_POST['R3']);
$xr22=stripslashes($_POST['R22']);
$xr33=stripslashes($_POST['R33']);
$xr4=stripslashes($_POST['R4']);
//$xr44=mysql_real_escape_string(stripslashes($_POST['R44']));
//echo strlen(trim($xr1));
if (trim($xr1) == "")
{
	// "пустой" ник
	header("Location: reg.php?id=6");
}elseif (!(trim($xr2)==trim($xr22))) {
	// Разные пароли
	header ("Location: reg.php?id=7");
}elseif (!(trim($xr3)==trim($xr33))){
	// Разные адреса
	header ("Location: reg.php?id=8");
}elseif (trim($xr2) == '') {
	// Пустой пароль
	header ("Location: reg.php?id=10");
}elseif (trim($xr4) == '') {
	//Пустой идентификатор
	header ("Location: reg.php?id=11");
}else{
	$xr1=$str_head=str_replace("  "," ",$xr1);
	$xr1=$str_head=str_replace("  "," ",$xr1);
	$xr1=$str_head=str_replace("  "," ",$xr1);
	$xr1=$str_head=str_replace("  "," ",$xr1);
	$xr1=$str_head=str_replace("  "," ",$xr1);
	$xr1=$str_head=str_replace("  "," ",$xr1);
	$xr1=trim($xr1);
	while (substr($xr1,0,1)=="." or substr($xr1,0,1)=="," or substr($xr1,0,1)==":" or substr($xr1,0,1)==";" or substr($xr1,0,1)=='"' or substr($xr1,0,1)=="'" or substr($xr1,0,1)=="`" or substr($xr1,0,1)=="~" or substr($xr1,0,1)=="^" or substr($xr1,0,1)=="*" or substr($xr1,0,1)=="(" or substr($xr1,0,1)==")"){
		if (strlen($xr1)>1){
			$xr1=substr($xr1,1,strlen($xr1)-1);
			$xr1=trim($xr1);
		}else{
			$xr1="";
			break;
		}
	}
	if (trim($xr1) == "," or trim($xr1) == ".") header("Location: reg.php?id=6");
	if (trim($xr1) == "") header("Location: reg.php?id=6");
	while (substr($xr1, strlen($xr1)-1)=="." or substr($xr1, strlen($xr1)-1)=="," or substr($xr1, strlen($xr1)-1)==":" or substr($xr1, strlen($xr1)-1)==";" or substr($xr1, strlen($xr1)-1)=='"' or substr($xr1, strlen($xr1)-1)=="'" or substr($xr1, strlen($xr1)-1)=="*" or substr($xr1, strlen($xr1)-1)=="~" or substr($xr1, strlen($xr1)-1)=="`" or substr($xr1, strlen($xr1)-1)=="^" or substr($xr1, strlen($xr1)-1)=="(" or substr($xr1, strlen($xr1)-1)==")"){
		$xr1=substr($xr1, 0, strlen($xr1)-1);
		$xr1=trim($xr1);
	}
	// Если ничего не осталось :)
	if (trim($xr1) == "") header("Location: reg.php?id=6");
	// Ищем ник
	// Проверяем, вдруг уже есть?
	$query = "SELECT nick, pass, mail FROM users;";
	$result = fd_query($query, $conn, "");
	$num_rows=mysql_num_rows($result);
	$out=0;
	for ($i1=0; $i1<$num_rows; $i1++) {
		$from="аекорсухіІАВЕКМНОРСХ";
		$to="aekopcyxiIABEKMHOPCX";
		if (strtr(mysql_result($result, $i1, 'nick'), $from, $to)==strtr(trim($xr1), $from, $to)){
			$out=1;
			break;
		}
		if (trim(mysql_result($result, $i1, 'mail'))==trim($xr3)){
			$out=2;
			break;
		}
	}
	if($out==0) {
		// Если новый
		$xr1=mysql_real_escape_string($xr1);
		$xr2=mysql_real_escape_string($xr2);
		$xr3=mysql_real_escape_string($xr3);
		$xr22=mysql_real_escape_string($xr22);
		$xr33=mysql_real_escape_string($xr33);
		$xr4=mysql_real_escape_string($xr4);
		$query = "insert into users (nick, pass, mail, pass2, ban) values ('$xr1', '$xr2', '$xr3', '$xr4', 0)";
		$result = fd_query($query, $conn, "");
		$query = "SELECT id FROM users where nick='$xr1';";
		$result = fd_query($query, $conn, "");
		setcookie("user", $xr1, time()+1209600, "/forum", "www.diletant.com.ua" );
		setcookie("idu", mysql_result($result, 0, 'id'), time()+1209600, "/forum", "www.diletant.com.ua" );
		setcookie("pass2", $xr4, time()+1209600, "/forum", "www.diletant.com.ua" );
		setcookie("user", $xr1, time()+1209600, "/forum", "diletant.com.ua" );
		setcookie("idu", mysql_result($result, 0, 'id'), time()+1209600, "/forum", "diletant.com.ua" );
		setcookie("pass2", $xr4, time()+1209600, "/forum", "diletant.com.ua" );
		if (isset($_SESSION['id'])) $t5=$_SESSION['id'];
		session_destroy();
		session_start();
		$_SESSION['autor']=$xr1;
		$_SESSION['idu']=mysql_result($result, 0, 'id');
		$_SESSION['pass1']=$xr2;
		// Установки по умолчанию
		$_SESSION['pp']=30; // количество строк в странице тем форума
		$_SESSION['pt']=40; //количество строк в странице просмотра темы
		$_SESSION['def_view']=1; // Интерфейс по умолчанию

// Отправка письма          
// Вступление.
      $strMailHead="Регистрация на форуме <a href='http://www.diletant.com.ua/forum'>Дилетант</a>. <br>\r\n";
      $strMailHead=$strMailHead."Для того, чтобы активировать свой аккаунт, нажмите на ссылку ниже";
      $strMailHead=$strMailHead."<a href='http://www.diletant.com.ua/forum/activate.php?id=".$activateCode."'>Активировать доступ</a><br><br><br>\r\n";      
	$strMailAll=$strMailHead;
      $strMailCut="";
      while (strlen($strMailAll)>900){
         $strMailCut=$strMailCut.substr($str_body, 0, 900)."\r\n";
         $str_body=substr($str_body, 900);
      }      
      $str_body=$strMailCut.$str_body;
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
		echo "<html>";
		echo "<head>";
		echo "<meta http-equiv='Refresh' content='0; url=index.php'>";
		echo "</head><body></body></html>";
	}elseif ($out==1){
		// А у нас такой уже есть!
		$_SESSION['nick']=$xr1;
		header ("Location: reg.php?id=5");
	}elseif ($out==2){
		// Mail уже есть
		header ("Location: reg.php?id=12");
	}
}
?>