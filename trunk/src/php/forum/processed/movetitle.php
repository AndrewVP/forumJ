<?
   // ��������� ������� ���� ����� �������.
   session_start();
   include("query.php");
   //������������� �����������
   include("cache.php");
   // ����������� � MySql
   include("setup.php");
   // �������� ����������
   $action=30;
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
      for ($x1=0; $x1< $_POST['NRW']; $x1++){
         // �������� ���� ��� ��������
         if (isset($_POST[$x1])){ 
            echo "����������� ���� id ".$_POST[$x1]."<br>";
            // ������� ���������� �������
            $sql_deltranzit="
            DELETE FROM
               fdtranzit
            WHERE
               title=".$_POST[$x1]."
               AND user=".$n0."  
            ";
            $rslt_deltranzit=fd_query($sql_deltranzit, $conn, "");
            // ������� ����� �������
            if ($_POST['VIEW']!=1){
               $sql_newtranzit="
               INSERT INTO
                  fdtranzit
                  (
                  title,
                  user,
                  folder,
                  d_cr)
               VALUES   
                  (
                  ".$_POST[$x1].",
                  ".$n0.",
                  ".$_POST['VIEW'].",
                  NOW()
                  )  
               ";
               $rslt_newtranzit=fd_query($sql_newtranzit, $conn, "");
	         }
         }
      }
      $add="";
      if (isset($_GET['page'])) $add="?page=".$_GET['page'];
      echo "<html>";
      echo "<head>";                                                               
      echo "<meta http-equiv='Refresh' content='0; url=index.php".$add."'>";
      echo "<title>";
      echo "</title>";
      echo "</head>";
      echo "<body>";
      echo "</body>";
      echo "</html>";
   }
   else {
      echo "<html>";
      echo "<head>";
      echo "<meta http-equiv='Refresh' content='0; url=index.php'>";
      echo "<title>";
      echo "</title>";
      echo "</head>";
      echo "<body>";
      echo "</body>";
      echo "</html>";
   }
?>