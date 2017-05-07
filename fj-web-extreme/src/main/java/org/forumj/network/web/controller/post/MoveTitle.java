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
package org.forumj.network.web.controller.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;
import org.forumj.network.web.FJServletTools;
import org.forumj.network.web.FJUrl;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class MoveTitle{

   public void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws ServletException, IOException {
      try {
         HttpSession session = request.getSession();
         String newViewIdParameter = request.getParameter("VIEW");
         String pageParameter = request.getParameter("page");
         IUser user = (IUser) session.getAttribute("user");
         if (user != null && !user.isBanned() && user.isLogined()){
            if (!isEmptyParameter(newViewIdParameter)){
               Long newViewId = Long.valueOf(newViewIdParameter);
               FolderService service = FJServiceHolder.getFolderService();
               String nrwParameter = request.getParameter("NRW");
               Integer nrw = Integer.valueOf(nrwParameter);
               for (int nrwIndex = 0; nrwIndex < nrw; nrwIndex++) {
                  String folderIdParameter = request.getParameter(String.valueOf(nrwIndex));
                  if (folderIdParameter != null){
                     Long folderId = Long.valueOf(folderIdParameter);
                     service.moveToFolder(folderId, newViewId, user);
                  }
               }
            }
            String page = "";
            if (!isEmptyParameter(pageParameter)){
               page = "?page=" + pageParameter;
            }
            StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.INDEX).append(page);
            response.sendRedirect(url.toString());
         }else{
            // Session expired
            StringBuilder exit = new StringBuilder("/").append(userURI).append("/").append(FJUrl.INDEX);
            response.sendRedirect(exit.toString());
         }
      } catch (Throwable e) {
         e.printStackTrace();
         StringBuffer buffer = new StringBuffer();
         buffer.append(FJServletTools.errorOut(e));
         response.setContentType("text/html; charset=UTF-8");
         response.getWriter().write(buffer.toString());
      }
   }
   private boolean isEmptyParameter(String parameter){
      return (parameter == null || "".equals(parameter));
   }

}
