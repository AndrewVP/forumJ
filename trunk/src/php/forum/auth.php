<?
// �����������
   session_start();
//������������� �����������
   include("cache.php");
   if (isset($_GET['id'])) $gid=stripslashes($_GET['id']);
// ���� ����� ����� ����������? 5,6 - logout!
//   if (($gid<>5) and ($gid<>6)) $_SESSION['where']=$gid;
   include('setup.php');
   include("query.php");
// ����������� � �����
// �������� ����������
   $action=3;
   include("stat.php");
//
   mysql_close($conn);
// ����� ���� ����������? �� ��������� - ���������� :)
   include("lang.php");
//
   echo "<html>";
   echo "<head>";
   echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
// �����
   include('style.php');
   echo "<title>";
   echo "�����������";
   echo "</title>";
   echo "</head>";
// ���� ���� ��������
   echo "<body bgcolor=#EFEFEF>";
// ������� �������
   echo "<table border='0' style='border-collapse: collapse' width='100%'>";
// ������� � ���� � ������� ��������
   include("logo.php");
// ������� ������
   include("menu.php");
// ����� �����������
   echo "<tr><td width='100%' align='center'><table width='100%'><tr><td>";
   echo "<form  action='submit.php' method='POST'>";
   echo "<table><tr><td><p>";
// ����������, ������ �� ���� ������?
   switch ($gid){
// ������ �� ������ ����
      case 1:
         echo("�������������, ����������");
         break;
      case 4:
         echo("<b>�������������, ����������</b>");
         break;
// ���������� ���������� ���� ���������������������
      case 2:
         echo("���������� ���� ��� ���������� ����� ������ ������������������ ����������!");
         break;
// ���������� �������� ���������������������
      case 3:
         echo("��������� ���� ������ ����� ������ ������������������ ����������!");
         break;
      case 5:
         echo("<b>������, �� ����� �� �����! :)</b>");
         break;
      case 6:
         echo("<b>�� �� ������� ������! :)</b>");
         break;
      case 7:
         echo("<b>� ����� � ����������� ������� ������������� ���������� ������ ����� ��� ������ ��������� ������������ :) ���������� �������� ��� ���� �������������, ������� �� ������� ������ ���� ��� �, ���������� ��� �� �������� �������� ��������� ���� ����.</b>");
         break;
      case 8:
         echo("� ����� � ����������� ������� ������������� ���������� ������ ����� ��� ������ ��������� ������������ :) ���������� �������� ��� ���� �������������, ������� �� ������� ������ ���� ��� �, ���������� ��� �� �������� �������� ��������� ���� ����.<br> <b>�������������� �� ���������</b>");
         break;
      case 9:
         echo("��������� ������ ����� ������ ������������������ ������������");
         break;
      }
      echo "</p></td></tr>";
// � ������������ ���������
// ����������� ���
   echo "<tr><td><table><tr><td>";
   echo "���</td>";
   echo "<td><input type='text' name='T1' size='20'></td>";
   echo "</tr>";
// ������
   echo "<tr>";
   echo "<td>������</td>";
   echo "<td><input type=password name='T2' size='20'>";
   echo "</td>";
   echo "</tr>";
// �������������
   if ($gid==7 or $gid==8) {
      echo "<tr>";
      echo "<td>�������������</td>";
      echo "<td><input type=password name='T3' size='20'>";
      echo "</td>";
      echo "</tr>";
      $_SESSION['xxxx']='1';
      }
// ������
   echo "<tr>";
   echo "<td>";
   echo "<input type='submit' value='���������' name='B1'>";
   echo "<input type='reset' value='��������' name='B2'>";
   echo "</td></tr></table></td></tr></table></form></td></tr></table></td></tr>";
// ����� �����������
// ������� ������
   include("menu.php");
   include("end.php");
   echo "</body>";
   echo "</html>";
?>
