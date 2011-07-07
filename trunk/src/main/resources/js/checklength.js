function checkLength(id, ml){
   var maxlength = ml;
   var str = id.value;
   if (str.length > maxlength) {
      str = str.substr(0, maxlength);
      id.value = str;
   }
}
