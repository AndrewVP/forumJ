/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.web.servlet.post;

import static org.forumj.tool.Diletant.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.db.dao.FJSubscribeDao;
import org.forumj.db.entity.User;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebServlet(urlPatterns = {FJUrl.DELONE_SUBSCRIBE}, name=FJServletName.DELONE_SUBSCRIBE)
public class DelOneSubs extends HttpServlet {

   private static final long serialVersionUID = -8156876539710291711L;

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         StringBuffer buffer = new StringBuffer();
         HttpSession session = request.getSession();
         String threadIdParameter = request.getParameter("IDT");
         String pageParameter = request.getParameter("pg");
         User user = (User) session.getAttribute("user");
         if (user != null && !user.isBanned() && user.isLogined()){
            if (threadIdParameter != null && !"".equals(threadIdParameter)){
               Long subscribeId = Long.valueOf(threadIdParameter);
               FJSubscribeDao dao = new FJSubscribeDao();
               dao.delete(subscribeId, user);
               String urlQuery = "?id=" + threadIdParameter;
               if (pageParameter != null && !"".equals(pageParameter)){
                  urlQuery += "&page=" + pageParameter;
               }
               buffer.append(successPostOut("0", "tema.php" + urlQuery));
            }
         }else{
            // Вошли незарегистрировавшись
            buffer.append(unRegisteredPostOut());
         }
      }catch (Exception e) {
         e.printStackTrace();
      }
   }
}
