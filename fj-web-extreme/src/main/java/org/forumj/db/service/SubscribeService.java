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
package org.forumj.db.service;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class SubscribeService extends FJCommonService {
   
   public static boolean isKeyPresent(Integer key) throws SQLException, ConfigurationException, IOException{
      return getSubscribedao().isKeyPresent(key);
   }
   
   public static void createSubscribe(IFJSubscribe subscribe) throws ConfigurationException, SQLException, IOException{
      getSubscribedao().create(subscribe);
   }

   public static void deleteSubscribeByTitleId(long titleId, IUser user) throws ConfigurationException, SQLException, IOException{
      getSubscribedao().deleteByTitleId(titleId, user);
   }
   public static void deleteSubscribeByKey(long key) throws ConfigurationException, SQLException, IOException{
      getSubscribedao().deleteByKey(key);
   }
   public static void deleteSuscribeById(long id, IUser user) throws ConfigurationException, SQLException, IOException{
      getSubscribedao().deleteById(id, user);
   }
}
