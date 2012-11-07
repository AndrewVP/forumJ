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
package org.forumj.web.requesthandler.handler;

import static org.forumj.web.servlet.tool.FJServletTools.*;

import javax.servlet.AsyncContext;
import javax.servlet.http.*;

import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;
import org.forumj.common.exception.FJWebException;
import org.forumj.web.requesthandler.BaseHandler;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class LogoutHandler extends BaseHandler{

    @Override
    protected void doHandle(AsyncContext context) throws FJWebException {
        try{
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            IUser user = (IUser) request.getSession().getAttribute("user");
            if (user == null || user.isLogined()){
                UserService userService = FJServiceHolder.getUserService();
                request.getSession().setAttribute("user", userService.readUser(0l));
                setcookie(response, "idu", "", 0, request.getContextPath(), request.getServerName());
                setcookie(response, "pass2", "", 0, request.getContextPath(), request.getServerName());
            }
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (Throwable e){
            throw new FJWebException(e);
        }
    }

}
