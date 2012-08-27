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

import static org.forumj.tool.FJServletTools.cache;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.FJServiceHolder;
import org.forumj.common.db.service.FolderService;
import org.forumj.common.db.service.IgnorService;
import org.forumj.common.db.service.InterfaceService;
import org.forumj.common.db.service.MailService;
import org.forumj.common.db.service.SubscribeService;
import org.forumj.tool.LocaleString;
import org.forumj.web.servlet.FJServlet;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/settings"}, name = "settings")
public class Settings extends FJServlet {

   /**
    * {@inheritDoc}
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
         generateLangLinks(request);
         String ru = getRus();
         String ua = getUkr();
         String exit = getExitUrl();
         request.setAttribute("ua", ua);
         request.setAttribute("ru", ru);
         request.setAttribute("exit", exit);
         request.setAttribute("id", id);
      } catch (Throwable e) {
         e.printStackTrace();
      }
      response.setContentType("text/html; charset=UTF-8");
      RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/admin.jsp");
      dispatcher.include(request, response);
   }

}
