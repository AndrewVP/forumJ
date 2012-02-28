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

import static org.forumj.tool.Diletant.errorOut;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.*;
import org.forumj.common.config.FJConfiguration;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;
import org.forumj.email.FJEMail;
import org.forumj.tool.LocaleString;
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
         {".",","},
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
            String nick = prepareNick(nickParameter);
            UserService userService = FJServiceHolder.getUserService();
            NicksListHolder holder = new NicksListHolder(nick, userService);
            if (isDuplicate(nick, holder, 0, ruseng) || holder.checkIsDuplicate()){
               session.setAttribute("nick", nickParameter);
               response.sendRedirect("reg.php?id=5");
            }else{
               IUser user = userService.readUserByMail(email1Parameter);
               if (user != null){
                  System.out.println();
                  System.out.println("-------------------------------------------------------");
                  System.out.println(new Date().toString() + ":Registration fail, mail: " + email1Parameter + " , nick: " + nick);
                  System.out.println("-------------------------------------------------------");
                  System.out.println();
                  response.sendRedirect("reg.php?id=12");
               }else{
                  user = userService.getUserObject();
                  user.setNick(nick);
                  user.setEmail(email1Parameter);
                  user.setPass(pass1Parameter);
                  user.setPass2(String.valueOf(generateRandom()));
                  user.setPp(FJConfiguration.getConfig().getInt("fj.default.threadsOnPage"));
                  user.setPt(FJConfiguration.getConfig().getInt("fj.default.postsOnPage"));
                  user.setView(FJConfiguration.getConfig().getInt("fj.default.viewId"));
                  user.setIsActive(Boolean.FALSE);
                  int activateCode = generateRandom();
                  while (userService.checkCodeUsed(activateCode)){
                     activateCode = generateRandom();
                  }
                  user.setActivateCode(activateCode);
                  userService.create(user);
                  FJEMail.sendActivateMail(user, (LocaleString) session.getAttribute("locale"));
                  response.sendRedirect(FJUrl.MESSAGE + "?id=1");
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
   
   private String prepareNick(String nick){
      nick = removeExtraSpaces(nick);
      return nick;
   }
   
   private boolean isDuplicate(String nick, NicksListHolder lists, int alphaPosition, String[][] alphas) throws ConfigurationException, SQLException, IOException{
      boolean result = false; 
      for (; alphaPosition < nick.length(); alphaPosition++){
         for (String[] pare : alphas) {
            if (nick.substring(alphaPosition, alphaPosition + 1).equalsIgnoreCase(pare[0])){
               String word = nick.substring(0, alphaPosition) + pare[1] + nick.substring(alphaPosition + 1);
               if (lists.addNick(word) || isDuplicate(word, lists, alphaPosition + 1, alphas)){
                  break;
               }
            }
            if (nick.substring(alphaPosition, alphaPosition + 1).equalsIgnoreCase(pare[1])){
               String word = nick.substring(0, alphaPosition) + pare[0] + nick.substring(alphaPosition + 1);
               if (lists.addNick(word) || isDuplicate(word, lists, alphaPosition + 1, alphas)){
                  break;
               }
            }
         }
         if (result){
            break;
         }
      }
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

   private class NicksListHolder{
      
      private String nick;
      
      private List<String> nickList;

      private UserService userService;
      
      public NicksListHolder(String nick, UserService userService){
         nickList = new ArrayList<String>(100);
         nickList.add(nick);
         this.nick = nick;
         this.userService = userService;
      }
      
      public boolean checkIsDuplicate() throws ConfigurationException, SQLException, IOException{
         List<String> users = userService.check(nickList);
         if (users.size() > 0){
            System.out.println();
            System.out.println("-------------------------------------------------------");
            System.out.println(new Date().toString() + ":Registration fail, nick: " + nick);
            for (String user : users) {
               System.out.println(user);
            }
            System.out.println("-------------------------------------------------------");
            System.out.println();
            return true;
         }else{
            nickList = new ArrayList<String>(100);
            return false;
         }
      }
      
      public boolean addNick(String nick) throws ConfigurationException, SQLException, IOException{
         boolean result = false;
         nickList.add(nick);
         if (nickList.size() > 99){
            result = checkIsDuplicate();
         }
         return result;
      }

   }

}
