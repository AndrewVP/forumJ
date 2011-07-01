<?
// Забыли пароль
   session_start();
//Предотвращаем кеширование
   include("cache.php");
   include('setup.php');
   include("query.php");
// Собираем статистику
   $action=10;
   include("stat.php");

   mysql_close($conn);
// Какой язык интерфейса? по умолчанию - украинский :)
   include("lang.php");
   mail($_SESSION['mail'], "Вы забыли пароль :)","Ваш пароль на форуме Дилетант:\n".$_SESSION['pass'] , "From: diletant@diletant.com.ua\nReply-To: pass@diletant.com.ua\nX-Mailer: PHP/" . phpversion());
   echo "<html>";
   echo "<head>";
   echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
   echo "<link rel='stylesheet' type='text/css' href='/style/main.css'>";
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
   echo "<tr>";
   echo "<td width='100%'>";
   echo "<table border='0' style='border-collapse: collapse' width='100%'>";
   echo "<tr bgcolor='#C0C0C0'>";
// Дилетант
   echo "<td><p align='center'><a href='/index.html'><span class='a'>Дилетант</span></a></td>";
// Вернуться в форум
   echo "<td><p align='center'><a href='index.php'><span class='a'>Список тем форума</span></a></td>";
   echo "</tr></table></td></tr>";
// Ссылки кончились
// Обрадуем :)
   echo "<tr><td><table width='100%'><tr><td>";
   echo "<span class='d'>Письмо с паролем вам отправлено!</span>";
   echo "</td></tr></table></td></tr>";
// Главные ссылки
   echo "<tr>";
   echo "<td width='100%'>";
   echo "<table border='0' style='border-collapse: collapse' width='100%'>";
   echo "<tr bgcolor='#C0C0C0'>";
// Дилетант
//   echo "<td><p align='center'><a href='/index.html'><span class='a'>Дилетант</span></a></td>";
// Вернуться в форум
   echo "<td><p align='center'><a href='index.php'><span class='a'>&nbsp;</span></a></td>";
   echo "</tr></table></td></tr>";
// Ссылки кончились
// Баннер внизу, счетчики и копирайт.
   include("end.php");
   echo "</body>";
   echo "</html>";

?>