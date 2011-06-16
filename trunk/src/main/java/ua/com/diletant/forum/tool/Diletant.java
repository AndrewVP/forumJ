/*
 * Copyright &&rew V. Pogrebnyak
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions &&
 * limitations under the License.
 */
package ua.com.diletant.forum.tool;

import static ua.com.diletant.forum.tool.PHP.*;

/**
 * Diletant custom php functions 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">&&rew V. Pogrebnyak</a>
 */
public class Diletant {

   public static String fd_head(String result){
      result=PHP.str_replace("[span class='found']", "<span class='found'>", result); 
      result=PHP.str_replace("[/span]", "</span>", result); 
      result=fd_smiles(result);
      result=fd_cenz(result);
      return result;
   }

   public static String fd_smiles(String tmptxt) {
      tmptxt=PHP.str_replace(":)","<img border='0' src='smiles/smile_.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":(","<img border='0' src='smiles/sad.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":D","<img border='0' src='smiles/biggrin.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[russian]","<img border='0' src='smiles/russian.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[pioners]","<img border='0' src='smiles/take_example.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[beer]","<img border='0' src='smiles/drinks.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[no-no]","<img border='0' src='smiles/acute.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[nea]","<img border='0' src='smiles/nea.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[babruysk]","<img border='0' src='smiles/to_babruysk.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[ohi]","<img border='0' src='smiles/girl_sigh.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[klizma]","<img border='0' src='smiles/girl_hospital.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[king]","<img border='0' src='smiles/king2.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":g)","<img border='0' src='smiles/girl_smile.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":g(","<img border='0' src='smiles/girl_sad.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[blum]","<img border='0' src='smiles/girl_blum.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[ghaha]","<img border='0' src='smiles/girl_haha.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[gwacko]","<img border='0' src='smiles/girl_wacko.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[gmad]","<img border='0' src='smiles/girl_mad.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[ghide]","<img border='0' src='smiles/girl_hide.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[glove]","<img border='0' src='smiles/girl_in_love.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[gfish]","<img border='0' src='smiles/girl_prepare_fish.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[gcrazy]","<img border='0' src='smiles/girl_crazy.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[mblum]","<img border='0' src='smiles/blum3.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[toclue]","<img border='0' src='smiles/to_clue.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[snooks]","<img border='0' src='smiles/snooks.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[scare]","<img border='0' src='smiles/scare.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[scare2]","<img border='0' src='smiles/scare2.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[gwerewolf]","<img border='0' src='smiles/girl_werewolf.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[gdevil]","<img border='0' src='smiles/girl_devil.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[friends]","<img border='0' src='smiles/friends.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[taunt]","<img border='0' src='smiles/taunt.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[offtopic]","<img border='0' src='smiles/offtopic.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[queen]","<img border='0' src='smiles/queen.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[butcher]","<img border='0' src='smiles/butcher.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[rtfm]","<img border='0' src='smiles/rtfm.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[shok]","<img border='0' src='smiles/shok.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[kr2]","<img border='0' src='smiles/KidRock_02.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[kr5]","<img border='0' src='smiles/KidRock_05.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[kr7]","<img border='0' src='smiles/KidRock_07.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[kr4]","<img border='0' src='smiles/KidRock_04.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[whistle]","<img border='0' src='smiles/whistle.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[whatever]","<img border='0' src='smiles/whatever_01.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[vinsent]","<img border='0' src='smiles/vinsent.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[victory]","<img border='0' src='smiles/victory.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[triniti]","<img border='0' src='smiles/triniti.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[tommy]","<img border='0' src='smiles/tommy.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[to_keep_order]","<img border='0' src='smiles/to_keep_order.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[tease]","<img border='0' src='smiles/tease.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[suicide]","<img border='0' src='smiles/suicide.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[spruce_up]","<img border='0' src='smiles/spruce_up.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[slow]","<img border='0' src='smiles/slow.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[skull]","<img border='0' src='smiles/skull.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[rofl]","<img border='0' src='smiles/rofl.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[read]","<img border='0' src='smiles/read.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[rabbi]","<img border='0' src='smiles/rabbi.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[punish]","<img border='0' src='smiles/punish.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[pooh_door]","<img border='0' src='smiles/pooh_door.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[pioneer]","<img border='0' src='smiles/pioneer.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[ok]","<img border='0' src='smiles/ok.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[new_russian]","<img border='0' src='smiles/new_russian.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[moil]","<img border='0' src='smiles/moil.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[lazy2]","<img border='0' src='smiles/lazy2.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[jc]","<img border='0' src='smiles/Just_Cuz_11.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[hi]","<img border='0' src='smiles/hi.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[help]","<img border='0' src='smiles/help.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[heat]","<img border='0' src='smiles/heat.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[good]","<img border='0' src='smiles/good.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[fuck]","<img border='0' src='smiles/fuck.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[fool]","<img border='0' src='smiles/fool.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[flirt]","<img border='0' src='smiles/flirt.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[dntknw]","<img border='0' src='smiles/dntknw.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[dance2]","<img border='0' src='smiles/dance2.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[brunette]","<img border='0' src='smiles/brunette.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[angel]","<img border='0' src='smiles/angel.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[aleksey_01]","<img border='0' src='smiles/aleksey_01.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[girl_cray2]","<img border='0' src='smiles/girl_cray2.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[girl_cray3]","<img border='0' src='smiles/girl_cray3.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[girl_impossible]","<img border='0' src='smiles/girl_impossible.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[girl_wink]","<img border='0' src='smiles/girl_wink.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[girl_dance]","<img border='0' src='smiles/girl_dance.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[snoozer_18]","<img border='0' src='smiles/snoozer_18.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[drag_10]","<img border='0' src='smiles/drag_10.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[Koshechka_09]","<img border='0' src='smiles/Koshechka_09.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[Koshechka_11]","<img border='0' src='smiles/Koshechka_11.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[libelle_1]","<img border='0' src='smiles/libelle_1.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[connie_6]","<img border='0' src='smiles/connie_6.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[connie_1]","<img border='0' src='smiles/connie_1.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[aftar]","<img border='0' src='smiles/aftar.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[party]","<img border='0' src='smiles/party.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[smoke]","<img border='0' src='smiles/smoke.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[feminist]","<img border='0' src='smiles/feminist.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[spam_light]","<img border='0' src='smiles/spam_light.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[laie_32]","<img border='0' src='smiles/laie_32.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[laie_44]","<img border='0' src='smiles/laie_44.gif'>",tmptxt);
      tmptxt=PHP.str_replace(":[laie_48]","<img border='0' src='smiles/laie_48.gif'>",tmptxt);
      tmptxt=PHP.str_replace(";)","<img border='0' src='smiles/wink3.gif'>",tmptxt);

      return tmptxt;
   }   

   public static String fd_cenz(String result) {
      //TODO Доделать
      // маты 
      //      
      //   /*хуй*/
      //      $pt[0]="/([а-яА-ЯiIіІєЄїЇ]*[а-пс-фц-яА-ПС-ФЦ-ЯiIіІєЄїЇ][а-яА-ЯiIіІєЄїЇ][хХxX]+[yYуУ]+[eEеЕяЯИийЙ]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //      $pt[1]="/([а-яА-ЯiIіІєЄїЇ]*[рРХх][б-тф-яБ-ТФ-ЯiIіІєЄїЇ][хХxX]+[yYуУ]+[eEеЕяЯИийЙ]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //      $pt[2]="/(\b[а-яА-ЯiIіІєЄїЇ][хХxX]+[yYуУ]+[eEеЕяЯИийЙ]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //      $pt[3]="/(\b[хХxX]+[yYуУ]+[eEеЕяЯИийЙ]+[а-яА-ЯiIіІєЄїЇ]*)/"; 
      //   /*отсос*/   
      //      $pt[4]="/(\b[OoОо0]+[Тт]+[CcСс]+[OoОо0]+[CcСс]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*ебал*/   
      //      $pt[5]="/([а-яА-ЯiIіІєЄїЇ]*[а-км-яА-КМ-ЯiIіІєЄїЇ][ЇїЁёЕе]+[Бб6]+[AaАаOoОо0]+[НнНlL]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //      $pt[6]="/([а-яА-ЯiIіІєЄїЇ]*[а-км-яА-ГЕ-КМ-ЯiIіІєЄїЇ][ЇїЁёЕе]+[Бб6]+[AaАа]+[Лл]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //      $pt[7]="/(\b[ЇїЁёЕе]+[Бб6]+[AaАа]+[Лл]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*ебись*/   
      //      $pt[8]="/([а-яА-ЯiIіІєЄїЇ]*[ЁёЕе]+[Бб6]+[Ии]+[CcСс]+[Ьь]+[а-яА-ЯiIіІєЄїЇ]*)/";     
      //   /*кацап*/   
      //      $pt[9]="/([а-яА-ЯiIіІєЄїЇ]*[KkКк]+[AaАаOoОо]+[Цц]+[AaАа]+[Ппn]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*хохол*/   
      //      $pt[10]="/([а-яА-ЯiIіІєЄїЇ]*[хХxX]+[aAаАOoОо0]+[хХxX]+[OoОо0]*[ЛлlL]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //      $pt[30]="/(\b[хХxXhH]+[kKкК]+[аАaAоОoO0]+[-.]+[а-яА-Яa-zA-Z0-9їЇіІєЄ]*)/";
      //      $pt[31]="/(\b[kKкК]+[аАaAоОoO0]+[kKкК]+[-]+[лЛlL]+[а-яА-Яa-zA-Z0-9їЇіІєЄ]*)/";
      //      
      //   /*майданут*/
      //      $pt[11]="/([а-яА-ЯiIіІєЄїЇ]*[MМм]+[AaАа]+[ИийЙ]+[ДдdD]+[AaАа]+[HНн]+[яЯyYуУ]+[Тт]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*пизд*/   
      //      $pt[12]="/([а-яА-ЯiIіІєЄїЇ]*[Пп]+[IiІіИийЙ]+[ЗзzZ]+[ДдdD]+[a-zA-Zа-яА-ЯiIіІєЄїЇ]*)/";
      //   /*залуп*/   
      //      $pt[13]="/([а-яА-ЯiIіІєЄїЇ]*[Зз]+[AaАа]+[ЛлlL]+[УуYy]+[Пп]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*уебок*/   
      //      $pt[14]="/(\b[уУyYuU]?[ЁёЕеEe]+[Бб6]+[OoОо0]?[KkКкТтT]+[а-яА-ЯiIіІєЄїЇ]*)/";
      ////      $pt[14]="/(\b[а-цш-яА-ЦШ-ЯiIіІєЄїЇ]*[а-км-о-цш-яА-КМ-О-ЦШ-ЯiIіІєЄїЇ][ЁёЕе]+[Бб6]+[OoОо0]+[KkКкТтT]+[а-яА-ЯiIіІєЄїЇ]*)/";
      ////      $pt[14]="/(\b[а-цш-яА-ЦШ-ЯiIіІєЄїЇ]*[ЁёЕе]+[Бб6]+[OoОо0]+[KkКкТтT]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*ебищ*/   
      //      $pt[15]="/(\b[а-цш-яА-ЦШ-ЯiIіІєЄїЇ]*[ЁёЕе]+[Бб6]+[иИ]+[шШщЩ]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*пидор*/   
      //      $pt[16]="/([а-яА-ЯiIіІєЄїЇ]*[Пп]+[іІИийЙ]+[ДдdD]+[ЕеEeОоOo0AaАа]+[PpРр]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*блядь*/   
      ////      $pt[17]="/([а-яА-ЯiIіІєЄїЇ]*[Бб6]+[Ии]*[ЛлlL]+[Яя]+[ДдdD]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //      $pt[29]="/(\b[Бб6]+[Ии]*[ЛлlL]+[Яя]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //      
      ////      $pt[17]="/([а-яА-ЯiIіІєЄїЇ]*[СсCc]+[Пп]+[ЕеEe]+[PpРр]+[МмM]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*клитор*/   
      //      $pt[18]="/([а-яА-ЯiIіІєЄїЇ]*[KkКк]+[ЛлlL]+[Ии]+[Тт]+[ОоOo0]+[PpРр]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*ебут*/   
      //      $pt[19]="/([а-яА-ЯiIіІєЄїЇ]*[а-яА-ЯiIіІєЄїЇ][а-пс-яА-ПС-ЯiIіІєЄїЇ][ЁёЕе]+[Бб6]+[yYуУ]+[Тт][^Тт])/";
      //      $pt[20]="/([а-яА-ЯiIіІєЄїЇ]*[а-вд-яА-ВД-ЯiIіІєЄїЇ][Рр][ЁёЕе]+[Бб6]+[yYуУ]+[Тт][^Тт])/";
      //      $pt[21]="/(\b[а-яА-ЯiIіІєЄїЇ][ЁёЕе]+[Бб6]+[yYуУ]+[Тт][^Тт])/";
      //      $pt[22]="/(\b[ЁёЕе]+[Бб6]+[yYуУ]+[Тт][^Тт])/";
      //   /*жид*/
      //      $pt[23]="/(\b[жЖ]+[.,-_ ;:\"'`~!@#$%^&*()]?[1iIіІиИыЫuU]+[.,-_ ;:\"'`~!@#$%^&*()]?[дДdDgG] )/";
      //      $pt[24]="/(\b[жЖ]+[.,-_ ;:\"'`~!@#$%^&*()]?[1iIіІиИыЫuU]+[.,-_ ;:\"'`~!@#$%^&*()]?[дДdDgG]+[.,-_ ;:\"'`~!@#$%^&*()]?[а-яА-Яa-zA-Z0-9їЇіІєЄ] )/";
      //      $pt[25]="/(\b[жЖ]+[.,-_;:\"'`~!@#$%^&*()]?[1iIіІиИыЫuU]+[.,-_ ;:\"'`~!@#$%^&*()]?[дДdDgG]+[.,-_ ;:\"'`~!@#$%^&*()]?[а-ил-яА-ИЛ-Яa-zA-Z0-9їЇіІєЄ][а-яА-Яa-zA-Z0-9їЇіІєЄ]+)/";
      //      $pt[26]="/([а-нп-яА-ЕЗ-НП-Яa-zA-Z0-9їЇіІєЄ]+[жЖ]+[.,-_;:\"'`~!@#$%^&*()]?[iIіІиИыЫuU]+[.,-_;:\"'`~!@#$%^&*()]?[дДdD]+[.,-_ ;:\"'`~!@#$%^&*()]?[а-яА-Яa-zA-Z0-9їЇіІєЄ]*)/";
      //      $pt[27]="/(\b[жЖ]+[уУ]+[дДdD]+[а-яА-Яa-zA-Z0-9їЇіІєЄ]*)/";
      //      $pt[28]="/(\]\|\[+[iIіІиИыЫ]+[дДdD]+[а-яА-Яa-zA-Z0-9їЇіІєЄ]*)/";
      //      
      //      
      //      if (strpos(" " + $_SERVER['PHP_SELF'], "index.php")){
      //         $rp="<font color=red>***</font>";  
      //      }    
      //      else{
      //         $rp="<font color=red><a title='\\1'>***</a></font>";  
      //      }
      ////      $rp="\\1";
      //      
      //      $_result=preg_replace($pt, $rp, $_result);
      return result;
   }

   public static String fd_body(String result) {
      result=fd_bbcode(result);
      result=fd_smiles(result);
      result=fd_cenz(result);
      return result;
   }
   
   public static String fd_bbcode(String $str_body) {
      int $fstoccq = 0;
      int $lastoccq = 0;
      int $fstoccq1 = 0;
      int $fstocc = 0;
      String $postbody=stripslashes(nl2br(htmlspecialchars($str_body)));
      $postbody=str_replace("[span class='found']", "<span class='found'>", $postbody); 
      $postbody=str_replace("[/span]", "</span>", $postbody); 
      // [b] [/b] <b> </b>
      String $result="";
      int $lastocc=0;
      int $sndocc=1;
      while($sndocc > 0)
      {
         $fstocc=strpos($postbody, "[b]", $lastocc);
         $sndocc=strpos($postbody, "[/b]", $fstocc);
         if(($fstocc > 0 && $sndocc > 0 && $lastocc > 0) || ($fstocc >= 0 && $sndocc > 0 && $lastocc== 0))
         {
            $result += substr($postbody, $lastocc, $fstocc - $lastocc);
            $result += "<b>" + substr($postbody, $fstocc + 3, $sndocc - $fstocc - 3) + "</b>";
            $lastocc = $sndocc + 4;
         }
         else
         {
            $result +=substr($postbody, $lastocc, strlen($postbody)-$lastocc);
            break;
         }
      }
      // [i] [/i] <i> </i>
      $postbody=$result;
      $result="";
      int $lastocci=0;
      int $sndocci=1;
      while($sndocci > 0)
      {
         int $fstocci=strpos($postbody, "[i]", $lastocci);
         $sndocci=strpos($postbody, "[/i]", $fstocci);
         if(($fstocci > 0 && $sndocci > 0 && $lastocci > 0) || ($fstocci >= 0 && $sndocci > 0 && $lastocci== 0))
         {
            $result += substr($postbody, $lastocci, $fstocci - $lastocci);
            $result += "<i>" + substr($postbody, $fstocci + 3, $sndocci - $fstocci - 3) + "</i>";
            $lastocci = $sndocci + 4;
         }
         else
         {
            $result +=substr($postbody, $lastocci, strlen($postbody)-$lastocci);
            break;
         }
      }
      // [u] [/u] <u> </u>
      $postbody=$result;
      $result="";
      int $lastoccu=0;
      int $sndoccu=1;
      while($sndoccu > 0)
      {
         int $fstoccu=strpos($postbody, "[u]", $lastoccu);
         $sndoccu=strpos($postbody, "[/u]", $fstoccu);
         if(($fstoccu > 0 && $sndoccu > 0 && $lastoccu > 0) || ($fstoccu >= 0 && $sndoccu > 0 && $lastoccu== 0))
         {
            $result += substr($postbody, $lastoccu, $fstoccu - $lastoccu);
            $result += "<u>" + substr($postbody, $fstoccu + 3, $sndoccu - $fstoccu - 3) + "</u>";
            $lastoccu = $sndoccu + 4;
         }
         else
         {
            $result +=substr($postbody, $lastoccu, strlen($postbody)-$lastoccu);
            break;
         }
      }
      // [size=1] [/size]
      int $sndoccq=1;
      while ($sndoccq>0){
         $postbody=$result;
         $lastoccq=0;
         $sndoccq=2;
         $fstoccq1=1;
         while($fstoccq1 < $sndoccq + $fstoccq1){
            $fstoccq=strpos(" " + $postbody, "[size=1]", $lastoccq);
            $fstoccq1=strpos(" " + $postbody, "[size=1]", $fstoccq+1);
            $sndoccq=strpos(" " + $postbody, "[/size]", $fstoccq+1);
            $lastoccq=$fstoccq1;
         }
         if ($sndoccq > 0 && $fstoccq > 0){
            $result=substr($postbody, 0, $fstoccq-1) + "<span class='post1'>" + substr($postbody, $fstoccq+7,$sndoccq - $fstoccq - 8) + "</span>" + substr($postbody, $sndoccq+6);
         }else{
            break;
         }
      }
      // [size=2] [/size]
      $sndoccq=1;
      while ($sndoccq>0){
         $postbody=$result;
         $lastoccq=0;
         $sndoccq=2;
         $fstoccq1=1;
         while($fstoccq1 < $sndoccq + $fstoccq1){
            $fstoccq=strpos(" " + $postbody, "[size=2]", $lastoccq);
            $fstoccq1=strpos(" " + $postbody, "[size=2]", $fstoccq+1);
            $sndoccq=strpos(" " + $postbody, "[/size]", $fstoccq+1);
            $lastoccq=$fstoccq1;
         }
         if ($sndoccq > 0 && $fstoccq > 0){
            $result=substr($postbody, 0, $fstoccq-1) + "<span class='post2'>" + substr($postbody, $fstoccq+7,$sndoccq - $fstoccq - 8) + "</span>" + substr($postbody, $sndoccq+6);
         }else{
            break;
         }
      }
      // [size=3] [/size]
      $sndoccq=1;
      while ($sndoccq>0){
         $postbody=$result;
         $lastoccq=0;
         $sndoccq=2;
         $fstoccq1=1;
         while($fstoccq1<$sndoccq + $fstoccq1){
            $fstoccq=strpos(" " + $postbody, "[size=3]", $lastoccq);
            $fstoccq1=strpos(" " + $postbody, "[size=3]", $fstoccq+1);
            $sndoccq=strpos(" " + $postbody, "[/size]", $fstoccq+1);
            $lastoccq=$fstoccq1;
         }
         if ($sndoccq > 0 && $fstoccq > 0){
            $result=substr($postbody, 0, $fstoccq-1) + "<span class='post3'>" + substr($postbody, $fstoccq+7,$sndoccq - $fstoccq - 8) + "</span>" + substr($postbody, $sndoccq+6);
         }
         else{
            break;
         }
      }
      // [size=4] [/size]
      $sndoccq=1;
      while ($sndoccq>0){
         $postbody=$result;
         $lastoccq=0;
         $sndoccq=2;
         $fstoccq1=1;
         while($fstoccq1<$sndoccq + $fstoccq1){
            $fstoccq=strpos(" " + $postbody, "[size=4]", $lastoccq);
            $fstoccq1=strpos(" " + $postbody, "[size=4]", $fstoccq+1);
            $sndoccq=strpos(" " + $postbody, "[/size]", $fstoccq+1);
            $lastoccq=$fstoccq1;
         }
         if ($sndoccq > 0 && $fstoccq > 0){
            $result=substr($postbody, 0, $fstoccq-1) + "<span class='post4'>" + substr($postbody, $fstoccq+7,$sndoccq - $fstoccq - 8) + "</span>" + substr($postbody, $sndoccq+6);
         }
         else{
            break;
         }
      }
      // [size=5] [/size]
      $sndoccq=1;
      while ($sndoccq>0){
         $postbody=$result;
         $lastoccq=0;
         $sndoccq=2;
         $fstoccq1=1;
         while($fstoccq1<$sndoccq + $fstoccq1){
            $fstoccq=strpos(" " + $postbody, "[size=5]", $lastoccq);
            $fstoccq1=strpos(" " + $postbody, "[size=5]", $fstoccq+1);
            $sndoccq=strpos(" " + $postbody, "[/size]", $fstoccq+1);
            $lastoccq=$fstoccq1;
         }
         if ($sndoccq > 0 && $fstoccq > 0){
            $result=substr($postbody, 0, $fstoccq-1) + "<span class='post5'>" + substr($postbody, $fstoccq+7,$sndoccq - $fstoccq - 8) + "</span>" + substr($postbody, $sndoccq+6);
         }
         else{
            break;
         }
      }
      // [color=red] [/color]
      $sndoccq=1;
      while ($sndoccq>0){
         $postbody=$result;
         $lastoccq=0;
         $sndoccq=2;
         $fstoccq1=1;
         while($fstoccq1<$sndoccq + $fstoccq1){
            $fstoccq=strpos(" " + $postbody, "[color=red]", $lastoccq);
            $fstoccq1=strpos(" " + $postbody, "[color=red]", $fstoccq+1);
            $sndoccq=strpos(" " + $postbody, "[/color]", $fstoccq+1);
            $lastoccq=$fstoccq1;
         }
         if ($sndoccq > 0 && $fstoccq > 0){
            $result=substr($postbody, 0, $fstoccq-1) + "<font color='red'>" + substr($postbody, $fstoccq+10,$sndoccq - $fstoccq - 11) + "</font>" + substr($postbody, $sndoccq+7);
         }
         else{
            break;
         }
      }
      // [color=green] [/color]
      $sndoccq=1;
      while ($sndoccq>0){
         $postbody=$result;
         $lastoccq=0;
         $sndoccq=2;
         $fstoccq1=1;
         while($fstoccq1<$sndoccq + $fstoccq1){
            $fstoccq=strpos(" " + $postbody, "[color=green]", $lastoccq);
            $fstoccq1=strpos(" " + $postbody, "[color=green]", $fstoccq+1);
            $sndoccq=strpos(" " + $postbody, "[/color]", $fstoccq+1);
            $lastoccq=$fstoccq1;
         }
         if ($sndoccq > 0 && $fstoccq > 0){
            $result=substr($postbody, 0, $fstoccq-1) + "<font color='green'>" + substr($postbody, $fstoccq+12,$sndoccq - $fstoccq - 13) + "</font>" + substr($postbody, $sndoccq+7);
         }
         else{
            break;
         }
      }
      // [color=blue] [/color]
      $sndoccq=1;
      while ($sndoccq>0){
         $postbody=$result;
         $lastoccq=0;
         $sndoccq=2;
         $fstoccq1=1;
         while($fstoccq1<$sndoccq + $fstoccq1){
            $fstoccq=strpos(" " + $postbody, "[color=blue]", $lastoccq);
            $fstoccq1=strpos(" " + $postbody, "[color=blue]", $fstoccq+1);
            $sndoccq=strpos(" " + $postbody, "[/color]", $fstoccq+1);
            $lastoccq=$fstoccq1;
         }
         if ($sndoccq > 0 && $fstoccq > 0){
            $result=substr($postbody, 0, $fstoccq-1) + "<font color='blue'>" + substr($postbody, $fstoccq+11,$sndoccq - $fstoccq - 12) + "</font>" + substr($postbody, $sndoccq+7);
         }
         else{
            break;
         }
      }
      // [color=yellow] [/color]
      $sndoccq=1;
      while ($sndoccq>0){
         $postbody=$result;
         $lastoccq=0;
         $sndoccq=2;
         $fstoccq1=1;
         while($fstoccq1<$sndoccq + $fstoccq1){
            $fstoccq=strpos(" " + $postbody, "[color=yellow]", $lastoccq);
            $fstoccq1=strpos(" " + $postbody, "[color=yellow]", $fstoccq+1);
            $sndoccq=strpos(" " + $postbody, "[/color]", $fstoccq+1);
            $lastoccq=$fstoccq1;
         }
         if ($sndoccq > 0 && $fstoccq > 0){
            $result=substr($postbody, 0, $fstoccq-1) + "<font color='yellow'>" + substr($postbody, $fstoccq+13,$sndoccq - $fstoccq - 14) + "</font>" + substr($postbody, $sndoccq+7);
         }
         else{
            break;
         }
      }
      // [color=purple] [/color]
      $sndoccq=1;
      while ($sndoccq>0){
         $postbody=$result;
         $lastoccq=0;
         $sndoccq=2;
         $fstoccq1=1;
         while($fstoccq1<$sndoccq + $fstoccq1){
            $fstoccq=strpos(" " + $postbody, "[color=purple]", $lastoccq);
            $fstoccq1=strpos(" " + $postbody, "[color=purple]", $fstoccq+1);
            $sndoccq=strpos(" " + $postbody, "[/color]", $fstoccq+1);
            $lastoccq=$fstoccq1;
         }
         if ($sndoccq > 0 && $fstoccq > 0){
            $result=substr($postbody, 0, $fstoccq-1) + "<font color='purple'>" + substr($postbody, $fstoccq+13,$sndoccq - $fstoccq - 14) + "</font>" + substr($postbody, $sndoccq+7);
         }
         else{
            break;
         }
      }
      // [color=orange] [/color]
      $sndoccq=1;
      while ($sndoccq>0){
         $postbody=$result;
         $lastoccq=0;
         $sndoccq=2;
         $fstoccq1=1;
         while($fstoccq1<$sndoccq + $fstoccq1){
            $fstoccq=strpos(" " + $postbody, "[color=orange]", $lastoccq);
            $fstoccq1=strpos(" " + $postbody, "[color=orange]", $fstoccq+1);
            $sndoccq=strpos(" " + $postbody, "[/color]", $fstoccq+1);
            $lastoccq=$fstoccq1;
         }
         if ($sndoccq > 0 && $fstoccq > 0){
            $result=substr($postbody, 0, $fstoccq-1) + "<font color='orange'>" + substr($postbody, $fstoccq+13,$sndoccq - $fstoccq - 14) + "</font>" + substr($postbody, $sndoccq+7);
         }
         else{
            break;
         }
      }
      // [color=teal] [/color]
      $sndoccq=1;
      while ($sndoccq>0){
         $postbody=$result;
         $lastoccq=0;
         $sndoccq=2;
         $fstoccq1=1;
         while($fstoccq1<$sndoccq + $fstoccq1){
            $fstoccq=strpos(" " + $postbody, "[color=teal]", $lastoccq);
            $fstoccq1=strpos(" " + $postbody, "[color=teal]", $fstoccq+1);
            $sndoccq=strpos(" " + $postbody, "[/color]", $fstoccq+1);
            $lastoccq=$fstoccq1;
         }
         if ($sndoccq > 0 && $fstoccq > 0){
            $result=substr($postbody, 0, $fstoccq-1) + "<font color='teal'>" + substr($postbody, $fstoccq+11,$sndoccq - $fstoccq - 12) + "</font>" + substr($postbody, $sndoccq+7);
         }
         else{
            break;
         }
      }
      // [color=gray] [/color]
      $sndoccq=1;
      while ($sndoccq>0){
         $postbody=$result;
         $lastoccq=0;
         $sndoccq=2;
         $fstoccq1=1;
         while($fstoccq1<$sndoccq + $fstoccq1){
            $fstoccq=strpos(" " + $postbody, "[color=gray]", $lastoccq);
            $fstoccq1=strpos(" " + $postbody, "[color=gray]", $fstoccq+1);
            $sndoccq=strpos(" " + $postbody, "[/color]", $fstoccq+1);
            $lastoccq=$fstoccq1;
         }
         if ($sndoccq > 0 && $fstoccq > 0){
            $result=substr($postbody, 0, $fstoccq-1) + "<font color='gray'>" + substr($postbody, $fstoccq+11,$sndoccq - $fstoccq - 12) + "</font>" + substr($postbody, $sndoccq+7);
         }
         else{
            break;
         }
      }
      // [color=brown] [/color]
      $sndoccq=1;
      while ($sndoccq>0){
         $postbody=$result;
         $lastoccq=0;
         $sndoccq=2;
         $fstoccq1=1;
         while($fstoccq1<$sndoccq + $fstoccq1){
            $fstoccq=strpos(" " + $postbody, "[color=brown]", $lastoccq);
            $fstoccq1=strpos(" " + $postbody, "[color=brown]", $fstoccq+1);
            $sndoccq=strpos(" " + $postbody, "[/color]", $fstoccq+1);
            $lastoccq=$fstoccq1;
         }
         if ($sndoccq > 0 && $fstoccq > 0){
            $result=substr($postbody, 0, $fstoccq-1) + "<font color='brown'>" + substr($postbody, $fstoccq+12,$sndoccq - $fstoccq - 13) + "</font>" + substr($postbody, $sndoccq+7);
         }
         else{
            break;
         }
      }
      // [quote] [/quote]
      $sndoccq=1;
      while ($sndoccq>0){
         $postbody=$result;
         $lastoccq=0;
         $sndoccq=2;
         $fstoccq1=1;
         while($fstoccq1<$sndoccq + $fstoccq1){
            $fstoccq=strpos(" " + $postbody, "[quote]", $lastoccq);
            $fstoccq1=strpos(" " + $postbody, "[quote]", $fstoccq+1);
            $sndoccq=strpos(" " + $postbody, "[/quote]", $fstoccq+1);
            $lastoccq=$fstoccq1;
         }
         if ($sndoccq > 0 && $fstoccq > 0){
            $result=substr($postbody, 0, $fstoccq-1) + "<table align=\"center\" width=\"90%\"><tr><td class=tdquote><span class='quote'>" + substr($postbody, $fstoccq+6,$sndoccq - $fstoccq - 7) + " </span></td></tr></table>" + substr($postbody, $sndoccq+7);
         }
         else{
            break;
         }
      }
      // Гиперссылки
      $postbody=str_replace("<br", " <br",$result) + " ";
      int $pos=0;
      while (strpos(" " + $postbody, "http://", $pos) > 0) {
         int $npos=strpos(" " + $postbody, "http://", $pos)-1;
         int $epos=strpos($postbody, " ", $npos);
         int $slpos=strpos(" " + $postbody, "/", $npos+8);
         if ($npos< 5 || substr($postbody, $npos-5, 5).equals("[img]")) $postbody=substr($postbody, 0, $npos) + "<a href='" + substr($postbody, $npos, $epos-$npos) + "'><span class='nick'>" + substr($postbody, $npos+7, $slpos-$npos-8) + "</span></a>" + substr($postbody, $epos);
         $pos=$epos;

      }
      $result=$postbody;
      // [img] [/img] (<img border='0' src=') ('>)
      $postbody=$result;
      $result="";
      $lastocc=0;
      $sndocc=1;
      while($sndocc > 0)
      {
         $fstocc=strpos($postbody, "[img]", $lastocc);
         $sndocc=strpos($postbody, "[/img]", $fstocc);
         if(($fstocc > 0 && $sndocc > 0 && $lastocc > 0) || ($fstocc >= 0 && $sndocc > 0 && $lastocc== 0))
         {
            $result += substr($postbody, $lastocc, $fstocc - $lastocc);
            $result += "<img border='0' src='" + substr($postbody, $fstocc + 5, $sndocc - $fstocc - 5) + "'>";
            $lastocc = $sndocc + 6;
         }
         else
         {
            $result +=substr($postbody, $lastocc, strlen($postbody)-$lastocc);
            break;
         }
      }
      return $result;
   }   

   public static String fd_href(String $href_head){
      String $postbody=str_replace("<br", " <br", $href_head) + " ";
      int $pos=0;
      while (strpos(" " + $postbody, "http://", $pos) > 0) {
         int $npos=strpos(" " + $postbody, "http://", $pos)-1;
         int $epos=strpos($postbody, " ", $npos);
         int $slpos=strpos(" " + $postbody, "/", $npos+8);
         if ($npos < 5 || !substr($postbody, $npos-5, 5).equals("[img]")) $postbody=substr($postbody, 0, $npos) + "<a href='" + substr($postbody, $npos, $epos-$npos) + "'><span class='nick'>" + substr($postbody, $npos+7, $slpos-$npos-8) + "</span></a>" + substr($postbody, $epos);
         $pos=$epos;
   
      }
      return $postbody;
   }      
}
