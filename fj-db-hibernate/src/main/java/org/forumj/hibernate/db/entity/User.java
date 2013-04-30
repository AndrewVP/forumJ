package org.forumj.hibernate.db.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.forumj.common.db.entity.IUser;
import org.forumj.common.web.Locale;

@Entity
@Table(name="users")
public class User implements IUser {

	   
	@Id
	@Column(name="id")
	private Long id = null;

	@Column(name="nick")
	private String nick = null;

	@Column(name="pass")
	private String pass = null;

	@Column(name="pass2")
	private String pass2 = null;

	@Column(name="pp_def")
	private int pp = 0;

	@Column(name="pt_def")
	private int pt = 0;

	@Column(name="view_def")
	private int view = 0;

	@Column(name="v_avatars")
	private Boolean wantSeeAvatars = true;

	@Column(name="ban")
	private int ban = 0;

	@Column(name="avatar")
	private String avatar = " ";

	@Column(name="ok_avatar")
	private Boolean avatarApproved = true;

	@Column(name="s_avatar")
	private Boolean showAvatar = true;

	@Column(name="name")
	private String name = null;

	@Column(name="fam")
	private String fam = null;

	@Column(name="sex")
	private String sex = null;

	@Column(name="bith")
	private Date bith = null;

	@Column(name="reg")
	private Timestamp reg = null;

	@Column(name="smail")
	private Boolean showMail = false;

	@Column(name="sname")
	private Boolean showName = false;

	@Column(name="city")
	private String city = " ";

	@Column(name="scity")
	private Boolean showCity = false;

	@Column(name="country")
	private String country = " ";

	@Column(name="scountry")
	private Boolean showCountry = false;

	@Column(name="ssex")
	private Boolean showSex = false;

	@Column(name="sbith")
	private Boolean showBithday = false;

	@Column(name="icq")
	private String icq = " ";

	@Column(name="sicq")
	private Boolean showIcq = false;

	@Column(name="h_ip")
	private Boolean hideIp = false;

	@Column(name="lang")
	private Locale languge = Locale.UA;

	@Column(name="fd_timezone")
	private Integer timeZone = 2; 

	@Column(name="footer")
	private String footer = " ";

	@Column(name="activate_code")
	private Integer activateCode = 0;

	@Column(name="is_active")
	private Boolean isActive = true;
	   
	@Column(name="mail")
	private String email = null;

	@Column(name="is_approoved")
	private Boolean isApproved = true;

	public User() {
		super();
	}

