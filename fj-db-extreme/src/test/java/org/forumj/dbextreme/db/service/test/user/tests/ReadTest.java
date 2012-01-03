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

import static org.junit.Assert.*;
import static org.forumj.dbextreme.db.dao.FJDao.*;
import java.io.IOException;
import java.sql.*;
import java.util.Date;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.UserService;
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

   private static String name = "";
   private static String fam = "";
   private static String sex = "M";
   private static Date bith = new Date(); 
   private static String pass2 = "";
   private static boolean smail = true;
   private static boolean sname = true;
   private static String city = "";
   private static boolean scity = true;
   private static String country = "";
   private static boolean scountry = true;
   private static boolean ssex = true;
   private static String icq = "";
   private static boolean sicq = true;
   private static boolean sbith = true;
   private static int lang = 1;
   private static boolean h_ip = true;
   private static int view_def = 1;
   private static int pp_def = 1;
   private static int pt_def = 1;
   private static String avatar = "";
   private static boolean s_avatar = true;
   private static boolean ok_avatar = true;
   private static boolean v_avatars = true;
   private static int fd_timezone = 1;
   private static String footer = "";
   private static boolean ban = true;
   private static int activate_code = 1;
   private static boolean is_active = true;
   private static long userId = 9999999999l;

   private static IUser user = null;

   @BeforeClass
   public static void prepare() throws SQLException, ConfigurationException, IOException{
      String query = "INSERT INTO users (nick, pass, mail, name, fam, sex, bith, pass2, smail, sname, city, scity, country, scountry, ssex, icq, sicq, sbith, lang, h_ip, view_def, pp_def, pt_def, avatar, s_avatar, ok_avatar, v_avatars, fd_timezone, footer, ban, activate_code, is_active) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      Connection conn = getConnection();
      PreparedStatement st = conn.prepareStatement(query, new String[]{"id"});
      st.setString(1, nick);
      st.setString(2, pass);
      st.setString(3, mail);
      st.setString(4, name);
      st.setString(5, fam);
      st.setString(6, sex);
      st.setDate(7, new java.sql.Date(bith.getTime()));
      st.setString(8, pass2);
      st.setInt(9, smail ? 1 : 0);
      st.setInt(10, sname ? 1 : 0);
      st.setString(11, city);
      st.setInt(12, scity ? 1 : 0);
      st.setString(13, country);
      st.setInt(14, scountry ? 1 : 0);
      st.setInt(15, ssex ? 1 : 0);
      st.setString(16, icq);
      st.setInt(17, sicq ? 1 : 0);
      st.setInt(18, sbith ? 1 : 0);
      st.setInt(19, lang);
      st.setInt(20, h_ip ? 1 : 0);
      st.setInt(21, view_def);
      st.setInt(22, pp_def);
      st.setInt(23, pt_def);
      st.setString(24, avatar);
      st.setInt(25, s_avatar ? 1 : 0);
      st.setInt(26, ok_avatar ? 1 : 0);
      st.setInt(27, v_avatars ? 1 : 0);
      st.setInt(28, fd_timezone);
      st.setString(29, footer);
      st.setInt(30, ban ? 1 : 0);
      st.setInt(31, activate_code);
      st.setInt(32, is_active ? 1 : 0);
      st.executeUpdate();
      ResultSet idRs = st.getGeneratedKeys();
      if (idRs.next()){
         userId = idRs.getLong(1);
      }
      UserService service = new UserServiceImpl();
      user = service.readUser(userId);
   }

   public void loadTest(){
      assertNotNull("user is not loaded", user);
   }
   
   @Test
   public void idTest(){
      if (user != null){
         assertEquals("user id is not loaded", userId, user.getId().longValue());
      }
   }
   
   @Test
   public void nameTest(){
      if (user != null){
         assertEquals("user name is not loaded", name, user.getName());
      }
   }
   
   @Test
   public void famTest(){
      if (user != null){
         assertEquals("user fam is not loaded", fam, user.getFam());
      }
   }
   
   @Test
   public void sexTest(){
      if (user != null){
         assertEquals("user sex is not loaded", sex, user.getSex());
      }
   }
}
