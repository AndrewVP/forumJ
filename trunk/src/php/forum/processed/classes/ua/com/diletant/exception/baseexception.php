<?php
/**
 * ������� ����������
 */
class BaseException extends Exception{
	
	/**
	 * �����������
	 *
	 * @param string $message
	 * @param int $code
	 */
	public function __construct($message = 'undefined error', $code=0){
		parent::__construct($message, $code);
	}
	
	/**
	 * ���������� ����������� ���������� �� ����������
	 * (�� ��� ���������� ����� �������� �� UI, �������� ����� SQL - ��������)
	 *
	 * @return unknown
	 */
	public function getExtendedErrorMessage(){
		return parent::__toString();
	}
}
?>
