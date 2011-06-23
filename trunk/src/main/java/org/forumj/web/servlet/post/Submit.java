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
import static org.forumj.web.servlet.tool.FJServletTools.setcookie;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.forumj.db.dao.UserDao;
import org.forumj.db.entity.User;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebServlet(urlPatterns = {"/submit.php"}, name="submit")
public class Submit extends HttpServlet {

   private static final long serialVersionUID = -4733196229093348352L;

   /**
    * {@inheritDoc}
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      HttpSession session = request.getSession();
      // принимаем ник, пароль
      String $t1 = request.getParameter("T1");
      String $t2 = request.getParameter("T2");
      UserDao dao = new UserDao();
      User user = dao.loadUser($t1, $t2, true);
      if(user != null) {
         session.setAttribute("user", user);
         Long $idu=user.getId();
         String $pass2=user.getPass2();
         // ставим куку
         setcookie(response, "user", $t1, 1209600, "/forum", "www.diletant.com.ua");
         setcookie(response, "idu", $idu.toString(), 1209600, "/forum", "www.diletant.com.ua");
         setcookie(response, "pass2", $pass2, 1209600, "/forum", "www.diletant.com.ua");
         setcookie(response, "user", $t1, 1209600, "/forum", "diletant.com.ua");
         setcookie(response, "idu", $idu.toString(), 1209600, "/forum", "diletant.com.ua");
         setcookie(response, "pass2", $pass2, 1209600, "/forum", "diletant.com.ua");
         // Возвращаем на форум
         String out = "<html><head><meta http-equiv='Refresh' content='0; url=index.php'></head><body></body></html>";
         response.setContentType("text/html; charset=UTF-8");
         PrintWriter writer = response.getWriter();
         writer.write(out);
      }else{
         // пароль не совпал
         response.setHeader("Location", "auth.php?id=6");
      }      
   }

}
