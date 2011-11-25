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
import static org.forumj.web.servlet.tool.FJServletTools.*;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.common.db.entity.IUser;
import org.forumj.db.dao.*;
import org.forumj.db.entity.*;
import org.forumj.exception.InvalidKeyException;
import org.forumj.tool.LocaleString;
import org.forumj.web.servlet.FJServlet;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.SEND_PIVATE_MESSAGE}, name=FJServletName.SEND_PIVATE_MESSAGE)
public class Send extends FJServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         StringBuffer buffer = new StringBuffer();
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
         FJMail mail = new FJMail();
         FJUserDao userDao = new FJUserDao();
         IUser receiver = userDao.read(receiverNickParameter);
         mail.setSender(user);
         mail.setReceiver(receiver);
         mail.setBody(bodyParameter);
         mail.setSubject(headParameter);
         mail.setCreateDate(currentDate);
         mail.setSentDate(currentDate);
         if (user != null && !user.isBanned() && user.isLogined()){
            if (!isEmptyParameter(comandParameter) && comandParameter.equals("view")){
               buffer.append(view(mail, locale, request));
            }else{
               FJMailDao dao = new FJMailDao();
               dao.create(mail);
               buffer.append(successPostOut("0", "control.php?id=" + idParameter));
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

   private StringBuffer view(FJMail mail, LocaleString locale, HttpServletRequest request) throws InvalidKeyException, IOException{
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
      buffer.append("<div class=nik>" + fd_head(mail.getSubject()) + "</div>");
      /*Тело.*/
      buffer.append("<div class=post>" + fd_body(mail.getBody()) + "</div>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append(menu(request, mail.getSender(), locale, false));
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<table>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<form name='post' action='send.php' method='POST'>");
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
      buffer.append(smiles_add(locale.getString("mess11")));
      buffer.append("</td>");
      buffer.append("<td width='500' align='CENTER' valign='top'>");
      /*Автотеги*/
      buffer.append(autotags_add());
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

}
