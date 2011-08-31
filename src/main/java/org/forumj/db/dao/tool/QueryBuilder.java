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
   
   private static String updatePostQuery = null;

   private static String updatePostBodyQuery = null;

   private static String updatePostHeadQuery = null;

   private static String readThreadQuery = null;
   
   private static String firstPostIdInThreadQuery = null;

   private static String createPostQuery = null;
   
   private static String readPostQuery = null;

   private static String readPostHeadQuery = null;

   private static String readPostBodyQuery = null;
   
   private static String createPostBodyQuery = null;
   
   private static String createPostHeadQuery = null;

   private static String loadAnswersQuery = null;

   private static String loadIgnorQuery = null;

   private static String createAnswerQuery = null;
   
   private static String receiveMailQuery = null;

   private static String markMailAsReadQuery = null;
   
   private static String loadInboxQuery = null;
   
   private static String loadOutNotReceivedBoxQuery = null;

   private static String loadOutReceivedBoxQuery = null;

   private static String loadDraftBoxQuery = null;
   
   private static String loadMailQuery = null;

   private static String loadInterfacesQuery = null;
   
   private static String loadInterfaceQuery = null;

   private static String loadFoldersInQuery = null;
   
   private static String loadFoldersNotInQuery = null;
   
   private static String loadFoldersQuery = null;
   
   private static String loadSubscribesQuery = null;
   
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
   
   public static String getUpdatePostQuery() throws IOException{
      if (updatePostQuery == null){
         updatePostQuery = loadQuery("/sql/update_post.sql");
      }
      return updatePostQuery;
   }
   
   public static String getUpdatePostBodyQuery(String bodyTable) throws IOException{
      if (updatePostBodyQuery == null){
         updatePostBodyQuery = loadQuery("/sql/update_post_body.sql");
      }
      return updatePostBodyQuery.replace("@@TABLE@@", bodyTable);
   }
   
   public static String getUpdatePostHeadQuery(String headTable) throws IOException{
      if (updatePostHeadQuery == null){
         updatePostHeadQuery = loadQuery("/sql/update_post_head.sql");
      }
      return updatePostHeadQuery.replace("@@TABLE@@", headTable);
   }
   
   public static String getReadThreadQuery() throws IOException{
      if (readThreadQuery == null){
         readThreadQuery = loadQuery("/sql/read_thread.sql");
      }
      return readThreadQuery;
   }
   
   public static String getFirstPostIdInThreadQuery() throws IOException{
      if (firstPostIdInThreadQuery == null){
         firstPostIdInThreadQuery = loadQuery("/sql/first_post_id_in_thread.sql");
      }
      return firstPostIdInThreadQuery;
   }
   
   public static String getCreatePostQuery() throws IOException{
      if (createPostQuery == null){
         createPostQuery = loadQuery("/sql/create_post.sql");
      }
      return createPostQuery;
   }
   
   public static String getReadPostQuery() throws IOException{
      if (readPostQuery == null){
         readPostQuery = loadQuery("/sql/read_post.sql");
      }
      return readPostQuery;
   }
   
   public static String getReadPostHeadQuery(String headTable) throws IOException{
      if (readPostHeadQuery == null){
         readPostHeadQuery = loadQuery("/sql/read_post_head.sql");
      }
      return readPostHeadQuery.replace("@@TABLE@@", headTable);
   }
   
   public static String getReadPostBodyQuery(String bodyTable) throws IOException{
      if (readPostBodyQuery == null){
         readPostBodyQuery = loadQuery("/sql/read_post_body.sql");
      }
      return readPostBodyQuery.replace("@@TABLE@@", bodyTable);
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
   
   public static String getLoadAnswersQuery() throws IOException{
      if (loadAnswersQuery == null){
         loadAnswersQuery = loadQuery("/sql/load_answers.sql");
      }
      return loadAnswersQuery;
   }
   
   public static String getLoadIgnorQuery() throws IOException{
      if (loadIgnorQuery == null){
         loadIgnorQuery = loadQuery("/sql/load_ignor.sql");
      }
      return loadIgnorQuery;
   }
   
   public static String getCreateAnswerQuery() throws IOException{
      if (createAnswerQuery == null){
         createAnswerQuery = loadQuery("/sql/create_answer.sql");
      }
      return createAnswerQuery;
   }
   
   public static String getReceiveMailQuery() throws IOException{
      if (receiveMailQuery  == null){
         receiveMailQuery  = loadQuery("/sql/receive_mail.sql");
      }
      return receiveMailQuery ;
   }
   
   public static String getMarkMailAsReadQuery() throws IOException{
      if (markMailAsReadQuery  == null){
         markMailAsReadQuery  = loadQuery("/sql/mark_mail_as_read.sql");
      }
      return markMailAsReadQuery ;
   }
   
   public static String getLoadInboxQuery() throws IOException{
      if (loadInboxQuery  == null){
         loadInboxQuery  = loadQuery("/sql/load_inbox.sql");
      }
      return loadInboxQuery ;
   }
   
   public static String getLoadOutNotReceivedBoxQuery() throws IOException{
      if (loadOutNotReceivedBoxQuery  == null){
         loadOutNotReceivedBoxQuery  = loadQuery("/sql/load_out_not_received_box.sql");
      }
      return loadOutNotReceivedBoxQuery ;
   }
   
   public static String getLoadOutReceivedBoxQuery() throws IOException{
      if (loadOutReceivedBoxQuery  == null){
         loadOutReceivedBoxQuery  = loadQuery("/sql/load_out_received_box.sql");
      }
      return loadOutReceivedBoxQuery;
   }
   
   public static String getLoadDraftBoxQuery() throws IOException{
      if (loadDraftBoxQuery  == null){
         loadDraftBoxQuery  = loadQuery("/sql/load_draft_box.sql");
      }
      return loadDraftBoxQuery ;
   }
   
   public static String getLoadMailQuery() throws IOException{
      if (loadMailQuery  == null){
         loadMailQuery  = loadQuery("/sql/load_mail.sql");
      }
      return loadMailQuery ;
   }
   
   public static String getLoadInterfacesQuery() throws IOException{
      if (loadInterfacesQuery == null){
         loadInterfacesQuery = loadQuery("/sql/load_interfaces.sql");
      }
      return loadInterfacesQuery;
   }
   
   public static String getLoadInterfaceQuery() throws IOException{
      if (loadInterfaceQuery == null){
         loadInterfaceQuery = loadQuery("/sql/load_interface.sql");
      }
      return loadInterfaceQuery;
   }
   
   public static String getLoadFoldersInQuery() throws IOException{
      if (loadFoldersInQuery == null){
         loadFoldersInQuery = loadQuery("/sql/load_folders_in.sql");
      }
      return loadFoldersInQuery;
   }
   
   public static String getLoadFoldersNotInQuery() throws IOException{
      if (loadFoldersNotInQuery == null){
         loadFoldersNotInQuery = loadQuery("/sql/load_folders_not_in.sql");
      }
      return loadFoldersNotInQuery;
   }
   
   public static String getLoadFoldersQuery() throws IOException{
      if (loadFoldersQuery == null){
         loadFoldersQuery = loadQuery("/sql/load_folders.sql");
      }
      return loadFoldersQuery;
   }
   
   public static String getLoadSubscribesQuery() throws IOException{
      if (loadSubscribesQuery == null){
         loadSubscribesQuery = loadQuery("/sql/load_subscribes.sql");
      }
      return loadSubscribesQuery;
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
