/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.network.web.controller.filter;
import static org.forumj.network.web.FJServletTools.errorOut;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.forumj.common.config.FJConfiguration;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.web.Locale;
import org.forumj.network.web.resources.LocaleString;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class LocaleResolver {

   public void doFilter(ServletRequest req, ServletResponse resp, String webapp, String userURI, FilterChain chain) throws Exception{
      HttpServletRequest request = (HttpServletRequest) req;
      HttpServletResponse response = (HttpServletResponse)resp;
      try {
         HttpSession session = request.getSession(true);
         String langParameter = request.getParameter("lang");
         LocaleString currentLocale = (LocaleString) session.getAttribute("locale");
         IUser user = (IUser) session.getAttribute("user");
         Locale defaultLocale = user != null ? user.getLanguge() : Locale.valueOfString(FJConfiguration.getConfig().getString("lang.default"));
         if (langParameter != null){
            Locale newLocaleName = Locale.valueOfString(langParameter);
            currentLocale = new LocaleString(newLocaleName, "messages", defaultLocale);
         }else{
            if(currentLocale == null) {
               currentLocale = new LocaleString(defaultLocale, "messages", defaultLocale);
            }
         }
         session.setAttribute("locale", currentLocale);
         chain.doFilter(request, response, webapp, userURI);
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
