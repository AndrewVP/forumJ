<%@page import="org.forumj.common.Component"%>
<%@page import="org.forumj.common.Command"%>
<%@page import="org.forumj.common.FJRequestParameter"%>
<%@page import="org.forumj.common.FJUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv='content-type' content='text/html; charset=UTF-8'>
<link rel="stylesheet" href="css/reset.css" type="text/css" />
<link rel="stylesheet" href="css/style.css" type="text/css" />
<script type="text/javascript">
var layout="";
var logo="";
var topMenu="";
var content="";
var bottomMenu="";
var usersOnline="";
var footer="";
var currentComponent=<%=Component.DEFAUL_COMPONENT.getId()%>;;
var mainUrl="<%=request.getContextPath()%>/<%=FJUrl.MAIN%>";
var COMMAND_PARAMETER="<%=FJRequestParameter.COMMAND%>";
var COMPONENT_PARAMETER="<%=FJRequestParameter.COMPONENT%>";
var GET_LOGO_COMMAND="<%=Command.GET_LOGO.getCommand()%>";
var GET_MENU_COMMAND="<%=Command.GET_MENU.getCommand()%>";
var LOGOUT_COMMAND="<%=Command.LOGOUT.getCommand()%>";
var GET_LOGIN_COMMAND="<%=Command.GET_LOGIN.getCommand()%>";
var FORUM_INDEX_COMMAND="<%=Command.FORUM_INDEX.getCommand()%>";
var FORUM_THREAD_COMMAND="<%=Command.FORUM_THREAD.getCommand()%>";
var FORUM_INDEX_COMPONENT=<%=Component.FORUM_INDEX.getId()%>;
var FORUM_THREAD_COMPONENT=<%=Component.FORUM_THREAD.getId()%>;
</script>
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jsmain_chek.js"></script>
<script type="text/javascript" src="js/component_logo.js"></script>
<script type="text/javascript" src="js/component_menu.js"></script>
<script type="text/javascript" src="js/component_forum_index.js"></script>
<script type="text/javascript" src="js/component_forum_thread.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/indicator.js"></script>
<script type="text/javascript" src="js/jsview_ok.js"></script>
<link rel="icon" href="/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<title>Форум дилетантів</title>
</head>

<body class='mainBodyBG'>
<div id='layout'>
	<div id='logo'>
	</div>
	<div id='topMenu'>
	</div>
	<div id='content'>
	</div>
	<div id='bottomMenu'>
	</div>
	<div id='usersOnline'>
	</div>
	<div id='footer'>
	</div>
</div>
</body>
</html>