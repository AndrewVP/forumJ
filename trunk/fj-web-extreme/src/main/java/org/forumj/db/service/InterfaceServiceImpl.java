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
import java.sql.*;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.*;
import org.forumj.common.db.service.InterfaceService;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class InterfaceServiceImpl extends FJCommonService implements InterfaceService {

   public boolean isInterfaceContainsFolder(long interfaceId, long folderId, IUser user) throws IOException, ConfigurationException, SQLException{
      return getInterfaceDao().isInterfaceContainsFolder(interfaceId, folderId, user);
   }
   
   public void addFolder(long interfaceId, long folderId, IUser user, Connection connection) throws ConfigurationException, SQLException, IOException{
      getInterfaceDao().addFolder(interfaceId, folderId, user, connection);
   }
   
   public void deleteInterface(long interfaceId, IUser user) throws ConfigurationException, SQLException, IOException{
      getInterfaceDao().delete(interfaceId, user);
   }
   

   public List<IFJInterface> findAllInterfaces(IUser user) throws IOException, SQLException, ConfigurationException{
      return getInterfaceDao().findAll(user);
   }
   
   public IFJInterface findInterface(IUser user, Long id) throws IOException, SQLException, ConfigurationException{
      return getInterfaceDao().find(user, id);
   }
}
