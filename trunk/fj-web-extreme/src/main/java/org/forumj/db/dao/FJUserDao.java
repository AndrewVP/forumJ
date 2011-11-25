package org.forumj.db.dao;

import static org.forumj.db.dao.tool.QueryBuilder.*;

import java.io.IOException;
import java.sql.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.IUser;
import org.forumj.db.entity.*;


public class FJUserDao extends FJDao {

   public IUser read(Long userId, String password, boolean firstPassword) throws ConfigurationException, SQLException, IOException{
      IUser result = null;
      String query = null;
      if(firstPassword){
         query = getReadUserByFirstPasswordQuery();
      }else{
         query = getReadUserBySecondPasswordQuery();
      }
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query) ;
         st.setLong(1, userId);
         st.setString(2, password);
         ResultSet rs = st.executeQuery();
         result = loadUser(rs);
      }finally{
         readFinally(conn, st);
      }
      return result;
   }

   public IUser read(String nick, String password, Boolean firstPassword) throws ConfigurationException, SQLException, IOException{
      IUser result = null;
      String query = null;
      if(firstPassword){
         query = getReadUserByNickAndFirstPasswordQuery();
      }else{
         query = getReadUserByNickAndSecondPasswordQuery();
      }
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query) ;
         st.setString(1, nick);
         st.setString(2, password);
         ResultSet rs = st.executeQuery();
         result = loadUser(rs);
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
   
   public IUser read(Long userId) throws ConfigurationException, SQLException, IOException{
      IUser result = null;
      String query = getReadUserByIdQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query) ;
         st.setLong(1, userId);
         ResultSet rs = st.executeQuery();
         result = loadUser(rs);
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
   
   public IUser read(String nick) throws ConfigurationException, SQLException, IOException{
      IUser result = null;
      String query = getReadUserByNickQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query) ;
         st.setString(1, nick);
         ResultSet rs = st.executeQuery();
         result = loadUser(rs);
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
   
   public IUser readByMail(String mail) throws ConfigurationException, SQLException, IOException{
      IUser result = null;
      String query = getReadUserByMailQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query) ;
         st.setString(1, mail);
         ResultSet rs = st.executeQuery();
         result = loadUser(rs);
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
   
   private User loadUser(ResultSet rs) throws ConfigurationException, SQLException{
      User result = null;
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
         result.setWantSeeAvatars(rs.getInt("v_avatars") > 0);
         result.setBan(rs.getInt("ban"));
         result.setAvatar(rs.getString("avatar"));
         result.setAvatarApproved(rs.getInt("ok_avatar") > 0);
         result.setShowAvatar(rs.getInt("s_avatar") > 0);
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
         result.setEmail(rs.getString("mail"));
      }
      return result;
   }

   public void update(IUser user) throws IOException, ConfigurationException, SQLException {
      String query = getUpdateUserQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try{
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setString(1, user.getNick());
         st.setString(2, user.getPass());
         st.setString(3, user.getEmail());
         st.setString(4, user.getName());
         st.setString(5, user.getFam());
         st.setByte(6, user.getSex());
         java.sql.Date birhTime = null;
         if (user.getBith() != null){
            birhTime = new java.sql.Date(user.getBith().getTime());
         }
         st.setDate(7, birhTime);
         st.setString(8, user.getPass2());
         st.setInt(9, user.getShowMail() ? 1 : 0);
         st.setInt(10, user.getShowName() ? 1 : 0);
         st.setString(11, user.getCity());
         st.setInt(12, user.getShowCity() ? 1 : 0);
         st.setString(13, user.getCountry());
         st.setInt(14, user.getShowCountry() ? 1 : 0);
         st.setInt(15, user.getShowSex() ? 1 : 0);
         st.setString(16, user.getIcq());
         st.setInt(17, user.getShowIcq() ? 1 : 0);
         st.setInt(18, user.getShowBithday() ? 1 : 0);
         st.setInt(19, user.getLanguge());
         st.setInt(20, user.getHideIp() ? 1 : 0);
         st.setInt(21, user.getView());
         st.setInt(22, user.getPp());
         st.setInt(23, user.getPt());
         st.setString(24, user.getAvatar());
         st.setInt(25, user.getShowAvatar() ? 1 : 0);
         st.setInt(26, user.getAvatarApproved() ? 1 : 0);
         st.setInt(27, user.getWantSeeAvatars() ? 1 : 0);
         st.setInt(28, user.getTimeZone());
         st.setString(29, user.getFooter());
         st.setInt(30, user.getBan());
         st.setInt(31, user.getActivateCode());
         st.setInt(32, user.getIsActive() ? 1 : 0);
         st.setLong(33, user.getId());
         st.executeUpdate();
      }finally{
         readFinally(null, st);
      }
   }
   
   public void create(IUser user) throws SQLException, ConfigurationException, IOException{
      String query = getUpdateUserQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try{
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setString(1, user.getNick());
         st.setString(2, user.getPass());
         st.setString(3, user.getEmail());
         st.setString(4, user.getName());
         st.setString(5, user.getFam());
         st.setByte(6, user.getSex());
         java.sql.Date birhTime = null;
         if (user.getBith() != null){
            birhTime = new java.sql.Date(user.getBith().getTime());
         }
         st.setDate(7, birhTime);
         st.setString(8, user.getPass2());
         st.setInt(9, user.getShowMail() ? 1 : 0);
         st.setInt(10, user.getShowName() ? 1 : 0);
         st.setString(11, user.getCity());
         st.setInt(12, user.getShowCity() ? 1 : 0);
         st.setString(13, user.getCountry());
         st.setInt(14, user.getShowCountry() ? 1 : 0);
         st.setInt(15, user.getShowSex() ? 1 : 0);
         st.setString(16, user.getIcq());
         st.setInt(17, user.getShowIcq() ? 1 : 0);
         st.setInt(18, user.getShowBithday() ? 1 : 0);
         st.setInt(19, user.getLanguge());
         st.setInt(20, user.getHideIp() ? 1 : 0);
         st.setInt(21, user.getView());
         st.setInt(22, user.getPp());
         st.setInt(23, user.getPt());
         st.setString(24, user.getAvatar());
         st.setInt(25, user.getShowAvatar() ? 1 : 0);
         st.setInt(26, user.getAvatarApproved() ? 1 : 0);
         st.setInt(27, user.getWantSeeAvatars() ? 1 : 0);
         st.setInt(28, user.getTimeZone());
         st.setString(29, user.getFooter());
         st.setInt(30, user.getBan());
         st.setInt(31, user.getActivateCode());
         st.setInt(32, user.getIsActive() ? 1 : 0);
         st.executeUpdate();
      }finally{
         readFinally(null, st);
      }
   }
}
