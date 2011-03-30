<script language="PHP">
echo "      
   <sc"."ript>
      var DHTML = (document.getElementById || document.all || document.layers);
      
      function getObj(name)	{
          if (document.getElementById)	{    	// DOM level 1 browsers: IE 5+, NN 6+
              this.obj = document.getElementById(name);
              this.style = document.getElementById(name).style;
           }
           else if (document.all)	{  			// IE 4
              this.obj = document.all[name];
              this.style = document.all[name].style;
           }
           else if (document.layers)  { 			// NN 4
               this.obj = document.layers[name];
               this.style = document.layers[name];
           }
      }
      
      function togglemsg(id) {
          if (!DHTML){
              alert ('Ваш броузер не поддерживает DHTML! Позор! Отстой! :)');
              return;
          }
          var newmsg = new getObj(id);
          if (newmsg.style.visibility == 'visible') {
              newmsg.style.visibility = 'hidden';
              newmsg.style.display = 'none';
          }
          else {
              newmsg.style.visibility = 'visible';
              newmsg.style.display = 'block';
          }
      }
   </scr"."ipt>                  
";                  
</script>               