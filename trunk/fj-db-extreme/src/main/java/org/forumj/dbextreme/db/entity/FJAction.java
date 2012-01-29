/*
 * Copyright (c) 2012
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
  */
package org.forumj.dbextreme.db.entity;

import java.util.Date;

import org.forumj.common.db.entity.IFJAction;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJAction implements IFJAction {
   
   private Long id;
   
   private String ip;
   
   private String subnet;
   
   private long userId;
   
   private Date date;
   
   private String servletName;
   
   private String uas;
   
   private Integer action;
   
   private String refer;

   /* (non-Javadoc)
    * @see org.forumj.dbextreme.db.entity.IFJAction#getId()
    */
   @Override
   public Long getId() {
      return id;
   }

   /* (non-Javadoc)
    * @see org.forumj.dbextreme.db.entity.IFJAction#setId(java.lang.Long)
    */
   @Override
   public void setId(Long id) {
      this.id = id;
   }

   /* (non-Javadoc)
    * @see org.forumj.dbextreme.db.entity.IFJAction#getIp()
    */
   @Override
   public String getIp() {
      return ip;
   }

   /* (non-Javadoc)
    * @see org.forumj.dbextreme.db.entity.IFJAction#setIp(java.lang.String)
    */
   @Override
   public void setIp(String ip) {
      this.ip = ip;
   }

   /* (non-Javadoc)
    * @see org.forumj.dbextreme.db.entity.IFJAction#getSubnet()
    */
   @Override
   public String getSubnet() {
      return subnet;
   }

   /* (non-Javadoc)
    * @see org.forumj.dbextreme.db.entity.IFJAction#setSubnet(java.lang.String)
    */
   @Override
   public void setSubnet(String subnet) {
      this.subnet = subnet;
   }

   /* (non-Javadoc)
    * @see org.forumj.dbextreme.db.entity.IFJAction#getIserId()
    */
   @Override
   public long getUserId() {
      return userId;
   }

   /* (non-Javadoc)
    * @see org.forumj.dbextreme.db.entity.IFJAction#setIserId(long)
    */
   @Override
   public void setUserId(long userId) {
      this.userId = userId;
   }

   /* (non-Javadoc)
    * @see org.forumj.dbextreme.db.entity.IFJAction#getDate()
    */
   @Override
   public Date getDate() {
      return date;
   }

   /* (non-Javadoc)
    * @see org.forumj.dbextreme.db.entity.IFJAction#setDate(java.util.Date)
    */
   @Override
   public void setDate(Date date) {
      this.date = date;
   }

   /* (non-Javadoc)
    * @see org.forumj.dbextreme.db.entity.IFJAction#getServletName()
    */
   @Override
   public String getServletName() {
      return servletName;
   }

   /* (non-Javadoc)
    * @see org.forumj.dbextreme.db.entity.IFJAction#setServletName(java.lang.String)
    */
   @Override
   public void setServletName(String servletName) {
      this.servletName = servletName;
   }

   /* (non-Javadoc)
    * @see org.forumj.dbextreme.db.entity.IFJAction#getUas()
    */
   @Override
   public String getUas() {
      return uas;
   }

   /* (non-Javadoc)
    * @see org.forumj.dbextreme.db.entity.IFJAction#setUas(java.lang.String)
    */
   @Override
   public void setUas(String uas) {
      this.uas = uas;
   }

   /* (non-Javadoc)
    * @see org.forumj.dbextreme.db.entity.IFJAction#getAction()
    */
   @Override
   public Integer getAction() {
      return action;
   }

   /* (non-Javadoc)
    * @see org.forumj.dbextreme.db.entity.IFJAction#setAction(java.lang.Integer)
    */
   @Override
   public void setAction(Integer action) {
      this.action = action;
   }

   /* (non-Javadoc)
    * @see org.forumj.dbextreme.db.entity.IFJAction#getRefer()
    */
   @Override
   public String getRefer() {
      return refer;
   }

   /* (non-Javadoc)
    * @see org.forumj.dbextreme.db.entity.IFJAction#setRefer(java.lang.String)
    */
   @Override
   public void setRefer(String refer) {
      this.refer = refer;
   }

}
