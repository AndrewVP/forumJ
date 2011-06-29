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

import static org.forumj.tool.PHP.*;

import org.forumj.exception.InvalidKeyException;
import org.forumj.tool.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJThread{
   
   private Long lastPostId = null;

   /**
    * Локализация
    */
   private LocaleString locale;

   /**
    * Тип прикрепления
    */
   private int dock;

   /**
    * Заголовок
    */
   private String head;

   /**
    * Ник автора
    */
   private String nick;

   /**
    * Id автора
    */
   private Long authId;
   
   /**
    * id ветки
    */
   private Long id;

   /**
    * время последнего поста
    */
   private String lastPostTime;

   /**
    * Ник автора последнего поста
    */
   private String lastPostNick;

   /**
    * Количество постов в ветке
    */
   private int pcount;

   /**
    * Количество просмотров участников
    */
   private Integer snid;

   /**
    * Количество просмотров полное
    */
   private Integer snall;

   /**
    * Тип ветки
    */
   private int type;

   /**
    * Папка
    */
   private String folder;

   /**
    * Папка
    */
   private Long folderId;
   
   /**
    * Дизайн
    */
   private int disain;

   /**
    * Количество постов на странице
    */
   private int pt;

   /**
    * Текущая страница
    */
   private int pg;

   /**
    * номер позиции
    */
   private int i;

   /**
    * Текущий пользователь
    */
   private User currentUser = null;

   public String toString(){
      String result = "";
      if (this.disain == 1) { 
         result += "<tr class=trees >";
      }else {
         result += "<tr class=matras>";   
      }
      // Картинки
      // Пиктограммка опроса
      result += "<td width='10' align='center' style='padding:0px 5px 0px 5px'>";
      if (this.type == 1 || this.type == 2){
         result += "<img border='0' src='smiles/quest.gif'>";
      }else{
         if (this.dock==5){
            result += "<img border='0' src='smiles/icon4.gif'>";
         }else if(this.dock==10) {
            result+="<img border='0' src='picts/f_pinned.gif'>";
         }else {
            result+="<img border='0' src='smiles/icon1.gif'>";
         }
      }
      result+="</td>";
      result+="<td width='1'></td>";
      // Тема
      result+="<td><p>";
      String str_head = htmlspecialchars(stripslashes(this.head));
      // Добавляем смайлики
      str_head = Diletant.fd_head(str_head);
      // Опрос? Добавляем "метку"
      try {
         if (this.type==1 || this.type==2){
            str_head="<b>" +this.locale.getString("mess9")+ "</b> " +str_head;
         }
         // Подписываем прикрепленные
         switch (this.dock){
         case 10:
            result+="<font class=trforum><b>" +this.locale.getString("mess7")+ " </b><a href='tema.php?id=" +this.id.toString() + "'>" +str_head+ "</a></font>";
            break;
         case 5:
            result+="<font class=trforum><b>" +this.locale.getString("mess8")+ " </b><a href='tema.php?id=" +this.id.toString() + "'>" +str_head+ "</a></font>";
            break;
         case 3:
            result+="<font class=trforum><b>" +this.locale.getString("mess163")+ " </b><a href='tema.php?id=" +this.id.toString() + "'>" +str_head+ "</a></font>";
            break;
         case 0:
            result+="<font class=trforum><a href='tema.php?id=" +this.id.toString()+ "'>" +str_head+ "</a></font>";
            break;
         }
         // Cсылки на страницы в ветке
         if (this.pcount+1>this.pt) {
            result+="<br><font size=1>" +this.locale.getString("mess10")+ ":&nbsp";
            int k1=0;
            int k2=0;
            for (int k=1; k<=ceil((this.pcount+1)/this.pt); k++) {
               k1=k1+1;
               if (k1==10){
                  result+="<a href='tema.php?page=" +k+ "&id=" +this.id.toString()+ "'>" +k+ "</a>";
                  if (k != ceil((this.pcount+1)/this.pt)) result+=",&nbsp;&nbsp;";
                  k1=0;
                  k2=k2+1;
               }
               if (k==1){
                  result+="<a href='tema.php?page=" +k+ "&id=" +this.id.toString()+ "'>" +k+ "</a>,&nbsp;&nbsp;";
               }
               if ((ceil((this.pcount+1)/this.pt)-k2*10)< 10 && (k-k2*10) != 0 && k!=1){
                  result+="<a href='tema.php?page=" +k+ "&id=" +this.id.toString()+ "'>" +k+ "</a>";
                  if (k != ceil((this.pcount+1)/this.pt)) result+=",&nbsp;&nbsp;";
               }

            }
            result+="</font>";
         }
         result+="</p></td>";
         // Количество постов
         result+="<td width='20' align='center' valign='middle'><span class='mnuforum' style='{color: purple}'>" +this.pcount;
         result+="</span><span id='posts" +this.id.toString()+ "' class='mnuforum' style='{color: red}'>&nbsp</span></td>";
         // кол-во просмотров
         result+="<td width='80' align='center' valign='middle'>";
         // Количество просмотров участников
         result+="<div class='mnuforum'><font size='1' color='green'>" + this.snid + "</font><br>";
         // Количество просмотров всего
         result+="<font size='1' color='purple'>" + this.snall + "</font></div></td>";
         // Автор
         result+="<td width='120' align='center' valign='middle'><div class='trforum'><font size='1'>" +htmlspecialchars(this.nick)+ "</font></div>";
         // Время создания
         result+="</td>";
         // Автор последнего поста
         result+="<td width='120' align=center><div class='mnuforum'><font size='1'>" +htmlspecialchars(this.lastPostNick)+ "</font></div>";
         // Время последнего поста
         result+="<div class='mnuforum'><a href='tema.php?id=" + this.id.toString() + "&end=1#end' rel='nofollow'><font size='1'>" + substr(this.lastPostTime, 0, 5) + "&nbsp;" + substr(this.lastPostTime, 6, 5) + "</font></a></div>";
         result+="</td>";
         // Папка
         result+="<td align='center' valign='middle'>";
         result+="<div class='mnuforum'><font size='1'>" +this.folder+ "</font></div>";
         result+="</td>";
         // Флажок (только для зарегистрированых)
         if (this.isLogin()){
            result+="<td align='center' valign='middle'>";
            result+="<input type='checkbox' id='ch" +this.i+ "' name='" +this.i+ "' value='" +this.id.toString()+ "'>";
            result+="</td>";
            result+="<td style='padding:0px 5px 0px 5px' align='right'>";
            result+="<a href='delone.php?id=" +this.id.toString()+ "&usr=" +String.valueOf(currentUser.getId())+ "&page=" +this.pg+ "'><img border='0' src='picts/del1.gif'></a>";
            result+="</td>";
         }
      } catch (InvalidKeyException e) {
         // TODO Доделать!
         e.printStackTrace();
      }
      return result;
   }

   /**
    * @return
    */
   private boolean isLogin() {
      return currentUser.isLogined();
   }

   /**
    * @return the locale
    */
   public LocaleString getLocale() {
      return locale;
   }

   /**
    * @param locale the locale to set
    */
   public void setLocale(LocaleString locale) {
      this.locale = locale;
   }

   /**
    * @return the dock
    */
   public int getDock() {
      return dock;
   }

   /**
    * @param dock the dock to set
    */
   public void setDock(int dock) {
      this.dock = dock;
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
    * @return the lastPostTime
    */
   public String getLastPostTime() {
      return lastPostTime;
   }

   /**
    * @param lastPostTime the lastPostTime to set
    */
   public void setLastPostTime(String lastPostTime) {
      this.lastPostTime = lastPostTime;
   }

   /**
    * @return the lastPostNick
    */
   public String getLastPostNick() {
      return lastPostNick;
   }

   /**
    * @param lpauthor the lpauthor to set
    */
   public void setLastPostNick(String lastPostNick) {
      this.lastPostNick = lastPostNick;
   }

   /**
    * @return the pcount
    */
   public int getPcount() {
      return pcount;
   }

   /**
    * @param pcount the pcount to set
    */
   public void setPcount(int pcount) {
      this.pcount = pcount;
   }

   /**
    * @return the snid
    */
   public Integer getSnid() {
      return snid;
   }

   /**
    * @param snid the snid to set
    */
   public void setSnid(Integer snid) {
      this.snid = snid;
   }

   /**
    * @return the snall
    */
   public Integer getSnall() {
      return snall;
   }

   /**
    * @param snall the snall to set
    */
   public void setSnall(Integer snall) {
      this.snall = snall;
   }

   /**
    * @return the type
    */
   public int getType() {
      return type;
   }

   /**
    * @param type the type to set
    */
   public void setType(int type) {
      this.type = type;
   }

   /**
    * @return the folder
    */
   public String getFolder() {
      return folder;
   }

   /**
    * @param folder the folder to set
    */
   public void setFolder(String folder) {
      this.folder = folder;
   }

   /**
    * @return the disain
    */
   public int getDisain() {
      return disain;
   }

   /**
    * @param disain the disain to set
    */
   public void setDisain(int disain) {
      this.disain = disain;
   }

   /**
    * @return the pt
    */
   public int getPt() {
      return pt;
   }

   /**
    * @param pt the pt to set
    */
   public void setPt(int pt) {
      this.pt = pt;
   }

   /**
    * @return the pg
    */
   public int getPg() {
      return pg;
   }

   /**
    * @param pg the pg to set
    */
   public void setPg(int pg) {
      this.pg = pg;
   }

   /**
    * @return the i
    */
   public int getI() {
      return i;
   }

   /**
    * @param i the i to set
    */
   public void setI(int i) {
      this.i = i;
   }

   /**
    * @return the currentUser
    */
   public User getCurrentUser() {
      return currentUser;
   }

   /**
    * @param currentUser the currentUser to set
    */
   public void setCurrentUser(User currentUser) {
      this.currentUser = currentUser;
   }

   /**
    * @return the authId
    */
   public Long getAuthId() {
      return authId;
   }

   /**
    * @param authId the authId to set
    */
   public void setAuthId(Long authId) {
      this.authId = authId;
   }

   /**
    * @return the lastPostId
    */
   public Long getLastPostId() {
      return lastPostId;
   }

   /**
    * @param lastPostId the lastPostId to set
    */
   public void setLastPostId(Long lastPostId) {
      this.lastPostId = lastPostId;
   }

   /**
    * @return the folderId
    */
   public Long getFolderId() {
      return folderId;
   }

   /**
    * @param folderId the folderId to set
    */
   public void setFolderId(Long folderId) {
      this.folderId = folderId;
   }
   
   
}
