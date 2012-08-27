<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename='nls.messages'/>
<tr>
<td>
<table class='control'>
<tr>
<td class='leftTop'></td>
<td class='top' colspan='2'></td>
<td class='rightTop'></td>
</tr>
<tr class='heads'>
<td class='left'></td>
<c:if test='${!sessionScope["user"].logined}'>
   <td class=bg align='LEFT'>
      <c:if test='${index == null}'>
         <img src='picts/index.gif' border='0' class='menuImg'><a class='mnuforumSm' href='index.php'><fmt:message key="mess135"/></a>
      </c:if>
   <img src='picts/new_top.gif' border='0' class='menuImg'>
   <a class='mnuforumSm' href='auth.php' rel='nofollow'><fmt:message key="mess4"/>
   </a>
   <img src='picts/new_quest.gif' border='0' class='menuImg'>
   <a class='mnuforumSm' href='auth.php' rel='nofollow'>
         <fmt:message key="mess3"/>
   </a>
   <img src='picts/new_search.gif' border='0' class='menuImg'>
   <a class='mnuforumSm' href='search.php' rel='nofollow'>
         <fmt:message key="mess30"/>
   </a>
   <img src='picts/key_add.gif' border='0' class='menuImg'>
   <a class='mnuforumSm' href='auth.php?id=1' rel='nofollow'>
         <fmt:message key="mess1"/>
   </a>
   <img src='picts/new_user.gif' border='0' class='menuImg'>
   <a class='mnuforumSm' href='reg.php?id=1' rel='nofollow'>
         <fmt:message key="mess2"/>
   </a>
   </td>
   <td class=bg align='right'>
   <a class='mnuforumSm' href='<c:out value="${ua}"/>' rel='nofollow'>Українська</a>•<a class='mnuforumSm' href='<c:out value="${ru}"/>' rel='nofollow'>Русский</a>
   </td>
</c:if>
<c:if test='${sessionScope["user"].logined}'>
   <td class=bg align='LEFT'>
   <img src='picts/nick.gif' border='0' class='menuImg'>
   <span class=nik>
   <c:out value='${sessionScope["user"].nick}'/>
   </span>
      <c:if test='${index == null}'>
      <img src='picts/index.gif' border='0' class='menuImg'><a class='mnuforumSm' href='index.php'><fmt:message key="mess135"/></a>
      </c:if>
   <img src='picts/new_top.gif' border='0' class='menuImg'>
   <a class='mnuforumSm' href='mess.php' rel='nofollow'>
         <fmt:message key="mess4"/>
   </a>
   <img src='picts/new_quest.gif' border='0' class='menuImg'>
   <a class='mnuforumSm' href='opr.php' rel='nofollow'>
         <fmt:message key="mess3"/>
   </a>
   <img src='picts/new_search.gif' border='0' class='menuImg'>
   <a class='mnuforumSm' href='search.php' rel='nofollow'>
         <fmt:message key="mess30"/>
   </a>
   <img src='picts/profile.gif' border='0' class='menuImg'>
   <a class='mnuforumSm' href='control.php' rel='nofollow'>
         <fmt:message key="mess31"/>
   </a>
   <img src='picts/email.gif' border='0' class='menuImg'>
   <a class='mnuforumSm' href='control.php?id=2' rel='nofollow'>
         <fmt:message key="mess23"/>
   </a>
   <img src='picts/key_delete.gif' border='0' class='menuImg'>
   <a class='mnuforumSm' href='<c:out value="${exit}"/>' rel='nofollow'>
         <fmt:message key="mess6"/>
   </a>
   </td>
   <td class=bg align='right'>
   <a class='mnuforumSm' href='<c:out value="${ua}"/>' rel='nofollow'>Українська</a>•<a class='mnuforumSm' href='<c:out value="${ru}"/>' rel='nofollow'>Русский</a>
   </td>
</c:if>
<td class=right></td>
</tr>
<tr>
<td class=leftBtm></td>
<td class=btm colspan=2></td>
<td class=rightBtm></td>
</tr>
</table>
</td>
</tr>     
