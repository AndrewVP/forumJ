<?php
// 1-index +
// 2-tema +
// 3-����������� +
// 4-��������� ����������� +
// 5-��������� ����� +
// 6-��������� ����� ��������� +
// 7-����� ���� +
// 8-��������� ���������� ������ ����� +
// 9-��������� ����� ���� +
// 10-������ ������
// 11-����������� ������ ����� +
// 12-��������� ���������� ������ �������� ������ +
// 13-��������� ����� ����� +
// 14-����� ����� +
// 15-��������� �������� ������ +
// 16-�����
// 17-www.diletant.com.ua
// 18 - ������ ���������
// 19-������ ��������� (inbox)
// 20-��������� ���������� ������
// 21-��������� ��������� ������
// 22 - ����������
// 23 - ��������� index
// 24 - ��������� �������� ������
// 25 - ��������� tema
// 26 - ���������� �����
// 27 - �������� �����
// 28 - ���������� ����������
// 29 - ������������ �����������
// 30 ������� ���� ����� �������
// 31 - ������������ ���������� �� ���������    
// 32 - ��������� "����"
// 33 - ��������� �������� �������� �� ��������
// 34 - ��������� ���������� ��������
// 35 - ��������� �������� �������� �� �����
// 36 - ��������� �������� �������� ����� ����  
// 37 - ����� ������ 
// 38 - �������
// 39 - ��������� / ���������� ������ ������
// 40 - ��������� �������� ����� ���� � ������� 
// 41 - ��������� ��������� �������
// 42 - ��������� � �����
// 43 - ���������� ����� ��� ������ ����� Ajax-�������
$refer="";
if (isset($_SERVER['HTTP_REFERER'])) $refer=$_SERVER['HTTP_REFERER'];
   if (strpos(" ".$_SERVER['HTTP_USER_AGENT'], "StackRambler")==false and strpos(" ".$_SERVER['HTTP_USER_AGENT'], "Googlebot")==false and strpos(" ".$_SERVER['HTTP_USER_AGENT'], "Yandex")==false and strpos(" ".$_SERVER['HTTP_USER_AGENT'], "msnbot")==false and strpos(" ".$_SERVER['HTTP_USER_AGENT'], "Jyxobot")==false and strpos(" ".$_SERVER['HTTP_USER_AGENT'], "Slurp")==false){
      $_subnet=NULL;
      if (isset($_SERVER['HTTP_X_FORWARDED_FOR'])) $_subnet=$_SERVER['HTTP_X_FORWARDED_FOR'];
      if (isset($_SESSION['idu'])){
      $query='' .
      		'INSERT INTO fd_action ' .
      		'(' .
      		'	fd_ip, ' .
      		'	fd_subnet, ' .
      		'	fd_user, ' .
      		'	fd_time, ' .
      		'	fd_page, ' .
      		'	fd_refer, ' .
      		'	fd_reefer, ' .
      		'	fd_action) ' .
      		'VALUES ' .
      		'	("'.$_SERVER['REMOTE_ADDR'].'", ' .
      			'"'.$_subnet.'",' .
                $_SESSION['idu'].','. 
      			' now(), ' .
      			'"'.mysql_real_escape_string($_SERVER['PHP_SELF']." ".$_SERVER['QUERY_STRING']).'", ' .
      			'"'.mysql_real_escape_string($_SERVER['HTTP_USER_AGENT']).'", ' .
      			'"'.mysql_real_escape_string($refer).'", ' .
      			''.$action.');';
   }
   else
   {
      $query='' .
      		'INSERT INTO fd_action ' .
      		'(' .
      		'	fd_ip, ' .
      		'	fd_subnet, ' .
      		'	fd_user, ' .
      		'	fd_time, ' .
      		'	fd_page, ' .
      		'	fd_refer, ' .
      		'	fd_reefer, ' .
      		'	fd_action) ' .
      		'VALUES ' .
      		'	("'.$_SERVER['REMOTE_ADDR'].'", ' .
      			'"'.$_subnet.'",' .
      			' 0,' .
      			' now(), ' .
      			'"'.mysql_real_escape_string($_SERVER['PHP_SELF']." ".$_SERVER['QUERY_STRING']).'", ' .
      			'"'.mysql_real_escape_string($_SERVER['HTTP_USER_AGENT']).'", ' .
      			'"'.mysql_real_escape_string($refer).'", ' .
      			''.$action.');';
   }
   }
   else
   {
      $query='insert into robots (ip, user, time, page, refer, reefer, action) values ("'.$_SERVER['REMOTE_ADDR'].'", 0, now(), "'.mysql_real_escape_string($_SERVER['PHP_SELF']." ".$_SERVER['QUERY_STRING']).'", "'.mysql_real_escape_string($_SERVER['HTTP_USER_AGENT']).'", "'.mysql_real_escape_string($refer).'", '.$action.');';
   }
//   echo $query;
   $res1=fd_query($query, $conn,"");
?>
