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
import org.forumj.db.dao.*;
import org.forumj.db.entity.*;
import org.forumj.exception.InvalidKeyException;
import org.forumj.tool.*;
import org.forumj.web.servlet.FJServlet;


/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.INDEX, "/"}, name = FJServletName.INDEX)
public class Index extends FJServlet {

   /**
    * {@inheritDoc}
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      long startTime = new Date().getTime();
      StringBuffer buffer = new StringBuffer();
      List<Map<String, Object>> viewsList = null;
      try {
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
         FJPostDao postDao = new FJPostDao();
         FJThreadDao threadDao = new FJThreadDao();
         FJMailDao mailDao = new FJMailDao();
         FJInterfaceDao interfaceDao = new FJInterfaceDao();
         FJFolderDao folderDao = new FJFolderDao();
         FJActionDao actionDao = new FJActionDao();
         // Собираем статистику
         buffer.append("<!doctype html public \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
         buffer.append("<html>");
         buffer.append("<head>");
         buffer.append("<meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
         // Стили
         buffer.append(loadCSS("/css/style.css"));
         // Скрипты (флажки)
         buffer.append(loadJavaScript("/js/jsmain_chek.js"));
         Long m_xb = postDao.getMaxPostId();
         Long m_xt = threadDao.getMaxThreadId();
         buffer.append("<script language='javascript' type='text/javascript'>");
         buffer.append("// <!-- \n");
         buffer.append("var m_xb=" + m_xb + ";");
         buffer.append("var m_xt=" + m_xt + ";");
         buffer.append("\n// -->");
         buffer.append("</script>");
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
         buffer.append(logo(request));
         // соединяемся и определяем кол-во страниц
         long nfirstpost=(pageNumber-1)*user.getPp();
         if (nfirstpost < 0){
            nfirstpost = 0;
         }
         // Интерфейс по умолчанию
         if (session.getAttribute("view") == null){
            session.setAttribute("view", user.getView());
         }
         List<Ignor> ignorList = new FJIgnorDao().loadAll(user.getId());
         FJThreads threads = threadDao.getThreads(Long.valueOf((Integer) session.getAttribute("view")), nfirstpost, locale, user, ignorList);
         List<FJThread> threadsList = threads.getThreads();
         long threadsCount = threads.getThreadCount();
         // кол-во страниц с заголовками
         int couP = ceil(threadsCount/user.getPp())+1;
         // Проверяем наличие почты
         String newMail = "";
         if (user.isLogined()) {
            int mailCount = mailDao.getNewMailCount(user.getId());
            if (mailCount > 0) {
               newMail="<a class=hdforum href='control.php?id=2' rel='nofollow'><font color=red>" + locale.getString("mess66") + " " + mailCount +" " + locale.getString("mess67") + "</font></a>";
            }
         }
         // Таблица главных ссылок
         buffer.append("<tr>");
         buffer.append("<td width='100%'>");
         buffer.append("<table id='t2' width='100%'>");
         /*Главное меню*/
         buffer.append(menu(request, user, locale, true));
         // Интерфейс
         // Имя текущего
         if (session.getAttribute("vname") == null){
            session.setAttribute("vname", interfaceDao.getCurrentViewName(Long.valueOf((Integer)session.getAttribute("view"))));
         }
         viewsList = interfaceDao.getViewsArray(userId);
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
         buffer.append(session.getAttribute("vname"));
         buffer.append("</span>");
         buffer.append("</td>");
         buffer.append("<td class=bg2 align=right>");
         buffer.append("<form method='post' name='view_form' action='slctview.php' class=frmsmall>");
         /*Выводим интерфейсы*/
         buffer.append("<span class=mnuforum>");
         buffer.append(locale.getString("mess80"));
         buffer.append("</span>");
         buffer.append("<select class='mnuforumSm'  size='1' name='VIEW'>");
         buffer.append("<option selected class=mnuprof value='" + viewsList.get(0).get("id") + "'>");
         buffer.append(viewsList.get(0).get("name"));
         buffer.append("</option>");
         for (int vw1=1; vw1< viewsList.size(); vw1++)
         {
            buffer.append("<option class=mnuprof value='" + viewsList.get(vw1).get("id") + "'>");
            buffer.append(viewsList.get(vw1).get("name"));
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
                  buffer.append("<a class='pageLink' href='index.php?page=" + i1 + "'>" + i1 + "</a>");
               }
            }
         }
         buffer.append("<font class='page' style='margin-left:5px;'><b>" + locale.getString("mess136") + "&nbsp;" + (couP-1) + "</b></font>");
         buffer.append("</td>");
         buffer.append("<td align='right'>");
         buffer.append("<form name='str' method='get' class=frmsmall action='index.php'>");
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
         buffer.append("<span class=posthead id='indicatort' style='color:red'>&nbsp;</span><br />");
         buffer.append("<span class=posthead >" + locale.getString("mess165") + ":&nbsp;</span>");
         buffer.append("<span class=posthead id='indicatorb' style='color:red'>&nbsp;</span>");
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
            buffer.append("<form method='post' name='del_form' action='movetitle.php?page=" + pageNumber + "' class=frmsmall>");
            buffer.append("<table border='0' style='border-collapse: collapse' width='100%'>");
         }
         buffer.append("<tr>");
         buffer.append("<td height='400' valign='top'>");
         buffer.append("<table class='content'>");
         // Заголовки таблицы
         buffer.append("<tr><td class=internal align='left' colspan='3'><span class=hdforum2>Тема:  </span>" + newMail + " </td>");
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
         // Определяем кол-во строк таблицы
         long i5=pageNumber*user.getPp();
         if (i5>threadsCount) {
            i5=threadsCount-(pageNumber-1)*user.getPp();
         }else{
            i5=user.getPp();
         }
         // Выводим строки
         for (int threadIndex = 0; threadIndex < threadsList.size(); threadIndex++) {
            FJThread thread = threadsList.get(threadIndex);
            thread.setI(threadIndex);
            buffer.append(writeThread(thread, user, locale));
         }
         // Главные ссылки внизу страницы
         buffer.append("</table>");
         buffer.append("<script type='text/javascript'>");
         buffer.append("if (request){");
         if(threads.getIndctrIds() == null || threads.getIndctrIds().trim().length() == 0){
            buffer.append("var idss = '0';");
         }else{
            buffer.append("var idss = '" + substr(threads.getIndctrIds(), 1) + "';");
         }
         buffer.append("getIndicatorInfo();");
         buffer.append("}");
         buffer.append("</script>");
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
                  buffer.append("<a class='pageLink' href='index.php?page=" + i1 + "'>" + i1 + "</a>");
               }
            }
         }
         buffer.append("<font class='page' style='margin-left:5px;'><b>" + locale.getString("mess136") + "&nbsp;" + (couP-1) + "</b></font>");
         buffer.append("</td>");
         buffer.append("</tr>");
         // Сервис интерфейса
         if (user.isLogined()) {
            // Выбираем доступные папки
            List<IFJFolder> foldersList = folderDao.getFoldersArray(userId);
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
            buffer.append("<span class=mnuforum>" + locale.getString("mess81") + "</span><span class=nik>" + session.getAttribute("vname") + "</span>");
            buffer.append("</td>");
            buffer.append("<td class=bg2 align=right>");
            // Выводим папки
            buffer.append("<span class=mnuforum>" + locale.getString("mess83") + "</span>");
            buffer.append("<select class='mnuforumSm' size='1' name='VIEW'>");
            buffer.append("<option selected value='" + foldersList.get(0).getId() + "'><span class=mnuprof>" + foldersList.get(0).getName() + "</span></option>");
            for (int fl1=1; fl1< foldersList.size()-1; fl1++){
               buffer.append("<option value='" + foldersList.get(fl1).getId() + "'><span class=mnuprof>" + foldersList.get(fl1).getName() + "</span></option>");
            }        
            buffer.append("</select>");
            // Прередаем нужные пераметры...
            buffer.append(fd_form_add(user));
            buffer.append("<input type=hidden name=\"NRW\" id='nrw' value=\"" + i5 + "\">");
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
         menu(request, user, locale, true);
         buffer.append("</table>");
         buffer.append("</td>");
         buffer.append("</tr>");
         // Таблица активных пользователей
         // Выбираем Активных юзеров
         List<IUser> userList = actionDao.getUsersArray();
         buffer.append("<tr>");
         buffer.append("<td width=\"100%\">");
         buffer.append("<table width='100%'><tr><td>");
         buffer.append("<font class=mnuforum>");
         buffer.append(locale.getString("MSG_READERS") + "<br>");
         buffer.append("</font>");
         buffer.append("<font class=nick>");
         for (int userIndex=0 ; userIndex<userList.size()-1; userIndex++){
            buffer.append(str_replace(" ", "&nbsp;", userList.get(userIndex).getNick()));
            if (userIndex != userList.size()-2) buffer.append("; ");
         }
         buffer.append("</font>");
         buffer.append("<font class=mnuforum>");
         buffer.append("<br>" + locale.getString("MSG_GUESTS") + ": ");
         buffer.append("</font>");
         buffer.append("<font class=nick>");
         // Выводим количество гостей
         buffer.append(actionDao.getGuestsAmount());
         buffer.append("</font>");
         buffer.append("</td>");
         buffer.append("</tr>");
         buffer.append("</table>");
         buffer.append("</td>");
         buffer.append("</tr>");
         // Баннер внизу, счетчики и копирайт.
         buffer.append(footer(request));
         buffer.append("</table></td></tr></table></td></tr></table>");
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

   private StringBuffer writeThread(FJThread thread, IUser user, LocaleString locale) throws InvalidKeyException{
      StringBuffer buffer = new StringBuffer();
      if (thread.getDisain() == 1) { 
         buffer.append("<tr class=trees >");
      }else {
         buffer.append("<tr class=matras>");   
      }
      // Картинки
      // Пиктограммка опроса
      buffer.append("<td width='10' align='center' style='padding:0px 5px 0px 5px'>");
      if (thread.getType() == 1 || thread.getType() == 2){
         buffer.append("<img border='0' src='smiles/quest.gif'>");
      }else{
         if (thread.getDock()==5){
            buffer.append("<img border='0' src='smiles/icon4.gif'>");
         }else if(thread.getDock() == 10) {
            buffer.append("<img border='0' src='picts/f_pinned.gif'>");
         }else {
            buffer.append("<img border='0' src='smiles/icon1.gif'>");
         }
      }
      buffer.append("</td>");
      buffer.append("<td width='1'></td>");
      // Тема
      buffer.append("<td><p>");
      String str_head = htmlspecialchars(stripslashes(thread.getHead()));
      // Добавляем смайлики
      str_head = Diletant.fd_head(str_head);
      // Опрос? Добавляем "метку"
      if (thread.getType() == 1 || thread.getType() == 2){
         str_head="<b>" +locale.getString("mess9")+ "</b> " +str_head;
      }
      // Подписываем прикрепленные
      switch (thread.getDock()){
      case 10:
         buffer.append("<font class=trforum><b>" +locale.getString("mess7")+ " </b><a href='tema.php?id=" +thread.getId().toString() + "'>" +str_head+ "</a></font>");
         break;
      case 5:
         buffer.append("<font class=trforum><b>" +locale.getString("mess8")+ " </b><a href='tema.php?id=" +thread.getId().toString() + "'>" +str_head+ "</a></font>");
         break;
      case 3:
         buffer.append("<font class=trforum><b>" +locale.getString("mess163")+ " </b><a href='tema.php?id=" +thread.getId().toString() + "'>" +str_head+ "</a></font>");
         break;
      case 0:
         buffer.append("<font class=trforum><a href='tema.php?id=" +thread.getId().toString()+ "'>" +str_head+ "</a></font>");
         break;
      }
      // Cсылки на страницы в ветке
      int pcount = thread.getPcount();
      if (pcount+1>user.getPt()) {
         buffer.append("<br><font size=1>" +locale.getString("mess10")+ ":&nbsp");
         int k1=0;
         int k2=0;
         for (int k=1; k<=ceil((pcount+1)/user.getPt()); k++) {
            k1=k1+1;
            if (k1==10){
               buffer.append("<a href='tema.php?page=" +k+ "&id=" +thread.getId().toString()+ "'>" +k+ "</a>");
               if (k != ceil((pcount+1)/user.getPt())) buffer.append(",&nbsp;&nbsp;");
               k1=0;
               k2=k2+1;
            }
            if (k==1){
               buffer.append("<a href='tema.php?page=" +k+ "&id=" +thread.getId().toString()+ "'>" +k+ "</a>,&nbsp;&nbsp;");
            }
            if ((ceil((pcount+1)/user.getPt())-k2*10)< 10 && (k-k2*10) != 0 && k!=1){
               buffer.append("<a href='tema.php?page=" +k+ "&id=" +thread.getId().toString()+ "'>" +k+ "</a>");
               if (k != ceil((pcount+1)/user.getPt())) buffer.append(",&nbsp;&nbsp;");
            }

         }
         buffer.append("</font>");
      }
      buffer.append("</p></td>");
      // Количество постов
      buffer.append("<td width='20' align='center' valign='middle'><span class='mnuforum' style='{color: purple}'>" +pcount);
      buffer.append("</span><span id='posts" +thread.getId().toString()+ "' class='mnuforum' style='{color: red}'>&nbsp</span></td>");
      // кол-во просмотров
      buffer.append("<td width='80' align='center' valign='middle'>");
      // Количество просмотров участников
      buffer.append("<div class='mnuforum'><font size='1' color='green'>" + thread.getSnid() + "</font><br>");
      // Количество просмотров всего
      buffer.append("<font size='1' color='purple'>" + thread.getSnall() + "</font></div></td>");
      // Автор
      buffer.append("<td width='120' align='center' valign='middle'><div class='trforum'><font size='1'>" +htmlspecialchars(thread.getNick())+ "</font></div></td>");
      // Автор последнего поста
      buffer.append("<td width='120' align=center><div class='mnuforum'><font size='1'>" +htmlspecialchars(thread.getLastPostNick())+ "</font></div>");
      // Время последнего поста
      buffer.append("<div class='mnuforum'><a href='tema.php?id=" + thread.getId().toString() + "&end=1#end' rel='nofollow'><font size='1'>" + date("dd.MM.yy HH:mm",thread.getLastPostTime().getTime()) + "</font></a></div>");
      buffer.append("</td>");
      // Папка
      buffer.append("<td align='center' valign='middle'>");
      buffer.append("<div class='mnuforum'><font size='1'>" +thread.getFolder()+ "</font></div>");
      buffer.append("</td>");
      // Флажок (только для зарегистрированых)
      if (user.isLogined()){
         buffer.append("<td align='center' valign='middle'>");
         buffer.append("<input type='checkbox' id='ch" +thread.getI()+ "' name='" +thread.getI()+ "' value='" +thread.getId().toString()+ "'>");
         buffer.append("</td>");
         buffer.append("<td style='padding:0px 5px 0px 5px' align='right'>");
         buffer.append("<a href='delone.php?id=" +thread.getId().toString()+ "&usr=" +String.valueOf(user.getId())+ "&page=" +thread.getPg()+ "'><img border='0' src='picts/del1.gif'></a>");
         buffer.append("</td>");
      }
      return buffer;
   }
}


