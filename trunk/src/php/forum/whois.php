 <html>
<head>
<title>ip information</title>
</head>
<body>
<center>
<h4>Информация об ip-адресе</h4>
<form action=whois.php method=post>
<input type=text name=ip size=35>
<input type=submit value='Проверить'>
</form>
</center>

<?php
if ($_POST['ip']!="") {
$sock = fsockopen ("whois.ripe.net",43,$errno,$errstr);
//соединение с сокетом tcp, ожидающим на сервере "whois.ripe.net" на 43 порту.
//Возвращает дескриптор соединения

if (!$sock) {
echo("$errno($errstr)");
return;
}
else {
fputs ($sock, $_POST['ip']."rn");
//записываем строку из переменной $ip в дескриптор сокета

while (!feof($sock)) {
echo (str_replace(":",": ",fgets ($sock,128))."<br>");
//осуществляем чтение из дескриптора сокета
}
}
fclose ($sock);
//закрытие соединения
}
?>

</body>
</html> 