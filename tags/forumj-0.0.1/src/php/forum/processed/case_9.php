<?
/*Аватара*/
$sql_avatar="
SELECT 
   avatar,
   s_avatar,
   ok_avatar
FROM
   users
WHERE
   id=".$_SESSION['idu'];     
$rslt_avatar=fd_query($sql_avatar, $conn, "");
if (trim(mysql_result($rslt_avatar, 0, 'avatar'))<>"" and mysql_result($rslt_avatar, 0, 'ok_avatar'))
{?>
   <div class=mnuprof align='CENTER'>
   	<b>
   		<?echo($_mess93);?>
   	</b>
   </div>
   <div>
   	<img border='0' src='<?echo(trim(mysql_result($rslt_avatar, 0, 'avatar')));?>'>
   </div>
   <br>
   <div>
   	<?echo($_mess95);?>
   </div>
   <br>
<?}else{?>
   <div class=mnuprof align='CENTER'>
   	<b>
   		<?echo($_mess92);?>
   	</b>
   </div>
   <br>
   <div>
   	<?echo($_mess96);?>
   </div>
   <br>
<?}?>
<form method='POST' class=content action='setavatar.php?id=9'>
	<?echo($_mess97."&nbsp;");?>
	<input type=text size=100 name='avatar' value='<?echo(trim(htmlspecialchars(stripslashes(mysql_result($rslt_avatar, 0, 'avatar')))));?>'>
	<br>
	<br>
	<?	
	if (mysql_result($rslt_avatar, 0, 's_avatar'))
	{?>
   	<input type=checkbox checked  name='s_avatar'>
   	<?echo("&nbsp;".$_mess94);?>
   	<br>
   	<br>
	<?}else{?>
   	<input type=checkbox  name='s_avatar'>
   	<?echo("&nbsp;".$_mess94);?>
   	<br>
   	<br>
	<?}?>
	<input type='submit' value='<?echo($_mess75);?>'>
	<?echo(fd_form_add());?>
</form>
<?
$sql_vavatar="
SELECT 
   v_avatars
FROM
   users
WHERE
   id=".$_SESSION['idu'];     
$rslt_vavatar=fd_query($sql_vavatar, $conn, "");
?>
<form method='POST' class=content action='vavatar.php?id=9'>
   <?
   if (mysql_result($rslt_vavatar, 0, 'v_avatars'))
   {?>
   	<input type=checkbox checked  name='v_avatar'>
   	<?echo("&nbsp;".$_mess98);?>
   	<br>
   	<br>
   <?}else{?>
   	<input type=checkbox  name='v_avatar'>
   	<?echo("&nbsp;".$_mess98);?>
   	<br>
   	<br>
   <?}?>
   <input type='submit' value='<?echo($_mess85);?>'>
   <?echo(fd_form_add());?>
</form>