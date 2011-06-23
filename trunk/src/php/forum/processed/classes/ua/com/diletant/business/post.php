<?php
include_once 'classes/ua/com/diletant/util/time.php';

class Post{

	/**
	 * Ник
	 * @var unknown_type
	 */
	private $str_nick;

	/**
	 * Id автора
	 * @var unknown_type
	 */
	private $str_idu;

	/**
	 * id пста
	 * @var unknown_type
	 */
	private $str_id;

	/**
	 * Заголовок
	 * @var unknown_type
	 */
	private $str_head;

	/**
	 * Текст
	 * @var unknown_type
	 */
	private $str_body;

	/**
	 * id Ветки
	 * @var unknown_type
	 */
	private $str_t_id;

	/**
	 * Количество редактрований
	 * @var unknown_type
	 */
	private $str_nred;

	/**
	 * ip автора
	 * @var unknown_type
	 */
	private $str_ip;

	/**
	 * Можно ли показывать ip
	 * @var unknown_type
	 */
	private $str_hip;

	/**
	 * Адрес аватары
	 * @var unknown_type
	 */
	private $str_avatar;

	/**
	 * Показывать аватару
	 * @var unknown_type
	 */
	private $str_s_avatar;

	/**
	 * Показывать аватару
	 * @var unknown_type
	 */
	private $str_ok_avatar;

	/**
	 * Страна автора
	 * @var unknown_type
	 */
	private $str_country;

	/**
	 * Город автора
	 * @var unknown_type
	 */
	private $str_city;

	/**
	 * Показывать город
	 * @var unknown_type
	 */
	private $str_s_city;

	/**
	 * Подпись к посту
	 * @var unknown_type
	 */
	private $str_footer;

	/**
	 * Домен автора
	 * @var unknown_type
	 */
	private $str_domen;

	/**
	 * Показывать страну
	 * @var unknown_type
	 */
	private $str_s_country;

	/**
	 * Номер странцы
	 * @var unknown_type
	 */
	private $pageNumber;

	/**
	 * Id темы
	 * @var unknown_type
	 */
	private $idThread;

	/**
	 * Resultset игнорируемых
	 * @var unknown_type
	 */
	private $res_ignor;

	/**
	 * Наличие игнора у посетителя
	 * @var unknown_type
	 */
	private $has_ignor;

	/**
	 * Локализованные сообщения 
	 * @var LocaleString
	 */
	private $locale;

	/**
	 * Признак последнего поста
	 * @var unknown_type
	 */
	private $isLastPost;

	/**
	 * Признак опроса
	 * @var unknown_type
	 */
	private $isQuest;

	/**
	 * Отрисовывать ли ссылки для админа
	 * @var unknown_type
	 */
	private $isAdminForvard;

	/**
	 * Может ли пользователь добавлять варианты ответов
	 * (для опросов)
	 * @var unknown_type
	 */
	private $isUserCanAddAnswer;

	/**
	 * Показывать ли аватару
	 * @var unknown_type
	 */
	private $str_v_avatars;

	/**
	 * Тип поиска 
	 * @var unknown_type
	 */
	private $is_found;

	/**
	 * Массив поисковых слов
	 * @var unknown_type
	 */
	private $idWordsForms;

	/**
	 * Текущая страница
	 * @var unknown_type
	 */
	private $pg;
	
	/**
	 * Соединение с БД
	 *
	 * @var IConnection
	 */
	private $conn;
	
	/**
	 * Время создания поста
	 *
	 * @var Time
	 */
	private $postTime;
	
	/**
	 * Время редактирования поста
	 *
	 * @var Time
	 */
	private $postEditTime;
	
	/**
	 * Признак первого поста в ветке
	 *
	 * @var unknown_type
	 */
	private $isFirst = false;
	
	/**
	 * Вопрос опроса
	 *
	 * @var string
	 */
	private $question;
	
	/**
	 * Количество вариантов ответа в опросе
	 *
	 * @var int
	 */
	private $answerAmount;
	
	/**
	 * Массив вариантов ответов в опросе
	 *
	 * @var array
	 */
	private $arrNodes;
	
