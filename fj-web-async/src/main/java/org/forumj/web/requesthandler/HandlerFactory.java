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

import org.forumj.common.Command;
import org.forumj.common.exception.FJWebException;
import org.forumj.web.requesthandler.handler.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class HandlerFactory{

    public static Handler getHandler(Command command) throws FJWebException{
        switch(command){
        case GET_LOGO:
            return new LogoHandler();
        case GET_MAIN:
            return new MainHandler();
        case GET_MENU:
            return new MenuHandler();
        case LOGOUT:
            return new LogoutHandler();
        case GET_LOGIN:
            return new GetLoginHandler();
        case FORUM_INDEX:
            return new ForumIndexHandler();
        case FORUM_THREAD:
            return new ForumThreadHandler();
        default:
            throw new FJWebException("Unimplemented command parameter: " + command.getCommand());
        }
    }
}