	public User(Long id, String nick) {
		super();
		this.id = id;
		this.nick = nick;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the nick
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @return the pass2
	 */
	public String getPass2() {
		return pass2;
	}

	/**
	 * @return the pp
	 */
	public Integer getPp() {
		return pp;
	}

	/**
	 * @return the pt
	 */
	public Integer getPt() {
		return pt;
	}

	/**
	 * @return the view
	 */
	public Integer getView() {
		return view;
	}

	/**
	 * @return the wantSeeAvatars
	 */
	public Boolean getWantSeeAvatars() {
		return wantSeeAvatars;
	}

	/**
	 * @return the ban
	 */
	public Integer getBan() {
		return ban;
	}
	
	

	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * @return the avatarApproved
	 */
	public Boolean getAvatarApproved() {
		return avatarApproved;
	}

	/**
	 * @return the showAvatar
	 */
	public Boolean getShowAvatar() {
		return showAvatar;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the fam
	 */
	public String getFam() {
		return fam;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @return the bith
	 */
	public Date getBith() {
		return bith;
	}

	/**
	 * @return the reg
	 */
	public Timestamp getReg() {
		return reg;
	}

	/**
	 * @return the showMail
	 */
	public Boolean getShowMail() {
		return showMail;
	}

	/**
	 * @return the showName
	 */
	public Boolean getShowName() {
		return showName;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the showCity
	 */
	public Boolean getShowCity() {
		return showCity;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return the showCountry
	 */
	public Boolean getShowCountry() {
		return showCountry;
	}

	/**
	 * @return the showSex
	 */
	public Boolean getShowSex() {
		return showSex;
	}

	/**
	 * @return the showBithday
	 */
	public Boolean getShowBithday() {
		return showBithday;
	}

	/**
	 * @return the icq
	 */
	public String getIcq() {
		return icq;
	}

	/**
	 * @return the showIcq
	 */
	public Boolean getShowIcq() {
		return showIcq;
	}

	/**
	 * @return the hideIp
	 */
	public Boolean getHideIp() {
		return hideIp;
	}

	/**
	 * @return the languge
	 */
	public Locale getLanguge() {
		return languge;
	}

	/**
	 * @return the timeZone
	 */
	public Integer getTimeZone() {
		return timeZone;
	}

	/**
	 * @return the footer
	 */
	public String getFooter() {
		return footer;
	}

	/**
	 * @return the activateCode
	 */
	public Integer getActivateCode() {
		return activateCode;
	}

	/**
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the isApproved
	 */
	public Boolean getIsApproved() {
		return isApproved;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param nick the nick to set
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @param pass2 the pass2 to set
	 */
	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}

	/**
	 * @param pp the pp to set
	 */
	public void setPp(Integer pp) {
		this.pp = pp;
	}

	/**
	 * @param pt the pt to set
	 */
	public void setPt(Integer pt) {
		this.pt = pt;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(Integer view) {
		this.view = view;
	}

	/**
	 * @param wantSeeAvatars the wantSeeAvatars to set
	 */
	public void setWantSeeAvatars(Boolean wantSeeAvatars) {
		this.wantSeeAvatars = wantSeeAvatars;
	}

	/**
	 * @param ban the ban to set
	 */
	public void setBan(Integer ban) {
		this.ban = ban;
	}

	/**
	 * @param avatar the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * @param avatarApproved the avatarApproved to set
	 */
	public void setAvatarApproved(Boolean avatarApproved) {
		this.avatarApproved = avatarApproved;
	}

	/**
	 * @param showAvatar the showAvatar to set
	 */
	public void setShowAvatar(Boolean showAvatar) {
		this.showAvatar = showAvatar;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param fam the fam to set
	 */
	public void setFam(String fam) {
		this.fam = fam;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @param bith the bith to set
	 */
	public void setBith(Date bith) {
		this.bith = bith;
	}

	/**
	 * @param reg the reg to set
	 */
	public void setReg(Timestamp reg) {
		this.reg = reg;
	}

	/**
	 * @param showMail the showMail to set
	 */
	public void setShowMail(Boolean showMail) {
		this.showMail = showMail;
	}

	/**
	 * @param showName the showName to set
	 */
	public void setShowName(Boolean showName) {
		this.showName = showName;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param showCity the showCity to set
	 */
	public void setShowCity(Boolean showCity) {
		this.showCity = showCity;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @param showCountry the showCountry to set
	 */
	public void setShowCountry(Boolean showCountry) {
		this.showCountry = showCountry;
	}

	/**
	 * @param showSex the showSex to set
	 */
	public void setShowSex(Boolean showSex) {
		this.showSex = showSex;
	}

	/**
	 * @param showBithday the showBithday to set
	 */
	public void setShowBithday(Boolean showBithday) {
		this.showBithday = showBithday;
	}

	/**
	 * @param icq the icq to set
	 */
	public void setIcq(String icq) {
		this.icq = icq;
	}

	/**
	 * @param showIcq the showIcq to set
	 */
	public void setShowIcq(Boolean showIcq) {
		this.showIcq = showIcq;
	}

	/**
	 * @param hideIp the hideIp to set
	 */
	public void setHideIp(Boolean hideIp) {
		this.hideIp = hideIp;
	}

	/**
	 * @param languge the languge to set
	 */
	public void setLanguge(Locale languge) {
		this.languge = languge;
	}

	/**
	 * @param timeZone the timeZone to set
	 */
	public void setTimeZone(Integer timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * @param footer the footer to set
	 */
	public void setFooter(String footer) {
		this.footer = footer;
	}

	/**
	 * @param activateCode the activateCode to set
	 */
	public void setActivateCode(Integer activateCode) {
		this.activateCode = activateCode;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param isApproved the isApproved to set
	 */
	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	@Override
	public boolean isBanned() {
		return ban !=0;
	}

	@Override
	public boolean isLogined() {
		return id != 0;
	}

	@Override
	public boolean isModerator() {
		return id.equals(new Long(3));
	}
	
	

}
