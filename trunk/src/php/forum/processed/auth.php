<?
// Авторизация
   session_start();
//Предотвращаем кеширование
   include("cache.php");
   if (isset($_GET['id'])) $gid=stripslashes($_GET['id']);
// Куда потом будем отправлять? 5,6 - logout!
//   if (($gid<>5) and ($gid<>6)) $_SESSION['where']=$gid;
   include('setup.php');
   include("query.php");
// Соединяемся с базой
// Собираем статистику
   $action=3;
   include("stat.php");
//
   mysql_close($conn);
// Какой язык интерфейса? по умолчанию - украинский :)
   include("lang.php");
//
   echo "<html>";
   echo "<head>";
   echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
// Стили
   include('style.php');
   echo "<title>";
   echo "Авторизация";
   echo "</title>";
   echo "</head>";
// Цвет фона страницы
   echo "<body bgcolor=#EFEFEF>";
// Главная таблица
   echo "<table border='0' style='border-collapse: collapse' width='100%'>";
// Таблица с лого и верхним баннером
   include("logo.php");
// Главные ссылки
   include("menu.php");
// Форма авторизации
   echo "<tr><td width='100%' align='center'><table width='100%'><tr><td>";
   echo "<form  action='submit.php' method='POST'>";
   echo "<table><tr><td><p>";
// Определяем, откуда мы сюда попали?
   switch ($gid){
// Нажали на ссылку Вход
      case 1:
         echo("Авторизуйтесь, пожалуйста");
         break;
      case 4:
         echo("<b>Авторизуйтесь, пожалуйста</b>");
         break;
// Попытались предложить тему незарегистрировавшись
      case 2:
         echo("Предлагать темы для обсуждения могут только зарегистрированные посетители!");
         break;
// Попытались ответить незарегистрировавшись
      case 3:
         echo("Добавлять свое мнение могут только зарегистрированные посетители!");
         break;
      case 5:
         echo("<b>Пардон, мы таких не знаем! :)</b>");
         break;
      case 6:
         echo("<b>Вы не угадали пароль! :)</b>");
         break;
      case 7:
         echo("<b>В связи с усложнением системы идентификации участников форума прошу вас пройти процедуру дооформления :) необходимо добавить еще один идентификатор, который вы введете только ОДИН раз в, дальнейшем это не позволит хацкерам подделать вашу куку.</b>");
         break;
      case 8:
         echo("В связи с усложнением системы идентификации участников форума прошу вас пройти процедуру дооформления :) необходимо добавить еще один идентификатор, который вы введете только ОДИН раз в, дальнейшем это не позволит хацкерам подделать вашу куку.<br> <b>Идентификаторы не совпадают</b>");
         break;
      case 9:
         echo("Проводить опросы могут только зарегистрированные пользователи");
         break;
      }
      echo "</p></td></tr>";
// С любезностями закончили
// Запрашиваем Ник
   echo "<tr><td><table><tr><td>";
   echo "Ник</td>";
   echo "<td><input type='text' name='T1' size='20'></td>";
   echo "</tr>";
// Пароль
   echo "<tr>";
   echo "<td>Пароль</td>";
   echo "<td><input type=password name='T2' size='20'>";
   echo "</td>";
   echo "</tr>";
// Идентификатор
   if ($gid==7 or $gid==8) {
      echo "<tr>";
      echo "<td>Идентификатор</td>";
      echo "<td><input type=password name='T3' size='20'>";
      echo "</td>";
      echo "</tr>";
      $_SESSION['xxxx']='1';
      }
// Кнопки
   echo "<tr>";
   echo "<td>";
   echo "<input type='submit' value='Отправить' name='B1'>";
   echo "<input type='reset' value='Отменить' name='B2'>";
   echo "</td></tr></table></td></tr></table></form></td></tr></table></td></tr>";
// Форма закончилась
// Главные ссылки
   include("menu.php");
   include("end.php");
   echo "</body>";
   echo "</html>";
?>
