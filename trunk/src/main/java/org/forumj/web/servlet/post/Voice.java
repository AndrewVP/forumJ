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
import org.forumj.db.entity.User;
import org.forumj.web.servlet.FJServlet;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.VOICE}, name=FJServletName.VOICE)
public class Voice extends FJServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         StringBuffer buffer = new StringBuffer();
         HttpSession session = request.getSession();
         String threadIdParameter = request.getParameter("IDT1");
         String answerIdParameter = request.getParameter("ANSWER");
         User user = (User) session.getAttribute("user");
         if (user != null && !user.isBanned() && user.isLogined()){
            if (threadIdParameter != null && !"".equals(threadIdParameter)){
               FJVoiceDao voteDao = new FJVoiceDao();
               Long threadId = Long.valueOf(threadIdParameter);
               if (!voteDao.isUserVoted(threadId, user)){
                  Long answerId = Long.valueOf(answerIdParameter);
                  FJQuestNodeDao questDao = new FJQuestNodeDao();
                  questDao.addVote(threadId, answerId, user, null);
               }
               String urlQuery = "?id=" + threadIdParameter;
               buffer.append(successPostOut("3", "tema.php" + urlQuery));
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
