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
package org.forumj.tool;

import static org.forumj.tool.PHP.*;

import org.forumj.db.dao.UserDao;
import org.forumj.db.entity.User;

/**
 * Diletant custom php functions 
 * @author <a href="mailto:an.pogrebnyak@gmail.com'>&&rew V. Pogrebnyak</a>
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
            $result=substr($postbody, 0, $fstoccq-1) + "<table align=\"center\" width=\"90%\'><tr><td class=tdquote><span class='quote'>" + substr($postbody, $fstoccq+6,$sndoccq - $fstoccq - 7) + " </span></td></tr></table>" + substr($postbody, $sndoccq+7);
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
   public static String fd_button(String $mess, String  $onClick, String $name, String $numb){
      String $button="<table id='"+$name+"_table' class='bttn"+$numb+"' onclick='"+$onClick+"'>"+
      "<tr>"+
      "  <td class='bttn"+$numb+"LeftTop'></td>"+
      "  <td class='bttn"+$numb+"Top'></td>"+
      "  <td class='bttn"+$numb+"RightTop'></td>"+
      "</tr>"+
      "<tr>"+
      "  <td class='bttn"+$numb+"Left'></td>"+
      "  <td class='bttn"+$numb+"Bg'>"+$mess+"</td>"+
      "  <td class='bttn"+$numb+"Right'></td>"+
      "</tr>"+
      "<tr>"+
      "  <td class='bttn"+$numb+"LeftBtm'></td>"+
      "  <td class='bttn"+$numb+"Btm'></td>"+
      "  <td class='bttn"+$numb+"RightBtm'></td>"+
      "</tr>"+
      "</table>  ";
      return $button;
   }
   public static String fd_form_add(User user) {
      String $result="";
      //Команда для обработки
      $result+="<input type=hidden name='comand'>";
      // Автор
      $result+="<input type=hidden name='IDU' value='"+user.getId().toString()+"'>";
      $result+="<input type=hidden name='AUT' value='"+user.getNick()+"'>";
      // пароль автора
      if (isset(user.getPass2())) {
         // кука
         $result+="<input type=hidden name='PS2' value='"+user.getPass2()+"'>";
      }else{
         // не кука
         $result+="<input type=hidden name='PS1' value='"+user.getPass()+"'>";
      }
      return $result;
   }
   public static String fd_input(String name, String value, String size, String numb){
      StringBuffer input= new StringBuffer();
      input.append("<table id='" + name + "_table' class='input" + numb + "'>");
      input.append("<tr>");
      input.append("<td class='input" + numb + "LeftTop'></td>");
      input.append("<td class='input" + numb + "Top'></td>");
      input.append("<td class='input" + numb + "RightTop'></td>");
      input.append("</tr>");
      input.append("<tr>");
      input.append("<td class='input" + numb + "Left'></td>");
      input.append("<td class='input" + numb + "Bg'><input class='input" + numb + "' type=text name='" + name + "' size='" + size + "' value='" + value + "' maxlength='120'></td>");
      input.append("<td class='input" + numb + "Right'></td>");
      input.append("</tr>");
      input.append("<tr>");
      input.append("<td class='input" + numb + "LeftBtm'></td>");
      input.append("<td class='input" + numb + "Btm'></td>");
      input.append("<td class='input" + numb + "RightBtm'></td>");
      input.append("</tr>");
      input.append("</table>");    
      return input.toString();
   }
   public static int fd_timezone_hr(int $id_timezone){
      int result = 0;
      switch($id_timezone) {
         case 1:
            // (GMT - 12:00)
            result = -14;
            break;
         case 2:
            // (GMT - 11:00)
            result = -13;
            break;
         case 3:
            // (GMT - 10:00)
            result = -12;
            break;
         case 4:
            // (GMT - 9:00)
            result = -11;
            break;
         case 5:
            // (GMT - 8:00)
            result = -10;
            break;
         case 6:
            // (GMT - 7:00)
            result = -9;
            break;
         case 7:
            // (GMT - 6:00)
            result = -8;
            break;
         case 8:
            // (GMT - 5:00)
            result = -7;
            break;
         case 9:
            // (GMT - 4:00)
            result = -6;
            break;
         case 10:
            // (GMT - 3:30)
            result = -5;
            break;
         case 11:
            // (GMT - 3:00)
            result = -5;
            break;
         case 12:
            // (GMT - 2:00)
            result = -4;
            break;
         case 13:
            // (GMT - 1:00)
            result = -3;
            break;
         case 14:
            // (GMT)
            result = -2;
            break;
         case 15:
            // (GMT + 1:00)
            result = -1;
            break;
         case 16:
            // (GMT + 2:00)
            result = 0;
            break;
         case 17:
            // (GMT + 3:00)
            result = 1;
            break;
         case 18:
            // (GMT + 3:30)
            result = +1;
            break;
         case 19:
            // (GMT + 4:00)
            result = 2;
            break;
         case 20:
            // (GMT + 4:30)
            result = 2;
            break;
         case 21:
            // (GMT + 5:00)
            result = 3;
            break;
         case 22:
            // (GMT + 5:30)
            result = 3;
            break;
         case 23:
            // (GMT + 6:00)
            result = 4;
            break;
         case 24:
            // (GMT + 7:00)
            result = 5;
            break;
         case 25:
            // (GMT + 8:00)
            result = 6;
            break;
         case 26:
            // (GMT + 9:00)
            result = 7;
            break;
         case 27:
            // (GMT + 9:30)
            result = 7;
            break;
         case 28:
            // (GMT + 10:00)
            result = 8;
            break;
         case 29:
            // (GMT + 11:00)
            result = 9;
            break;
         case 30:
            // (GMT + 12:00)
            result = 10;
            break;
      }
      return result;

   }
   public static int fd_timezone_mn(int $id_timezone){
      int result = 0;
      switch($id_timezone) {
         case 10:
            // (GMT - 3:30)
            result = -30;
            break;
         case 18:
            // (GMT + 3:30)
            result = 30;
            break;
         case 20:
            // (GMT + 4:30)
            result = 30;
            break;
         case 22:
            // (GMT + 5:30)
            result = 30;
            break;
         case 27:
            // (GMT + 9:30)
            result = 30;
            break;
         default: 
            result = 0;
      }
      return result;
   }

   public static StringBuffer smiles_add(String mess11){
      StringBuffer result = new StringBuffer();
      result.append("<table>");
      result.append("<tr>");
      result.append("<td id='td1'>");
      /*Первая строчка Мальчики*/
      result.append("<a href='#' onclick=\"smile(':)'); return false;\" rel='nofollow'>");
      result.append("<img border='0' src='smiles/smile_.gif'>");
      result.append("</a>");
      result.append("<a href='#' onclick=\"smile(':('); return false;\" rel='nofollow'>");
      result.append("<img border='0' src='smiles/sad.gif'>");
      result.append("</a>");
      result.append("<a href='#' onclick=\"smile(';)'); return false;\" rel='nofollow'>");
      result.append("<img border='0' src='smiles/wink3.gif'>");
      result.append("</a>");
      result.append("</td>");
      result.append("</tr>");
      /*Вторая строчка Девочки*/
      result.append("<tr>");
      result.append("<td id='td2'>");
      result.append("<a href='#' onclick=\"smile(':g)'); return false;\" rel='nofollow'><img border='0' src='smiles/girl_smile.gif'></a>"); 
      result.append("<a href='#' onclick=\"smile(':g('); return false;\" rel='nofollow'><img border='0' src='smiles/girl_sad.gif'></a>"); 
      result.append("</td>");
      result.append("</tr>");
      /*Третья строчка Остальное*/
      result.append("<tr>");
      result.append("<td id='td3'></td>");
      result.append("</tr>");
      result.append("<tr>");
      result.append("<td align=center>");
      /* Кнопка "Показать все"*/
      result.append(fd_button(mess11,"viewsml();","btn1", "1"));
      result.append("<br><br><br>");
      result.append("</td>");
      result.append("</tr>");
      result.append("</table>");
      return result;
   }

   public static StringBuffer autotags_add(){
      StringBuffer result = new StringBuffer();
      result.append("<img src='skin/standart/picts/b.gif' onclick=\"InsertTags('[b]','[/b]')\" alt='Полужирный текст'>");
      result.append("<img src='skin/standart/picts/i.gif' onclick=\"InsertTags('[i]','[/i]')\" alt='Курсивный текст'>");
      result.append("<img src='skin/standart/picts/u.gif' onclick=\"InsertTags('[u]','[/u]')\" alt='Подчеркнутый текст'>");
      result.append("&nbsp;");
      result.append("<img src='skin/standart/picts/1.gif' onclick=\"InsertTags('[size=1]','[/size]')\" alt='Размер шрифта 1'>");
      result.append("<img src='skin/standart/picts/2.gif' onclick=\"InsertTags('[size=2]','[/size]')\" alt='Размер шрифта 2'>");
      result.append("<img src='skin/standart/picts/3.gif' onclick=\"InsertTags('[size=3]','[/size]')\" alt='Размер шрифта 3'>");
      result.append("<img src='skin/standart/picts/4.gif' onclick=\"InsertTags('[size=4]','[/size]')\" alt='Размер шрифта 4'>");
      result.append("<img src='skin/standart/picts/5.gif' onclick=\"InsertTags('[size=5]','[/size]')\" alt='Размер шрифта 5'>");
      result.append("&nbsp;");
      result.append("<img src='skin/standart/picts/img.gif' onclick=\"InsertTags('[img]','[/img]')\" alt='Вставить картинку'>");
      result.append("<img src='skin/standart/picts/quote.gif' onclick=\"InsertTags('[quote]','[/quote]')\" alt='Добавить рамку'>");
      result.append("<br>");
      result.append("<SELECT style='margin-top:1px; font:11px verdana; border: solid 1px black;' name=fcolor  onchange='javascript: InsertTags('[color=' + document.post.fcolor.options[document.post.fcolor.selectedIndex].value + ']', '[/color]'); document.post.fcolor.selectedIndex=0'>");
      result.append("<OPTION style='color:black' value='black'>Black</OPTION>");
      result.append("<OPTION style='color:red' value='red'>Red</OPTION>");
      result.append("<OPTION style='color:green' value='green'>Green</OPTION>");
      result.append("<OPTION style='color:blue' value='blue'>Blue</OPTION>");
      result.append("<OPTION style='color:yellow' value='yellow'>Yellow</OPTION>");
      result.append("<OPTION style='color:purple' value='purple'>Purple</OPTION>");
      result.append("<OPTION style='color:orange' value='orange'>Orange</OPTION>");
      result.append("<OPTION style='color:teal' value='teal'>Teal</OPTION>");
      result.append("<OPTION style='color:brown' value='brown'>Brown</OPTION>");
      result.append("<OPTION style='color:gray' value='gray'>Gray</OPTION>");
      result.append("</SELECT>");
      return result;
   }
   
   @Deprecated
   public static User fd_guard(Long userId, String password, boolean firstPassword){
      UserDao dao = new UserDao();
      User user = dao.loadUser(userId, password, firstPassword);
      return user;
   }
   
   @Deprecated
   public static boolean fd_ban(User user){
      return user.getBan() == 1;
   }

   @Deprecated
   public static String fd_nick(User user){
      return user.getNick();
   }
}
