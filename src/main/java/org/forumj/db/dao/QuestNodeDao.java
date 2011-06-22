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

   public List<QuestNode> loadNodes(Long threadId){
      List<QuestNode> result = new ArrayList<QuestNode>();
      String query="SELECT quest.id, quest.node, quest.numb,quest.user, quest.gol, quest.type, users.nick FROM quest LEFT JOIN users ON quest.user=users.id WHERE quest.head=" + threadId + " ORDER BY numb";
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
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
}
