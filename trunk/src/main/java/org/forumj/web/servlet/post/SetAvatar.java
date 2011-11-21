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
import org.forumj.db.dao.FJUserDao;
import org.forumj.db.entity.User;
import org.forumj.email.FJEMail;
import org.forumj.web.servlet.FJServlet;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.SET_AVATAR}, name=FJServletName.SET_AVATAR)
public class SetAvatar extends FJServlet {

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         StringBuffer buffer = new StringBuffer();
         String avatarParameter = request.getParameter("avatar");
         String sAvatarParameter = request.getParameter("s_avatar");
         boolean sAvatar = sAvatarParameter != null; 
         HttpSession session = request.getSession();
         User user = (User) session.getAttribute("user");
         FJUserDao dao = new FJUserDao();
         if (user != null && !user.isBanned() && user.isLogined()){
            user.setAvatar(avatarParameter);
            user.setAvatarApproved(false);
            user.setShowAvatar(sAvatar);
            dao.update(user);
            // TODO NLS!
            String text="Изменена Аватара <a href='http://www.diletant.com.ua/forum/ok_avatar.php?qqnn=" + user.getId() + "'>" + user.getNick() + "</a>";
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
            buffer.append(successPostOut("0", "control.php?id=9"));
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
