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
import static org.forumj.tool.FJServletTools.*;
import static org.forumj.web.servlet.tool.FJServletTools.*;

import java.io.*;
import java.sql.SQLException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.*;
import org.forumj.common.db.entity.*;
import org.forumj.common.db.service.*;
import org.forumj.common.exception.*;
import org.forumj.common.tool.*;
import org.forumj.email.FJEMail;
import org.forumj.network.web.Command;
import org.forumj.tool.LocaleString;

import com.tecnick.htmlutils.htmlentities.HTMLEntities;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class Write{

   public void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      try {
         HttpSession session = request.getSession();
         LocaleString locale = (LocaleString) session.getAttribute("locale");
         IUser user = (IUser) session.getAttribute("user");
         if (user != null && !user.isBanned() && user.isLogined()){
            String head = request.getParameter("NHEAD");
            String body = request.getParameter("A2");
            String strCommand = request.getParameter("comand");
            /* Все нормально*/
            /* Может пустое??*/
            String threadId = request.getParameter("IDT");
            if (head != null && body!= null && head.trim().length() > 0 && body.trim().length() > 0) {
               /* Не пустое*/
               /* Автор кто?*/
               Time threadTime = new Time(new Date().getTime());
               String rgTime = threadTime.toString("dd.MM.yyyy HH:mm");
               String ip = request.getRemoteAddr();
               String domen = ip;
               /*Просмотр или запись?*/
               Command command = Command.valueOfString(strCommand);
               PostService postService = FJServiceHolder.getPostService();
               switch (command){
                  case PREVIEW_NEW_POST:
                  case PREVIEW_EDITED_POST:
                     buffer.append(view(locale, request, user, head, ip, domen, threadId, rgTime, body, command, webapp, userURI));
                     break;
                  case CREATE_POST:
                     createNewPost(body, user, domen, ip, head, Long.valueOf(threadId), postService, locale);
                     break;
                  case UPDATE_POST:
                     String postId = request.getParameter("IDB");
                     write_edit(body, user, domen, ip, head, Long.valueOf(threadId), Long.valueOf(postId), postService);
                     break;
               }
               if(command ==  Command.CREATE_POST || command ==  Command.UPDATE_POST){
                  StringBuilder exit = new StringBuilder("/").append(userURI).append("/");
                  if (request.getParameter("no_exit") != null){
                     exit.append(FJUrl.VIEW_THREAD).append("?id=").append(threadId).append("&end=1#end");
                  }else{
                     exit.append(FJUrl.INDEX);
                  }
                  response.sendRedirect(exit.toString());
               }else if (command ==  Command.PREVIEW_NEW_POST || command ==  Command.PREVIEW_EDITED_POST){
                  response.setContentType("text/html; charset=UTF-8");
                  PrintWriter writer = response.getWriter();
                  String out = buffer.toString();
                  writer.write(out);
               }else{
                  // command had not sent
                  StringBuilder exit = new StringBuilder("/").append(userURI).append("/");
                  if (request.getParameter("no_exit") != null){
                     exit.append(FJUrl.VIEW_THREAD).append("?id=").append(threadId).append("&end=1#end");
                  }else{
                     exit.append(FJUrl.INDEX);
                  }
                  response.sendRedirect(exit.toString());
               }
            }else{
               // TODO validation - empty body or head or threadId
               StringBuilder exit = new StringBuilder("/").append(userURI).append("/");
               if (request.getParameter("no_exit") != null){
                  exit.append(FJUrl.VIEW_THREAD).append("?id=").append(threadId).append("&end=1#end");
               }else{
                  exit.append(FJUrl.INDEX);
               }
               response.sendRedirect(exit.toString());
            }
         }else{
            // Session expired
            StringBuilder exit = new StringBuilder("/").append(userURI).append("/").append(FJUrl.INDEX);
            response.sendRedirect(exit.toString());
         }
      } catch (Throwable e) {
         e.printStackTrace();
         buffer = new StringBuffer();
         buffer.append(errorOut(e));
         response.setContentType("text/html; charset=UTF-8");
         PrintWriter writer = response.getWriter();
         String out = buffer.toString();
         writer.write(out);
      }
   }

   private StringBuffer view(LocaleString locale, HttpServletRequest request, IUser user, String head, String str_ip, String str_dom, String idt, String lptime, String body, Command command, String webapp, String userURI) throws IOException, InvalidKeyException{
      StringBuffer buffer = new StringBuffer();
      buffer.append("<!doctype html public \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
      buffer.append("<html>");
      buffer.append("<head>");
      buffer.append("<meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
      // Стили
      buffer.append(loadCSS("/css/style.css"));
      // Скрипты (смайлики)
      buffer.append(loadJavaScript("/js/smile_.js"));
      // Скрипты (автовставка тегов)
      buffer.append(loadJavaScript("/js/jstags.js"));
      // Скрипты (submit поста)
      buffer.append(post_submit(locale.getString("mess128")));
      buffer.append("<link rel='icon' href='/favicon.ico' type='image/x-icon'>");
      buffer.append("<link rel='shortcut icon' href='/favicon.ico' type='image/x-icon'>");
      buffer.append("<title>");
      buffer.append("</title>");
      buffer.append("</head>");
      buffer.append("<body bgcolor=#EFEFEF>");
      buffer.append("<table class='content'>");
      buffer.append("<tr class=heads>");
      buffer.append("<td  class=internal>");
      /*"Закладка" последнего поста*/
      /*"Закладка" номера поста для ссылки из поиска, возврата после обработки игнора*/
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
      buffer.append("<img border='0' src='").append("/").append(FJUrl.STATIC).append("/smiles/icon_minipost.gif'>&nbsp;");
      buffer.append("<span class='posthead'>" + lptime+ "</span>");
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
         buffer.append("<a href='" + "/" + userURI + "/" + FJUrl.SETTINGS + "?id=9' rel='nofollow'><img border='0' src='").append("/").append(FJUrl.STATIC).append("/").append("smiles/no_avatar.gif'></a>");
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
      buffer.append(menu(request, user, locale, false, webapp, userURI));
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<table>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<form name='post' action='" + FJUrl.ADD_POST + "' method='post'>");
      buffer.append("<table width='100%'>");
      /*Тема*/
      buffer.append("<tr>");
      buffer.append("<td colspan='2' align='CENTER'>");
      buffer.append(locale.getString("mess59") + ":&nbsp;");
      buffer.append(fd_input("NHEAD", HtmlChars.convertHtmlSymbols(removeSlashes(head)), "70", "1"));
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      /*Смайлики заголовок*/
      buffer.append("<td width='400' align='CENTER'>");
      buffer.append("<p>");
      buffer.append(locale.getString("mess21") + ":");
      buffer.append("</p>");
      buffer.append("</td>");
      /*Приглашение*/
      buffer.append("<td align='CENTER'>");
      buffer.append("<p>" + locale.getString("mess12") + "</p>");
      buffer.append("</td>");
      buffer.append("</tr>");
      /*Пост*/
      buffer.append("<tr>");
      buffer.append("<td valign='TOP' width='100%' height='100%'>");
      /*Смайлики*/
      buffer.append(smiles_add(locale.getString("mess11")));
      buffer.append("</td>");
      buffer.append("<td width='500' align='CENTER' valign='top'>");
      /*Автотеги*/
      buffer.append(autotags_add());
      /* текстарий*/
      buffer.append("<p>");
      buffer.append("<textarea class='mnuforumSm' rows='20' id='ed1' name='A2' cols='55'>" + HTMLEntities.htmlentities(removeSlashes(body)) + "</textarea>");
      buffer.append("</p>");
      String checked="";
      if (request.getParameter("no_exit") != null){
         checked="CHECKED";
      }
      buffer.append("<input type='checkbox'"+  checked+" name='no_exit' value='1'>");
      buffer.append(locale.getString("mess123"));
      /*Кнопки*/
      buffer.append("<table>");
      buffer.append("<tr>");
      buffer.append("<td>");
      if (command == Command.PREVIEW_NEW_POST){
         buffer.append(fd_button(locale.getString("mess13"),"post_submit(\"" + Command.CREATE_POST.getCommand() + "\");","B1", "1"));
      }else if (command == Command.PREVIEW_EDITED_POST){
         buffer.append(fd_button(locale.getString("mess13"),"post_submit(\"" + Command.UPDATE_POST.getCommand() + "\");","B1", "1"));
      }
      buffer.append("</td>");
      buffer.append("<td>");
      buffer.append(fd_button(locale.getString("mess63"),"post_submit(\"" + command.getCommand() + "\");","B3", "1"));
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      /*Прередаем нужные пераметры...*/
      buffer.append(fd_form_add(user));
      /* Если редактируем*/
      if (idt != null) {
         buffer.append("<input type=hidden name='IDB' value='"+ request.getParameter("IDB")+"'>");
      }
      /*id темы*/
      buffer.append("<input type=hidden name='IDT' value='"+ request.getParameter("IDT")+"'>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("</td>");
      buffer.append("<td align='CENTER' valign='top'>");
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
   
   private void createNewPost(String body, IUser user, String domen, String ip, String head, Long threadId, PostService postService, LocaleString locale) throws DBException, ConfigurationException, IOException, SQLException, AddressException, InvalidKeyException, MessagingException{
      IFJPost post = postService.getPostObject();
      post.setState(1);
      post.setThreadId(threadId);
      post.setBody(body);
      post.setAuth(user.getId());
      post.setAuthor(user);
      post.setDomen(domen);
      post.setIp(ip);
      post.setNred(0);
      post.setTitle(head);
      post.setThreadId(threadId);
      post.setCreateTime(new Date().getTime());
      postService.create(post);
      FJEMail.sendSuscribedPost(post, user);
   }
   private void write_edit(String body, IUser user, String domen, String ip, String head, Long threadId, Long postId, PostService postService) throws DBException, ConfigurationException, IOException, SQLException{
      IFJPost post = postService.read(postId);
      post.setBody(body);
      post.setDomen(domen);
      post.setIp(ip);
      post.setNred(post.getNred() + 1);
      post.setEditTime(new Date().getTime());
      post.setTitle(head);
      postService.update(post);
   }

}
