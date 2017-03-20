package org.forumj.dbextreme.db.entity;

import org.forumj.common.db.entity.Image;
import org.forumj.common.db.entity.ImageType;

/**
 * Created by Andrew on 04/03/2017.
 */
public class FJImage implements Image {

    private long id;

    private long parentId;

    private String extension;

    private String initialName;

    private String imageName;

    private String path;

    private int height;

    private int width;

    private ImageType imageType;

    private long albumId;

    private long userId;

    private long dateAdd;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    @Override
    public ImageType getImageType() {
        return imageType;
    }

    @Override
    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }

    @Override
    public String getExtension() {
        return extension;
    }

    @Override
    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String getInitialName() {
        return initialName;
    }

    @Override
    public void setInitialName(String initialName) {
        this.initialName = initialName;
    }

    @Override
    public String getImageName() {
        return imageName;
    }

    @Override
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public boolean isOriginal() {
        return imageType == ImageType.ORIGINAL;
    }

    @Override
    public long getAlbumId() {
        return albumId;
    }

    @Override
    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    @Override
    public long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public long getDateAdd() {
        return dateAdd;
    }

    @Override
    public void setDateAdd(long dateAdd) {
        this.dateAdd = dateAdd;
    }
}
