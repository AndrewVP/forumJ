<?
   include("query.php");
   // обработка подпись.
   session_start();
   // Функция проверки авторизации
   include('guard.php');
   //Предотвращаем кеширование
   include("cache.php");
   // Соединяемся с MySql
   include('setup.php');
   // Собираем статистику
   $action=41;
   include("stat.php");
   //
   $n0=stripslashes($_POST['IDU']);
   // Вдруг сюда попали незарегистрировавшись?
   if (fd_guard($n0, $conn)){
      // все нормально.
      $footer="";
      if (isset($_POST['foot'])){
         $footer=mysql_real_escape_string($_POST['foot']);
      }
         $sql_updfooter="
         UPDATE
            users
         SET
            footer='".$footer."'
         WHERE
            id=".$n0;
         $rslt_updfooter=fd_query($sql_updfooter, $conn, "");
            $add="";
            if (isset($_GET['id'])) $add="?id=".$_GET['id'];
            echo "<html>";
            echo "<head>";                                                               
            echo "<meta http-equiv='Refresh' content='0; url=control.php".$add."'>";
            echo "<title>";
            echo "</title>";
            echo "</head>";
            echo "<body>";
            echo "</body>";
            echo "</html>";
   }
   else {
      $add="";
      if (isset($_GET['id'])) $add="?id=".$_GET['id'];
      echo "<html>";
      echo "<head>";
      echo "<meta http-equiv='Refresh' content='0; url=control.php".$add."'>";
      echo "<title>";
      echo "</title>";
      echo "</head>";
      echo "<body>";
      echo "</body>";
      echo "</html>";
   }
?>