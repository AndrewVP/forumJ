<script type='text/javascript'>
function add_node()
{
   var tbody = document.getElementById('tbl_node').getElementsByTagName('TBODY')[0];	
   var kol = document.getElementById('kol');
   var kolvo=parseInt(kol.value);
   var new_kol=kolvo+1;
   kol.value=new_kol.toFixed(0);
   var p_name='P'+new_kol.toFixed(0);
   var row = document.createElement("TR");
   tbody.appendChild(row);
   var td = document.createElement("TD");
   row.appendChild(td);
   sp=document.createTextNode(new_kol.toFixed(0)+". ");
   td.appendChild(sp);
   var p = document.createElement("INPUT");
   p.size=100;
   p.name=p_name;
   p.id=p_name;
   p.className="mnuforumSm";
   td.appendChild(p);
}
</script>