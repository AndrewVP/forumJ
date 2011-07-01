<script language="PHP">
      $str_head="<div style='font-family: Arial; font-size: 12pt; font-weight: bold;'>Test</div><br><br>";
// Вступление.
      $strMailHead="<html><head><title></title></head><body bgcolor=#EFEFEF>";
      $strMailHead=$strMailHead."Вы получили это сообщение, потому что подписаны на рассылку сообщений на форуме <a href='http://www.diletant.com.ua/forum'>Дилетант</a>. <br>";
      $strMailHead=$strMailHead."Отказаться от подписки на эту ветку вы можете, нажав на эту ссылку: <a href='http://www.diletant.com.ua/forum/delonesubsbymail.php?id='>Отказаться от подписки на ветку</a><br><br><br>";      
      $strMailFoot="</p></td></tr></table></body></html>";
// Собираем шапку
      $strPostHead="<table border='0' cellpadding='2' cellspacing='0' width='100%'>";
      $strPostHead=$strPostHead."<tr style='background-color:#D1D7DC'>";
      $strPostHead=$strPostHead."<td style='border:1px ridge; border-collapse: collapse; padding: 3px; border-color:#f1f7fC;'>";
      $strPostHead=$strPostHead."<span style='font-family: Verdana; font-size: 8pt;'>Автор:&nbsp;</span><span style='font-family: Arial; font-size: 12pt; font-weight: bold;'>Lbk</span>";
      $strPostHead=$strPostHead."<span style='font-family: Verdana; font-size: 8pt;'>&nbsp;".chr(149)."&nbsp;Дата:&nbsp;</span>";
      $strPostHead=$strPostHead."<span style='font-family: Verdana; font-size: 10pt;'>xdhgxdfhgsdhgdf&nbsp;</span>".chr(149);      
      $strPostHead=$strPostHead."<span style='font-family: Verdana; font-size: 8pt;'>&nbsp;Хост:&nbsp;</span><span style='font-family: Verdana; font-size: 10pt;'>xzfghdfzgfz</span>";
      $strPostHead=$strPostHead."</td></tr><tr><td><p style='font-family: Verdana; font-size: 10pt;'>";
      $strMailAll=$strMailHead.$strPostHead.$str_head.$strMailFoot;
// Пробуем пошинковать...
         $strMailCut=substr($strMailAll, 0, 910)."\r\n";
         $strMailAll=substr($strMailAll, 910);
      while (strlen($strMailAll)>900){
         $strMailCut=$strMailCut.substr($strMailAll, 0, 900)."\r\n";
         $strMailAll=substr($strMailAll, 900);
      }      
      $strMailAll=$strMailCut.$strMailAll;
// Вставляем код. 
         $to="a_2005@bigmir.net";
         $from="diletant@diletant.com.ua";
         $subject="Рассылка форума Дилетант";
         $headers='Content-type: text/html; charset="windows-1251"';
         $headers="From: ".$from."\nX-Mailer: Diletant\n".$headers;
   mail($to, $subject,$strMailAll , $headers);
</script>
