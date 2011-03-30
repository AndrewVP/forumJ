<?php
$strt=microtime();
$startSec=substr($strt,11);
$startMSec=substr($strt,0,10);
include("setup.php");
include("query.php");
$sqlIds="
SELECT id_body from body_id
";
$rsltIds=fd_query($sqlIds, $conn, "5");
$x=0;
while($row=mysql_fetch_row($rsltIds)) {
	$sqlUpdate="
	UPDATE 
		body
	SET
		fd_state=1
	WHERE
		id=$row[0]
	";
	$rsltUpdate=fd_query($sqlUpdate, $conn, "5");
	$x++;
	echo $x*100/15898;
	echo " %<br/>";
	echo "id: ".$row[0]."<br/>";
   $end=microtime();
   $pgTime=substr(substr($end,11)-$startSec+substr($end,0,10)-$startMSec, 0, 5);
   echo "Time: ".$pgTime."<br/><br/>";
}
mysql_free_result($rsltIds);
mysql_close($conn);

?>