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
package org.forumj.common.db.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public interface SubscribeService {
   
   public IFJSubscribe getSubscribeObject();

   public boolean isKeyPresent(Integer key) throws SQLException,
         ConfigurationException, IOException;

   public void createSubscribe(IFJSubscribe subscribe)
         throws ConfigurationException, SQLException, IOException;

   public void deleteSubscribeByTitleId(long titleId, IUser user)
         throws ConfigurationException, SQLException, IOException;

   public void deleteSubscribeByKey(long key) throws ConfigurationException,
         SQLException, IOException;

   public void deleteSuscribeById(long id, IUser user)
         throws ConfigurationException, SQLException, IOException;

   /**
    * 
    * @param user
    * @param active
    * @return
    * @throws SQLException
    * @throws ConfigurationException
    * @throws IOException
    */
   public List<IFJSubscribe> findAllSubscribes(IUser user, Integer active)
         throws SQLException, ConfigurationException, IOException;
   
   /**
    * 
    * @param idUser
    * @param threadId
    * @return
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public Boolean isUserSubscribed(Long idUser, Long threadId) throws ConfigurationException, SQLException, IOException;
   
   public List<IUser> getSubscribedUsers(Long threadId, Long userId) throws ConfigurationException, SQLException, IOException;
   
}