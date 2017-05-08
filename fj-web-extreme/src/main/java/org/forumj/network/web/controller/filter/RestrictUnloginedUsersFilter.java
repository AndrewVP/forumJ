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
package org.forumj.network.web.controller.filter;

import javax.servlet.*;
import javax.servlet.http.*;

import org.forumj.common.db.entity.IUser;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class RestrictUnloginedUsersFilter{

   public void doFilter(ServletRequest req, ServletResponse resp, String webapp, String userURI, String exitControllerName, FilterChain chain) throws Exception{
      HttpServletRequest request = (HttpServletRequest) req;
      HttpServletResponse response = (HttpServletResponse) resp;
      HttpSession session = request.getSession(true);
      IUser user = (IUser) session.getAttribute("user");
      if (user == null || !user.isLogined()){
         response.sendRedirect("/" + userURI + "/" + exitControllerName);
      }else{
         chain.doFilter(request, response, webapp, userURI);
      }
   }
}
