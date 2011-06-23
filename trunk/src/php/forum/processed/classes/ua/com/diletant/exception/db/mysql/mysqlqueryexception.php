<?php
include_once 'classes/ua/com/diletant/exception/db/databaseexception.php';
/**
 * ����������, ������������ ��� ������ ���������� ������� � MySQL
 */
class MySQLQueryException extends DataBaseException{
	
	/**
	 * ��������� ������
	 *
	 * @var string
	 */
	private $query;
	
	public function __construct($resource, $query){
		parent::__construct(mysql_error($resource), mysql_errno($resource));
		$this->query = $query;
	}

	/**
	 * ���������� ������, ������� ������ � ������
	 *
	 * @return unknown
	 */
	public function getErrorQuery(){
		return $this->query;
	}
	
	/**
	 * ���������� ����������� ���������� �� ����������
	 * (�� ��� ���������� ����� �������� �� UI, �������� ����� SQL - ��������)
	 *
	 * @return unknown
	 */
		public function getExtendedErrorMessage(){
		$result = "Error � ".$this->getCode()." \nError Message: \"".$this->getMessage()."\""."\n Error Query: \"".$this->getErrorQuery()."\"";
		return $result; 
	}
}
?>
