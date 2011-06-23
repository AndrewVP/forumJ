<script type='text/javascript'>
	function post_submit(comand)
	{
		if (document.post.NHEAD.value.replace(/(^\s*)|(\s*$)/g, "").length==0){
			alert('<?
			if (isset($locale)){
				$locale->getString("mess128");
			}else{
				echo("$_mess128");
			}
			?>');
		}else if (document.post.A2.value.replace(/(^\s*)|(\s*$)/g, "").length==0){
			alert('<?
			if (isset($locale)){
				$locale->getString("mess128");
			}else{
				echo("$_mess129");
			}
			?>');
		}else{
		document.post.comand.value=comand;
		document.post.submit();
		}
	} 
</script>