package org.forumj.web.filter;

import static org.forumj.common.FJServletName.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
/**
 * Filter for transcoding {@link String} parameters from ISO-8859-1 to UTF-8 in the {@link ServletRequest}
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebFilter(servletNames={ADD_POST, DO_LOGIN, ADD_QUESTION, ADD_THREAD, NEW_FOLDER, NEW_VIEW, SEND_PIVATE_MESSAGE, SET_FOOTER, SET_LOCATION, ADD_VOTE, DO_REGISTRATION})
public class Utf8Filter implements Filter{

   /**
    * {@inheritDoc}
    */
   public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest request = (HttpServletRequest) req;
      Map<String, String[]> params = request.getParameterMap();
      Set<Entry<String,String[]>> entrySet = params.entrySet();
      for (Iterator<Entry<String, String[]>> iterator = entrySet.iterator(); iterator.hasNext();) {
         Entry<String, String[]> entry = iterator.next();
         String[] parameter = (String[]) entry.getValue();
         for (int i = 0; i < parameter.length; i++) {
            if (parameter[i] != null && parameter[i].length() > 0){
               parameter[i] = new String(parameter[i].getBytes("ISO-8859-1"), "UTF-8");
            }
         }
      }
      chain.doFilter(request, resp);
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