	/**
	 * Проголосовал ли уже текущий посетитель
	 *
	 * @var boolean
	 */
	private $userVote;
	
	/**
	 * Количество голосов в опросе
	 *
	 * @var int
	 */
	private $voicesAmount;
	
	/**
	 * Конструктор
	 *
	 * @param unknown_type $arrFetch
	 * @param LocaleString $locale
	 */
	public function __construct($arrFetch, 
											LocaleString $locale, 
											$idWordsForms, 
											$res_ignor, 
											$has_ignor, 
											$is_found, 
											$isAdminForvard, 
											$isQuest, 
											$isUserCanAddAnswer,
											$pg,
											IConnection $conn,
											$isFirst){
		// Присваиваем локализованные сообщения
		$this->locale = $locale;
		// id ветки
		$this->idThread = $arrFetch['head'];
		// 
		$this->pg = $pg;
		// 
		$this->str_id = $arrFetch['id'];
		// Массив поисковых слов
		if (!is_null($idWordsForms)){
			$this->idWordsForms = $idWordsForms;
		}
		// тип поиска
		$this->is_found = $is_found;
		//
		$this->isAdminForvard = $isAdminForvard;
		// 
		$this->isQuest = $isQuest;
		// 
		$this->isAdminForvard=$isAdminForvard;
		// 
		$this->isUserCanAddAnswer = $isUserCanAddAnswer; 
		// 
		$this->res_ignor = $res_ignor;
		// 
		$this->has_ignor = $has_ignor;
		// 
		$this->conn = $conn;
		//
		$this->isFirst = $isFirst;;
		
	}
	
