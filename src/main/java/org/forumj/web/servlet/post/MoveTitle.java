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
import org.forumj.db.dao.*;
import org.forumj.db.entity.User;
import org.forumj.web.servlet.FJServlet;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.MOVE_TITLE}, name = FJServletName.MOVE_TITLE)
public class MoveTitle extends FJServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         StringBuffer buffer = new StringBuffer();
         HttpSession session = request.getSession();
         String newViewIdParameter = request.getParameter("VIEW");
         String pageParameter = request.getParameter("page");
         User user = (User) session.getAttribute("user");
         if (user != null && !user.isBanned() && user.isLogined()){
            if (!isEmptyParameter(newViewIdParameter)){
               Long newViewId = Long.valueOf(newViewIdParameter);
               FJFolderDao dao = new FJFolderDao();
               String nrwParameter = request.getParameter("NRW");
               Integer nrw = Integer.valueOf(nrwParameter);
               for (int nrwIndex = 0; nrwIndex < nrw; nrwIndex++) {
                  String folderIdParameter = request.getParameter(String.valueOf(nrwIndex));
                  if (folderIdParameter != null){
                     Long folderId = Long.valueOf(folderIdParameter);
                     dao.moveToFolder(folderId, newViewId, user);
                  }
               }
            }
            String page = "";
            if (!isEmptyParameter(pageParameter)){
               page = "?page=" + pageParameter;
            }
            buffer.append(successPostOut("0", "index.php" + page));
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
