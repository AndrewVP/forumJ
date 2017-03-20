/*
 * Copyright Andrew V. Pogrebnyak
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.forumj.common.db.entity;

import java.util.Date;

import org.forumj.common.web.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public interface IFJThread {
   
   public static final String AUTH_FIELD_NAME = "auth";
   public static final String HEAD_FIELD_NAME = "head";
   public static final String REGISTRATION_DATE_FIELD_NAME = "reg";
   public static final String LAST_POST_DATE_FIELD_NAME = "lposttime";
   public static final String LAST_POST_USER_ID_FIELD_NAME = "lpostuser";
   public static final String LAST_POST_USER_NICK_FIELD_NAME = "lpostnick";
   public static final String LAST_POST_ID_FIELD_NAME = "id_last_post";
   public static final String SEEN_ID_FIELD_NAME = "seenid";
   public static final String SEEN_ALL_FIELD_NAME = "seenall";
   public static final String DOCK_FIELD_NAME = "dock";
   public static final String TYPE_FIELD_NAME = "type";
   public static final String FOLDER_ID_FIELD_NAME = "folder";
   public static final String POSTS_COUNT_FIELD_NAME = "npost";
   public static final String CLOSED_FIELD_NAME = "closed";
   public boolean isQuest();
   public void setFolderId(Long folderId);
   public Long getFolderId();
   public void setLastPostId(Long lastPostId);
   public Long getLastPostId();
   public void setAuthId(Long authId);
   public Long getAuthId();
   public void setPg(int pg);
   public int getPg();
   public void setPt(int pt);
   public int getPt();
   public void setFolder(String folder);
   public String getFolder();
   public void setType(ThreadType type);
   public ThreadType getType();
   public void setSnall(Integer snall);
   public Integer getSnall();
   public void setSnid(Integer snid);
   public Integer getSnid();
   public void setPostsAmount(int postsAmount);
   public int getPostsAmount();
   public void setLastPostNick(String lastPostNick);
   public String getLastPostNick();
   public void setLastPostTime(Date lastPostTime);
   public Date getLastPostTime();
   public void setId(Long id);
   public Long getId();
   public void setNick(String nick);
   public String getNick();
   public void setHead(String head);
   public String getHead();
   public void setDock(Pin dock);
   public Pin getDock();
   public void setRegDate(Date regDate);
   public Date getRegDate();
   public void setLastPostAuthId(Long lastPostAuthId);
   public Long getLastPostAuthId();
   public boolean isClosed();
   public void setClosed(boolean closed);
}