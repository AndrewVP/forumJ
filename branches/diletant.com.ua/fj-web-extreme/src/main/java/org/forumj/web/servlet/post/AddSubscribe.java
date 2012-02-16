package org.forumj.web.servlet.post;

import static org.forumj.tool.Diletant.*;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.common.db.entity.*;
import org.forumj.common.db.service.*;
import org.forumj.web.servlet.FJServlet;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.ADD_SUBSCRIBE}, name=FJServletName.ADD_SUBSCRIBE)
public class AddSubscribe extends FJServlet {

   private static Random random = new Random(new Date().getTime());

   static int generateRandom() {
       return Math.abs(random.nextInt());
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
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
               String urlQuery = "?id=" + threadIdParameter;
               if (pageParameter != null && !"".equals(pageParameter)){
                  urlQuery += "&page=" + pageParameter;
               }
               urlQuery += "#subs";
               buffer.append(successPostOut("0", "tema.php" + urlQuery));
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
