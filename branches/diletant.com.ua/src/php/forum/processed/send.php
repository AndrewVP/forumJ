<?
   // ��������� ������� ������.
   session_start();
   include("query.php");
   //������������� �����������
   include("cache.php");
   // ����������� � MySql
   include('setup.php');
   // �������� ����������
   $action=24;
   include("stat.php");
   include("guard.php");
   //
   // ����� ���� ������ ���������������������?
   $n0=stripslashes($_POST['IDU']);
   $n1=fd_nick($n0, $conn);
   /* ������� �����*/
   $lptime=date('Y-m-d H:i:s');
   if ($n1!=""){
      // ��� ���������
      if (isset($_POST['comand']) && $_POST['comand']=="view"){
         include("send_view.php");
      }else{
         include("send_write.php");
      }
   }
   else
   // ����� ���������������������
   {
      $_SESSION['rcvr']=$_POST['RCVR'];
      $_SESSION['head']=$_POST['NHEAD'];
      $_SESSION['body']=$_POST['A2'];
      $_SESSION['error']=1;
      // ���������� �������.
      echo "<html>";
      echo "<head>";
      echo "<meta http-equiv='Refresh' content='0; url=".$_SERVER['HTTP_REFERER']."'>";
      echo "<title>";
      echo "</title>";
      echo "</head>";
      echo "<body>";
      echo "</body>";
      echo "</html>";
      mysql_close($conn);
   }
?>
