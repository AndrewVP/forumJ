<?php
/**
 */
class MySQLConnectException extends DataBaseException{
	
	private $error;
	
	public function __construct($message = 'DataBaseConnectException'){
		parent::__construct($message);
		$this->errNo = mysql_errno();
		$this->error = mysql_error();
	}
	
	public function getErrNo(){
		return $this->errNo;
	}

	public function getError(){
		return $this->error;
	}
}
?>
