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
import org.forumj.db.dao.*;
import org.forumj.db.entity.IUser;
import org.forumj.web.servlet.FJServlet;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.ADD_VOTE}, name=FJServletName.ADD_VOTE)
public class UserVoice extends FJServlet {
   
   //TODO Нет валидации параметра answerParameter - в случае пустого ничего не происходит

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         StringBuffer buffer = new StringBuffer();
         HttpSession session = request.getSession();
         String threadIdParameter = request.getParameter("IDT1");
         String anonymouslyParameter = request.getParameter("HD");
         String answerParameter = request.getParameter("P");
         IUser user = (IUser) session.getAttribute("user");
         if (user != null && !user.isBanned() && user.isLogined()){
            if (threadIdParameter != null && !"".equals(threadIdParameter)){
               if (answerParameter != null && !"".equals(answerParameter)){
                  FJQuestNodeDao questDao = new FJQuestNodeDao();
                  Long threadId = Long.valueOf(threadIdParameter);
                  FJVoiceDao voteDao = new FJVoiceDao();
                  // TODO Magic integer!
                  int answerType = anonymouslyParameter == null ? 1 : 2; 
                  if (!voteDao.isUserVoted(threadId, user)){
                     questDao.addCustomAnswer(threadId, answerParameter, answerType, user);
                  }
               }
               buffer.append(successPostOut("3", "tema.php?id=" + threadIdParameter));
            }
         }else{
            // Вошли незарегистрировавшись
            buffer.append(unRegisteredPostOut());
         }
         response.setContentType("text/html; charset=UTF-8");
         response.getWriter().write(buffer.toString());
      }catch (Exception e) {
         e.printStackTrace();
      }
   }


}
