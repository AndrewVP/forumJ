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

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJPost implements IFJPost {
   
   private Long id = null;
   
   private Long threadId = null;
   
   private Integer state = null;
   
   private String tablePost = null;
   
   private String tableHead = null;
   
   private FJPostBody body = null;
   
   private FJPostHead head = null;
   
   private boolean lastPost = false;

   /**
    * @return the lastPost
    */
   public boolean isLastPost() {
      return lastPost;
   }

   /**
    * @param lastPost the lastPost to set
    */
   public void setLastPost(boolean lastPost) {
      this.lastPost = lastPost;
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
    * @return the headId
    */
   public Long getThreadId() {
      return threadId;
   }

   /**
    * @param headId the headId to set
    */
   public void setThreadId(Long headId) {
      this.threadId = headId;
   }

   /**
    * @return the state
    */
   public Integer getState() {
      return state;
   }

   /**
    * @param state the state to set
    */
   public void setState(Integer state) {
      this.state = state;
   }

   /**
    * @return the tablePost
    */
   public String getTablePost() {
      return tablePost;
   }

   /**
    * @param tablePost the tablePost to set
    */
   public void setTablePost(String tablePost) {
      this.tablePost = tablePost;
   }

   /**
    * @return the tableHead
    */
   public String getTableHead() {
      return tableHead;
   }

   /**
    * @param tableHead the tableHead to set
    */
   public void setTableHead(String tableHead) {
      this.tableHead = tableHead;
   }

   /**
    * @return the body
    */
   public FJPostBody getBody() {
      return body;
   }

   /**
    * @param body the body to set
    */
   public void setBody(FJPostBody body) {
      this.body = body;
   }

   /**
    * @return the head
    */
   public FJPostHead getHead() {
      return head;
   }

   /**
    * @param head the head to set
    */
   public void setHead(FJPostHead head) {
      this.head = head;
   }

}
