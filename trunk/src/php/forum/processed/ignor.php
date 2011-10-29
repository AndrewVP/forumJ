<?
// Обработка добавление нового в игнор-лист
   session_start();
   include("query.php");
//Предотвращаем кеширование
   include("cache.php");
// Соединяемся с MySql
   include('setup.php');
// Собираем статистику
   $action=20;
   include("stat.php");
// Принимаем переданные данные
// id Игнорируемого
   if (isset($_GET['idi']) and $_GET['idi']!=""){
      $idi=$_GET['idi'];
   }
   else {
      header("Location: index.php");
      exit;
   }
// id Темы, откуда пришли
   if (isset($_GET['idt']) and $_GET['idt']!=""){
      $idt=$_GET['idt'];
   }
   else {
      header("Location: index.php");
      exit;
   }
// id Поста игнорируемого
   if (isset($_GET['idp']) and $_GET['idp']!=""){
      $idp=$_GET['idp'];
   }
   else {
      header("Location: index.php");
      exit;
   }
// № Страницы
   if (isset($_GET['pg']) and $_GET['pg']!=""){
      $pg=$_GET['pg'];
   }
   else {
      header("Location: index.php");
      exit;
   }
// Вносим игнор в таблицу, по умолчанию - 7 дней
   $sql_ign="insert into ignor (type, user, ignor, begin, end) values (1, ".$_SESSION['idu'].", ".$idi.", now(), adddate(now(), INTERVAL 7 DAY))";
   $result=fd_query($sql_ign, $conn, "");
   mysql_close($conn);
// Отправляем обратно
   echo "<html>";
   echo "<head>";
   echo "<meta http-equiv='Refresh' content='0; url=tema.php?id=".$idt."&page=".$pg."#".$idp."'>";
   echo "<title>";
   echo "</title>";
   echo "</head>";
   echo "<body>";
   echo "</body>";
   echo "</html>";
?>