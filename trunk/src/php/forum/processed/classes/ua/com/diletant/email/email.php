<?php
class EMail{
	/**
	 * �������� ������ � ����������� ���������� �������
	 * TODO ������� ����� ����� �������������
	 *
	 * @param $count ���������� �������
	 * @param $time ����� ����������, �
	 * @param $query ������
	 */
	public function sendInvalidQueryMail($count, $time, $query, $component){
		$strMailAll="Count: " . $count . " \r\n";
		$strMailAll.="Time: " . $time . " c\r\n";
		$strMailAll.="Query: " . $query . "\r\n";
		$strMailAll.="Component: " . $component;
		$server="smtp.freehost.com.ua";
		$from="diletant@diletant.com.ua";
		$subject="Long query time on Diletant";
		$headers='Content-type: text/html; charset="windows-1251"';
		$headers="From: ".$from."\nSubject: ".$subject."\nX-Mailer: Diletant\n".$headers;
		//		mail("andrew@sunbay.com", $subject, $strMailAll, $headers);
		mail("an.home@mail.ru", $subject, $strMailAll, $headers);
	}

}
?>