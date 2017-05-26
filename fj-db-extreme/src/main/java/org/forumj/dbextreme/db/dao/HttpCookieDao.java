package org.forumj.dbextreme.db.dao;

import org.forumj.common.db.entity.Entity;
import org.forumj.common.db.entity.HttpCookie;
import org.forumj.dbextreme.db.entity.HttpCookieImpl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.*;

/**
 * Created by Andrew on 13/05/2017.
 */
public class HttpCookieDao extends BaseDao {

    public HttpCookie getObject(){
        return new HttpCookieImpl();
    }

    @Override
    protected int prepareStatementForUpdate(Entity entity, PreparedStatement st) throws SQLException {
        int parameterIndex = 0;
        if (entity instanceof HttpCookie){
            HttpCookie httpCookie = (HttpCookie) entity;
            st.setLong(++parameterIndex, httpCookie.getRequestId());
            // TODO NPE
            st.setLong(++parameterIndex, httpCookie.getName().getId());
            st.setString(++parameterIndex, httpCookie.getValue());
            st.setString(++parameterIndex, httpCookie.getDomain());
            st.setString(++parameterIndex, httpCookie.getPath());
            st.setInt(++parameterIndex, httpCookie.getMaxAge());
            st.setInt(++parameterIndex, httpCookie.isSecure() ? 1 : 0);
            st.setInt(++parameterIndex, httpCookie.getVersion());
            st.setInt(++parameterIndex, httpCookie.isHttpOnly() ? 1 : 0);
        }
        return parameterIndex;
    }

    @Override
    protected String getCreateQuery() throws IOException {
        return getCreateHttpCookieQuery();
    }
}
