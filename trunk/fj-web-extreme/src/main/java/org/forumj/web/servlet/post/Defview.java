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
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.*;
import org.forumj.common.db.service.*;
import org.forumj.db.entity.User;
import org.forumj.web.servlet.FJServlet;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.SET_DEFAULT_VIEW}, name = FJServletName.SET_DEFAULT_VIEW)
public class Defview extends FJServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      HttpSession session = request.getSession();
      User user = (User) session.getAttribute("user");
      String defaultViewParameter = request.getParameter("DVIEW");
      try {
         if (user != null && !user.isBanned() && user.isLogined()){
            if (defaultViewParameter != null && !"".equals(defaultViewParameter)){
               UserService userService = FJServiceHolder.getUserService();
               user.setView(Integer.valueOf(defaultViewParameter));
               userService.update(user);
            }
            buffer.append(successPostOut("0", "control.php?id=6"));
         }else{
            // Вошли незарегистрировавшись
            buffer.append(unRegisteredPostOut());
         }
         response.setContentType("text/html; charset=UTF-8");
         response.getWriter().write(buffer.toString());
      } catch (ConfigurationException e) {
         e.printStackTrace();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

}
