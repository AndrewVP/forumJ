<?
/* �������*/
//include("bbcode.php");
include("smiles.php");
include("href.php");
//include("cenz.php");
include("head.php");
include("body.php");
include("form_add.php");
?>
<html>
   <head>
      <meta http-equiv='content-type' content='text/html; charset=windows-1251'>      
      <?
      /* �����*/
      include('style.php');
      /*������� (��������)*/
      if (strpos($_SERVER['HTTP_USER_AGENT'], "MSIE 5.0")){
         include('smile_ie5.php');
      }
      else {
         include('smile_.php');
      }
      /*������� (����������� �����)*/
      include('jstags.php');
      /*������� (submit �����)*/
      include('js/post_submit.php');
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
               <?/*"��������" ���������� �����*/
                  /*"��������" ������ ����� ��� ������ �� ������, �������� ����� ��������� ������*/
                  /*����*/?>
               <div class=nik>
                  <b>&nbsp;&nbsp;<? echo(fd_smiles(htmlspecialchars(stripslashes($_POST['NHEAD']))));?></b>
               </div>
            </td>
         </tr>
         <tr>
            <td class='matras'>
                  <?/*���*/?>
               <span class='tbtextnread'>
                  <? echo(htmlspecialchars($n1));?>
               </span>
               &nbsp;<? echo(chr(149));?>
                  <?/*����*/?>
               &nbsp;
               <img border='0' src='smiles/icon_minipost.gif'>
               &nbsp;
               <span class='posthead'><? echo($lptime);?></span>
               &nbsp;<? echo(chr(149));?>
                  <?/*����*/?> 
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
                  <?/*������������*/?>
               &nbsp;<? echo(chr(149));?>
               <span class="posthead">
                  <? echo($_mess68);?>
               </span>
            </td>
         </tr>
         <tr>
            <td>
                  <?/* div ��� ������*/?>
               <div>
                     <?/*�������*/?>
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
                              <tr>
                                 <td>
                                       <?/* ������� �����*/?>
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
                        <form name='post' action='write.php' method='POST'>
                           <table width='100%'>
                              <?/*����*/?>
                              <tr>
                                 <td colspan='2' align='CENTER'>
	   										<?echo($_mess59.":&nbsp;");?>
                                    <input class='mnuforumSm' type=text name='NHEAD' size='70' value='<? echo(htmlspecialchars(stripslashes($_POST['NHEAD'])))?>'>
                                 </td>
                              </tr>
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
                                    /* ���������*/?>
                                    <p>
                                       <textarea rows='20' class='mnuforumSm'  id='ed1' name='A2' cols='55'><? echo(htmlspecialchars(stripslashes($_POST['A2'])))?></textarea>
                                    </p>
                                    <?
                                    $checked="";
                                    if (isset($_POST['no_exit'])){
	                                    $checked="CHECKED";
                                    }
                                    ?>
	        									<input type="checkbox" <?echo($checked);?> name="no_exit" value="1">
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
                                       <?/*��������� ������ ���������...*/?>
	        									<?echo(fd_form_add());?>
                                       <?/* ���� �����������*/
                                    if (isset($_POST['IDB'])) {?>
                                       <input type=hidden name='IDB' value='<?echo($_POST['IDB'])?>'>
                                    <?}?>
                                       <?/*id ����*/?>
                                    <input type=hidden name='IDT' value='<?echo($_POST['IDT'])?>'>
                                 </td>
                              </tr>
                              <tr>
                                 <td>
                                 </td>
                                 <td align='CENTER' valign='top'>
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