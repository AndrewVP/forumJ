<?
setlocale(LC_ALL, "ru_RU.CP1251");
/* ��������� ����� ���������.*/
session_start();
// ������
include_once 'classes/ua/com/diletant/util/time.php';
   /* ������� ��������� �����*/
include("bbcode_mail.php");
include("cenz.php");
include("button.php");
/*������� ������*/
include("search_engine.php");
   /*������������� �����������*/
include("cache.php");
   /* ����������� � MySql*/
include('setup.php');
   include("query.php");
   /* �������� ����������*/
$action=6;
include("stat.php");
   /*��������� �����*/
include("guard.php");
include("lang.php");
   /* ����� ���� ������ ���������������������?*/
$n0=stripslashes($_POST['IDU']);
$n1=fd_nick($n0, $conn);
$ban=fd_ban($n0, $conn);
if ($n1!="" && $ban!="1"){
      /* ��� ���������*/
      /* ����� ������??*/
   if (!(trim($_POST['A2'])=="" or trim($_POST['NHEAD'])=="")) {
         /* �� ������*/
         /* ��������� ���������*/
      $str_body=mysql_real_escape_string($_POST['A2']);
         /* ����� ���������*/
      $str_head=mysql_real_escape_string($_POST['NHEAD']);
         /* ��������� ���������*/
      $str_idd=stripslashes($_POST['IDT']);
         /* ����� ���?*/
      $str_id=$n0;
         /* ������� �����*/
      $postTime = new Time(time());
      $lptime = $postTime->toString("Y-m-d H:i:s");
         /*ip �����*/
      $str_ip=$_SERVER['REMOTE_ADDR'];
      $str_dom=gethostbyaddr($str_ip);
         /*�������� ��� ������?*/
      if (isset($_POST['comand']) && $_POST['comand']=="view"){
         include("write_view.php");
      }else{
            /* ���������� ��� �����������???*/
         If (!isset($_POST['IDB'])) {
               /*����� ����*/
            include("write_new.php");
         }
         else {
               /* ����������� ������ ����*/
            include("write_edit.php");
         }
         mysql_close($conn);
         /* ���������� � �����*/
         /*�������� � �����?*/
         if (isset($_POST['no_exit'])){
         	$exit="tema.php?id=".$str_idd."&end=1#end";
         }else{
         	$exit="index.php";
         }
         echo "<html><head>";
      echo "<meta http-equiv='Refresh' content='0; url=".$exit."'>";
         include('style.php');
         echo "<title></title></head><body></body></html>";
      }
   }else{
// ������
      echo "<html>";
      echo "<head>";
      echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
      echo "<meta http-equiv='Refresh' content='0; url=index.php'>";
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
   }
}else{
// ����� ���������������������
   echo "<html>";
   echo "<head>";
   echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
   echo "<meta http-equiv='Refresh' content='10; url=auth.php?id=4'>";
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