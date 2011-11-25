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
import org.forumj.db.dao.*;
import org.forumj.web.servlet.FJServlet;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.NEW_FOLDER}, name = FJServletName.NEW_FOLDER)
public class NewFolder extends FJServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         StringBuffer buffer = new StringBuffer();
         HttpSession session = request.getSession();
         String idParameter = request.getParameter("id");
         String viewIdParameter = request.getParameter("view");
         String folderNameParameter = request.getParameter("FOLD");
         IUser user = (IUser) session.getAttribute("user");
         if (user != null && !user.isBanned() && user.isLogined()){
            if (!isEmptyParameter(folderNameParameter)){
               FJFolderDao folderDao = new FJFolderDao();
               if (!folderDao.isExist(folderNameParameter, user)){
                  folderDao.create(folderNameParameter, user);
               }
            }
            String urlQuery = "";
            if (idParameter != null && !"".equals(idParameter)){
               urlQuery = "?id=" + idParameter;
            }
            if (viewIdParameter != null && !"".equals(viewIdParameter)){
               urlQuery += "&view=" + viewIdParameter;
            }
            buffer.append(successPostOut("0", "control.php" + urlQuery));
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
