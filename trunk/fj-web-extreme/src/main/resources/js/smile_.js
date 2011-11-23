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
   addsml('smiles/good.gif',':[good]', '1');
   addsml('smiles/biggrin.gif',':D', '1');
   addsml('smiles/acute.gif',':[no-no]', '1');
   addsml('smiles/nea.gif',':[nea]', '1');
   addsml('smiles/blum3.gif',':[mblum]', '1');
   addsml('smiles/to_clue.gif',':[toclue]', '1');
   addsml('smiles/drinks.gif',':[beer]', '1');
   addsml('smiles/friends.gif',':[friends]', '1');
   addsml('smiles/snooks.gif',':[snooks]', '1');
   addsml('smiles/scare.gif',':[scare]', '1');
   addsml('smiles/scare2.gif',':[scare2]', '1');
   addsml('smiles/taunt.gif',':[taunt]', '1');
   addsml('smiles/shok.gif',':[shok]', '1');
   addsml('smiles/whistle.gif',':[whistle]', '1');
   addsml('smiles/victory.gif',':[victory]', '1');
   addsml('smiles/vinsent.gif',':[vinsent]', '1');
   addsml('smiles/tommy.gif',':[tommy]', '1');
   addsml('smiles/to_keep_order.gif',':[to_keep_order]', '1');
   addsml('smiles/tease.gif',':[tease]', '1');
   addsml('smiles/suicide.gif',':[suicide]', '1');
   addsml('smiles/slow.gif',':[slow]', '1');
   addsml('smiles/rofl.gif',':[rofl]', '1');
   addsml('smiles/read.gif',':[read]', '1');
   addsml('smiles/rabbi.gif',':[rabbi]', '1');
   addsml('smiles/punish.gif',':[punish]', '1');
   addsml('smiles/ok.gif',':[ok]', '1');
   addsml('smiles/new_russian.gif',':[new_russian]', '1');
   addsml('smiles/lazy2.gif',':[lazy2]', '1');
   addsml('smiles/Just_Cuz_11.gif',':[jc]', '1');
   addsml('smiles/hi.gif',':[hi]', '1');
   addsml('smiles/help.gif',':[help]', '1');
   addsml('smiles/heat.gif',':[heat]', '1');
   addsml('smiles/fuck.gif',':[fuck]', '1');
   addsml('smiles/fool.gif',':[fool]', '1');
   addsml('smiles/dntknw.gif',':[dntknw]', '1');
   addsml('smiles/dance2.gif',':[dance2]', '1');
   addsml('smiles/aftar.gif',':[aftar]', '1');
   addsml('smiles/party.gif',':[party]', '1');
   addsml('smiles/smoke.gif',':[smoke]', '1');
   addsml('smiles/laie_32.gif',':[laie_32]', '1');
   addsml('smiles/laie_44.gif',':[laie_44]', '1');
   addsml('smiles/laie_48.gif',':[laie_48]', '1');

   addsml('smiles/girl_sigh.gif',':[ohi]', '2');
   addsml('smiles/girl_blum.gif',':[blum]', '2');
   addsml('smiles/girl_haha.gif',':[ghaha]', '2');
   addsml('smiles/girl_mad.gif',':[gmad]', '2');
   addsml('smiles/girl_wacko.gif',':[gwacko]', '2');
   addsml('smiles/girl_hospital.gif',':[klizma]', '2');
   addsml('smiles/to_babruysk.gif',':[babruysk]', '2');
   addsml('smiles/girl_hide.gif',':[ghide]', '2');
   addsml('smiles/girl_in_love.gif',':[glove]', '2');
   addsml('smiles/girl_prepare_fish.gif',':[gfish]', '2');
   addsml('smiles/girl_crazy.gif',':[gcrazy]', '2');
   addsml('smiles/girl_werewolf.gif',':[gwerewolf]', '2');
   addsml('smiles/girl_devil.gif',':[gdevil]', '2');
   addsml('smiles/queen.gif',':[queen]', '2');
   addsml('smiles/triniti.gif',':[triniti]', '2');
   addsml('smiles/spruce_up.gif',':[spruce_up]', '2');
   addsml('smiles/flirt.gif',':[flirt]', '2');
   addsml('smiles/feminist.gif',':[feminist]', '2');
   addsml('smiles/brunette.gif',':[brunette]', '2');
   addsml('smiles/angel.gif',':[angel]', '2');
   addsml('smiles/girl_cray2.gif',':[girl_cray2]', '2');
   addsml('smiles/girl_cray3.gif',':[girl_cray3]', '2');
   addsml('smiles/girl_impossible.gif',':[girl_impossible]', '2');
   addsml('smiles/girl_wink.gif',':[girl_wink]', '2');
   addsml('smiles/girl_dance.gif',':[girl_dance]', '2');
   addsml('smiles/snoozer_18.gif',':[snoozer_18]', '2');
   addsml('smiles/Koshechka_09.gif',':[Koshechka_09]', '2');
   addsml('smiles/Koshechka_11.gif',':[Koshechka_11]', '2');
   addsml('smiles/libelle_1.gif',':[libelle_1]', '2');
   addsml('smiles/connie_1.gif',':[connie_1]', '2');
   addsml('smiles/connie_6.gif',':[connie_6]', '2');

   addsml('smiles/KidRock_02.gif',':[kr2]', '3');
   addsml('smiles/KidRock_05.gif',':[kr5]', '3');
   addsml('smiles/KidRock_04.gif',':[kr4]', '3');
   addsml('smiles/KidRock_07.gif',':[kr7]', '3');
   addsml('smiles/offtopic.gif',':[offtopic]', '3');
   addsml('smiles/whatever_01.gif',':[whatever]', '3');
   addsml('smiles/russian.gif',':[russian]', '3');
   addsml('smiles/pioneer.gif',':[pioneer]', '3');
   addsml('smiles/take_example.gif',':[pioners]', '3');
   addsml('smiles/king2.gif',':[king]', '3');
   addsml('smiles/butcher.gif',':[butcher]', '3');
   addsml('smiles/rtfm.gif',':[rtfm]', '3');
   addsml('smiles/skull.gif',':[skull]', '3');
   addsml('smiles/pooh_door.gif',':[pooh_door]', '3');
   addsml('smiles/moil.gif',':[moil]', '3');
   addsml('smiles/drag_10.gif',':[drag_10]', '3');
   addsml('smiles/spam_light.gif',':[spam_light]', '3');
   addsml('smiles/aleksey_01.gif',':[aleksey_01]', '3');
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