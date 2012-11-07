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

import javax.servlet.*;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.common.exception.FJWebException;
import org.forumj.web.requesthandler.BaseHandler;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class MenuHandler extends BaseHandler{

    @Override
    protected void doHandle(AsyncContext context) throws FJWebException {
        try{
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            Component component = getComponent(request);
            request.setAttribute("component", component.getId());
            //TODO Доделать
            request.setAttribute("ua", "");
            request.setAttribute("ru", "");
            request.setAttribute("exit", "");
            response.setContentType("text/html; charset=UTF-8");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/menu.jsp");
            dispatcher.include(request, response);
        }catch (Throwable e){
            throw new FJWebException(e);
        }
    }
    
    private Component getComponent(HttpServletRequest request){
        String componentParameter = request.getParameter(FJRequestParameter.COMPONENT);
        if (componentParameter != null && !componentParameter.trim().isEmpty()){
            return Component.fromId(Integer.valueOf(componentParameter));
        }else{
            return Component.DEFAUL_COMPONENT;
        }
    }

}
