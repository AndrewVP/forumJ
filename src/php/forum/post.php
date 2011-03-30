<?php
include_once('str_ireplace.php');
function fd_post($str_nick,
					$str_idu,
					$str_reg,
					$str_id,
					$str_head,
					$str_body,
					$str_t_id,
					$str_nred,
					$str_dred,
					$str_ip,
					$str_hip,
					$str_avatar,
					$str_s_avatar,
					$str_ok_avatar,
					$str_country,
					$str_city,
					$str_s_city,
					$str_footer,
					$str_domen,
					$str_s_country,
					$pg,
					$gid,
					$ignor,
					$res_ignor,
					$_mess111,
					$_mess68,
					$_mess112,
					$_mess114,
					$_mess139,
					$_mess140,
					$_mess141,
					$_mess142,
					$conn,
					$_mess143,
					$_mess144, 
					$_mess145, 
					$_mess146, 
					$_mess147, 
					$_mess148, 
					$_mess149, 
					$_mess150, 
					$_mess151,
					$_mess152,
					$_mess153,
					$isLastPost,
					$isQuest,
					$isAdminForvard,
					$isUserCanAddAnswer,
					$_mess50,
					$_mess51,
					$str_v_avatars,
					$is_found,
					$_mess161,
					$_mess162,
					$idWordsForms
					){
	if ($is_found==1){
		$str_nick=htmlspecialchars($str_nick);
		$str_nick=str_ireplace($idWordsForms, "<span class='found'>".$idWordsForms."</span>", $str_nick);
//		$str_nick="<span class='found'>".$str_nick."</span>";
	}elseif($is_found==2){
		$rp = "[span class='found']$0[/span]";
		$str_head = preg_replace($idWordsForms,$rp, $str_head);
	}elseif($is_found==3){
		$rp="[span class='found']$0[/span]";
		$str_body=preg_replace($idWordsForms,$rp, $str_body);
	}
	$result='';
	if (trim($str_ip)==trim($str_domen)){
		$str_domen=substr($str_domen, 0, strrpos($str_domen, '.')+1).'---';
	}else{
		$str_domen='---'.substr($str_domen, strpos($str_domen, '.'));
	}
	$result.="<tr class=heads>";
	$result.= "<td  class=internal>";
	if ($isLastPost) $result.="<a name='end'>";
	$result.= "<a name='$str_id'>&nbsp;</a>";
	$result.= "<a class=nik href='tema.php?id=".$gid."&msg=".$str_id."#".$str_id."'><b>&nbsp;&nbsp;".fd_head(htmlspecialchars($str_head))."</b></a>";
	$result.= "</td></tr>";
	$result.= "<tr><td>";
	$div=0;
	$_div="";
	if ($ignor){
		for ($xi1=0; $xi1<$ignor; $xi1++){
			if ($str_idu==mysql_result($res_ignor, $xi1, 'ignor')) $div=1;
		}
	}
	$result.= "<span class='tbtextnread'>".$str_nick."</span>&nbsp;".chr(149);
	$result.="&nbsp;<img border='0' src='smiles/icon_minipost.gif'>&nbsp;<span class='posthead'>".substr($str_reg,8,2).".".substr($str_reg,5,2).".".substr($str_reg,2,2)."&nbsp;".substr($str_reg,11,2).":".substr($str_reg,14,2)."</span>&nbsp;";
	if (!$div and isset($_SESSION['idu']) and $str_idu!=$_SESSION['idu']){
		$result.= chr(149).'&nbsp;<a class="posthead" href="ignor.php?idi='.$str_idu.'&idt='.$gid.'&idp='.$str_id.'&pg='.$pg.'" rel="nofollow">'.$_mess68.'</a>';
	}
	$result.="</td></tr>";
	$result.="<tr><td>";
	if ($div){
		$result.= "<a href='#' onclick='togglemsg(\"dd".$str_id."\"); return false;' rel='nofollow'>".$_mess142."</a>";
		$_div=" style='visibility: hidden; display:none;'";
	}
	else {
		$_div="";
	}
	$result.= "<div id=dd".$str_id.$_div.">";
	if (!(!isset($_SESSION['idu']) and $div)){
		$result.= "<table width='100%'><tr><td valign=top class='matras'>";
		$result.= "<table style='table-layout:fixed;' width='170'><tr><td valign=top>";
		$result.= "<div style='padding:10px;'>";
		if ($str_s_avatar and $str_ok_avatar and trim($str_avatar)<>"" and $str_v_avatars){
			$result.= "<a href='control.php?id=9'><img border='0' src='".$str_avatar."' rel=\"nofollow\"></a>";
		}else{
			$result.= "<a href='control.php?id=9' rel='nofollow'><img border='0' src='smiles/no_avatar.gif'></a>";
		}
		$result.= "</div>";
		$result.= "<span class='posthead'><u>".$_mess111."</u></span><br>";
		if ($str_s_country || $str_country==""){
			$result.= "<span class='posthead'>".$_mess114."</span><br>";
		}else{
			$result.= "<span class='posthead'>".$str_country."</span><br>";
		}
		$result.= "<span class='posthead'><u>".$_mess112."</u></span><br>";
		if ($str_s_city || $str_city==""){
			$result.= "<span class='posthead'>".$_mess114."</span><br>";
		}else{
			$result.= "<span class='posthead'>".$str_city."</span><br>";
		}
		$result.= "</td></tr></table>";
		$result.= "</td><td valign='top' width='100%'>";
		$result.= "<table width='100%'>";
		if ($isQuest){
   			$result.=fd_opros($str_t_id, 
   								$conn, 
   								$gid, 
   								$_mess143,
								$_mess144, 
								$_mess145, 
								$_mess146, 
								$_mess147, 
								$_mess148, 
								$_mess149, 
								$_mess150, 
								$_mess151,
								$_mess152,
								$_mess153,
								$isUserCanAddAnswer,
								$_mess161 
   								);
		}
		$result.="<tr><td>";
		$result.="<p class=post>".fd_body($str_body)."</p>";
		$result.="</td></tr>";
		$result.= "</table></td></tr>";
		$result.="<tr><td class='matras' colspan=2></td></tr>";
		$result.="<tr><td class='matras'></td><td>";
		$result.="<p class=post>".fd_body($str_footer)."</p>";
		$result.="</td></tr>";
		$result.="<tr><td align='RIGHT' width='100%' colspan=2>";
		if ($str_nred>0){
			$result.= "<table class='matras' width='100%'>";
			$result.= "<tr><td align='LEFT'>";
			$result.= "<span class='posthead'>".$_mess50.$str_nred.$_mess51.$str_dred."</span>";
		}
		else {
			$result.= "<table class='matras'>";
			$result.= "<tr><td align='LEFT'>";
			$result.= " ";
		}
		$result.="</td>";
		if (isset($_SESSION['autor'])) {
			if ($isAdminForvard){
				$result.= "<td align='CENTER' width='70'>";
				$result.= "<span class='posthead'><a href='site.php?id=".$gid."&post=".$str_id."' rel=\"nofollow\">$_mess162</a></span>";
				$result.= "</td>";
			}
			if ($_SESSION['idu']==$str_idu) {
				$result.= "<td align='CENTER' width='70'>";
				$result.= "<span class='posthead'><a href='tema.php?id=".$gid."&reply=".$str_id."#edit' rel=\"nofollow\">$_mess141</a></span>";
				$result.= "</td>";
			}
			else {
				$result.= "<td align='CENTER' width='70'>";
				$result.= "<span class='posthead'><a href='tema.php?id=".$gid."&reply=".$str_id."#edit' rel=\"nofollow\">$_mess139</a></span>";
				$result.= "</td>";
				$result.= "<td align='CENTER' width='70'>";
				$result.= "<span class='posthead'><a href='tema.php?id=".$gid."&reply=".$str_id."&ans=1#edit' rel=\"nofollow\">$_mess140</a></span>";
				$result.= "</td>";
			}}
   $result.= "</tr></table>";
   $result.= "</td></tr>";
   $result.= "</table>";
	}else{
		$result.= $_mess103;
	}
	$result.= "</div>";
	$result.= "</td></tr>";
	return $result;

}
function fd_opros($str_t_id,
					IConnection $conn,
					$gid,
					$_mess143, 
					$_mess144, 
					$_mess145, 
					$_mess146, 
					$_mess147, 
					$_mess148, 
					$_mess149, 
					$_mess150, 
					$_mess151,
					$_mess152,
					$_mess153,
					$isUserCanAddAnswer,
					$_mess161 
					){
	$result='';
	$query_quest="
      SELECT
         quest.id, 
         quest.node, 
         quest.user, 
         quest.gol, 
         quest.type, 
         users.nick 
      FROM 
      quest 
      left join users on quest.user=users.id 
      WHERE 
         quest.head=".$str_t_id." 
      ORDER BY 
         numb";
	$resq1= $conn->doQuery($query_quest);
	$strq_quest=stripslashes(mysql_result($resq1, 0, 'node'));
	$strqnumb=mysql_num_rows($resq1);
	if (isset($_SESSION['idu'])){
		$query_quest2="
         SELECT 
            user 
         FROM 
            voice 
         WHERE 
            head=".$str_t_id." 
            AND user=".$_SESSION['idu'];
		$resq2=$conn->doQuery($query_quest2);
		$strq2numb=mysql_num_rows($resq2);
	}
	$sql_nvcs="
      SELECT 
         COUNT(id) AS nvcs 
      FROM 
         voice 
      WHERE 
         head=".$str_t_id;
	$resqnvc= $conn->doQuery($sql_nvcs);
	$nvcs=mysql_result($resqnvc, 0, 'nvcs');
	$result.=("<tr><td>");
	$result.= "<p align=\"CENTER\"><font size=4><b>".$strq_quest."</b></font></p><br>";
	$result.=("</td></tr>");
	$result.=("<tr><td align=\"CENTER\">");
	if (isset($_SESSION['autor']) and !$strq2numb){
		$result.=("<form  action='voice.php' method='POST'><table class=content>");
		for ($iq1=1; $iq1<$strqnumb; $iq1++){
			$result.=("<tr><td class=voice_left align='right'>");
			$in1=mysql_result($resq1, $iq1, 'id');
			$check="";
			if ($iq1==1) $check=" CHECKED";
			if (mysql_result($resq1, $iq1, 'type')){
				$result.= $_mess143;
				if (mysql_result($resq1, $iq1, 'type')==1){
					$result.= "<b>".mysql_result($resq1, $iq1, 'nick')."</b>";
				}
				else{
					$result.= "<b>".$_mess144."</b>";
				}
				$result.= "</td><td class=voice_right align='left'>";
				$result.=("<input type='radio' name='ANSWER' value='$in1'>&nbsp;".fd_smiles(fd_href(stripslashes(mysql_result($resq1, $iq1, 'node'))))."<br>");
			}
			else {
				$result.= "</td><td class=voice_right align='left'>";
				$result.=("<input type='radio' name='ANSWER' value='$in1'".$check.">&nbsp;".fd_smiles(fd_href(stripslashes(mysql_result($resq1, $iq1, 'node'))))."<br>");
			}
			$result.=("</td></tr>");
		}
		$result.=("<tr><td colspan='2' align='CENTER'>");
		$result.= "<input type=hidden name=\"IDU1\" size=\"20\" value=\"".$_SESSION['idu']."\">";
		$result.= "<input type=hidden name=\"AUT1\" size=\"20\" value=\"".$_SESSION['autor']."\">";
		$result.= "<input type=hidden name=\"IDT1\" size=\"20\" value=\"".$gid."\">";
		if (isset($_SESSION['pass2'])) {
			$result.= "<input type=hidden name=\"PS21\" size=\"20\" value=\"".$_SESSION['pass2']."\">";
		}
		else {
			$result.= "<input type=hidden name=\"PS11\" size=\"20\" value=\"".$_SESSION['pass1']."\">";
		}
		$result.="<input type='submit' value='".$_mess145."' name='OK'>";
		$result.=("</td></tr>");
		$result.=("</table></form>");
		$result.=("</td></tr>");
		if ($isUserCanAddAnswer){
			$iq3=0;
			for ($iq2=1; $iq2<$strqnumb; $iq2++){
				if (mysql_result($resq1, $iq2, 'user')==$_SESSION['idu']) $iq3=1;
			}
			if (!$iq3){
				$result.=("<tr><td>");
				$result.=("<form  action=\"uservoice.php\" method=\"POST\"><table align=\"CENTER\">");
				$result.=("<tr><td>");
				$result.=$_mess153.":<br>";
				$result.=("<input type=\"text\" name=\"P\" size=\"100\">");
				$result.= "<input type=hidden name=\"IDU2\" size=\"20\" value=\"".$_SESSION['idu']."\">";
				$result.= "<input type=hidden name=\"AUT2\" size=\"20\" value=\"".$_SESSION['autor']."\">";
				$result.= "<input type=hidden name=\"IDT2\" size=\"20\" value=\"".$gid."\">";
				if (isset($_SESSION['pass2'])) {
					$result.= "<input type=hidden name=\"PS22\" size=\"20\" value=\"".$_SESSION['pass2']."\">";
				}
				else {
					$result.= "<input type=hidden name=\"PS12\" size=\"20\" value=\"".$_SESSION['pass1']."\">";
				}
				$result.=("</td></tr>");
				$result.=("<tr><td align=\"CENTER\">");
				$result.="<input type='checkbox' name='HD' value='1' checked>&nbsp;".$_mess146."<br>";
				$result.="<input type='submit' value='".$_mess145."' name='OK'>";
				$result.=("</td></tr>");
				$result.=("</table></form>");
				$result.=("</td></tr>");
   }
		}
	}
	if (!$nvcs) $nvcs=1/10000000;
	$result.=("<tr><td align=\"CENTER\">");
	$result.= "<b>".$_mess152.": ".round($nvcs,0)."</b>";
	$result.=("</td></tr>");
	$result.=("<tr><td align=\"CENTER\">");
	$result.= "<table align='CENTER' class=control>";
	$result.=("<tr class=heads><th class='internal'>");
	$result.= $_mess147;
	$result.=("</th><th class='internal'>");
	$result.= $_mess148;
	$result.=("</th><th class='internal'>");
	$result.= $_mess149;
	$result.=("</th><th class='internal' width='350'>");
	$result.= $_mess150;
	$result.=("</th><th class='internal'>");
	$result.= $_mess151;
	$result.=("</th></tr><tr>");
	for ($iq11=1; $iq11<$strqnumb; $iq11++){
		if (mysql_result($resq1, $iq11, 'type')){
			if (mysql_result($resq1, $iq11, 'type')==1){
				$result.= "<td align='LEFT' class='internal'>".mysql_result($resq1, $iq11, 'nick')."</td>";
			}
			else{
				$result.= "<td align='LEFT' class='internal'>".$_mess144."</td>";
			}}
			else
			{
				$result.= "<td align='LEFT' class='internal'></td>";
			}
			$result.=("<td class='internal'>".fd_body(stripslashes(mysql_result($resq1, $iq11, 'node')))."</td>");

			$result.=("<td align='CENTER' class='internal'>");
			$result.=(mysql_result($resq1, $iq11, 'gol')."</td>");
			$result.= "<td class='internal'><TABLE cellSpacing=0 cellPadding=0 width='".round((mysql_result($resq1, $iq11, 'gol')/$nvcs)*300,0)."' border=0><TR><TD align=left bgColor=red><FONT size=-3>&nbsp;</FONT></TD></TR></TABLE>";
			$result.=("</td>");
			$result.=("<td class='internal'>");
			$result.=(round((mysql_result($resq1, $iq11, 'gol')/$nvcs)*100, 2))."%";
			$result.=("</td></tr>");

	}
	$result.= "</table>";
	if (isset($strq2numb) and $strq2numb){
		$result.= "<form method=\"POST\" action=\"delvoice.php\" align=\"CENTER\">";
		$result.= "<input type=hidden name=\"IDU\" size=\"20\" value=\"".$_SESSION['idu']."\">";
		$result.= "<input type=hidden name=\"AUT\" size=\"20\" value=\"".$_SESSION['autor']."\">";
		$result.= "<input type=hidden name=\"IDT\" size=\"20\" value=\"".$gid."\">";
		if (isset($_SESSION['pass2'])) {
			$result.= "<input type=hidden name=\"PS2\" size=\"20\" value=\"".$_SESSION['pass2']."\">";
		}
		else {
			$result.= "<input type=hidden name=\"PS1\" size=\"20\" value=\"".$_SESSION['pass1']."\">";
		}

		$result.= "<input type='submit' value='".$_mess161."'>";
		$result.= "</form>";
	}
	$result.=("</td></tr>");
	return $result;
}
?>