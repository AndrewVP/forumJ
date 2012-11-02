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
import org.forumj.common.*;
import org.forumj.common.db.entity.*;
import org.forumj.common.db.service.*;
import org.forumj.common.exception.InvalidKeyException;
import org.forumj.common.tool.HtmlChars;
import org.forumj.common.web.Command;
import org.forumj.common.web.Locale;
import org.forumj.tool.*;
import org.forumj.web.servlet.FJServlet;

import com.tecnick.htmlutils.htmlentities.HTMLEntities;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.SETTINGS}, name = FJServletName.SETTINGS)
public class Control extends FJServlet {

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
         Long msg = request.getParameter("msg") == null ? null : Long.valueOf(request.getParameter("msg"));
         Long view = request.getParameter("view") == null ? null : Long.valueOf(request.getParameter("view"));
         // Загружаем локализацию
         LocaleString locale = (LocaleString) session.getAttribute("locale");
         IUser user = (IUser) session.getAttribute("user");
         IgnorService ignorService = FJServiceHolder.getIgnorService();
         FolderService folderService = FJServiceHolder.getFolderService();
         MailService mailService = FJServiceHolder.getMailService();
         SubscribeService subscribeService = FJServiceHolder.getSubscribeService();
         InterfaceService interfaceService = FJServiceHolder.getInterfaceService();
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
         buffer.append("<a class='mnuprof' href='" + FJUrl.SETTINGS + "?id=1'>" + locale.getString("mess24") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr></table>");
         /*e-mail*/
         buffer.append("<table class='control'><tr class='heads'>");
         buffer.append("<th class='internal'>");
         buffer.append("<div class='mnuprof'>" + locale.getString("MSG_EMAIL") + "</div>");
         buffer.append("</th>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='" + FJUrl.SETTINGS + "?id=13'>" + locale.getString("MSG_EMAIL") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr></table>");
         // Личная переписка
         buffer.append("<table class='control'><tr class='heads'>");
         buffer.append("<th class='internal'>");
         buffer.append("<div class='mnuprof'>" + locale.getString("mess23") + "</div>");
         buffer.append("</th>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='" + FJUrl.SETTINGS + "?id=2'>" + locale.getString("mess54") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='" + FJUrl.SETTINGS + "?id=3'>" + locale.getString("mess57") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='" + FJUrl.SETTINGS + "?id=4'>" + locale.getString("mess55") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='" + FJUrl.SETTINGS + "?id=5'>" + locale.getString("mess56") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr></table>");
         // Интерфейсы
         buffer.append("<table class='control'><tr class='heads'>");
         buffer.append("<th class='internal'>");
         buffer.append("<div class='mnuprof'>" + locale.getString("mess71") + "</div>");
         buffer.append("</th>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='" + FJUrl.SETTINGS + "?id=6'>" + locale.getString("mess71") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='" + FJUrl.SETTINGS + "?id=7'>" + locale.getString("mess72") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr></table>");
         // Подписка
         buffer.append("<table class='control'><tr class='heads'>");
         buffer.append("<th class='internal'>");
         buffer.append("<div class='mnuprof'>" + locale.getString("mess86") + "</div>");
         buffer.append("</th>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='" + FJUrl.SETTINGS + "?id=8'>" + locale.getString("mess86") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr></table>");
         // Аватара
         buffer.append("<table class='control'><tr class='heads'>");
         buffer.append("<th class='internal'>");
         buffer.append("<div class='mnuprof'>" + locale.getString("mess93") + "</div>");
         buffer.append("</th>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='" + FJUrl.SETTINGS + "?id=9'>" + locale.getString("mess93") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr></table>");
         // Местонахождение
         buffer.append("<table class='control'><tr class='heads'>");
         buffer.append("<th class='internal'>");
         buffer.append("<div class='mnuprof'>" + locale.getString("mess104") + "</div>");
         buffer.append("</th>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='" + FJUrl.SETTINGS + "?id=10'>" + locale.getString("mess104") + "</a><br>");
         buffer.append("</td>");
         /*Locale*/
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='" + FJUrl.SETTINGS + "?id=12'>" + locale.getString("MSG_INTERF_LOCALE") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr></table>");
         /*Подпись*/
         buffer.append("<table class='control'><tr class='heads'>");
         buffer.append("<th class='internal'>");
         buffer.append("<div class='mnuprof'>" + locale.getString("mess138") + "</div>");
         buffer.append("</th>");
         buffer.append("</tr><tr>");
         buffer.append("<td class='internal'>");
         buffer.append("<a class='mnuprof' href='" + FJUrl.SETTINGS + "?id=11'>" + locale.getString("mess138") + "</a><br>");
         buffer.append("</td>");
         buffer.append("</tr></table>");

         buffer.append("</td>");
         buffer.append("<td valign='TOP' style='padding-left:5px;'>");
         switch(id) {
         case 0:
            // Зашли "по умолчанию"
            break;
         case 1:
            // Игнор-лист
            buffer.append(case1(user, locale, ignorService));
            break;
         case 2:
            // Inbox
            buffer.append(case2(locale, user, msg, mailService));
            break;
         case 3:
            // Отправлено, но не доставлено
            buffer.append(case3(locale, user, msg, mailService));
            break;
         case 4:
            // Отправлено, и доставлено
            buffer.append(case4(locale, user, msg, mailService));
            break;
         case 5:
            //  Черновики
            buffer.append(case5(locale, user, msg, mailService));
            break;
         case 6:
            // Интерфейсы
            buffer.append(case6(locale, user, view, interfaceService, folderService));
            break;
         case 7:
            // Папки
            buffer.append(case7(locale, user, folderService));
            break;
         case 8:
            // Подписка
            buffer.append(case8(locale, user, subscribeService));
            break;
         case 9:
            // Аватара
            buffer.append(case9(locale, user));
            break;
         case 10:
            // Местонахождение
            buffer.append(case10(locale, user));
            break;
         case 11:
            // Подпись
            buffer.append(case11(locale, user));
            break;
         case 12:
            // Язык интерфейса
            buffer.append(language(locale, user));
            break;
         case 13:
            // E-mail
            buffer.append(eMail(locale, user));
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
            buffer.append("<form name='post' action='" + FJUrl.SEND_PIVATE_MESSAGE + "?id=" + id + "' method='post'>");
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
         buffer.append("</table>");
         buffer.append("</body>");
         buffer.append("</html>");
      } catch (Throwable e) {
         buffer = new StringBuffer();
         buffer.append(errorOut(e));
         e.printStackTrace();
      }
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter writer = response.getWriter();
      String out = buffer.toString();
      writer.write(out);
   }

   private StringBuffer case1(IUser user, LocaleString locale, IgnorService ignorService) throws InvalidKeyException, IOException, ConfigurationException, SQLException{
      StringBuffer buffer = new StringBuffer();
      // Выбираем список Игнорируемых
      List<IIgnor> ignorList = ignorService.readUserIgnor(user.getId());
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
            IIgnor ignor = ignorList.get(ignorIndex);
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
            buffer.append("<form method='post' action='" + FJUrl.UPDATE_IGNORING + "' class=frmsmall>");
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
            buffer.append("<option class='mnuprof' selected value='2012'>2012</option>");
            for (int year=2013; year < 2100; year++){
               buffer.append("<option class='mnuprof' value='" + year + "'>" + year + "</option>");
            }
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
            buffer.append("<input type='hidden' name=\"IDZ\" size=\"20\" value=\"" + ignorId + "\">");
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

   private StringBuffer case2(LocaleString locale, IUser user, Long msg, MailService mailService) throws ConfigurationException, SQLException, IOException, InvalidKeyException{
      StringBuffer buffer = new StringBuffer();
      IFJMail mail = null;
      if (msg != null){
         // Находим его
         mail = mailService.loadMail(user, msg, false);
         // Помечаем как прочитанное.
         if (mail.getReadDate() == null){
            mailService.markMailAsRead(user.getId(), msg);
         }
      }
      buffer.append("<div class='mnuprof' align='CENTER'><b>" + locale.getString("mess17") + "</b></div>");
      buffer.append("<form method='post' class='content' action='" + FJUrl.DELETE_MAIL + "?id=2'>");
      buffer.append("<table class='control'><tr class='heads'>");
      mailService.receiveMail(user.getId());
      // Выбираем почту
      List<IFJMail> mails = mailService.loadInbox(user);
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
            IFJMail gridMail = mails.get(mailIndex);
            buffer.append("<tr>");
            // От.
            buffer.append("<td class='internal'><div align='center' class='tbtext'>");
            buffer.append(gridMail.getSender().getNick());
            buffer.append("</div></td>");
            // Тема письма
            if (gridMail.getReadDate() == null){
               buffer.append("<td class='internal'><div class='tbtextnread'>");
               buffer.append("<a href='" + FJUrl.SETTINGS + "?id=2&msg=" + gridMail.getId() + "'>" + fd_head(gridMail.getSubject()) + "</a>");
               buffer.append("</div></td>");
            }
            else {
               buffer.append("<td class='internal'><div class='tbtext'>");
               buffer.append("<a href='" + FJUrl.SETTINGS + "?id=2&msg=" + gridMail.getId() + "'>" + fd_head(gridMail.getSubject()) + "</a>");
               buffer.append("</div></td>");
            }
            // Когда пришло.
            buffer.append("<td class='internal'><div align='center' class='tbtext'>");
            buffer.append(gridMail.getReceiveDate());
            buffer.append("</div></td>");
            // Когда отправлено.
            buffer.append("<td class='internal'><div align='center' class='tbtext'>");
            buffer.append(gridMail.getSentDate());
            buffer.append("</div></td>");
            // Флажок.
            buffer.append("<td class='internal'><div align='center' class='tbtext'>");
            buffer.append("<input type='checkbox' name='" + mailIndex + "' value='" + gridMail.getId() + "'>");
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
         buffer.append(fd_form_add(user));
         buffer.append("<input value='OK' type='submit'>");
         buffer.append("</td>");
         buffer.append("</tr>");
      }else{
         buffer.append("<th class='internal'><div class='tbtext'>" + locale.getString("mess18") + "</div></th>");
         buffer.append("</tr>");
      }
      // Кнопка формы
      //Выводим письмо
      if (msg != null){
         if (mail != null){ 
            buffer.append("<tr class='heads'><td colspan=5 class='internal'>");
            // От кого.
            buffer.append("<span class='tbtext'>" + locale.getString("mess60") + ":&nbsp;" + mail.getReceiveDate() + "&nbsp;" + locale.getString("mess61") + ":&nbsp;" + mail.getSentDate() + "&nbsp;" + locale.getString("mess58") + ":&nbsp;</span><span class=nick>" + mail.getSender().getNick() + "</span>");
            buffer.append("</td></tr>");
            // Тело.
            buffer.append("<tr><td colspan=5 class='internal'>");
            buffer.append("<div class=nik>" + fd_head(mail.getSubject()) + "</div>");
            buffer.append("<div class=post>" + fd_body(mail.getBody()) + "</div>");
            buffer.append("</td></tr>");
         }
      }
      buffer.append("</table>");
      buffer.append("</form>");
      return buffer;
   }

   private StringBuffer case3(LocaleString locale, IUser user, Long msg, MailService mailService) throws InvalidKeyException, ConfigurationException, IOException, SQLException{
      StringBuffer buffer = new StringBuffer();
      buffer.append("<div class='mnuprof' align='CENTER'><b>" + locale.getString("mess15") + "</b></div>");
      buffer.append("<table class='control'><tr class=heads>");
      // Выбираем почту
      List<IFJMail> mails = mailService.loadOutNotReceivedBox(user);
      if (mails.size() > 0){
         // Заголовки таблицы
         buffer.append("<th class='internal' width='120'><div class=tbtext>" + locale.getString("mess19") + "</div></th>");
         buffer.append("<th class='internal'><div class=tbtext>" + locale.getString("mess59") + "</div></th>");
         buffer.append("<th class='internal' width='120'><div class=tbtext>" + locale.getString("mess61") + "</div></th>");
         buffer.append("</tr>");
         // Выводим сообщения
         for (int mailIndex=0; mailIndex < mails.size(); mailIndex++){
            IFJMail mail = mails.get(mailIndex);
            buffer.append("<tr>");
            // Кому
            buffer.append("<td class='internal' width='120'><div class=tbtext>");
            buffer.append(mail.getReceiver().getNick());
            buffer.append("</div></td>");
            // Тема письма
            buffer.append("<td class='internal'><div class=tbtext>");
            buffer.append("<a href='" + FJUrl.SETTINGS + "?id=3&msg=" + mail.getId() + "'>" + fd_head(mail.getSubject()) + "</a>");
            buffer.append("</div></td>");
            // Когда отправлено
            buffer.append("<td class='internal' width='120'><div class=tbtext>");
            buffer.append(mail.getSentDate());
            buffer.append("</div></td>");
            buffer.append("</tr>");
         }}
      else {
         buffer.append("<th class='internal'><div class=tbtext>" + locale.getString("mess18") + "</div></th>");
         buffer.append("</tr>");
      }
      // Выводим письмо.
      if (msg != null){
         // Находим его
         IFJMail mail = mailService.loadMail(user, msg, false);
         if (mail != null){
            buffer.append("<tr class=heads><td colspan=3 class=internal>");
            // Кому.
            buffer.append("<span class=tbtext>" + locale.getString("mess61") + ":&nbsp;" + mail.getSentDate() + "&nbsp;" + locale.getString("mess19") + ":&nbsp;</span><span class=nick>" + mail.getReceiver().getNick() + "</span>");
            buffer.append("</td></tr>");
            // Тело.
            buffer.append("<tr><td colspan=3 class=internal>");
            buffer.append("<div class=nik>" + fd_head(mail.getSubject()) + "</div>");
            buffer.append("<div class=post>" + fd_body(mail.getBody()) + "</div>");
            buffer.append("</td></tr>");
         }
      }
      buffer.append("</table>");
      buffer.append("</form>");
      return buffer;
   }

   private StringBuffer case4(LocaleString locale, IUser user, Long msg, MailService mailService) throws ConfigurationException, IOException, SQLException, InvalidKeyException{
      StringBuffer buffer = new StringBuffer();
      buffer.append("<div class='mnuprof' align='CENTER'><b>" + locale.getString("mess16") + "</b></div>");
      buffer.append("<form method='post' class='content' action='" + FJUrl.DELETE_MAIL + "?id=4'>");
      buffer.append("<table class='control'><tr class=heads>");
      // Выбираем почту
      List<IFJMail> mails = mailService.loadOutReceivedBox(user);
      if (mails.size() > 0){
         // Заголовки таблицы
         // Кому
         buffer.append("<th class='internal' width='120'><div class=tbtext>" + locale.getString("mess19") + "</div></th>");
         // Тема письма
         buffer.append("<th class='internal'><div class=tbtext>" + locale.getString("mess59") + "</div></th>");
         // Когда отправлено.
         buffer.append("<th class='internal' width='120'><div class=tbtext>" + locale.getString("mess61") + "</div></th>");
         // Когда получено.
         buffer.append("<th class='internal' width='120'><div class=tbtext>" + locale.getString("mess60") + "</div></th>");
         buffer.append("<th class='internal' width='20'></th>");
         buffer.append("</tr>");
         // Выводим сообщения
         for (int mailIndex=0; mailIndex < mails.size(); mailIndex++){
            IFJMail mail = mails.get(mailIndex);
            buffer.append("<tr>");
            // Кому
            buffer.append("<td class='internal' width='120'><div class=tbtext>");
            buffer.append(mail.getReceiver().getNick());
            buffer.append("</div></td>");
            // Тема письма
            buffer.append("<td class='internal'><div class=tbtext>");
            buffer.append("<a href='" + FJUrl.SETTINGS + "?id=4&msg=" + mail.getId() + "'>" + fd_head(mail.getSubject()) + "</a>");
            buffer.append("</div></td>");
            // Когда отправлено.
            buffer.append("<td class='internal' width='120'><div class=tbtext>");
            buffer.append(mail.getSentDate());
            buffer.append("</div></td>");
            // Когда получено.
            buffer.append("<td class='internal' width='120'><div class=tbtext>");
            buffer.append(mail.getReceiveDate());
            buffer.append("</div></td>");
            // Флажок.
            buffer.append("<td class='internal'><div align='center' class=tbtext>");
            buffer.append("<input type='checkbox' name='" + mailIndex + "' value='" + mail.getId() + "'>");
            buffer.append("</div></td>");       
            buffer.append("</tr>");
         }
         // Сервис (пока только удаление)
         buffer.append("<tr>");
         buffer.append("<td colspan=5 align='right'>"); 
         buffer.append("<span class=tbtextnread>" + locale.getString("mess69") + "&nbsp;&nbsp;</span>");
         buffer.append("<select size='1' name='ACT'>");
         buffer.append("<option selected value='del'><span class='mnuprof'>" + locale.getString("mess70") + "&nbsp;&nbsp;</span></option>");
         buffer.append("</select>&nbsp;");
         buffer.append("<input type='hidden' value='" + mails.size() + "' name='NRW'>");
         buffer.append(fd_form_add(user));
         buffer.append("<input value='OK' type='submit'>");
         buffer.append("</td>");
         buffer.append("</tr>");
      }else{
         buffer.append("<th class='internal'><div class=tbtext>" + locale.getString("mess18") + "</div></th>");
         buffer.append("</tr>");
      }
      //Выводим письмо
      if (msg != null){
         // Находим его
         IFJMail mail = mailService.loadMail(user, msg, true);
         if (mail != null){
            buffer.append("<tr class=heads><td colspan=4 class=internal>");
            // Кому.
            buffer.append("<span class=tbtext>" + locale.getString("mess60") + ":&nbsp;" + mail.getReceiveDate() + "&nbsp;" + locale.getString("mess61") + ":&nbsp;" + mail.getSentDate() + "&nbsp;" + locale.getString("mess28") + ":&nbsp;</span><span class=nick>" + mail.getReceiver().getNick() + "</span>");
            buffer.append("</td></tr>");
            // Тело.
            buffer.append("<tr><td colspan=4 class=internal>");
            buffer.append("<div class=nik>" + fd_head(mail.getSubject()) + "</div>");
            buffer.append("<div class=post>" + fd_body(mail.getBody()) + "</div>");
            buffer.append("</td></tr>");
         }
      }
      buffer.append("</table>");
      buffer.append("</form>");
      return buffer;
   }

   private StringBuffer case5(LocaleString locale, IUser user, Long msg, MailService mailService) throws InvalidKeyException, ConfigurationException, IOException, SQLException{
      StringBuffer buffer = new StringBuffer();
      buffer.append("<div class='mnuprof' align='CENTER'><b>" + locale.getString("mess62") + "</b></div>");
      buffer.append("<table class='control'><tr class=heads>");
      // Выбираем почту
      List<IFJMail> mails = mailService.loadDraftBox(user);
      if (mails.size() > 0){
         // Заголовки таблицы
         // Кому
         buffer.append("<th class='internal' width='120'><div class=tbtext>" + locale.getString("mess19") + "</div></th>");
         // Тема письма
         buffer.append("<th class='internal'><div class=tbtext>" + locale.getString("mess59") + "</div></th>");
         // Когда создано
         buffer.append("<th class='internal' width='120'><div class=tbtext>" + locale.getString("mess20") + "</div></th>");
         buffer.append("</tr>");
         // Выводим сообщения
         for (int mailIndex=0; mailIndex < mails.size(); mailIndex++){
            IFJMail mail = mails.get(mailIndex);
            buffer.append("<tr>");
            // Кому
            buffer.append("<td class='internal' width='120'><div class=tbtext>");
            buffer.append(mail.getReceiver().getNick());
            buffer.append("</div></td>");
            // Тема письма
            buffer.append("<td class='internal'><div class=tbtext>");
            buffer.append("<a href='" + FJUrl.SETTINGS + "?id=5&msg=" + mail.getId() + "'>" + fd_head(mail.getSubject()) + "</a>");
            buffer.append("</div></td>");
            // Когда создано.
            buffer.append("<td class='internal' width='120'><div class=tbtext>");
            buffer.append(mail.getCreateDate());
            buffer.append("</div></td>");
            buffer.append("</tr>");
         }
      }else{
         buffer.append("<th class='internal'><div class=tbtext>" + locale.getString("mess18") + "</div></th>");
         buffer.append("</tr>");
      }
      //Выводим письмо
      if (msg != null){
         // Находим его
         IFJMail mail = mailService.loadMail(user, msg, false);
         if (mail != null){
            buffer.append("<tr class=heads><td colspan=3 class=internal>");
            // Кому.
            buffer.append("<span class=tbtext>" + locale.getString("mess20") + ":&nbsp;" + mail.getCreateDate() + "&nbsp;" + locale.getString("mess19") + ":&nbsp;</span><span class=nick>" + mail.getReceiver().getNick());
            buffer.append("</td></tr>");
            // Тело.
            buffer.append("<tr><td colspan=3 class=internal>");
            buffer.append("<div class=nik>" + fd_head(mail.getSubject()) + "</div>");
            buffer.append("<div class=post>" + fd_body(mail.getBody()) + "</div>");
            buffer.append("</td></tr>");
         }
      }
      buffer.append("</table>");
      return buffer;
   }

   private StringBuffer case6(LocaleString locale, IUser user, Long viewId, InterfaceService interfaceService, FolderService folderService) throws InvalidKeyException, ConfigurationException, SQLException, IOException{
      StringBuffer buffer = new StringBuffer();
      // Выбираем список интерфейсов
      List<IFJInterface> interfaces = interfaceService.findAllInterfaces(user);
      // "Список Ваших интерфейсов"
      buffer.append("<div class='mnuprof' align='CENTER'><b>" + locale.getString("mess76") + "</b></div>");
      // Интерфейс по умолчанию
      // 
      buffer.append("<form method='post' class='content' action='" + FJUrl.SET_DEFAULT_VIEW + "'>");
      buffer.append("<span class=tbtext><b>" + locale.getString("mess84") + "</b></span>");
      buffer.append("<select size='1' name='DVIEW'>");
      for (int interfIndex=0; interfIndex < interfaces.size(); interfIndex++){
         String add="";
         IFJInterface interf = interfaces.get(interfIndex);
         if (interf.getId().equals(user.getView())) {
            add="selected ";
         }
         buffer.append("<option " + add + "value='" + interf.getId() + "'><span class='mnuprof'>" + interf.getName() + "</span></option>");
      }
      buffer.append("</select>&nbsp;");
      buffer.append("<input type='submit' value='" + locale.getString("mess85") + "'>");
      // Прередаем нужные пераметры...
      // Автор
      buffer.append(fd_form_add(user));
      buffer.append("</form>");

      buffer.append("<form method='post' class='content' action='" + FJUrl.DELETE_VIEW + "?'>");
      buffer.append("<table class='control'><tr class=heads>");
      // Заголовки таблицы
      // Имя интерфейса
      buffer.append("<th class='internal'><div class=tbtext>" + locale.getString("mess77") + "</div></th>");
      // Флажок
      buffer.append("<th class='internal' width='20'></th>");
      buffer.append("</tr>");
      int interfacesAmount = interfaces.size();
      for (int interfIndex = 0; interfIndex < interfacesAmount; interfIndex++){ 
         IFJInterface interf = interfaces.get(interfIndex);
         // Имя интерфейса
         String name = interf.getName();
         // выделяем выбраный интерфейс
         if (viewId != null && viewId.equals(interf.getId())){
            name = "<b>" + name + "</b>";
         }
         buffer.append("<tr>");
         // Интерфейс
         buffer.append("<td class='internal'><div class=tbtext>");
         buffer.append("<a href=" + FJUrl.SETTINGS + "?id=6&view=" + interf.getId() + ">" + name + "</a>");
         buffer.append("</div></td>");
         // Флажок.
         buffer.append("<td class='internal'>");
         if (interf.getUser() != null){
            buffer.append("<div align='center' class=tbtext>");
            buffer.append("<input type='checkbox' name='" + interfIndex + "' value='" + interf.getId() + "'>");
            buffer.append("</div>");
         }
         buffer.append("</td>");       
         buffer.append("</tr>");
      }
      // Сервис (пока только удаление)
      buffer.append("<tr>");
      buffer.append("<td colspan=2 class='internal' align='right'>"); 
      buffer.append("<span class=tbtextnread>" + locale.getString("mess69") + "&nbsp;&nbsp;</span>");
      buffer.append("<select size='1' name='ACT'>");
      buffer.append("<option selected value='del'><span class='mnuprof'>" + locale.getString("mess70") + "&nbsp;&nbsp;</span></option>");
      buffer.append("</select>&nbsp;");
      buffer.append("<input type='hidden' value='" + interfacesAmount + "' name='NRW'>");
      // Прередаем нужные пераметры...
      buffer.append(fd_form_add(user));
      buffer.append("<input value='OK' type='submit'>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</form>");  
      buffer.append("<form method='post' class='content' action='" + FJUrl.NEW_VIEW + "'>");
      buffer.append("<span class=tbtext>" + locale.getString("mess77") + ":&nbsp;</span>"); 
      buffer.append("<input type='text' size=50 name='FOLD'>");
      buffer.append("&nbsp;<input type='submit' value='" + locale.getString("mess75") + "' >");
      // Прередаем нужные пераметры...
      buffer.append(fd_form_add(user));
      buffer.append("</form>");  
      // Настройки интерфейса
      if (viewId != null) {
         IFJInterface interf = interfaceService.findInterface(user, viewId);
         // Выбираем список папок в интерфейсе
         List<IFJFolder> folders = folderService.findAllFolders(user, interf);
         buffer.append("<div class='mnuprof' align='CENTER'><b>" + locale.getString("mess78") + "&nbsp;<u>" + interf.getName() + "</u></b></div>");
         buffer.append("<form method='post' class='content' action='" + FJUrl.DELETE_FOLDER_FROM_VIEW + "'>");
         buffer.append("<table class='control'><tr class=heads>");
         // Заголовки таблицы
         // Имя папки
         buffer.append("<th class='internal'><div class=tbtext>" + locale.getString("mess74") + "</div></th>");
         // Флажок
         buffer.append("<th class='internal' width='20'></th>");
         buffer.append("</tr>");
         for (int folderIndex = 0; folderIndex < folders.size(); folderIndex++){
            IFJFolder folder = folders.get(folderIndex);
            // Папки
            buffer.append("<tr>");
            buffer.append("<td class='internal'><div class=tbtext>");
            buffer.append(folder.getName());
            buffer.append("</div></td>");
            // Флажок.
            buffer.append("<td class='internal'>");
            if (folder.getUser() != null){
               buffer.append("<div align='center' class=tbtext>");
               buffer.append("<input type='checkbox' name='" + folderIndex + "' value='" + folder.getId() + "'>");
               buffer.append("</div>");
            }
            buffer.append("</td>");       
            buffer.append("</tr>");
         }
         // Сервис (пока только удаление)
         buffer.append("<tr>");
         buffer.append("<td colspan=2 class='internal' align='right'>"); 
         buffer.append("<span class=tbtextnread>" + locale.getString("mess69") + "&nbsp;&nbsp;</span>");
         buffer.append("<select size='1' name='ACT'>");
         buffer.append("<option selected value='del'><span class='mnuprof'>" + locale.getString("mess70") + "&nbsp;&nbsp;</span></option>");
         buffer.append("</select>&nbsp;");
         buffer.append("<input type='hidden' value='" + folders.size() + "' name='NRW'>");
         // id Интерфейса
         buffer.append("<input type='hidden' name='IDVW' value='" + viewId + "'>");
         // Прередаем нужные пераметры...
         buffer.append(fd_form_add(user));
         buffer.append("<input value='OK' type='submit'>");
         buffer.append("</td>");
         buffer.append("</tr>");
         buffer.append("</table>");
         buffer.append("</form>");
         List<IFJFolder> foldersNotIn = folderService.findAllFoldersNotIn(user, interf);
         int foldersAmount = foldersNotIn.size();
         buffer.append("<div class='mnuprof' align='CENTER'><b>" + locale.getString("mess73") + "</b></div>");
         buffer.append("<form method='post' class='content' action='" + FJUrl.FOLDER_TOOLS + "?id=6&view=" + viewId + "'>");
         buffer.append("<table class='control'><tr class=heads>");
         // Заголовки таблицы
         // Имя папки
         buffer.append("<th class='internal'><div class=tbtext>" + locale.getString("mess74") + "</div></th>");
         // Птичка
         buffer.append("<th class='internal' width='20'></th>");
         buffer.append("</tr>");
         for (int folderIndex=0; folderIndex<foldersAmount; folderIndex++){ 
            IFJFolder folder = foldersNotIn.get(folderIndex);
            // Папка
            buffer.append("<tr>");
            buffer.append("<td class='internal'><div class=tbtext>");
            buffer.append(folder.getName());
            buffer.append("</div></td>");
            // Флажок.
            buffer.append("<td class='internal'>");
            buffer.append("<div align='center' class=tbtext>");
            buffer.append("<input type='checkbox' name='" + folderIndex + "' value='" + folder.getId() + "'>");
            buffer.append("</div>");
            buffer.append("</td>");       
            buffer.append("</tr>");
         }
         // Сервис: добавление в интерфейс 
         buffer.append("<tr>");
         buffer.append("<td colspan=2 class='internal' align='right'>"); 
         buffer.append("<span class=tbtextnread>" + locale.getString("mess69") + "&nbsp;&nbsp;</span>");
         buffer.append("<select size='1' name='ACT'>");
         buffer.append("<option selected value='add'><span class='mnuprof'>" + locale.getString("mess79") + "&nbsp;" + interf.getName() + "&nbsp;&nbsp;</span></option>");
         buffer.append("</select>&nbsp;");
         buffer.append("<input type='hidden' value='" + foldersAmount + "' name='NRW'>");
         // Прередаем нужные пераметры...
         buffer.append(fd_form_add(user));
         buffer.append("<input value='OK' type='submit'>");
         buffer.append("</td>");
         buffer.append("</tr>");
         buffer.append("</table>");
         buffer.append("</form>");  
         // Добавление новой папки
         buffer.append("<form method='post' class='content' action='" + FJUrl.NEW_FOLDER + "?id=6&view=" + viewId + "'>");
         buffer.append("<span class=tbtext>" + locale.getString("mess74") + ":&nbsp;</span>"); 
         buffer.append("<input type='text' size=50 name='FOLD'>");
         buffer.append("&nbsp;<input type='submit' value='" + locale.getString("mess75") + "' >");
         // Прередаем нужные пераметры...
         buffer.append(fd_form_add(user));
         buffer.append("</form>");  
      }
      return buffer;
   }

   private StringBuffer case7(LocaleString locale, IUser user, FolderService folderService) throws ConfigurationException, SQLException, IOException, InvalidKeyException{
      StringBuffer buffer = new StringBuffer();
      // Выбираем список папок
      List<IFJFolder> folders = folderService.getUserFolders(user);
      int foldersAmount = folders.size();
      buffer.append("<div class='mnuprof' align='CENTER'><b>" + locale.getString("mess73") + "</b></div>");
      buffer.append("<form method='post' class='content' action='" + FJUrl.FOLDER_TOOLS + "?id=7'>");
      buffer.append("<table class='control'><tr class=heads>");
      // Заголовки таблицы
      // Имя папки
      buffer.append("<th class='internal'><div class=tbtext>" + locale.getString("mess74") + "</div></th>");
      // Птичка
      buffer.append("<th class='internal' width='20'></th>");
      buffer.append("</tr>");
      for (int folderIndex = 0; folderIndex<foldersAmount; folderIndex++){ 
         IFJFolder folder = folders.get(folderIndex);
         // Папка
         buffer.append("<tr>");
         buffer.append("<td class='internal'><div class=tbtext>");
         buffer.append(folder.getName());
         buffer.append("</div></td>");
         // Флажок.
         buffer.append("<td class='internal'>");
         if (folder.getUser() != null){
            buffer.append("<div align='center' class=tbtext>");
            buffer.append("<input type='checkbox' name='" + folderIndex + "' value='" + folder.getId() + "'>");
            buffer.append("</div>");
         }
         buffer.append("</td>");       
         buffer.append("</tr>");
      }
      // Сервис (пока только удаление)
      buffer.append("<tr>");
      buffer.append("<td colspan=2 class='internal' align='right'>"); 
      buffer.append("<span class=tbtextnread>" + locale.getString("mess69") + "&nbsp;&nbsp;</span>");
      buffer.append("<select size='1' name='ACT'>");
      buffer.append("<option selected value='del'><span class='mnuprof'>" + locale.getString("mess70") + "&nbsp;&nbsp;</span></option>");
      buffer.append("</select>&nbsp;");
      buffer.append("<input type='hidden' value='" + foldersAmount + "' name='NRW'>");
      buffer.append(fd_form_add(user));
      buffer.append("<input value='OK' type='submit'>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</form>");  
      // Добавление новой папки
      buffer.append("<form method='post' class='content' action='" + FJUrl.NEW_FOLDER + "?id=7'>");
      buffer.append("<span class=tbtext>" + locale.getString("mess74") + ":&nbsp;</span>"); 
      buffer.append("<input type='text' size=50 name='FOLD'>");
      buffer.append("&nbsp;<input type='submit' value='" + locale.getString("mess75") + "' >");
      buffer.append(fd_form_add(user));
      buffer.append("</form>");  
      return buffer;
   }

   private StringBuffer case8(LocaleString locale, IUser user, SubscribeService subscribeService) throws InvalidKeyException, ConfigurationException, SQLException, IOException{
      StringBuffer buffer = new StringBuffer();
      // Выбираем список подписаных веток
      List<IFJSubscribe> subscribes = subscribeService.findAllSubscribes(user, new Integer(1));
      buffer.append("<div class='mnuprof' align='CENTER'><b>" + locale.getString("mess87") + "</b></div>");
      buffer.append("<form method='post' class='content' action='" + FJUrl.DELETE_SUBSCRIBES + "?id=8'>");
      buffer.append("<table class='control'><tr class=heads>");
      // Заголовки таблицы
      // Тема ветки
      buffer.append("<th class='internal'><div class=tbtext>" + locale.getString("mess59") + "</div></th>");
      // Птичка
      buffer.append("<th class='internal' width='20'></th>");
      buffer.append("</tr>");
      int subscribesAmount = subscribes.size();
      for (int subscribeIndex = 0; subscribeIndex < subscribesAmount; subscribeIndex++){
         IFJSubscribe subscribe = subscribes.get(subscribeIndex);
         // Ветка
         buffer.append("<tr>");
         buffer.append("<td class='internal'><div class=tbtext>");
         buffer.append("<a href='" + FJUrl.VIEW_THREAD + "?id=" + subscribe.getTitleId() + "&end=1#end'>" + Diletant.fd_head(HtmlChars.convertHtmlSymbols(removeSlashes(subscribe.getHead()))) + "</a>");
         buffer.append("</div></td>");
         // Флажок.
         buffer.append("<td class='internal'>");
         buffer.append("<div align='center' class=tbtext>");
         buffer.append("<input type='checkbox' name='" + subscribeIndex + "' value= '" + subscribe.getId() + "'>");
         buffer.append("</div>");
         buffer.append("</td>");       
         buffer.append("</tr>");
      }
      // Сервис (пока только отписка)
      buffer.append("<tr>");
      buffer.append("<td colspan=2 class='internal' align='right'>"); 
      buffer.append("<span class=tbtextnread>" + locale.getString("mess69") + "&nbsp;&nbsp;</span>");
      buffer.append("<select size='1' name='ACT'>");
      buffer.append("<option selected value='del'><span class='mnuprof'>" + locale.getString("mess88") + "&nbsp;&nbsp;</span></option>");
      buffer.append("</select>&nbsp;");
      buffer.append("<input type='hidden' value='" + subscribesAmount + "' name='NRW'>");
      buffer.append(fd_form_add(user));
      buffer.append("<input value='OK' type='submit'>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</form>");  
      return buffer;
   }

   private StringBuffer case9(LocaleString locale, IUser user)
         throws InvalidKeyException {
      StringBuffer buffer = new StringBuffer();
      if (user.getAvatar() != null && user.getAvatarApproved()) {
         buffer.append("<div class='mnuprof' align='CENTER'>");
         buffer.append("<b>");
         buffer.append(locale.getString("mess93"));
         buffer.append("</b>");
         buffer.append("</div>");
         buffer.append("<div>");
         buffer.append("<img border='0' src='" + user.getAvatar() + "'>");
         buffer.append("</div>");
         buffer.append("<br>");
         buffer.append("<div>");
         buffer.append(locale.getString("mess95"));
         buffer.append("</div>");
         buffer.append("<br>");
      } else {
         buffer.append("<div class='mnuprof' align='CENTER'>");
         buffer.append("<b>");
         buffer.append(locale.getString("mess92"));
         buffer.append("</b>");
         buffer.append("</div>");
         buffer.append("<br>");
         buffer.append("<div>");
         buffer.append(locale.getString("mess96"));
         buffer.append("</div>");
         buffer.append("<br>");
      }
      buffer.append("<form method='post' class='content' action='" + FJUrl.SET_AVATAR + "?id=9'>");
      buffer.append(locale.getString("mess97") + "&nbsp;");
      buffer.append("<input type=text size=100 name='avatar' value='" + HTMLEntities.htmlentities(user.getAvatar()) + "'>");
      buffer.append("<br>");
      buffer.append("<br>");
      if (user.getShowAvatar()) {
         buffer.append("<input type=checkbox checked  name='s_avatar'>");
         buffer.append("&nbsp;" + locale.getString("mess94"));
         buffer.append("<br>");
         buffer.append("<br>");
      } else {
         buffer.append("<input type=checkbox  name='s_avatar'>");
         buffer.append("&nbsp;" + locale.getString("mess94"));
         buffer.append("<br>");
         buffer.append("<br>");
      }
      buffer.append("<input type='submit' value='" + locale.getString("mess75") + "'>");
      buffer.append(fd_form_add(user));
      buffer.append("</form>");
      buffer.append("<form method='post' class='content' action='" + FJUrl.V_AVATAR + "?id=9'>");
      if (user.getAvatar() != null) {
         buffer.append("<input type=checkbox checked  name='v_avatar'>");
         buffer.append("&nbsp;" + locale.getString("mess98"));
         buffer.append("<br>");
         buffer.append("<br>");
      } else {
         buffer.append("<input type=checkbox  name='v_avatar'>");
         buffer.append("&nbsp;" + locale.getString("mess98"));
         buffer.append("<br>");
         buffer.append("<br>");
      }
      buffer.append("<input type='submit' value='" + locale.getString("mess85") + "'>");
      buffer.append(fd_form_add(user));
      buffer.append("</form>");
      return buffer;
   }

   private StringBuffer case10(LocaleString locale, IUser user) throws InvalidKeyException {
      StringBuffer buffer = new StringBuffer();
      buffer.append("<div class='mnuprof' align='CENTER'>");
      buffer.append("<b>" + locale.getString("mess105") + "</b>");
      buffer.append("</div>");
      buffer.append("<br>");
      buffer.append("<div>");
      buffer.append("<form method='post' class='content' action='" + FJUrl.SET_LOCATION + "?id=10'>");
      buffer.append(fd_form_add(user));
      buffer.append("<table>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append(locale.getString("mess106"));
      buffer.append("</td>");
      buffer.append("<td>");
      buffer.append("<select name='timezone'>");
      for (int localeInex = 1; localeInex < 31; localeInex++){
         buffer.append("<option value='" + localeInex + "'");
         if (user.getTimeZone().equals(localeInex)){
            buffer.append(" SELECTED ");
         }
         buffer.append(">");
         buffer.append(locale.getString("mess110[" + localeInex + "]"));
         buffer.append("</option>"); 
      } 
      buffer.append("</select>");     
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append(locale.getString("mess111"));
      buffer.append("</td>");
      buffer.append("<td>");
      buffer.append("<input size='25' maxlength='20' type='text' name='country' value='" + HTMLEntities.htmlentities(user.getCountry() != null ? user.getCountry() : "") + "'>");
      buffer.append("<input type='checkbox' name='scountry' ");
      if (!user.getShowCountry()){
         buffer.append(" CHECKED ");
      }
      buffer.append(">");
      buffer.append("&nbsp;" + locale.getString("mess113"));
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append(locale.getString("mess112"));
      buffer.append("</td>");
      buffer.append("<td>");
      buffer.append("<input size='25' maxlength='20' type='text' name='city' value='" + HTMLEntities.htmlentities(user.getCity() != null ? user.getCity() : "") + "'>");
      buffer.append("<input type='checkbox' name='scity' ");
      if (!user.getShowCity()){
         buffer.append(" CHECKED ");
      }
      buffer.append(">");
      buffer.append("&nbsp;" + locale.getString("mess113"));
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td colspan='2' align=right>");
      buffer.append("<br>");
      buffer.append("<input type='submit' value='" + locale.getString("mess85") + "'>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</form>");  
      buffer.append("</div>");
      return buffer;
   }

   /*подпись*/
   private StringBuffer case11(LocaleString locale, IUser user) throws InvalidKeyException {
      StringBuffer buffer = new StringBuffer();
      buffer.append("<div class='mnuprof' align='LEFT' style='padding: 10px 0 5px 0'>");
      buffer.append("<b>");
      buffer.append(locale.getString("mess138"));
      buffer.append("</b>");
      buffer.append("</div>");
      String $textArea = "";
      if (user.getFooter() != null && !"".equals(user.getFooter())){
         $textArea=HTMLEntities.htmlentities(user.getFooter());
      }
      buffer.append("<form method='post' name='footer' class='content' action='" + FJUrl.SET_FOOTER + "?id=11'>");
      buffer.append("<textarea name='foot' cols=50 rows=15 onkeyup='checkLength(this, 255);' onkeypress='this.onkeyup();' onChange='this.onkeyup();' onFocus='this.onkeyup();' onBlur='this.onkeyup();' onSelect='this.onkeyup();' onMouseOut='this.onkeyup();' onMouseMove='this.onkeyup();'>" + $textArea + "</textarea>");
      buffer.append("<div style='padding: 5px 0 0 0'>");
      buffer.append(fd_button(locale.getString("mess85"),"document.footer.submit();","foot_ok", "1"));
      buffer.append(fd_form_add(user));
      buffer.append("</div>");
      buffer.append("</form>");
      return buffer;
   }

   /*e-mail*/
   private StringBuffer eMail(LocaleString locale, IUser user) throws InvalidKeyException {
      StringBuffer buffer = new StringBuffer();
      buffer.append("<div class='mnuprof' align='LEFT' style='padding: 10px 0 5px 0'>");
      buffer.append("<b>");
      buffer.append(locale.getString("MSG_YUOR_EMAIL"));
      buffer.append("</b>");
      buffer.append("</div>");
      String eMail = "";
      if (user.getEmail() != null && !"".equals(user.getEmail().trim())){
         eMail=HTMLEntities.htmlentities(user.getEmail());
      }
      buffer.append("<form method='post' name='email_form' class='content' action='" + FJUrl.POST + "'>");
      buffer.append(fd_input("mail", eMail, "50", "1"));
      buffer.append("<div style='padding: 5px 0 0 0'>");
      buffer.append("<input type='hidden' name='command' value='" + Command.SET_EMAIL.getCommand() + "' />");
      buffer.append(fd_button(locale.getString("mess85"),"document.email_form.submit();", Command.SET_EMAIL.getCommand(), "1"));
      buffer.append(fd_form_add(user));
      buffer.append("</div>");
      buffer.append("</form>");
      return buffer;
   }
   
   /*язык интерфейса*/
   private StringBuffer language(LocaleString locale, IUser user) throws InvalidKeyException {
      StringBuffer buffer = new StringBuffer();
      buffer.append("<div class='mnuprof' align='LEFT' style='padding: 10px 0 5px 0'>");
      buffer.append("<b>");
      buffer.append(locale.getString("MSG_DEFAULT_LOCALE"));
      buffer.append("</b>");
      buffer.append("</div>");
      buffer.append("<form method='post' name='locale' class='content' action='" + FJUrl.POST + "'>");
      buffer.append("<select name='locale'>");
      for (Locale localeParameter: Locale.values()){
         buffer.append("<option value='" + localeParameter.getCode() + "'");
         if (user.getLanguge() == localeParameter){
            buffer.append(" SELECTED ");
         }
         buffer.append(">");
         buffer.append(locale.getString("MSG_LOCALE" + localeParameter.getCode()));
         buffer.append("</option>"); 
      } 
      buffer.append("</select>");     
      buffer.append("<div style='padding: 5px 0 0 0'>");
      buffer.append("<input type='hidden' name='command' value='" + Command.SET_LOCALE.getCommand() + "' />");
      buffer.append(fd_button(locale.getString("mess85"),"document.locale.submit();", Command.SET_LOCALE.getCommand(), "1"));
      buffer.append(fd_form_add(user));
      buffer.append("</div>");
      buffer.append("</form>");
      return buffer;
   }
   
}
