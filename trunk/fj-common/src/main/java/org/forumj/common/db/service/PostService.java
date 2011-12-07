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
import org.forumj.common.exception.DBException;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public interface PostService {
   
   public IFJPost getPostObject();
   
   public IFJPostBody getPostbodyObject();
   
   public IFJPostHead getPostHeadObject();

   public Long create(IFJPost post) throws IOException, DBException, ConfigurationException, SQLException;
   
   public void update(IFJPost post) throws IOException, SQLException, ConfigurationException;

   /**
    * 
    * @param id
    * @return
    * @throws IOException
    * @throws ConfigurationException
    * @throws SQLException
    */
   public IFJPost read(Long id) throws IOException, ConfigurationException, SQLException;

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
   public List<IFJPost> readPosts(IUser user, Long threadId, long nfirstpost, int count, int page, boolean lastPost) throws IOException, SQLException, ConfigurationException;

   /**
    * 
    * @param threadId
    * @param idMax
    * @return
    * @throws ConfigurationException
    * @throws SQLException
    * @throws IOException
    */
   public Integer getPostsCountInThread(Long threadId, Long idMax)
         throws ConfigurationException, SQLException, IOException;
}
