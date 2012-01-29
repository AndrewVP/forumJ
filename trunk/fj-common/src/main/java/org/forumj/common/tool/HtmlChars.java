/*
 * Copyright (c) 2012
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
  */
package org.forumj.common.tool;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class HtmlChars {
   
   private static String chars[][] = new String[][]{
      {"\"", " &quot;"},
      {"<", "&lt;"},
      {">", "&gt;"},
      {"'", "&apos;"},
      {"\\", ""},
      };

   public static String convertHtmlSymbols(String text){
      for (int i = 0; i < chars.length; i++) {
         String[] symbol = chars[i];
         text = text.replace(chars[i][0], chars[i][1]);
      }
      return text;
   }

   private static String convertHtmlSymbol(String text, String symbol, String replace){
      String result = "";
      int position = text.indexOf(symbol);
      if(position > -1){
         text.replace(symbol, replace);
      }
      return result;
   }
}
