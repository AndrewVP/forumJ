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
public interface IFJIpAddress {
   
   public abstract void setLastCheck(Date lastCheck);

   public abstract Date getLastCheck();

   public abstract void setSource(String source);

   public abstract String getSource();

   public abstract void setSpammer(boolean spammer);

   public abstract boolean isSpammer();

   public abstract void setIp(String ip);

   public abstract String getIp();

   public abstract void setId(long id);

   public abstract long getId();

}
