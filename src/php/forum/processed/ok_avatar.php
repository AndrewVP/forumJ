<?
// Соединяемся
   include('setup.php');
   include("query.php");
   $avatarSql="
   SELECT
      avatar,
      s_avatar,
      ok_avatar
   FROM
      users
   WHERE
      id=".$_GET['qqnn'];
   $avatarRslt=fd_query($avatarSql, $conn, "");         
   
      echo "<html><head><title></title><meta http-equiv='content-type' content='text/html; charset=windows-1251'></head><body><table><tr><td>";
      echo "<form action='s_avatar.php?qqnn=".$_GET['qqnn']."' method='post'>";
      echo "<input size='100' type='text' name='avatar' value='".trim(htmlspecialchars(stripslashes(mysql_result($avatarRslt, 0, 'avatar'))))."'><br><br><br>";
      if (mysql_result($avatarRslt, 0, 's_avatar')){
         echo "<input type=checkbox checked  name='s_avatar'>&nbsp;Показывать<br><br>";   
      } else {
         echo "<input type=checkbox  name='s_avatar'>&nbsp;Показывать<br><br>";   
      }
      echo "<input type=checkbox  name='ok_avatar'>&nbsp;Разрешить<br><br>";   
      echo "<input type='password' name=pass>";
      echo "<input type='submit'>";
      echo "</form></td></tr></table></html>";
?>