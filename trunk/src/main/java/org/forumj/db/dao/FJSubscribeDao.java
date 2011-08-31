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
import static org.forumj.db.entity.IFJSubscribe.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.db.entity.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJSubscribeDao extends FJDao {

   public List<IFJSubscribe> findAll(User user, Integer active) throws SQLException, ConfigurationException, IOException{
      List<IFJSubscribe> result = new ArrayList<IFJSubscribe>();
      String query = getLoadSubscribesQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, user.getId());
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            IFJSubscribe subscribe = new FJSubscribe();
            subscribe.setId(rs.getLong(ID_FIELD_NAME));
            subscribe.setUser(user);
            subscribe.setTitleId(rs.getLong(TITLE_ID_FIELD_NAME));
            subscribe.setHead(rs.getString(HEAD_FIELD_NAME));
            subscribe.setStart(rs.getDate(START_DATE_FIELD_NAME));
            subscribe.setEnd(rs.getDate(END_DATE_FIELD_NAME));
            subscribe.setKey(rs.getLong(KEY_FIELD_NAME));
            subscribe.setType(rs.getInt(TYPE_FIELD_NAME));
            subscribe.setActive(rs.getInt(ACTIVE_FIELD_NAME));
            result.add(subscribe);
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
}
