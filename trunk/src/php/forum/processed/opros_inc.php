<?
// �������� �������� �������
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
// �������� ������ ���������������
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
// ������� ������� �����
      $sql_nvcs="
      SELECT 
         COUNT(id) AS nvcs 
      FROM 
         voice 
      WHERE 
         head=".$str_t_id;
      $resqnvc=fd_query($sql_nvcs, $conn, "");
      $nvcs=mysql_result($resqnvc, 0, 'nvcs');
// ������
   echo("<tr><td>");
      echo "<p align=\"CENTER\"><font size=4><b>".$strq_quest."</b></font></p><br>";
      echo("</td></tr>");
      echo("<tr><td align=\"CENTER\">");
//���� ����������������� � �� ���������
      if (isset($_SESSION['autor']) and !$strq2numb){
      echo("<form  action='voice.php' method='POST'><table class=content>");
      for ($iq1=1; $iq1<$strqnumb; $iq1++){
          echo("<tr><td class=voice_left align='right'>");
// �������� id ������ ��� �������� ������
          $in1=mysql_result($resq1, $iq1, 'id');
// ������ �� ���������
          $check="";
          if ($iq1==1) $check=" CHECKED";
// ��������� �������?
          if (mysql_result($resq1, $iq1, 'type')){
// ���
             echo "������� ���������:&nbsp;";
// ����� ������ ���� ����������?
             if (mysql_result($resq1, $iq1, 'type')==1){
// ��
                echo "<b>".mysql_result($resq1, $iq1, 'nick')."</b>";
             }
             else{
// ������
                echo "<b>��������</b>";
             }
//���������������� ������� ������       
             echo "</td><td class=voice_right align='left'>";
             echo("<input type='radio' name='ANSWER' value='$in1'>&nbsp;".fd_smiles(fd_href(stripslashes(mysql_result($resq1, $iq1, 'node'))))."<br>");
          }
          else {
//��������� ������� ������
             echo "</td><td class=voice_right align='left'>";
             echo("<input type='radio' name='ANSWER' value='$in1'".$check.">&nbsp;".fd_smiles(fd_href(stripslashes(mysql_result($resq1, $iq1, 'node'))))."<br>");
          }
          echo("</td></tr>");
      }
             echo("<tr><td colspan='2' align='CENTER'>");
// ��������� ������ ���������...
// �����
             echo "<input type=hidden name=\"IDU1\" size=\"20\" value=\"".$_SESSION['idu']."\">";
             echo "<input type=hidden name=\"AUT1\" size=\"20\" value=\"".$_SESSION['autor']."\">";
// id ����
             echo "<input type=hidden name=\"IDT1\" size=\"20\" value=\"".$gid."\">";
// ������ ������
             if (isset($_SESSION['pass2'])) {
// ����
                echo "<input type=hidden name=\"PS21\" size=\"20\" value=\"".$_SESSION['pass2']."\">";
             }
             else {
// �� ����
                echo "<input type=hidden name=\"PS11\" size=\"20\" value=\"".$_SESSION['pass1']."\">";
             }
// ������
             echo("<input type=\"submit\" value=\"�������������\" name=\"OK\">");
             echo("</td></tr>");
             echo("</table></form>");     
      echo("</td></tr>");
// ����� ��������� �������� ������ ������������
   if (isset($_SESSION['autor']) and $str_type==2 and $pg==1 and $i==0){
// ���� ��� � ������� ��������� ������
      $iq3=0;
      for ($iq2=1; $iq2<$strqnumb; $iq2++){
          if (mysql_result($resq1, $iq2, 'user')==$_SESSION['idu']) $iq3=1;
      }
      if (!$iq3){
      echo("<tr><td>");
      echo("<form  action=\"uservoice.php\" method=\"POST\"><table align=\"CENTER\">");
      echo("<tr><td>");
      echo("��� ������� ������:<br>");
      echo("<input type=\"text\" name=\"P\" size=\"100\">");
// ��������� ������ ���������...
// �����
      echo "<input type=hidden name=\"IDU2\" size=\"20\" value=\"".$_SESSION['idu']."\">";
      echo "<input type=hidden name=\"AUT2\" size=\"20\" value=\"".$_SESSION['autor']."\">";
// id ����
      echo "<input type=hidden name=\"IDT2\" size=\"20\" value=\"".$gid."\">";
// ������ ������
      if (isset($_SESSION['pass2'])) {
// ����
         echo "<input type=hidden name=\"PS22\" size=\"20\" value=\"".$_SESSION['pass2']."\">";
      }
      else {
// �� ����
         echo "<input type=hidden name=\"PS12\" size=\"20\" value=\"".$_SESSION['pass1']."\">";
             }
// /������ � ������ ������� �����
      echo("</td></tr>");
// ������ � ������ ������� �����
      echo("<tr><td align=\"CENTER\">");
      echo("<input type=\"checkbox\" name=\"HD\" value=\"1\" checked>&nbsp;������ ������ ��������<br>");
      echo("<input type=\"submit\" value=\"�������������\" name=\"OK\">");
// /������ � ������ ������� �����
      echo("</td></tr>");
// /����� � ������� �����
      echo("</table></form>");
// /������ � ������ ������� ������
      echo("</td></tr>");

   }}
          }
