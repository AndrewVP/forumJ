<?
   $strt=microtime();
   $startSec=substr($strt,11);
   $startMSec=substr($strt,0,10);
   ob_start();
   session_start();
   // валидируем параметры строки
	include_once 'classes/ua/com/diletant/web/request.php';
	$request = new Request(false);
	$request->SetRedirectOnConstraintFailure(true);
	$request->SetConstraintFailureDefaultRedirectTargetURL("index.php?page=1");
	$pageConstraint = new Constraint(Constraint::$CT_PERMITTEDCHARACTERS, "1234567890");
	$request->AddConstraint("page", Constraint::$VERB_METHOD_GET, $pageConstraint, false, 1);
	$pageConstraint = new Constraint(Constraint::$CT_MINLENGTH, 1);
	$request->AddConstraint("page", Constraint::$VERB_METHOD_GET, $pageConstraint, false, "");
	$pageConstraint = new Constraint(Constraint::$CT_MORETHAN, 0);
	$request->AddConstraint("page", Constraint::$VERB_METHOD_GET, $pageConstraint, false, "");
	$langConstraint = new Constraint(Constraint::$CT_MUSTMATCHREGEXP, "/(ru)|(ua)/");
	$defLang = "ua";
	if (isset($_SESSION['lang'])){
		$defLang = $_SESSION['lang']; 
	}
	$request->AddConstraint("lang", Constraint::$VERB_METHOD_GET, $langConstraint, false, $defLang);
	$request->TestConstraints();	 
   //Предотвращаем кеширование
   include("cache.php"); 
	// Функции   
   include("smiles.php");
   include("cenz.php");
   include("head.php");
   include("button.php");
   include("form_add.php");
   include("query.php");
   // номер страницы
   $pg = $request->GetParameterValue("page");
	// Загружаем локализацию
	include_once 'classes/ua/com/diletant/locale/localestring.php';
	$locale = new LocaleString($request->GetParameterValue("lang"), "strings/lang_", "");
   $_SESSION['where']='index.php?page=$pg&lang=$lang';
   // Авторизован?
   // Соединяемся с БД
   include("setup.php");
	include_once 'classes/ua/com/diletant/sql/connection/mysql/connectionmysql.php';
	include_once 'classes/ua/com/diletant/dao/indexdao.php';
   // Подхватываем куку
   $location="Location: index.php";
   include("cookie.php");
	// нажато "выйти"
   include("exit.php");
   if (isset($_SESSION['idu'])){
      $_idu=$_SESSION['idu'];
   }
   else {
   // Если не авторизован - интерфейс модератора
      $_idu=95;
   }
   $connection = new ConnectionMySQL($host, $user, $pass, $data, 1, "index");
   $dao = new IndexDao($connection, $_idu);	
	// Собираем статистику
   $action=1;
   include("stat.php");
   echo '<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN">';
//   echo '<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"';
//   echo ' "http://www.w3.org/TR/html4/loose.dtd">';
   echo "<html>";
   echo "<head>";
   echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
	// Стили
   include('style.php');
	// Скрипты (флажки)
   include('jsmain_chek.php');
   $m_xb = $dao->getMaxPostId();
   $m_xt = $dao->getMaxThreadId();
   echo "<script language='javascript' type='text/javascript'>";
   echo "// <!-- \n";
   echo "var m_xb=".$m_xb.";";
   echo "var m_xt=".$m_xt.";";
	echo "// -->";
   echo "</script>";
   include('js/indicator.php');
   // Скрипты (submit формы интерфейсов)
   include('jsview_ok.php');
   echo '<link rel="icon" href="/favicon.ico" type="image/x-icon">';
   echo '<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">';
   echo "<title>";
   echo $locale->getString("MSG_MAIN_TITLE");
   echo "</title>";
   echo "</head>";
	// Цвет фона страницы
   echo "<body class='mainBodyBG'>";
   // Снег
//   include("js/snow.php");
	// Главная таблица
   echo "<table border='0' id=t1 style='border-collapse: collapse' width='100%'>";
   echo "<tr><td>";
   echo "<table border='0' style='border-collapse: collapse' width='100%'>";
	// Таблица с лого и верхним баннером
   include("logo.php");
	// соединяемся и определяем кол-во страниц
   $nfirstpost=($pg-1)*$_SESSION['pp'];
   $limit=$pg*$_SESSION['pp'];
	// Интерфейс по умолчанию
   if (!isset($_SESSION['view'])) $_SESSION['view']=$_SESSION['def_view'];
