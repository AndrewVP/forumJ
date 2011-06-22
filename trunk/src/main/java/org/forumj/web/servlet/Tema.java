/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.web.servlet;

import static org.forumj.tool.Diletant.*;
import static org.forumj.tool.FJServletTools.*;
import static org.forumj.web.servlet.tool.FJServletTools.*;
import static org.forumj.tool.PHP.*;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.forumj.db.dao.TemaDao;
import org.forumj.db.entity.*;
import org.forumj.exception.InvalidKeyException;
import org.forumj.tool.LocaleString;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebServlet(urlPatterns = {"/tema.php"}, name="tema")
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
         String $defLang = (String) session.getAttribute("lang");
         if ($defLang == null){
            $defLang = "ua"; 
         }
         cache(response);
         // Какой это номер страницы? если без номера, то первый
         Integer $pg = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));
         // id Темы
         Long $gid = request.getParameter("id") == null ? 1 : Long.valueOf(request.getParameter("id"));
         // Номер поста, на который отвечаем
         String $reply = request.getParameter("reply");
         LocaleString locale = new LocaleString($defLang, null, "ua");
         // У гостей интерфейс модератора
         User user = (User) session.getAttribute("user");
         TemaDao $dao = new TemaDao($gid, user);
         session.setAttribute("page", $pg);
         session.setAttribute("id", $gid);
         session.setAttribute("where", request.getContextPath() + "?id=$gid&page=$pg&lang=" + $defLang);
         // Зашли с поиска?
         String $_msg = request.getParameter("msg");
         int $countPosts = 0;
         if ($_msg != null && !"".equals($_msg.trim())){
            $countPosts = $dao.getPostsCountInThread(new Long($_msg));
            $pg=ceil($countPosts/user.getPt());
         }
         // Записываем счетчики
         // Робот?
         if (!isRobot(request)){
            // Нет
            $dao.setSeen();
         }
         String $title = $dao.getTitle();
         buffer.append("<html>");
         buffer.append("<head>");
         buffer.append("<meta http-equiv='content-type' content='text/html; charset=windows-1251'>");
         // Стили
         buffer.append("<style type='text/css'>");
         // Стили
         buffer.append(loadResource("/css/style.css"));
         buffer.append("</style>");
         // Скрипты (смайлики)
         buffer.append("<script type='text/javascript'>");
         buffer.append("// <!--\n");
         buffer.append(loadResource("/js/smile_.js"));
         buffer.append("\n// -->");
         buffer.append("</script>");
         // Скрипты (игнор)
         buffer.append("<script type='text/javascript'>");
         buffer.append("// <!--\n");
         buffer.append(loadResource("/js/jsignor.js"));
         buffer.append("\n// -->");
         buffer.append("</script>");
         // Скрипты (подписка)
         buffer.append("<script type='text/javascript'>");
         buffer.append("// <!--\n");
         buffer.append(loadResource("/js/jssubscribe.js"));
         buffer.append("\n// -->");
         buffer.append("</script>");
         // Скрипты (submit поста)
         buffer.append("<script type='text/javascript'>");
         buffer.append("// <!--\n");
         buffer.append("function post_submit(comand){");
         buffer.append("if (document.post.NHEAD.value.replace(/(^\\s*)|(\\s*$)/g, \"\").length==0){");
         buffer.append("alert('" + locale.getString("mess128") + "');");
         buffer.append("");
         buffer.append("}else if (document.post.A2.value.replace(/(^\\s*)|(\\s*$)/g, \"\").length==0){");
         buffer.append("alert('" + locale.getString("mess128") + "');");
         buffer.append("}else{");
         buffer.append("document.post.comand.value=comand;");
         buffer.append("document.post.submit();}}");
         buffer.append("\n// -->");
         buffer.append("</script>");
         // Скрипты (автовставка тегов)
         buffer.append("<script type='text/javascript'>");
         buffer.append("// <!--\n");
         buffer.append(loadResource("/js/jstags.js"));
         buffer.append("\n// -->");
         buffer.append("</script>");
         buffer.append("<link rel='icon' href='/favicon.ico' type='image/x-icon'>");
         buffer.append("<link rel='shortcut icon' href='/favicon.ico' type='image/x-icon'>");
         buffer.append("<title>");
         buffer.append("форум Дилетантов :: "+$title);
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
         buffer.append(menu(request, user, locale));
         // Сколько страниц?
         Integer $count = $dao.getPostsCountInThread(null);
         Integer $cou_p = ceil($count/user.getPt())+1;
         // Если цитирование или последний пост, то нам на последнюю
         boolean $lastPost = false;
         String $_end = request.getParameter("end");
         if ($reply != null && !"".equals($reply.trim()) || isset($_end)){
            $pg = $cou_p-1;
            $lastPost = true;
         }
         int $nfirstpost = ($pg-1)*user.getPt();
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
         int $i_2=0;
         for (int $i_1=1; $i_1<$cou_p; $i_1++){
            $i_2=$i_2+1;
            if (($i_1>($pg-5) && $i_1<($pg+5))||$i_2==10||$i_1==1||$i_1==($cou_p-1)){
               if ($i_2==10) $i_2=0;
               if ($i_1==$pg){
                  buffer.append("<td class='pagecurrent'>");
                  buffer.append("<span class=mnuforum><b>"+$i_1 + "</b></span>");
                  buffer.append("</td>");
               }
               else {
                  buffer.append("<td class='page'>");
                  buffer.append("<a class=mnuforum href='tema.php?page="+$i_1 + "&id="+$gid + "'>"+$i_1 + "</a>");
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
         int $i2=$pg*user.getPt();
         if ($i2>$count) {
            $i2=$count-($pg-1)*user.getPt();
         }else{
            $i2=user.getPt();
         }
         // Получаем массив постов
         Map<Long, Post> $arrPosts = $dao.getPostsList(fd_timezone_hr(user.getTimezone()), fd_timezone_mn(user.getTimezone()), $nfirstpost,$i2, locale, $pg, $lastPost);
         // Тема
         // Выводим строки
         for (Iterator<Entry<Long, Post>> iterator = $arrPosts.entrySet().iterator(); iterator.hasNext();) {
            Post post = iterator.next().getValue();
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
         $i_2=0;
         for (int $i_1=1; $i_1<$cou_p; $i_1++){
            $i_2=$i_2+1;
            if (($i_1>($pg-5) && $i_1<($pg+5))||$i_2==10||$i_1==1||$i_1==($cou_p-1)){
               if ($i_2==10) $i_2=0;
               if ($i_1==$pg){
                  buffer.append("<td class='pagecurrent'>");
                  buffer.append("<span class=mnuforum><b>"+$i_1 + "</b></span>");
                  buffer.append("</td>");
               }
               else {
                  buffer.append("<td class='page'>");
                  buffer.append("<a class=mnuforum href='tema.php?page="+$i_1 + "&id="+$gid + "'>"+$i_1 + "</a>");
                  buffer.append("</td>");
               }
            }
         }
         buffer.append("</tr>");
         buffer.append("</table>");
         buffer.append("</td>");
         buffer.append("</tr>");
         // Главное "меню"
         buffer.append(menu(request, user, locale));
         buffer.append("</table></td></tr>");
         if (user.isLogined()){
            //Форма подписки/отписки  на ветку
            //Мы уже подписаны?
            String $action = "";
            String $mess = "";
            if ($dao.isUserSubscribed(user.getId())){
               //               Подписка есть, предлагаем отказаться
               $action="delonesubs.php?pg="+$pg;
               $mess=locale.getString("mess90");
            }else{
               //               Подписки нет - тогда предлагаем подписаться
               $action="addsubs.php?pg="+$pg;
               $mess=locale.getString("mess89");   
            }
            buffer.append("<tr>");
            buffer.append("<td align=right>");
            buffer.append("<form id='subs' action='" + $action + "' method='POST'>");
            buffer.append("<table>");
            buffer.append("<tr>");
            buffer.append("<td>");
            buffer.append(fd_button($mess,"subscribe();","btn_subs", "1"));
            //Прередаем нужные пераметры...
            buffer.append("<input type=hidden name='IDT' value='" + $gid + "'>");
            buffer.append(fd_form_add(user));
            buffer.append("</td>");
            buffer.append("</tr>");
            buffer.append("</table>");
            buffer.append("</form>");
            buffer.append("</td>");
            buffer.append("</tr>");
            String $re="";
            String $str_head=$title;
            Post $res2 = null;
            // Если цитируем/редактируем
            if ($reply != null && !"".equals($reply.trim())) {
               $res2 = $dao.getPost(Long.valueOf($reply));
               // Редактируем?
               $str_head=stripslashes($res2.getHead());
               if ($res2.getNick().equalsIgnoreCase(user.getNick())) {
                  // Да
                  session.setAttribute("edit",$reply);
                  $re="";
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
            buffer.append(fd_input("NHEAD", $re + htmlspecialchars($str_head), "70", "1"));
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
            buffer.append("<?echo($_mess12);?>");
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
            String $textarea="";
            if ($reply != null && !"".equals($reply.trim())) {
               String $_ans = request.getParameter("ans");
               if (session.getAttribute("edit") != null){
                  $textarea+=stripslashes($res2.getBody());
               }else if (!isset($_ans)){
                  $textarea+="[quote][b]"+stripslashes($res2.getNick()) + "[/b]";
                  $textarea+=locale.getString("mess14")+chr(13);
                  $textarea+=stripslashes($res2.getBody()) + "[/quote]";
               }else{
                  $textarea+="[b]"+stripslashes($res2.getNick()) + "[/b]";
                  $textarea+=", ";
               }
            }
            buffer.append("<textarea rows='20' class='mnuforumSm' id='ed1' name='A2' cols='55'><?echo($textarea);?></textarea>");
            buffer.append("<br>");
            buffer.append("<input type='checkbox' name='no_exit' value='1'>");
            buffer.append("<?echo($_mess123);?>");
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
            if ($reply != null && !"".equals($reply.trim()) && ($res2.getIdu() == user.getId())){
               buffer.append("<input type=hidden name='IDB' size='20' value='<?echo($reply);?>'>");
               buffer.append("<input type=hidden name='IDTbl' size='20' value='" + $res2.getTablePost() + "'>");
               buffer.append("<input type=hidden name='IDPst' size='20' value='" + $res2.getId().toString() + "'>");
               buffer.append("<input type=hidden name='IDTblHead' size='20' value='" + $res2.getTableHead() + "'>");
               buffer.append("<input type=hidden name='IDHead' size='20' value='" + $res2.getId().toString() + "'>");
            }
            //id темы
            buffer.append("<input type=hidden name='IDT' size='20' value='" + $gid + "'>");
            if ($dao.isQuest()){
               buffer.append("<input type=hidden name='ISQUEST' size='20' value='<?echo(true);?>'>");
            }
            buffer.append("<?echo(fd_form_add());?>");
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
      }
      Double allTime = (double) ((new Date().getTime() - startTime));
      DecimalFormat format = new DecimalFormat("##0.###");
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter writer = response.getWriter();
      String out = buffer.toString();
      writer.write(out.replace("ъъ_ъ", format.format(allTime/1000)));
   }
}
