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
package org.forumj.db.entity;

import java.util.Date;

import org.forumj.common.db.entity.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class Ignor implements IIgnor {
   
   private Long id = null;
   private Integer type = null;
   private Long userId = null;
   private IUser user = null;
   private Date start = null;
   private Date end = null;
   
   /**
    * @return the userId
    */
   public Long getUserId() {
      return userId;
   }
   /**
    * @param userId the userId to set
    */
   public void setUserId(Long userId) {
      this.userId = userId;
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
    * @return the type
    */
   public Integer getType() {
      return type;
   }
   /**
    * @param type the type to set
    */
   public void setType(Integer type) {
      this.type = type;
   }
   /**
    * @return the user
    */
   public IUser getUser() {
      return user;
   }
   /**
    * @param user the user to set
    */
   public void setUser(IUser user) {
      this.user = user;
   }
   /**
    * @return the start
    */
   public Date getStart() {
      return start;
   }
   /**
    * @param start the start to set
    */
   public void setStart(Date start) {
      this.start = start;
   }
   /**
    * @return the end
    */
   public Date getEnd() {
      return end;
   }
   /**
    * @param end the end to set
    */
   public void setEnd(Date end) {
      this.end = end;
   }
   
}
