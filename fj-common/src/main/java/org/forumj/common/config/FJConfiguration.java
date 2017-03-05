/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.common.config;

import org.apache.commons.configuration.*;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJConfiguration {

   public final static String HOME_DIR = "fj.home.dir";
   public final static String AVATARS_CONTEXT_DIR = "avatarsContextDir";
   public final static String AVATAR = "avatar";
   public final static String ALBUM_THUMB = "album.thumb";
   public final static String POST_THUMB = "post.thumb";
   public final static String WIDTH = "width";
   public final static String HEIGHT = "height";
   public final static String AVATAR_WIDTH = AVATAR + "." + WIDTH;
   public final static String AVATAR_HEIGHT = AVATAR + "." + HEIGHT;

   private static Configuration config = null;
   
   public static Configuration getConfig() throws ConfigurationException{
      if (config == null){
         config = new PropertiesConfiguration("fj.properties");
      }
      return config;
   }

}
