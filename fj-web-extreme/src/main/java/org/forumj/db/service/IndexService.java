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
import org.forumj.db.entity.*;
import org.forumj.tool.LocaleString;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class IndexService extends FJCommonService{
   
   /**
    * Возвращает id последнего поста в форуме
    * @return
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public static Long getLastPostId() throws ConfigurationException, SQLException, IOException{
      return getPostDao().getLastPostId(); 
   }
   
   /**
    * Возвращает id последней ветки в форуме
    * @return
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public static Long getMaxThreadId() throws ConfigurationException, SQLException, IOException{
      return getThreadDao().getMaxThreadId();
   }
   
   /**
    * 
    * @param viewId
    * @param nfirstpost
    * @param locale
    * @param user
    * @param ignorList
    * @return
    * @throws SQLException
    * @throws ConfigurationException
    */
   public static FJThreads getThreads(Long viewId, long nfirstpost, LocaleString locale, IUser user, List<Ignor> ignorList) throws SQLException, ConfigurationException{
      return getThreadDao().getThreads(viewId, nfirstpost, locale, user, ignorList);
   }
   
   /**
    * 
    * @param idUser
    * @return
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public static int getNewMailCount(Long idUser) throws ConfigurationException, SQLException, IOException{
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
   public static String getViewName(Long idView) throws ConfigurationException, SQLException, IOException{
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
   public static List<IFJInterface> getViews(Long idUser) throws ConfigurationException, SQLException, IOException{
      return getInterfaceDao().getViewsArray(idUser);
   }
   
   
   /**
    * 
    * @return
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public static List<IUser> getUsersArray() throws ConfigurationException, SQLException, IOException{
      return getActiondao().getUsersArray();
   }
   
   /**
    * 
    * @return
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public static int getGuestsAmount() throws ConfigurationException, SQLException, IOException{
      return getActiondao().getGuestsAmount();
   }
   
}