	public function toString(){
		if ($this->is_found == 1){
			$this->str_nick = htmlspecialchars($this->str_nick);
			$this->str_nick = str_ireplace($this->idWordsForms, "<span class='found'>".$this->idWordsForms."</span>", $this->str_nick);
			//		$str_nick="<span class='found'>".$str_nick."</span>";
		}elseif($this->is_found == 2){
			$rp = "[span class='found']$0[/span]";
			$this->str_head = preg_replace($this->idWordsForms,$rp, $this->str_head);
		}elseif($this->is_found == 3){
			$rp = "[span class='found']$0[/span]";
			$this->str_body = preg_replace($this->idWordsForms,$rp, $this->str_body);
		}
		$result = '';
		if (trim($this->str_ip) == trim($this->str_domen)){
			$this->str_domen = substr($this->str_domen, 0, strrpos($this->str_domen, '.')+1).'---';
		}else{
			$this->str_domen = '---'.substr($this->str_domen, strpos($this->str_domen, '.'));
		}
		$result.="<tr class=heads>";
		$result.= "<td  class=internal>";
		if ($this->isLastPost) $result.= "<a name='end'></a>";
		$result.= "<a name='$this->str_id'>&nbsp;</a>";
		$result.= "<a class=nik href='tema.php?id=".$this->idThread."&msg=".$this->str_id."#".$this->str_id."'><b>&nbsp;&nbsp;".fd_head(htmlspecialchars($this->str_head))."</b></a>";
		$result.= "</td></tr>";
		$result.= "<tr><td>";
		$div = 0;
		$_div = "";
		if ($this->has_ignor){
			if (in_array($this->str_idu, $this->res_ignor)) $div = 1;
		}
		$result.= "<span class='tbtextnread'>".$this->str_nick."</span>&nbsp;".chr(149);
		$result.= "&nbsp;<img border='0' src='smiles/icon_minipost.gif'>&nbsp;<span class='posthead'>".$this->postTime->toString("d.m.Y H:i")."</span>&nbsp;";
		if (!$div and isset($_SESSION['idu']) and $this->str_idu!=$_SESSION['idu']){
			$result.= chr(149).'&nbsp;<a class="posthead" href="ignor.php?idi='.$this->str_idu.'&idt='.$this->idThread.'&idp='.$this->str_id.'&pg='.$this->pg.'" rel="nofollow">'.$this->locale->getString("mess68").'</a>';
		}
		$result.="</td></tr>";
		$result.="<tr><td>";
		if ($div){
			$result.= "<a href='#' onclick='togglemsg(\"dd".$this->str_id."\"); return false;' rel='nofollow'>".$this->locale->getString("mess142")."</a>";
			$_div=" style='visibility: hidden; display:none;'";
		}
		else {
			$_div="";
		}
		$result.= "<div id=dd".$this->str_id.$_div.">";
		if (!(!isset($_SESSION['idu']) and $div)){
			$result.= "<table width='100%'><tr><td valign=top class='matras'>";
			$result.= "<table style='table-layout:fixed;' width='170'><tr><td valign=top>";
			$result.= "<div style='padding:10px;'>";
			if ($this->str_s_avatar and $this->str_ok_avatar and trim($this->str_avatar)<>"" and $this->str_v_avatars){
				$result.= "<a href='control.php?id=9'><img border='0' src='".$this->str_avatar."' rel=\"nofollow\"></a>";
			}else{
				$result.= "<a href='control.php?id=9' rel='nofollow'><img border='0' src='smiles/no_avatar.gif'></a>";
			}
			$result.= "</div>";
			$result.= "<span class='posthead'><u>".$this->locale->getString("mess111")."</u></span><br>";
			if ($this->str_s_country || $this->str_country==""){
				$result.= "<span class='posthead'>".$this->locale->getString("mess114")."</span><br>";
			}else{
				$result.= "<span class='posthead'>".$this->str_country."</span><br>";
			}
			$result.= "<span class='posthead'><u>".$this->locale->getString("mess112")."</u></span><br>";
			if ($this->str_s_city || $this->str_city==""){
				$result.= "<span class='posthead'>".$this->locale->getString("mess114")."</span><br>";
			}else{
				$result.= "<span class='posthead'>".$this->str_city."</span><br>";
			}
			$result.= "</td></tr></table>";
			$result.= "</td><td valign='top' width='100%'>";
			$result.= "<table width='100%'>";
			if ($this->isQuest){
				$result.= $this->getQuest() ;
			}
			$result.="<tr><td>";
			$result.="<p class=post>".fd_body($this->str_body)."</p>";
			$result.="</td></tr>";
			$result.= "</table></td></tr>";
			$result.="<tr><td class='matras' colspan=2></td></tr>";
			$result.="<tr><td class='matras'></td><td>";
			$result.="<p class=post>".fd_body($this->str_footer)."</p>";
			$result.="</td></tr>";
			$result.="<tr><td align='RIGHT' width='100%' colspan=2>";
			if ($this->str_nred>0){
				$result.= "<table class='matras' width='100%'>";
				$result.= "<tr><td align='LEFT'>";
				$result.= "<span class='posthead'>".$this->locale->getString("mess50").$this->str_nred.$this->locale->getString("mess51").$this->postEditTime->toString("d.m.Y H:i")."</span>";
			}
			else {
				$result.= "<table class='matras'>";
				$result.= "<tr><td align='LEFT'>";
				$result.= " ";
			}
			$result.="</td>";
			if (isset($_SESSION['autor'])) {
				if ($this->isAdminForvard){
					$result.= "<td align='CENTER' width='70'>";
					$result.= "<span class='posthead'><a href='site.php?id=".$this->idThread."&post=".$this->str_id."' rel=\"nofollow\">".$this->locale->getString("mess162")."</a></span>";
					$result.= "</td>";
				}
				if ($_SESSION['idu']==$this->str_idu) {
					$result.= "<td align='CENTER' width='70'>";
					$result.= "<span class='posthead'><a href='tema.php?id=".$this->idThread."&reply=".$this->str_id."#edit' rel=\"nofollow\">".$this->locale->getString("mess141")."</a></span>";
					$result.= "</td>";
				}
				else {
					$result.= "<td align='CENTER' width='70'>";
					$result.= "<span class='posthead'><a href='tema.php?id=".$this->idThread."&reply=".$this->str_id."#edit' rel=\"nofollow\">".$this->locale->getString("mess139")."</a></span>";
					$result.= "</td>";
					$result.= "<td align='CENTER' width='70'>";
					$result.= "<span class='posthead'><a href='tema.php?id=".$this->idThread."&reply=".$this->str_id."&ans=1#edit' rel=\"nofollow\">".$this->locale->getString("mess140")."</a></span>";
					$result.= "</td>";
				}}
				$result.= "</tr></table>";
				$result.= "</td></tr>";
				$result.= "</table>";
		}else{
			$result.= $this->locale->getString("mess103");
		}
		$result.= "</div>";
		$result.= "</td></tr>";
		return $result;
	}
	
