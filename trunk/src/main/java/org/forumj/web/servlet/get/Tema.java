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
import org.forumj.db.dao.TemaDao;
import org.forumj.db.entity.*;
import org.forumj.exception.InvalidKeyException;
import org.forumj.tool.LocaleString;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebServlet(urlPatterns = {"/" + FJUrl.VIEW_THREAD}, name = FJServletName.VIEW_THREAD)
public class Tema extends HttpServlet {

   private static final long serialVersionUID = 64298210092336195L;

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
         // Номер поста, на который отвечаем
         String replyPostId = request.getParameter("reply");
         LocaleString locale = (LocaleString) session.getAttribute("locale");
         User user = (User) session.getAttribute("user");
         TemaDao temaDao = new TemaDao(threadId, user);
         session.setAttribute("page", pageNumber);
         session.setAttribute("id", threadId);
         session.setAttribute("where", request.getContextPath() + "?id=$gid&page=$pg");
         // Зашли с поиска?
         String msg = request.getParameter("msg");
         int countPosts = 0;
         if (msg != null && !"".equals(msg.trim())){
            countPosts = temaDao.getPostsCountInThread(new Long(msg));
            pageNumber=ceil(countPosts/user.getPt());
         }
         // Записываем счетчики
         // Робот?
         if (!isRobot(request)){
            // Нет
            temaDao.setSeen();
         }
         String title = temaDao.getTitle();
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
         buffer.append("форум Дилетантов :: "+title);
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
         // Сколько страниц?
         Integer count = temaDao.getPostsCountInThread(null);
         Integer couP = ceil((double)count/user.getPt())+1;
         // Если цитирование или последний пост, то нам на последнюю
         boolean lastPost = false;
         String end = request.getParameter("end");
         if (replyPostId != null && !"".equals(replyPostId.trim()) || isset(end)){
            pageNumber = couP-1;
            lastPost = true;
         }
         int nfirstpost = (pageNumber-1)*user.getPt();
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
         int i3=pageNumber*user.getPt();
         if (i3>count) {
            i3=count-(pageNumber-1)*user.getPt();
         }else{
            i3=user.getPt();
         }
         // Получаем массив постов
         List<Post> postsList = temaDao.getPostsList(fd_timezone_hr(user.getTimeZone()), fd_timezone_mn(user.getTimeZone()), nfirstpost,i3, locale, pageNumber, lastPost);
         // Тема
         // Выводим строки
         for (int postIndex = 0; postIndex < postsList.size(); postIndex++) {
            Post post = postsList.get(postIndex);
            buffer.append(post.toString());
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
            if (temaDao.isUserSubscribed(user.getId())){
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
            String head=title;
            Post replyPost = null;
            // Если цитируем/редактируем
            if (replyPostId != null && !"".equals(replyPostId.trim())) {
               replyPost = temaDao.getPost(Long.valueOf(replyPostId));
               // Редактируем?
               head=stripslashes(replyPost.getHead());
               if (replyPost.getNick().equalsIgnoreCase(user.getNick())) {
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
                  textarea+=stripslashes(replyPost.getBody());
               }else if (ans != null){
                  textarea+="[quote][b]"+stripslashes(replyPost.getNick()) + "[/b]";
                  textarea+=locale.getString("mess14")+chr(13);
                  textarea+=stripslashes(replyPost.getBody()) + "[/quote]";
               }else{
                  textarea+="[b]"+stripslashes(replyPost.getNick()) + "[/b]";
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
            if (replyPostId != null && !"".equals(replyPostId.trim()) && (replyPost.getIdu() == user.getId())){
               buffer.append("<input type=hidden name='IDB' size='20' value='" + replyPostId + "'>");
               buffer.append("<input type=hidden name='IDTbl' size='20' value='" + replyPost.getTablePost() + "'>");
               buffer.append("<input type=hidden name='IDPst' size='20' value='" + replyPost.getId().toString() + "'>");
               buffer.append("<input type=hidden name='IDTblHead' size='20' value='" + replyPost.getTableHead() + "'>");
               buffer.append("<input type=hidden name='IDHead' size='20' value='" + replyPost.getId().toString() + "'>");
            }
            //id темы
            buffer.append("<input type=hidden name='IDT' size='20' value='" + threadId + "'>");
            if (temaDao.isQuest()){
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
}
