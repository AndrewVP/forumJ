package org.forumj.dbextreme.db.dao;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.Entity;
import org.forumj.common.db.entity.HttpCookieName;
import org.forumj.dbextreme.db.entity.HttpCookieNameImpl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.getCheckCookieNameExistsQuery;
import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.getCreateHttpCookieNameQuery;

/**
 * Created by Andrew on 13/05/2017.
 */
public class HttpCookieNameDao extends BaseDao {

    public HttpCookieName getObject(){
        return new HttpCookieNameImpl();
    }

    @Override
    protected int prepareStatementForUpdate(Entity entity, PreparedStatement st) throws SQLException {
        int parameterIndex = 0;
        if (entity instanceof HttpCookieName){
            HttpCookieName httpCookieName = (HttpCookieName) entity;
            st.setString(++parameterIndex, httpCookieName.getName());
        }
        return parameterIndex;
    }

    public Long find(String name) throws IOException, SQLException, ConfigurationException {
        return (Long) readValue(getCheckCookieNameExistsQuery(), name);
    }

    @Override
    protected String getCreateQuery() throws IOException {
        return getCreateHttpCookieNameQuery();
    }
}
