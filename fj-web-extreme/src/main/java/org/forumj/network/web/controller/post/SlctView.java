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

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.forumj.network.web.FJServletTools;
import org.forumj.network.web.FJUrl;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class SlctView{

   public void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws Exception {
      String viewParameter = request.getParameter("VIEW");
      HttpSession session = request.getSession();
      if (!isEmptyParameter(viewParameter)){
         session.setAttribute("view", Long.valueOf(viewParameter));
      }
      StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.INDEX);
      response.sendRedirect(url.toString());
   }
   private boolean isEmptyParameter(String parameter){
      return (parameter == null || "".equals(parameter));
   }
}
