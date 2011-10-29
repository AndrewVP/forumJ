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
import static org.forumj.db.entity.IFJInterface.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.db.entity.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJInterfaceDao extends FJDao {

   public List<IFJInterface> findAll(User user) throws IOException, SQLException, ConfigurationException{
      List<IFJInterface> result = new ArrayList<IFJInterface>();
      String query = getLoadInterfacesQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, user.getId());
         ResultSet rs = st.executeQuery();
         while (rs.next()){
            IFJInterface interf = new FJInterface();
            interf.setId(rs.getLong(ID_FIELD_NAME));
            interf.setCreateDate(rs.getDate(DATE_CREATE_FIELD_NAME));
            if (rs.getLong(USER_ID_FIELD_NAME) != 0){
               interf.setUser(user);
            }
            interf.setName(rs.getString(NAME_FIELD_NAME));
            result.add(interf);
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }

   public IFJInterface find(User user, Long id) throws IOException, SQLException, ConfigurationException{
      IFJInterface result = new FJInterface();
      String query = getLoadInterfaceQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, user.getId());
         st.setLong(2, id);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result.setId(id);
            result.setCreateDate(rs.getDate(DATE_CREATE_FIELD_NAME));
            if (rs.getLong(USER_ID_FIELD_NAME) != 0){
               result.setUser(user);
            }
            result.setName(rs.getString(NAME_FIELD_NAME));
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
   
   public boolean isInterfaceContainsFolder(long interfaceId, long folderId, User user) throws IOException, ConfigurationException, SQLException{
      boolean result = false;
      String query = getIsInterfaceContainsFolderQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, interfaceId);
         st.setLong(2, folderId);
         st.setLong(3, user.getId());
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = true;
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
   
   public void addFolder(long interfaceId, long folderId, User user) throws ConfigurationException, SQLException, IOException{
      String query = getAddFolderQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, interfaceId);
         st.setLong(2, folderId);
         st.setLong(3, user.getId());
         st.executeUpdate();
      }finally{
         readFinally(conn, st);
      }
   }

   public void delete(long interfaceId, User user) throws ConfigurationException, SQLException, IOException{
      String deleteFoldersQuery = getDeleteAllFoldersFromViewQuery();
      String deleteViewQuery = getDeleteViewQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(deleteFoldersQuery);
         st.setLong(1, interfaceId);
         st.setLong(2, user.getId());
         st.executeUpdate();
         st = conn.prepareStatement(deleteViewQuery);
         st.setLong(1, interfaceId);
         st.setLong(2, user.getId());
         st.executeUpdate();
      }finally{
         readFinally(conn, st);
      }
   }
}
