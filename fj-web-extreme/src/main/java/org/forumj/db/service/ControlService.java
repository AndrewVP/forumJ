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
import org.forumj.db.entity.FJMail;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class ControlService extends FJCommonService {
   
   /**
    * 
    * @param userId
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public static void receiveMail(Long userId) throws ConfigurationException, SQLException, IOException{
      getMailDao().receiveMail(userId);
   }
   
   /**
    * 
    * @param user
    * @return
    * @throws IOException
    * @throws ConfigurationException
    * @throws SQLException
    */
   public static List<FJMail> loadInbox(IUser user) throws IOException, ConfigurationException, SQLException{
      return getMailDao().loadInbox(user);
   }
   
   /**
    * 
    * @param user
    * @param mailId
    * @param userIsSender
    * @return
    * @throws IOException
    * @throws ConfigurationException
    * @throws SQLException
    */
   public static FJMail loadMail(IUser user, Long mailId, boolean userIsSender) throws IOException, ConfigurationException, SQLException{
      return getMailDao().loadMail(user, mailId, userIsSender);
   }

   /**
    * 
    * @param userId
    * @param mailId
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public static void markMailAsRead(Long userId, Long mailId) throws ConfigurationException, SQLException, IOException{
      getMailDao().markMailAsRead(userId, mailId);
   }

   /**
    * 
    * @param user
    * @return
    * @throws IOException
    * @throws ConfigurationException
    * @throws SQLException
    */
   public static List<FJMail> loadOutNotReceivedBox(IUser user) throws IOException, ConfigurationException, SQLException{
      return getMailDao().loadOutNotReceivedBox(user);
   }
   
   /**
    * 
    * @param user
    * @return
    * @throws IOException
    * @throws ConfigurationException
    * @throws SQLException
    */
   public static List<FJMail> loadOutReceivedBox(IUser user) throws IOException, ConfigurationException, SQLException{
      return getMailDao().loadOutReceivedBox(user);
   }

   /**
    * 
    * @param user
    * @return
    * @throws IOException
    * @throws ConfigurationException
    * @throws SQLException
    */
   public static List<FJMail> loadDraftBox(IUser user) throws IOException, ConfigurationException, SQLException{
      return getMailDao().loadDraftBox(user);
   }

   /**
    * 
    * @param user
    * @return
    * @throws IOException
    * @throws SQLException
    * @throws ConfigurationException
    */
   public static List<IFJInterface> findAllInterfaces(IUser user) throws IOException, SQLException, ConfigurationException{
      return getInterfaceDao().findAll(user);
   }
   
   /**
    * 
    * @param user
    * @param id
    * @return
    * @throws IOException
    * @throws SQLException
    * @throws ConfigurationException
    */
   public static IFJInterface findInterface(IUser user, Long id) throws IOException, SQLException, ConfigurationException{
      return getInterfaceDao().find(user, id);
   }

   /**
    * 
    * @param user
    * @param interf
    * @return
    * @throws SQLException
    * @throws ConfigurationException
    * @throws IOException
    */
   public static List<IFJFolder> findAllFolders(IUser user, IFJInterface interf) throws SQLException, ConfigurationException, IOException{
      return getFolderDao().findAll(user, interf);
   }

   /**
    * 
    * @param user
    * @param interf
    * @return
    * @throws SQLException
    * @throws ConfigurationException
    * @throws IOException
    */
   public static List<IFJFolder> findAllFoldersNotIn(IUser user, IFJInterface interf) throws SQLException, ConfigurationException, IOException{
      return getFolderDao().findAllNotIn(user, interf);
   }

   /**
    * 
    * @param user
    * @param active
    * @return
    * @throws SQLException
    * @throws ConfigurationException
    * @throws IOException
    */
   public static List<IFJSubscribe> findAllSubscribes(IUser user, Integer active) throws SQLException, ConfigurationException, IOException{
      return getSubscribedao().findAll(user, active);
   }
}
