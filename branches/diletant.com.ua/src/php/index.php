<?
   ob_start();
   include('forum/setup.php');
   include("forum/query.php");
   include("forum/smiles_index.php");
// Соединяемся с базой
// Собираем статистику
   $action=37;
   include("forum/stat.php");
   session_start();
//Предотвращаем кеширование
   include("forum/cache.php"); 
   echo "<html><head>";
   echo '<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">';
// Стили
   include('forum/style_site.php');
   echo "<title>Дилетант в политике</title>";
   echo "</head><body>";
   echo "<table width='100%'>";
   include("site/logo_white.php");
   
   echo "<tr>";
   echo "<td>";
   echo "<table class=control>";
?>   
         <tr>
            <td class=leftTop></td>
            <td class=top></td>
            <td class=rightTop></td>
         </tr>
         <tr class=heads>
            <td class=left></td>
<?         
   echo "<td class=bg align='LEFT'>&nbsp;<a class=mnuforumSm href='forum/index.php'>Полный список тем</a>&nbsp;</td>";
?>   
            <td class=right></td>
         </tr>
         <tr>
            <td class=leftBtm></td>
            <td class=btm></td>
            <td class=rightBtm></td>
         </tr>
<?         
   echo "</table>";        
   echo "</td>";        
   echo "</tr>"; 
   echo "<tr>";
   echo "<td>";
   echo "<table>";
   echo "<tr>";
   echo "<td valign=top>";
   echo "<table>";
   $countNewsTodaySql="
   SELECT
      COUNT(*) AS num_
   FROM   
      fd_site
      LEFT JOIN sd_rubriks ON fd_site.rubr=sd_rubriks.id
   WHERE
      sd_rubriks.colon=1      
      AND DATE_FORMAT(fd_site.time_site, '%y.%d.%m')=DATE_FORMAT(curdate(), '%y.%d.%m')
   ";
   $countNewsTodayRslt=mysql_query($countNewsTodaySql, $conn);
   while(!$countNewsTodayRslt){
      sleep(5);
      $countNewsTodayRslt=mysql_query($countNewsTodaySql, $conn);
   }
   $countRows=18;
   if (mysql_result($countNewsTodayRslt,0,'num_')> 18){
      $countRows=mysql_result($countNewsTodayRslt,0,'num_');
   }
   $leftColumnSql="
   SELECT
      fd_site.id,
      fd_site.title,
      fd_site.head,
      fd_site.body,
      users.nick,
      DATE_FORMAT(fd_site.time_site, '%d.%m %H:%i') as aa,
      sd_rubriks.r_name
   FROM
      fd_site
      LEFT JOIN users ON fd_site.auth=users.id
      LEFT JOIN sd_rubriks ON fd_site.rubr=sd_rubriks.id
   WHERE
      sd_rubriks.colon=1      
   ORDER BY 
      fd_site.time_site DESC
   LIMIT ".$countRows;
   $leftColumnRslt=mysql_query($leftColumnSql, $conn);
   while(!$leftColumnRslt){
      sleep(5);
      $leftColumnRslt=mysql_query($leftColumnSql, $conn);
   }
   $xLeft=0;
   while($row=mysql_fetch_row($leftColumnRslt)) {
      $xLeft=$xLeft+1;
      echo "<tr>";
      echo "<td style='{width:266}' valign=top class=internal1>";
      echo "<span class=posthead>".$row[6]."</span><br>";
      echo "<a href='forum/tema.php?id=".$row[2]."' class=hdforum>".fd_smiles(nl2br(stripslashes($row[1])))."</a><br>";
      echo "<span class=posthead  >".$row[5]."</span><br>";
      echo "<a href='forum/tema.php?id=".$row[2]."' class=posthead>".fd_smiles(nl2br(stripslashes($row[3])))."</a>";
      echo "<span class=posthead><i>Предоставил(а):</i>&nbsp;</span><span class=nick>".$row[4]."</span>";
      echo "</td>";
      echo "</tr>";
      if ($xLeft==2){
         echo "<tr>";
         echo "<td style='{width:266}' align=center>"; 
         echo "<table><tr><td align=center>";
         echo "
<!-- Ukrainian Banner Network 120х60 START -->
<center><sc"."ript>
//<!--
user = \"38782\";
page = \"1\";
pid = Math.round((Math.random() * (10000000 - 1)));
document.write(\"<iframe src='http://banner.kiev.ua/cgi-bin/bi.cgi?h\" + user + \"&amp;\"+ pid + \"&amp;\" + page + \"&amp;4' frameborder=0 vspace=0 hspace=0 \" + \" width=120 height=60 marginwidth=0 marginheight=0 scrolling=no>\");
document.write(\"<a href='http://banner.kiev.ua/cgi-bin/bg.cgi?\" +
user + \"&amp;\"+ pid + \"&amp;\" + page + \"&amp;4' target=_top>\");
document.write(\"<img border=0 src='http://banner.kiev.ua/\" +
\"cgi-bin/bi.cgi?i\" + user + \"&amp;\" + pid + \"&amp;\" + page +
\"&amp;4' width=120 height=60 alt='Ukrainian Banner Network'></a>\");
document.write(\"</iframe>\");
//-->
</s"."cript>
<!-- Ukrainian Banner Network 120х60 END -->
         ";
         echo "</td><td align=center>";
         echo "
<!-- Another Banner Network -->
<script language=\"javascript\" type=\"text/javascript\"><!--
bn_id='1676';
bn_url='http://c.abnad.net';
bn_rnd=Math.round((Math.random()*10000000));
bn_addurl='?t=120&w=120&h=60&id='+bn_id;
if(window.screen) bn_addurl+='&c='+screen.colorDepth+'&cw='+screen.width;
if(document.referrer) bn_addurl+='&ref='+escape(document.referrer);
bn_addurl+='&tz='+(new Date()).getTimezoneOffset()+'&r='+bn_rnd;
document.write('<iframe src=\"'+bn_url+'/iframe'+bn_addurl+'\" width=\"120\" height=\"60\" frameborder=\"0\" vspace=\"0\" hspace=\"0\" marginwidth=\"0\" marginheight=\"0\" scrolling=no><a href=\"'+bn_url+'/nsanchor'+bn_addurl+'\" target=_top><img src=\"'+bn_url+'/nsimg'+bn_addurl+'\" width=\"120\" height=\"60\" border=0 /></a></iframe>');
//--></script><noscript><a href=\"http://c.abnad.net/nsanchor?t=120&id=1676\" target=\"_top\"><img src=\"http://c.abnad.net/nsimg?t=120&w=120&h=60&id=1676\" width=\"120\" height=\"60\" border=\"0\" alt=\"\"/></a></noscript>
<!-- /Another Banner Network -->
         ";
         echo "</td></tr></table>";
         echo "</td>";
         echo "</tr>";
      }
      if ($xLeft==10){
         echo "<tr>";
         echo "<td style='{width:266}' align=center>"; 
         echo "<table><tr><td align=center>";
         echo "
         <!-- bigbn.com.ua 120x60-->
         <sc"."ript lan"."guage='Javascript'>
            d=document;
            rnd_num = Math.round((Math.random()*10000000));
            bbn_l='&'+escape((self!=top)?'f'+d.referrer:'h'+window.location.href);
         d.write('<iFrame src=http://ad2.bigmir.net/t.bbn?23258&2&f&'+rnd_num+bbn_l+
         ' width=120 height=60 frameborder=0 vspace=0 hspace=0 marginwidth=0 marginheight=0 scrolling=no>');
         d.write('<a target=_blank href=http://ad2.bigmir.net/c.bbn?23258&2&'+rnd_num+bbn_l+
         '><img src=http://ad2.bigmir.net/t.bbn?23258&2&i&'+rnd_num+bbn_l+' width=120 height=60 border=0 alt=\"BigBN Business\"></a></iFrame>');
         </scr"."ipt>
         <!-- bigbn.com.ua 120x60-->"
         ;
         echo "</td><td align=center>";
         echo "
<!-- Another Banner Network -->
<script language=\"javascript\" type=\"text/javascript\"><!--
bn_id='1676';
bn_url='http://c.abnad.net';
bn_rnd=Math.round((Math.random()*10000000));
bn_addurl='?t=120&w=120&h=60&id='+bn_id;
if(window.screen) bn_addurl+='&c='+screen.colorDepth+'&cw='+screen.width;
if(document.referrer) bn_addurl+='&ref='+escape(document.referrer);
bn_addurl+='&tz='+(new Date()).getTimezoneOffset()+'&r='+bn_rnd;
document.write('<iframe src=\"'+bn_url+'/iframe'+bn_addurl+'\" width=\"120\" height=\"60\" frameborder=\"0\" vspace=\"0\" hspace=\"0\" marginwidth=\"0\" marginheight=\"0\" scrolling=no><a href=\"'+bn_url+'/nsanchor'+bn_addurl+'\" target=_top><img src=\"'+bn_url+'/nsimg'+bn_addurl+'\" width=\"120\" height=\"60\" border=0 /></a></iframe>');
//--></script><noscript><a href=\"http://c.abnad.net/nsanchor?t=120&id=1676\" target=\"_top\"><img src=\"http://c.abnad.net/nsimg?t=120&w=120&h=60&id=1676\" width=\"120\" height=\"60\" border=\"0\" alt=\"\"/></a></noscript>
<!-- /Another Banner Network -->
         ";
         echo "</td></tr></table>";
         echo "</td>";
         echo "</tr>";
      }
   }
         echo "<tr>";
         echo "<td style='{width:266}' align=center>"; 
         echo "<table><tr><td align=center>";
         echo "
<!-- Ukrainian Banner Network 120х60 START -->
<center><scr"."ipt>
//<!--
user = \"38782\";
page = \"1\";
pid = Math.round((Math.random() * (10000000 - 1)));
document.write(\"<iframe src='http://banner.kiev.ua/cgi-bin/bi.cgi?h\" +
user + \"&amp;\"+ pid + \"&amp;\" + page + \"&amp;4' frameborder=0 vspace=0 hspace=0 \" +
\" width=120 height=60 marginwidth=0 marginheight=0 scrolling=no>\");
document.write(\"<a href='http://banner.kiev.ua/cgi-bin/bg.cgi?\" +
user + \"&amp;\"+ pid + \"&amp;\" + page + \"&amp;4' target=_top>\");
document.write(\"<img border=0 src='http://banner.kiev.ua/\" +
\"cgi-bin/bi.cgi?i\" + user + \"&amp;\" + pid + \"&amp;\" + page +
\"&amp;4' width=120 height=60 alt='Ukrainian Banner Network'></a>\");
document.write(\"</iframe>\");
//-->
</s"."cript>
<!-- Ukrainian Banner Network 120х60 END -->
         ";
         echo "</td><td align=center>";
         echo "
         <!-- bigbn.com.ua 120x60-->
         <sc"."ript lan"."guage='Javascript'>
            d=document;
            rnd_num = Math.round((Math.random()*10000000));
            bbn_l='&'+escape((self!=top)?'f'+d.referrer:'h'+window.location.href);
         d.write('<iFrame src=http://ad2.bigmir.net/t.bbn?23258&2&f&'+rnd_num+bbn_l+
         ' width=120 height=60 frameborder=0 vspace=0 hspace=0 marginwidth=0 marginheight=0 scrolling=no>');
         d.write('<a target=_blank href=http://ad2.bigmir.net/c.bbn?23258&2&'+rnd_num+bbn_l+
         '><img src=http://ad2.bigmir.net/t.bbn?23258&2&i&'+rnd_num+bbn_l+' width=120 height=60 border=0 alt=\"BigBN Business\"></a></iFrame>');
         </scr"."ipt>
         <!-- bigbn.com.ua 120x60-->
         ";
         echo "</td></tr></table>";
         echo "</td>";
         echo "</tr>";
   echo "</table>";
   echo "</td>";
   echo "<td valign=top>";
   echo "<table>";
   $count_Rows=11;
   $rightColumnSql="
   SELECT
      fd_site.id,
      fd_site.title,
      fd_site.head,
      fd_site.body,
      users.nick,
      DATE_FORMAT(fd_site.time_site, '%d.%m %H:%i') as aa,
      sd_rubriks.r_name
   FROM
      fd_site
      LEFT JOIN users ON fd_site.auth=users.id
      LEFT JOIN sd_rubriks ON fd_site.rubr=sd_rubriks.id
   WHERE
      sd_rubriks.colon=2      
   ORDER BY 
      fd_site.time_site DESC
   LIMIT ".$count_Rows;
   $rightColumnRslt=mysql_query($rightColumnSql, $conn);
   while(!$rightColumnRslt){
      sleep(5);
      $rightColumnRslt=mysql_query($rightColumnSql, $conn);
   }
   $xCenter=0;
   while($row=mysql_fetch_row($rightColumnRslt)) {
      $xCenter=$xCenter+1;
      echo "<tr>";
      echo "<td style='{width:468}' class=internal1>";
      echo "<span class=posthead>".$row[6]."</span><br>";
      echo "<a href='forum/tema.php?id=".$row[2]."' class=nik>".fd_smiles(nl2br(stripslashes($row[1])))."</a><br>";
      echo "<span class=posthead  >".$row[5]."</span><br><br>";
      echo "<a href='forum/tema.php?id=".$row[2]."' class=tbtext>".fd_smiles(nl2br(stripslashes($row[3])))."</a><br>";
      echo "<span class=posthead><i>Автор:</i>&nbsp;</span><span class=nick>".$row[4]."</span>";
      echo "</td>";
      echo "</tr>";
      if ($xCenter==4){
         echo "<tr>";
         echo "<td style='{width:468}' class=internal1>";
         // Баннер MegaBan
         srand((double)microtime()*10000000);
         $rnd = rand();
         
         echo "<center>";
         echo "<iframe src='http://b.megaban.com.ua:/?0&52&0&$rnd' ";
         echo "frameborder=0 vspace=0 hspace=0 width=468 height=60 marginwidth=0 marginheight=0 scrolling=no>";
         echo "<a href='http://b.megaban.com.ua/c?0&52&0&$rnd' target=_blank>";
         echo "<img src=http://b.megaban.com.ua/?0&52&0&$rnd&1'";
         echo "alt='MegaBan' border=0 width=468 height=60 ismap></a></iframe>";
         echo "</center>";
         echo "</td>";
         echo "</tr>";
      } 
      if ($xCenter==2){
         echo "<tr>";
         echo "<td style='{width:468}' class=internal1>";
         echo "
            <!-- Ukrainian Banner Network 468x60 START -->
            <center><script>
            //<!--
            user = \"38782\";
            page = \"1\";
            pid = Math.round((Math.random() * (10000000 - 1)));
            document.write(\"<iframe src='http://banner.kiev.ua/cgi-bin/bi.cgi?h\" +
            user + \"&amp;\"+ pid + \"&amp;\" + page + \"' frameborder=0 vspace=0 hspace=0 \" +
            \" width=468 height=60 marginwidth=0 marginheight=0 scrolling=no>\");
            document.write(\"<a href='http://banner.kiev.ua/cgi-bin/bg.cgi?\" +
            user + \"&amp;\"+ pid + \"&amp;\" + page + \"' target=_top>\");
            document.write(\"<img border=0 src='http://banner.kiev.ua/\" +
            \"cgi-bin/bi.cgi?i\" + user + \"&amp;\" + pid + \"&amp;\" + page +
            \"' width=468 height=60 alt='Украинская Баннерная Сеть'></a>\");
            document.write(\"</iframe>\");
            //-->
            </script>
            </center>
            <!-- Ukrainian Banner Network 468x60 END -->
         ";
         echo "</td>";
         echo "</tr>";
      }
   }
   echo "<tr>";
   echo "<td style='{width:468}' class=internal1>";
   echo "
   <script>
   var bau_login = \"diletant\";
   var bau_options = \"options=N\";
   var bau_random = Math.round(Math.random() * 100000);
   document.write('<iframe src=\"http://biz.advertarium.com.ua/cgi-bin/iframe/'+bau_login+'?'+bau_random +'&'+bau_options+'\" width=\"468\" height=\"60\" marginwidth=0 marginheight=0 scrolling=no frameborder=0><a href=\"http://biz.advertarium.com.ua/cgi-bin/href/'+bau_login+'?'+bau_random +'\" target=_blank><img src=\"http://biz.advertarium.com.ua/cgi-bin/banner/'+bau_login+'?'+bau_random +'&'+bau_options+'\" alt=\"Advertarium\" width=\"468\" height=\"60\" border=0 ismap></a></iframe>');
   </script>
   <noscript>
   <iframe src=\"http://biz.advertarium.com.ua/cgi-bin/iframe/diletant?options=N\" width=\"468\" height=\"60\" marginwidth=0 marginheight=0 scrolling=no frameborder=0><a href=\"http://biz.advertarium.com.ua/cgi-bin/href/diletant\" target=_blank><img src=\"http://biz.advertarium.com.ua/cgi-bin/banner/diletant?options=N\" alt=\"Advertarium\" width=\"468\" height=\"60\" border=0 ismap></a></iframe>
   </noscript>   
   ";
   echo "</td>";
   echo "</tr>";
   echo "</table>";
   echo "</td>";
   echo "<td valign=top>";
   echo "<table>";
   $countRows=ceil(($countRows*13)/18);
   $leftColumnSql="
   SELECT
      fd_site.id,
      fd_site.title,
      fd_site.head,
      fd_site.body,
      users.nick,
      DATE_FORMAT(titles.lposttime, '%d.%m %H:%i') as aa,
      sd_rubriks.r_name,
      titles.lpostnick,
      DATE_FORMAT(time_post, '%d.%m %H:%i') as aa
   FROM
      fd_site
      LEFT JOIN users ON fd_site.auth=users.id 
      LEFT JOIN sd_rubriks ON fd_site.rubr=sd_rubriks.id
      LEFT JOIN titles ON fd_site.head=titles.id 
   WHERE
      sd_rubriks.colon=3  
   ORDER BY  
      titles.lposttime DESC
   LIMIT ".$countRows;
   $leftColumnRslt=mysql_query($leftColumnSql, $conn);
   while(!$leftColumnRslt) {
      sleep(5);
      $leftColumnRslt=mysql_query($leftColumnSql, $conn);
   }    
   $xRight=0;
   while($row=mysql_fetch_row($leftColumnRslt)) {
      $xRight=$xRight+1;
      echo "<tr>";
      echo "<td style='{width:266}' valign=top class=internal1>";
      echo "<span class=posthead>".$row[6]."</span><br>";
      echo "<a href='forum/tema.php?id=".$row[2]."' class=hdforum>".fd_smiles(nl2br(stripslashes($row[1])))."</a><br>";
      echo "<span class=posthead  >".$row[8]."</span><br>";
      echo "<a href='forum/tema.php?id=".$row[2]."' class=posthead>".fd_smiles(nl2br(stripslashes($row[3])))."</a><br>";
      echo "<span class=posthead><i>Предоставил(а):</i>&nbsp;</span><span class=nick>".$row[4]."</span><br>";
      echo "<a href='forum/tema.php?id=".$row[2]."&end=1#end' class=posthead><i>Последнее мнение:</i>&nbsp;</a><span class=nick>".$row[7]."</span><br>";
      echo "<a href='forum/tema.php?id=".$row[2]."&end=1#end' class=posthead>".$row[5]."</a>";
      echo "</td>";
      echo "</tr>";
      if ($xRight==2){
         echo "<tr>";
         echo "<td style='{width:266}' align=center>";
         ?>
				<script>
				// <!--
					var hau_login = "diletant";
					var hau_options = "options=N";
					var hau_random = Math.round(Math.random() * 100000);
					document.write('<iframe src="http://half.advertarium.com.ua/cgi-bin/iframe/'+hau_login+'?'+hau_random +'&'+hau_options+'" width="160" height="60" marginwidth=0 marginheight=0 scrolling=no frameborder=0><a href="http://half.advertarium.com.ua/cgi-bin/href/'+hau_login+'?'+hau_random +'" target=_blank><img src="http://half.advertarium.com.ua/cgi-bin/banner/'+hau_login+'?'+hau_random +'&'+hau_options+'" alt="Advertarium" width="160" height="60" border=0 ismap></a></iframe>');
				// -->
				</script>
				<noscript>
					<iframe src="http://half.advertarium.com.ua/cgi-bin/iframe/diletant?options=N" width="160" height="60" marginwidth=0 marginheight=0 scrolling=no frameborder=0><a href="http://half.advertarium.com.ua/cgi-bin/href/diletant" target=_blank><img src="http://half.advertarium.com.ua/cgi-bin/banner/diletant?options=N" alt="Advertarium" width="160" height="60" border=0 ismap></a></iframe>
				</noscript>         
         <?
         echo "</td>";
         echo "</tr>";
      }
      if ($xRight==5){
         echo "<tr>";
         echo "<td style='{width:266}' align=center>";
         echo "
            <scr"."ipt lang"."uage='Javascript'>
               d=document;
               rnd_num = Math.round((Math.random()*10000000));
               bbn_l='&'+escape((self!=top)?'f'+d.referrer:'h'+window.location.href);
               d.write('<iFrame src=http://ad3.bigmir.net/t.bbn?23259&3&f&'+rnd_num+bbn_l+
                  ' width=160 height=60 frameborder=0 vspace=0 hspace=0 marginwidth=0 marginheight=0 scrolling=no>');
               d.write('<a target=_blank href=http://ad3.bigmir.net/c.bbn?23259&3&'+rnd_num+bbn_l+
                  '><img src=http://ad3.bigmir.net/t.bbn?23259&3&i&'+rnd_num+bbn_l+' width=160 height=60 border=0 alt=\"BigBN Business\"></a></iFrame>');
            </scr"."ipt>
         ";
         echo "</td>";
         echo "</tr>";
      }
   }
         echo "<tr>";
         echo "<td style='{width:266}' align=center>";
         ?>
				<script>
				// <!--
					var hau_login = "diletant";
					var hau_options = "options=N";
					var hau_random = Math.round(Math.random() * 100000);
					document.write('<iframe src="http://half.advertarium.com.ua/cgi-bin/iframe/'+hau_login+'?'+hau_random +'&'+hau_options+'" width="160" height="60" marginwidth=0 marginheight=0 scrolling=no frameborder=0><a href="http://half.advertarium.com.ua/cgi-bin/href/'+hau_login+'?'+hau_random +'" target=_blank><img src="http://half.advertarium.com.ua/cgi-bin/banner/'+hau_login+'?'+hau_random +'&'+hau_options+'" alt="Advertarium" width="160" height="60" border=0 ismap></a></iframe>');
				// -->
				</script>
				<noscript>
					<iframe src="http://half.advertarium.com.ua/cgi-bin/iframe/diletant?options=N" width="160" height="60" marginwidth=0 marginheight=0 scrolling=no frameborder=0><a href="http://half.advertarium.com.ua/cgi-bin/href/diletant" target=_blank><img src="http://half.advertarium.com.ua/cgi-bin/banner/diletant?options=N" alt="Advertarium" width="160" height="60" border=0 ismap></a></iframe>
				</noscript>         
         <?
                  echo "</td>";
         echo "</tr>";
   echo "</table>";
   echo "</td>";
   echo "</tr>";
   echo "</table>";
   echo "</td>";
   echo "</tr>";
   echo "<tr>";
   echo "<td>";
   echo "<table class=control>";
?>   
         <tr>
            <td class=leftTop></td>
            <td class=top></td>
            <td class=rightTop></td>
         </tr>
         <tr class=heads>
            <td class=left></td>
<?         
   echo "<td class=bg align='LEFT'>&nbsp;<a class=mnuforumSm href='forum/index.php'>Полный список тем</a>&nbsp;</td>";
?>   
            <td class=right></td>
         </tr>
         <tr>
            <td class=leftBtm></td>
            <td class=btm></td>
            <td class=rightBtm></td>
         </tr>
<?         
   echo "</table>";        
   echo "</td>";        
   echo "</tr>";        
   include("site/end.php");
//
   mysql_close($conn);    
   $strtmp=ob_get_contents();
   ob_end_clean();
   echo $strtmp;
?>