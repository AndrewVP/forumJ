<?php
// Нажата ссылка "выход"
   if (isset($_GET['exit']) or isset($_SESSION['exit'])){
//      if (isset($_SESSION['view'])) $_view=$_SESSION['view'];
      if (isset($_SESSION['exit']) and !isset($_GET['exit'])) $_enter=1;
      session_destroy();
      session_start();
      $_SESSION['pt']=40;
      $_SESSION['pp']=30;
      $_SESSION['view']=1;
      $_SESSION['def_view']=1;
      $_SESSION['exit']='1';
      if (isset($_enter)) $_SESSION['enter']='1';
      if (isset($_SESSION['idu'])) unset($_SESSION['idu']);
      if (isset($_SESSION['exit'])) unset($_SESSION['exit']);
      }
?>
