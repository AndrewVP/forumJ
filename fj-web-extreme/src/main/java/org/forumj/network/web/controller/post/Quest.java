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
import static org.forumj.tool.FJServletTools.menu;
import static org.forumj.web.servlet.tool.FJServletTools.*;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.common.db.entity.*;
import org.forumj.common.db.service.*;
import org.forumj.common.exception.InvalidKeyException;
import org.forumj.common.tool.*;
import org.forumj.common.web.ThreadType;
import org.forumj.tool.LocaleString;

import com.tecnick.htmlutils.htmlentities.HTMLEntities;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class Quest{

   public void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      try {
         HttpSession session = request.getSession();
         LocaleString locale = (LocaleString) session.getAttribute("locale");
         IUser user = (IUser) session.getAttribute("user");
         if (user != null && !user.isBanned() && user.isLogined()){
            String body = request.getParameter("A2");
            String head = request.getParameter("T");
            String question = request.getParameter("Q");
            if (body != null && !"".equalsIgnoreCase(body.trim()) 
                  && head != null && !"".equalsIgnoreCase(head.trim())
                  && question != null && !"".equalsIgnoreCase(question.trim())){

               String answer1 = request.getParameter("P1");
               String answer2 = request.getParameter("P2");
               if (answer1 != null && !"".equalsIgnoreCase(answer1.trim()) 
                     && answer2 != null && !"".equalsIgnoreCase(answer2.trim())){
                  String command = request.getParameter("comand");
                  String ip = request.getRemoteAddr();
                  //TODO need to be implemented
                  String domen = ip;
                  boolean usersCanAddAnswers = request.getParameter("US") != null; 
                  Time threadTime = new Time(new Date().getTime());
                  ArrayList<IQuestNode> answers = new ArrayList<>();
                  QuestService questService = FJServiceHolder.getQuestService();
                  answers.add(questService.getQuestNodeObject(1, answer1, user.getId()));
                  answers.add(questService.getQuestNodeObject(2, answer2, user.getId()));
                  for (int answerIndex = 3;; answerIndex++){
                     String answer = request.getParameter("P" + answerIndex);
                     if (answer != null && !"".equalsIgnoreCase(answer.trim())){
                        answers.add(questService.getQuestNodeObject(answerIndex, answer, user.getId()));
                     }else{
                        break;
                     }
                  }
                  if (command != null && "view".equalsIgnoreCase(command)){
                     buffer.append(view(locale, head, question, user, threadTime.toString("dd.MM.yyyy HH:mm"), ip, domen, answers, usersCanAddAnswers, body, request, webapp, userURI));
                  }else{
                     PostService postService = FJServiceHolder.getPostService();
                     IFJPost post = postService.getPostObject();
                     post.setState(1);
                     post.setBody(body);
                     post.setAuth(user.getId());
                     post.setDomen(domen);
                     post.setIp(ip);
                     post.setNred(0);
                     post.setTitle(head);
                     post.setAuthor(user);
                     ThreadService treadService = FJServiceHolder.getThreadService();
                     IFJQuestionThread thread = treadService.getQuestionThreadObject();
                     thread.setAuthId(user.getId());
                     thread.setHead(head);
                     thread.setNick(user.getNick());
                     thread.setSnall(0);
                     thread.setSnid(0);
                     thread.setFolderId((long) 1);
                     thread.setPostsAmount(1);
                     thread.setType(ThreadType.valueOfInteger(usersCanAddAnswers ? 2 :1));
                     thread.setAnswers(answers);
                     thread.setQuestion(question);
                     treadService.create(thread, post);
                     StringBuilder exit = new StringBuilder("/").append(userURI).append("/").append(FJUrl.INDEX);
                     response.sendRedirect(exit.toString());
                  }
               }else{
                  // TODO validation - hve to be two answers
                  StringBuilder exit = new StringBuilder("/").append(userURI).append("/").append(FJUrl.NEW_THREAD);
                  response.sendRedirect(exit.toString());
               }
            }else{
               // TODO validation - empty body or head
               StringBuilder exit = new StringBuilder("/").append(userURI).append("/").append(FJUrl.NEW_QUESTION);
               response.sendRedirect(exit.toString());
            }
         }else{
            // session expired
            StringBuilder exit = new StringBuilder("/").append(userURI).append("/").append(FJUrl.INDEX);
            response.sendRedirect(exit.toString());
         }
      } catch (Throwable e) {
         buffer = new StringBuffer();
         buffer.append(errorOut(e));
         e.printStackTrace();
      }
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter writer = response.getWriter();
      String out = buffer.toString();
      writer.write(out);
   }

   private StringBuffer view(LocaleString locale, String head, String question, IUser user, String rgtime, String str_ip, String str_dom, List<IQuestNode> answers, boolean usersCanAddAnswers, String body, HttpServletRequest request, String webapp, String userURI) throws InvalidKeyException, IOException{
      StringBuffer buffer = new StringBuffer();
      buffer.append("<!doctype html public \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
      buffer.append("<html>");
      buffer.append("<head>");
      buffer.append("<meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
      /* Стили*/
      buffer.append(loadCSS("/css/style.css"));
      // Скрипты (смайлики)
      buffer.append(loadJavaScript("/js/smile_.js"));
      // Скрипты (автовставка тегов)
      buffer.append(loadJavaScript("/js/jstags.js"));
      /*Скрипты (добавление вариантов ответа)*/
      buffer.append(loadJavaScript("/js/jsnode.js"));
      // Скрипты (submit поста)
      buffer.append(quest_submit(locale));
      buffer.append("<link rel='icon' href='/favicon.ico' type='image/x-icon'>");
      buffer.append("<link rel='shortcut icon' href='/favicon.ico' type='image/x-icon'>");
      buffer.append("<title>" + fd_smiles(HtmlChars.convertHtmlSymbols(removeSlashes(head)), false) + "</title>");
      buffer.append("</head>");
      buffer.append("<body bgcolor='#EFEFEF'>");
      buffer.append("<table class='content'>");
      buffer.append("<tr class='heads'>");
      buffer.append("<td  class='internal'>");
      /*Тема*/
      buffer.append("<div class=nik>");
      buffer.append("<b>&nbsp;&nbsp;" + fd_smiles(HtmlChars.convertHtmlSymbols(removeSlashes(head)), false)+ "</b>");
      buffer.append("</div>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td class='matras'>");
      /*Ник*/
      buffer.append("<span class='tbtextnread'>");
      buffer.append(HtmlChars.convertHtmlSymbols(removeSlashes(user.getNick())));
      buffer.append("</span>&nbsp;•&nbsp;");

      /*Дата*/

      buffer.append("<img border='0' src='").append("/").append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/icon_minipost.gif'>&nbsp;");

      buffer.append("<span class='posthead'>" + rgtime + "</span>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td>");
      /* div для игнора*/
      buffer.append("<div>");
      /*Аватара*/
      buffer.append("<table width='100%'>");
      buffer.append("<tr>");
      buffer.append("<td valign=top class='matras' style='padding:10px;'>");
      buffer.append("<div>");
      if (user.getWantSeeAvatars() && user.getAvatarApproved() && user.getAvatar() != null && !user.getAvatar().trim().isEmpty() && user.getShowAvatar()){
         buffer.append("<a href='" + "/" + userURI + "/" + FJUrl.SETTINGS + "?id=9' rel='nofollow'><img border='0' src='").append("/").append(FJUrl.STATIC).append("/").append(user.getAvatar() + "?seed=" + (new Date()).getTime() + "'></a>");
      }else{
         buffer.append("<a href='" + "/" + userURI + "/" + FJUrl.SETTINGS + "?id=9' rel='nofollow'><img border='0' src='").append("/").append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/no_avatar.gif'></a>");
      }
      buffer.append("</div>");
      buffer.append("<span class='posthead'><u>" + locale.getString("mess111") + "</u></span><br>");
      if (!user.getShowCountry() || user.getCountry() == null || user.getCountry().isEmpty()){
         buffer.append("<span class='posthead'>" + locale.getString("mess114") + "</span><br>");
      }else{
         buffer.append("<span class='posthead'>" + user.getCountry() + "</span><br>");
      }
      buffer.append("<span class='posthead'><u>" + locale.getString("mess112") + "</u></span><br>");
      if (user.getShowCity() || user.getCity() == null || user.getCity().isEmpty()){
         buffer.append("<span class='posthead'>" + locale.getString("mess114") + "</span><br>");
      }else{
         buffer.append("<span class='posthead'>" + user.getCity() + "</span><br>");
      }
      buffer.append("</td>");
      buffer.append("<td valign='top' width='100%'>");
      buffer.append("<table width='100%'>");
      buffer.append("<tr>");
      buffer.append("<td>");
      /*Вопрос*/
      buffer.append("<p align='CENTER'>");
      buffer.append("<font size='4'>");
      buffer.append("<b>");
      buffer.append(fd_smiles(question.replace("\\", ""), false));
      buffer.append("</b>");
      buffer.append("</font>");
      buffer.append("</p>");
      buffer.append("<br>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td align='CENTER'>");
      buffer.append("<table>");
      buffer.append("<tr>");
      buffer.append("<td align='CENTER'>");
      buffer.append("<table class=content>");
      for (int i = 0; i < answers.size(); i++) {
         String answer = answers.get(i).getNode();
         String check="";
         if (i == 0) check=" CHECKED";
         buffer.append("<tr>");
         buffer.append("<td class=voice_right >");
         buffer.append("<input type='radio' name='ANSWER' value='" + (i + 1) + "'" + check + ">");
         buffer.append("</td>");
         buffer.append("<td class=voice_right nowrap align='left'>");
         buffer.append(fd_smiles(fd_href(answer.replace("\\", "")), false));
         buffer.append("</td>");
         buffer.append("</tr>");
      }
      buffer.append("</table>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td width=100% align='center'>");
      //TODO localize!
      if (usersCanAddAnswers){
         buffer.append("Голосующие могут добавлять свои варианты ответа");
      }else{
         buffer.append("Голосующие не могут добавлять свои варианты ответа");
      }
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td>");
      /* Выводим текст*/
      buffer.append("<p class='post'>" + fd_body(HtmlChars.convertHtmlSymbols(removeSlashes(body))) + "</p>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr><td class='matras' colspan=2></td></tr>");
      buffer.append("<tr><td class='matras'></td><td>");
      buffer.append("<p class=post>" + fd_body(HtmlChars.convertHtmlSymbols(removeSlashes(user.getFooter()))) + "</p>");
      buffer.append("</td></tr>");
      buffer.append("</table>");
      buffer.append("</div>");
      buffer.append("</td>");
      buffer.append("</tr>");
      menu(request, user, locale, false, webapp, userURI);
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<table>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<form method='post' name='post' action='" + FJUrl.ADD_QUESTION + "'>");
      buffer.append("<table>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<table>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append(locale.getString("mess59") + ":&nbsp");
      buffer.append("</td>");
      buffer.append("<td>");
      buffer.append(fd_input("T", HtmlChars.convertHtmlSymbols(removeSlashes(head)), "70", "1"));
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append(locale.getString("mess124") + ":&nbsp");
      buffer.append("</td>");
      buffer.append("<td>");
      buffer.append("<input type='text' class='mnuforumSm' name='Q' size='120' value='" + question.replace("\\", "") + "'>");
      buffer.append(fd_input("Q", HtmlChars.convertHtmlSymbols(removeSlashes(head)), "70", "1"));
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<table id=tbl_node>");
      for (int i = 0; i < answers.size(); i++) {
         String answer = answers.get(i).getNode();
         buffer.append("<tr>");
         buffer.append("<td>");
//         buffer.append("" + (i + 1) + ". <input type='text' class='mnuforumSm' value='" + answer + "' name='P" + (i + 1) +"' id='P"+ (i + 1) + "' size='100'>");
         buffer.append("" + (i + 1) + ". " + fd_input("P" + (i + 1), HtmlChars.convertHtmlSymbols(removeSlashes(answer)), "70", "1"));
         buffer.append("</td>");
         buffer.append("</tr>");
      }
      buffer.append("</table>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append(fd_button(locale.getString("mess126"),"add_node();","btn_add", "1"));
      buffer.append("<input type='hidden' id='kol' name='kol' value='" + answers.size() + "'>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<input type='checkbox' name='US' checked>");
      buffer.append(locale.getString("mess125"));
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      /*Смайлики заголовок*/
      buffer.append("<td width='100%'>");
      buffer.append("<table width='100%'>");
      buffer.append("<tr>");
      buffer.append("<td align='center'>");
      buffer.append("<p>");
      buffer.append(locale.getString("mess21") + ":");
      buffer.append("</p>");
      buffer.append("</td>");
      /*Приглашение*/
      buffer.append("<td align='CENTER'>");
      buffer.append("<p>");
      buffer.append(locale.getString("mess12"));
      buffer.append("</p>");
      buffer.append("</td>");
      buffer.append("</tr>");
      /*Пост*/
      buffer.append("<tr>");
      buffer.append("<td valign='TOP'>");
      /*Смайлики*/
      smiles_add(locale.getString("mess11"));
      buffer.append("</td>");
      buffer.append("<td align='CENTER' valign='top'>");
      /*Автотеги*/
      autotags_add();
      /*текстарий*/
      buffer.append("<p>");
      buffer.append("<textarea class='mnuforumSm' rows='20' id='ed1' name='A2' cols='55'>" + HTMLEntities.htmlentities(removeSlashes(body)) + "</textarea>");
      buffer.append("</p>");
      /*Кнопки*/
      buffer.append("<table>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append(fd_button(locale.getString("mess13"),"quest_submit('write');","B1", "1"));
      buffer.append("</td>");
      buffer.append("<td>");
      buffer.append(fd_button(locale.getString("mess63"),"quest_submit('view');","B3", "1"));
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      /*Прередаем нужные пераметры...*/
      fd_form_add(user);
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</form>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</body>");
      buffer.append("</html>");
      return buffer;
   }
}
