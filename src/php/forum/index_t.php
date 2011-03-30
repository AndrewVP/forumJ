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
	$request->AddConstraint("lang", Constraint::$VERB_METHOD_GET, $langConstraint, false, "ua");
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
   if (isset($_SESSION['idu'])){
      $_idu=$_SESSION['idu'];
   }
   else {
   // Если не авторизован - интерфейс модератора
      $_idu=95;
   }
   // Соединяемся с БД
   include("setup.php");
	include_once 'classes/ua/com/diletant/sql/connection/mysql/connectionmysql.php';
	include_once 'classes/ua/com/diletant/dao/indexdao.php';
   $connection = new ConnectionMySQL($host, $user, $pass, $data, 1, "index");
   $dao = new IndexDao($connection, $_idu);	
   // Подхватываем куку
   $location="Location: index.php";
   include("cookie.php");
// нажато "выйти"
   include("exit.php");
// Собираем статистику
   $action=1;
   include("stat.php");
   echo "<html>";
   echo "<head>";
   echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
// Стили
   include('style.php');
// Скрипты (флажки)
   include('jsmain_chek.php');
/*   $sql_indb="SELECT
                max(id) as mx
             FROM body";
//   $res=fd_query($sql_indb, $conn, "");
   $res = $connection->doQuery($sql_indb);
   $m_xb = mysql_result($res, 0, 'mx');
*/
   $m_xb = $dao->getMaxPostId();
/*   $sql_indt="SELECT
                max(id) as mx
             FROM titles";
   $res = $connection->doQuery($sql_indt);
//   $res=fd_query($sql_indt, $conn, "");
   $m_xt=mysql_result($res, 0, 'mx');
*/   
   $m_xt = $dao->getMaxThreadId();
   echo "<script language='javascript' type='text/javascript'>";
   echo "var m_xb=".$m_xb.";";
   echo "var m_xt=".$m_xt.";";
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
   if (!isset($_SESSION['idu'])) $_SESSION['view']=$_SESSION['def_view'];
