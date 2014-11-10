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
package org.forumj.web.filter.parametersValidator;

import static org.forumj.tool.Diletant.*;
import static org.forumj.common.FJServletName.*;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import org.forumj.common.db.service.*;
import org.forumj.web.request.RequestWrapper;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebFilter(servletNames={VIEW_THREAD})
public class TemaValidator implements Filter{


   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      boolean isOk = true;
      try {
         ThreadService threadService = FJServiceHolder.getThreadService();
         PostService postService = FJServiceHolder.getPostService();
         RequestWrapper req = new RequestWrapper((HttpServletRequest) request);
         String page = request.getParameter("page");
         if (page == null){
            req.addOrReplaceParameter("page", "1");
         }else{
            try {
               Integer.valueOf(page);
            } catch (NumberFormatException e) {
               req.addOrReplaceParameter("page", "1");
            }
         }
         String id = request.getParameter("id");
         if (id == null){
            isOk = false;
         }else{
            Long threadId = null;
            try {
               threadId = Long.valueOf(id);
            } catch (NumberFormatException e) {
               isOk = false;
            }
            if (threadId != null){
               isOk = isOk && threadService.checkThreadExist(threadId);
            }
         }
         String reply = request.getParameter("reply");
         if (reply != null){
            Long postId = null;
            try {
               postId = Long.valueOf(reply);
            } catch (NumberFormatException e) {
               req.addOrReplaceParameter("reply", RequestWrapper.FAKE_NULL);
            }
            if(postId != null && !postService.checkPostExist(postId)){
               req.addOrReplaceParameter("reply", RequestWrapper.FAKE_NULL);
            }
         }
         String msg = request.getParameter("msg");
         if (msg != null){
            Long postId = null;
            try {
               postId = Long.valueOf(msg);
            } catch (NumberFormatException e) {
               req.addOrReplaceParameter("msg", RequestWrapper.FAKE_NULL);
            }
            if(postId != null && !postService.checkPostExist(postId)){
               req.addOrReplaceParameter("msg", RequestWrapper.FAKE_NULL);
            }
         }
         if (isOk){
            chain.doFilter(req, response);
         }else{
            ((HttpServletResponse) response).sendRedirect("/");
         }
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
