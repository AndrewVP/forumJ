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

import java.text.SimpleDateFormat;
import java.util.Date;

import org.forumj.common.db.entity.IUser;

/**
 * Diletant custom php functions 
 * @author <a href="mailto:an.pogrebnyak@gmail.com'>&&rew V. Pogrebnyak</a>
 */
public class Diletant {

   public static String fd_head(String result){
      result = result.replace("[span class='found']", "<span class='found'>"); 
      result = result.replace("[/span]", "</span>"); 
      result = fd_smiles(result);
      result = fd_cenz(result);
      return result;
   }
   
   public static String removeSlashes(String text){
      while (text.contains("\\\"")){
         text = text.replace("\\\"", "\"");
      }
      return text;
   }

   public static String fd_smiles(String tmptxt) {
      tmptxt = tmptxt.replace(":)","<img border='0' src='smiles/smile_.gif'>");
      tmptxt = tmptxt.replace(":(","<img border='0' src='smiles/sad.gif'>");
      tmptxt=tmptxt.replace(":D","<img border='0' src='smiles/biggrin.gif'>");
      tmptxt=tmptxt.replace(":[russian]","<img border='0' src='smiles/russian.gif'>");
      tmptxt=tmptxt.replace(":[pioners]","<img border='0' src='smiles/take_example.gif'>");
      tmptxt=tmptxt.replace(":[beer]","<img border='0' src='smiles/drinks.gif'>");
      tmptxt=tmptxt.replace(":[no-no]","<img border='0' src='smiles/acute.gif'>");
      tmptxt=tmptxt.replace(":[nea]","<img border='0' src='smiles/nea.gif'>");
      tmptxt=tmptxt.replace(":[babruysk]","<img border='0' src='smiles/to_babruysk.gif'>");
      tmptxt=tmptxt.replace(":[ohi]","<img border='0' src='smiles/girl_sigh.gif'>");
      tmptxt=tmptxt.replace(":[klizma]","<img border='0' src='smiles/girl_hospital.gif'>");
      tmptxt=tmptxt.replace(":[king]","<img border='0' src='smiles/king2.gif'>");
      tmptxt=tmptxt.replace(":g)","<img border='0' src='smiles/girl_smile.gif'>");
      tmptxt=tmptxt.replace(":g(","<img border='0' src='smiles/girl_sad.gif'>");
      tmptxt=tmptxt.replace(":[blum]","<img border='0' src='smiles/girl_blum.gif'>");
      tmptxt=tmptxt.replace(":[ghaha]","<img border='0' src='smiles/girl_haha.gif'>");
      tmptxt=tmptxt.replace(":[gwacko]","<img border='0' src='smiles/girl_wacko.gif'>");
      tmptxt=tmptxt.replace(":[gmad]","<img border='0' src='smiles/girl_mad.gif'>");
      tmptxt=tmptxt.replace(":[ghide]","<img border='0' src='smiles/girl_hide.gif'>");
      tmptxt=tmptxt.replace(":[glove]","<img border='0' src='smiles/girl_in_love.gif'>");
      tmptxt=tmptxt.replace(":[gfish]","<img border='0' src='smiles/girl_prepare_fish.gif'>");
      tmptxt=tmptxt.replace(":[gcrazy]","<img border='0' src='smiles/girl_crazy.gif'>");
      tmptxt=tmptxt.replace(":[mblum]","<img border='0' src='smiles/blum3.gif'>");
      tmptxt=tmptxt.replace(":[toclue]","<img border='0' src='smiles/to_clue.gif'>");
      tmptxt=tmptxt.replace(":[snooks]","<img border='0' src='smiles/snooks.gif'>");
      tmptxt=tmptxt.replace(":[scare]","<img border='0' src='smiles/scare.gif'>");
      tmptxt=tmptxt.replace(":[scare2]","<img border='0' src='smiles/scare2.gif'>");
      tmptxt=tmptxt.replace(":[gwerewolf]","<img border='0' src='smiles/girl_werewolf.gif'>");
      tmptxt=tmptxt.replace(":[gdevil]","<img border='0' src='smiles/girl_devil.gif'>");
      tmptxt=tmptxt.replace(":[friends]","<img border='0' src='smiles/friends.gif'>");
      tmptxt=tmptxt.replace(":[taunt]","<img border='0' src='smiles/taunt.gif'>");
      tmptxt=tmptxt.replace(":[offtopic]","<img border='0' src='smiles/offtopic.gif'>");
      tmptxt=tmptxt.replace(":[queen]","<img border='0' src='smiles/queen.gif'>");
      tmptxt=tmptxt.replace(":[butcher]","<img border='0' src='smiles/butcher.gif'>");
      tmptxt=tmptxt.replace(":[rtfm]","<img border='0' src='smiles/rtfm.gif'>");
      tmptxt=tmptxt.replace(":[shok]","<img border='0' src='smiles/shok.gif'>");
      tmptxt=tmptxt.replace(":[kr2]","<img border='0' src='smiles/KidRock_02.gif'>");
      tmptxt=tmptxt.replace(":[kr5]","<img border='0' src='smiles/KidRock_05.gif'>");
      tmptxt=tmptxt.replace(":[kr7]","<img border='0' src='smiles/KidRock_07.gif'>");
      tmptxt=tmptxt.replace(":[kr4]","<img border='0' src='smiles/KidRock_04.gif'>");
      tmptxt=tmptxt.replace(":[whistle]","<img border='0' src='smiles/whistle.gif'>");
      tmptxt=tmptxt.replace(":[whatever]","<img border='0' src='smiles/whatever_01.gif'>");
      tmptxt=tmptxt.replace(":[vinsent]","<img border='0' src='smiles/vinsent.gif'>");
      tmptxt=tmptxt.replace(":[victory]","<img border='0' src='smiles/victory.gif'>");
      tmptxt=tmptxt.replace(":[triniti]","<img border='0' src='smiles/triniti.gif'>");
      tmptxt=tmptxt.replace(":[tommy]","<img border='0' src='smiles/tommy.gif'>");
      tmptxt=tmptxt.replace(":[to_keep_order]","<img border='0' src='smiles/to_keep_order.gif'>");
      tmptxt=tmptxt.replace(":[tease]","<img border='0' src='smiles/tease.gif'>");
      tmptxt=tmptxt.replace(":[suicide]","<img border='0' src='smiles/suicide.gif'>");
      tmptxt=tmptxt.replace(":[spruce_up]","<img border='0' src='smiles/spruce_up.gif'>");
      tmptxt=tmptxt.replace(":[slow]","<img border='0' src='smiles/slow.gif'>");
      tmptxt=tmptxt.replace(":[skull]","<img border='0' src='smiles/skull.gif'>");
      tmptxt=tmptxt.replace(":[rofl]","<img border='0' src='smiles/rofl.gif'>");
      tmptxt=tmptxt.replace(":[read]","<img border='0' src='smiles/read.gif'>");
      tmptxt=tmptxt.replace(":[rabbi]","<img border='0' src='smiles/rabbi.gif'>");
      tmptxt=tmptxt.replace(":[punish]","<img border='0' src='smiles/punish.gif'>");
      tmptxt=tmptxt.replace(":[pooh_door]","<img border='0' src='smiles/pooh_door.gif'>");
      tmptxt=tmptxt.replace(":[pioneer]","<img border='0' src='smiles/pioneer.gif'>");
      tmptxt=tmptxt.replace(":[ok]","<img border='0' src='smiles/ok.gif'>");
      tmptxt=tmptxt.replace(":[new_russian]","<img border='0' src='smiles/new_russian.gif'>");
      tmptxt=tmptxt.replace(":[moil]","<img border='0' src='smiles/moil.gif'>");
      tmptxt=tmptxt.replace(":[lazy2]","<img border='0' src='smiles/lazy2.gif'>");
      tmptxt=tmptxt.replace(":[jc]","<img border='0' src='smiles/Just_Cuz_11.gif'>");
      tmptxt=tmptxt.replace(":[hi]","<img border='0' src='smiles/hi.gif'>");
      tmptxt=tmptxt.replace(":[help]","<img border='0' src='smiles/help.gif'>");
      tmptxt=tmptxt.replace(":[heat]","<img border='0' src='smiles/heat.gif'>");
      tmptxt=tmptxt.replace(":[good]","<img border='0' src='smiles/good.gif'>");
      tmptxt=tmptxt.replace(":[fuck]","<img border='0' src='smiles/fuck.gif'>");
      tmptxt=tmptxt.replace(":[fool]","<img border='0' src='smiles/fool.gif'>");
      tmptxt=tmptxt.replace(":[flirt]","<img border='0' src='smiles/flirt.gif'>");
      tmptxt=tmptxt.replace(":[dntknw]","<img border='0' src='smiles/dntknw.gif'>");
      tmptxt=tmptxt.replace(":[dance2]","<img border='0' src='smiles/dance2.gif'>");
      tmptxt=tmptxt.replace(":[brunette]","<img border='0' src='smiles/brunette.gif'>");
      tmptxt=tmptxt.replace(":[angel]","<img border='0' src='smiles/angel.gif'>");
      tmptxt=tmptxt.replace(":[aleksey_01]","<img border='0' src='smiles/aleksey_01.gif'>");
      tmptxt=tmptxt.replace(":[girl_cray2]","<img border='0' src='smiles/girl_cray2.gif'>");
      tmptxt=tmptxt.replace(":[girl_cray3]","<img border='0' src='smiles/girl_cray3.gif'>");
      tmptxt=tmptxt.replace(":[girl_impossible]","<img border='0' src='smiles/girl_impossible.gif'>");
      tmptxt=tmptxt.replace(":[girl_wink]","<img border='0' src='smiles/girl_wink.gif'>");
      tmptxt=tmptxt.replace(":[girl_dance]","<img border='0' src='smiles/girl_dance.gif'>");
      tmptxt=tmptxt.replace(":[snoozer_18]","<img border='0' src='smiles/snoozer_18.gif'>");
      tmptxt=tmptxt.replace(":[drag_10]","<img border='0' src='smiles/drag_10.gif'>");
      tmptxt=tmptxt.replace(":[Koshechka_09]","<img border='0' src='smiles/Koshechka_09.gif'>");
      tmptxt=tmptxt.replace(":[Koshechka_11]","<img border='0' src='smiles/Koshechka_11.gif'>");
      tmptxt=tmptxt.replace(":[libelle_1]","<img border='0' src='smiles/libelle_1.gif'>");
      tmptxt=tmptxt.replace(":[connie_6]","<img border='0' src='smiles/connie_6.gif'>");
      tmptxt=tmptxt.replace(":[connie_1]","<img border='0' src='smiles/connie_1.gif'>");
      tmptxt=tmptxt.replace(":[aftar]","<img border='0' src='smiles/aftar.gif'>");
      tmptxt=tmptxt.replace(":[party]","<img border='0' src='smiles/party.gif'>");
      tmptxt=tmptxt.replace(":[smoke]","<img border='0' src='smiles/smoke.gif'>");
      tmptxt=tmptxt.replace(":[feminist]","<img border='0' src='smiles/feminist.gif'>");
      tmptxt=tmptxt.replace(":[spam_light]","<img border='0' src='smiles/spam_light.gif'>");
      tmptxt=tmptxt.replace(":[laie_32]","<img border='0' src='smiles/laie_32.gif'>");
      tmptxt=tmptxt.replace(":[laie_44]","<img border='0' src='smiles/laie_44.gif'>");
      tmptxt=tmptxt.replace(":[laie_48]","<img border='0' src='smiles/laie_48.gif'>");
//      tmptxt=tmptxt.replace(";)","<img border='0' src='smiles/wink3.gif'>");

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

   public static String fd_bbcode(String body) {
      String result = body.replace("\r\n","<br />").replace("\\", "");
      result = parce(result, "[span class='found']", "[/span]", "<span class='found'>", "</span>");
      // [b] [/b] <b> </b>
      result = parce(result, "[b]", "[/b]", "<b>", "</b>");
      // [i] [/i] <i> </i>
      result = parce(result, "[i]", "[/i]", "<i>", "</i>");
      // [u] [/u] <u> </u>
      result = parce(result, "[u]", "[/u]", "<u>", "</u>");
      // [s] [/s] <s> </s>
      result = parce(result, "[s]", "[/s]", "<s>", "</s>");
      // [size=1] [/size]
      result = parce(result, "[size=1]", "[/size]", "<span class='post1'>", "</span>");
      // [size=2] [/size]
      result = parce(result, "[size=2]", "[/size]", "<span class='post2'>", "</span>");
      // [size=3] [/size]
      result = parce(result, "[size=3]", "[/size]", "<span class='post3'>", "</span>");
      // [size=4] [/size]
      result = parce(result, "[size=4]", "[/size]", "<span class='post4'>", "</span>");
      // [size=5] [/size]
      result = parce(result, "[size=5]", "[/size]", "<span class='post5'>", "</span>");
      // [color=red] [/color]
      result = parce(result, "[color=red]", "[/color]", "<font color='red'>", "</font>");
      // [color=green] [/color]
      result = parce(result, "[color=green]", "[/color]", "<font color='green'>", "</font>");
      // [color=blue] [/color]
      result = parce(result, "[color=blue]", "[/color]", "<font color='blue'>", "</font>");
      // [color=yellow] [/color]
      result = parce(result, "[color=yellow]", "[/color]", "<font color='yellow'>", "</font>");
      // [color=purple] [/color]
      result = parce(result, "[color=purple]", "[/color]", "<font color='purple'>", "</font>");
      // [color=orange] [/color]
      result = parce(result, "[color=orange]", "[/color]", "<font color='orange'>", "</font>");
      // [color=teal] [/color]
      result = parce(result, "[color=teal]", "[/color]", "<font color='teal'>", "</font>");
      // [color=gray] [/color]
      result = parce(result, "[color=gray]", "[/color]", "<font color='gray'>", "</font>");
      // [color=brown] [/color]
      result = parce(result, "[color=brown]", "[/color]", "<font color='brown'>", "</font>");
      // [quote] [/quote]
      result = parce(result, "[quote]", "[/quote]", "<table align='center' width='90%'><tr><td class=tdquote><span class='quote'>", " </span></td></tr></table>");
      // [img] [/img] (<img border='0' src=') ('>)
      result = fd_href(result);
      result = parce(result, "[img]", "[/img]", "<img border='0' src='", "'>");
      result = parce(result, "[url]", "[/url]", "<a href='", "'>");
      // youtube
      result = parce(result, "[youtube]", "[/youtube]", "<iframe width='640' height='390' frameborder='0' allowfullscreen='' src='http://www.youtube.com/embed/", "'></iframe>");
      return result;
   }
   
   private static String parce(String source, String startCode, String endCode, String startReplace, String endReplace){
      StringBuffer result = new StringBuffer();
      int startCodePosition = source.indexOf(startCode);
      int endCodePosition = source.indexOf(endCode);
      if (startCodePosition > -1 && endCodePosition > startCodePosition){
         result.append(source.substring(0, startCodePosition));
         result.append(startReplace + source.substring(startCodePosition + startCode.length(), endCodePosition) + endReplace);
         result.append(source.substring(endCodePosition + endCode.length()));
      }else{
         return source;
      }
      return parce(result.toString(), startCode, endCode, startReplace, endReplace);
   }

   public static String fd_href(String href_head){
      String postbody;
      try {
         postbody = href_head.replace("<br", " <br") + " ";
         int pos=0;
         while ((" " + postbody).indexOf("http://", pos) > 0) {
            int npos=(" " + postbody).indexOf("http://", pos)-1;
            int epos = postbody.indexOf(" ", npos);
            int slpos = (" " + postbody).indexOf("/", npos+8);
            if (npos < 5 || !postbody.substring(npos-5, npos).equals("[img]")) postbody=postbody.substring(0, npos) + "<a href='" + postbody.substring(npos, epos) + "'><span class='nick'>" + postbody.substring(npos + 7, slpos - 1) + "</span></a>" + postbody.substring(epos);
            pos=epos;
         }
      } catch (Exception e) {
         System.out.println(href_head);
         e.printStackTrace();
         postbody = href_head;
      }
      return postbody;
   }      
   public static StringBuffer fd_button(String mess, String  onClick, String name, String numb){
      StringBuffer result = new StringBuffer();
      result.append("<table id='" + name + "_table' class='bttn" + numb + "' onclick='" + onClick + "'>");
      result.append("<tr>");
      result.append("<td class='bttn" + numb + "LeftTop'></td>");
      result.append("<td class='bttn" + numb + "Top'></td>");
      result.append("<td class='bttn" + numb + "RightTop'></td>");
      result.append("</tr>");
      result.append("<tr>");
      result.append("<td class='bttn" + numb + "Left'></td>");
      result.append("<td class='bttn" + numb + "Bg'>" + mess + "</td>");
      result.append("<td class='bttn" + numb + "Right'></td>");
      result.append("</tr>");
      result.append("<tr>");
      result.append("<td class='bttn" + numb + "LeftBtm'></td>");
      result.append("<td class='bttn" + numb + "Btm'></td>");
      result.append("<td class='bttn" + numb + "RightBtm'></td>");
      result.append("</tr>");
      result.append("</table>");
      return result;
   }
   public static StringBuffer fd_form_add(IUser user) {
      StringBuffer result = new StringBuffer();
      //Команда для обработки
      result.append("<input type='hidden' name='comand' />");
      // Автор
      result.append("<input type=hidden name='IDU' value='"+user.getId().toString()+"'>");
      result.append("<input type=hidden name='AUT' value='"+user.getNick()+"'>");
      // пароль автора
      if (user.getPass2() != null) {
         // кука
         result.append("<input type=hidden name='PS2' value='"+user.getPass2()+"'>");
      }else{
         // не кука
         result.append("<input type=hidden name='PS1' value='"+user.getPass()+"'>");
      }
      return result;
   }
   public static StringBuffer fd_input(String name, String value, String size, String numb){
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
      return input;
   }
   public static int fd_timezone_hr(int id_timezone){
      int result = 0;
      switch(id_timezone) {
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
   public static int fd_timezone_mn(int id_timezone){
      int result = 0;
      switch(id_timezone) {
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
      result.append("<img src='skin/standart/picts/s.gif' onclick=\"InsertTags('[s]','[/s]')\" alt='Зачеркнутый текст'>");
      result.append("&nbsp;");
      result.append("<img src='skin/standart/picts/1.gif' onclick=\"InsertTags('[size=1]','[/size]')\" alt='Размер шрифта 1'>");
      result.append("<img src='skin/standart/picts/2.gif' onclick=\"InsertTags('[size=2]','[/size]')\" alt='Размер шрифта 2'>");
      result.append("<img src='skin/standart/picts/3.gif' onclick=\"InsertTags('[size=3]','[/size]')\" alt='Размер шрифта 3'>");
      result.append("<img src='skin/standart/picts/4.gif' onclick=\"InsertTags('[size=4]','[/size]')\" alt='Размер шрифта 4'>");
      result.append("<img src='skin/standart/picts/5.gif' onclick=\"InsertTags('[size=5]','[/size]')\" alt='Размер шрифта 5'>");
      result.append("&nbsp;");
      result.append("<img src='skin/standart/picts/img.gif' onclick=\"InsertTags('[img]','[/img]')\" alt='Вставить картинку'>");
      result.append("<img src='skin/standart/picts/youtube.gif' onclick=\"InsertTags('[youtube]','[/youtube]')\" alt='Вставить видео youtube'>");
      result.append("<img src='skin/standart/picts/quote.gif' onclick=\"InsertTags('[quote]','[/quote]')\" alt='Добавить рамку'>");
      result.append("<br>");
      result.append("<SELECT style='margin-top:1px; font:11px verdana; border: solid 1px black;' name=fcolor  onchange=\"javascript: InsertTags('[color=' + document.post.fcolor.options[document.post.fcolor.selectedIndex].value + ']', '[/color]'); document.post.fcolor.selectedIndex=0;\">");
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
   public static boolean fd_ban(IUser user){
      return user.getBan() == 1;
   }

   @Deprecated
   public static String fd_nick(IUser user){
      return user.getNick();
   }
   
   public static StringBuffer unRegisteredPostOut(){
      StringBuffer buffer = new StringBuffer();
      buffer.append("<html>");
      buffer.append("<head>");
      buffer.append("<meta http-equiv='content-type' content='text/html; charset=windows-1251'>");
      buffer.append("<meta http-equiv='Refresh' content='5; url=auth.php?id=4.php'>");
      buffer.append("<title>");
      buffer.append("Мы не во всем Дилетанты!");
      buffer.append("</title>");
      buffer.append("</head>");
      // Цвет фона страницы
      buffer.append("<body bgcolor=#EFEFEF>");
      buffer.append("<font size='5'><b>Входить надо как все нормальные люди!</b></font>");
      buffer.append("</body>");
      buffer.append("</html>");
      return buffer;
   }

   public static StringBuffer errorOut(Throwable e){
      StringBuffer buffer = new StringBuffer();
      buffer.append("<html>");
      buffer.append("<head>");
      buffer.append("<meta http-equiv='content-type' content='text/html; charset=utf-8'>");
      buffer.append("<title>");
      buffer.append("Error");
      buffer.append("</title>");
      buffer.append("</head>");
      // Цвет фона страницы
      buffer.append("<body bgcolor=#EFEFEF>");
      buffer.append("<b>An error occurred:</b><br />");
      buffer.append("<b>" + e.getMessage() + "</b><br />");
      StackTraceElement[] elements = e.getStackTrace();
      for (int i = 0; i < elements.length; i++) {
         StackTraceElement element = elements[i];
         buffer.append(element.toString() + "<br />");
      }
      buffer.append("</body>");
      buffer.append("</html>");
      return buffer;
   }
   
   public static StringBuffer blankPostOut(){
      StringBuffer buffer = new StringBuffer();
      buffer.append("<html>");
      buffer.append("<head>");
      buffer.append("<meta http-equiv='content-type' content='text/html; charset=windows-1251'>");
      buffer.append("<meta http-equiv='Refresh' content='5; url=index.php'>");
      buffer.append("<title>");
      buffer.append("Мы не во всем Дилетанты!");
      buffer.append("</title>");
      buffer.append("</head>");
      // Цвет фона страницы
      buffer.append("<body bgcolor=#EFEFEF>");
      buffer.append("<font size='5'><b>Шо, думаешь написано Дилетант, так тут все просто? Писать надо не только пробелами!</b></font>");
      buffer.append("</body>");
      buffer.append("</html>");
      return buffer;
   }

   public static StringBuffer twoAnswersReminedOut(){
      StringBuffer buffer = new StringBuffer();
      buffer.append("<html>");
      buffer.append("<head>");
      buffer.append("<meta http-equiv='content-type' content='text/html; charset=windows-1251'>");
      buffer.append("<meta http-equiv='Refresh' content='5; url=index.php'>");
      buffer.append("<title>");
      buffer.append("Мы не во всем Дилетанты!");
      buffer.append("</title>");
      buffer.append("</head>");
      // Цвет фона страницы
      buffer.append("<body bgcolor=#EFEFEF>");
      buffer.append("<font size='5'><b>Надо предложить хотя бы два варианта ответов!</b></font>");
      buffer.append("</body>");
      buffer.append("</html>");
      return buffer;
   }

   public static StringBuffer successPostOut(String seconds, String url){
      StringBuffer buffer = new StringBuffer();
      buffer.append("<html>");
      buffer.append("<head>");
      buffer.append("<meta http-equiv='Refresh' content='" + seconds + "; url=" + url + "'>");
      buffer.append("<title>");
      buffer.append("</title>");
      buffer.append("</head>");
      buffer.append("<body>");
      buffer.append("</body>");
      buffer.append("</html>");
      return buffer;
   }
   
   public static String dateToString(Date date, String pattern){
      SimpleDateFormat sdf = new SimpleDateFormat(pattern);
      return sdf.format(date);
   }
}
