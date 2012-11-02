package org.forumj.common.db.entity;

import java.util.Date;

public interface IFJAction {

   /**
    * @return the id
    */
   public Long getId();

   /**
    * @param id the id to set
    */
   public void setId(Long id);

   /**
    * @return the ip
    */
   public String getIp();

   /**
    * @param ip the ip to set
    */
   public void setIp(String ip);

   /**
    * @return the subnet
    */
   public String getSubnet();

   /**
    * @param subnet the subnet to set
    */
   public void setSubnet(String subnet);

   /**
    * @return the iserId
    */
   public long getUserId();

   /**
    * @param iserId the iserId to set
    */
   public void setUserId(long userId);

   /**
    * @return the date
    */
   public Date getDate();

   /**
    * @param date the date to set
    */
   public void setDate(Date date);

   /**
    * @return the servletName
    */
   public String getServletName();

   /**
    * @param servletName the servletName to set
    */
   public void setServletName(String servletName);

   /**
    * @return the uas
    */
   public String getUas();

   /**
    * @param uas the uas to set
    */
   public void setUas(String uas);

   /**
    * @return the action
    */
   public Integer getAction();

   /**
    * @param action the action to set
    */
   public void setAction(Integer action);

   /**
    * @return the refer
    */
   public String getRefer();

   /**
    * @param refer the refer to set
    */
   public void setRefer(String refer);

}