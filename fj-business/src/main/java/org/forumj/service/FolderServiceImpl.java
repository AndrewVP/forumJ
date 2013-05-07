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
import org.forumj.common.db.service.FolderService;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FolderServiceImpl extends FJService implements FolderService {

   public void moveToRecyclebin(long threadId, IUser user) throws IOException, ConfigurationException, SQLException{
      // TODO Magic integer!
      getFolderDao().moveToFolder(threadId, 3, user);
   }
   
   public void deleteFolder(Long folderId, IUser user) throws ConfigurationException, SQLException, IOException{
      getFolderDao().delete(folderId, user);
   }

   public void deleteFolderFromView(Long folderId, Long viewId, IUser user) throws ConfigurationException, SQLException, IOException{
      getFolderDao().deleteFolderFromView(folderId, viewId, user);
   }

   /**
    * 
    * @param idUser
    * @return
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   @Override
   public List<IFJFolder> getUserFolders(IUser user) throws ConfigurationException, SQLException, IOException{
      return getFolderDao().findAll(user);
   }
   

   public List<IFJFolder> findAllFolders(IUser user, IFJInterface interf) throws SQLException, ConfigurationException, IOException{
      return getFolderDao().findAll(user, interf);
   }

   public List<IFJFolder> findAllFoldersNotIn(IUser user, IFJInterface interf) throws SQLException, ConfigurationException, IOException{
      return getFolderDao().findAllNotIn(user, interf);
   }

   public void moveToFolder(long threadId, long folderId, IUser user) throws IOException, ConfigurationException, SQLException{
      getFolderDao().moveToFolder(threadId, folderId, user);
   }

   public void create(String folderName, IUser user) throws ConfigurationException, SQLException, IOException {
      getFolderDao().create(folderName, user);
   }

   @Override
   public boolean isExist(String name, IUser user) throws ConfigurationException, SQLException, IOException {
      return getFolderDao().isExist(name, user);
   }
}
