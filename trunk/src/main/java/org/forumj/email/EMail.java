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
package org.forumj.email;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class EMail {
   public void sendInvalidQueryMail(int $count, int $time, String $query, String $component){
      String $strMailAll="Count: " + $count + " \r\n";
      $strMailAll +="Time: " + $time + " c\r\n";
      $strMailAll+="Query: " + $query + "\r\n";
      $strMailAll+="Component: " + $component;
      String $server="smtp.freehost.com.ua";
      String $from="diletant@diletant.com.ua";
      String $subject="Long query time on Diletant";
      String $headers="Content-type: text/html; charset=\"windows-1251\"";
      $headers="From: "+$from+"\nSubject: "+$subject+"\nX-Mailer: Diletant\n"+$headers;
      //TODO Сделать!
//      mail("an.home@mail.ru", $subject, $strMailAll, $headers);
   }
}
