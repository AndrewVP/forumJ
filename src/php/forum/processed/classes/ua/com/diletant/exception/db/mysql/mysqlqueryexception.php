<?php
include_once 'classes/ua/com/diletant/exception/db/databaseexception.php';
/**
 * Исключение, генерируемое при ошибке выполнения запроса К MySQL
 */
class MySQLQueryException extends DataBaseException{
	
	/**
	 * Ошибочный запрос
	 *
	 * @var string
	 */
	private $query;
	
	public function __construct($resource, $query){
		parent::__construct(mysql_error($resource), mysql_errno($resource));
		$this->query = $query;
	}

	/**
	 * Возвращает запрос, который привел к ошибке
	 *
	 * @return unknown
	 */
	public function getErrorQuery(){
		return $this->query;
	}
	
	/**
	 * Возвращает расширенную информацию об исключении
	 * (не всю информацию можно выводить на UI, например текст SQL - запросов)
	 *
	 * @return unknown
	 */
		public function getExtendedErrorMessage(){
		$result = "Error № ".$this->getCode()." \nError Message: \"".$this->getMessage()."\""."\n Error Query: \"".$this->getErrorQuery()."\"";
		return $result; 
	}
}
?>
