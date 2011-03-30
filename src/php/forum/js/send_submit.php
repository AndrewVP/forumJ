<script type='text/javascript'>
	function send_submit(comand)
	{
		if (document.post.RCVR.value.replace(/(^\s*)|(\s*$)/g, "").length==0){
			alert('<?echo("$_mess132");?>');
		}else if (document.post.NHEAD.value.replace(/(^\s*)|(\s*$)/g, "").length==0){
			alert('<?echo("$_mess128");?>');
		}else if (document.post.A2.value.replace(/(^\s*)|(\s*$)/g, "").length==0){
			alert('<?echo("$_mess129");?>');
		}else{
		document.post.comand.value=comand;
		document.post.submit();
		}
	} 
</script>