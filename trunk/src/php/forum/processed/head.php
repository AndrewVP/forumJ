<?
      // ��������� ���� ���������
   function fd_head($_result) {
	   $_result=str_replace("[span class='found']", "<span class='found'>", $_result); 
   	$_result=str_replace("[/span]", "</span>", $_result); 
      $_result=fd_smiles($_result);
      $_result=fd_cenz($_result);
      return $_result;
   }
?>