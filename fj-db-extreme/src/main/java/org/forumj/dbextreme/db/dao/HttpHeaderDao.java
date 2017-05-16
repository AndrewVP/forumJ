package org.forumj.dbextreme.db.dao;

import org.forumj.common.db.entity.Entity;
import org.forumj.common.db.entity.HttpHeader;
import org.forumj.common.db.entity.HttpHeaderName;
import org.forumj.dbextreme.db.entity.HttpHeaderImpl;
import org.forumj.dbextreme.db.entity.HttpHeaderNameImpl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.getCreateHttpHeaderNameQuery;
import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.getCreateHttpHeaderQuery;

/**
 * Created by Andrew on 13/05/2017.
 */
public class HttpHeaderDao extends FJDao {

    public HttpHeader getObject(){
        return new HttpHeaderImpl();
    }

    @Override
    protected int prepareStatmentForUpdate(Entity entity, PreparedStatement st) throws SQLException {
        int parameterIndex = 0;
        if (entity instanceof HttpHeader){
            HttpHeader httpHeader = (HttpHeader) entity;
            st.setLong(++parameterIndex, httpHeader.getRequestId());
            //TODO can be NPE
            st.setLong(++parameterIndex, httpHeader.getName().getId());
            st.setString(++parameterIndex, httpHeader.getValue());
        }
        return parameterIndex;
    }

    @Override
    protected String getCreateQuery() throws IOException {
        return getCreateHttpHeaderQuery();
    }
}