//   if (!isset($_SESSION['idu'])) $_SESSION['view']=$_SESSION['def_view'];
   $threads = $dao->getThreads($_SESSION['view'], $_SESSION['pp'], $nfirstpost, $locale, isset($_SESSION['idu']), $pg, $_SESSION['pt']);
   $count = $dao->getThreadCount();
	// кол-во страниц с заголовками
   $cou_p=ceil($count/$_SESSION['pp'])+1;
   // Проверяем наличие почты
   $str_nmail="";
   if (isset($_SESSION['idu'])) {
   	$mailCount = $dao->getNewMailCount($_SESSION['idu']);
      if ($mailCount > 0) $str_nmail="<a class=hdforum href='control.php?id=2' rel='nofollow'><font color=red>".$locale->getString("mess66")." ".$mailCount." ".$locale->getString("mess67")."</font></a>";
   }
   // Таблица главных ссылок
   echo "<tr>";
   echo "<td width='100%'>";
   echo "<table id='t2' width='100%'>";
   /*Главное меню*/
   include("menu.php");
   // Интерфейс
   // Имя текущего
   if (!isset($_SESSION['vname'])){
      $_SESSION['vname'] = $dao->getCurrentViewName($_SESSION['view']);
   }
   $views = $dao->getViewsArray($_idu);
   echo "<tr><td>";
?>
   	<table class=control>
         <tr>
            <td class=leftTop></td>
            <td class=top colspan=3></td>
            <td class=rightTop></td>
         </tr>
         <tr class=heads>
            <td class=left></td>
   			<td class=bg2 align=left>
   				<span class=mnuforum>
   					<?echo($locale->getString("mess81"));?>
   				</span>
   				<span class=nik>
   					<?echo($_SESSION['vname']);?>
   				</span>
   			</td>
   			<td class=bg2 align=right>
					<form method='post' name='view_form' action='slctview.php' class=frmsmall>
   					<?/*Выводим интерфейсы*/?>
   					<span class=mnuforum>
   						<?echo($locale->getString("mess80"));?>
   					</span>
   					<select class='mnuforumSm'  size='1' name='VIEW'>
   						<option selected class=mnuprof value='<?echo($views[0]['id']);?>'>
   							<?echo($views[0]['name']);?>
   						</option>
   						<?for ($vw1=1; $vw1< sizeof($views)-1; $vw1++)
   						{?>
      						<option class=mnuprof value='<?echo($views[$vw1]['id']);?>'>
      							<?echo($views[$vw1]['name']);?>
      						</option>
   						<?}?>
   					</select>
	   			</form>
  				</td>
  				<td class=bg2 align=right>
					<?echo(fd_button("OK","document.view_form.submit();","view_ok", "1", "", "", "", ""));?>
   			</td>
            <td class=right></td>
         </tr>
         <tr>
            <td class=leftBtm></td>
            <td class=btm colspan=3></td>
            <td class=rightBtm></td>
         </tr>
   </table>
<?
   echo "</td>";        
   echo "</tr>";
	// Стройка!!!
   echo "<tr>";
   echo "<td width='100%'>";
   echo "<table width='100%'>";
   if (!isset($_SESSION['idu'])){
      $mess=$locale->getString("mess133");
   }
   else {
      $mess=$locale->getString("mess134");
   }
      echo "<tr><td colspan='3'><p><font face='Arial' color='red' size='3'><span style='text-decoration: none'><b>";
      echo $mess;
      echo "</b></span></font></p></td>";
   echo "<td style='text-align: right;'>";    
	// Сторінка сформована :)   
   echo "<span class=posthead>".$locale->getString("mess91")."</span>";
   echo "</td>";
   echo "</tr>";
	// Ссылки на другие страницы (здесь collspan!)
   echo "<tr><td style='padding:2px'>";
   echo "<font class='page'><b>".$locale->getString("mess22")."&nbsp;</b></font>";
   $i_3=1;
   if ($pg>5) $i_3=$pg-5;
   $i_4=$pg+5;
   if ($cou_p-$pg<5) $i_4=$cou_p;
   $i_2=0;
   for ($i_1=$i_3; $i_1<$i_4; $i_1++){
      $i_2=$i_2+1;
      if (($i_1>($pg-5) and $i_1<($pg+5)) or $i_2==10 or $i_1==1 or $i_1==($cou_p-1)){
         if ($i_2==10) $i_2=0;
         if ($i_1==$pg){
            echo "<font class='pagecurrent'><b>".$i_1."</b></font>";
         }
         else {
            echo "<a class='pageLink' href='index.php?page=".$i_1."'>".$i_1."</a>";
         }
      }
   }
   echo "<font class='page' style='margin-left:5px;'><b>".$locale->getString("mess136")."&nbsp;".($cou_p-1)."</b></font>";
   echo "</td>";
   echo "<td align='right'>";
