<?
   ob_start();
   // ������ ���������
   session_start();
// �������   
   include("bbcode.php");
   include("smiles.php");
   include("cenz.php");
   include("head.php");
   include("body.php");
   include("form_add.php");
   include("button.php");
   include("query.php");
   // ��������� id, ���� �� ��������, �� - 0
   if (isset($_GET['id'])){
      $gid=$_GET['id'];
   }
   else {
      $gid=0;
   }
   // ���� ������ �����
   include("exit.php");
   // ���� ��� �� ���������������
   if (!isset($_SESSION['idu'])) {
      header("Location: index.php");
      exit;
   }
   // ������������� �����������
   include("cache.php");
   // ����������� � MySql
   include("setup.php");
   // �������� ����������
   $action=18;
   include("stat.php");
   //
   // �������
   // ����� ���� ����������? �� ��������� - ���������� :)
   include("lang.php");
   // �������� ������
   echo "<html>";
   echo "<head>";
   echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
   // �����
   include('style.php');
   // ������� (��������)
   if (strpos($_SERVER['HTTP_USER_AGENT'], "MSIE 5.0")){
      include('smile_ie5.php');
   }
   else {
      include('smile_.php');
   }

   /*������� (������ ���������)*/
   include('js/checklength.php');
   /*������� (����������� �����)*/
   include('jstags.php');
   /*������� (submit �����)*/
   include('js/send_submit.php');
   echo '<link rel="icon" href="/favicon.ico" type="image/x-icon">';
   echo '<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">';
   echo "<title>";
   echo $_mess127;
   echo "</title>";
   echo "</head>";
   // ���� ���� ��������
   echo "<body bgcolor=#EFEFEF>";
   // ������� �������
   echo "<table border='0' style='border-collapse: collapse' width='100%'>";
   // ������� � ���� � ������� ��������
   include("logo.php");
   // ������� "����"
   echo "<tr>";
   echo "<td width='100%'>";
   echo "<table class=content>";
   include("menu.php");
   echo "</table></td></tr>";
   //
   echo "<tr><td>";
   // ������� ������
   echo "<table class=content><tr><td>";
   // ������� ��������
   echo "<table class=content><tr>";
   // ������ �� �������
   // �����-����.
   echo "<td height='300' valign='TOP' width='150'>";
   echo "<table class=control><tr class=heads>";
   echo "<th class=internal>";
   echo '<div class=mnuprof>'.$_mess24.'</div>';
   echo "</th>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=1">'.$_mess24.'</a><br>';
   echo "</td>";
   echo "</tr></table>";
   // ������ ���������
   echo "<table class=control><tr class=heads>";
   echo "<th class=internal>";
   echo '<div class=mnuprof>'.$_mess23.'</div>';
   echo "</th>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=2">'.$_mess54.'</a><br>';
   echo "</td>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=3">'.$_mess57.'</a><br>';
   echo "</td>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=4">'.$_mess55.'</a><br>';
   echo "</td>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=5">'.$_mess56.'</a><br>';
   echo "</td>";
   echo "</tr></table>";
// ����������
   echo "<table class=control><tr class=heads>";
   echo "<th class=internal>";
   echo '<div class=mnuprof>'.$_mess71.'</div>';
   echo "</th>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=6">'.$_mess71.'</a><br>';
   echo "</td>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=7">'.$_mess72.'</a><br>';
   echo "</td>";
   echo "</tr></table>";
   // ��������
   echo "<table class=control><tr class=heads>";
   echo "<th class=internal>";
   echo '<div class=mnuprof>'.$_mess86.'</div>';
   echo "</th>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=8">'.$_mess86.'</a><br>';
   echo "</td>";
   echo "</tr></table>";
   // �������
   echo "<table class=control><tr class=heads>";
   echo "<th class=internal>";
   echo '<div class=mnuprof>'.$_mess93.'</div>';
   echo "</th>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=9">'.$_mess93.'</a><br>';
   echo "</td>";
   echo "</tr></table>";
   // ���������������
   echo "<table class=control><tr class=heads>";
   echo "<th class=internal>";
   echo '<div class=mnuprof>'.$_mess104.'</div>';
   echo "</th>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=10">'.$_mess104.'</a><br>';
   echo "</td>";
   echo "</tr></table>";
   /*�������*/
   echo "<table class=control><tr class=heads>";
   echo "<th class=internal>";
   echo '<div class=mnuprof>'.$_mess138.'</div>';
   echo "</th>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=11">'.$_mess138.'</a><br>';
   echo "</td>";
   echo "</tr></table>";
   // �������
   echo "</td>";
   echo "<td valign='TOP'>";
   switch($gid) {
      case 0:
         // ����� "�� ���������"
         break;
         case 1:
         // �����-����
         include("case_1.php");
         break;
      case 2:
         // Inbox
         include("case_2.php");
         break;
      case 3:
         // ����������, �� �� ����������
         include("case_3.php");
         break;
      case 4:
         // ����������, � ����������
         include("case_4.php");
         break;
      case 5:
         //  ���������
         include("case_5.php");
         break;
      case 6:
         // ����������
         include("case_6.php");
         break;
      case 7:
         // �����
         include("case_7.php");
         break;
      case 8:
         // ��������
         include("case_8.php");
         break;
      case 9:
         // �������
         include("case_9.php");
         break;
      case 10:
         // ���������������
         include("case_10.php");
         break;
      case 11:
         // �������
         include("case_11.php");
         break;
   }
   echo "</td>";
   echo "</tr>";
   echo "</table>";
   echo "</td>";
   echo "</tr>";
   echo "</table>";
   echo "</td>";
   echo "</tr>";
   // ��������� ���������� MySql
   // ������� "����"
   echo "<tr>";
   echo "<td width='100%'>";
   echo "<table border='0' style='border-collapse: collapse' width='100%'>";
   include("menu.php");
   echo "</table></td></tr>";
// ����� �������� ������ ������ ���������   
   include("control_mail.php");
   // ������ �����, �������� � ��������.
   include("end.php");
   echo "</body>";
   echo "</html>";
   mysql_close($conn);
   $strtmp=ob_get_contents();
   ob_end_clean();
   echo $strtmp;
?>