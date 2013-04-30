package org.forumj.hibernate.db.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.forumj.common.db.entity.IFJAction;

@Entity
@Table(name="fd_action")
public class FJAction implements IFJAction{

	   
	@Id
	@Column(name="id")
	private Long id;

	@Column(name="fd_ip")
	private String ip;
	
	@Column(name="fd_subnet")
	private String subnet;
	
	@Column(name="fd_user")
	private Long userId;
	
	@Column(name="fd_time")
	private Date date;
	
	@Column(name="fd_page")
	private String servletName;
	
	@Column(name="fd_refer")
	private String uas;
	
	@Column(name="fd_action")
	private Integer action;
	
	@Column(name="fd_reefer")
	private String refer;

	
	public FJAction(Long id, String ip, String subnet, Long userId, Date date,
			String servletName, String uas, Integer action, String refer) {
		super();
		this.id = id;
		this.ip = ip;
		this.subnet = subnet;
		this.userId = userId;
		this.date = date;
		this.servletName = servletName;
		this.uas = uas;
		this.action = action;
		this.refer = refer;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the ip
	 */
	
	public String getIp() {
		return ip;
	}

	/**
	 * @return the subnet
	 */
	
	public String getSubnet() {
		return subnet;
	}

	/**
	 * @return the userId
	 */
	
	public Long getUserId() {
		return userId;
	}

	/**
	 * @return the date
	 */
	
	public Date getDate() {
		return date;
	}

	/**
	 * @return the servletName
	 */
	
	public String getServletName() {
		return servletName;
	}

	/**
	 * @return the uas
	 */
	
	public String getUas() {
		return uas;
	}

	/**
	 * @return the action
	 */
	
	public Integer getAction() {
		return action;
	}

	/**
	 * @return the refer
	 */
	
	public String getRefer() {
		return refer;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @param subnet the subnet to set
	 */
	public void setSubnet(String subnet) {
		this.subnet = subnet;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @param servletName the servletName to set
	 */
	public void setServletName(String servletName) {
		this.servletName = servletName;
	}

	/**
	 * @param uas the uas to set
	 */
	public void setUas(String uas) {
		this.uas = uas;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(Integer action) {
		this.action = action;
	}

	/**
	 * @param refer the refer to set
	 */
	public void setRefer(String refer) {
		this.refer = refer;
	}
	
	

}