?>   
<form name='str' method='get' class=frmsmall action='index.php'>
	<font class=page style='margin-right:4px;'>
		<b>
			<?echo($locale->getString("mess137"));?>
		</b>
	</font>
	<input class='mnuforumSm' style='padding:2px' type="text" size='5' name='page'>
</form>
</td>
<td>
	<?echo(fd_button("OK","document.str.submit();","page_ok", "1", "", "", "", ""));?>
</td>	
<?
   echo "<td style='text-align: right;'>";    
// Индикатор   
   echo "<span class=posthead>".$locale->getString("mess164").":&nbsp;</span>";
   echo "<span class=posthead id='indicatort' style='color:red'>&nbsp;</span><br />";
   echo "<span class=posthead >".$locale->getString("mess165").":&nbsp;</span>";
   echo "<span class=posthead id='indicatorb' style='color:red'>&nbsp;</span>";
   echo "</td>";
echo "</tr>";
   echo "</table>";
   echo "</td>";
   echo "</tr>";
// Закончили таблицу главных ссылок
// Таблица Заголовков тем
   if (isset($_SESSION['idu'])){
      // Форма выводится только для зарегистрированых
      echo "</table>";        
      echo "<form method='post' name='del_form' action='movetitle.php?page=".$pg."' class=frmsmall>";
      echo "<table border='0' style='border-collapse: collapse' width='100%'>";
   }
   echo "<tr>";
   echo "<td height='400' valign='top'>";
   echo "<table class='content'>";
// Заголовки таблицы
   echo "<tr><td class=internal align='left' colspan='3'><span class=hdforum2>Тема:  </span>".$str_nmail." </td>";
// Ответы
   echo "<td class=internal align='center'><span class=hdforum2>".$locale->getString("MSG_ANSW")."</span>";
   echo "</td>";
// Просмотры
   echo "<td class=internal align='center'><span class=hdforum2>";
   echo $locale->getString("MSG_VIEWS")."</span></td>";
   echo "<td class=internal align='center'><span class=hdforum2>".$locale->getString("MSG_AUTH")."</span></td>";
   echo "<td class=internal align='center'><span class=hdforum2>".$locale->getString("MSG_LAST")."</span></td>";
// Папка
   echo "<td class=internal align='center'><span class=hdforum2>".$locale->getString("mess82")."</span></td>";
   // Флажок (только для авторизованых)
   if (isset($_SESSION['idu'])) {
      echo "<td class=internal align='center'>";
      echo "<input type='checkbox' id='main_ch' onclick='m_chek()'>";
      echo "</td>";
      echo "<td class=internal></td>";
   }
      echo "</tr>";
// Определяем кол-во строк таблицы
   $i2=$pg*$_SESSION['pp'];
   if ($i2>$count) {
   	$i2=$count-($pg-1)*$_SESSION['pp'];
   }else{
   	$i2=$_SESSION['pp'];
   }
// Выводим строки
	foreach ($threads as $thread) {
		echo $thread->toString();
	}
