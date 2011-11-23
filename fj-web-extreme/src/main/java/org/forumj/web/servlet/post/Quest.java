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
import static org.forumj.tool.FJServletTools.*;
import static org.forumj.tool.PHP.*;
import static org.forumj.web.servlet.tool.FJServletTools.*;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.*;
import org.forumj.db.dao.FJThreadDao;
import org.forumj.db.entity.*;
import org.forumj.exception.*;
import org.forumj.tool.*;
import org.forumj.web.servlet.FJServlet;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.ADD_QUESTION}, name = FJServletName.ADD_QUESTION)
public class Quest extends FJServlet {

   /**
    * {@inheritDoc}
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      HttpSession session = request.getSession();
      LocaleString locale = (LocaleString) session.getAttribute("locale");
      IUser user = (IUser) session.getAttribute("user");
      try {
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
                  String domen = gethostbyaddr(ip);
                  boolean usersCanAddAnswers = request.getParameter("US") != null; 
                  Time threadTime = new Time(new Date().getTime());
                  ArrayList<QuestNode> answers = new ArrayList<QuestNode>();
                  answers.add(new QuestNode(1, answer1, user.getId()));
                  answers.add(new QuestNode(2, answer2, user.getId()));
                  for (int answerIndex = 3;; answerIndex++){
                     String answer = request.getParameter("P" + answerIndex);
                     if (answer != null && !"".equalsIgnoreCase(answer.trim())){
                        answers.add(new QuestNode(answerIndex, answer, user.getId()));
                     }else{
                        break;
                     }
                  }
                  if (command != null && "view".equalsIgnoreCase(command)){
                     buffer.append(view(locale, head, question, user, threadTime.toString("dd.MM.yyyy HH:mm"), ip, domen, answers, usersCanAddAnswers, body, request));
                  }else{
                     FJPost post = new FJPost();
                     FJPostBody postBody = new FJPostBody();
                     FJPostHead postHead = new FJPostHead();
                     post.setState(1);
                     post.setBody(postBody);
                     post.setHead(postHead);
                     postBody.setBody(body);
                     postHead.setAuth(user.getId());
                     postHead.setDomen(domen);
                     postHead.setIp(ip);
                     postHead.setNred(0);
                     postHead.setTitle(head);
                     FJQuestionThread thread = new FJQuestionThread();
                     thread.setAuthId(user.getId());
                     thread.setHead(head);
                     thread.setNick(user.getNick());
                     thread.setSnall(0);
                     thread.setSnid(0);
                     thread.setFolderId((long) 1);
                     thread.setPcount(1);
                     thread.setType(usersCanAddAnswers ? 2 :1);
                     thread.setAnswers(answers);
                     thread.setQuestion(question);
                     FJThreadDao threadDao = new FJThreadDao();
                     threadDao.create(thread, post);
                     buffer.append(successPostOut("3", "index.php"));
                  }
               }else{
                  // Пустая
                  buffer.append(twoAnswersReminedOut());
               }
            }else{
               // Пустая
               buffer.append(blankPostOut());
            }
         }else{
            // Вошли незарегистрировавшись
            buffer.append(unRegisteredPostOut());
         }
         response.setContentType("text/html; charset=UTF-8");
         PrintWriter writer = response.getWriter();
         String out = buffer.toString();
         writer.write(out);
      } catch (InvalidKeyException e) {
         e.printStackTrace();
      } catch (DBException e) {
         e.printStackTrace();
      } catch (ConfigurationException e) {
         e.printStackTrace();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   private StringBuffer view(LocaleString locale, String head, String question, IUser user, String $rgtime, String $str_ip, String $str_dom, List<QuestNode> answers, boolean usersCanAddAnswers, String body, HttpServletRequest request) throws InvalidKeyException, IOException{
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
      buffer.append("<title>" + head + "</title>");
      buffer.append("</head>");
      buffer.append("<body bgcolor='#EFEFEF'>");
      buffer.append("<table class='content'>");
      buffer.append("<tr class='heads'>");
      buffer.append("<td  class='internal'>");
      /*Тема*/
      buffer.append("<div class=nik>");
      buffer.append("<b>&nbsp;&nbsp;" + fd_smiles(stripslashes(head))+ "</b>");
      buffer.append("</div>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td class='matras'>");
      /*Ник*/
      buffer.append("<span class='tbtextnread'>");
      buffer.append(user.getNick());
      buffer.append("</span>&nbsp;•");

      /*Дата*/

      buffer.append("&nbsp;<img border='0' src='smiles/icon_minipost.gif'>&nbsp;");

      buffer.append("<span class='posthead'>" + $rgtime + "</span>&nbsp;•");

      /*Хост*/ 
      if ($str_ip.trim().equalsIgnoreCase($str_dom.trim())){
         $str_dom = substr($str_dom, 0, strrpos($str_dom, ".")+1) + "---";
      }else{
         $str_dom = "---" + substr($str_dom, strpos($str_dom, ".") + 1);
      }

      buffer.append("&nbsp;<span class='posthead'>");
      buffer.append($str_dom);
      buffer.append("</span>&nbsp;&nbsp;•");
      /*игнорировать*/

      buffer.append("<span class='posthead'>");
      buffer.append(locale.getString("mess68"));
      buffer.append("</span>");
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
      buffer.append("<img border='0' src='smiles/no_avatar.gif'>");
      buffer.append("</div>");
      buffer.append("</td>");
      buffer.append("<td valign='top' width='100%'>");
      buffer.append("<table width='100%'>");
      buffer.append("<tr>");
      buffer.append("<td>");
      /*Вопрос*/
      buffer.append("<p align='CENTER'>");
      buffer.append("<font size='4'>");
      buffer.append("<b>");
      buffer.append(fd_smiles(stripslashes(question)));
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
         String $check="";
         if (i == 0) $check=" CHECKED";
         buffer.append("<tr>");
         buffer.append("<td class=voice_right >");
         buffer.append("<input type='radio' name='ANSWER' value='" + (i + 1) + "'" + $check + ">");
         buffer.append("</td>");
         buffer.append("<td class=voice_right nowrap align='left'>");
         buffer.append(fd_smiles(fd_href(stripslashes(answer))));
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
      buffer.append("<p class='post'" + nl2br(fd_smiles(fd_bbcode(stripslashes(body))))+ "</p>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</div>");
      buffer.append("</td>");
      buffer.append("</tr>");
      menu(request, user, locale, false);
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<table>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<form method='POST' name='post' action='quest.php'>");
      buffer.append("<table>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<table>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append(locale.getString("mess59") + ":&nbsp");
      buffer.append("</td>");
      buffer.append("<td>");
      buffer.append("<input type='text' class='mnuforumSm' name='T' size='120' value='" + stripslashes(head)+ "'>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append(locale.getString("mess124") + ":&nbsp");
      buffer.append("</td>");
      buffer.append("<td>");
      buffer.append("<input type='text' class='mnuforumSm' name='Q' size='120' value='" + stripslashes(question) + "'>");
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
         buffer.append("" + (i + 1) + ". <input type='text' class='mnuforumSm' value='" + answer + "' name='P" + (i + 1) +"' id='P"+ (i + 1) + "' size='100'>");
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
      buffer.append("<textarea class='mnuforumSm' rows='30' id='ed1' name='A2' cols='55'>" + stripslashes(body) + "</textarea>");
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
