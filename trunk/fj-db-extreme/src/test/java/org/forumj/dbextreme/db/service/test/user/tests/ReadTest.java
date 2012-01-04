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

import static org.forumj.dbextreme.db.dao.FJDao.getConnection;
import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.UserService;
import org.forumj.dbextreme.db.entity.User;
import org.forumj.dbextreme.db.service.UserServiceImpl;
import org.junit.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class ReadTest {

   private static IUser testUser1 = new User();
   private static IUser testUser2 = new User();
   private static IUser testUser3 = new User();
   private UserService service = new UserServiceImpl();

   @BeforeClass
   public static void prepare() throws SQLException, ConfigurationException{
      prepareUser1();
      prepareUser2();
      prepareUser3();
      String query = "INSERT INTO users (nick, pass, mail, name, fam, sex, bith, pass2, smail, sname, city, scity, country, scountry, ssex, icq, sicq, sbith, lang, h_ip, view_def, pp_def, pt_def, avatar, s_avatar, ok_avatar, v_avatars, fd_timezone, footer, ban, activate_code, is_active) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      Connection conn = getConnection();
      PreparedStatement st = conn.prepareStatement(query, new String[]{"id"});
      prepareStatement(st, testUser1);
      executeSql(st, testUser1);
      prepareStatement(st, testUser2);
      executeSql(st, testUser2);
      prepareStatement(st, testUser3);
      executeSql(st, testUser3);
   }
   
   private static void prepareStatement(PreparedStatement st, IUser user) throws SQLException{
      st.setString(1, user.getNick());
      st.setString(2, user.getPass());
      st.setString(3, user.getEmail());
      st.setString(4, user.getName());
      st.setString(5, user.getFam());
      st.setString(6, user.getSex());
      st.setDate(7, user.getBith());
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
   }
   
   private static void executeSql(PreparedStatement st, IUser user) throws SQLException{
      st.executeUpdate();
      ResultSet idRs = st.getGeneratedKeys();
      if (idRs.next()){
         user.setId(idRs.getLong(1));
      }
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

   private static void prepareUser2(){
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
   
   private static void prepareUser3(){
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
   
   @Test
   public void readByIdTest() throws ConfigurationException, SQLException, IOException{
      IUser user = service.readUser(testUser1.getId());
      if (user != null){
         testEqualsUsers(testUser1, user);
      }else{
         fail("User1 is not loaded");
      }
      user = service.readUser(testUser2.getId());
      if (user != null){
         testEqualsUsers(testUser2, user);
      }else{
         fail("User2 is not loaded");
      }
   }
   
   @Test
   public void readByEmailTest() throws ConfigurationException, SQLException, IOException{
      IUser user = service.readUserByMail(testUser1.getEmail());
      if (user != null){
         testEqualsUsers(testUser1, user);
      }else{
         fail("User1 is not loaded");
      }
      user = service.readUserByMail(testUser2.getEmail());
      if (user != null){
         testEqualsUsers(testUser2, user);
      }else{
         fail("User2 is not loaded");
      }
   }
   
   @Test
   public void readByNickTest() throws ConfigurationException, SQLException, IOException{
      IUser user = service.read(testUser1.getNick());
      if (user != null){
         testEqualsUsers(testUser1, user);
      }else{
         fail("User1 is not loaded");
      }
      user = service.read(testUser2.getNick());
      if (user != null){
         testEqualsUsers(testUser2, user);
      }else{
         fail("User2 is not loaded");
      }
   }

   @Test
   public void readByNickPassTest() throws ConfigurationException, SQLException, IOException{
      IUser user = service.read(testUser1.getNick(), testUser1.getPass(), true);
      if (user != null){
         testEqualsUsers(testUser1, user);
      }else{
         fail("User1 is not loaded");
      }
      user = service.read(testUser2.getNick(), testUser2.getPass(), true);
      if (user != null){
         testEqualsUsers(testUser2, user);
      }else{
         fail("User2 is not loaded");
      }
   }
   
   @Test
   public void readByNickPass2Test() throws ConfigurationException, SQLException, IOException{
      IUser user = service.read(testUser1.getNick(), testUser1.getPass2(), false);
      if (user != null){
         testEqualsUsers(testUser1, user);
      }else{
         fail("User1 is not loaded");
      }
      user = service.read(testUser2.getNick(), testUser2.getPass2(), false);
      if (user != null){
         testEqualsUsers(testUser2, user);
      }else{
         fail("User2 is not loaded");
      }
   }
   
   @Test
   public void readByIdPassTest() throws ConfigurationException, SQLException, IOException{
      IUser user = service.read(testUser1.getId(), testUser1.getPass(), true);
      if (user != null){
         testEqualsUsers(testUser1, user);
      }else{
         fail("User1 is not loaded");
      }
      user = service.read(testUser2.getId(), testUser2.getPass(), true);
      if (user != null){
         testEqualsUsers(testUser2, user);
      }else{
         fail("User2 is not loaded");
      }
   }
   
   @Test
   public void readByIdPass2Test() throws ConfigurationException, SQLException, IOException{
      IUser user = service.read(testUser1.getId(), testUser1.getPass2(), false);
      if (user != null){
         testEqualsUsers(testUser1, user);
      }else{
         fail("User1 is not loaded");
      }
      user = service.read(testUser2.getId(), testUser2.getPass2(), false);
      if (user != null){
         testEqualsUsers(testUser2, user);
      }else{
         fail("User2 is not loaded");
      }
   }
   
   private void testEqualsUsers(IUser expected, IUser user){
      //TODO FixMe!
      user.setReg(null);
      //TODO FixMe!
      user.setBith(expected.getBith());
      assertEquals(expected, user);
   }
}
