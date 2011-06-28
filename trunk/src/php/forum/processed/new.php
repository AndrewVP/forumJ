<?
// Обработка Новая тема
session_start();
// Классы
include_once 'classes/ua/com/diletant/util/time.php';
//Предотвращаем кеширование
include("cache.php");
// Соединяемся
include('setup.php');
   include("query.php");
// Функции обработки поста
include("bbcode_mail.php");
// Собираем статистику
$action=9;
include("stat.php");
   /*Проверяем юзера*/
include("guard.php");
include("lang.php");
include("search_engine.php");
//
$n0=stripslashes($_POST['IDU']);
//$n1=fd_nick($n0, $conn);
$ban=fd_ban($n0, $conn);
if (fd_guard($n0, $conn) && $ban!="1"){
// Все нормально
// Может пустая??
   if (!(trim($_POST['NHEAD'])=="" or trim($_POST['A2'])=="")) {
// Не пустая
      /*Просмотр?*/
   	$threadTime = new Time(time());
      $rgtime = $threadTime->toString("Y-m-d H:i:s");
      $str_ip=$_SERVER['REMOTE_ADDR'];
      $str_dom=gethostbyaddr($str_ip);
      if (isset($_POST['comand']) && $_POST['comand']=="view"){
         include("new_view.php");
      }else{
         include("new_write.php");
      }
   }else{
      // Пустая
      echo "<html>";
      echo "<head>";
      echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
      echo "<meta http-equiv='Refresh' content='5; url=index.php'>";
   // Стили
      echo "<title>";
      echo "Мы не во всем Дилетанты!";
      echo "</title>";
      echo "</head>";
   // Цвет фона страницы
      echo "<body bgcolor=#EFEFEF>";
      echo "<font size='5'><b>Шо, думаешь написано Дилетант, так тут все просто? Писать надо не только пробелами!</b></font>";
      echo "</body>";
      echo "</html>";
      mysql_close($conn);
   }
}else{
// Вошли незарегистрировавшись
   echo "<html>";
   echo "<head>";
   echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
   echo "<meta http-equiv='Refresh' content='5; url=auth.php?id=4.php'>";
// Стили
   echo "<title>";
   echo "Мы не во всем Дилетанты!";
   echo "</title>";
   echo "</head>";
// Цвет фона страницы
   echo "<body bgcolor=#EFEFEF>";
   echo "<font size='5'><b>Входить надо как все нормальные люди!</b></font>";
   echo "</body>";
   echo "</html>";
   mysql_close($conn);
}
?>