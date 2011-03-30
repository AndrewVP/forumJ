<?php
include_once 'classes/ua/com/diletant/util/time.php';

class Thread{

	/**
	 * Локализация
	 * @var LocaleString
	 */
	private $locale;
	
	/**
	 * Тип прикрепления
	 * @var 
	 */
	private $str_dock;
	
	/**
	 * Заголовок
	 * @var 
	 */
	private $str_head;
	
	/**
	 * Автор
	 * @var 
	 */
	private $str_nick;
	
	/**
	 * Время открытия
	 * @var 
	 */
	private $str_reg;
	
	/**
	 * id ветки
	 * @var 
	 */
	private $str_id;
	
	/**
	 * время последнего поста
	 * @var 
	 */
	private $str_lpt;
	
	/**
	 * id Автора последнего поста
	 * @var 
	 */
	private $str_lpus;
	
	/**
	 * Ник автора последнего поста
	 * @var 
	 */
	private $str_lpn;
	
	/**
	 * Количество постов в ветке
	 * @var 
	 */
	private $str_pcount;
	
	/**
	 * Количество просмотров участников
	 * @var 
	 */
	private $str_snid;
	
	/**
	 * Количество просмотров полное
	 * @var 
	 */
	private $str_snall;
	
	/**
	 * Id последнего поста в ветке
	 * @var 
	 */
	private $idLastPost;
	
	/**
	 * Тип ветки
	 * @var 
	 */
	private $str_type;
	
	/**
	 * Папка
	 * @var 
	 */
	private $str_folder;
	
	/**
	 * Дизайн
	 * @var 
	 */
	private $disain;
	
	/**
	 * Авторизван ли посетитель
	 * @var 
	 */
	private $login;
	
	/**
	 * Количество постов на странице
	 * @var 
	 */
	private $pt;
	
	/**
	 * Текущая страница
	 * @var 
	 */
	private $pg;
	
	/**
	 * номер позиции
	 * @var 
	 */
	private $i;
	
	/**
	 * Возвращает авторизван ли посетитель
	 *
	 * @return unknown
	 */
	private function isLogin(){
		return $this->login;
	}
	
	/**
	 * Конструктор
	 *
	 * @param unknown_type $arrFetch
	 * @param LocaleString $locale
	 */
	public function __construct($arrFetch, 
											LocaleString $locale,
											$disain,
											$isLogin,
											$pg,
											$pt,
											$i){
		// Присваиваем локализованные сообщения
		$this->locale = $locale;
		//
		$this->str_dock = $arrFetch['dock'];
		//
		$this->str_head = $arrFetch['head'];
		//
		$this->str_nick = $arrFetch['nick'];
		//
		$this->str_reg = $arrFetch['reg_'];
		//
		$this->str_id = $arrFetch['id'];
		//
		$this->str_lpt = $arrFetch['lposttime_'];
		//
		$this->str_lpus = $arrFetch['lpostuser'];
		//
		$this->str_lpn = $arrFetch['lpostnick'];
		//
		$this->str_pcount = $arrFetch['npost']-1;
		//
		$this->str_snid = $arrFetch['seenid'];
		//
		$this->str_snall = $arrFetch['seenall'];
		//
		$this->idLastPost = $arrFetch['id_last_post'];
		//
		$this->str_type = $arrFetch['type'];
		//
		$this->str_folder = $arrFetch['_flname'];
		//
		$this->disain = $disain;
		//
		$this->pg = $pg;
		//
		$this->pt = $pt;
		//
		$this->login = $isLogin;
		//
		$this->i = $i;
	}
	
