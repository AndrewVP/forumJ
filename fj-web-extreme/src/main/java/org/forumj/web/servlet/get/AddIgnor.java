/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.web.servlet.get;

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
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.ADD_IGNOR}, name=FJServletName.ADD_IGNOR)
public class AddIgnor extends FJServlet {

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      try {
         HttpSession session = request.getSession();
         String threadIdParameter = request.getParameter("idt");
         String pageParameter = request.getParameter("pg");
         String postIdParameter = request.getParameter("idp");
         String ignoredUserIdParameter = request.getParameter("idi");
         IUser user = (IUser) session.getAttribute("user");
         IgnorService service = FJServiceHolder.getIgnorService();
         if (user != null && !user.isBanned() && user.isLogined()){
            if (!isEmptyParameter(threadIdParameter)
                  && !isEmptyParameter(postIdParameter)
                  && !isEmptyParameter(ignoredUserIdParameter)){
               Long ignoredUserId = Long.valueOf(ignoredUserIdParameter);
               service.createIgnor(ignoredUserId, user);
               String add = "";
               if (pageParameter != null){
                  add = "&page=" + pageParameter;
               }
               buffer.append(successPostOut("0", "tema.php?id=" + threadIdParameter + add + "#" + postIdParameter));
            }
         }else{
            // Вошли незарегистрировавшись
            buffer.append(unRegisteredPostOut());
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
