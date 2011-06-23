<?php
class Menu{
	var $menuType;
	var $localLang;
	var $user;
	function Menu($menuType, $localLang, $user){
		$this->$menuType=$menuType;
		$this->localLang=$localLang;
		$this->user=$user;
	}
	function setUser($user){
		$this->user=$user;
	}
	function getUser(){
		return $this->user;
	}
	function setLang($localLang){
		$this->localLang=$localLang;
	}
	function getLang(){
		return $this->localLang;
	}
	function getMenuType(){
		return $this->menuType;
	}
	function echoMenu(){
		// Определяем ссылки для смены языка
		$ref=$_SERVER['PHP_SELF'];
		if($_SERVER['QUERY_STRING']=="")
		{
			$ref=$ref."?";
		}elseif(!strpos(" ".$_SERVER['QUERY_STRING'], 'lang=')){
			$ref=$ref."?".$_SERVER['QUERY_STRING']."&";
		}elseif(strpos($_SERVER['QUERY_STRING'], 'lang=')==0){
			if(strlen($_SERVER['QUERY_STRING'])>7)
			{
				$ref=$ref."?".substr($_SERVER['QUERY_STRING'], 8)."&";
			}else{
				$ref=$ref."?";
			}
		}else{
			$ref=$ref."?".substr($_SERVER['QUERY_STRING'], 0, strpos($_SERVER['QUERY_STRING'], 'lang=')-1).substr($_SERVER['QUERY_STRING'], strpos($_SERVER['QUERY_STRING'], 'lang=')+7)."&";
		}
		$ukr=$ref.'lang=ua';
		$rus=$ref.'lang=ru';
		$echoResult="";
		$echoResult.="
		<tr>
		   <td>
		      <table class=control>
		         <tr>
		            <td class=leftTop></td>
		            <td class=top colspan=2></td>
		            <td class=rightTop></td>
		         </tr>
		         <tr class=heads>
		            <td class=left></td>
		";
		/*Логин есть?*/
		if (!$this->getUser()==''){
			/*
			Если Гость
			Авторизуемся id: код (1-Просто вход с "темы"), gid: номер темы, pg: номер страницы
			*/
			$echoResult.="<td class=bg align='LEFT'>";
			if ($this->getMenuType()!=1){
				/*Список тем*/
				$echoResult.="<img src='picts/index.gif' border='0' class='menuImg'>";
            $echoResult.="<a class=mnuforumSm href='index.php'>";
           	$echoResult.=$_mess135;
           	$echoResult.="</a>";
			}
			/*Новая тема*/
       	$echoResult.="<img src='picts/new_top.gif' border='0' class='menuImg'>";
        	$echoResult.="<a class=mnuforumSm href='auth.php?id=".$this->getMenuType()."' rel='nofollow'>";
			$echoResult.=$_mess4;
			$echoResult.="</a>";
			/*Новый опрос*/
        	$echoResult.="<img src='picts/new_quest.gif' border='0' class='menuImg'>";
			$echoResult.="<a class=mnuforumSm href='auth.php?id=".$this->getMenuType()."' rel='nofollow'>";
			$echoResult.=$_mess3;
			$echoResult.="</a>";
			/*Поиск*/
        	$echoResult.="<img src='picts/new_search.gif' border='0' class='menuImg'>";
			$echoResult.="<a class=mnuforumSm href='search.php' rel='nofollow'>";
			$echoResult.=$_mess30;
			$echoResult.="</a>";
			/*Вход*/
        	$echoResult.="<img src='picts/key_add.gif' border='0' class='menuImg'>";
			$echoResult.="<a class=mnuforumSm href='auth.php?id=1' rel='nofollow'>";
			$echoResult.=$_mess1;
			$echoResult.="</a>";
			/*Регистрация*/
        	$echoResult.="<img src='picts/new_user.gif' border='0' class='menuImg'>";
			$echoResult.="<a class=mnuforumSm href='reg.php?id=1' rel='nofollow'>";
			$echoResult.=$_mess2;
			$echoResult.="</a>";
			$echoResult.="</td>";
			$echoResult.="<td class=bg align='right'>";
         /*Укр. интерфейс*/
         $echoResult.="<a class=mnuforumSm href='".$ukr."' rel='nofollow'>";
         $echoResult.="Українська";
         $echoResult.="</a>";
         $echoResult.=chr(149);
         /*Рус. интерфейс*/
         $echoResult.="<a class=mnuforumSm href='".$rus."' rel='nofollow'>";
         $echoResult.="Русский";
         $echoResult.="</a>";
         $echoResult.="</td>";
      }else{
         /*Если логин есть*/
         $echoResult.="<td class=bg align='LEFT'>";
          /*Ник*/
        	$echoResult.="<img src='picts/nick.gif' border='0' class='menuImg'>";
         $echoResult.="<span class=nik>";
         $echoResult.=$this->getUser()->getNick();
         $echoResult.="</span>";
         if ($this->getMenuType()!=1){
	  		   /*Список тем*/
	        	$echoResult.="<img src='picts/index.gif' border='0' class='menuImg'>";
	         $echoResult.="<a class=mnuforumSm href='index.php'>";
	        	$echoResult.=$_mess135;
	        	$echoResult.="</a>";
        	}
         /*Новая тема*/
        	$echoResult.="<img src='picts/new_top.gif' border='0' class='menuImg'>";
         $echoResult.="<a class=mnuforumSm href='mess.php' rel='nofollow'>";
         $echoResult.=$_mess4;
         $echoResult.="</a>";
         /*Новый опрос*/
        	$echoResult.="<img src='picts/new_quest.gif' border='0' class='menuImg'>";
         $echoResult.="<a class=mnuforumSm href='opr.php' rel='nofollow'>";
         $echoResult.=$_mess3;
         $echoResult.="</a>";
          /*Поиск*/
        	$echoResult.="<img src='picts/new_search.gif' border='0' class='menuImg'>";
         $echoResult.="<a class=mnuforumSm href='search.php' rel='nofollow'>";
         $echoResult.=$_mess30;
         $echoResult.="</a>";
         /* Личные настройки*/
       	$echoResult.="<img src='picts/profile.gif' border='0' class='menuImg'>";
         $echoResult.="<a class=mnuforumSm href='control.php' rel='nofollow'>";
         $echoResult.=$_mess31;
         $echoResult.="</a>";
         /* Переписка*/
        	$echoResult.="<img src='picts/email.gif' border='0' class='menuImg'>";
         $echoResult.="<a class=mnuforumSm href='control.php?id=2' rel='nofollow'>";
         $echoResult.=$_mess23;
         $echoResult.="</a>";
         /* Выход*/
         $ref=$_SERVER['PHP_SELF']."?".$_SERVER['QUERY_STRING']."&exit=0";
        	$echoResult.="<img src='picts/key_delete.gif' border='0' class='menuImg'>";
         $echoResult.="<a class=mnuforumSm href='".$ref."' rel='nofollow'>";
         $echoResult.=$_mess6;
         $echoResult.="</a>";
         $echoResult.="</td>";
         /* Укр. интерфейс*/
         $echoResult.="<td class=bg align='right'>";
         $echoResult.="<a class=mnuforumSm href='".$ukr.">' rel='nofollow'>";
         $echoResult.="Українська";
         $echoResult.="</a>";
         $echoResult.=chr(149);
         /* Рус. интерфейс*/
         $echoResult.="<a class=mnuforumSm href='".$rus."' rel='nofollow'>";
         $echoResult.="Русский";
         $echoResult.="</a>";
         $echoResult.="</td>";
      }
      $echoResult.="   
		         <td class=right></td>
		         </tr>
		         <tr>
		            <td class=leftBtm></td>
		            <td class=btm colspan=2></td>
		            <td class=rightBtm></td>
		         </tr>
		      </table>
		   </td>
		</tr>		
		";		
		return $echoResult;		
	}
}
?>