	public function toString(){
		$result = "";
      if ($this->disain==1) { 
         $result.="<tr class=trees >";
      }else {
         $result.="<tr class=matras>";   
      }
		// Картинки
		// Пиктограммка опроса
      $result.="<td width='10' align='center' style='padding:0px 5px 0px 5px'>";
      if ($this->str_type==1 or $this->str_type==2){
         $result.="<img border='0' src='smiles/quest.gif'>";
      }else{
         if ($this->str_dock==5){
            $result.="<img border='0' src='smiles/icon4.gif'>";
         }elseif($this->str_dock==10) {
            $result.="<img border='0' src='picts/f_pinned.gif'>";
         }else {
            $result.="<img border='0' src='smiles/icon1.gif'>";
         }
      }
      $result.="</td>";
      $result.="<td width='1'></td>";
		// Тема
      $result.="<td><p>";
      $str_head=htmlspecialchars(stripslashes($this->str_head));
		// Добавляем смайлики
      $str_head=fd_head($str_head);
		// Опрос? Добавляем "метку"
      if ($this->str_type==1 or $this->str_type==2) $str_head="<b>".$this->locale->getString("mess9")."</b> ".$str_head;
		// Подписываем прикрепленные
      switch ($this->str_dock){
         case 10:
              $result.="<font class=trforum><b>".$this->locale->getString("mess7")." </b><a href='tema.php?id=".$this->str_id."'>".$str_head."</a></font>";
              break;
         case 5:
              $result.="<font class=trforum><b>".$this->locale->getString("mess8")." </b><a href='tema.php?id=".$this->str_id."'>".$str_head."</a></font>";
              break;
         case 3:
              $result.="<font class=trforum><b>".$this->locale->getString("mess163")." </b><a href='tema.php?id=".$this->str_id."'>".$str_head."</a></font>";
              break;
         case 0:
              $result.="<font class=trforum><a href='tema.php?id=".$this->str_id."'>".$str_head."</a></font>";
              break;
         }
		// Cсылки на страницы в ветке
      if ($this->str_pcount+1>$this->pt) {
         $result.="<br><font size=1>".$this->locale->getString("mess10").":&nbsp";
         $k1=0;
         $k2=0;
         for ($k=1; $k<=ceil(($this->str_pcount+1)/$this->pt); $k++) {
            $k1=$k1+1;
            if ($k1==10){
               $result.="<a href='tema.php?page=".$k."&id=".$this->str_id."'>".$k."</a>";
               if ($k<>ceil(($this->str_pcount+1)/$this->pt)) $result.=",&nbsp;&nbsp;";
               $k1=0;
               $k2=$k2+1;
            }
            if ($k==1){
               $result.="<a href='tema.php?page=".$k."&id=".$this->str_id."'>".$k."</a>,&nbsp;&nbsp;";
            }
            if ((ceil(($this->str_pcount+1)/$this->pt)-$k2*10)< 10 and ($k-$k2*10) != 0 and $k!=1){
               $result.="<a href='tema.php?page=".$k."&id=".$this->str_id."'>".$k."</a>";
               if ($k<>ceil(($this->str_pcount+1)/$this->pt)) $result.=",&nbsp;&nbsp;";
            }
            
         }
         $result.="</font>";
      }
      $result.="</p></td>";
		// Количество постов
      $result.="<td width='20' align='center' valign='middle'><span class='mnuforum' style='{color: purple}'>".$this->str_pcount;
      $result.="</span><span id='posts".$this->str_id."' class='mnuforum' style='{color: red}'>&nbsp</span></td>";
		// кол-во просмотров
      $result.="<td width='80' align='center' valign='middle'>";
		// Количество просмотров участников
      $result.='<div class="mnuforum"><font size="1" color="green">'.$this->str_snid.'</font><br>';
		// Количество просмотров всего
      $result.='<font size="1" color="purple">'.$this->str_snall.'</font></div></td>';
		// Автор
      $result.="<td width='120' align='center' valign='middle'><div class='trforum'><font size='1'>".htmlspecialchars($this->str_nick)."</font></div>";
		// Время создания
      $result.="</td>";
		// Автор последнего поста
      $result.="<td width='120' align=center><div class='mnuforum'><font size='1'>".htmlspecialchars($this->str_lpn)."</font></div>";
		// Время последнего поста
      $result.='<div class="mnuforum"><a href="tema.php?id='.$this->str_id.'&end=1#end" rel="nofollow"><font size="1">'.substr($this->str_lpt, 0, 5).'&nbsp;'.substr($this->str_lpt, 6, 5).'</font></a></div>';
      $result.="</td>";
      // Папка
      $result.="<td align='center' valign='middle'>";
      $result.="<div class='mnuforum'><font size='1'>".$this->str_folder."</font></div>";
      $result.="</td>";
      // Флажок (только для зарегистрированых)
      if ($this->isLogin()){
         $result.="<td align='center' valign='middle'>";
         $result.="<input type='checkbox' id='ch".$this->i."' name='".$this->i."' value='".$this->str_id."'>";
         $result.="</td>";
         $result.="<td style='padding:0px 5px 0px 5px' align='right'>";
         $result.="<a href='delone.php?id=".$this->str_id."&usr=".$_SESSION['idu']."&page=".$this->pg."'><img border='0' src='picts/del1.gif'></a>";
         $result.="</td>";
      }
      return $result;
	}
}
?>