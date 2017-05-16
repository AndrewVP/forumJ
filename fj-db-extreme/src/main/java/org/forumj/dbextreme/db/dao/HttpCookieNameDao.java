package org.forumj.dbextreme.db.dao;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.Entity;
import org.forumj.common.db.entity.HttpCookieName;
import org.forumj.common.db.entity.HttpHeaderName;
import org.forumj.dbextreme.db.entity.HttpCookieNameImpl;
import org.forumj.dbextreme.db.entity.HttpHeaderNameImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.getCheckCookieNameExistsQuery;
import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.getCreateHttpCookieNameQuery;
import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.getCreateHttpHeaderNameQuery;

/**
 * Created by Andrew on 13/05/2017.
 */
public class HttpCookieNameDao extends FJDao {

    public HttpCookieName getObject(){
        return new HttpCookieNameImpl();
    }

    @Override
    protected int prepareStatmentForUpdate(Entity entity, PreparedStatement st) throws SQLException {
        int parameterIndex = 0;
        if (entity instanceof HttpCookieName){
            HttpCookieName httpCookieName = (HttpCookieName) entity;
            st.setString(++parameterIndex, httpCookieName.getName());
        }
        return parameterIndex;
    }

    public Long find(String name) throws IOException, SQLException, ConfigurationException {
        Long result = null;
        String query = getCheckCookieNameExistsQuery();
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(query);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                result = rs.getLong("id");
            }
        }finally{
            readFinally(conn, st);
        }
        return result;
    }

    @Override
    protected String getCreateQuery() throws IOException {
        return getCreateHttpCookieNameQuery();
    }
}
