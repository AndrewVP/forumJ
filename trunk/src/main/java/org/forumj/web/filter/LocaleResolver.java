/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.web.filter;
import static org.forumj.common.FJServletName.*;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import org.apache.commons.configuration.*;
import org.forumj.common.FJConfiguration;
import org.forumj.tool.LocaleString;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebFilter(servletNames={INDEX, VIEW_THREAD, LOGIN, NEW_THREAD, ADD_POST, ADD_THREAD, NEW_QUESTION, ADD_QUESTION, SETTINGS, REGISTRATION})
public class LocaleResolver implements Filter {

   /**
    * {@inheritDoc}
    */
   @Override
   public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest request = (HttpServletRequest) req;
      HttpSession session = request.getSession(true);
      String lang = request.getParameter("lang");
      if (lang == null){
         if(session.getAttribute("locale") == null){
            try {
               lang = FJConfiguration.getConfig().getString("lang.default");
               LocaleString locale = new LocaleString(lang, null, lang);
               session.setAttribute("locale", locale);
            } catch (ConfigurationException e) {
               e.printStackTrace();
            }
         }else{
            LocaleString locale = new LocaleString(lang, null, lang);
            session.setAttribute("locale", locale);
         }
      }
      chain.doFilter(req, resp);
   }
   

   @Override
   public void destroy() {}


   @Override
   public void init(FilterConfig filterConfig) throws ServletException {}
}
