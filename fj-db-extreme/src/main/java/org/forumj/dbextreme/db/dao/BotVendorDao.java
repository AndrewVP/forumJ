package org.forumj.dbextreme.db.dao;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.BotVendor;
import org.forumj.common.db.entity.Entity;
import org.forumj.dbextreme.db.entity.BotVendorImpl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.getCheckClientBrowserExistsQuery;
import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.getCreateBotVendorQuery;

/**
 * Created by Andrew on 13/05/2017.
 */
public class BotVendorDao extends BaseDao {

    public BotVendor getObject(){
        return new BotVendorImpl();
    }

    @Override
    protected int prepareStatementForUpdate(Entity entity, PreparedStatement st) throws SQLException {
        int parameterIndex = 0;
        if (entity instanceof BotVendor){
            BotVendor botVendor = (BotVendor) entity;
            st.setString(++parameterIndex, botVendor.getName());
        }
        return parameterIndex;
    }

    public Long find(String name) throws IOException, SQLException, ConfigurationException {
        return (Long) readValue(getCheckClientBrowserExistsQuery(), name);
    }

    @Override
    protected String getCreateQuery() throws IOException {
        return getCreateBotVendorQuery();
    }
}
