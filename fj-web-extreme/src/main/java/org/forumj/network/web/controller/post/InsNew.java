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

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.*;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.forumj.checkip.CheckIp;
import org.forumj.common.config.FJConfiguration;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;
import org.forumj.network.web.FJEMail;
import org.forumj.network.web.FJUrl;
import org.forumj.network.web.resources.LocaleString;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class InsNew{

   private Logger logger = LogManager.getLogger(getClass().getCanonicalName());

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
//symbols         
         {".",","},
   }; 
   
   private static Random random = new Random(new Date().getTime());

   static int generateRandom() {
       return Math.abs(random.nextInt());
   }
   
//   @Override
//   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      response.sendRedirect(FJUrl.INDEX);
//   }
   public void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws Exception {
      String ip = request.getRemoteAddr();
      boolean spammer = ip != null && CheckIp.isSpammerIp(ip);
      HttpSession session = request.getSession();
      String nickParameter = request.getParameter("R1");
      String pass1Parameter = request.getParameter("R2");
      String pass2Parameter = request.getParameter("R22");
      String email1Parameter = request.getParameter("R3");
      String email2Parameter = request.getParameter("R33");
      if (spammer) {
         response.sendRedirect(FJUrl.INDEX);
      } else if (isEmptyParameter(nickParameter)) {
         StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.REGISTRATION).append("?id=6");
         response.sendRedirect(url.toString());
      } else if (isEmptyParameter(pass1Parameter) || isEmptyParameter(pass2Parameter)) {
         StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.REGISTRATION).append("?id=10");
         response.sendRedirect(url.toString());
      } else if (isEmptyParameter(email1Parameter) || isEmptyParameter(email2Parameter)) {
         StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.REGISTRATION).append("?id=11");
         response.sendRedirect(url.toString());
      } else if (!email1Parameter.equals(email2Parameter)) {
         StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.REGISTRATION).append("?id=8");
         response.sendRedirect(url.toString());
      } else if (!pass1Parameter.equals(pass2Parameter)) {
         StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.REGISTRATION).append("?id=7");
         response.sendRedirect(url.toString());
      } else {
         String nick = prepareNick(nickParameter.trim());
         UserService userService = FJServiceHolder.getUserService();
         NicksListHolder holder = new NicksListHolder(nick, userService);
         if (isDuplicate(nick, holder, 0, ruseng) || holder.checkIsDuplicate()) {
            session.setAttribute("nick", nickParameter);
            StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.REGISTRATION).append("?id=5");
            response.sendRedirect(url.toString());
         } else {
            email1Parameter = email1Parameter.trim();
            IUser user = userService.readUserByMail(email1Parameter);
            if (user != null) {
               logger.warn("-------------------------------------------------------");
               logger.warn("Registration fail, mail: " + email1Parameter + " , nick: " + nick);
               logger.warn("-------------------------------------------------------");
               StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.REGISTRATION).append("?id=12");
               response.sendRedirect(url.toString());
            } else {
               user = userService.getUserObject();
               user.setNick(nick);
               user.setEmail(email1Parameter);
               user.setPass(pass1Parameter);
               user.setPass2(String.valueOf(generateRandom()));
               user.setThreadsOnPage(FJConfiguration.getConfig().getInt("fj.default.threadsOnPage"));
               user.setPostsOnPage(FJConfiguration.getConfig().getInt("fj.default.postsOnPage"));
               user.setView(FJConfiguration.getConfig().getInt("fj.default.viewId"));
               user.setIsActive(Boolean.FALSE);
               int activateCode = generateRandom();
               while (userService.checkCodeUsed(activateCode)) {
                  activateCode = generateRandom();
               }
               user.setActivateCode(activateCode);
               userService.create(user);
               FJEMail.sendActivateMail(user, (LocaleString) session.getAttribute("locale"));
               //TODO Magic integer
               StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.MESSAGE).append("?id=1");
               response.sendRedirect(url.toString());
            }
         }
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
   private boolean isEmptyParameter(String parameter){
      return (parameter == null || "".equals(parameter));
   }

}
