<?
   $strt=microtime();
   $startSec=substr($strt,11);
   $startMSec=substr($strt,0,10);
   ob_start();
// �������� �����
   session_start();
   // ���������� ��������� ������
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
   // �������
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
   //������������� �����������
   include("cache.php");
// ����� ��� ����� ��������? ���� ��� ������, �� ������
   $pg = $request->GetParameterValue("page");
   // ����� ���� ����������? �� ��������� - ���������� :)
//   include("lang.php");
// id ����
	$gid = $request->GetParameterValue("id");
// ����� �����, �� ������� ��������
	$_reply = $request->GetParameterValue("reply");
   if (!empty($_reply)){
   	$reply = stripslashes($request->GetParameterValue("reply"));
   }
// �����������
   include('setup.php');
	include_once 'classes/ua/com/diletant/sql/connection/mysql/connectionmysql.php';
	include_once 'classes/ua/com/diletant/dao/temadao.php';
	include_once 'classes/ua/com/diletant/locale/localestring.php';
	$locale = new LocaleString($request->GetParameterValue("lang"), "strings/lang_", "");
	$connection = new ConnectionMySQL($host, $user, $pass, $data, 1, "tema");
   // � ������ ��������� ����������
   $idUser = 95;
   if (isset($_SESSION['idu'])) {
   	$idUser = $_SESSION['idu'];
   }
   $dao = new TemaDao($connection, mysql_real_escape_string($request->GetParameterValue("id")), $idUser);
   // ������������ ����
   $location="Location: tema.php?".$_SERVER['QUERY_STRING'];
   include("cookie.php");
// ������ "�����"
   include("exit.php");
   $_SESSION['page']=$pg;
   $_SESSION['where']=$_SERVER['PHP_SELF']."?id=$gid&page=$pg&lang=".$request->GetParameterValue("lang");
   $_SESSION['id']=$gid;
// ����� � ������?
	$_msg = $request->GetParameterValue("msg");
   if (!empty($_msg)){
      $countPosts = $dao->getPostsCountInThread($request->GetParameterValue("msg"));
      $pg=ceil($countPosts/$_SESSION['pt']);
      if (!$pg) $pg=1;
   }
// �������� ����������
   $action=2;
   include("stat.php");
// ���������� ��������
// �����?
   if (strpos($_SERVER['HTTP_USER_AGENT'], "StackRambler") == false
   		&& strpos($_SERVER['HTTP_USER_AGENT'], "Googlebot") == false
   		&& strpos($_SERVER['HTTP_USER_AGENT'], "Yandex") == false
   		&& strpos($_SERVER['HTTP_USER_AGENT'], "msnbot") == false
   		&& strpos($_SERVER['HTTP_USER_AGENT'], "Jyxobot") == false
   		&& strpos($_SERVER['HTTP_USER_AGENT'], "Slurp") == false){
// ���
		$dao->setSeen(isset($_SESSION['autor']) || isset($_COOKIE['user']));
   }
   $title = $dao->getTitle();
   echo "<html>";
   echo "<head>";
   echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
// �����
   include('style.php');
// ������� (��������)
   if (strpos($_SERVER['HTTP_USER_AGENT'], "MSIE 5.0")){
      include('smile_ie5.php');
   }
   else {
      include('smile_.php');
   }
// ������� (�����)
   include('jsignor.php');
// ������� (��������)
   include('jssubscribe.php');
// ������� (submit �����)
   include('js/post_submit.php');
// ������� (����������� �����)
   include('jstags.php');
   echo '<link rel="icon" href="/favicon.ico" type="image/x-icon">';
   echo '<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">';
   echo "<title>";
   echo "����� ���������� :: ".$title;
   echo "</title>";
   echo "</head>";
// ���� ���� ��������
   echo "<body class='mainBodyBG'>";
// ������� �������
   echo "<table border='0' style='border-collapse: collapse' width='100%'>";
// ������� � ���� � ������� ��������
   include("logo.php");
// ������� ������� ������
   echo "<tr>";
   echo "<td width='100%'>";
   echo "<table border='0' style='border-collapse: collapse' width='100%'>";
// ������� "����"
   include("menu.php");
// ������� �������?
   $count = $dao->getPostsCountInThread("");
   $cou_p = ceil($count/$_SESSION['pt'])+1;
