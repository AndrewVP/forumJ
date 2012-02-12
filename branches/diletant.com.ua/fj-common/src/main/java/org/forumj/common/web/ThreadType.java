/*
 * Copyright (c) 2012
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
  */
package org.forumj.common.web;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public enum ThreadType {
   COMMON(0), QUEST1(1), QUEST2(2), CLOSED(100);
   
   private int type;

   private ThreadType(int type) {
      this.type = type;
   }

   /**
    * @return the type
    */
   public int getType() {
      return type;
   }

   public static ThreadType valueOfInteger(int type){
      for(ThreadType result : values()){
         if(result.getType() == type) return result;
      }
      throw new IllegalArgumentException("Illegal type parameter: " + type); 
   }
}
