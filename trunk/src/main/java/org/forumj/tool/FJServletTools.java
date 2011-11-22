package org.forumj.tool;

import static org.forumj.tool.PHP.*;

import java.util.*;

import javax.servlet.http.*;

import org.forumj.db.entity.IUser;
import org.forumj.exception.InvalidKeyException;


public class FJServletTools {
   public static void cache(HttpServletResponse response){
      response.setHeader("Expires", "Mon, 26 Jul 1991 05:00:00 GMT");
      response.setHeader("Last-Modified", new Date().toString());
      response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
      response.setHeader("Cache-Control", "post-check=0, pre-check=0");
      response.setHeader("Pragma", "no-cache");
   }

   public static StringBuffer menu(HttpServletRequest request, IUser user, LocaleString locale, boolean index) throws InvalidKeyException{
      StringBuffer buffer = new StringBuffer();
      Enumeration<String> parameters = request.getParameterNames();
      boolean first = true;
      String query = "";
      while (parameters.hasMoreElements()){
         String parameterName = parameters.nextElement();
         if (!parameterName.equalsIgnoreCase("lang") && !parameterName.equalsIgnoreCase("exit")){
            if(first){
               query = "?";
               first = false;
            }else{
               query += "&";
            }
            query += parameterName + "=" + request.getParameter(parameterName);  
         }
      }
      String ukr = request.getContextPath() + "/" + request.getRequestURI().split("/")[request.getRequestURI().split("/").length-1] + ("".equalsIgnoreCase(query.trim()) ? "?lang=ua" : query.trim() + "&lang=ua");
      String rus = request.getContextPath() + "/" + request.getRequestURI().split("/")[request.getRequestURI().split("/").length-1] + ("".equalsIgnoreCase(query.trim()) ? "?lang=ru" : query.trim() + "&lang=ru");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<table class=control>");
      buffer.append("<tr>");
      buffer.append("<td class=leftTop></td>");
      buffer.append("<td class=top colspan='2'></td>");
      buffer.append("<td class=rightTop></td>");
      buffer.append("</tr>");
      buffer.append("<tr class=heads>");
      buffer.append("<td class=left></td>");
      /*Логин есть?*/
      if (!user.isLogined()){
         /*
                  Если Гость
                  Авторизуемся id: код (1-Просто вход с "темы"), gid: номер темы, pg: номер страницы
          */
         buffer.append("<td class=bg align='LEFT'>");
         if(!index){
            /*Список тем*/
            buffer.append("<img src='picts/index.gif' border='0' class='menuImg'>");
            buffer.append("<a class=mnuforumSm href='index.php'>");
            buffer.append(locale.getString("mess135"));
            buffer.append("</a>");
         }
         /*Новая тема*/
         buffer.append("<img src='picts/new_top.gif' border='0' class='menuImg'>");
         buffer.append("<a class=mnuforumSm href='auth.php' rel='nofollow'>");
         buffer.append(locale.getString("mess4"));
         buffer.append("</a>");
         /*Новый опрос*/
         buffer.append("<img src='picts/new_quest.gif' border='0' class='menuImg'>");
         buffer.append("<a class=mnuforumSm href='auth.php' rel='nofollow'>");
         buffer.append(locale.getString("mess3"));
         buffer.append("</a>");
         /*Поиск*/
         buffer.append("<img src='picts/new_search.gif' border='0' class='menuImg'>");
         buffer.append("<a class=mnuforumSm href='search.php' rel='nofollow'>");
         buffer.append(locale.getString("mess30"));
         buffer.append("</a>");
         /*Вход*/
         buffer.append("<img src='picts/key_add.gif' border='0' class='menuImg'>");
         buffer.append("<a class=mnuforumSm href='auth.php?id=1' rel='nofollow'>");
         buffer.append(locale.getString("mess1"));
         buffer.append("</a>");
         /*Регистрация*/
         buffer.append("<img src='picts/new_user.gif' border='0' class='menuImg'>");
         buffer.append("<a class=mnuforumSm href='reg.php?id=1' rel='nofollow'>");
         buffer.append(locale.getString("mess2"));
         buffer.append("</a>");
         buffer.append("</td>");
         buffer.append("<td class=bg align='right'>");
         /*Укр. интерфейс*/
         buffer.append("<a class=mnuforumSm href='" + ukr + "' rel='nofollow'>");
         buffer.append("Українська");
         buffer.append("</a>");
         buffer.append("•");
         /*Рус. интерфейс*/
         buffer.append("<a class=mnuforumSm href='"+ rus + "' rel='nofollow'>");
         buffer.append("Русский");
         buffer.append("</a>");
         buffer.append("</td>");
      }else{
         /*Если логин есть*/
         buffer.append("<td class=bg align='LEFT'>");
         /*Ник*/
         buffer.append("<img src='picts/nick.gif' border='0' class='menuImg'>");
         buffer.append("<span class=nik>");
         buffer.append(stripslashes(htmlspecialchars(user.getNick())));
         buffer.append("</span>");
         if(!index){
            /*Список тем*/
            buffer.append("<img src='picts/index.gif' border='0' class='menuImg'>");
            buffer.append("<a class=mnuforumSm href='index.php'>");
            buffer.append(locale.getString("mess135"));
            buffer.append("</a>");
         }
         /*Новая тема*/
         buffer.append("<img src='picts/new_top.gif' border='0' class='menuImg'>");
         buffer.append("<a class=mnuforumSm href='mess.php' rel='nofollow'>");
         buffer.append(locale.getString("mess4"));
         buffer.append("</a>");
         /*Новый опрос*/
         buffer.append("<img src='picts/new_quest.gif' border='0' class='menuImg'>");
         buffer.append("<a class=mnuforumSm href='opr.php' rel='nofollow'>");
         buffer.append(locale.getString("mess3"));
         buffer.append("</a>");
         /*Поиск*/
         buffer.append("<img src='picts/new_search.gif' border='0' class='menuImg'>");
         buffer.append("<a class=mnuforumSm href='search.php' rel='nofollow'>");
         buffer.append(locale.getString("mess30"));
         buffer.append("</a>");
         /* Личные настройки*/
         buffer.append("<img src='picts/profile.gif' border='0' class='menuImg'>");
         buffer.append("<a class=mnuforumSm href='control.php' rel='nofollow'>");
         buffer.append(locale.getString("mess31"));
         buffer.append("</a>");
         /* Переписка*/
         buffer.append("<img src='picts/email.gif' border='0' class='menuImg'>");
         buffer.append("<a class=mnuforumSm href='control.php?id=2' rel='nofollow'>");
         buffer.append(locale.getString("mess23"));
         buffer.append("</a>");
         /*Выход*/
         String exitUrl = request.getContextPath() + "/" + request.getRequestURI().split("/")[request.getRequestURI().split("/").length-1] + (query == null || "".equalsIgnoreCase(query.trim()) ? "?exit=0" : query.trim() + "&exit=0");
         buffer.append("<img src='picts/key_delete.gif' border='0' class='menuImg'>");
         buffer.append("<a class=mnuforumSm href='" + exitUrl + "' rel='nofollow'>");
         buffer.append(locale.getString("mess6"));
         buffer.append("</a>");
         buffer.append("</td>");
         /* Укр. интерфейс*/
         buffer.append("<td class=bg align='right'>");
         buffer.append("<a class=mnuforumSm href='" + ukr + "' rel='nofollow'>");
         buffer.append("Українська");
         buffer.append("</a>");
         buffer.append("•");
         /* Рус. интерфейс*/
         buffer.append("<a class=mnuforumSm href='" + rus + "' rel='nofollow'>");
         buffer.append("Русский");
         buffer.append("</a>");
         buffer.append("</td>");
      }
      buffer.append("<td class=right></td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td class=leftBtm></td>");
      buffer.append("<td class=btm colspan=2></td>");
      buffer.append("<td class=rightBtm></td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</td>");
      buffer.append("</tr>");     
      return buffer;
   }

