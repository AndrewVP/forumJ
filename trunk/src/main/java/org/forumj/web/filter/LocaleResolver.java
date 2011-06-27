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

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import org.apache.commons.configuration.*;
import org.forumj.db.entity.User;
import org.forumj.tool.LocaleString;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebFilter(servletNames={"index", "tema", "auth", "mess"})
public class LocaleResolver implements Filter {

   protected static Configuration config = null;

   /**
    * {@inheritDoc}
    */
   @Override
   public void init(FilterConfig filterConfig) throws ServletException {
      try {
         config = new PropertiesConfiguration("fj.properties");
      } catch (ConfigurationException e) {
         e.printStackTrace();
      }
   }

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
               lang = getConfig().getString("lang.default");
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
   

   /**
    * {@inheritDoc}
    */
   @Override
   public void destroy() {
      // TODO Auto-generated method stub

   }
   
   private Configuration getConfig() throws ConfigurationException{
      if (config == null){
         config = new PropertiesConfiguration("fj.properties");
      }
      return config;
   }

}
