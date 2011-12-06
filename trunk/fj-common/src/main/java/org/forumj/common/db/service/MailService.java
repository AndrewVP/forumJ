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
public interface MailService {

   public void deleteMailFromInbox(Long mailId, IUser user)
         throws ConfigurationException, SQLException, IOException;

   public void deleteMailFromOutbox(Long mailId, IUser user)
         throws ConfigurationException, SQLException, IOException;

   /**
    * 
    * @param userId
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public void receiveMail(Long userId) throws ConfigurationException,
         SQLException, IOException;

   /**
    * 
    * @param user
    * @return
    * @throws IOException
    * @throws ConfigurationException
    * @throws SQLException
    */
   public List<IFJMail> loadInbox(IUser user) throws IOException,
         ConfigurationException, SQLException;

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
   public IFJMail loadMail(IUser user, Long mailId, boolean userIsSender)
         throws IOException, ConfigurationException, SQLException;

   /**
    * 
    * @param userId
    * @param mailId
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public void markMailAsRead(Long userId, Long mailId)
         throws ConfigurationException, SQLException, IOException;

   /**
    * 
    * @param user
    * @return
    * @throws IOException
    * @throws ConfigurationException
    * @throws SQLException
    */
   public List<IFJMail> loadOutNotReceivedBox(IUser user) throws IOException,
         ConfigurationException, SQLException;

   /**
    * 
    * @param user
    * @return
    * @throws IOException
    * @throws ConfigurationException
    * @throws SQLException
    */
   public List<IFJMail> loadOutReceivedBox(IUser user) throws IOException,
         ConfigurationException, SQLException;

   /**
    * 
    * @param user
    * @return
    * @throws IOException
    * @throws ConfigurationException
    * @throws SQLException
    */
   public List<IFJMail> loadDraftBox(IUser user) throws IOException,
         ConfigurationException, SQLException;

}