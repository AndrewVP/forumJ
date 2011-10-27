<?
   // обработка установление интерфейса по умолчанию
   session_start();
   include("query.php");
   //Предотвращаем кеширование
   include("cache.php");
   // Соединяемся с MySql
   include('setup.php');
   // Собираем статистику
   $action=25;
   include("stat.php");
   //
   // Вдруг сюда попали незарегистрировавшись?
   $n0=stripslashes($_POST['IDU']);
   if (isset($_POST['PS1'])) {
      $query = "SELECT pass, nick FROM users where id=".$n0;
      $pp2=$_POST['PS1'];
      $result = fd_query($query, $conn, "");
      $ppp=mysql_result($result, 0, 'pass');
   }
   else
   {
      $query = "SELECT pass2, nick FROM users where id=".$n0;
      $pp2=$_POST['PS2'];
      $result = fd_query($query, $conn, "");
      $ppp=mysql_result($result, 0, 'pass2');
   }
   if (mysql_num_rows($result) and $pp2==$ppp ){
      // все нормально.
      $sql_defview="
      UPDATE
         users
      SET   
         view_def=".$_POST['DVIEW']."
      WHERE
         id=".$n0."
      ";
      $rslt_defview=fd_query($sql_defview, $conn, "");
      $_SESSION['def_view']=$_POST['DVIEW'];
      echo "<html>";
      echo "<head>";                                                               
      echo "<meta http-equiv='Refresh' content='0; url=control.php?id=6'>";
      echo "<title>";
      echo "</title>";
      echo "</head>";
      echo "<body>";
      echo "</body>";
      echo "</html>";
      }   else {
      $add="";
      if (isset($_GET['id'])) $add="?id=".$_GET['id'];
      if (isset($_GET['view'])) $add=$add."&view=".$_GET['view'];
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