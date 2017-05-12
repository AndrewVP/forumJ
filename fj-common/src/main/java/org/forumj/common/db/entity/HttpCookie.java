package org.forumj.common.db.entity;

/**
 * Created by Andrew on 12/05/2017.
 */
public interface HttpCookie {
    Long getId();

    void setId(Long id);

    HttpCookieName getName();

    void setName(HttpCookieName name);

    String getValue();

    void setValue(String value);

    String getComment();

    void setComment(String comment);

    String getDomain();

    void setDomain(String domain);

    int getMaxAge();

    void setMaxAge(int maxAge);

    String getPath();

    void setPath(String path);

    boolean isSecure();

    void setSecure(boolean secure);

    int getVersion();

    void setVersion(int version);

    boolean isHttpOnly();

    void setHttpOnly(boolean httpOnly);
}
