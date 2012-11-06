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

import org.forumj.common.exception.FJWebException;
import org.forumj.common.web.Command;
import org.forumj.web.requesthandler.handler.LogoHandler;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class HandlerFactory{

    public static Handler getHandler(Command command) throws FJWebException{
        switch(command){
        case GET_LOGO:
            return new LogoHandler();
        default:
            throw new FJWebException("Uniplemented command parameter: " + command.getCommand());
        }
    }
}
