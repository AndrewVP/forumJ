package org.forumj.dbextreme.db.dao;

import org.forumj.common.db.entity.HttpHeader;
import org.forumj.dbextreme.db.entity.HttpHeaderImpl;

/**
 * Created by Andrew on 13/05/2017.
 */
public class HttpHeaderNameDao {

    public HttpHeader getObject(){
        return new HttpHeaderImpl();
    }

    public void create(HttpHeader httpHeader){

    }
}
