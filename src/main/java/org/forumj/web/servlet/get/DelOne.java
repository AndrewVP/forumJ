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
import org.forumj.db.dao.*;
import org.forumj.db.entity.User;

/**
 * перенос темы в корзину 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebServlet(urlPatterns = {FJUrl.DELONE}, name=FJServletName.DELONE)
public class DelOne extends HttpServlet {

   private static final long serialVersionUID = 3767489881801866697L;

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         StringBuffer buffer = new StringBuffer();
         HttpSession session = request.getSession();
         String idParameter = request.getParameter("id");
         String pageParameter = request.getParameter("page");
         User user = (User) session.getAttribute("user");
         FJFolderDao folderDao = new FJFolderDao();
         if (user != null && !user.isBanned() && user.isLogined()){
            if (idParameter != null && !"".equals(idParameter)){
               Long id = Long.valueOf(idParameter);
               folderDao.moveToRecyclebin(id, user);
            }
            String urlQuery = "";
            if (pageParameter != null && !"".equals(pageParameter)){
               urlQuery += "?page=" + pageParameter;
            }
            buffer.append(successPostOut("0", "index.php" + urlQuery));
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
