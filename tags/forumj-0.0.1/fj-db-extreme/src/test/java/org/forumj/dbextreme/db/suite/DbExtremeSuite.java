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
package org.forumj.dbextreme.db.suite;

import org.forumj.dbextreme.db.tool.DbTool;
import org.junit.runners.Suite;
import org.junit.runners.model.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class DbExtremeSuite extends Suite {

   public DbExtremeSuite(Class<?> klass, RunnerBuilder builder) throws InitializationError{
      super(builder, klass, getTests(klass));
   }

   private static Class<?>[] getTests(Class<?> klass) throws InitializationError{
      SuiteClasses annotation = klass.getAnnotation(SuiteClasses.class);
      if (annotation == null){
         throw new InitializationError(String.format("class '%s' must have a SuiteClasses annotation", klass.getName()));
      }
      if (DbTool.dbExist() && DbTool.initDb()){
         return annotation.value();
      }else{
         return new Class<?>[] {};
      }
   }
}
