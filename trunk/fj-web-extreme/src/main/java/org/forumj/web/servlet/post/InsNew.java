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
import static org.forumj.web.servlet.tool.FJServletTools.*;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.common.config.FJConfiguration;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;
import org.forumj.web.servlet.FJServlet;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.DO_REGISTRATION}, name = FJServletName.DO_REGISTRATION)
public class InsNew extends FJServlet {

   private String[][] ruseng = new String[][]{
         {"а","a"},
         {"е","e"},
         {"и","u"},
         {"о","o"},
         {"у","y"},
         {"ц","u"},
         {"к","k"},
         {"г","r"},
         {"х","x"},
         {"п","n"},
         {"р","p"},
         {"с","c"},
         {"ь","b"},
         {"й","y"},
         {"і","i"},
//translit         
         {"ц","c"},
         {"у","u"},
         {"к","k"},
         {"е","e"},
         {"н","n"},
         {"г","g"},
         {"з","z"},
         {"з","z"},
         {"х","h"},
         {"в","v"},
         {"п","p"},
         {"р","r"},
         {"л","l"},
         {"д","d"},
         {"с","s"},
         {"м","m"},
         {"и","i"},
         {"т","t"},
         {"б","b"},
         {"б","b"},
   }; 
   
   private static Random random = new Random(new Date().getTime());

   static int generateRandom() {
       return Math.abs(random.nextInt());
   }
   
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         HttpSession session = request.getSession();
         String nickParameter = request.getParameter("R1");
         String pass1Parameter = request.getParameter("R2");
         String pass2Parameter = request.getParameter("R22");
         String email1Parameter = request.getParameter("R3");
         String email2Parameter = request.getParameter("R33");
         if (isEmptyParameter(nickParameter)){
            response.sendRedirect("reg.php?id=6");
         }else if (isEmptyParameter(pass1Parameter) || isEmptyParameter(pass2Parameter)){
            response.sendRedirect("reg.php?id=10");
         }else if (isEmptyParameter(email1Parameter) || isEmptyParameter(email2Parameter)){
            response.sendRedirect("reg.php?id=11");
         }else if (!email1Parameter.equals(email2Parameter)){
            response.sendRedirect("reg.php?id=8");
         }else if (!pass1Parameter.equals(pass2Parameter)){
            response.sendRedirect("reg.php?id=7");
         }else{
            List<List<String>> nicks = prepareNick(nickParameter);
            UserService userService = FJServiceHolder.getUserService();
            List<String> users = userService.check(nicks); 
            if (users.size() > 0){
               System.out.println();
               System.out.println("-------------------------------------------------------");
               System.out.println(new Date().toString() + ":Registration fail, nick: " + nickParameter);
               for (String nick : users) {
                  System.out.println(nick);
               }
               System.out.println("-------------------------------------------------------");
               System.out.println();
               session.setAttribute("nick", nickParameter);
               response.sendRedirect("reg.php?id=5");
            }else{
               IUser user = userService.readUserByMail(email1Parameter);
               if (user != null){
                  System.out.println();
                  System.out.println("-------------------------------------------------------");
                  System.out.println(new Date().toString() + ":Registration fail, mail: " + email1Parameter);
                  System.out.println("-------------------------------------------------------");
                  System.out.println();
                  response.sendRedirect("reg.php?id=12");
               }else{
                  user = userService.getUserObject();
                  List<String> list = nicks.get(0);
                  String nick = list.get(list.size() - 1);      
                  user.setNick(nick);
                  user.setEmail(email1Parameter);
                  user.setPass(pass1Parameter);
                  user.setPass2(String.valueOf(generateRandom()));
                  user.setPp(FJConfiguration.getConfig().getInt("fj.default.threadsOnPage"));
                  user.setPt(FJConfiguration.getConfig().getInt("fj.default.postsOnPage"));
                  user.setView(FJConfiguration.getConfig().getInt("fj.default.viewId"));
                  userService.create(user);
                  session.setAttribute("user", user);
                  // ставим куку
                  setcookie(response, "idu", user.getId().toString(), 1209600, request.getContextPath(), request.getServerName());
                  setcookie(response, "pass2", user.getPass2(), 1209600, request.getContextPath(), request.getServerName());
                  response.sendRedirect("index.php");
               }
            }
         }
      } catch (Throwable e) {
         e.printStackTrace();
         StringBuffer buffer = new StringBuffer();
         buffer.append(errorOut(e));
         response.setContentType("text/html; charset=UTF-8");
         response.getWriter().write(buffer.toString());
      }
   }
   
   private List<List<String>> prepareNick(String nick){
      List<List<String>> result = new ArrayList<List<String>>();
      nick = removeExtraSpaces(nick);
      checkTrolls(nick, result, 0, ruseng);
       List<String> list = result.get(0);
      list.add(nick);
      return result;
   }
   
   private String removeExtraSpaces(String string){
      String result = "";
      String[] parts = string.split(" ");
      for (int partIndex = 0; partIndex < parts.length; partIndex++) {
         String part = parts[partIndex];
         if (part != null && !"".equals(part)){
            result += " " + part;
         }
      }
      return result.trim();
   }

   private void checkTrolls(String nick, List<List<String>> lists, int alphaPosition, String[][] alphas){
      List<String> result;
      if (lists.size() == 0){
         result = new ArrayList<String>(100);
         lists.add(result);
      }else{
         result = lists.get(lists.size() - 1);
         if (result.size() > 90){
            result = new ArrayList<String>(100);
            lists.add(result);
         }
      }
      for (; alphaPosition < nick.length(); alphaPosition++){
         for (String[] pare : alphas) {
            if (nick.substring(alphaPosition, alphaPosition + 1).equalsIgnoreCase(pare[0])){
               String word = nick.substring(0, alphaPosition) + pare[1] + nick.substring(alphaPosition + 1);
               result.add(word);
               checkTrolls(word, lists, alphaPosition + 1, alphas);
            }
            if (nick.substring(alphaPosition, alphaPosition + 1).equalsIgnoreCase(pare[1])){
               String word = nick.substring(0, alphaPosition) + pare[0] + nick.substring(alphaPosition + 1);
               result.add(word);
               checkTrolls(word, lists, alphaPosition + 1, alphas);
            }
         } 
      }
   }

}
