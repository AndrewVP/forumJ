<?php
/**
 * ���������, ����������� ���������� � ��
 *
 */
interface IConnection{
	
	/**
	 * ������� ���������� ������� � ��
	 *
	 * @param $query ����� �������
	 */
	public function doQuery(QueryResult $result);
}
?>