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

import static org.forumj.common.db.entity.IIgnor.*;
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
public class FJIgnorDao extends BaseDao {

   public List<IIgnor> loadAll(Long userId) throws IOException, ConfigurationException, SQLException{
      List<IIgnor> result = new ArrayList<IIgnor>();
      String query = getLoadIgnorQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, userId);
         ResultSet rs = st.executeQuery();
         while (rs.next()){
            Ignor ignor = new Ignor();
            ignor.setId(rs.getLong(ID_FIELD_NAME));
            ignor.setType(rs.getInt(TYPE_FIELD_NAME));
            ignor.setUserId(userId);
            IUser ignoredUser = new User();
            ignoredUser.setId(rs.getLong(IGNORED_USER_ID_FIELD_NAME));
            ignoredUser.setNick(rs.getString("nick"));
            ignor.setUser(ignoredUser);
            ignor.setStart(rs.getDate(IGNOR_START_FIELD_NAME));
            ignor.setEnd(rs.getDate(IGNOR_END_FIELD_NAME));
            result.add(ignor);
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
   
   public void update(IIgnor ignor) throws IOException, ConfigurationException, SQLException{
      String query = getUpdateIgnorQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setTimestamp(1, new java.sql.Timestamp(ignor.getEnd().getTime()));
         st.setInt(2, ignor.getType());
         st.setLong(3, ignor.getId());
         st.setLong(4, ignor.getUserId());
         st.executeUpdate();
      }finally{
         readFinally(conn, st);
      }
   }
   
   public void create(long ignoredUserId, IUser user) throws SQLException, ConfigurationException, IOException{
      String query = getCreateIgnorQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, user.getId());
         st.setLong(2, ignoredUserId);
         st.executeUpdate();
      }finally{
         readFinally(conn, st);
      }
   }
}
