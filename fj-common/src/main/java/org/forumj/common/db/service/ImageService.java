package org.forumj.common.db.service;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.entity.Image;
import org.forumj.common.db.entity.ImageType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ImageService {

   public Image create(DiskFileItem fileItem, IUser user, long albumId, ImageType type) throws Exception;
   
   public Image getObject();

   public List<Image> getImages(long userId, long albumId, ImageType type) throws Exception;

   public Image getImage(long id) throws Exception;

}