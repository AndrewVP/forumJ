/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
  */
package org.forumj.db.entity;

import java.util.*;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJQuestionThread extends FJThread {

   private List<QuestNode> answers = new ArrayList<QuestNode>();
   
   private String question = null;
   
   private Boolean usersCanAddCustomAnswers = null;

   /**
    * @param answers the answers to set
    */
   public void setAnswers(List<QuestNode> answers) {
      this.answers = answers;
   }

   /**
    * @return the answers
    */
   public List<QuestNode> getAnswers() {
      return answers;
   }

   /**
    * @param answers the answers to set
    */
   public void addAnswer(QuestNode answer) {
      this.answers.add(answer);
   }

   /**
    * @return the question
    */
   public String getQuestion() {
      return question;
   }

   /**
    * @param question the question to set
    */
   public void setQuestion(String question) {
      this.question = question;
   }

   /**
    * @return the usersCanAddCustomAnswers
    */
   public Boolean getUsersCanAddCustomAnswers() {
      return usersCanAddCustomAnswers;
   }

   /**
    * @param usersCanAddCustomAnswers the usersCanAddCustomAnswers to set
    */
   public void setUsersCanAddCustomAnswers(Boolean usersCanAddCustomAnswers) {
      this.usersCanAddCustomAnswers = usersCanAddCustomAnswers;
   }
   
}
