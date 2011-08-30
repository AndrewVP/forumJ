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
public interface IFJFolder {
   public static final String ID_FIELD_NAME = "id";
   public static final String DATE_CREATE_FIELD_NAME = "d_cr";
   public static final String USER_ID_FIELD_NAME = "user";
   public static final String NAME_FIELD_NAME = "flname";
   public void setCreateDate(Date createDate);
   public Date getCreateDate();
   public void setName(String name);
   public String getName();
   public void setUser(User user);
   public User getUser();
   public void setId(Long id);
   public Long getId();
}