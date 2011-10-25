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
package org.forumj.db.dao;

import java.sql.*;

import javax.sql.DataSource;

import org.apache.commons.configuration.*;
import org.apache.commons.dbcp.*;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.forumj.email.EMail;
import org.forumj.exception.DBException;


/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJDao {

   /**
    * E-mail класс для отправки сообщений об ошибах
    *
    * @var Email
    */
   private EMail mail = new EMail();

   public static DataSource dataSource = null;

   protected static Configuration config = null;
   
   public static Connection getConnection() throws SQLException, ConfigurationException{
      if (dataSource == null){
         synchronized (FJDao.class) {
            if (config == null){
               config = new PropertiesConfiguration("fj.properties");
            }
            if (dataSource == null){
               ObjectPool connectionPool = new GenericObjectPool(null);
               String connectURI = config.getString("jdbc.url");
               String userName = config.getString("jdbc.user.name");
               String userPassword = config.getString("jdbc.user.password");
               String driver = config.getString("jdbc.driver");
               if (driver != null){
                  try {
                     Class.forName(driver);
                  } catch (ClassNotFoundException e) {
                     e.printStackTrace();
                  }
               }
               ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(connectURI, userName, userPassword);
               PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory,connectionPool,null,null,false,true);
               dataSource = new PoolingDataSource(connectionPool); 
            }
         }
      }
      return dataSource.getConnection();
   }
   
   /**
    * Вызывается при возникновении ошибки БД
    *
    * @param DataBaseException $exception
    */
   protected void onDatabaseError(DBException exception){
      mail.sendInvalidQueryMail(999, 999, exception.getMessage(), "tema");
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
         if (!error){
            conn.commit();
         }else{
            conn.rollback();
         }
         conn.setAutoCommit(true);
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
