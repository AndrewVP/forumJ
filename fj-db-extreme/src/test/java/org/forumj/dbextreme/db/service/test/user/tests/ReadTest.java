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

   private static String nick = "testПользователь";

   private static String pass = "testPass";

   private static String mail = "testPass";

   private static String name = "name";
   private static String fam = "fam";
   private static String sex = "M";
   private static Date bith = new Date(new java.util.Date().getTime()); 
   private static String pass2 = "pass2";
   private static boolean smail = true;
   private static boolean sname = true;
   private static String city = "city";
   private static boolean scity = true;
   private static String country = "country";
   private static boolean scountry = true;
   private static boolean ssex = true;
   private static String icq = "icq";
   private static boolean sicq = true;
   private static boolean sbith = true;
   private static int lang = 1;
   private static boolean h_ip = true;
   private static int view_def = 1;
   private static int pp_def = 1;
   private static int pt_def = 1;
   private static String avatar = "avatar";
   private static boolean s_avatar = true;
   private static boolean ok_avatar = true;
   private static boolean v_avatars = true;
   private static int fd_timezone = 1;
   private static String footer = "footer";
   private static boolean ban = true;
   private static int activate_code = 1;
   private static boolean is_active = true;

   private static IUser testUser = new User();
   private UserService service = new UserServiceImpl();

   @BeforeClass
   public static void prepare() throws SQLException, ConfigurationException{
      String query = "INSERT INTO users (nick, pass, mail, name, fam, sex, bith, pass2, smail, sname, city, scity, country, scountry, ssex, icq, sicq, sbith, lang, h_ip, view_def, pp_def, pt_def, avatar, s_avatar, ok_avatar, v_avatars, fd_timezone, footer, ban, activate_code, is_active) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      Connection conn = getConnection();
      PreparedStatement st = conn.prepareStatement(query, new String[]{"id"});
      st.setString(1, nick);
      testUser.setNick(nick);
      st.setString(2, pass);
      testUser.setPass(pass);
      st.setString(3, mail);
      testUser.setEmail(mail);
      st.setString(4, name);
      testUser.setName(name);
      st.setString(5, fam);
      testUser.setFam(fam);
      st.setString(6, sex);
      testUser.setSex(sex);
      st.setDate(7, bith);
      testUser.setBith(bith);
      st.setString(8, pass2);
      testUser.setPass2(pass2);
      st.setInt(9, smail ? 1 : 0);
      testUser.setShowMail(smail);
      st.setInt(10, sname ? 1 : 0);
      testUser.setShowName(sname);
      st.setString(11, city);
      testUser.setCity(city);
      st.setInt(12, scity ? 1 : 0);
      testUser.setShowCity(scity);
      st.setString(13, country);
      testUser.setCountry(country);
      st.setInt(14, scountry ? 1 : 0);
      testUser.setShowCountry(scountry);
      st.setInt(15, ssex ? 1 : 0);
      testUser.setShowSex(ssex);
      st.setString(16, icq);
      testUser.setIcq(icq);
      st.setInt(17, sicq ? 1 : 0);
      testUser.setShowIcq(sicq);
      st.setInt(18, sbith ? 1 : 0);
      testUser.setShowBithday(sbith);
      st.setInt(19, lang);
      testUser.setLanguge(lang);
      st.setInt(20, h_ip ? 1 : 0);
      testUser.setHideIp(h_ip);
      st.setInt(21, view_def);
      testUser.setView(view_def);
      st.setInt(22, pp_def);
      testUser.setPp(pp_def);
      st.setInt(23, pt_def);
      testUser.setPt(pt_def);
      st.setString(24, avatar);
      testUser.setAvatar(avatar);
      st.setInt(25, s_avatar ? 1 : 0);
      testUser.setShowAvatar(s_avatar);
      st.setInt(26, ok_avatar ? 1 : 0);
      testUser.setAvatarApproved(ok_avatar);
      st.setInt(27, v_avatars ? 1 : 0);
      testUser.setWantSeeAvatars(v_avatars);
      st.setInt(28, fd_timezone);
      testUser.setTimeZone(fd_timezone);
      st.setString(29, footer);
      testUser.setFooter(footer);
      st.setInt(30, ban ? 1 : 0);
      testUser.setBan(ban ? 1 : 0);
      st.setInt(31, activate_code);
      testUser.setActivateCode(activate_code);
      st.setInt(32, is_active ? 1 : 0);
      testUser.setIsActive(is_active);
      st.executeUpdate();
      ResultSet idRs = st.getGeneratedKeys();
      if (idRs.next()){
         testUser.setId(idRs.getLong(1));
      }
   }

   @Test
   public void readByIdTest() throws ConfigurationException, SQLException, IOException{
      IUser user = service.readUser(testUser.getId());
      if (user != null){
         testEqualsUsers(user);
      }else{
         fail("User is not loaded");
      }
   }
   
   @Test
   public void readByEmailTest() throws ConfigurationException, SQLException, IOException{
      IUser user = service.readUserByMail(testUser.getEmail());
      if (user != null){
         testEqualsUsers(user);
      }else{
         fail("User is not loaded");
      }
   }
   
   @Test
   public void readByNickTest() throws ConfigurationException, SQLException, IOException{
      IUser user = service.read(testUser.getNick());
      if (user != null){
         testEqualsUsers(user);
      }else{
         fail("User is not loaded");
      }
   }

   @Test
   public void readByNickPassTest() throws ConfigurationException, SQLException, IOException{
      IUser user = service.read(testUser.getNick(), testUser.getPass(), true);
      if (user != null){
         testEqualsUsers(user);
      }else{
         fail("User is not loaded");
      }
   }
   
   @Test
   public void readByNickPass2Test() throws ConfigurationException, SQLException, IOException{
      IUser user = service.read(testUser.getNick(), testUser.getPass2(), false);
      if (user != null){
         testEqualsUsers(user);
      }else{
         fail("User is not loaded");
      }
   }
   
   @Test
   public void readByIdPassTest() throws ConfigurationException, SQLException, IOException{
      IUser user = service.read(testUser.getId(), testUser.getPass(), true);
      if (user != null){
         testEqualsUsers(user);
      }else{
         fail("User is not loaded");
      }
   }
   
   @Test
   public void readByIdPass2Test() throws ConfigurationException, SQLException, IOException{
      IUser user = service.read(testUser.getId(), testUser.getPass2(), false);
      if (user != null){
         testEqualsUsers(user);
      }else{
         fail("User is not loaded");
      }
   }
   
   private void testEqualsUsers(IUser user){
      //TODO FixMe!
      user.setReg(null);
      //TODO FixMe!
      user.setBith(testUser.getBith());
      assertEquals(testUser, user);
   }
}
