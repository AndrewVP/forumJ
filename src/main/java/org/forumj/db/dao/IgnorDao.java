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
package org.forumj.db.dao;

import static org.forumj.db.dao.tool.QueryBuilder.*;
import static org.forumj.db.entity.IIgnor.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.db.entity.*;
import org.forumj.exception.DBException;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class IgnorDao extends FJDao {

   public List<Ignor> loadAll(Long userId) throws IOException{
      List<Ignor> result = new ArrayList<Ignor>();
      String query = getLoadIgnorQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query) ;
         st.setLong(1, userId);
         ResultSet rs = st.executeQuery();
         while (rs.next()){
            Ignor ignor = new Ignor();
            ignor.setId(rs.getLong(ID_FIELD_NAME));
            ignor.setType(rs.getInt(TYPE_FIELD_NAME));
            ignor.setUserId(userId);
            User ignoredUser = new User();
            ignoredUser.setId(rs.getLong(IGNORED_USER_ID_FIELD_NAME));
            ignoredUser.setNick(rs.getString("nick"));
            ignor.setUser(ignoredUser);
            ignor.setStart(rs.getDate(IGNOR_START_FIELD_NAME));
            ignor.setEnd(rs.getDate(IGNOR_END_FIELD_NAME));
            result.add(ignor);
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
