<script type='text/javascript'>
	function quest_submit(comand)
	{
		if (document.post.T.value.replace(/(^\s*)|(\s*$)/g, "").length==0){
			alert('<?echo("$_mess128");?>');
		}else if (document.post.Q.value.replace(/(^\s*)|(\s*$)/g, "").length==0){
			alert('<?echo("$_mess130");?>');
		}else if (document.post.A2.value.replace(/(^\s*)|(\s*$)/g, "").length==0){
			alert('<?echo("$_mess129");?>');
		}else{
			var x1=1;
			var x2=0;
			while (document.getElementById('P'+x1.toFixed(0))!=null){
				if (document.getElementById('P'+x1.toFixed(0)).value.replace(/(^\s*)|(\s*$)/g, "").length!=0)
				{
					x2+=1;
	    		}
	    		x1+=1;
	    	}
			if (x2<2)
			{
				alert('<?echo("$_mess131");?>');
			}else{
				document.post.comand.value=comand;
				document.post.submit();
			}
		}
	} 
</script>