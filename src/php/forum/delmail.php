<?
   // обработка удаление письма.
   session_start();
   include("query.php");
   //ѕредотвращаем кеширование
   include("cache.php");
   // —оедин€емс€ с MySql
   include('setup.php');
   // —обираем статистику
   $action=24;
   include("stat.php");
   //
   // ¬друг сюда попали незарегистрировавшись?
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
         switch($_GET['id']) {
            case 2:
               $_and="AND rcvr=".$n0;
               $_set="del_r=1";
               break;
            case 4:
               $_set="del_s=1";
               $_and="AND sndr=".$n0;
               break;
         }      
         for ($x1=0; $x1<$_POST['NRW']; $x1++){
            if (isset($_POST[$x1])){
               $sql_delmail="
               UPDATE
                  fdmail
               SET
                  ".$_set."   
               WHERE
                  id=".$_POST[$x1]."
                  ".$_and."  
               ";
               $rslt_delmail=fd_query($sql_delmail, $conn, "");
            }}
               echo "<html>";
               echo "<head>";                                                               
               echo "<meta http-equiv='Refresh' content='0; url=control.php?id=".$_GET['id']."'>";
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
      echo "<meta http-equiv='Refresh' content='0; url=index.php'>";
      echo "<title>";
      echo "</title>";
      echo "</head>";
      echo "<body>";
      echo "</body>";
      echo "</html>";
   }
?>