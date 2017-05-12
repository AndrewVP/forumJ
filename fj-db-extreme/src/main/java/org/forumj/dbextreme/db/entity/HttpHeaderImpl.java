package org.forumj.dbextreme.db.entity;

import org.forumj.common.db.entity.HttpHeader;
import org.forumj.common.db.entity.HttpHeaderName;

/**
 * Created by Andrew on 12/05/2017.
 */
public class HttpHeaderImpl implements HttpHeader {

    private Long id;

    private HttpHeaderName name;

    private String value;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public HttpHeaderName getName() {
        return name;
    }

    @Override
    public void setName(HttpHeaderName name) {
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
}
