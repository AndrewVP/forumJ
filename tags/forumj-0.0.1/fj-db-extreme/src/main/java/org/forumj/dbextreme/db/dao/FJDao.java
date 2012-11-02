/*
 * Copyright Andrew V. Pogrebnyak
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.forumj.dbextreme.db.dao;

import java.sql.*;

import javax.sql.DataSource;

import org.apache.commons.configuration.*;
import org.apache.commons.dbcp.*;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.forumj.common.config.FJConfiguration;


/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJDao {

   public static DataSource dataSource = null;

   public static Connection getConnection() throws SQLException, ConfigurationException{
      if (dataSource == null){
         synchronized (FJDao.class) {
            Configuration config = FJConfiguration.getConfig();
            if (dataSource == null){
               Integer maxActive = config.getInteger("dbcp.maxActive", 10);
               ObjectPool connectionPool = new GenericObjectPool(null, maxActive);
               String connectURI = config.getString("jdbc.url");
               String userName = config.getString("jdbc.user.name");
               String userPassword = config.getString("jdbc.user.password");
               String driver = config.getString("jdbc.driver");
               String validationQuery = config.getString("dbcp.validationQuery");
               if (driver != null){
                  try {
                     Class.forName(driver);
                  } catch (ClassNotFoundException e) {
                     e.printStackTrace();
                  }
               }
               ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(connectURI, userName, userPassword);
               new PoolableConnectionFactory(connectionFactory,connectionPool,null,validationQuery,false,true);
               dataSource = new PoolingDataSource(connectionPool); 
            }
         }
      }
      return dataSource.getConnection();
   }
   
   protected void readFinally(Connection conn, Statement st){
      try {
         if (conn != null){
            conn.close();
         }
         if (st != null){
            st.close();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   protected void writeFinally(Connection conn, Statement st, boolean error){
      try {
         if (conn != null){
            if (!error){
               conn.commit();
            }else{
               conn.rollback();
            }
            conn.setAutoCommit(true);
         }
         if (st != null){
            st.close();
         }
         if (conn != null){
            conn.close();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}
