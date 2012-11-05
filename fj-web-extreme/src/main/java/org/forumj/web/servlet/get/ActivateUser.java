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
import static org.forumj.web.servlet.tool.FJServletTools.*;

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
@WebServlet(urlPatterns = {"/" + FJUrl.ACTIVATE_USER}, name=FJServletName.ACTIVATE_USER)
public class ActivateUser extends FJServlet {

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      try {
         HttpSession session = request.getSession();
         UserService userService = FJServiceHolder.getUserService();
         String userIdParameter = request.getParameter("id");
         String codeParameter = request.getParameter("c");
         if (userIdParameter != null && codeParameter != null){
            int activateCode = Integer.valueOf(codeParameter);
            if (activateCode != 0){
               IUser user = userService.read(Long.valueOf(userIdParameter), activateCode);
               if (user != null){
                  user.setIsActive(Boolean.TRUE);
                  user.setActivateCode(0);
                  userService.update(user);
                  session.setAttribute("user", user);
                  // ставим куку
                  setcookie(response, "idu", user.getId().toString(), 1209600, request.getContextPath(), request.getServerName());
                  setcookie(response, "pass2", user.getPass2(), 1209600, request.getContextPath(), request.getServerName());
               }
            }
         }
         response.sendRedirect(FJUrl.INDEX);
      } catch (Throwable e) {
         buffer = new StringBuffer();
         buffer.append(errorOut(e));
         e.printStackTrace();
      }
      response.setContentType("text/html; charset=UTF-8");
      response.getWriter().write(buffer.toString());
   }
}
