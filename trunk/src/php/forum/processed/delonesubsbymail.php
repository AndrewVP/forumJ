<?
   // ��������� �������� �������� ����� ����.
   session_start();
   include("query.php");
   // ������� �������� �����������
   include('guard.php');
   //������������� �����������
   include("cache.php");
   // ����������� � MySql
   include('setup.php');
   // �������� ����������
   $action=36;
   include("stat.php");
   //
      $sql_delsubs="
      DELETE FROM
         fd_subscribe 
      WHERE
         kod=".$_GET['id'];
      $rslt_delsubs=fd_query($sql_delsubs, $conn, "");
      echo "<html>";
      echo "<head>";                                                               
//      echo "<meta http-equiv='Refresh' content='0; url=http://www.diletant".$add."#subs'>";
      echo "<title>";
      echo "����� ��������";
      echo "</title>";
      echo "</head>";
      echo "<body>";
      echo "���������� � �������� �������";
      echo "</body>";
      echo "</html>";
?>