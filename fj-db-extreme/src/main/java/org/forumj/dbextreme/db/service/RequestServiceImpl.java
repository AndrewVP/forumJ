package org.forumj.dbextreme.db.service;

import org.forumj.common.db.entity.Request;
import org.forumj.common.db.service.RequestService;

/**
 * Created by Andrew on 12/05/2017.
 */
public class RequestServiceImpl extends FJService implements RequestService {

    @Override
    public Request getObject() {
        return getRequestDao().getObject();
    }

    @Override
    public void create(Request request) throws Exception {
        if (request.getIp().getId() == null){
            String ip = request.getIp().getIp();
            Long ipId = getIpaddressDao().find(ip);
            if (ipId == null){
                getIpaddressDao().create(request.getIp());
            }
            request.getIp().setId(ipId);
        }
        getRequestDao().create(request);
    }
}
