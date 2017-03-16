package org.forumj.network.web.servlet;

import org.forumj.common.FJServletName;
import org.forumj.common.FJUrl;
import org.forumj.web.servlet.get.Index;
import org.forumj.web.servlet.get.Page404;
import org.forumj.web.servlet.get.Tema;
import sun.security.krb5.internal.PAData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.forumj.tool.Diletant.errorOut;
import static org.forumj.tool.FJServletTools.cache;
import static org.forumj.tool.FJServletTools.logo;
import static org.forumj.web.servlet.tool.FJServletTools.loadCSS;

/**
 * Created by Andrew on 16/03/2017.
 */
@WebServlet(urlPatterns = {"/"}, name = FJServletName.INDEX)
public class Dispatcher extends HttpServlet {

    private Page404 page404 = new Page404();
    private Index pageGroupIndex = new Index();
    private Tema pageGroupThread = new Tema();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path != null && !path.isEmpty()) {
            String[] pathParts = path.split("/");
            String userName = pathParts[1]; // pathParts[0] is empty
            if (isCorrectUserName(userName)) {
                if (pathParts.length == 2){
                    pageGroupIndex.doGet(request, response);
                }else{
                    String controllerName = pathParts[2];
                    switch (controllerName){
                        case FJUrl.INDEX :
                            pageGroupIndex.doGet(request, response);
                            break;
                        case FJUrl.VIEW_THREAD:
                        case FJUrl.VIEW_THREAD_OLD:
                            pageGroupThread.doGet(request, response);
                    }
                }
            }
        }
        page404.doGet(request, response);

    }

    private boolean isCorrectUserName(String partPath){
        //TODO it is temporary stub!!
        return partPath.equals("forum");
    }
}
