/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
  */
package org.forumj.dbextreme.db.entity;

import java.util.Date;

import org.forumj.common.db.entity.*;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJMail implements IFJMail {
   
   private Long id = null;
   
   private IUser sender = null;
   
   private IUser receiver = null;
   
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
   @Override
   public IUser getSender() {
      return sender;
   }

   /**
    * @param sender the sender to set
    */
   @Override
   public void setSender(IUser sender) {
      this.sender = sender;
   }

   /**
    * @return the receiver
    */
   @Override
   public IUser getReceiver() {
      return receiver;
   }

   /**
    * @param receiver the receiver to set
    */
   @Override
   public void setReceiver(IUser receiver) {
      this.receiver = receiver;
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

   /**
    * @return the createDate
    */
   @Override
   public Date getCreateDate() {
      return createDate;
   }

   /**
    * @param createDate the createDate to set
    */
   @Override
   public void setCreateDate(Date createDate) {
      this.createDate = createDate;
   }

   /**
    * @return the sentDate
    */
   @Override
   public Date getSentDate() {
      return sentDate;
   }

   /**
    * @param sentDate the sentDate to set
    */
   @Override
   public void setSentDate(Date sentDate) {
      this.sentDate = sentDate;
   }

   /**
    * @return the receiveDate
    */
   @Override
   public Date getReceiveDate() {
      return receiveDate;
   }

   /**
    * @param receiveDate the receiveDate to set
    */
   @Override
   public void setReceiveDate(Date receiveDate) {
      this.receiveDate = receiveDate;
   }

   /**
    * @return the readDate
    */
   @Override
   public Date getReadDate() {
      return readDate;
   }

   /**
    * @param readDate the readDate to set
    */
   @Override
   public void setReadDate(Date readDate) {
      this.readDate = readDate;
   }

   /**
    * @return the subject
    */
   @Override
   public String getSubject() {
      return subject;
   }

   /**
    * @param subject the subject to set
    */
   @Override
   public void setSubject(String subject) {
      this.subject = subject;
   }

   /**
    * @return the body
    */
   @Override
   public String getBody() {
      return body;
   }

   /**
    * @param body the body to set
    */
   @Override
   public void setBody(String body) {
      this.body = body;
   }

   /**
    * @return the deletedBySender
    */
   @Override
   public Integer getDeletedBySender() {
      return deletedBySender;
   }

   /**
    * @param deletedBySender the deletedBySender to set
    */
   @Override
   public void setDeletedBySender(Integer deletedBySender) {
      this.deletedBySender = deletedBySender;
   }

   /**
    * @return the deletedByReceiver
    */
   @Override
   public Integer getDeletedByReceiver() {
      return deletedByReceiver;
   }

   /**
    * @param deletedByReceiver the deletedByReceiver to set
    */
   @Override
   public void setDeletedByReceiver(Integer deletedByReceiver) {
      this.deletedByReceiver = deletedByReceiver;
   }
   

}
