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
package org.forumj.web.servlet.post;

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
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.DELETE_VIEW}, name = FJServletName.DELETE_VIEW)
public class DelView extends FJServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         StringBuffer buffer = new StringBuffer();
         HttpSession session = request.getSession();
         String actionParameter = request.getParameter("ACT");
         IUser user = (IUser) session.getAttribute("user");
         InterfaceService interfaceService = FJServiceHolder.getInterfaceService();
         if (user != null && !user.isBanned() && user.isLogined()){
            if (actionParameter != null && !"".equals(actionParameter)){
               String nrwParameter = request.getParameter("NRW");
               Integer nrw = Integer.valueOf(nrwParameter);
               if ("del".equalsIgnoreCase(actionParameter)){
                  for (int nrwIndex = 0; nrwIndex < nrw; nrwIndex++) {
                     String folderIdParameter = request.getParameter(String.valueOf(nrwIndex));
                     if (folderIdParameter != null){
                        Long folderId = Long.valueOf(folderIdParameter);
                        interfaceService.deleteInterface(folderId, user);
                     }
                  }
               }
            }
            //TODO Magic integer!
            buffer.append(successPostOut("0", "control.php?id=6"));
         }else{
            // Вошли незарегистрировавшись
            buffer.append(unRegisteredPostOut());
         }
         response.setContentType("text/html; charset=UTF-8");
         response.getWriter().write(buffer.toString());
      }catch (Exception e) {
         e.printStackTrace();
      }
   }
}
