<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename='nls.messages'/>
<table>
<tr>
<td id='td1'>
<!-- 
      /*Первая строчка Мальчики*/
 -->
<a href='#' onclick="smile(':)'); return false;" rel='nofollow'>
<img border='0' src='smiles/smile_.gif'>
</a>
<a href='#' onclick="smile(':('); return false;" rel='nofollow'>
<img border='0' src='smiles/sad.gif'>
</a>
<a href='#' onclick="smile(';)'); return false;" rel='nofollow'>
<img border='0' src='smiles/wink3.gif'>
</a>
</td>
</tr>
<!-- 
      /*Вторая строчка Девочки*/
 -->
<tr>
<td id='td2'>
<a href='#' onclick="smile(':g)'); return false;" rel='nofollow'><img border='0' src='smiles/girl_smile.gif'></a> 
<a href='#' onclick="smile(':g('); return false;" rel='nofollow'><img border='0' src='smiles/girl_sad.gif'></a> 
</td>
</tr>
<!-- 
      /*Третья строчка Остальное*/
 -->
<tr>
<td id='td3'></td>
</tr>
<tr>
<td align=center>
<!-- 
      /* Кнопка "Показать все"*/
 -->
 <fmt:message key="mess11" var="mess"/>
 <c:set var="onClick" scope="page" value='viewsml();'/>
 <c:set var="name" scope="page" value='btn1'/>
 <c:set var="numb" scope="page" value='1'/>
 <%@ include file="fd_button.jsp" %>
<br><br><br>
</td>
</tr>
</table>
