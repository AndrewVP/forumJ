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
package ua.com.diletant.forum.db.sql.result;

import java.util.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class QueryResult {
   
   /**
    * SQL-запрос
    *
    * @var string
    */
   private String query;

   /**
    * result непосредственно
    */
   private List<Map<String, Object>> result;
   
   /**
    * Время выполнения SQL-запроса
    */
   private int queryTime;
   
   public QueryResult(String $query){
      this.setQuery($query);
   }
   
   /**
    * Возвращает result непосредственно
    */
   public List<Map<String, Object>> getResult(){
      return this.result;
   }
   
   /**
    * Устанавливает result непосредственно
    */
   public void setResult(List<Map<String, Object>> result){
      this.result = result;
   }

   /**
    * Возвращает SQL-запрос
    */
   public String getQuery(){
      return this.query;
   }

   /**
    * Устанавливает SQL-запрос
    */
   public void setQuery(String query){
      this.query = query;
   }

   /**
    * Возвращает время выполнения SQL-запроса
    */
   public int getQueryTime(){
      return this.queryTime;
   }

   /**
    * Устанавливает время выполнения SQL-запроса
    */
   public void setQueryTime(int queryTime){
      this.queryTime = queryTime;
   }
}
