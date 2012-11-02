<?
// Обработка Добавление нового варианта ответа
   session_start();
   include("query.php");
//Предотвращаем кеширование
   include("cache.php");
// Соединяемся
   include('setup.php');
// Собираем статистику
   $action=12;
   include("stat.php");
//
   $n0=stripslashes($_POST['IDU2']);
   if (isset($_POST['PS12'])) {
      $query = "SELECT pass, nick FROM users where id=".$n0;
      $pp2=$_POST['PS12'];
      $result = fd_query($query, $conn, "");
      $ppp=mysql_result($result, 0, 'pass');
   }
   else
   {
      $query = "SELECT pass2, nick FROM users where id=".$n0;
      $pp2=$_POST['PS22'];
      $result = fd_query($query, $conn, "");
      $ppp=mysql_result($result, 0, 'pass2');
   }
// Вдруг сюда попали незарегистрировавшись?
   if (!mysql_num_rows($result)==0 and $pp2==$ppp ){
// Все нормально
// Может пустая??
   if (!(trim($_POST['P'])=="")) {
// Не пустая
   $str_id=$n0;
//Максимальный номер варианта
   $sql_max="select max(numb) as mx from quest where head=".$_POST['IDT2'];
   $resultmax = fd_query($sql_max, $conn, "");
   $str_numb=mysql_result($resultmax, 0, 'mx')+1;
// Анонимно? 1 - нет, 2 - да
   $str_type=1;
   if (isset($_POST['HD'])) $str_type=2;
// Добавляем ответ
   $str_post=mysql_real_escape_string(trim($_POST['P']));
   $sql_ins="insert into quest (head, numb, node, gol, type, user) values ('".$_POST['IDT2']."', '$str_numb','$str_post', 1, '$str_type', '".$_POST['IDU2']."')";
   $cvcv=fd_query($sql_ins, $conn, "");
// определяем его id
   $sql_id="select id from quest where head=".$_POST['IDT2']." and numb='$str_numb'";
   $resultid=0;
   $resultid=fd_query($sql_id, $conn, "");
   $str_id=mysql_result($resultid, 0, 'id');
// Добавляем голос
   $sql_voice="insert into voice (head, node, user) values ('".$_POST['IDT2']."', '$str_id', '".$_POST['IDU2']."')";
   $cvcv=fd_query($sql_voice, $conn,"");
   $lptime=date('Y-m-d H:i:s');
 // Обновляем заголовок
   $query="UPDATE titles set lposttime='".$lptime."', lpostnick='Добавлен голос' WHERE id=".$_POST['IDT2'];
   $cvcv=fd_query($query, $conn, "");
   mysql_close($conn);
// Отправляем в форум
   header ("Location: index.php");
   }
   else
// Пустая
   {
   echo "<html>";
   echo "<head>";
   echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
   echo "<meta http-equiv='Refresh' content='5; url=index.php'>";
// Стили
   echo "<title>";
   echo "Мы не во всем Дилетанты!";
   echo "</title>";
   echo "</head>";
// Цвет фона страницы
   echo "<body bgcolor=#EFEFEF>";
   echo "<font size='5'><b>Шо, думаешь написано Дилетант, так тут все просто? Писать надо не только пробелами!</b></font>";
   echo "</body>";
   echo "</html>";
   mysql_close($conn);
   }}
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