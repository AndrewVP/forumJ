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
import org.forumj.common.db.entity.*;
import org.forumj.common.db.service.*;

public class CloseThread{

   public void doGet(HttpServletRequest request, HttpServletResponse response, String userURI) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      try {
         HttpSession session = request.getSession();
         String idThreadParameter = request.getParameter(HttpParameters.ID);
         String pageParameter = request.getParameter(HttpParameters.PAGE);
         String closeParameter = request.getParameter("close");
         ThreadService service = FJServiceHolder.getThreadService();
         IUser user = (IUser) session.getAttribute(HttpParameters.USER);
         if (user != null && !user.isBanned() && user.isLogined() 
               && closeParameter != null
                 //TODO Magic integer!!
               && ("0".equals(closeParameter) || "1".equals(closeParameter))
               && idThreadParameter != null && !"".equals(idThreadParameter)){
            Long idThread = Long.valueOf(idThreadParameter);
            boolean canClose = user.isModerator(); 
            if (!canClose){
               IFJThread thread = service.readThread(idThread);
               canClose = thread.getAuthId().equals(user.getId());
            }
            if (canClose){
               //TODO Magic integer!!
               service.close(idThread, closeParameter.equalsIgnoreCase("1"));
            }
            StringBuffer buffer1 = new StringBuffer("/").append(userURI).append("/").append(FJUrl.INDEX);
            if (pageParameter != null && !pageParameter.isEmpty()){
               buffer1.append("?").append(HttpParameters.PAGE).append("=").append(pageParameter);
            }
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
