<?
   // ��������� �������� �����.
   session_start();
   include("query.php");
   //������������� �����������
   include("cache.php");
   // ����������� � MySql
   include('setup.php');
   // �������� ����������
   $action=27;
   include("stat.php");
   //
   // ����� ���� ������ ���������������������?
   $n0=stripslashes($_POST['IDU']);
   if (isset($_POST['PS1'])) {
      $query = "SELECT pass, nick FROM users where id=".$n0;
      $pp2=$_POST['PS1'];
      $result = fd_query($query, $conn, "");
      $ppp=mysql_result($result, 0, 'pass');
   }
   else
   {
      $query = "SELECT pass2, nick FROM users where id=".$n0;
      $pp2=$_POST['PS2'];
      $result = fd_query($query, $conn, "");
      $ppp=mysql_result($result, 0, 'pass2');
   }
   if (mysql_num_rows($result) and $pp2==$ppp ){
      // ��� ���������.
      switch($_POST['ACT']) {
         // ��������
         case 'del':
         for ($x1=0; $x1<$_POST['NRW']; $x1++){
            // �������� ����� �� ��������
            if (isset($_POST[$x1])){
               // ������� ����� ��� � �������
               $sql_deltranzit="
               DELETE FROM
                  fdtranzit
               WHERE
                  folder=".$_POST[$x1]."
                  AND user=".$n0."  
               ";
               $rslt_deltranzit=fd_query($sql_deltranzit, $conn, "");
               // ������� ����� ����� � ���������������
               $sql_delvtranzit="
               DELETE FROM
                  fdvtranzit
               WHERE
                  folder=".$_POST[$x1]."
                  AND user=".$n0."  
               ";
               $rslt_delvtranzit=fd_query($sql_delvtranzit, $conn, "");
               // ������� �����
               $sql_delfolder="
               DELETE FROM
                  fdfolders
               WHERE
                  id=".$_POST[$x1]."
                  AND user=".$n0."  
               ";
               $rslt_delfolder=fd_query($sql_delfolder, $conn, "");
            }}
               $add="";
               if (isset($_GET['id'])) $add="?id=".$_GET['id'];
               if (isset($_GET['view'])) $add=$add."&view=".$_GET['view'];
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
         case 'add':
         for ($x1=0; $x1<$_POST['NRW']; $x1++){
            // �������� ����� �� ����������
            if (isset($_POST[$x1])){
               // � ����� �� ��� ��� ����?
               $sql_vtrexist="
               SELECT
                  id
               FROM   
                  fdvtranzit
               WHERE
                  view=".$_GET['view']."
                  AND folder=".$_POST[$x1]."
                  AND user=".$n0."   
               ";
               $rslt_vtrexist=fd_query($sql_vtrexist, $conn, "");
               if (!mysql_num_rows($rslt_vtrexist)){
                  // ��������� ����� ����� � ���������������
                  $sql_insvtranzit="
                  INSERT INTO
                     fdvtranzit
                     (
                     view,
                     folder,
                     d_cr,
                     user
                     )
                  VALUES
                     (
                     ".$_GET['view'].",
                     ".$_POST[$x1].",
                     NOW(),
                     ".$n0."
                     )
                  ";
                  $rslt_insvtranzit=fd_query($sql_insvtranzit, $conn, "");
            }}}
               $add="";
               if (isset($_GET['id'])) $add="?id=".$_GET['id'];
               if (isset($_GET['view'])) $add=$add."&view=".$_GET['view'];
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
      }
      }
   else {
      $add="";
      if (isset($_GET['id'])) $add="?id=".$_GET['id'];
      if (isset($_GET['view'])) $add=$add."&view=".$_GET['view'];
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