<?
function fd_form_add() {
	$result="";
	//Команда для обработки
  $result.="<input type=hidden name='comand'>";
  // Автор
  $result.="<input type=hidden name='IDU' value='".$_SESSION['idu']."'>";
  $result.="<input type=hidden name='AUT' value='".$_SESSION['autor']."'>";
  // пароль автора
  if (isset($_SESSION['pass2'])) {
     // кука
     $result.="<input type=hidden name='PS2' value='".$_SESSION['pass2']."'>";
  }else{
     // не кука
     $result.="<input type=hidden name='PS1' value='".$_SESSION['pass1']."'>";
   }
	return $result;
}
?>