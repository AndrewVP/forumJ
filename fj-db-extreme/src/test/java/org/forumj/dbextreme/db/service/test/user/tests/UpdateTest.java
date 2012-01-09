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
package org.forumj.dbextreme.db.service.test.user.tests;

import static org.forumj.dbextreme.db.dao.FJDao.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;
import org.forumj.dbextreme.db.entity.User;
import org.junit.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class UpdateTest {

   private static UserService service = FJServiceHolder.getUserService();
   private static IUser testUser1 = service.getUserObject();
   private static IUser testUser2 = service.getUserObject();
   private static IUser testUser3 = service.getUserObject();
   private static String query = "SELECT * FROM users WHERE id = ?";
   
   @Test
   public void updateUser2Test() throws ConfigurationException, SQLException, IOException{
      service.update(testUser2);
      IUser user = loadUser(testUser2.getId());
      testEqualsUsers(testUser2, user);
   }
   
   @Test
   public void updateUser3Test() throws ConfigurationException, SQLException, IOException{
      service.update(testUser3);
      IUser user = loadUser(testUser3.getId());
      testEqualsUsers(testUser3, user);
   }
   
   @BeforeClass
   public static void prepare() throws SQLException, ConfigurationException, IOException{
      prepareUser1();
      service.create(testUser1);
      prepareUser2(testUser1.getId());
      prepareUser3(testUser1.getId());
   }
   
   private static void prepareUser1(){
      testUser1.setNick("nickПользователь1");
      testUser1.setPass("pass1");
      testUser1.setEmail("mail");
      testUser1.setName("name");
      testUser1.setFam("fam");
      testUser1.setSex("M");
      testUser1.setBith(new Date(new java.util.Date().getTime()));
      testUser1.setPass2("pass2");
      testUser1.setShowMail(true);
      testUser1.setShowName(true);
      testUser1.setCity("city");
      testUser1.setShowCity(true);
      testUser1.setCountry("country");
      testUser1.setShowCountry(true);
      testUser1.setShowSex(true);
      testUser1.setIcq("icq");
      testUser1.setShowIcq(true);
      testUser1.setShowBithday(true);
      testUser1.setLanguge(1);
      testUser1.setHideIp(true);
      testUser1.setView(5);
      testUser1.setPp(66);
      testUser1.setPt(77);
      testUser1.setAvatar("avatar");
      testUser1.setShowAvatar(true);
      testUser1.setAvatarApproved(true);
      testUser1.setWantSeeAvatars(true);
      testUser1.setTimeZone(7);
      testUser1.setFooter("footer");
      testUser1.setBan(1);
      testUser1.setActivateCode(45567678);
      testUser1.setIsActive(true);
   }

   private static void prepareUser2(Long id){
      testUser2.setId(id);
      testUser2.setNick("nickПользователь2");
      testUser2.setPass("pass11112пароль");
      testUser2.setEmail("mail2");
      testUser2.setName("name");
      testUser2.setFam("fam");
      testUser2.setSex("M");
      testUser2.setBith(new Date(new java.util.Date().getTime()));
      testUser2.setPass2("paввsвs2ааааа");
      testUser2.setShowMail(false);
      testUser2.setShowName(false);
      testUser2.setCity("city");
      testUser2.setShowCity(false);
      testUser2.setCountry("country");
      testUser2.setShowCountry(false);
      testUser2.setShowSex(false);
      testUser2.setIcq("icq");
      testUser2.setShowIcq(false);
      testUser2.setShowBithday(false);
      testUser2.setLanguge(2);
      testUser2.setHideIp(false);
      testUser2.setView(6);
      testUser2.setPp(66);
      testUser2.setPt(77);
      testUser2.setAvatar("avatar");
      testUser2.setShowAvatar(false);
      testUser2.setAvatarApproved(false);
      testUser2.setWantSeeAvatars(false);
      testUser2.setTimeZone(7);
      testUser2.setFooter("footer");
      testUser2.setBan(0);
      testUser2.setActivateCode(45567678);
      testUser2.setIsActive(false);
   }
   
   private static void prepareUser3(Long id){
      testUser3.setId(id);
      testUser3.setNick("ыnicыыыkПоыыльызыыыоывыатыель");
      testUser3.setPass("pass1ывыавпм");
      testUser3.setEmail("mailыкуав");
      testUser3.setName("name");
      testUser3.setFam("fam");
      testUser3.setSex("M");
      testUser3.setBith(new Date(new java.util.Date().getTime()));
      testUser3.setPass2("paвпsррsр2р");
      testUser3.setShowMail(true);
      testUser3.setShowName(true);
      testUser3.setCity("city");
      testUser3.setShowCity(true);
      testUser3.setCountry("country");
      testUser3.setShowCountry(true);
      testUser3.setShowSex(true);
      testUser3.setIcq("icq");
      testUser3.setShowIcq(true);
      testUser3.setShowBithday(true);
      testUser3.setLanguge(1);
      testUser3.setHideIp(true);
      testUser3.setView(7);
      testUser3.setPp(66);
      testUser3.setPt(77);
      testUser3.setAvatar("avatar");
      testUser3.setShowAvatar(true);
      testUser3.setAvatarApproved(true);
      testUser3.setWantSeeAvatars(true);
      testUser3.setTimeZone(7);
      testUser3.setFooter("footer");
      testUser3.setBan(1);
      testUser3.setActivateCode(45567678);
      testUser3.setIsActive(true);
   }
   
   private IUser loadUser(Long userId) throws SQLException, ConfigurationException{
      IUser result = null;
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query) ;
         st.setLong(1, userId);
         ResultSet rs = st.executeQuery();
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
            result.setSex(rs.getString("sex"));
            result.setBith(rs.getDate("bith"));
            result.setReg(rs.getTimestamp("reg"));
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
            result.setIsActive(rs.getInt("is_active")>0);
            result.setEmail(rs.getString("mail"));
         }
      }finally{
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
      return result;
   }
   
   private void testEqualsUsers(IUser expected, IUser user){
      //TODO FixMe!
      user.setReg(null);
      //TODO FixMe!
      user.setBith(expected.getBith());
      assertEquals(expected, user);
   }
}
