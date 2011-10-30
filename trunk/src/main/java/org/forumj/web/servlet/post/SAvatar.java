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

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.forumj.common.FJConfiguration;
import org.forumj.db.dao.UserDao;
import org.forumj.db.entity.User;
import org.forumj.web.servlet.FJServlet;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
public class SAvatar extends FJServlet {

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         StringBuffer buffer = new StringBuffer();
         String passParameter = request.getParameter("pass");
         String avatarParameter = request.getParameter("avatar");
         String userIdParameter = request.getParameter("qqnn");
         String okAvatarPass = FJConfiguration.getConfig().getString("okAvatar.password");
         if (!isEmptyParameter(passParameter) && passParameter.equalsIgnoreCase(okAvatarPass)){
            Long userId = Long.valueOf(userIdParameter);
            UserDao dao = new UserDao();
            User user = dao.loadUser(userId);
            user.setAvatar(avatarParameter);
            user.setOk_avatar(true);
            user.setS_avatar(true);
            dao.update(user);
            buffer.append("<!doctype html public \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
            buffer.append("<html><head><title></title><meta http-equiv='content-type' content='text/html; charset=UTF-8'></head><body>");
            buffer.append("Аватара изменена<br />");
            buffer.append("<img src='" + avatarParameter + "'>");
            buffer.append("</body></html>");
         }
      }catch (Exception e) {
         e.printStackTrace();
      }
   }
   
}
