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

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;
import org.forumj.common.web.Locale;
import org.forumj.email.FJEMail;
import org.forumj.tool.LocaleString;

public class ActivateUser{

   public void doGet(HttpServletRequest request, HttpServletResponse response, String userURI) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      try {
         HttpSession session = request.getSession();
         UserService userService = FJServiceHolder.getUserService();
         String userIdParameter = request.getParameter(HttpParameters.USER_ID);
         String codeParameter = request.getParameter(HttpParameters.ACTIVATE_EMAIL_CODE);
         if (userIdParameter != null && codeParameter != null){
            int activateCode = Integer.valueOf(codeParameter);
            if (activateCode != 0){
               IUser user = userService.read(Long.valueOf(userIdParameter), activateCode);
               if (user != null){
                  user.setIsActive(Boolean.TRUE);
                  user.setActivateCode(0);
                  userService.update(user);
                  FJEMail.sendApproveMail(user, new LocaleString(Locale.UA, "messages", Locale.UA));
                  //TODO Magic integers!
                  String url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.MESSAGE).append("?id=2").toString();
                  response.sendRedirect(url);
               }
            }
         }else{
            String url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.INDEX).toString();
            response.sendRedirect(url);
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
