package org.forumj.common.db.service;

import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.entity.Request;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Andrew on 12/05/2017.
 */
public interface RequestService {

    Request getObject();

    void create(Request request) throws Exception;

    void create(HttpServletRequest httpServletRequest, IUser user) throws Exception;
}
