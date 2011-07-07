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
package org.forumj.web.servlet.get;

import static org.forumj.tool.Diletant.*;
import static org.forumj.tool.FJServletTools.*;
import static org.forumj.web.servlet.tool.FJServletTools.*;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.db.dao.*;
import org.forumj.db.entity.*;
import org.forumj.exception.InvalidKeyException;
import org.forumj.tool.LocaleString;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebServlet(urlPatterns = {"/control.php", "/"}, name="control")
public class Control extends HttpServlet {

   private static final long serialVersionUID = -6828786894000688297L;

   /**
    * {@inheritDoc}
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      try {
         HttpSession session = request.getSession();
         //Предотвращаем кеширование
         cache(response);
         // Функции   
         // номер страницы
         Integer id = request.getParameter("id") == null ? 1 : Integer.valueOf(request.getParameter("id"));
         Long msg = request.getParameter("msg") == null ? 1 : Long.valueOf(request.getParameter("msg"));
         // Загружаем локализацию
         LocaleString locale = (LocaleString) session.getAttribute("locale");
         User user = (User) session.getAttribute("user");
         // Собираем статистику
         buffer.append("<!doctype html public \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
         buffer.append("<html>");
         buffer.append("<head>");
         buffer.append("<meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
         // Стили
         buffer.append(loadCSS("/css/style.css"));
         // Скрипты (смайлики)
         buffer.append(loadJavaScript("/js/smile_.js"));
         // Скрипты (счечик текстария)
         buffer.append(loadJavaScript("/js/checklength.js"));
         // Скрипты (автовставка тегов)
         buffer.append(loadJavaScript("/js/jstags.js"));
         // Скрипты (submit поста)
         buffer.append(send_submit(locale));
         buffer.append("<link rel=\"icon\" href=\"/favicon.ico\" type=\"image/x-icon\">");
         buffer.append("<link rel=\"shortcut icon\" href=\"/favicon.ico\" type=\"image/x-icon\">");
         buffer.append("<title>");
         buffer.append(locale.getString("mess127"));
         buffer.append("</title>");
         buffer.append("</head>");
         // Цвет фона страницы
         buffer.append("<body class='mainBodyBG'>");
         // Главная таблица
         buffer.append("<table border='0' style='border-collapse: collapse' width='100%'>");
         // Таблица с лого и верхним баннером
         buffer.append(logo(request));
         // Таблица главных ссылок
         buffer.append("<tr>");
         buffer.append("<td width='100%'>");
         buffer.append("<table border='0' style='border-collapse: collapse' width='100%'>");
         // Главное "меню"
         buffer.append(menu(request, user, locale, false));
         buffer.append("</table></td></tr>");
         //
         buffer.append("<tr><td>");
         // Таблица форума
         buffer.append("<table class='content'><tr><td>");
         // Таблица контента
         buffer.append("<table class='content'><tr>");
         // Ссылки на сервисы
         // Игнор-лист.
         buffer.append("<td height='300' valign='TOP' width='150'>");
         buffer.append("<table class='control'><tr class='heads'>");
         buffer.append("<th class='internal'>");
         buffer.append("<div class='mnuprof'>" + locale.getString("mess24") + "</div>");
         buffer.append("</th>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='control.php?id=1'>" + locale.getString("mess24") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr></table>");
         // Личная переписка
         buffer.append("<table class='control'><tr class='heads'>");
         buffer.append("<th class='internal'>");
         buffer.append("<div class='mnuprof'>" + locale.getString("mess23") + "</div>");
         buffer.append("</th>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='control.php?id=2'>" + locale.getString("mess54") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='control.php?id=3'>" + locale.getString("mess57") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='control.php?id=4'>" + locale.getString("mess55") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='control.php?id=5'>" + locale.getString("mess56") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr></table>");
         // Интерфейсы
         buffer.append("<table class='control'><tr class='heads'>");
         buffer.append("<th class='internal'>");
         buffer.append("<div class='mnuprof'>" + locale.getString("mess71") + "</div>");
         buffer.append("</th>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='control.php?id=6'>" + locale.getString("mess71") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='control.php?id=7'>" + locale.getString("mess72") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr></table>");
         // Подписка
         buffer.append("<table class='control'><tr class='heads'>");
         buffer.append("<th class='internal'>");
         buffer.append("<div class='mnuprof'>" + locale.getString("mess86") + "</div>");
         buffer.append("</th>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='control.php?id=8'>" + locale.getString("mess86") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr></table>");
         // Аватара
         buffer.append("<table class='control'><tr class='heads'>");
         buffer.append("<th class='internal'>");
         buffer.append("<div class='mnuprof'>" + locale.getString("mess93") + "</div>");
         buffer.append("</th>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='control.php?id=9'>" + locale.getString("mess93") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr></table>");
         // Местонахождение
         buffer.append("<table class='control'><tr class='heads'>");
         buffer.append("<th class='internal'>");
         buffer.append("<div class='mnuprof'>" + locale.getString("mess104") + "</div>");
         buffer.append("</th>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='control.php?id=10'>" + locale.getString("mess104") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr></table>");
         /*Подпись*/
         buffer.append("<table class='control'><tr class='heads'>");
         buffer.append("<th class='internal'>");
         buffer.append("<div class='mnuprof'>" + locale.getString("mess138") + "</div>");
         buffer.append("</th>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='control.php?id=11'>" + locale.getString("mess138") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr></table>");
         // Сервисы
         buffer.append("</td>");
         buffer.append("<td valign='TOP'>");
         switch(id) {
            case 0:
               // Зашли "по умолчанию"
               break;
            case 1:
               // Игнор-лист
               buffer.append(case1(user, locale));
               break;
            case 2:
               // Inbox
               buffer.append(case2(locale, user, msg));
               break;
            case 3:
               // Отправлено, но не доставлено
               buffer.append(case3());
               break;
            case 4:
               // Отправлено, и доставлено
               buffer.append(case4());
               break;
            case 5:
               //  Черновики
               buffer.append(case5());
               break;
            case 6:
               // Интерфейсы
               buffer.append(case6());
               break;
            case 7:
               // Папки
               buffer.append(case7());
               break;
            case 8:
               // Подписка
               buffer.append(case8());
               break;
            case 9:
               // Аватара
               buffer.append(case9());
               break;
            case 10:
               // Местонахождение
               buffer.append(case10());
               break;
            case 11:
               // Подпись
               buffer.append(case11());
               break;
         }
         buffer.append("</td>");
         buffer.append("</tr>");
         buffer.append("</table>");
         buffer.append("</td>");
         buffer.append("</tr>");
         buffer.append("</table>");
         buffer.append("</td>");
         buffer.append("</tr>");
         // Закрываем соединение MySql
         // Главное "меню"
         buffer.append("<tr>");
         buffer.append("<td width='100%'>");
         buffer.append("<table border='0' style='border-collapse: collapse' width='100%'>");
         buffer.append(menu(request, user, locale, false));
         buffer.append("</table></td></tr>");
         // Форма отправки письма личной переписки   
         if (id>1 && id<6){
            /*Форма нового мейла*/
            buffer.append("<tr>");
            buffer.append("<td>");
            buffer.append("<table>");
            buffer.append("<tr>");
            buffer.append("<td>");
            buffer.append("<form name='post' action='send.php' method='POST'>");
            buffer.append("<table width='100%'>");
            buffer.append("<tr>");
            buffer.append("<td width='100%'>");
            buffer.append("<table width='100%'>");
            /*От*/
            buffer.append("<tr>");
            buffer.append("<td align='LEFT'>");
            buffer.append("<div class='mnuprof'>");
            buffer.append(locale.getString("mess58") + "&nbsp;");
            buffer.append("</div>");
            buffer.append("</td>");
            buffer.append("<td>");
            buffer.append("<div class='mnuprof'>");
            buffer.append(user.getNick());
            buffer.append("</div>");
            buffer.append("</td>");
            buffer.append("</tr>");
            /*Кому*/
            buffer.append("<tr>");
            buffer.append("<td align='LEFT'>");
            buffer.append("<div class='mnuprof'>");
            buffer.append(locale.getString("mess28") + "&nbsp;");
            buffer.append("</div>");
            buffer.append("</td>");
            buffer.append("<td colspan='2'>");
            Integer error = (Integer) session.getAttribute("error");
            if (error != null){
               buffer.append("<input type='text' class='mnuforumSm' value='" + session.getAttribute("rcvr") + "' name='RCVR' size='30'>");
               if (error == 1){
                  buffer.append("<span class=hdforum>");
                  buffer.append("<font color='red'>");
                  buffer.append(locale.getString("mess65"));
                  buffer.append("</font>");
                  buffer.append("</span>");
               }
            }else{
               buffer.append("<input type=text class='mnuforumSm' name='RCVR' size='30'>");
            }
            buffer.append("</td>");
            buffer.append("</tr>");
            /*Тема*/
            buffer.append("<tr>");
            buffer.append("<td align='LEFT'>");
            buffer.append("<div class='mnuprof'>");
            buffer.append(locale.getString("mess59") + "&nbsp;");
            buffer.append("</div>");
            buffer.append("</td>");
            buffer.append("<td>");
            if (error != null){
               buffer.append("<input type=text class='mnuforumSm' name='NHEAD' value='" + session.getAttribute("head") + "' size='100'>");
            }else{
               buffer.append("<input type=text class='mnuforumSm' name='NHEAD' size='100'>");
            }
            buffer.append("</td>");
            buffer.append("</tr>");
            buffer.append("</table>");
            buffer.append("</td>");
            buffer.append("</tr>");
            buffer.append("<tr>");
            buffer.append("<td>");
            buffer.append("<table>");
            buffer.append("<tr>");
            /*Смайлики заголовок*/
            buffer.append("<td width='400' align='CENTER'>");
            buffer.append("<p>");
            buffer.append(locale.getString("mess21") + ":");
            buffer.append("</p>");
            buffer.append("</td>");
            /*Приглашение*/
            buffer.append("<td align='CENTER'>");
            buffer.append("<p>" + locale.getString("mess12") + "</p>");
            buffer.append("</td>");
            buffer.append("</tr>");
            /*Пост*/
            buffer.append("<tr>");
            buffer.append("<td valign='TOP' width='100%' height='100%'>");
            /*Смайлики*/
            buffer.append(smiles_add(locale.getString("mess11")));
            buffer.append("</td>");
            buffer.append("<td width='500' align='CENTER' valign='top'>");
            /*Автотеги*/
            buffer.append(autotags_add());
            /*текстарий*/
            buffer.append("<p>");
            String textareaValue = "";
            if (error != null) {
               textareaValue = (String) session.getAttribute("body");
            }
            buffer.append("<textarea class='mnuforumSm' rows='20' id='ed1' name='A2' cols='55'>" + textareaValue + "</textarea>");
            buffer.append("</p>");
            /*Кнопки*/
            buffer.append("<table>");
            buffer.append("<tr>");
            buffer.append("<td>");
            buffer.append(fd_button(locale.getString("mess13"),"send_submit(\"write\");","B1", "1"));
            buffer.append("</td>");
            buffer.append("<td>");
            buffer.append(fd_button(locale.getString("mess63"),"send_submit(\"view\");","B3", "1"));
            buffer.append("</td>");
            buffer.append("</tr>");
            buffer.append("</table>");
            /*Прередаем нужные пераметры...*/
            buffer.append(fd_form_add(user));
            buffer.append("</td>");
            buffer.append("</tr>");
            buffer.append("</table>");
            buffer.append("</td>");
            buffer.append("</tr>");
            buffer.append("</table>");
            buffer.append("</form>");
            buffer.append("</td>");
            buffer.append("</tr>");
            buffer.append("</table>");
            buffer.append("</td>");
            buffer.append("</tr>");
         }
         // Баннер внизу, счетчики и копирайт.
         buffer.append(footer(request));
         buffer.append("</body>");
         buffer.append("</html>");
      } catch (InvalidKeyException e) {
         e.printStackTrace();
      } catch (ConfigurationException e) {
         e.printStackTrace();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter writer = response.getWriter();
      String out = buffer.toString();
      writer.write(out);
   }

