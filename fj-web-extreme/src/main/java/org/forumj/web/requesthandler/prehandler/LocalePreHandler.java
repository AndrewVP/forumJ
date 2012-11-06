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

import javax.servlet.AsyncContext;
import javax.servlet.http.*;

import org.forumj.common.config.FJConfiguration;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.exception.FJWebException;
import org.forumj.common.web.Locale;
import org.forumj.tool.LocaleString;
import org.forumj.web.requesthandler.PreHandler;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class LocalePreHandler implements PreHandler{

    @Override
    public boolean handle(AsyncContext context) throws FJWebException {
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        try {
           HttpSession session = request.getSession(true);
           String lang = request.getParameter("lang");
           IUser user = (IUser) session.getAttribute("user");
           Locale defaultLocaleName = user != null ? user.getLanguge() : Locale.valueOfString(FJConfiguration.getConfig().getString("lang.default"));
           Locale localeName = null;
           if (lang != null){
              localeName = Locale.valueOfString(lang);
           }else{
              localeName = defaultLocaleName;
           }
           LocaleString locale = (LocaleString) session.getAttribute("locale"); 
           if(locale == null){
              locale = new LocaleString(localeName, "messages", defaultLocaleName);
              session.setAttribute("locale", locale);
           }else if (lang != null && locale.getLanguage() != localeName){
              locale = new LocaleString(localeName, "messages", defaultLocaleName);
              session.setAttribute("locale", locale);
           }
           return true;
        } catch (Throwable e) {
            throw new FJWebException(e);
        }
    }

}
