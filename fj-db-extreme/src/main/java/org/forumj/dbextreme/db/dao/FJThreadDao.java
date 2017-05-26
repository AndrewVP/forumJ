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

import static org.forumj.common.db.entity.IFJThread.*;
import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.*;
import org.forumj.common.exception.DBException;
import org.forumj.common.web.*;
import org.forumj.dbextreme.db.entity.*;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJThreadDao extends BaseDao {

   public void create(IFJThread thread, IFJPost post) throws IOException, DBException, SQLException, ConfigurationException{
      String createThreadQuery = getCreateThreadQuery(); 
      Connection conn = null;
      PreparedStatement st = null;
      boolean error = true;
      try {
         conn = getConnection();
         conn.setAutoCommit(false);
         st = conn.prepareStatement(createThreadQuery, new String[]{"id"});
         st.setLong(1, thread.getAuthId());
         st.setString(2, thread.getHead());
         Date date = new Date();
         st.setDate(3, new java.sql.Date(date.getTime()));
         st.setDate(4, new java.sql.Date(date.getTime()));
         st.setString(5, thread.getNick());
         st.setInt(6, thread.getType().getType());
         st.executeUpdate();
         ResultSet idRs = st.getGeneratedKeys();
         if (idRs.next()){
            Long threadId = idRs.getLong(1);
            thread.setId(threadId);
            post.setThreadId(threadId);
            post.setCreateTime(date.getTime());
            FJPostDao postDao = new FJPostDao();
            Long postId = postDao.create(post, conn, false);
            thread.setLastPostId(postId);
            thread.setLastPostAuthId(post.getAuth());
            thread.setLastPostTime(new Date(post.getCreateTime()));
            thread.setLastPostNick(post.getAuthor().getNick());
            update(thread, conn);
            if (thread instanceof FJQuestionThread){
               FJQuestNodeDao answersDao = new FJQuestNodeDao();
               FJQuestionThread questionThread = (FJQuestionThread) thread;
               List<IQuestNode> answers = questionThread.getAnswers();
               QuestNode question = new QuestNode();
               question.setNode(questionThread.getQuestion());
               question.setGol(0);
               question.setHead(threadId);
               question.setNumb(0);
               question.setType(0);
               question.setUserId((long) 0);
               answersDao.create(question, conn);
               for(int answerIndex = 0; answerIndex < answers.size(); answerIndex++){
                  IQuestNode answer = answers.get(answerIndex);
                  answer.setHead(threadId);
                  answersDao.create(answer, conn);
               }
            }
         }else{
            throw new DBException("Thread wasn't created");
         }
         error = false;
      }finally{
         writeFinally(conn, st, error);
      }
   }

   public void update(FJThread thread) throws IOException, DBException, ConfigurationException, SQLException{
      Connection conn = null;
      try {
         conn = getConnection();
         update(thread, conn);
      }finally{
         readFinally(conn, null);
      }
   }
   
   public boolean checkThreadExist(Long id) throws IOException, SQLException, ConfigurationException{
      String readThreadQuery = getCheckThreadExistQuery(); 
      PreparedStatement st = null;
      Connection conn = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(readThreadQuery);
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
   
   public FJThread read(Long id) throws ConfigurationException, SQLException, IOException{
      FJThread thread = null;
      String readThreadQuery = getReadThreadQuery(); 
      PreparedStatement st = null;
      Connection conn = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(readThreadQuery);
         st.setLong(1, id);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            thread = new FJThread();
            thread.setId(id);
            thread.setAuthId(rs.getLong(AUTH_FIELD_NAME));
            thread.setHead(rs.getString(HEAD_FIELD_NAME));
            thread.setRegDate(rs.getDate(REGISTRATION_DATE_FIELD_NAME));
            thread.setLastPostTime(rs.getTimestamp(LAST_POST_DATE_FIELD_NAME));
            thread.setLastPostAuthId(rs.getLong(LAST_POST_USER_ID_FIELD_NAME));
            thread.setLastPostNick(rs.getString(LAST_POST_USER_NICK_FIELD_NAME));
            thread.setLastPostId(rs.getLong(LAST_POST_ID_FIELD_NAME));
            thread.setSnid(rs.getInt(SEEN_ID_FIELD_NAME));
            thread.setSnall(rs.getInt(SEEN_ALL_FIELD_NAME));
            thread.setDock(Pin.valueOfInteger(rs.getInt(DOCK_FIELD_NAME)));
            thread.setType(ThreadType.valueOfInteger(rs.getInt(TYPE_FIELD_NAME)));
            thread.setFolderId(rs.getLong(FOLDER_ID_FIELD_NAME));
            thread.setPostsAmount(rs.getInt(POSTS_COUNT_FIELD_NAME));
            thread.setClosed(rs.getBoolean(CLOSED_FIELD_NAME));
         }
      }finally{
         readFinally(conn, st);
      }
      return thread;
   }
   
   public void update(IFJThread thread, Connection conn) throws IOException, SQLException{
      String updateThreadQuery = getUpdateThreadQuery(); 
      PreparedStatement st = null;
      try {
         st = conn.prepareStatement(updateThreadQuery);
         st.setString(1, thread.getHead());
         st.setTimestamp(2, new java.sql.Timestamp(thread.getLastPostTime().getTime()));
         st.setLong(3, thread.getLastPostAuthId());
         st.setString(4, thread.getLastPostNick());
         st.setLong(5, thread.getLastPostId());
         st.setInt(6, thread.getDock().getCode());
         st.setLong(7, thread.getFolderId());
         st.setInt(8, thread.getPostsAmount());
         st.setBoolean(9, thread.isClosed());
         st.setLong(10, thread.getId());
         st.executeUpdate();
      }finally{
         readFinally(null, st);
      }
   }

   public long getAddedThreadsAmount(long lastThreadId, long userId) throws SQLException, ConfigurationException, IOException{
      long result = 0;
      String query = getAddedThreadsAmountQuery();
      PreparedStatement st = null;
      Connection conn = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, lastThreadId);
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

   /**
    * Возвращает количество постов в ветке
    * @throws SQLException 
    * @throws ConfigurationException 
    * @throws IOException 
    */
   public Integer getPostsCountInThread(Long threadId, Long idMax) throws ConfigurationException, SQLException, IOException{
      Integer result = null;
      String query = getPostsCountInThreadQuery();
      PreparedStatement st = null;
      Connection conn = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, threadId);
         st.setLong(2, idMax);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = rs.getInt("kolvo");
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
   
   /**
    * Записывает состояние счетчиков просмотров ветки
    *
    * @throws SQLException
    * @throws ConfigurationException 
    * @throws IOException 
    */
   public void setSeen(IUser user, Long threadId) throws ConfigurationException, SQLException, IOException{
      String query = "";
      if (user.isLogined()){
         query = getSeenByUserQuery();
      }else{
         query = getSeenByGuestQuery();
      }
      PreparedStatement st = null;
      Connection conn = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, threadId);
         st.executeUpdate();
      }finally{
         readFinally(conn, st);
      }
   }

   private class View {
      private boolean hasForum;
      private boolean hasOnlyForum;
      private String folders;

      public View(boolean hasForum, boolean hasOnlyForum, String folders) {
         this.hasForum = hasForum;
         this.hasOnlyForum = hasOnlyForum;
         this.folders = folders;
      }

      public boolean isHasForum() {
         return hasForum;
      }

      public boolean isHasOnlyForum() {
         return hasOnlyForum;
      }

      public String getFolders() {
         return folders;
      }
   }

   //TODO Move it to the FJFolderDao
   private View loadFolders(IUser user, long viewId) throws IOException, SQLException, ConfigurationException {
      View result = null;
      String sql_views = getLoadFoldersIdInViewQuery();
      int foldersAmount = 0;
      boolean hasForum = false;
      StringBuilder folders = new StringBuilder("(");
      try (
           Connection conn = getConnection();
           PreparedStatement st = conn.prepareStatement(sql_views);
      ){
         st.setLong(1, user.getId());
         st.setLong(2, viewId);
         ResultSet rs = st.executeQuery();
         while (rs.next()) {
            long folderId = rs.getLong("folder");
            if (folderId == 1) {
               hasForum = true;
            } else {
               folders.append(folderId).append(",");
            }
            foldersAmount++;
         }
         if (foldersAmount == 1 && hasForum) {
            /*есть только форум*/
         } else if (foldersAmount == 0) {
            // TODO Why is this here?
            folders = new StringBuilder("(1)");
            hasForum = true;
         } else {
            /*другое*/
            folders.deleteCharAt(folders.length() - 1);
            folders.append(")");
         }
         result = new View(hasForum, hasForum && foldersAmount == 1, folders.toString());
      }
      return result;
   }

   private String loadLastThreadsIdsOnlyInForum(IUser user, long start, boolean pinned) throws SQLException, ConfigurationException, IOException {
      StringBuilder result = new StringBuilder("AND thread in (");
      String query = null;
      if (pinned){
         query = getLoadPinnedThreadsIdsOnlyInForumQuery();
      }else{
         query = getLoadLastThreadsIdsOnlyInForumQuery();
      }
      try (
              Connection conn = getConnection();
              PreparedStatement st = conn.prepareStatement(query);
      ) {
         st.setLong(1, user.getId());
         st.setLong(2, user.getId());
         st.setLong(3, user.getId());
         if (!pinned){
            st.setLong(4, start);
            int threadsOnPage = user.getThreadsOnPage();
            st.setInt(5, threadsOnPage);
         }
         ResultSet rs = st.executeQuery();
         boolean hasResult = false;
         while (rs.next()) {
            hasResult = true;
            result.append(rs.getLong("thread")).append(",");
         }
         if (hasResult){
            // at least one thread present
            result.deleteCharAt(result.length() - 1);
            result.append(")\n");
            return result.toString();
         }else{
            return "";
         }
      }
   }

   private String loadLastThreadsIdsNotOnlyInForum(IUser user, Long viewId, long start, boolean pinned) throws SQLException, ConfigurationException, IOException {
      StringBuilder result = new StringBuilder("AND thread in (");
      String query = null;
      if (pinned){
         query = getLoadPinnedThreadsIdsNotOnlyInForumQuery();
      }else{
         query = getLoadLastThreadsIdsNotOnlyInForumQuery();
      }
      try (
              Connection conn = getConnection();
              PreparedStatement st = conn.prepareStatement(query);
      ) {
         st.setLong(1, user.getId());
         st.setLong(2, user.getId());
         st.setLong(3, viewId);
         st.setLong(4, user.getId());
         st.setLong(5, user.getId());
         if (!pinned){
            st.setLong(6, start);
            int threadsOnPage = user.getThreadsOnPage();
            st.setInt(7, threadsOnPage);
         }
         ResultSet rs = st.executeQuery();
         boolean hasResult = false;
         while (rs.next()) {
            hasResult = true;
            result.append(rs.getLong("thread")).append(",");
         }
         if (hasResult){
            // at least one thread present
            result.deleteCharAt(result.length() - 1);
            result.append(")\n");
            return result.toString();
         }else{
            return "";
         }
      }
   }

   private String loadLastThreadsIdsNoForum(IUser user, Long viewId, long start, boolean pinned) throws SQLException, ConfigurationException, IOException {
      StringBuilder result = new StringBuilder("AND thread in (");
      String query = null;
      if (pinned){
         query = getLoadPinnedThreadsIdsNoForumQuery();
      }else{
         query = getLoadLastThreadsIdsNoForumQuery();
      }
      try (
              Connection conn = getConnection();
              PreparedStatement st = conn.prepareStatement(query);
      ) {
         st.setLong(1, user.getId());
         st.setLong(2, user.getId());
         st.setLong(3, viewId);
         st.setLong(4, user.getId());
         st.setLong(5, user.getId());
         if (!pinned){
            st.setLong(6, start);
            int threadsOnPage = user.getThreadsOnPage();
            st.setInt(7, threadsOnPage);
         }

         ResultSet rs = st.executeQuery();
         boolean hasResult = false;
         while (rs.next()) {
            hasResult = true;
            result.append(rs.getLong("thread")).append(",");
         }
         if (hasResult){
            // at least one thread present
            result.deleteCharAt(result.length() - 1);
            result.append(")\n");
            return result.toString();
         }else{
            return "";
         }
      }
   }


   public long getThreadsAmount(Long viewId, IUser user) throws Exception{
      long count = 0;
      Connection conn = null;
      PreparedStatement st = null;
      ResultSet rs = null;
      String lastThreadsIds = null;
      String sql_count = null;
      try {
         conn = getConnection();
         View view = loadFolders(user, viewId);
         if (view.isHasForum()) {
            /*Есть форум*/
            if (view.isHasOnlyForum()) {
               /*Есть только форум*/
               sql_count = getCountThreadsOnlyInForumQuery();
               st = conn.prepareStatement(sql_count);
               st.setLong(1, user.getId());
               st.setLong(2, user.getId());
            } else {
               /*кроме форума есть что-то еще*/
               sql_count = getCountThreadsNotOnlyForumQuery();
               st = conn.prepareStatement(sql_count);
               st.setLong(1, user.getId());
               st.setLong(2, user.getId());
               st.setLong(3, viewId);
               st.setLong(4, user.getId());
            }
         } else {
            /*форума в интерфейсе нет*/
            sql_count = getCountThreadsNoForumQuery();
            st = conn.prepareStatement(sql_count);
            st.setLong(1, user.getId());
            st.setLong(2, user.getId());
            st.setLong(3, viewId);
         }
         rs = st.executeQuery();
         if (rs.next()) {
            count = rs.getLong("kolvo");
         }
      }finally{
         readFinally(conn, st);
      }
      return count;
   }

   public List<IFJThread> getThreads(Long viewId, long startPosition, IUser user, boolean pinned) throws SQLException, ConfigurationException, IOException{
      List<IFJThread> result = new LinkedList<>();
      Connection conn = null;
      PreparedStatement st = null;
      ResultSet rs = null;
      String lastThreadsIds = null;
      try {
         conn = getConnection();
         View view = loadFolders(user, viewId);
         if (view.isHasForum()){
            /*Есть форум*/
            if (view.isHasOnlyForum()){
               /*Есть только форум*/
               lastThreadsIds = loadLastThreadsIdsOnlyInForum(user, startPosition, pinned);
            }else{
               /*кроме форума есть что-то еще*/
               lastThreadsIds = loadLastThreadsIdsNotOnlyInForum(user, viewId, startPosition, pinned);
            }
         }else{
            /*форума в интерфейсе нет*/
            lastThreadsIds = loadLastThreadsIdsNoForum(user, viewId, startPosition, pinned);
         }
         if (!lastThreadsIds.isEmpty()){
            String sql_main=getLoadForumIndexQuery(lastThreadsIds);
            st = conn.prepareStatement(sql_main);
            st.setLong(1, user.getId());
            st.setLong(2, user.getId());
            rs = st.executeQuery();
            while (rs.next()){
               Long id = rs.getLong("thread");
               Long idLastPost = rs.getLong("maxd");
               FJThread thr = new FJThread();
               thr.setId(id);
               thr.setLastPostId(idLastPost);
               thr.setDock(Pin.valueOfInteger(rs.getInt("dock")));
               thr.setLastPostTime(new Date(rs.getLong("created")));
               thr.setHead(rs.getString("head"));
               thr.setNick(rs.getString("nick"));
               thr.setLastPostNick(rs.getString("lpostnick"));
               thr.setPostsAmount(rs.getInt("npost")-1);
               thr.setSnid(rs.getInt("seenid"));
               thr.setSnall(rs.getInt("seenall"));
               thr.setType(ThreadType.valueOfInteger(rs.getInt("type")));
               thr.setClosed(rs.getBoolean("closed"));
               thr.setAuthId(rs.getLong("auth"));
               String flName = rs.getString("flname");
               // TODO Localization!
               thr.setFolder(flName == null || flName.isEmpty() ? "Форум" : flName);
               result.add(thr);
            }
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }


   public List<IFJThread> getThreads(Long viewId, long startPosition, IUser user) throws SQLException, ConfigurationException, IOException{
      List<IFJThread> result = new LinkedList<>();
      if (startPosition == 0){
          List<IFJThread> pinnedThreads = getThreads(viewId, startPosition, user, true);
          if (pinnedThreads.size() > 0){
             result.addAll(pinnedThreads);
          }
      }
      List<IFJThread> otherThreads = getThreads(viewId, startPosition, user, false);
      result.addAll(otherThreads);
      return result;
   }

   /**
    * Возвращает id последней ветки в форуме
    *
    * @return unknown
    * @throws SQLException 
    * @throws ConfigurationException 
    * @throws IOException 
    */
   public Long getMaxThreadId() throws ConfigurationException, SQLException, IOException{
      Long result = 0l;
      String query = getLastThreadIdQuery();
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

   public void pin(Long threadId, Pin pin) throws IOException, ConfigurationException, SQLException {
      String query = getPinQuery();
      PreparedStatement st = null;
      Connection conn = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setInt(1, pin.getCode());
         st.setLong(2, threadId);
         st.executeUpdate();
      }finally{
         readFinally(conn, st);
      }
   }

   public void close(Long threadId, boolean closed) throws IOException, ConfigurationException, SQLException {
      String query = getCloseThreadQuery();
      PreparedStatement st = null;
      Connection conn = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setBoolean(1, closed);
         st.setLong(2, threadId);
         st.executeUpdate();
      }finally{
         readFinally(conn, st);
      }
   }
}
