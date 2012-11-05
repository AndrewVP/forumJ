package org.forumj.web.filter;

import static org.forumj.common.FJServletName.*;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
/**
 * Filter for transcoding {@link String} parameters from ISO-8859-1 to UTF-8 in the {@link ServletRequest}
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebFilter(servletNames={NEW_QUESTION, ADD_POST, DO_LOGIN, ADD_QUESTION, ADD_THREAD, NEW_FOLDER, NEW_VIEW, 
      SEND_PIVATE_MESSAGE, NEW_THREAD, UPDATE_IGNORING, DELETE_MAIL, MOVE_THREAD_TO_RECYCLE, DELETE_SUBSCRIBE,
      DELETE_SUBSCRIBES, DELETE_ONE_SUBSCRIBE_BY_EMAIL, DELETE_FOLDER_FROM_VIEW, DELETE_VIEW, SET_DEFAULT_VIEW, 
      SET_FOOTER, SET_LOCATION, ADD_VOTE, DO_REGISTRATION})
public class AAAAAUtf8Filter implements Filter{

   /**
    * {@inheritDoc}
    */
   public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
      req.setCharacterEncoding("UTF-8");
      chain.doFilter(req, resp);
   }

   /**
    * {@inheritDoc}
    */
   public void destroy() {}

   /**
    * {@inheritDoc}
    */
   public void init(FilterConfig filterConfig) throws ServletException {}
}
