/*
 * Copyright Andrew V. Pogrebnyak
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
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ua.com.diletant.forum.db.entity;

import static ua.com.diletant.forum.tool.PHP.*;
import static ua.com.diletant.forum.tool.Diletant.*;

import java.util.Map;

import ua.com.diletant.forum.exception.InvalidKeyException;
import ua.com.diletant.forum.tool.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class Post {

   /**
    * Ник
    */
   private String str_nick;

   /**
    * Id автора
    */
   private Long str_idu;

   /**
    * id поста
    */
   private Long str_id;

   /**
    * Заголовок
    */
   private String str_head;

   /**
    * Текст
    * @var unknown_type
    */
   private String str_body;

   /**
    * id Ветки
    * @var unknown_type
    */
   private Long str_t_id;

   /**
    * Количество редактрований
    * @var unknown_type
    */
   private int str_nred;

   /**
    * ip автора
    * @var unknown_type
    */
   private String str_ip;

   /**
    * Можно ли показывать ip
    * @var unknown_type
    */
   private Boolean str_hip;

   /**
    * Адрес аватары
    * @var unknown_type
    */
   private String str_avatar;

   /**
    * Показывать аватару
    * @var unknown_type
    */
   private Boolean str_s_avatar;

   /**
    * Показывать аватару
    * @var unknown_type
    */
   private Boolean str_ok_avatar;

   /**
    * Страна автора
    * @var unknown_type
    */
   private String str_country;

   /**
    * Город автора
    * @var unknown_type
    */
   private String str_city;

   /**
    * Показывать город
    * @var unknown_type
    */
   private Boolean str_s_city;

   /**
    * Подпись к посту
    * @var unknown_type
    */
   private String str_footer;

   /**
    * Домен автора
    * @var unknown_type
    */
   private String str_domen;

   /**
    * Показывать страну
    * @var unknown_type
    */
   private Boolean str_s_country;

   /**
    * Номер странцы
    * @var unknown_type
    */
   private int pageNumber;

   /**
    * Id темы
    * @var unknown_type
    */
   private Long idThread;

   /**
    * Resultset игнорируемых
    * @var unknown_type
    */
   private Long[] res_ignor = null;

   /**
    * Наличие игнора у посетителя
    * @var unknown_type
    */
   private Boolean has_ignor;

   /**
    * Локализация
    */
   private LocaleString locale;

   /**
    * Признак последнего поста
    * @var unknown_type
    */
   private Boolean isLastPost;

   /**
    * Признак опроса
    * @var unknown_type
    */
   private Boolean isQuest;

   /**
    * Отрисовывать ли ссылки для админа
    * @var unknown_type
    */
   private Boolean isAdminForvard;

   /**
    * Может ли пользователь добавлять варианты ответов
    * (для опросов)
    * @var unknown_type
    */
   private Boolean isUserCanAddAnswer;

   /**
    * Показывать ли аватару
    * @var unknown_type
    */
   private Boolean str_v_avatars;

   /**
    * Тип поиска 
    * @var unknown_type
    */
   private Integer is_found = null;

   /**
    * Массив поисковых слов
    * @var unknown_type
    */
   private String[] idWordsForms = null;

   /**
    * Текущая страница
    * @var unknown_type
    */
   private int pg;

   /**
    * Время создания поста
    *
    * @var Time
    */
   private Time postTime = null;

   /**
    * Время редактирования поста
    *
    * @var Time
    */
   private Time postEditTime = null;

   /**
    * Признак первого поста в ветке
    *
    * @var unknown_type
    */
   private Boolean isFirst = false;

   /**
    * Вопрос опроса
    *
    * @var string
    */
   private String question;

   /**
    * Количество вариантов ответа в опросе
    *
    * @var int
    */
   private int answerAmount;

   /**
    * Массив вариантов ответов в опросе
    *
    * @var array
    */
   private String[][] arrNodes = null;

   /**
    * Проголосовал ли уже текущий посетитель
    *
    * @var boolean
    */
   private Boolean userVote;

   /**
    * Количество голосов в опросе
    *
    * @var int
    */
   private int voicesAmount;

   /**
    * Текущий пользователь
    */
   private User currentUser = null;

   /**
    * Конструктор
    *
    * @param unknown_type $arrFetch
    * @param LocaleString $locale
    */
   public Post(Long idThread, Long idPost, LocaleString locale, 
         String[] idWordsForms, 
         Long[] res_ignor, 
         Boolean has_ignor, 
         Integer is_found, 
         Boolean isAdminForvard, 
         Boolean isQuest, 
         Boolean isUserCanAddAnswer,
         int pg,
         Boolean isFirst
         , User currentUser                              
   ){
      this.currentUser = currentUser;
      // Присваиваем локализованные сообщения
      this.locale = locale;
      // id ветки
      this.idThread = idThread;
      // 
      this.pg = pg;
      // 
      this.str_id = idPost;
      // Массив поисковых слов
      if (idWordsForms != null){
         this.idWordsForms = idWordsForms;
      }
      // тип поиска
      this.is_found = is_found;
      //
      this.isAdminForvard = isAdminForvard;
      // 
      this.isQuest = isQuest;
      // 
      this.isAdminForvard=isAdminForvard;
      // 
      this.isUserCanAddAnswer = isUserCanAddAnswer; 
      // 
      this.res_ignor = res_ignor;
      // 
      this.has_ignor = has_ignor;
      //
      this.isFirst = isFirst;;
   }

   public String toString(){
      String result = "";
      try {
         String rp;
         if (this.is_found == 1){
            this.str_nick = htmlspecialchars(this.str_nick);
            this.str_nick = str_ireplace(this.idWordsForms, "<span class='found'>" + this.idWordsForms + "</span>", this.str_nick);
         }else if(this.is_found == 2){
            rp = "[span class='found']$0[/span]";
            this.str_head = preg_replace(this.idWordsForms,rp, this.str_head);
         }else if(this.is_found == 3){
            rp = "[span class='found']$0[/span]";
            this.str_body = preg_replace(this.idWordsForms,rp, this.str_body);
         }
         if (trim(this.str_ip) == trim(this.str_domen)){
            this.str_domen = substr(this.str_domen, 0, strrpos(this.str_domen, " + ")+1) + "---";
         }else{
            this.str_domen = "---" + substr(this.str_domen, strpos(this.str_domen, " + "));
         }
         result +="<tr class=heads>";
         result += "<td  class=internal>";
         if (this.isLastPost) result += "<a name='end'></a>";
         result += "<a name='this.str_id'>&nbsp;</a>";
         result += "<a class=nik href='tema.php?id=" + this.idThread + "&msg=" + this.str_id + "#" + this.str_id + "'><b>&nbsp;&nbsp;" + fd_head(htmlspecialchars(this.str_head)) + "</b></a>";
         result += "</td></tr>";
         result += "<tr><td>";
         int div = 0;
         String div_ = "";
         if (this.has_ignor){
            if (in_array(this.str_idu, this.res_ignor)) div = 1;
         }
         result += "<span class='tbtextnread'>" + this.str_nick + "</span>&nbsp;" + chr(149);
         result += "&nbsp;<img border='0' src='smiles/icon_minipost.gif'>&nbsp;<span class='posthead'>" + this.postTime.toString("d.m.Y H:i") + "</span>&nbsp;";
         if (div != 0 && isset(this.currentUser) && this.str_idu!=this.currentUser.getId()){
            result += chr(149) + "&nbsp;<a class=\"posthead\" href=\"ignor.php?idi=" + this.str_idu + "&idt=" + this.idThread + "&idp=" + this.str_id + "&pg=" + this.pg + "\" rel=\"nofollow\">" + this.locale.getString("mess68") + "</a>";
         }
         result +="</td></tr>";
         result +="<tr><td>";
         if (div != 0){
            result += "<a href='#' onclick='togglemsg(\"dd" + this.str_id + "\"); return false;' rel='nofollow'>" + this.locale.getString("mess142") + "</a>";
            div_ =" style='visibility: hidden; display:none;'";
         }
         else {
            div_ ="";
         }
         result += "<div id=dd" + this.str_id.toString() + div_ + ">";
         if (!(isset(this.currentUser) && div > 0)){
            result += "<table width='100%'><tr><td valign=top class='matras'>";
            result += "<table style='table-layout:fixed;' width='170'><tr><td valign=top>";
            result += "<div style='padding:10px;'>";
            if (this.str_s_avatar && this.str_ok_avatar && trim(this.str_avatar).isEmpty() && this.str_v_avatars){
               result += "<a href='control.php?id=9'><img border='0' src='" + this.str_avatar + "' rel=\"nofollow\"></a>";
            }else{
               result += "<a href='control.php?id=9' rel='nofollow'><img border='0' src='smiles/no_avatar.gif'></a>";
            }
            result += "</div>";
            result += "<span class='posthead'><u>" + this.locale.getString("mess111") + "</u></span><br>";
            if (this.str_s_country || this.str_country==""){
               result += "<span class='posthead'>" + this.locale.getString("mess114") + "</span><br>";
            }else{
               result += "<span class='posthead'>" + this.str_country + "</span><br>";
            }
            result += "<span class='posthead'><u>" + this.locale.getString("mess112") + "</u></span><br>";
            if (this.str_s_city || this.str_city==""){
               result += "<span class='posthead'>" + this.locale.getString("mess114") + "</span><br>";
            }else{
               result += "<span class='posthead'>" + this.str_city + "</span><br>";
            }
            result += "</td></tr></table>";
            result += "</td><td valign='top' width='100%'>";
            result += "<table width='100%'>";
            if (this.isQuest){
               result += this.getQuest() ;
            }
            result +="<tr><td>";
            result +="<p class=post>" + fd_body(this.str_body) + "</p>";
            result +="</td></tr>";
            result += "</table></td></tr>";
            result +="<tr><td class='matras' colspan=2></td></tr>";
            result +="<tr><td class='matras'></td><td>";
            result +="<p class=post>" + fd_body(this.str_footer) + "</p>";
            result +="</td></tr>";
            result +="<tr><td align='RIGHT' width='100%' colspan=2>";
            if (this.str_nred>0){
               result += "<table class='matras' width='100%'>";
               result += "<tr><td align='LEFT'>";
               result += "<span class='posthead'>" + this.locale.getString("mess50") + this.str_nred + this.locale.getString("mess51") + this.postEditTime.toString("d.m.Y H:i") + "</span>";
            }
            else {
               result += "<table class='matras'>";
               result += "<tr><td align='LEFT'>";
               result += " ";
            }
            result +="</td>";
            if (isset(this.currentUser.getNick())) {
               if (this.isAdminForvard){
                  result += "<td align='CENTER' width='70'>";
                  result += "<span class='posthead'><a href='site.php?id=" + this.idThread + "&post=" + this.str_id + "' rel=\"nofollow\">" + this.locale.getString("mess162") + "</a></span>";
                  result += "</td>";
               }
               if (this.currentUser.getId()==this.str_idu) {
                  result += "<td align='CENTER' width='70'>";
                  result += "<span class='posthead'><a href='tema.php?id=" + this.idThread + "&reply=" + this.str_id + "#edit' rel=\"nofollow\">" + this.locale.getString("mess141") + "</a></span>";
                  result += "</td>";
               }
               else {
                  result += "<td align='CENTER' width='70'>";
                  result += "<span class='posthead'><a href='tema.php?id=" + this.idThread + "&reply=" + this.str_id + "#edit' rel=\"nofollow\">" + this.locale.getString("mess139") + "</a></span>";
                  result += "</td>";
                  result += "<td align='CENTER' width='70'>";
                  result += "<span class='posthead'><a href='tema.php?id=" + this.idThread + "&reply=" + this.str_id + "&ans=1#edit' rel=\"nofollow\">" + this.locale.getString("mess140") + "</a></span>";
                  result += "</td>";
               }}
            result += "</tr></table>";
            result += "</td></tr>";
            result += "</table>";
         }else{
            result += this.locale.getString("mess103");
         }
         result += "</div>";
         result += "</td></tr>";
      } catch (InvalidKeyException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
   }

   /**
    * Устанавливает текс поста
    *
    * @param unknown_type $strBody
    */
   public void setBody(String strBody){
      this.str_body = strBody;
   }

   /**
    * Устанавливает признак последнего поста в ветке
    *
    */
   public void setLastPost(){
      this.isLastPost = true; 
   }

   public void loadHeads(Map $arrFetch,long $add_hr, long $add_mn){
      // 
      this.str_nick = (String) $arrFetch.get("nick");
      // 
      this.str_idu = (Long) $arrFetch.get("auth");
      // 
      Time $time = new Time((Long) $arrFetch.get("post_time"));
      $time.add($add_hr, Time.HOUR);
      $time.add($add_mn, Time.MINUTE);
      this.postTime =  $time;
      // 
      this.str_head = stripslashes((String)$arrFetch.get("tilte"));
      // 
      //      this.str_type = $arrFetch.get("type");
      // 
      this.str_nred = (Integer) $arrFetch.get("nred");
      // 
      Time $edTime = new Time((Long) $arrFetch.get("post_edit_time"));
      $edTime.add($add_hr, Time.HOUR);
      $edTime.add($add_mn, Time.MINUTE);
      this.postEditTime = $edTime; 
      // 
      this.str_ip = (String) $arrFetch.get("ip");
      // 
      this.str_hip = (Boolean) $arrFetch.get("h_ip");
      // 
      this.str_avatar = (String) $arrFetch.get("avatar");
      // 
      this.str_s_avatar = (Boolean) $arrFetch.get("s_avatar");
      // 
      this.str_ok_avatar = (Boolean) $arrFetch.get("ok_avatar");
      // 
      this.str_v_avatars = (Boolean) $arrFetch.get("v_avatars");
      // 
      this.str_country = (String) $arrFetch.get("country");
      // 
      this.str_s_country = (Boolean) $arrFetch.get("scountry");
      // 
      this.str_city = (String) $arrFetch.get("city");
      // 
      this.str_s_city = (Boolean) $arrFetch.get("scity");
      // 
      this.str_footer = (String) $arrFetch.get("footer");
      // 
      this.str_domen = (String) $arrFetch.get("domen");
   }

   /**
    * Устанавливает признак первого поста в опросе
    * 
    */
   public void setQuest(){
      this.isQuest = true;
   }

   /**
    * Возвращает признак первого поста в ветке
    * 
    */
   public Boolean isFirst(){
      return this.isFirst;
   }

   /**
    * Устанавливает вопрос опроса
    *
    * @param string $question
    */
   public void setQuestion(String question){
      this.question = question;
   }

   /**
    * Возвращает вопрос опроса
    *
    * @return unknown
    */
   private String getQuestion(){
      return this.question;
   }

   /**
    * Устанавливает количество вариантов ответа в опросе
    *
    * @param int $answerAmount
    */
   public void setAnswerAmount(int answerAmount){
      this.answerAmount = answerAmount;
   }

   /**
    * Возвращает количество вариантов ответа в опросе
    * @return int
    */
   public Integer getAnswerAmount(){
      return this.answerAmount;
   }

   /**
    * Устанавливает количество голосов в опросе
    *
    * @param int $voicesAmount
    */
   public void setVoicesAmount(Integer voicesAmount){
      this.voicesAmount = voicesAmount;
   }

   /**
    * Возвращает количество голосов в опросе
    * @return int
    */
   public Integer getVoicesAmount(){
      return this.voicesAmount;
   }

   /**
    * Устанавливает варианты ответа в опросе
    *
    * @param array $arrNodes
    */
   public void setNodes(String[][] arrNodes){
      this.arrNodes = arrNodes;
   }

   /**
    * Возвращает варианты ответа в опросе
    * @return array
    */
   public String[][] getNodes(){
      return this.arrNodes;
   }

   /**
    * Устанавливает проголосовал ли уже текщий юзер
    *
    * @param boolean $arrNodes
    */
   public void setUserVote(Boolean userVote){
      this.userVote = userVote;
   }

   /**
    * Возвращает проголосовал ли уже текщий юзер
    * @return boolean
    */
   public Boolean isUserVote(){
      return this.userVote;
   }

   private String getQuest(){
      String result="";
      try {
         int $nvcs = this.getVoicesAmount();
         result +=("<tr><td>");
         result += "<p align=\"CENTER\"><font size=4><b>" + this.getQuestion() + "</b></font></p><br>";
         result +=("</td></tr>");
         result +=("<tr><td align=\"CENTER\">");
         String[][] $nodes = this.getNodes();
         if (isset(this.currentUser.getNick()) && !this.isUserVote()){
            result +=("<form  action='voice.php' method='POST'><table class=content>");
            for (int $iq1=1; $iq1<this.getAnswerAmount(); $iq1++){
               result +=("<tr><td class=voice_left align='right'>");
               String $in1 = $nodes[$iq1][0];
               String $check = "";
               if ($iq1==1) $check=" CHECKED";
               if (!"".equals($nodes[$iq1][1])){
                  result += this.locale.getString("mess143");
                  if ($nodes[$iq1][1].equals("1")){
                     result += "<b>" + $nodes[$iq1][2] + "</b>";
                  }
                  else{
                     result += "<b>" + this.locale.getString("mess144") + "</b>";
                  }
                  result += "</td><td class=voice_right align='left'>";
                  result +=("<input type='radio' name='ANSWER' value='$in1'>&nbsp;" + fd_smiles(fd_href(stripslashes($nodes[$iq1][1]))) + "<br>");
               }
               else {
                  result += "</td><td class=voice_right align='left'>";
                  result +=("<input type='radio' name='ANSWER' value='$in1'" + $check + ">&nbsp;" + fd_smiles(fd_href(stripslashes($nodes[$iq1][1]))) + "<br>");
               }
               result +=("</td></tr>");
            }
            result +=("<tr><td colspan='2' align='CENTER'>");
            result += "<input type=hidden name=\"IDU1\" size=\"20\" value=\"" + this.currentUser.getId() + "\">";
            result += "<input type=hidden name=\"AUT1\" size=\"20\" value=\"" + this.currentUser.getNick() + "\">";
            result += "<input type=hidden name=\"IDT1\" size=\"20\" value=\"" + this.idThread + "\">";
            if (isset(this.currentUser.getPass2())) {
               result += "<input type=hidden name=\"PS21\" size=\"20\" value=\"" + this.currentUser.getPass2() + "\">";
            }
            else {
               result += "<input type=hidden name=\"PS11\" size=\"20\" value=\"" + this.currentUser.getPass() + "\">";
            }
            result +="<input type='submit' value='" + this.locale.getString("mess145") + "' name='OK'>";
            result +=("</td></tr>");
            result +=("</table></form>");
            result +=("</td></tr>");
            if (this.isUserCanAddAnswer){
               int $iq3=0;
               for (int $iq2=1; $iq2<this.getAnswerAmount(); $iq2++){
                  if ($nodes[$iq2][3].equals(this.currentUser.getId().toString())) $iq3=1;
               }
               if ($iq3 > 0){
                  result +=("<tr><td>");
                  result +=("<form  action=\"uservoice.php\" method=\"POST\"><table align=\"CENTER\">");
                  result +=("<tr><td>");
                  result +=this.locale.getString("mess153") + ":<br>";
                  result +=("<input type=\"text\" name=\"P\" size=\"100\">");
                  result += "<input type=hidden name=\"IDU2\" size=\"20\" value=\"" + this.currentUser.getId() + "\">";
                  result += "<input type=hidden name=\"AUT2\" size=\"20\" value=\"" + this.currentUser.getNick() + "\">";
                  result += "<input type=hidden name=\"IDT2\" size=\"20\" value=\"" + this.idThread + "\">";
                  if (isset(this.currentUser.getPass2())) {
                     result += "<input type=hidden name=\"PS22\" size=\"20\" value=\"" + this.currentUser.getPass2() + "\">";
                  }
                  else {
                     result += "<input type=hidden name=\"PS12\" size=\"20\" value=\"" + this.currentUser.getPass() + "\">";
                  }
                  result +=("</td></tr>");
                  result +=("<tr><td align=\"CENTER\">");
                  result +="<input type='checkbox' name='HD' value='1' checked>&nbsp;" + this.locale.getString("mess146") + "<br>";
                  result +="<input type='submit' value='" + this.locale.getString("mess145") + "' name='OK'>";
                  result +=("</td></tr>");
                  result +=("</table></form>");
                  result +=("</td></tr>");
               }
            }
         }
         if ($nvcs > 0) $nvcs=1/10000000;
         result +=("<tr><td align=\"CENTER\">");
         result += "<b>" + this.locale.getString("mess152") + ": " + round($nvcs,0) + "</b>";
         result +=("</td></tr>");
         result +=("<tr><td align=\"CENTER\">");
         result += "<table align='CENTER' class=control>";
         result +=("<tr class=heads><th class='internal'>");
         result += this.locale.getString("mess147");
         result +=("</th><th class='internal'>");
         result += this.locale.getString("mess148");
         result +=("</th><th class='internal'>");
         result += this.locale.getString("mess149");
         result +=("</th><th class='internal' width='350'>");
         result += this.locale.getString("mess150");
         result +=("</th><th class='internal'>");
         result += this.locale.getString("mess151");
         result +=("</th></tr><tr>");
         for (int $iq11=1; $iq11<this.getAnswerAmount(); $iq11++){
            if (! "".equals($nodes[$iq11][1])){
               if ($nodes[$iq11][1].equals(1)){
                  result += "<td align='LEFT' class='internal'>" + $nodes[$iq11][4] + "</td>";
               }
               else{
                  result += "<td align='LEFT' class='internal'>" + this.locale.getString("mess144") + "</td>";
               }
            }
            else
            {
               result += "<td align='LEFT' class='internal'></td>";
            }
            result +=("<td class='internal'>" + fd_body(stripslashes($nodes[$iq11][1])) + "</td>");

            result +=("<td align='CENTER' class='internal'>");
            result +=($nodes[$iq11][5] + "</td>");
            result += "<td class='internal'><TABLE cellSpacing=0 cellPadding=0 width='" + round(( Integer.valueOf($nodes[$iq11][5]).intValue()/$nvcs)*300,0) + "' border=0><TR><TD align=left bgColor=red><FONT size=-3>&nbsp;</FONT></TD></TR></TABLE>";
            result +=("</td>");
            result +=("<td class='internal'>");
            result +=(round((Integer.valueOf($nodes[$iq11][5])/$nvcs)*100, 2)) + "%";
            result +=("</td></tr>");

         }
         result += "</table>";
         if (isset(this.currentUser.getId()) && this.isUserVote()){
            result += "<form method=\"POST\" action=\"delvoice.php\" align=\"CENTER\">";
            result += "<input type=hidden name=\"IDU\" size=\"20\" value=\"" + this.currentUser.getId() + "\">";
            result += "<input type=hidden name=\"AUT\" size=\"20\" value=\"" + this.currentUser.getNick() + "\">";
            result += "<input type=hidden name=\"IDT\" size=\"20\" value=\"" + this.idThread + "\">";
            if (isset(this.currentUser.getPass2())) {
               result += "<input type=hidden name=\"PS2\" size=\"20\" value=\"" + this.currentUser.getPass2() + "\">";
            }
            else {
               result += "<input type=hidden name=\"PS1\" size=\"20\" value=\"" + this.currentUser.getPass() + "\">";
            }

            result += "<input type='submit' value='" + this.locale.getString("mess161") + "'>";
            result += "</form>";
         }
         result +=("</td></tr>");
      } catch (InvalidKeyException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
   }
}
