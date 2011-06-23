<?php

include("timezone.php");
//$host='db2.freehost.com.ua'; //
$host='db2.freehost.com.ua'; // Хост MySQL
$user='diletant';      // USER
$pass='gjA2NjPGMl';  // PASS
$data='diletant';     // Database
$pp='30';            //
$pt='40';            //
if (!isset($_SESSION['idu'])) {
   $_SESSION['pp']=30;
   $_SESSION['pt']=40;
   $_SESSION['def_view']=1;
}
$conn = @mysql_connect($host,$user,$pass);
while (!$conn){
      sleep(5);
	 $conn=@mysql_connect($host,$user,$pass);
}
$sql_1251="
SET NAMES cp1251;
";
mysql_query($sql_1251, $conn)
    or die(mysql_error());

$db=mysql_select_db($data, $conn)
    or die(mysql_error());

while (!$db){
	 $db=mysql_select_db($data, $conn)
	     or die(mysql_error());

      sleep(5);
	}
   $str_v_avatars=1;
if (isset($_SESSION['idu'])) {
   $sql_vavatar="
   SELECT
      v_avatars,
      fd_timezone
   FROM
      users
   WHERE
      id=".$_SESSION['idu'];
   $rslt_vavatar=mysql_query($sql_vavatar, $conn)
       or die(mysql_error());

   while (!$rslt_vavatar){
     sleep(5);
     $rslt_vavatar=mysql_query($sql_vavatar, $conn)
         or die(mysql_error());

   }
   $str_v_avatars=mysql_result($rslt_vavatar, 0, 'v_avatars');
   $str_fd_timezone_hr=fd_timezone_hr(mysql_result($rslt_vavatar, 0, 'fd_timezone'));
   $str_fd_timezone_mn=fd_timezone_mn(mysql_result($rslt_vavatar, 0, 'fd_timezone'));
}else{
   $str_fd_timezone_hr=0;
   $str_fd_timezone_mn=0;
}
setlocale(LC_ALL, "ru_RU.CP1251");
define("IN_TOP_BODY", 100, true);
define("NOT_IN_TOP_BODY", 200, true);
define("IN_TOP_QUEST_BODY", 300, true);
define("NOT_IN_TOP_QUEST_BODY", 400, true);
define("IN_TOP_HEAD", 500, true);
define("NOT_IN_TOP_HEAD", 600, true);
define("IN_TOP_QUEST", 700, true);
define("NOT_IN_TOP_QUEST_HEAD", 800, true);
$is_search=false;
?>