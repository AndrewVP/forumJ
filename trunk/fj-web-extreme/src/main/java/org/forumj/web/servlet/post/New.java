/*
 * Copyright Andrew V. Pogrebnyak
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.forumj.web.servlet.post;

import static org.forumj.tool.Diletant.*;
import static org.forumj.tool.FJServletTools.*;
import static org.forumj.tool.PHP.*;
import static org.forumj.web.servlet.tool.FJServletTools.*;

import java.io.*;
import java.sql.SQLException;
import java.util.Date;

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
@WebServlet(urlPatterns = {"/" + FJUrl.ADD_THREAD}, name = FJServletName.ADD_THREAD)
public class New extends FJServlet {

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
            // Все нормально
            String head = request.getParameter("NHEAD");
            String body = request.getParameter("A2");
            String command = request.getParameter("comand");
            // Может пустая??
            if (!("".equals(head.trim()) || "".equals(body.trim()))) {
               // Не пустая
               /*Просмотр?*/
               Time threadTime = new Time(new Date().getTime());
               String rgTime = threadTime.toString("dd.MM.yyyy HH:mm");
               String ip = request.getRemoteAddr();
               String domen = gethostbyaddr(ip);
               if (command != null && "view".equalsIgnoreCase(command)){
                  buffer.append(new_view(locale, head, user, rgTime, ip, domen, body, request));
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
                  FJThread thread = new FJThread();
                  thread.setAuthId(user.getId());
                  thread.setHead(head);
                  thread.setNick(user.getNick());
                  thread.setSnall(0);
                  thread.setSnid(0);
                  thread.setFolderId((long) 1);
                  thread.setPcount(1);
                  thread.setType(0);
                  FJThreadDao threadDao = new FJThreadDao();
                  threadDao.create(thread, post);
                  // Подготавливаем текст поста.          
                  //bbcode
                  //                  String outBody=fd_bbcode(body);
                  //                  // смайлики      
                  //                  outBody=stripslashes(fd_smiles(outBody));
                  //                  //цензура      
                  //                  //                              $str_body=fd_cenz($str_body);
                  //                  // Заголовок      
                  //                  String outHead="<div style='font-family: Arial; font-size: 12pt; font-weight: bold;'>" + stripslashes(head) + "</div><br><br>";
                  //                  // Вступление.
                  //                  String mailHead="<html><head><title></title></head><body bgcolor=#EFEFEF>";
                  //                  mailHead=mailHead + "Вы получили это сообщение, потому что подписаны на рассылку сообщений на форуме <a href='http://www.diletant.com.ua/forum'>Дилетант</a>. <br>\r\n";
                  //                  mailHead=mailHead + "<a href='http://www.diletant.com.ua/forum/site.php?id=" + thread.getId() + "&post=" + post.getId()+ "'>Ссылка на форму</a><br><br><br>\r\n";      
                  //                  String mailFoot="</p></td></tr></table></body></html>";
                  //                  // Собираем шапку
                  //                  String mailPostHead="<table border='0' cellpadding='2' cellspacing='0' width='100%'>\r\n";
                  //                  
                  //                  mailPostHead=mailPostHead + "<tr style='background-color:#D1D7DC'>";
                  //                  mailPostHead=mailPostHead + "<td style='border:1px ridge; border-collapse: collapse; padding: 3px; border-color:#f1f7fC;'>\r\n";
                  //                  mailPostHead=mailPostHead + "<span style='font-family: Verdana; font-size: 8pt;'>Автор:&nbsp;</span><span style='font-family: Arial; font-size: 12pt; font-weight: bold;'>" + stripslashes(htmlspecialchars(user.getNick())) + "</span>\r\n";
                  //                  mailPostHead=mailPostHead + "<span style='font-family: Verdana; font-size: 8pt;'>&nbsp;" + chr(149) + "&nbsp;Дата:&nbsp;</span>";
                  ////                  mailPostHead=mailPostHead + "<span style='font-family: Verdana; font-size: 10pt;'>" + substr(rgTime,8,2) + "." + substr(rgTime,5,2) + "." + substr(rgTime,2,2) + "&nbsp;" + substr(rgTime,11,2) + ":" + substr(rgTime,14,2) + "&nbsp;</span>" + chr(149) + "\r\n";      
                  //                  mailPostHead=mailPostHead + "<span style='font-family: Verdana; font-size: 8pt;'>&nbsp;Хост:&nbsp;</span><span style='font-family: Verdana; font-size: 10pt;'>" + domen + "</span>\r\n";
                  //                  mailPostHead=mailPostHead + "</td></tr><tr><td><p style='font-family: Verdana; font-size: 10pt;'>\r\n";
                  //                  outBody=nl2br(outBody);
                  //                  String mailAll=mailHead + mailPostHead + outHead + outBody + mailFoot;
                  //                  // Вставляем код.         
                  //                  String server="smtp.freehost.com.ua";
                  //                  String from="diletant@diletant.com.ua";
                  //                  String subject ="Тест";
                  //                  String headers ="Content-type: text/html; charset='windows-1251'";
                  //                  headers="From: " + from + "\nSubject: " + subject + "\nX-Mailer: Diletant\n" + headers;
                  //mail("an.diletant@mail.ru", $subject, $strMailAll, $headers);
                  //mail("andrew@sunbay.com", $subject, $strMailAll, $headers);
                  // Отправляем в форум
                  buffer.append(successPostOut("3", "index.php"));
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

   public StringBuffer new_view(LocaleString locale, String head, IUser user, String $rgtime, String $str_ip, String $str_dom, String body, HttpServletRequest request) throws IOException, InvalidKeyException{
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
      /*Скрипты (submit поста)*/
      buffer.append(new_submit(locale.getString("mess128")));
      buffer.append("<link rel='icon' href='/favicon.ico' type='image/x-icon'>");
      buffer.append("<link rel='shortcut icon' href='/favicon.ico' type='image/x-icon'>");
      buffer.append("<title>" + head + "</title>");
      buffer.append("</head>");
      buffer.append("<body bgcolor=#EFEFEF>");
      buffer.append("<table class='content'>");
      buffer.append("<tr class='heads'>");
      buffer.append("<td  class='internal'>");
      /*"Закладка" последнего поста*/
      /*"Закладка" номера поста для ссылки из поиска, возврата после обработки игнора*/
      /*Тема*/
      buffer.append("<div class='nik'>");
      buffer.append("<b>&nbsp;&nbsp;" + fd_smiles(head) + "</b>");
      buffer.append("</div>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td class='matras'>");
      /*Ник*/
      buffer.append("<span class='tbtextnread'>" + user.getNick() + "</span>&nbsp;•&nbsp;");
      /*Дата*/
      
      buffer.append("<img border='0' src='smiles/icon_minipost.gif'>&nbsp;");
      buffer.append("<span class='posthead'>" + $rgtime + "</span>&nbsp;•");
      /*Хост*/ 
      if ($str_ip.trim().equalsIgnoreCase($str_dom.trim())){
         $str_dom = substr($str_dom, 0, strrpos($str_dom, ".")+1) + "---";
      }else{
         $str_dom = "---" + substr($str_dom, strpos($str_dom, ".") + 1);
      }
      
      buffer.append("&nbsp;<span class='posthead'>" + $str_dom + "</span>&nbsp;");
      /*игнорировать*/
      buffer.append("&nbsp;•<span class='posthead'>");
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
      buffer.append("<p class='post'>" + nl2br(fd_smiles(fd_bbcode(stripslashes(body)))) + "</p>");
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
      buffer.append("<form name='post' action='new.php' method='POST'>");
      buffer.append("<table width='100%'>");
      buffer.append("<tr>");
      buffer.append("<td colspan='2' align='CENTER'>");
      /*Тема*/
      buffer.append(locale.getString("mess4") + "&nbsp");
      buffer.append("<input class='mnuforumSm' type=text name='NHEAD' size='70' value='" +htmlspecialchars(stripslashes(head)) + "'>");
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
      buffer.append("<p>");
      buffer.append(locale.getString("mess12"));
      buffer.append("</p>");
      buffer.append("</td>");
      buffer.append("</tr>");
      /*Пост*/
      buffer.append("<tr>");
      /*Смайлики*/
      buffer.append("<td valign='TOP' width='100%' height='100%'>");
      /*Смайлики*/
      buffer.append(smiles_add(locale.getString("mess11")));
      buffer.append("</td>");
      buffer.append("<td align='CENTER' valign='top'>");
      buffer.append(autotags_add());
      /* текстарий*/
      buffer.append("<p>");
      buffer.append("<textarea class='mnuforumSm' rows='20' id='ed1' name='A2' cols='55'>" + htmlspecialchars(stripslashes(body)) + "</textarea>");
      buffer.append("</p>");
      /*Кнопки*/
      buffer.append("<table>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append(fd_button(locale.getString("mess13"),"new_submit(\"write\");","B1", "1"));
      buffer.append("</td>");
      buffer.append("<td>");
      buffer.append(fd_button(locale.getString("mess63"),"new_submit(\"view\");","B3", "1"));
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      /*Прередаем нужные пераметры...*/
      buffer.append(fd_form_add(user));
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
}
