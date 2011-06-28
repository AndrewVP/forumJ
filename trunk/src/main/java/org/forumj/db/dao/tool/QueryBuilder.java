/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
  */
package org.forumj.db.dao.tool;

import java.io.*;

import org.forumj.web.servlet.tool.FJServletTools;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class QueryBuilder {
   
   private static String loadConfigQuery = null;

   private static String createThreadQuery = null;

   private static String updateThreadQuery = null;

   private static String createPostQuery = null;
   
   private static String createPostBodyQuery = null;
   
   private static String createPostHeadQuery = null;
   
   public static String getLoadConfigQuery() throws IOException{
      if (loadConfigQuery == null){
         loadConfigQuery = loadQuery("/sql/load_config.sql");
      }
      return loadConfigQuery;
   }
   
   public static String getCreateThreadQuery() throws IOException{
      if (createThreadQuery == null){
         createThreadQuery = loadQuery("/sql/create_thread.sql");
      }
      return createThreadQuery;
   }
   
   public static String getUpdateThreadQuery() throws IOException{
      if (updateThreadQuery == null){
         updateThreadQuery = loadQuery("/sql/update_thread.sql");
      }
      return updateThreadQuery;
   }
   
   public static String getCreatePostQuery() throws IOException{
      if (createPostQuery == null){
         createPostQuery = loadQuery("/sql/create_post.sql");
      }
      return createPostQuery;
   }
   
   public static String getCreatePostBodyQuery(String bodyTableName) throws IOException{
      if (createPostBodyQuery == null){
         createPostBodyQuery = loadQuery("/sql/create_post_body.sql");
      }
      return createPostBodyQuery.replace("@@currentBodyTable@@", bodyTableName);
   }
   
   public static String getCreatePostHeadQuery(String headTableName) throws IOException{
      if (createPostHeadQuery == null){
         createPostHeadQuery = loadQuery("/sql/create_post_head.sql");
      }
      return createPostHeadQuery.replace("@@currentHeadTable@@", headTableName);
   }
   
   private static String loadQuery(String path) throws IOException{
      ClassLoader classLoader = FJServletTools.class.getClassLoader();
      InputStream stream = classLoader.getResourceAsStream(path);
      BufferedReader br = new BufferedReader(new InputStreamReader(stream));
      StringBuffer result = new StringBuffer();
      while(br.ready()){
         result.append(br.readLine() + "\n");
      }
      return result.toString();
   }
}
