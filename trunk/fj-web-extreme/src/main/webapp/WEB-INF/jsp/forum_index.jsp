<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<div id='interface'>
			<table class=control>
				<tr>
					<td class=leftTop></td>
					<td class=top colspan=3></td>
					<td class=rightTop></td>
				</tr>
				<tr class=heads>
					<td class=left></td>
					<td class=bg2 align=left><span class=mnuforum>Поточний
							інтерфейс:</span><span class=nik>Все вместе, кроме корзины</span></td>
					<td class=bg2 align=right><form method='post' name='view_form'
							action='slctview' class=frmsmall>
							<span class=mnuforum>Переключити інтерфейс на:</span><select
								class='mnuforumSm' size='1pt' name='VIEW'><option
									selected class=mnuprof value='1'>Форум</option>
								<option class=mnuprof value='2'>Избранное</option>
								<option class=mnuprof value='3'>Все вместе, кроме
									корзины</option>
								<option class=mnuprof value='4'>Все вместе</option>
								<option class=mnuprof value='5'>Корзина</option>
								<option class=mnuprof value='25'>Технический</option>
								<option class=mnuprof value='26'>Прочитать</option>
								<option class=mnuprof value='40'>Баннеры</option>
								<option class=mnuprof value='41'>ДР</option>
								<option class=mnuprof value='44'>Вопросы</option></select>
						</form></td>
					<td class=bg2 align=right><table id='view_ok_table'
							class='bttn1' onclick='document.view_form.submit();'>
							<tr>
								<td class='bttn1LeftTop'></td>
								<td class='bttn1Top'></td>
								<td class='bttn1RightTop'></td>
							</tr>
							<tr>
								<td class='bttn1Left'></td>
								<td class='bttn1Bg'>OK</td>
								<td class='bttn1Right'></td>
							</tr>
							<tr>
								<td class='bttn1LeftBtm'></td>
								<td class='bttn1Btm'></td>
								<td class='bttn1RightBtm'></td>
							</tr>
						</table></td>
					<td class=right></td>
				</tr>
				<tr>
					<td class=leftBtm></td>
					<td class=btm colspan=3></td>
					<td class=rightBtm></td>
				</tr>
			</table>
		</div>
		<div>
			<table width='100%'>
				<tr>
					<td colspan='3'><p>
							<font face='Arial' color='red' size='3'><span
								style='text-decoration: none'><b>А ТИ видалив флуд зі
										свого форуму?</b></span></font>
						</p></td>
					<td style='text-align: right;'><span class=posthead>Сторінка
							сформована за<br> 0.167 сек
					</span></td>
				</tr>
				<tr>
					<td style='padding: 2px'><font class='page'><b>Сторінка:&nbsp;</b></font><font
						class='pagecurrent'><b>1</b></font><a class='pageLink'
						href='index?page=2'>2</a><a class='pageLink' href='index?page=3'>3</a><a
						class='pageLink' href='index?page=4'>4</a><a class='pageLink'
						href='index?page=5'>5</a><font class='page'
						style='margin-left: 5px;'><b>Загалом
								сторінок:&nbsp;1456</b></font></td>
					<td align='right'><form name='str' method='get' class=frmsmall
							action='index'>
							<font class=page style='margin-right: 4px;'><b>Перейти
									до сторінки:</b></font><input class='mnuforumSm' style='padding: 2px'
								type="text" size='5' name='page'>
						</form></td>
					<td><table id='page_ok_table' class='bttn1'
							onclick='document.str.submit();'>
							<tr>
								<td class='bttn1LeftTop'></td>
								<td class='bttn1Top'></td>
								<td class='bttn1RightTop'></td>
							</tr>
							<tr>
								<td class='bttn1Left'></td>
								<td class='bttn1Bg'>OK</td>
								<td class='bttn1Right'></td>
							</tr>
							<tr>
								<td class='bttn1LeftBtm'></td>
								<td class='bttn1Btm'></td>
								<td class='bttn1RightBtm'></td>
							</tr>
						</table></td>
					<td style='text-align: right;'><span class=posthead>Нових
							тем:&nbsp;</span><span class=posthead id='indicatorb' style='color: red'>&nbsp;</span><br />
						<span class=posthead>Нових повідомлень:&nbsp;</span><span
						class=posthead id='indicatort' style='color: red'>&nbsp;</span></td>
				</tr>
			</table>
		</div>
		<div>
                  <table class='content'>
							<tr>
								<td class=internal align='left' colspan='3'><span
									class=hdforum2>Тема: </span></td>
								<td class=internal align='center'><span class=hdforum2>Відп.</span></td>
								<td class=internal align='center'><span class=hdforum2>Перегл.</span></td>
								<td class=internal align='center'><span class=hdforum2>Запропонована</span></td>
								<td class=internal align='center'><span class=hdforum2>Останнє</span></td>
								<td class=internal align='center'><span class=hdforum2>Тека</span></td>
								<td class=internal align='center'><input type='checkbox'
									id='main_ch' onclick='m_chek()'></td>
								<td class=internal></td>
							</tr>
							<tr class=matras>
								<td width='10' align='center' style='padding: 0px 5px 0px 5px'><img
									border='0' src='skin/standart/picts/closed.png'></td>
								<td width='1'></td>
								<td><p>
										<font class=trforum><b>Прикріплена. </b><a
											href='tema?id=44277'>Если у вас не получается
												зарегистрироваться</a></font><br /> <font face='Verdana' size='1pt'><a
											href='pin?id=44277&pin=0'>відкріпити</a>&nbsp;<a
											href='pin?id=44277&pin=3'>дн</a>&nbsp;<a
											href='pin?id=44277&pin=5'>об'ява</a>&nbsp;<a
											href='delone?id=44277&usr=0&page=1'>видалити</a>&nbsp;</font><font
											face='Verdana' size='1pt'><a
											href='close?id=44277&close=0&page=1'>відкрити</a></font>
									</p></td>
								<td width='20' align='center' valign='middle'><span
									class='mnuforum' style='color: purple'>0</span><span
									id='posts44277' class='mnuforum' style='color: red'>&nbsp</span></td>
								<td width='80' align='center' valign='middle'><div
										class='mnuforum'>
										<font size='1pt' color='green'>19</font><br> <font
											size='1pt' color='purple'>33</font>
									</div></td>
								<td width='120' align='center' valign='middle'><div
										class='trforum'>
										<font size='1pt'>Дилетант</font>
									</div></td>
								<td width='120' align=center><div class='mnuforum'>
										<font size='1pt'>Дилетант</font>
									</div>
									<div class='mnuforum'>
										<a href='tema?id=44277&end=1#end' rel='nofollow'><font
											size='1pt'>05.11.12 11:03</font></a>
									</div></td>
								<td align='center' valign='middle'><div class='mnuforum'>
										<font size='1pt'>Техническая</font>
									</div></td>
								<td align='center' valign='middle'><input type='checkbox'
									id='ch0' name='0' value='44277'></td>
								<td style='padding: 0px 5px 0px 5px' align='right'><a
									href='delone?id=44277&usr=3&page=0'><img border='0'
										src='picts/del1.gif'></a></td>
							</tr>
							<tr class=trees>
								<td width='10' align='center' style='padding: 0px 5px 0px 5px'><img
									border='0' src='smiles/icon4.gif'></td>
								<td width='1'></td>
								<td><p>
										<font class=trforum><b>Об'ява. </b><a
											href='tema?id=44234'>Ну давайте попробуем в очередной раз</a></font><br />
										<font face='Verdana' size='1pt'><a
											href='pin?id=44234&pin=0'>відкріпити</a>&nbsp;<a
											href='pin?id=44234&pin=10'>прикріпити</a>&nbsp;<a
											href='pin?id=44234&pin=3'>дн</a>&nbsp;<a
											href='delone?id=44234&usr=0&page=1'>видалити</a>&nbsp;</font><font
											face='Verdana' size='1pt'><a
											href='close?id=44234&close=1&page=1'>закрити</a></font>
									</p></td>
								<td width='20' align='center' valign='middle'><span
									class='mnuforum' style='color: purple'>22</span><span
									id='posts44234' class='mnuforum' style='color: red'>&nbsp</span></td>
								<td width='80' align='center' valign='middle'><div
										class='mnuforum'>
										<font size='1pt' color='green'>190</font><br> <font
											size='1pt' color='purple'>130</font>
									</div></td>
								<td width='120' align='center' valign='middle'><div
										class='trforum'>
										<font size='1pt'>Дилетант</font>
									</div></td>
								<td width='120' align=center><div class='mnuforum'>
										<font size='1pt'>Козак</font>
									</div>
									<div class='mnuforum'>
										<a href='tema?id=44234&end=1#end' rel='nofollow'><font
											size='1pt'>05.11.12 14:21</font></a>
									</div></td>
								<td align='center' valign='middle'><div class='mnuforum'>
										<font size='1pt'>Техническая</font>
									</div></td>
								<td align='center' valign='middle'><input type='checkbox'
									id='ch1' name='1' value='44234'></td>
								<td style='padding: 0px 5px 0px 5px' align='right'><a
									href='delone?id=44234&usr=3&page=0'><img border='0'
										src='picts/del1.gif'></a></td>
							</tr>
						</table>
		</div>
		<script type='text/javascript'>if (request){var idss = '44277,725475;44234,725517;44212,725625;44221,725624;44179,725623;44235,725619;44270,725618;44220,725616;44265,725613;44264,725600;44222,725586;44206,725553;44276,725528;44279,725527;44236,725515;44267,725502;44274,725495;44278,725489;44275,725473;44268,725467;44224,725465;44272,725427;44273,725425;44271,725395;44239,725322;44269,725318;44259,725314;44219,725284;44263,725257;44231,725255;44257,725233;44233,725227;44266,725210;44262,725161;44232,725129;44260,725128;44230,725122;44261,725114;44258,725108;23051,725098';getIndicatorInfo();}</script>
		<div>
			<table border='0' style='border-collapse: collapse' width='100%'>
				<tr>
					<td colspan='4' style='padding: 2px'><font class=page><b>Сторінка:&nbsp;</b></font><font
						class='pagecurrent'><b>1</b></font><a class='pageLink'
						href='index?page=2'>2</a><a class='pageLink' href='index?page=3'>3</a><a
						class='pageLink' href='index?page=4'>4</a><a class='pageLink'
						href='index?page=5'>5</a><font class='page'
						style='margin-left: 5px;'><b>Загалом
								сторінок:&nbsp;1456</b></font></td>
				</tr>
				<tr>
				</tr>
			</table>
		</div>
