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

import static org.forumj.tool.Diletant.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class UserVoice{
   
   //TODO Нет валидации параметра answerParameter - в случае пустого ничего не происходит

   public void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws ServletException, IOException {
      try {
         HttpSession session = request.getSession();
         String threadIdParameter = request.getParameter("IDT2");
         String anonymouslyParameter = request.getParameter("HD");
         String answerParameter = request.getParameter("P");
         IUser user = (IUser) session.getAttribute("user");
         if (user != null && !user.isBanned() && user.isLogined()){
            if (threadIdParameter != null && !"".equals(threadIdParameter)){
               if (answerParameter != null && !"".equals(answerParameter)){
                  QuestService questService = FJServiceHolder.getQuestService();
                  VoiceService voiceService = FJServiceHolder.getVoiceService();
                  Long threadId = Long.valueOf(threadIdParameter);
                  // TODO Magic integer!
                  int answerType = anonymouslyParameter == null ? 1 : 2; 
                  if (!voiceService.isUserVoted(threadId, user.getId())){
                     questService.addCustomAnswer(threadId, answerParameter, answerType, user);
                  }
               }
               StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.VIEW_THREAD).append("?id=").append(threadIdParameter);
               response.sendRedirect(url.toString());
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
