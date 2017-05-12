package org.forumj.dbextreme.db.entity;

import org.forumj.common.db.entity.HttpHeaderName;

/**
 * Created by Andrew on 12/05/2017.
 */
public class HttpHeaderNameImpl implements HttpHeaderName {

    private Long id;

    private String name;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
