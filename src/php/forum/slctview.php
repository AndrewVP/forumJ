<?
   session_start();
   //Предотвращаем кеширование
   include("cache.php");
   // Соединяемся с БД
   include("setup.php");
   include("query.php");
   // Собираем статистику
   $action=29;
   include("stat.php");
   // Устанавливаем интерфейс
   $_SESSION['view']=$_POST['VIEW'];
   // Ищем его имя
   $sql_vname="
   SELECT
      name
   FROM
      fdviews
   WHERE
      id=".$_SESSION['view']."      
   ";   
   $rslt_vname=fd_query($sql_vname, $conn, "");
   $_SESSION['vname']=mysql_result($rslt_vname, 0, 'name');
   // Закрываем соединение с БД
   mysql_close($conn);
   // Возвращаем в Форум
   echo "<html>";
   echo "<head>";                                                               
   echo "<meta http-equiv='Refresh' content='0; url=index.php'>";
   echo "<title>";
   echo "</title>";
   echo "</head>";
   echo "<body>";
   echo "</body>";
   echo "</html>";
?>