/*// Выбираем папки
   $sql_views="SELECT
                  folder
               FROM
                  fdvtranzit
               WHERE
                  (user=".$_idu." OR user=0)
                  AND view=".$_SESSION['view']." 
                  ";
   $rslt_folders=fd_query($sql_views,$conn, "");
   $xRow=0;
   $isForum=0;
   $folders="(";
   while ($row=mysql_fetch_row($rslt_folders)){
   	if ($row[0]==1){
		   $isForum=1;
   	}else{
   		$folders.=" ".$row[0].",";
   	}
   		$xRow+=1;
   }
   if ($xRow==1 and $isForum){
   	есть только форум
   }else{
   	другое
	   $folders=substr($folders, 0, strlen($folders)-1).")";
   }
   выбираем минусы игнора
	$sqlIgnor="
   SELECT
      ignor.ignor
   FROM 
      ignor
   WHERE
      ignor.user=".$_idu."
      AND ignor.type=1
      AND ignor.end > now() 
	";
	$rsltIgnor=fd_query($sqlIgnor,$conn, "");
   if (mysql_num_rows($rsltIgnor)!=0){
   	игнор есть
      $ignored="(";
      while ($row=mysql_fetch_row($rsltIgnor)){
     		$ignored.=" ".$row[0].",";
      }
	   $ignored=substr($ignored, 0, strlen($ignored)-1).")";
   }
   $where="";
   if (isset($ignored)){
   	$where="WHERE titles.auth NOT IN ".$ignored." ";
   }
   if ($isForum){
   	Есть форум
   	if ($xRow==1){
   		Есть только форум
   		Определяем минусы - все перемещенное
   		$sqlMoved="
   		SELECT
   			title
   		FROM
   			fdtranzit
   		WHERE
   			user=".$_idu."
   		";
   		$rsltMoved=fd_query($sqlMoved, $conn, "");
         if (mysql_num_rows($rsltMoved)!=0){
         	перемещения есть
            $moved="(";
            while ($row=mysql_fetch_row($rsltMoved)){
           		$moved.=" ".$row[0].",";
            }
      	   $moved=substr($moved, 0, strlen($moved)-1).")";
         }
         Собираем запросы
         if (isset($moved)){
	         if (isset($ignored)){
	         	$where.="AND titles.id NOT IN ".$moved." ";
	         }else{
	         	$where="WHERE titles.id NOT IN ".$moved." ";
	         }
         }
         $folderName="'Форум' as _flname, ";
         $join="";
   	}else{
   		кроме форума есть что-то еще
   		Находим минусы - перемещенные в другие папки
   		$sqlMoved="
   		SELECT
   			title
   		FROM
   			fdtranzit
   		WHERE
   			user=".$_idu."
   			AND folder NOT IN ".$folders."
   		";
   		$rsltMoved=fd_query($sqlMoved, $conn, "");
         if (mysql_num_rows($rsltMoved)!=0){
         	перемещения есть
            $moved="(";
            while ($row=mysql_fetch_row($rsltMoved)){
           		$moved.=" ".$row[0].",";
            }
      	   $moved=substr($moved, 0, strlen($moved)-1).")";
         }
         Собираем запросы
         if (isset($moved)){
	         if (isset($ignored)){
	         	$where.="AND titles.id NOT IN ".$moved." ";
	         }else{
	         	$where="WHERE titles.id NOT IN ".$moved." ";
	         }
         }
         $folderName="IF (ISNULL(fdfolders.flname), 'Форум', fdfolders.flname) as _flname, ";
// Временная таблица
		$sqlTmpJoinTable="
			CREATE TEMPORARY TABLE fdutranzit LIKE fdtranzit";
		$sqlTmpJoinTableInsert="
			INSERT INTO fdutranzit (title, folder) 
				SELECT 
					fdtranzit.title, 
					fdtranzit.folder 
				FROM 
					fdtranzit 
				WHERE 
					fdtranzit.user=".$_idu.";	
		";
	      $join="
	      LEFT JOIN fdutranzit on titles.id=fdutranzit.title
	      LEFT JOIN fdfolders ON fdutranzit.folder=fdfolders.id
	      ";
   	}
   }else{
   	форума в интерфейсе нет
		Определяем плюсы - все перемещенное в папки
		$sqlMoved="
		SELECT
			title
		FROM
			fdtranzit
		WHERE
			user=".$_idu."
 			AND folder IN ".$folders."
		";
		$rsltMoved=fd_query($sqlMoved, $conn, "");
      if (mysql_num_rows($rsltMoved)!=0){
      	перемещения есть
         $moved="(";
         while ($row=mysql_fetch_row($rsltMoved)){
        		$moved.=" ".$row[0].",";
         }
   	   $moved=substr($moved, 0, strlen($moved)-1).")";
      }
      Собираем запросы
      if (isset($moved)){
         if (isset($ignored)){
         	$where.="AND titles.id IN ".$moved." ";
         }else{
         	$where="WHERE titles.id IN ".$moved." ";
         }
      }
      $folderName="IF (ISNULL(fdfolders.flname), 'Форум', fdfolders.flname) as _flname, ";
// Временная таблица
	$sqlTmpJoinTable="
		CREATE TEMPORARY TABLE fdutranzit LIKE fdtranzit";
	$sqlTmpJoinTableInsert="
		INSERT INTO fdutranzit (title, folder) 
			SELECT 
				fdtranzit.title, 
				fdtranzit.folder 
			FROM 
				fdtranzit 
			WHERE 
				fdtranzit.user=".$_idu.";	
	";
      $join="
      LEFT JOIN fdutranzit on titles.id=fdutranzit.title
      LEFT JOIN fdfolders ON fdutranzit.folder=fdfolders.id
      ";
   }
   $sql_main="
   SELECT
   	titles.id, 
   	titles.dock, 
   	DATE_FORMAT(DATE_ADD(DATE_ADD(titles.lposttime,INTERVAL 0 HOUR), INTERVAL 0 MINUTE), '%d.%m %H:%i') as lposttime_, 
   	titles.type, 
   	titles.npost, 
   	titles.seenid, 
   	titles.seenall, 
   	DATE_FORMAT(titles.reg, '%d.%m %H:%i') as reg_, 
   	titles.head, 
   	titles.lpostuser, 
   	titles.lpostnick,
   	titles.id_last_post,
      ".$folderName."
   	users.nick 
   FROM
		titles force index(id_3)
      LEFT JOIN users ON titles.auth=users.id
      ".$join."
   ".$where."
   ORDER BY
      titles.dock desc,
      titles.lposttime desc 
   LIMIT 
      ".$nfirstpost.", ".$_SESSION['pp']."
   ";
   $sql_count="
   SELECT
      COUNT(id) as kolvo
   FROM
      titles
      ".$where.";
   ";
// Добавляем временную таблицу 
	if (isset($sqlTmpJoinTable)){
	 	$res1=fd_query($sqlTmpJoinTable,$conn, ""); 
		$res1=fd_query($sqlTmpJoinTableInsert,$conn, ""); 
	}     
//   echo $sql_main."<br><br>";     
//   echo $sqlTmpJoinTable."<br><br>";     
//   echo $sql_count;
//   $res1=fd_query($sql_main,$conn, "");
	$res1 = $connection->doQuery($sql_main); 
   $zcount=fd_query($sql_count,$conn, "");
   $count=mysql_result($zcount, 0, 'kolvo');
   if (isset($sql_count2)){
      $zcount2=fd_query($sql_count2,$conn, "");
	   $count=$count + mysql_result($zcount2, 0, 'kolvo');
   }
*/   
   $threads = $dao->getThreads($_SESSION['view'], $_SESSION['pp'], $nfirstpost, $locale, isset($_SESSION['idu']), $pg, $_SESSION['pt']);
   $count = $dao->getThreadCount();
