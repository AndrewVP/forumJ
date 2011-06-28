<?php
// 1-index +
// 2-tema +
// 3-авторизация +
// 4-обработка авторизация +
// 5-Обработка опрос +
// 6-обработка новое сообщение +
// 7-Новая тема +
// 8-Обработка добавление нового юзера +
// 9-Обработка Новая тема +
// 10-Забыли пароль
// 11-Регистрация нового юзера +
// 12-Обработка Добавление нового варианта ответа +
// 13-Обработка Новый опрос +
// 14-Новый опрос +
// 15-Обработка удаление голоса +
// 16-поиск
// 17-www.diletant.com.ua
// 18 - личные настройки
// 19-Личная переписка (inbox)
// 20-обработка добавления игнора
// 21-обработка изменение игнора
// 22 - статистика
// 23 - индикатор index
// 24 - Обработка отправка письма
// 25 - индикатор tema
// 26 - добавление папки
// 27 - удаление папки
// 28 - лобавление интерфейса
// 29 - Переключение интерфейсов
// 30 Перенос темы между папками
// 31 - Установление интерфейса по умолчанию    
// 32 - Обработка "Вход"
// 33 - Обработка удаления подписки из контрола
// 34 - обработка добавление подписки
// 35 - обработка удаление подписки из ветки
// 36 - обработка удаление подписки через мыло  
// 37 - Новый индекс 
// 38 - Аватара
// 39 - Включение / отключение показа Аватар
// 40 - Обработка удаление одной темы с индекса 
// 41 - Обработка изменение подписи
// 42 - Активация с мейла
// 43 - Индексация форма для поиска через Ajax-запросы
$refer="";
if (isset($_SERVER['HTTP_REFERER'])) $refer=$_SERVER['HTTP_REFERER'];
   if (strpos(" ".$_SERVER['HTTP_USER_AGENT'], "StackRambler")==false and strpos(" ".$_SERVER['HTTP_USER_AGENT'], "Googlebot")==false and strpos(" ".$_SERVER['HTTP_USER_AGENT'], "Yandex")==false and strpos(" ".$_SERVER['HTTP_USER_AGENT'], "msnbot")==false and strpos(" ".$_SERVER['HTTP_USER_AGENT'], "Jyxobot")==false and strpos(" ".$_SERVER['HTTP_USER_AGENT'], "Slurp")==false){
      $_subnet=NULL;
      if (isset($_SERVER['HTTP_X_FORWARDED_FOR'])) $_subnet=$_SERVER['HTTP_X_FORWARDED_FOR'];
      if (isset($_SESSION['idu'])){
      $query='' .
      		'INSERT INTO fd_action ' .
      		'(' .
      		'	fd_ip, ' .
      		'	fd_subnet, ' .
      		'	fd_user, ' .
      		'	fd_time, ' .
      		'	fd_page, ' .
      		'	fd_refer, ' .
      		'	fd_reefer, ' .
      		'	fd_action) ' .
      		'VALUES ' .
      		'	("'.$_SERVER['REMOTE_ADDR'].'", ' .
      			'"'.$_subnet.'",' .
                $_SESSION['idu'].','. 
      			' now(), ' .
      			'"'.mysql_real_escape_string($_SERVER['PHP_SELF']." ".$_SERVER['QUERY_STRING']).'", ' .
      			'"'.mysql_real_escape_string($_SERVER['HTTP_USER_AGENT']).'", ' .
      			'"'.mysql_real_escape_string($refer).'", ' .
      			''.$action.');';
   }
   else
   {
      $query='' .
      		'INSERT INTO fd_action ' .
      		'(' .
      		'	fd_ip, ' .
      		'	fd_subnet, ' .
      		'	fd_user, ' .
      		'	fd_time, ' .
      		'	fd_page, ' .
      		'	fd_refer, ' .
      		'	fd_reefer, ' .
      		'	fd_action) ' .
      		'VALUES ' .
      		'	("'.$_SERVER['REMOTE_ADDR'].'", ' .
      			'"'.$_subnet.'",' .
      			' 0,' .
      			' now(), ' .
      			'"'.mysql_real_escape_string($_SERVER['PHP_SELF']." ".$_SERVER['QUERY_STRING']).'", ' .
      			'"'.mysql_real_escape_string($_SERVER['HTTP_USER_AGENT']).'", ' .
      			'"'.mysql_real_escape_string($refer).'", ' .
      			''.$action.');';
   }
   }
   else
   {
      $query='insert into robots (ip, user, time, page, refer, reefer, action) values ("'.$_SERVER['REMOTE_ADDR'].'", 0, now(), "'.mysql_real_escape_string($_SERVER['PHP_SELF']." ".$_SERVER['QUERY_STRING']).'", "'.mysql_real_escape_string($_SERVER['HTTP_USER_AGENT']).'", "'.mysql_real_escape_string($refer).'", '.$action.');';
   }
//   echo $query;
   $res1=fd_query($query, $conn,"");
?>
