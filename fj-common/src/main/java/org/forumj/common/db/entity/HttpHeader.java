package org.forumj.common.db.entity;

/**
 * Created by Andrew on 12/05/2017.
 */
public interface HttpHeader extends Entity{

    HttpHeaderName getName();

    void setName(HttpHeaderName name);

    void setName(String name);

    String getValue();

    void setValue(String value);

    Long getRequestId();

    void setRequestId(Long requestId);
}
