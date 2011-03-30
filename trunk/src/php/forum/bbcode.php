<?php
function fd_bbcode($str_body) {
   $postbody=stripslashes(nl2br(htmlspecialchars($str_body)));
   $postbody=str_replace("[span class='found']", "<span class='found'>", $postbody); 
   $postbody=str_replace("[/span]", "</span>", $postbody); 
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
   $lastocci=0;
   $sndocci=1;
   while($sndocci)
   {
      $fstocci=strpos($postbody, "[i]", $lastocci);
      $sndocci=strpos($postbody, "[/i]", $fstocci);
      if(($fstocci > 0 && $sndocci > 0 && $lastocci > 0) || ($fstocci >= 0 && $sndocci > 0 && $lastocci== 0))
      {
         $result .= substr($postbody, $lastocci, $fstocci - $lastocci);
         $result .= "<i>".substr($postbody, $fstocci + 3, $sndocci - $fstocci - 3)."</i>";
         $lastocci = $sndocci + 4;
      }
      else
      {
         $result .=substr($postbody, $lastocci, strlen($postbody)-$lastocci);
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
   $sndoccq=1;
   while ($sndoccq>0){
      $postbody=$result;
      $lastoccq=0;
      $sndoccq=2;
      $fstoccq1=1;
      while($fstoccq1<$sndoccq and $fstoccq1){
         $fstoccq=strpos(" ".$postbody, "[quote]", $lastoccq);
         $fstoccq1=strpos(" ".$postbody, "[quote]", $fstoccq+1);
         $sndoccq=strpos(" ".$postbody, "[/quote]", $fstoccq+1);
         $lastoccq=$fstoccq1;
      }
      if ($sndoccq and $fstoccq){
         $result=substr($postbody, 0, $fstoccq-1)."<table align=\"center\" width=\"90%\"><tr><td class=tdquote><span class='quote'>".substr($postbody, $fstoccq+6,$sndoccq - $fstoccq - 7)." </span></td></tr></table>".substr($postbody, $sndoccq+7);
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
?>