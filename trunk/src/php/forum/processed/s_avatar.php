<?
// �����������
// TODO ������� ����� ����� �������
   include('setup.php');
   include("query.php");
   if (isset($_POST['pass']) and $_POST['pass']=='cjkysirj'){
         $sql_updavatar="
         UPDATE
            users
         SET
            avatar='".mysql_real_escape_string($_POST['avatar'])."',   
            ok_avatar=1,
            s_avatar=1
         WHERE
            id=".$_GET['qqnn'];
         $rslt_updavatar=fd_query($sql_updavatar, $conn, "");
         echo "������� ��������<br />";
         echo "<img src='".$_POST['avatar']."'>";
   }
   else {
      echo "������ �� �������.";
   }
   mysql_close($conn);
?>