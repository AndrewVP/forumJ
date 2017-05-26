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
package org.forumj.dbextreme.db.service.test.voice.tests;

import static org.forumj.dbextreme.db.dao.BaseDao.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.*;
import org.forumj.common.db.service.*;
import org.junit.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class ReadVoiceTest {

   private static VoiceService service = FJServiceHolder.getVoiceService();
   private static IFJVoice testVoice1 = service.getVoiceObject();
   private static IFJVoice testVoice2 = service.getVoiceObject();
   private static IFJVoice testVoice3 = service.getVoiceObject();
   private static String query = "INSERT INTO voice (head, node, user) VALUES (?, ?, ?)";
   
   @BeforeClass
   public static void prepare() throws SQLException, ConfigurationException{
      prepareVoice1();
      prepareVoice2();
      prepareVoice3();
      Connection conn = getConnection();
      PreparedStatement st = conn.prepareStatement(query, new String[]{"id"});
      prepareStatement(st, testVoice1);
      executeSql(st, testVoice1);
      prepareStatement(st, testVoice2);
      executeSql(st, testVoice2);
      prepareStatement(st, testVoice3);
      executeSql(st, testVoice3);
      conn.close();
   }

   @Test
   public void readTest1() throws ConfigurationException, SQLException, IOException{
      IFJVoice voice = service.read(testVoice1.getThreadId(), testVoice1.getUserId());
      assertEquals(testVoice1, voice);
   }
   
   @Test
   public void readTest2() throws ConfigurationException, SQLException, IOException{
      IFJVoice voice = service.read(testVoice2.getThreadId(), testVoice2.getUserId());
      assertEquals(testVoice2, voice);
   }
   
   @Test
   public void readTest3() throws ConfigurationException, SQLException, IOException{
      IFJVoice voice = service.read(testVoice3.getThreadId(), testVoice3.getUserId());
      assertEquals(testVoice3, voice);
   }
   
   @Test
   public void isUserVotedTest1() throws ConfigurationException, SQLException, IOException{
      assertTrue(service.isUserVoted(testVoice3.getThreadId(), testVoice3.getUserId()));
   }
   
   @Test
   public void isUserVotedTest2() throws ConfigurationException, SQLException, IOException{
      assertFalse(service.isUserVoted(testVoice1.getThreadId(), testVoice2.getUserId()));
   }
   
   @Test
   public void isUserVotedTest3() throws ConfigurationException, SQLException, IOException{
      assertFalse(service.isUserVoted(testVoice1.getThreadId(), testVoice3.getUserId()));
   }
   
   
   
   
   private static void prepareVoice1(){
      testVoice1.setNodeId(1l);
      testVoice1.setThreadId(2l);
      testVoice1.setUserId(3l);
   }

   private static void prepareVoice2(){
      testVoice2.setNodeId(2l);
      testVoice2.setThreadId(2l);
      testVoice2.setUserId(2l);
   }
   
   private static void prepareVoice3(){
      testVoice3.setNodeId(1l);
      testVoice3.setThreadId(3l);
      testVoice3.setUserId(3l);
   }

   private static void prepareStatement(PreparedStatement st, IFJVoice voice) throws SQLException{
      st.setLong(1, voice.getThreadId());
      st.setLong(2, voice.getNodeId());
      st.setLong(3, voice.getUserId());
   }
   
   private static void executeSql(PreparedStatement st, IFJVoice voice) throws SQLException{
      st.executeUpdate();
      ResultSet idRs = st.getGeneratedKeys();
      if (idRs.next()){
         voice.setId(idRs.getLong(1));
      }
   }
}
