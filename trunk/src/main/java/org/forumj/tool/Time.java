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
package org.forumj.tool;
import static org.forumj.tool.PHP.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class Time {
   
   /**
    * Количество секунд в соответствующих периодах
    *
    * @var unknown_type
    */
   public static final int SECOND = 1;
   public static final int  MINUTE = 60;
   public static final int  HOUR = 3600;
   public static  final int DAY = 86400;
   
   /**
    * Метка времени
    */
   private long timestamp;
   
   /**
    * Создается объект с заданным временем
    * @param unknown_type $timestamp метка времени 
    */
   public Time(long timestamp){
      this.timestamp = timestamp;
   }
   
   /**
    * Форматирует дату по маске
    *
    * @param unknown_type $mask
    */
   public String toString(String mask){
      return date(mask, this.timestamp);
   }
   
   /**
    * Удаляет из времени часы, минуты и секунды
    *
    */
   public void setClearTime(){
       this.timestamp = strtotime(toString("m/d/Y"));
   }
   
   /**
    * Возвращает метку времени без часов, минут и секунд
    *
    */
   public long getClearTime(){
       return strtotime(this.toString("m/d/Y"));
   }
   
   /**
    * Устанавливает метку времени
    *
    * @param unknown_type $timestamp
    */
   public void setTimestamp(long timestamp){
      this.timestamp = timestamp;
   }
   
   /**
    * Возвращает метку времени
    *
    * @return unknown
    */
   public long getTimestamp(){
      return this.timestamp;
   }
   
   /**
    * Добавляет к метке времени соответствующее количество периодов
    *
    * @param unknown_type $value
    * @param unknown_type $seconds
    */
   public void add(long value, long seconds){
      this.timestamp += value * seconds;
   }

   /**
    * Вычитает из метки времени соответствующее количество периодов
    *
    * @param unknown_type $value
    * @param unknown_type $seconds
    */
   public void sub(long value, long seconds){
      this.timestamp -= value * seconds;
   }

}
