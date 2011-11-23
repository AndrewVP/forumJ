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
import org.forumj.db.entity.IUser;
import org.forumj.web.servlet.FJServlet;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.DELETE_SUBSCRIBES}, name=FJServletName.DELETE_SUBSCRIBES)
public class DelSubs extends FJServlet {
   
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         StringBuffer buffer = new StringBuffer();
         HttpSession session = request.getSession();
         String actionParameter = request.getParameter("ACT");
         IUser user = (IUser) session.getAttribute("user");
         if (user != null && !user.isBanned() && user.isLogined()){
            if (actionParameter != null && !"".equals(actionParameter)){
               FJSubscribeDao dao = new FJSubscribeDao();
               String nrwParameter = request.getParameter("NRW");
               Integer nrw = Integer.valueOf(nrwParameter);
               if ("del".equalsIgnoreCase(actionParameter)){
                  for (int nrwIndex = 0; nrwIndex < nrw; nrwIndex++) {
                     String subscribeIdParameter = request.getParameter(String.valueOf(nrwIndex));
                     if (subscribeIdParameter != null){
                        Long subscribeId = Long.valueOf(subscribeIdParameter);
                        dao.deleteById(subscribeId, user);
                     }
                  }
               }
            }
            buffer.append(successPostOut("0", "control.php?id=8"));
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
