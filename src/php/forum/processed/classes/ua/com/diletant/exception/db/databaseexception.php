<?php
/**
 */
include_once 'classes/ua/com/diletant/exception/baseexception.php';
class DataBaseException extends BaseException{
	
	public function __construct($message = 'database error', $code = 0){
		parent::__construct($message, $code);
	}
}
?>
