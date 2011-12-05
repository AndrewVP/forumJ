/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.common.db.entity;

import java.util.Date;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public interface IFJMail {
   public static final String ID_FIELD_NAME = "id";
   public static final String DATE_CREATE_FIELD_NAME = "d_cr";
   public static final String DATE_SENT_FIELD_NAME = "d_snt";
   public static final String DATE_RECEIVED_FIELD_NAME = "d_rcv";
   public static final String DATE_READ_FIELD_NAME = "d_read";
   public static final String RECEIVER_ID_FIELD_NAME = "d_read";
   public static final String SENDER_ID_FIELD_NAME = "d_read";
   public static final String SUBJECT_FIELD_NAME = "head";
   public static final String BODY_FIELD_NAME = "body";
   public static final String DELETED_BY_SENDER_FIELD_NAME = "del_s";
   public static final String DELETED_RECEIVER_FIELD_NAME = "del_r";

   public static final String RECEIVER_NICK_FIELD_NAME = "rcvr_nick";
   public static final String SENDER_NICK_FIELD_NAME = "sndr_nick";
   public abstract void setDeletedByReceiver(Integer deletedByReceiver);
   public abstract Integer getDeletedByReceiver();
   public abstract void setDeletedBySender(Integer deletedBySender);
   public abstract Integer getDeletedBySender();
   public abstract void setBody(String body);
   public abstract String getBody();
   public abstract void setSubject(String subject);
   public abstract String getSubject();
   public abstract void setReadDate(Date readDate);
   public abstract Date getReadDate();
   public abstract void setReceiveDate(Date receiveDate);
   public abstract Date getReceiveDate();
   public abstract void setSentDate(Date sentDate);
   public abstract Date getSentDate();
   public abstract void setCreateDate(Date createDate);
   public abstract Date getCreateDate();
   public abstract void setId(Long id);
   public abstract Long getId();
   public abstract void setReceiver(IUser receiver);
   public abstract IUser getReceiver();
   public abstract void setSender(IUser sender);
   public abstract IUser getSender();
}