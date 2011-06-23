<?php
include_once 'classes/ua/com/diletant/sql/connection/iconnection.php';
include_once 'classes/ua/com/diletant/exception/db/mysql/mysqlqueryexception.php';
include_once 'classes/ua/com/diletant/exception/db/mysql/mysqlconnectexception.php';
include_once 'classes/ua/com/diletant/email/email.php';
include_once 'classes/ua/com/diletant/sql/result/queryresult.php';

/**
 * Класс подключения к БД MySQL
 * реализует подключение и обмен данными с БД
 */
class ConnectionMySQL implements IConnection{

	/**
	 * @var Соединение с БД
	 */
	private $connection;

	/**
	 * @var Тайм-аут при ошибке БД
	 */
	private $timeout = 1;

	/**
	 * @var Количество попыток выполнения запроса к БД до полного отказа
	 */
	private $maxTry = 10;

	/**
	 * @var Имя компоненты, в которой используется класс
	 */
	private $component;
	
	/**
	 * Конструктор
	 *
	 * @param $hostName Имя хоста
	 * @param $login Логин
	 * @param $password Пароль
	 * @param $databaseName Имя БД
	 */
	public function __construct($hostName, $login, $password, $databaseName, $timeout, $component){
		$this->component = $component;
		$qryStrt = microtime();
		$qryStartSec = substr($qryStrt,11);
		$qryStartMSec = substr($qryStrt,0,10);
		if ($timeout == ""){
			$timeout = $this->getTimeout();
		}
		$this->connection = @mysql_connect($hostName,$login,$password);
		$qryCount = 0;
		while (!$this->connection){
			$qryCount++;
			if ($qryCount >= $this->maxTry){
				break;
			}
			sleep($timeout);
			$this->connection = @mysql_connect($hostName,$login,$password);
		}
		$db = false;
		if ($this->connection){
			$db = mysql_select_db($databaseName, $this->connection);
			$qryCount = 0;
			while (!$db){
				$qryCount++;
				if ($qryCount >= $this->maxTry){
					break;
				}
				sleep($timeout);
				$db = mysql_select_db($databaseName, $this->connection);
			}
		}
		$qryEnd = microtime();
		$qryTime = substr(substr($qryEnd,11)-$qryStartSec+substr($qryEnd,0,10)-$qryStartMSec, 0, 5);
		$this->onConnect($qryCount, $qryTime);
		if (!$this->connection){
			throw new MySQLConnectException();
		}
	}

	/**
	 * Выполняет запрос к БД
	 *
	 * @param текст SQL-запроса
	 */
	public function doQuery(QueryResult $result){
		$qryStrt = microtime();
		$qryStartSec = substr($qryStrt,11);
		$qryStartMSec = substr($qryStrt,0,10);
		$resultSet = mysql_query($result->getQuery(), $this->connection);
		$result->setResult($resultSet);
		$qryEnd = microtime();
		$qryTime = substr(substr($qryEnd,11)-$qryStartSec+substr($qryEnd,0,10)-$qryStartMSec, 0, 5);
		$result->setQueryTime($qryTime);
//		$this->onQuery($qryCount, $qryTime, $query);
		if (!$result->getResult()){
			throw new MySQLQueryException($this->connection, $result->getQuery());
		}
		return $result;
	}

	/**
	 * Устанавливает тайм-аут при ошибке БД
	 */
	public function setTimeout($timeout){
		$this->timeout = $timeout;
	}

	/**
	 * Возвращает  тайм-аут при ошибке БД
	 */
	public function getTimeout(){
		return $this->timeout;
	}

	/**
	 * Устанавливает максимальное количество попыток выполнения запроса
	 *
	 * @param $maxTry Количество попыток выполнения запроса до отказа
	 */
	public function setMaxTry($maxTry){
		$this->maxTry = $tmaxTry;
	}

	/**
	 * Возвращает максимальное количество попыток выполнения запроса
	 */
	public function getMaxTry(){
		return $this->maxTry;
	}

	/**
	 * Событие, генерируемое после соединения с БД
	 *
	 * @param $count Количество попыток
	 * @param $time Время выполнения, с
	 * 	 */
	private function onConnect($count, $time){
		if ($time>10 || $count>0){
			$mail = new EMail();
			$mail->sendInvalidQueryMail($count, $time, "CONNECT", $this->component);
		}
	}

	/**
	 * Событие, генерируемое после выполнения запроса
	 * 
	 * @param $count Количество попыток
	 * @param $time Время выполнения, с
	 * @param $query Запрос
	 * 	 */
	private function onQuery($count, $time, $query){
		if ($time>10 || $count>0){
			$mail = new EMail();
			$mail->sendInvalidQueryMail($count, $time, $query, $this->component);
		}
	}
	
	private function disconnect(){
		mysql_close($this->connection);
	}
	
	public function __destruct(){
		$this->disconnect();
	}
}
?>