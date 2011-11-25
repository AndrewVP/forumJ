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
public class FJSubscribe implements IFJSubscribe {

   private Long id = null;
   
   private IUser user = null;
   
   private Long titleId = null;
   
   private String head = null;
   
   private Date start = null;
   
   private Date end = null;
   
   private Long key = null;
   
   private Integer type = null;
   
   private Integer active = null;
   
   /**
    * @return the titleId
    */
   @Override
   public Long getTitleId() {
      return titleId;
   }

   /**
    * @param titleId the titleId to set
    */
   @Override
   public void setTitleId(Long titleId) {
      this.titleId = titleId;
   }

   /**
    * @return the head
    */
   @Override
   public String getHead() {
      return head;
   }

   /**
    * @param head the head to set
    */
   @Override
   public void setHead(String head) {
      this.head = head;
   }

   /**
    * {@inheritDoc}
    */
   public Long getId() {
      return id;
   }

   /**
    * {@inheritDoc}
    */
   public void setId(Long id) {
      this.id = id;
   }

   /**
    * {@inheritDoc}
    */
   public IUser getUser() {
      return user;
   }

   /**
    * {@inheritDoc}
    */
   public void setUser(IUser user) {
      this.user = user;
   }

   /**
    * {@inheritDoc}
    */
   public Date getStart() {
      return start;
   }

   /**
    * {@inheritDoc}
    */
   public void setStart(Date start) {
      this.start = start;
   }

   /**
    * {@inheritDoc}
    */
   public Date getEnd() {
      return end;
   }

   /**
    * {@inheritDoc}
    */
   public void setEnd(Date end) {
      this.end = end;
   }

   /**
    * {@inheritDoc}
    */
   public Long getKey() {
      return key;
   }

   /**
    * {@inheritDoc}
    */
   public void setKey(Long key) {
      this.key = key;
   }

   /**
    * {@inheritDoc}
    */
   public Integer getType() {
      return type;
   }

   /**
    * {@inheritDoc}
    */
   public void setType(Integer type) {
      this.type = type;
   }

   /**
    * {@inheritDoc}
    */
   public Integer getActive() {
      return active;
   }

   /**
    * {@inheritDoc}
    */
   public void setActive(Integer active) {
      this.active = active;
   }
   
   
}
