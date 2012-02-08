<?
function fd_input($name, $value, $size, $numb, $r1, $r2, $r3, $r4){
	$button="";
	$button="
	<table id='".$name."_table' class='input".$numb."'>
		<tr>
			<td class='input".$numb."LeftTop'></td>
			<td class='input".$numb."Top'></td>
			<td class='input".$numb."RightTop'></td>
		</tr>
		<tr>
			<td class='input".$numb."Left'></td>
			<td class='input".$numb."Bg'><input class='input".$numb."' type=text name='".$name."' size='".$size."' value='".$value."' maxlength='120'></td>
			<td class='input".$numb."Right'></td>
		</tr>
		<tr>
			<td class='input".$numb."LeftBtm'></td>
			<td class='input".$numb."Btm'></td>
			<td class='input".$numb."RightBtm'></td>
		</tr>
	</table>			
	";
	return $button;
}
