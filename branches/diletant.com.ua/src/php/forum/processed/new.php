<?
// ��������� ����� ����
session_start();
// ������
include_once 'classes/ua/com/diletant/util/time.php';
//������������� �����������
include("cache.php");
// �����������
include('setup.php');
   include("query.php");
// ������� ��������� �����
include("bbcode_mail.php");
// �������� ����������
$action=9;
include("stat.php");
   /*��������� �����*/
include("guard.php");
include("lang.php");
include("search_engine.php");
//
$n0=stripslashes($_POST['IDU']);
//$n1=fd_nick($n0, $conn);
$ban=fd_ban($n0, $conn);
if (fd_guard($n0, $conn) && $ban!="1"){
// ��� ���������
// ����� ������??
   if (!(trim($_POST['NHEAD'])=="" or trim($_POST['A2'])=="")) {
// �� ������
      /*��������?*/
   	$threadTime = new Time(time());
      $rgtime = $threadTime->toString("Y-m-d H:i:s");
      $str_ip=$_SERVER['REMOTE_ADDR'];
      $str_dom=gethostbyaddr($str_ip);
      if (isset($_POST['comand']) && $_POST['comand']=="view"){
         include("new_view.php");
      }else{
         include("new_write.php");
      }
   }else{
      // ������
      echo "<html>";
      echo "<head>";
      echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
      echo "<meta http-equiv='Refresh' content='5; url=index.php'>";
   // �����
      echo "<title>";
      echo "�� �� �� ���� ���������!";
      echo "</title>";
      echo "</head>";
   // ���� ���� ��������
      echo "<body bgcolor=#EFEFEF>";
      echo "<font size='5'><b>��, ������� �������� ��������, ��� ��� ��� ������? ������ ���� �� ������ ���������!</b></font>";
      echo "</body>";
      echo "</html>";
      mysql_close($conn);
   }
}else{
// ����� ���������������������
   echo "<html>";
   echo "<head>";
   echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
   echo "<meta http-equiv='Refresh' content='5; url=auth.php?id=4.php'>";
// �����
   echo "<title>";
   echo "�� �� �� ���� ���������!";
   echo "</title>";
   echo "</head>";
// ���� ���� ��������
   echo "<body bgcolor=#EFEFEF>";
   echo "<font size='5'><b>������� ���� ��� ��� ���������� ����!</b></font>";
   echo "</body>";
   echo "</html>";
   mysql_close($conn);
}
?>