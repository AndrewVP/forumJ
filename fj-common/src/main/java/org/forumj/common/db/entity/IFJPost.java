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

import java.util.List;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public interface IFJPost {
   
   public static final String ID_FIELD_NAME = "id";
   public static final String THREAD_ID_FIELD_NAME = "head";
   public static final String STATE_FIELD_NAME = "fd_state";
   public static final String TABLE_POST_FIELD_NAME = "table_post";
   public static final String TABLE_HEAD_FIELD_NAME = "table_head";
   public abstract void setFirstPost(boolean firstPost);
   public abstract boolean isFirstPost();
   public abstract void setAnswers(List<IQuestNode> answers);
   public abstract List<IQuestNode> getAnswers();
   public abstract void setQuestion(IQuestNode question);
   public abstract IQuestNode getQuestion();
   public abstract void setTableHead(String tableHead);
   public abstract String getTableHead();
   public abstract void setTablePost(String tablePost);
   public abstract String getTablePost();
   public abstract void setState(Integer state);
   public abstract Integer getState();
   public abstract void setThreadId(Long headId);
   public abstract Long getThreadId();
   public abstract void setId(Long id);
   public abstract Long getId();
   public abstract void setLastPost(boolean lastPost);
   public abstract boolean isLastPost();
   public abstract void setVoicesAmount(int voicesAmount);

   Long getReplyTo();

   void setReplyTo(Long replyTo);

   Integer getType();

   void setType(Integer type);

   public abstract int getVoicesAmount();


   public abstract void setPostId(Long postId);
   public abstract Long getPostId();
   public abstract void setEditTime(Long editTime);
   public abstract Long getEditTime();
   public abstract void setCreateTime(Long createTime);
   public abstract Long getCreateTime();
   public abstract void setNred(Integer nred);
   public abstract Integer getNred();
   public abstract void setOutd(String outd);
   public abstract String getOutd();
   public abstract void setDomen(String domen);
   public abstract String getDomen();
   public abstract void setIp(String ip);
   public abstract String getIp();
   public abstract void setTitle(String title);
   public abstract String getTitle();
   public abstract void setAuth(Long auth);
   public abstract Long getAuth();
   public abstract void setAuthor(IUser author);
   public abstract IUser getAuthor();

   public abstract void setBody(String body);
   public abstract String getBody();

}