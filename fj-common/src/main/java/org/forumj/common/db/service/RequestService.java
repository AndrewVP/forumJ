package org.forumj.common.db.service;

import org.forumj.common.db.entity.Request;

/**
 * Created by Andrew on 12/05/2017.
 */
public interface RequestService {

    Request getObject();

    void create(Request request) throws Exception;
}
