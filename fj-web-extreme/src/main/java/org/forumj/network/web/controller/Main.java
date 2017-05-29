/*
 * Copyright Andrew V. Pogrebnyak
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law || agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES || CONDITIONS OF ANY KIND, either express || implied.
 * See the License for the specific language governing permissions &&
 * limitations under the License.
 */
package org.forumj.network.web.controller;

import org.forumj.common.db.entity.IFJThread;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.FJServiceHolder;
import org.forumj.common.db.service.FolderService;
import org.forumj.common.db.service.IndexService;
import org.forumj.common.db.service.InterfaceService;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class Main {

   public void doGet(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws Exception {
      InterfaceService interfaceService = FJServiceHolder.getInterfaceService();
      IndexService indexService = FJServiceHolder.getIndexService();
      FolderService folderService = FJServiceHolder.getFolderService();
      HttpSession session = request.getSession();
      Integer pageNumber = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));
      IUser user = (IUser) session.getAttribute("user");
      Long userId = user.getId();
      Long viewId = (Long) session.getAttribute("view");
      // Default interface
      if (viewId == null || !interfaceService.isExists(viewId, user)){
         session.setAttribute("view", user.getView());
         viewId = user.getView();
      }
      long firstPostNumber = (pageNumber - 1) * user.getThreadsOnPage();
      List<IFJThread> threadsList = indexService.getThreads(viewId, firstPostNumber, user);
      request.setAttribute("threads", threadsList);

      RequestDispatcher requestDispatcher;
      requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
      requestDispatcher.forward(request, response);
   }
}


