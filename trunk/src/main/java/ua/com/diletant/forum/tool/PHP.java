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

import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.text.rtf.RTFEditorKit;

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

   public static String str_ireplace(String[] search, String replace, String source){
      int start = 0;
      int end = 0;
      StringBuffer result = new StringBuffer();
      for (int wordIndex = 0; wordIndex < search.length; wordIndex++) {
         String word = search[wordIndex];
         while ((end = source.indexOf(word, start)) >= 0) {
            result.append(source.substring(start, end));
            result.append(replace);
            start = end + word.length();
         }
      }
      result.append(source.substring(start));
      return result.toString();
   }

   public static String stripslashes(String text){
      //TODO Доделать
      return text;
   }

   public static String htmlspecialchars(String text){
      return HTMLEntities.htmlentities(text);
   }

   public static int ceil(double argument){
      long roundResult = Math.round(argument);
      if (roundResult - argument < 0){
         roundResult++;
      }
      return (int)roundResult;
   }

   public static String substr(String string, int start , int length){
      return string.substring(start, string.length() - length + 1);
   }

   public static String substr(String string, int start){
      return string.substring(start);
   }

   public static String date(String mask , long timestamp){
      SimpleDateFormat format = new SimpleDateFormat(mask);
      Date date = new Date();
      date.setTime(timestamp);
      return format.format(date);
   }

   public static long strtotime(String text){
      return StrToTime.strtotime(text).getTime();
   }

   public static String preg_replace(String[] patterns, String replacement, String subject ){
      String result = subject;
      for (int patternIndex = 0; patternIndex < patterns.length; patternIndex++) {
         String pattern = patterns[patternIndex];
         result = result.replaceAll(pattern, replacement);
      }
      return result;
   }

   public static String trim(String string){
      return string.trim();
   }

   public static int strrpos(String haystack ,String needle){
      return haystack.lastIndexOf(needle);
   }

   public static int strpos(String haystack ,String needle){
      return haystack.indexOf(needle);
   }

   public static int strpos(String haystack ,String needle, int start){
      return haystack.indexOf(needle, start);
   }
   
   public static boolean in_array(String needle, String[] haystack) {
      for(int arrIndex=0; arrIndex < haystack.length; arrIndex++) {
         if(haystack[arrIndex].equals(needle)) {
            return true;
         }
      }
      return false;
   }

   public static boolean in_array(Long needle, Long[] haystack) {
      for(int arrIndex=0; arrIndex < haystack.length; arrIndex++) {
         if(haystack[arrIndex].longValue() == needle.longValue()) {
            return true;
         }
      }
      return false;
   }
   
   public static String chr(int chr){
      return String.valueOf((char) chr);
   }
   
   public static boolean isset(Object var){
      return var != null;
   }
   
   public static int strlen(String string){
      return string.length();
   }
   
   public static String nl2br(String string){
      return string.replace("\n","<br>");
   }
   
   public static long round(double arg, int precision){
      Formatter formatter = new Formatter();
      return Math.round(arg);
   }
}
