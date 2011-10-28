/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.web.servlet.post;

import static org.forumj.tool.Diletant.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.db.dao.FJSubscribeDao;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebServlet(urlPatterns = {FJUrl.DELONE_SUBSCRIBE_BY_EMAIL}, name=FJServletName.DEL_ONE_SUBSCRIBE_BY_EMAIL)
public class DelOneSubsByMail extends HttpServlet {

   private static final long serialVersionUID = 1635270592175711515L;

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         StringBuffer buffer = new StringBuffer();
         String keyParameter = request.getParameter("id");
         if (keyParameter != null && !"".equals(keyParameter)){
            Long key = Long.valueOf(keyParameter);
            FJSubscribeDao dao = new FJSubscribeDao();
            dao.deleteByKey(key);
            buffer.append(successPostOut("0", "index.php"));
         }
      }catch (Exception e) {
         e.printStackTrace();
      }
   }
}
