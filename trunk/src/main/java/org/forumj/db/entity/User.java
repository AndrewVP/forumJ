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

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class User implements IUser {

   private Long id = null;

   private String nick = null;

   private String pass = null;

   private String pass2 = null;

   private int pp = 0;

   private int pt = 0;

   private int view = 0;

   private Boolean showAvatars = null;

   private int ban = 0;

   private String avatar = null;

   private Boolean ok_avatar = null;

   private Boolean s_avatar = null;

   private String name = null;

   private String fam = null;

   private Byte sex = null;

   private Date bith = null;

   private Date reg = null;

   private Boolean showMail = null;

   private Boolean showName = null;

   private String city = null;

   private Boolean showCity = null;

   private String country = null;

   private Boolean showCountry = null;

   private Boolean showSex = null;

   private Boolean showBithday = null;

   private String icq = null;

   private Boolean showIcq = null;

   private Boolean hideIp = null;

   private Integer languge = null;

   private Integer timeZone = null; 

   private String footer = null;

   private Integer activateCode = null;

   private Boolean isActive = null;
   
   private String email = null;

   /**
    * @return the eMail
    */
   public String getEmail() {
      return email;
   }

   /**
    * @param eMail the eMail to set
    */
   public void setEmail(String email) {
      this.email = email;
   }

   public Boolean getShowAvatars() {
      return showAvatars;
   }

   public User() {
      super();
   }

   public User(Long id, String nick) {
      super();
      this.id = id;
      this.nick = nick;
   }

   public void setShowAvatars(Boolean showAvatars) {
      this.showAvatars = showAvatars;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getFam() {
      return fam;
   }

   public void setFam(String fam) {
      this.fam = fam;
   }

   public Byte getSex() {
      return sex;
   }

   public void setSex(Byte sex) {
      this.sex = sex;
   }

   public Date getBith() {
      return bith;
   }

   public void setBith(Date bith) {
      this.bith = bith;
   }

   public Date getReg() {
      return reg;
   }

   public void setReg(Date reg) {
      this.reg = reg;
   }

   public Boolean getShowMail() {
      return showMail;
   }

   public void setShowMail(Boolean showMail) {
      this.showMail = showMail;
   }

   public Boolean getShowName() {
      return showName;
   }

   public void setShowName(Boolean showName) {
      this.showName = showName;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public Boolean getShowCity() {
      return showCity;
   }

   public void setShowCity(Boolean showCity) {
      this.showCity = showCity;
   }

   public String getCountry() {
      return country;
   }

   public void setCountry(String country) {
      this.country = country;
   }

   public Boolean getShowCountry() {
      return showCountry;
   }

   public void setShowCountry(Boolean showCountry) {
      this.showCountry = showCountry;
   }

   public Boolean getShowSex() {
      return showSex;
   }

   public void setShowSex(Boolean showSex) {
      this.showSex = showSex;
   }

   public Boolean getShowBithday() {
      return showBithday;
   }

   public void setShowBithday(Boolean showBithday) {
      this.showBithday = showBithday;
   }

   public String getIcq() {
      return icq;
   }

   public void setIcq(String icq) {
      this.icq = icq;
   }

   public Boolean getShowIcq() {
      return showIcq;
   }

   public void setShowIcq(Boolean showIcq) {
      this.showIcq = showIcq;
   }

   public Boolean getHideIp() {
      return hideIp;
   }

   public void setHideIp(Boolean hideIp) {
      this.hideIp = hideIp;
   }

   public Integer getLanguge() {
      return languge;
   }

   public void setLanguge(Integer languge) {
      this.languge = languge;
   }

   public Integer getTimeZone() {
      return timeZone;
   }

   public void setTimeZone(Integer timeZone) {
      this.timeZone = timeZone;
   }

   public String getFooter() {
      return footer;
   }

   public void setFooter(String footer) {
      this.footer = footer;
   }

   public Integer getActivateCode() {
      return activateCode;
   }

   public void setActivateCode(Integer activateCode) {
      this.activateCode = activateCode;
   }

   public Boolean getIsActive() {
      return isActive;
   }

   public void setIsActive(Boolean isActive) {
      this.isActive = isActive;
   }

   public Boolean getS_avatar() {
      return s_avatar;
   }

   public void setS_avatar(Boolean s_avatar) {
      this.s_avatar = s_avatar;
   }

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
    public Boolean getVavatars() {
       return showAvatars;
    }

    /**
     * @param v_avatars the v_avatars to set
     */
    public void setVavatars(boolean v_avatars) {
       this.showAvatars = v_avatars;
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

    public String getAvatar() {
       return avatar;
    }

    public void setAvatar(String avatar) {
       this.avatar = avatar;
    }

    public Boolean getOk_avatar() {
       return ok_avatar;
    }

    public void setOk_avatar(Boolean ok_avatar) {
       this.ok_avatar = ok_avatar;
    }
}
