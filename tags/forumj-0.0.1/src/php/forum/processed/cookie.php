<?
if (!isset($_SESSION['idu'])){
   if (isset($_COOKIE['user'])){
      if (!isset($_COOKIE['pass2'])) {
         setcookie("user", "", 0, "/forum", "www.diletant.com.ua");
         setcookie("idu", "", 0, "/forum", "www.diletant.com.ua");
         setcookie("pass2", "", 0, "/forum", "www.diletant.com.ua");
         setcookie("user", "", 0, "/forum", "diletant.com.ua");
         setcookie("idu", "", 0, "/forum", "diletant.com.ua");
         setcookie("pass2", "", 0, "/forum", "diletant.com.ua");
         header($location);
      }
      else
      {
         $sq=fd_query('select pass2 from users where id='.$_COOKIE['idu'], $conn, "");
         if (!(mysql_result($sq, 0, 'pass2')==$_COOKIE['pass2'])){
            setcookie("user", "", 0, "/forum", "www.diletant.com.ua");
            setcookie("idu", "", 0, "/forum", "www.diletant.com.ua");
            setcookie("pass2", "", 0, "/forum", "www.diletant.com.ua");
            setcookie("user", "", 0, "/forum", "diletant.com.ua");
            setcookie("idu", "", 0, "/forum", "diletant.com.ua");
            setcookie("pass2", "", 0, "/forum", "diletant.com.ua");
            header($location);
         }
         else
         {
            $_SESSION['autor']=$_COOKIE['user'];
            $_SESSION['idu']=$_COOKIE['idu'];
            if (isset($_SESSION['pass1'])) unset($_SESSION['pass1']);
            $_SESSION['pass2']=$_COOKIE['pass2'];
            // ��������� �� ���������
            $sql_defs="
            SELECT
               view_def,
               pp_def,
               pt_def
            FROM
               users
            WHERE
               id=".$_SESSION['idu']."      
            "; 
            $rslt_defs=fd_query($sql_defs, $conn, "");
            $_SESSION['pp']=mysql_result($rslt_defs, 0, 'pp_def'); // ���������� ����� � �������� ��� ������
            $_SESSION['pt']=mysql_result($rslt_defs, 0, 'pt_def'); //���������� ����� � �������� ��������� ����
            $_SESSION['def_view']=mysql_result($rslt_defs, 0, 'view_def');
         }
      }
   }
}
?>