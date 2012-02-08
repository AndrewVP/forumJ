<?
   $strt=microtime();
   $startSec=substr($strt,11);
   $startMSec=substr($strt,0,10);
   ob_start();
// Просмотр ветки
   session_start();
   // валидируем параметры строки
	include_once 'classes/ua/com/diletant/web/request.php';
	$request = new Request(false);
	$request->SetRedirectOnConstraintFailure(true);
	$request->SetConstraintFailureDefaultRedirectTargetURL("index.php?page=1&lang=ua");
	$pageConstraint = new Constraint(Constraint::$CT_PERMITTEDCHARACTERS, "1234567890");
	$request->AddConstraint("page", Constraint::$VERB_METHOD_GET, $pageConstraint, false, 1);
	$pageConstraint = new Constraint(Constraint::$CT_MINLENGTH, 1);
	$request->AddConstraint("page", Constraint::$VERB_METHOD_GET, $pageConstraint, false, 1);
	$pageConstraint = new Constraint(Constraint::$CT_MORETHAN, 0);
	$request->AddConstraint("page", Constraint::$VERB_METHOD_GET, $pageConstraint, false, 1);

	$replyConstraint  = new Constraint(Constraint::$CT_PERMITTEDCHARACTERS, "1234567890");
	$request->AddConstraint("reply", Constraint::$VERB_METHOD_GET, $replyConstraint , false, "");
	$replyConstraint  = new Constraint(Constraint::$CT_MINLENGTH, 1);
	$request->AddConstraint("reply", Constraint::$VERB_METHOD_GET, $replyConstraint , false, "");
	$replyConstraint  = new Constraint(Constraint::$CT_MORETHAN, 0);
	$request->AddConstraint("reply", Constraint::$VERB_METHOD_GET, $replyConstraint , false, "");

	$ansConstraint  = new Constraint(Constraint::$CT_PERMITTEDCHARACTERS, "1");
	$request->AddConstraint("ans", Constraint::$VERB_METHOD_GET, $ansConstraint , false, "");
	$ansConstraint  = new Constraint(Constraint::$CT_MINLENGTH, 1);
	$request->AddConstraint("ans", Constraint::$VERB_METHOD_GET, $ansConstraint , false, "");

	$endConstraint  = new Constraint(Constraint::$CT_PERMITTEDCHARACTERS, "1");
	$request->AddConstraint("end", Constraint::$VERB_METHOD_GET, $endConstraint , false, "");
	$endConstraint  = new Constraint(Constraint::$CT_MINLENGTH, 1);
	$request->AddConstraint("end", Constraint::$VERB_METHOD_GET, $endConstraint , false, "");

	$msgConstraint   = new Constraint(Constraint::$CT_PERMITTEDCHARACTERS, "1234567890");
	$request->AddConstraint("msg", Constraint::$VERB_METHOD_GET, $msgConstraint  , false, "");
	$msgConstraint   = new Constraint(Constraint::$CT_MINLENGTH, 1);
	$request->AddConstraint("msg", Constraint::$VERB_METHOD_GET, $msgConstraint  , false, "");
	$msgConstraint   = new Constraint(Constraint::$CT_MORETHAN, 0);
	$request->AddConstraint("msg", Constraint::$VERB_METHOD_GET, $msgConstraint  , false, "");

	$idConstraint = new Constraint(Constraint::$CT_PERMITTEDCHARACTERS, "1234567890");
	$request->AddConstraint("id", Constraint::$VERB_METHOD_GET, $idConstraint, true, "");
	$idConstraint = new Constraint(Constraint::$CT_MINLENGTH, 1);
	$request->AddConstraint("id", Constraint::$VERB_METHOD_GET, $idConstraint, true, "");
	$idConstraint = new Constraint(Constraint::$CT_MORETHAN, 0);
	$request->AddConstraint("id", Constraint::$VERB_METHOD_GET, $idConstraint, true, "");
	$langConstraint = new Constraint(Constraint::$CT_MUSTMATCHREGEXP, "/(ru)|(ua)/");
	$defLang = "ua";
	if (isset($_SESSION['lang'])){
		$defLang = $_SESSION['lang']; 
	}
	$request->AddConstraint("lang", Constraint::$VERB_METHOD_GET, $langConstraint, false, $defLang);
	$request->TestConstraints();
   // Функции
   include("bbcode.php");
   include("smiles.php");
   include("href.php");
   include("cenz.php");
   include("head.php");
   include("body.php");
   include("form_add.php");
   include("button.php");
   include("input.php");
   include("query.php");
   include("post.php");
   //Предотвращаем кеширование
   include("cache.php");
