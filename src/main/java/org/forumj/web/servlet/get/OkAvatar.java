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

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.*;
import org.forumj.db.dao.UserDao;
import org.forumj.db.entity.User;
import org.forumj.web.servlet.FJServlet;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {FJUrl.OK_AVATAR}, name=FJServletName.OK_AVATAR)
public class OkAvatar extends FJServlet {

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      String userIdParameter = request.getParameter("qqnn");
      Long userId = Long.valueOf(userIdParameter);
      UserDao dao = new UserDao();
      try {
         User user = dao.loadUser(userId);
         buffer.append("<!doctype html public \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
         buffer.append("<html><head><title></title><meta http-equiv='content-type' content='text/html; charset=UTF-8'></head><body><table><tr><td>");
         buffer.append("<form action='s_avatar.php?qqnn=" + userIdParameter + "' method='post'>");
         buffer.append("<input size='100' type='text' name='avatar' value='" + user.getAvatar() + "'><br><br><br>");
         if (user.getS_avatar()){
            buffer.append("<input type=checkbox checked  name='s_avatar'>&nbsp;Показывать<br><br>");  
         } else {
            buffer.append("<input type=checkbox  name='s_avatar'>&nbsp;Показывать<br><br>");   
         }
         buffer.append("<input type=checkbox  name='ok_avatar'>&nbsp;Разрешить<br><br>");   
         buffer.append("<input type='password' name=pass>");
         buffer.append("<input type='submit'>");
         buffer.append("</form></td></tr></table></body></html>");
         response.getWriter().write(buffer.toString());
      } catch (ConfigurationException e) {
         e.printStackTrace();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
}
