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

   public List<IFJFolder> findAll(IUser user, IFJInterface interf) throws SQLException, ConfigurationException, IOException{
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

   public List<IFJFolder> findAllNotIn(IUser user, IFJInterface interf) throws SQLException, ConfigurationException, IOException{
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
   
   public List<IFJFolder> findAll(IUser user) throws SQLException, ConfigurationException, IOException{
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
   
   public void delete(Long folderId, IUser user) throws ConfigurationException, SQLException, IOException{
      String deleteTranzitQuery = getDeleteFolderTranzitQuery();
      String deleteVTranzitQuery = getDeleteVTranzitQuery();
      String deleteFolderQuery = getDeleteFolderQuery();
      Connection conn = null;
      PreparedStatement st = null;
      boolean error = true;
      try {
         conn = getConnection();
         conn.setAutoCommit(false);
         st = conn.prepareStatement(deleteTranzitQuery);
         st.setLong(1, folderId);
         st.setLong(2, user.getId());
         st.executeUpdate();
         st = conn.prepareStatement(deleteVTranzitQuery);
         st.setLong(1, folderId);
         st.setLong(2, user.getId());
         st.executeUpdate();
         st = conn.prepareStatement(deleteFolderQuery);
         st.setLong(1, folderId);
         st.setLong(2, user.getId());
         st.executeUpdate();
         error = false;
      }finally{
         writeFinally(conn, st, error);
      }
   }
   
   public void deleteFolderFromView(Long folderId, Long viewId, IUser user) throws ConfigurationException, SQLException, IOException{
      String deleteTranzitQuery = getDeleteFolderFromViewQuery();
      Connection conn = null;
      PreparedStatement st = null;
      boolean error = true;
      try {
         conn = getConnection();
         conn.setAutoCommit(false);
         st = conn.prepareStatement(deleteTranzitQuery);
         st.setLong(1, folderId);
         st.setLong(2, user.getId());
         st.setLong(3, viewId);
         st.executeUpdate();
         error = false;
      }finally{
         writeFinally(conn, st, error);
      }
   }
   
   public void moveToRecyclebin(long threadId, IUser user) throws IOException, ConfigurationException, SQLException{
      // TODO Magic integer!
      moveToFolder(threadId, 3, user);
   }

   public void moveToFolder(long threadId, long folderId, IUser user) throws IOException, ConfigurationException, SQLException{
      String deleteTranzitQuery = getDeleteThreadTranzitQuery();
      String appendQuery = getAppendThreadInFolderQuery();
      Connection conn = null;
      PreparedStatement st = null;
      boolean error = true;
      try {
         conn = getConnection();
         conn.setAutoCommit(false);
         st = conn.prepareStatement(deleteTranzitQuery);
         st.setLong(1, threadId);
         st.setLong(2, user.getId());
         st.executeUpdate();
         st = conn.prepareStatement(appendQuery);
         st.setLong(1, threadId);
         st.setLong(2, user.getId());
         st.setLong(3, folderId);
         st.executeUpdate();
         error = false;
      }finally{
         writeFinally(conn, st, error);
      }
   }
   
   public boolean isExist(String name, IUser user) throws SQLException, ConfigurationException, IOException{
      boolean result = false;
      String query = getIsFolderExistQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, user.getId());
         st.setString(2, name);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = true;
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
   
   public Long create(String folderName, IUser user) throws SQLException, ConfigurationException, IOException{
      Long result = null; 
      String query = getCreateFolderQuery();
      PreparedStatement st = null;
      Connection conn = null;
      boolean error = true;
      try{
         conn = getConnection();
         conn.setAutoCommit(false);
         st = conn.prepareStatement(query, new String[]{"id"});
         st.setString(1, folderName);
         st.setLong(3, user.getId());
         st.executeUpdate();
         ResultSet idRs = st.getGeneratedKeys();
         if (idRs.next()){
            result = idRs.getLong(1);
         }
         FJInterfaceDao interfaceDao = new FJInterfaceDao();
         //TODO Magic integer!
         interfaceDao.addFolder(3, result, user, conn);
         interfaceDao.addFolder(4, result, user, conn);
         error = false;
      }finally{
         writeFinally(conn, st, error);
      }
      return result;
   }

   public List<Map<String, Object>> getFoldersArray(Long idUser) throws ConfigurationException, SQLException{
      List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
      String sql_views="SELECT id, flname FROM fdfolders WHERE user=0 OR user=" + idUser.toString() + " ORDER BY id ";
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql_views);
         while (rs.next()){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", rs.getLong("id")) ;
            map.put("flname", rs.getString("flname")) ;
            result.add(map);
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
}
