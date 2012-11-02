<?
// Регистрация нового юзера
session_start();
   include("query.php");
//Предотвращаем кеширование
include("cache.php");
if (isset($_GET['id'])) $gid=stripslashes($_GET['id']);
// Куда потом будем отправлять? 5 - logout!
if ($gid<5 or $gid>8) $_SESSION['where']=$gid;
include('setup.php');
// Соединяемся с базой
// Собираем статистику
$action=11;
include("stat.php");
//
 mysql_close($conn);
// Какой язык интерфейса? по умолчанию - украинский :)
include("lang.php");
?>
<html>
   <head>
      <meta http-equiv='content-type' content='text/html; charset=windows-1251'>
      <?
      //Стили
      include('style.php');
      ?>
      <title>
         Мы приветсвуем новых собеседников! :)
      </title>
   </head>
   <?
   // Цвет фона страницы
   ?>
   <body bgcolor=#EFEFEF>
      <?// Главная таблица?>
      <table border='0' style='{border-collapse: collapse;}' width='100%'>
         <?
         // Таблица с лого и верхним баннером
         include("logo.php");
         // Главные ссылки
         include("menu.php");
         // Форма регистрации
         ?>
         <tr>
             <td width='100%'>
<!--               <form  action='' method='POST'> -->
                  <table>
                     <tr>
                        <td>
                           <p>
                           <font color='red'>Извините, но регистрация временно отключена</color>
                              <?
                              // Определяем, откуда мы сюда попали?
                              switch ($gid){
                                 // Нажали на ссылку регистрации
                                 case 1:
                                    //echo("Зарегистрируйтесь, пожалуйста");
                                    break;
                                 case 4:
                                    echo("Зарегистрируйтесь, пожалуйста");
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
                                    echo("<b>Извините, но ".$_SESSION['nick']." уже зарегистрирован...</b>");
                                    unset($_SESSION['nick']);
                                    break;
                                 case 6:
                                    echo("<b>Вы попали на могилу безНикого юзера, убитого при активном участии Нудного и Сель Ави :)</b>");
                                 // На всякий случай :)
                                    unset($_SESSION['nick']);
                                    break;
                                 case 7:
                                    echo("Не совпадают пароли");
                                    break;
                                 case 8:
                                    echo("Не совпадают E-mail");
                                    break;
                                 case 9:
                                    echo("Не совпадают идентификаторы");
                                    break;
                                 case 10:
                                    echo("Не используйте пустые пароли! :)");
                                    break;
                                 case 11:
                                    echo("Не используйте пустые идентификаторы! :)");
                                    break;
                                 case 12:
                                    echo("<b>Посетитель с таким почтовым ящиком уже зарегистрирован! :)</b>");
                                    break;
                              }
                              ?>
                           </p>
                        </td>
                     </tr>
                     <?
                     // С любезностями закончили
                     // Запрашиваем Ник
                     ?>
                     <tr>
                        <td>
                           <table>
                              <tr>
                                 <td>
                                    Ник*
                                 </td>
                                 <td>
                                    <input type='text' name='R1' size='20'>
                                 </td>
                              </tr>
                              <?
                              // Пароль
                              ?>
                              <tr>
                                 <td>
                                    Пароль*
                                 </td>
                                 <td>
                                    <input type=password name='R2' size='20'>
                                 </td>
                                 <td>
                                    Повторите пароль*
                                 </td>
                                 <td>
                                    <input type=password name='R22' size='20'>
                                 </td>
                              </tr>
                              <?
                              // Мыло
                              ?>
                              <tr>
                                 <td>
                                    E-Mail*
                                 </td>
                                 <td>
                                    <input type=text name='R33' size='20'>
                                 </td>
                                 <td>
                                    Повторите E-Mail*
                                 </td>
                                 <td>
                                    <input type=text name='R3' size='20'>
                                 </td>
                              </tr>
                              <?
                              // Идентификатор
                              ?>
                              <tr>
                                 <td>
                                    Идентификатор*
                                 </td>
                                 <td>
                                    <input type=password name='R4' size='20'>
                                 </td>
                              </tr>
                              <tr>
                                 <td colspan='4'>
                                    Идентификатор вводится один раз и больше нигде не отображается и не вводится, используется для борьбы с 'имитацией' чужих кук
                                 </td>
                              </tr>
                              <?
                              // Кнопки
                              ?>
                              <tr>
                                 <td>
                                    <input type='submit' value='Отправить' name='B1' disabled>
                                    <input type='reset' value='Отменить' name='B2'>
                                 </td>
                              </tr>
                           </table>
                        </td>
                     </tr>
                  </table>
<!--               </form> -->
            </td>
         </tr>
         <?
         // Форма закончилась
         // Главные ссылки
            include("menu.php");
         // Баннер внизу, счетчики и копирайт.
            include("end.php");
         ?>
   </body>
</html>