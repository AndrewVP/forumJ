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

import java.io.IOException;
import java.sql.*;
import java.util.Date;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.db.entity.*;
import org.forumj.exception.DBException;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJThreadDao extends FJDao {

   public void create(FJThread thread, FJPost post) throws IOException, DBException{
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
         st.executeUpdate();
         ResultSet idRs = st.getGeneratedKeys();
         if (idRs.next()){
            Long threadId = idRs.getLong(1);
            thread.setId(threadId);
            post.setHeadId(threadId);
            post.getHead().setThreadId(threadId);
            post.getHead().setCreateTime(date);
            FJPostDao postDao = new FJPostDao();
            Long postId = postDao.create(post, conn);
            thread.setLastPostId(postId);
            update(thread, conn);
         }else{
            throw new DBException("Thread wasn't created");
         }
         error = false;
      } catch (ConfigurationException e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      } catch (SQLException e) {
         DBException ex = new DBException(e);
         onDatabaseError(ex);
         e.printStackTrace();
         throw ex;
      }finally{
         try {
            if (!error){
               conn.commit();
            }else{
               conn.rollback();
            }
            conn.setAutoCommit(true);
            if (st != null){
               st.close();
            }
            if (conn != null){
               conn.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }

   public void update(FJThread thread) throws IOException, DBException{
      Connection conn = null;
      try {
         conn = getConnection();
         update(thread, conn);
      } catch (ConfigurationException e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      } catch (SQLException e) {
         DBException ex = new DBException(e);
         onDatabaseError(ex);
         e.printStackTrace();
         throw ex;
      }finally{
         try {
            if (conn != null){
               conn.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }
   
   private void update(FJThread thread, Connection conn) throws IOException, SQLException{
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
         try {
            if (st != null){
               st.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }
}
