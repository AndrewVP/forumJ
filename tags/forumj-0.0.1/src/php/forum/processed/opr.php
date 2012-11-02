<?
/*Новый опрос*/
session_start();
/*Предотвращаем кеширование*/
include("cache.php");
include("setup.php");
   include("query.php");
/*Собираем статистику*/
include("form_add.php");
include("button.php");
$action=14;
include("stat.php");
mysql_close($conn);
/*Какой язык интерфейса? по умолчанию - украинский :)*/
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
   	/*Скрипты (добавление вариантов ответа)*/
      include('js/jsnode.php');
      /*Скрипты (submit поста)*/
	   include('js/quest_submit.php');
      ?>
   	<title>
   		<?echo($_mess3);?>
   	</title>
   </head>
	<?/*Цвет фона страницы*/?>
   <body bgcolor=#EFEFEF>
		<?/*Главная таблица*/?>
   	<table border='0' style='border-collapse: collapse' width='100%'>
			<?
			/*Таблица с лого и верхним баннером*/
   		include("logo.php");
			/*Главное "меню"*/
   		include("menu.php");
			/*Форма нового опроса*/
			?>
   		<tr>
   			<td>
   				<table>
   					<tr>
   						<td>
                        <form method="POST" name="post" action="quest.php">
                        	<table>
                        		<tr>
                        			<td>
                        				<table>
                        					<tr>
                        						<td>
									   					<?echo($_mess59.":&nbsp");?>
									   				</td>
									   				<td>
									   					<input class='mnuforumSm' type="text" name="T" size="120" maxlength='120'>
									   				</td>
				                        	</tr>
            				            	<tr>
                        						<td>
									   					<?echo($_mess124.":&nbsp");?>
									   				</td>
									   				<td>
									   					<input class='mnuforumSm' type="text" name="Q" size="120" maxlength='120'>
			                        			</td>
         			               		</tr>
         			               	</table>
         			               </td>
         			            </tr>
                        		<tr>
                        			<td>
	                        			<table id=tbl_node>
   			                     		<tr>
            			            			<td>
                     			   				1. <input class='mnuforumSm' type="text" id="P1" name="P1" size="100">
                        						</td>
                                 		</tr>
                                 		<tr>
                                 			<td>
                                 				2. <input class='mnuforumSm' type="text" id="P2" name="P2" size="100">
                                 			</td>
                                 		</tr>
                                 		<tr>
                                 			<td>
                                 				3. <input class='mnuforumSm' type="text" id="P3" name="P3" size="100">
                                 			</td>
                                 		</tr>
                                 	</table>
                                 </td>
                              </tr>
                              <tr>
         	            			<td>
         		                  	<?echo(fd_button($_mess126,"add_node();","btn_add", "1", "", "", "", ""))?>
               	      				<input type="hidden" id="kol" name="kol" value="3">
                  	   			</td>
                     			</tr>
                     			<tr>
                     				<td>
                     					<input type="checkbox" name="US" checked>
         						   		<?echo($_mess125);?>
                     				</td>
                     			</tr>
                        		<tr>
         								<?/*Смайлики заголовок*/?>
            							<td width='100%'>
            								<table width='100%'>
            									<tr>
            										<td align='center'>
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
            											         <?echo(fd_button($_mess13,"quest_submit(\"write\");","B1", "1", "", "", "", ""))?>
                    											</td>
                    											<td>
            											         <?echo(fd_button($_mess63,"quest_submit(\"view\");","B3", "1", "", "", "", ""))?>
                    											</td>
                    										</tr>
                    									</table>
                                             <?/*Прередаем нужные пераметры...*/?>
                    									<?echo(fd_form_add());?>
            									   </td>
            									</tr>
            								</table>
               						</td>
               					</tr>
               				</table>
               			</form>
               		</td>
               	</tr>
               </table>
            </td>
         </tr>
			<?/*Форма закончилась*/
			/*Главное "меню"*/
   		include("menu.php");
			/*Баннер внизу, счетчики и копирайт.*/
   		include("end.php");
   		?>
   </body>
</html>