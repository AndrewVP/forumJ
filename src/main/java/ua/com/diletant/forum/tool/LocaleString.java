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

import java.util.*;

import ua.com.diletant.forum.exception.InvalidKeyException;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class LocaleString{
   /**
    * Массив сообщений формы
    * @var unknown_type
    */
   private Map<String, String> arrStrings = new HashMap<String, String>();
   
   /**
    * Конструктор
    *
    * @param unknown_type $strLang
    * @param unknown_type $strFileName
    * @param unknown_type $strDefaultLang
    */
   public LocaleString(String strLang, String strFileName, String strDefaultLang){
//      $_string = array();
//      switch ($strLang) {
//         case "ru":
//            include_once $strFileName."ru".".php";
//            break;
//         case "ua":
//            include_once $strFileName."ua".".php";
//            break;
//         default:
//            include_once $strFileName.$strDefaultLang.".php";
//            break;
//      }
//      $this->arrStrings = $_string;    
   }
   
   /**
    * Возвращает локализованную строку
    *
    * @param unknown_type $strKey
    * @return unknown
    * @throws InvalidKeyException 
    */
   public String getString(String strKey) throws InvalidKeyException{
      String result = null;
      if (strKey == null){
         throw new InvalidKeyException("Key can not be null!"); 
      }else{
         result = arrStrings.get(strKey);
         if (result == null){
            throw new InvalidKeyException("Invalid key " + strKey + "!"); 
         }else{
            return result;
         }

      }
   }
}
