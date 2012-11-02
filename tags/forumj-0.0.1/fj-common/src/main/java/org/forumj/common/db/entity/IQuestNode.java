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

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public interface IQuestNode {

   /**
    * @return the node
    */
   public String getNode();

   /**
    * @param node the node to set
    */
   public void setNode(String node);

   /**
    * @return the userNick
    */
   public String getUserNick();

   /**
    * @param userNick the userNick to set
    */
   public void setUserNick(String userNick);

   /**
    * @return the id
    */
   public Long getId();

   /**
    * @param id the id to set
    */
   public void setId(Long id);

   /**
    * @return the head
    */
   public Long getHead();

   /**
    * @param head the head to set
    */
   public void setHead(Long head);

   /**
    * @return the numb
    */
   public Integer getNumb();

   /**
    * @param numb the numb to set
    */
   public void setNumb(Integer numb);

   /**
    * @return the gol
    */
   public Integer getGol();

   /**
    * @param gol the gol to set
    */
   public void setGol(Integer gol);

   /**
    * @return the type
    */
   public Integer getType();

   /**
    * @param type the type to set
    */
   public void setType(Integer type);

   /**
    * @return the userId
    */
   public Long getUserId();

   /**
    * @param userId the userId to set
    */
   public void setUserId(Long userId);

}