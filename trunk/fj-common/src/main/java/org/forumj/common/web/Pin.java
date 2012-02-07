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
public enum Pin {
   
   COMMON(0), BIRTHDAY(3), NOTICE(5), PIN(10);
   
   private int code;

   /**
    * @param code
    */
   private Pin(int code) {
      this.code = code;
   }

   public int getCode() {
      return code;
   }
   
   public static Pin valueOfInteger(int code){
      for(Pin result : values()){
         if (result.getCode() == code) return result;
      }
     throw new IllegalArgumentException("Illegal code parameter: " + code); 
   }

}
