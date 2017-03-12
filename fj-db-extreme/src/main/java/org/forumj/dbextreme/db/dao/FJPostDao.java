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
import org.forumj.common.web.*;
import org.forumj.dbextreme.db.entity.*;
import org.forumj.dbextreme.db.service.FJService;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJPostDao extends FJDao {

   public Long create(IFJPost post, Connection conn, boolean updateThread) throws IOException, DBException, SQLException, ConfigurationException{
      Long postId = null;
      String createPostQuery = getCreatePostQuery();
      FJForumDao forumDao = new FJForumDao();
      PreparedStatement st = null;
      try {
         st = conn.prepareStatement(createPostQuery, new String[]{"id"});
         prepareStatmentForUpdate(post, st);
         st.executeUpdate();
         ResultSet idRs = st.getGeneratedKeys();
         if (idRs.next()){
            postId = idRs.getLong(1);
            post.setId(postId);
            st.close();
            if (updateThread){
               FJThreadDao threadDao = new FJThreadDao();
               FJThread thread = threadDao.read(post.getThreadId());
               thread.setLastPostId(postId);
               thread.setLastPostAuthId(post.getAuth());
               thread.setLastPostTime(new Date(post.getCreateTime()));
               thread.setLastPostNick(post.getAuthor().getNick());
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

   private int prepareStatmentForUpdate(IFJPost post, PreparedStatement st) throws SQLException {
      int parameterIndex = 0;
      st.setInt(++parameterIndex, post.getType());
      st.setLong(++parameterIndex, post.getThreadId());
      st.setLong(++parameterIndex, post.getAuth());
      st.setLong(++parameterIndex, post.getCreateTime());
      st.setInt(++parameterIndex, post.getState());
      st.setLong(++parameterIndex, post.getReplyTo());
      st.setString(++parameterIndex, post.getTitle());
      st.setString(++parameterIndex, post.getIp());
      st.setString(++parameterIndex, post.getDomen());
      st.setInt(++parameterIndex, post.getNred());
      st.setLong(++parameterIndex, post.getEditTime());
      st.setString(++parameterIndex, post.getBody());
      return parameterIndex;
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
      Connection conn = null;
      PreparedStatement st = null;
      boolean error = true;
      try {
         conn = getConnection();
         conn.setAutoCommit(false);
         st = conn.prepareStatement(updatePostQuery);
         int parameterIndex = prepareStatmentForUpdate(post, st);
         st.setLong(++parameterIndex, post.getId());
         st.executeUpdate();
         if (isFirstPost(post.getId(), post.getThreadId(), conn)){
            FJThreadDao threadDao = new FJThreadDao();
            FJThread thread = threadDao.read(post.getThreadId());
            thread.setHead(post.getTitle());
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
            result = readPost(rs);
            FJUserDao userDao = new FJUserDao();
            IUser user = userDao.read(result.getAuth());
            result.setAuthor(user);
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
   
   public long getAddedPostsAmount(long lastPostId, long userId) throws SQLException, ConfigurationException, IOException{
      long result = 0;
      String query = getAddedPostsAmountQuery();
      PreparedStatement st = null;
      Connection conn = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, lastPostId);
         st.setLong(2, userId);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = rs.getLong("mx");
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }

   public long getAddedPostsAmount(long threadId, long lastPostId, long userId) throws SQLException, ConfigurationException, IOException{
      long result = 0;
      String query = getAddedPostsInThreadAmountQuery();
      PreparedStatement st = null;
      Connection conn = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, lastPostId);
         st.setLong(2, threadId);
         st.setLong(3, userId);
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
      boolean isQuest = false;
      boolean isFirst;
      int nPost = 0;
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
         FJPost firstPost = null;
         while (rs.next()){
            post = readPost(rs);
            User postAuthor = loadUser(rs);
            post.setAuthor(postAuthor);
            int type = rs.getInt("thread_type");
            if ((type == 1 || type == 2)){
               isQuest = true;
            }
            lastPostId = post.getId();
            if (page == 1 && ++nPost == 1){
               post.setFirstPost(true);
               firstPost = post;
            }
            result.add(post);
         }
         if (isQuest){
            List<IQuestNode> questNodes = questDao.loadNodes(threadId);
            FJVoiceDao voiceDao = new FJVoiceDao();
            firstPost.setVoicesAmount(voiceDao.getVoicesAmount(threadId));
            firstPost.setAnswers(questNodes);
            firstPost.setQuestion(questNodes.get(0));
         }
         if (lastPost && post != null){
            post.setLastPost(true);
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

   private FJPost readPost(ResultSet rs) throws SQLException {
      FJPost result = new FJPost();
      result.setId(rs.getLong("id"));
      result.setType(rs.getInt("type"));
      result.setThreadId(rs.getLong("thread"));
      result.setAuth(rs.getLong("author"));
      result.setCreateTime(rs.getLong("created"));
      result.setState(rs.getInt("state"));
      result.setReplyTo(rs.getLong("reply_to"));
      result.setTitle(rs.getString("title"));
      result.setIp(rs.getString("ip"));
      result.setDomen(rs.getString("domen"));
      result.setNred(rs.getInt("edited_times"));
      result.setEditTime(rs.getLong("edited"));
      result.setBody(rs.getString("post"));
      return result;
   }

   private User loadUser(ResultSet rs) throws ConfigurationException, SQLException{
      User result = new User();
      result.setId(rs.getLong("author"));
      result.setNick(rs.getString("nick"));
      result.setAvatar(rs.getString("avatar"));
      result.setShowAvatar(rs.getInt("s_avatar") > 0);
      result.setAvatarApproved(rs.getInt("ok_avatar") > 0);
      result.setWantSeeAvatars(rs.getInt("v_avatars") > 0);
      result.setHideIp(rs.getBoolean("h_ip"));
      result.setCity(rs.getString("city"));
      result.setShowCity(rs.getBoolean("scity"));
      result.setCountry(rs.getString("country"));
      result.setShowCountry(rs.getBoolean("scountry"));
      result.setFooter(rs.getString("footer"));
      return result;
   }

   /**
    * Возвращает id последнего поста в кокретной ветке
    *
    * @return unknown
    * @throws SQLException
    * @throws ConfigurationException
    * @throws IOException
    */
   public Long getLastPostId(long threadId) throws ConfigurationException, SQLException, IOException{
      Long result = 0l;
      String query = getLastPostIdInThreadQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, threadId);
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
