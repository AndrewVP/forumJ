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
package org.forumj.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.*;
import org.forumj.common.db.service.IgnorService;
import org.forumj.dbextreme.db.entity.Ignor;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class IgnorServiceImpl extends FJService implements IgnorService {

   public void createIgnor(long ignoredUserId, IUser user) throws SQLException, ConfigurationException, IOException{
      getIgnorDao().create(ignoredUserId, user);
   }

   public void updateIgnor(IIgnor ignor) throws IOException, ConfigurationException, SQLException{
      getIgnorDao().update(ignor);
   }

   /**
    * 
    * @param userId
    * @return
    * @throws IOException
    * @throws ConfigurationException
    * @throws SQLException
    */
   @Override
   public List<IIgnor> readUserIgnor(Long userId) throws IOException, ConfigurationException, SQLException{
      return getIgnorDao().loadAll(userId);
   }

   public IIgnor getIgnorObject() {
      return new Ignor();
   }
}
