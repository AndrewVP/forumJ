<?
   // обработка удаление подписки.
   session_start();
   include("query.php");
   // Функция проверки авторизации
   include('guard.php');
   //Предотвращаем кеширование
   include("cache.php");
   // Соединяемся с MySql
   include('setup.php');
   // Собираем статистику
   $action=33;
   include("stat.php");
   //
   $n0=stripslashes($_POST['IDU']);
   // Вдруг сюда попали незарегистрировавшись?
   if (fd_guard($n0, $conn)){
      // все нормально.
      switch($_POST['ACT']) {
         // Удаление
         case 'del':
            for ($x1=0; $x1<$_POST['NRW']; $x1++){
               // Выбираем папки на удаление
               if (isset($_POST[$x1])){
                  // Удаляем связь тем с папками
                  $sql_delsubs="
                  DELETE FROM
                     fd_subscribe
                  WHERE
                     id=".$_POST[$x1]."
                     AND user=".$n0."  
                  ";
                  $rslt_delsubs=fd_query($sql_delsubs, $conn, "");
               }
            }
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
            break;
         // Добавление папок в интерфейс
      }
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