if (!$nvcs) $nvcs=1/10000000;
// ������ � ������ ������� ������
      echo("<tr><td align=\"CENTER\">");
echo "<b>����� �������: ".round($nvcs,0)."</b>";
// /������ � ������ ������� ������
      echo("</td></tr>");
// ������ � ������ ������� ������
      echo("<tr><td align=\"CENTER\">");
// ������� ����������� �����������
          echo "<table align='CENTER' class=control>";
              echo("<tr class=heads><th class='internal'>");
              echo "����� ��������";
              echo("</th><th class='internal'>");
              echo "�������";
              echo("</th><th class='internal'>");
              echo "�������";
              echo("</th><th class='internal' width='350'>");
              echo "�������";
              echo("</th><th class='internal'>");
              echo "����";
              echo("</th></tr><tr>");
          for ($iq11=1; $iq11<$strqnumb; $iq11++){

// ��������� �������?
          if (mysql_result($resq1, $iq11, 'type')){
// ���
// ����� ������ ���� ����������?
             if (mysql_result($resq1, $iq11, 'type')==1){
// ��
                echo "<td align='LEFT' class='internal'>".mysql_result($resq1, $iq11, 'nick')."</td>";
             }
             else{
// ������
                echo "<td align='LEFT' class='internal'>��������</td>";
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
// /������� ����������� �����������
          echo "</table>";
          if (isset($strq2numb) and $strq2numb){
// �����
             echo "<form method=\"POST\" action=\"delvoice.php\" align=\"CENTER\">";
// ��������� ������ ���������...
// �����
      echo "<input type=hidden name=\"IDU\" size=\"20\" value=\"".$_SESSION['idu']."\">";
      echo "<input type=hidden name=\"AUT\" size=\"20\" value=\"".$_SESSION['autor']."\">";
// id ����
      echo "<input type=hidden name=\"IDT\" size=\"20\" value=\"".$gid."\">";
// ������ ������
      if (isset($_SESSION['pass2'])) {
// ����
         echo "<input type=hidden name=\"PS2\" size=\"20\" value=\"".$_SESSION['pass2']."\">";
      }
      else {
// �� ����
         echo "<input type=hidden name=\"PS1\" size=\"20\" value=\"".$_SESSION['pass1']."\">";
             }

             echo "<input type=\"SUBMIT\" value=\"�������� ���� �����\">";
// /�����
             echo "</form>";
          }
// /������ � ������ ������� ������
      echo("</td></tr>");
?>