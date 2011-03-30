<?
         echo "<div class=mnuprof align='CENTER'><b>".$_mess62."</b></div>";
         echo "<table class='control'><tr class=heads>";
         // Выбираем почту
         $sql_mail="
         SELECT
            fdmail.id,
            fdmail.head,
            DATE_FORMAT(fdmail.d_cr, '%d.%m %H:%i') as d__cr,
            users.nick
         FROM
            fdmail
            LEFT JOIN users ON fdmail.rcvr=users.id
         WHERE
            fdmail.sndr=".$_SESSION['idu']." AND
            fdmail.del_s<>1 AND
            fdmail.d_snt IS NULL
         ORDER BY
            fdmail.d_cr DESC
         ";
         $res_mail=fd_query($sql_mail, $conn, "");
         $numrows=mysql_num_rows($res_mail);
         if ($numrows){
         // Заголовки таблицы
            // Кому
         echo "<th class='internal' width='120'><div class=tbtext>".$_mess19."</div></th>";
            // Тема письма
         echo "<th class='internal'><div class=tbtext>".$_mess59."</div></th>";
            // Когда создано
         echo "<th class='internal' width='120'><div class=tbtext>".$_mess20."</div></th>";
         echo "</tr>";
         // Выводим сообщения
         for ($m1=0; $m1 < $numrows; $m1++) {
            // id письма
            $str_id=mysql_result($res_mail, $m1, 'id');
            // Тема письма
            $str_head=mysql_result($res_mail, $m1, 'head');
            // Кому
            $str_nick=mysql_result($res_mail, $m1, 'nick');
            // Когда создано .
            $str_cr=mysql_result($res_mail, $m1, 'd__cr');
            echo "<tr>";
            // Кому
            echo "<td class='internal' width='120'><div class=tbtext>";
            echo $str_nick;
            echo "</div></td>";
            // Тема письма
            echo "<td class='internal'><div class=tbtext>";
            echo "<a href='control.php?id=5&msg=".$str_id."'>".fd_head($str_head)."</a>";
            echo "</div></td>";
            // Когда создано.
            echo "<td class='internal' width='120'><div class=tbtext>";
            echo $str_cr;
            echo "</div></td>";
            echo "</tr>";
            }}
            else {
               echo "<th class='internal'><div class=tbtext>".$_mess18."</div></th>";
               echo "</tr>";
            }
            // Выводим письмо.
            if (isset($_GET['msg']) and $_GET['msg']<>""){
               // Находим его
               $sql_msg="
               SELECT
                  fdmail.head,
                  fdmail.body,
                  DATE_FORMAT(fdmail.d_cr, '%d.%m %H:%i') as d__cr,
                  users.nick
               FROM
                  fdmail
                  LEFT JOIN users ON fdmail.rcvr=users.id
               WHERE
                  fdmail.sndr=".$_SESSION['idu']." AND
                  fdmail.del_s<>1 AND
                  fdmail.d_snt IS NULL
                  AND fdmail.id=".$_GET['msg']."
               ";
               $rslt_msg=fd_query($sql_msg, $conn, "");
               if (mysql_num_rows($rslt_msg)){
                  // Принимаем.     
                  // id письма
                  $str_id=$_GET['msg'];
                  // Создано.
                  $str_cr=mysql_result($rslt_msg, 0, 'd__cr');
                  // Кому.
                  $str_nick=mysql_result($rslt_msg, 0, 'nick');
                  // Заголовок.
                  $str_head=mysql_result($rslt_msg, 0, 'head');
                  // Тело.
                  $str_body=mysql_result($rslt_msg, 0, 'body');
                  echo "<tr class=heads><td colspan=3 class=internal>";
                  // Кому.
                  echo "<span class=tbtext>".$_mess20.":&nbsp;".$str_cr."&nbsp;".$_mess19.":&nbsp;</span><span class=nick>".$str_nick;
                  echo "</td></tr>";
                  // Тело.
                  echo "<tr><td colspan=3 class=internal>";
                  echo "<div class=nik>".fd_head($str_head)."</div>";
                  echo "<div class=post>".fd_body($str_body)."</div>";
                  echo "</td></tr>";
               }
            }
         echo "</table>";
?>