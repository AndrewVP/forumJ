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
package org.forumj.dbextreme.db.service;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.forumj.common.config.FJConfiguration;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.entity.Image;
import org.forumj.common.db.entity.ImageType;
import org.forumj.common.db.service.ImageService;
import org.forumj.dbextreme.db.entity.FJImage;
import org.forumj.image.ImageSize;
import org.forumj.image.ImageTools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;


/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class ImageServiceImpl extends FJService implements ImageService {

    private static Logger logger = LogManager.getLogger("org.forumj.service");

    private static String imagesContextDir = "images";
    private static String imagesDir = null;

    static {
        try {
            imagesContextDir = FJConfiguration.getConfig().getString("imagesContextDir", imagesContextDir);
            imagesDir = FJConfiguration.getConfig().getString("fj.home.dir") + File.separator + imagesContextDir;
        } catch (ConfigurationException e) {
            logger.error(e.getMessage(), e);
        }

    }

    /* (non-Javadoc)
     * @see org.forumj.dbextreme.db.service.ActionService#create(org.forumj.common.db.entity.IFJAction)
     */
    @Override
    public Image create(DiskFileItem imageFile, IUser user, long albumId, ImageType type) throws Exception {
        Image fjImage = new FJImage();
        String imageFileName = imageFile.getName();
        fjImage.setAlbumId(albumId);
        fjImage.setExtension(ImageTools.getFileExtension(imageFileName));
        fjImage.setInitialName(imageFileName);
        fjImage.setImageType(type);
        fjImage.setUserId(user.getId());
        fjImage.setDateAdd(System.currentTimeMillis());
        getImageDao().create(fjImage);
        java.awt.Image image = ImageTools.getImage(imageFile);
        ImageSize size = ImageTools.getImageSize(image);
        fjImage.setHeight(size.getHeight());
        fjImage.setWidth(size.getWidth());
        String imagePath = ImageTools.makePath(fjImage.getId(), imagesDir);
        ImageTools.checkAndCreateImagePath(imagePath);
        String imageNewFileName = ImageTools.makeImageName(fjImage.getId());
        fjImage.setImageName(imageNewFileName);
        String fullFileName = ImageTools.makeImageName(fjImage.getId(), imagePath, fjImage.getExtension());
        fjImage.setPath(fullFileName);
        File destFile = new File(fullFileName);
        imageFile.write(destFile);
        getImageDao().update(fjImage);
        createChildImage(fjImage, ImageType.ALBUM_THUMBNAIL);
        createChildImage(fjImage, ImageType.POST_THUMBNAIL);
        return fjImage;
    }

    @Override
    public Image getObject() {
        return new FJImage();
    }

    @Override
    public List<Image> getImages(long userId, long albumId, ImageType type) throws Exception {
        return getImageDao().getImages(userId, albumId, type);
    }

    @Override
    public Image getImage(long id) throws Exception {
        return getImageDao().getImage(id);
    }

    private Image createChildImage(Image parent, ImageType type) throws Exception{
        Image fjImage = new FJImage();
        fjImage.setAlbumId(parent.getAlbumId());
        fjImage.setExtension(parent.getExtension());
        fjImage.setImageType(type);
        fjImage.setUserId(parent.getUserId());
        fjImage.setDateAdd(parent.getDateAdd());
        fjImage.setParentId(parent.getId());
        getImageDao().create(fjImage);
        java.awt.Image image = ImageTools.getImage(parent.getPath());
        ImageSize size = new ImageSize(parent.getHeight(), parent.getWidth());
        if (type.getHeight() == 0 && type.getWidth() == 0){
        }else if (type.getHeight() == 0){
            size = ImageTools.fitImageToWidth(size, type.getWidth());
        }else if (type.getWidth() == 0){
            //TODO Missed code!
        }else{
            size = ImageTools.fitImageToSize(size,type.getWidth(), type.getHeight());
        }
        fjImage.setHeight(size.getHeight());
        fjImage.setWidth(size.getWidth());
        String imagePath = ImageTools.makePath(fjImage.getId(), imagesDir);
        ImageTools.checkAndCreateImagePath(imagePath);
        String imageNewFileName = ImageTools.makeImageName(fjImage.getId());
        fjImage.setImageName(imageNewFileName);
        String fullFileName = ImageTools.makeImageName(fjImage.getId(), imagePath, fjImage.getExtension());
        fjImage.setPath(fullFileName);
        ImageTools.writeImageToFile(size, BufferedImage.TYPE_INT_RGB, image, fullFileName, parent.getExtension());
        getImageDao().update(fjImage);

        return fjImage;
    }
}
