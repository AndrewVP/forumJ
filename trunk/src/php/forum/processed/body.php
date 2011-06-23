<?php
      // обработка тела сообщения
   function fd_body($_result) {
/*
   	include_once('bbcode/bbcode.lib.php');
		$bb = new bbcode($_result);
// Конвертируем BBCode в HTML и выводим его
		$_result = $bb -> get_html();
*/

   	$_result=fd_bbcode($_result);
      $_result=fd_smiles($_result);
      $_result=fd_cenz($_result);
      return $_result;
   }
?>