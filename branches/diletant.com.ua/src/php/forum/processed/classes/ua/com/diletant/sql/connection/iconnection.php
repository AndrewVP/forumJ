<?php
/**
 * Интерфейс, описывающий соединение с БД
 *
 */
interface IConnection{
	
	/**
	 * Функция выполнения запроса к БД
	 *
	 * @param $query Текст запроса
	 */
	public function doQuery(QueryResult $result);
}
?>