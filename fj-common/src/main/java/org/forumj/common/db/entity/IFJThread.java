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

import org.forumj.common.web.Pin;

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
   public abstract boolean isQuest();
   public abstract void setFolderId(Long folderId);
   public abstract Long getFolderId();
   public abstract void setLastPostId(Long lastPostId);
   public abstract Long getLastPostId();
   public abstract void setAuthId(Long authId);
   public abstract Long getAuthId();
   public abstract void setPg(int pg);
   public abstract int getPg();
   public abstract void setPt(int pt);
   public abstract int getPt();
   public abstract void setDisain(int disain);
   public abstract int getDisain();
   public abstract void setFolder(String folder);
   public abstract String getFolder();
   public abstract void setType(int type);
   public abstract int getType();
   public abstract void setSnall(Integer snall);
   public abstract Integer getSnall();
   public abstract void setSnid(Integer snid);
   public abstract Integer getSnid();
   public abstract void setPcount(int pcount);
   public abstract int getPcount();
   public abstract void setLastPostNick(String lastPostNick);
   public abstract String getLastPostNick();
   public abstract void setLastPostTime(Date lastPostTime);
   public abstract Date getLastPostTime();
   public abstract void setId(Long id);
   public abstract Long getId();
   public abstract void setNick(String nick);
   public abstract String getNick();
   public abstract void setHead(String head);
   public abstract String getHead();
   public abstract void setDock(Pin dock);
   public abstract Pin getDock();
   public abstract void setRegDate(Date regDate);
   public abstract Date getRegDate();
   public abstract void setLastPostAuthId(Long lastPostAuthId);
   public abstract Long getLastPostAuthId();

}