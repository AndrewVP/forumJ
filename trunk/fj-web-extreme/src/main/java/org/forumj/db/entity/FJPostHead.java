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

import org.forumj.common.db.entity.*;


/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJPostHead implements IFJPostHead {

   private Long id = null;
   
   private Long auth = null;
   
   private String title = null;
   
   private String ip = null;
   
   private String domen = null;
   
   private String outd = null;
   
   private Integer nred = null;
   
   private Long createTime = null;
   
   private Long editTime = null;
   
   private Long postId = null;
   
   private Long threadId = null;
   
   private IUser author = null;

   /**
    * @return the author
    */
   public IUser getAuthor() {
      return author;
   }

   /**
    * @param author the author to set
    */
   public void setAuthor(IUser author) {
      this.author = author;
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
    * @return the auth
    */
   public Long getAuth() {
      return auth;
   }

   /**
    * @param auth the auth to set
    */
   public void setAuth(Long auth) {
      this.auth = auth;
   }

   /**
    * @return the title
    */
   public String getTitle() {
      return title;
   }

   /**
    * @param title the title to set
    */
   public void setTitle(String title) {
      this.title = title;
   }

   /**
    * @return the ip
    */
   public String getIp() {
      return ip;
   }

   /**
    * @param ip the ip to set
    */
   public void setIp(String ip) {
      this.ip = ip;
   }

   /**
    * @return the domen
    */
   public String getDomen() {
      return domen;
   }

   /**
    * @param domen the domen to set
    */
   public void setDomen(String domen) {
      this.domen = domen;
   }

   /**
    * @return the outd
    */
   public String getOutd() {
      return outd;
   }

   /**
    * @param outd the outd to set
    */
   public void setOutd(String outd) {
      this.outd = outd;
   }

   /**
    * @return the nred
    */
   public Integer getNred() {
      return nred;
   }

   /**
    * @param nred the nred to set
    */
   public void setNred(Integer nred) {
      this.nred = nred;
   }

   /**
    * @return the createTime
    */
   public Long getCreateTime() {
      return createTime;
   }

   /**
    * @param createTime the createTime to set
    */
   public void setCreateTime(Long createTime) {
      this.createTime = createTime;
   }

   /**
    * @return the editTime
    */
   public Long getEditTime() {
      return editTime;
   }

   /**
    * @param editTime the editTime to set
    */
   public void setEditTime(Long editTime) {
      this.editTime = editTime;
   }

   /**
    * @return the postId
    */
   public Long getPostId() {
      return postId;
   }

   /**
    * @param postId the postId to set
    */
   public void setPostId(Long postId) {
      this.postId = postId;
   }

   /**
    * @return the threadId
    */
   public Long getThreadId() {
      return threadId;
   }

   /**
    * @param threadId the threadId to set
    */
   public void setThreadId(Long threadId) {
      this.threadId = threadId;
   }
   
}
