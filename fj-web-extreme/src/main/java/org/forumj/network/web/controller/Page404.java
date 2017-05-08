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

import org.forumj.network.web.FJUrl;
import org.forumj.network.web.resources.ResourcesBuilder;
import org.forumj.network.web.FJServletTools;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.forumj.network.web.FJServletTools.errorOut;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class Page404{

   public void doGet(HttpServletRequest request, HttpServletResponse response, String webapp) throws Exception {
      StringBuffer buffer = new StringBuffer();
      FJServletTools.cache(response);
      buffer.append("<!doctype html public \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
      buffer.append("<html>");
      buffer.append("<head>");
      buffer.append("<meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
      /*Стили*/
      buffer.append(ResourcesBuilder.getStyleCSS(webapp));
      buffer.append("<title>");
      buffer.append(404);
      buffer.append("</title>");
      buffer.append("</head>");
      /*Цвет фона страницы*/
      buffer.append("<body bgcolor=#EFEFEF>");
      /*Главная таблица*/
      buffer.append("<table border='0' style='border-collapse: collapse' width='100%'>");
      /*Таблица с лого и верхним баннером*/
      buffer.append(FJServletTools.logo(webapp));
      // Сообщение
      buffer.append("<tr>");
      buffer.append("<td><div class='messageDiv'>");
      buffer.append(404);
      buffer.append("<br/><br/>");
      buffer.append("<b><a href='/");
      buffer.append(webapp);
      buffer.append(webapp.isEmpty() ? "" : "/").append(FJUrl.DEFAULT_USER);
      buffer.append("/'>Вам сюда</a></b>");
      buffer.append("</div></td>");
      buffer.append("</tr>");
      buffer.append("</body>");
      buffer.append("</html>");
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter writer = response.getWriter();
      String out = buffer.toString();
      writer.write(out);
   }

}
