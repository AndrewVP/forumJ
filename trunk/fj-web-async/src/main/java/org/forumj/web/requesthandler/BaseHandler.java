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
package org.forumj.web.requesthandler;

import java.io.PrintWriter;
import java.util.*;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletResponse;

import org.forumj.common.exception.FJWebException;
import org.forumj.web.tool.ErrorResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public abstract class BaseHandler implements Handler{

    private List<PreHandler> preHandlers = new ArrayList<PreHandler>();
    private List<PostHandler> postHandlers = new ArrayList<PostHandler>();

    @Override
    public void handle(AsyncContext context) throws FJWebException {
        try{
            if (preHandle(context)){
                doHandle(context);
                postHandle(context);
            }
            context.complete();
        }catch (Exception e){
            handleException(e, context);
        }
    }

    protected abstract void doHandle(AsyncContext context) throws FJWebException ;

    protected void handleException(Throwable exception, AsyncContext context){
        try{
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(writer, new ErrorResponse(exception));
        }catch (Throwable e){
            // TODO Log!!
            e.printStackTrace();
        }
    }

    protected void addPreHandler(PreHandler handler){
        preHandlers.add(handler);
    }

    protected void addPostHandler(PostHandler handler){
        postHandlers.add(handler);
    }

    private boolean preHandle(AsyncContext context) throws FJWebException{
        for(PreHandler handler : preHandlers){
            if(!handler.handle(context)) return false;
        }
        return true;
    }

    private void postHandle(AsyncContext context) throws FJWebException{
        for(PostHandler handler : postHandlers){
            if (!handler.handle(context)) break;
        }
    }
}
