<script language="PHP">
      $str_head="<div style='font-family: Arial; font-size: 12pt; font-weight: bold;'>Test</div><br><br>";
// ����������.
      $strMailHead="<html><head><title></title></head><body bgcolor=#EFEFEF>";
      $strMailHead=$strMailHead."�� �������� ��� ���������, ������ ��� ��������� �� �������� ��������� �� ������ <a href='http://www.diletant.com.ua/forum'>��������</a>. <br>";
      $strMailHead=$strMailHead."���������� �� �������� �� ��� ����� �� ������, ����� �� ��� ������: <a href='http://www.diletant.com.ua/forum/delonesubsbymail.php?id='>���������� �� �������� �� �����</a><br><br><br>";      
      $strMailFoot="</p></td></tr></table></body></html>";
// �������� �����
      $strPostHead="<table border='0' cellpadding='2' cellspacing='0' width='100%'>";
      $strPostHead=$strPostHead."<tr style='background-color:#D1D7DC'>";
      $strPostHead=$strPostHead."<td style='border:1px ridge; border-collapse: collapse; padding: 3px; border-color:#f1f7fC;'>";
      $strPostHead=$strPostHead."<span style='font-family: Verdana; font-size: 8pt;'>�����:&nbsp;</span><span style='font-family: Arial; font-size: 12pt; font-weight: bold;'>Lbk</span>";
      $strPostHead=$strPostHead."<span style='font-family: Verdana; font-size: 8pt;'>&nbsp;".chr(149)."&nbsp;����:&nbsp;</span>";
      $strPostHead=$strPostHead."<span style='font-family: Verdana; font-size: 10pt;'>xdhgxdfhgsdhgdf&nbsp;</span>".chr(149);      
      $strPostHead=$strPostHead."<span style='font-family: Verdana; font-size: 8pt;'>&nbsp;����:&nbsp;</span><span style='font-family: Verdana; font-size: 10pt;'>xzfghdfzgfz</span>";
      $strPostHead=$strPostHead."</td></tr><tr><td><p style='font-family: Verdana; font-size: 10pt;'>";
      $strMailAll=$strMailHead.$strPostHead.$str_head.$strMailFoot;
// ������� �����������...
         $strMailCut=substr($strMailAll, 0, 910)."\r\n";
         $strMailAll=substr($strMailAll, 910);
      while (strlen($strMailAll)>900){
         $strMailCut=$strMailCut.substr($strMailAll, 0, 900)."\r\n";
         $strMailAll=substr($strMailAll, 900);
      }      
      $strMailAll=$strMailCut.$strMailAll;
// ��������� ���. 
         $to="a_2005@bigmir.net";
         $from="diletant@diletant.com.ua";
         $subject="�������� ������ ��������";
         $headers='Content-type: text/html; charset="windows-1251"';
         $headers="From: ".$from."\nX-Mailer: Diletant\n".$headers;
   mail($to, $subject,$strMailAll , $headers);
</script>
