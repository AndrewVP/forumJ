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
import static org.forumj.common.FJServletName.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import org.apache.commons.codec.*;
import org.apache.commons.codec.net.QuotedPrintableCodec;
import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.IUser;
import org.forumj.db.dao.FJUserDao;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebFilter(servletNames={INDEX, VIEW_THREAD, LOGIN, NEW_THREAD, NEW_QUESTION, SETTINGS, REGISTRATION})
public class LoginFilter implements Filter {

   /**
    * {@inheritDoc}
    */
   @Override
   public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
      boolean ok = true;
      HttpServletRequest request = (HttpServletRequest) req;
      HttpServletResponse response = (HttpServletResponse) resp;
      IUser user = (IUser) request.getSession(true).getAttribute("user");
      FJUserDao dao = new FJUserDao();
      QuotedPrintableCodec codec = new QuotedPrintableCodec();
      try {
         if (user == null || !user.isLogined()){
            Cookie[] cookies = request.getCookies();
            Cookie iduCookie = getCookie(cookies, "idu"); 
            Cookie pass2Cookie = getCookie(cookies, "pass2"); 
            if (pass2Cookie != null) {
               String pass2 = pass2Cookie.getValue();
               if (pass2 != null){
                  pass2 = codec.decode(pass2);
                  user = dao.read(Long.valueOf(iduCookie.getValue()), pass2Cookie.getValue(), false);
                  if (user == null){
                     ok = false;
                  }else{
                     request.getSession().setAttribute("user", user);               
                  }
               }else{
                  ok = false;
               }
            }
         }
         if (user == null){
            request.getSession().setAttribute("user", dao.read(0l));
         }
         if (ok){
            chain.doFilter(request, response);
         }else{
            goAwayStupidHackers(response, request.getContextPath() + "/", request);
         }
      } catch (EncoderException e) {
         e.printStackTrace();
      } catch (DecoderException e) {
         e.printStackTrace();
      } catch (NumberFormatException e) {
         e.printStackTrace();
      } catch (ConfigurationException e) {
         e.printStackTrace();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   private void goAwayStupidHackers(HttpServletResponse response, String redirectLocation, HttpServletRequest request) throws IOException, EncoderException{
      setcookie(response, "idu", "", 0, request.getContextPath(), request.getServerName());
      setcookie(response, "pass2", "", 0, request.getContextPath(), request.getServerName());
      response.sendRedirect(redirectLocation);
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
