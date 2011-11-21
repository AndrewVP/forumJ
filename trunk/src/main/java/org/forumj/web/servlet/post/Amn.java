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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.db.dao.FJIgnorDao;
import org.forumj.db.entity.*;
import org.forumj.web.servlet.FJServlet;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.UPDATE_IGNORING}, name = FJServletName.UPDATE_IGNORING)
public class Amn extends FJServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         StringBuffer buffer = new StringBuffer();
         HttpSession session = request.getSession();
         User user = (User) session.getAttribute("user");
         String ignorTypeParameter = request.getParameter("C1");
         String ignorIdParameter = request.getParameter("IDZ");
         String ignorYearParameter = request.getParameter("Y");
         String ignorMounthParameter = request.getParameter("MTH");
         String ignorDayParameter = request.getParameter("D");
         String ignorHourParameter = request.getParameter("H");
         String ignorMinuteParameter = request.getParameter("M");
         if (user != null && !user.isBanned() && user.isLogined()){
            String strDate = ignorDayParameter+ "." + ignorMounthParameter + "." + ignorYearParameter + "." + ignorHourParameter + "." + ignorMinuteParameter;
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.HH.mm");
            Date newEndDate = format.parse(strDate);
            FJIgnorDao dao = new FJIgnorDao();
            Ignor ignor = new Ignor();
            ignor.setId(Long.valueOf(ignorIdParameter));
            ignor.setUserId(user.getId());
            ignor.setType(Integer.valueOf(ignorTypeParameter));
            ignor.setEnd(newEndDate);
            dao.update(ignor);
            buffer.append(successPostOut("0", "control.php?id=1"));
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
