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
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;
import org.forumj.web.servlet.FJServlet;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.ADD_VOTE}, name=FJServletName.ADD_VOTE)
public class UserVoice extends FJServlet {
   
   //TODO Нет валидации параметра answerParameter - в случае пустого ничего не происходит

   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
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
               buffer.append(successPostOut("0", FJUrl.VIEW_THREAD + "?id=" + threadIdParameter));
            }
         }else{
            // Вошли незарегистрировавшись
            buffer.append(unRegisteredPostOut());
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
