package org.forumj.common.db.entity;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.forumj.common.config.FJConfiguration;

import java.io.File;

/**
 * Created by Andrew on 05/03/2017.
 */
public enum ImageType {
    ORIGINAL
    , AVATAR(FJConfiguration.AVATAR, 150, 150)
    , ALBUM_THUMBNAIL(FJConfiguration.ALBUM_THUMB, 300, 0)
    , POST_THUMBNAIL(FJConfiguration.POST_THUMB, 150, 0);

    private Logger logger = LogManager.getLogger("org.forumj.service");

    private int width;
    private int height;

    private ImageType(){}

    private ImageType(String parameterName, int defaultWidthValue, int defaultHeightValue){
        try {
            width = FJConfiguration.getConfig().getInt(parameterName + "." + FJConfiguration.WIDTH, defaultWidthValue);
            height = FJConfiguration.getConfig().getInt(parameterName + "." + FJConfiguration.HEIGHT, defaultHeightValue);
        } catch (ConfigurationException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}
