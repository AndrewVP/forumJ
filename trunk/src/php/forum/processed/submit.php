<?
// ��������� �����������
   session_start();
   include("query.php");
//������������� �����������
//   include("cache.php");
// �� ������ ������ :)
   if (isset($_SESSION['pass'])) unset($_SESSION['pass']);
   if (isset($_SESSION['mail'])) unset($_SESSION['mail']);
// ��������� ���, ������
   if (isset($_POST['T1'])) $t1=stripslashes($_POST['T1']); //���
   if (isset($_POST['T2'])) $t2=stripslashes($_POST['T2']); //������
   if (isset($_SESSION['where'])) $t3=$_SESSION['where'];
   if (isset($_SESSION['page'])) $t4=$_SESSION['page'];
   if (isset($_SESSION['id'])) $t5=$_SESSION['id'];
// ����������� � �����
   include('setup.php');
// �������� ����������
   $action=4;
   include("stat.php");
   $xt1=mysql_real_escape_string($t1);
   $query = "SELECT id, pass, pass2, mail FROM users where nick='$xt1'";
   $result = fd_query($query, $conn, "");
   $num_rows=mysql_num_rows($result);
// ����� �������?
   if($num_rows==0) {
// ���
         header ("Location: auth.php?id=5");
   }
   else {
// ��
      $ppass=mysql_result($result, 0, 'pass');
// ��������� ������
      if($t2==$ppass) {
         $idu=mysql_result($result, 0, 'id');
         $_SESSION['autor']=$t1;
         $pass2=mysql_result($result, 0, 'pass2');
// ������ ����
          setcookie("user", $t1, time()+1209600, "/forum", "www.diletant.com.ua");
          setcookie("idu", $idu, time()+1209600, "/forum", "www.diletant.com.ua");
          setcookie("pass2", $pass2, time()+1209600, "/forum", "www.diletant.com.ua");
          setcookie("user", $t1, time()+1209600, "/forum", "diletant.com.ua");
          setcookie("idu", $idu, time()+1209600, "/forum", "diletant.com.ua");
          setcookie("pass2", $pass2, time()+1209600, "/forum", "diletant.com.ua");
          session_destroy();
          session_start();
          $_SESSION['autor']=$t1;
          $_SESSION['idu']=$idu;
          $_SESSION['pass1']=$ppass;
          // ��������� �� ���������
          $sql_defs="
          SELECT
             view_def,
             pp_def,
             pt_def
          FROM
             users
          WHERE
             id=".$_SESSION['idu']."      
          "; 
          $rslt_defs=fd_query($sql_defs, $conn, "");
          $_SESSION['pp']=mysql_result($rslt_defs, 0, 'pp_def'); // ���������� ����� � �������� ��� ������
          $_SESSION['pt']=mysql_result($rslt_defs, 0, 'pt_def'); //���������� ����� � �������� ��������� ����
          $_SESSION['def_view']=mysql_result($rslt_defs, 0, 'view_def');
          mysql_close($conn);
// ���������� �� �����
   echo "<html>";
   echo "<head>";
   echo "<meta http-equiv='Refresh' content='0; url=index.php'>";
   echo "</head><body></body></html>";
      }
      else {
// ������ �� ������
         $_SESSION['pass']=mysql_result($result, 0, 'pass');
         $_SESSION['mail']=mysql_result($result, 0, 'mail');
         mysql_close($conn);
         header ("Location: auth.php?id=6");
}      }
?>
