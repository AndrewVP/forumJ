<?php
include_once 'classes/ua/com/diletant/exception/baseexception.php';
/**
 * ������������ ��� ������� ����������
 * � ����� ��������� �� ��������������� �����
 */
Class InvalidKeyException extends BaseException {

	public function __construct($message = 'invalid key exception'){
		parent::__construct($message);
	}
}
?>