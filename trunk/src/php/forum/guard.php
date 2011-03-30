<?
   // Вдруг сюда попали незарегистрировавшись?
   function fd_guard($n0, $conn){
      if (isset($_POST['PS1'])) {
         $query = "SELECT pass AS pasw, nick FROM users where id=".$n0;
         $pp2=$_POST['PS1'];
      }
      else
      {
         $query = "SELECT pass2 AS pasw, nick FROM users where id=".$n0;
         $pp2=$_POST['PS2'];
      }
      $result = fd_query($query, $conn, "");
      $ppp=mysql_result($result, 0, 'pasw');
      if (mysql_num_rows($result) and $pp2==$ppp ){
         $n1=mysql_result($result, 0, 'nick');
         return $n1;
      }
      else
      {
         return false;
      }
   }
   function fd_ban($n0, $conn){
         $query = "SELECT ban FROM users where id=".$n0;
	      $result = fd_query($query, $conn, "");
      return mysql_result($result, 0, 'ban');
   }
   function fd_nick($n0, $conn){
      if (isset($_POST['PS1'])) {
         $query = "SELECT pass AS pasw, nick FROM users where id=".$n0;
         $pp2=$_POST['PS1'];
      }
      else
      {
         $query = "SELECT pass2 AS pasw, nick FROM users where id=".$n0;
         $pp2=$_POST['PS2'];
      }
      $result = fd_query($query, $conn, "");
      $ppp=mysql_result($result, 0, 'pasw');
      if (mysql_num_rows($result) and $pp2==$ppp ){
         $n1=mysql_result($result, 0, 'nick');
         return $n1;
      }
      else
      {
         return "";
      }
   }
?>