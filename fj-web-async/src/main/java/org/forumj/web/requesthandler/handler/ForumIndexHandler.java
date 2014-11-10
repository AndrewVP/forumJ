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
package org.forumj.web.requesthandler.handler;

import static org.forumj.common.web.Pin.*;
import static org.forumj.tool.Diletant.*;
import static org.forumj.tool.FJServletTools.*;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.forumj.common.FJUrl;
import org.forumj.common.db.entity.*;
import org.forumj.common.db.service.*;
import org.forumj.common.exception.*;
import org.forumj.common.tool.*;
import org.forumj.tool.*;
import org.forumj.web.requesthandler.BaseHandler;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class ForumIndexHandler extends BaseHandler{

    @Override
    protected void doHandle(AsyncContext context) throws FJWebException {
        try{
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            long startTime = new Date().getTime();
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
            IndexService indexService = FJServiceHolder.getIndexService();
            IgnorService ignorService = FJServiceHolder.getIgnorService();
            FolderService folderService = FJServiceHolder.getFolderService();
            Long m_xb = indexService.getLastPostId();
            Long m_xt = indexService.getMaxThreadId();
            // соединяемся и определяем кол-во страниц
            long nfirstpost=(pageNumber-1)*user.getPp();
            if (nfirstpost < 0){
               nfirstpost = 0;
            }
            // Интерфейс по умолчанию
            if (session.getAttribute("view") == null){
               session.setAttribute("view", user.getView());
            }
            List<IIgnor> ignorList = ignorService.readUserIgnor(user.getId());
            FJThreads threads = indexService.getThreads(Long.valueOf((Integer) session.getAttribute("view")), nfirstpost, user, ignorList);
            List<IFJThread> threadsList = threads.getThreads();
            long threadsCount = threads.getThreadCount();
            // кол-во страниц с заголовками
            int couP = (int) (Math.floor(threadsCount/user.getPp())+2);
            // Проверяем наличие почты
            String newMail = "";
            if (user.isLogined()) {
               int mailCount = indexService.getNewMailCount(user.getId());
               if (mailCount > 0) {
                  newMail="<a class=hdforum href='" + FJUrl.SETTINGS + "?id=2' rel='nofollow'><font color=red>" + locale.getString("mess66") + " " + mailCount +" " + locale.getString("mess67") + "</font></a>";
               }
            }
            if (session.getAttribute("vname") == null){
                session.setAttribute("vname", indexService.getViewName(Long.valueOf((Integer)session.getAttribute("view"))));
            }
            List<IFJInterface> viewsList = indexService.getViews(userId);
            response.setContentType("text/html; charset=UTF-8");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/forum_index.jsp");
            dispatcher.include(request, response);
        }catch (Throwable e){
            throw new FJWebException(e);
        }
    }
        
        public void old(int couP, List<IFJInterface> viewsList, IUser user, LocaleString locale, int pageNumber, String newMail, long threadsCount, List<IFJThread> threadsList, FolderService folderService, HttpSession session, HttpServletResponse response, long startTime) throws Exception{
            StringBuffer buffer = new StringBuffer();
            
            
            buffer.append("<script language='javascript' type='text/javascript'>");
            buffer.append("// <!-- \n");
            buffer.append("var FORUM_PAGES=" + couP + ";");
            buffer.append("\n// -->");
            buffer.append("</script>");
            // Интерфейс
            // Имя текущего
            
            buffer.append("<div id='interfaces'>");
            buffer.append("<ul>");
            IFJInterface fjinterface;
            for (int vw1=0; vw1< viewsList.size(); vw1++)
            {
                fjinterface = viewsList.get(vw1);
                buffer.append("<li><a href='#tabs-1' id='" + fjinterface.getId() + "'>" + fjinterface.getName() + "</a></li>");
            }
            buffer.append("</ul>");
            buffer.append("<div id='" + viewsList.get(0).getId() + "'>");
            buffer.append("<div>");
            // Стройка!!!
            String mess = "";
            if (!user.isLogined()){
                mess=locale.getString("mess133");
            }else{
                mess=locale.getString("mess134");
            }
            buffer.append("<table width='100%'>");
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
                        buffer.append("<a class='pageLink' href='" + FJUrl.INDEX + "?page=" + i1 + "'>" + i1 + "</a>");
                    }
                }
            }
            buffer.append("<font class='page' style='margin-left:5px;'><b>" + locale.getString("mess136") + "&nbsp;" + (couP-1) + "</b></font>");
            buffer.append("</td>");
            buffer.append("<td align='right'>");
            buffer.append("<form name='str' method='get' class=frmsmall action='" + FJUrl.INDEX + "'>");
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
            buffer.append("</div>");
            // Закончили таблицу главных ссылок
            // Таблица Заголовков тем
            buffer.append("<div>");
            if (user.isLogined()){
                // Форма выводится только для зарегистрированых
                buffer.append("<form method='post' name='del_form' action='" + FJUrl.MOVE_TITLE + "?page=" + pageNumber + "' class=frmsmall>");
            }
            buffer.append("<div style='height:400px'>");
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
                IFJThread thread = threadsList.get(threadIndex);
                buffer.append(writeThread(thread, user, locale, threadIndex, pageNumber));
            }
            // Главные ссылки внизу страницы
            buffer.append("</table>");
            buffer.append("</div>");
