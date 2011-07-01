<?
ob_start();
session_start();
include("cache.php");
include("setup.php");
include("query.php");
include("post.php");
include("head.php");
include("bbcode.php");
include("smiles.php");
include("href.php");
include("cenz.php");
include("body.php");
$action=16;
include("stat.php");
$location="Location: search.php";
include("cookie.php");
include("exit.php");
include("lang.php");
?>
<html>
<head>
<meta http-equiv='content-type'
	content='text/html; charset=windows-1251'>
<?
include('style.php');
?>
<title><?echo($_mess30);?></title>
</head>
<body bgcolor=#EFEFEF>
<table border='0' style='border-collapse: collapse' width='100%'>
<?
include("logo.php");
?>
	<tr>
		<td width='100%'>
		<table border='0' style='border-collapse: collapse' width='100%'>
		<?
		include("menu.php");
		$space=300;
		$ss="";
		if (isset($_GET['SP'])) {
			$space=0;
			$ss=$_GET['SP'];
		}
		if (isset($_GET['SP_N'])) {
			$space=0;
			$sn=$_GET['SP_N'];
		}
		?>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table>
			<tr>
				<td height='.$space.' valign="TOP"><?php
				 $sqlDate="
				 SELECT
				 date_post
				 FROM
				 ss
				 ";
				 $rsltDate=fd_query($sqlDate, $conn, "5");
				 $sDate=mysql_result($rsltDate, 0, "date_post");
				 $sDate=substr($sDate,8,2).".".substr($sDate,5,2).".".substr($sDate,0,4)." ".substr($sDate,11,2).":".substr($sDate,14,2);
				/*
				$sDate="XX";
				 */
				?>
				<div class='messageDiv'><?=$_mess160?></div>
			</td>
		</tr>
	</table>
	</td>
	</tr>
	<tr>
		<td width='100%'>
		<table border='0' style='border-collapse: collapse' width='100%'>
		<?
		include("menu.php");
		?>
		</table>
		</td>
	</tr>
	<?
	include("end.php");
	?>

</body>
</html>
	<?
	$strtmp=ob_get_contents();
	ob_end_clean();
	echo $strtmp;
	?>