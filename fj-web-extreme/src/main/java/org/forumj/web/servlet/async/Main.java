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
package org.forumj.web.servlet.async;

import java.io.IOException;
import java.util.concurrent.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.web.request.RequestWrapper;
import org.forumj.web.servlet.FJServlet;


/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.MAIN}, name = FJServletName.MAIN, asyncSupported=true)
public class Main extends FJServlet {

    private ThreadPoolExecutor executor;

    @Override
    public void init() throws ServletException {
        super.init();
        executor = new ThreadPoolExecutor(20, 100, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());       
    }

    @Override
    public void destroy() {
        super.destroy();
        executor.shutdown();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AsyncContext context = request.startAsync(request, response);
        context.setTimeout(120000);
        executor.execute(new MainThread(context));       
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestWrapper wrappedRequest = new RequestWrapper(request);
        wrappedRequest.addOrReplaceParameter(FJRequestParameter.COMMAND, Command.GET_MAIN.getCommand());
        doPost(wrappedRequest, response);
    }
}


