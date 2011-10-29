<?
   // обработка удаление папки.
   session_start();
   include("query.php");
   //Предотвращаем кеширование
   include("cache.php");
   // Соединяемся с MySql
   include('setup.php');
   // Собираем статистику
   $action=27;
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
      if ($_POST['ACT']=='del'){
         for ($x1=0; $x1< $_POST['NRW']; $x1++){
            // Выбираем папки на удаление
            if (isset($_POST[$x1])){
               // Удаляем связь папок с представлениями
               $sql_delvtranzit="
               DELETE FROM
                  fdvtranzit
               WHERE
                  folder=".$_POST[$x1]."
                  AND view=".$_POST['IDVW']."
                  AND user=".$n0."  
               ";
               $rslt_delvtranzit=fd_query($sql_delvtranzit, $conn, "");
            }}
               echo "<html>";
               echo "<head>";                                                               
               echo "<meta http-equiv='Refresh' content='0; url=control.php?id=6&view=".$_POST['IDVW']."'>";
               echo "<title>";
               echo "</title>";
               echo "</head>";
               echo "<body>";
               echo "</body>";
               echo "</html>";
      }}
   else {
      echo "<html>";
      echo "<head>";
      echo "<meta http-equiv='Refresh' content='0; url=control.php?id=6&view=".$_POST['IDVW']."'>";
      echo "<title>";
      echo "</title>";
      echo "</head>";
      echo "<body>";
      echo "</body>";
      echo "</html>";
   }
?>