//            buffer.append("<script type='text/javascript'>");
//            buffer.append("if (request){");
//            if(threads.getIndctrIds() == null || threads.getIndctrIds().trim().length() == 0){
//               buffer.append("var idss = '0';");
//            }else{
//               buffer.append("var idss = '" + threads.getIndctrIds().substring(1) + "';");
//            }
//            buffer.append("getIndicatorInfo();");
//            buffer.append("}");
//            buffer.append("</script>");
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
                        buffer.append("<a class='pageLink' href='" + FJUrl.INDEX + "?page=" + i1 + "'>" + i1 + "</a>");
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
                buffer.append("<span class=mnuforum>" + locale.getString("mess81") + "</span><span class=nik>" + session.getAttribute("vname") + "</span>");
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
            }else{
                buffer.append("</table>");        
            }
            buffer.append("</div>");
            buffer.append("</div>");
            buffer.append("</div>");
            
            Double allTime = (double) ((new Date().getTime() - startTime));
            DecimalFormat format = new DecimalFormat("##0.###");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            String out = buffer.toString();
            writer.write(out.replace("ъъ_ъ", format.format(allTime/1000)));
        }

    private StringBuffer writeThread(IFJThread thread, IUser user, LocaleString locale, int threadIndex, int pageNumber) throws InvalidKeyException{
        StringBuffer buffer = new StringBuffer();
        if (thread.getDisain() == 1) { 
           buffer.append("<tr class=trees >");
        }else {
           buffer.append("<tr class=matras>");   
        }
        // Картинки
        // Пиктограммка опроса
        buffer.append("<td width='10' align='center' style='padding:0px 5px 0px 5px'>");
        if (thread.isClosed()){
           buffer.append("<img border='0' src='skin/standart/picts/closed.png'>");
        }else if (thread.isQuest()){
           buffer.append("<img border='0' src='smiles/quest.gif'>");
        }else{
           if (thread.getDock()== NOTICE){
              buffer.append("<img border='0' src='smiles/icon4.gif'>");
           }else if(thread.getDock() == PIN) {
              buffer.append("<img border='0' src='picts/f_pinned.gif'>");
           }else {
              buffer.append("<img border='0' src='smiles/icon1.gif'>");
           }
        }
        buffer.append("</td>");
        buffer.append("<td width='1'></td>");
        // Тема
        buffer.append("<td><p>");
        String str_head = HtmlChars.convertHtmlSymbols(removeSlashes(thread.getHead()));
        // Добавляем смайлики
        str_head = Diletant.fd_head(str_head);
        // Опрос? Добавляем "метку"
        if (thread.isQuest()){
           str_head="<b>" +locale.getString("mess9")+ "</b> " +str_head;
        }
        // Подписываем прикрепленные
        switch (thread.getDock()){
        case PIN:
           buffer.append("<font class=trforum><b>" +locale.getString("mess7")+ " </b><a href='" + FJUrl.VIEW_THREAD + "?id=" +thread.getId().toString() + "' onclick='threadClick(" + thread.getId().toString() + ");return false;'>" +str_head+ "</a></font>");
           break;
        case NOTICE:
           buffer.append("<font class=trforum><b>" +locale.getString("mess8")+ " </b><a href='" + FJUrl.VIEW_THREAD + "?id=" +thread.getId().toString() + "' onclick='threadClick(" + thread.getId().toString() + ");return false;'>" +str_head+ "</a></font>");
           break;
        case BIRTHDAY:
           buffer.append("<font class=trforum><b>" +locale.getString("mess163")+ " </b><a href='" + FJUrl.VIEW_THREAD + "?id=" +thread.getId().toString() + "' onclick='threadClick(" + thread.getId().toString() + ");return false;'>" +str_head+ "</a></font>");
           break;
        case COMMON:
           buffer.append("<font class=trforum><a href='" + FJUrl.VIEW_THREAD + "?id=" +thread.getId().toString()+ "' onclick='threadClick(" + thread.getId().toString() + ");return false;'>" +str_head+ "</a></font>");
           break;
        }
        // Cсылки на страницы в ветке
        int pcount = thread.getPcount();
        if (pcount+1>user.getPt() || user.isModerator()|| thread.getAuthId().equals(user.getId())) {
           buffer.append("<br />");
        }
        if (pcount+1>user.getPt()) {
           buffer.append("<font face='Verdana' size='1pt'>" +locale.getString("mess10")+ ":&nbsp");
           int k1=0;
           int k2=0;
           for (int k=1; k<=Math.floor((pcount+1)/user.getPt()) + 1; k++) {
              k1=k1+1;
              if (k1==10){
                 buffer.append("<a href='" + FJUrl.VIEW_THREAD + "?page=" +k+ "&id=" +thread.getId().toString()+ "'>" +k+ "</a>");
                 if (k != Math.floor((pcount+1)/user.getPt()) + 1) buffer.append(",&nbsp;");
                 k1=0;
                 k2=k2+1;
              }
              if (k==1){
                 buffer.append("<a href='" + FJUrl.VIEW_THREAD + "?page=" +k+ "&id=" +thread.getId().toString()+ "'>" +k+ "</a>,&nbsp;");
              }
              if ((Math.floor((pcount+1)/user.getPt())-k2*10 + 1)< 10 && (k-k2*10) != 0 && k!=1){
                 buffer.append("<a href='" + FJUrl.VIEW_THREAD + "?page=" +k+ "&id=" +thread.getId().toString()+ "'>" +k+ "</a>");
                 if (k != Math.floor((pcount+1)/user.getPt()) + 1) buffer.append(",&nbsp;");
              }

           }
           buffer.append("&nbsp;</font>");
        }
        if(user.isModerator()){
           buffer.append("<font face='Verdana' size='1pt'>");
           if (thread.getDock().getCode() > 0){
              buffer.append("<a href='" + FJUrl.PIN_THREAD + "?id=" + thread.getId() + "&pin=" + COMMON.getCode() + "'>" +locale.getString("MSG_COMMON_PIN")+ "</a>&nbsp;");
           }
           if (thread.getDock() != PIN){
              buffer.append("<a href='" + FJUrl.PIN_THREAD + "?id=" + thread.getId() + "&pin=" + PIN.getCode() + "'>" +locale.getString("MSG_PIN")+ "</a>&nbsp;");
           }
           if (thread.getDock() != BIRTHDAY){
              buffer.append("<a href='" + FJUrl.PIN_THREAD + "?id=" + thread.getId() + "&pin=" + BIRTHDAY.getCode() + "'>" +locale.getString("MSG_BIRTHDAY_PIN")+ "</a>&nbsp;");
           }
           if (thread.getDock() != NOTICE){
              buffer.append("<a href='" + FJUrl.PIN_THREAD + "?id=" + thread.getId() + "&pin=" + NOTICE.getCode() + "'>" +locale.getString("MSG_NOTICE_PIN")+ "</a>&nbsp;");
           }
           buffer.append("<a href='" + FJUrl.MOVE_THREAD_TO_RECYCLE + "?id=" +thread.getId().toString()+ "&usr=0&page=" + pageNumber + "'>" + locale.getString("mess70") + "</a>");
           buffer.append("&nbsp;</font>");
        }
        if (user.isModerator() || thread.getAuthId().equals(user.getId())){
           buffer.append("<font face='Verdana' size='1pt'>");
           if (thread.isClosed()){
              buffer.append("<a href='close?id=" +thread.getId().toString()+ "&close=0&page=" + pageNumber + "'>" + locale.getString("MSG_OPEN_THREAD") + "</a>");
           }else{
              buffer.append("<a href='close?id=" +thread.getId().toString()+ "&close=1&page=" + pageNumber + "'>" + locale.getString("MSG_CLOSE_THREAD") + "</a>");
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
        buffer.append("<div class='mnuforum'><font size='1pt' color='green'>" + thread.getSnid() + "</font><br>");
        // Количество просмотров всего
        buffer.append("<font size='1pt' color='purple'>" + thread.getSnall() + "</font></div></td>");
        // Автор
        buffer.append("<td width='120' align='center' valign='middle'><div class='trforum'><font size='1pt'>" +HtmlChars.convertHtmlSymbols(thread.getNick())+ "</font></div></td>");
        // Автор последнего поста
        buffer.append("<td width='120' align=center><div class='mnuforum'><font size='1pt'>" +HtmlChars.convertHtmlSymbols(thread.getLastPostNick())+ "</font></div>");
        // Время последнего поста
        buffer.append("<div class='mnuforum'><a href='" + FJUrl.VIEW_THREAD + "?id=" + thread.getId().toString() + "&end=1#end' rel='nofollow'><font size='1pt'>" + Time.date("dd.MM.yy HH:mm",thread.getLastPostTime().getTime()) + "</font></a></div>");
        buffer.append("</td>");
        // Папка
        buffer.append("<td align='center' valign='middle'>");
        buffer.append("<div class='mnuforum'><font size='1pt'>" +thread.getFolder()+ "</font></div>");
        buffer.append("</td>");
        // Флажок (только для зарегистрированых)
        if (user.isLogined()){
           buffer.append("<td align='center' valign='middle'>");
           buffer.append("<input type='checkbox' id='ch" +threadIndex+ "' name='" +threadIndex+ "' value='" +thread.getId().toString()+ "'>");
           buffer.append("</td>");
           buffer.append("<td style='padding:0px 5px 0px 5px' align='right'>");
           buffer.append("<a href='" + FJUrl.MOVE_THREAD_TO_RECYCLE + "?id=" +thread.getId().toString()+ "&usr=" +String.valueOf(user.getId())+ "&page=" +thread.getPg()+ "'><img border='0' src='picts/del1.gif'></a>");
           buffer.append("</td>");
        }
        buffer.append("</tr>");
        return buffer;
     }
}
