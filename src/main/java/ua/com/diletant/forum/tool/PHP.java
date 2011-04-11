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
package ua.com.diletant.forum.tool;

/**
 * PHP functions 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class PHP {

   public static String str_replace(String search, String replace, String source){
      int start = 0;
      int end = 0;
      StringBuffer result = new StringBuffer();
      while ((end = source.indexOf(search, start)) >= 0) {
         result.append(source.substring(start, end));
         result.append(replace);
         start = end + search.length();
      }
      result.append(source.substring(start));
      return result.toString();

   }
}
