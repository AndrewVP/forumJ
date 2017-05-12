package org.forumj.dbextreme.db.entity;

import org.forumj.common.db.entity.HttpCookie;
import org.forumj.common.db.entity.HttpCookieName;

import javax.servlet.http.Cookie;

/**
 * Created by Andrew on 12/05/2017.
 */
public class HttpCookieImpl implements HttpCookie {

    private Long id;
    private HttpCookieName name; // NAME= ... "$Name" style is reserved
    private String value; // value of NAME
    private String comment; // ;Comment=VALUE ... describes cookie's use
    private String domain; // ;Domain=VALUE ... domain that sees cookie
    private int maxAge; // ;Max-Age=VALUE ... cookies auto-expire
    private String path; // ;Path=VALUE ... URLs that see the cookie
    private boolean secure; // ;Secure ... e.g. use SSL
    private int version; // ;Version=1 ... means RFC 2109++ style
    private boolean httpOnly; // Not in cookie specs, but supported by browsers

    public HttpCookieImpl() {}

    public HttpCookieImpl(Cookie cookie) {
        this.name = new HttpCookieNameImpl(cookie.getName());
        this.value = cookie.getValue();
        this.comment = cookie.getComment();
        this.domain = cookie.getDomain();
        this.maxAge = cookie.getMaxAge();
        this.path = cookie.getPath();
        this.secure = cookie.getSecure();
        this.version = cookie.getVersion();
        this.httpOnly = cookie.isHttpOnly();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public HttpCookieName getName() {
        return name;
    }

    @Override
    public void setName(HttpCookieName name) {
        this.name = name;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public int getMaxAge() {
        return maxAge;
    }

    @Override
    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
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
    public boolean isSecure() {
        return secure;
    }

    @Override
    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean isHttpOnly() {
        return httpOnly;
    }

    @Override
    public void setHttpOnly(boolean httpOnly) {
        this.httpOnly = httpOnly;
    }
}
