<div class=mnuprof align='CENTER'>
	<b>
		<?echo($_mess138);?>
	</b>
</div>
<?
/*подпись*/
$sql_footer="
SELECT 
   footer
FROM
   users
WHERE
   id=".$_SESSION['idu'];     
$rslt_footer=fd_query($sql_footer, $conn, "");
if (trim(mysql_result($rslt_footer, 0, 'footer'))<>"")
{
	$textArea=htmlspecialchars(stripslashes(mysql_result($rslt_footer, 0, 'footer')));
}else{
	$textArea="";
}?>
<form method='POST' name='footer' class=content action='setfooter.php?id=11'>
	<br>
	<br>
	<textarea name='foot' cols=50 rows=15 onkeyup="checkLength(this, 255);" onkeypress="this.onkeyup();" onChange="this.onkeyup();" onFocus="this.onkeyup();" onBlur="this.onkeyup();" onSelect="this.onkeyup();" onMouseOut="this.onkeyup();" onMouseMove="this.onkeyup();"><?echo($textArea);?></textarea>
	<br>
	<?echo(fd_button($_mess85,"document.footer.submit();","foot_ok", "1", "", "", "", ""));?>
   <?echo(fd_form_add());?>
</form>