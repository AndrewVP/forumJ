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

import static org.forumj.common.FJServletName.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.IUser;
import org.forumj.db.dao.FJUserDao;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebFilter(servletNames={NEW_THREAD, ADD_THREAD, ADD_POST, NEW_QUESTION, ADD_QUESTION, SETTINGS, ADD_SUBSCRIBE, UPDATE_IGNORING, SET_DEFAULT_VIEW, FOLDER_TOOLS, 
      DELETE_MAIL, MOVE_THREAD_TO_RECYCLE, DELETE_SUBSCRIBE, DELETE_FOLDER_FROM_VIEW, DELETE_VIEW, DELETE_VOICE, VOICE, ADD_VOTE,
      MOVE_TITLE, NEW_FOLDER, NEW_VIEW, SET_AVATAR, SET_FOOTER, SET_LOCATION, V_AVATAR, SEND_PIVATE_MESSAGE, ADD_IGNOR})
public class RestrictUnloginedUsersFilter implements Filter {

   /**
    * {@inheritDoc}
    */
   @Override
   public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest request = (HttpServletRequest) req;
      HttpServletResponse response = (HttpServletResponse) resp;
      HttpSession session = request.getSession(true);
      IUser user = (IUser) session.getAttribute("user");
      if (user == null || !user.isLogined()){
         try {
            String idu = request.getParameter("IDU");
            String password1 = request.getParameter("PS1");
            String password2 = request.getParameter("PS2");
            if (idu != null && (password1 != null || password2 != null)){
               Long userId = Long.valueOf(idu);
               FJUserDao dao = new FJUserDao();
               boolean firstPassword = password1 != null;
               String password = password1 == null ? password1 : password2;
               user = dao.read(userId, password, firstPassword);
               if (user != null){
                  session.setAttribute("user", user);
               }
            }
         } catch (ConfigurationException e) {
            e.printStackTrace();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      if (user == null || !user.isLogined()){
         response.sendRedirect(request.getContextPath() + "/");
      }else{
         chain.doFilter(req, resp);
      }
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public void init(FilterConfig filterConfig) throws ServletException {}

   /**
    * {@inheritDoc}
    */
   @Override
   public void destroy() {}

}
