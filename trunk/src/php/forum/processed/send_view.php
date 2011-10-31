<?
include("form_add.php");
include("button.php");
include("bbcode.php");
include("smiles.php");
include("cenz.php");
include("head.php");
include("body.php");
$str_head=$_POST['NHEAD'];
/*Тело.*/
$str_body=$_POST['A2'];
include("lang.php");
?>
<html>
   <head>
      <meta http-equiv='content-type' content='text/html; charset=windows-1251'>
      <?/*Стили*/
      include('style.php');
      /*Скрипты (смайлики)*/
      if (strpos($_SERVER['HTTP_USER_AGENT'], "MSIE 5.0")){
         include('smile_ie5.php');
      }else {
         include('smile_.php');
      }
      /*Скрипты (автовставка тегов)*/
      include('jstags.php');
      /*Скрипты (submit поста)*/
      include('js/send_submit.php');
      ?>
      <link rel="icon" href="/favicon.ico" type="image/x-icon">
      <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
      <title>
         <?echo($_mess127);?>
      </title>
   </head>
   <body bgcolor=#EFEFEF>
      <table border='0' style='border-collapse: collapse' width='100%'>
         <?/*Шапка*/?>
         <tr>
            <td>
               <table class='control'>
                  <tr class=heads>
                     <td colspan=5 class=internal>                                 
                        <span class=tbtext>
                           <?echo($_mess61);?>
                           :&nbsp;
                           <?echo($lptime);?>
                           &nbsp;
                           <?echo($_mess58);?>
                           :&nbsp;
                        </span>
                        <span class=nick>
                           <?echo($n1);?>
                        </span>
                        <span class=tbtext>
                           &nbsp;
                           <?echo($_mess19);?>
                           :&nbsp;
                        </span>
                        <span class=nick>
                           <?echo(htmlspecialchars(stripslashes($_POST['RCVR'])));?>
                        </span>
                     </td>
                  </tr>
                  <tr>
                     <td colspan=5 class=internal>
                        <?/*Заголовок.*/?>
                        <div class=nik><?echo(fd_head($str_head));?></div>
                        <?/*Тело.*/?>
                        <div class=post><?echo(fd_body($str_body));?></div>
                     </td>
                  </tr>
               </table>
            </td>
         </tr>
         <?include("menu.php");?>

         <tr>
            <td>
               <table>
               <tr>
                  <td>
                     <form name='post' action='send.php' method='POST'>
                        <table width='100%'>
                           <tr>
                              <td width='100%'>
                                 <table width='100%'>
                                    <?/*От*/?>
                                    <tr>
                                       <td align='LEFT'>
                                          <div class=mnuprof>
                                             <?echo($_mess58);?>&nbsp;
                                          </div>
                                       </td>
                                       <td>
                                          <div class=mnuprof>
                                             <?echo($_SESSION['autor']);?>
                                          </div>
                                       </td>
                                    </tr>
                                    <?/*Кому*/?>
                                    <tr>
                                       <td align='LEFT'>
                                          <div class=mnuprof>
                                             <?echo($_mess28);?>&nbsp;
                                          </div>
                                       </td>
                                       <td colspan='2'>
                                          <input type=text class='mnuforumSm' value='<?echo(htmlspecialchars(stripslashes($_POST['RCVR'])))?>' name='RCVR' size='30'>
                                       </td>
                                    </tr>
                                    <?/*Тема*/?>
                                    <tr>
                                       <td align='LEFT'>
                                          <div class=mnuprof>
                                             <?echo($_mess59);?>&nbsp;
                                          </div>
                                       </td>
                                       <td>
                                          <input type=text class='mnuforumSm' name='NHEAD' value='<?echo(htmlspecialchars(stripslashes($_POST['NHEAD'])))?>' size='100'>
                                       </td>
                                    </tr>
                                 </table>
                              </td>
                           </tr>
                           <tr>
                              <td>
                                 <table>
                                    <tr>
                                       <?/*Смайлики заголовок*/?>
                                       <td width='400' align='CENTER'>
               									<p>
               										<?echo($_mess21.":");?>
               									</p>
                                       </td>
                                          <?/*Приглашение*/?>
                                       <td align='CENTER'>
                                          <p><? echo($_mess12);?></p>
                                       </td>
                                    </tr>
                                       <?/*Пост*/?>
                                    <tr>
                                       <td valign='TOP' width='100%' height='100%'>
               									<?/*Смайлики*/?>
               									<?include("smiles_add.php");?>
                                       </td>
                                       <td width='500' align='CENTER' valign='top'>
	            									<?/*Автотеги*/
   	         									include("autotags_add.php");
      	                                 /* текстарий*/?>
         	                              <p>
            	                              <textarea rows='20' class='mnuforumSm' id='ed1' name='A2' cols='55'><?echo(htmlspecialchars(stripslashes($_POST['A2'])))?></textarea>
               	                        </p>
               	                  		<?/*Кнопки*/?>
               									<table>
               										<tr>
               											<td>
            										         <?echo(fd_button($_mess13,"send_submit(\"write\");","B1", "1", "", "", "", ""))?>
               											</td>
               											<td>
            										         <?echo(fd_button($_mess63,"send_submit(\"view\");","B3", "1", "", "", "", ""))?>
               											</td>
               										</tr>
               									</table>
                                          <?/*Прередаем нужные пераметры...*/?>
                 									<?echo(fd_form_add());?>
	                                    </td>
                                    </table>
                                 </td>
                              </tr>
                           </table>
                        </form>
                     </td>
                  </tr>
               </table>
            </td>
         </tr>
      </table>
   </body>
</html>
<?mysql_close($conn);?>   