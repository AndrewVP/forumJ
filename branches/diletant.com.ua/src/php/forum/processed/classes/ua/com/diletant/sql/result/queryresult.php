<?php
/**
 * Base class for database results;
 */
class QueryResult{
	
	/**
	 * SQL-������
	 *
	 * @var string
	 */
	private $query;

	/**
	 * result ���������������
	 */
	private $result;
	
	/**
	 * ����� ���������� SQL-�������
	 */
	private $queryTime;
	
	public function __construct($query){
		$this->setQuery($query);
	}
	
	public function __destruct(){
//		mysql_free_result($this->result);
	}
	
	/**
	 * ���������� result ���������������
	 */
	public function getResult(){
		return $this->result;
	}
	
	/**
	 * ������������� result ���������������
	 */
	public function setResult($result){
		$this->result = $result;
	}

	/**
	 * ���������� SQL-������
	 */
	public function getQuery(){
		return $this->query;
	}

	/**
	 * ������������� SQL-������
	 */
	public function setQuery($query){
		$this->query = $query;
	}

	/**
	 * ���������� ����� ���������� SQL-�������
	 */
	public function getQueryTime(){
		return $this->queryTime;
	}

	/**
	 * ������������� ����� ���������� SQL-�������
	 */
	public function setQueryTime($queryTime){
		$this->queryTime = $queryTime;
	}
}
?>