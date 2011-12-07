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
import java.sql.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.*;
import org.forumj.common.db.service.QuestService;
import org.forumj.dbextreme.db.entity.QuestNode;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class QuestServiceImpl extends FJService implements QuestService {

   public void repealVote(Long threadId, IUser user) throws ConfigurationException, IOException, SQLException{
      getQuestNodeDao().repealVote(threadId, user);
   }

   public void addCustomAnswer(long threadId, String node, int type, IUser user) throws ConfigurationException, IOException, SQLException {
      getQuestNodeDao().addCustomAnswer(threadId, node, type, user);
   }

   public void addVote(Long threadId, Long answerId, IUser user, Connection connection) throws ConfigurationException, IOException, SQLException {
      getQuestNodeDao().addVote(threadId, answerId, user, connection);
   }

   public IQuestNode getQuestNodeObject() {
      return new QuestNode();
   }

   public IQuestNode getQuestNodeObject(Integer numb, String node, Long userId) {
      return new QuestNode(numb, node, userId);
   }
}
