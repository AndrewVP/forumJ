<?
// ��������� ���������� ������ � �����-����
   session_start();
   include("query.php");
//������������� �����������
   include("cache.php");
// ����������� � MySql
   include('setup.php');
// �������� ����������
   $action=20;
   include("stat.php");
// ��������� ���������� ������
// id �������������
   if (isset($_GET['idi']) and $_GET['idi']!=""){
      $idi=$_GET['idi'];
   }
   else {
      header("Location: index.php");
      exit;
   }
// id ����, ������ ������
   if (isset($_GET['idt']) and $_GET['idt']!=""){
      $idt=$_GET['idt'];
   }
   else {
      header("Location: index.php");
      exit;
   }
// id ����� �������������
   if (isset($_GET['idp']) and $_GET['idp']!=""){
      $idp=$_GET['idp'];
   }
   else {
      header("Location: index.php");
      exit;
   }
// � ��������
   if (isset($_GET['pg']) and $_GET['pg']!=""){
      $pg=$_GET['pg'];
   }
   else {
      header("Location: index.php");
      exit;
   }
// ������ ����� � �������, �� ��������� - 7 ����
   $sql_ign="insert into ignor (type, user, ignor, begin, end) values (1, ".$_SESSION['idu'].", ".$idi.", now(), adddate(now(), INTERVAL 7 DAY))";
   $result=fd_query($sql_ign, $conn, "");
   mysql_close($conn);
// ���������� �������
   echo "<html>";
   echo "<head>";
   echo "<meta http-equiv='Refresh' content='0; url=tema.php?id=".$idt."&page=".$pg."#".$idp."'>";
   echo "<title>";
   echo "</title>";
   echo "</head>";
   echo "<body>";
   echo "</body>";
   echo "</html>";
?>