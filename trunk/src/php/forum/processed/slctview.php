<?
   session_start();
   //������������� �����������
   include("cache.php");
   // ����������� � ��
   include("setup.php");
   include("query.php");
   // �������� ����������
   $action=29;
   include("stat.php");
   // ������������� ���������
   $_SESSION['view']=$_POST['VIEW'];
   // ���� ��� ���
   $sql_vname="
   SELECT
      name
   FROM
      fdviews
   WHERE
      id=".$_SESSION['view']."      
   ";   
   $rslt_vname=fd_query($sql_vname, $conn, "");
   $_SESSION['vname']=mysql_result($rslt_vname, 0, 'name');
   // ��������� ���������� � ��
   mysql_close($conn);
   // ���������� � �����
   echo "<html>";
   echo "<head>";                                                               
   echo "<meta http-equiv='Refresh' content='0; url=index.php'>";
   echo "<title>";
   echo "</title>";
   echo "</head>";
   echo "<body>";
   echo "</body>";
   echo "</html>";
?>