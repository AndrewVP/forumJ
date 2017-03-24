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
import org.forumj.common.db.service.*;
import org.forumj.web.servlet.FJServlet;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.DELETE_ONE_SUBSCRIBE_BY_EMAIL}, name=FJServletName.DELETE_ONE_SUBSCRIBE_BY_EMAIL)
public class DelOneSubsByMail extends FJServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      try {
         String keyParameter = request.getParameter("id");
         if (keyParameter != null && !"".equals(keyParameter)){
            SubscribeService subscribeService = FJServiceHolder.getSubscribeService();
            Long key = Long.valueOf(keyParameter);
            subscribeService.deleteSubscribeByKey(key);
            buffer.append(successPostOut("0", FJUrl.INDEX));
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
