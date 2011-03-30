<?
// выбираем варианты ответов
      $query_quest="
      SELECT
         quest.id, 
         quest.node, 
         quest.user, 
         quest.gol, 
         quest.type, 
         users.nick 
      FROM 
      quest 
      left join users on quest.user=users.id 
      WHERE 
         quest.head=".$str_t_id." 
      ORDER BY 
         numb";
      $resq1=fd_query($query_quest,$conn, "");
      $strq_quest=stripslashes(mysql_result($resq1, 0, 'node'));
      $strqnumb=mysql_num_rows($resq1);
// выбираем список проголосовавших
      if (isset($_SESSION['idu'])){
         $query_quest2="
         SELECT 
            user 
         FROM 
            voice 
         WHERE 
            head=".$str_t_id." 
            AND user=".$_SESSION['idu'];
         $resq2=fd_query($query_quest2,$conn, "");
         $strq2numb=mysql_num_rows($resq2);
      }
// Сколько голосов всего
      $sql_nvcs="
      SELECT 
         COUNT(id) AS nvcs 
      FROM 
         voice 
      WHERE 
         head=".$str_t_id;
      $resqnvc=fd_query($sql_nvcs, $conn, "");
      $nvcs=mysql_result($resqnvc, 0, 'nvcs');
// Вопрос
   echo("<tr><td>");
      echo "<p align=\"CENTER\"><font size=4><b>".$strq_quest."</b></font></p><br>";
      echo("</td></tr>");
      echo("<tr><td align=\"CENTER\">");
//Если зарегистрировался и не голосовал
      if (isset($_SESSION['autor']) and !$strq2numb){
      echo("<form  action='voice.php' method='POST'><table class=content>");
      for ($iq1=1; $iq1<$strqnumb; $iq1++){
          echo("<tr><td class=voice_left align='right'>");
// Передаем id ответа как значение выбора
          $in1=mysql_result($resq1, $iq1, 'id');
// Первый по умолчанию
          $check="";
          if ($iq1==1) $check=" CHECKED";
// Авторский вариант?
          if (mysql_result($resq1, $iq1, 'type')){
// Нет
             echo "Вариант предложен:&nbsp;";
// Автор ответа себя показывает?
             if (mysql_result($resq1, $iq1, 'type')==1){
// Да
                echo "<b>".mysql_result($resq1, $iq1, 'nick')."</b>";
             }
             else{
// Аноним
                echo "<b>Анонимно</b>";
             }
//Пользовательский вариант ответа       
             echo "</td><td class=voice_right align='left'>";
             echo("<input type='radio' name='ANSWER' value='$in1'>&nbsp;".fd_smiles(fd_href(stripslashes(mysql_result($resq1, $iq1, 'node'))))."<br>");
          }
          else {
//Авторский вариант ответа
             echo "</td><td class=voice_right align='left'>";
             echo("<input type='radio' name='ANSWER' value='$in1'".$check.">&nbsp;".fd_smiles(fd_href(stripslashes(mysql_result($resq1, $iq1, 'node'))))."<br>");
          }
          echo("</td></tr>");
      }
             echo("<tr><td colspan='2' align='CENTER'>");
// Прередаем нужные пареметры...
// Автор
             echo "<input type=hidden name=\"IDU1\" size=\"20\" value=\"".$_SESSION['idu']."\">";
             echo "<input type=hidden name=\"AUT1\" size=\"20\" value=\"".$_SESSION['autor']."\">";
// id темы
             echo "<input type=hidden name=\"IDT1\" size=\"20\" value=\"".$gid."\">";
// пароль автора
             if (isset($_SESSION['pass2'])) {
// кука
                echo "<input type=hidden name=\"PS21\" size=\"20\" value=\"".$_SESSION['pass2']."\">";
             }
             else {
// не кука
                echo "<input type=hidden name=\"PS11\" size=\"20\" value=\"".$_SESSION['pass1']."\">";
             }
// Кнопка
             echo("<input type=\"submit\" value=\"Проголосовать\" name=\"OK\">");
             echo("</td></tr>");
             echo("</table></form>");     
      echo("</td></tr>");
// Опрос допускает варианты ответа пользователя
   if (isset($_SESSION['autor']) and $str_type==2 and $pg==1 and $i==0){
// Ищем его в авторах имеющихся постов
      $iq3=0;
      for ($iq2=1; $iq2<$strqnumb; $iq2++){
          if (mysql_result($resq1, $iq2, 'user')==$_SESSION['idu']) $iq3=1;
      }
      if (!$iq3){
      echo("<tr><td>");
      echo("<form  action=\"uservoice.php\" method=\"POST\"><table align=\"CENTER\">");
      echo("<tr><td>");
      echo("Ваш вариант ответа:<br>");
      echo("<input type=\"text\" name=\"P\" size=\"100\">");
// Прередаем нужные параметры...
// Автор
      echo "<input type=hidden name=\"IDU2\" size=\"20\" value=\"".$_SESSION['idu']."\">";
      echo "<input type=hidden name=\"AUT2\" size=\"20\" value=\"".$_SESSION['autor']."\">";
// id темы
      echo "<input type=hidden name=\"IDT2\" size=\"20\" value=\"".$gid."\">";
// пароль автора
      if (isset($_SESSION['pass2'])) {
// кука
         echo "<input type=hidden name=\"PS22\" size=\"20\" value=\"".$_SESSION['pass2']."\">";
      }
      else {
// не кука
         echo "<input type=hidden name=\"PS12\" size=\"20\" value=\"".$_SESSION['pass1']."\">";
             }
// /Строка и ячейка таблицы формы
      echo("</td></tr>");
// Строка и ячейка таблицы формы
      echo("<tr><td align=\"CENTER\">");
      echo("<input type=\"checkbox\" name=\"HD\" value=\"1\" checked>&nbsp;Скрыть автора варианта<br>");
      echo("<input type=\"submit\" value=\"Проголосовать\" name=\"OK\">");
// /Строка и ячейка таблицы формы
      echo("</td></tr>");
// /Форма и таблица формы
      echo("</table></form>");
// /Строка и ячейка таблицы форума
      echo("</td></tr>");

   }}
          }
