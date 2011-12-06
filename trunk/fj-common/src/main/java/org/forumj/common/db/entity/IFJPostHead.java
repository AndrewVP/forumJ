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

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public interface IFJPostHead {
   
   public static final String ID_FIELD_NAME = "id";
   public static final String ATHOR_ID_FIELD_NAME = "auth";
   public static final String TITLE_FIELD_NAME = "tilte";
   public static final String ATHOR_IP_FIELD_NAME = "ip";
   public static final String ATHOR_DOMEN_FIELD_NAME = "domen";
   public static final String OUTD_FIELD_NAME = "outd";
   public static final String NUMBER_OF_EDITS_FIELD_NAME = "nred";
   public static final String CREATIN_DATE_FIELD_NAME = "fd_post_time";
   public static final String LAST_EDIT_DATE_FIELD_NAME = "fd_post_edit_time";
   public static final String POST_ID_FIELD_NAME = "id_post";
   public static final String THREAD_ID_FIELD_NAME = "thread_id";
   public abstract void setThreadId(Long threadId);
   public abstract Long getThreadId();
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
   public abstract void setId(Long id);
   public abstract Long getId();
   public abstract void setAuthor(IUser author);
   public abstract IUser getAuthor();
}