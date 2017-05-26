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

import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.FJServiceHolder;
import org.forumj.common.db.service.UserService;
import org.forumj.network.web.FJUrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.forumj.network.web.FJServletTools.setcookie;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class Submit{

   public void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws Exception {
      HttpSession session = request.getSession();
      // принимаем ник, пароль
      String nickParameter = request.getParameter("T1");
      String passwordParameter = request.getParameter("T2");
      UserService userService = FJServiceHolder.getUserService();
      IUser user = userService.read(nickParameter, passwordParameter, true);
      if(user != null && user.getIsActive() && user.isApproved()) {
         session.setAttribute("user", user);
         Long userId = user.getId();
         String password2 = user.getPass2();
         session.setAttribute("view", null);
         // ставим куку
         String path = webapp.isEmpty() ? "/" : new StringBuilder("/").append(webapp).append("/").toString();
         setcookie(response, "idu", userId.toString(), 1209600, path, request.getServerName());
         setcookie(response, "pass2", password2, 1209600, path, request.getServerName());
         // Возвращаем на форум
         StringBuilder exit = new StringBuilder("/").append(userURI).append("/").append(FJUrl.INDEX);
         response.sendRedirect(exit.toString());
      }else if (user != null && (!user.getIsActive() || !user.isApproved())){
         // не активирован
         //TODO Magic integers!!
         StringBuilder exit = new StringBuilder("/").append(userURI).append("/").append(FJUrl.LOGIN).append("?id=10");
         response.sendRedirect(exit.toString());
      }else{
         // не угадал пароль
         StringBuilder exit = new StringBuilder("/").append(userURI).append("/").append(FJUrl.LOGIN).append("?id=6");
         response.sendRedirect(exit.toString());
      }
   }

}
