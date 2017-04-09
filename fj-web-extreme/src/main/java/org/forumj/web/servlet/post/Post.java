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
import org.forumj.common.web.*;
import org.forumj.network.web.Command;
import org.forumj.tool.LocaleString;
import org.forumj.web.servlet.FJServlet;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.POST}, name=FJServletName.POST)
public class Post extends FJServlet {

   public void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws ServletException, IOException {
      try {
         String commandParameter = request.getParameter("command");
         HttpSession session = request.getSession();
         IUser user = (IUser) session.getAttribute("user");
         if (user != null && !user.isBanned() && user.isLogined()){
            if (commandParameter != null && commandParameter.trim().length() > 0){
               StringBuilder url = new StringBuilder("/").append(userURI).append("/");
               switch (Command.valueOfString(commandParameter)) {
               case SET_LOCALE:
                  String localeParameter = request.getParameter("locale");
                  if (localeParameter != null && !localeParameter.trim().isEmpty()){
                     Locale localeName = Locale.valueOfInteger(Integer.valueOf(localeParameter));
                     user.setLanguge(localeName);
                     FJServiceHolder.getUserService().update(user);
                     session.setAttribute("locale", new LocaleString(localeName, "messages", localeName));
                  }
                  //TODO Magic integer!
                  url.append(FJUrl.SETTINGS).append("?id=12");
                  response.sendRedirect(url.toString());
                  break;
               case SET_EMAIL:
                  String emailParameter = request.getParameter("mail");
                  if (emailParameter != null && !emailParameter.trim().isEmpty()){
                     user.setEmail(emailParameter);
                     FJServiceHolder.getUserService().update(user);
                  }
                  //TODO Magic integer!
                  url.append(FJUrl.SETTINGS).append("?id=13");
                  response.sendRedirect(url.toString());
                  break;
               default:
                  break;
               }
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