	/**
	 * Устанавливает текс поста
	 *
	 * @param unknown_type $strBody
	 */
	public function setBody($strBody){
		$this->str_body = $strBody;
	}
	 
	/**
	 * Устанавливает признак последнего поста в ветке
	 *
	 */
	public function setLastPost(){
		$this->isLastPost = true; 
	}

	public function loadHeads($arrFetch, $add_hr, $add_mn){
		// 
		$this->str_nick = $arrFetch['nick'];
		// 
		$this->str_idu = $arrFetch['auth'];
		// 
		$time = new Time($arrFetch['post_time']);
		$time->add($add_hr, Time::$HOUR);
		$time->add($add_mn, Time::$MINUTE);
		$this->postTime =  $time;
		// 
		$this->str_head = stripslashes($arrFetch['tilte']);
		// 
		$this->str_type = $arrFetch['type'];
		// 
		$this->str_nred = $arrFetch['nred'];
		// 
		$edTime = new Time($arrFetch['post_edit_time']);
		$edTime->add($add_hr, Time::$HOUR);
		$edTime->add($add_mn, Time::$MINUTE);
		$this->postEditTime = $edTime; 
		// 
		$this->str_ip = $arrFetch['ip'];
		// 
		$this->str_hip = $arrFetch['h_ip'];
		// 
		$this->str_avatar = $arrFetch['avatar'];
		// 
		$this->str_s_avatar = $arrFetch['s_avatar'];
		// 
		$this->str_ok_avatar = $arrFetch['ok_avatar'];
		// 
		$this->str_v_avatars = $arrFetch['v_avatars'];
		// 
		$this->str_country = $arrFetch['country'];
		// 
		$this->str_s_country = $arrFetch['scountry'];
		// 
		$this->str_city = $arrFetch['city'];
		// 
		$this->str_s_city = $arrFetch['scity'];
		// 
		$this->str_footer = $arrFetch['footer'];
		// 
		$this->str_domen = $arrFetch['domen'];
	}

	/**
	 * Устанавливает признак первого поста в опросе
	 * 
	 */
	public function setQuest(){
		$this->isQuest = true;
	}

	/**
	 * Возвращает признак первого поста в ветке
	 * 
	 */
	public function isFirst(){
		return $this->isFirst;
	}

	/**
	 * Устанавливает вопрос опроса
	 *
	 * @param string $question
	 */
	public function setQuestion($question){
		$this->question = $question;
	}
	
	/**
	 * Возвращает вопрос опроса
	 *
	 * @return unknown
	 */
	private function getQuestion(){
		return $this->question;
	}
	
	/**
	 * Устанавливает количество вариантов ответа в опросе
	 *
	 * @param int $answerAmount
	 */
	public function setAnswerAmount($answerAmount){
		$this->answerAmount = $answerAmount;
	}
	
	/**
	 * Возвращает количество вариантов ответа в опросе
	 * @return int
	 */
	public function getAnswerAmount(){
		return $this->answerAmount;
	}
	
	/**
	 * Устанавливает количество голосов в опросе
	 *
	 * @param int $voicesAmount
	 */
	public function setVoicesAmount($voicesAmount){
		$this->voicesAmount = $voicesAmount;
	}
	
	/**
	 * Возвращает количество голосов в опросе
	 * @return int
	 */
	public function getVoicesAmount(){
		return $this->voicesAmount;
	}
	
	/**
	 * Устанавливает варианты ответа в опросе
	 *
	 * @param array $arrNodes
	 */
	public function setNodes($arrNodes){
		$this->arrNodes = $arrNodes;
	}
	
