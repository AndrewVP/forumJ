<?
// Индикатор
session_start();
include("query.php");
//Предотвращаем кеширование
include("cache.php");
// Соединяемся с MySql
include('setup.php');
// Собираем статистику
// Записываем свероятностью 5% - чтобы не перегружать БД
$rand = rand(0, 20);
if ($rand < 1){
	$action=23;
	include("stat.php");
}
//
if (isset($_GET['idb'])){
   $m_xb=$_GET['idb'];
   $sql_indb="SELECT
                count(id) as mx
             FROM body
             WHERE id>".$m_xb;
   $res=fd_query($sql_indb, $conn, "");
   $m_xb=mysql_result($res, 0, 'mx');
   $m_xt=$_GET['idt'];
   $sql_indt="SELECT
                count(id) as mx
             FROM titles
             WHERE id>".$m_xt;
   $res=fd_query($sql_indt, $conn, "");
   $m_xt=mysql_result($res, 0, 'mx');
   $ids=$_GET['ids'];
   $arIds=array_values(split(";", $ids));
   for ($x=0; $x<count($arIds); $x++){
   	$arrIds[]=array_values(split(",", $arIds[$x]));
   }
   $retIds="";
   for ($x=0; $x<count($arrIds); $x++){
	   $sql_indb="SELECT
	                count(id) as mx
	             FROM body
	             WHERE 
	                id>".$arrIds[$x][1]."
	                AND head=".$arrIds[$x][0]."
	             ";
	   $res=fd_query($sql_indb, $conn, "");
	   $mx=mysql_result($res, 0, 'mx');
	   $retIds.="|".$arrIds[$x][0].",".$mx;
   }
   echo $m_xt;
   echo ";".$m_xb;
   echo ";".substr($retIds, 1);
}?>
