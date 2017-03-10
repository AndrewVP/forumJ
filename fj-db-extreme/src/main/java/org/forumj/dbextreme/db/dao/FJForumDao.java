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

import java.io.IOException;
import java.sql.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.exception.DBException;
import org.forumj.dbextreme.db.dao.tool.QueryBuilder;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJForumDao extends FJDao {
   
   private void loadConfig() throws IOException, DBException, ConfigurationException, SQLException{
      String query = QueryBuilder.getLoadConfigQuery();
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         if (rs.next()){
         }
      }finally{
         readFinally(conn, st);
      }
   }
}
