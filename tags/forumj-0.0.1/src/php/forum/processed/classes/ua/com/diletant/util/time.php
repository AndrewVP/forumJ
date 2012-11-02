<?php

/**
 * Класс для работы со временем
 *
 */

Class Time{
	
	/**
	 * Количество секунд в соответствующих периодах
	 *
	 * @var unknown_type
	 */
	public static $SECOND = 1;
	public static $MINUTE = 60;
	public static $HOUR = 3600;
	public static $DAY = 86400;
	
	/**
	 * Метка времени
	 */
	private $timestamp;
	
	/**
	 * Создается объект с заданным временем
	 * @param unknown_type $timestamp метка времени 
	 */
	public function __construct($timestamp){
		$this->timestamp = $timestamp;
	}
	
	/**
	 * Форматирует дату по маске
	 *
	 * @param unknown_type $mask
	 */
	public function toString($mask){
		return date($mask, $this->timestamp);
	}
	
	/**
	 * Удаляет из времени часы, минуты и секунды
	 *
	 */
	public function setClearTime(){
		 $this->timestamp = strtotime($this->toString("m/d/Y"));
	}
	
	/**
	 * Возвращает метку времени без часов, минут и секунд
	 *
	 */
	public function getClearTime(){
		 return strtotime($this->toString("m/d/Y"));
	}
	
	/**
	 * Устанавливает метку времени
	 *
	 * @param unknown_type $timestamp
	 */
	public function setTimestamp($timestamp){
		$this->timestamp = $timestamp;
	}
	
	/**
	 * Возвращает метку времени
	 *
	 * @return unknown
	 */
	public function getTimestamp(){
		return $this->timestamp;
	}
	
	/**
	 * Добавляет к метке времени соответствующее количество периодов
	 *
	 * @param unknown_type $value
	 * @param unknown_type $seconds
	 */
	public function add($value, $seconds){
		$this->timestamp += $value*$seconds;
	}

	/**
	 * Вычитает из метки времени соответствующее количество периодов
	 *
	 * @param unknown_type $value
	 * @param unknown_type $seconds
	 */
	public function sub($value, $seconds){
		$this->timestamp -= $value*$seconds;
	}
}
?>