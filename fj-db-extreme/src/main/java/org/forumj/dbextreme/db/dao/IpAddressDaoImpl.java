/*
 * Copyright Andrew V. Pogrebnyak
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.forumj.dbextreme.db.dao;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.IpAddress;

import java.io.IOException;
import java.sql.*;

import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class IpAddressDaoImpl extends BaseDao {

   public void update(IpAddress ipAddress) throws IOException, ConfigurationException, SQLException{
      String query = getUpdateIpAddressQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setString(1, ipAddress.getIp());
         st.setBoolean(2, ipAddress.isSpammer());
         st.setString(3, ipAddress.getSource());
         st.setTimestamp(4, new java.sql.Timestamp(ipAddress.getLastCheck().getTime()));
         st.setLong(5, ipAddress.getId());
         st.executeUpdate();
      }finally{
         readFinally(conn, st);
      }
   }
   
   public void create(IpAddress ipAddress) throws SQLException, ConfigurationException, IOException{
      String query = getCreateIpAddressQuery();
      Connection conn = null;
      PreparedStatement st = null;
      boolean error = true;
      try {
         conn = getConnection();
         conn.setAutoCommit(false);
         st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
         st.setString(1, ipAddress.getIp());
         st.setBoolean(2, ipAddress.isSpammer());
         st.setString(3, ipAddress.getSource());
         Timestamp timestamp = ipAddress.getLastCheck() == null ? null : new Timestamp(ipAddress.getLastCheck().getTime());
         st.setTimestamp(4, timestamp);
         int insertedRows = st.executeUpdate();
         if (insertedRows == 1){
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()){
               ipAddress.setId(rs.getLong(1));
               error = false;
            }
         }
      }finally{
         writeFinally(conn, st, error);
      }
   }

   public Boolean isSpammer(String ip) throws IOException, SQLException, ConfigurationException{
      Boolean result = null;
      String query = getCheckSpammerQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setString(1, ip);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = new Boolean(rs.getBoolean("spammer"));
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }

   public Long find(String ip) throws IOException, SQLException, ConfigurationException {
      return (Long) readValue(getCheckIpExistsQuery(), ip);
   }
}
