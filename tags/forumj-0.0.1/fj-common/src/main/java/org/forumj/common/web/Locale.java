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
package org.forumj.common.web;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public enum Locale {
   
   UA("ua", 1), RU("ru", 2);
   
   private String name;
   
   private int code;

   /**
    * @param name
    * @param code
    */
   private Locale(String name, int code) {
      this.name = name;
      this.code = code;
   }

   public String getName() {
      return name;
   }

   public int getCode() {
      return code;
   }

   public static Locale valueOfString(String value){
      if (value != null && !value.trim().isEmpty()){
         for(Locale result : values()){
            if (result.getName().equalsIgnoreCase(value)) return result;
         }
      }else{
         throw new NullPointerException("Value parameter can't be null!");
      }
      throw new IllegalArgumentException("Illegal value parameter: " + value);
   }
   
   public static Locale valueOfInteger(int value){
      for(Locale result : values()){
         if (result.getCode() == value) return result;
      }
      throw new IllegalArgumentException("Illegal value parameter: " + value);
   }
   
}
