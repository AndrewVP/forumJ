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
package org.forumj.web.servlet.async;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;

import org.forumj.common.*;
import org.forumj.common.exception.FJWebException;
import org.forumj.web.requesthandler.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class MainThread implements Runnable{
    
    private AsyncContext context;
    
    public MainThread(AsyncContext context){
        super();
        this.context = context;
    }

    @Override
    public void run() {
        try{
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            Command command = getCommand(request);
            Handler handler = HandlerFactory.getHandler(command);
            handler.handle(context);
        }catch (Throwable e){
            handleException(e);
        }
    }

    private Command getCommand(HttpServletRequest request) throws FJWebException{
        String commandParameter = request.getParameter(FJRequestParameter.COMMAND);
        if (commandParameter != null && !commandParameter.trim().isEmpty()){
            return Command.valueOfString(commandParameter);
        }else{
            throw new FJWebException(FJRequestParameter.COMMAND + " parameter not found in request");
        }
    }
    
    private void handleException(Throwable exception){
        // TODO!!!!!!!!!
    }
}
