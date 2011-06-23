<?php
// Определяем ссылки для смены языка
if (isset($request)){
	$lang = $request->GetParameterValue("lang");
}elseif(isset($_GET["lang"])){
	$lang = $_GET["lang"]; 
}else{
	$lang = "ua";
}
include_once 'lang.php';
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
?>
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
         <?php /*Логин есть?*/
         if (!isset($_SESSION['autor'])){
            /*
            Если Гость
            Авторизуемся id: код (1-Просто вход с "темы"), gid: номер темы, pg: номер страницы
            */
            ?>

            <td class=bg align='LEFT'>
              <?php
              if ($action!=1)
              {
         		   /*Список тем*/?>
	              	<img src='picts/index.gif' border='0' class='menuImg'>
	               <a class=mnuforumSm href='index.php'>
               	<?php echo($_mess135);?>
               	</a>
            <?php }?>
               <?php /*Новая тема*/?>
              	<img src='picts/new_top.gif' border='0' class='menuImg'>
               <a class=mnuforumSm href='auth.php?id=<?php echo($action);?>' rel='nofollow'>
                  <?php echo($_mess4);?>
               </a>
               <?php /*Новый опрос*/?>
              	<img src='picts/new_quest.gif' border='0' class='menuImg'>
               <a class=mnuforumSm href='auth.php?id=<?php echo($action);?>' rel='nofollow'>
                  <?php echo($_mess3);?>
               </a>
               <?php /*Поиск*/?>
              	<img src='picts/new_search.gif' border='0' class='menuImg'>
               <a class=mnuforumSm href='search.php' rel='nofollow'>
                  <?php echo($_mess30);?>
               </a>
               <?php /*Вход*/?>
              	<img src='picts/key_add.gif' border='0' class='menuImg'>
               <a class=mnuforumSm href='auth.php?id=1' rel='nofollow'>
               <?php echo $_mess1?>
               </a>
               <?php /*Регистрация*/?>
              	<img src='picts/new_user.gif' border='0' class='menuImg'>
               <a class=mnuforumSm href='reg.php?id=1' rel='nofollow'>
               <?php  echo $_mess2?>
               </a>
            </td>
            <td class=bg align='right'>
               <?php /*Укр. интерфейс*/?>
               <a class=mnuforumSm href='<?php echo($ukr);?>' rel='nofollow'>
               Українська
               </a>
               <?php  echo  chr(149)?>
               <?php /*Рус. интерфейс*/?>
               <a class=mnuforumSm href='<?php echo($rus);?>' rel='nofollow'>
               Русский
               </a>
            </td>
         <?php }else{
            /*Если логин есть*/
            ?>   
            <td class=bg align='LEFT'>
               <?php /*Ник*/?>
              	<img src='picts/nick.gif' border='0' class='menuImg'>
               <span class=nik>
               <?php   echo stripslashes(htmlspecialchars($_SESSION['autor']))?>
               </span>
	            <?php 
	            if ($action!=1)
   	         {
         		   /*Список тем*/?>
	              	<img src='picts/index.gif' border='0' class='menuImg'>
	               <a class=mnuforumSm href='index.php'>
               	<?php echo($_mess135);?>
               	</a>
            	<?php }?>
               <?php /*Новая тема*/?>
              	<img src='picts/new_top.gif' border='0' class='menuImg'>
               <a class=mnuforumSm href='mess.php' rel='nofollow'>
                  <?php  echo $_mess4?>
               </a>
               <?php /*Новый опрос*/?>
              	<img src='picts/new_quest.gif' border='0' class='menuImg'>
               <a class=mnuforumSm href='opr.php' rel='nofollow'>
                  <?php  echo $_mess3?>
               </a>
               <?php /*Поиск*/?>
              	<img src='picts/new_search.gif' border='0' class='menuImg'>
               <a class=mnuforumSm href='search.php' rel='nofollow'>
                  <?php  echo $_mess30?>
               </a>
               <?php /* Личные настройки*/?>
              	<img src='picts/profile.gif' border='0' class='menuImg'>
               <a class=mnuforumSm href='control.php' rel='nofollow'>
               <?php  echo $_mess31?>
               </a>
               <?php /* Переписка*/?>
              	<img src='picts/email.gif' border='0' class='menuImg'>
               <a class=mnuforumSm href='control.php?id=2' rel='nofollow'>
               <?php  echo $_mess23?>
               </a>
               <?php /* Выход*/
               $ref=$_SERVER['PHP_SELF']."?".$_SERVER['QUERY_STRING']."&exit=0";
               ?>
              	<img src='picts/key_delete.gif' border='0' class='menuImg'>
               <a class=mnuforumSm href='<?php  echo $ref?>' rel='nofollow'>
               <?php  echo $_mess6?>
               </a>
            </td>
            <?php /* Укр. интерфейс*/?>
            <td class=bg align='right'>
               <a class=mnuforumSm href='<?php echo($ukr);?>' rel='nofollow'>
               Українська
               </a>
               <?php  echo chr(149)?>
               <?php /* Рус. интерфейс*/?>
               <a class=mnuforumSm href='<?php echo($rus);?>' rel='nofollow'>
               Русский
               </a>
            </td>
         <?php }?>
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