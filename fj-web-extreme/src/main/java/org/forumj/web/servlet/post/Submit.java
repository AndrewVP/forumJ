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
import static org.forumj.web.servlet.tool.FJServletTools.setcookie;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;
import org.forumj.web.servlet.FJServlet;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.DO_LOGIN}, name = FJServletName.DO_LOGIN)
public class Submit extends FJServlet {

   /**
    * {@inheritDoc}
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         HttpSession session = request.getSession();
         // принимаем ник, пароль
         String nickParameter = request.getParameter("T1");
         String passwordParameter = request.getParameter("T2");
         UserService userService = FJServiceHolder.getUserService();
         IUser user = userService.read(nickParameter, passwordParameter, true);
         if(user != null && user.getIsActive()) {
            session.setAttribute("user", user);
            Long userId = user.getId();
            String password2 = user.getPass2();
            // ставим куку
            setcookie(response, "idu", userId.toString(), 1209600, request.getContextPath(), request.getServerName());
            setcookie(response, "pass2", password2, 1209600, request.getContextPath(), request.getServerName());
            // Возвращаем на форум
            String out = "<html><head><meta http-equiv='Refresh' content='0; url=index.php'></head><body></body></html>";
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(out);
         }else if (user != null && !user.getIsActive()){
            // не активирован
            response.sendRedirect(request.getContextPath() + "/auth.php?id=10");
         }else{
            // не угадал пароль
            response.sendRedirect(request.getContextPath() + "/auth.php?id=6");
         }      
      } catch (Throwable e) {
         e.printStackTrace();
         StringBuffer buffer = new StringBuffer();
         buffer.append(errorOut(e));
         response.setContentType("text/html; charset=UTF-8");
         PrintWriter writer = response.getWriter();
         String out = buffer.toString();
         writer.write(out);
      }
   }

}
