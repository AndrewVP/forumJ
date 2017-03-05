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
import org.forumj.common.db.entity.Image;
import org.forumj.dbextreme.db.entity.User;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.*;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class ImageDao extends FJDao {

   public void create(Image image) throws SQLException, ConfigurationException, IOException{
      String query = getCreateImageQuery();
      PreparedStatement st = null;
      Connection conn = null;
      boolean error = true;
      try{
         conn = getConnection();
         conn.setAutoCommit(false);
         st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
         prepareStatmentForUpdate(image, st);
         int insertedRows = st.executeUpdate();
         if (insertedRows == 1){
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()){
               image.setId(rs.getLong(1));
               error = false;
            }
         }
      }finally{
         writeFinally(conn, st, error);
      }
   }

   public void update(Image image) throws SQLException, ConfigurationException, IOException{
      String query = getUpdateImageQuery();
      PreparedStatement st = null;
      Connection conn = null;
      boolean error = true;
      try{
         conn = getConnection();
         conn.setAutoCommit(false);
         st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
         int parameterIndex = prepareStatmentForUpdate(image, st);
         st.setLong(++parameterIndex, image.getId());
         int updatedRows = st.executeUpdate();
         if (updatedRows == 1){
            error = false;
         }
      }finally{
         writeFinally(conn, st, error);
      }
   }

    private int prepareStatmentForUpdate(Image image, PreparedStatement st) throws SQLException {
       int parameterIndex = 0;
       st.setLong(++parameterIndex, image.getUserId());
       st.setLong(++parameterIndex, image.getParentId());
       st.setLong(++parameterIndex, image.getAlbumId());
       st.setString(++parameterIndex, image.getExtension());
       st.setString(++parameterIndex, image.getInitialName());
       st.setString(++parameterIndex, image.getImageName());
       st.setString(++parameterIndex, image.getPath());
       st.setInt(++parameterIndex, image.getHeight());
       st.setInt(++parameterIndex, image.getWidth());
       st.setInt(++parameterIndex, image.getImageType().ordinal());
       st.setLong(++parameterIndex, image.getDateAdd());
       return parameterIndex;
    }
}

