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
import org.forumj.common.db.entity.*;
import org.forumj.dbextreme.db.entity.FJImage;
import org.forumj.dbextreme.db.entity.QuestNode;
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
         st = conn.prepareStatement(query);
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

   public List<Image> getImages(long userId, long albumId, ImageType type) throws Exception{
      List<Image> result = new ArrayList<>();
      String query = getLoadImageThumbsQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query) ;
         int parameterIndex = 0;
         st.setLong(++parameterIndex, userId);
         st.setLong(++parameterIndex, albumId);
         st.setLong(++parameterIndex, type.ordinal());
         ResultSet rs = st.executeQuery();
         while (rs.next()){
            result.add(getImage(rs));
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }

   public Image getImage(long id) throws Exception{
      Image result = null;
      String query = getLoadImageQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query) ;
         int parameterIndex = 0;
         st.setLong(++parameterIndex, id);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = getImage(rs);
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }

   private Image getImage(ResultSet rs) throws Exception{
      Image result = null;
      result = new FJImage();
      result.setAlbumId(rs.getLong("albumId"));
      result.setDateAdd(rs.getLong("dateAdd"));
      result.setExtension(rs.getString("extension"));
      result.setHeight(rs.getInt("height"));
      result.setWidth(rs.getInt("width"));
      result.setId(rs.getLong("id"));
      result.setImageName(rs.getString("imageName"));
      result.setImageType(ImageType.fromOrder(rs.getInt("imageType")));
      result.setInitialName(rs.getString("initialName"));
      result.setParentId(rs.getLong("parent"));
      result.setPath(rs.getString("path"));
      result.setUserId(rs.getLong("userId"));
      return result;
   }

}