if (!$nvcs) $nvcs=1/10000000;
// Строка и ячейка таблицы форума
      echo("<tr><td align=\"CENTER\">");
echo "<b>Всего голосов: ".round($nvcs,0)."</b>";
// /Строка и ячейка таблицы форума
      echo("</td></tr>");
// Строка и ячейка таблицы форума
      echo("<tr><td align=\"CENTER\">");
// Таблица результатов голосования
          echo "<table align='CENTER' class=control>";
              echo("<tr class=heads><th class='internal'>");
              echo "Автор варианта";
              echo("</th><th class='internal'>");
              echo "Вариант";
              echo("</th><th class='internal'>");
              echo "Голосов";
              echo("</th><th class='internal' width='350'>");
              echo "Рейтинг";
              echo("</th><th class='internal'>");
              echo "Доля";
              echo("</th></tr><tr>");
          for ($iq11=1; $iq11<$strqnumb; $iq11++){

// Авторский вариант?
          if (mysql_result($resq1, $iq11, 'type')){
// Нет
// Автор ответа себя показывает?
             if (mysql_result($resq1, $iq11, 'type')==1){
// Да
                echo "<td align='LEFT' class='internal'>".mysql_result($resq1, $iq11, 'nick')."</td>";
             }
             else{
// Аноним
                echo "<td align='LEFT' class='internal'>Анонимно</td>";
             }}
             else
             {
                echo "<td align='LEFT' class='internal'></td>";
             }
             echo("<td class='internal'>".fd_body(stripslashes(mysql_result($resq1, $iq11, 'node')))."</td>");

              echo("<td align='CENTER' class='internal'>");
              echo(mysql_result($resq1, $iq11, 'gol')."</td>");
              echo "<td class='internal'><TABLE cellSpacing=0 cellPadding=0 width='".round((mysql_result($resq1, $iq11, 'gol')/$nvcs)*300,0)."' border=0><TR><TD align=left bgColor=red><FONT size=-3>&nbsp;</FONT></TD></TR></TABLE>";
              echo("</td>");
              echo("<td class='internal'>");
              echo(round((mysql_result($resq1, $iq11, 'gol')/$nvcs)*100, 2))."%";
              echo("</td></tr>");

          }
// /Таблица результатов голосования
          echo "</table>";
          if (isset($strq2numb) and $strq2numb){
// Форма
             echo "<form method=\"POST\" action=\"delvoice.php\" align=\"CENTER\">";
// Прередаем нужные параметры...
// Автор
      echo "<input type=hidden name=\"IDU\" size=\"20\" value=\"".$_SESSION['idu']."\">";
      echo "<input type=hidden name=\"AUT\" size=\"20\" value=\"".$_SESSION['autor']."\">";
// id темы
      echo "<input type=hidden name=\"IDT\" size=\"20\" value=\"".$gid."\">";
// пароль автора
      if (isset($_SESSION['pass2'])) {
// кука
         echo "<input type=hidden name=\"PS2\" size=\"20\" value=\"".$_SESSION['pass2']."\">";
      }
      else {
// не кука
         echo "<input type=hidden name=\"PS1\" size=\"20\" value=\"".$_SESSION['pass1']."\">";
             }

             echo "<input type=\"SUBMIT\" value=\"Отменить свой голос\">";
// /Форма
             echo "</form>";
          }
// /Строка и ячейка таблицы форума
      echo("</td></tr>");
?>