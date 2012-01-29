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

import org.apache.commons.configuration.*;
import org.forumj.common.exception.InvalidKeyException;


/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class LocaleString{

   private PropertiesConfiguration locales = null; 
   private PropertiesConfiguration defaultLocales = null; 
   String strLang = null;
   
   /**
    * Конструктор
    *
    * @param unknown_type $strLang
    * @param unknown_type $strFileName
    * @param unknown_type $strDefaultLang
    * @throws ConfigurationException 
    */
   public LocaleString(String strLang, String strFileName, String strDefaultLang) throws ConfigurationException{
      this.strLang = strLang;
      locales = new PropertiesConfiguration();
      locales.setDelimiterParsingDisabled(true);
      locales.setFileName("nls/" + strFileName + "_" + strLang + ".properties");
      locales.setEncoding("UTF-8");
      locales.load();
      defaultLocales = new PropertiesConfiguration();
      defaultLocales.setDelimiterParsingDisabled(true);
      defaultLocales.setFileName("nls/" + strFileName + "_" + strDefaultLang + ".properties");
      defaultLocales.setEncoding("UTF-8");
      defaultLocales.load();
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
         result = locales.getString(strKey);
         if (result == null){
            result = defaultLocales.getString(strKey);
            if (result == null){
               throw new InvalidKeyException("Invalid key " + strKey + "!"); 
            }
         }
      }
      return result;
   }
   
   public String getLanguage(){
      return strLang;
   }
}
