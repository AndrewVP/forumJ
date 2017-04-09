package org.forumj.network.web.controller.post;

import static org.forumj.tool.Diletant.*;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.common.db.entity.*;
import org.forumj.common.db.service.*;

public class AddSubscribe{

   private static Random random = new Random(new Date().getTime());

   static int generateRandom() {
       return Math.abs(random.nextInt());
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws ServletException, IOException {
      try {
         HttpSession session = request.getSession();
         String threadIdParameter = request.getParameter("IDT");
         String pageParameter = request.getParameter("pg");
         IUser user = (IUser) session.getAttribute("user");
         if (user != null && !user.isBanned() && user.isLogined()){
            if (threadIdParameter != null && !"".equals(threadIdParameter)){
               Long threadId = Long.valueOf(threadIdParameter);
               int key = generateRandom();
               SubscribeService subscribeService = FJServiceHolder.getSubscribeService();
               for(; subscribeService.isKeyPresent(key); key = generateRandom());
               IFJSubscribe subscribe = subscribeService.getSubscribeObject();
               subscribe.setKey((long) key);
               subscribe.setUser(user);
               subscribe.setTitleId(threadId);
               subscribe.setStart(new Date());
               //TODO Magic integer!!!!!
               subscribe.setType(1);
               subscribeService.createSubscribe(subscribe);
               StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.VIEW_THREAD).append("?id=").append(threadIdParameter);
               if (pageParameter != null && !"".equals(pageParameter)){
                  url.append("&page=").append(pageParameter);
               }
               url.append("#subs");
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
