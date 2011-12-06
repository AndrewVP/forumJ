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

import java.util.List;

import org.forumj.common.db.entity.*;

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
   
   private IFJPostBody body = null;
   
   private IFJPostHead head = null;
   
   private boolean lastPost = false;
   
   private boolean firstPost = false;
   
   private IQuestNode question = null; 
   
   private List<IQuestNode> answers = null;
   
   private int voicesAmount = 0; 
   
   @Override
   public int getVoicesAmount() {
      return voicesAmount;
   }

   @Override
   public void setVoicesAmount(int voicesAmount) {
      this.voicesAmount = voicesAmount;
   }

   /**
    * @return the lastPost
    */
   @Override
   public boolean isLastPost() {
      return lastPost;
   }

   /**
    * @param lastPost the lastPost to set
    */
   @Override
   public void setLastPost(boolean lastPost) {
      this.lastPost = lastPost;
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
    * @return the headId
    */
   @Override
   public Long getThreadId() {
      return threadId;
   }

   /**
    * @param headId the headId to set
    */
   @Override
   public void setThreadId(Long headId) {
      this.threadId = headId;
   }

   /**
    * @return the state
    */
   @Override
   public Integer getState() {
      return state;
   }

   /**
    * @param state the state to set
    */
   @Override
   public void setState(Integer state) {
      this.state = state;
   }

   /**
    * @return the tablePost
    */
   @Override
   public String getTablePost() {
      return tablePost;
   }

   /**
    * @param tablePost the tablePost to set
    */
   @Override
   public void setTablePost(String tablePost) {
      this.tablePost = tablePost;
   }

   /**
    * @return the tableHead
    */
   @Override
   public String getTableHead() {
      return tableHead;
   }

   /**
    * @param tableHead the tableHead to set
    */
   @Override
   public void setTableHead(String tableHead) {
      this.tableHead = tableHead;
   }

   /**
    * @return the body
    */
   @Override
   public IFJPostBody getBody() {
      return body;
   }

   /**
    * @param body the body to set
    */
   @Override
   public void setBody(IFJPostBody body) {
      this.body = body;
   }

   /**
    * @return the head
    */
   @Override
   public IFJPostHead getHead() {
      return head;
   }

   /**
    * @param head the head to set
    */
   @Override
   public void setHead(IFJPostHead head) {
      this.head = head;
   }

   @Override
   public IQuestNode getQuestion() {
      return question;
   }

   @Override
   public void setQuestion(IQuestNode question) {
      this.question = question;
   }

   @Override
   public List<IQuestNode> getAnswers() {
      return answers;
   }

   @Override
   public void setAnswers(List<IQuestNode> answers) {
      this.answers = answers;
   }

   @Override
   public boolean isFirstPost() {
      return firstPost;
   }

   @Override
   public void setFirstPost(boolean firstPost) {
      this.firstPost = firstPost;
   }

}
