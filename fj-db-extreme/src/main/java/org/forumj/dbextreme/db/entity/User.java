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

import java.sql.*;

import org.forumj.common.db.entity.IUser;
import org.forumj.common.web.Locale;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class User implements IUser {

   private Long id = null;

   private String nick = null;

   private String pass = null;

   private String pass2 = null;

   private int threadsOnPage = 0;

   private int postsOnPage = 0;

   private long view = 0;

   private Boolean wantSeeAvatars = true;

   private int ban = 0;

   private String avatar = " ";

   private Boolean avatarApproved = true;

   private Boolean showAvatar = true;

   private String name = null;

   private String fam = null;

   private String sex = null;

   private Date bith = null;

   private Timestamp reg = null;

   private Boolean showMail = false;

   private Boolean showName = false;

   private String city = " ";

   private Boolean showCity = false;

   private String country = " ";

   private Boolean showCountry = false;

   private Boolean showSex = false;

   private Boolean showBithday = false;

   private String icq = " ";

   private Boolean showIcq = false;

   private Boolean hideIp = false;

   //TODO Must be default value
   private Locale languge = Locale.UA;

   private Integer timeZone = 2; 

   private String footer = " ";

   private Integer activateCode = 0;

   private Boolean isActive = true;

   private boolean approved = false;

   private String email = null;

   /**
    * @return the eMail
    */
   @Override
   public String getEmail() {
      return email;
   }

   /**
    * @param email the eMail to set
    */
   @Override
   public void setEmail(String email) {
      this.email = email;
   }

   @Override
   public Boolean getWantSeeAvatars() {
      return wantSeeAvatars;
   }

   public User() {
      super();
   }

   public User(Long id, String nick) {
      super();
      this.id = id;
      this.nick = nick;
   }

   @Override
   public void setWantSeeAvatars(Boolean wantSeeAvatars) {
      this.wantSeeAvatars = wantSeeAvatars;
   }

   @Override
   public String getName() {
      return name;
   }

   @Override
   public void setName(String name) {
      this.name = name;
   }

   @Override
   public String getFam() {
      return fam;
   }

   @Override
   public void setFam(String fam) {
      this.fam = fam;
   }

   @Override
   public String getSex() {
      return sex;
   }

   @Override
   public void setSex(String sex) {
      this.sex = sex;
   }

   @Override
   public Date getBith() {
      return bith;
   }

   @Override
   public void setBith(Date bith) {
      this.bith = bith;
   }

   @Override
   public Timestamp getReg() {
      return reg;
   }

   @Override
   public void setReg(Timestamp reg) {
      this.reg = reg;
   }

   @Override
   public Boolean getShowMail() {
      return showMail;
   }

   @Override
   public void setShowMail(Boolean showMail) {
      this.showMail = showMail;
   }

   @Override
   public Boolean getShowName() {
      return showName;
   }

   @Override
   public void setShowName(Boolean showName) {
      this.showName = showName;
   }

   @Override
   public String getCity() {
      return city;
   }

   @Override
   public void setCity(String city) {
      this.city = city;
   }

   @Override
   public Boolean getShowCity() {
      return showCity;
   }

   @Override
   public void setShowCity(Boolean showCity) {
      this.showCity = showCity;
   }

   @Override
   public String getCountry() {
      return country;
   }

   @Override
   public void setCountry(String country) {
      this.country = country;
   }

   @Override
   public Boolean getShowCountry() {
      return showCountry;
   }

   @Override
   public void setShowCountry(Boolean showCountry) {
      this.showCountry = showCountry;
   }

   @Override
   public Boolean getShowSex() {
      return showSex;
   }

   @Override
   public void setShowSex(Boolean showSex) {
      this.showSex = showSex;
   }

   @Override
   public Boolean getShowBithday() {
      return showBithday;
   }

   @Override
   public void setShowBithday(Boolean showBithday) {
      this.showBithday = showBithday;
   }

   @Override
   public String getIcq() {
      return icq;
   }

   @Override
   public void setIcq(String icq) {
      this.icq = icq;
   }

   @Override
   public Boolean getShowIcq() {
      return showIcq;
   }

   @Override
   public void setShowIcq(Boolean showIcq) {
      this.showIcq = showIcq;
   }

   @Override
   public Boolean getHideIp() {
      return hideIp;
   }

   @Override
   public void setHideIp(Boolean hideIp) {
      this.hideIp = hideIp;
   }

   @Override
   public Locale getLanguge() {
      return languge;
   }

   @Override
   public void setLanguge(Locale languge) {
      this.languge = languge;
   }

   @Override
   public Integer getTimeZone() {
      return timeZone;
   }

   @Override
   public void setTimeZone(Integer timeZone) {
      this.timeZone = timeZone;
   }

   @Override
   public String getFooter() {
      return footer;
   }

   @Override
   public void setFooter(String footer) {
      this.footer = footer;
   }

   @Override
   public Integer getActivateCode() {
      return activateCode;
   }

   @Override
   public void setActivateCode(Integer activateCode) {
      this.activateCode = activateCode;
   }

   @Override
   public Boolean getIsActive() {
      return isActive;
   }

   @Override
   public void setIsActive(Boolean isActive) {
      this.isActive = isActive;
   }

   @Override
   public Boolean getShowAvatar() {
      return showAvatar;
   }

   @Override
   public void setShowAvatar(Boolean s_avatar) {
      this.showAvatar = s_avatar;
   }

   /**
    * @return the ban
    */
    @Override
   public int getBan() {
       return ban;
    }

    /**
     * @param ban the ban to set
     */
    @Override
   public void setBan(int ban) {
       this.ban = ban;
    }

    /**
     * @return the view
     */
    @Override
   public long getView() {
       return view;
    }

    /**
     * @param view the view to set
     */
    @Override
   public void setView(long view) {
       this.view = view;
    }

    /**
     * @return the pg
     */
    public int getThreadsOnPage() {
       return threadsOnPage;
    }

    /**
     * @param threadsOnPage the pg to set
     */
    public void setThreadsOnPage(int threadsOnPage) {
       this.threadsOnPage = threadsOnPage;
    }

    /**
     * @return the postsOnPage
     */
    public int getPostsOnPage() {
       return postsOnPage;
    }

    /**
     * @param postsOnPage the postsOnPage to set
     */
    public void setPostsOnPage(int postsOnPage) {
       this.postsOnPage = postsOnPage;
    }

    /**
     * @return the logined
     */
    @Override
   public boolean isLogined() {
       return id != 0;
    }

    /**
     * @return the pass
     */
    @Override
   public String getPass() {
       return pass;
    }

    /**
     * @param pass the pass to set
     */
    @Override
   public void setPass(String pass) {
       this.pass = pass;
    }

    /**
     * @return the pass2
     */
    @Override
   public String getPass2() {
       return pass2;
    }

    /**
     * @param pass2 the pass2 to set
     */
    @Override
   public void setPass2(String pass2) {
       this.pass2 = pass2;
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

    @Override
   public boolean isBanned(){
       return ban != 0;
    }

    @Override
   public String getAvatar() {
       return avatar;
    }

    @Override
   public void setAvatar(String avatar) {
       this.avatar = avatar;
    }

    @Override
   public Boolean getAvatarApproved() {
       return avatarApproved;
    }

    @Override
   public void setAvatarApproved(Boolean ok_avatar) {
       this.avatarApproved = ok_avatar;
    }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("User [id=");
      builder.append(id);
      builder.append(", nick=");
      builder.append(nick);
      builder.append(", pass=");
      builder.append(pass);
      builder.append(", pass2=");
      builder.append(pass2);
      builder.append(", threadsOnPage=");
      builder.append(threadsOnPage);
      builder.append(", postsOnPage=");
      builder.append(postsOnPage);
      builder.append(", view=");
      builder.append(view);
      builder.append(", wantSeeAvatars=");
      builder.append(wantSeeAvatars);
      builder.append(", ban=");
      builder.append(ban);
      builder.append(", avatar=");
      builder.append(avatar);
      builder.append(", avatarApproved=");
      builder.append(avatarApproved);
      builder.append(", showAvatar=");
      builder.append(showAvatar);
      builder.append(", name=");
      builder.append(name);
      builder.append(", fam=");
      builder.append(fam);
      builder.append(", sex=");
      builder.append(sex);
      builder.append(", bith=");
      builder.append(bith);
      builder.append(", reg=");
      builder.append(reg);
      builder.append(", showMail=");
      builder.append(showMail);
      builder.append(", showName=");
      builder.append(showName);
      builder.append(", city=");
      builder.append(city);
      builder.append(", showCity=");
      builder.append(showCity);
      builder.append(", country=");
      builder.append(country);
      builder.append(", showCountry=");
      builder.append(showCountry);
      builder.append(", showSex=");
      builder.append(showSex);
      builder.append(", showBithday=");
      builder.append(showBithday);
      builder.append(", icq=");
      builder.append(icq);
      builder.append(", showIcq=");
      builder.append(showIcq);
      builder.append(", hideIp=");
      builder.append(hideIp);
      builder.append(", languge=");
      builder.append(languge);
      builder.append(", timeZone=");
      builder.append(timeZone);
      builder.append(", footer=");
      builder.append(footer);
      builder.append(", activateCode=");
      builder.append(activateCode);
      builder.append(", isActive=");
      builder.append(isActive);
      builder.append(", approved=");
      builder.append(approved);
      builder.append(", email=");
      builder.append(email);
      builder.append("]");
      return builder.toString();
   }

   public boolean isModerator() {
      //TODO Переделать!!!!!!!!!
      return id.equals(new Long(3));
   }

    @Override
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public boolean isApproved() {
        return approved;
    }
}
