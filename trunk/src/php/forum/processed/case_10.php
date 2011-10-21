<?
// Местонахождение
$sql_location="
SELECT 
   fd_timezone,
   city,
   scity,
   country,
   scountry
FROM
   users
WHERE
   id=".$_SESSION['idu'];     
$rslt_location=fd_query($sql_location, $conn, "");
?>
<div class=mnuprof align='CENTER'>
	<b><?echo($_mess105);?></b>
</div>
<br>
<div>
	<form method='POST' class=content action='setlocation.php?id=10'>
		<?echo(fd_form_add());?>
		<table>
			<tr>
				<td>
    			<?echo($_mess106);?>
    		</td>
    		<td>
          <select name="timezone">
          	<?for ($x10=1; $x10<31; $x10++){?>
          		<option value="<?echo($x10);?>"<?if ($x10==mysql_result($rslt_location, 0, 'fd_timezone')){echo(' SELECTED ');}?>><?echo($_mess110[$x10]);?></option>	
          	<?}?>	
          </select>		
        </td>
      </tr>
			<tr>
				<td>
    			<?echo($_mess111);?>
    		</td>
    		<td>
    			<input size="25" maxlength="20" type="text" name="country" value="<?echo(htmlspecialchars(stripslashes(mysql_result($rslt_location, 0, 'country'))));?>">
    			<input type="checkbox" name="scountry" value="1"<?if (mysql_result($rslt_location, 0, 'scountry')){echo(' CHECKED');}?>>
    			<?echo("&nbsp;".$_mess113);?>
    		</td>
    	</tr>
			<tr>
				<td>
    			<?echo($_mess112);?>
    		</td>
    		<td>
    			<input size="25" maxlength="20" type="text" name="city"value="<?echo(htmlspecialchars(stripslashes(mysql_result($rslt_location, 0, 'city'))));?>">
    			<input type="checkbox" name="scity" value="1"<?if (mysql_result($rslt_location, 0, 'scity')){echo(' CHECKED');}?>>
    			<?echo("&nbsp;".$_mess113);?>
    		</td>
    	</tr>
			<tr>
				<td colspan='2' align=right>
					<br>
					<input type="submit" value="<?echo($_mess85);?>">
    		</td>
    	</tr>
    </table>
	</form>	
</div>
