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
public class FJMail implements IFJMail {
   
   private Long id = null;
   
   private User sender = null;
   
   private User receiver = null;
   
   private Date createDate = null;
   
   private Date sentDate = null;
   
   private Date receiveDate = null;
   
   private Date readDate = null;
   
   private String subject = null;
   
   private String body = null;
   
   private Integer deletedBySender = null;
   
   private Integer deletedByReceiver = null;

   /**
    * @return the sender
    */
   public User getSender() {
      return sender;
   }

   /**
    * @param sender the sender to set
    */
   public void setSender(User sender) {
      this.sender = sender;
   }

   /**
    * @return the receiver
    */
   public User getReceiver() {
      return receiver;
   }

   /**
    * @param receiver the receiver to set
    */
   public void setReceiver(User receiver) {
      this.receiver = receiver;
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

   /**
    * @return the createDate
    */
   public Date getCreateDate() {
      return createDate;
   }

   /**
    * @param createDate the createDate to set
    */
   public void setCreateDate(Date createDate) {
      this.createDate = createDate;
   }

   /**
    * @return the sentDate
    */
   public Date getSentDate() {
      return sentDate;
   }

   /**
    * @param sentDate the sentDate to set
    */
   public void setSentDate(Date sentDate) {
      this.sentDate = sentDate;
   }

   /**
    * @return the receiveDate
    */
   public Date getReceiveDate() {
      return receiveDate;
   }

   /**
    * @param receiveDate the receiveDate to set
    */
   public void setReceiveDate(Date receiveDate) {
      this.receiveDate = receiveDate;
   }

   /**
    * @return the readDate
    */
   public Date getReadDate() {
      return readDate;
   }

   /**
    * @param readDate the readDate to set
    */
   public void setReadDate(Date readDate) {
      this.readDate = readDate;
   }

   /**
    * @return the subject
    */
   public String getSubject() {
      return subject;
   }

   /**
    * @param subject the subject to set
    */
   public void setSubject(String subject) {
      this.subject = subject;
   }

   /**
    * @return the body
    */
   public String getBody() {
      return body;
   }

   /**
    * @param body the body to set
    */
   public void setBody(String body) {
      this.body = body;
   }

   /**
    * @return the deletedBySender
    */
   public Integer getDeletedBySender() {
      return deletedBySender;
   }

   /**
    * @param deletedBySender the deletedBySender to set
    */
   public void setDeletedBySender(Integer deletedBySender) {
      this.deletedBySender = deletedBySender;
   }

   /**
    * @return the deletedByReceiver
    */
   public Integer getDeletedByReceiver() {
      return deletedByReceiver;
   }

   /**
    * @param deletedByReceiver the deletedByReceiver to set
    */
   public void setDeletedByReceiver(Integer deletedByReceiver) {
      this.deletedByReceiver = deletedByReceiver;
   }
   

}
