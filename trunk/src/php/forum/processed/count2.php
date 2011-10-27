<?
// Индикатор
session_start();
include("query.php");
//Предотвращаем кеширование
include("cache.php");
// Соединяемся с MySql
include('setup.php');
// Собираем статистику
$action=25;
include("stat.php");
//
if (isset($_GET['idb'])){
   $m_xb=$_GET['idb'];
   $m_xt=$_GET['idt'];
   $sql_indb="SELECT
                count(*) as mx
             FROM body
             WHERE 
                id>".$m_xt."
                AND head=".$m_xb."
             ";
   $res=fd_query($sql_indb, $conn, "");
   $m_xb=mysql_result($res, 0, 'mx');
   echo '<html>';
   echo '<head>';
   echo '<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">';
   echo '<meta http-equiv="Refresh" content="30; url=count2.php?idb='.$_GET['idb'].'&idt='.$_GET['idt'].'">';
   echo "<title>";
   echo "</title>";
   echo '</head>';
   echo '<body bgcolor=#EFEFEF>';
   $xx="";
   $xx1="";
   if ($m_xb>0){
      $xx="<font color='red'><b>";
      $xx1="</font></b>";
   }
   echo "постов:&nbsp;".$xx.$m_xb.$xx1;
   echo '</body>';
   echo '</html>';
}
?>