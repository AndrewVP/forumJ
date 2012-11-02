<SCRIPT LANGUAGE="JavaScript1.2">
  var no = 10; // snow number

  var dx, xp, yp;    // coordinate and position variables
  var am, stx, sty;  // amplitude and step variables
  var i, doc_width = 800, doc_height = 600;
  
  doc_width = document.body.clientWidth;
  doc_height = document.body.clientHeight;

  dx = new Array();
  xp = new Array();
  yp = new Array();
  am = new Array();
  stx = new Array();
  sty = new Array();
  
  for (i = 0; i < no; ++ i) {  
    dx[i] = 0;                        // set coordinate variables
    xp[i] = Math.random()*(doc_width-50);  // set position variables
    yp[i] = Math.random()*doc_height;
    am[i] = Math.random()*20;         // set amplitude variables
    stx[i] = 0.02 + Math.random()/10; // set step variables
    sty[i] = 0.7 + Math.random();     // set step variables
    if (i == 0) {
      document.write("<div id=\"dot"+ i +"\" style=\"POSITION: absolute; Z-INDEX: "+ i +"; VISIBILITY: visible; TOP: 15px; LEFT: 15px;\"><img src=\"picts/snow.gif\" border=\"0\"></div>");
    } else {
      document.write("<div id=\"dot"+ i +"\" style=\"POSITION: absolute; Z-INDEX: "+ i +"; VISIBILITY: visible; TOP: 15px; LEFT: 15px;\"><img src=\"picts/snow.gif\" border=\"0\"></div>");
    }
  }

  function snow() {
    for (i = 0; i < no; ++ i) {  // iterate for every dot
      yp[i] += sty[i];
      if (yp[i] > doc_height-50) {
        xp[i] = Math.random()*(doc_width-am[i]-30);
        yp[i] = 0;
        stx[i] = 0.02 + Math.random()/10;
        sty[i] = 0.7 + Math.random();
        doc_width = document.body.clientWidth;
        doc_height = document.body.clientHeight;
      }
      dx[i] += stx[i];
      document.getElementById("dot"+i).style.top = yp[i];
      document.getElementById("dot"+i).style.left = xp[i] + am[i]*Math.sin(dx[i]);
    }
    setTimeout("snow()", 10);
  }

  snow();
</script>
