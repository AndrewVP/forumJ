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

import static org.forumj.network.web.FJServletTools.setcookie;

import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.http.*;

import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class ExitFilter{

   public void doFilter(ServletRequest req, ServletResponse resp, String webapp, String userURI, String exitControllerName, boolean omitQuery, FilterChain chain) throws Exception{
      HttpServletRequest request = (HttpServletRequest) req;
      HttpServletResponse response = (HttpServletResponse)resp;
      Map<String, String[]> parameters = request.getParameterMap();
      IUser user = (IUser) request.getSession().getAttribute("user");
      if (parameters.containsKey("exit")){
         if (user != null && user.isLogined()){
            UserService userService = FJServiceHolder.getUserService();
            IUser guest = userService.readUser(0l);
            request.getSession().setAttribute("user", guest);
            String path = webapp.isEmpty() ? "/" : new StringBuilder("/").append(webapp).append("/").toString();
            setcookie(response, "idu", "", 0, path, request.getServerName());
            setcookie(response, "pass2", "", 0, path, request.getServerName());
         }
         StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(exitControllerName).append("/");
         if (!omitQuery){
            String query = parameters.entrySet().stream()
                    .filter(entry -> !entry.getKey().equals("exit"))
                    .map(entry -> new StringBuilder(entry.getKey()).append("=").append(entry.getValue()[0]).toString())
                    .collect(Collectors.joining("&"));
            if (!query.isEmpty()){
               url.append("?").append(query);
            }
         }
         response.sendRedirect(url.toString());
      }else{
         chain.doFilter(request, response, webapp, userURI);
      }
   }

}
