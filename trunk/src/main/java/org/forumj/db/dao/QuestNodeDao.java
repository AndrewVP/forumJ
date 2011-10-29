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
import java.util.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.db.entity.*;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class QuestNodeDao extends FJDao {

   public List<QuestNode> loadNodes(Long threadId) throws IOException, ConfigurationException, SQLException{
      List<QuestNode> result = new ArrayList<QuestNode>();
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

   public void create(QuestNode answer, Connection conn) throws IOException, SQLException{
      String query = getCreateAnswerQuery();
      PreparedStatement st = null;
      try{
         st = conn.prepareStatement(query);
         st.setString(1, answer.getNode());
         st.setInt(2, answer.getNumb());
         st.setLong(3, answer.getUserId());
         st.setInt(4, answer.getType());
         st.setInt(5, answer.getGol());
         st.setLong(6, answer.getHead());
         st.executeUpdate();
      }finally{
         readFinally(null, st);
      }
   }

   public void repealVote(Long threadId, User user) throws ConfigurationException, IOException, SQLException{
      FJVoiceDao voiceDao = new FJVoiceDao();
      FJVoice voice = voiceDao.read(threadId, user);
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

   public void addVote(Long threadId, Long answerId, User user) throws ConfigurationException, IOException, SQLException{
      FJVoiceDao voiceDao = new FJVoiceDao();
      FJVoice voice = new FJVoice();
      String query = getIncreaseVoiceNumbersQuery();
      Connection conn = null;
      PreparedStatement st = null;
      boolean error = true;
      try {
         conn = getConnection();
         conn.setAutoCommit(false);
         voiceDao.create(voice, conn);
         st = conn.prepareStatement(query);
         st.setLong(1, voice.getNodeId());
         st.executeUpdate();
         error = false;
      }finally{
         writeFinally(conn, st, error);
      }
   }
}
