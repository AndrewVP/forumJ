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
   $action=39;
   include("stat.php");
   //
   $n0=stripslashes($_POST['IDU']);
   // ����� ���� ������ ���������������������?
   if (fd_guard($n0, $conn)){
      // ��� ���������.
      $v_avatar=0;
      if (isset($_POST['v_avatar'])){
         $v_avatar=1;
      }
         $sql_updavatar="
         UPDATE
            users
         SET
            v_avatars=".$v_avatar."
         WHERE
            id=".$n0;
         $rslt_updavatar=fd_query($sql_updavatar, $conn. "");
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