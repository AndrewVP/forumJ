<?
         echo "<div class=mnuprof align='CENTER'><b>".$_mess16."</b></div>";
         echo "<form method='POST' class=content action='delmail.php?id=4'>";
         echo "<table class='control'><tr class=heads>";
         // �������� �����
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
         // ��������� �������
         // ����
         echo "<th class='internal' width='120'><div class=tbtext>".$_mess19."</div></th>";
            // ���� ������
         echo "<th class='internal'><div class=tbtext>".$_mess59."</div></th>";
            // ����� ����������.
         echo "<th class='internal' width='120'><div class=tbtext>".$_mess61."</div></th>";
            // ����� ��������.
         echo "<th class='internal' width='120'><div class=tbtext>".$_mess60."</div></th>";
         echo "<th class='internal' width='20'></th>";
         echo "</tr>";
         // ������� ���������
         for ($m1=0; $m1 < $numrows; $m1++) {
            // id ������
            $str_id=mysql_result($res_mail, $m1, 'id');
            // ���� ������
            $str_head=mysql_result($res_mail, $m1, 'head');
            // ����
            $str_nick=mysql_result($res_mail, $m1, 'nick');
            // ����� ����������.
            $str_snt=mysql_result($res_mail, $m1, 'd__snt');
            // ����� ��������.
            $str_rcv=mysql_result($res_mail, $m1, 'd__rcv');
            echo "<tr>";
            // ����
            echo "<td class='internal' width='120'><div class=tbtext>";
            echo $str_nick;
            echo "</div></td>";
            // ���� ������
            echo "<td class='internal'><div class=tbtext>";
            echo "<a href='control.php?id=4&msg=".$str_id."'>".fd_head($str_head)."</a>";
            echo "</div></td>";
            // ����� ����������.
            echo "<td class='internal' width='120'><div class=tbtext>";
            echo $str_snt;
            echo "</div></td>";
            // ����� ��������.
            echo "<td class='internal' width='120'><div class=tbtext>";
            echo $str_rcv;
            echo "</div></td>";
            // ������.
            echo "<td class='internal'><div align='center' class=tbtext>";
            echo '<input type="checkbox" name="'.$m1.'" value="'.$str_id.'">';
            echo "</div></td>";       
            echo "</tr>";
         }
         // ������ (���� ������ ��������)
         echo "<tr>";
         echo "<td colspan=5 align='right'>"; 
         echo "<span class=tbtextnread>".$_mess69."&nbsp;&nbsp;</span>";
         echo "<select size='1' name='ACT'>";
         echo "<option selected value='del'><span class=mnuprof>".$_mess70."&nbsp;&nbsp;</span></option>";
         echo "</select>&nbsp;";
         echo '<input type="hidden" value="'.$numrows.'" name="NRW">';
         // ��������� ������ ���������...
         // �����
         echo "<input type=hidden name='IDU' value='".$_SESSION['idu']."'>";
         echo "<input type=hidden name='AUT' value='".$_SESSION['autor']."'>";
         // ������ ������
         if (isset($_SESSION['pass2'])) {
            // ����
            echo "<input type=hidden name='PS2' value='".$_SESSION['pass2']."'>";
         }
         else
         {
            // �� ����
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
// ������� ������
            if (isset($_GET['msg']) and $_GET['msg']<>""){
               // ������� ���
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
                  // ���������.     
                  // id ������
                  $str_id=$_GET['msg'];
                  // ����������.
                  $str_snt=mysql_result($rslt_msg, 0, 'd__snt');
                  // ��������.
                  $str_rcv=mysql_result($rslt_msg, 0, 'd__rcv');
                  // ����.
                  $str_nick=mysql_result($rslt_msg, 0, 'nick');
                  // ���������.
                  $str_head=mysql_result($rslt_msg, 0, 'head');
                  // ����.
                  $str_body=mysql_result($rslt_msg, 0, 'body');
                  echo "<tr class=heads><td colspan=4 class=internal>";
                  // ����.
                  echo "<span class=tbtext>".$_mess60.":&nbsp;".$str_rcv."&nbsp;".$_mess61.":&nbsp;".$str_snt."&nbsp;".$_mess28.":&nbsp;</span><span class=nick>".$str_nick."</span>";
                  echo "</td></tr>";
                  // ����.
                  echo "<tr><td colspan=4 class=internal>";
                  echo "<div class=nik>".fd_head($str_head)."</div>";
                  echo "<div class=post>".fd_body($str_body)."</div>";
                  echo "</td></tr>";
               }
            }
         echo "</table>";
         echo "</form>";
?>