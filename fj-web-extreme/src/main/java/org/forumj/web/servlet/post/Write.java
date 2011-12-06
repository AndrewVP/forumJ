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

import static org.forumj.common.tool.PHP.*;
import static org.forumj.tool.Diletant.*;
import static org.forumj.tool.FJServletTools.*;
import static org.forumj.web.servlet.tool.FJServletTools.*;

import java.io.*;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.*;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.exception.*;
import org.forumj.common.tool.Time;
import org.forumj.db.dao.FJPostDao;
import org.forumj.db.entity.*;
import org.forumj.tool.LocaleString;
import org.forumj.web.servlet.FJServlet;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.ADD_POST}, name = FJServletName.ADD_POST)
public class Write extends FJServlet {

   /**
    * {@inheritDoc}
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         StringBuffer buffer = new StringBuffer();
         HttpSession session = request.getSession();
         LocaleString locale = (LocaleString) session.getAttribute("locale");
         IUser user = (IUser) session.getAttribute("user");
         if (user != null && !user.isBanned() && user.isLogined()){
            String head = request.getParameter("NHEAD");
            String body = request.getParameter("A2");
            String command = request.getParameter("comand");
            /* Все нормально*/
            /* Может пустое??*/
            if (!("".equals(head.trim()) || "".equals(body.trim()))) {
               /* Не пустое*/
               /* Добавляем Сообщение*/
               //               $str_body=mysql_real_escape_string($_POST['A2']);
               /* Текст заголовка*/
               //               $str_head=mysql_real_escape_string($_POST['NHEAD']);
               /* Добавляем заголовок*/
               String threadId = request.getParameter("IDT");
               String idt = request.getParameter("IDT");
               /* Автор кто?*/
               Time threadTime = new Time(new Date().getTime());
               String rgTime = threadTime.toString("dd.MM.yyyy HH:mm");
               String ip = request.getRemoteAddr();
               String domen = gethostbyaddr(ip);
               /*Просмотр или запись?*/
               if (command != null && "view".equalsIgnoreCase(command)){
                  buffer.append(view(locale, request, user, head, ip, domen, idt, rgTime, body));
               }else{
                  /* Записываем или редактируем???*/
                  if (idt != null) {
                     /*новый пост*/
                     write_new(body, user, domen, ip, head, Long.valueOf(threadId));
                  }else{
                     String postId = request.getParameter("IDB");
                     /* Редактируем старый пост*/
                     write_edit(body, user, domen, ip, head, Long.valueOf(threadId), Long.valueOf(postId));
                  }
                  /* Отправляем в форум*/
                  /*Остаемся в ветке?*/
                  String $exit = "index.php";
                  if (request.getParameter("no_exit") != null){
                     $exit="tema.php?id=" + threadId + "&end=1#end";
                  }
                  buffer.append(successPostOut("3", $exit));
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
      } catch (NumberFormatException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (ConfigurationException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   private StringBuffer view(LocaleString locale, HttpServletRequest request, IUser user, String head, String $str_ip, String $str_dom, String idt, String $lptime, String body) throws IOException, InvalidKeyException{
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
      buffer.append("<b>&nbsp;&nbsp;" + fd_smiles(stripslashes(head))+ "</b>");
      buffer.append("</div>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td class='matras'>");
      /*Ник*/
      buffer.append("<span class='tbtextnread'>");
      buffer.append(user.getNick());
      buffer.append("</span>&nbsp;•&nbsp;");
      /*Дата*/

      buffer.append("<img border='0' src='smiles/icon_minipost.gif'>&nbsp;");

      buffer.append("<span class='posthead'>" + $lptime+ "</span></span>&nbsp;•&nbsp;");
      /*Хост*/ 
      if ($str_ip.trim().equalsIgnoreCase($str_dom.trim())){
         $str_dom = substr($str_dom, 0, strrpos($str_dom, ".")+1) + "---";
      }else{
         $str_dom = "---" + substr($str_dom, strpos($str_dom, ".") + 1);
      }

      buffer.append("&nbsp;<span class='posthead'>");
      buffer.append($str_dom);
      buffer.append("</span>&nbsp;");
      /*игнорировать*/
      buffer.append("&nbsp;•");
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
      /* Выводим текст*/
      buffer.append("<p class=post>" + nl2br(fd_smiles(fd_bbcode(stripslashes(body))))+ "</p>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</div>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append(menu(request, user, locale, false));
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<table>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<form name='post' action='write.php' method='POST'>");
      buffer.append("<table width='100%'>");
      /*Тема*/
      buffer.append("<tr>");
      buffer.append("<td colspan='2' align='CENTER'>");
      buffer.append(locale.getString("mess59") + ":&nbsp;");
      buffer.append("<input class='mnuforumSm' type=text name='NHEAD' size='70' value='" + stripslashes(head) +"'>");
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
      buffer.append("<textarea rows='20' class='mnuforumSm'  id='ed1' name='A2' cols='55'>"+stripslashes(body)+"</textarea>");
      buffer.append("</p>");
      String $checked="";
      if (request.getParameter("no_exit") != null){
         $checked="CHECKED";
      }
      buffer.append("<input type='checkbox'"+  $checked+" name='no_exit' value='1'>");
      buffer.append(locale.getString("mess123"));
      /*Кнопки*/
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
   
   private void write_new(String body, IUser user, String domen, String ip, String head, Long threadId) throws DBException, ConfigurationException, IOException, SQLException{
      FJPost post = new FJPost();
      FJPostBody postBody = new FJPostBody();
      FJPostHead postHead = new FJPostHead();
      post.setState(1);
      post.setBody(postBody);
      post.setHead(postHead);
      post.setThreadId(threadId);
      postBody.setBody(body);
      postHead.setAuth(user.getId());
      postHead.setDomen(domen);
      postHead.setIp(ip);
      postHead.setNred(0);
      postHead.setTitle(head);
      postHead.setThreadId(threadId);
      postHead.setCreateTime(new Date().getTime());
      FJPostDao postDao = new FJPostDao();
      postDao.create(post);
   }
   private void write_edit(String body, IUser user, String domen, String ip, String head, Long threadId, Long postId) throws DBException, ConfigurationException, IOException, SQLException{
      FJPostDao postDao = new FJPostDao();
      FJPost post = postDao.read(postId);
      FJPostBody postBody = post.getBody();
      FJPostHead postHead = post.getHead();
      postBody.setBody(body);
      postHead.setDomen(domen);
      postHead.setIp(ip);
      postHead.setNred(postHead.getNred() + 1);
      postHead.setEditTime(new Date().getTime());
      postHead.setTitle(head);
      postDao.update(post);
   }

}
