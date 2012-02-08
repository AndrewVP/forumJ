<script language="PHP">
//include("cenz.php");
function fd_bbcode($str_body) {
//   $str_body=str_replace("\\r\\n","<br>",htmlspecialchars($str_body));
   $postbody=htmlspecialchars($str_body);
//   $postbody=stripslashes($str_body);
   // [b] [/b] <b> </b>
   $result="";
   $lastocc=0;
   $sndocc=1;
   while($sndocc)
   {
      $fstocc=strpos($postbody, "[b]", $lastocc);
      $sndocc=strpos($postbody, "[/b]", $fstocc);
      if(($fstocc > 0 && $sndocc > 0 && $lastocc > 0) || ($fstocc >= 0 && $sndocc > 0 && $lastocc== 0))
      {
         $result .= substr($postbody, $lastocc, $fstocc - $lastocc);
         $result .= "<b>".substr($postbody, $fstocc + 3, $sndocc - $fstocc - 3)."</b>";
         $lastocc = $sndocc + 4;
      }
      else
      {
         $result .=substr($postbody, $lastocc, strlen($postbody)-$lastocc);
         break;
      }
   }
   // [i] [/i] <i> </i>
   $postbody=$result;
   $result="";
   $lastocc=0;
   $sndocc=1;
   while($sndocc)
   {
      $fstocc=strpos($postbody, "[i]", $lastocc);
      $sndocc=strpos($postbody, "[/i]", $fstocc);
      if(($fstocc > 0 && $sndocc > 0 && $lastocc > 0) || ($fstocc >= 0 && $sndocc > 0 && $lastocc== 0))
      {
         $result .= substr($postbody, $lastocc, $fstocc - $lastocc);
         $result .= "<i>".substr($postbody, $fstocc + 3, $sndocc - $fstocc - 3)."</i>";
         $lastocc = $sndocc + 4;
      }
      else
      {
         $result .=substr($postbody, $lastocc, strlen($postbody)-$lastocc);
         break;
      }
   }
   // [u] [/u] <u> </u>
   $postbody=$result;
   $result="";
   $lastoccu=0;
   $sndoccu=1;
   while($sndoccu)
   {
      $fstoccu=strpos($postbody, "[u]", $lastoccu);
      $sndoccu=strpos($postbody, "[/u]", $fstoccu);
      if(($fstoccu > 0 && $sndoccu > 0 && $lastoccu > 0) || ($fstoccu >= 0 && $sndoccu > 0 && $lastoccu== 0))
      {
         $result .= substr($postbody, $lastoccu, $fstoccu - $lastoccu);
         $result .= "<u>".substr($postbody, $fstoccu + 3, $sndoccu - $fstoccu - 3)."</u>";
         $lastoccu = $sndoccu + 4;
      }
      else
      {
         $result .=substr($postbody, $lastoccu, strlen($postbody)-$lastoccu);
         break;
      }
   }
   // [size=1] [/size]
   $sndoccq=1;
   while ($sndoccq>0){
      $postbody=$result;
      $lastoccq=0;
      $sndoccq=2;
      $fstoccq1=1;
      while($fstoccq1<$sndoccq and $fstoccq1){
         $fstoccq=strpos(" ".$postbody, "[size=1]", $lastoccq);
         $fstoccq1=strpos(" ".$postbody, "[size=1]", $fstoccq+1);
         $sndoccq=strpos(" ".$postbody, "[/size]", $fstoccq+1);
         $lastoccq=$fstoccq1;
      }
      if ($sndoccq and $fstoccq){
         $result=substr($postbody, 0, $fstoccq-1)."<span class='post1'>".substr($postbody, $fstoccq+7,$sndoccq - $fstoccq - 8)."</span>".substr($postbody, $sndoccq+6);
      }
      else{
         break;
      }
   }
   // [size=2] [/size]
   $sndoccq=1;
   while ($sndoccq>0){
      $postbody=$result;
      $lastoccq=0;
      $sndoccq=2;
      $fstoccq1=1;
      while($fstoccq1<$sndoccq and $fstoccq1){
         $fstoccq=strpos(" ".$postbody, "[size=2]", $lastoccq);
         $fstoccq1=strpos(" ".$postbody, "[size=2]", $fstoccq+1);
         $sndoccq=strpos(" ".$postbody, "[/size]", $fstoccq+1);
         $lastoccq=$fstoccq1;
      }
      if ($sndoccq and $fstoccq){
         $result=substr($postbody, 0, $fstoccq-1)."<span class='post2'>".substr($postbody, $fstoccq+7,$sndoccq - $fstoccq - 8)."</span>".substr($postbody, $sndoccq+6);
      }
      else{
         break;
      }
   }
   // [size=3] [/size]
   $sndoccq=1;
   while ($sndoccq>0){
      $postbody=$result;
      $lastoccq=0;
      $sndoccq=2;
      $fstoccq1=1;
      while($fstoccq1<$sndoccq and $fstoccq1){
         $fstoccq=strpos(" ".$postbody, "[size=3]", $lastoccq);
         $fstoccq1=strpos(" ".$postbody, "[size=3]", $fstoccq+1);
         $sndoccq=strpos(" ".$postbody, "[/size]", $fstoccq+1);
         $lastoccq=$fstoccq1;
      }
      if ($sndoccq and $fstoccq){
         $result=substr($postbody, 0, $fstoccq-1)."<span class='post3'>".substr($postbody, $fstoccq+7,$sndoccq - $fstoccq - 8)."</span>".substr($postbody, $sndoccq+6);
      }
      else{
         break;
      }
   }
   // [size=4] [/size]
   $sndoccq=1;
   while ($sndoccq>0){
      $postbody=$result;
      $lastoccq=0;
      $sndoccq=2;
      $fstoccq1=1;
      while($fstoccq1<$sndoccq and $fstoccq1){
         $fstoccq=strpos(" ".$postbody, "[size=4]", $lastoccq);
         $fstoccq1=strpos(" ".$postbody, "[size=4]", $fstoccq+1);
         $sndoccq=strpos(" ".$postbody, "[/size]", $fstoccq+1);
         $lastoccq=$fstoccq1;
      }
      if ($sndoccq and $fstoccq){
         $result=substr($postbody, 0, $fstoccq-1)."<span class='post4'>".substr($postbody, $fstoccq+7,$sndoccq - $fstoccq - 8)."</span>".substr($postbody, $sndoccq+6);
      }
      else{
         break;
      }
   }
   // [size=5] [/size]
   $sndoccq=1;
   while ($sndoccq>0){
      $postbody=$result;
      $lastoccq=0;
      $sndoccq=2;
      $fstoccq1=1;
      while($fstoccq1<$sndoccq and $fstoccq1){
         $fstoccq=strpos(" ".$postbody, "[size=5]", $lastoccq);
         $fstoccq1=strpos(" ".$postbody, "[size=5]", $fstoccq+1);
         $sndoccq=strpos(" ".$postbody, "[/size]", $fstoccq+1);
         $lastoccq=$fstoccq1;
      }
      if ($sndoccq and $fstoccq){
         $result=substr($postbody, 0, $fstoccq-1)."<span class='post5'>".substr($postbody, $fstoccq+7,$sndoccq - $fstoccq - 8)."</span>".substr($postbody, $sndoccq+6);
      }
      else{
         break;
      }
   }
   // [color=red] [/color]
   $sndoccq=1;
   while ($sndoccq>0){
      $postbody=$result;
      $lastoccq=0;
      $sndoccq=2;
      $fstoccq1=1;
      while($fstoccq1<$sndoccq and $fstoccq1){
         $fstoccq=strpos(" ".$postbody, "[color=red]", $lastoccq);
         $fstoccq1=strpos(" ".$postbody, "[color=red]", $fstoccq+1);
         $sndoccq=strpos(" ".$postbody, "[/color]", $fstoccq+1);
         $lastoccq=$fstoccq1;
      }
      if ($sndoccq and $fstoccq){
         $result=substr($postbody, 0, $fstoccq-1)."<font color='red'>".substr($postbody, $fstoccq+10,$sndoccq - $fstoccq - 11)."</font>".substr($postbody, $sndoccq+7);
      }
      else{
         break;
      }
   }
   // [color=green] [/color]
   $sndoccq=1;
   while ($sndoccq>0){
      $postbody=$result;
      $lastoccq=0;
      $sndoccq=2;
      $fstoccq1=1;
      while($fstoccq1<$sndoccq and $fstoccq1){
         $fstoccq=strpos(" ".$postbody, "[color=green]", $lastoccq);
         $fstoccq1=strpos(" ".$postbody, "[color=green]", $fstoccq+1);
         $sndoccq=strpos(" ".$postbody, "[/color]", $fstoccq+1);
         $lastoccq=$fstoccq1;
      }
      if ($sndoccq and $fstoccq){
         $result=substr($postbody, 0, $fstoccq-1)."<font color='green'>".substr($postbody, $fstoccq+12,$sndoccq - $fstoccq - 13)."</font>".substr($postbody, $sndoccq+7);
      }
      else{
         break;
      }
   }
   // [color=blue] [/color]
   $sndoccq=1;
   while ($sndoccq>0){
      $postbody=$result;
      $lastoccq=0;
      $sndoccq=2;
      $fstoccq1=1;
      while($fstoccq1<$sndoccq and $fstoccq1){
         $fstoccq=strpos(" ".$postbody, "[color=blue]", $lastoccq);
         $fstoccq1=strpos(" ".$postbody, "[color=blue]", $fstoccq+1);
         $sndoccq=strpos(" ".$postbody, "[/color]", $fstoccq+1);
         $lastoccq=$fstoccq1;
      }
      if ($sndoccq and $fstoccq){
         $result=substr($postbody, 0, $fstoccq-1)."<font color='blue'>".substr($postbody, $fstoccq+11,$sndoccq - $fstoccq - 12)."</font>".substr($postbody, $sndoccq+7);
      }
      else{
         break;
      }
   }
   // [color=yellow] [/color]
   $sndoccq=1;
   while ($sndoccq>0){
      $postbody=$result;
      $lastoccq=0;
      $sndoccq=2;
      $fstoccq1=1;
      while($fstoccq1<$sndoccq and $fstoccq1){
         $fstoccq=strpos(" ".$postbody, "[color=yellow]", $lastoccq);
         $fstoccq1=strpos(" ".$postbody, "[color=yellow]", $fstoccq+1);
         $sndoccq=strpos(" ".$postbody, "[/color]", $fstoccq+1);
         $lastoccq=$fstoccq1;
      }
      if ($sndoccq and $fstoccq){
         $result=substr($postbody, 0, $fstoccq-1)."<font color='yellow'>".substr($postbody, $fstoccq+13,$sndoccq - $fstoccq - 14)."</font>".substr($postbody, $sndoccq+7);
      }
      else{
         break;
      }
   }
   // [color=purple] [/color]
   $sndoccq=1;
   while ($sndoccq>0){
      $postbody=$result;
      $lastoccq=0;
      $sndoccq=2;
      $fstoccq1=1;
      while($fstoccq1<$sndoccq and $fstoccq1){
         $fstoccq=strpos(" ".$postbody, "[color=purple]", $lastoccq);
         $fstoccq1=strpos(" ".$postbody, "[color=purple]", $fstoccq+1);
         $sndoccq=strpos(" ".$postbody, "[/color]", $fstoccq+1);
         $lastoccq=$fstoccq1;
      }
      if ($sndoccq and $fstoccq){
         $result=substr($postbody, 0, $fstoccq-1)."<font color='purple'>".substr($postbody, $fstoccq+13,$sndoccq - $fstoccq - 14)."</font>".substr($postbody, $sndoccq+7);
      }
      else{
         break;
      }
   }
   // [color=orange] [/color]
   $sndoccq=1;
   while ($sndoccq>0){
      $postbody=$result;
      $lastoccq=0;
      $sndoccq=2;
      $fstoccq1=1;
      while($fstoccq1<$sndoccq and $fstoccq1){
         $fstoccq=strpos(" ".$postbody, "[color=orange]", $lastoccq);
         $fstoccq1=strpos(" ".$postbody, "[color=orange]", $fstoccq+1);
         $sndoccq=strpos(" ".$postbody, "[/color]", $fstoccq+1);
         $lastoccq=$fstoccq1;
      }
      if ($sndoccq and $fstoccq){
         $result=substr($postbody, 0, $fstoccq-1)."<font color='orange'>".substr($postbody, $fstoccq+13,$sndoccq - $fstoccq - 14)."</font>".substr($postbody, $sndoccq+7);
      }
      else{
         break;
      }
   }
   // [color=teal] [/color]
   $sndoccq=1;
   while ($sndoccq>0){
      $postbody=$result;
      $lastoccq=0;
      $sndoccq=2;
      $fstoccq1=1;
      while($fstoccq1<$sndoccq and $fstoccq1){
         $fstoccq=strpos(" ".$postbody, "[color=teal]", $lastoccq);
         $fstoccq1=strpos(" ".$postbody, "[color=teal]", $fstoccq+1);
         $sndoccq=strpos(" ".$postbody, "[/color]", $fstoccq+1);
         $lastoccq=$fstoccq1;
      }
      if ($sndoccq and $fstoccq){
         $result=substr($postbody, 0, $fstoccq-1)."<font color='teal'>".substr($postbody, $fstoccq+11,$sndoccq - $fstoccq - 12)."</font>".substr($postbody, $sndoccq+7);
      }
      else{
         break;
      }
   }
   // [color=gray] [/color]
   $sndoccq=1;
   while ($sndoccq>0){
      $postbody=$result;
      $lastoccq=0;
      $sndoccq=2;
      $fstoccq1=1;
      while($fstoccq1<$sndoccq and $fstoccq1){
         $fstoccq=strpos(" ".$postbody, "[color=gray]", $lastoccq);
         $fstoccq1=strpos(" ".$postbody, "[color=gray]", $fstoccq+1);
         $sndoccq=strpos(" ".$postbody, "[/color]", $fstoccq+1);
         $lastoccq=$fstoccq1;
      }
      if ($sndoccq and $fstoccq){
         $result=substr($postbody, 0, $fstoccq-1)."<font color='gray'>".substr($postbody, $fstoccq+11,$sndoccq - $fstoccq - 12)."</font>".substr($postbody, $sndoccq+7);
      }
      else{
         break;
      }
   }
   // [color=brown] [/color]
   $sndoccq=1;
   while ($sndoccq>0){
      $postbody=$result;
      $lastoccq=0;
      $sndoccq=2;
      $fstoccq1=1;
      while($fstoccq1<$sndoccq and $fstoccq1){
         $fstoccq=strpos(" ".$postbody, "[color=brown]", $lastoccq);
         $fstoccq1=strpos(" ".$postbody, "[color=brown]", $fstoccq+1);
         $sndoccq=strpos(" ".$postbody, "[/color]", $fstoccq+1);
         $lastoccq=$fstoccq1;
      }
      if ($sndoccq and $fstoccq){
         $result=substr($postbody, 0, $fstoccq-1)."<font color='brown'>".substr($postbody, $fstoccq+12,$sndoccq - $fstoccq - 13)."</font>".substr($postbody, $sndoccq+7);
      }
      else{
         break;
      }
   }
   // [quote] [/quote]
   $sndocc=1;
   while ($sndocc>0){
      $postbody=$result;
      $lastocc=0;
      $sndocc=2;
      $fstocc1=1;
      while($fstocc1<$sndocc and $fstocc1){
         $fstocc=strpos(" ".$postbody, "[quote]", $lastocc);
         $fstocc1=strpos(" ".$postbody, "[quote]", $fstocc+1);
         $sndocc=strpos(" ".$postbody, "[/quote]", $fstocc+1);
         $lastocc=$fstocc1;
      }
      if ($sndocc and $fstocc){
         $result=substr($postbody, 0, $fstocc-1)."<table align='center' width='90%'><tr><td style='width:100%; border-style:ridge; border-width:2px; border-collapse: collapse; background-color:#f7f7f7; border-color:#f1f7fC'><span style='font-family: Verdana; font-size: 8pt; color: #000000'>".substr($postbody, $fstocc+6,$sndocc - $fstocc - 7)." </span></td></tr></table>".substr($postbody, $sndocc+7);
      }
      else{
         break;
      }
   }
   // Гиперссылки
   $postbody=str_replace("<br", " <br",$result)." ";
   $pos=0;
   while (strpos(" ".$postbody, "http://", $pos)) {
      $npos=strpos(" ".$postbody, "http://", $pos)-1;
      $epos=strpos($postbody, " ", $npos);
      $slpos=strpos(" ".$postbody, "/", $npos+8);
      if ($npos< 5 or substr($postbody, $npos-5, 5)!='[img]') $postbody=substr($postbody, 0, $npos)."<a href='".substr($postbody, $npos, $epos-$npos)."'><span class='nick'>".substr($postbody, $npos+7, $slpos-$npos-8)."</span></a>".substr($postbody, $epos);
      $pos=$epos;

   }
   $result=$postbody;
   // [img] [/img] (<img border='0' src=') ('>)
   $postbody=$result;
   $result="";
   $lastocc=0;
   $sndocc=1;
   while($sndocc)
   {
      $fstocc=strpos($postbody, "[img]", $lastocc);
      $sndocc=strpos($postbody, "[/img]", $fstocc);
      if(($fstocc > 0 && $sndocc > 0 && $lastocc > 0) || ($fstocc >= 0 && $sndocc > 0 && $lastocc== 0))
      {
         $result .= substr($postbody, $lastocc, $fstocc - $lastocc);
         $result .= "<img border='0' src='".substr($postbody, $fstocc + 5, $sndocc - $fstocc - 5)."'>";
         $lastocc = $sndocc + 6;
      }
      else
      {
         $result .=substr($postbody, $lastocc, strlen($postbody)-$lastocc);
         break;
      }
   }
   return $result;
}
function fd_smiles_mail($tmptxt) {
   $tmptxt=str_replace(":)","<img border='0' src='http://www.diletant.com.ua/forum/smiles/smile_.gif'>",$tmptxt);
   $tmptxt=str_replace(":(","<img border='0' src='http://www.diletant.com.ua/forum/smiles/sad.gif'>",$tmptxt);
   $tmptxt=str_replace(":D","<img border='0' src='http://www.diletant.com.ua/forum/smiles/biggrin.gif'>",$tmptxt);
   $tmptxt=str_replace(":[russian]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/russian.gif'>",$tmptxt);
   $tmptxt=str_replace(":[pioners]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/take_example.gif'>",$tmptxt);
   $tmptxt=str_replace(":[beer]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/drinks.gif'>",$tmptxt);
   $tmptxt=str_replace(":[no-no]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/acute.gif'>",$tmptxt);
   $tmptxt=str_replace(":[nea]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/nea.gif'>",$tmptxt);
   $tmptxt=str_replace(":[babruysk]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/to_babruysk.gif'>",$tmptxt);
   $tmptxt=str_replace(":[ohi]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/girl_sigh.gif'>",$tmptxt);
   $tmptxt=str_replace(":[klizma]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/girl_hospital.gif'>",$tmptxt);
   $tmptxt=str_replace(":[king]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/king2.gif'>",$tmptxt);
   $tmptxt=str_replace(":g)","<img border='0' src='http://www.diletant.com.ua/forum/smiles/girl_smile.gif'>",$tmptxt);
   $tmptxt=str_replace(":g(","<img border='0' src='http://www.diletant.com.ua/forum/smiles/girl_sad.gif'>",$tmptxt);
   $tmptxt=str_replace(":[blum]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/girl_blum.gif'>",$tmptxt);
   $tmptxt=str_replace(":[ghaha]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/girl_haha.gif'>",$tmptxt);
   $tmptxt=str_replace(":[gwacko]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/girl_wacko.gif'>",$tmptxt);
   $tmptxt=str_replace(":[gmad]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/girl_mad.gif'>",$tmptxt);
   $tmptxt=str_replace(":[ghide]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/girl_hide.gif'>",$tmptxt);
   $tmptxt=str_replace(":[glove]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/girl_in_love.gif'>",$tmptxt);
   $tmptxt=str_replace(":[gfish]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/girl_prepare_fish.gif'>",$tmptxt);
   $tmptxt=str_replace(":[gcrazy]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/girl_crazy.gif'>",$tmptxt);
   $tmptxt=str_replace(":[mblum]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/blum3.gif'>",$tmptxt);
   $tmptxt=str_replace(":[toclue]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/to_clue.gif'>",$tmptxt);
   $tmptxt=str_replace(":[snooks]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/snooks.gif'>",$tmptxt);
   $tmptxt=str_replace(":[scare]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/scare.gif'>",$tmptxt);
   $tmptxt=str_replace(":[scare2]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/scare2.gif'>",$tmptxt);
   $tmptxt=str_replace(":[gwerewolf]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/girl_werewolf.gif'>",$tmptxt);
   $tmptxt=str_replace(":[gdevil]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/girl_devil.gif'>",$tmptxt);
   $tmptxt=str_replace(":[friends]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/friends.gif'>",$tmptxt);
   $tmptxt=str_replace(":[taunt]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/taunt.gif'>",$tmptxt);
   $tmptxt=str_replace(":[offtopic]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/offtopic.gif'>",$tmptxt);
   $tmptxt=str_replace(":[queen]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/queen.gif'>",$tmptxt);
   $tmptxt=str_replace(":[butcher]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/butcher.gif'>",$tmptxt);
   $tmptxt=str_replace(":[rtfm]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/rtfm.gif'>",$tmptxt);
   $tmptxt=str_replace(":[shok]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/shok.gif'>",$tmptxt);
   $tmptxt=str_replace(":[kr2]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/KidRock_02.gif'>",$tmptxt);
   $tmptxt=str_replace(":[kr5]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/KidRock_05.gif'>",$tmptxt);
   $tmptxt=str_replace(":[kr7]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/KidRock_07.gif'>",$tmptxt);
   $tmptxt=str_replace(":[kr4]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/KidRock_04.gif'>",$tmptxt);
   $tmptxt=str_replace(":[whistle]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/whistle.gif'>",$tmptxt);
   $tmptxt=str_replace(":[whatever]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/whatever_01.gif'>",$tmptxt);
   $tmptxt=str_replace(":[vinsent]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/vinsent.gif'>",$tmptxt);
   $tmptxt=str_replace(":[victory]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/victory.gif'>",$tmptxt);
   $tmptxt=str_replace(":[triniti]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/triniti.gif'>",$tmptxt);
   $tmptxt=str_replace(":[tommy]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/tommy.gif'>",$tmptxt);
   $tmptxt=str_replace(":[to_keep_order]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/to_keep_order.gif'>",$tmptxt);
   $tmptxt=str_replace(":[tease]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/tease.gif'>",$tmptxt);
   $tmptxt=str_replace(":[suicide]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/suicide.gif'>",$tmptxt);
   $tmptxt=str_replace(":[spruce_up]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/spruce_up.gif'>",$tmptxt);
   $tmptxt=str_replace(":[slow]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/slow.gif'>",$tmptxt);
   $tmptxt=str_replace(":[skull]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/skull.gif'>",$tmptxt);
   $tmptxt=str_replace(":[rofl]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/rofl.gif'>",$tmptxt);
   $tmptxt=str_replace(":[read]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/read.gif'>",$tmptxt);
   $tmptxt=str_replace(":[rabbi]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/rabbi.gif'>",$tmptxt);
   $tmptxt=str_replace(":[punish]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/punish.gif'>",$tmptxt);
   $tmptxt=str_replace(":[pooh_door]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/pooh_door.gif'>",$tmptxt);
   $tmptxt=str_replace(":[pioneer]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/pioneer.gif'>",$tmptxt);
   $tmptxt=str_replace(":[ok]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/ok.gif'>",$tmptxt);
   $tmptxt=str_replace(":[new_russian]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/new_russian.gif'>",$tmptxt);
   $tmptxt=str_replace(":[moil]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/moil.gif'>",$tmptxt);
   $tmptxt=str_replace(":[lazy2]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/lazy2.gif'>",$tmptxt);
   $tmptxt=str_replace(":[jc]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/Just_Cuz_11.gif'>",$tmptxt);
   $tmptxt=str_replace(":[hi]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/hi.gif'>",$tmptxt);
   $tmptxt=str_replace(":[help]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/help.gif'>",$tmptxt);
   $tmptxt=str_replace(":[heat]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/heat.gif'>",$tmptxt);
   $tmptxt=str_replace(":[good]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/good.gif'>",$tmptxt);
   $tmptxt=str_replace(":[fuck]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/fuck.gif'>",$tmptxt);
   $tmptxt=str_replace(":[fool]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/fool.gif'>",$tmptxt);
   $tmptxt=str_replace(":[flirt]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/flirt.gif'>",$tmptxt);
   $tmptxt=str_replace(":[dntknw]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/dntknw.gif'>",$tmptxt);
   $tmptxt=str_replace(":[dance2]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/dance2.gif'>",$tmptxt);
   $tmptxt=str_replace(":[brunette]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/brunette.gif'>",$tmptxt);
   $tmptxt=str_replace(":[angel]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/angel.gif'>",$tmptxt);
   $tmptxt=str_replace(":[aleksey_01]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/aleksey_01.gif'>",$tmptxt);
   $tmptxt=str_replace(":[girl_cray2]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/girl_cray2.gif'>",$tmptxt);
   $tmptxt=str_replace(":[girl_cray3]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/girl_cray3.gif'>",$tmptxt);
   $tmptxt=str_replace(":[girl_impossible]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/girl_impossible.gif'>",$tmptxt);
   $tmptxt=str_replace(":[girl_wink]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/girl_wink.gif'>",$tmptxt);
   $tmptxt=str_replace(":[girl_dance]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/girl_dance.gif'>",$tmptxt);
   $tmptxt=str_replace(":[snoozer_18]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/snoozer_18.gif'>",$tmptxt);
   $tmptxt=str_replace(":[drag_10]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/drag_10.gif'>",$tmptxt);
   $tmptxt=str_replace(":[Koshechka_09]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/Koshechka_09.gif'>",$tmptxt);
   $tmptxt=str_replace(":[Koshechka_11]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/Koshechka_11.gif'>",$tmptxt);
   $tmptxt=str_replace(":[libelle_1]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/libelle_1.gif'>",$tmptxt);
   $tmptxt=str_replace(":[connie_6]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/connie_6.gif'>",$tmptxt);
   $tmptxt=str_replace(":[connie_1]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/connie_1.gif'>",$tmptxt);
   $tmptxt=str_replace(":[aftar]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/aftar.gif'>",$tmptxt);
   $tmptxt=str_replace(":[party]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/party.gif'>",$tmptxt);
   $tmptxt=str_replace(":[smoke]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/smoke.gif'>",$tmptxt);
   $tmptxt=str_replace(":[feminist]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/feminist.gif'>",$tmptxt);
   $tmptxt=str_replace(":[spam_light]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/spam_light.gif'>",$tmptxt);
   $tmptxt=str_replace(":[laie_32]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/laie_32.gif'>",$tmptxt);
   $tmptxt=str_replace(":[laie_44]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/laie_44.gif'>",$tmptxt);
   $tmptxt=str_replace(":[laie_48]","<img border='0' src='http://www.diletant.com.ua/forum/smiles/laie_48.gif'>",$tmptxt);
   $tmptxt=str_replace(";)","<img border='0' src='http://www.diletant.com.ua/forum/smiles/wink3.gif'>",$tmptxt);
   
   $_result=$tmptxt;
   return $_result;
}
</script>