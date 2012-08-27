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
         <%@ include file="menu.jsp" %>
         </table>
         </td>
         </tr>
<!-- 
         // Форма отправки письма личной переписки   
         if (id>1 && id<6){
         }
-->
            <c:if test='${id > 1 && id < 6}'>
         <%@ include file="settings_mail_form.jsp" %>
            </c:if>
<!-- 
         // Баннер внизу, счетчики и копирайт.
-->
         <%@ include file="footer.jsp" %>

            </table>
         </body>
         </html>
         