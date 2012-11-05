<%@page import="org.forumj.common.FJUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:setBundle basename='nls.messages' />
<c:if test='${fn:length(ignorList) eq 0}'>
	<span class='mnuprof'><fmt:message key="mess25" /></span>
</c:if>
<c:if test='${fn:length(ignorList) gt 0}'>
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
		<c:set var="ignorIndex" value="0" />
		<c:forEach var="ignor" items="${ignorList}">
			<c:set var="ignorIndex" value="${ignorIndex + 1}" />
			<tr>
				<%// № %>
				<td class='internal'><span class='mnuprof'>${ignorIndex}</span></td>
				<%// Ignored %>
				<td class='internal'><span class='mnuprof'><c:out value="${ignor.user.nick}"/></span></td>
				<%// Begin %>
				<td class='internal'><span class='mnuprof'><c:out value="${ignor.start}"/></span></td>
				<%// End %>
				<td class='internal'><span class='mnuprof'><c:out value="${ignor.end}"/></span></td>
				<%// Is threads ignored? %>
				<c:if test='${ignor.type == 0}'><td class='internal' align='CENTER'><span class='mnuprof'><fmt:message key="mess27" /></span></td></c:if>
				<c:if test='${ignor.type != 0}'><td class='internal' align='CENTER'><span class='mnuprof'><fmt:message key="mess26" /></span></td></c:if>
				<% // Form for change %>
				<td class='internal'>
					<form method='post' action='<%=FJUrl.UPDATE_IGNORING%>' class=frmsmall>
                  <% // Day %>
						<select size='1' name='D'>
							<option class='mnuprof' selected value='1'>1</option>
							<c:forEach var="day" begin="2" end="31">
								<option class='mnuprof' value='${day}'>${day}</option>
							</c:forEach>
						</select>&nbsp;
            <% // Mounth %>
						<select size='1' name='MTH'>
							<option class='mnuprof' selected value='1'><fmt:message key="mess32" /></option>
							<option class='mnuprof' value='2'><fmt:message key="mess33" /></option>
							<option class='mnuprof' value='3'><fmt:message key="mess34" /></option>
							<option class='mnuprof' value='4'><fmt:message key="mess35" /></option>
							<option class='mnuprof' value='5'><fmt:message key="mess36" /></option>
							<option class='mnuprof' value='6'><fmt:message key="mess37" /></option>
							<option class='mnuprof' value='7'><fmt:message key="mess38" /></option>
							<option class='mnuprof' value='8'><fmt:message key="mess39" /></option>
							<option class='mnuprof' value='9'><fmt:message key="mess40" /></option>
							<option class='mnuprof' value='10'><fmt:message key="mess41" /></option>
							<option class='mnuprof' value='11'><fmt:message key="mess42" /></option>
							<option class='mnuprof' value='12'><fmt:message key="mess43" /></option>
						</select>&nbsp;
            <% // Year %>
						<select size='1' name='Y'>
							<option class='mnuprof' selected value='2012'>2012</option>
							<c:forEach var="year" begin="2013" end="2100">
								<option class='mnuprof' value='${year}'>${year}</option>
							</c:forEach>
						</select>&nbsp;
            <% // Hour %>
						<select size='1' name='H'>
							<option class='mnuprof' selected value='0'>0</option>
							<c:forEach var="hour" begin="1" end="24">
								<option class='mnuprof' value='${hour}'>${hour}</option>
							</c:forEach>
						</select>&nbsp;
            <% // Minute %>
						<select size='1' name='M'>
							<option class='mnuprof' selected value='0'>0</option>
							<c:forEach var="minute" begin="1" end="60">
								<option class='mnuprof' value='${minute}'>${minute}</option>
							</c:forEach>
						</select>&nbsp;
            <% // Submit button %>
						<input class='mnuprof' type="submit" value="<fmt:message key="mess49"/>" name="B1"><br>
            <% // Is ignore threads %>
						<input type='checkbox' name='C1' value='ON'>&nbsp;<span class='mnuprof'><fmt:message key="mess52" /></span>
						<%// id %>
						<input type='hidden' name="IDZ" value="${ignor.id}">
						<% // Current user %>
						<%@ include file="../fd_form_add.jsp"%>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</c:if>
