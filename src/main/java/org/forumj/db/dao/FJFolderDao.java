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
import static org.forumj.db.entity.IFJFolder.*;
import static org.forumj.db.entity.IFJInterface.USER_ID_FIELD_NAME;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.db.entity.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJFolderDao extends FJDao {

   public List<IFJFolder> findAll(User user, IFJInterface interf) throws SQLException, ConfigurationException, IOException{
      List<IFJFolder> result = new ArrayList<IFJFolder>();
      String query = getLoadFoldersInQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, user.getId());
         st.setLong(2, interf.getId());
         ResultSet rs = st.executeQuery();
         while (rs.next()){
            IFJFolder folder = new FJFolder();
            folder.setId(rs.getLong(ID_FIELD_NAME));
            folder.setCreateDate(rs.getDate(DATE_CREATE_FIELD_NAME));
            if (rs.getLong(USER_ID_FIELD_NAME) != 0){
               folder.setUser(user);
            }
            folder.setName(rs.getString(NAME_FIELD_NAME));
            result.add(folder);
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }

   public List<IFJFolder> findAllNotIn(User user, IFJInterface interf) throws SQLException, ConfigurationException, IOException{
      List<IFJFolder> result = new ArrayList<IFJFolder>();
      String query = getLoadFoldersNotInQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, user.getId());
         st.setLong(2, user.getId());
         st.setLong(3, interf.getId());
         ResultSet rs = st.executeQuery();
         while (rs.next()){
            IFJFolder folder = new FJFolder();
            folder.setId(rs.getLong(ID_FIELD_NAME));
            folder.setCreateDate(rs.getDate(DATE_CREATE_FIELD_NAME));
            if (rs.getLong(USER_ID_FIELD_NAME) != 0){
               folder.setUser(user);
            }
            folder.setName(rs.getString(NAME_FIELD_NAME));
            result.add(folder);
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
   
   public List<IFJFolder> findAll(User user) throws SQLException, ConfigurationException, IOException{
      List<IFJFolder> result = new ArrayList<IFJFolder>();
      String query = getLoadFoldersQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, user.getId());
         ResultSet rs = st.executeQuery();
         while (rs.next()){
            IFJFolder folder = new FJFolder();
            folder.setId(rs.getLong(ID_FIELD_NAME));
            folder.setCreateDate(rs.getDate(DATE_CREATE_FIELD_NAME));
            if (rs.getLong(USER_ID_FIELD_NAME) != 0){
               folder.setUser(user);
            }
            folder.setName(rs.getString(NAME_FIELD_NAME));
            result.add(folder);
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
   
   
}
