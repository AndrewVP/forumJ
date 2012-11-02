<table>
	<tr>
		<td id="td1">
			<?/*Первая строчка Мальчики*/?>
			<a href="#" onclick="smile(':)'); return false" rel="nofollow">
				<img border="0" src="smiles/smile_.gif">
			</a>
			<a href="#" onclick="smile(':('); return false" rel="nofollow">
				<img border="0" src="smiles/sad.gif">
			</a>
			<a href="#" onclick="smile(';)'); return false" rel="nofollow">
				<img border="0" src="smiles/wink3.gif">
			</a>
		</td>
	</tr>
   <?/*Вторая строчка Девочки*/?>
   <tr>
      <td id='td2'>
         <a href='#' onclick="smile(':g)'); return false" rel='nofollow'><img border='0' src='smiles/girl_smile.gif'></a> 
         <a href='#' onclick="smile(':g('); return false" rel='nofollow'><img border='0' src='smiles/girl_sad.gif'></a> 
      </td>
   </tr>
      <?/*Третья строчка Остальное*/?>
   <tr>
      <td id='td3'></td>
   </tr>
   <tr>
      <td align=center>
	      <?/* Кнопка "Показать все"*/?>
         <?echo(fd_button($_mess11,"viewsml();","btn1", "1", "", "", "", ""))?>
         <br><br><br>
<?/* Реклама колобков Отключена - глючит
         <a href="http://www.kolobok.wrg.ru" target="_blank" rel="nofollow">
            <img src="http://kolobok.wrg.ru/button/kolobok_blight.gif" width="88" height="31" border="0" alt="Авторские смайлы стиля КОЛОБОК">
         </a>
*/?>
      </td>
   </tr>
</table>
