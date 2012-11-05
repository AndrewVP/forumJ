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

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;
import org.forumj.web.servlet.FJServlet;

/**
 * перенос темы в корзину 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.MOVE_THREAD_TO_RECYCLE}, name=FJServletName.MOVE_THREAD_TO_RECYCLE)
public class DelOne extends FJServlet {

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      try {
         HttpSession session = request.getSession();
         String idThreadParameter = request.getParameter("id");
         String pageParameter = request.getParameter("page");
         String usrParameter = request.getParameter("usr");
         IUser user = (IUser) session.getAttribute("user");
         FolderService service = FJServiceHolder.getFolderService();
         if (user != null && !user.isBanned() && user.isLogined()){
            if (idThreadParameter != null && !"".equals(idThreadParameter)){
               Long idThread = Long.valueOf(idThreadParameter);
               if (user.isModerator() && usrParameter != null && usrParameter.equals("0")){
                  UserService userService = FJServiceHolder.getUserService();
                  user = userService.readUser(0l);
                  service.moveToRecyclebin(idThread, user);
               }else{
                  service.moveToRecyclebin(idThread, user);
               }
            }
            String urlQuery = "";
            if (pageParameter != null && !"".equals(pageParameter)){
               urlQuery += "?page=" + pageParameter;
            }
            buffer.append(successPostOut("0", FJUrl.INDEX + "" + urlQuery));
         }else{
            // Вошли незарегистрировавшись
            buffer.append(unRegisteredPostOut());
         }
      } catch (Throwable e) {
         buffer = new StringBuffer();
         buffer.append(errorOut(e));
         e.printStackTrace();
      }
      response.setContentType("text/html; charset=UTF-8");
      response.getWriter().write(buffer.toString());
   }
}
