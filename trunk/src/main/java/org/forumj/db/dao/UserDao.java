package org.forumj.db.dao;

import java.sql.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.db.entity.User;
import org.forumj.exception.DBException;


public class UserDao extends FJDao {

   public User loadUser(Long userId, String password, boolean firstPassword){
      String query="SELECT * FROM users WHERE id=" + userId.toString();
      String add = "";
      if(firstPassword){
         add = " AND pass='" + password + "'";
      }else{
         add = " AND pass2='" + password + "'";
      }
      return loadUser(query + add, firstPassword);
   }
   
   public User loadUser(Long userId){
      String query="SELECT * FROM users WHERE id=" + userId.toString();
      return loadUser(query, null);
   }
   
   private User loadUser(String sql, Boolean firstPassword){
      User result = null;
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql);
         if (rs.next()){
            result = new User();
            result.setId(rs.getLong("id"));
            result.setNick(rs.getString("nick"));
            if (firstPassword != null){
               if (firstPassword){
                  result.setPass(rs.getString("pass"));
               }else{
                  result.setPass2(rs.getString("pass2"));
               }
            }
            result.setPp(rs.getInt("pp_def"));
            result.setPt(rs.getInt("pt_def"));
            result.setView(rs.getInt("view_def"));
         }
      } catch (ConfigurationException e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      } catch (SQLException e) {
         DBException ex = new DBException(e);
         onDatabaseError(ex);
         e.printStackTrace();
         throw new RuntimeException(e);
      }finally{
         try {
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
      return result;
   }
}
