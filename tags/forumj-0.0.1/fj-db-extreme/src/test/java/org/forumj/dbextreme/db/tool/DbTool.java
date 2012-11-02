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
package org.forumj.dbextreme.db.tool;

import java.io.*;
import java.sql.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.dbextreme.db.dao.FJDao;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class DbTool {

   public static boolean dbExist(){
      Connection connection = null;
      try {
         connection = FJDao.getConnection();
         if (connection != null){
            return true;
         }else{
            System.out.println("Database connection is not established.");
            return false;
         }
      } catch (Throwable e) {
         System.out.println("Database not found.");
         return false;
      }finally{
         if (connection !=null){
            try {
               connection .close();
            } catch (SQLException e) {}
         }
      }
   }
   
   public static boolean initDb(){
      try {
         execute("db/drop_tbls.sql");
         execute("db/create_tbls.sql");
         execute("db/init_db.sql");
         return true;
      } catch (ConfigurationException e) {
         e.printStackTrace();
         return false;
      } catch (IOException e) {
         e.printStackTrace();
         return false;
      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
   }

   public static void execute(String path) throws IOException, ConfigurationException, SQLException{
      ClassLoader classLoader = DbTool.class.getClassLoader();
      InputStream stream = classLoader.getResourceAsStream(path);
      BufferedReader br = new BufferedReader(new InputStreamReader(stream));
      StringBuffer result = new StringBuffer();
      Connection connection = null;
      Statement st = null;
      try {
         connection = FJDao.getConnection();
         while(br.ready()){
            String line = br.readLine().trim();
            if (line.startsWith("--")){
               continue;
            }
            if (line.endsWith(";")){
               result.append(line);
               st = connection.createStatement();
               String query = result.toString();
               st.executeUpdate(query);
               result = new StringBuffer();
               continue;
            }
            result.append(line + "\n");
         }
      } finally {
         if (br != null){
            br.close();
         }
         if (st != null){
            try {
               st.close();
            } catch (SQLException e) {}
         }
         if (connection != null){
            try {
               connection.close();
            } catch (SQLException e) {}
         }
      }
   }
   
}