   public static StringBuffer logo(HttpServletRequest request){
      StringBuffer buffer = new StringBuffer();
      String ref=request.getContextPath();
      buffer.append("<tr>");
      buffer.append("<td width='100%'>");
      buffer.append("<table border='0' width='100%' style='border-collapse: collapse'>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<a href='/'><img border='0' src='" + ref + "/images/all/title.gif'></a><br>");
      buffer.append("<a href='/' class=tbtextnread>www.Дилетант.com.ua</a>");
      buffer.append("</td>");
      buffer.append("<td align=center>");
      buffer.append("<a href='http://www.donor.org.ua/index.php?module=help' target=\"_blank\" title='Украинская Открытая Ассоциация Организаций, Групп и Лиц, работающих с детьми, страдающими онкозаболеваниями'><img src='banner/donor_2.gif'></a>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table></td></tr>");
      return buffer;
   }

   public static StringBuffer footer(HttpServletRequest request){
      StringBuffer buffer = new StringBuffer();
      String ref=request.getContextPath();
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<table width='100%'>");
      buffer.append("<tr>");
      buffer.append("<td align=\"center\">");
      buffer.append("<a href='http://www.donor.org.ua/index.php?module=help' target=\"_blank\" title='Украинская Открытая Ассоциация Организаций, Групп и Лиц, работающих с детьми, страдающими онкозаболеваниями'><img src='" + ref + "/banner/donor_2.gif'></a>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td align='center' width='100%'>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<table>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<span class='copy'>");
      buffer.append("За достоверность и правдивость опубликованой информации администрация сайта и форума ответственности не несет<br>");
      buffer.append("<a href='http://www.diletant.com.ua'>");
      buffer.append("www.diletant.com.ua");
      buffer.append("</a>");
      buffer.append("<br>");
      buffer.append("Пишите нам:&nbsp;<a href='mailto:diletant@diletant.com.ua'>diletant@diletant.com.ua</a>");
      buffer.append("</span>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</td>");
      buffer.append("</tr>");
      return buffer;
   }


}
