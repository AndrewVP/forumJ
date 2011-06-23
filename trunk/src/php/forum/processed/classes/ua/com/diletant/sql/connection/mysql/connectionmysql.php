<?php
include_once 'classes/ua/com/diletant/sql/connection/iconnection.php';
include_once 'classes/ua/com/diletant/exception/db/mysql/mysqlqueryexception.php';
include_once 'classes/ua/com/diletant/exception/db/mysql/mysqlconnectexception.php';
include_once 'classes/ua/com/diletant/email/email.php';
include_once 'classes/ua/com/diletant/sql/result/queryresult.php';

/**
 * ����� ����������� � �� MySQL
 * ��������� ����������� � ����� ������� � ��
 */
class ConnectionMySQL implements IConnection{

	/**
	 * @var ���������� � ��
	 */
	private $connection;

	/**
	 * @var ����-��� ��� ������ ��
	 */
	private $timeout = 1;

	/**
	 * @var ���������� ������� ���������� ������� � �� �� ������� ������
	 */
	private $maxTry = 10;

	/**
	 * @var ��� ����������, � ������� ������������ �����
	 */
	private $component;
	
	/**
	 * �����������
	 *
	 * @param $hostName ��� �����
	 * @param $login �����
	 * @param $password ������
	 * @param $databaseName ��� ��
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
	 * ��������� ������ � ��
	 *
	 * @param ����� SQL-�������
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
	 * ������������� ����-��� ��� ������ ��
	 */
	public function setTimeout($timeout){
		$this->timeout = $timeout;
	}

	/**
	 * ����������  ����-��� ��� ������ ��
	 */
	public function getTimeout(){
		return $this->timeout;
	}

	/**
	 * ������������� ������������ ���������� ������� ���������� �������
	 *
	 * @param $maxTry ���������� ������� ���������� ������� �� ������
	 */
	public function setMaxTry($maxTry){
		$this->maxTry = $tmaxTry;
	}

	/**
	 * ���������� ������������ ���������� ������� ���������� �������
	 */
	public function getMaxTry(){
		return $this->maxTry;
	}

	/**
	 * �������, ������������ ����� ���������� � ��
	 *
	 * @param $count ���������� �������
	 * @param $time ����� ����������, �
	 * 	 */
	private function onConnect($count, $time){
		if ($time>10 || $count>0){
			$mail = new EMail();
			$mail->sendInvalidQueryMail($count, $time, "CONNECT", $this->component);
		}
	}

	/**
	 * �������, ������������ ����� ���������� �������
	 * 
	 * @param $count ���������� �������
	 * @param $time ����� ����������, �
	 * @param $query ������
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