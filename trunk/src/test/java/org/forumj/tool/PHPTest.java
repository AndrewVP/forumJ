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
package org.forumj.tool;

import junit.framework.Assert;

import org.forumj.tool.PHP;
import org.junit.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class PHPTest {

   @Test
   public void str_replaceTest(){
      String search = "a";
      String replace = "b";
      String source = "aaccaadd";
      String expect = "bbccbbdd";
      String result = PHP.str_replace(search, replace, source);
      Assert.assertEquals(expect, result);
   }
   
   @Test
   @Ignore
   public void stripslashesTest(){
      String text = "\\a";
      String expect = "a";
      String result = PHP.stripslashes(text);
      Assert.assertEquals(expect, result);
   }
}
