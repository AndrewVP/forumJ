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
import static org.forumj.db.entity.IFJThread.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.db.entity.*;
import org.forumj.exception.DBException;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJThreadDao extends FJDao {

   public void create(FJThread thread, FJPost post) throws IOException, DBException, SQLException, ConfigurationException{
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
         st.setInt(6, thread.getType());
         st.executeUpdate();
         ResultSet idRs = st.getGeneratedKeys();
         if (idRs.next()){
            Long threadId = idRs.getLong(1);
            thread.setId(threadId);
            post.setThreadId(threadId);
            post.getHead().setThreadId(threadId);
            post.getHead().setCreateTime(date);
            FJPostDao postDao = new FJPostDao();
            Long postId = postDao.create(post, conn);
            thread.setLastPostId(postId);
            update(thread, conn);
            if (thread instanceof FJQuestionThread){
               QuestNodeDao answersDao = new QuestNodeDao();
               FJQuestionThread questionThread = (FJQuestionThread) thread;
               List<QuestNode> answers = questionThread.getAnswers();
               QuestNode question = new QuestNode();
               question.setNode(questionThread.getQuestion());
               question.setGol(0);
               question.setHead(threadId);
               question.setNumb(0);
               question.setType(0);
               question.setUserId((long) 0);
               answersDao.create(question, conn);
               for(int answerIndex = 0; answerIndex < answers.size(); answerIndex++){
                  QuestNode answer = answers.get(answerIndex);
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
            thread.setLastPostTime(rs.getDate(LAST_POST_DATE_FIELD_NAME));
            thread.setLastPostAuthId(rs.getLong(LAST_POST_USER_ID_FIELD_NAME));
            thread.setLastPostNick(rs.getString(LAST_POST_USER_NICK_FIELD_NAME));
            thread.setLastPostId(rs.getLong(LAST_POST_ID_FIELD_NAME));
            thread.setSnid(rs.getInt(SEEN_ID_FIELD_NAME));
            thread.setSnall(rs.getInt(SEEN_ALL_FIELD_NAME));
            thread.setDock(rs.getInt(DOCK_FIELD_NAME));
            thread.setType(rs.getInt(TYPE_FIELD_NAME));
            thread.setFolderId(rs.getLong(FOLDER_ID_FIELD_NAME));
            thread.setPcount(rs.getInt(POSTS_COUNT_FIELD_NAME));
         }
      }finally{
         readFinally(conn, st);
      }
      return thread;
   }
   
   public void update(FJThread thread, Connection conn) throws IOException, SQLException{
      String updateThreadQuery = getUpdateThreadQuery(); 
      PreparedStatement st = null;
      try {
         st = conn.prepareStatement(updateThreadQuery);
         st.setString(1, thread.getHead());
         Date date = new Date();
         st.setDate(2, new java.sql.Date(date.getTime()));
         st.setLong(3, thread.getAuthId());
         st.setString(4, thread.getNick());
         st.setLong(5, thread.getLastPostId());
         st.setInt(6, thread.getSnid());
         st.setInt(7, thread.getSnall());
         st.setInt(8, thread.getDock());
         st.setLong(9, thread.getFolderId());
         st.setInt(10, thread.getPcount());
         st.setLong(11, thread.getId());
         st.executeUpdate();
      }finally{
         readFinally(null, st);
      }
   }

   public long getAddedThreadsAmount(long lastThreadId) throws SQLException, ConfigurationException, IOException{
      long result = 0;
      String query = getAddedThreadsAmountQuery();
      PreparedStatement st = null;
      Connection conn = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, lastThreadId);
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
