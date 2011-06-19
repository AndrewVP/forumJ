/*
 * Copyright Andrew V. Pogrebnyak
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ua.com.diletant.forum.tool;

import java.util.*;

import ua.com.diletant.forum.exception.InvalidKeyException;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class LocaleString{
   /**
    * Массив сообщений формы
    * @var unknown_type
    */
   private Map<String, String> arrStrings = new HashMap<String, String>();
   
   /**
    * Конструктор
    *
    * @param unknown_type $strLang
    * @param unknown_type $strFileName
    * @param unknown_type $strDefaultLang
    */
   public LocaleString(String strLang, String strFileName, String strDefaultLang){
      arrStrings.put("mess1", "Вход");
      arrStrings.put("mess2", "Регистрация");
      arrStrings.put("mess3", "Новый опрос");
      arrStrings.put("mess4", "Новая тема");
      arrStrings.put("mess5", "Посетитель");
      arrStrings.put("mess6", "Выход");
      arrStrings.put("mess7", "Прикреплена.");
      arrStrings.put("mess8", "Объявление.");
      arrStrings.put("mess9", "Опрос.");
      arrStrings.put("mess10", "Стр.");
      arrStrings.put("mess11", "Показать все смайлики");
      arrStrings.put("mess12", "Ваше мнение:");
      arrStrings.put("mess13", "Отправить");
      arrStrings.put("mess14", " пишет:");
      arrStrings.put("mess15", "Почта в пути");
      arrStrings.put("mess16", "Отправленная почта");
      arrStrings.put("mess17", "Полученная почта");
      arrStrings.put("mess18", "Папка пуста");
      arrStrings.put("mess19", "Кому");
      arrStrings.put("mess20", "Создано");
      arrStrings.put("mess21", "Смайлики");
      arrStrings.put("mess22", "Страница:");
      arrStrings.put("mess23", "Личная переписка");
      arrStrings.put("mess24", "Игнор-лист");
      arrStrings.put("mess25", "Вы никого не игнорируете");
      arrStrings.put("mess26", "Да");
      arrStrings.put("mess27", "Нет");
      arrStrings.put("mess28", "Кому");
      arrStrings.put("mess29", "Пишите нам:");
      arrStrings.put("mess30", "Поиск");
      arrStrings.put("mess31", "Мой профиль");
      arrStrings.put("mess32", "Января");
      arrStrings.put("mess33", "Февраля");
      arrStrings.put("mess34", "Марта");
      arrStrings.put("mess35", "Апреля");
      arrStrings.put("mess36", "Мая");
      arrStrings.put("mess37", "Июня");
      arrStrings.put("mess38", "Июля");
      arrStrings.put("mess39", "Августа");
      arrStrings.put("mess40", "Сентября");
      arrStrings.put("mess41", "Октября");
      arrStrings.put("mess42", "Ноября");
      arrStrings.put("mess43", "Декабря");
      arrStrings.put("mess44", "Ник");
      arrStrings.put("mess45", "Начало");
      arrStrings.put("mess46", "Завершение");
      arrStrings.put("mess47", "Изменить конечное время");
      arrStrings.put("mess48", "Извините, но время пока только Киевское... (GMT+2)");
      arrStrings.put("mess49", "Изменить");
      arrStrings.put("mess50", "Редактировалось ");
      arrStrings.put("mess51", " раз(а), последний: ");
      arrStrings.put("mess52", "Игнорировать темы");
      arrStrings.put("mess53", "Игнорировать<br>темы");
      arrStrings.put("mess54", "Полученные");
      arrStrings.put("mess55", "Отправленные");
      arrStrings.put("mess56", "Отложеные");
      arrStrings.put("mess57", "В пути");
      arrStrings.put("mess58", "От");
      arrStrings.put("mess59", "Тема");
      arrStrings.put("mess60", "Получено");
      arrStrings.put("mess61", "Отправлено");
      arrStrings.put("mess62", "Отложенная почта");
      arrStrings.put("mess63", "Просмотр");
      arrStrings.put("mess64", "Письмо");
      arrStrings.put("mess65", "Адресат не найден!");
      arrStrings.put("mess66", "Вам пришло");
      arrStrings.put("mess67", " нов. писем!");
      arrStrings.put("mess68", "Игнорировать мнение этого автора");
      arrStrings.put("mess69", "Выделенные");
      arrStrings.put("mess70", "удалить");
      arrStrings.put("mess71", "Интерфейсы");
      arrStrings.put("mess72", "Папки");
      arrStrings.put("mess73", "Перечень ваших папок");
      arrStrings.put("mess74", "Имя папки");
      arrStrings.put("mess75", "Добавить");
      arrStrings.put("mess76", "Перечень ваших интерфейсов");
      arrStrings.put("mess77", "Имя интерфейса");
      arrStrings.put("mess78", "Перечень папок в интерфейсе ");
      arrStrings.put("mess79", "добавить в интерфейс ");
      arrStrings.put("mess80", "Переключить интерфейс на: ");
      arrStrings.put("mess81", "Текущий интерфейс: ");
      arrStrings.put("mess82", "Папка");
      arrStrings.put("mess83", "Выделенные темы перенести в: ");
      arrStrings.put("mess84", "Интерфейс по умолчанию: ");
      arrStrings.put("mess85", "Установить");
      arrStrings.put("mess86", "Подписка");
      arrStrings.put("mess87", "Список подписаных тем");
      arrStrings.put("mess88", "Отменить");
      arrStrings.put("mess89", "Подписаться&nbsp;на&nbsp;новые&nbsp;сообщения&nbsp;в&nbsp;этой&nbsp;теме");
      arrStrings.put("mess90", "Отменить&nbsp;подписку&nbsp;на&nbsp;эту&nbsp;тему");
      arrStrings.put("mess91", "Страница сформирована за<br> ъъ_ъ сек");
      arrStrings.put("mess92", "Внимание! Ваша аватара будет активирована после просмотра модератором. Это сделано в связи с возможностью взлома.");
      arrStrings.put("mess93", "Аватара");
      arrStrings.put("mess94", "Показывать аватару");
      arrStrings.put("mess95", "Аватара активирована модератором");
      arrStrings.put("mess96", "Аватара не активирована модератором");
      arrStrings.put("mess97", "URL Аватары");
      arrStrings.put("mess98", "Показывать аватары");
      arrStrings.put("mess99", "Искать");
      arrStrings.put("mess100", "Полный поиск");
      arrStrings.put("mess101", "Среди авторов");
      arrStrings.put("mess102", "Количество букв должно быть не менее двух!");
      arrStrings.put("mess103", "Для того, чтобы прочитать этот пост, вам необходимо зарегистрироваться.");
      arrStrings.put("mess104", "Местонахождение");
      arrStrings.put("mess105", "Местонахождение и часовой пояс");
      arrStrings.put("mess106", "Часовой пояс:&nbsp;");
      arrStrings.put("mess107", "Цитировать");
      arrStrings.put("mess108", "Ответить");
      arrStrings.put("mess109", "Редактировать");
      arrStrings.put("mess110[1]", "(GMT - 12:00) Эневеток, Кваджалейн");
      arrStrings.put("mess110[2]", "(GMT - 11:00) о.Мидуэй, Самоа");
      arrStrings.put("mess110[3]", "(GMT - 10:00) Гавайи");
      arrStrings.put("mess110[4]", "(GMT - 9:00) Аляска");
      arrStrings.put("mess110[5]", "(GMT - 8:00) Тихоокеанское время (США и Канада), Тихуана");
      arrStrings.put("mess110[6]", "(GMT - 7:00) Горное время (США и Канада), Аризона");
      arrStrings.put("mess110[7]", "(GMT - 6:00) Центральное время (США и Канада), Мехико");
      arrStrings.put("mess110[8]", "(GMT - 5:00) Восточное время (США и Канада), Богота, Лима, Кито");
      arrStrings.put("mess110[9]", "(GMT - 4:00) Атлантическое время (Канада), Каракас, Ла Пас, Сантьяго");
      arrStrings.put("mess110[10]", "(GMT - 3:30) Ньюфаундленд");
      arrStrings.put("mess110[11]", "(GMT - 3:00) Бразилия, Буэнос-Айрес, Джорджтаун, Гренландия");
      arrStrings.put("mess110[12]", "(GMT - 2:00) Среднеатлантическое время");
      arrStrings.put("mess110[13]", "(GMT - 1:00) Азорские о-ва, о-ва Зеленого мыса");
      arrStrings.put("mess110[14]", "(GMT) Лондон, Касабланка, Дублин, Эдинбург, Лиссабон, Монровия");
      arrStrings.put("mess110[15]", "(GMT + 1:00) Амстердам, Берлин, Брюссель, Мадрид, Париж, Рим");
      arrStrings.put("mess110[16]", "(GMT + 2:00) Украина, Польша, Калининград, Каир, Хельсинки, Южная Африка");
      arrStrings.put("mess110[17]", "(GMT + 3:00) Москва, Багдад, Эр-Рияд, Найроби");
      arrStrings.put("mess110[18]", "(GMT + 3:30) Тегеран");
      arrStrings.put("mess110[19]", "(GMT + 4:00) Тбилиси, Баку, Абу-Даби, Мускат");
      arrStrings.put("mess110[20]", "(GMT + 4:30) Кабул");
      arrStrings.put("mess110[21]", "(GMT + 5:00) Екатеринбург, Ташкент, Исламабад, Карачи");
      arrStrings.put("mess110[22]", "(GMT + 5:30) Бомбей, Калькутта, Мадрас, Нью-Дели");
      arrStrings.put("mess110[23]", "(GMT + 6:00) Новосибирск, Омск, Алма-Ата, Коломбо, Дхака");
      arrStrings.put("mess110[24]", "(GMT + 7:00) Красноярск, Бангкок, Ханой, Джакарта");
      arrStrings.put("mess110[25]", "(GMT + 8:00) Пекин, Гонконг, Перт, Сингапур, Тайпей");
      arrStrings.put("mess110[26]", "(GMT + 9:00) Якутск, Осака, Саппоро, Сеул, Токио");
      arrStrings.put("mess110[27]", "(GMT + 9:30) Аделаида, Дарвин");
      arrStrings.put("mess110[28]", "(GMT + 10:00) Владивосток, Канберра, Мельбурн, Гуам, Сидней");
      arrStrings.put("mess110[29]", "(GMT + 11:00) Магадан, Новая Каледония, Соломоновы о-ва");
      arrStrings.put("mess110[30]", "(GMT + 12:00) Камчатка, Окленд, Фиджи, Веллингтон, Маршалловы о-ва");
      arrStrings.put("mess111", "Страна:&nbsp;");
      arrStrings.put("mess112", "Город/нас.пункт.:&nbsp;");
      arrStrings.put("mess113", "Скрыть");
      arrStrings.put("mess114", "Нет данных");
      arrStrings.put("mess115", "Последние 7 дней");
      arrStrings.put("mess116", "Последние 30 дней");
      arrStrings.put("mess117", "Последние 90 дней");
      arrStrings.put("mess118", "По всей базе");
      arrStrings.put("mess119", "В темах");
      arrStrings.put("mess120", "В текстах сообщений");
      arrStrings.put("mess121", "Период поиска");
      arrStrings.put("mess122", "Где искать");
      arrStrings.put("mess123", "Остаться в этом обсуждении");
      arrStrings.put("mess124", "Вопрос");
      arrStrings.put("mess125", "Голосующие могут добавлять свои варианты ответа");
      arrStrings.put("mess126", "Добавить еще вариант");
      arrStrings.put("mess127", "Личные настройки");
      arrStrings.put("mess128", "Поле \"тема\" не заполнено!");
      arrStrings.put("mess129", "Пустые посты отправлять нельзя!");
      arrStrings.put("mess130", "Надо задать вопрос!");
      arrStrings.put("mess131", "Должно быть хотя бы два варианта ответа!");
      arrStrings.put("mess132", "Необходимо указать получателя!");
      arrStrings.put("mess133", "ВНИМАНИЕ!!! Неавторизованым посетителям недоступны все преимущества интерфейса форума!<br>Зарегистрируйтесь!");
      arrStrings.put("mess134", "А ТЫ удалил флуд из своего форума?");
      arrStrings.put("mess135", "Список тем");
      arrStrings.put("mess136", "Всего страниц:");
      arrStrings.put("mess137", "Перейти к странице:");
      arrStrings.put("mess138", "Подпись:");
      arrStrings.put("mess139", "Цитировать");
      arrStrings.put("mess140", "Ответить");
      arrStrings.put("mess141", "Редактировать");
      arrStrings.put("mess142", "Скрыть/показать сообщение");
      arrStrings.put("mess143", "Вариант предложен:&nbsp;");
      arrStrings.put("mess144", "Анонимно");
      arrStrings.put("mess145", "Проголосовать");
      arrStrings.put("mess146", "Скрыть автора варианта");
      arrStrings.put("mess147", "Автор варианта");
      arrStrings.put("mess148", "Вариант");
      arrStrings.put("mess149", "Голосов");
      arrStrings.put("mess150", "Рейтинг");
      arrStrings.put("mess151", "Доля");
      arrStrings.put("mess152", "Всего голосов");
      arrStrings.put("mess153", "Ваш вариант ответа");
      arrStrings.put("mess154", "Для того чтобы перейти к сообщению, нажмите на его заголовок");
      arrStrings.put("mess155[1]", "На Ваш почтовый адрес отправлено письмо с дальнейшими инструкциями");
      arrStrings.put("mess155[2]", "Ваш ник будет активирован после просмотра данных модератором");
      arrStrings.put("mess156", "Сообщение");
      arrStrings.put("mess157", "Внимание! В данный момент идет автоматическая индексация форума для поисковой системы. Поэтому не все данные готовы для поиска.");
      arrStrings.put("mess158", "На текущее время проиндексированы данные, начиная с момента: ");
      arrStrings.put("mess159", "Пока поиск умеет искать только одно слово, длиннее 2 букв. Над расширением возможностей поиска я работаю, планируется добавление поиска всех словоформ и фраз.");
      arrStrings.put("mess160", "Внимание! Механизм поиска тестируется и пока работает некорректно!");
      arrStrings.put("mess161", "Отменить свой голос");
      arrStrings.put("mess162", "Разместить");
      arrStrings.put("mess163", "День рождения");
      arrStrings.put("mess164", "Новых тем");
      arrStrings.put("mess165", "Новых сообщений");
      arrStrings.put("MSG_MAIN_TITLE", "Форум дилетантов");
      arrStrings.put("MSG_ANSW", "Отв.");
      arrStrings.put("MSG_VIEWS", "Просм.");
      arrStrings.put("MSG_AUTH", "Предложена");
      arrStrings.put("MSG_LAST", "Последнее");
      arrStrings.put("MSG_READERS", "Форум сейчас читают:");
      arrStrings.put("MSG_GUESTS", "Гостей");
   }
   
   /**
    * Возвращает локализованную строку
    *
    * @param unknown_type $strKey
    * @return unknown
    * @throws InvalidKeyException 
    */
   public String getString(String strKey) throws InvalidKeyException{
      String result = null;
      if (strKey == null){
         throw new InvalidKeyException("Key can not be null!"); 
      }else{
         result = arrStrings.get(strKey);
         if (result == null){
            throw new InvalidKeyException("Invalid key " + strKey + "!"); 
         }else{
            return result;
         }

      }
   }
}
