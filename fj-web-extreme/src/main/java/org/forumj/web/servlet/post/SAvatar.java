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

import static org.forumj.tool.Diletant.errorOut;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.common.config.FJConfiguration;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;
import org.forumj.web.servlet.FJServlet;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.S_AVATAR}, name=FJServletName.S_AVATAR)
public class SAvatar extends FJServlet {

   protected void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      try {
         String passParameter = request.getParameter("pass");
         String avatarParameter = request.getParameter("avatar");
         String userIdParameter = request.getParameter("qqnn");
         String okAvatarPass = FJConfiguration.getConfig().getString("okAvatar.password");
         if (!isEmptyParameter(passParameter) && passParameter.equalsIgnoreCase(okAvatarPass)){
            Long userId = Long.valueOf(userIdParameter);
            UserService userService = FJServiceHolder.getUserService();
            IUser user = userService.readUser(userId);
            user.setAvatar(avatarParameter);
            user.setAvatarApproved(true);
            user.setShowAvatar(true);
            userService.update(user);
            buffer.append("<!doctype html public \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
            buffer.append("<html><head><title></title><meta http-equiv='content-type' content='text/html; charset=UTF-8'></head><body>");
            buffer.append("Аватара изменена<br />");
            buffer.append("<img src='" + avatarParameter + "'>");
            buffer.append("</body></html>");
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
