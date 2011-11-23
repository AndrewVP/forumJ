var request = false;
try {
   request = new XMLHttpRequest();
} catch (trymicrosoft) {
   try {
      request = new ActiveXObject("Msxml2.XMLHTTP");
   } catch (othermicrosoft) {
      try {
         request = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (failed) {
         request = false;
      }
   }
}
function getIndicatorInfo() {
   var url = "/forum/count.php?idb=" + m_xb + "&idt=" + m_xt + "&ids=" + idss;
   request.open("GET", url, true);
   request.onreadystatechange = updateIndicator;
   request.send(null);
}

function updateIndicator() {
   if (request.readyState == 4) {
      if (request.status == 200) {
         var response = request.responseText.split(";");
         var indicatort = document.getElementById("indicatort");
         var text = document.createTextNode(response[0]);
         indicatort.removeChild(indicatort.firstChild);
         indicatort.appendChild(text);
         var indicatorb = document.getElementById("indicatorb");
         var textb = document.createTextNode(response[1]);
         indicatorb.removeChild(indicatorb.firstChild);
         indicatorb.appendChild(textb);
         var newposts = response[2].split("|");
         for (x=0; x< newposts.length; x++){
            posts = newposts[x].split(",");
            if (posts[1] !=0){
               var post = document.getElementById("posts" + posts[0]);
               var text = document.createTextNode("+" + posts[1]);
               post.removeChild(post.firstChild);
               post.appendChild(text);
            }
         }
      }
      window.setTimeout(getIndicatorInfo, 30000);
   }
}
