<?
// ��������� ���������� ������ �������� ������
   session_start();
   include("query.php");
//������������� �����������
   include("cache.php");
// �����������
   include('setup.php');
// �������� ����������
   $action=12;
   include("stat.php");
//
   $n0=stripslashes($_POST['IDU2']);
   if (isset($_POST['PS12'])) {
      $query = "SELECT pass, nick FROM users where id=".$n0;
      $pp2=$_POST['PS12'];
      $result = fd_query($query, $conn, "");
      $ppp=mysql_result($result, 0, 'pass');
   }
   else
   {
      $query = "SELECT pass2, nick FROM users where id=".$n0;
      $pp2=$_POST['PS22'];
      $result = fd_query($query, $conn, "");
      $ppp=mysql_result($result, 0, 'pass2');
   }
// ����� ���� ������ ���������������������?
   if (!mysql_num_rows($result)==0 and $pp2==$ppp ){
// ��� ���������
// ����� ������??
   if (!(trim($_POST['P'])=="")) {
// �� ������
   $str_id=$n0;
//������������ ����� ��������
   $sql_max="select max(numb) as mx from quest where head=".$_POST['IDT2'];
   $resultmax = fd_query($sql_max, $conn, "");
   $str_numb=mysql_result($resultmax, 0, 'mx')+1;
// ��������? 1 - ���, 2 - ��
   $str_type=1;
   if (isset($_POST['HD'])) $str_type=2;
// ��������� �����
   $str_post=mysql_real_escape_string(trim($_POST['P']));
   $sql_ins="insert into quest (head, numb, node, gol, type, user) values ('".$_POST['IDT2']."', '$str_numb','$str_post', 1, '$str_type', '".$_POST['IDU2']."')";
   $cvcv=fd_query($sql_ins, $conn, "");
// ���������� ��� id
   $sql_id="select id from quest where head=".$_POST['IDT2']." and numb='$str_numb'";
   $resultid=0;
   $resultid=fd_query($sql_id, $conn, "");
   $str_id=mysql_result($resultid, 0, 'id');
// ��������� �����
   $sql_voice="insert into voice (head, node, user) values ('".$_POST['IDT2']."', '$str_id', '".$_POST['IDU2']."')";
   $cvcv=fd_query($sql_voice, $conn,"");
   $lptime=date('Y-m-d H:i:s');
 // ��������� ���������
   $query="UPDATE titles set lposttime='".$lptime."', lpostnick='�������� �����' WHERE id=".$_POST['IDT2'];
   $cvcv=fd_query($query, $conn, "");
   mysql_close($conn);
// ���������� � �����
   header ("Location: index.php");
   }
   else
// ������
   {
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
   }}
   else
// ����� ���������������������
   {
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