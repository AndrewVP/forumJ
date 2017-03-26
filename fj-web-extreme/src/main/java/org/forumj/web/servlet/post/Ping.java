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

import org.forumj.common.FJServletName;
import org.forumj.common.FJUrl;
import org.forumj.common.db.service.CountService;
import org.forumj.common.db.service.FJServiceHolder;
import org.forumj.dbextreme.db.entity.User;
import org.forumj.web.servlet.FJServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.PING}, name = FJServletName.PING)
public class Ping extends FJServlet {
   
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws ServletException, IOException {
      User user = (User) request.getSession().getAttribute("user");
      StringBuffer result = new StringBuffer();
      String m_xbParameter = request.getParameter("idb");
      String idParameter = request.getParameter("id");
      String m_xtParameter = request.getParameter("idt");
      String idsParameter = request.getParameter("ids");
      CountService service = FJServiceHolder.getCountService();
         try {
            if(m_xbParameter != null && m_xtParameter != null && idsParameter != null){
                  long m_xb = service.getAddedPostsAmount(Long.valueOf(m_xbParameter), user.getId());
                  result.append("{\n\"posts\":\"");
                  result.append(m_xb);
                  result.append("\",\n");
                  long m_xt = service.getAddedThreadsAmount(Long.valueOf(m_xtParameter), user.getId());
                  result.append("\"threads\":\"");
                  result.append(m_xt);
                  result.append("\",\n");
                  result.append("\"ids\":[\n");
                  String[] threads = idsParameter.split(";");
                  for (int i = 0; i < threads.length; i++) {
                     String[] ids = threads[i].split(",");
                     if (ids.length > 1){
                        String threadId = ids[0];
                        String lastPostId = ids[1];
                        result.append("{\"id\":\"");
                        result.append(threadId);
                        result.append("\",");
                        result.append("\"amount\":\"");
                        result.append(service.getAddedPostsAmount(Long.valueOf(threadId), Long.valueOf(lastPostId), user.getId()));
                        result.append("\"},\n");
                     }
                  }
                  result.deleteCharAt(result.length() - 2);
                  result.append("]\n}");
            }else if (m_xbParameter != null && idParameter != null){ // indicator for thread
               long m_xb = service.getAddedPostsAmount(Long.valueOf(idParameter), Long.valueOf(m_xbParameter), user.getId());
               result.append("{\n\"posts\":\"");
               result.append(m_xb);
               result.append("\"}\n");
            }
            response.setContentType("text/json; charset=UTF-8");
            response.getWriter().write(result.toString());
         } catch (Throwable e) {
            e.printStackTrace();
         }
   }

}
