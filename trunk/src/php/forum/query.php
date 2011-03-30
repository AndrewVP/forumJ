<?php
function fd_query($query, $connection, $time)
{
   $qryStrt=microtime();
   $qryStartSec=substr($qryStrt,11);
   $qryStartMSec=substr($qryStrt,0,10);
	if ($time=="") $time=1;
   $rslt=mysql_query($query, $connection);
	$qryCount=0;
	while (!$rslt){
		$qryCount+=1;
   	sleep($time);
	   $rslt=mysql_query($query, $connection);
	   if ($qryCount > 20) break;
   }
	$qryEnd=microtime();
   $qryTime=substr(substr($qryEnd,11)-$qryStartSec+substr($qryEnd,0,10)-$qryStartMSec, 0, 5);
   if ($qryTime>10 or  $qryCount>0){
	   $strMailAll="Count: ".$qryCount." c"."\r\n";
	   $strMailAll.="Time: ".$qryTime." c"."\r\n";
	   $strMailAll.="Query: ".$query."\r\n";
	   $strMailAll.="Action: ".$action;
	   $server="smtp.freehost.com.ua";
	   $from="diletant@diletant.com.ua";
	   $subject="Long query time on Diletant";
	   $headers='Content-type: text/html; charset="windows-1251"';
	   $headers="From: ".$from."\nSubject: ".$subject."\nX-Mailer: Diletant\n".$headers;
	   mail("andrew@sunbay.com", $subject, $strMailAll, $headers);
   	if ($qryCount>20) die();
   }
	return $rslt;
}

function fd_new_query($query, $connection, $time, $var1, $var2, $var3){
   $qryStrt=microtime();
   $qryStartSec=substr($qryStrt,11);
   $qryStartMSec=substr($qryStrt,0,10);
	if ($time=="") $time=1;
   $rslt=mysql_query($query, $connection);
   $qryCount=0;
	while (!$rslt){
		if (mysql_errno()==1062){
			 break;
		}
		$qryCount+=1;
      sleep($time);
	   $rslt=mysql_query($query, $connection);
   }
   $qryEnd=microtime();
   $qryTime=substr(substr($qryEnd,11)-$qryStartSec+substr($qryEnd,0,10)-$qryStartMSec, 0, 5);

   if ($qryTime>10 or  $qryCount>0){
	   $strMailAll="Count: ".$qryCount." c"."\r\n";
	   $strMailAll.="Time: ".$qryTime." c"."\r\n";
	   $strMailAll.="Query: ".$query."\r\n";
	   $strMailAll.="Action: ".$action;
	   $server="smtp.freehost.com.ua";
	   $from="diletant@diletant.com.ua";
	   $subject="Long query time on Diletant";
	   $headers='Content-type: text/html; charset="windows-1251"';
	   $headers="From: ".$from."\nSubject: ".$subject."\nX-Mailer: Diletant\n".$headers;
	   mail("andrew@sunbay.com", $subject, $strMailAll, $headers);
   	
   }
	return $rslt;
}
?>