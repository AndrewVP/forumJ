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

import org.forumj.common.FJUrl;
import org.forumj.common.HttpParameters;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.FJServiceHolder;
import org.forumj.common.db.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.forumj.tool.Diletant.errorOut;

public class Ban{

   public void doGet(HttpServletRequest request, HttpServletResponse response, String userURI) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      try {
         HttpSession session = request.getSession();
         IUser currentUser = (IUser) session.getAttribute("user");
         if (currentUser != null && currentUser.isModerator()){
         UserService userService = FJServiceHolder.getUserService();
            String userIdParameter = request.getParameter(HttpParameters.USER_ID);
            if (userIdParameter != null){
               IUser user = userService.readUser(Long.valueOf(userIdParameter));
               if (user != null){
                  if (user.isBanned()){
                     user.setBan(0);
                  }else {
                     user.setBan(1);
                  }
                  userService.update(user);
                  StringBuffer buffer1 = new StringBuffer("/").append(userURI).append("/").append(FJUrl.SETTINGS);
                  //TODO Magic integer!!
                  buffer1.append("?").append(HttpParameters.ID).append("=").append(14);
                  response.sendRedirect(buffer1.toString());
               }
            }
         }else {
            response.sendRedirect(new StringBuffer("/").append(userURI).append("/").append(FJUrl.INDEX).toString());
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
