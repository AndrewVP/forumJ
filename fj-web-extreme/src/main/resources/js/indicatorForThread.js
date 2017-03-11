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
    url = "ping?id="+ threadId;
    params = "idb=" + m_xb;
	request.open("POST", url, true);

	//Send the proper header information along with the request
	request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	request.setRequestHeader("Content-length", params.length);
	request.setRequestHeader("Connection", "close");

	request.onreadystatechange = updateIndicator;
	request.send(params);
}

function updateIndicator() {
   if (request.readyState == 4) {
      if (request.status == 200) {
         var response = JSON.parse(request.responseText);
         var indicatort = document.getElementById("indicatort");
         var text = document.createTextNode(response.posts);
         indicatort.removeChild(indicatort.firstChild);
         indicatort.appendChild(text);
         if (response.posts != 0){
             if (typeof document.oldTitle === "undefined"){
                 document.oldTitle = document.title;
             }
             document.title = '(' + response.posts + ') ' + document.oldTitle;
         }
      }
      window.setTimeout(getIndicatorInfo, 30000);
   }
}
