/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.network.web.controller;

import static org.forumj.tool.Diletant.*;
import static org.forumj.tool.FJServletTools.*;
import static org.forumj.web.servlet.tool.FJServletTools.*;

import java.io.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.*;
import org.forumj.common.db.entity.*;
import org.forumj.common.db.service.*;
import org.forumj.common.exception.InvalidKeyException;
import org.forumj.common.tool.*;
import org.forumj.common.web.ThreadType;
import org.forumj.network.web.resources.ResourcesBuilder;
import org.forumj.tool.LocaleString;

import com.tecnick.htmlutils.htmlentities.HTMLEntities;

public class Tema{

   public void doGet(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws ServletException, IOException {
      long startTime = new Date().getTime();
      ImageService imageService = FJServiceHolder.getImageService();
      StringBuffer buffer = new StringBuffer();
      try {
         HttpSession session = request.getSession();
         cache(response);
         // Какой это номер страницы? если без номера, то первый
         Integer pageNumber = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));
         // id Темы
         Long threadId = request.getParameter("id") == null ? 1 : Long.valueOf(request.getParameter("id"));
         // Номер поста, на который отвечаем
         String replyPostParameter = request.getParameter("reply");
         String end = request.getParameter("end");
         // Зашли с поиска?
         String msg = request.getParameter("msg");
         VoiceService voiceService = FJServiceHolder.getVoiceService();
         PostService postService = FJServiceHolder.getPostService();
         SubscribeService subscribeService = FJServiceHolder.getSubscribeService();
         ThreadService treadService = FJServiceHolder.getThreadService();
         IFJThread thread = treadService.readThread(threadId);
         boolean isAnswer = replyPostParameter != null && !"".equals(replyPostParameter.trim());
         LocaleString locale = (LocaleString) session.getAttribute("locale");
         IUser user = (IUser) session.getAttribute("user");
         IgnorService ignorService = FJServiceHolder.getIgnorService();
         List<IIgnor> ignorList = ignorService.readUserIgnor(user.getId());
         // Сколько страниц?
         Integer count = thread.getPostsAmount();
         Integer couP = (int) (Math.floor((double)count/user.getPostsOnPage())+2);
         // Если цитирование или последний пост, то нам на последнюю
         boolean lastPost = false;
         if (isAnswer || end != null){
            pageNumber = couP-1;
            lastPost = true;
         }
         int nfirstpost = (pageNumber-1)*user.getPostsOnPage();
         List<IFJPost> posts = postService.readPosts(user, threadId, nfirstpost, user.getPostsOnPage(), pageNumber, lastPost);
         int postsAmount = posts.size();
         // Получаем массив постов
         session.setAttribute("page", pageNumber);
         session.setAttribute("id", threadId);
         session.setAttribute("where", request.getContextPath() + "?id=" + threadId + "&amp;page=" + pageNumber);
         int countPosts = 0;
         if (msg != null && !"".equals(msg.trim())){
            try {
               Long msgId = new Long(msg);
               countPosts = postService.getPostsCountInThread(threadId, msgId);
            } catch (NumberFormatException e) {
               e.printStackTrace();
               msg = null;
            }
            pageNumber=(int) (Math.floor(countPosts/user.getPostsOnPage()) + 1);
         }
         // Записываем счетчики
         // Робот?
         if (!isRobot(request)){
            // Нет
            treadService.setSeen(user, threadId);
         }
         buffer.append("<!doctype html public \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
         buffer.append("<html>");
         buffer.append("<head>");
         buffer.append("<meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
         // Стили
         buffer.append(ResourcesBuilder.getStyleCSS(webapp));
         IndexService indexService = FJServiceHolder.getIndexService();
         Long m_xb = indexService.getLastPostId(threadId);
         buffer.append("<script language='javascript' type='text/javascript'>\n");
         buffer.append("var m_xb=" + m_xb + ";\n");
         buffer.append("var pingURL='").append("/").append(userURI).append("/").append(FJUrl.PING).append("/?id=").append(threadId).append("';\n");
         buffer.append("</script>\n");
         buffer.append("<script language='javascript' type='text/javascript'>\n");
         buffer.append("var HEADER_IS_EMPTY='").append(locale.getString("mess128")).append("';\n");
         buffer.append("var POST_IS_EMPTY='").append(locale.getString("mess129")).append("';\n");
         buffer.append("var webapp='").append(webapp.isEmpty() ? "" : "/" + webapp).append("';\n");
         buffer.append("</script>\n");
         buffer.append(loadJavaScript("/js/indicatorForThread.js"));
         // Скрипты (смайлики)
         buffer.append(loadJavaScript("/js/smile_.js"));
         // Скрипты (игнор)
         buffer.append(loadJavaScript("/js/jsignor.js"));
         // Скрипты (подписка)
         buffer.append(loadJavaScript("/js/jssubscribe.js"));
         // Скрипты (submit поста)
         buffer.append(loadJavaScript("/js/postSubmit.js"));
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
         buffer.append(logo(webapp));
         // Таблица главных ссылок
         buffer.append("<tr>");
         buffer.append("<td width='100%'>");
         buffer.append("<table border='0' style='border-collapse: collapse' width='100%'>");
         // Главное "меню"
         buffer.append(menu(request, user, locale, false, webapp, userURI));
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
                  buffer.append("<span class=mnuforum><b>");
                  buffer.append(i1);
                  buffer.append("</b></span>");
                  buffer.append("</td>");
               }else {
                  buffer.append("<td class='page'>");
                  buffer.append("<a class='mnuforum' href='");
                  buffer.append("/").append(userURI).append("/").append(FJUrl.VIEW_THREAD);
                  buffer.append("?id=");
                  buffer.append(threadId);
                  buffer.append("&amp;page=");
                  buffer.append(i1);
                  buffer.append("'>");
                  buffer.append(i1);
                  buffer.append("</a>");
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
         buffer.append("<br/>");
         buffer.append("<span class=posthead >" + locale.getString("mess165") + ":&nbsp;</span>");
         buffer.append("<span class=posthead id='indicatort' style='color:red'>&nbsp;</span>");

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
         if (postsAmount>count) {
            postsAmount=count-(pageNumber-1)*user.getPostsOnPage();
         }else{
            postsAmount=user.getPostsOnPage();
         }
         // Тема
         // Выводим строки
         for (int postIndex = 0; postIndex < posts.size(); postIndex++) {
            IFJPost post = posts.get(postIndex);
            buffer.append(writePost(post, ignorList, user, pageNumber, locale, thread, voiceService, userURI, webapp));
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
                  buffer.append("<span class=mnuforum><b>");
                  buffer.append(i1);
                  buffer.append("</b></span>");
                  buffer.append("</td>");
               }else {
                  buffer.append("<td class='page'>");
                  buffer.append("<a class='mnuforum' href='");
                  buffer.append("/").append(userURI).append("/").append(FJUrl.VIEW_THREAD);
                  buffer.append("?id=");
                  buffer.append(threadId);
                  buffer.append("&amp;page=");
                  buffer.append(i1);
                  buffer.append("'>");
                  buffer.append(i1);
                  buffer.append("</a>");
                  buffer.append("</td>");
               }
            }
         }
         buffer.append("</tr>");
         buffer.append("</table>");
         buffer.append("<script type='text/javascript'>");
         buffer.append("if (request){");
         buffer.append("getIndicatorInfo();");
         buffer.append("}");
         buffer.append("</script>");
         buffer.append("</td>");
         buffer.append("</tr>");
         // Главное "меню"
         buffer.append(menu(request, user, locale, false, webapp, userURI));
         buffer.append("</table></td></tr>");
         if (user.isLogined() && !user.isBanned() && !thread.isClosed()){
            //Форма подписки/отписки  на ветку
            //Мы уже подписаны?
            String action = "/" + userURI + "/";
            String mess = "";
            if (subscribeService.isUserSubscribed(threadId, user.getId())){
               //Подписка есть, предлагаем отказаться
               action += FJUrl.DELETE_SUBSCRIBE + "?pg=" + pageNumber;
               mess = locale.getString("mess90");
            }else{
               //Подписки нет - тогда предлагаем подписаться
               action += FJUrl.ADD_SUBSCRIBE + "?pg="+pageNumber;
               mess=locale.getString("mess89");   
            }
            buffer.append("<tr>");
            buffer.append("<td align=right>");
            buffer.append("<form id='subs' action='" + action + "' method='post'>");
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
            IFJPost replyPost = null;
            // Если цитируем/редактируем
            if (isAnswer) {
               replyPost = postService.read(Long.valueOf(replyPostParameter));
               head = removeSlashes(replyPost.getTitle());
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
            buffer.append("<form name='post' id='postForm' action='" + "/" + userURI + "/" + FJUrl.ADD_POST + "' method='post'>");
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
            buffer.append(fd_input("NHEAD", re + HtmlChars.convertHtmlSymbols(head), "70", "1"));
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
            /*Photoalbum header*/
            buffer.append("<td align=left>");
            buffer.append("<p>");
            buffer.append(locale.getString("MSG_PHOTOALBUM") + ":");
            buffer.append("</p>");
            buffer.append("</td>");
            buffer.append("</tr>");
            //Пост
            buffer.append("<tr>");
            buffer.append("<td valign='TOP' width='100%' height='100%'>");
            //Смайлики
            buffer.append(smiles_add(locale.getString("mess11"), webapp));
            buffer.append("</td>");
            buffer.append("<td width='500' align='CENTER' valign='top'>");
            //Автотеги
            buffer.append(autotags_add(webapp));
            // текстарий
            String textarea="";
            if (isAnswer) {
               String ans = request.getParameter("ans");
               if (replyPost.getAuth().equals(user.getId())){
                  textarea += HtmlChars.convertHtmlSymbols(removeSlashes(replyPost.getBody()));
               }else if (ans == null){
                  textarea += "[quote][b]" + HtmlChars.convertHtmlSymbols(removeSlashes(replyPost.getAuthor().getNick())) + "[/b] ";
                  textarea += locale.getString("mess14")+String.valueOf((char) 13);
                  textarea += HtmlChars.convertHtmlSymbols(removeSlashes(replyPost.getBody())) + "[/quote]";
               }else{
                  textarea += "[b]" + removeSlashes(replyPost.getAuthor().getNick()) + "[/b]";
                  textarea += ", ";
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
            if (isAnswer && (replyPost.getAuth().equals(user.getId()))){
               buffer.append(fd_button(locale.getString("mess13"),"post_submit(\"write_edit\");","B1", "1"));
            }else{
               buffer.append(fd_button(locale.getString("mess13"),"post_submit(\"write_new\");","B1", "1"));
            }
            buffer.append("</td>");
            buffer.append("<td>");
            if (isAnswer && (replyPost.getAuth().equals(user.getId()))){
               buffer.append(fd_button(locale.getString("mess63"),"post_submit(\"view_edit\");","B1", "1"));
            }else{
               buffer.append(fd_button(locale.getString("mess63"),"post_submit(\"view_new\");","B3", "1"));
            }
            buffer.append("</td>");
            buffer.append("</tr>");
            buffer.append("</table>");
            //Если редактируем
            if (isAnswer && (replyPost.getAuth().equals(user.getId()))){
               buffer.append("<input type=hidden name='IDB' size='20' value='" + replyPostParameter + "'>");
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
            //Photoalbum
            buffer.append("<td align='LEFT' valign='top'>");

            List<Image> imageThumbs = imageService.getImages(user.getId(), 0, ImageType.POST_THUMBNAIL);
            buffer.append("<div style='float: left;width: 330px;overflow-y: auto;overflow-x: hidden;height:400px;'>");
            buffer.append("<div style='float: left;width: 160px;'>");

            for (int thumbIndex = 0; thumbIndex < imageThumbs.size(); thumbIndex += 2){
               Image thumb = imageThumbs.get(thumbIndex);
               buffer.append("<div style='width: 150px;margin-bottom:10px;'>");
               buffer.append("<img border='0' src='/");
               if(!webapp.isEmpty()){
                  buffer.append(webapp).append("/");
               }
               buffer.append(FJUrl.STATIC).append("/").append(FJUrl.PHOTO).append("/");
               buffer.append(thumb.getId());
               buffer.append("?id=");
               buffer.append(thumb.getId());
               buffer.append("' onclick=\"InsertTags('[img]/");
               if(!webapp.isEmpty()){
                  buffer.append(webapp).append("/");
               }
               buffer.append(FJUrl.STATIC).append("/").append(FJUrl.PHOTO).append("/");
               buffer.append(thumb.getParentId());
               buffer.append("?id=");
               buffer.append(thumb.getParentId());
               buffer.append("','[/img]')\" alt='Вставить картинку'>");
               buffer.append("</div>");
            }
            buffer.append("</div>");
            buffer.append("<div style='margin-left: 160px;width: 160px;'>");
            for (int thumbIndex = 1; thumbIndex < imageThumbs.size(); thumbIndex += 2){
               Image thumb = imageThumbs.get(thumbIndex);
               buffer.append("<div style='width: 150px;margin-bottom:10px;'>");
               buffer.append("<img border='0' src='/");
               if(!webapp.isEmpty()){
                  buffer.append(webapp).append("/");
               }
               buffer.append(FJUrl.STATIC).append("/").append(FJUrl.PHOTO).append("/");
               buffer.append(thumb.getId());
               buffer.append("?id=");
               buffer.append(thumb.getId());
               buffer.append("' onclick=\"InsertTags('[img]/");
               if(!webapp.isEmpty()){
                  buffer.append(webapp).append("/");
               }
               buffer.append(FJUrl.STATIC).append("/").append(FJUrl.PHOTO).append("/");
               buffer.append(thumb.getParentId());
               buffer.append("?id=");
               buffer.append(thumb.getParentId());
               buffer.append("','[/img]')\" alt='Вставить картинку'>");
               buffer.append("</div>");
            }
            buffer.append("</div>");
            buffer.append("</div>");

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
         buffer.append(footer(webapp));
         buffer.append("</table>");
         buffer.append("</body>");
         buffer.append("</html>");
      } catch (Throwable e) {
         buffer = new StringBuffer();
         buffer.append(errorOut(e));
         e.printStackTrace();
      }
      Double allTime = (double) ((new Date().getTime() - startTime));
      DecimalFormat format = new DecimalFormat("##0.###");
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter writer = response.getWriter();
      String out = buffer.toString();
      writer.write(out.replace("ъъ_ъ", format.format(allTime/1000)));
   }

   private StringBuffer writePost(IFJPost post, List<IIgnor> ignorList, IUser user, Integer pageNumber, LocaleString locale, IFJThread thread, VoiceService voiceService, String userURI, String webapp) throws InvalidKeyException, ConfigurationException, SQLException, IOException{
      StringBuffer buffer = new StringBuffer();
      Time postTime = new Time(post.getCreateTime());
      IUser author = post.getAuthor();
      buffer.append("<tr class=heads>");
      buffer.append("<td  class=internal>");
      if (post.isLastPost()) buffer.append("<a name='end'></a>");
      buffer.append("<a name='" + post.getId() + "'>&nbsp;</a>");
      buffer.append("<a class=nik href='" + "/" + userURI + "/" + FJUrl.VIEW_THREAD + "?id=" + post.getThreadId() + "&amp;msg=" + post.getId() + "#" + post.getId() + "'  rel='nofollow'><b>&nbsp;&nbsp;" + fd_head(HTMLEntities.htmlentities(removeSlashes(post.getTitle())), webapp) + "</b></a>");
      buffer.append("</td></tr>");
      buffer.append("<tr><td>");
      boolean ignored = false;
      String div_ = "";
      if (ignorList.size() > 0){
         if (isIgnored(post.getAuth(), ignorList)) ignored = true;
      }
      buffer.append("<span class='tbtextnread'>" + HtmlChars.convertHtmlSymbols(author.getNick()) + "</span>&nbsp;•");
      buffer.append("&nbsp;<img border='0' src='/");
      if(!webapp.isEmpty()){
         buffer.append(webapp).append("/");
      }
      buffer.append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/icon_minipost.gif'>&nbsp;<span class='posthead'>").append(postTime.toString("dd.MM.yyyy HH:mm")).append("</span>&nbsp;");
      if (user.isModerator()){
         buffer.append("•&nbsp;<span class='posthead'>" + post.getIp() + "</span>&nbsp;" );
      }
      if (!ignored && user.isLogined() && post.getAuth() != user.getId()){
         buffer.append("•&nbsp;<a class='posthead' href='" + "/" + userURI + "/" + FJUrl.ADD_IGNOR + "?idi=" + post.getAuth() + "&amp;idt=" + thread.getId() + "&amp;idp=" + post.getId() + "&amp;pg=" + pageNumber + "' rel='nofollow'>" + locale.getString("mess68") + "</a>");
      }
      buffer.append("</td></tr>");
      buffer.append("<tr><td>");
      if (ignored){
         buffer.append("<a href='#' onclick='togglemsg(\"dd" + post.getId() + "\"); return false;' rel='nofollow'>" + locale.getString("mess142") + "</a>");
         div_ =" style='visibility: hidden; display:none;'";
      }else{
         div_ ="";
      }
      buffer.append("<div id=dd" + post.getId().toString() + div_ + ">");
      if ((user.isLogined() || !ignored)){
         buffer.append("<table width='100%'><tr><td valign=top class='matras'>");
         buffer.append("<table style='table-layout:fixed;' width='170'><tr><td valign=top>");
         buffer.append("<div style='padding:10px;'>");
         //avatar
         if (user.getWantSeeAvatars() && author.getAvatarApproved() && author.getAvatar() != null && !author.getAvatar().trim().isEmpty() && author.getShowAvatar()){
            StringBuilder avatarURL = new StringBuilder();
            if (author.getAvatar().startsWith("http://")){
               avatarURL.append(author.getAvatar());
            }else{
               avatarURL.append("/");
               if(!webapp.isEmpty()){
                  avatarURL.append(webapp).append("/");
               }
               avatarURL.append(FJUrl.STATIC).append("/").append(author.getAvatar()).append("?seed=").append(System.currentTimeMillis());
            }
            buffer.append("<a href='" + "/" + userURI + "/" + FJUrl.SETTINGS + "?id=9' rel='nofollow'><img border='0' src='").append(avatarURL).append("'></a>");
         }else{
            buffer.append("<a href='" + "/" + userURI + "/" + FJUrl.SETTINGS + "?id=9' rel='nofollow'><img border='0' src='/");
            if(!webapp.isEmpty()){
               buffer.append(webapp).append("/");
            }
            buffer.append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/no_avatar.gif'></a>");
         }
         buffer.append("</div>");
         buffer.append("<span class='posthead'><u>" + locale.getString("mess111") + "</u></span><br>");
         //country
         if (!author.getShowCountry() || author.getCountry() == null || author.getCountry().isEmpty()){
            buffer.append("<span class='posthead'>" + locale.getString("mess114") + "</span><br>");
         }else{
            buffer.append("<span class='posthead'>" + HtmlChars.convertHtmlSymbols(author.getCountry()) + "</span><br>");
         }
         buffer.append("<span class='posthead'><u>" + locale.getString("mess112") + "</u></span><br>");
         if (!author.getShowCity() || author.getCity() == null || author.getCity().isEmpty()){
            buffer.append("<span class='posthead'>" + locale.getString("mess114") + "</span><br>");
         }else{
            buffer.append("<span class='posthead'>" + HtmlChars.convertHtmlSymbols(author.getCity()) + "</span><br>");
         }
         buffer.append("</td></tr></table>");
         buffer.append("</td><td valign='top' width='100%'>");
         buffer.append("<table width='100%'>");
         if (thread.isQuest() && post.getAnswers() != null){
            buffer.append(writeQuest(post, user, locale, thread, voiceService, userURI, webapp));
         }
         buffer.append("<tr><td>");
         buffer.append("<p class=post>" + fd_body(HtmlChars.convertHtmlSymbols(removeSlashes(post.getBody())), webapp) + "</p>");
         buffer.append("</td></tr>");
         buffer.append("</table></td></tr>");
         buffer.append("<tr><td class='matras' colspan=2></td></tr>");
         buffer.append("<tr><td class='matras'></td><td>");
         buffer.append("<p class=post>" + fd_body(HtmlChars.convertHtmlSymbols(removeSlashes(author.getFooter())), webapp) + "</p>");
         buffer.append("</td></tr>");
         buffer.append("<tr><td align='RIGHT' width='100%' colspan=2>");
         if (post.getNred()>0){
            Time postEditTime = new Time(post.getEditTime());
            buffer.append("<table class='matras' width='100%'>");
            buffer.append("<tr><td align='LEFT'>");
            buffer.append("<span class='posthead'>" + locale.getString("mess50") + "&nbsp;" + post.getNred() + "&nbsp;" + locale.getString("mess51") + "&nbsp;" + postEditTime.toString("dd.MM.yyyy HH:mm") + "</span>");
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
            if (user.getId().equals(author.getId())) {
               buffer.append("<td align='CENTER' width='70'>");
               buffer.append("<span class='posthead'><a href='" + "/" + userURI + "/" + FJUrl.VIEW_THREAD + "?id=" + thread.getId() + "&amp;reply=" + post.getId() + "#edit' rel=\"nofollow\">" + locale.getString("mess141") + "</a></span>");
               buffer.append("</td>");
            }else{
               buffer.append("<td align='CENTER' width='70'>");
               buffer.append("<span class='posthead'><a href='" + "/" + userURI + "/" + FJUrl.VIEW_THREAD + "?id=" + thread.getId() + "&amp;reply=" + post.getId() + "#edit' rel=\"nofollow\">" + locale.getString("mess139") + "</a></span>");
               buffer.append("</td>");
               buffer.append("<td align='CENTER' width='70'>");
               buffer.append("<span class='posthead'><a href='" + "/" + userURI + "/" + FJUrl.VIEW_THREAD + "?id=" + thread.getId() + "&amp;reply=" + post.getId() + "&amp;ans=1#edit' rel=\"nofollow\">" + locale.getString("mess140") + "</a></span>");
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

   private StringBuffer writeQuest(IFJPost post, IUser user, LocaleString locale, IFJThread thread, VoiceService voiceService, String userURI, String webapp) throws ConfigurationException, SQLException, InvalidKeyException, IOException{
      StringBuffer buffer = new StringBuffer(); 
      int nvcs = post.getVoicesAmount();
      buffer.append("<tr><td>");
      buffer.append("<p align=\"CENTER\"><font size=4><b>" + removeSlashes(post.getQuestion().getNode()) + "</b></font></p><br>");
      buffer.append("</td></tr>");
      buffer.append("<tr><td align=\"CENTER\">");
      List<IQuestNode> nodes = post.getAnswers();
      boolean userVoted = voiceService.isUserVoted(thread.getId(), user.getId());
      if (user.isLogined() && !userVoted && !thread.isClosed()){
         buffer.append("<form  action='" + "/" + userURI + "/" + FJUrl.VOICE + "' method='post'><table class=content>");
         for (int nodeIndex = 1; nodeIndex < nodes.size(); nodeIndex++) {
            IQuestNode questNode = nodes.get(nodeIndex);
            buffer.append("<tr><td class=voice_left align='right'>");
            String check = "";
            if (nodeIndex == 1){
               check=" CHECKED";
            }
            if (questNode.getType() != 0){
               buffer.append(locale.getString("mess143"));
               if (questNode.getType() == 1){
                  buffer.append("<b>" + questNode.getUserNick() + "</b>");
               }else{
                  buffer.append("<b>" + locale.getString("mess144") + "</b>");
               }
               buffer.append("</td><td class='voice_right' align='left'>");
               buffer.append("<input type='radio' name='ANSWER' value='" + questNode.getId() + "'>&nbsp;" + fd_smiles(fd_href(removeSlashes(questNode.getNode())), false, webapp) + "<br>");
            }else {
               buffer.append("</td><td class='voice_right' align='left'>");
               buffer.append("<input type='radio' name='ANSWER' value='" + questNode.getId() + "'" + check + ">&nbsp;" + fd_smiles(fd_href(removeSlashes(questNode.getNode())), false, webapp) + "<br>");
            }
            buffer.append("</td></tr>");
         }
         buffer.append("<tr><td colspan='2' align='CENTER'>");
         buffer.append("<input type=hidden name=\"IDU1\" size=\"20\" value=\"" + user.getId() + "\">");
         buffer.append("<input type=hidden name=\"AUT1\" size=\"20\" value=\"" + user.getNick() + "\">");
         buffer.append("<input type=hidden name=\"IDT1\" size=\"20\" value=\"" + thread.getId() + "\">");
         if (user.getPass2() != null) {
            buffer.append("<input type=hidden name=\"PS21\" size=\"20\" value=\"" + user.getPass2() + "\">");
         }
         else {
            buffer.append("<input type=hidden name='PS11' size='20' value='" + user.getPass() + "'>");
         }
         buffer.append("<input type='submit' value='" + locale.getString("mess145") + "' name='OK'>");
         buffer.append("</td></tr>");
         buffer.append("</table></form>");
         buffer.append("</td></tr>");
         //Users can add custom answers 
         if (thread.getType() == ThreadType.QUEST2){
            boolean userVotes = false;
            for (int nodeIndex = 1; nodeIndex < nodes.size(); nodeIndex++) {
               IQuestNode questNode = nodes.get(nodeIndex);
               if (questNode.getUserId().equals(user.getId())) userVotes = true;
            }
            if (!userVotes){
               buffer.append("<tr><td>");
               buffer.append("<form  action='" + "/" + userURI + "/" + FJUrl.ADD_VOTE + "' method='post'><table align='center'>");
               buffer.append("<tr><td>");
               buffer.append(locale.getString("mess153") + ":<br>");
               buffer.append("<input type='text' name='P' size='100'>");
               buffer.append("<input type=hidden name='IDU2' size='20' value='" + user.getId() + "'>");
               buffer.append("<input type=hidden name='AUT2' size='20' value='" + user.getNick() + "'>");
               buffer.append("<input type=hidden name='IDT2' size='20' value='" + thread.getId() + "'>");
               if (user.getPass2() != null) {
                  buffer.append("<input type=hidden name='PS22' size='20' value='" + user.getPass2() + "'>");
               }
               else {
                  buffer.append("<input type=hidden name='PS12' size='20' value='" + user.getPass() + "'>");
               }
               buffer.append("</td></tr>");
               buffer.append("<tr><td align='center'>");
               buffer.append("<input type='checkbox' name='HD' value='1' checked>&nbsp;" + locale.getString("mess146") + "<br>");
               buffer.append("<input type='submit' value='" + locale.getString("mess145") + "' name='OK'>");
               buffer.append("</td></tr>");
               buffer.append("</table></form>");
               buffer.append("</td></tr>");
            }
         }
      }
      buffer.append("<tr><td align='CENTER'>");
      buffer.append("<b>" + locale.getString("mess152") + ": " + nvcs + "</b>");
      buffer.append("</td></tr>");
      buffer.append("<tr><td align='CENTER'>");
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
      for (int nodeIndex = 1; nodeIndex < nodes.size(); nodeIndex++) {
         IQuestNode questNode = nodes.get(nodeIndex);
         if (questNode.getType() == 0){
            buffer.append("<td align='LEFT' class='internal'></td>");
         }else if (questNode.getType() == 1){
            buffer.append("<td align='LEFT' class='internal'>" + questNode.getUserNick() + "</td>");
         }else if (questNode.getType() == 2){
            buffer.append("<td align='LEFT' class='internal'>" + locale.getString("mess144") + "</td>");
         }
         buffer.append("<td class='internal'>" + fd_body(questNode.getNode(), webapp) + "</td>");

         buffer.append("<td align='CENTER' class='internal'>");
         buffer.append(questNode.getGol() + "</td>");
         buffer.append("<td class='internal'><TABLE cellSpacing=0 cellPadding=0 width='" + Math.floor((questNode.getGol().doubleValue()/(nvcs == 0 ? 1 : nvcs))*300) + "' border=0><TR><TD align=left bgColor=red><FONT size=-3>&nbsp;</FONT></TD></TR></TABLE>");
         buffer.append("</td>");
         buffer.append("<td class='internal'>");
         buffer.append(Math.floor((questNode.getGol().doubleValue()/(nvcs == 0 ? 1 : nvcs))*1000)/10 + "%");
         buffer.append("</td></tr>");

      }
      buffer.append("</table>");
      if (user.isLogined() && userVoted && !thread.isClosed()){
         buffer.append("<form method='post' action='" + "/" + userURI + "/" + FJUrl.DELETE_VOICE + "' align='center'>");
         buffer.append("<input type=hidden name='IDT' size='20' value='" + thread.getId() + "'>");
         buffer.append(fd_form_add(user));
         buffer.append("<input type='submit' value='" + locale.getString("mess161") + "'>");
         buffer.append("</form>");
      }
      buffer.append("</td></tr>");
      return buffer;
   }

   private boolean isIgnored(Long userId, List<IIgnor> ignorList){
      for(int arrIndex=0; arrIndex < ignorList.size(); arrIndex++) {
         if(ignorList.get(arrIndex).getUser().getId().equals(userId)) {
            return true;
         }
      }
      return false;
   }
}
