<?
// Соединяемся
   include('setup.php');
   include("query.php");
   $topicSql="
   SELECT
      auth,
      tilte,
      body,
      reg
   FROM
      body
   WHERE
      id=".$_GET['post'];
   $topicRslt=fd_query($topicSql, $conn, "");         
   
      echo "<html><head><title></title width='100%'></head><body><table><tr><td>";
      echo "<form action='editor.php' method='post'>";
      echo "<input size='140' type='text' name='head' value='".stripslashes(mysql_result($topicRslt, 0, 'tilte'))."'><br><br><br>";
      echo "<textarea name='body' cols=100 rows=40>".stripslashes(mysql_result($topicRslt, 0, 'body'))."</textarea><br><br>";
// Выбираем рубрики.
      $rubrSql="
      SELECT
         id,
         r_name,
         colon         
      FROM
         sd_rubriks
      ORDER BY
         colon,
         r_name      
      ";
      $rubrRslt=fd_query($rubrSql, $conn, ""); 
      while($row=mysql_fetch_row($rubrRslt)) {
         echo "<input type='radio' name='type' value='".$row[0]."'><b>".$row[1]."</b>&nbsp;Колонка:&nbsp;<b>".$row[2]."</b><br>";
      }
      echo "<input type='radio' name='type' value='0'>&nbsp;Заголовок:&nbsp;<input size='40' type='text' name='new_rubr'>&nbsp;Колонка:&nbsp;<input size='40' type='text' name='new_colmn'><br>";
      echo "<input size='40' type='password' name='pass'><br>";
      echo "<input type='hidden' name='idd' value='".$_GET['id']."'>";
      echo "<input type='hidden' name='auth' value='".mysql_result($topicRslt, 0, 'auth')."'>";
      echo "<input type='hidden' name='time_' value='".mysql_result($topicRslt, 0, 'reg')."'>";
      echo "<input type='submit' value='Разместить'>";
      echo "</form></td></tr></table></html>";
?>