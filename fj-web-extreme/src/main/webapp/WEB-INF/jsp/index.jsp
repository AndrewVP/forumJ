<%@ page contentType="text/html; charset=UTF-8" %>
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

<div class="container-fluid" style="height: 2000px; padding-top: 20px;">
    <img src="/forum/00/banner/narodny.gif">
    <nav class="navbar navbar-default" data-spy="affix" data-offset-top="130">
        <div class="container">
            <div class="navbar-header">
                <img class="navbar-brand" src="../images/diletant.png">
            </div>
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

</body>
</html>
