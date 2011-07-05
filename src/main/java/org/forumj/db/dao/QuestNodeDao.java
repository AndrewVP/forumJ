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
import org.forumj.db.entity.QuestNode;
import org.forumj.exception.DBException;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class QuestNodeDao extends FJDao {

   public List<QuestNode> loadNodes(Long threadId) throws IOException{
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
      } catch (ConfigurationException e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      } catch (SQLException e) {
         DBException ex = new DBException(e);
         onDatabaseError(ex);
         e.printStackTrace();
         throw new RuntimeException(e);
      }finally{
         try {
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
