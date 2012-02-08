<?php
include_once 'classes/ua/com/diletant/email/email.php';

/**
 * Base class for DAO 
 */
class Dao{

	/**
	 * E-mail класс для отправки сообщений об ошибах
	 *
	 * @var Email
	 */
	private $mail;

	/**
	 * Максимальное время выполнения запроса
	 *
	 * @var maxQueryTime
	 */
	private $maxQueryTime = 10;
	
	/**
	 * Соединение
	 *
	 * @var IConnection
	 */
	protected $connection;

	
	/**
	 * Вызывается при возникновении ошибки БД
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
	 * Событие, генерируемое после выполнения запроса к БД
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