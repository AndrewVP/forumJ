<%@ page import="org.forumj.network.web.FJUrl" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Diletant</title>

    <!-- Bootstrap -->
    <link href="/forum/00/css/bootstrap.min.css" rel="stylesheet">
    <link href="/forum/00/css/bootstrap-theme.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="/forum/00/js/html5shiv.min.js"></script>
    <script src="/forum/00/js/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins)
    <script src="../js/jquery.min.js"></script>
    -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/forum/00/js/jquery-3.2.1.min.js"></script>
    <script src="/forum/00/js/bootstrap.min.js"></script>
    <style>
        .affix {
            top: 0;
            width: calc(100% - 30px);
        }
        .affix + article {
            padding-top: 62px;
        }
    </style>
</head>
<body>

<div class="container-fluid">
    <img src="${pageContext.request.contextPath}/<%=FJUrl.STATIC%>/banner/narodny.gif">
    <nav class="navbar navbar-default" data-spy="affix" data-offset-top="130">
        <div class="navbar-header">
            <img class="navbar-brand" src="/forum/00/images/diletant.png">
        </div>
        <div class="container">
            <div class="container">
                <ul class="nav navbar-nav">
                    <li><a href="#">Нова тема</a></li>
                    <li><a href="#">Нове опитування</a></li>
                    <li><a href="#">Мій профіль</a></li>
                    <li><a href="#">Приватне листування</a></li>
                    <li><a href="#">Фотоальбом</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>
<div class="container-fluid">
    <div class="row">
        <table class="table table-condensed table-striped">
            <thead>
            <tr>
                <th colspan='2'>Тема</th>
                <th>Відп.</th>
                <th>Перегл.</th>
                <th>Запропонована</th>
                <th>Останнє</th>
                <th>Тека</th>
                <th><input type='checkbox' id='main_ch' onclick='m_chek()'></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${threads}" var="thread">
                <tr>
                    <td><img src='../images/icon1.gif'></td>
                    <td><div><a href='tema?id=${thread.id}'>${thread.head}</a></div>
                    <td class="small">${thread.postsAmount}</td>
                    <td><div class="small">${thread.snid}</div><div class="small">${thread.snall}</div></td>
                    <td><div>${thread.nick}</div></td>
                    <td><div>${thread.lastPostNick}</div><div class="small"><a href='tema?id=${thread.id}&end=1#end' rel='nofollow'>${thread.lastPostTime}</a></div></td>
                    <td><div>${thread.folder}</div></td>
                    <td><input type='checkbox' id='ch0' name='0' value='${thread.id}'></td>
                    <td><a href='delone?id=${thread.id}&usr=${user.id}&page=0'><img src='../images/del1.gif'></a></td>
                </tr>
            </c:forEach>
            <!--
    <tr>
            <div class="super-small"><a href='pin?id=52174&pin=10'>прикріпити</a>&nbsp;<a href='pin?id=52174&pin=3'>дн</a>&nbsp;<a href='pin?id=52174&;pin=5'>об'ява</a>&nbsp;<a href='delone?id=52174&usr=0&page=1'>видалити</a>&nbsp;<a href='close?id=52174&close=1&page=1'>закрити</a></div></td>
            </tr>
            <tr>
                <td><img src='../images/icon1.gif'></td>
                <td><div><a href='tema?id=52184'>Война войной, а еда по расписанию.Война войной, а еда по расписанию.Война войной, а еда по расписанию.Война войной, а еда по расписанию.Война войной, а еда по расписанию.Война войной, а еда по расписанию.Война войной, а еда по расписанию.</a></div>
                    <div class="super-small">Стор.:&nbsp<a href='tema?page=1&id=52184'>1</a>,&nbsp;<a href='tema?page=2&id=52184'>2</a>,&nbsp;<a href='tema?page=3&id=52184'>3</a>&nbsp;<a href='pin?id=52174&pin=10'>прикріпити</a>&nbsp;<a href='pin?id=52174&pin=3'>дн</a>&nbsp;<a href='pin?id=52174&;pin=5'>об'ява</a>&nbsp;<a href='delone?id=52174&usr=0&page=1'>видалити</a>&nbsp;<a href='close?id=52174&close=1&page=1'>закрити</a></div></td>
                <td class="small">101</td>
                <td><div class="small">445</div><div class="small">884</div></td>
                <td><div>Сель Ави</div></td>
                <td><div>Вільха</div><div class="small"><a href='tema?id=52184&end=1#end' rel='nofollow'>22.05.17 02:29</a></div></td>
                <td><div>Форум</div></td>
                <td><input type='checkbox' id='ch1' name='1' value='52184'></td>
                <td><a href='delone?id=52184&usr=3&page=0'><img src='../images/del1.gif'></a></td>
            </tr>
            -->
        </table>
    </div>
</div>
</div>

</body>
</html>
