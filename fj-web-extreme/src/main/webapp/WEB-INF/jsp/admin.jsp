<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename='nls.messages'/>
<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv='content-type' content='text/html; charset=UTF-8' />
<link rel="stylesheet" href="css/style.css" type="text/css" />
<script type="text/javascript" src="js/smile_.js"></script>
<script type="text/javascript" src="js/checklength.js"></script>
<script type="text/javascript" src="js/jstags.js"></script>
<script type="text/javascript">
//<!--
mess132=<fmt:message key="mess132"/>;
mess128=<fmt:message key="mess128"/>;
mess129=<fmt:message key="mess129"/>;
// -->
</script>
<script type="text/javascript" src="js/send_submit.js"></script>
<link rel="icon" href="/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<title><fmt:message key="mess127"/></title>
</head>
<!-- 
         // Цвет фона страницы
-->
         <body class='mainBodyBG'>
<!-- 
         // Главная таблица
-->
         <table border='0' style='border-collapse: collapse' width='100%'>
<!-- 
         // Таблица с лого и верхним баннером
-->
         <%@ include file="logo.jsp" %>
<!-- 
         // Таблица главных ссылок
-->
         <tr>
         <td width='100%'>
         <table border='0' style='border-collapse: collapse' width='100%'>
         <%@ include file="menu.jsp" %>
<!-- 
         // Главное "меню"
         menu(request, user, locale, false));
-->
         </table></td></tr>
         <tr>
         <td>
<!-- 
         // Таблица форума
-->
         <table class='content'><tr><td>
<!-- 
         // Таблица контента
-->
         <table class='content'><tr>
<!-- 
         // Ссылки на сервисы
         // Игнор-лист.
-->
         <td height='300' valign="top" width='150'>
         <table class='control'>
         <tr class='heads'>
         <th class='internal'>
         <div class='mnuprof'><fmt:message key="mess24"/></div>
         </th>
         </tr>
         <tr>
         <td class='internal'>
         <a class='mnuprof' href='control.php?id=1'><fmt:message key="mess24"/></a><br/>
         </td>
         </tr>
         </table>
<!-- 
         /*e-mail*/
-->
         <table class='control'><tr class='heads'>
         <th class='internal'>
         <div class='mnuprof'><fmt:message key="MSG_EMAIL"/></div>
         </th>
         </tr><tr>
         <td class='internal'>
         <a class='mnuprof' href='control.php?id=13'><fmt:message key="MSG_EMAIL"/></a><br/>
         </td>
         </tr></table>
<!-- 
         // Личная переписка
-->
         <table class='control'><tr class='heads'>
         <th class='internal'>
         <div class='mnuprof'><fmt:message key="mess23"/></div>
         </th>
         </tr><tr>
         <td class='internal'>
         <a class='mnuprof' href='control.php?id=2'><fmt:message key="mess54"/></a><br/>
         </td>
         </tr><tr>
         <td class='internal'>
         <a class='mnuprof' href='control.php?id=3'><fmt:message key="mess57"/></a><br/>
         </td>
         </tr><tr>
         <td class='internal'>
         <a class='mnuprof' href='control.php?id=4'><fmt:message key="mess55"/></a><br/>
         </td>
         </tr><tr>
         <td class='internal'>
         <a class='mnuprof' href='control.php?id=5'><fmt:message key="mess56"/></a><br/>
         </td>
         </tr></table>
<!-- 
         // Интерфейсы
-->
         <table class='control'><tr class='heads'>
         <th class='internal'>
         <div class='mnuprof'><fmt:message key="mess71"/></div>
         </th>
         </tr><tr>
         <td class='internal'>
         <a class='mnuprof' href='control.php?id=6'><fmt:message key="mess71"/></a><br/>
         </td>
         </tr><tr>
         <td class='internal'>
         <a class='mnuprof' href='control.php?id=7'><fmt:message key="mess72"/></a><br/>
         </td>
         </tr></table>
<!-- 
         // Подписка
-->
         <table class='control'><tr class='heads'>
         <th class='internal'>
         <div class='mnuprof'><fmt:message key="mess86"/></div>
         </th>
         </tr><tr>
         <td class='internal'>
         <a class='mnuprof' href='control.php?id=8'><fmt:message key="mess86"/></a><br/>
         </td>
         </tr></table>
<!-- 
         // Аватара
-->
         <table class='control'><tr class='heads'>
         <th class='internal'>
         <div class='mnuprof'><fmt:message key="mess93"/></div>
         </th>
         </tr><tr>
         <td class='internal'>
         <a class='mnuprof' href='control.php?id=9'><fmt:message key="mess93"/></a><br/>
         </td>
         </tr></table>
<!-- 
         // Местонахождение
