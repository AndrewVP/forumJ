package org.forumj.web.servlet.post;

import static org.forumj.tool.Diletant.*;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.db.dao.FJSubscribeDao;
import org.forumj.db.entity.*;
import org.forumj.web.servlet.FJServlet;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.ADD_SUBSCRIBE}, name=FJServletName.ADD_SUBSCRIBE)
public class AddSubscribe extends FJServlet {

   private static Random random = new Random(new Date().getTime());

   static int generateRandom() {
       return Math.abs(random.nextInt());
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         StringBuffer buffer = new StringBuffer();
         HttpSession session = request.getSession();
         String threadIdParameter = request.getParameter("IDT");
         String pageParameter = request.getParameter("pg");
         IUser user = (IUser) session.getAttribute("user");
         if (user != null && !user.isBanned() && user.isLogined()){
            if (threadIdParameter != null && !"".equals(threadIdParameter)){
               Long threadId = Long.valueOf(threadIdParameter);
               int key = generateRandom();
               FJSubscribeDao dao = new FJSubscribeDao();
               for(; dao.isKeyPresent(key); key = generateRandom());
               FJSubscribe subscribe = new FJSubscribe();
               subscribe.setKey((long) key);
               subscribe.setUser(user);
               subscribe.setTitleId(threadId);
               subscribe.setStart(new Date());
               //TODO Magic integer!!!!!
               subscribe.setType(1);
               dao.create(subscribe);
               String urlQuery = "?id=" + threadIdParameter;
               if (pageParameter != null && !"".equals(pageParameter)){
                  urlQuery += "&page=" + pageParameter;
               }
               urlQuery += "#subs";
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
