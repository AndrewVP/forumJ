<?
// обработка изменение игнора.
   session_start();
   include("query.php");
//Предотвращаем кеширование
   include("cache.php");
// Соединяемся с MySql
   include('setup.php');
// Собираем статистику
   $action=21;
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
//      Игнор тем
      $str_tema=0;
      if (isset($_POST['C1'])) $str_tema=1;
// Обновляем запись
      $sql_upd="UPDATE
                   ignor
                SET
                   end='".$_POST['Y']."-".$_POST['MTH']."-".$_POST['D']." ".$_POST['H'].":".$_POST['M']."',
                   type=$str_tema
                WHERE
                id=".$_POST['IDZ']." and
                user=".$_POST['IDU'];
//       echo $sql_upd;
      $res1=fd_query($sql_upd, $conn, "");
      mysql_close($conn);
// Отправляем в форум
      echo "<html>";
      echo "<head>";
      echo "<meta http-equiv='Refresh' content='0; url=control.php?id=1'>";
      echo "<title>";
      echo "</title>";
      echo "</head>";
      echo "<body>";
      echo "</body>";
      echo "</html>";
   }
?>
