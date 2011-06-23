<?php
include_once 'classes/ua/com/diletant/email/email.php';

/**
 * Base class for DAO 
 */
class Dao{

	/**
	 * E-mail ����� ��� �������� ��������� �� ������
	 *
	 * @var Email
	 */
	private $mail;

	/**
	 * ������������ ����� ���������� �������
	 *
	 * @var maxQueryTime
	 */
	private $maxQueryTime = 10;
	
	/**
	 * ����������
	 *
	 * @var IConnection
	 */
	protected $connection;

	
	/**
	 * ���������� ��� ������������� ������ ��
	 *
	 * @param DataBaseException $exception
	 */
	protected function onDatabaseError(DataBaseException $exception){
		if (!isset($this->mail)){
			$this->mail = new EMail();
		}
		$this->mail->sendInvalidQueryMail(999, 999, $exception->getExtendedErrorMessage(), "tema");
		die("Database Error");
	}
	
	/**
	 * �������, ������������ ����� ���������� ������� � ��
	 *
	 * @param QueryResult $result
	 */
	protected function onQuery(QueryResult $result){
		if ($result->getQueryTime() > $this->maxQueryTime){
			if (!isset($this->mail)){
				$this->mail = new EMail();
			}
			$this->mail->sendInvalidQueryMail(0, $result->getQueryTime(), $result->getQuery(), "tema");
		}
	}
}
?>