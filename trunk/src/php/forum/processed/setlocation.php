<?
   // ��������� �������.
   session_start();
   include("query.php");
   // ������� �������� �����������
   include('guard.php');
   //������������� �����������
   include("cache.php");
   // ����������� � MySql
   include('setup.php');
   // �������� ����������
   $action=38;
   include("stat.php");
   //
   $n0=stripslashes($_POST['IDU']);
   // ����� ���� ������ ���������������������?
   if (fd_guard($n0, $conn)){
      // ��� ���������.
      $scity=0;
      if (isset($_POST['scity'])){
         $scity=1;
      }
      $scountry=0;
      if (isset($_POST['scountry'])){
         $scountry=1;
      }
         $sql_updlocation="
         UPDATE
            users
         SET
            fd_timezone=".$_POST['timezone'].",
            scity=".$scity.",
            scountry=".$scountry.",
            city='".mysql_real_escape_string($_POST['city'])."',   
            country='".mysql_real_escape_string($_POST['country'])."'   
         WHERE
            id=".$n0;
//            echo $sql_updlocation;
         $rslt_updlocation=fd_query($sql_updlocation, $conn, "");
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