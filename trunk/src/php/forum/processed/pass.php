<?
// ������ ������
   session_start();
//������������� �����������
   include("cache.php");
   include('setup.php');
   include("query.php");
// �������� ����������
   $action=10;
   include("stat.php");

   mysql_close($conn);
// ����� ���� ����������? �� ��������� - ���������� :)
   include("lang.php");
   mail($_SESSION['mail'], "�� ������ ������ :)","��� ������ �� ������ ��������:\n".$_SESSION['pass'] , "From: diletant@diletant.com.ua\nReply-To: pass@diletant.com.ua\nX-Mailer: PHP/" . phpversion());
   echo "<html>";
   echo "<head>";
   echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
   echo "<link rel='stylesheet' type='text/css' href='/style/main.css'>";
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
   echo "<tr>";
   echo "<td width='100%'>";
   echo "<table border='0' style='border-collapse: collapse' width='100%'>";
   echo "<tr bgcolor='#C0C0C0'>";
// ��������
   echo "<td><p align='center'><a href='/index.html'><span class='a'>��������</span></a></td>";
// ��������� � �����
   echo "<td><p align='center'><a href='index.php'><span class='a'>������ ��� ������</span></a></td>";
   echo "</tr></table></td></tr>";
// ������ ���������
// �������� :)
   echo "<tr><td><table width='100%'><tr><td>";
   echo "<span class='d'>������ � ������� ��� ����������!</span>";
   echo "</td></tr></table></td></tr>";
// ������� ������
   echo "<tr>";
   echo "<td width='100%'>";
   echo "<table border='0' style='border-collapse: collapse' width='100%'>";
   echo "<tr bgcolor='#C0C0C0'>";
// ��������
//   echo "<td><p align='center'><a href='/index.html'><span class='a'>��������</span></a></td>";
// ��������� � �����
   echo "<td><p align='center'><a href='index.php'><span class='a'>&nbsp;</span></a></td>";
   echo "</tr></table></td></tr>";
// ������ ���������
// ������ �����, �������� � ��������.
   include("end.php");
   echo "</body>";
   echo "</html>";

?>