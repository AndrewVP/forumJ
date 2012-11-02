<?
   session_start();
   include('setup.php');
   include("query.php");
// Соединяемся с базой
// Собираем статистику
   $action=32;
   include("stat.php");
//                 
   mysql_close($conn);  
   if ( isset($_POST['B2'])) {
      $_SESSION['enter']=1;
      echo "<html>";
      echo "<head>";
      if (!isset($_SESSION['location'])){
         echo "<meta http-equiv='Refresh' content='0; url=index.php'>";
      }
      else {
         echo "<meta http-equiv='Refresh' content='0; url=".$_SESSION['location']."'>";
         unset($_SESSION['location']);
      }
      echo "<title>";
      echo "</title>";
      echo "</head>";
      echo "<body>";
      echo "</body>";
      echo "</html>";
   }   
   if ( isset($_POST['B1'])) {
      echo "<html>";
      echo "<head>";
      echo "<meta http-equiv='Refresh' content='0; url=reg.php?id=1'>";
      echo "<title>";
      echo "</title>";
      echo "</head>";
      echo "<body>";
      echo "</body>";
      echo "</html>";
   }   
   if ( isset($_POST['B3'])) {
      echo "<html>";
      echo "<head>";
      echo "<meta http-equiv='Refresh' content='0; url=auth.php?id=1'>";
      echo "<title>";
      echo "</title>";
      echo "</head>";
      echo "<body>";
      echo "</body>";
      echo "</html>";
   }   
?>   