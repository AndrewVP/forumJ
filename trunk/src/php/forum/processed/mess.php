<?
// ����� ����
   session_start();
   include("query.php");
//������������� �����������
   include("cache.php");
   include("form_add.php");
   include("button.php");
   include('setup.php');
// �������� ����������
   $action=7;
   include("stat.php");
//
   mysql_close($conn);
// ����� ���� ����������? �� ��������� - ���������� :)
   include("lang.php");
?>   
<html>
	<head>
      <meta http-equiv='content-type' content='text/html; charset=windows-1251'>
      <?
      /*�����*/
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
	   include('js/new_submit.php');
      ?>   
   		<title>
   			<?echo($_mess4);?>
   		</title>
   	</head>
		<?/*���� ���� ��������*/?>
   	<body bgcolor=#EFEFEF>
			<?/*������� �������*/?>
   		<table border='0' style='border-collapse: collapse' width='100%'>
				<?
				/*������� � ���� � ������� ��������*/
   			include("logo.php");
				// ������� ������
				// ������� "����"
   			include("menu.php");
				// ����� ����� �����
				?>
   			<tr>
   				<td>
   					<table width=100%>
   						<tr>
   							<td>
   								<form name='post' action='new.php' method='POST'>
   									<table width='100%'>
											<?/*����*/?>
   										<tr>
   											<td colspan='2' align='left'>
								   				<?echo($_mess4);?>&nbsp;
   												<input class='mnuforumSm' type=text name='NHEAD' size='120' maxlength="120">
   											</td>
   										</tr>
   										<tr>
												<?/*�������� ���������*/?>
   											<td align=center>
            									<p>
            										<?echo($_mess21.":");?>
            									</p>
											   </td>
												<?/*�����������*/?>
   											<td align='CENTER'>
   											<p>
           										<?echo($_mess12);?>
   											</p>
   										</td>
   									</tr>
										<?/*����*/?>
   									<tr>
   										<td valign='TOP'>
   	   									<?/*��������*/?>
         									<?include("smiles_add.php");?>
										   </td>
   										<td align='CENTER' valign='top'>
												<?/*��������*/
   	   									include("autotags_add.php");
   											/*���������*/?>
   											<p>
   												<textarea class='mnuforumSm' rows='30' id='ed1' name='A2' cols='55'></textarea>
   											</p>
                              		<?/*������*/?>
           									<table>
           										<tr>
           											<td>
   											         <?echo(fd_button($_mess13,"new_submit(\"write\");","B1", "1", "", "", "", ""))?>
           											</td>
           											<td>
   											         <?echo(fd_button($_mess63,"new_submit(\"view\");","B3", "1", "", "", "", ""))?>
           											</td>
           										</tr>
           									</table>
                                    <?/*��������� ������ ���������...*/?>
	        									<?echo(fd_form_add());?>
										   </td>
   									</tr>
   								</table>
   							</form>
   						</td>
   					</tr>
   				</table>
   			</td>
   		</tr>
			<?
			/*������� "����"*/
   		include("menu.php");
			// ������ �����, �������� � ��������.
   		include("end.php");
   		?>
   </body>
</html>

