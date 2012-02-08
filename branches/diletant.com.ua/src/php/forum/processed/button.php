<?
function fd_button($mess, $onClick, $name, $numb, $r1, $r1, $r1, $r1){
	$button="";
	$button="
	<table id='".$name."_table' class='bttn".$numb."' onclick='".$onClick."'>
		<tr>
			<td class='bttn".$numb."LeftTop'></td>
			<td class='bttn".$numb."Top'></td>
			<td class='bttn".$numb."RightTop'></td>
		</tr>
		<tr>
			<td class='bttn".$numb."Left'></td>
			<td class='bttn".$numb."Bg'>".$mess."</td>
			<td class='bttn".$numb."Right'></td>
		</tr>
		<tr>
			<td class='bttn".$numb."LeftBtm'></td>
			<td class='bttn".$numb."Btm'></td>
			<td class='bttn".$numb."RightBtm'></td>
		</tr>
	</table>			
	";
	return $button;
}