// Какой это номер страницы? если без номера, то первый
   $pg = $request->GetParameterValue("page");
   // Какой язык интерфейса? по умолчанию - украинский :)
//   include("lang.php");
// id Темы
	$gid = $request->GetParameterValue("id");
// Номер поста, на который отвечаем
	$_reply = $request->GetParameterValue("reply");
   if (!empty($_reply)){
   	$reply = stripslashes($request->GetParameterValue("reply"));
   }
// Соединяемся
   include('setup.php');
	include_once 'classes/ua/com/diletant/sql/connection/mysql/connectionmysql.php';
	include_once 'classes/ua/com/diletant/dao/temadao.php';
	include_once 'classes/ua/com/diletant/locale/localestring.php';
	$locale = new LocaleString($request->GetParameterValue("lang"), "strings/lang_", "");
	$connection = new ConnectionMySQL($host, $user, $pass, $data, 1, "tema");
   // У гостей интерфейс модератора
   $idUser = 95;
   if (isset($_SESSION['idu'])) {
   	$idUser = $_SESSION['idu'];
   }
   $dao = new TemaDao($connection, mysql_real_escape_string($request->GetParameterValue("id")), $idUser);
   // Подхватываем куку
   $location="Location: tema.php?".$_SERVER['QUERY_STRING'];
   include("cookie.php");
// нажато "выйти"
   include("exit.php");
   $_SESSION['page']=$pg;
   $_SESSION['where']=$_SERVER['PHP_SELF']."?id=$gid&page=$pg&lang=".$request->GetParameterValue("lang");
   $_SESSION['id']=$gid;
// Зашли с поиска?
	$_msg = $request->GetParameterValue("msg");
   if (!empty($_msg)){
      $countPosts = $dao->getPostsCountInThread($request->GetParameterValue("msg"));
      $pg=ceil($countPosts/$_SESSION['pt']);
      if (!$pg) $pg=1;
   }
// Собираем статистику
   $action=2;
   include("stat.php");
// Записываем счетчики
// Робот?
   if (strpos($_SERVER['HTTP_USER_AGENT'], "StackRambler") == false
   		&& strpos($_SERVER['HTTP_USER_AGENT'], "Googlebot") == false
   		&& strpos($_SERVER['HTTP_USER_AGENT'], "Yandex") == false
   		&& strpos($_SERVER['HTTP_USER_AGENT'], "msnbot") == false
   		&& strpos($_SERVER['HTTP_USER_AGENT'], "Jyxobot") == false
   		&& strpos($_SERVER['HTTP_USER_AGENT'], "Slurp") == false){
// Нет
		$dao->setSeen(isset($_SESSION['autor']) || isset($_COOKIE['user']));
   }
   $title = $dao->getTitle();
   echo "<html>";
   echo "<head>";
   echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
// Стили
   include('style.php');
// Скрипты (смайлики)
   if (strpos($_SERVER['HTTP_USER_AGENT'], "MSIE 5.0")){
      include('smile_ie5.php');
   }
   else {
      include('smile_.php');
   }
// Скрипты (игнор)
   include('jsignor.php');
// Скрипты (подписка)
   include('jssubscribe.php');
// Скрипты (submit поста)
   include('js/post_submit.php');
// Скрипты (автовставка тегов)
   include('jstags.php');
   echo '<link rel="icon" href="/favicon.ico" type="image/x-icon">';
   echo '<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">';
   echo "<title>";
   echo "форум Дилетантов :: ".$title;
   echo "</title>";
   echo "</head>";
// Цвет фона страницы
   echo "<body class='mainBodyBG'>";
// Главная таблица
   echo "<table border='0' style='border-collapse: collapse' width='100%'>";
// Таблица с лого и верхним баннером
   include("logo.php");
// Таблица главных ссылок
   echo "<tr>";
   echo "<td width='100%'>";
   echo "<table border='0' style='border-collapse: collapse' width='100%'>";
// Главное "меню"
   include("menu.php");
// Сколько страниц?
   $count = $dao->getPostsCountInThread("");
   $cou_p = ceil($count/$_SESSION['pt'])+1;
// Если цитирование или последний пост, то нам на последнюю
	$lastPost = false;
	$_end = $request->GetParameterValue("end");
   if (!empty($_reply) || isset($_end)){
   	$pg = $cou_p-1;
   	$lastPost = true;
   }
   $nfirstpost = ($pg-1)*$_SESSION['pt'];
