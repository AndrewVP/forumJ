 <html>
<head>
<title>ip information</title>
</head>
<body>
<center>
<h4>���������� �� ip-������</h4>
<form action=whois.php method=post>
<input type=text name=ip size=35>
<input type=submit value='���������'>
</form>
</center>

<?php
if ($_POST['ip']!="") {
$sock = fsockopen ("whois.ripe.net",43,$errno,$errstr);
//���������� � ������� tcp, ��������� �� ������� "whois.ripe.net" �� 43 �����.
//���������� ���������� ����������

if (!$sock) {
echo("$errno($errstr)");
return;
}
else {
fputs ($sock, $_POST['ip']."rn");
//���������� ������ �� ���������� $ip � ���������� ������

while (!feof($sock)) {
echo (str_replace(":",": ",fgets ($sock,128))."<br>");
//������������ ������ �� ����������� ������
}
}
fclose ($sock);
//�������� ����������
}
?>

</body>
</html> 