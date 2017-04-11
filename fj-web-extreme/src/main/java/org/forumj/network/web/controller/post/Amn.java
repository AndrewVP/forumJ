/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.network.web.controller.post;

import static org.forumj.tool.Diletant.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.common.db.entity.*;
import org.forumj.common.db.service.*;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class Amn{

   public void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws ServletException, IOException {
      try {
         HttpSession session = request.getSession();
         IUser user = (IUser) session.getAttribute("user");
         String ignorTypeParameter = request.getParameter("C1");
         String ignorIdParameter = request.getParameter("IDZ");
         String ignorYearParameter = request.getParameter("Y");
         String ignorMounthParameter = request.getParameter("MTH");
         String ignorDayParameter = request.getParameter("D");
         String ignorHourParameter = request.getParameter("H");
         String ignorMinuteParameter = request.getParameter("M");
         IgnorService service = FJServiceHolder.getIgnorService();
         if (user != null && !user.isBanned() && user.isLogined()){
            String strDate = ignorDayParameter+ "." + ignorMounthParameter + "." + ignorYearParameter + "." + ignorHourParameter + "." + ignorMinuteParameter;
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.HH.mm");
            Date newEndDate = format.parse(strDate);
            IIgnor ignor = service.getIgnorObject();
            ignor.setId(Long.valueOf(ignorIdParameter));
            ignor.setUserId(user.getId());
            ignor.setType(ignorTypeParameter == null ? 0 : 1);
            ignor.setEnd(newEndDate);
            service.updateIgnor(ignor);
            StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.SETTINGS).append("?id=1");
            response.sendRedirect(url.toString());
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
