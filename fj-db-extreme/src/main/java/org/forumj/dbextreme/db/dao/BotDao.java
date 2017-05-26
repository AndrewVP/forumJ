package org.forumj.dbextreme.db.dao;

import org.forumj.common.db.entity.Bot;
import org.forumj.common.db.entity.Entity;
import org.forumj.dbextreme.db.entity.BotImpl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.getCreateBotQuery;

/**
 * Created by Andrew on 13/05/2017.
 */
public class BotDao extends BaseDao {

    public Bot getObject(){
        return new BotImpl();
    }

    @Override
    protected int prepareStatementForUpdate(Entity entity, PreparedStatement st) throws SQLException {
        int parameterIndex = 0;
        if (entity instanceof Bot){
            Bot bot = (Bot) entity;
            st.setString(++parameterIndex, bot.getName());
            st.setInt(++parameterIndex, (int)(long) bot.getBotVendor().getId());
        }
        return parameterIndex;
    }

    @Override
    protected String getCreateQuery() throws IOException {
        return getCreateBotQuery();
    }
}
