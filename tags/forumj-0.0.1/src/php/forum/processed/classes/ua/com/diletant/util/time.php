<?php

/**
 * ����� ��� ������ �� ��������
 *
 */

Class Time{
	
	/**
	 * ���������� ������ � ��������������� ��������
	 *
	 * @var unknown_type
	 */
	public static $SECOND = 1;
	public static $MINUTE = 60;
	public static $HOUR = 3600;
	public static $DAY = 86400;
	
	/**
	 * ����� �������
	 */
	private $timestamp;
	
	/**
	 * ��������� ������ � �������� ��������
	 * @param unknown_type $timestamp ����� ������� 
	 */
	public function __construct($timestamp){
		$this->timestamp = $timestamp;
	}
	
	/**
	 * ����������� ���� �� �����
	 *
	 * @param unknown_type $mask
	 */
	public function toString($mask){
		return date($mask, $this->timestamp);
	}
	
	/**
	 * ������� �� ������� ����, ������ � �������
	 *
	 */
	public function setClearTime(){
		 $this->timestamp = strtotime($this->toString("m/d/Y"));
	}
	
	/**
	 * ���������� ����� ������� ��� �����, ����� � ������
	 *
	 */
	public function getClearTime(){
		 return strtotime($this->toString("m/d/Y"));
	}
	
	/**
	 * ������������� ����� �������
	 *
	 * @param unknown_type $timestamp
	 */
	public function setTimestamp($timestamp){
		$this->timestamp = $timestamp;
	}
	
	/**
	 * ���������� ����� �������
	 *
	 * @return unknown
	 */
	public function getTimestamp(){
		return $this->timestamp;
	}
	
	/**
	 * ��������� � ����� ������� ��������������� ���������� ��������
	 *
	 * @param unknown_type $value
	 * @param unknown_type $seconds
	 */
	public function add($value, $seconds){
		$this->timestamp += $value*$seconds;
	}

	/**
	 * �������� �� ����� ������� ��������������� ���������� ��������
	 *
	 * @param unknown_type $value
	 * @param unknown_type $seconds
	 */
	public function sub($value, $seconds){
		$this->timestamp -= $value*$seconds;
	}
}
?>