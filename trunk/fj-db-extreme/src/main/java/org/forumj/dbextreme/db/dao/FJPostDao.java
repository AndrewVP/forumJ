/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.dbextreme.db.dao;

import static org.forumj.common.db.entity.IFJPost.*;
import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.Date;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.*;
import org.forumj.common.exception.DBException;
import org.forumj.dbextreme.db.entity.*;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJPostDao extends FJDao {

   public Long create(IFJPost post, Connection conn, boolean updateThread) throws IOException, DBException, SQLException, ConfigurationException{
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
            IFJPostBody postBody = post.getBody();
            st.setLong(1, postId);
            st.setLong(2, postId);
            st.setString(3, postBody.getBody());
            st.executeUpdate();
            st.close();
            st = conn.prepareStatement(createPostHeadQuery);
            IFJPostHead postHead = post.getHead();
            st.setLong(1, postId);
            st.setLong(2, postId);
            st.setLong(3, postHead.getAuth());
            st.setLong(4, postHead.getThreadId());
            st.setString(5, postHead.getTitle());
            st.setLong(6, postHead.getCreateTime());
            st.setString(7, postHead.getIp());
            st.setString(8, postHead.getDomen());
            st.setString(9, " ");
            st.executeUpdate();
            if (updateThread){
               FJThreadDao threadDao = new FJThreadDao();
               FJThread thread = threadDao.read(post.getThreadId());
               thread.setLastPostId(postId);
               thread.setLastPostAuthId(postHead.getAuth());
               thread.setLastPostTime(new Date(postHead.getCreateTime()));
               thread.setLastPostNick(postHead.getAuthor().getNick());
               thread.setPcount(thread.getPcount()+1);
               threadDao.update(thread, conn);
            }
         }else{
            throw new DBException("Post wasn't created");
         }
      }finally{
         readFinally(null, st);
      }
      return postId;
   }

   public Long create(IFJPost post) throws IOException, DBException, ConfigurationException, SQLException{
      Connection conn = null;
      Long result = null;
      boolean error = true;
      try {
         conn = getConnection();
         conn.setAutoCommit(false);
         result = create(post, conn, true);
         error = false;
      }finally{
         writeFinally(conn, null, error);
      }
      return result;
   }

   public void update(IFJPost post) throws IOException, SQLException, ConfigurationException{
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
         
         IFJPostHead postHead = post.getHead();
         st = conn.prepareStatement(updatePostHeadQuery);
         st.setLong(1, postHead.getAuth());
         st.setString(2, postHead.getTitle());
         st.setString(3, postHead.getIp());
         st.setString(4, postHead.getDomen());
         st.setString(5, postHead.getOutd());
         st.setInt(6, postHead.getNred());
         st.setLong(7, postHead.getCreateTime());
         st.setLong(8, postHead.getEditTime());
         st.setLong(9, postHead.getPostId());
         st.setLong(10, postHead.getThreadId());
         st.setLong(11, postHead.getId());
         st.executeUpdate();
         
         IFJPostBody postBody = post.getBody();
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
            result = postId.equals(rs.getLong("id"));
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
            result.setThreadId(rs.getLong(THREAD_ID_FIELD_NAME));
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
   
   private FJPostHead readHead(Long id, String tableHead, Connection conn) throws SQLException, IOException, ConfigurationException{
      FJUserDao userDao = new FJUserDao();
      String readPostHeadQuery = getReadPostHeadQuery(tableHead);
      FJPostHead result = null;
      PreparedStatement st = null;
      Connection cn = null;
      try {
         cn = conn == null ? getConnection(): conn;
         st = cn.prepareStatement(readPostHeadQuery);
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
            result.setCreateTime(rs.getLong(IFJPostHead.CREATIN_DATE_FIELD_NAME));
            result.setEditTime(rs.getLong(IFJPostHead.LAST_EDIT_DATE_FIELD_NAME));
            result.setPostId(rs.getLong(IFJPostHead.POST_ID_FIELD_NAME));
            result.setThreadId(rs.getLong(IFJPostHead.THREAD_ID_FIELD_NAME));
            IUser user = userDao.read(result.getAuth(), conn);
            result.setAuthor(user);
         }
      }finally{
         readFinally(conn == null ? cn : null, st);
      }
      return result;
   }

   private FJPostBody readBody(Long id, String bodyTable, Connection conn) throws SQLException, IOException, ConfigurationException{
      String readPostBodyQuery = getReadPostBodyQuery(bodyTable);
      FJPostBody result = null;
      PreparedStatement st = null;
      Connection cn = null;
      try {
         cn = conn == null ? getConnection(): conn;
         st = cn.prepareStatement(readPostBodyQuery);
         st.setLong(1, id);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = new FJPostBody();
            result.setId(id);
            result.setBody(rs.getString(IFJPostBody.BODY_FIELD_NAME));
            result.setPostId(rs.getLong(IFJPostBody.POST_ID_FIELD_NAME));
         }
      }finally{
         readFinally(conn == null ? cn : null, st);
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
         st.setLong(2, threadId);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = rs.getLong("mx");
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }

   /**
    * Возвращает список постов на странице темы
    * 
    * @param user
    * @param threadId
    * @param nfirstpost
    * @param count
    * @param page
    * @param lastPost
    * @return
    * @throws IOException
    * @throws SQLException
    * @throws ConfigurationException
    */
   public List<IFJPost> getPostsList(IUser user, Long threadId, long nfirstpost, int count, int page, boolean lastPost) throws IOException, SQLException, ConfigurationException{
      String query = getReadPostsQuery();
      List<IFJPost> result = new ArrayList<IFJPost>();
      Map<Long, FJPost> postsMap = new HashMap<Long, FJPost>();
      boolean isQuest = false;
      boolean isFirst;
      int nPost = 0;
      Map<String, String> bodiesId = new HashMap<String, String>();
      Map<String, String> headsId = new HashMap<String, String>();
      Long lastPostId = null;
      Connection conn = null;
      PreparedStatement st = null;
      FJQuestNodeDao questDao = new FJQuestNodeDao();
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, threadId);
         st.setLong(2, nfirstpost);
         st.setInt(3, count);
         ResultSet rs = st.executeQuery();
         FJPost post = null; 
         while (rs.next()){
            isFirst = page == 1 && ++nPost == 1;
            lastPostId = rs.getLong("id");
            post = new FJPost();
            post.setId(lastPostId);
            post.setFirstPost(isFirst);
            post.setThreadId(rs.getLong(THREAD_ID_FIELD_NAME));
            result.add(post);
            postsMap.put(lastPostId, post);
            String tablePost = rs.getString("table_post");
            String tablePostIds = bodiesId.get(tablePost);
            if (tablePostIds != null){
               tablePostIds += ", " + lastPostId.toString();
            }else{
               tablePostIds = lastPostId.toString();
            }
            bodiesId.put(tablePost, tablePostIds);
            String tableHead = rs.getString("table_head");
            String tableHeadIds = headsId.get(tableHead);
            if (tableHeadIds != null){
               tableHeadIds += ", " + lastPostId.toString();
            }else{
               tableHeadIds = lastPostId.toString();
            }
            headsId.put(tableHead, tableHeadIds);
         }
         if (lastPost && post != null){
            post.setLastPost(true);
         }
         //Загружаем заголовки постов
         for (Iterator<Entry<String, String>> iterator = headsId.entrySet().iterator(); iterator.hasNext();) {
            Entry<String, String> entry = iterator.next();
            String table = entry.getKey();
            String ids = entry.getValue();
            query = getReadPostsHeadsQuery(table, ids);
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
               Long postId = rs.getLong("id");
               int type = rs.getInt("type");
               isQuest = (type == 1 || type == 2);
               post = postsMap.get(postId);
               FJPostHead postHead = new FJPostHead();
               post.setHead(postHead);
               if (post.isFirstPost() && isQuest){
                  post.setAnswers(new ArrayList<IQuestNode>());
                  List<IQuestNode> questNodes = questDao.loadNodes(threadId);
                  FJVoiceDao voiceDao = new FJVoiceDao();
                  post.setVoicesAmount(voiceDao.getVoicesAmount(threadId));
                  post.setAnswers(questNodes);
                  post.setQuestion(questNodes.get(0));
               }
               IUser author = new User();
               postHead.setAuthor(author);
               author.setNick(rs.getString("nick"));
               author.setId(rs.getLong("auth"));
               postHead.setAuth(author.getId());
               postHead.setIp(rs.getString("ip"));
               author.setAvatar(rs.getString("avatar"));
               author.setShowAvatar(rs.getInt("users.s_avatar") == 1);
               author.setAvatarApproved(rs.getInt("ok_avatar") == 1);
               author.setWantSeeAvatars(rs.getInt("v_avatars") == 1);
               author.setCountry(rs.getString("country"));
               author.setShowCountry(rs.getInt("scountry") == 1);
               postHead.setCreateTime(rs.getLong("post_time"));
               author.setCity(rs.getString("city"));
               author.setShowCity(rs.getInt("scity") == 1);
               author.setFooter(rs.getString("footer"));
               postHead.setDomen(rs.getString("domen"));
               postHead.setTitle(rs.getString("tilte"));
               postHead.setNred(rs.getInt("nred"));
               postHead.setEditTime(rs.getLong("post_edit_time"));
            }
         }
         //Загружаем посты
         for (Iterator<Entry<String, String>> iterator = bodiesId.entrySet().iterator(); iterator.hasNext();) {
            Entry<String, String> entry = iterator.next();
            String table = entry.getKey();
            String ids = entry.getValue();
            query = getReadPostsBodiesQuery(table, ids);
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
               Long postId = rs.getLong("id");
               post = postsMap.get(postId);
               FJPostBody postBody = new FJPostBody();
               post.setBody(postBody);
               postBody.setBody(rs.getString("body"));
            }
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
   
   public boolean checkPostExist(Long id) throws ConfigurationException, SQLException, IOException{
      String checkPostQuery = getCheckPostExistQuery(); 
      PreparedStatement st = null;
      Connection conn = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(checkPostQuery);
         st.setLong(1, id);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            return true;
         }else{
            return false;
         }
      }finally{
         readFinally(conn, st);
      }
   }
   
   
   /**
    * Возвращает id последнего поста в форуме
    *
    * @return unknown
    * @throws SQLException 
    * @throws ConfigurationException 
    * @throws IOException 
    */
   public Long getLastPostId() throws ConfigurationException, SQLException, IOException{
      Long result = 0l;
      String query = getLastPostIdQuery();
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         if (rs.next()){
            result = rs.getLong("mx");
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
}
