/*
 * Copyright Andrew V. Pogrebnyak
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law || agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES || CONDITIONS OF ANY KIND, either express || implied.
 * See the License for the specific language governing permissions &&
 * limitations under the License.
 */
package org.forumj.network.web.controller;

import static org.forumj.common.web.Pin.*;
import static org.forumj.network.web.FJServletTools.*;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.forumj.common.db.entity.*;
import org.forumj.common.db.service.*;
import org.forumj.common.exception.InvalidKeyException;
import org.forumj.common.tool.*;
import org.forumj.network.web.FJUrl;
import org.forumj.network.web.resources.LocaleString;
import org.forumj.network.web.resources.ResourcesBuilder;


/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class Index{

   public void doGet(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws ServletException, IOException {
      long startTime = new Date().getTime();
      StringBuffer buffer = new StringBuffer();
      try {
         InterfaceService interfaceService = FJServiceHolder.getInterfaceService();
         IndexService indexService = FJServiceHolder.getIndexService();
         FolderService folderService = FJServiceHolder.getFolderService();
         HttpSession session = request.getSession();
         //Предотвращаем кеширование
         cache(response);
         // Функции   
         // номер страницы
         Integer pageNumber = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));
         // Загружаем локализацию
         LocaleString locale = (LocaleString) session.getAttribute("locale");
         IUser user = (IUser) session.getAttribute("user");
         Long userId = user.getId();
         // Собираем статистику
         buffer.append("<!doctype html public \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
         buffer.append("<html>");
         buffer.append("<head>");
         buffer.append("<meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
         // Стили
         buffer.append(ResourcesBuilder.getStyleCSS(webapp));
         // Скрипты (флажки)
         buffer.append(loadJavaScript("/js/jsmain_chek.js"));
         Long m_xb = indexService.getLastPostId();
         Long m_xt = indexService.getMaxThreadId();
         buffer.append("<script language='javascript' type='text/javascript'>");
         buffer.append("// <!-- \n");
         buffer.append("var m_xb=").append(m_xb).append(";\n");
         buffer.append("var m_xt=").append(m_xt).append(";\n");
         buffer.append("var pingURL='").append("/").append(userURI).append("/").append(FJUrl.PING).append("';\n");
         buffer.append("// -->\n");
         buffer.append("</script>\n");
         buffer.append(loadJavaScript("/js/indicator.js"));
         // Скрипты (submit формы интерфейсов)
         buffer.append(loadJavaScript("/js/jsview_ok.js"));
         buffer.append("<link rel=\"icon\" href=\"/favicon.ico\" type=\"image/x-icon\">");
         buffer.append("<link rel=\"shortcut icon\" href=\"/favicon.ico\" type=\"image/x-icon\">");
         buffer.append("<title>");
         buffer.append(locale.getString("MSG_MAIN_TITLE"));
         buffer.append("</title>");
         buffer.append("</head>");
         // Цвет фона страницы
         buffer.append("<body class='mainBodyBG'>");
         // Главная таблица
         buffer.append("<table border='0' id=t1 style='border-collapse: collapse' width='100%'>");
         buffer.append("<tr><td>");
         buffer.append("<table border='0' style='border-collapse: collapse' width='100%'>");
         // Таблица с лого и верхним баннером
         buffer.append(logo(webapp));
         // определяем кол-во страниц
         long nfirstpost = (pageNumber - 1) * user.getThreadsOnPage();
         if (nfirstpost < 0){
            nfirstpost = 0;
         }
         Long viewId = (Long) session.getAttribute("view");
         // Интерфейс по умолчанию
         if (viewId == null || !interfaceService.isExists(viewId, user)){
            session.setAttribute("view", user.getView());
            viewId = user.getView();
         }
         List<IFJThread> threadsList = indexService.getThreads(viewId, nfirstpost, user);
         int threadsAmountAtPage = threadsList.size();
         long threadsCount = indexService.getThreadsAmount(viewId, user);
         // кол-во страниц с заголовками
         int couP = (int) (Math.floor(threadsCount / user.getThreadsOnPage()) + 2);
         // Проверяем наличие почты
         StringBuilder newMail = new StringBuilder();
         if (user.isLogined()) {
            int mailCount = indexService.getNewMailCount(user.getId());
            if (mailCount > 0) {
               newMail.append("<a class=hdforum href='");
               newMail.append("/").append(userURI).append("/").append(FJUrl.SETTINGS);
               newMail.append("?id=2' rel='nofollow'><font color=red>");
               newMail.append(locale.getString("mess66"));
               newMail.append(" ");
               newMail.append(mailCount);
               newMail.append(" ");
               newMail.append(locale.getString("mess67"));
               newMail.append("</font></a>");
            }
         }
         // Таблица главных ссылок
         buffer.append("<tr>");
         buffer.append("<td width='100%'>");
         buffer.append("<table id='t2' width='100%'>");
         /*Главное меню*/
         buffer.append(menu(request, user, locale, true, webapp, userURI));
         // Интерфейс
         // Имя текущего
         String viewName = indexService.getViewName(viewId);
         List<IFJInterface> viewsList = indexService.getViews(userId);
         buffer.append("<tr><td>");

         buffer.append("<table class=control>");
         buffer.append("<tr>");
         buffer.append("<td class=leftTop></td>");
         buffer.append("<td class=top colspan=3></td>");
         buffer.append("<td class=rightTop></td>");
         buffer.append("</tr>");
         buffer.append("<tr class=heads>");
         buffer.append("<td class=left></td>");
         buffer.append("<td class=bg2 align=left>");
         buffer.append("<span class=mnuforum>");
         buffer.append(locale.getString("mess81"));
         buffer.append("</span>");
         buffer.append("<span class=nik>");
         buffer.append(viewName);
         buffer.append("</span>");
         buffer.append("</td>");
         buffer.append("<td class=bg2 align=right>");
         buffer.append("<form method='post' name='view_form' action='");
         buffer.append("/").append(userURI).append("/").append(FJUrl.SELECT_VIEW);
         buffer.append("' class=frmsmall>");
         /*Выводим интерфейсы*/
         buffer.append("<span class=mnuforum>");
         buffer.append(locale.getString("mess80"));
         buffer.append("</span>");
         buffer.append("<select class='mnuforumSm'  size='1pt' name='VIEW'>");
         IFJInterface fjinterface = viewsList.get(0);
         buffer.append("<option selected class=mnuprof value='" + fjinterface.getId() + "'>");
         buffer.append(fjinterface.getName());
         buffer.append("</option>");
         for (int vw1=1; vw1< viewsList.size(); vw1++)
         {
            fjinterface = viewsList.get(vw1);
            buffer.append("<option class=mnuprof value='" + fjinterface.getId() + "'>");
            buffer.append(fjinterface.getName());
            buffer.append("</option>");
         }
         buffer.append("</select>");
         buffer.append("</form>");
         buffer.append("</td>");
         buffer.append("<td class=bg2 align=right>");
         buffer.append(fd_button("OK","document.view_form.submit();","view_ok", "1"));
         buffer.append("</td>");
         buffer.append("<td class=right></td>");
         buffer.append("</tr>");
         buffer.append("<tr>");
         buffer.append("<td class=leftBtm></td>");
         buffer.append("<td class=btm colspan=3></td>");
         buffer.append("<td class=rightBtm></td>");
         buffer.append("</tr>");
         buffer.append("</table>");

         buffer.append("</td>");        
         buffer.append("</tr>");
         // Стройка!!!
         buffer.append("<tr>");
         buffer.append("<td width='100%'>");
         buffer.append("<table width='100%'>");
         String mess = "";
         if (!user.isLogined()){
            mess=locale.getString("mess133");
         }else{
            mess=locale.getString("mess134");
         }
         buffer.append("<tr><td colspan='3'><p><font face='Arial' color='red' size='3'><span style='text-decoration: none'><b>");
         buffer.append(mess);
         buffer.append("</b></span></font></p></td>");
         buffer.append("<td style='text-align: right;'>");    
         // Сторінка сформована :)   
         buffer.append("<span class=posthead>" + locale.getString("mess91") + "</span>");
         buffer.append("</td>");
         buffer.append("</tr>");
         // Ссылки на другие страницы (здесь collspan!)
         buffer.append("<tr><td style='padding:2px'>");
         buffer.append("<font class='page'><b>" + locale.getString("mess22") + "&nbsp;</b></font>");
         int i3=1;
         if (pageNumber>5) i3=pageNumber-5;
         int i4 = pageNumber+5;
         if (couP-pageNumber<5) i4=couP;
         int i2 = 0;
         for (int i1=i3; i1<i4; i1++){
            i2=i2+1;
            if ((i1>(pageNumber-5) && i1<(pageNumber+5)) || i2==10 || i1==1 || i1==(couP-1)){
               if (i2==10) i2=0;
               if (i1==pageNumber){
                  buffer.append("<font class='pagecurrent'><b>" + i1 + "</b></font>");
               }
               else {
                  buffer.append("<a class='pageLink' href='");
                  buffer.append("/").append(userURI).append("/").append(FJUrl.INDEX);
                  buffer.append("?page=");
                  buffer.append(i1);
                  buffer.append("'>");
                  buffer.append(i1);
                  buffer.append("</a>");
               }
            }
         }
         buffer.append("<font class='page' style='margin-left:5px;'><b>" + locale.getString("mess136") + "&nbsp;" + (couP-1) + "</b></font>");
         buffer.append("</td>");
         buffer.append("<td align='right'>");
         buffer.append("<form name='str' method='get' class=frmsmall action='");
         buffer.append("/").append(userURI).append("/").append(FJUrl.INDEX);
         buffer.append("'>");
         buffer.append("<font class=page style='margin-right:4px;'>");
         buffer.append("<b>");
         buffer.append(locale.getString("mess137"));
         buffer.append("</b>");
         buffer.append("</font>");
         buffer.append("<input class='mnuforumSm' style='padding:2px' type=\"text\" size='5' name='page'>");
         buffer.append("</form>");
         buffer.append("</td>");
         buffer.append("<td>");
         buffer.append(fd_button("OK","document.str.submit();","page_ok", "1"));
         buffer.append("</td>"); 
         buffer.append("<td style='text-align: right;'>");    
         // Индикатор   
         buffer.append("<span class=posthead>" + locale.getString("mess164") + ":&nbsp;</span>");
         buffer.append("<span class=posthead id='indicatorb' style='color:red'>&nbsp;</span><br />");
         buffer.append("<span class=posthead >" + locale.getString("mess165") + ":&nbsp;</span>");
         buffer.append("<span class=posthead id='indicatort' style='color:red'>&nbsp;</span>");
         buffer.append("</td>");
         buffer.append("</tr>");
         buffer.append("</table>");
         buffer.append("</td>");
         buffer.append("</tr>");
         // Закончили таблицу главных ссылок
         // Таблица Заголовков тем
         if (user.isLogined()){
            // Форма выводится только для зарегистрированых
            buffer.append("</table>");        
            buffer.append("<form method='post' name='del_form' action='" + "/" + userURI + "/" + FJUrl.MOVE_TITLE + "?page=" + pageNumber + "' class=frmsmall>");
            buffer.append("<table border='0' style='border-collapse: collapse' width='100%'>");
         }
         buffer.append("<tr>");
         buffer.append("<td height='400' valign='top'>");
         buffer.append("<table class='content'>");
         // Заголовки таблицы
         buffer.append("<tr><td class=internal align='left' colspan='3'><span class=hdforum2>Тема:  </span>");
         buffer.append(newMail);
         buffer.append(" </td>");
         // Ответы
         buffer.append("<td class=internal align='center'><span class=hdforum2>" + locale.getString("MSG_ANSW") + "</span>");
         buffer.append("</td>");
         // Просмотры
         buffer.append("<td class=internal align='center'><span class=hdforum2>");
         buffer.append(locale.getString("MSG_VIEWS") + "</span></td>");
         buffer.append("<td class=internal align='center'><span class=hdforum2>" + locale.getString("MSG_AUTH") + "</span></td>");
         buffer.append("<td class=internal align='center'><span class=hdforum2>" + locale.getString("MSG_LAST") + "</span></td>");
         // Папка
         buffer.append("<td class=internal align='center'><span class=hdforum2>" + locale.getString("mess82") + "</span></td>");
         // Флажок (только для авторизованых)
         if (user.isLogined()) {
            buffer.append("<td class=internal align='center'>");
            buffer.append("<input type='checkbox' id='main_ch' onclick='m_chek()'>");
            buffer.append("</td>");
            buffer.append("<td class=internal></td>");
         }
         buffer.append("</tr>");
         // Выводим строки
         StringBuffer indctrIds = new StringBuffer();
         for (int threadIndex = 0; threadIndex < threadsList.size(); threadIndex++) {
            if ((threadIndex & 1) == 0) {
               buffer.append("<tr class='matras'>");
            }else {
               buffer.append("<tr class='trees'>");
            }
            IFJThread thread = threadsList.get(threadIndex);
            StringBuffer threadContent = writeThread(thread, user, locale, threadIndex, pageNumber, userURI, webapp);
            buffer.append(threadContent);
            buffer.append("</tr>");
            indctrIds.append(";").append(thread.getId()).append(",").append(thread.getLastPostId());
         }
         if (threadsList.size() > 0){
            indctrIds.deleteCharAt(0);
         }
         // Главные ссылки внизу страницы
         buffer.append("</table>\n");
         buffer.append("<script type='text/javascript'>\n");
         buffer.append("if (request){\n");
         buffer.append("var idss = '");
         if(threadsList.size() > 0){
            buffer.append(indctrIds);
         }else{
            buffer.append("0");
         }
         buffer.append("';\n");
         buffer.append("getIndicatorInfo();\n");
         buffer.append("}\n");
         buffer.append("</script>\n");
         buffer.append("</td>");
         buffer.append("</tr>");
         buffer.append("<tr>");
         buffer.append("<td width='100%'>");
         buffer.append("<table border='0' style='border-collapse: collapse' width='100%'>");
         buffer.append("<tr><td colspan='4' style='padding:2px'>");
         buffer.append("<font class=page><b>" + locale.getString("mess22") + "&nbsp;</b></font>");
         i3=1;
         if (pageNumber>5) i3=pageNumber-5;
         i4=pageNumber+5;
         if (couP-pageNumber<5) i4=couP;
         i2=0;
         for (int i1=i3; i1<i4; i1++){
            i2=i2+1;
            if ((i1>(pageNumber-5) && i1<(pageNumber+5)) || i2==10 || i1==1 || i1==(couP-1)){
               if (i2==10) i2=0;
               if (i1==pageNumber){
                  buffer.append("<font class='pagecurrent'><b>" + i1 + "</b></font>");
               }
               else {
                  buffer.append("<a class='pageLink' href='" + "/" + userURI + "/" + FJUrl.INDEX + "?page=" + i1 + "'>" + i1 + "</a>");
               }
            }
         }
         buffer.append("<font class='page' style='margin-left:5px;'><b>" + locale.getString("mess136") + "&nbsp;" + (couP-1) + "</b></font>");
         buffer.append("</td>");
         buffer.append("</tr>");
         // Сервис интерфейса
         if (user.isLogined()) {
            // Выбираем доступные папки
            List<IFJFolder> foldersList = folderService.getUserFolders(user);
            buffer.append("<tr>");
            buffer.append("<table class=control>");        
            buffer.append("<tr>");
            buffer.append("<td class=leftTop></td>");
            buffer.append("<td class=top colspan=3></td>");
            buffer.append("<td class=rightTop></td>");
            buffer.append("</tr>");
            buffer.append("<tr class=heads>");
            buffer.append("<td class=left></td>");
            buffer.append("<td class=bg2 align=left>");
            buffer.append("<span class=mnuforum>");
            buffer.append(locale.getString("mess81"));
            buffer.append("</span><span class=nik>");
            buffer.append(viewName);
            buffer.append("</span>");
            buffer.append("</td>");
            buffer.append("<td class=bg2 align=right>");
            // Выводим папки
            buffer.append("<span class=mnuforum>" + locale.getString("mess83") + "</span>");
            buffer.append("<select class='mnuforumSm' size='1' name='VIEW'>");
            buffer.append("<option selected value='" + foldersList.get(0).getId() + "'><span class=mnuprof>" + foldersList.get(0).getName() + "</span></option>");
            for (int fl1=1; fl1< foldersList.size(); fl1++){
               buffer.append("<option value='" + foldersList.get(fl1).getId() + "'><span class=mnuprof>" + foldersList.get(fl1).getName() + "</span></option>");
            }        
            buffer.append("</select>");
            // Прередаем нужные пераметры...
            buffer.append(fd_form_add(user));
            buffer.append("<input type=hidden name='NRW' id='nrw' value='").append(threadsAmountAtPage).append("'>");
            // Кнопка
            buffer.append("</td>");        
            buffer.append("<td class=bg2 align=right>");
            buffer.append(fd_button("OK","document.del_form.submit();","del_ok", "1"));
            buffer.append("</td>");        
            buffer.append("<td class=right></td>");
            buffer.append("</tr>");
            buffer.append("<tr>");
            buffer.append("<td class=leftBtm></td>");
            buffer.append("<td class=btm colspan=3></td>");
            buffer.append("<td class=rightBtm></td>");
            buffer.append("</tr>");
            buffer.append("</table>");        
            buffer.append("</tr>");
            buffer.append("</table>");        
            buffer.append("</form>");
            buffer.append("<table border='0' style='border-collapse: collapse' width='100%'>");
         }
         /*Главное меню*/
         buffer.append(menu(request, user, locale, true, webapp, userURI));
         buffer.append("</table>");
         buffer.append("</td>");
         buffer.append("</tr>");
         // Таблица активных пользователей
         // Выбираем Активных юзеров
         List<IUser> userList = indexService.getUsersArray();
         buffer.append("<tr>");
         buffer.append("<td width=\"100%\">");
         buffer.append("<table width='100%'><tr><td>");
         buffer.append("<font class=mnuforum>");
         buffer.append(locale.getString("MSG_READERS") + "<br>");
         buffer.append("</font>");
         buffer.append("<font class=nick>");
         for (int userIndex=0 ; userIndex<userList.size(); userIndex++){
            buffer.append(userList.get(userIndex).getNick().replace(" ", "&nbsp;"));
            if (userIndex != userList.size()-1) buffer.append("; ");
         }
         buffer.append("</font>");
         buffer.append("<font class=mnuforum>");
         buffer.append("<br>" + locale.getString("MSG_GUESTS") + ": ");
         buffer.append("</font>");
         buffer.append("<font class=nick>");
         // Выводим количество гостей
         buffer.append(indexService.getGuestsAmount());
         buffer.append("</font>");
         buffer.append("</td>");
         buffer.append("</tr>");
         buffer.append("</table>");
         buffer.append("</td>");
         buffer.append("</tr>");
         // Баннер внизу, счетчики и копирайт.
         buffer.append(footer(webapp));
         buffer.append("</table></td></tr></table></td></tr></table>");
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

   private StringBuffer writeThread(IFJThread thread, IUser user, LocaleString locale, int threadIndex, int pageNumber, String userURI, String webapp) throws InvalidKeyException{
      StringBuffer buffer = new StringBuffer();
      // Картинки
      // Пиктограммка опроса
      buffer.append("<td width='10' align='center' style='padding:0px 5px 0px 5px'>");
      if (thread.isClosed()){
         buffer.append("<img border='0' src='");
         buffer.append("/");
         if(!webapp.isEmpty()){
            buffer.append(webapp).append("/");
         }
         buffer.append(FJUrl.STATIC).append("/");
         buffer.append(FJUrl.SKIN);
         buffer.append("/standart/picts/closed.png'>");
      }else if (thread.isQuest()){
         buffer.append("<img border='0' src='");
         buffer.append("/");
         if(!webapp.isEmpty()){
            buffer.append(webapp).append("/");
         }
         buffer.append(FJUrl.STATIC).append("/");
         buffer.append(FJUrl.SMILES);

         buffer.append("/quest.gif'>");
      }else{
         if (thread.getDock()== NOTICE){
            buffer.append("<img border='0' src='");
            buffer.append("/");
            if(!webapp.isEmpty()){
               buffer.append(webapp).append("/");
            }
            buffer.append(FJUrl.STATIC).append("/");
            buffer.append(FJUrl.SMILES);

            buffer.append("/icon4.gif'>");
         }else if(thread.getDock() == PIN) {
            buffer.append("<img border='0' src='");
            buffer.append("/");
            if(!webapp.isEmpty()){
               buffer.append(webapp).append("/");
            }
            buffer.append(FJUrl.STATIC).append("/");
            buffer.append(FJUrl.PICTS);

            buffer.append("/f_pinned.gif'>");
         }else {
            buffer.append("<img border='0' src='");
            buffer.append("/");
            if(!webapp.isEmpty()){
               buffer.append(webapp).append("/");
            }
            buffer.append(FJUrl.STATIC).append("/");
            buffer.append(FJUrl.SMILES);

            buffer.append("/icon1.gif'>");
         }
      }
      buffer.append("</td>");
      buffer.append("<td width='1'></td>");
      // Тема
      buffer.append("<td><p>");
      String str_head = HtmlChars.convertHtmlSymbols(removeSlashes(thread.getHead()));
      // Добавляем смайлики
      str_head = fd_head(str_head, webapp);
      // Опрос? Добавляем "метку"
      if (thread.isQuest()){
         str_head="<b>" +locale.getString("mess9")+ "</b> " +str_head;
      }
      // Подписываем прикрепленные
      switch (thread.getDock()){
      case PIN:
         buffer.append("<font class=trforum><b>" +locale.getString("mess7")+ " </b><a href='" + "/" + userURI + "/" + FJUrl.VIEW_THREAD + "?id=" +thread.getId().toString() + "'>" +str_head+ "</a></font>");
         break;
      case NOTICE:
         buffer.append("<font class=trforum><b>" +locale.getString("mess8")+ " </b><a href='" + "/" + userURI + "/" + FJUrl.VIEW_THREAD + "?id=" +thread.getId().toString() + "'>" +str_head+ "</a></font>");
         break;
      case BIRTHDAY:
         buffer.append("<font class=trforum><b>" +locale.getString("mess163")+ " </b><a href='" + "/" + userURI + "/" + FJUrl.VIEW_THREAD + "?id=" +thread.getId().toString() + "'>" +str_head+ "</a></font>");
         break;
      case COMMON:
         buffer.append("<font class=trforum><a href='" + "/" + userURI + "/" + FJUrl.VIEW_THREAD + "?id=" +thread.getId().toString()+ "'>" +str_head+ "</a></font>");
         break;
      }
      // Cсылки на страницы в ветке
      int postsAmount = thread.getPostsAmount();
      if (postsAmount + 1 > user.getPostsOnPage() || user.isModerator()|| thread.getAuthId().equals(user.getId())) {
         buffer.append("<br />");
      }
      if (postsAmount + 1 > user.getPostsOnPage()) {
         buffer.append("<font face='Verdana' size='1pt'>" +locale.getString("mess10")+ ":&nbsp");
         int k1=0;
         int k2=0;
         for (int k = 1; k<=Math.floor((postsAmount+1)/user.getPostsOnPage()) + 1; k++) {
            k1=k1+1;
            if (k1==10){
               buffer.append("<a href='" + "/" + userURI + "/" + FJUrl.VIEW_THREAD + "?page=" +k+ "&amp;id=" +thread.getId().toString()+ "'>" +k+ "</a>");
               if (k != Math.floor((postsAmount+1)/user.getPostsOnPage()) + 1) buffer.append(",&nbsp;");
               k1=0;
               k2=k2+1;
            }
            if (k==1){
               buffer.append("<a href='" + "/" + userURI + "/" + FJUrl.VIEW_THREAD + "?page=" +k+ "&amp;id=" +thread.getId().toString()+ "'>" +k+ "</a>,&nbsp;");
            }
            if ((Math.floor((postsAmount+1)/user.getPostsOnPage())-k2*10 + 1)< 10 && (k-k2*10) != 0 && k!=1){
               buffer.append("<a href='" + "/" + userURI + "/" + FJUrl.VIEW_THREAD + "?page=" +k+ "&amp;id=" +thread.getId().toString()+ "'>" +k+ "</a>");
               if (k != Math.floor((postsAmount+1)/user.getPostsOnPage()) + 1) buffer.append(",&nbsp;");
            }

         }
         buffer.append("&nbsp;</font>");
      }
      if(user.isModerator()){
         buffer.append("<font face='Verdana' size='1pt'>");
         if (thread.getDock().getCode() > 0){
            buffer.append("<a href='" + "/" + userURI + "/" + FJUrl.PIN_THREAD + "?id=" + thread.getId() + "&amp;pin=" + COMMON.getCode() + "'>" +locale.getString("MSG_UNPIN")+ "</a>&nbsp;");
         }
         if (thread.getDock() != PIN){
            buffer.append("<a href='" + "/" + userURI + "/" + FJUrl.PIN_THREAD + "?id=" + thread.getId() + "&amp;pin=" + PIN.getCode() + "'>" +locale.getString("MSG_PIN")+ "</a>&nbsp;");
         }
         if (thread.getDock() != BIRTHDAY){
            buffer.append("<a href='" + "/" + userURI + "/" + FJUrl.PIN_THREAD + "?id=" + thread.getId() + "&amp;pin=" + BIRTHDAY.getCode() + "'>" +locale.getString("MSG_BIRTHDAY_PIN")+ "</a>&nbsp;");
         }
         if (thread.getDock() != NOTICE){
            buffer.append("<a href='" + "/" + userURI + "/" + FJUrl.PIN_THREAD + "?id=" + thread.getId() + "&amp;pin=" + NOTICE.getCode() + "'>" +locale.getString("MSG_NOTICE_PIN")+ "</a>&nbsp;");
         }
         buffer.append("<a href='" + "/" + userURI + "/" + FJUrl.MOVE_THREAD_TO_RECYCLE + "?id=" +thread.getId().toString()+ "&amp;usr=0&amp;page=" + pageNumber + "'>" + locale.getString("mess70") + "</a>");
         buffer.append("&nbsp;</font>");
      }
      if (user.isModerator() || thread.getAuthId().equals(user.getId())){
         buffer.append("<font face='Verdana' size='1pt'>");
         if (thread.isClosed()){
            buffer.append("<a href='close?id=" +thread.getId().toString()+ "&amp;close=0&amp;page=" + pageNumber + "'>" + locale.getString("MSG_OPEN_THREAD") + "</a>");
         }else{
            buffer.append("<a href='close?id=" +thread.getId().toString()+ "&amp;close=1&amp;page=" + pageNumber + "'>" + locale.getString("MSG_CLOSE_THREAD") + "</a>");
         }
         buffer.append("</font>");
      }
      buffer.append("</p></td>");
      // Количество постов
      buffer.append("<td width='20' align='center' valign='middle'><span class='mnuforum' style='color: purple'>" +postsAmount);
      buffer.append("</span><span id='posts" +thread.getId().toString()+ "' class='mnuforum' style='color: red'>&nbsp;</span></td>");
      // кол-во просмотров
      buffer.append("<td width='80' align='center' valign='middle'>");
      // Количество просмотров участников
      buffer.append("<div class='mnuforum'><font size='1pt' color='green'>" + thread.getSnid() + "</font><br>");
      // Количество просмотров всего
      buffer.append("<font size='1pt' color='purple'>" + thread.getSnall() + "</font></div></td>");
      // Автор
      buffer.append("<td width='120' align='center' valign='middle'><div class='trforum'><font size='1pt'>" +HtmlChars.convertHtmlSymbols(thread.getNick())+ "</font></div></td>");
      // Автор последнего поста
      buffer.append("<td width='120' align=center><div class='mnuforum'><font size='1pt'>" +HtmlChars.convertHtmlSymbols(thread.getLastPostNick())+ "</font></div>");
      // Время последнего поста
      buffer.append("<div class='mnuforum'><a href='" + "/" + userURI + "/" + FJUrl.VIEW_THREAD + "?id=" + thread.getId().toString() + "&amp;end=1#end' rel='nofollow'><font size='1pt'>" + Time.date("dd.MM.yy HH:mm",thread.getLastPostTime().getTime()) + "</font></a></div>");
      buffer.append("</td>");
      // Папка
      buffer.append("<td align='center' valign='middle'>");
      buffer.append("<div class='mnuforum'><font size='1pt'>" +thread.getFolder()+ "</font></div>");
      buffer.append("</td>");
      // Флажок (только для зарегистрированых)
      if (user.isLogined()){
         buffer.append("<td align='center' valign='middle'>");
         buffer.append("<input type='checkbox' id='ch").append(threadIndex).append("' name='").append(threadIndex).append("' value='").append(thread.getId()).append("'>");
         buffer.append("</td>");
         buffer.append("<td style='padding:0px 5px 0px 5px' align='right'>");
         buffer.append("<a href='" + "/" + userURI + "/" + FJUrl.MOVE_THREAD_TO_RECYCLE + "?id=" +thread.getId().toString()+ "&amp;usr=" +String.valueOf(user.getId())+ "&amp;page=" +thread.getPg()+ "'><img border='0' src='");
         buffer.append("/");
         if(!webapp.isEmpty()){
            buffer.append(webapp).append("/");
         }
         buffer.append(FJUrl.STATIC).append("/");
         buffer.append(FJUrl.PICTS);
         buffer.append("/del1.gif'></a>");
         buffer.append("</td>");
      }
      return buffer;
   }
}


