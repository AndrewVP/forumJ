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
package org.forumj.db.entity;

import static org.forumj.tool.Diletant.*;
import static org.forumj.tool.PHP.*;

import java.util.*;

import org.forumj.exception.InvalidKeyException;
import org.forumj.tool.*;


/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class Post {

   /**
    * Ник
    */
   private String nick;

   /**
    * Id автора
    */
   private Long idu;

   /**
    * id поста
    */
   private Long id;

   /**
    * Заголовок
    */
   private String head;

   /**
    * Текст
    * @var unknown_type
    */
   private String body;

   /**
    * Количество редактрований
    * @var unknown_type
    */
   private int nred;

   /**
    * ip автора
    * @var unknown_type
    */
   private String ip;

   /**
    * Адрес аватары
    * @var unknown_type
    */
   private String avatar;

   /**
    * Показывать аватару
    * @var unknown_type
    */
   private Boolean showAvatar;

   /**
    * Показывать аватару
    * @var unknown_type
    */
   private Boolean showAvatarApproved;

   /**
    * Страна автора
    * @var unknown_type
    */
   private String country;

   /**
    * Город автора
    * @var unknown_type
    */
   private String city;

   /**
    * Показывать город
    * @var unknown_type
    */
   private Boolean showCity;

   /**
    * Подпись к посту
    * @var unknown_type
    */
   private String postFooter;

   /**
    * Домен автора
    * @var unknown_type
    */
   private String domen;

   /**
    * Показывать страну
    * @var unknown_type
    */
   private Boolean showCountry;

   /**
    * Id темы
    * @var unknown_type
    */
   private Long idThread;

   /**
    * Resultset игнорируемых
    * @var unknown_type
    */
   private List<Long> ignor = null;

   /**
    * Наличие игнора у посетителя
    * @var unknown_type
    */
   private Boolean hasIgnor;

   /**
    * Локализация
    */
   private LocaleString locale;

   /**
    * Признак последнего поста
    * @var unknown_type
    */
   private Boolean isLastPost = false;

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
   private Boolean wantSeeAvatars;

   /**
    * Тип поиска 
    * @var unknown_type
    */
   private Integer searchType = null;

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
   private List<QuestNode> arrNodes = null;

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
   
   private String tablePost = null;
   private String tableHead = null;

   /**
    * Конструктор
    *
    * @param unknown_type $arrFetch
    * @param LocaleString $locale
    */
   public Post(Long idThread, Long idPost, LocaleString locale, 
         String[] idWordsForms, 
         List<Long> res_ignor, 
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
      this.id = idPost;
      // Массив поисковых слов
      if (idWordsForms != null){
         this.idWordsForms = idWordsForms;
      }
      // тип поиска
      this.searchType = is_found;
      //
      this.isAdminForvard = isAdminForvard;
      // 
      this.isQuest = isQuest;
      // 
      this.isAdminForvard=isAdminForvard;
      // 
      this.isUserCanAddAnswer = isUserCanAddAnswer; 
      // 
      this.ignor = res_ignor;
      // 
      this.hasIgnor = has_ignor;
      //
      this.isFirst = isFirst;;
   }

   /**
    * @return the idThread
    */
   public Long getIdThread() {
      return idThread;
   }

   /**
    * @param idThread the idThread to set
    */
   public void setIdThread(Long idThread) {
      this.idThread = idThread;
   }

   /**
    * @return the tablePost
    */
   public String getTablePost() {
      return tablePost;
   }

   /**
    * @param tablePost the tablePost to set
    */
   public void setTablePost(String tablePost) {
      this.tablePost = tablePost;
   }

   /**
    * @return the tableHead
    */
   public String getTableHead() {
      return tableHead;
   }

   /**
    * @param tableHead the tableHead to set
    */
   public void setTableHead(String tableHead) {
      this.tableHead = tableHead;
   }

   /**
    * 
    */
   public Post(Long id) {
      this.id = id;
   }

   /**
    * @return the id
    */
   public Long getId() {
      return id;
   }

   /**
    * @param id the id to set
    */
   public void setId(Long id) {
      this.id = id;
   }

   /**
    * @return the body
    */
   public String getBody() {
      return body;
   }

   /**
    * @return the country
    */
   public String getCountry() {
      return country;
   }

   /**
    * @param country the country to set
    */
   public void setCountry(String country) {
      this.country = country;
   }

   /**
    * @return the city
    */
   public String getCity() {
      return city;
   }

   /**
    * @param city the city to set
    */
   public void setCity(String city) {
      this.city = city;
   }

   /**
    * @return the showCity
    */
   public Boolean getShowCity() {
      return showCity;
   }

   /**
    * @param showCity the showCity to set
    */
   public void setShowCity(Boolean showCity) {
      this.showCity = showCity;
   }

   /**
    * @return the postFooter
    */
   public String getPostFooter() {
      return postFooter;
   }

   /**
    * @param postFooter the postFooter to set
    */
   public void setPostFooter(String postFooter) {
      this.postFooter = postFooter;
   }

   /**
    * @return the domen
    */
   public String getDomen() {
      return domen;
   }

   /**
    * @param domen the domen to set
    */
   public void setDomen(String domen) {
      this.domen = domen;
   }

   /**
    * @return the showCountry
    */
   public Boolean getShowCountry() {
      return showCountry;
   }

   /**
    * @param showCountry the showCountry to set
    */
   public void setShowCountry(Boolean showCountry) {
      this.showCountry = showCountry;
   }

   /**
    * @return the wantSeeAvatars
    */
   public Boolean getWantSeeAvatars() {
      return wantSeeAvatars;
   }

   /**
    * @param wantSeeAvatars the wantSeeAvatars to set
    */
   public void setWantSeeAvatars(Boolean wantSeeAvatars) {
      this.wantSeeAvatars = wantSeeAvatars;
   }

   /**
    * @return the nick
    */
   public String getNick() {
      return nick;
   }

   /**
    * @param nick the nick to set
    */
   public void setNick(String nick) {
      this.nick = nick;
   }

   /**
    * @return the idu
    */
   public Long getIdu() {
      return idu;
   }

   /**
    * @param idu the idu to set
    */
   public void setIdu(Long idu) {
      this.idu = idu;
   }

   /**
    * @return the head
    */
   public String getHead() {
      return head;
   }

   /**
    * @param head the head to set
    */
   public void setHead(String head) {
      this.head = head;
   }

   /**
    * @return the nred
    */
   public int getNred() {
      return nred;
   }

   /**
    * @param nred the nred to set
    */
   public void setNred(int nred) {
      this.nred = nred;
   }

   /**
    * @return the ip
    */
   public String getIp() {
      return ip;
   }

   /**
    * @param ip the ip to set
    */
   public void setIp(String ip) {
      this.ip = ip;
   }

   /**
    * @return the avatar
    */
   public String getAvatar() {
      return avatar;
   }

   /**
    * @param avatar the avatar to set
    */
   public void setAvatar(String avatar) {
      this.avatar = avatar;
   }

   /**
    * @return the showAvatar
    */
   public Boolean getShowAvatar() {
      return showAvatar;
   }

   /**
    * @param showAvatar the showAvatar to set
    */
   public void setShowAvatar(Boolean showAvatar) {
      this.showAvatar = showAvatar;
   }

   /**
    * @return the showAvatarApproved
    */
   public Boolean getShowAvatarApproved() {
      return showAvatarApproved;
   }

   /**
    * @param showAvatarApproved the showAvatarApproved to set
    */
   public void setShowAvatarApproved(Boolean showAvatarApproved) {
      this.showAvatarApproved = showAvatarApproved;
   }

   /**
    * @return the postTime
    */
   public Time getPostTime() {
      return postTime;
   }

   /**
    * @param postTime the postTime to set
    */
   public void setPostTime(long postTime,long add_hr, long add_mn) {
      Time time = new Time(postTime);
      time.add(add_hr, Time.HOUR);
      time.add(add_mn, Time.MINUTE);
      this.postTime = time;
   }

   /**
    * @return the postEditTime
    */
   public Time getPostEditTime() {
      return postEditTime;
   }

   /**
    * @param postEditTime the postEditTime to set
    */
   public void setPostEditTime(Time postEditTime) {
      this.postEditTime = postEditTime;
   }

   public String toString(){
      String result = "";
      try {
         String rp;
         if (this.searchType == 1){
            this.nick = htmlspecialchars(this.nick);
            this.nick = str_ireplace(this.idWordsForms, "<span class='found'>" + this.idWordsForms + "</span>", this.nick);
         }else if(this.searchType == 2){
            rp = "[span class='found']$0[/span]";
            this.head = preg_replace(this.idWordsForms,rp, this.head);
         }else if(this.searchType == 3){
            rp = "[span class='found']$0[/span]";
            this.body = preg_replace(this.idWordsForms,rp, this.body);
         }
         if (trim(this.ip).equalsIgnoreCase(trim(this.domen))){
            this.domen = substr(this.domen, 0, strrpos(this.domen, ".")+1) + "---";
         }else{
            this.domen = "---" + substr(this.domen, strpos(this.domen, ".") + 1);
         }
         result +="<tr class=heads>";
         result += "<td  class=internal>";
         if (this.isLastPost) result += "<a name='end'></a>";
         result += "<a name='this.str_id'>&nbsp;</a>";
         result += "<a class=nik href='tema.php?id=" + this.idThread + "&msg=" + this.id + "#" + this.id + "'><b>&nbsp;&nbsp;" + fd_head(htmlspecialchars(this.head)) + "</b></a>";
         result += "</td></tr>";
         result += "<tr><td>";
         int div = 0;
         String div_ = "";
         if (this.hasIgnor){
            if (in_array(this.idu, this.ignor)) div = 1;
         }
         result += "<span class='tbtextnread'>" + this.nick + "</span>&nbsp;•";
         result += "&nbsp;<img border='0' src='smiles/icon_minipost.gif'>&nbsp;<span class='posthead'>" + this.postTime.toString("dd.MM.yyyy HH:mm") + "</span>&nbsp;";
         if (div != 0 && isset(this.currentUser) && this.idu!=this.currentUser.getId()){
            result += chr(149) + "&nbsp;<a class=\"posthead\" href=\"ignor.php?idi=" + this.idu + "&idt=" + this.idThread + "&idp=" + this.id + "&pg=" + this.pg + "\" rel=\"nofollow\">" + this.locale.getString("mess68") + "</a>";
         }
         result +="</td></tr>";
         result +="<tr><td>";
         if (div != 0){
            result += "<a href='#' onclick='togglemsg(\"dd" + this.id + "\"); return false;' rel='nofollow'>" + this.locale.getString("mess142") + "</a>";
            div_ =" style='visibility: hidden; display:none;'";
         }
         else {
            div_ ="";
         }
         result += "<div id=dd" + this.id.toString() + div_ + ">";
         if (!(isset(this.currentUser) && div > 0)){
            result += "<table width='100%'><tr><td valign=top class='matras'>";
            result += "<table style='table-layout:fixed;' width='170'><tr><td valign=top>";
            result += "<div style='padding:10px;'>";
            if (this.showAvatar && this.showAvatarApproved && trim(this.avatar).isEmpty() && this.wantSeeAvatars){
               result += "<a href='control.php?id=9'><img border='0' src='" + this.avatar + "' rel=\"nofollow\"></a>";
            }else{
               result += "<a href='control.php?id=9' rel='nofollow'><img border='0' src='smiles/no_avatar.gif'></a>";
            }
            result += "</div>";
            result += "<span class='posthead'><u>" + this.locale.getString("mess111") + "</u></span><br>";
            if (this.showCountry || this.country==""){
               result += "<span class='posthead'>" + this.locale.getString("mess114") + "</span><br>";
            }else{
               result += "<span class='posthead'>" + this.country + "</span><br>";
            }
            result += "<span class='posthead'><u>" + this.locale.getString("mess112") + "</u></span><br>";
            if (this.showCity || this.city==""){
               result += "<span class='posthead'>" + this.locale.getString("mess114") + "</span><br>";
            }else{
               result += "<span class='posthead'>" + this.city + "</span><br>";
            }
            result += "</td></tr></table>";
            result += "</td><td valign='top' width='100%'>";
            result += "<table width='100%'>";
            if (this.isQuest){
               result += this.getQuest() ;
            }
            result +="<tr><td>";
            result +="<p class=post>" + fd_body(this.body) + "</p>";
            result +="</td></tr>";
            result += "</table></td></tr>";
            result +="<tr><td class='matras' colspan=2></td></tr>";
            result +="<tr><td class='matras'></td><td>";
            result +="<p class=post>" + fd_body(this.postFooter) + "</p>";
            result +="</td></tr>";
            result +="<tr><td align='RIGHT' width='100%' colspan=2>";
            if (this.nred>0){
               result += "<table class='matras' width='100%'>";
               result += "<tr><td align='LEFT'>";
               result += "<span class='posthead'>" + this.locale.getString("mess50") + this.nred + this.locale.getString("mess51") + this.postEditTime.toString("d.m.Y H:i") + "</span>";
            }
            else {
               result += "<table class='matras'>";
               result += "<tr><td align='LEFT'>";
               result += " ";
            }
            result +="</td>";
            if(this.currentUser.isLogined()){
               if (this.isAdminForvard){
                  result += "<td align='CENTER' width='70'>";
                  result += "<span class='posthead'><a href='site.php?id=" + this.idThread + "&post=" + this.id + "' rel=\"nofollow\">" + this.locale.getString("mess162") + "</a></span>";
                  result += "</td>";
               }
               if (this.currentUser.getId() == this.idu) {
                  result += "<td align='CENTER' width='70'>";
                  result += "<span class='posthead'><a href='tema.php?id=" + this.idThread + "&reply=" + this.id + "#edit' rel=\"nofollow\">" + this.locale.getString("mess141") + "</a></span>";
                  result += "</td>";
               }else{
                  result += "<td align='CENTER' width='70'>";
                  result += "<span class='posthead'><a href='tema.php?id=" + this.idThread + "&reply=" + this.id + "#edit' rel=\"nofollow\">" + this.locale.getString("mess139") + "</a></span>";
                  result += "</td>";
                  result += "<td align='CENTER' width='70'>";
                  result += "<span class='posthead'><a href='tema.php?id=" + this.idThread + "&reply=" + this.id + "&ans=1#edit' rel=\"nofollow\">" + this.locale.getString("mess140") + "</a></span>";
                  result += "</td>";
               }
            }
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
      this.body = strBody;
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
      this.nick = (String) $arrFetch.get("nick");
      // 
      this.idu = (Long) $arrFetch.get("auth");
      // 
      Time $time = new Time((Long) $arrFetch.get("post_time"));
      $time.add($add_hr, Time.HOUR);
      $time.add($add_mn, Time.MINUTE);
      this.postTime =  $time;
      // 
      this.head = stripslashes((String)$arrFetch.get("tilte"));
      // 
      //      this.str_type = $arrFetch.get("type");
      // 
      this.nred = (Integer) $arrFetch.get("nred");
      // 
      Time $edTime = new Time((Long) $arrFetch.get("post_edit_time"));
      $edTime.add($add_hr, Time.HOUR);
      $edTime.add($add_mn, Time.MINUTE);
      this.postEditTime = $edTime; 
      // 
      this.ip = (String) $arrFetch.get("ip");
      // 
      this.avatar = (String) $arrFetch.get("avatar");
      // 
      this.showAvatar = (Boolean) $arrFetch.get("s_avatar");
      // 
      this.showAvatarApproved = (Boolean) $arrFetch.get("ok_avatar");
      // 
      this.wantSeeAvatars = (Boolean) $arrFetch.get("v_avatars");
      // 
      this.country = (String) $arrFetch.get("country");
      // 
      this.showCountry = (Boolean) $arrFetch.get("scountry");
      // 
      this.city = (String) $arrFetch.get("city");
      // 
      this.showCity = (Boolean) $arrFetch.get("scity");
      // 
      this.postFooter = (String) $arrFetch.get("footer");
      // 
      this.domen = (String) $arrFetch.get("domen");
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
   public void setNodes(List<QuestNode> arrNodes){
      this.arrNodes = arrNodes;
   }

   /**
    * Возвращает варианты ответа в опросе
    * @return array
    */
   public List<QuestNode> getNodes(){
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

   
   /**
    * @return the isUserCanAddAnswer
    */
   public Boolean getIsUserCanAddAnswer() {
      return isUserCanAddAnswer;
   }

   /**
    * @param isUserCanAddAnswer the isUserCanAddAnswer to set
    */
   public void setIsUserCanAddAnswer(Boolean isUserCanAddAnswer) {
      this.isUserCanAddAnswer = isUserCanAddAnswer;
   }

   private String getQuest(){
      String result="";
      try {
         int $nvcs = this.getVoicesAmount();
         result +=("<tr><td>");
         result += "<p align=\"CENTER\"><font size=4><b>" + this.getQuestion() + "</b></font></p><br>";
         result +=("</td></tr>");
         result +=("<tr><td align=\"CENTER\">");
         List<QuestNode> $nodes = this.getNodes();
         if (isset(this.currentUser.getNick()) && !this.isUserVote()){
            result +=("<form  action='voice.php' method='POST'><table class=content>");
            boolean first = true;
            for (Iterator<QuestNode> iterator = $nodes.iterator(); iterator.hasNext();) {
               QuestNode questNode = iterator.next();
               result +=("<tr><td class=voice_left align='right'>");
               String $check = "";
               if (first){
                  first = false;
                  $check=" CHECKED";
               }
               if (questNode.getUserId() == 0){
                  result += this.locale.getString("mess143");
                  if (questNode.getType() == 1){
                     result += "<b>" + questNode.getUserNick() + "</b>";
                  }
                  else{
                     result += "<b>" + this.locale.getString("mess144") + "</b>";
                  }
                  result += "</td><td class=voice_right align='left'>";
                  result +=("<input type='radio' name='ANSWER' value='$in1'>&nbsp;" + fd_smiles(fd_href(stripslashes(questNode.getNode()))) + "<br>");
               }
               else {
                  result += "</td><td class=voice_right align='left'>";
                  result +=("<input type='radio' name='ANSWER' value='$in1'" + $check + ">&nbsp;" + fd_smiles(fd_href(stripslashes(questNode.getNode()))) + "<br>");
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
               boolean userVotes = false;
               for (Iterator<QuestNode> iterator = $nodes.iterator(); iterator.hasNext();) {
                  QuestNode questNode = iterator.next();
                  if (questNode.getUserId() == this.currentUser.getId()) userVotes = true;
               }
               if (!userVotes){
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
         for (Iterator<QuestNode> iterator = $nodes.iterator(); iterator.hasNext();) {
            QuestNode questNode = iterator.next();
            if (questNode.getUserId() == 0){
               if (questNode.getType() == 1){
                  result += "<td align='LEFT' class='internal'>" + questNode.getUserNick() + "</td>";
               }
               else{
                  result += "<td align='LEFT' class='internal'>" + this.locale.getString("mess144") + "</td>";
               }
            }
            else
            {
               result += "<td align='LEFT' class='internal'></td>";
            }
            result +=("<td class='internal'>" + fd_body(stripslashes(questNode.getNode())) + "</td>");

            result +=("<td align='CENTER' class='internal'>");
            result +=(questNode.getGol() + "</td>");
            result += "<td class='internal'><TABLE cellSpacing=0 cellPadding=0 width='" + round((questNode.getGol()/($nvcs == 0 ? 1 : $nvcs))*300,0) + "' border=0><TR><TD align=left bgColor=red><FONT size=-3>&nbsp;</FONT></TD></TR></TABLE>";
            result +=("</td>");
            result +=("<td class='internal'>");
            result +=(round((questNode.getGol()/($nvcs == 0 ? 1 : $nvcs))*100, 2)) + "%";
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
