package org.forumj.common.db.entity;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public interface Image {

    long getId();

    void setId(long id);

    long getParentId();

    void setParentId(long parentId);

    ImageType getImageType();

    void setImageType(ImageType imageType);

    String getExtension();

    void setExtension(String extension);

    String getInitialName();

    void setInitialName(String initialName);

    String getImageName();

    void setImageName(String imageName);

    String getPath();

    void setPath(String path);

    int getHeight();

    void setHeight(int height);

    int getWidth();

    void setWidth(int width);

    boolean isOriginal();

    long getAlbumId();

    void setAlbumId(long albumId);

    long getUserId();

    void setUserId(long userId);

    long getDateAdd();

    void setDateAdd(long dateAdd);
}
