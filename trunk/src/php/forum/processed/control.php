<?
   ob_start();
   // Личные настройки
   session_start();
// Функции   
   include("bbcode.php");
   include("smiles.php");
   include("cenz.php");
   include("head.php");
   include("body.php");
   include("form_add.php");
   include("button.php");
   include("query.php");
   // Принимает id, если не передано, то - 0
   if (isset($_GET['id'])){
      $gid=$_GET['id'];
   }
   else {
      $gid=0;
   }
   // Если нажали Выход
   include("exit.php");
   // Если тут не авторизовавшись
   if (!isset($_SESSION['idu'])) {
      header("Location: index.php");
      exit;
   }
   // Предотвращаем кеширование
   include("cache.php");
   // Соединяемся с MySql
   include("setup.php");
   // Собираем статистику
   $action=18;
   include("stat.php");
   //
   // Собрали
   // Какой язык интерфейса? по умолчанию - украинский :)
   include("lang.php");
   // доделать ссылки
   echo "<html>";
   echo "<head>";
   echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
   // Стили
   include('style.php');
   // Скрипты (смайлики)
   if (strpos($_SERVER['HTTP_USER_AGENT'], "MSIE 5.0")){
      include('smile_ie5.php');
   }
   else {
      include('smile_.php');
   }

   /*Скрипты (счечик текстария)*/
   include('js/checklength.php');
   /*Скрипты (автовставка тегов)*/
   include('jstags.php');
   /*Скрипты (submit поста)*/
   include('js/send_submit.php');
   echo '<link rel="icon" href="/favicon.ico" type="image/x-icon">';
   echo '<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">';
   echo "<title>";
   echo $_mess127;
   echo "</title>";
   echo "</head>";
   // Цвет фона страницы
   echo "<body bgcolor=#EFEFEF>";
   // Главная таблица
   echo "<table border='0' style='border-collapse: collapse' width='100%'>";
   // Таблица с лого и верхним баннером
   include("logo.php");
   // Главное "меню"
   echo "<tr>";
   echo "<td width='100%'>";
   echo "<table class=content>";
   include("menu.php");
   echo "</table></td></tr>";
   //
   echo "<tr><td>";
   // Таблица форума
   echo "<table class=content><tr><td>";
   // Таблица контента
   echo "<table class=content><tr>";
   // Ссылки на сервисы
   // Игнор-лист.
   echo "<td height='300' valign='TOP' width='150'>";
   echo "<table class=control><tr class=heads>";
   echo "<th class=internal>";
   echo '<div class=mnuprof>'.$_mess24.'</div>';
   echo "</th>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=1">'.$_mess24.'</a><br>';
   echo "</td>";
   echo "</tr></table>";
   // Личная переписка
   echo "<table class=control><tr class=heads>";
   echo "<th class=internal>";
   echo '<div class=mnuprof>'.$_mess23.'</div>';
   echo "</th>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=2">'.$_mess54.'</a><br>';
   echo "</td>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=3">'.$_mess57.'</a><br>';
   echo "</td>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=4">'.$_mess55.'</a><br>';
   echo "</td>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=5">'.$_mess56.'</a><br>';
   echo "</td>";
   echo "</tr></table>";
// Интерфейсы
   echo "<table class=control><tr class=heads>";
   echo "<th class=internal>";
   echo '<div class=mnuprof>'.$_mess71.'</div>';
   echo "</th>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=6">'.$_mess71.'</a><br>';
   echo "</td>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=7">'.$_mess72.'</a><br>';
   echo "</td>";
   echo "</tr></table>";
   // Подписка
   echo "<table class=control><tr class=heads>";
   echo "<th class=internal>";
   echo '<div class=mnuprof>'.$_mess86.'</div>';
   echo "</th>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=8">'.$_mess86.'</a><br>';
   echo "</td>";
   echo "</tr></table>";
   // Аватара
   echo "<table class=control><tr class=heads>";
   echo "<th class=internal>";
   echo '<div class=mnuprof>'.$_mess93.'</div>';
   echo "</th>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=9">'.$_mess93.'</a><br>';
   echo "</td>";
   echo "</tr></table>";
   // Местонахождение
   echo "<table class=control><tr class=heads>";
   echo "<th class=internal>";
   echo '<div class=mnuprof>'.$_mess104.'</div>';
   echo "</th>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=10">'.$_mess104.'</a><br>';
   echo "</td>";
   echo "</tr></table>";
   /*Подпись*/
   echo "<table class=control><tr class=heads>";
   echo "<th class=internal>";
   echo '<div class=mnuprof>'.$_mess138.'</div>';
   echo "</th>";
   echo "</tr><tr>";
   echo "<td class=internal>";
   echo '<a class=mnuprof href="control.php?id=11">'.$_mess138.'</a><br>';
   echo "</td>";
   echo "</tr></table>";
   // Сервисы
   echo "</td>";
   echo "<td valign='TOP'>";
   switch($gid) {
      case 0:
         // Зашли "по умолчанию"
         break;
         case 1:
         // Игнор-лист
         include("case_1.php");
         break;
      case 2:
         // Inbox
         include("case_2.php");
         break;
      case 3:
         // Отправлено, но не доставлено
         include("case_3.php");
         break;
      case 4:
         // Отправлено, и доставлено
         include("case_4.php");
         break;
      case 5:
         //  Черновики
         include("case_5.php");
         break;
      case 6:
         // Интерфейсы
         include("case_6.php");
         break;
      case 7:
         // Папки
         include("case_7.php");
         break;
      case 8:
         // Подписка
         include("case_8.php");
         break;
      case 9:
         // Аватара
         include("case_9.php");
         break;
      case 10:
         // Местонахождение
         include("case_10.php");
         break;
      case 11:
         // Подпись
         include("case_11.php");
         break;
   }
   echo "</td>";
   echo "</tr>";
   echo "</table>";
   echo "</td>";
   echo "</tr>";
   echo "</table>";
   echo "</td>";
   echo "</tr>";
   // Закрываем соединение MySql
   // Главное "меню"
   echo "<tr>";
   echo "<td width='100%'>";
   echo "<table border='0' style='border-collapse: collapse' width='100%'>";
   include("menu.php");
   echo "</table></td></tr>";
// Форма отправки письма личной переписки   
   include("control_mail.php");
   // Баннер внизу, счетчики и копирайт.
   include("end.php");
   echo "</body>";
   echo "</html>";
   mysql_close($conn);
   $strtmp=ob_get_contents();
   ob_end_clean();
   echo $strtmp;
?>