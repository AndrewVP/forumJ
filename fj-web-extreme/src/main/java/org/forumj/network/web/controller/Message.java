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
package org.forumj.network.web.controller;

import static org.forumj.tool.Diletant.*;
import static org.forumj.tool.FJServletTools.*;
import static org.forumj.web.servlet.tool.FJServletTools.*;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.common.db.entity.IUser;
import org.forumj.tool.LocaleString;
import org.forumj.web.servlet.FJServlet;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class Message{

   public void doGet(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      try{
         boolean is404 = false;
         HttpSession session = request.getSession();
         String msgIdParameter = request.getParameter("id");
         IUser user = (IUser) session.getAttribute("user");
         LocaleString locale = (LocaleString) session.getAttribute("locale");
         String message = "";
         msgIdParameter = msgIdParameter == null || msgIdParameter.isEmpty() ? "0" : msgIdParameter;
         switch (msgIdParameter){
            case "1":
               message = locale.getString("MSG_ACTIVATE_MAIL_SENT");
               break;
            case "2":
               message = locale.getString("MSG_WILL_BE_APPROVED");
               break;
            case "0":
            default:
               message = "404";
               is404 = true;
         }
         cache(response);
         buffer.append("<!doctype html public \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
         buffer.append("<html>");
         buffer.append("<head>");
         buffer.append("<meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
         /*Стили*/
         buffer.append(loadCSS("/css/style.css"));
         buffer.append("<title>");
         buffer.append(locale.getString("MSG"));
         buffer.append("</title>");
         buffer.append("</head>");
         /*Цвет фона страницы*/
         buffer.append("<body bgcolor=#EFEFEF>");
         /*Главная таблица*/
         buffer.append("<table border='0' style='border-collapse: collapse' width='100%'>");
         /*Таблица с лого и верхним баннером*/
         buffer.append(logo(webapp));
         if (!is404){
            // Главные ссылки
            // Главное "меню"
            buffer.append(menu(request, user, locale, false, webapp, userURI));
         }
         // Сообщение
         buffer.append("<tr>");
         buffer.append("<td><div class='messageDiv'>");
         buffer.append(message);
         buffer.append("</div></td>");
         buffer.append("</tr>");
         if (!is404) {
            // Главное "меню"
            buffer.append(menu(request, user, locale, false, webapp, userURI));
            // Баннер внизу, счетчики и копирайт.
            buffer.append(footer(webapp));
         }
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

}
