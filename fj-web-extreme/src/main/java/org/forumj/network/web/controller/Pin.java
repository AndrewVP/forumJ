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

public class Pin{

   public void doGet(HttpServletRequest request, HttpServletResponse response, String userURI) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      try {
         HttpSession session = request.getSession();
         String idThreadParameter = request.getParameter(HttpParameters.ID);
         String pinParameter = request.getParameter(HttpParameters.PIN);
         ThreadService service = FJServiceHolder.getThreadService();
         IUser user = (IUser) session.getAttribute("user");
         if (user != null && !user.isBanned() && user.isLogined() && user.isModerator()){
            if (idThreadParameter != null && !"".equals(idThreadParameter) && pinParameter != null && !"".equals(pinParameter)){
               Long idThread = Long.valueOf(idThreadParameter);
               Integer pinCode = Integer.valueOf(pinParameter);
               service.pin(idThread, org.forumj.common.web.Pin.valueOfInteger(pinCode));
            }
            StringBuffer buffer1 = new StringBuffer("/").append(userURI).append("/").append(FJUrl.INDEX);
            response.sendRedirect(buffer1.toString());
         }else{
            // unlogined
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
