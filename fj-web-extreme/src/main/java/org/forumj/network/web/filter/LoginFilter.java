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
package org.forumj.network.web.filter;

import static org.forumj.tool.Diletant.errorOut;
import static org.forumj.web.servlet.tool.FJServletTools.*;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.QuotedPrintableCodec;
import org.forumj.checkip.CheckIp;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class LoginFilter{

   public void doFilter(ServletRequest req, ServletResponse resp, String webapp, String userURI, String exitControllerName, FilterChain chain) throws Exception{
      boolean ok = true;
      HttpServletRequest request = (HttpServletRequest) req;
      HttpServletResponse response = (HttpServletResponse) resp;
      try {
         IUser user = (IUser) request.getSession(true).getAttribute("user");
         UserService userService = FJServiceHolder.getUserService();
         if (user == null || !user.isLogined()){
            QuotedPrintableCodec codec = new QuotedPrintableCodec();
            Cookie[] cookies = request.getCookies();
            Cookie iduCookie = getCookie(cookies, "idu"); 
            Cookie pass2Cookie = getCookie(cookies, "pass2"); 
            if (pass2Cookie != null) {
               String pass2 = pass2Cookie.getValue();
               if (pass2 != null){
                  pass2 = codec.decode(pass2);
                  user = userService.read(Long.valueOf(iduCookie.getValue()), pass2, false);
                  if (user == null){
                     ok = false;
                  }else{
                     if (!user.getIsActive() || !user.isApproved()){
                        user = null;
                     }else{
                        request.getSession().setAttribute("user", user);               
                     }
                  }
               }else{
                  ok = false;
               }
            }
         }
         if (user == null){
            String iduParameter = request.getParameter("IDU");
            String pass1Parameter = request.getParameter("PS1");
            String pass2Parameter = request.getParameter("PS2");
            if (iduParameter != null && (pass1Parameter != null || pass2Parameter != null)){
               boolean firstPassword = pass1Parameter != null;
               user = userService.read(Long.valueOf(iduParameter), firstPassword ? pass1Parameter : pass2Parameter, firstPassword);
               if (user == null){
                  ok = false;
               }else{
                  if (!user.getIsActive() || !user.isApproved()){
                     user = null;
                  }else{
                     request.getSession().setAttribute("user", user);               
                  }
               }
            }
         }
         if (user == null){
            user = userService.readUser(0l);
            request.getSession().setAttribute("user", user);
         }
         if (ok){
            if (user != null && user.isLogined()){
               String ip = request.getRemoteAddr();
               if (ip != null && CheckIp.isSpammerIp(ip)){
                  String path = webapp.isEmpty() ? "/" : new StringBuilder("/").append(webapp).append("/").toString();
                  setcookie(response, "idu", "", 0, path, request.getServerName());
                  setcookie(response, "pass2", "", 0, path, request.getServerName());
                  user = userService.readUser(0l);
                  request.getSession().setAttribute("user", user);
               }
            }
            chain.doFilter(request, response, webapp, userURI);
         }else{
            String path = webapp.isEmpty() ? "/" : new StringBuilder("/").append(webapp).append("/").toString();
            setcookie(response, "idu", "", 0, path, request.getServerName());
            setcookie(response, "pass2", "", 0, path, request.getServerName());
            response.sendRedirect(new StringBuilder("/").append(userURI).append("/").append( exitControllerName).toString());
         }
      } catch (Throwable e) {
         e.printStackTrace();
         StringBuffer buffer = new StringBuffer();
         buffer.append(errorOut(e));
         response.setContentType("text/html; charset=UTF-8");
         PrintWriter writer = response.getWriter();
         String out = buffer.toString();
         writer.write(out);
      }
   }

}
