<?
if ($gid>1 and $gid<6)
{
	  /*����� ������ �����*/
?>
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
      									<?/*��*/?>
      									<tr>
      										<td align='LEFT'>
      											<div class=mnuprof>
      												<?echo($_mess58."&nbsp;");?>
      											</div>
      										</td>
         									<td>
         										<div class=mnuprof>
         											<?echo($_SESSION['autor']);?>
         										</div>
         									</td>
         								</tr>
         								<?/*����*/?>
         								<tr>
         									<td align='LEFT'>
         										<div class=mnuprof>
         											<?echo($_mess28."&nbsp;");?>
         										</div>
         									</td>
            								<td colspan='2'>
                        					<?
                        					if (isset($_SESSION['error'])) 
                        					{?>
                           					<input type=text class='mnuforumSm' value='<?echo(htmlspecialchars(stripslashes($_SESSION['rcvr'])));?>' name='RCVR' size='30'>
      					                     <?
      					                     if ($_SESSION['error']==1)
      					                     {?>
      					                        <span class=hdforum>
      					                        	<font color='red'>
      					                        		<?echo($_mess65);?>
      					                        	</font>
      					                        </span>
      						                  <?}?>
                        					<?}else{?>
                           					<input type=text class='mnuforumSm' name='RCVR' size='30'>
                        					<?}?>
                        				</td>
                        			</tr>
                        			<?/*����*/?>
                        			<tr>
                        				<td align='LEFT'>
                        					<div class=mnuprof>
                        						<?echo($_mess59."&nbsp;");?>
                        					</div>
                        				</td>
                           			<td>
                                       <?if (isset($_SESSION['error'])) 
                                       {?>
                                          <input type=text class='mnuforumSm' name='NHEAD' value='<?echo(htmlspecialchars(stripslashes($_SESSION['head'])));?>' size='100'>
                                       <?}else{?>
                                          <input type=text class='mnuforumSm' name='NHEAD' size='100'>
                                       <?}?>
                           			</td>
                           		</tr>
                           	</table>
                           </td>
                        </tr>
                        <tr>
                           <td>
                           	<table>
                           		<tr>
                                    <?/*�������� ���������*/?>
                                    <td width='400' align='CENTER'>
            									<p>
            										<?echo($_mess21.":");?>
            									</p>
                                    </td>
                                       <?/*�����������*/?>
                                    <td align='CENTER'>
                                       <p><? echo($_mess12);?></p>
                                    </td>
                                 </tr>
                                    <?/*����*/?>
                                 <tr>
                                    <td valign='TOP' width='100%' height='100%'>
            									<?/*��������*/?>
            									<?include("smiles_add.php");?>
                                    </td>
                                    <td width='500' align='CENTER' valign='top'>
         									<?/*��������*/
         									include("autotags_add.php");
         			                  /*���������*/?>
                              		<p>
                                       <?
                                       $textarea="";
                                       if (isset($_SESSION['error'])) {
                                          $textarea=htmlspecialchars(stripslashes($_SESSION['body']));
                                          unset($_SESSION['error']); 
                                       }
                                       ?>
                              			<textarea class='mnuforumSm' rows='20' id='ed1' name='A2' cols='55'><?echo($textarea);?></textarea>
                              		</p>
            	                  		<?/*������*/?>
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
                                          <?/*��������� ������ ���������...*/?>
              									<?echo(fd_form_add());?>
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
<?}?>