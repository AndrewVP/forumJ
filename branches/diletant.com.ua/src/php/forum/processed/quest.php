<?
// Обработка Новый опрос
session_start();
/*Функции поиска*/
// Классы
include_once 'classes/ua/com/diletant/util/time.php';
include("search_engine.php");
define("IN_HEAD_QUEST", 2, true);
define("IN_BODY_QUEST", 3, true);
define("TOP_POST_QUEST", 2, true);
define("NOT_TOP_POST_QUEST", 3, true);
include("query.php");
/* Функции обработки поста*/
//Предотвращаем кеширование
include("cache.php");
// Соединяемся
include('setup.php');
// Соединяемся
include('guard.php');
// Собираем статистику
$action=13;
include("stat.php");
//
include("lang.php");
$n0=stripslashes($_POST['IDU']);
$n1=fd_nick($n0, $conn);
// Вдруг сюда попали незарегистрировавшись?
if ($n1!="0"){
	// Все нормально
	// Может пустая??
	if (!(trim($_POST['A2'])=="" or trim($_POST['T'])=="")) {
		// Не пустая
		// Вопросы есть?
		if (!(trim($_POST['P1'])=="" or trim($_POST['P2'])=="" or trim($_POST['Q'])=="")){
			// Минимум два
	   	$questTime = new Time(time());
	      $rgtime = $questTime->toString("Y-m-d H:i:s");
			/*ip юзера*/
			$str_ip=$_SERVER['REMOTE_ADDR'];
			$str_dom=gethostbyaddr($str_ip);
			/*Заголовок*/
			$str_head=mysql_real_escape_string($_POST['T']);
			if (isset($_POST['comand']) && $_POST['comand']=="view"){
				include("quest_view.php");
			}else{
				include('quest_add.php');
			}
		}else{
			// Нет вопросов
			?>
            <html>
               <head>
                  <meta http-equiv='content-type' content='text/html; charset=windows-1251'>
                  <meta http-equiv='Refresh' content='5; url=index.php'>
                  <title>
                     Мы не во всем Дилетанты!
                  </title>
               </head>
               <body bgcolor=#EFEFEF>
                  <font size='5'><b>Надо предложить хотя бы два варианта!</b></font>
               </body>
            </html>
         <?   
            mysql_close($conn);
         }
      }else{
   // Пустая
      ?>
         <html>
            <head>
               <meta http-equiv='content-type' content='text/html; charset=windows-1251'>
               <meta http-equiv='Refresh' content='5; url=index.php'>
               <title>
                  Мы не во всем Дилетанты!
               </title>
            </head>
            <body bgcolor=#EFEFEF>
               <font size='5'><b>Шо, думаешь написано Дилетант, так тут все просто? Писать надо не только пробелами!</b></font>
            </body>
         </html>
      <?
         mysql_close($conn);
      }
   }else{
   //Вошли незарегистрировавшись
   ?>
      <html>
         <head>
            <meta http-equiv='content-type' content='text/html; charset=windows-1251'>
            <meta http-equiv='Refresh' content='5; url=auth.php?id=4.php'>
            <title>
               Мы не во всем Дилетанты!
            </title>
         </head>
         <body bgcolor=#EFEFEF>
            <font size='5'><b>Входить надо как все нормальные люди!</b></font>
         </body>
      </html>
   <?
      mysql_close($conn);
   }
?>