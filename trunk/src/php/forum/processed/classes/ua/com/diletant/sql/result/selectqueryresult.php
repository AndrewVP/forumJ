<?php
include_once 'classes/ua/com/diletant/business/post.php';

/**
 * ����� ��� ����������� �������� SELECT
 *
 */
class SelectQueryResult extends QueryResult{
	
	/**
	 * ���������� �����
	 *
	 * @var unknown_type
	 */
	private $numRows; 
	
	/**
	 * ���������� ���������� �����
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
	 * ���������� �������� ����.
	 * �� ��������� - ������� ������ 
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