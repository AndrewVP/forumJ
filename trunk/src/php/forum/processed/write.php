<?
setlocale(LC_ALL, "ru_RU.CP1251");
/* обработка новое сообщение.*/
session_start();
// Классы
include_once 'classes/ua/com/diletant/util/time.php';
   /* Функции обработки поста*/
include("bbcode_mail.php");
include("cenz.php");
include("button.php");
/*Функции поиска*/
include("search_engine.php");
   /*Предотвращаем кеширование*/
include("cache.php");
   /* Соединяемся с MySql*/
include('setup.php');
   include("query.php");
   /* Собираем статистику*/
$action=6;
include("stat.php");
   /*Проверяем юзера*/
include("guard.php");
include("lang.php");
   /* Вдруг сюда попали незарегистрировавшись?*/
$n0=stripslashes($_POST['IDU']);
$n1=fd_nick($n0, $conn);
$ban=fd_ban($n0, $conn);
if ($n1!="" && $ban!="1"){
      /* Все нормально*/
      /* Может пустое??*/
   if (!(trim($_POST['A2'])=="" or trim($_POST['NHEAD'])=="")) {
         /* Не пустое*/
         /* Добавляем Сообщение*/
      $str_body=mysql_real_escape_string($_POST['A2']);
         /* Текст заголовка*/
      $str_head=mysql_real_escape_string($_POST['NHEAD']);
         /* Добавляем заголовок*/
      $str_idd=stripslashes($_POST['IDT']);
         /* Автор кто?*/
      $str_id=$n0;
         /* Текущее время*/
      $postTime = new Time(time());
      $lptime = $postTime->toString("Y-m-d H:i:s");
         /*ip юзера*/
      $str_ip=$_SERVER['REMOTE_ADDR'];
      $str_dom=gethostbyaddr($str_ip);
         /*Просмотр или запись?*/
      if (isset($_POST['comand']) && $_POST['comand']=="view"){
         include("write_view.php");
      }else{
            /* Записываем или редактируем???*/
         If (!isset($_POST['IDB'])) {
               /*новый пост*/
            include("write_new.php");
         }
         else {
               /* Редактируем старый пост*/
            include("write_edit.php");
         }
         mysql_close($conn);
         /* Отправляем в форум*/
         /*Остаемся в ветке?*/
         if (isset($_POST['no_exit'])){
         	$exit="tema.php?id=".$str_idd."&end=1#end";
         }else{
         	$exit="index.php";
         }
         echo "<html><head>";
      echo "<meta http-equiv='Refresh' content='0; url=".$exit."'>";
         include('style.php');
         echo "<title></title></head><body></body></html>";
      }
   }else{
// Пустое
      echo "<html>";
      echo "<head>";
      echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
      echo "<meta http-equiv='Refresh' content='0; url=index.php'>";
   // Стили
      echo "<title>";
      echo "Мы не во всем Дилетанты!";
      echo "</title>";
      echo "</head>";
   // Цвет фона страницы
      echo "<body bgcolor=#EFEFEF>";
      echo "<font size='5'><b>Шо, думаешь написано Дилетант, так тут все просто? Писать надо не только пробелами!</b></font>";
      echo "</body>";
      echo "</html>";
   }
}else{
// Вошли незарегистрировавшись
   echo "<html>";
   echo "<head>";
   echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
   echo "<meta http-equiv='Refresh' content='10; url=auth.php?id=4'>";
// Стили
   echo "<title>";
   echo "Мы не во всем Дилетанты!";
   echo "</title>";
   echo "</head>";
// Цвет фона страницы
   echo "<body bgcolor=#EFEFEF>";
   echo "<font size='5'><b>Входить надо как все нормальные люди!</b></font>";
   echo "</body>";
   echo "</html>";
   mysql_close($conn);
}
?>