   private StringBuffer case1(User user, LocaleString locale) throws InvalidKeyException, IOException{
      StringBuffer buffer = new StringBuffer();
      // Выбираем список Игнорируемых
      List<Ignor> ignorList = new IgnorDao().loadAll(user.getId());
      if (ignorList.size() == 0) {
         // Нет.
         buffer.append("<span class='mnuprof'>" + locale.getString("mess25") + "</span>");
      }
      else {
         // Да
         // Таблица Игнора
         buffer.append("<div class='mnuprof' align='CENTER'><b>" + locale.getString("mess48") + "</b></div>");
         buffer.append("<table class='control'>");
         buffer.append("<tr class='heads'>");
         buffer.append("<th class='internal'>");
         buffer.append("<span class='mnuprof'>№</span>");
         buffer.append("</th>");
         buffer.append("<th class='internal'>");
         buffer.append("<span class='mnuprof'>" + locale.getString("mess44") + "</span>");
         buffer.append("</th>");
         buffer.append("<th class='internal'>");
         buffer.append("<span class='mnuprof'>" + locale.getString("mess45") + "</span>");
         buffer.append("</th>");
         buffer.append("<th class='internal'>");
         buffer.append("<span class='mnuprof'>" + locale.getString("mess46") + "</span>");
         buffer.append("</th>");
         buffer.append("<th class='internal'>");
         buffer.append("<span class='mnuprof'>" + locale.getString("mess53") + "</span>");
         buffer.append("</th>");
         buffer.append("<th class='internal'>");
         buffer.append("<span class='mnuprof'>" + locale.getString("mess47") + "</span>");
         buffer.append("</th>");
         buffer.append("</tr>");
         for (int ignorIndex=0; ignorIndex<ignorList.size(); ignorIndex++) {
            Ignor ignor = ignorList.get(ignorIndex);
            // id Записи
            Long ignorId = ignor.getId();
            // Тип игнора
            Integer ignorType = ignor.getType();
            // Игнорируемый
            String ignoredUserNick = ignor.getUser().getNick();
            // Начало
            Date ignorBeginDate = ignor.getStart(); 
            // Конец
            Date ignorEndDate = ignor.getEnd();
            // Выводим спи
            buffer.append("<tr>");
            // Номер
            buffer.append("<td class='internal'>");
            buffer.append("<span class='mnuprof'>" + (ignorIndex+1) + "</span>");
            buffer.append("</td>");
            // Игнорируемый
            buffer.append("<td class='internal'>");
            buffer.append("<span class='mnuprof'>" + ignoredUserNick + "</span>");
            buffer.append("</td>");
            // Начал
            buffer.append("<td class='internal'>");
            buffer.append("<span class='mnuprof'>" + ignorBeginDate + "</span>");
            buffer.append("</td>");
            // Конец
            buffer.append("<td class='internal'>");
            buffer.append("<span class='mnuprof'>" + ignorEndDate + "</span>");
            buffer.append("</td>");
            // Ветки игнорируются?
            buffer.append("<td class='internal' align='CENTER'>");
            String tema = locale.getString("mess26");
            if (ignorType == 0) tema=locale.getString("mess27");
            buffer.append("<span class='mnuprof'>" + tema + "</span>");
            buffer.append("</td>");
            // Изменение
            buffer.append("<td class='internal'>");
            buffer.append("<form method='POST' action='amn.php' class=frmsmall>");
            buffer.append("<select size='1' name='D'>");
            buffer.append("<option selected value='01'><span class='mnuprof'>1</span></option>");
            for (int xo1=2; xo1<32; xo1++){
               String dd="";
               if (xo1<10) dd="0";
               buffer.append("<option value='" + dd + xo1 + "'><span class='mnuprof'>" + xo1 + "</span></option>");
            }
            buffer.append("</select>&nbsp;");
            buffer.append("<select size='1' name='MTH'>");
            buffer.append("<option selected value='01'><span class='mnuprof'>" + locale.getString("mess32") + "</span></option>");
            buffer.append("<option value='02'><span class='mnuprof'>" + locale.getString("mess33") + "</span></option>");
            buffer.append("<option value='03'><span class='mnuprof'>" + locale.getString("mess34") + "</span></option>");
            buffer.append("<option value='04'><span class='mnuprof'>" + locale.getString("mess35") + "</span></option>");
            buffer.append("<option value='05'><span class='mnuprof'>" + locale.getString("mess36") + "</span></option>");
            buffer.append("<option value='06'><span class='mnuprof'>" + locale.getString("mess37") + "</span></option>");
            buffer.append("<option value='07'><span class='mnuprof'>" + locale.getString("mess38") + "</span></option>");
            buffer.append("<option value='08'><span class='mnuprof'>" + locale.getString("mess39") + "</span></option>");
            buffer.append("<option value='09'><span class='mnuprof'>" + locale.getString("mess40") + "</span></option>");
            buffer.append("<option value='10'><span class='mnuprof'>" + locale.getString("mess41") + "</span></option>");
            buffer.append("<option value='11'><span class='mnuprof'>" + locale.getString("mess42") + "</span></option>");
            buffer.append("<option value='12'><span class='mnuprof'>" + locale.getString("mess43") + "</span></option>");
            buffer.append("</select>&nbsp;");
            buffer.append("<select size='1' name='Y'>");
            buffer.append("<option class='mnuprof' selected value='2006'>2006</option>");
            buffer.append("<option class='mnuprof' value='2007'>2007</option>");
            buffer.append("<option class='mnuprof' value='2008'>2008</option>");
            buffer.append("<option class='mnuprof' value='2009'>2009</option>");
            buffer.append("</select>&nbsp;");
            buffer.append("<select size='1' name='H'>");
            buffer.append("<option class='mnuprof' selected value='00'>0</option>");
            for (int xo2=1; xo2<24; xo2++){
               String dd="";
               if (xo2<10) dd="0";
               buffer.append("<option class='mnuprof' value='" + dd + xo2 + "'>" + xo2 + "</option>");
            }
            buffer.append("</select>&nbsp;");
            buffer.append("<select size='1' name='M'>");
            buffer.append("<option class='mnuprof' selected value='00'>0</option>");
            for (int xo3=1; xo3<60; xo3++){
               String dd="";
               if (xo3<10) dd="0";
               buffer.append("<option class='mnuprof' value='" + dd + xo3 + "'>" + xo3 + "</option>");
            }
            buffer.append("</select>&nbsp;");
            buffer.append("<input class='mnuprof' type=\"submit\" value=\"" + locale.getString("mess49") + "\" name=\"B1\">");
            buffer.append("<br><input type='checkbox' name='C1' value='ON'> <span class='mnuprof'>" + locale.getString("mess52") + "</span>");
            // Прередаем нужные параметры...
            // id Записи
            buffer.append("<input type=hidden name=\"IDZ\" size=\"20\" value=\"" + ignorId + "\">");
            // Автор
            buffer.append(fd_form_add(user));
            buffer.append("</form>");
            buffer.append("</td>");

            buffer.append("</tr>");
         }
         buffer.append("</table>");
      }
      return buffer;
   }

