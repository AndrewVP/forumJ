<?
// Соединяемся
   include("query.php");
   echo $_POST['pass']."<br/>";
   include('setup.php');
   if (isset($_POST['pass']) and $_POST['pass']=='cjkysirj'){
      if ($_POST['type']==0){
         $rbrSql="
         INSERT INTO
            sd_rubriks
            (
            r_name,
            colon,
            b_date
            )
         VALUES
            (
            '".$_POST['new_rubr']."',
            '".$_POST['new_colmn']."',
            NOW()
            )   
         ";
         $rbrRslt=fd_query($rbrSql, $conn, ""); 
         echo "Рубрика добавлена<br>";
         $idRubr=mysql_insert_id($conn);
      }
      else {
         $idRubr=$_POST['type'];
      }
      $postSql="
      INSERT INTO
         fd_site
         (
         title,
         head,
         body,
         auth,
         time_post,
         rubr,
         time_site
         )
      VALUES
         (
         '".mysql_real_escape_string($_POST['head'])."',
         ".$_POST['idd'].",
         '".mysql_real_escape_string($_POST['body'])."',
         ".$_POST['auth'].",
         '".$_POST['time_']."',
         ".$idRubr.",
         NOW()
         )   
      ";
      $postResult=fd_query($postSql, $conn, ""); 
      echo "Пост добавлен<br>";
   }
   else {
      echo "Пароль не опознан.";
   }
   mysql_close($conn);
?>