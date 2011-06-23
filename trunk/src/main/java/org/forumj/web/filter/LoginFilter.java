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
package org.forumj.web.filter;

import static org.forumj.web.servlet.tool.FJServletTools.*;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import org.forumj.db.dao.UserDao;
import org.forumj.db.entity.User;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebFilter(servletNames={"index", "tema", "auth"})
public class LoginFilter implements Filter {

   /**
    * {@inheritDoc}
    */
   @Override
   public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
      boolean ok = true;
      HttpServletRequest request = (HttpServletRequest) req;
      HttpServletResponse response = (HttpServletResponse) resp;
      User user = (User) request.getSession(true).getAttribute("user");
      Cookie[] cookies = request.getCookies();
      Cookie iduCookie = getCookie(cookies, "idu"); 
      Cookie userCookie = getCookie(cookies, "user"); 
      Cookie pass2Cookie = getCookie(cookies, "pass2"); 
      UserDao dao = new UserDao();
      if (user == null){
         if (userCookie != null){
            if (pass2Cookie == null) {
               goAwayStupidHackers(response, request.getContextPath());
               ok = false;
            }else{
               user = dao.loadUser(Long.valueOf(iduCookie.getValue()), pass2Cookie.getValue(), false);
               if (user == null){
                  goAwayStupidHackers(response, request.getContextPath());
                  ok = false;
               }else{
                  request.getSession().setAttribute("user", user);               
               }
            }
         }
      }
      if (user == null){
         request.getSession().setAttribute("user", dao.loadUser(0l));
      }
      if (ok){
         chain.doFilter(request, response);
      }
   }

   private void goAwayStupidHackers(HttpServletResponse response, String redirectLocation){
      setcookie(response, "user", "", 0, "/forum", "www.diletant.com.ua");
      setcookie(response, "idu", "", 0, "/forum", "www.diletant.com.ua");
      setcookie(response, "pass2", "", 0, "/forum", "www.diletant.com.ua");
      setcookie(response, "user", "", 0, "/forum", "diletant.com.ua");
      setcookie(response, "idu", "", 0, "/forum", "diletant.com.ua");
      setcookie(response, "pass2", "", 0, "/forum", "diletant.com.ua");
      response.setStatus(HttpServletResponse.SC_OK);
      response.setHeader("Location", redirectLocation);
   }
   
   /**
    * {@inheritDoc}
    */
   @Override
   public void init(FilterConfig filterConfig) throws ServletException {}

   /**
    * {@inheritDoc}
    */
   @Override
   public void destroy() {}

}
