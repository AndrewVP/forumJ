<?php
/**
 * Базовое исключение
 */
class BaseException extends Exception{
	
	/**
	 * Конструктор
	 *
	 * @param string $message
	 * @param int $code
	 */
	public function __construct($message = 'undefined error', $code=0){
		parent::__construct($message, $code);
	}
	
	/**
	 * Возвращает расширенную информацию об исключении
	 * (не всю информацию можно выводить на UI, например текст SQL - запросов)
	 *
	 * @return unknown
	 */
	public function getExtendedErrorMessage(){
		return parent::__toString();
	}
}
?>
