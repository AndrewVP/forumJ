<script type='text/javascript'>
   function checkLength(id, ml)
   {
   	var maxlength = ml;
   	var str = id.value;
   		
   	if (str.length > maxlength) {
   		str = str.substr(0, maxlength);
   		id.value = str;
   		}
//   	document.forms[0].count.value = maxlength - str.length;
   }
</script>