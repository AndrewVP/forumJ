<?
// Обработка удаление голоса
   session_start();
   include("query.php");
//Предотвращаем кеширование
   include("cache.php");
// Соединяемся
   include('setup.php');
// Собираем статистику
   $action=15;
   include("stat.php");
//
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
//      echo $query;
      $pp2=$_POST['PS2'];
      $result = fd_query($query, $conn, "");
      $ppp=mysql_result($result, 0, 'pass2');
   }
// Вдруг сюда попали незарегистрировавшись?
   if (!mysql_num_rows($result)==0 and $pp2==$ppp ){
// Все нормально
// Определяем id ответа
       $idt=$_POST['IDT'];
       $sql_voice="select id, node from voice where user=$n0 and head=$idt";
       $result1 = fd_query($sql_voice, $conn, "");
       $node=mysql_result($result1, 0, 'node');
       $sql_gol="update quest set gol=gol-1 where id=$node";
       $cvcv=fd_query($sql_gol, $conn, "");
       $delvoice=mysql_result($result1, 0, 'id');
       $sql_delvoice="delete from voice where id=$delvoice";
       $cvcv=fd_query($sql_delvoice, $conn, "");
       mysql_close($conn);
       header ("Location: index.php");
   }
   else
// Вошли незарегистрировавшись
   {
   echo "<html>";
   echo "<head>";
   echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
   echo "<meta http-equiv='Refresh' content='5; url=auth.php?id=4.php'>";
// Стили
   echo "<title>";
   echo "Мы не во всем Дилетанты!";
   echo "</title>";
   echo "</head>";
// Цвет фона страницы
   echo "<body bgcolor=#EFEFEF>";
   echo "<font size='5'><b>Входить надо как все нормальные люди!</b></font>";
   echo "</body>";
   echo "</html>";
   mysql_close($conn);
   }
?>