<script language="PHP">
   function fd_href($href_head)
   {
      $postbody=str_replace("<br", " <br", $href_head)." ";
      $pos=0;
      while (strpos(" ".$postbody, "http://", $pos)) {
         $npos=strpos(" ".$postbody, "http://", $pos)-1;
         $epos=strpos($postbody, " ", $npos);
         $slpos=strpos(" ".$postbody, "/", $npos+8);
         if ($npos< 5 or substr($postbody, $npos-5, 5)!='[img]') $postbody=substr($postbody, 0, $npos)."<a href='".substr($postbody, $npos, $epos-$npos)."'><span class='nick'>".substr($postbody, $npos+7, $slpos-$npos-8)."</span></a>".substr($postbody, $epos);
         $pos=$epos;
   
      }
      $result=$postbody;
      return $result;
   }      
</script>   