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
package org.forumj.web.requesthandler.prehandler;

import static org.forumj.web.servlet.tool.FJServletTools.*;

import javax.servlet.AsyncContext;
import javax.servlet.http.*;

import org.apache.commons.codec.net.QuotedPrintableCodec;
import org.forumj.checkip.CheckIp;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;
import org.forumj.common.exception.FJWebException;
import org.forumj.web.requesthandler.PreHandler;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class LoginPreHandler implements PreHandler{

    @Override
    public boolean handle(AsyncContext context) throws FJWebException {
        boolean ok = true;
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
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
                       if (!user.getIsActive()){
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
                    if (!user.getIsActive()){
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
                    setcookie(response, "idu", "", 0, request.getContextPath(), request.getServerName());
                    setcookie(response, "pass2", "", 0, request.getContextPath(), request.getServerName());
                    user = userService.readUser(0l);
                    request.getSession().setAttribute("user", user);
                 }
              }
              return true;
           }else{
              return false;
           }
        } catch (Throwable e) {
            throw new FJWebException(e);
        }
    }

}