   private StringBuffer case2(LocaleString locale, User user, Long msg) throws ConfigurationException, SQLException, IOException, InvalidKeyException{
      StringBuffer buffer = new StringBuffer();
      buffer.append("<div class='mnuprof' align='CENTER'><b>" + locale.getString("mess17") + "</b></div>");
      buffer.append("<form method='POST' class='content' action='delmail.php?id=2'>");
      buffer.append("<table class='control'><tr class='heads'>");
      FJMailDao mailDao = new FJMailDao();
      mailDao.receiveMail(user.getId());
      // Выбираем почту
      List<FJMail> mails = mailDao.loadInbox(user);
      if (mails.size() > 0){
         // Заголовки таблицы
         buffer.append("<th class='internal' width='120'><div class='tbtext' width='120'>" + locale.getString("mess58") + "</div></th>");
         buffer.append("<th class='internal'><div class='tbtext'>" + locale.getString("mess59") + "</div></th>");
         buffer.append("<th class='internal' width='120'><div class='tbtext' width='120'>" + locale.getString("mess60") + "</div></th>");
         buffer.append("<th class='internal' width='120'><div class='tbtext' width='120'>" + locale.getString("mess61") + "</div></th>");
         buffer.append("<th class='internal' width='20'></th>");
         buffer.append("</tr>");
         // Выводим сообщения
         for (int mailIndex=0; mailIndex < mails.size(); mailIndex++){
            FJMail mail = mails.get(mailIndex);
            // id письма
            Long mailId = mail.getId();
            // Тема письма
            String mailSubject = mail.getSubject();
            // От.
            String senderNick = mail.getSender().getNick();
            // Когда пришло.
            Date receiveDate = mail.getReceiveDate();
            // Когда отправлено.
            Date sentDate = mail.getSentDate();
            // Прочитано?
            Date readDate = mail.getReadDate();
            buffer.append("<tr>");
            // От.
            buffer.append("<td class='internal'><div align='center' class='tbtext'>");
            buffer.append(senderNick);
            buffer.append("</div></td>");
            // Тема письма
            if (readDate == null){
               buffer.append("<td class='internal'><div class='tbtextnread'>");
               buffer.append("<a href='control.php?id=2&msg=" + mailId + "'>" + fd_head(mailSubject) + "</a>");
               buffer.append("</div></td>");
            }
            else {
               buffer.append("<td class='internal'><div class='tbtext'>");
               buffer.append("<a href='control.php?id=2&msg=" + mailId + "'>" + fd_head(mailSubject) + "</a>");
               buffer.append("</div></td>");
            }
            // Когда пришло.
            buffer.append("<td class='internal'><div align='center' class='tbtext'>");
            buffer.append(receiveDate);
            buffer.append("</div></td>");
            // Когда отправлено.
            buffer.append("<td class='internal'><div align='center' class='tbtext'>");
            buffer.append(sentDate);
            buffer.append("</div></td>");
            // Флажок.
            buffer.append("<td class='internal'><div align='center' class='tbtext'>");
            buffer.append("<input type='checkbox' name='" + mailIndex + "' value='" + mailId + "'>");
            buffer.append("</div></td>");       
            buffer.append("</tr>");
         }
         // Сервис (пока только удаление)
         buffer.append("<tr>");
         buffer.append("<td colspan=5 align='right'>"); 
         buffer.append("<span class='tbtextnread'>" + locale.getString("mess69") + "&nbsp;&nbsp;</span>");
         buffer.append("<select size='1' name='ACT'>");
         buffer.append("<option selected value='del'><span class='mnuprof'>" + locale.getString("mess70") + "&nbsp;&nbsp;</span></option>");
         buffer.append("</select>&nbsp;");
         buffer.append("<input type='hidden' value='" + mails.size() + "' name='NRW'>");
         // Прередаем нужные пераметры...
         // Автор
         buffer.append(fd_form_add(user));
         buffer.append("<input value='OK' type='submit'>");
         buffer.append("</td>");
         buffer.append("</tr>");
      }
      else {
         buffer.append("<th class='internal'><div class='tbtext'>" + locale.getString("mess18") + "</div></th>");
         buffer.append("</tr>");
      }
      // Кнопка формы
      //Выводим письмо
      if (msg != null){
         // Находим его
         FJMail mail = mailDao.loadMail(user, msg);
         if (mail != null){ 
            // Помечаем как прочитанное.
            if (mail.getReadDate() == null){
               mailDao.markMailAsRead(user.getId(), msg);
            }
            // Принимаем.     
            // Отправлено.
            Date sentDate = mail.getSentDate();
            // Получено.
            Date receiveDate = mail.getReceiveDate();
            // От кого.
            String senderNick = mail.getSender().getNick();
            // Заголовок.
            String subject = mail.getSubject();
            // Тело.
            String body = mail.getBody();
            buffer.append("<tr class='heads'><td colspan=5 class='internal'>");
            // От кого.
            buffer.append("<span class='tbtext'>" + locale.getString("mess60") + ":&nbsp;" + receiveDate + "&nbsp;" + locale.getString("mess61") + ":&nbsp;" + sentDate + "&nbsp;" + locale.getString("mess58") + ":&nbsp;</span><span class=nick>" + senderNick + "</span>");
            buffer.append("</td></tr>");
            // Тело.
            buffer.append("<tr><td colspan=5 class='internal'>");
            buffer.append("<div class=nik>" + fd_head(subject) + "</div>");
            buffer.append("<div class=post>" + fd_body(body) + "</div>");
            buffer.append("</td></tr>");
         }
      }
      buffer.append("</table>");
      buffer.append("</form>");
      return buffer;
   }

   private StringBuffer case3(){
      StringBuffer buffer = new StringBuffer();
      return buffer;
   }

   private StringBuffer case4(){
      StringBuffer buffer = new StringBuffer();
      return buffer;
   }

   private StringBuffer case5(){
      StringBuffer buffer = new StringBuffer();
      return buffer;
   }

   private StringBuffer case6(){
      StringBuffer buffer = new StringBuffer();
      return buffer;
   }

   private StringBuffer case7(){
      StringBuffer buffer = new StringBuffer();
      return buffer;
   }

   private StringBuffer case8(){
      StringBuffer buffer = new StringBuffer();
      return buffer;
   }

   private StringBuffer case9(){
      StringBuffer buffer = new StringBuffer();
      return buffer;
   }

   private StringBuffer case10(){
      StringBuffer buffer = new StringBuffer();
      return buffer;
   }

   private StringBuffer case11(){
      StringBuffer buffer = new StringBuffer();
      return buffer;
   }

}
