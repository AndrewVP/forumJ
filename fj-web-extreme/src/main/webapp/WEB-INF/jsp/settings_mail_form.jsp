<%@page import="org.forumj.common.FJUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename='nls.messages'/>
<!-- 
            /*Форма нового мейла*/
 -->
<tr>
<td>
<table>
<tr>
<td>
<form name='post' action='<%=FJUrl.SEND_PIVATE_MESSAGE%>?id=<c:out value="${id}"/>' method='post'>
<table width='100%'>
<tr>
<td width='100%'>
<table width='100%'>
<!-- 
            /*От*/
 -->
<tr>
<td align='LEFT'>
<div class='mnuprof'><fmt:message key="mess58"/>&nbsp;</div>
</td>
<td>
<div class='mnuprof'><c:out value='${sessionScope["user"].nick}'/></div>
</td>
</tr>
<!-- 
            /*Кому*/
 -->
<tr>
<td align='LEFT'>
<div class='mnuprof'><fmt:message key="mess28"/>&nbsp;</div>
</td>
<td colspan='2'>
<c:if test='${sessionScope["error"] != null}'>
   <input type='text' class='mnuforumSm' value='<c:out value='${sessionScope["rcvr"]}'/>' name='RCVR' size='30'>
<c:if test='${sessionScope["error"] == 1}'>
      <span class=hdforum><font color='red'><fmt:message key="mess65"/></font></span>
</c:if>
</c:if>
<c:if test='${sessionScope["error"] == null}'>
   <input type=text class='mnuforumSm' name='RCVR' size='30'>
</c:if>
</td>
</tr>
<!-- 
            /*Тема*/
 -->
<tr>
<td align='LEFT'>
<div class='mnuprof'><fmt:message key="mess59"/>&nbsp;</div>
</td>
<td>
<c:if test='${sessionScope["error"] != null}'>
   <input type=text class='mnuforumSm' name='NHEAD' value='<c:out value='${sessionScope["head"]}'/>' size='100'>
</c:if>
<c:if test='${sessionScope["error"] == null}'>
   <input type=text class='mnuforumSm' name='NHEAD' size='100'>
</c:if>
</td>
</tr>
</table>
</td>
</tr>
<tr>
<td>
<table>
<tr>
<!-- 
            /*Смайлики заголовок*/
 -->
<td width='400' align='CENTER'>
<p><fmt:message key="mess21"/>:</p>
</td>
<!-- 
            /*Приглашение*/
 -->
<td align='CENTER'>
<p><fmt:message key="mess12"/></p>
</td>
</tr>
<!-- 
            /*Пост*/
 -->
<tr>
<td valign='TOP' width='100%' height='100%'>
<!-- 
            /*Смайлики*/
 -->
         <%@ include file="smiles_add.jsp" %>
</td>
<td width='500' align='CENTER' valign='top'>
<!-- 
            /*Автотеги*/
 -->
         <%@ include file="autotags_add.jsp" %>
<!-- 
            /*текстарий*/
 -->
<p><c:if test='${sessionScope["error"] != null}'>
<textarea class='mnuforumSm' rows='20' id='ed1' name='A2' cols='55'><c:out value='${sessionScope["body"]}'/></textarea>
</c:if>
<c:if test='${sessionScope["error"] == null}'>
<textarea class='mnuforumSm' rows='20' id='ed1' name='A2' cols='55'></textarea>
</c:if>
</p>
<!-- 
            /*Кнопки*/
 -->
<table>
<tr>
<td>
 <fmt:message key="mess13" var="mess"/>
 <c:set var="onClick" scope="page" value='send_submit("write");'/>
 <c:set var="name" scope="page" value='B1'/>
 <c:set var="numb" scope="page" value='1'/>
 <%@ include file="fd_button.jsp" %>
</td>
<td>
 <fmt:message key="mess63" var="mess"/>
 <c:set var="onClick" scope="page" value='send_submit("view");'/>
 <c:set var="name" scope="page" value='B3'/>
 <c:set var="numb" scope="page" value='1'/>
 <%@ include file="fd_button.jsp" %>
</td>
</tr>
</table>
<!-- 
            /*Прередаем нужные пераметры...*/
 -->
 <%@ include file="fd_form_add.jsp" %>
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
