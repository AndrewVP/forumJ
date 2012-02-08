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
package org.forumj.dbextreme.db.entity;

import java.util.Date;

import org.forumj.common.db.entity.IFJThread;
import org.forumj.common.web.Pin;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJThread implements IFJThread{
   
   private Long lastPostId = null;

   /**
    * Тип прикрепления
    */
   private Pin dock = Pin.COMMON;

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
   @Override
   public Long getLastPostAuthId() {
      return lastPostAuthId;
   }

   /**
    * @param lastPostAuthId the lastPostAuthId to set
    */
   @Override
   public void setLastPostAuthId(Long lastPostAuthId) {
      this.lastPostAuthId = lastPostAuthId;
   }

   /**
    * @return the regDate
    */
   @Override
   public Date getRegDate() {
      return regDate;
   }

   /**
    * @param regDate the regDate to set
    */
   @Override
   public void setRegDate(Date regDate) {
      this.regDate = regDate;
   }

   /**
    * @return the dock
    */
   @Override
   public Pin getDock() {
      return dock;
   }

   /**
    * @param dock the dock to set
    */
   @Override
   public void setDock(Pin dock) {
      this.dock = dock;
   }

   /**
    * @return the head
    */
   @Override
   public String getHead() {
      return head;
   }

   /**
    * @param head the head to set
    */
   @Override
   public void setHead(String head) {
      this.head = head;
   }

   /**
    * @return the nick
    */
   @Override
   public String getNick() {
      return nick;
   }

   /**
    * @param nick the nick to set
    */
   @Override
   public void setNick(String nick) {
      this.nick = nick;
   }

   /**
    * @return the id
    */
   @Override
   public Long getId() {
      return id;
   }

   /**
    * @param id the id to set
    */
   @Override
   public void setId(Long id) {
      this.id = id;
   }

   /**
    * @return the lastPostTime
    */
   @Override
   public Date getLastPostTime() {
      return lastPostTime;
   }

   /**
    * @param lastPostTime the lastPostTime to set
    */
   @Override
   public void setLastPostTime(Date lastPostTime) {
      this.lastPostTime = lastPostTime;
   }

   /**
    * @return the lastPostNick
    */
   @Override
   public String getLastPostNick() {
      return lastPostNick;
   }

   /**
    * @param lpauthor the lpauthor to set
    */
   @Override
   public void setLastPostNick(String lastPostNick) {
      this.lastPostNick = lastPostNick;
   }

   /**
    * @return the pcount
    */
   @Override
   public int getPcount() {
      return pcount;
   }

   /**
    * @param pcount the pcount to set
    */
   @Override
   public void setPcount(int pcount) {
      this.pcount = pcount;
   }

   /**
    * @return the snid
    */
   @Override
   public Integer getSnid() {
      return snid;
   }

   /**
    * @param snid the snid to set
    */
   @Override
   public void setSnid(Integer snid) {
      this.snid = snid;
   }

   /**
    * @return the snall
    */
   @Override
   public Integer getSnall() {
      return snall;
   }

   /**
    * @param snall the snall to set
    */
   @Override
   public void setSnall(Integer snall) {
      this.snall = snall;
   }

   /**
    * @return the type
    */
   @Override
   public int getType() {
      return type;
   }

   /**
    * @param type the type to set
    */
   @Override
   public void setType(int type) {
      this.type = type;
   }

   /**
    * @return the folder
    */
   @Override
   public String getFolder() {
      return folder;
   }

   /**
    * @param folder the folder to set
    */
   @Override
   public void setFolder(String folder) {
      this.folder = folder;
   }

   /**
    * @return the disain
    */
   @Override
   public int getDisain() {
      return disain;
   }

   /**
    * @param disain the disain to set
    */
   @Override
   public void setDisain(int disain) {
      this.disain = disain;
   }

   /**
    * @return the pt
    */
   @Override
   public int getPt() {
      return pt;
   }

   /**
    * @param pt the pt to set
    */
   @Override
   public void setPt(int pt) {
      this.pt = pt;
   }

   /**
    * @return the pg
    */
   @Override
   public int getPg() {
      return pg;
   }

   /**
    * @param pg the pg to set
    */
   @Override
   public void setPg(int pg) {
      this.pg = pg;
   }

   /**
    * @return the authId
    */
   @Override
   public Long getAuthId() {
      return authId;
   }

   /**
    * @param authId the authId to set
    */
   @Override
   public void setAuthId(Long authId) {
      this.authId = authId;
   }

   /**
    * @return the lastPostId
    */
   @Override
   public Long getLastPostId() {
      return lastPostId;
   }

   /**
    * @param lastPostId the lastPostId to set
    */
   @Override
   public void setLastPostId(Long lastPostId) {
      this.lastPostId = lastPostId;
   }

   /**
    * @return the folderId
    */
   @Override
   public Long getFolderId() {
      return folderId;
   }

   /**
    * @param folderId the folderId to set
    */
   @Override
   public void setFolderId(Long folderId) {
      this.folderId = folderId;
   }

   @Override
   public boolean isQuest(){
      return type == 1 || type == 2;
   }
   
}
