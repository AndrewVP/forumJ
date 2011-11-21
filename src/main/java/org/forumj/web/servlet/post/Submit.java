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
import static org.forumj.web.servlet.tool.FJServletTools.*;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.*;
import org.forumj.db.dao.FJUserDao;
import org.forumj.db.entity.User;
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
      HttpSession session = request.getSession();
      // принимаем ник, пароль
      String $t1 = request.getParameter("T1");
      String $t2 = request.getParameter("T2");
      FJUserDao dao = new FJUserDao();
      try {
         User user = dao.read($t1, $t2, true);
         if(user != null) {
            session.setAttribute("user", user);
            Long $idu=user.getId();
            String $pass2=user.getPass2();
            // ставим куку
            try {
               setcookie(response, "idu", $idu.toString(), 1209600, request.getContextPath(), request.getServerName());
               setcookie(response, "pass2", $pass2, 1209600, request.getContextPath(), request.getServerName());
            } catch (EncoderException e) {
               e.printStackTrace();
            }
            // Возвращаем на форум
            String out = "<html><head><meta http-equiv='Refresh' content='0; url=index.php'></head><body></body></html>";
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(out);
         }else{
            // пароль не совпал
            response.sendRedirect(request.getContextPath() + "/auth.php?id=6");
         }      
      } catch (ConfigurationException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } catch (SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
   }

}
