/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.db.entity;

import java.util.Date;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public interface IUser {
   
   public static final String ID_FIELD_NAME = "id";
   public static final String NICK_FIELD_NAME = "nick";
   
   public void setAvatarApproved(Boolean ok_avatar);
   public Boolean getAvatarApproved();
   public void setAvatar(String avatar);
   public String getAvatar();
   public boolean isBanned();
   public void setId(Long id);
   public Long getId();
   public void setNick(String nick);
   public String getNick();
   public void setPass2(String pass2);
   public String getPass2();
   public void setPass(String pass);
   public String getPass();
   public boolean isLogined();
   public void setPt(int pt);
   public int getPt();
   public void setPp(int pp);
   public int getPp();
   public void setView(int view);
   public int getView();
   public void setBan(int ban);
   public int getBan();
   public void setShowAvatar(Boolean s_avatar);
   public Boolean getShowAvatar();
   public void setIsActive(Boolean isActive);
   public Boolean getIsActive();
   public void setActivateCode(Integer activateCode);
   public Integer getActivateCode();
   public void setFooter(String footer);
   public String getFooter();
   public void setTimeZone(Integer timeZone);
   public Integer getTimeZone();
   public void setLanguge(Integer languge);
   public Integer getLanguge();
   public void setHideIp(Boolean hideIp);
   public Boolean getHideIp();
   public void setShowIcq(Boolean showIcq);
   public Boolean getShowIcq();
   public void setIcq(String icq);
   public String getIcq();
   public void setShowBithday(Boolean showBithday);
   public Boolean getShowBithday();
   public void setShowSex(Boolean showSex);
   public Boolean getShowSex();
   public void setShowCountry(Boolean showCountry);
   public Boolean getShowCountry();
   public void setCountry(String country);
   public String getCountry();
   public void setShowCity(Boolean showCity);
   public Boolean getShowCity();
   public void setCity(String city);
   public String getCity();
   public void setShowName(Boolean showName);
   public Boolean getShowName();
   public void setShowMail(Boolean showMail);
   public Boolean getShowMail();
   public void setReg(Date reg);
   public Date getReg();
   public void setBith(Date bith);
   public Date getBith();
   public void setSex(Byte sex);
   public Byte getSex();
   public void setFam(String fam);
   public String getFam();
   public void setName(String name);
   public String getName();
   public void setWantSeeAvatars(Boolean wantSeeAvatars);
   public Boolean getWantSeeAvatars();
   public void setEmail(String email);
   public String getEmail();

}