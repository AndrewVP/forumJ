package org.forumj.dbextreme.db.dao;

import org.forumj.common.db.entity.Entity;
import org.forumj.common.db.entity.Request;
import org.forumj.dbextreme.db.entity.RequestImpl;

import java.io.IOException;
import java.sql.*;

import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.*;

/**
 * Created by Andrew on 12/05/2017.
 */
public class RequestDaoImpl extends FJDao {

    public Request getObject() {
        return new RequestImpl();
    }

    @Override
    protected int prepareStatmentForUpdate(Entity entity, PreparedStatement st) throws SQLException {
        int parameterIndex = 0;
        if (entity instanceof Request){
            Request request = (Request) entity;
            st.setLong(++parameterIndex, request.getMethod().getId());
            st.setLong(++parameterIndex, request.getUserId());
            st.setLong(++parameterIndex, request.getIp().getId());
            st.setString(++parameterIndex, request.getUrl());
            st.setLong(++parameterIndex, request.getTime());
        }
        return parameterIndex;
    }

    @Override
    protected String getCreateQuery() throws IOException {
        return getCreateRequestQuery();
    }
}
