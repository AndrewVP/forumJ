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

import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.*;
import org.forumj.dbextreme.db.entity.*;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJQuestNodeDao extends FJDao {

   public List<IQuestNode> loadNodes(Long threadId) throws IOException, ConfigurationException, SQLException{
      List<IQuestNode> result = new ArrayList<IQuestNode>();
      String query = getLoadAnswersQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query) ;
         st.setLong(1, threadId);
         ResultSet rs = st.executeQuery();
         while (rs.next()){
            QuestNode node = new QuestNode();
            node.setId(rs.getLong("id"));
            node.setGol(rs.getInt("gol"));
            node.setHead(threadId);
            node.setNumb(rs.getInt("numb"));
            node.setType(rs.getInt("type"));
            node.setUserId(rs.getLong("user"));
            node.setUserNick(rs.getString("nick"));
            node.setNode(rs.getString("node"));
            result.add(node);
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }

   public Long create(IQuestNode answer, Connection conn) throws IOException, SQLException{
      Long result = null; 
      String query = getCreateAnswerQuery();
      PreparedStatement st = null;
      try{
         st = conn.prepareStatement(query, new String[]{"id"});
         st.setString(1, answer.getNode());
         st.setInt(2, answer.getNumb());
         st.setLong(3, answer.getUserId());
         st.setInt(4, answer.getType());
         st.setInt(5, answer.getGol());
         st.setLong(6, answer.getHead());
         st.executeUpdate();
         ResultSet idRs = st.getGeneratedKeys();
         if (idRs.next()){
            result = idRs.getLong(1);
         }
      }finally{
         readFinally(null, st);
      }
      return result;
   }

   public void repealVote(Long threadId, IUser user) throws ConfigurationException, IOException, SQLException{
      FJVoiceDao voiceDao = new FJVoiceDao();
      IFJVoice voice = voiceDao.read(threadId, user.getId());
      if (voice != null){
         String query = getReduceVoiceNumbersQuery();
         Connection conn = null;
         PreparedStatement st = null;
         boolean error = true;
         try {
            conn = getConnection();
            conn.setAutoCommit(false);
            st = conn.prepareStatement(query);
            st.setLong(1, voice.getNodeId());
            st.executeUpdate();
            voiceDao.delete(voice, conn);
            error = false;
         }finally{
            writeFinally(conn, st, error);
         }
      }
   }

   public void addVote(Long threadId, Long answerId, IUser user, Connection connection) throws ConfigurationException, IOException, SQLException{
      FJVoiceDao voiceDao = new FJVoiceDao();
      FJVoice voice = new FJVoice();
      voice.setThreadId(threadId);
      voice.setNodeId(answerId);
      voice.setUserId(user.getId());
      String query = getIncreaseVoiceNumbersQuery();
      Connection conn = null;
      PreparedStatement st = null;
      boolean error = true;
      try {
         conn = connection == null ? getConnection() : connection;
         conn.setAutoCommit(false);
         voiceDao.create(voice, conn);
         st = conn.prepareStatement(query);
         st.setLong(1, voice.getNodeId());
         st.executeUpdate();
         error = false;
      }finally{
         writeFinally(connection == null ? conn : null, st, error);
      }
   }
   
   public void addCustomAnswer(long threadId, String node, int type, IUser user) throws ConfigurationException, IOException, SQLException{
      QuestNode answer = new QuestNode();
      String maxAnswerNumberQuery = getMaxAnswerNumberQuery();
      Connection conn = null;
      PreparedStatement st = null;
      boolean error = true;
      try {
         conn = getConnection();
         conn.setAutoCommit(false);
         st = conn.prepareStatement(maxAnswerNumberQuery);
         st.setLong(1, threadId);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            int maxNumber = rs.getInt("mx") + 1;
            answer.setHead(threadId);
            answer.setNumb(maxNumber);
            answer.setNode(node);
            answer.setGol(1);
            answer.setType(type);
            answer.setUserId(user.getId());
            Long nodeId = create(answer, conn);
            if (nodeId != null){
               addVote(threadId, nodeId, user, conn);
               error = false;
            }
         }
      }finally{
         writeFinally(conn, st, error);
      }
   }
}
