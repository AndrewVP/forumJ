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

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJInterface implements IFJInterface {
   
   private Long id = null;
   
   private IUser user = null;
   
   private String name = null;
   
   private Date createDate = null;

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
    * @return the user
    */
   @Override
   public IUser getUser() {
      return user;
   }

   /**
    * @param user the user to set
    */
   @Override
   public void setUser(IUser user) {
      this.user = user;
   }

   /**
    * @return the name
    */
   @Override
   public String getName() {
      return name;
   }

   /**
    * @param name the name to set
    */
   @Override
   public void setName(String name) {
      this.name = name;
   }

   /**
    * @return the createDate
    */
   @Override
   public Date getCreateDate() {
      return createDate;
   }

   /**
    * @param createDate the createDate to set
    */
   @Override
   public void setCreateDate(Date createDate) {
      this.createDate = createDate;
   }
}
