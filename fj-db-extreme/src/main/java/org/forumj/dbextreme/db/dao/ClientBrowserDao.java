package org.forumj.dbextreme.db.dao;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.ClientBrowser;
import org.forumj.common.db.entity.Entity;
import org.forumj.common.db.entity.HttpHeaderName;
import org.forumj.dbextreme.db.entity.HttpHeaderNameImpl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.getCheckClientBrowserExistsQuery;
import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.getCreateClientBrowserQuery;

/**
 * Created by Andrew on 13/05/2017.
 */
public class ClientBrowserDao extends BaseDao {

    public HttpHeaderName getObject(){
        return new HttpHeaderNameImpl();
    }

    @Override
    protected int prepareStatementForUpdate(Entity entity, PreparedStatement st) throws SQLException {
        int parameterIndex = 0;
        if (entity instanceof ClientBrowser){
            ClientBrowser clientclientBrowserS = (ClientBrowser) entity;
            st.setString(++parameterIndex, clientclientBrowserS.getName());
        }
        return parameterIndex;
    }

    public Long find(String name) throws IOException, SQLException, ConfigurationException {
        return (Long) readValue(getCheckClientBrowserExistsQuery(), name);
    }

    @Override
    protected String getCreateQuery() throws IOException {
        return getCreateClientBrowserQuery();
    }
}
