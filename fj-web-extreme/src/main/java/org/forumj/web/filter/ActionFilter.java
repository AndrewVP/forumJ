package org.forumj.web.filter;

import static org.forumj.common.FJServletName.*;
import static org.forumj.tool.Diletant.*;

import java.io.*;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import org.forumj.common.db.entity.*;
import org.forumj.common.db.service.*;
import org.forumj.web.servlet.tool.FJServletTools;
/**
 * Filter for logging
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebFilter("/*")
public class ActionFilter implements Filter{

   private Logger logger = LogManager.getLogger("org.forumj.web.filter");

   /**
    * {@inheritDoc}
    */
   public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest request = (HttpServletRequest) req;
      HttpServletResponse response = (HttpServletResponse)resp;
      try {
         String uaString = request.getHeader("user-agent");
         uaString += " " + Arrays.toString(uaString.getBytes());
         logger.debug(uaString);
         if (!FJServletTools.isRobot(request)){
            IUser user = (IUser) request.getSession(true).getAttribute("user");
            ActionService service = FJServiceHolder.getActionService();
            IFJAction action = service.getObject();
            action.setIp(request.getRemoteAddr());
            String ref = request.getHeader("referer");
            if (ref != null){
               action.setRefer(ref.substring(0, ref.length() > 200 ? 199 : ref.length()));
            }
            String url = request.getRequestURI();
            if (url != null){
               action.setServletName(url.substring(0, url.length() > 200 ? 199 : url.length()));
            }
//action.setSubnet(subnet);
            action.setUas(request.getHeader("User-Agent"));
            if (user != null){
               action.setUserId(user.getId());
            }
            service.create(action);
         }
         chain.doFilter(request, resp);
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

   /**
    * {@inheritDoc}
    */
   public void destroy() {}

   /**
    * {@inheritDoc}
    */
   public void init(FilterConfig filterConfig) throws ServletException {}
}
