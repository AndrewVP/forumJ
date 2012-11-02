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
import org.forumj.common.config.FJConfiguration;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;
import org.forumj.email.FJEMail;
import org.forumj.web.servlet.FJServlet;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.SET_AVATAR}, name=FJServletName.SET_AVATAR)
public class SetAvatar extends FJServlet {

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      try {
         String avatarParameter = request.getParameter("avatar");
         String sAvatarParameter = request.getParameter("s_avatar");
         boolean sAvatar = sAvatarParameter != null; 
         HttpSession session = request.getSession();
         IUser user = (IUser) session.getAttribute("user");
         if (user != null && !user.isBanned() && user.isLogined()){
            user.setAvatar(avatarParameter);
            user.setAvatarApproved(false);
            user.setShowAvatar(sAvatar);
            UserService userService = FJServiceHolder.getUserService();
            userService.update(user);
            // TODO NLS!
            String text="Изменена Аватара <a href='http://www.diletant.com.ua/forum/" + FJUrl.OK_AVATAR + "?qqnn=" + user.getId() + "'>" + user.getNick() + "</a>";
            String from = FJConfiguration.getConfig().getString("mail.from");
            String host = FJConfiguration.getConfig().getString("mail.smtp.host");
            String subject="Avatar changed";
            for (int toIndex = 0; toIndex < 1000; toIndex++) {
               String to = FJConfiguration.getConfig().getString("mail.admin.address." + toIndex);
               if (to != null){
                  FJEMail.sendMail(to, from, host, subject, text);
               }else{
                  break;
               }
               
            }
            //TODO Magic integer!
            buffer.append(successPostOut("0", FJUrl.SETTINGS + "?id=9"));
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
