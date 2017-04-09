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
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;
import org.forumj.web.servlet.FJServlet;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.DELETE_SUBSCRIBE}, name=FJServletName.DELETE_SUBSCRIBE)
public class DelOneSubs extends FJServlet {

   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws ServletException, IOException {
      try {
         HttpSession session = request.getSession();
         String threadIdParameter = request.getParameter("IDT");
         String pageParameter = request.getParameter("pg");
         IUser user = (IUser) session.getAttribute("user");
         if (user != null && !user.isBanned() && user.isLogined()){
            if (threadIdParameter != null && !"".equals(threadIdParameter)){
               SubscribeService subscribeService = FJServiceHolder.getSubscribeService();
               Long subscribeId = Long.valueOf(threadIdParameter);
               subscribeService.deleteSubscribeByTitleId(subscribeId, user);
               String urlQuery = "?id=" + threadIdParameter;
               if (pageParameter != null && !"".equals(pageParameter)){
                  urlQuery += "&page=" + pageParameter;
               }
               StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.VIEW_THREAD).append(urlQuery);
               response.sendRedirect(url.toString());
            }
         }else{
            // Session expired
            StringBuilder exit = new StringBuilder("/").append(userURI).append("/").append(FJUrl.INDEX);
            response.sendRedirect(exit.toString());
         }
      } catch (Throwable e) {
         e.printStackTrace();
         StringBuffer buffer = new StringBuffer();
         buffer.append(errorOut(e));
         response.setContentType("text/html; charset=UTF-8");
         response.getWriter().write(buffer.toString());
      }
   }
}
