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

   public User loadUser(String nick, String password, Boolean firstPassword){
      String query="SELECT * FROM users WHERE nick='" + nick + "' AND pass='" + password + "'";
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
            result.setPass(rs.getString("pass"));
            result.setPass2(rs.getString("pass2"));
            result.setPp(rs.getInt("pp_def"));
            result.setPt(rs.getInt("pt_def"));
            result.setView(rs.getInt("view_def"));
            result.setTimeZone(rs.getInt("fd_timezone"));
            result.setVavatars(rs.getInt("v_avatars") > 0);
            result.setBan(rs.getInt("ban"));
            result.setAvatar(rs.getString("avatar"));
            result.setOk_avatar(rs.getInt("ok_avatar") > 0);
            result.setS_avatar(rs.getInt("s_avatar") > 0);
            result.setName(rs.getString("name"));
            result.setFam(rs.getString("fam"));
            result.setSex(rs.getByte("sex"));
            result.setBith(rs.getDate("bith"));
            result.setReg(rs.getDate("reg"));
            result.setShowMail(rs.getBoolean("smail"));
            result.setShowName(rs.getBoolean("sname"));
            result.setCity(rs.getString("city"));
            result.setShowCity(rs.getBoolean("scity"));
            result.setCountry(rs.getString("country"));
            result.setShowCountry(rs.getBoolean("scountry"));
            result.setShowSex(rs.getBoolean("ssex"));
            result.setShowBithday(rs.getBoolean("sbith"));
            result.setIcq(rs.getString("icq"));
            result.setShowIcq(rs.getBoolean("sicq"));
            result.setHideIp(rs.getBoolean("h_ip"));
            result.setLanguge(rs.getInt("lang"));
            result.setFooter(rs.getString("footer"));
            result.setActivateCode(rs.getInt("activate_code"));
            result.setIsActive(rs.getInt("is_active")>1);
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

   public void save(User user) {
      throw new IllegalAccessError("Unimplimented!");
   }
}
