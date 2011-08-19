<?
         echo "<div class=mnuprof align='CENTER'><b>".$_mess16."</b></div>";
         echo "<form method='POST' class=content action='delmail.php?id=4'>";
         echo "<table class='control'><tr class=heads>";
         // Выбираем почту
         $sql_mail="
         SELECT
            fdmail.id,
            fdmail.head,
            DATE_FORMAT(fdmail.d_snt, '%d.%m %H:%i') as d__snt,
            DATE_FORMAT(fdmail.d_rcv, '%d.%m %H:%i') as d__rcv,
            users.nick
         FROM
            fdmail
            LEFT JOIN users ON fdmail.rcvr=users.id
         WHERE
            fdmail.sndr=".$_SESSION['idu']." 
            AND fdmail.del_s<>1
            AND fdmail.d_rcv IS NOT NULL
         ORDER BY
            fdmail.d_snt DESC
         ";
         $res_mail=fd_query($sql_mail, $conn, "");
         $numrows=mysql_num_rows($res_mail);
         if ($numrows){
         // Заголовки таблицы
         // Кому
         echo "<th class='internal' width='120'><div class=tbtext>".$_mess19."</div></th>";
            // Тема письма
         echo "<th class='internal'><div class=tbtext>".$_mess59."</div></th>";
            // Когда отправлено.
         echo "<th class='internal' width='120'><div class=tbtext>".$_mess61."</div></th>";
            // Когда получено.
         echo "<th class='internal' width='120'><div class=tbtext>".$_mess60."</div></th>";
         echo "<th class='internal' width='20'></th>";
         echo "</tr>";
         // Выводим сообщения
         for ($m1=0; $m1 < $numrows; $m1++) {
            // id письма
            $str_id=mysql_result($res_mail, $m1, 'id');
            // Тема письма
            $str_head=mysql_result($res_mail, $m1, 'head');
            // Кому
            $str_nick=mysql_result($res_mail, $m1, 'nick');
            // Когда отправлено.
            $str_snt=mysql_result($res_mail, $m1, 'd__snt');
            // Когда получено.
            $str_rcv=mysql_result($res_mail, $m1, 'd__rcv');
            echo "<tr>";
            // Кому
            echo "<td class='internal' width='120'><div class=tbtext>";
            echo $str_nick;
            echo "</div></td>";
            // Тема письма
            echo "<td class='internal'><div class=tbtext>";
            echo "<a href='control.php?id=4&msg=".$str_id."'>".fd_head($str_head)."</a>";
            echo "</div></td>";
            // Когда отправлено.
            echo "<td class='internal' width='120'><div class=tbtext>";
            echo $str_snt;
            echo "</div></td>";
            // Когда получено.
            echo "<td class='internal' width='120'><div class=tbtext>";
            echo $str_rcv;
            echo "</div></td>";
            // Флажок.
            echo "<td class='internal'><div align='center' class=tbtext>";
            echo '<input type="checkbox" name="'.$m1.'" value="'.$str_id.'">';
            echo "</div></td>";       
            echo "</tr>";
         }
         // Сервис (пока только удаление)
         echo "<tr>";
         echo "<td colspan=5 align='right'>"; 
         echo "<span class=tbtextnread>".$_mess69."&nbsp;&nbsp;</span>";
         echo "<select size='1' name='ACT'>";
         echo "<option selected value='del'><span class=mnuprof>".$_mess70."&nbsp;&nbsp;</span></option>";
         echo "</select>&nbsp;";
         echo '<input type="hidden" value="'.$numrows.'" name="NRW">';
         // Прередаем нужные пераметры...
         // Автор
         echo "<input type=hidden name='IDU' value='".$_SESSION['idu']."'>";
         echo "<input type=hidden name='AUT' value='".$_SESSION['autor']."'>";
         // пароль автора
         if (isset($_SESSION['pass2'])) {
            // кука
            echo "<input type=hidden name='PS2' value='".$_SESSION['pass2']."'>";
         }
         else
         {
            // не кука
            echo "<input type=hidden name='PS1' value='".$_SESSION['pass1']."'>";
         }
         echo '<input value="OK" type="submit">';
         echo "</td>";
         echo "</tr>";
            }
            else {
               echo "<th class='internal'><div class=tbtext>".$_mess18."</div></th>";
               echo "</tr>";
            }
// Выводим письмо
            if (isset($_GET['msg']) and $_GET['msg']<>""){
               // Находим его
               $sql_msg="
               SELECT
                  fdmail.head,
                  fdmail.body,
                  DATE_FORMAT(fdmail.d_snt, '%d.%m %H:%i') as d__snt,
                  DATE_FORMAT(fdmail.d_rcv, '%d.%m %H:%i') as d__rcv,
                  users.nick
               FROM
                  fdmail
                  LEFT JOIN users ON fdmail.rcvr=users.id
               WHERE
                  fdmail.sndr=".$_SESSION['idu']." AND
                  fdmail.del_s<>1 AND
                  fdmail.d_rcv IS NOT NULL
                  AND fdmail.id=".$_GET['msg']."
               ";
               $rslt_msg=fd_query($sql_msg, $conn, "");
               if (mysql_num_rows($rslt_msg)){
                  // Принимаем.     
                  // id письма
                  $str_id=$_GET['msg'];
                  // Отправлено.
                  $str_snt=mysql_result($rslt_msg, 0, 'd__snt');
                  // Получено.
                  $str_rcv=mysql_result($rslt_msg, 0, 'd__rcv');
                  // Кому.
                  $str_nick=mysql_result($rslt_msg, 0, 'nick');
                  // Заголовок.
                  $str_head=mysql_result($rslt_msg, 0, 'head');
                  // Тело.
                  $str_body=mysql_result($rslt_msg, 0, 'body');
                  echo "<tr class=heads><td colspan=4 class=internal>";
                  // Кому.
                  echo "<span class=tbtext>".$_mess60.":&nbsp;".$str_rcv."&nbsp;".$_mess61.":&nbsp;".$str_snt."&nbsp;".$_mess28.":&nbsp;</span><span class=nick>".$str_nick."</span>";
                  echo "</td></tr>";
                  // Тело.
                  echo "<tr><td colspan=4 class=internal>";
                  echo "<div class=nik>".fd_head($str_head)."</div>";
                  echo "<div class=post>".fd_body($str_body)."</div>";
                  echo "</td></tr>";
               }
            }
         echo "</table>";
         echo "</form>";
?>