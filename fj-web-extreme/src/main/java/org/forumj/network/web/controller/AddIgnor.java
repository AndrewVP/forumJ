/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.network.web.controller;

import static org.forumj.tool.Diletant.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;

public class AddIgnor{

   public void doGet(HttpServletRequest request, HttpServletResponse response, String userURI) throws ServletException, IOException {
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
            if (threadIdParameter != null && !threadIdParameter.isEmpty()
                  && postIdParameter != null && !postIdParameter.isEmpty()
                  && ignoredUserIdParameter != null && !ignoredUserIdParameter.isEmpty()){
               Long ignoredUserId = Long.valueOf(ignoredUserIdParameter);
               service.createIgnor(ignoredUserId, user);
               String add = "";
               if (pageParameter != null){
                  add = "&amp;page=" + pageParameter;
               }
               String url = new StringBuilder("/").append(userURI).append("/")
                       .append(FJUrl.VIEW_THREAD).append("?id=").append(threadIdParameter).append(add)
                       .append("#").append(postIdParameter).toString();
               buffer.append(successPostOut("0", url));
            }
         }else{
            // Вошли незарегистрировавшись
            String url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.INDEX).toString();
            response.sendRedirect(url);
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
