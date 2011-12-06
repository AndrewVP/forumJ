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
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.common.config.FJConfiguration;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;
import org.forumj.db.entity.User;
import org.forumj.web.servlet.FJServlet;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.DO_REGISTRATION}, name = FJServletName.DO_REGISTRATION)
public class InsNew extends FJServlet {

   private static Random random = new Random(new Date().getTime());

   static int generateRandom() {
       return Math.abs(random.nextInt());
   }
   
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         HttpSession session = request.getSession();
         String nickParameter = request.getParameter("R1");
         String pass1Parameter = request.getParameter("R2");
         String pass2Parameter = request.getParameter("R22");
         String email1Parameter = request.getParameter("R3");
         String email2Parameter = request.getParameter("R33");
         if (isEmptyParameter(nickParameter)){
            response.sendRedirect("reg.php?id=6");
         }else if (isEmptyParameter(pass1Parameter) || isEmptyParameter(pass2Parameter)){
            response.sendRedirect("reg.php?id=10");
         }else if (isEmptyParameter(email1Parameter) || isEmptyParameter(email2Parameter)){
            response.sendRedirect("reg.php?id=11");
         }else if (!email1Parameter.equals(email2Parameter)){
            response.sendRedirect("reg.php?id=8");
         }else if (!pass1Parameter.equals(pass2Parameter)){
            response.sendRedirect("reg.php?id=7");
         }else{
            String nick = prepareNick(nickParameter);
            UserService userService = FJServiceHolder.getUserService();
            IUser user = userService.readUser(nick);
            if (user != null){
               session.setAttribute("nick", nick);
               response.sendRedirect("reg.php?id=5");
            }else{
               user = userService.readUserByMail(email1Parameter);
               if (user != null){
                  response.sendRedirect("reg.php?id=12");
               }else{
                  user = new User();
                  user.setNick(nick);
                  user.setEmail(email1Parameter);
                  user.setPass(pass1Parameter);
                  user.setPass2(String.valueOf(generateRandom()));
                  user.setPp(FJConfiguration.getConfig().getInt("fj.default.threadsOnPage"));
                  user.setPt(FJConfiguration.getConfig().getInt("fj.default.postsOnPage"));
                  user.setView(FJConfiguration.getConfig().getInt("fj.default.viewId"));
                  userService.createUser(user);
                  session.setAttribute("user", user);
                  response.sendRedirect("index.php");
               }
            }
         }
      }catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   private String prepareNick(String nick){
      nick = removeExtraSpaces(nick);
      return nick;
   }
   
   private String removeExtraSpaces(String string){
      String result = "";
      String[] parts = string.split(" ");
      for (int partIndex = 0; partIndex < parts.length; partIndex++) {
         String part = parts[partIndex];
         if (part != null && !"".equals(part)){
            result += " " + part;
         }
      }
      return result.trim();
   }


}
