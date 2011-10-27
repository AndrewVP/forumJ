/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.db.dao;

import static org.forumj.db.dao.tool.QueryBuilder.*;
import static org.forumj.db.entity.IFJPost.*;

import java.io.IOException;
import java.sql.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.db.entity.*;
import org.forumj.exception.DBException;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJPostDao extends FJDao {

   public Long create(FJPost post, Connection conn) throws IOException, DBException, SQLException, ConfigurationException{
      Long postId = null;
      String createPostQuery = getCreatePostQuery();
      FJForumDao forumDao = new FJForumDao();
      String tableBody = forumDao.getCurretBodyTable();
      String tableHead = forumDao.getCurretBodyHeadTable(); 
      String createPostBodyQuery = getCreatePostBodyQuery(tableBody);
      String createPostHeadQuery = getCreatePostHeadQuery(tableHead);
      PreparedStatement st = null;
      try {
         st = conn.prepareStatement(createPostQuery, new String[]{"id"});
         st.setLong(1, post.getThreadId());
         st.setInt(2, post.getState());
         st.setString(3, tableBody);
         st.setString(4, tableHead);
         st.executeUpdate();
         ResultSet idRs = st.getGeneratedKeys();
         if (idRs.next()){
            postId = idRs.getLong(1);
            post.setId(postId);
            st.close();
            st = conn.prepareStatement(createPostBodyQuery);
            FJPostBody postBody = post.getBody();
            st.setLong(1, postId);
            st.setLong(2, postId);
            st.setString(3, postBody.getBody());
            st.executeUpdate();
            st.close();
            st = conn.prepareStatement(createPostHeadQuery);
            FJPostHead postHead = post.getHead();
            st.setLong(1, postId);
            st.setLong(2, postId);
            st.setLong(3, postHead.getAuth());
            st.setLong(4, postHead.getThreadId());
            st.setString(5, postHead.getTitle());
            st.setInt(6, (int) postHead.getCreateTime().getTime());
            st.setString(7, postHead.getIp());
            st.setString(8, postHead.getDomen());
            st.executeUpdate();
            FJThreadDao threadDao = new FJThreadDao();
            FJThread thread = threadDao.read(post.getThreadId());
            thread.setLastPostId(postId);
            threadDao.update(thread, conn);
         }else{
            throw new DBException("Post wasn't created");
         }
      }finally{
         readFinally(null, st);
      }
      return postId;
   }

   public Long create(FJPost post) throws IOException, DBException, ConfigurationException, SQLException{
      Connection conn = null;
      try {
         conn = getConnection();
         return create(post, conn);
      }finally{
         readFinally(conn, null);
      }
   }

   public void update(FJPost post) throws IOException, SQLException, ConfigurationException{
      String updatePostQuery = getUpdatePostQuery();
      String updatePostHeadQuery = getUpdatePostHeadQuery(post.getTableHead());
      String updatePostBodyQuery = getUpdatePostBodyQuery(post.getTablePost());
      Connection conn = null;
      PreparedStatement st = null;
      boolean error = true;
      try {
         conn = getConnection();
         conn.setAutoCommit(false);
         
         st = conn.prepareStatement(updatePostQuery);
         st.setLong(1, post.getThreadId());
         st.setInt(2, post.getState());
         st.setString(3, post.getTablePost());
         st.setString(4, post.getTableHead());
         st.setLong(5, post.getId());
         st.executeUpdate();
         
         FJPostHead postHead = post.getHead();
         st = conn.prepareStatement(updatePostHeadQuery);
         st.setLong(1, postHead.getAuth());
         st.setString(2, postHead.getTitle());
         st.setString(3, postHead.getIp());
         st.setString(4, postHead.getDomen());
         st.setString(5, postHead.getOutd());
         st.setInt(6, postHead.getNred());
         st.setInt(7, (int) postHead.getCreateTime().getTime());
         st.setInt(8, (int) postHead.getEditTime().getTime());
         st.setLong(9, postHead.getPostId());
         st.setLong(10, postHead.getThreadId());
         st.setLong(11, postHead.getId());
         st.executeUpdate();
         
         FJPostBody postBody = post.getBody();
         st = conn.prepareStatement(updatePostBodyQuery);
         st.setLong(1, postBody.getPostId());
         st.setString(2, postBody.getBody());
         st.setLong(3, postBody.getId());
         st.executeUpdate();
         
         if (isFirstPost(post.getId(), post.getThreadId(), conn)){
            FJThreadDao threadDao = new FJThreadDao();
            FJThread thread = threadDao.read(post.getThreadId());
            thread.setHead(postHead.getTitle());
            threadDao.update(thread, conn);
         }
         error = false;
      }finally{
         writeFinally(conn, st, error);
      }
   }

   private boolean isFirstPost(Long postId, Long threadId, Connection conn) throws ConfigurationException, SQLException, IOException{
      boolean result = false;
      String firstPostIdInThreadQuery = getFirstPostIdInThreadQuery(); 
      PreparedStatement st = null;
      try {
         st = conn.prepareStatement(firstPostIdInThreadQuery);
         st.setLong(1, threadId);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = postId == rs.getLong("id");
         }
      }finally{
         readFinally(null, st);
      }
      return result;
   }
   
   public FJPost read(Long id) throws IOException, ConfigurationException, SQLException{
      FJPost result = null;
      String readPostQuery = getReadPostQuery();
      PreparedStatement st = null;
      Connection conn = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(readPostQuery);
         st.setLong(1, id);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = new FJPost();
            result.setId(id);
            result.setState(rs.getInt(STATE_FIELD_NAME));
            result.setThreadId(rs.getLong(THREAT_ID_FIELD_NAME));
            result.setTableHead(rs.getString(TABLE_HEAD_FIELD_NAME));
            result.setTablePost(rs.getString(TABLE_POST_FIELD_NAME));
            FJPostHead postHead = readHead(id, result.getTableHead(), conn);
            result.setHead(postHead);
            FJPostBody postBody = readBody(id, result.getTablePost(), conn);
            result.setBody(postBody);
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
   
   private FJPostHead readHead(Long id, String tableHead, Connection conn) throws SQLException, IOException{
      String readPostHeadQuery = getReadPostHeadQuery(tableHead);
      FJPostHead result = null;
      PreparedStatement st = null;
      try {
         st = conn.prepareStatement(readPostHeadQuery);
         st.setLong(1, id);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = new FJPostHead();
            result.setId(id);
            result.setAuth(rs.getLong(IFJPostHead.ATHOR_ID_FIELD_NAME));
            result.setTitle(rs.getString(IFJPostHead.TITLE_FIELD_NAME));
            result.setIp(rs.getString(IFJPostHead.ATHOR_IP_FIELD_NAME));
            result.setDomen(rs.getString(IFJPostHead.ATHOR_DOMEN_FIELD_NAME));
            result.setOutd(rs.getString(IFJPostHead.OUTD_FIELD_NAME));
            result.setNred(rs.getInt(IFJPostHead.NUMBER_OF_EDITS_FIELD_NAME));
            result.setCreateTime(rs.getDate(IFJPostHead.CREATIN_DATE_FIELD_NAME));
            result.setEditTime(rs.getDate(IFJPostHead.LAST_EDIT_DATE_FIELD_NAME));
            result.setPostId(rs.getLong(IFJPostHead.POST_ID_FIELD_NAME));
            result.setThreadId(rs.getLong(IFJPostHead.THREAD_ID_FIELD_NAME));
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }

   private FJPostBody readBody(Long id, String bodyTable, Connection conn) throws SQLException, IOException{
      String readPostBodyQuery = getReadPostBodyQuery(bodyTable);
      FJPostBody result = null;
      PreparedStatement st = null;
      try {
         st = conn.prepareStatement(readPostBodyQuery);
         st.setLong(1, id);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = new FJPostBody();
            result.setId(id);
            result.setBody(rs.getString(IFJPostBody.BODY_FIELD_NAME));
            result.setPostId(rs.getLong(IFJPostBody.POST_ID_FIELD_NAME));
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
   
   public long getAddedPostsAmount(long lastPostId) throws SQLException, ConfigurationException, IOException{
      long result = 0;
      String query = getAddedPostsAmountQuery();
      PreparedStatement st = null;
      Connection conn = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, lastPostId);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = rs.getLong("mx");
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }

   public long getAddedPostsAmount(long threadId, long lastPostId) throws SQLException, ConfigurationException, IOException{
      long result = 0;
      String query = getAddedPostsInThreadAmountQuery();
      PreparedStatement st = null;
      Connection conn = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, lastPostId);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = rs.getLong("mx");
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
}
