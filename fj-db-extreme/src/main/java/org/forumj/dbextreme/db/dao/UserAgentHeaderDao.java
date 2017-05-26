package org.forumj.dbextreme.db.dao;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.Device;
import org.forumj.common.db.entity.Entity;
import org.forumj.common.db.entity.UserAgentHeader;
import org.forumj.dbextreme.db.entity.UserAgentHeaderImpl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.getCheckUserAgentHeaderExistsQuery;
import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.getCreateUserAgentHeaderQuery;
import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.getReadUserAgentHeaderByValueQuery;

/**
 * Created by Andrew on 13/05/2017.
 */
public class UserAgentHeaderDao extends BaseDao {

    public UserAgentHeader getObject(){
        return new UserAgentHeaderImpl();
    }

    public Long find(String value) throws IOException, SQLException, ConfigurationException {
        return (Long) readValue(getCheckUserAgentHeaderExistsQuery(), value);
    }

    public UserAgentHeader readByValue(String value) throws IOException, SQLException, ConfigurationException {
        return (UserAgentHeader) read(getReadUserAgentHeaderByValueQuery(), value);
    }

    @Override
    protected int prepareStatementForUpdate(Entity entity, PreparedStatement st) throws SQLException {
        int parameterIndex = 0;
        if (entity instanceof UserAgentHeader){
            UserAgentHeader userAgentHeader = (UserAgentHeader) entity;
            st.setString(++parameterIndex, userAgentHeader.getValue());
            st.setInt(++parameterIndex, userAgentHeader.getBotId());
            st.setInt(++parameterIndex, userAgentHeader.getOsId());
            st.setInt(++parameterIndex, userAgentHeader.getBrowserId());
            st.setInt(++parameterIndex, userAgentHeader.getDevice().getId());
        }
        return parameterIndex;
    }

    @Override
    protected Object readObject(ResultSet rs) throws SQLException {
        UserAgentHeader uas = new UserAgentHeaderImpl();
        uas.setId(rs.getLong("id"));
        uas.setDevice(Device.fromId(rs.getInt("deviceType")));
        uas.setOsId(rs.getInt("operationSystemId"));
        uas.setBrowserId(rs.getInt("browserId"));
        uas.setBotId(rs.getInt("botId"));
        return uas;
    }

    @Override
    protected String getCreateQuery() throws IOException {
        return getCreateUserAgentHeaderQuery();
    }
}
