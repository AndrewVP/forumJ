package org.forumj.common.db.entity;

/**
 * Created by Andrew on 12/05/2017.
 */
public interface HttpHeader {

    Long getId();

    void setId(Long id);

    HttpHeaderName getName();

    void setName(HttpHeaderName name);

    String getValue();

    void setValue(String value);
}
