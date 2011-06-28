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

import static org.forumj.db.dao.tool.QueryBuilder.*;

import java.io.IOException;
import java.sql.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.db.entity.*;
import org.forumj.exception.DBException;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJPostDao extends FJDao {

   public Long create(FJPost post, Connection conn) throws IOException, DBException{
      Long postId = null;
      String createPostQuery = getCreatePostQuery();
      FJForumDao forumDao = new FJForumDao();
      String tableBody = forumDao.getCurretBodyTable();
      String tableHead = forumDao.getCurretBodyHeadTable(); 
      String createPostBodyQuery = getCreatePostBodyQuery(tableBody);
      String createPostHeadQuery = getCreatePostHeadQuery(tableHead);
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(createPostQuery, new String[]{"id"});
         st.setLong(1, post.getHeadId());
         st.setInt(2, post.getState());
         st.setString(3, tableBody);
         st.setString(4, tableHead);
         st.executeUpdate();
         ResultSet idRs = st.getGeneratedKeys();
         if (idRs.next()){
            postId = idRs.getLong(1);
            post.setId(postId);
            st.close();
            st = conn.prepareStatement(createPostBodyQuery);
            FJPostBody postBody = post.getBody();
            st.setLong(1, postId);
            st.setLong(2, postId);
            st.setString(3, postBody.getBody());
            st.executeUpdate();
            st.close();
            st = conn.prepareStatement(createPostHeadQuery);
            FJPostHead postHead = post.getHead();
            st.setLong(1, postId);
            st.setLong(2, postId);
            st.setLong(3, postHead.getAuth());
            st.setLong(4, postHead.getThreadId());
            st.setString(5, postHead.getTitle());
            st.setInt(6, (int) postHead.getCreateTime().getTime());
            st.setString(7, postHead.getIp());
            st.setString(8, postHead.getDomen());
            st.executeUpdate();
         }else{
            throw new DBException("Post wasn't created");
         }
      } catch (ConfigurationException e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      } catch (SQLException e) {
         DBException ex = new DBException(e);
         onDatabaseError(ex);
         e.printStackTrace();
         throw ex;
      }finally{
         try {
            if (st != null){
               st.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return postId;
   }
}
