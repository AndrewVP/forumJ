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

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class SelectQueryResult extends QueryResult {

   public SelectQueryResult(String $query) {
      super($query);
   }

   /**
    * Количество строк
    *
    * @var unknown_type
    */
   private int numRows = 0; 

   /**
    * Возвращает количество строк
    *
    * @return unknown
    */
   public int getNumRows(){
      if (this.numRows == 0){
         this.numRows = this.getResult().size();
      }
      return this.numRows;
   }

   /**
    * Возвращает значение поля.
    * По умолчанию - нулевой записи 
    *
    * @param unknown_type $field
    * @param unknown_type $row
    * @return unknown
    */
   public Object get(String field, int... rows){
      int row = 0;
      if (rows.length != 0){
         row = rows[0];
      }
      return this.getResult().get(row).get(field);
   }
}
