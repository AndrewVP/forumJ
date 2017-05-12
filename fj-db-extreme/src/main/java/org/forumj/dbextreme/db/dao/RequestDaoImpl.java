package org.forumj.dbextreme.db.dao;

import org.forumj.common.db.entity.Request;
import org.forumj.dbextreme.db.entity.RequestImpl;

import java.sql.*;

import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.*;

/**
 * Created by Andrew on 12/05/2017.
 */
public class RequestDaoImpl extends FJDao {

    public Request getObject() {
        return new RequestImpl();
    }


    public void create(Request request) throws Exception {
        String query = getCreateRequestQuery();
        PreparedStatement st = null;
        Connection conn = null;
        boolean error = true;
        try{
            conn = getConnection();
            conn.setAutoCommit(false);
            st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prepareStatmentForUpdate(request, st);
            int insertedRows = st.executeUpdate();
            if (insertedRows == 1){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    request.setId(rs.getLong(1));
                    error = false;
                }
            }
        }finally{
            writeFinally(conn, st, error);
        }
    }
    private int prepareStatmentForUpdate(Request request, PreparedStatement st) throws SQLException {
        int parameterIndex = 0;
        st.setLong(++parameterIndex, request.getMethod().getId());
        st.setLong(++parameterIndex, request.getUserId());
        st.setLong(++parameterIndex, request.getIp().getId());
        st.setString(++parameterIndex, request.getUrl());
        st.setLong(++parameterIndex, request.getTime());
        return parameterIndex;
    }

}
