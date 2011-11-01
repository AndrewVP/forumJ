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
package org.forumj.web.filter;

import static org.forumj.web.servlet.tool.FJServletTools.setcookie;
import static org.forumj.common.FJServletName.*;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.configuration.ConfigurationException;
import org.forumj.db.dao.UserDao;
import org.forumj.db.entity.User;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebFilter(servletNames={INDEX, VIEW_THREAD, NEW_THREAD, NEW_QUESTION, SETTINGS})
public class ExitFilter implements Filter {

   /**
    * {@inheritDoc}
    */
   @Override
   public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest request = (HttpServletRequest) req;
      HttpServletResponse response = (HttpServletResponse)resp;
      String exitParam = request.getParameter("exit");
      User user = (User) request.getSession().getAttribute("user");
      try {
         if (exitParam != null && user != null && user.isLogined()){
            UserDao dao = new UserDao();
            request.getSession().setAttribute("user", dao.read(0l));
            setcookie(response, "idu", "", 0, request.getContextPath(), request.getServerName());
            setcookie(response, "pass2", "", 0, request.getContextPath(), request.getServerName());
            String query = request.getQueryString();
            String[] queries = query.split("&");
            query = "";
            for (int i = 0; i < queries.length; i++) {
               String parameter = queries[i];
               if (!parameter.split("=")[0].equalsIgnoreCase("exit")){
                  query += ("".equals(query) ? "?" + parameter : "&" + parameter);
               }

            }
            response.sendRedirect(request.getRequestURI() + query);
         }else{
            chain.doFilter(request, response);
         }
      } catch (EncoderException e) {
         e.printStackTrace();
      } catch (ConfigurationException e) {
         e.printStackTrace();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public void init(FilterConfig filterConfig) throws ServletException {
      // TODO Auto-generated method stub

   }


   /**
    * {@inheritDoc}
    */
   @Override
   public void destroy() {
      // TODO Auto-generated method stub

   }

}
