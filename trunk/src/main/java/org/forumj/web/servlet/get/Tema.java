/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.web.servlet.get;

import static org.forumj.tool.Diletant.*;
import static org.forumj.tool.FJServletTools.*;
import static org.forumj.tool.PHP.*;
import static org.forumj.web.servlet.tool.FJServletTools.*;

import java.io.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.*;
import org.forumj.db.dao.*;
import org.forumj.db.entity.*;
import org.forumj.exception.InvalidKeyException;
import org.forumj.tool.*;
import org.forumj.web.servlet.FJServlet;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.VIEW_THREAD}, name = FJServletName.VIEW_THREAD)
public class Tema extends FJServlet {

   /**
    * {@inheritDoc}
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      long startTime = new Date().getTime();
      StringBuffer buffer = new StringBuffer();
      try {
         HttpSession session = request.getSession();
         cache(response);
         // Какой это номер страницы? если без номера, то первый
         Integer pageNumber = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));
         // id Темы
         Long threadId = request.getParameter("id") == null ? 1 : Long.valueOf(request.getParameter("id"));
         FJThreadDao dao = new FJThreadDao();
         FJThread thread = dao.read(threadId);
         // Номер поста, на который отвечаем
         String replyPostId = request.getParameter("reply");
         LocaleString locale = (LocaleString) session.getAttribute("locale");
         User user = (User) session.getAttribute("user");
         List<Ignor> ignorList = new IgnorDao().loadAll(user.getId());
         int nfirstpost = (pageNumber-1)*user.getPt();
         int i3=pageNumber*user.getPt();
         // Сколько страниц?
         Integer count = thread.getPcount();
         Integer couP = ceil((double)count/user.getPt())+1;
         // Если цитирование или последний пост, то нам на последнюю
         boolean lastPost = false;
         String end = request.getParameter("end");
         if (replyPostId != null && !"".equals(replyPostId.trim()) || isset(end)){
            pageNumber = couP-1;
            lastPost = true;
         }
         FJPostDao postDao = new FJPostDao();
         List<FJPost> posts = postDao.getPostsList(user, threadId, nfirstpost, i3, pageNumber, lastPost);
         // Получаем массив постов
         session.setAttribute("page", pageNumber);
         session.setAttribute("id", threadId);
         session.setAttribute("where", request.getContextPath() + "?id=$gid&page=$pg");
         // Зашли с поиска?
         String msg = request.getParameter("msg");
         int countPosts = 0;
         if (msg != null && !"".equals(msg.trim())){
            countPosts = dao.getPostsCountInThread(threadId, new Long(msg));
            pageNumber=ceil(countPosts/user.getPt());
         }
         // Записываем счетчики
         // Робот?
         if (!isRobot(request)){
            // Нет
            dao.setSeen(user, threadId);
         }
         buffer.append("<!doctype html public \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
         buffer.append("<html>");
         buffer.append("<head>");
         buffer.append("<meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
         // Стили
         buffer.append(loadCSS("/css/style.css"));
         // Скрипты (смайлики)
         buffer.append(loadJavaScript("/js/smile_.js"));
         // Скрипты (игнор)
         buffer.append(loadJavaScript("/js/jsignor.js"));
         // Скрипты (подписка)
         buffer.append(loadJavaScript("/js/jssubscribe.js"));
         // Скрипты (submit поста)
         buffer.append(post_submit(locale.getString("mess128")));
         // Скрипты (автовставка тегов)
         buffer.append(loadJavaScript("/js/jstags.js"));
         buffer.append("<link rel='icon' href='/favicon.ico' type='image/x-icon'>");
         buffer.append("<link rel='shortcut icon' href='/favicon.ico' type='image/x-icon'>");
         buffer.append("<title>");
         buffer.append("форум Дилетантов :: " + thread.getHead());
         buffer.append("</title>");
         buffer.append("</head>");
         // Цвет фона страницы
         buffer.append("<body class='mainBodyBG'>");
         // Главная таблица
         buffer.append("<table border='0' style='border-collapse: collapse' width='100%'>");
         // Таблица с лого и верхним баннером
         buffer.append(logo(request));
         // Таблица главных ссылок
         buffer.append("<tr>");
         buffer.append("<td width='100%'>");
         buffer.append("<table border='0' style='border-collapse: collapse' width='100%'>");
         // Главное "меню"
         buffer.append(menu(request, user, locale, false));
         // Ссылки на другие страницы  Тут надо убрать colspan!
         buffer.append("<tr><td width=100%>");
         buffer.append("<table width=100%>");
         buffer.append("<tr>");
         buffer.append("<td>");
         buffer.append("<table>");
         buffer.append("<tr>");
         buffer.append("<td class='page'>");
         buffer.append("<font class=mnuforum><b>" + locale.getString("mess22") + "&nbsp;</b></font>");
         buffer.append("</td>");
         int i2=0;
         for (int i1=1; i1<couP; i1++){
            i2=i2+1;
            if ((i1>(pageNumber-5) && i1<(pageNumber+5))||i2==10||i1==1||i1==(couP-1)){
               if (i2==10) i2=0;
               if (i1==pageNumber){
                  buffer.append("<td class='pagecurrent'>");
                  buffer.append("<span class=mnuforum><b>"+i1 + "</b></span>");
                  buffer.append("</td>");
               }
               else {
                  buffer.append("<td class='page'>");
                  buffer.append("<a class=mnuforum href='tema.php?page="+i1 + "&id="+threadId + "'>"+i1 + "</a>");
                  buffer.append("</td>");
               }
            }
         }
         buffer.append("</tr>");
         buffer.append("</table>");

         buffer.append("</td>");

         buffer.append("<td align=right>");
         // Сторінка сформована :)
         buffer.append("<span class=posthead>"+ locale.getString("mess91") + "</span>");
         buffer.append("</td>");
         buffer.append("</tr></table>");
         buffer.append("</td>");
         buffer.append("</tr></table></td></tr>");
         // Таблица главных ссылок кончилась
         //Строка с таблицей форума
         buffer.append("<tr><td height='400' valign='top'>");
         // Таблица форума
         buffer.append("<table border='0' cellpadding='2' cellspacing='0' width='100%'>");
         // Определяем кол-во строк таблицы
         if (i3>count) {
            i3=count-(pageNumber-1)*user.getPt();
         }else{
            i3=user.getPt();
         }
         // Тема
         // Выводим строки
         for (int postIndex = 0; postIndex < posts.size(); postIndex++) {
            FJPost post = posts.get(postIndex);
            buffer.append(writePost(post, ignorList, user, pageNumber, locale, thread));
         }
         // /Таблица форума
         buffer.append("</table>");
         // "Граница" внизу
         buffer.append("</td>");
         buffer.append("</tr>");
         // Таблица главных ссылок
         // Ссылки на страницы
         buffer.append("<tr>");
         buffer.append("<td width='100%'>");
         buffer.append("<table border='0' style='border-collapse: collapse' width='100%'>");
         buffer.append("<tr>");
         buffer.append("<td colspan='5'>");
         buffer.append("<table>");
         buffer.append("<tr>");
         buffer.append("<td class='page'>");
         buffer.append("<font class=mnuforum><b>" + locale.getString("mess22") + "&nbsp;</b></font>");
         buffer.append("</td>");
         i2=0;
         for (int i1=1; i1<couP; i1++){
            i2=i2+1;
            if ((i1>(pageNumber-5) && i1<(pageNumber+5))||i2==10||i1==1||i1==(couP-1)){
               if (i2==10) i2=0;
               if (i1==pageNumber){
                  buffer.append("<td class='pagecurrent'>");
                  buffer.append("<span class=mnuforum><b>"+i1 + "</b></span>");
                  buffer.append("</td>");
               }
               else {
                  buffer.append("<td class='page'>");
                  buffer.append("<a class=mnuforum href='tema.php?page="+i1 + "&id="+threadId + "'>"+i1 + "</a>");
                  buffer.append("</td>");
               }
            }
         }
         buffer.append("</tr>");
         buffer.append("</table>");
         buffer.append("</td>");
         buffer.append("</tr>");
         // Главное "меню"
         buffer.append(menu(request, user, locale, false));
         buffer.append("</table></td></tr>");
         if (user.isLogined()){
            //Форма подписки/отписки  на ветку
            //Мы уже подписаны?
            String action = "";
            String mess = "";
            FJSubscribeDao subscribeDao = new FJSubscribeDao(); 
            if (subscribeDao.isUserSubscribed(threadId, user.getId())){
               //Подписка есть, предлагаем отказаться
               action="delonesubs.php?pg="+pageNumber;
               mess=locale.getString("mess90");
            }else{
               //Подписки нет - тогда предлагаем подписаться
               action="addsubs.php?pg="+pageNumber;
               mess=locale.getString("mess89");   
            }
            buffer.append("<tr>");
            buffer.append("<td align=right>");
            buffer.append("<form id='subs' action='" + action + "' method='POST'>");
            buffer.append("<table>");
            buffer.append("<tr>");
            buffer.append("<td>");
            buffer.append(fd_button(mess,"subscribe();","btn_subs", "1"));
            //Прередаем нужные пераметры...
            buffer.append("<input type=hidden name='IDT' value='" + threadId + "'>");
            buffer.append(fd_form_add(user));
            buffer.append("</td>");
            buffer.append("</tr>");
            buffer.append("</table>");
            buffer.append("</form>");
            buffer.append("</td>");
            buffer.append("</tr>");
            String re="";
            String head = thread.getHead();
            FJPost replyPost = null;
            // Если цитируем/редактируем
            if (replyPostId != null && !"".equals(replyPostId.trim())) {
               replyPost = postDao.read(Long.valueOf(replyPostId));
               // Редактируем?
               head=stripslashes(replyPost.getHead().getTitle());
               if (replyPost.getHead().getAuthor().getNick().equalsIgnoreCase(user.getNick())) {
                  // Да
                  session.setAttribute("edit",replyPostId);
                  re="";
               }else{
                  // Нет
                  session.setAttribute("edit",null);
               }
            }
            // Новое мнение
            // Форма нового поста
            buffer.append("<tr>");
            buffer.append("<td>");
            buffer.append("<a name='edit'>&nbsp;");
            buffer.append("</a>");
            buffer.append("<table>");
            buffer.append("<tr>");
            buffer.append("<td>");
            buffer.append("<form name='post' action='write.php' method='POST'>");
            buffer.append("<table width='100%'>");
            //Тема
            buffer.append("<tr>");
            buffer.append("<td colspan='2' align='CENTER'>");
            buffer.append("<table>");
            buffer.append("<tr>");
            buffer.append("<td>");
            buffer.append(locale.getString("mess59") + ":&nbsp;");
            buffer.append("</td>");
            buffer.append("<td>");
            buffer.append(fd_input("NHEAD", re + htmlspecialchars(head), "70", "1"));
            buffer.append("</td>");
            buffer.append("</tr>");
            buffer.append("</table>");
            buffer.append("</td>");
            buffer.append("</tr>");
            buffer.append("<tr>");
            //Смайлики заголовок
            buffer.append("<td width='400' align='CENTER'>");
            buffer.append("<p>");
            buffer.append(locale.getString("mess21") + ":");
            buffer.append("</p>");
            buffer.append("</td>");
            //Приглашение
            buffer.append("<td align='CENTER'>");
            buffer.append("<p>");
            buffer.append(locale.getString("mess12"));
            buffer.append("</p>");
            buffer.append("</td>");
            buffer.append("</tr>");
            //Пост
            buffer.append("<tr>");
            buffer.append("<td valign='TOP' width='100%' height='100%'>");
            //Смайлики
            buffer.append(smiles_add(locale.getString("mess11")));
            buffer.append("</td>");
            buffer.append("<td width='500' align='CENTER' valign='top'>");
            //Автотеги
            buffer.append(autotags_add());
            // текстарий
            String textarea="";
            if (replyPostId != null && !"".equals(replyPostId.trim())) {
               String ans = request.getParameter("ans");
               if (session.getAttribute("edit") != null){
                  textarea+=stripslashes(replyPost.getBody().getBody());
               }else if (ans != null){
                  textarea+="[quote][b]"+stripslashes(replyPost.getHead().getAuthor().getNick()) + "[/b]";
                  textarea+=locale.getString("mess14")+chr(13);
                  textarea+=stripslashes(replyPost.getBody().getBody()) + "[/quote]";
               }else{
                  textarea+="[b]"+stripslashes(replyPost.getHead().getAuthor().getNick()) + "[/b]";
                  textarea+=", ";
               }
            }
            buffer.append("<textarea rows='20' class='mnuforumSm' id='ed1' name='A2' cols='55'>" + textarea + "</textarea>");
            buffer.append("<br>");
            buffer.append("<input type='checkbox' name='no_exit' value='1'>");
            buffer.append(locale.getString("mess123"));
            //Кнопки
            buffer.append("<table>");
            buffer.append("<tr>");
            buffer.append("<td>");
            buffer.append(fd_button(locale.getString("mess13"),"post_submit(\"write\");","B1", "1"));
            buffer.append("</td>");
            buffer.append("<td>");
            buffer.append(fd_button(locale.getString("mess63"),"post_submit(\"view\");","B3", "1"));
            buffer.append("</td>");
            buffer.append("</tr>");
            buffer.append("</table>");
            //Если редактируем
            if (replyPostId != null && !"".equals(replyPostId.trim()) && (replyPost.getHead().getAuthor().getId().equals(user.getId()))){
               buffer.append("<input type=hidden name='IDB' size='20' value='" + replyPostId + "'>");
               buffer.append("<input type=hidden name='IDTbl' size='20' value='" + replyPost.getTablePost() + "'>");
               buffer.append("<input type=hidden name='IDPst' size='20' value='" + replyPost.getId().toString() + "'>");
               buffer.append("<input type=hidden name='IDTblHead' size='20' value='" + replyPost.getTableHead() + "'>");
               buffer.append("<input type=hidden name='IDHead' size='20' value='" + replyPost.getId().toString() + "'>");
            }
            //id темы
            buffer.append("<input type=hidden name='IDT' size='20' value='" + threadId + "'>");
            if (thread.isQuest()){
               buffer.append("<input type=hidden name='ISQUEST' size='20' value='true'>");
            }
            buffer.append(fd_form_add(user));
            buffer.append("</td>");
            buffer.append("</tr>");
            buffer.append("</table>");
            buffer.append("</form>");
            buffer.append("</td>");
            buffer.append("</tr>");
            buffer.append("</table>");
            buffer.append("</td>");
            buffer.append("</tr>");
         }
         // Баннер внизу, счетчики и копирайт.
         buffer.append(footer(request));
         buffer.append("</table>");
         buffer.append("</body>");
         buffer.append("</html>");
      } catch (InvalidKeyException e) {
         e.printStackTrace();
      } catch (ConfigurationException e) {
         e.printStackTrace();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      Double allTime = (double) ((new Date().getTime() - startTime));
      DecimalFormat format = new DecimalFormat("##0.###");
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter writer = response.getWriter();
      String out = buffer.toString();
      writer.write(out.replace("ъъ_ъ", format.format(allTime/1000)));
   }

   private StringBuffer writePost(FJPost post, List<Ignor> ignorList, User user, Integer pageNumber, LocaleString locale, FJThread thread) throws InvalidKeyException, ConfigurationException, SQLException{
      StringBuffer buffer = new StringBuffer();
      Time postTime = new Time(post.getHead().getCreateTime());
      User author = post.getHead().getAuthor();
      String domen = post.getHead().getDomen();
      String ip = post.getHead().getIp();
      if (ip.trim().equalsIgnoreCase(trim(domen))){
         domen = substr(domen, 0, strrpos(domen, ".")+1) + "---";
      }else{
         domen = "---" + substr(domen, strpos(domen, ".") + 1);
      }
      buffer.append("<tr class=heads>");
      buffer.append("<td  class=internal>");
      if (post.isLastPost()) buffer.append("<a name='end'></a>");
      buffer.append("<a name='this.str_id'>&nbsp;</a>");
      buffer.append("<a class=nik href='tema.php?id=" + post.getThreadId() + "&msg=" + post.getId() + "#" + post.getId() + "'><b>&nbsp;&nbsp;" + fd_head(htmlspecialchars(post.getHead().getTitle())) + "</b></a>");
      buffer.append("</td></tr>");
      buffer.append("<tr><td>");
      int div = 0;
      String div_ = "";
      if (ignorList.size() > 0){
         if (isIgnored(post.getHead().getAuth(), ignorList)) div = 1;
      }
      buffer.append("<span class='tbtextnread'>" + author.getNick() + "</span>&nbsp;•");
      buffer.append("&nbsp;<img border='0' src='smiles/icon_minipost.gif'>&nbsp;<span class='posthead'>" + postTime.toString("dd.MM.yyyy HH:mm") + "</span>&nbsp;");
      if (div != 0 && user.isLogined() && post.getHead().getAuth() != user.getId()){
         buffer.append( chr(149) + "&nbsp;<a class=\"posthead\" href=\"ignor.php?idi=" + post.getHead().getAuth() + "&idt=" + post.getThreadId() + "&idp=" + post.getId() + "&pg=" + pageNumber + "\" rel=\"nofollow\">" + locale.getString("mess68") + "</a>");
      }
      buffer.append("</td></tr>");
      buffer.append("<tr><td>");
      if (div != 0){
         buffer.append("<a href='#' onclick='togglemsg(\"dd" + post.getId() + "\"); return false;' rel='nofollow'>" + locale.getString("mess142") + "</a>");
         div_ =" style='visibility: hidden; display:none;'";
      }
      else {
         div_ ="";
      }
      buffer.append("<div id=dd" + post.getId().toString() + div_ + ">");
      if (!(user.isLogined() && div > 0)){
         buffer.append("<table width='100%'><tr><td valign=top class='matras'>");
         buffer.append("<table style='table-layout:fixed;' width='170'><tr><td valign=top>");
         buffer.append("<div style='padding:10px;'>");
         if (user.getWantSeeAvatars() && author.getAvatarApproved() && author.getAvatar() != null && author.getAvatar().trim().isEmpty() && author.getShowAvatar()){
            buffer.append("<a href='control.php?id=9'><img border='0' src='" + author.getAvatar() + "' rel=\"nofollow\"></a>");
         }else{
            buffer.append("<a href='control.php?id=9' rel='nofollow'><img border='0' src='smiles/no_avatar.gif'></a>");
         }
         buffer.append("</div>");
         buffer.append("<span class='posthead'><u>" + locale.getString("mess111") + "</u></span><br>");
         if (!author.getShowCountry() || author.getCountry() == null || author.getCountry().isEmpty()){
            buffer.append("<span class='posthead'>" + locale.getString("mess114") + "</span><br>");
         }else{
            buffer.append("<span class='posthead'>" + author.getCountry() + "</span><br>");
         }
         buffer.append("<span class='posthead'><u>" + locale.getString("mess112") + "</u></span><br>");
         if (author.getShowCity() || author.getCity() == null || author.getCity().isEmpty()){
            buffer.append("<span class='posthead'>" + locale.getString("mess114") + "</span><br>");
         }else{
            buffer.append("<span class='posthead'>" + author.getCity() + "</span><br>");
         }
         buffer.append("</td></tr></table>");
         buffer.append("</td><td valign='top' width='100%'>");
         buffer.append("<table width='100%'>");
         if (post.getAnswers() != null){
            buffer.append(writeQuest(post, user, locale, thread));
         }
         //            if (this.isQuest){
         //               buffer.append( this.getQuest() ;
         //            }
         buffer.append("<tr><td>");
         buffer.append("<p class=post>" + fd_body(post.getBody().getBody()) + "</p>");
         buffer.append("</td></tr>");
         buffer.append("</table></td></tr>");
         buffer.append("<tr><td class='matras' colspan=2></td></tr>");
         buffer.append("<tr><td class='matras'></td><td>");
         buffer.append("<p class=post>" + fd_body(author.getFooter()) + "</p>");
         buffer.append("</td></tr>");
         buffer.append("<tr><td align='RIGHT' width='100%' colspan=2>");
         if (post.getHead().getNred()>0){
            Time postEditTime = new Time(post.getHead().getEditTime());

            buffer.append("<table class='matras' width='100%'>");
            buffer.append("<tr><td align='LEFT'>");
            buffer.append("<span class='posthead'>" + locale.getString("mess50") + post.getHead().getNred() + locale.getString("mess51") + postEditTime.toString("dd.MM.yyyy HH:mm") + "</span>");
         }
         else {
            buffer.append("<table class='matras'>");
            buffer.append("<tr><td align='LEFT'>");
            buffer.append(" ");
         }
         buffer.append("</td>");
         if(user.isLogined()){
            //               if (this.isAdminForvard){
            //                  buffer.append("<td align='CENTER' width='70'>");
            //                  buffer.append("<span class='posthead'><a href='site.php?id=" + post.getThreadId() + "&post=" + this.id + "' rel=\"nofollow\">" + locale.getString("mess162") + "</a></span>");
            //                  buffer.append("</td>");
            //               }
            if (user.getId() == author.getId()) {
               buffer.append("<td align='CENTER' width='70'>");
               buffer.append("<span class='posthead'><a href='tema.php?id=" + post.getThreadId() + "&reply=" + post.getId() + "#edit' rel=\"nofollow\">" + locale.getString("mess141") + "</a></span>");
               buffer.append("</td>");
            }else{
               buffer.append("<td align='CENTER' width='70'>");
               buffer.append("<span class='posthead'><a href='tema.php?id=" + post.getThreadId() + "&reply=" + post.getId() + "#edit' rel=\"nofollow\">" + locale.getString("mess139") + "</a></span>");
               buffer.append("</td>");
               buffer.append("<td align='CENTER' width='70'>");
               buffer.append("<span class='posthead'><a href='tema.php?id=" + post.getThreadId() + "&reply=" + post.getId() + "&ans=1#edit' rel=\"nofollow\">" + locale.getString("mess140") + "</a></span>");
               buffer.append("</td>");
            }
         }
         buffer.append("</tr></table>");
         buffer.append("</td></tr>");
         buffer.append("</table>");
      }else{
         buffer.append( locale.getString("mess103"));
      }
      buffer.append("</div>");
      buffer.append("</td></tr>");
      return buffer;
   }

   private StringBuffer writeQuest(FJPost post, User user, LocaleString locale, FJThread thread) throws ConfigurationException, SQLException, InvalidKeyException{
      StringBuffer buffer = new StringBuffer(); 
      int $nvcs = post.getAnswers().size();
      buffer.append("<tr><td>");
      buffer.append("<p align=\"CENTER\"><font size=4><b>" + post.getQuestion().getNode() + "</b></font></p><br>");
      buffer.append("</td></tr>");
      buffer.append("<tr><td align=\"CENTER\">");
      List<QuestNode> $nodes = post.getAnswers();
      FJVoiceDao voiceDao = new FJVoiceDao();
      boolean userVoted = voiceDao.isUserVote(thread.getId(), user);
      if (user.isLogined() && !userVoted){
         buffer.append("<form  action='voice.php' method='POST'><table class=content>");
         for (int nodeIndex = 1; nodeIndex < $nodes.size(); nodeIndex++) {
            QuestNode questNode = $nodes.get(nodeIndex);
            buffer.append("<tr><td class=voice_left align='right'>");
            String $check = "";
            if (nodeIndex == 1){
               $check=" CHECKED";
            }
            if (questNode.getUserId() == 0){
               buffer.append(locale.getString("mess143"));
               if (questNode.getType() == 1){
                  buffer.append("<b>" + questNode.getUserNick() + "</b>");
               }
               else{
                  buffer.append("<b>" + locale.getString("mess144") + "</b>");
               }
               buffer.append("</td><td class=voice_right align='left'>");
               buffer.append("<input type='radio' name='ANSWER' value='$in1'>&nbsp;" + fd_smiles(fd_href(stripslashes(questNode.getNode()))) + "<br>");
            }
            else {
               buffer.append("</td><td class=voice_right align='left'>");
               buffer.append("<input type='radio' name='ANSWER' value='$in1'" + $check + ">&nbsp;" + fd_smiles(fd_href(stripslashes(questNode.getNode()))) + "<br>");
            }
            buffer.append("</td></tr>");
         }
         buffer.append("<tr><td colspan='2' align='CENTER'>");
         buffer.append("<input type=hidden name=\"IDU1\" size=\"20\" value=\"" + user.getId() + "\">");
         buffer.append("<input type=hidden name=\"AUT1\" size=\"20\" value=\"" + user.getNick() + "\">");
         buffer.append("<input type=hidden name=\"IDT1\" size=\"20\" value=\"" + thread.getId() + "\">");
         if (isset(user.getPass2())) {
            buffer.append("<input type=hidden name=\"PS21\" size=\"20\" value=\"" + user.getPass2() + "\">");
         }
         else {
            buffer.append("<input type=hidden name=\"PS11\" size=\"20\" value=\"" + user.getPass() + "\">");
         }
         buffer.append("<input type='submit' value='" + locale.getString("mess145") + "' name='OK'>");
         buffer.append("</td></tr>");
         buffer.append("</table></form>");
         buffer.append("</td></tr>");
         //Users can add custom answers 
         if (thread.getType() == 2){
            boolean userVotes = false;
            for (int nodeIndex = 1; nodeIndex < $nodes.size(); nodeIndex++) {
               QuestNode questNode = $nodes.get(nodeIndex);
               if (questNode.getUserId() == user.getId()) userVotes = true;
            }
            if (!userVotes){
               buffer.append("<tr><td>");
               buffer.append("<form  action=\"uservoice.php\" method=\"POST\"><table align=\"CENTER\">");
               buffer.append("<tr><td>");
               buffer.append(locale.getString("mess153") + ":<br>");
               buffer.append("<input type=\"text\" name=\"P\" size=\"100\">");
               buffer.append("<input type=hidden name=\"IDU2\" size=\"20\" value=\"" + user.getId() + "\">");
               buffer.append("<input type=hidden name=\"AUT2\" size=\"20\" value=\"" + user.getNick() + "\">");
               buffer.append("<input type=hidden name=\"IDT2\" size=\"20\" value=\"" + thread.getId() + "\">");
               if (isset(user.getPass2())) {
                  buffer.append("<input type=hidden name=\"PS22\" size=\"20\" value=\"" + user.getPass2() + "\">");
               }
               else {
                  buffer.append("<input type=hidden name=\"PS12\" size=\"20\" value=\"" + user.getPass() + "\">");
               }
               buffer.append("</td></tr>");
               buffer.append("<tr><td align=\"CENTER\">");
               buffer.append("<input type='checkbox' name='HD' value='1' checked>&nbsp;" + locale.getString("mess146") + "<br>");
               buffer.append("<input type='submit' value='" + locale.getString("mess145") + "' name='OK'>");
               buffer.append("</td></tr>");
               buffer.append("</table></form>");
               buffer.append("</td></tr>");
            }
         }
      }
      if ($nvcs > 0) $nvcs=1/10000000;
      buffer.append("<tr><td align=\"CENTER\">");
      buffer.append("<b>" + locale.getString("mess152") + ": " + round($nvcs,0) + "</b>");
      buffer.append("</td></tr>");
      buffer.append("<tr><td align=\"CENTER\">");
      buffer.append("<table align='CENTER' class=control>");
      buffer.append("<tr class=heads><th class='internal'>");
      buffer.append(locale.getString("mess147"));
      buffer.append("</th><th class='internal'>");
      buffer.append(locale.getString("mess148"));
      buffer.append("</th><th class='internal'>");
      buffer.append(locale.getString("mess149"));
      buffer.append("</th><th class='internal' width='350'>");
      buffer.append(locale.getString("mess150"));
      buffer.append("</th><th class='internal'>");
      buffer.append(locale.getString("mess151"));
      buffer.append("</th></tr><tr>");
      for (int nodeIndex = 1; nodeIndex < $nodes.size(); nodeIndex++) {
         QuestNode questNode = $nodes.get(nodeIndex);
         if (questNode.getUserId() == 0){
            if (questNode.getType() == 1){
               buffer.append("<td align='LEFT' class='internal'>" + questNode.getUserNick() + "</td>");
            }
            else{
               buffer.append("<td align='LEFT' class='internal'>" + locale.getString("mess144") + "</td>");
            }
         }
         else
         {
            buffer.append("<td align='LEFT' class='internal'></td>");
         }
         buffer.append("<td class='internal'>" + fd_body(stripslashes(questNode.getNode())) + "</td>");

         buffer.append("<td align='CENTER' class='internal'>");
         buffer.append(questNode.getGol() + "</td>");
         buffer.append("<td class='internal'><TABLE cellSpacing=0 cellPadding=0 width='" + round((questNode.getGol()/($nvcs == 0 ? 1 : $nvcs))*300,0) + "' border=0><TR><TD align=left bgColor=red><FONT size=-3>&nbsp;</FONT></TD></TR></TABLE>");
         buffer.append("</td>");
         buffer.append("<td class='internal'>");
         buffer.append(round((questNode.getGol()/($nvcs == 0 ? 1 : $nvcs))*100, 2) + "%");
         buffer.append("</td></tr>");

      }
      buffer.append("</table>");
      if (isset(user.getId()) && userVoted){
         buffer.append("<form method=\"POST\" action=\"delvoice.php\" align=\"CENTER\">");
         buffer.append("<input type=hidden name=\"IDU\" size=\"20\" value=\"" + user.getId() + "\">");
         buffer.append("<input type=hidden name=\"AUT\" size=\"20\" value=\"" + user.getNick() + "\">");
         buffer.append("<input type=hidden name=\"IDT\" size=\"20\" value=\"" + thread.getId() + "\">");
         if (isset(user.getPass2())) {
            buffer.append("<input type=hidden name=\"PS2\" size=\"20\" value=\"" + user.getPass2() + "\">");
         }
         else {
            buffer.append("<input type=hidden name=\"PS1\" size=\"20\" value=\"" + user.getPass() + "\">");
         }

         buffer.append("<input type='submit' value='" + locale.getString("mess161") + "'>");
         buffer.append("</form>");
      }
      buffer.append("</td></tr>");
      return buffer;
   }

   private boolean isIgnored(Long userId, List<Ignor> ignorList){
      for(int arrIndex=0; arrIndex < ignorList.size(); arrIndex++) {
         if(ignorList.get(arrIndex).getUser().getId().longValue() == userId.longValue()) {
            return true;
         }
      }
      return false;
   }
}
