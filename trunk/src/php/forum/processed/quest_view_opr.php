<tr>
   <td>
      <?/*Вопрос*/?>
      <p align="CENTER">
         <font size=4>
            <b>
               <? echo(fd_smiles(stripslashes($_POST['Q'])));?>
            </b>
         </font>
      </p>
      <br>
   </td>
</tr>
<tr>
   <td align="CENTER">
      <table>
         <tr>
            <td align="CENTER">
               <table class=content>
                  <?
                  $x2=1;
                  while (isset($_POST['P'.$x2])){
                     $check="";
                     if ($x2==1) $check=" CHECKED";
                    	if (trim($_POST['P'.$x2])!=="")
                    	{
                     ?>       
                        <tr>
               	         <td class=voice_right >
                              <input type='radio' name='ANSWER' value='$in1'<?echo($check)?>>
               	         </td>
               	         <td class=voice_right nowrap align='left'>
                              <?echo(fd_smiles(fd_href(stripslashes($_POST['P'.$x2]))))?>
               	         </td>
                        </tr>
                     <?
	               	}
                     $x2+=1;
                  }?>     
               </table>
            </td>
         </tr>
      </table>
   </td>
</tr>
<tr>
   <td width=100% align='center'>
      <?if (isset($_POST['US'])){?>
         Голосующие могут добавлять свои варианты ответа
      <?}else{?>
         Голосующие не могут добавлять свои варианты ответа
      <?}?>
   </td>
</tr>
