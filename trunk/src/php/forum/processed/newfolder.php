<?
   // ��������� ���������� �����.
   session_start();
   include("query.php");
   //������������� �����������
   include("cache.php");
   // ����������� � MySql
   include('setup.php');
   // �������� ����������
   $action=25;
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
      if (trim($_POST['FOLD'])!=''){
         // ����� ����� ��� ����?
         $sql_srchfold="
         SELECT 
            id 
         FROM
            fdfolders
         WHERE
            flname='".$_POST['FOLD']."'      
            AND user=".$n0."
         ";
//         echo $sql_srchfold;
         $rslt_srchfold=fd_query($sql_srchfold, $conn, "");
         if (!mysql_num_rows($rslt_srchfold)){
            $sql_newfold="
            INSERT INTO
               fdfolders
               (
               flname,
               user,
               d_cr
               )
            VALUES
               (
               '".trim($_POST['FOLD'])."',
               ".$n0.",
               NOW()
               )   
            ";
            $rslt_newfold=fd_query($sql_newfold, $conn, "");
            // ��������� ����� ����� � ��������������� (3 � 4)
            $str_id=mysql_insert_id($conn);
            $sql_insv3tranzit="
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
               3,
               ".$str_id.",
               NOW(),
               ".$n0."
               )
            ";
            $rslt_insv3tranzit=fd_query($sql_insv3tranzit, $conn, "");
            $sql_insv4tranzit="
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
               4,
               ".$str_id.",
               NOW(),
               ".$n0."
               )
            ";
            $rslt_insv4tranzit=fd_query($sql_insv4tranzit, $conn, "");
         }
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
      }}
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