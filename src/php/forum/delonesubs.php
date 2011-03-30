<?
   // обработка добавление подписки.
   session_start();
   include("query.php");
   // Функция проверки авторизации
   include('guard.php');
   //Предотвращаем кеширование
   include("cache.php");
   // Соединяемся с MySql
   include('setup.php');
   // Собираем статистику
   $action=35;
   include("stat.php");
   //
   $n0=stripslashes($_POST['IDU']);
   // Вдруг сюда попали незарегистрировавшись?
   if (fd_guard($n0, $conn)){
      // все нормально.
      $sql_delsubs="
      DELETE FROM
         fd_subscribe 
      WHERE
         user=".$n0."   
         AND title=".$_POST['IDT'];
      $rslt_delsubs=fd_query($sql_delsubs, $conn, "");
      $add="?id=".$_POST['IDT'];
      if (isset($_GET['pg'])) $add=$add."&page=".$_GET['pg'];
      echo "<html>";
      echo "<head>";                                                               
      echo "<meta http-equiv='Refresh' content='0; url=tema.php".$add."#subs'>";
      echo "<title>";
      echo "</title>";
      echo "</head>";
      echo "<body>";
      echo "</body>";
      echo "</html>";
   }
   else {
      echo "<html>";
      echo "<head>";
      echo "<meta http-equiv='Refresh' content='0; url=index.php'>";
      echo "<title>";
      echo "</title>";
      echo "</head>";
      echo "<body>";
      echo "</body>";
      echo "</html>";
   }
?>