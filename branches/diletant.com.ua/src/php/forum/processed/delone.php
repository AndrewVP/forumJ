<?
   // обработка перенос темы между папками.
   session_start();
   include("query.php");
   //Предотвращаем кеширование
   include("cache.php");
   // Соединяемся с MySql
   include("setup.php");
   // Собираем статистику
   $action=40;
   include("stat.php");
   //
   // Вдруг сюда попали незарегистрировавшись?
   $access=0;
   if (isset($_GET['usr']) && isset($_GET['id']) && $_GET['usr']!="" && $_GET['id']!=""){
      $usr=$_GET['usr'];
      $id=$_GET['id'];
      $access=1;
   }
   if ($access){
      // все нормально.
         // Выбираем темы для переноса
            echo "Удаляется тема id ".$id."<br>";
            // Удаляем предыдущий транзит
            $sql_deltranzit="
            DELETE FROM
               fdtranzit
            WHERE
               title=".$id."
               AND user=".$usr."  
            ";
            $rslt_deltranzit=fd_query($sql_deltranzit, $conn, "");
            // Создаем новый транзит
            $sql_newtranzit="
            INSERT INTO
               `fdtranzit`
               (
               `title`,
               `user`,
               `folder`,
               `d_cr`)
            VALUES   
               (
               ".$id.",
               ".$usr.",
               3,
               NOW()
               )  
            ";
            $rslt_newtranzit=fd_query($sql_newtranzit, $conn, "");
      $add="";
      if (isset($_GET['page'])) $add="?page=".$_GET['page'];
      echo "<html>";
      echo "<head>";                                                               
      echo "<meta http-equiv='Refresh' content='0; url=index.php".$add."'>";
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