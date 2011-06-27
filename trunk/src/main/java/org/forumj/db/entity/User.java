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

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class User {

   private Long id = null;
   
   private String nick = null;
   
   private String pass = null;
   
   private String pass2 = null;
   
   private int pp = 0;
   
   private int pt = 0;
   
   private int view = 0;
   
   private int swowAvatars = 0;
   
   private int timezone = 0;
   
   private int ban = 0;
   
   /**
    * @return the ban
    */
   public int getBan() {
      return ban;
   }

   /**
    * @param ban the ban to set
    */
   public void setBan(int ban) {
      this.ban = ban;
   }

   /**
    * @return the v_avatars
    */
   public int getVavatars() {
      return swowAvatars;
   }

   /**
    * @param v_avatars the v_avatars to set
    */
   public void setVavatars(int v_avatars) {
      this.swowAvatars = v_avatars;
   }

   /**
    * @return the fd_timezone
    */
   public int getTimezone() {
      return timezone;
   }

   /**
    * @param fd_timezone the fd_timezone to set
    */
   public void setTimezone(int fd_timezone) {
      this.timezone = fd_timezone;
   }

   /**
    * @return the view
    */
   public int getView() {
      return view;
   }

   /**
    * @param view the view to set
    */
   public void setView(int view) {
      this.view = view;
   }

   /**
    * @return the pg
    */
   public int getPp() {
      return pp;
   }

   /**
    * @param pg the pg to set
    */
   public void setPp(int pp) {
      this.pp = pp;
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
    * @return the logined
    */
   public boolean isLogined() {
      return id != 0;
   }

   /**
    * @return the pass
    */
   public String getPass() {
      return pass;
   }

   /**
    * @param pass the pass to set
    */
   public void setPass(String pass) {
      this.pass = pass;
   }

   /**
    * @return the pass2
    */
   public String getPass2() {
      return pass2;
   }

   /**
    * @param pass2 the pass2 to set
    */
   public void setPass2(String pass2) {
      this.pass2 = pass2;
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
   
   public boolean isBanned(){
      return ban != 0;
   }
}
