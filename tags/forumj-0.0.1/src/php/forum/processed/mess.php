<?
// Новая тема
   session_start();
   include("query.php");
//Предотвращаем кеширование
   include("cache.php");
   include("form_add.php");
   include("button.php");
   include('setup.php');
// Собираем статистику
   $action=7;
   include("stat.php");
//
   mysql_close($conn);
// Какой язык интерфейса? по умолчанию - украинский :)
   include("lang.php");
?>   
<html>
	<head>
      <meta http-equiv='content-type' content='text/html; charset=windows-1251'>
      <?
      /*Стили*/
      include('style.php');
   	/*Скрипты (смайлики)*/
      if (strpos($_SERVER['HTTP_USER_AGENT'], "MSIE 5.0")){
         include('smile_ie5.php');
      }
      else {
         include('smile_.php');
      }
   	/*Скрипты (автовставка тегов)*/
      include('jstags.php');
      /*Скрипты (submit поста)*/
	   include('js/new_submit.php');
      ?>   
   		<title>
   			<?echo($_mess4);?>
   		</title>
   	</head>
		<?/*Цвет фона страницы*/?>
   	<body bgcolor=#EFEFEF>
			<?/*Главная таблица*/?>
   		<table border='0' style='border-collapse: collapse' width='100%'>
				<?
				/*Таблица с лого и верхним баннером*/
   			include("logo.php");
				// Главные ссылки
				// Главное "меню"
   			include("menu.php");
				// Форма новой ветки
				?>
   			<tr>
   				<td>
   					<table width=100%>
   						<tr>
   							<td>
   								<form name='post' action='new.php' method='POST'>
   									<table width='100%'>
											<?/*Тема*/?>
   										<tr>
   											<td colspan='2' align='left'>
								   				<?echo($_mess4);?>&nbsp;
   												<input class='mnuforumSm' type=text name='NHEAD' size='120' maxlength="120">
   											</td>
   										</tr>
   										<tr>
												<?/*Смайлики заголовок*/?>
   											<td align=center>
            									<p>
            										<?echo($_mess21.":");?>
            									</p>
											   </td>
												<?/*Приглашение*/?>
   											<td align='CENTER'>
   											<p>
           										<?echo($_mess12);?>
   											</p>
   										</td>
   									</tr>
										<?/*Пост*/?>
   									<tr>
   										<td valign='TOP'>
   	   									<?/*Смайлики*/?>
         									<?include("smiles_add.php");?>
										   </td>
   										<td align='CENTER' valign='top'>
												<?/*Автотеги*/
   	   									include("autotags_add.php");
   											/*текстарий*/?>
   											<p>
   												<textarea class='mnuforumSm' rows='30' id='ed1' name='A2' cols='55'></textarea>
   											</p>
                              		<?/*Кнопки*/?>
           									<table>
           										<tr>
           											<td>
   											         <?echo(fd_button($_mess13,"new_submit(\"write\");","B1", "1", "", "", "", ""))?>
           											</td>
           											<td>
   											         <?echo(fd_button($_mess63,"new_submit(\"view\");","B3", "1", "", "", "", ""))?>
           											</td>
           										</tr>
           									</table>
                                    <?/*Прередаем нужные пераметры...*/?>
	        									<?echo(fd_form_add());?>
										   </td>
   									</tr>
   								</table>
   							</form>
   						</td>
   					</tr>
   				</table>
   			</td>
   		</tr>
			<?
			/*Главное "меню"*/
   		include("menu.php");
			// Баннер внизу, счетчики и копирайт.
   		include("end.php");
   		?>
   </body>
</html>

