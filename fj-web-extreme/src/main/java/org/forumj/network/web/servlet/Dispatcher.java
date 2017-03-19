package org.forumj.network.web.servlet;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.forumj.common.FJServletName;
import org.forumj.common.FJUrl;
import org.forumj.common.config.FJConfiguration;
import org.forumj.web.servlet.get.*;
import org.forumj.web.servlet.get.images.Images;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Andrew on 16/03/2017.
 */
@WebServlet(urlPatterns = {"/"}, name = FJServletName.INDEX)
public class Dispatcher extends HttpServlet {

    private Logger logger = LogManager.getLogger("org.forumj.web.filter");

    private String webappName;
    private String realPath = null;

    private Page404 page404 = new Page404();
    private Index pageGroupIndex = new Index();
    private Tema pageGroupThread = new Tema();
    private Images imagesController = new Images();
    private Mess newThreadController = new Mess();
    private Quest questController = new Quest();

    @Override
    public void init() throws ServletException {
        super.init();
        realPath = getServletContext().getRealPath("/");
        imagesController.init(realPath);
        try {
            webappName = FJConfiguration.getAppName();
        } catch (ConfigurationException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path != null && !path.isEmpty()) {
            String[] pathParts = path.split("/");
            String userURI = getUserURI(pathParts);
            if (isCorrectUserURI(userURI)) {
                String controllerName = getControllerName(pathParts);
                if (!webappName.isEmpty()){
                    userURI = webappName + "/" + userURI;
                }
                switch (controllerName){
                    case FJUrl.INDEX :

                        pageGroupIndex.doGet(request, response, userURI, webappName);
                        break;
                    case FJUrl.VIEW_THREAD:
                    case FJUrl.VIEW_THREAD_OLD:
                        pageGroupThread.doGet(request, response, webappName, userURI);
                    case FJUrl.STATIC:
                        imagesController.doGet(request, response);
                    case FJUrl.NEW_THREAD:
                        newThreadController.doGet(request, response, webappName, userURI);
                    case FJUrl.ADD_QUESTION:
                        newThreadController.doGet(request, response, webappName, userURI);
                }
            }
        }
        page404.doGet(request, response, webappName);

    }

    private String getUserURI(String[] pathParts){
        int userPosition = 1;
        if (!webappName.isEmpty()){
            userPosition = 2;
        }
        return pathParts[userPosition]; // pathParts[0] is empty
    }

    private String getControllerName(String[] pathParts){
        String controllerName = null;
        int controllerPosition = 2;
        if (!webappName.isEmpty()){
            controllerPosition = 3;
        }
        if (pathParts.length == controllerPosition){
            controllerName = FJUrl.INDEX;
        }else {
            controllerName = pathParts[controllerPosition];
        }
        return controllerName;
    }

    private boolean isCorrectUserURI(String partPath){
        //TODO it is temporary stub!!
        return partPath.equals("forum");
    }
}
