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
package ua.com.diletant.forum.db.entity;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class User {

   private Long id = null;
   
   private String nick = null;
   
   private String pass = null;
   
   private String pass2 = null;

   /**
    * @return the pass
    */
   public String getPass() {
      return pass;
   }

   /**
    * @param pass the pass to set
    */
   public void setPass(String pass) {
      this.pass = pass;
   }

   /**
    * @return the pass2
    */
   public String getPass2() {
      return pass2;
   }

   /**
    * @param pass2 the pass2 to set
    */
   public void setPass2(String pass2) {
      this.pass2 = pass2;
   }

   /**
    * @return the nick
    */
   public String getNick() {
      return nick;
   }

   /**
    * @param nick the nick to set
    */
   public void setNick(String nick) {
      this.nick = nick;
   }

   /**
    * @return the id
    */
   public Long getId() {
      return id;
   }

   /**
    * @param id the id to set
    */
   public void setId(Long id) {
      this.id = id;
   }
}
