/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.common;

import org.apache.commons.configuration.*;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJConfiguration {
   
   private static Configuration config = null;
   
   public static Configuration getConfig() throws ConfigurationException{
      if (config == null){
         config = new PropertiesConfiguration("fj.properties");
      }
      return config;
   }

}
