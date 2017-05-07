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
package org.forumj.network.web;

import java.util.*;

import javax.servlet.http.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class RequestWrapper extends HttpServletRequestWrapper{
   

   public final static String FAKE_NULL = "null";
   
   Map<String, String> parameters = new HashMap<>();

   /**
    * @param request
    */
   public RequestWrapper(HttpServletRequest request) {
      super(request);
   }

   public void addOrReplaceParameter(String name, String value){
      parameters.put(name, value);
   }

   @Override
   public String getParameter(String name) {
      String parameter = parameters.get(name);
      if (parameter == null){
         parameter = super.getParameter(name);
      }else if (parameter.equalsIgnoreCase(FAKE_NULL)){
         parameter = null;
      }
      return parameter;
   }
}
