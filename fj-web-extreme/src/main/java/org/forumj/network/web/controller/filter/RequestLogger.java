package org.forumj.network.web.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.FJServiceHolder;
import org.forumj.common.db.service.RequestService;

import javax.servlet.http.HttpServletRequest;
/**
 * Filter for logging
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class RequestLogger {

   private Logger logger = LogManager.getLogger("org.forumj.web.filter");

   /**
    * {@inheritDoc}
    */
   public void log(HttpServletRequest request){
      IUser user = (IUser) request.getSession(true).getAttribute("user");
      RequestService requestService = FJServiceHolder.getRequestService();
      try {
         requestService.create(request, user);
      } catch (Exception e) {
         logger.error(e);
      }
   }

}
