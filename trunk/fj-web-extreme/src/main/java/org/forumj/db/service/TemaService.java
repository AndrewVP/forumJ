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
import org.forumj.common.db.entity.IUser;
import org.forumj.db.entity.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class TemaService extends FJCommonService {

   /**
    * 
    * @param id
    * @return
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public static FJThread readThread(Long id) throws ConfigurationException, SQLException, IOException{
      return getThreadDao().read(id);
   }
   
   /**
    * 
    * @param threadId
    * @param idMax
    * @return
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public static Integer getPostsCountInThread(Long threadId, Long idMax) throws ConfigurationException, SQLException, IOException{
      return getThreadDao().getPostsCountInThread(threadId, idMax);
   }
   
   /**
    * 
    * @param user
    * @param threadId
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public static void setSeen(IUser user, Long threadId) throws ConfigurationException, SQLException, IOException{
      getThreadDao().setSeen(user, threadId);
   }

   /**
    * 
    * @param user
    * @param threadId
    * @param nfirstpost
    * @param count
    * @param page
    * @param lastPost
    * @return
    * @throws IOException
    * @throws SQLException
    * @throws ConfigurationException
    */
   public static List<FJPost> readPosts(IUser user, Long threadId, long nfirstpost, int count, int page, boolean lastPost) throws IOException, SQLException, ConfigurationException{
      return getPostDao().getPostsList(user, threadId, nfirstpost, count, page, lastPost);
   }
   
   /**
    * 
    * @param id
    * @return
    * @throws IOException
    * @throws ConfigurationException
    * @throws SQLException
    */
   public static FJPost readPost(Long id) throws IOException, ConfigurationException, SQLException{
      return getPostDao().read(id);
   }

   /**
    * 
    * @param idUser
    * @param threadId
    * @return
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public static Boolean isUserSubscribed(Long idUser, Long threadId) throws ConfigurationException, SQLException, IOException{
      return getSubscribedao().isUserSubscribed(idUser, threadId);
   }
   
   /**
    * 
    * @param threadId
    * @param user
    * @return
    * @throws SQLException
    * @throws ConfigurationException
    * @throws IOException
    */
   public static boolean isUserVoted(long threadId, IUser user) throws SQLException, ConfigurationException, IOException{
      return getVoiceDao().isUserVoted(threadId, user);
   }
   
   
}
