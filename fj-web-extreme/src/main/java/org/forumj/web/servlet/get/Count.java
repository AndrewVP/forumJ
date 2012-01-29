/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.web.servlet.get;

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
@WebServlet(urlPatterns = {"/" + FJUrl.COUNT}, name = FJServletName.COUNT)
public class Count extends FJServlet {
   
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      StringBuffer result = new StringBuffer();
      String m_xbParameter = request.getParameter("idb");
      String m_xtParameter = request.getParameter("idt");
      String idsParameter = request.getParameter("ids");
      CountService service = FJServiceHolder.getCountService();
      try {
         long m_xb = service.getAddedPostsAmount(Long.valueOf(m_xbParameter));
         result.append(m_xb);
         result.append(";");
         long m_xt = service.getAddedThreadsAmount(Long.valueOf(m_xtParameter));
         result.append(m_xt);
         result.append(";");
         String[] threads = idsParameter.split(";");
         for (int i = 0; i < threads.length; i++) {
            String threadId = threads[i].split(",")[0];
            String lastPostId = threads[i].split(",")[1];
            if (i != 0){
               result.append("|");
            }
            result.append(threadId);
            result.append(",");
            result.append(service.getAddedPostsAmount(Long.valueOf(threadId), Long.valueOf(lastPostId)));
         }
         response.setContentType("text/html; charset=UTF-8");
         response.getWriter().write(result.toString());
      } catch (Throwable e) {
         e.printStackTrace();
      }
   }

}
