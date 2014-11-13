<%@page import="org.forumj.common.FJUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:setBundle basename='nls.messages' />
<c:if test='${fn:length(usersList) gt 0}'>
	<div class='mnuprof' align='CENTER'>
		<b><fmt:message key="mess48" /></b>
	</div>
	<table class='control'>
		<tr class='heads'>
			<th class='internal'><span class='mnuprof'>№</span></th>
			<th class='internal'><span class='mnuprof'><fmt:message key="mess44" /></span></th>
			<th class='internal'><span class='mnuprof'><fmt:message key="mess45" /></span></th>
			<th class='internal'><span class='mnuprof'><fmt:message key="mess46" /></span></th>
			<th class='internal'><span class='mnuprof'><fmt:message key="mess53" /></span></th>
			<th class='internal'><span class='mnuprof'><fmt:message key="mess47" /></span></th>
		</tr>
		<c:forEach var="user" items="${usersList}" varStatus="userIndex">
			<tr>
				<%// № %>
				<td class='internal'><span class='mnuprof'>${userIndex.index}</span></td>
				<%// Ignored %>
				<td class='internal'><span class='mnuprof'><c:out value="${user.id}"/></span></td>
				<%// End %>
				<td class='internal'><span class='mnuprof'><c:out value="${user.nick}"/></span></td>
				<%// Begin %>
				<td class='internal'><span class='mnuprof'><c:out value="${user.pass}"/></span></td>
				<%// Is threads ignored? %>
				<% // Form for change %>
				<td class='internal'><span class='mnuprof'><c:out value="${user.ban}"/></span></td>
				<td class='internal'></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
