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

import java.io.IOException;
import java.sql.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.db.dao.tool.QueryBuilder;
import org.forumj.exception.DBException;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJForumDao extends FJDao {
   
   private String curretBodyTable = null;
   private String curretBodyHeadTable = null;

   public String getCurretBodyTable() throws IOException, DBException{
      if (curretBodyTable == null){
         loadConfig();
      }
      return curretBodyTable;
   }
   
   public String getCurretBodyHeadTable() throws IOException, DBException{
      if (curretBodyHeadTable == null){
         loadConfig();
      }
      return curretBodyHeadTable;
   }
   
   private void loadConfig() throws IOException, DBException{
      String query = QueryBuilder.getLoadConfigQuery();
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         if (rs.next()){
            curretBodyTable = rs.getString("curret_body_table");
            curretBodyHeadTable = rs.getString("current_body_head_table");
         }
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
}
