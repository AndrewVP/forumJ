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
      $s_avatar=0;
      if (isset($_POST['s_avatar'])){
         $s_avatar=1;
      }
         $sql_updavatar="
         UPDATE
            users
         SET
            avatar='".mysql_real_escape_string($_POST['avatar'])."',   
            s_avatar=".$s_avatar.",
            ok_avatar=0
         WHERE
            id=".$n0;
         $rslt_updavatar=fd_query($sql_updavatar, $conn, "");
// �������������� ����� �����.          

      $strMailAll="�������� ������� <a href='http://www.diletant.com.ua/forum/ok_avatar.php?qqnn=".$n0."'>".$_POST['AUT']."</a>";
// ��������� ���.         
         $server="smtp.freehost.com.ua";
         $from="diletant@diletant.com.ua";
         $subject="�������� �������";
         $headers='Content-type: text/html; charset="windows-1251"';
         $headers="From: ".$from."\nSubject: ".$subject."\nX-Mailer: Diletant\n".$headers;
         mail("an.diletant@mail.ru", $subject, $strMailAll, $headers);
         mail("andrew@sunbay.com", $subject, $strMailAll, $headers);
         
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