package org.forumj.common.db.service;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.entity.Image;
import org.forumj.common.db.entity.ImageType;

import java.io.IOException;
import java.sql.SQLException;

public interface ImageService {

   public Image create(DiskFileItem fileItem, IUser user, long albumId, ImageType type) throws ConfigurationException, SQLException, IOException, Exception;
   
   public Image getObject();

}