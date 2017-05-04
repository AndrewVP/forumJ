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
package org.forumj.network.web.controller.post;

import static org.forumj.network.web.Command.CREATE_THREAD;
import static org.forumj.network.web.Command.PREVIEW_NEW_THREAD;
import static org.forumj.tool.Diletant.*;
import static org.forumj.tool.FJServletTools.menu;
import static org.forumj.web.servlet.tool.FJServletTools.*;
import org.forumj.network.web.resources.ResourcesBuilder;
import java.io.*;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.common.db.entity.*;
import org.forumj.common.db.service.*;
import org.forumj.common.exception.InvalidKeyException;
import org.forumj.common.tool.*;
import org.forumj.common.web.Pin;
import org.forumj.network.web.Command;
import org.forumj.tool.LocaleString;

import com.tecnick.htmlutils.htmlentities.HTMLEntities;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class New{

   public void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      PrintWriter writer = response.getWriter();
      response.setContentType("text/html; charset=UTF-8");
      try {
         HttpSession session = request.getSession();
         LocaleString locale = (LocaleString) session.getAttribute("locale");
         IUser user = (IUser) session.getAttribute("user");
         if (user != null && !user.isBanned() && user.isLogined()){
            // Все нормально
            String head = request.getParameter("NHEAD");
            String body = request.getParameter("A2");
            String strCommand = request.getParameter("comand");
            Command command = Command.valueOfString(strCommand);
            // Может пустая??
            if (!("".equals(head.trim()) || "".equals(body.trim()))) {
               // Не пустая
               /*Просмотр?*/
               Time threadTime = new Time(new Date().getTime());
               String rgTime = threadTime.toString("dd.MM.yyyy HH:mm");
               String ip = request.getRemoteAddr();
               //TODO need to be implemented
               String domen = ip;
               if (command == PREVIEW_NEW_THREAD){
                  buffer.append(new_view(locale, head, user, rgTime, ip, domen, body, request, webapp, userURI));
                  writer.write(buffer.toString());
               }else if (command == CREATE_THREAD){
                  PostService postService = FJServiceHolder.getPostService();
                  IFJPost post = postService.getPostObject();
                  post.setState(1);
                  post.setBody(body);
                  post.setAuth(user.getId());
                  post.setAuthor(user);
                  post.setDomen(domen);
                  post.setIp(ip);
                  post.setNred(0);
                  post.setTitle(head);
                  ThreadService treadService = FJServiceHolder.getThreadService();
                  IFJThread thread = treadService.getThreadObject();
                  thread.setAuthId(user.getId());
                  thread.setHead(head);
                  thread.setNick(user.getNick());
                  thread.setSnall(0);
                  thread.setSnid(0);
                  thread.setFolderId((long) 1);
                  thread.setPostsAmount(1);
                  thread.setDock(Pin.COMMON);
                  treadService.create(thread, post);
                  // Отправляем в форум
                  StringBuilder exit = new StringBuilder("/").append(userURI).append("/").append(FJUrl.INDEX);
                  response.sendRedirect(exit.toString());
               }
            }else{
               // Пустая
               // TODO validation - empty body or head
               StringBuilder exit = new StringBuilder("/").append(userURI).append("/").append(FJUrl.NEW_THREAD);
               response.sendRedirect(exit.toString());
            }
         }else{
            // session expired
            StringBuilder exit = new StringBuilder("/").append(userURI).append("/").append(FJUrl.INDEX);
            response.sendRedirect(exit.toString());
         }   
      } catch (Throwable e) {
         buffer = new StringBuffer();
         buffer.append(errorOut(e));
         e.printStackTrace();
         response.setContentType("text/html; charset=UTF-8");
         writer.write(buffer.toString());
      }
   }

   public StringBuffer new_view(LocaleString locale, String head, IUser user, String rgtime, String str_ip, String str_dom, String body, HttpServletRequest request, String webapp, String userURI) throws IOException, InvalidKeyException{
      StringBuffer buffer = new StringBuffer();
      buffer.append("<!doctype html public \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
      buffer.append("<html>");
      buffer.append("<head>");
      buffer.append("<meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
      /* Стили*/
      buffer.append(ResourcesBuilder.getStyleCSS(webapp));
      buffer.append("<script language='javascript' type='text/javascript'>\n");
      buffer.append("var webapp='").append(webapp.isEmpty() ? "" : "/" + webapp).append("';\n");
      buffer.append("</script>\n");
      // Скрипты (смайлики)
      buffer.append(loadJavaScript("/js/smile_.js"));
      // Скрипты (автовставка тегов)
      buffer.append(loadJavaScript("/js/jstags.js"));
      /*Скрипты (submit поста)*/
      buffer.append(new_submit(locale.getString("mess128")));
      buffer.append("<link rel='icon' href='/favicon.ico' type='image/x-icon'>");
      buffer.append("<link rel='shortcut icon' href='/favicon.ico' type='image/x-icon'>");
      buffer.append("<title>" + fd_smiles(HtmlChars.convertHtmlSymbols(removeSlashes(head)), false, webapp) + "</title>");
      buffer.append("</head>");
      buffer.append("<body bgcolor=#EFEFEF>");
      buffer.append("<table class='content'>");
      buffer.append("<tr class='heads'>");
      buffer.append("<td  class='internal'>");
      /*"Закладка" последнего поста*/
      /*"Закладка" номера поста для ссылки из поиска, возврата после обработки игнора*/
      /*Тема*/
      buffer.append("<div class='nik'>");
      buffer.append("<b>&nbsp;&nbsp;" + fd_smiles(HtmlChars.convertHtmlSymbols(removeSlashes(head)), false, webapp)+ "</b>");
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

      buffer.append("<img border='0' src='/");
      if(!webapp.isEmpty()){
         buffer.append(webapp).append("/");
      }
      buffer.append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/icon_minipost.gif'>&nbsp;");
      buffer.append("<span class='posthead'>").append(rgtime).append("</span>");
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
         StringBuilder avatarURL = new StringBuilder();
         if (user.getAvatar().startsWith("http://")){
            avatarURL.append(user.getAvatar());
         }else{
            avatarURL.append("/");
            if(!webapp.isEmpty()){
               avatarURL.append(webapp).append("/");
            }
            avatarURL.append(FJUrl.STATIC).append("/").append(user.getAvatar()).append("?seed=").append(System.currentTimeMillis());
         }
         buffer.append("<a href='" + "/" + userURI + "/" + FJUrl.SETTINGS + "?id=9' rel='nofollow'><img border='0' src='").append(avatarURL).append("'></a>");
      }else{
         buffer.append("<a href='/").append(userURI).append("/").append(FJUrl.SETTINGS).append("?id=9' rel='nofollow'><img border='0' src='/");
         if(!webapp.isEmpty()){
            buffer.append(webapp).append("/");
         }
         buffer.append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/no_avatar.gif'></a>");
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
      buffer.append("<p class='post'>" + fd_body(HtmlChars.convertHtmlSymbols(removeSlashes(body)), webapp) + "</p>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr><td class='matras' colspan=2></td></tr>");
      buffer.append("<tr><td class='matras'></td><td>");
      buffer.append("<p class=post>" + fd_body(HtmlChars.convertHtmlSymbols(removeSlashes(user.getFooter())), webapp) + "</p>");
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
      buffer.append("<form name='post' action='" + FJUrl.ADD_THREAD + "' method='post'>");
      buffer.append("<table width='100%'>");
      buffer.append("<tr>");
      buffer.append("<td colspan='2' align='CENTER'>");
      /*Тема*/
      buffer.append(locale.getString("mess4") + "&nbsp");
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
      buffer.append(smiles_add(locale.getString("mess11"), webapp));
      buffer.append("</td>");
      buffer.append("<td align='CENTER' valign='top'>");
      buffer.append(autotags_add(webapp));
      /* текстарий*/
      buffer.append("<p>");
      buffer.append("<textarea class='mnuforumSm' rows='20' id='ed1' name='A2' cols='55'>" + HTMLEntities.htmlentities(removeSlashes(body)) + "</textarea>");
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
