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

import static org.forumj.tool.Diletant.errorOut;
import static org.forumj.tool.FJServletTools.*;
import static org.forumj.web.servlet.tool.FJServletTools.*;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.exception.InvalidKeyException;
import org.forumj.tool.LocaleString;
import org.forumj.web.servlet.FJServlet;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.LOGIN}, name = FJServletName.LOGIN)
public class Auth extends FJServlet {

   /**
    * {@inheritDoc}
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      try {
         cache(response);
         HttpSession session = request.getSession();
         String gid = request.getParameter("id");
         gid = (gid == null || "".equalsIgnoreCase(gid.trim())) ? "1" : gid;
         LocaleString locale = (LocaleString) session.getAttribute("locale");
         IUser user = (IUser) session.getAttribute("user");
         buffer.append("<!doctype html public \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
         buffer.append("<html>");
         buffer.append("<head>");
         buffer.append("<meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
         // Стили
         buffer.append(loadCSS("/css/style.css"));
         buffer.append("<title>");
         buffer.append("Авторизация");
         buffer.append("</title>");
         buffer.append("</head>");
         // Цвет фона страницы
         buffer.append("<body bgcolor=#EFEFEF>");
         // Главная таблица
         buffer.append("<table border='0' style='border-collapse: collapse' width='100%'>");
         // Таблица с лого и верхним баннером
         buffer.append(logo(request));
         // Главные ссылки
         buffer.append(menu(request, user, locale, false));
         // Форма авторизации
         buffer.append("<tr><td width='100%' align='center'><table width='100%'><tr><td>");
         buffer.append("<form  action='submit.php' method='POST'>");
         buffer.append("<table><tr><td><p>");
         // Определяем, откуда мы сюда попали?
         switch (Integer.valueOf(gid)){
            // Нажали на ссылку Вход
            case 1:
               buffer.append("Авторизуйтесь, пожалуйста");
               break;
            case 4:
               buffer.append("<b>Авторизуйтесь, пожалуйста</b>");;
               break;
               // Попытались предложить тему незарегистрировавшись
            case 2:
               buffer.append("Предлагать темы для обсуждения могут только зарегистрированные посетители!");
               break;
               // Попытались ответить незарегистрировавшись
            case 3:
               buffer.append("Добавлять свое мнение могут только зарегистрированные посетители!");
               break;
            case 5:
               buffer.append("<b>Пардон, мы таких не знаем! :)</b>");;
               break;
            case 6:
               buffer.append("<b>Вы не угадали пароль! :)</b>");;
               break;
            case 7:
               buffer.append("<b>В связи с усложнением системы идентификации участников форума прошу вас пройти процедуру дооформления :) необходимо добавить еще один идентификатор, который вы введете только ОДИН раз в, дальнейшем это не позволит хацкерам подделать вашу куку.</b>");;
               break;
            case 8:
               buffer.append("В связи с усложнением системы идентификации участников форума прошу вас пройти процедуру дооформления :) необходимо добавить еще один идентификатор, который вы введете только ОДИН раз в, дальнейшем это не позволит хацкерам подделать вашу куку.<br> <b>Идентификаторы не совпадают</b>");;
               break;
            case 9:
               buffer.append("Проводить опросы могут только зарегистрированные пользователи");
               break;
         }
         buffer.append("</p></td></tr>");
         // С любезностями закончили
         // Запрашиваем Ник
         buffer.append("<tr><td><table><tr><td>");
         buffer.append("Ник</td>");
         buffer.append("<td><input type='text' name='T1' size='20'></td>");
         buffer.append("</tr>");
         // Пароль
         buffer.append("<tr>");
         buffer.append("<td>Пароль</td>");
         buffer.append("<td><input type=password name='T2' size='20'>");
         buffer.append("</td>");
         buffer.append("</tr>");
         // Идентификатор
         if (gid.equals("7") || gid.equals("8")) {
            buffer.append("<tr>");
            buffer.append("<td>Идентификатор</td>");
            buffer.append("<td><input type=password name='T3' size='20'>");
            buffer.append("</td>");
            buffer.append("</tr>");
            session.setAttribute("xxxx", "1");
         }
         // Кнопки
         buffer.append("<tr>");
         buffer.append("<td>");
         buffer.append("<input type='submit' value='Отправить' name='B1'>");
         buffer.append("<input type='reset' value='Отменить' name='B2'>");
         buffer.append("</td></tr></table></td></tr></table></form></td></tr></table></td></tr>");
         // Форма закончилась
         // Главные ссылки
         buffer.append(menu(request, user, locale, false));
         buffer.append(footer(request));
         buffer.append("</body>");
         buffer.append("</html>");
      } catch (Throwable e) {
         buffer = new StringBuffer();
         buffer.append(errorOut(e));
         e.printStackTrace();
      }
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter writer = response.getWriter();
      writer.write(buffer.toString());
   }

}
