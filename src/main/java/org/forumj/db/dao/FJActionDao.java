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

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJActionDao extends FJDao {

   public List<Map<String, Object>> getUsersArray() throws ConfigurationException, SQLException{
      List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
      String query33="SELECT "+
      "DISTINCT users.nick AS nicks "+
      "FROM fd_action "+
      "LEFT JOIN users on fd_action.fd_user=users.id "+
      "WHERE (fd_action.fd_user<>0) and (fd_action.fd_time >now() - INTERVAL 20 MINUTE) "+
      "and (fd_action.fd_user<>95) and (users.ban=0) "+
      "ORDER BY users.nick";
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query33);
         while (rs.next()){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("nick", rs.getString("nicks")) ;
            result.add(map);
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }

   public int getGuestCount() throws ConfigurationException, SQLException{
      Integer result = 0;
      String query34="" +
      "SELECT" +
      "  count(DISTINCT fd_ip) as guests " +
      "FROM " +
      "  fd_action " +
      "WHERE " +
      "  (fd_user=0) " +
      "  AND (fd_time > now() - INTERVAL 20 MINUTE) ";
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query34);
         if (rs.next()){
            result = rs.getInt("guests");
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
}