// Ссылки на другие страницы  Тут надо убрать colspan!
   echo "<tr><td width=100%>";
   echo "<table width=100%>";
   echo "<tr>";
   echo "<td>";
   echo "<table>";
   echo "<tr>";
   echo "<td class='page'>";
   echo "<font class=mnuforum><b>$_mess22&nbsp;</b></font>";
   echo "</td>";
   $i_2=0;
   for ($i_1=1; $i_1<$cou_p; $i_1++){
      $i_2=$i_2+1;
      if (($i_1>($pg-5) and $i_1<($pg+5)) or $i_2==10 or $i_1==1 or $i_1==($cou_p-1)){
         if ($i_2==10) $i_2=0;
         if ($i_1==$pg){
            echo "<td class='pagecurrent'>";
            echo "<span class=mnuforum><b>".$i_1."</b></span>";
            echo "</td>";
         }
         else {
            echo "<td class='page'>";
            echo "<a class=mnuforum href='tema.php?page=".$i_1."&id=".$gid."'>".$i_1."</a>";
            echo "</td>";
         }
      }
   }
   echo "</tr>";
   echo "</table>";

   echo "</td>";

   echo "<td align=right>";
// Сторінка сформована :)
   echo "<span class=posthead>".$_mess91."</span>";
   echo "</td>";
   echo "</tr></table>";
   echo "</td>";
   echo "</tr></table></td></tr>";
// Таблица главных ссылок кончилась
//Строка с таблицей форума
   echo "<tr><td height='400' valign='top'>";
// Таблица форума
   echo "<table border='0' cellpadding='2' cellspacing='0' width='100%'>";
// Определяем кол-во строк таблицы
   $i2=$pg*$_SESSION['pt'];
   if ($i2>$count) {
   	$i2=$count-($pg-1)*$_SESSION['pt'];
   }else{
   	$i2=$_SESSION['pt'];
   }
// Получаем массив постов
	$arrPosts = $dao->getPostsList($str_fd_timezone_hr, $str_fd_timezone_mn, $nfirstpost,$i2, $locale, $pg, $lastPost);
// Тема
   $xyz= "";
// Выводим строки
	foreach ($arrPosts as $post) {
		echo $post->toString();
	}

// /Таблица форума
   echo "</table>";
// "Граница" внизу
   echo "</td>";
   echo "</tr>";
// Таблица главных ссылок
// Ссылки на страницы
   echo "<tr>";
   echo "<td width='100%'>";
   echo "<table border='0' style='border-collapse: collapse' width='100%'>";
   echo "<tr>";
   echo "<td colspan='5'>";
   echo "<table>";
   echo "<tr>";
   echo "<td class='page'>";
   echo "<font class=mnuforum><b>$_mess22&nbsp;</b></font>";
   echo "</td>";
   $i_2=0;
   for ($i_1=1; $i_1<$cou_p; $i_1++){
      $i_2=$i_2+1;
      if (($i_1>($pg-5) and $i_1<($pg+5)) or $i_2==10 or $i_1==1 or $i_1==($cou_p-1)){
         if ($i_2==10) $i_2=0;
         if ($i_1==$pg){
            echo "<td class='pagecurrent'>";
            echo "<span class=mnuforum><b>".$i_1."</b></span>";
            echo "</td>";
         }
         else {
            echo "<td class='page'>";
            echo "<a class=mnuforum href='tema.php?page=".$i_1."&id=".$gid."'>".$i_1."</a>";
            echo "</td>";
         }
      }
   }
   echo "</tr>";
   echo "</table>";
   echo "</td>";
   echo "</tr>";
