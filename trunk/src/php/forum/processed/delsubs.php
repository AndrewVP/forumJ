<?
   // ��������� �������� ��������.
   session_start();
   include("query.php");
   // ������� �������� �����������
   include('guard.php');
   //������������� �����������
   include("cache.php");
   // ����������� � MySql
   include('setup.php');
   // �������� ����������
   $action=33;
   include("stat.php");
   //
   $n0=stripslashes($_POST['IDU']);
   // ����� ���� ������ ���������������������?
   if (fd_guard($n0, $conn)){
      // ��� ���������.
      switch($_POST['ACT']) {
         // ��������
         case 'del':
            for ($x1=0; $x1<$_POST['NRW']; $x1++){
               // �������� ����� �� ��������
               if (isset($_POST[$x1])){
                  // ������� ����� ��� � �������
                  $sql_delsubs="
                  DELETE FROM
                     fd_subscribe
                  WHERE
                     id=".$_POST[$x1]."
                     AND user=".$n0."  
                  ";
                  $rslt_delsubs=fd_query($sql_delsubs, $conn, "");
               }
            }
            $add="";
            if (isset($_GET['id'])) $add="?id=".$_GET['id'];
            echo "<html>";
            echo "<head>";                                                               
            echo "<meta http-equiv='Refresh' content='0; url=control.php".$add."'>";
            echo "<title>";
            echo "</title>";
            echo "</head>";
            echo "<body>";
            echo "</body>";
            echo "</html>";
            break;
         // ���������� ����� � ���������
      }
   }
   else {
      $add="";
      if (isset($_GET['id'])) $add="?id=".$_GET['id'];
      echo "<html>";
      echo "<head>";
      echo "<meta http-equiv='Refresh' content='0; url=control.php".$add."'>";
      echo "<title>";
      echo "</title>";
      echo "</head>";
      echo "<body>";
      echo "</body>";
      echo "</html>";
   }
?>