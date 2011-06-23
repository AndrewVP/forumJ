<?php
include_once 'classes/ua/com/diletant/exception/baseexception.php';
/**
 * Генерируется при попытке обратиться
 * к члену коллекции по несуществующему ключу
 */
Class InvalidKeyException extends BaseException {

	public function __construct($message = 'invalid key exception'){
		parent::__construct($message);
	}
}
?>