// кол-во страниц с заголовками
   $cou_p=ceil($count/$_SESSION['pp'])+1;
   // Проверяем наличие почты
   if (isset($_SESSION['idu'])) {
      $sql_newmail="
      SELECT
         COUNT(*) as nmail
      FROM
         fdmail
      WHERE
         rcvr=".$_SESSION['idu']." AND
         d_rcv IS NULL
      ";
      $rslt_newmail=fd_query($sql_newmail, $conn, "");
      $str_nmail="";
      if (mysql_result($rslt_newmail, 0, 'nmail')) $str_nmail="<a class=hdforum href='control.php?id=2' rel='nofollow'><font color=red>".$locale->getString("mess66")." ".mysql_result($rslt_newmail, 0, 'nmail')." ".$locale->getString("mess67")."</font></a>";
   }
   else {
      $str_nmail="";
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
      $sql_vname="
      SELECT
         name
      FROM
         fdviews
      WHERE
         id=".$_SESSION['view']."      
      ";   
      $rslt_vname=fd_query($sql_vname, $conn, "");
      $_SESSION['vname']=mysql_result($rslt_vname, 0, 'name');
   }
   // Выбираем доступные интерфейсы
   $sql_views="
   SELECT
      id,
      name
   FROM
      fdviews
   WHERE 
      user=0
      OR user=".$_idu."
   ORDER BY 
      id 
   ";
   $rslt_views=fd_query($sql_views, $conn, "");
   $numr_views=mysql_num_rows($rslt_views);
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
   						<option selected class=mnuprof value='<?echo(mysql_result($rslt_views, 0, 'id'));?>'>
   							<?echo(mysql_result($rslt_views, 0, 'name'));?>
   						</option>
   						<?for ($vw1=1; $vw1< $numr_views; $vw1++)
   						{?>
      						<option class=mnuprof value='<?echo(mysql_result($rslt_views, $vw1, 'id'));?>'>
      							<?echo(mysql_result($rslt_views, $vw1, 'name'));?>
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
/*
		// Прикреплена?
      $str_dock=stripslashes(mysql_result($res1, $i, 'dock'));
// Текст заголовка
      $str_head=stripslashes(mysql_result($res1, $i, 'head'));
// Автор
      $str_nick=stripslashes(mysql_result($res1, $i, 'nick'));
// Время Начала
      $str_reg=mysql_result($res1, $i, 'reg_');
// id Темы
      $str_id=mysql_result($res1, $i, 'id');
// Время последнего поста
      $str_lpt=mysql_result($res1, $i, 'lposttime_');
// id Автора последнего поста
      $str_lpus=mysql_result($res1, $i, 'lpostuser');
// Ник автора последнего поста
      $str_lpn=mysql_result($res1, $i, 'lpostnick');
// Количество постов в ветке
      $str_pcount=mysql_result($res1, $i, 'npost')-1;
// Количество просмотров участников
      $str_snid=mysql_result($res1, $i, 'seenid');
// Id последнего поста в ветке
      $idLastPost=mysql_result($res1, $i, 'id_last_post');
// Если id нулевой, надо проапдейтить.
		if ($idLastPost == 0){
			$query="SELECT 
						MAX(id) as id_post
					FROM
						body 
					WHERE 
						head=".$str_id;
	      $result=fd_query($query, $conn, "");
	      $idLastPost=mysql_result($result, 0, 'id_post'); 
			$query="UPDATE 
						titles 
					SET 
						id_last_post=".$idLastPost."  
					WHERE 
						id=".$str_id;
	      $result=fd_query($query, $conn, ""); 
		}
		$indctrIds.=";".$str_id.",".$idLastPost;
// Количество просмотров полное
      $str_snall=mysql_result($res1, $i, 'seenall');
// Тип ветки
      $str_type=mysql_result($res1, $i, 'type');
// Папка
      $str_folder=mysql_result($res1, $i, '_flname');
      if ($str_dock==5){
        	echo("<tr class=alert>");
      }elseif($str_dock==10){
        	echo("<tr class=sticked>");
      }else{
      
      	if ($disain==1) { 
         	echo("<tr class=trees >");
       	}else {
         	echo("<tr class=matras>");   
       	}
      $disain=$disain*(-1); 
// Картинки
// Пиктограммка опроса
      echo "<td width='10' align='center' style='padding:0px 5px 0px 5px'>";
      if ($str_type==1 or $str_type==2){
         echo "<img border='0' src='smiles/quest.gif'>";
      }
      else{
         if ($str_dock==5){
            echo "<img border='0' src='smiles/icon4.gif'>";
         }
         elseif($str_dock==10) {
            echo "<img border='0' src='picts/f_pinned.gif'>";
         }
         else {
            echo "<img border='0' src='smiles/icon1.gif'>";
         }
      }
      echo "</td>";
      echo ("<td width='1'></td>");
// Тема
      echo("<td><p>");
      $str_head=htmlspecialchars(stripslashes($str_head));
// Добавляем смайлики
      $str_head=fd_head($str_head);
// Опрос? Добавляем "метку"
      if ($str_type==1 or $str_type==2) $str_head="<b>".$locale->getString("mess9")."</b> ".$str_head;
// Подписываем прикрепленные
      switch ($str_dock){
         case 10:
              echo("<font class=trforum><b>".$locale->getString("mess7")." </b><a href='tema.php?id=" . $str_id . "'>".$str_head."</a></font>");
              break;
         case 5:
              echo("<font class=trforum><b>".$locale->getString("mess8")." </b><a href='tema.php?id=" . $str_id . "'>".$str_head."</a></font>");
              break;
         case 3:
              echo("<font class=trforum><b>".$locale->getString("mess163")." </b><a href='tema.php?id=" . $str_id . "'>".$str_head."</a></font>");
              break;
         case 0:
              echo("<font class=trforum><a href='tema.php?id=" . $str_id . "'>".$str_head."</a></font>");
              break;
         }
// Cсылки на страницы в ветке

      if ($str_pcount+1>$_SESSION['pt']) {
         echo ("<br><font size=1>".$locale->getString("mess10").":&nbsp");
         $k1=0;
         $k2=0;
         for ($k=1; $k<=ceil(($str_pcount+1)/$_SESSION['pt']); $k++) {
            $k1=$k1+1;
            if ($k1==10){
               echo ("<a href='tema.php?page=".$k."&id=".$str_id."'>".$k."</a>");
               if ($k<>ceil(($str_pcount+1)/$_SESSION['pt'])) echo (",&nbsp;&nbsp;");
               $k1=0;
               $k2=$k2+1;
            }
            if ($k==1){
               echo ("<a href='tema.php?page=".$k."&id=".$str_id."'>".$k."</a>,&nbsp;&nbsp;");
            }
            if ((ceil(($str_pcount+1)/$_SESSION['pt'])-$k2*10)< 10 and ($k-$k2*10) != 0 and $k!=1){
               echo ("<a href='tema.php?page=".$k."&id=".$str_id."'>".$k."</a>");
               if ($k<>ceil(($str_pcount+1)/$_SESSION['pt'])) echo (",&nbsp;&nbsp;");
            }
            
         }
         echo ("</font>");
      }
      echo("</p></td>");
// Количество постов
      echo("<td width='20' align='center' valign='middle'><span class='mnuforum' style='{color: purple}'>".$str_pcount);
      echo "</span><span id='posts".$str_id."' class='mnuforum' style='{color: red}'>&nbsp</span></td>";
// кол-во просмотров
      echo "<td width='80' align='center' valign='middle'>";
// Количество просмотров участников
      echo('<div class="mnuforum"><font size="1" color="green">'.$str_snid.'</font><br>');
// Количество просмотров всего
      echo('<font size="1" color="purple">'.$str_snall.'</font></div></td>');
// Автор
      echo("<td width='120' align='center' valign='middle'><div class='trforum'><font size='1'>" . htmlspecialchars($str_nick) . "</font></div>");
//      echo " ";
// Время создания
      echo("</td>");
// Автор последнего поста
      echo("<td width='120' align=center><div class='mnuforum'><font size='1'>" . htmlspecialchars($str_lpn))."</font></div>";
// Время последнего поста
      echo('<div class="mnuforum"><a href="tema.php?id='. $str_id .'&end=1#end" rel="nofollow"><font size="1">'.substr($str_lpt, 0, 5)).'&nbsp;'.substr($str_lpt, 6, 5).'</font></a></div>';
      echo("</td>");
      // Папка
      echo "<td align='center' valign='middle'>";
      echo "<div class='mnuforum'><font size='1'>".$str_folder."</font></div>";
      echo("</td>");
      // Флажок (только для зарегистрированых)
      if (isset($_SESSION['idu'])){
         echo "<td align='center' valign='middle'>";
         echo "<input type='checkbox' id='ch".$i."' name='".$i."' value='".$str_id."'>";
         echo "</td>";
         echo "<td style='padding:0px 5px 0px 5px' align='right'>";
         echo "<a href='delone.php?id=".$str_id."&usr=".$_SESSION['idu']."&page=".$pg."'><img border='0' src='picts/del1.gif'></a>";
         echo "</td>";
      }
   }
*/      
// Главные ссылки внизу страницы
   echo "</table>";
	echo "<script language='javascript' type='text/javascript'>";
   echo "if (request){";
   echo "var idss = '".substr($dao->getIndctrIds(), 1)."';";
	echo "getIndicatorInfo();";
   echo "}";
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
      $sql_folders="
      SELECT
         id,
         flname
      FROM
         fdfolders
      WHERE 
         user=0
         OR user=".$_idu."
      ORDER BY 
         id 
      ";
      $rslt_folders=0;
      $rslt_folders=fd_query($sql_folders, $conn, "");
      $numr_folders=mysql_num_rows($rslt_folders);
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
      echo "<option selected value='".mysql_result($rslt_folders, 0, 'id')."'><span class=mnuprof>".mysql_result($rslt_folders, 0, 'flname')."</span></option>";
      for ($fl1=1; $fl1< $numr_folders; $fl1++){
         echo "<option value='".mysql_result($rslt_folders, $fl1, 'id')."'><span class=mnuprof>".mysql_result($rslt_folders, $fl1, 'flname')."</span></option>";
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
// Выбираем количество гостей
//   $lptime=date('Y-m-d H:i:s');
   $query34="" .
   		"SELECT" .
   		"	count(DISTINCT fd_ip) as nick " .
   		"FROM " .
   		"	fd_action " .
   		"WHERE " .
   		"	(fd_user=0) " .
   		"	AND (fd_time > now() - INTERVAL 20 MINUTE) ";
   $res134=fd_query($query34,$conn, "");
// Выбираем Активных юзеров
   $query33="SELECT
              DISTINCT users.nick
           FROM fd_action
           LEFT JOIN users on fd_action.fd_user=users.id
           WHERE (fd_action.fd_user<>0) and (fd_action.fd_time >now() - INTERVAL 20 MINUTE)
           and (fd_action.fd_user<>95) and (users.ban=0)
           ORDER BY users.nick";
   $res133=fd_query($query33,$conn, "");
   $count33=mysql_num_rows($res133);
   echo "<tr>";
   echo "<td width=\"100%\">";
   echo "<table width='100%'><tr><td>";
   echo "<font class=mnuforum>";
   echo $locale->getString("MSG_READERS")."<br>";
   echo "</font>";
   echo "<font class=nick>";
   for ($tu1=0 ; $tu1<$count33; $tu1++){
      echo str_replace(' ', '&nbsp;', mysql_result($res133, $tu1, 'nick'));
      if ($tu1<>$count33-1) echo "; ";
   }
   echo "</font>";
   echo "<font class=mnuforum>";
   echo "<br>Гостей: ";
   echo "</font>";
   echo "<font class=nick>";
   echo mysql_result($res134, 0, 'nick');
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