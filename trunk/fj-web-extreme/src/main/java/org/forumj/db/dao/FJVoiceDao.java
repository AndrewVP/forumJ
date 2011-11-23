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

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.db.entity.*;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJVoiceDao extends FJDao {

   public FJVoice read(Long threadId, IUser user) throws IOException, ConfigurationException, SQLException{
      FJVoice result = new FJVoice();
      String query = getReadVoiceQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query) ;
         st.setLong(1, threadId);
         st.setLong(2, user.getId());
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result.setId(rs.getLong("id"));
            result.setThreadId(threadId);
            result.setUserId(rs.getLong("user"));
            result.setNodeId(rs.getLong("node"));
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }

   public void delete(FJVoice voice, Connection connection) throws ConfigurationException, IOException, SQLException{
      String query = getDeleteVoiceQuery();
      Connection conn = null;
      PreparedStatement st = null;
      boolean error = true;
      try {
         conn = connection == null ? getConnection() : connection;
         conn.setAutoCommit(false);
         st = conn.prepareStatement(query);
         st.setLong(1, voice.getNodeId());
         st.executeUpdate();
         error = false;
      }finally{
         writeFinally(connection == null ? conn : null, st, error);
      }
   }
   
   public boolean isUserVoted(long threadId, IUser user) throws SQLException, ConfigurationException, IOException{
      boolean result = false;
      String query = getIsUserVotedQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query) ;
         st.setLong(1, threadId);
         st.setLong(2, user.getId());
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = true;
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
   
   public void create(FJVoice voice, Connection connection) throws IOException, ConfigurationException, SQLException{
      String query = getCreateVoiceQuery();
      Connection conn = null;
      PreparedStatement st = null;
      boolean error = true;
      try {
         conn = connection == null ? getConnection() : connection;
         conn.setAutoCommit(false);
         st = conn.prepareStatement(query);
         st.setLong(1, voice.getThreadId());
         st.setLong(1, voice.getNodeId());
         st.setLong(1, voice.getUserId());
         st.executeUpdate();
         error = false;
      }finally{
         writeFinally(connection == null ? conn : null, st, error);
      }
   }

   /**
    * Возвращает количество проголосовавших
    * в опросе
    * @throws SQLException 
    * @throws ConfigurationException 
    * @throws IOException 
    */
   public int getVoicesAmount(Long threadId) throws ConfigurationException, SQLException, IOException{
      int result = 0;
      String query = getVoicesAmountQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query) ;
         st.setLong(1, threadId);
         ResultSet rs = st.executeQuery(query);
         if (rs.next()){
            result = rs.getInt("nvcs");
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }

}
