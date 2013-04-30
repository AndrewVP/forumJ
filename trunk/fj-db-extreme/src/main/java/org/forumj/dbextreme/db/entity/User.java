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

   private int pp = 0;

   private int pt = 0;

   private int view = 0;

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
   
   private String email = null;

   /**
    * @return the eMail
    */
   @Override
   public String getEmail() {
      return email;
   }

   /**
    * @param eMail the eMail to set
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
   public Integer getBan() {
       return ban;
    }

    /**
     * @param ban the ban to set
     */
    @Override
   public void setBan(Integer ban) {
       this.ban = ban;
    }

    /**
     * @return the view
     */
    @Override
   public Integer getView() {
       return view;
    }

    /**
     * @param view the view to set
     */
    @Override
   public void setView(Integer view) {
       this.view = view;
    }

    /**
     * @return the pg
     */
    @Override
   public Integer getPp() {
       return pp;
    }

    /**
     * @param pg the pg to set
     */
    @Override
   public void setPp(Integer pp) {
       this.pp = pp;
    }

    /**
     * @return the pt
     */
    @Override
   public Integer getPt() {
       return pt;
    }

    /**
     * @param pt the pt to set
     */
    @Override
   public void setPt(Integer pt) {
       this.pt = pt;
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

   /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result
            + ((activateCode == null) ? 0 : activateCode.hashCode());
      result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
      result = prime * result
            + ((avatarApproved == null) ? 0 : avatarApproved.hashCode());
      result = prime * result + ban;
      result = prime * result + ((bith == null) ? 0 : bith.hashCode());
      result = prime * result + ((city == null) ? 0 : city.hashCode());
      result = prime * result + ((country == null) ? 0 : country.hashCode());
      result = prime * result + ((email == null) ? 0 : email.hashCode());
      result = prime * result + ((fam == null) ? 0 : fam.hashCode());
      result = prime * result + ((footer == null) ? 0 : footer.hashCode());
      result = prime * result + ((hideIp == null) ? 0 : hideIp.hashCode());
      result = prime * result + ((icq == null) ? 0 : icq.hashCode());
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
      result = prime * result + ((languge == null) ? 0 : languge.hashCode());
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      result = prime * result + ((nick == null) ? 0 : nick.hashCode());
      result = prime * result + ((pass == null) ? 0 : pass.hashCode());
      result = prime * result + ((pass2 == null) ? 0 : pass2.hashCode());
      result = prime * result + pp;
      result = prime * result + pt;
      result = prime * result + ((reg == null) ? 0 : reg.hashCode());
      result = prime * result + ((sex == null) ? 0 : sex.hashCode());
      result = prime * result
            + ((showAvatar == null) ? 0 : showAvatar.hashCode());
      result = prime * result
            + ((showBithday == null) ? 0 : showBithday.hashCode());
      result = prime * result + ((showCity == null) ? 0 : showCity.hashCode());
      result = prime * result
            + ((showCountry == null) ? 0 : showCountry.hashCode());
      result = prime * result + ((showIcq == null) ? 0 : showIcq.hashCode());
      result = prime * result + ((showMail == null) ? 0 : showMail.hashCode());
      result = prime * result + ((showName == null) ? 0 : showName.hashCode());
      result = prime * result + ((showSex == null) ? 0 : showSex.hashCode());
      result = prime * result + ((timeZone == null) ? 0 : timeZone.hashCode());
      result = prime * result + view;
      result = prime * result
            + ((wantSeeAvatars == null) ? 0 : wantSeeAvatars.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      User other = (User) obj;
      if (activateCode == null) {
         if (other.activateCode != null) {
            return false;
         }
      } else if (!activateCode.equals(other.activateCode)) {
         return false;
      }
      if (avatar == null) {
         if (other.avatar != null) {
            return false;
         }
      } else if (!avatar.equals(other.avatar)) {
         return false;
      }
      if (avatarApproved == null) {
         if (other.avatarApproved != null) {
            return false;
         }
      } else if (!avatarApproved.equals(other.avatarApproved)) {
         return false;
      }
      if (ban != other.ban) {
         return false;
      }
      if (bith == null) {
         if (other.bith != null) {
            return false;
         }
      } else if (other.bith == null || bith.getTime() != other.bith.getTime()) {
         return false;
      }
      if (city == null) {
         if (other.city != null) {
            return false;
         }
      } else if (!city.equals(other.city)) {
         return false;
      }
      if (country == null) {
         if (other.country != null) {
            return false;
         }
      } else if (!country.equals(other.country)) {
         return false;
      }
      if (email == null) {
         if (other.email != null) {
            return false;
         }
      } else if (!email.equals(other.email)) {
         return false;
      }
      if (fam == null) {
         if (other.fam != null) {
            return false;
         }
      } else if (!fam.equals(other.fam)) {
         return false;
      }
      if (footer == null) {
         if (other.footer != null) {
            return false;
         }
      } else if (!footer.equals(other.footer)) {
         return false;
      }
      if (hideIp == null) {
         if (other.hideIp != null) {
            return false;
         }
      } else if (!hideIp.equals(other.hideIp)) {
         return false;
      }
      if (icq == null) {
         if (other.icq != null) {
            return false;
         }
      } else if (!icq.equals(other.icq)) {
         return false;
      }
      if (id == null) {
         if (other.id != null) {
            return false;
         }
      } else if (!id.equals(other.id)) {
         return false;
      }
      if (isActive == null) {
         if (other.isActive != null) {
            return false;
         }
      } else if (!isActive.equals(other.isActive)) {
         return false;
      }
      if (languge == null) {
         if (other.languge != null) {
            return false;
         }
      } else if (!languge.equals(other.languge)) {
         return false;
      }
      if (name == null) {
         if (other.name != null) {
            return false;
         }
      } else if (!name.equals(other.name)) {
         return false;
      }
      if (nick == null) {
         if (other.nick != null) {
            return false;
         }
      } else if (!nick.equals(other.nick)) {
         return false;
      }
      if (pass == null) {
         if (other.pass != null) {
            return false;
         }
      } else if (!pass.equals(other.pass)) {
         return false;
      }
      if (pass2 == null) {
         if (other.pass2 != null) {
            return false;
         }
      } else if (!pass2.equals(other.pass2)) {
         return false;
      }
      if (pp != other.pp) {
         return false;
      }
      if (pt != other.pt) {
         return false;
      }
      if (reg == null) {
         if (other.reg != null) {
            return false;
         }
      } else if (other.reg == null || reg.getTime() != other.reg.getTime()) {
         return false;
      }
      if (sex == null) {
         if (other.sex != null) {
            return false;
         }
      } else if (!sex.equals(other.sex)) {
         return false;
      }
      if (showAvatar == null) {
         if (other.showAvatar != null) {
            return false;
         }
      } else if (!showAvatar.equals(other.showAvatar)) {
         return false;
      }
      if (showBithday == null) {
         if (other.showBithday != null) {
            return false;
         }
      } else if (!showBithday.equals(other.showBithday)) {
         return false;
      }
      if (showCity == null) {
         if (other.showCity != null) {
            return false;
         }
      } else if (!showCity.equals(other.showCity)) {
         return false;
      }
      if (showCountry == null) {
         if (other.showCountry != null) {
            return false;
         }
      } else if (!showCountry.equals(other.showCountry)) {
         return false;
      }
      if (showIcq == null) {
         if (other.showIcq != null) {
            return false;
         }
      } else if (!showIcq.equals(other.showIcq)) {
         return false;
      }
      if (showMail == null) {
         if (other.showMail != null) {
            return false;
         }
      } else if (!showMail.equals(other.showMail)) {
         return false;
      }
      if (showName == null) {
         if (other.showName != null) {
            return false;
         }
      } else if (!showName.equals(other.showName)) {
         return false;
      }
      if (showSex == null) {
         if (other.showSex != null) {
            return false;
         }
      } else if (!showSex.equals(other.showSex)) {
         return false;
      }
      if (timeZone == null) {
         if (other.timeZone != null) {
            return false;
         }
      } else if (!timeZone.equals(other.timeZone)) {
         return false;
      }
      if (view != other.view) {
         return false;
      }
      if (wantSeeAvatars == null) {
         if (other.wantSeeAvatars != null) {
            return false;
         }
      } else if (!wantSeeAvatars.equals(other.wantSeeAvatars)) {
         return false;
      }
      return true;
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
      builder.append(", pp=");
      builder.append(pp);
      builder.append(", pt=");
      builder.append(pt);
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
      builder.append(", email=");
      builder.append(email);
      builder.append("]");
      return builder.toString();
   }

   public boolean isModerator() {
      //TODO Переделать!!!!!!!!!
      return id.equals(new Long(3));
   }
}
