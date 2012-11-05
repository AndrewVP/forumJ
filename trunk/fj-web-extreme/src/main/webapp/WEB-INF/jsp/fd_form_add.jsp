<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename='nls.messages'/>
<input type='hidden' name='comand' />
<input type=hidden name='IDU' value='<c:out value='${sessionScope["user"].id}'/>'>
<input type=hidden name='AUT' value='<c:out value='${sessionScope["user"].nick}'/>'>
<c:if test='${sessionScope["user"].pass2 != null}'>
<input type=hidden name='PS2' value='<c:out value='${sessionScope["user"].pass2}'/>'>
</c:if>
<c:if test='${sessionScope["user"].pass2 == null}'>
<input type=hidden name='PS1' value='<c:out value='${sessionScope["user"].pass}'/>'>
</c:if>