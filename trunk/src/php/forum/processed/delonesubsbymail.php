<?
   // обработка удаление подписки через мыло.
   session_start();
   include("query.php");
   // Функция проверки авторизации
   include('guard.php');
   //Предотвращаем кеширование
   include("cache.php");
   // Соединяемся с MySql
   include('setup.php');
   // Собираем статистику
   $action=36;
   include("stat.php");
   //
      $sql_delsubs="
      DELETE FROM
         fd_subscribe 
      WHERE
         kod=".$_GET['id'];
      $rslt_delsubs=fd_query($sql_delsubs, $conn, "");
      echo "<html>";
      echo "<head>";                                                               
//      echo "<meta http-equiv='Refresh' content='0; url=http://www.diletant".$add."#subs'>";
      echo "<title>";
      echo "Форум Дилетант";
      echo "</title>";
      echo "</head>";
      echo "<body>";
      echo "Информация о рассылке удалена";
      echo "</body>";
      echo "</html>";
?>