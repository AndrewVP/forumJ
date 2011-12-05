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
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.*;
import org.forumj.common.db.service.ControlService;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class ControlServiceImpl extends FJCommonService implements ControlService {
   
   /* (non-Javadoc)
    * @see org.forumj.db.service.ControlService#receiveMail(java.lang.Long)
    */
   public void receiveMail(Long userId) throws ConfigurationException, SQLException, IOException{
      getMailDao().receiveMail(userId);
   }
   
   /* (non-Javadoc)
    * @see org.forumj.db.service.ControlService#loadInbox(org.forumj.common.db.entity.IUser)
    */
   public List<IFJMail> loadInbox(IUser user) throws IOException, ConfigurationException, SQLException{
      return getMailDao().loadInbox(user);
   }
   
   /* (non-Javadoc)
    * @see org.forumj.db.service.ControlService#loadMail(org.forumj.common.db.entity.IUser, java.lang.Long, boolean)
    */
   public IFJMail loadMail(IUser user, Long mailId, boolean userIsSender) throws IOException, ConfigurationException, SQLException{
      return getMailDao().loadMail(user, mailId, userIsSender);
   }

   /* (non-Javadoc)
    * @see org.forumj.db.service.ControlService#markMailAsRead(java.lang.Long, java.lang.Long)
    */
   public void markMailAsRead(Long userId, Long mailId) throws ConfigurationException, SQLException, IOException{
      getMailDao().markMailAsRead(userId, mailId);
   }

   /* (non-Javadoc)
    * @see org.forumj.db.service.ControlService#loadOutNotReceivedBox(org.forumj.common.db.entity.IUser)
    */
   public List<IFJMail> loadOutNotReceivedBox(IUser user) throws IOException, ConfigurationException, SQLException{
      return getMailDao().loadOutNotReceivedBox(user);
   }
   
   /* (non-Javadoc)
    * @see org.forumj.db.service.ControlService#loadOutReceivedBox(org.forumj.common.db.entity.IUser)
    */
   public List<IFJMail> loadOutReceivedBox(IUser user) throws IOException, ConfigurationException, SQLException{
      return getMailDao().loadOutReceivedBox(user);
   }

   /* (non-Javadoc)
    * @see org.forumj.db.service.ControlService#loadDraftBox(org.forumj.common.db.entity.IUser)
    */
   public List<IFJMail> loadDraftBox(IUser user) throws IOException, ConfigurationException, SQLException{
      return getMailDao().loadDraftBox(user);
   }

   /* (non-Javadoc)
    * @see org.forumj.db.service.ControlService#findAllInterfaces(org.forumj.common.db.entity.IUser)
    */
   public List<IFJInterface> findAllInterfaces(IUser user) throws IOException, SQLException, ConfigurationException{
      return getInterfaceDao().findAll(user);
   }
   
   /* (non-Javadoc)
    * @see org.forumj.db.service.ControlService#findInterface(org.forumj.common.db.entity.IUser, java.lang.Long)
    */
   public IFJInterface findInterface(IUser user, Long id) throws IOException, SQLException, ConfigurationException{
      return getInterfaceDao().find(user, id);
   }

   /* (non-Javadoc)
    * @see org.forumj.db.service.ControlService#findAllFolders(org.forumj.common.db.entity.IUser, org.forumj.common.db.entity.IFJInterface)
    */
   public List<IFJFolder> findAllFolders(IUser user, IFJInterface interf) throws SQLException, ConfigurationException, IOException{
      return getFolderDao().findAll(user, interf);
   }

   /* (non-Javadoc)
    * @see org.forumj.db.service.ControlService#findAllFoldersNotIn(org.forumj.common.db.entity.IUser, org.forumj.common.db.entity.IFJInterface)
    */
   public List<IFJFolder> findAllFoldersNotIn(IUser user, IFJInterface interf) throws SQLException, ConfigurationException, IOException{
      return getFolderDao().findAllNotIn(user, interf);
   }

   /* (non-Javadoc)
    * @see org.forumj.db.service.ControlService#findAllSubscribes(org.forumj.common.db.entity.IUser, java.lang.Integer)
    */
   public List<IFJSubscribe> findAllSubscribes(IUser user, Integer active) throws SQLException, ConfigurationException, IOException{
      return getSubscribedao().findAll(user, active);
   }
}
