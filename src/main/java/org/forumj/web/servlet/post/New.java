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
import static org.forumj.tool.FJServletTools.new_view;
import static org.forumj.tool.PHP.*;

import java.io.*;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.forumj.db.dao.FJThreadDao;
import org.forumj.db.entity.*;
import org.forumj.exception.*;
import org.forumj.tool.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebServlet(urlPatterns = {"/new.php"}, name="new")
public class New extends HttpServlet {

   private static final long serialVersionUID = -1256025984434277731L;

   /**
    * {@inheritDoc}
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         StringBuffer buffer = new StringBuffer();
         HttpSession session = request.getSession();
         String userId = request.getParameter("IDU");
         String password = request.getParameter("PS1");
         boolean firstPassword = true;
         if (password == null){
            password = request.getParameter("PS2");
            firstPassword = false;
         }
         LocaleString locale = (LocaleString) session.getAttribute("locale");
         User user = fd_guard(Long.valueOf(userId), password, firstPassword);
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
      }
   }

}
