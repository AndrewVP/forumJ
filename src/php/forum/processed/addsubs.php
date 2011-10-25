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
   $action=34;
   include("stat.php");
   //
   $n0=stripslashes($_POST['IDU']);
   // Вдруг сюда попали незарегистрировавшись?
   if (fd_guard($n0, $conn)){
      // все нормально.
      // генерируем уникальный код подписки
      $kod=0;
      while ($kod==0){
         mt_srand();
         $kod=mt_rand(0, 10000000000);
         $sql_rand="
         SELECT
            kod
         FROM
            fd_subscribe 
         WHERE
            kod=".$kod;
         $rslt_rand=fd_query($sql_rand, $conn, "");
         if (mysql_num_rows($rslt_rand)!=0) $kod=0;
      }
      $sql_inssubs="
      INSERT INTO
         fd_subscribe 
         (
         user,
         title,
         d_start,
         kod,
         type)
      VALUES  
         (
         ".$n0.",
         ".$_POST['IDT'].",
         NOW(),
         ".$kod.",
         1
         )
      ";
      $rslt_inssubs=fd_query($sql_inssubs, $conn, "");
      $add="?id=".$_POST['IDT'];
      if (isset($_GET['pg'])) $add=$add."&page=".$_GET['pg'];
      echo "<html>";
      echo "<head>";                                                               
      echo "<meta http-equiv='Refresh' content='0; url=tema.php".$add."#subs'>";
      echo "<title>";
      echo "</title>";
      echo "</head>";
      echo "<body>";
//      echo $add;
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