<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename='nls.messages'/>
<table id='<c:out value="${name}"/>_table' class='bttn<c:out value="${numb}"/>' onclick='<c:out value="${onClick}"/>'>
<tr>
<td class='bttn<c:out value="${numb}"/>LeftTop'></td>
<td class='bttn<c:out value="${numb}"/>Top'></td>
<td class='bttn<c:out value="${numb}"/>RightTop'></td>
</tr>
<tr>
<td class='bttn<c:out value="${numb}"/>Left'></td>
<td class='bttn<c:out value="${numb}"/>Bg'><c:out value="${mess}"/></td>
<td class='bttn<c:out value="${numb}"/>Right'></td>
</tr>
<tr>
<td class='bttn<c:out value="${numb}"/>LeftBtm'></td>
<td class='bttn<c:out value="${numb}"/>Btm'></td>
<td class='bttn<c:out value="${numb}"/>RightBtm'></td>
</tr>
</table>
