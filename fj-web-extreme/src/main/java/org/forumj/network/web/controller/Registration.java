/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.network.web.controller;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.forumj.common.db.entity.IUser;
import org.forumj.network.web.FJUrl;
import org.forumj.network.web.resources.ResourcesBuilder;
import org.forumj.network.web.resources.LocaleString;
import org.forumj.network.web.FJServletTools;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class Registration{

      public void doGet(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      try {
         FJServletTools.cache(response);
         HttpSession session = request.getSession();
         String idParameter = request.getParameter("id");
         idParameter = (idParameter == null || "".equalsIgnoreCase(idParameter.trim())) ? "1" : idParameter;
         int id = 0;
         if (idParameter != null){
            id = Integer.valueOf(idParameter);
         }
         LocaleString locale = (LocaleString) session.getAttribute("locale");
         IUser user = (IUser) session.getAttribute("user");
         if (!user.isLogined()){
            buffer.append("<!doctype html public \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
            buffer.append("<html>");
            buffer.append("<head>");
            buffer.append("<meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
            // Стили
            buffer.append(ResourcesBuilder.getStyleCSS(webapp));
            buffer.append("<title>");
            buffer.append("Мы приветсвуем новых собеседников! :)");
            buffer.append("</title>");
            buffer.append("</head>");
            buffer.append("<body bgcolor=#EFEFEF>");
            // Главная таблица
            buffer.append("<table border='0' style='{border-collapse: collapse;}' width='100%'>");
            // Таблица с лого и верхним баннером
            buffer.append(FJServletTools.logo(webapp));
            // Главные ссылки
            buffer.append(FJServletTools.menu(request, user, locale, false, webapp, userURI));
            // Форма регистрации
            buffer.append("<tr>");
            buffer.append("<td width='100%'>");
            buffer.append("<form  action='" + FJUrl.DO_REGISTRATION + "' method='post'>");
            buffer.append("<table>");
            buffer.append("<tr>");
            buffer.append("<td>");
            buffer.append("<p>");
            // Определяем, откуда мы сюда попали?
            switch (id){
            // Нажали на ссылку регистрации
            case 1:
               //echo("Зарегистрируйтесь, пожалуйста");
               break;
               // Попытались предложить тему незарегистрировавшись
            case 2:
               buffer.append("Предлагать темы для обсуждения могут только зарегистрированные посетители!");
               break;
               // Попытались ответить незарегистрировавшись
            case 3:
               buffer.append("Добавлять свое мнение могут только зарегистрированные посетители!");
               break;
            case 4:
               buffer.append("Зарегистрируйтесь, пожалуйста");
               break;
            case 5:
               if (session.getAttribute("nick") != null){
                  buffer.append("<b>Извините, но " + session.getAttribute("nick") + " уже зарегистрирован...</b>");
                  session.setAttribute("nick", null);
               }
               break;
            case 6:
               buffer.append("<b>Вы попали на могилу безНикого юзера, убитого при активном участии Нудного и Сель Ави :)</b>");
               // На всякий случай :)
               session.setAttribute("nick", null);
               break;
            case 7:
               buffer.append("Не совпадают пароли");
               break;
            case 8:
               buffer.append("Не совпадают E-mail");
               break;
            case 9:
               buffer.append("Не совпадают идентификаторы");
               break;
            case 10:
               buffer.append("Не используйте пустые пароли! :)");
               break;
            case 11:
               buffer.append("<b>Не введен E-mail!</b>");
               break;
            case 12:
               buffer.append("<b>Посетитель с таким почтовым ящиком уже зарегистрирован! :)</b>");
               break;
            }
            buffer.append("</p>");
            buffer.append("</td>");
            buffer.append("</tr>");
            // С любезностями закончили
            // Запрашиваем Ник
            buffer.append("<tr>");
            buffer.append("<td>");
            buffer.append("<table>");
            buffer.append("<tr>");
            buffer.append("<td>");
            buffer.append("Ник*");
            buffer.append("</td>");
            buffer.append("<td>");
            buffer.append("<input type='text' name='R1' size='20'>");
            buffer.append("</td>");
            buffer.append("</tr>");
            // Пароль
            buffer.append("<tr>");
            buffer.append("<td>");
            buffer.append("Пароль*");
            buffer.append("</td>");
            buffer.append("<td>");
            buffer.append("<input type=password name='R2' size='20'>");
            buffer.append("</td>");
            buffer.append("<td>");
            buffer.append("Повторите пароль*");
            buffer.append("</td>");
            buffer.append("<td>");
            buffer.append("<input type=password name='R22' size='20'>");
            buffer.append("</td>");
            buffer.append("</tr>");
            // Мыло
            buffer.append("<tr>");
            buffer.append("<td>");
            buffer.append("E-Mail*");
            buffer.append("</td>");
            buffer.append("<td>");
            buffer.append("<input type=text name='R33' size='20'>");
            buffer.append("</td>");
            buffer.append("<td>");
            buffer.append("Повторите E-Mail*");
            buffer.append("</td>");
            buffer.append("<td>");
            buffer.append("<input type=text name='R3' size='20'>");
            buffer.append("</td>");
            buffer.append("</tr>");
            // Идентификатор
            // Кнопки
            buffer.append("<tr>");
            buffer.append("<td>");
            buffer.append("<input type='submit' value='Отправить' name='B1'>");
            buffer.append("<input type='reset' value='Отменить' name='B2'>");
            buffer.append("</td>");
            buffer.append("</tr>");
            buffer.append("</table>");
            buffer.append("</td>");
            buffer.append("</tr>");
            buffer.append("</table>");
            buffer.append("</form>");
            buffer.append("</td>");
            buffer.append("</tr>");
            // Форма закончилась
            // Главные ссылки
            buffer.append(FJServletTools.menu(request, user, locale, false, webapp, userURI));
            buffer.append(FJServletTools.footer(webapp));
            buffer.append("</body>");
            buffer.append("</html>");
         }
      } catch (Throwable e) {
         buffer = new StringBuffer();
         buffer.append(FJServletTools.errorOut(e));
         e.printStackTrace();
      }
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter writer = response.getWriter();
      writer.write(buffer.toString());
   }

}