-->
         <table class='control'><tr class='heads'>
         <th class='internal'>
         <div class='mnuprof'><fmt:message key="mess104"/></div>
         </th>
         </tr><tr>
         <td class='internal'>
         <a class='mnuprof' href='control.php?id=10'><fmt:message key="mess104"/></a><br/>
         </td>
<!-- 
         /*Locale*/
-->
         </tr><tr>
         <td class='internal'>
         <a class='mnuprof' href='control.php?id=12'><fmt:message key="MSG_INTERF_LOCALE"/></a><br/>
         </td>
         </tr></table>
<!-- 
         /*Подпись*/
-->
         <table class='control'><tr class='heads'>
         <th class='internal'>
         <div class='mnuprof'><fmt:message key="mess138"/></div>
         </th>
         </tr><tr>
         <td class='internal'>
         <a class='mnuprof' href='control.php?id=11'><fmt:message key="mess138"/></a><br/>
         </td>
         </tr></table>

         </td>
         <td valign="top" style='padding-left:5px;'>
         </td>
         </tr>
         </table>
         </td>
         </tr>
         </table>
         </td>
         </tr>
<!-- 
         // Главное "меню"
-->
         <tr>
         <td width='100%'>
         <table border='0' style='border-collapse: collapse' width='100%'>
<!-- 
         menu(request, user, locale, false));
-->
         </table>
         </td>
         </tr>
<!-- 
         // Форма отправки письма личной переписки   
         if (id>1 && id<6){
            /*Форма нового мейла*/
            <tr>
            <td>
            <table>
            <tr>
            <td>
            <form name='post' action='send.php?id=" + id + "' method='POST'>
            <table width='100%'>
            <tr>
            <td width='100%'>
            <table width='100%'>
            /*От*/
            <tr>
            <td align='LEFT'>
            <div class='mnuprof'>
            locale.getString("mess58") + "&nbsp;
            </div>
            </td>
            <td>
            <div class='mnuprof'>
            user.getNick());
            </div>
            </td>
            </tr>
            /*Кому*/
            <tr>
            <td align='LEFT'>
            <div class='mnuprof'>
            locale.getString("mess28") + "&nbsp;
            </div>
            </td>
            <td colspan='2'>
            Integer error = (Integer) session.getAttribute("error
            if (error != null){
               <input type='text' class='mnuforumSm' value='" + session.getAttribute("rcvr") + "' name='RCVR' size='30'>
               if (error == 1){
                  <span class=hdforum>
                  <font color='red'>
                  locale.getString("mess65"));
                  </font>
                  </span>
               }
            }else{
               <input type=text class='mnuforumSm' name='RCVR' size='30'>
            }
            </td>
            </tr>
            /*Тема*/
            <tr>
            <td align='LEFT'>
            <div class='mnuprof'>
            locale.getString("mess59") + "&nbsp;
            </div>
            </td>
            <td>
            if (error != null){
               <input type=text class='mnuforumSm' name='NHEAD' value='" + session.getAttribute("head") + "' size='100'>
            }else{
               <input type=text class='mnuforumSm' name='NHEAD' size='100'>
            }
            </td>
            </tr>
            </table>
            </td>
            </tr>
            <tr>
            <td>
            <table>
            <tr>
            /*Смайлики заголовок*/
            <td width='400' align='CENTER'>
            <p>
            locale.getString("mess21") + ":
            </p>
            </td>
            /*Приглашение*/
            <td align='CENTER'>
            <p>" + locale.getString("mess12") + "</p>
            </td>
            </tr>
            /*Пост*/
            <tr>
            <td valign='TOP' width='100%' height='100%'>
            /*Смайлики*/
            smiles_add(locale.getString("mess11")));
            </td>
            <td width='500' align='CENTER' valign='top'>
            /*Автотеги*/
            autotags_add());
            /*текстарий*/
            <p>
            String textareaValue = "";
            if (error != null) {
               textareaValue = (String) session.getAttribute("body
            }
            <textarea class='mnuforumSm' rows='20' id='ed1' name='A2' cols='55'>" + textareaValue + "</textarea>
            </p>
            /*Кнопки*/
            <table>
            <tr>
            <td>
            fd_button(locale.getString("mess13"),"send_submit("write","B1", "1"));
            </td>
            <td>
            fd_button(locale.getString("mess63"),"send_submit("view","B3", "1"));
            </td>
            </tr>
            </table>
            /*Прередаем нужные пераметры...*/
            fd_form_add(user));
            </td>
            </tr>
            </table>
            </td>
            </tr>
            </table>
            </form>
            </td>
            </tr>
            </table>
            </td>
            </tr>
         }
         // Баннер внизу, счетчики и копирайт.
         footer(request));
-->
            </table>
         </body>
         </html>
         