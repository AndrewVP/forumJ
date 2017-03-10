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

   private Integer type = 0;

   private String tablePost = null;
   
   private String tableHead = null;
   
   private boolean lastPost = false;
   
   private boolean firstPost = false;
   
   private IQuestNode question = null; 
   
   private List<IQuestNode> answers = null;
   
   private int voicesAmount = 0;

   private String body = null;

   private Long auth = null;

   private String title = null;

   private String ip = null;

   private String domen = null;

   private String outd = null;

   private Integer nred = null;

   private Long createTime = null;

   private Long editTime = null;

   private Long postId = null;

   private IUser author = null;


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
    * @return the author
    */
   @Override
   public IUser getAuthor() {
      return author;
   }

   /**
    * @param author the author to set
    */
   @Override
   public void setAuthor(IUser author) {
      this.author = author;
   }

   /**
    * @return the auth
    */
   @Override
   public Long getAuth() {
      return auth;
   }

   /**
    * @param auth the auth to set
    */
   @Override
   public void setAuth(Long auth) {
      this.auth = auth;
   }

   /**
    * @return the title
    */
   @Override
   public String getTitle() {
      return title;
   }

   /**
    * @param title the title to set
    */
   @Override
   public void setTitle(String title) {
      this.title = title;
   }

   /**
    * @return the ip
    */
   @Override
   public String getIp() {
      return ip;
   }

   /**
    * @param ip the ip to set
    */
   @Override
   public void setIp(String ip) {
      this.ip = ip;
   }

   /**
    * @return the domen
    */
   @Override
   public String getDomen() {
      return domen;
   }

   /**
    * @param domen the domen to set
    */
   @Override
   public void setDomen(String domen) {
      this.domen = domen;
   }

   /**
    * @return the outd
    */
   @Override
   public String getOutd() {
      return outd;
   }

   /**
    * @param outd the outd to set
    */
   @Override
   public void setOutd(String outd) {
      this.outd = outd;
   }

   /**
    * @return the nred
    */
   @Override
   public Integer getNred() {
      return nred;
   }

   /**
    * @param nred the nred to set
    */
   @Override
   public void setNred(Integer nred) {
      this.nred = nred;
   }

   /**
    * @return the createTime
    */
   @Override
   public Long getCreateTime() {
      return createTime;
   }

   /**
    * @param createTime the createTime to set
    */
   @Override
   public void setCreateTime(Long createTime) {
      this.createTime = createTime;
   }

   /**
    * @return the editTime
    */
   @Override
   public Long getEditTime() {
      return editTime;
   }

   /**
    * @param editTime the editTime to set
    */
   @Override
   public void setEditTime(Long editTime) {
      this.editTime = editTime;
   }

   /**
    * @return the postId
    */
   @Override
   public Long getPostId() {
      return postId;
   }

   /**
    * @param postId the postId to set
    */
   @Override
   public void setPostId(Long postId) {
      this.postId = postId;
   }

}
