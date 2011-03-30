<?
/* Функции*/
//include("bbcode.php");
include("smiles.php");
include("href.php");
//include("cenz.php");
include("head.php");
include("body.php");
include("bbcode.php");
include("form_add.php");
include("button.php");
?>
<html>
   <head>
      <meta http-equiv='content-type' content='text/html; charset=windows-1251'>      
      <?
      /* Стили*/
      include('style.php');
      /*Скрипты (смайлики)*/
      if (strpos($_SERVER['HTTP_USER_AGENT'], "MSIE 5.0")){
         include('smile_ie5.php');
      }
      else {
         include('smile_.php');
      }
      /*Скрипты (автовставка тегов)*/
      include('jstags.php');
      /*Скрипты (добавление вариантов ответов)*/
      include('js/jsnode.php');
      /*Скрипты (submit поста)*/
	   include('js/quest_submit.php');
      echo '<link rel="icon" href="/favicon.ico" type="image/x-icon">';
      echo '<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">';
      ?>   
      <title>
      </title>
   </head>
   <body bgcolor=#EFEFEF>
      <table class="content">
         <tr class=heads>
            <td  class=internal>
               <?/*Тема*/?>
               <div class=nik>
                  <b>&nbsp;&nbsp;<?echo(fd_smiles(stripslashes($_POST['T'])));?></b>
               </div>
            </td>
         </tr>
         <tr>
            <td class='matras'>
                  <?/*Ник*/?>
               <span class='tbtextnread'>
                  <? echo(htmlspecialchars($n1));?>
               </span>
               &nbsp;<? echo(chr(149));?>
                  <?/*Дата*/?>
               &nbsp;
               <img border='0' src='smiles/icon_minipost.gif'>
               &nbsp;
               <span class='posthead'><? echo($rgtime);?></span>
               &nbsp;<? echo(chr(149));?>
                  <?/*Хост*/?> 
               <?
               if (trim($str_ip)==trim($str_dom)){
                  $str_dom=substr($str_dom, 0, strrpos($str_dom, '.')+1).'---';
               }else {
                  $str_dom='---'.substr($str_dom, strpos($str_dom, '.'));
               }
               if ($str_hip) $str_dom='---.ua';?>
               &nbsp;
               <span class='posthead'>
                  <? echo($str_dom);?>
               </span>&nbsp;
                  <?/*игнорировать*/?>
               &nbsp;<? echo(chr(149));?>
               <span class="posthead">
                  <? echo($_mess68);?>
               </span>
            </td>
         </tr>
         <tr>
            <td>
                  <?/* div для игнора*/?>
               <div>
                     <?/*Аватара*/?>
                  <table width='100%'>
                     <tr>
                        <td valign=top class='matras' style='padding:10px;'>
                           <div>
                              <?/* if ($str_s_avatar and $str_ok_avatar and trim($str_avatar)<>"" and $str_v_avatars){
                                 <a href='control.php?id=9'><img border='0' src='".$str_avatar."' rel=\"nofollow\"></a>
                              else{*/?>
                                 <img border='0' src='smiles/no_avatar.gif'>
                              <?/*}*/?>
                           </div>
                        </td>
                        <td valign='top' width='100%'>
                           <table width='100%'>
                              <?include("quest_view_opr.php");?>
                              <tr>
                                 <td>
                                       <?/* Выводим текст*/?>
                                    <p class=post><? echo(nl2br(fd_smiles(fd_bbcode(stripslashes($_POST['A2'])))));?></p>
                                 </td>
                              </tr>
                           </table>
                        </td>
                     </tr>
                  </table>
               </div>
            </td>
         </tr>
         <? include("menu.php");?>
         <tr>
            <td>
               <table>
                  <tr>
                     <td>
                        <form method="POST" name="post" action="quest.php">
                        	<table>
                        		<tr>
                        			<td>
                        				<table>
                        					<tr>
                        						<td>
									   					<?echo($_mess59.":&nbsp");?>
									   				</td>
									   				<td>
									   					<input type="text" class='mnuforumSm' name="T" size="120" value="<?echo(htmlspecialchars(stripslashes($_POST['T'])))?>">
									   				</td>
				                        	</tr>
            				            	<tr>
                        						<td>
									   					<?echo($_mess124.":&nbsp");?>
									   				</td>
									   				<td>
									   					<input type="text" class='mnuforumSm' name="Q" size="120" value="<?echo(htmlspecialchars(stripslashes($_POST['Q'])))?>">
			                        			</td>
         			               		</tr>
         			               	</table>
                        			</td>
                        		</tr>
                        		<tr>
                        			<td>
                        			   <table id=tbl_node>
                                       <?
                                       $x1=1;
                                       $x1_1=1;
                                       while (isset($_POST['P'.$x1])){
                                       	if (trim($_POST['P'.$x1])!=="")
                                       	{
                              		         ?>       
                              		         <tr>
                              			         <td>
                              				         <?echo($x1_1);?>. <input type="text" class='mnuforumSm' value="<?echo(htmlspecialchars(stripslashes($_POST['P'.$x1])));?>" name="P<?echo($x1_1);?>" id="P<?echo($x1_1);?>" size="100">
                              			         </td>
                              		         </tr>
                                          	<?
	                                          $x1_1+=1;
                                       	}
                                          $x1+=1;
                                       }?>
                                 		</table>
                                 		</td>
                                 		</tr>
                                 		<tr>
                                 			<td>
			         		                  	<?echo(fd_button($_mess126,"add_node();","btn_add", "1", "", "", "", ""))?>
                                 				<input type="hidden" id="kol" name="kol" value="<?echo($x1_1-1);?>">
                                 			</td>
                                 		</tr>
                                 		<tr>
                                 			<td>
		      	               					<input type="checkbox" name="US" checked>
      		   							   		<?echo($_mess125);?>
                                 			</td>
                                 		</tr>
                                       <tr>
                  								<?/*Смайлики заголовок*/?>
                     							<td width='100%'>
                     								<table width='100%'>
                     									<tr>
                     										<td align='center'>
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
                        									<td valign='TOP'>
                       	   									<?/*Смайлики*/?>
                             									<?include("smiles_add.php");?>
                     									   </td>
                       										<td align='CENTER' valign='top'>
                     											<?/*Автотеги*/
                        	   								include("autotags_add.php");
                                                      /*текстарий*/?>
                                                      <p>
                                                         <textarea class='mnuforumSm' rows='30' id='ed1' name='A2' cols='55'><? echo(htmlspecialchars(stripslashes($_POST['A2'])))?></textarea>
                                                      </p>
                                                		<?/*Кнопки*/?>
                             									<table>
                             										<tr>
                             											<td>
                     											         <?echo(fd_button($_mess13,"quest_submit(\"write\");","B1", "1", "", "", "", ""))?>
                             											</td>
                             											<td>
                     											         <?echo(fd_button($_mess63,"quest_submit(\"view\");","B3", "1", "", "", "", ""))?>
                             											</td>
                             										</tr>
                             									</table>
                                                      <?/*Прередаем нужные пераметры...*/?>
                             									<?echo(fd_form_add());?>
                                                   </td>
                                                </tr>
                                             </table>
                                          </td>
                                       </tr>
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