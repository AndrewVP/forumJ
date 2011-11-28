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

import java.util.Date;

import org.forumj.common.db.entity.IFJThread;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJThread implements IFJThread{
   
   private Long lastPostId = null;

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
   private Date lastPostTime;

   /**
    * Ник автора последнего поста
    */
   private String lastPostNick;

   /**
    * Ник автора последнего поста
    */
   private Long lastPostAuthId = null;
   
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
    * Registration date
    */
   private Date regDate = null;

   /**
    * @return the lastPostAuthId
    */
   public Long getLastPostAuthId() {
      return lastPostAuthId;
   }

   /**
    * @param lastPostAuthId the lastPostAuthId to set
    */
   public void setLastPostAuthId(Long lastPostAuthId) {
      this.lastPostAuthId = lastPostAuthId;
   }

   /**
    * @return the regDate
    */
   public Date getRegDate() {
      return regDate;
   }

   /**
    * @param regDate the regDate to set
    */
   public void setRegDate(Date regDate) {
      this.regDate = regDate;
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
   public Date getLastPostTime() {
      return lastPostTime;
   }

   /**
    * @param lastPostTime the lastPostTime to set
    */
   public void setLastPostTime(Date lastPostTime) {
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

   public boolean isQuest(){
      return type == 1 || type == 2;
   }
   
}
