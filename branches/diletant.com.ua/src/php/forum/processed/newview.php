<?
   // обработка добавление папки.
   session_start();
   include("query.php");
   //Предотвращаем кеширование
   include("cache.php");
   // Соединяемся с MySql
   include('setup.php');
   // Собираем статистику
   $action=28;
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
      if (trim($_POST['FOLD'])!=''){
         // Вдруг такой уже есть?
         $sql_srchview="
         SELECT 
            id 
         FROM
            fdviews
         WHERE
            name='".$_POST['FOLD']."'      
            AND user=".$n0."
         ";
//         echo $sql_srchfold;
         $rslt_srchview=fd_query($sql_srchview, $conn, "");
         if (!mysql_num_rows($rslt_srchview)){
            $sql_newview="
            INSERT INTO
               fdviews
               (
               name,
               user,
               d_cr
               )
            VALUES
               (
               '".trim($_POST['FOLD'])."',
               ".$n0.",
               NOW()
               )   
            ";
            $rslt_newview=fd_query($sql_newview, $conn, "");
         }
               echo "<html>";
               echo "<head>";                                                               
               echo "<meta http-equiv='Refresh' content='0; url=control.php?id=6'>";
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
      echo "<meta http-equiv='Refresh' content='0; url=control.php?id=6'>";
      echo "<title>";
      echo "</title>";
      echo "</head>";
      echo "<body>";
      echo "</body>";
      echo "</html>";
   }
?>