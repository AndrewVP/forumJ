<?php
define("IN_HEAD", 0, true);
define("IN_BODY", 1, true);
define("TOP_POST", 0, true);
define("NOT_TOP_POST", 1, true);
//include("setup.php");
include_once("query.php");
include_once("search_engine.php");
$sqlId="
SELECT
	block
FROM
	ss
";
$rsltId=fd_query($sqlId, $conn, "5");
$block=mysql_result($rsltId, 0, "block");
//mysql_free_result($rsltId);
if ($block<1){
	$sqlId="
	UPDATE
		ss
	SET
		block=1
	";
	$rsltId=fd_query($sqlId, $conn, "5");
	$sqlId="
	SELECT
		id_post
	FROM
		ss
	";
	$rsltId=fd_query($sqlId, $conn, "5");
	$x1=mysql_result($rsltId, 0, "id_post");
	//mysql_free_result($rsltId);
	if ($x1>0){
		$x2=$x1-1;
		$sqlBody="
			SELECT
				*
			FROM
				body
			WHERE
				body.id=$x1
		";
		$rsltBody=fd_query($sqlBody, $conn, 5);
		while($row=mysql_fetch_row($rsltBody)) {
			if ($row[11]){
					$topPost=TOP_POST;	
			}else{
					$topPost=NOT_TOP_POST;	
			}
			fd_delete_post_data($row[0], $conn, "", "");		
			$searchHead=stripslashes($row[3]);
			fd_new_search_head($searchHead, $row[0], $row[5], $conn, $row[2], IN_HEAD, $topPost, "");
			$searchBody=$row[4];
			$searchBody=str_replace("\\r", " ", $searchBody);
			$searchBody=str_replace("\\n", " ", $searchBody);
			fd_new_search_head($searchBody, $row[0], $row[5], $conn, $row[2], IN_BODY, $topPost, "");	
			$tmPost=$row[5];
		}
		mysql_free_result($rsltBody);
		if (!isset($tmPost)){
			$tmPost="";	
		}
		$sqlId="
		UPDATE
			ss
		SET
			id_post=$x2,
			date_post='$tmPost',
			block=0
		";
		$rsltId=fd_query($sqlId, $conn, "5");
	}
}
?>