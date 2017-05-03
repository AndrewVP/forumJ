function smile(string){
   var area = document.getElementById('ed1');
   if (area){
      if (area.createTextRange) /* MSIE */
      {
         area.focus(area.caretPos);
         area.caretPos = document.selection.createRange().duplicate();
         curtxt = area.caretPos.text;
         area.caretPos.text = string + curtxt;
      }
      else /* Other browsers */
      {
         var pos = area.selectionStart;
         area.value =
            area.value.substring(0,pos) +
            string +
            area.value.substring(pos);
         area.focus();
         area.selectionStart = pos + string.length;
         area.selectionEnd = area.selectionStart;
      }
   } else {
      alert('Глюки....');
   }
}   
function viewsml(){
   var b1=document.getElementById('btn1_table');
   b1.style.display='none';
   addsml(webapp + '/00/smiles/good.gif',':[good]', '1');
   addsml(webapp + '/00/smiles/biggrin.gif',':D', '1');
   addsml(webapp + '/00/smiles/acute.gif',':[no-no]', '1');
   addsml(webapp + '/00/smiles/nea.gif',':[nea]', '1');
   addsml(webapp + '/00/smiles/blum3.gif',':[mblum]', '1');
   addsml(webapp + '/00/smiles/to_clue.gif',':[toclue]', '1');
   addsml(webapp + '/00/smiles/drinks.gif',':[beer]', '1');
   addsml(webapp + '/00/smiles/friends.gif',':[friends]', '1');
   addsml(webapp + '/00/smiles/snooks.gif',':[snooks]', '1');
   addsml(webapp + '/00/smiles/scare.gif',':[scare]', '1');
   addsml(webapp + '/00/smiles/scare2.gif',':[scare2]', '1');
   addsml(webapp + '/00/smiles/taunt.gif',':[taunt]', '1');
   addsml(webapp + '/00/smiles/shok.gif',':[shok]', '1');
   addsml(webapp + '/00/smiles/whistle.gif',':[whistle]', '1');
   addsml(webapp + '/00/smiles/victory.gif',':[victory]', '1');
   addsml(webapp + '/00/smiles/vinsent.gif',':[vinsent]', '1');
   addsml(webapp + '/00/smiles/tommy.gif',':[tommy]', '1');
   addsml(webapp + '/00/smiles/to_keep_order.gif',':[to_keep_order]', '1');
   addsml(webapp + '/00/smiles/tease.gif',':[tease]', '1');
   addsml(webapp + '/00/smiles/suicide.gif',':[suicide]', '1');
   addsml(webapp + '/00/smiles/slow.gif',':[slow]', '1');
   addsml(webapp + '/00/smiles/rofl.gif',':[rofl]', '1');
   addsml(webapp + '/00/smiles/read.gif',':[read]', '1');
   addsml(webapp + '/00/smiles/rabbi.gif',':[rabbi]', '1');
   addsml(webapp + '/00/smiles/punish.gif',':[punish]', '1');
   addsml(webapp + '/00/smiles/ok.gif',':[ok]', '1');
   addsml(webapp + '/00/smiles/new_russian.gif',':[new_russian]', '1');
   addsml(webapp + '/00/smiles/lazy2.gif',':[lazy2]', '1');
   addsml(webapp + '/00/smiles/Just_Cuz_11.gif',':[jc]', '1');
   addsml(webapp + '/00/smiles/hi.gif',':[hi]', '1');
   addsml(webapp + '/00/smiles/help.gif',':[help]', '1');
   addsml(webapp + '/00/smiles/heat.gif',':[heat]', '1');
   addsml(webapp + '/00/smiles/fuck.gif',':[fuck]', '1');
   addsml(webapp + '/00/smiles/fool.gif',':[fool]', '1');
   addsml(webapp + '/00/smiles/dntknw.gif',':[dntknw]', '1');
   addsml(webapp + '/00/smiles/dance2.gif',':[dance2]', '1');
   addsml(webapp + '/00/smiles/aftar.gif',':[aftar]', '1');
   addsml(webapp + '/00/smiles/party.gif',':[party]', '1');
   addsml(webapp + '/00/smiles/smoke.gif',':[smoke]', '1');
   addsml(webapp + '/00/smiles/laie_32.gif',':[laie_32]', '1');
   addsml(webapp + '/00/smiles/laie_44.gif',':[laie_44]', '1');
   addsml(webapp + '/00/smiles/laie_48.gif',':[laie_48]', '1');

   addsml(webapp + '/00/smiles/girl_sigh.gif',':[ohi]', '2');
   addsml(webapp + '/00/smiles/girl_blum.gif',':[blum]', '2');
   addsml(webapp + '/00/smiles/girl_haha.gif',':[ghaha]', '2');
   addsml(webapp + '/00/smiles/girl_mad.gif',':[gmad]', '2');
   addsml(webapp + '/00/smiles/girl_wacko.gif',':[gwacko]', '2');
   addsml(webapp + '/00/smiles/girl_hospital.gif',':[klizma]', '2');
   addsml(webapp + '/00/smiles/to_babruysk.gif',':[babruysk]', '2');
   addsml(webapp + '/00/smiles/girl_hide.gif',':[ghide]', '2');
   addsml(webapp + '/00/smiles/girl_in_love.gif',':[glove]', '2');
   addsml(webapp + '/00/smiles/girl_prepare_fish.gif',':[gfish]', '2');
   addsml(webapp + '/00/smiles/girl_crazy.gif',':[gcrazy]', '2');
   addsml(webapp + '/00/smiles/girl_werewolf.gif',':[gwerewolf]', '2');
   addsml(webapp + '/00/smiles/girl_devil.gif',':[gdevil]', '2');
   addsml(webapp + '/00/smiles/queen.gif',':[queen]', '2');
   addsml(webapp + '/00/smiles/triniti.gif',':[triniti]', '2');
   addsml(webapp + '/00/smiles/spruce_up.gif',':[spruce_up]', '2');
   addsml(webapp + '/00/smiles/flirt.gif',':[flirt]', '2');
   addsml(webapp + '/00/smiles/feminist.gif',':[feminist]', '2');
   addsml(webapp + '/00/smiles/brunette.gif',':[brunette]', '2');
   addsml(webapp + '/00/smiles/angel.gif',':[angel]', '2');
   addsml(webapp + '/00/smiles/girl_cray2.gif',':[girl_cray2]', '2');
   addsml(webapp + '/00/smiles/girl_cray3.gif',':[girl_cray3]', '2');
   addsml(webapp + '/00/smiles/girl_impossible.gif',':[girl_impossible]', '2');
   addsml(webapp + '/00/smiles/girl_wink.gif',':[girl_wink]', '2');
   addsml(webapp + '/00/smiles/girl_dance.gif',':[girl_dance]', '2');
   addsml(webapp + '/00/smiles/snoozer_18.gif',':[snoozer_18]', '2');
   addsml(webapp + '/00/smiles/Koshechka_09.gif',':[Koshechka_09]', '2');
   addsml(webapp + '/00/smiles/Koshechka_11.gif',':[Koshechka_11]', '2');
   addsml(webapp + '/00/smiles/libelle_1.gif',':[libelle_1]', '2');
   addsml(webapp + '/00/smiles/connie_1.gif',':[connie_1]', '2');
   addsml(webapp + '/00/smiles/connie_6.gif',':[connie_6]', '2');

   addsml(webapp + '/00/smiles/KidRock_02.gif',':[kr2]', '3');
   addsml(webapp + '/00/smiles/KidRock_05.gif',':[kr5]', '3');
   addsml(webapp + '/00/smiles/KidRock_04.gif',':[kr4]', '3');
   addsml(webapp + '/00/smiles/KidRock_07.gif',':[kr7]', '3');
   addsml(webapp + '/00/smiles/offtopic.gif',':[offtopic]', '3');
   addsml(webapp + '/00/smiles/whatever_01.gif',':[whatever]', '3');
   addsml(webapp + '/00/smiles/russian.gif',':[russian]', '3');
   addsml(webapp + '/00/smiles/pioneer.gif',':[pioneer]', '3');
   addsml(webapp + '/00/smiles/take_example.gif',':[pioners]', '3');
   addsml(webapp + '/00/smiles/king2.gif',':[king]', '3');
   addsml(webapp + '/00/smiles/butcher.gif',':[butcher]', '3');
   addsml(webapp + '/00/smiles/rtfm.gif',':[rtfm]', '3');
   addsml(webapp + '/00/smiles/skull.gif',':[skull]', '3');
   addsml(webapp + '/00/smiles/pooh_door.gif',':[pooh_door]', '3');
   addsml(webapp + '/00/smiles/moil.gif',':[moil]', '3');
   addsml(webapp + '/00/smiles/drag_10.gif',':[drag_10]', '3');
   addsml(webapp + '/00/smiles/spam_light.gif',':[spam_light]', '3');
   addsml(webapp + '/00/smiles/aleksey_01.gif',':[aleksey_01]', '3');
}  
function addsml(src_, bb, n){
   var cc1=document.getElementById('td'+n);
   var sml = document.createElement('IMG');
   sp=document.createTextNode(' ');
   sml.src=src_;
   sml.border=0;
   sml.onclick=function() {smile(bb);}
   sml.style.cursor='pointer';
   cc1.appendChild(sml);
   cc1.appendChild(sp);
} 