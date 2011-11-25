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

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public interface IFJSubscribe {
   
   public static final String ID_FIELD_NAME = "id";
   public static final String USER_ID_FIELD_NAME = "user";
   public static final String TITLE_ID_FIELD_NAME = "title";
   public static final String HEAD_FIELD_NAME = "head";
   public static final String START_DATE_FIELD_NAME = "d_start";
   public static final String END_DATE_FIELD_NAME = "d_end";
   public static final String KEY_FIELD_NAME = "kod";
   public static final String TYPE_FIELD_NAME = "type";
   public static final String ACTIVE_FIELD_NAME = "act";

   /**
    * @return the id
    */
   public Long getId();

   /**
    * @param id the id to set
    */
   public void setId(Long id);

   /**
    * @return the user
    */
   public IUser getUser();

   /**
    * @param user the user to set
    */
   public void setUser(IUser user);

   /**
    * @return the start
    */
   public Date getStart();

   /**
    * @param start the start to set
    */
   public void setStart(Date start);

   /**
    * @return the end
    */
   public Date getEnd();

   /**
    * @param end the end to set
    */
   public void setEnd(Date end);

   /**
    * @return the key
    */
   public Long getKey();

   /**
    * @param key the key to set
    */
   public void setKey(Long key);

   /**
    * @return the type
    */
   public Integer getType();

   /**
    * @param type the type to set
    */
   public void setType(Integer type);

   /**
    * @return the active
    */
   public Integer getActive();

   /**
    * @param active the active to set
    */
   public void setActive(Integer active);

   public abstract void setHead(String head);

   public abstract String getHead();

   public abstract void setTitleId(Long titleId);

   public abstract Long getTitleId();

}