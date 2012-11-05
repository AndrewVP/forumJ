/*
 */
package org.forumj.web.servlet.get.includes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/js/*"}, name="JavaScripts")
public class JavaScripts extends IncludesBase {

   @Override
   public void init() throws ServletException {
      super.init();
      setContentType("text/javascript; charset=UTF-8");
   }
}
