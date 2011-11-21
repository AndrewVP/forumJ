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
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJThreads {

   private List<FJThread> threads = new ArrayList<FJThread>();
   
   /**
    * Количество веток
    */
   private long threadCount;
   
   /**
    * Строка id для индикаторов
    *
    * @var unknown_type
    */
   private String indctrIds = "";

   /**
    * @return the threads
    */
   public List<FJThread> getThreads() {
      return threads;
   }

   /**
    * @param threads the threads to set
    */
   public void setThreads(List<FJThread> threads) {
      this.threads = threads;
   }

   /**
    * @return the threadCount
    */
   public long getThreadCount() {
      return threadCount;
   }

   /**
    * @param threadCount the threadCount to set
    */
   public void setThreadCount(long threadCount) {
      this.threadCount = threadCount;
   }

   /**
    * @return the indctrIds
    */
   public String getIndctrIds() {
      return indctrIds;
   }

   /**
    * @param indctrIds the indctrIds to set
    */
   public void setIndctrIds(String indctrIds) {
      this.indctrIds = indctrIds;
   }
   
}
