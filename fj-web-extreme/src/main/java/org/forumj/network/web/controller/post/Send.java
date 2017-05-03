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

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.common.db.entity.*;
import org.forumj.common.db.service.*;
import org.forumj.common.exception.InvalidKeyException;
import org.forumj.tool.LocaleString;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class Send{

   public void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws ServletException, IOException {
      try {
         HttpSession session = request.getSession();
         IUser user = (IUser) session.getAttribute("user");
         LocaleString locale = (LocaleString) session.getAttribute("locale");
         String comandParameter = request.getParameter("comand");
         // TODO parameter must be added!
         String idParameter = request.getParameter("id");
         String headParameter = request.getParameter("NHEAD");
         String bodyParameter = request.getParameter("A2");
         String receiverNickParameter = request.getParameter("RCVR");
         Date currentDate = new Date();
         MailService mailService = FJServiceHolder.getMailService();
         IFJMail mail = mailService.getMailObject();
         UserService userService = FJServiceHolder.getUserService();
         IUser receiver = userService.read(receiverNickParameter);
         mail.setSender(user);
         mail.setReceiver(receiver);
         mail.setBody(bodyParameter);
         mail.setSubject(headParameter);
         mail.setCreateDate(currentDate);
         mail.setSentDate(currentDate);
         if (user != null && !user.isBanned() && user.isLogined()){
            if (!isEmptyParameter(comandParameter) && comandParameter.equals("view")){
               StringBuffer buffer = new StringBuffer();
               buffer.append(view(mail, locale, request, webapp, userURI));
               response.setContentType("text/html; charset=UTF-8");
               response.getWriter().write(buffer.toString());
            }else{
               mailService.create(mail);
               //TODO Magic integer!
               StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.SETTINGS).append("?id=").append(idParameter);
               response.sendRedirect(url.toString());
            }
         }else{
            // Session expired
            StringBuilder exit = new StringBuilder("/").append(userURI).append("/").append(FJUrl.INDEX);
            response.sendRedirect(exit.toString());
         }
      } catch (Throwable e) {
         e.printStackTrace();
         StringBuffer buffer = new StringBuffer();
         buffer.append(errorOut(e));
         response.setContentType("text/html; charset=UTF-8");
         response.getWriter().write(buffer.toString());
      }
   }

   private StringBuffer view(IFJMail mail, LocaleString locale, HttpServletRequest request, String webapp, String userURI) throws InvalidKeyException, IOException{
      StringBuffer buffer = new StringBuffer();
      /*Тело.*/
      buffer.append("<html>");
      buffer.append("<head>");
      buffer.append("<meta http-equiv='content-type' content='text/html; charset=windows-1251'>");
      // Стили
      buffer.append(loadCSS("/css/style.css"));
      // Скрипты (флажки)
      buffer.append(loadJavaScript("/js/jsmain_chek.js"));
      // Скрипты (автовставка тегов)
      buffer.append(loadJavaScript("/js/jstags.js"));
      // Скрипты (submit поста)
      buffer.append(send_submit(locale));
      buffer.append("<link rel='icon' href='/favicon.ico' type='image/x-icon'>");
      buffer.append("<link rel='shortcut icon' href='/favicon.ico' type='image/x-icon'>");
      buffer.append("<title>");
      buffer.append(locale.getString("mess127"));
      buffer.append("</title>");
      buffer.append("</head>");
      buffer.append("<body bgcolor='#EFEFEF'>");
      buffer.append("<table border='0' style='border-collapse: collapse' width='100%'>");
      /*Шапка*/
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<table class='control'>");
      buffer.append("<tr class=heads>");
      buffer.append("<td colspan=5 class=internal>");                                 
      buffer.append("<span class=tbtext>");
      buffer.append(locale.getString("mess61"));
      buffer.append(":&nbsp;");
      buffer.append(dateToString(mail.getSentDate(), "yyyy-MM-dd HH:mm:ss"));
      buffer.append("&nbsp;");
      buffer.append(locale.getString("mess58"));
      buffer.append(":&nbsp;");
      buffer.append("</span>");
      buffer.append("<span class=nick>");
      buffer.append(mail.getSender().getNick());
      buffer.append("</span>");
      buffer.append("<span class='tbtext'>");
      buffer.append("&nbsp;");
      buffer.append(locale.getString("mess19"));
      buffer.append(":&nbsp;");
      buffer.append("</span>");
      buffer.append("<span class='nick'>");
      buffer.append(mail.getReceiver().getNick());
      buffer.append("</span>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td colspan='5' class='internal'>");
      /*Заголовок.*/
      buffer.append("<div class=nik>" + fd_head(mail.getSubject(), webapp) + "</div>");
      /*Тело.*/
      buffer.append("<div class=post>" + fd_body(mail.getBody(), webapp) + "</div>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append(menu(request, mail.getSender(), locale, false, webapp, userURI));
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<table>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<form name='post' action='/").append(userURI).append("/").append(FJUrl.SEND_PIVATE_MESSAGE).append("' method='post'>");
      buffer.append("<table width='100%'>");
      buffer.append("<tr>");
      buffer.append("<td width='100%'>");
      buffer.append("<table width='100%'>");
      /*От*/
      buffer.append("<tr>");
      buffer.append("<td align='LEFT'>");
      buffer.append("<div class='mnuprof'>");
      buffer.append(locale.getString("mess58") + "&nbsp;");
      buffer.append("</div>");
      buffer.append("</td>");
      buffer.append("<td>");
      buffer.append("<div class='mnuprof'>");
      buffer.append(mail.getSender().getNick());
      buffer.append("</div>");
      buffer.append("</td>");
      buffer.append("</tr>");
      /*Кому*/
      buffer.append("<tr>");
      buffer.append("<td align='LEFT'>");
      buffer.append("<div class='mnuprof'>");
      buffer.append(locale.getString("mess28") + "&nbsp;");
      buffer.append("</div>");
      buffer.append("</td>");
      buffer.append("<td colspan='2'>");
      buffer.append("<input type=text class='mnuforumSm' value='" + mail.getReceiver().getNick() + "' name='RCVR' size='30'>");
      buffer.append("</td>");
      buffer.append("</tr>");
      /*Тема*/
      buffer.append("<tr>");
      buffer.append("<td align='LEFT'>");
      buffer.append("<div class='mnuprof'>");
      buffer.append(locale.getString("mess59") + "&nbsp;");
      buffer.append("</div>");
      buffer.append("</td>");
      buffer.append("<td>");
      buffer.append("<input type=text class='mnuforumSm' name='NHEAD' value='" + mail.getSubject() + "' size='100'>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<table>");
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
      buffer.append(smiles_add(locale.getString("mess11"), webapp));
      buffer.append("</td>");
      buffer.append("<td width='500' align='CENTER' valign='top'>");
      /*Автотеги*/
      buffer.append(autotags_add(webapp));
      /* текстарий*/
      buffer.append("<p>");
      buffer.append("<textarea rows='20' class='mnuforumSm' id='ed1' name='A2' cols='55'>" + mail.getBody() + "</textarea>");
      buffer.append("</p>");
      /*Кнопки*/
      buffer.append("<table>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append(fd_button(locale.getString("mess13"),"send_submit(\"write\");","B1", "1"));
      buffer.append("</td>");
      buffer.append("<td>");
      buffer.append(fd_button(locale.getString("mess63"),"send_submit(\"view\");","B3", "1"));
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      /*Прередаем нужные пераметры...*/
      buffer.append(fd_form_add(mail.getSender()));
      buffer.append("</td>");
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
   private boolean isEmptyParameter(String parameter){
      return (parameter == null || "".equals(parameter));
   }
}
