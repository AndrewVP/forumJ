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
package org.forumj.network.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.forumj.tool.Diletant.errorOut;
import static org.forumj.tool.FJServletTools.cache;
import static org.forumj.tool.FJServletTools.logo;
import static org.forumj.web.servlet.tool.FJServletTools.loadCSS;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class RootController {

   public void doGet(HttpServletRequest request, HttpServletResponse response, String webapp) throws ServletException, IOException {
      try{
          String resource = request.getRequestURI();
          int xx = 0;
      } catch (Throwable e) {
      }
   }

}
