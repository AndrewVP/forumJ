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
package org.forumj.dbextreme.db.entity;

import java.util.Date;

import org.forumj.common.db.entity.IFJIpAddress;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJIpAddress implements IFJIpAddress{

   private long id;
   
   private String ip;
   
   private boolean spammer;
   
   private String source;
   
   private Date lastCheck;

   @Override
   public long getId() {
      return id;
   }

   @Override
   public void setId(long id) {
      this.id = id;
   }

   @Override
   public String getIp() {
      return ip;
   }

   @Override
   public void setIp(String ip) {
      this.ip = ip;
   }

   @Override
   public boolean isSpammer() {
      return spammer;
   }

   @Override
   public void setSpammer(boolean spammer) {
      this.spammer = spammer;
   }

   @Override
   public String getSource() {
      return source;
   }

   @Override
   public void setSource(String source) {
      this.source = source;
   }

   @Override
   public Date getLastCheck() {
      return lastCheck;
   }

   @Override
   public void setLastCheck(Date lastCheck) {
      this.lastCheck = lastCheck;
   }
   
   
}
