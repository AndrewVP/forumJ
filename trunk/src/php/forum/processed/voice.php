<?
// ��������� �����
   session_start();
   include("query.php");
//������������� �����������
   include("cache.php");
// �����������
   include('setup.php');
// �������� ����������
   $action=5;
   include("stat.php");
   $n0=stripslashes($_POST['IDU1']);
   if (isset($_POST['PS11'])) {
      $query = "SELECT pass, nick FROM users where id=".$n0;
      $pp2=$_POST['PS11'];
      $result = fd_query($query, $conn, "");
      $ppp=mysql_result($result, 0, 'pass');
   }
   else
   {
      $query = "SELECT pass2, nick FROM users where id=".$n0;
      $pp2=$_POST['PS21'];
      $result = fd_query($query, $conn, "");
      $ppp=mysql_result($result, 0, 'pass2');
   }
// ����� ���� ������ ���������������������?
   if (!mysql_num_rows($result)==0 and $pp2==$ppp ){
// ��� ���������
   $str_id=$n0;
// ��������� �����
   $sql_voice="insert into voice (head, node, user) values ('".$_POST['IDT1']."', '".$_POST['ANSWER']."', '".$_POST['IDU1']."')";
   $cvcv=fd_query($sql_voice, $conn, "");
// ������� ��� �������?
   $sql_nvoice="select gol from quest where id=".$_POST['ANSWER'];
   $resultnv=fd_query($sql_nvoice, $conn, "");
   $gol=mysql_result($resultnv, 0, 'gol')+1;
   $sql_newvoice="update quest set gol=".$gol." where id=".$_POST['ANSWER'];
   $cvcv=fd_query($sql_newvoice, $conn, "");
 // ��������� ���������
/*�������� ������ ������*/
	$sqlIgnor="
	   SELECT
	      ignor.ignor
	   FROM
	      ignor
	   WHERE
	      ignor.user=95
	      AND ignor.ignor=".$str_id."
	      AND ignor.end > now()
		";
	$rsltIgnor=fd_query($sqlIgnor,$conn, "");
	if (mysql_num_rows($rsltIgnor)==0){	   $lptime=date('Y-m-d H:i:s');
	   $query="UPDATE titles set lposttime='".$lptime."', lpostnick='�������� �����' WHERE id=".$_POST['IDT1'];
	   $cvcv=fd_query($query, $conn, "");
	}
   mysql_close($conn);
// ���������� � �����
   header ("Location: index.php");
   mysql_close($conn);
   }else{   	// ����� ���������������������
   echo "<html>";
   echo "<head>";
   echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
   echo "<meta http-equiv='Refresh' content='15; url=auth.php?id=4.php'>";
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