function InsertTags(tagStart, tagEnd) {
   document.post.A2.focus();
   if (document.selection) {
      // ie & may be opera 8
      var rng = document.selection.createRange();
      if (rng.text) {   
         document.selection.createRange().text = tagStart + rng.text + tagEnd;
      } else {  
         document.post.A2.value += tagStart  + tagEnd;
      }
      document.post.A2.focus();
   } else if (document.post.A2.selectionStart ||
         document.post.A2.selectionStart == '0') {
      // mozilla: intellegent btagStart support
      var selStart = document.post.A2.selectionStart;
      var selEnd = document.post.A2.selectionEnd;

      var s = document.post.A2.value;
      s = s.substring(0, selStart) + tagStart + s.substring(selStart, selEnd)
      + tagEnd + s.substring(selEnd, s.length);
      document.post.A2.value = s;

      if (selEnd != selStart) {
         document.post.A2.selectionStart = selStart;
         document.post.A2.selectionEnd = selEnd + tagStart.length +
         tagEnd.length;
      } else {
         document.post.A2.selectionStart = selStart + tagStart.length;
         document.post.A2.selectionEnd =
            document.post.A2.selectionStart;
      }
   } else {
      // other browsers
      document.post.A2.value += tagStart + tagEnd;
   }
}
