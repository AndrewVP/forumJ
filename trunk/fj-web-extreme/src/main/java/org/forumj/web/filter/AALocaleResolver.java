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
import static org.forumj.tool.Diletant.errorOut;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import org.forumj.common.config.FJConfiguration;
import org.forumj.tool.LocaleString;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebFilter(servletNames={INDEX, VIEW_THREAD, LOGIN, NEW_THREAD, ADD_POST, ADD_THREAD, NEW_QUESTION, ADD_QUESTION, SETTINGS, REGISTRATION})
public class AALocaleResolver implements Filter {

   /**
    * {@inheritDoc}
    */
   @Override
   public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest request = (HttpServletRequest) req;
      HttpServletResponse response = (HttpServletResponse)resp;
      try {
         HttpSession session = request.getSession(true);
         String lang = request.getParameter("lang");
         String defaultLanguage = FJConfiguration.getConfig().getString("lang.default"); 
         if (lang == null){
            lang = defaultLanguage; 
         }
         LocaleString locale = (LocaleString) session.getAttribute("locale"); 
         if(locale == null){
            locale = new LocaleString(lang, "messages", defaultLanguage);
            session.setAttribute("locale", locale);
         }else if (!locale.getLanguage().equalsIgnoreCase(lang)){
            locale = new LocaleString(lang, "messages", defaultLanguage);
            session.setAttribute("locale", locale);
         }
         chain.doFilter(req, resp);
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


   @Override
   public void destroy() {}


   @Override
   public void init(FilterConfig filterConfig) throws ServletException {}
}
