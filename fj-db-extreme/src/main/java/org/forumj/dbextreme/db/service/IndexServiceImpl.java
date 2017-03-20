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
package org.forumj.dbextreme.db.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.*;
import org.forumj.common.db.service.IndexService;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class IndexServiceImpl extends FJService implements IndexService{
   
   /**
    * Возвращает id последнего поста в форуме
    * @return
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public Long getLastPostId() throws ConfigurationException, SQLException, IOException{
      return getPostDao().getLastPostId(); 
   }
   
   /**
    * Возвращает id последнего поста в форуме
    * @return
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public Long getLastPostId(long threadId) throws ConfigurationException, SQLException, IOException{
      return getPostDao().getLastPostId(threadId);
   }

   /**
    * Возвращает id последней ветки в форуме
    * @return
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public Long getMaxThreadId() throws ConfigurationException, SQLException, IOException{
      return getThreadDao().getMaxThreadId();
   }

   public long getThreadsAmount(Long viewId, IUser user) throws Exception{
      return getThreadDao().getThreadsAmount(viewId, user);
   }


   /**
    * 
    * @param viewId
    * @param nfirstpost
    * @param user
    * @return
    * @throws SQLException
    * @throws ConfigurationException
    */
   public List<IFJThread> getThreads(Long viewId, long nfirstpost, IUser user) throws SQLException, ConfigurationException, IOException {
      return getThreadDao().getThreads(viewId, nfirstpost, user);
   }
   
   /**
    * 
    * @param idUser
    * @return
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public int getNewMailCount(Long idUser) throws ConfigurationException, SQLException, IOException{
      return getMailDao().getNewMailCount(idUser);
   }

   /**
    * 
    * @param idView
    * @return
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public String getViewName(Long idView) throws ConfigurationException, SQLException, IOException{
      return getInterfaceDao().getViewName(idView);
   }
   
   /**
    * 
    * @param idUser
    * @return
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public List<IFJInterface> getViews(Long idUser) throws ConfigurationException, SQLException, IOException{
      return getInterfaceDao().getViewsArray(idUser);
   }
   
   
   /**
    * 
    * @return
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public List<IUser> getUsersArray() throws ConfigurationException, SQLException, IOException{
      return getActiondao().getUsersArray();
   }
   
   /**
    * 
    * @return
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public int getGuestsAmount() throws ConfigurationException, SQLException, IOException{
      return getActiondao().getGuestsAmount();
   }
   
}
