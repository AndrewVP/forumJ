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

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.IFJAction;
import org.forumj.common.db.entity.IUser;
import org.forumj.dbextreme.db.entity.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.*;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJActionDao extends BaseDao {

   public List<IUser> getUsersArray() throws ConfigurationException, SQLException, IOException{
      List<IUser> result = new ArrayList<IUser>();
      String query = getConnectedUsersQuery();
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         while (rs.next()){
            IUser user = new User();
            user.setNick(rs.getString("nicks")) ;
            result.add(user);
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }

   public int getGuestsAmount() throws ConfigurationException, SQLException, IOException{
      Integer result = 0;
      String query = getConnectedGuestsAmountQuery();
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         if (rs.next()){
            result = rs.getInt("guests");
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
   
   public void create(IFJAction action) throws SQLException, ConfigurationException, IOException{
      String query = getCreateActionQuery();
      PreparedStatement st = null;
      Connection conn = null;
      boolean error = true;
      try{
         conn = getConnection();
         conn.setAutoCommit(false);
         st = conn.prepareStatement(query);
         st.setString(1, action.getIp());
         st.setString(2, action.getSubnet());
         st.setLong(3, action.getUserId());
         st.setString(4, action.getServletName());
         st.setString(5, action.getUas());
         st.setString(6, action.getRefer());
         st.executeUpdate();
         error = false;
      }finally{
         writeFinally(conn, st, error);
      }
   }
}