// ���� ����������� ��� ��������� ����, �� ��� �� ���������
	$lastPost = false;
	$_end = $request->GetParameterValue("end");
   if (!empty($_reply) || isset($_end)){
   	$pg = $cou_p-1;
   	$lastPost = true;
   }
   $nfirstpost = ($pg-1)*$_SESSION['pt'];
// ������ �� ������ ��������  ��� ���� ������ colspan!
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
// ������� ���������� :)
   echo "<span class=posthead>".$_mess91."</span>";
   echo "</td>";
   echo "</tr></table>";
   echo "</td>";
   echo "</tr></table></td></tr>";
// ������� ������� ������ ���������
//������ � �������� ������
   echo "<tr><td height='400' valign='top'>";
// ������� ������
   echo "<table border='0' cellpadding='2' cellspacing='0' width='100%'>";
// ���������� ���-�� ����� �������
   $i2=$pg*$_SESSION['pt'];
   if ($i2>$count) {
   	$i2=$count-($pg-1)*$_SESSION['pt'];
   }else{
   	$i2=$_SESSION['pt'];
   }
// �������� ������ ������
	$arrPosts = $dao->getPostsList($str_fd_timezone_hr, $str_fd_timezone_mn, $nfirstpost,$i2, $locale, $pg, $lastPost);
// ����
   $xyz= "";
// ������� ������
	foreach ($arrPosts as $post) {
		echo $post->toString();
	}

// /������� ������
   echo "</table>";
// "�������" �����
   echo "</td>";
   echo "</tr>";
// ������� ������� ������
// ������ �� ��������
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
// ������� "����"
   include("menu.php");
   echo "</table></td></tr>";
   if (isset($_SESSION['autor'])){
		/*����� ��������/�������  �� �����*/
		/*�� ��� ���������?*/
      if ($dao->isUserSubscribed($_SESSION['idu'])){
      	/*�������� ����, ���������� ����������*/
         $action="delonesubs.php?pg=".$pg;
         $mess=$_mess90;
      }else{
         /*�������� ��� - ����� ���������� �����������*/
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
									<?/*��������� ������ ���������...*/?>
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
   // ���� ��������/�����������
      if (!empty($_reply)) {
         $res2 = $dao->getPost($reply);
   // �����������?
         $str_head=stripslashes($res2['tilte']);
         if (strtoupper($res2['nick'])==strtoupper($_SESSION['autor'])) {
   // ��
            $_SESSION['edit']=$reply;
            $re='';
         }
         else {
   // ���
            $_SESSION['edit']=0;
         }
      }
   // ����� ������
   // ����� ������ �����
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
									<?/*����*/?>
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
										<?/*�������� ���������*/?>
   									<td width="400" align='CENTER'>
      									<p>
      										<?echo($_mess21.":");?>
      									</p>
      								</td>
   									<?/*�����������*/?>
								      <td align='CENTER'>
      									<p>
      										<?echo($_mess12);?>
      									</p>
      								</td>
      							</tr>
   								<?/*����*/?>
      							<tr>
      								<td valign="TOP" width="100%" height="100%">
	   									<?/*��������*/?>
      									<?include("smiles_add.php");?>
                              </td>
                              <td width='500' align='CENTER' valign='top'>
											<?/*��������*/
      									include("autotags_add.php");
                                 /* ���������*/
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
                           		<?/*������*/?>
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
		                           /*���� �����������*/
                              	if (!empty($_reply) && ($res2['auth']==$_SESSION['idu']))
                              	{?>
                              		<input type=hidden name="IDB" size="20" value="<?echo($reply);?>">
                              		<input type=hidden name="IDTbl" size="20" value="<?echo($res2['table_post']);?>">
                              		<input type=hidden name="IDPst" size="20" value="<?echo($res2['id']);?>">
                              		<input type=hidden name="IDTblHead" size="20" value="<?echo($res2['table_head']);?>">
                              		<input type=hidden name="IDHead" size="20" value="<?echo($res2['id']);?>">
                              	<?}
                           		/*id ����*/?>
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
      /*����� �����������*/
      }
   mysql_close($conn);
// ������ �����, �������� � ��������.
   include("end.php");
   ?>
	</body>
</html>
<?
   $strtmp=ob_get_contents();
   ob_end_clean();
   $end=microtime();
   $pgTime=substr($end,11)-$startSec+substr($end,0,10)-$startMSec;
   $strtmp=str_replace("��_�", $pgTime, $strtmp);
   echo $strtmp;
?>