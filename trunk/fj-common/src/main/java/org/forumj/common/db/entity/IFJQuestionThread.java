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
package org.forumj.common.db.entity;

import java.util.List;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public interface IFJQuestionThread extends IFJThread{

   /**
    * @param answers the answers to set
    */
   public void setAnswers(List<IQuestNode> answers);

   /**
    * @return the answers
    */
   public List<IQuestNode> getAnswers();

   /**
    * @param answers the answers to set
    */
   public void addAnswer(IQuestNode answer);

   /**
    * @return the question
    */
   public String getQuestion();

   /**
    * @param question the question to set
    */
   public void setQuestion(String question);

   /**
    * @return the usersCanAddCustomAnswers
    */
   public Boolean getUsersCanAddCustomAnswers();

   /**
    * @param usersCanAddCustomAnswers the usersCanAddCustomAnswers to set
    */
   public void setUsersCanAddCustomAnswers(Boolean usersCanAddCustomAnswers);

}