// Главные ссылки внизу страницы
   echo "</table>";
	echo "<script language='javascript' type='text/javascript'>";
   echo "// <!-- \n";
	echo "if (request){";
   echo "var idss = '".substr($dao->getIndctrIds(), 1)."';";
	echo "getIndicatorInfo();";
   echo "}";
	echo "// -->";
	echo "</script>";
   echo "</td>";
   echo "</tr>";
   echo "<tr>";
   echo "<td width='100%'>";
   echo "<table border='0' style='border-collapse: collapse' width='100%'>";
   echo "<tr><td colspan='4' style='padding:2px'>";
   echo "<font class=page><b>".$locale->getString("mess22")."&nbsp;</b></font>";
   $i_3=1;
   if ($pg>5) $i_3=$pg-5;
   $i_4=$pg+5;
   if ($cou_p-$pg<5) $i_4=$cou_p;
   $i_2=0;
   for ($i_1=$i_3; $i_1<$i_4; $i_1++){
      $i_2=$i_2+1;
      if (($i_1>($pg-5) and $i_1<($pg+5)) or $i_2==10 or $i_1==1 or $i_1==($cou_p-1)){
         if ($i_2==10) $i_2=0;
         if ($i_1==$pg){
            echo "<font class='pagecurrent'><b>".$i_1."</b></font>";
         }
         else {
            echo "<a class='pageLink' href='index.php?page=".$i_1."'>".$i_1."</a>";
         }
      }
   }
   echo "<font class='page' style='margin-left:5px;'><b>".$locale->getString("mess136")."&nbsp;".($cou_p-1)."</b></font>";
   echo "</td>";
   echo "</tr>";
   // Сервис интерфейса
   if (isset($_SESSION['idu'])) {
      // Выбираем доступные папки
      $arrFolders = $dao->getFoldersArray($_idu);
      echo "<tr>";
      echo "<table class=control>";        
?>
         <tr>
            <td class=leftTop></td>
            <td class=top colspan=3></td>
            <td class=rightTop></td>
         </tr>
         <tr class=heads>
            <td class=left></td>
<?
      echo "<td class=bg2 align=left>";
      echo "<span class=mnuforum>".$locale->getString("mess81")."</span><span class=nik>".$_SESSION['vname']."</span>";
      echo "</td>";
      echo "<td class=bg2 align=right>";
      // Выводим папки
      echo "<span class=mnuforum>".$locale->getString("mess83")."</span>";
      echo "<select class='mnuforumSm' size='1' name='VIEW'>";
      echo "<option selected value='".$arrFolders[0]['id']."'><span class=mnuprof>".$arrFolders[0]['flname']."</span></option>";
      for ($fl1=1; $fl1< sizeof($arrFolders)-1; $fl1++){
         echo "<option value='".$arrFolders[$fl1]['id']."'><span class=mnuprof>".$arrFolders[$fl1]['flname']."</span></option>";
      }        
      echo "</select>";
   // Прередаем нужные пераметры...
		echo(fd_form_add());
      echo "<input type=hidden name=\"NRW\" id='nrw' value=\"".$i2."\">";
   // Кнопка
      echo "</td>";        
      echo "<td class=bg2 align=right>";
		echo(fd_button("OK","document.del_form.submit();","del_ok", "1", "", "", "", ""));
      echo "</td>";        
?>
            <td class=right></td>
         </tr>
         <tr>
            <td class=leftBtm></td>
            <td class=btm colspan=3></td>
            <td class=rightBtm></td>
         </tr>
<?
      echo "</table>";        
      echo "</tr>";
      echo "</table>";        
      echo "</form>";
      echo "<table border='0' style='border-collapse: collapse' width='100%'>";
   }
   /*Главное меню*/
   include("menu.php");
   echo "</table>";
   echo "</td>";
   echo "</tr>";
// Таблица активных пользователей
// Выбираем Активных юзеров
   $arrUsers = $dao->getUsersArray();
   echo "<tr>";
   echo "<td width=\"100%\">";
   echo "<table width='100%'><tr><td>";
   echo "<font class=mnuforum>";
   echo $locale->getString("MSG_READERS")."<br>";
   echo "</font>";
   echo "<font class=nick>";
   for ($tu1=0 ; $tu1<sizeof($arrUsers)-1; $tu1++){
      echo str_replace(' ', '&nbsp;', $arrUsers[$tu1]['nick']);
      if ($tu1<>sizeof($arrUsers)-2) echo "; ";
   }
   echo "</font>";
   echo "<font class=mnuforum>";
   echo "<br>".$locale->getString("MSG_GUESTS").": ";
   echo "</font>";
   echo "<font class=nick>";
	// Выводим количество гостей
   echo $dao->getGuestCount();
   // Закрываем соединение с БД
   mysql_close($conn);
   echo "</font>";
   echo "</td>";
   echo "</tr>";
   echo "</table>";
   echo "</td>";
   echo "</tr>";
// Баннер внизу, счетчики и копирайт.
   include("end.php");
   echo "</body>";
   echo "</html>";
   $strtmp=ob_get_contents();
   ob_end_clean();
   $end=microtime();
   $pgTime=substr(substr($end,11)-$startSec+substr($end,0,10)-$startMSec, 0, 5);
   $strtmp=str_replace("ъъ_ъ", $pgTime, $strtmp);
   echo $strtmp;
?>