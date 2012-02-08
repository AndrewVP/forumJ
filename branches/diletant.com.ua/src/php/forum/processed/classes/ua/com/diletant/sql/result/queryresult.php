<?php
/**
 * Base class for database results;
 */
class QueryResult{
	
	/**
	 * SQL-запрос
	 *
	 * @var string
	 */
	private $query;

	/**
	 * result непосредственно
	 */
	private $result;
	
	/**
	 * Время выполнения SQL-запроса
	 */
	private $queryTime;
	
	public function __construct($query){
		$this->setQuery($query);
	}
	
	public function __destruct(){
//		mysql_free_result($this->result);
	}
	
	/**
	 * Возвращает result непосредственно
	 */
	public function getResult(){
		return $this->result;
	}
	
	/**
	 * Устанавливает result непосредственно
	 */
	public function setResult($result){
		$this->result = $result;
	}

	/**
	 * Возвращает SQL-запрос
	 */
	public function getQuery(){
		return $this->query;
	}

	/**
	 * Устанавливает SQL-запрос
	 */
	public function setQuery($query){
		$this->query = $query;
	}

	/**
	 * Возвращает время выполнения SQL-запроса
	 */
	public function getQueryTime(){
		return $this->queryTime;
	}

	/**
	 * Устанавливает время выполнения SQL-запроса
	 */
	public function setQueryTime($queryTime){
		$this->queryTime = $queryTime;
	}
}
?>