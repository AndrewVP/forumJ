package org.forumj.dbextreme.db.entity;

import org.forumj.common.db.entity.*;
import org.forumj.common.web.HttpMethod;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrew on 12/05/2017.
 */
public class RequestImpl implements Request {

    private Long id;

    private HttpMethod method;

    private Long userId;

    private IpAddress ip;

    private String url;

    private Long time;

    private List<HttpHeader> headers = new LinkedList<>();

    private List<HttpCookie> cookies = new LinkedList<>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public HttpMethod getMethod() {
        return method;
    }

    @Override
    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public IpAddress getIp() {
        return ip;
    }

    @Override
    public void setIp(IpAddress ip) {
        this.ip = ip;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Long getTime() {
        return time;
    }

    @Override
    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public List<HttpHeader> getHeaders() {
        return headers;
    }

    @Override
    public void setHeaders(List<HttpHeader> headers) {
        this.headers = headers;
    }

    @Override
    public List<HttpCookie> getCookies() {
        return cookies;
    }

    @Override
    public void setCookies(List<HttpCookie> cookies) {
        this.cookies = cookies;
    }

    @Override
    public void addCookie(HttpCookie httpCookie){
        cookies.add(httpCookie);
    }

    @Override
    public void addHeader(HttpHeader httpHeader){
        headers.add(httpHeader);
    }
}
