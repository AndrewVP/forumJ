/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.web.servlet.get;

import static org.forumj.tool.Diletant.*;
import static org.forumj.tool.FJServletTools.*;
import static org.forumj.web.servlet.tool.FJServletTools.*;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.common.db.entity.IUser;
import org.forumj.exception.InvalidKeyException;
import org.forumj.tool.LocaleString;
import org.forumj.web.servlet.FJServlet;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.NEW_QUESTION}, name = FJServletName.NEW_QUESTION)
public class Opr extends FJServlet {

   /**
    * {@inheritDoc}
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      try {
         HttpSession session = request.getSession();
         cache(response);
         LocaleString locale = (LocaleString) session.getAttribute("locale");
         IUser user = (IUser) session.getAttribute("user");
         buffer.append("<!doctype html public \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
         buffer.append("<html>");
         buffer.append("<head>");
         buffer.append("<meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
         // Стили
         buffer.append(loadCSS("/css/style.css"));
         // Скрипты (смайлики)
         buffer.append(loadJavaScript("/js/smile_.js"));
         // Скрипты (автовставка тегов)
         buffer.append(loadJavaScript("/js/jstags.js"));
         /*Скрипты (добавление вариантов ответа)*/
         buffer.append(loadJavaScript("/js/jsnode.js"));
         // Скрипты (submit поста)
         buffer.append(quest_submit(locale));
         buffer.append("<link rel='icon' href='/favicon.ico' type='image/x-icon'>");
         buffer.append("<link rel='shortcut icon' href='/favicon.ico' type='image/x-icon'>");
         buffer.append("<title>");
         buffer.append(locale.getString("mess3"));
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
         buffer.append("<tr>");
         buffer.append("<td>");
         buffer.append("<table>");
         buffer.append("<tr>");
         buffer.append("<td>");
         buffer.append("<form method='POST' name='post' action='quest.php'>");
         buffer.append("<table>");
         buffer.append("<tr>");
         buffer.append("<td>");
         buffer.append("<table>");
         buffer.append("<tr>");
         buffer.append("<td>");
         buffer.append(locale.getString("mess59") + ":&nbsp");
         buffer.append("</td>");
         buffer.append("<td>");
         buffer.append("<input class='mnuforumSm' type='text' name='T' size='120' maxlength='120'>");
         buffer.append("</td>");
         buffer.append("</tr>");
         buffer.append("<tr>");
         buffer.append("<td>");
         buffer.append(locale.getString("mess124") + ":&nbsp");
         buffer.append("</td>");
         buffer.append("<td>");
         buffer.append("<input class='mnuforumSm' type='text' name='Q' size='120' maxlength='120'>");
         buffer.append("</td>");
         buffer.append("</tr>");
         buffer.append("</table>");
         buffer.append("</td>");
         buffer.append("</tr>");
         buffer.append("<tr>");
         buffer.append("<td>");
         buffer.append("<table id=tbl_node>");
         buffer.append("<tr>");
         buffer.append("<td>");
         buffer.append("1. <input class='mnuforumSm' type='text' id='P1' name='P1' size='100'>");
         buffer.append("</td>");
         buffer.append("</tr>");
         buffer.append("<tr>");
         buffer.append("<td>");
         buffer.append("2. <input class='mnuforumSm' type='text' id='P2' name='P2' size='100'>");
         buffer.append("</td>");
         buffer.append("</tr>");
         buffer.append("<tr>");
         buffer.append("<td>");
         buffer.append("3. <input class='mnuforumSm' type='text' id='P3' name='P3' size='100'>");
         buffer.append("</td>");
         buffer.append("</tr>");
         buffer.append("</table>");
         buffer.append("</td>");
         buffer.append("</tr>");
         buffer.append("<tr>");
         buffer.append("<td>");
         buffer.append(fd_button(locale.getString("mess126"),"add_node();","btn_add", "1"));
         buffer.append("<input type='hidden' id='kol' name='kol' value='3'>");
         buffer.append("</td>");
         buffer.append("</tr>");
         buffer.append("<tr>");
         buffer.append("<td>");
         buffer.append("<input type='checkbox' name='US' checked>");
         buffer.append(locale.getString("mess125"));
         buffer.append("</td>");
         buffer.append("</tr>");
         buffer.append("<tr>");
         /*Смайлики заголовок*/
         buffer.append("<td width='100%'>");
         buffer.append("<table width='100%'>");
         buffer.append("<tr>");
         buffer.append("<td align='center'>");
         buffer.append("<p>");
         buffer.append(locale.getString("mess21") + ":");
         buffer.append("</p>");
         buffer.append("</td>");
         /*Приглашение*/
         buffer.append("<td align='CENTER'>");
         buffer.append("<p>");
         buffer.append(locale.getString("mess12"));
         buffer.append("</p>");
         buffer.append("</td>");
         buffer.append("</tr>");
         /*Пост*/
         buffer.append("<tr>");
         buffer.append("<td valign='TOP'>");
         /*Смайлики*/
         buffer.append(smiles_add(locale.getString("mess11")));
         buffer.append("</td>");
         buffer.append("<td align='CENTER' valign='top'>");
         /*Автотеги*/
         buffer.append(autotags_add());
         /*текстарий*/
         buffer.append("<p>");
         buffer.append("<textarea class='mnuforumSm' rows='30' id='ed1' name='A2' cols='55'></textarea>");
         buffer.append("</p>");
         /*Кнопки*/
         buffer.append("<table>");
         buffer.append("<tr>");
         buffer.append("<td>");
         buffer.append(fd_button(locale.getString("mess13"),"quest_submit(\"write\");","B1", "1"));
         buffer.append("</td>");
         buffer.append("<td>");
         buffer.append(fd_button(locale.getString("mess63"),"quest_submit(\"view\");","B3", "1"));
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
         buffer.append(menu(request, user, locale, false));
         // Баннер внизу, счетчики и копирайт.
         buffer.append(footer(request));
         buffer.append("</table></td></tr></table></td></tr></table>");
         buffer.append("</body>");
         buffer.append("</html>");
      } catch (InvalidKeyException e) {
         e.printStackTrace();
      }
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter writer = response.getWriter();
      String out = buffer.toString();
      writer.write(out);
   }
}