	/**
	 * Возвращает варианты ответа в опросе
	 * @return array
	 */
	public function getNodes(){
		return $this->arrNodes;
	}
	
	/**
	 * Устанавливает проголосовал ли уже текщий юзер
	 *
	 * @param boolean $arrNodes
	 */
	public function setUserVote($userVote){
		$this->userVote = $userVote;
	}
	
	/**
	 * Возвращает проголосовал ли уже текщий юзер
	 * @return boolean
	 */
	public function isUserVote(){
		return $this->userVote;
	}
	
	private function getQuest(){
		$result='';
		$nvcs = $this->getVoicesAmount();
		$result.=("<tr><td>");
		$result.= "<p align=\"CENTER\"><font size=4><b>".$this->getQuestion()."</b></font></p><br>";
		$result.=("</td></tr>");
		$result.=("<tr><td align=\"CENTER\">");
		$nodes = $this->getNodes();
		if (isset($_SESSION['autor']) and !$this->isUserVote()){
			$result.=("<form  action='voice.php' method='POST'><table class=content>");
			for ($iq1=1; $iq1<$this->getAnswerAmount(); $iq1++){
				$result.=("<tr><td class=voice_left align='right'>");
				$in1 = $nodes[$iq1]['id'];
				$check="";
				if ($iq1==1) $check=" CHECKED";
				if ($nodes[$iq1]['type']){
					$result.= $this->locale->getString("mess143");
					if ($nodes[$iq1]['type'] == 1){
						$result.= "<b>".$nodes[$iq1]['nick']."</b>";
					}
					else{
						$result.= "<b>".$this->locale->getString("mess144")."</b>";
					}
					$result.= "</td><td class=voice_right align='left'>";
					$result.=("<input type='radio' name='ANSWER' value='$in1'>&nbsp;".fd_smiles(fd_href(stripslashes($nodes[$iq1]['node'])))."<br>");
				}
				else {
					$result.= "</td><td class=voice_right align='left'>";
					$result.=("<input type='radio' name='ANSWER' value='$in1'".$check.">&nbsp;".fd_smiles(fd_href(stripslashes($nodes[$iq1]['node'])))."<br>");
				}
				$result.=("</td></tr>");
			}
			$result.=("<tr><td colspan='2' align='CENTER'>");
			$result.= "<input type=hidden name=\"IDU1\" size=\"20\" value=\"".$_SESSION['idu']."\">";
			$result.= "<input type=hidden name=\"AUT1\" size=\"20\" value=\"".$_SESSION['autor']."\">";
			$result.= "<input type=hidden name=\"IDT1\" size=\"20\" value=\"".$this->idThread."\">";
			if (isset($_SESSION['pass2'])) {
				$result.= "<input type=hidden name=\"PS21\" size=\"20\" value=\"".$_SESSION['pass2']."\">";
			}
			else {
				$result.= "<input type=hidden name=\"PS11\" size=\"20\" value=\"".$_SESSION['pass1']."\">";
			}
			$result.="<input type='submit' value='".$this->locale->getString("mess145")."' name='OK'>";
			$result.=("</td></tr>");
			$result.=("</table></form>");
			$result.=("</td></tr>");
			if ($this->isUserCanAddAnswer){
				$iq3=0;
				for ($iq2=1; $iq2<$this->getAnswerAmount(); $iq2++){
					if ($nodes[$iq2]['user'] == $_SESSION['idu']) $iq3=1;
				}
				if (!$iq3){
					$result.=("<tr><td>");
					$result.=("<form  action=\"uservoice.php\" method=\"POST\"><table align=\"CENTER\">");
					$result.=("<tr><td>");
					$result.=$this->locale->getString("mess153").":<br>";
					$result.=("<input type=\"text\" name=\"P\" size=\"100\">");
					$result.= "<input type=hidden name=\"IDU2\" size=\"20\" value=\"".$_SESSION['idu']."\">";
					$result.= "<input type=hidden name=\"AUT2\" size=\"20\" value=\"".$_SESSION['autor']."\">";
					$result.= "<input type=hidden name=\"IDT2\" size=\"20\" value=\"".$this->idThread."\">";
					if (isset($_SESSION['pass2'])) {
						$result.= "<input type=hidden name=\"PS22\" size=\"20\" value=\"".$_SESSION['pass2']."\">";
					}
					else {
						$result.= "<input type=hidden name=\"PS12\" size=\"20\" value=\"".$_SESSION['pass1']."\">";
					}
					$result.=("</td></tr>");
					$result.=("<tr><td align=\"CENTER\">");
					$result.="<input type='checkbox' name='HD' value='1' checked>&nbsp;".$this->locale->getString("mess146")."<br>";
					$result.="<input type='submit' value='".$this->locale->getString("mess145")."' name='OK'>";
					$result.=("</td></tr>");
					$result.=("</table></form>");
					$result.=("</td></tr>");
	   }
			}
		}
		if (!$nvcs) $nvcs=1/10000000;
		$result.=("<tr><td align=\"CENTER\">");
		$result.= "<b>".$this->locale->getString("mess152").": ".round($nvcs,0)."</b>";
		$result.=("</td></tr>");
		$result.=("<tr><td align=\"CENTER\">");
		$result.= "<table align='CENTER' class=control>";
		$result.=("<tr class=heads><th class='internal'>");
		$result.= $this->locale->getString("mess147");
		$result.=("</th><th class='internal'>");
		$result.= $this->locale->getString("mess148");
		$result.=("</th><th class='internal'>");
		$result.= $this->locale->getString("mess149");
		$result.=("</th><th class='internal' width='350'>");
		$result.= $this->locale->getString("mess150");
		$result.=("</th><th class='internal'>");
		$result.= $this->locale->getString("mess151");
		$result.=("</th></tr><tr>");
		for ($iq11=1; $iq11<$this->getAnswerAmount(); $iq11++){
			if ($nodes[$iq11]['type']){
				if ($nodes[$iq11]['type'] == 1){
					$result.= "<td align='LEFT' class='internal'>".$nodes[$iq11]['nick']."</td>";
				}
				else{
					$result.= "<td align='LEFT' class='internal'>".$this->locale->getString("mess144")."</td>";
				}}
				else
				{
					$result.= "<td align='LEFT' class='internal'></td>";
				}
				$result.=("<td class='internal'>".fd_body(stripslashes($nodes[$iq11]['node']))."</td>");
	
				$result.=("<td align='CENTER' class='internal'>");
				$result.=($nodes[$iq11]['gol']."</td>");
				$result.= "<td class='internal'><TABLE cellSpacing=0 cellPadding=0 width='".round(($nodes[$iq11]['gol']/$nvcs)*300,0)."' border=0><TR><TD align=left bgColor=red><FONT size=-3>&nbsp;</FONT></TD></TR></TABLE>";
				$result.=("</td>");
				$result.=("<td class='internal'>");
				$result.=(round(($nodes[$iq11]['gol']/$nvcs)*100, 2))."%";
				$result.=("</td></tr>");
	
		}
		$result.= "</table>";
		if (isset($_SESSION['idu']) && $this->isUserVote()){
			$result.= "<form method=\"POST\" action=\"delvoice.php\" align=\"CENTER\">";
			$result.= "<input type=hidden name=\"IDU\" size=\"20\" value=\"".$_SESSION['idu']."\">";
			$result.= "<input type=hidden name=\"AUT\" size=\"20\" value=\"".$_SESSION['autor']."\">";
			$result.= "<input type=hidden name=\"IDT\" size=\"20\" value=\"".$this->idThread."\">";
			if (isset($_SESSION['pass2'])) {
				$result.= "<input type=hidden name=\"PS2\" size=\"20\" value=\"".$_SESSION['pass2']."\">";
			}
			else {
				$result.= "<input type=hidden name=\"PS1\" size=\"20\" value=\"".$_SESSION['pass1']."\">";
			}
	
			$result.= "<input type='submit' value='".$this->locale->getString("mess161")."'>";
			$result.= "</form>";
		}
		$result.=("</td></tr>");
		return $result;
	}
}
?>