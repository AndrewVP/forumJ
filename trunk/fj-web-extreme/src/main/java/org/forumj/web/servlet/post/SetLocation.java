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
@WebServlet(urlPatterns = {"/" + FJUrl.SET_LOCATION}, name=FJServletName.SET_LOCATION)
public class SetLocation extends FJServlet {

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         StringBuffer buffer = new StringBuffer();
         boolean scity = request.getParameter("scity") != null;;
         boolean scountry = request.getParameter("scountry") != null;;
         String timezoneParameter = request.getParameter("timezone");
         String cityParameter = request.getParameter("city");
         String countryParameter = request.getParameter("country");
         int timeZone = Integer.valueOf(timezoneParameter); 
         HttpSession session = request.getSession();
         IUser user = (IUser) session.getAttribute("user");
         if (user != null && !user.isBanned() && user.isLogined()){
            user.setShowCity(scity);
            user.setShowCountry(scountry);
            user.setCity(cityParameter);
            user.setCountry(countryParameter);
            user.setTimeZone(timeZone);
            UserService userService = FJServiceHolder.getUserService();
            userService.update(user);
            //TODO Magic integer!
            buffer.append(successPostOut("0", "control.php?id=10"));
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
