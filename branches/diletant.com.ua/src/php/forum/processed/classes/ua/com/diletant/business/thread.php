<?php
include_once 'classes/ua/com/diletant/util/time.php';

class Thread{

	/**
	 * �����������
	 * @var LocaleString
	 */
	private $locale;
	
	/**
	 * ��� ������������
	 * @var 
	 */
	private $str_dock;
	
	/**
	 * ���������
	 * @var 
	 */
	private $str_head;
	
	/**
	 * �����
	 * @var 
	 */
	private $str_nick;
	
	/**
	 * ����� ��������
	 * @var 
	 */
	private $str_reg;
	
	/**
	 * id �����
	 * @var 
	 */
	private $str_id;
	
	/**
	 * ����� ���������� �����
	 * @var 
	 */
	private $str_lpt;
	
	/**
	 * id ������ ���������� �����
	 * @var 
	 */
	private $str_lpus;
	
	/**
	 * ��� ������ ���������� �����
	 * @var 
	 */
	private $str_lpn;
	
	/**
	 * ���������� ������ � �����
	 * @var 
	 */
	private $str_pcount;
	
	/**
	 * ���������� ���������� ����������
	 * @var 
	 */
	private $str_snid;
	
	/**
	 * ���������� ���������� ������
	 * @var 
	 */
	private $str_snall;
	
	/**
	 * Id ���������� ����� � �����
	 * @var 
	 */
	private $idLastPost;
	
	/**
	 * ��� �����
	 * @var 
	 */
	private $str_type;
	
	/**
	 * �����
	 * @var 
	 */
	private $str_folder;
	
	/**
	 * ������
	 * @var 
	 */
	private $disain;
	
	/**
	 * ���������� �� ����������
	 * @var 
	 */
	private $login;
	
	/**
	 * ���������� ������ �� ��������
	 * @var 
	 */
	private $pt;
	
	/**
	 * ������� ��������
	 * @var 
	 */
	private $pg;
	
	/**
	 * ����� �������
	 * @var 
	 */
	private $i;
	
	/**
	 * ���������� ���������� �� ����������
	 *
	 * @return unknown
	 */
	private function isLogin(){
		return $this->login;
	}
	
	/**
	 * �����������
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
		// ����������� �������������� ���������
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
		// ��������
		// ������������ ������
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
		// ����
      $result.="<td><p>";
      $str_head=htmlspecialchars(stripslashes($this->str_head));
		// ��������� ��������
      $str_head=fd_head($str_head);
		// �����? ��������� "�����"
      if ($this->str_type==1 or $this->str_type==2) $str_head="<b>".$this->locale->getString("mess9")."</b> ".$str_head;
		// ����������� �������������
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
		// C����� �� �������� � �����
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
		// ���������� ������
      $result.="<td width='20' align='center' valign='middle'><span class='mnuforum' style='{color: purple}'>".$this->str_pcount;
      $result.="</span><span id='posts".$this->str_id."' class='mnuforum' style='{color: red}'>&nbsp</span></td>";
		// ���-�� ����������
      $result.="<td width='80' align='center' valign='middle'>";
		// ���������� ���������� ����������
      $result.='<div class="mnuforum"><font size="1" color="green">'.$this->str_snid.'</font><br>';
		// ���������� ���������� �����
      $result.='<font size="1" color="purple">'.$this->str_snall.'</font></div></td>';
		// �����
      $result.="<td width='120' align='center' valign='middle'><div class='trforum'><font size='1'>".htmlspecialchars($this->str_nick)."</font></div>";
		// ����� ��������
      $result.="</td>";
		// ����� ���������� �����
      $result.="<td width='120' align=center><div class='mnuforum'><font size='1'>".htmlspecialchars($this->str_lpn)."</font></div>";
		// ����� ���������� �����
      $result.='<div class="mnuforum"><a href="tema.php?id='.$this->str_id.'&end=1#end" rel="nofollow"><font size="1">'.substr($this->str_lpt, 0, 5).'&nbsp;'.substr($this->str_lpt, 6, 5).'</font></a></div>';
      $result.="</td>";
      // �����
      $result.="<td align='center' valign='middle'>";
      $result.="<div class='mnuforum'><font size='1'>".$this->str_folder."</font></div>";
      $result.="</td>";
      // ������ (������ ��� �����������������)
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