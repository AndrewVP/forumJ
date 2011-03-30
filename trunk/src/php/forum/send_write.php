<?
      // Может пустое??
      if (!(trim($_POST['A2'])=="" or trim($_POST['NHEAD'])=="" or trim($_POST['RCVR'])=="")) {
         // Ищем адресата
         $sql_id = "
         SELECT 
            id 
         FROM 
            users 
         WHERE 
            nick='".trim($_POST['RCVR'])."'";
         $rslt_id = fd_query($sql_id, $conn, "");
         if (mysql_num_rows($rslt_id)){
            //Нашли.
            $str_rcvr=mysql_result($rslt_id, 0, 'id');
            $str_head=mysql_real_escape_string(trim($_POST['NHEAD']));
            $str_body=mysql_real_escape_string(trim($_POST['A2']));
            $sql_mail="
            INSERT INTO
               fdmail
               (
               rcvr,
               sndr,
               head,
               body,
               d_cr,
               d_snt
               )
            VALUES
               (
               '$str_rcvr',
               '$n0',
               '$str_head',
               '$str_body',
               now(),
               now()
               )
            ";
            $rslt_mail=fd_query($sql_mail, $conn, "");
            // Отправляем в форум
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
         else {
            // Не нашли получателя.
            $_SESSION['rcvr']=$_POST['RCVR'];
            $_SESSION['head']=$_POST['NHEAD'];
            $_SESSION['body']=$_POST['A2'];
            $_SESSION['error']=1;
            // Отправляем обратно.
            echo "<html>";
            echo "<head>";
            echo "<meta http-equiv='Refresh' content='0; url=".$_SERVER['HTTP_REFERER']."'>";
            echo "<title>";
            echo "</title>";
            echo "</head>";
            echo "<body>";
            echo "</body>";
            echo "</html>";
         }


      }
      else
      // Пустое
      {
         $_SESSION['rcvr']=$_POST['RCVR'];
         $_SESSION['head']=$_POST['NHEAD'];
         $_SESSION['body']=$_POST['A2'];
         $_SESSION['error']=2;
         // Отправляем обратно.
         echo "<html>";
         echo "<head>";
         echo "<meta http-equiv='Refresh' content='0; url=".$_SERVER['HTTP_REFERER']."'>";
         echo "<title>";
         echo "</title>";
         echo "</head>";
         echo "<body>";
         echo "</body>";
         echo "</html>";
      }
?>