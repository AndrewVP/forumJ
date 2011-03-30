<?php
include_once 'classes/ua/com/diletant/business/post.php';

/**
 * Класс для результатов запросов SELECT
 *
 */
class SelectQueryResult extends QueryResult{
	
	/**
	 * Количество строк
	 *
	 * @var unknown_type
	 */
	private $numRows; 
	
	/**
	 * Возвращает количество строк
	 *
	 * @return unknown
	 */
	public function getNumRows(){
		if (!isset($this->numRows)){
			$this->numRows = mysql_num_rows($this->getResult());
		}
		return $this->numRows;
	}
	
	/**
	 * Возвращает значение поля.
	 * По умолчанию - нулевой записи 
	 *
	 * @param unknown_type $field
	 * @param unknown_type $row
	 * @return unknown
	 */
	public function get($field, $row = 0){
		return mysql_result($this->getResult(), $row, $field);
	}
}
?>