// Главное "меню"
   include("menu.php");
   echo "</table></td></tr>";
   if (isset($_SESSION['autor'])){
		/*Форма подписки/отписки  на ветку*/
		/*Мы уже подписаны?*/
      if ($dao->isUserSubscribed($_SESSION['idu'])){
      	/*Подписка есть, предлагаем отказаться*/
         $action="delonesubs.php?pg=".$pg;
         $mess=$_mess90;
      }else{
         /*Подписки нет - тогда предлагаем подписаться*/
         $action="addsubs.php?pg=".$pg;
         $mess=$_mess89;
      }?>
         <tr>
         	<td align=right>
         		<form id='subs' action='<?echo($action);?>' method=POST >
         			<table>
         				<tr>
         					<td>
						         <?echo(fd_button($mess,"subscribe();","btn_subs", "1", "", "", "", ""))?>
									<?/*Прередаем нужные пераметры...*/?>
									<input type=hidden name="IDT" value="<?echo($gid);?>">
									<?echo(fd_form_add());?>
         					</td>
         				</tr>
         			</table>
         		</form>
         	</td>
         </tr>
<?
      $re='';
      $str_head=$title;
   // Если цитируем/редактируем
      if (!empty($_reply)) {
         $res2 = $dao->getPost($reply);
   // Редактируем?
         $str_head=stripslashes($res2['tilte']);
         if (strtoupper($res2['nick'])==strtoupper($_SESSION['autor'])) {
   // Да
            $_SESSION['edit']=$reply;
            $re='';
         }
         else {
   // Нет
            $_SESSION['edit']=0;
         }
      }
   // Новое мнение
   // Форма нового поста
?>
		<tr>
			<td>
				<a name='edit'>
					&nbsp;
				</a>
				<table>
   				<tr>
   					<td>
   						<form name='post' action='write.php' method='POST'>
   							<table width='100%'>
									<?/*Тема*/?>
   								<tr>
   									<td colspan="2" align='CENTER'>
   										<table>
   											<tr>
   												<td>
			   										<?echo($_mess59.":&nbsp;");?>
   												</td>
   												<td>
											         <?echo(fd_input("NHEAD", $re.htmlspecialchars($str_head), 70, "1", "mnuforumSm", "", "", ""))?>
   												</td>
   											</tr>
   										</table>
   									</td>
   								</tr>
   								<tr>
										<?/*Смайлики заголовок*/?>
   									<td width="400" align='CENTER'>
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
      								<td valign="TOP" width="100%" height="100%">
	   									<?/*Смайлики*/?>
      									<?include("smiles_add.php");?>
                              </td>
                              <td width='500' align='CENTER' valign='top'>
											<?/*Автотеги*/
      									include("autotags_add.php");
                                 /* текстарий*/
                                 $textarea="";
                                 if (!empty($_reply)) {
                                 	$_ans = $request->GetParameterValue("ans");
                                    if ($_SESSION['edit']>0){
                                       $textarea.=stripslashes($res2['body']);
                                    }elseif (!isset($_ans)){
                                        $textarea.="[quote][b]".stripslashes($res2['nick'])."[/b]";
                                        $textarea.=$_mess14.chr(13);
                                        $textarea.=stripslashes($res2['body'])."[/quote]";
                                    }else{
                                       $textarea.="[b]".stripslashes($res2['nick'])."[/b]";
                                       $textarea.=", ";
                                    }
                                 }
			         					?>
				         				<textarea rows='20' class='mnuforumSm' id='ed1' name='A2' cols='55'><?echo($textarea);?></textarea>
        									<br>
        									<input type="checkbox" name="no_exit" value="1">
        									<?echo($_mess123);?>
                           		<?/*Кнопки*/?>
        									<table>
        										<tr>
        											<td>
											         <?echo(fd_button($_mess13,"post_submit(\"write\");","B1", "1", "", "", "", ""))?>
        											</td>
        											<td>
											         <?echo(fd_button($_mess63,"post_submit(\"view\");","B3", "1", "", "", "", ""))?>
        											</td>
        										</tr>
        									</table>
                                 <?
		                           /*Если редактируем*/
                              	if (!empty($_reply) && ($res2['auth']==$_SESSION['idu']))
                              	{?>
                              		<input type=hidden name="IDB" size="20" value="<?echo($reply);?>">
                              		<input type=hidden name="IDTbl" size="20" value="<?echo($res2['table_post']);?>">
                              		<input type=hidden name="IDPst" size="20" value="<?echo($res2['id']);?>">
                              		<input type=hidden name="IDTblHead" size="20" value="<?echo($res2['table_head']);?>">
                              		<input type=hidden name="IDHead" size="20" value="<?echo($res2['id']);?>">
                              	<?}
                           		/*id темы*/?>
                              	<input type=hidden name="IDT" size="20" value="<?echo($gid);?>">
                              	<?
                              	if ($dao->isQuest($gid)){
                              	 ?>
                              	<input type=hidden name="ISQUEST" size="20" value="<?echo(true);?>">
                              	 <?
                              	}
                              	 ?>
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
      /*Форма закончилась*/
      }
   mysql_close($conn);
// Баннер внизу, счетчики и копирайт.
   include("end.php");
   ?>
	</body>
</html>
<?
   $strtmp=ob_get_contents();
   ob_end_clean();
   $end=microtime();
   $pgTime=substr($end,11)-$startSec+substr($end,0,10)-$startMSec;
   $strtmp=str_replace("ъъ_ъ", $pgTime, $strtmp);
   echo $strtmp;
?>