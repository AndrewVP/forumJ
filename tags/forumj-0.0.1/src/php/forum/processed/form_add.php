<?
function fd_form_add() {
	$result="";
	//������� ��� ���������
  $result.="<input type=hidden name='comand'>";
  // �����
  $result.="<input type=hidden name='IDU' value='".$_SESSION['idu']."'>";
  $result.="<input type=hidden name='AUT' value='".$_SESSION['autor']."'>";
  // ������ ������
  if (isset($_SESSION['pass2'])) {
     // ����
     $result.="<input type=hidden name='PS2' value='".$_SESSION['pass2']."'>";
  }else{
     // �� ����
     $result.="<input type=hidden name='PS1' value='".$_SESSION['pass1']."'>";
   }
	return $result;
}
?>