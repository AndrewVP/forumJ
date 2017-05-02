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
import java.sql.*;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public interface InterfaceService {

   /**
    * 
    * @param user
    * @return
    * @throws IOException
    * @throws SQLException
    * @throws ConfigurationException
    */
   public List<IFJInterface> findAllInterfaces(IUser user) throws IOException,
         SQLException, ConfigurationException;

   /**
    * 
    * @param user
    * @param id
    * @return
    * @throws IOException
    * @throws SQLException
    * @throws ConfigurationException
    */
   public IFJInterface findInterface(IUser user, Long id) throws IOException,
         SQLException, ConfigurationException;

   public boolean isInterfaceContainsFolder(long interfaceId, long folderId,
         IUser user) throws IOException, ConfigurationException, SQLException;

   public void addFolder(long interfaceId, long folderId, IUser user,
         Connection connection) throws ConfigurationException, SQLException,
         IOException;

   public void deleteInterface(long interfaceId, IUser user)
         throws ConfigurationException, SQLException, IOException;

   /**
    * @param name
    * @param user
    * @return
    */
   public boolean isExists(String name, IUser user) throws ConfigurationException, SQLException, IOException ;

   public boolean isExists(Long id, IUser user) throws ConfigurationException, SQLException, IOException ;

   /**
    * @param name
    * @param user
    */
   public void create(String name, IUser user) throws ConfigurationException, SQLException, IOException ;

}