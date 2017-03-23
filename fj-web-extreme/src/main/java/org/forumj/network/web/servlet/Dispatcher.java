package org.forumj.network.web.servlet;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.forumj.common.FJServletName;
import org.forumj.common.FJUrl;
import org.forumj.common.config.FJConfiguration;
import org.forumj.network.web.controller.*;

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
    private Control settingsController = new Control();
    private Message messageController = new Message();
    private Auth loginController = new Auth();
    private Opr newQuestionController = new Opr();
    private Registration registrationController = new Registration();
    private ActivateUser confirmEmailController = new ActivateUser();
    private AddIgnor addIgnorController = new AddIgnor();
    private ApproveUser approveUserController = new ApproveUser();
    private Ban banController = new Ban();
    private CloseThread closeThreadController = new CloseThread();
    private DelOne moveToRecycleController = new DelOne();
    private Pin pinThreadController = new Pin();

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
        // removing doubled slashes
        path = path.replace("//", "/");
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
                        pageGroupIndex.doGet(request, response, webappName, userURI);
                        break;
                    case FJUrl.VIEW_THREAD:
                    case FJUrl.VIEW_THREAD_OLD:
                        pageGroupThread.doGet(request, response, webappName, userURI);
                        break;
                    case FJUrl.STATIC:
                    case FJUrl.PHOTO: // backward compatibility
                        imagesController.doGet(request, response);
                        break;
                    case FJUrl.NEW_THREAD:
                        newThreadController.doGet(request, response, webappName, userURI);
                        break;
                    case FJUrl.SETTINGS:
                        settingsController.doGet(request, response, webappName, userURI);
                        break;
                    case FJUrl.MESSAGE:
                        messageController.doGet(request, response, webappName, userURI);
                        break;
                    case FJUrl.LOGIN:
                        loginController.doGet(request, response, webappName, userURI);
                        break;
                    case FJUrl.NEW_QUESTION:
                        newQuestionController.doGet(request, response, webappName, userURI);
                        break;
                    case FJUrl.REGISTRATION:
                        registrationController.doGet(request, response, webappName, userURI);
                        break;
                    case FJUrl.ACTIVATE_USER:
                        confirmEmailController.doGet(request, response, userURI);
                        break;
                    case FJUrl.ADD_IGNOR:
                        addIgnorController.doGet(request, response, userURI);
                        break;
                    case FJUrl.APPROVE_USER:
                        approveUserController.doGet(request, response, userURI);
                        break;
                    case FJUrl.BAN:
                        banController.doGet(request, response, userURI);
                        break;
                    case FJUrl.CLOSE_THREAD:
                        closeThreadController.doGet(request, response, userURI);
                        break;
                    case FJUrl.MOVE_THREAD_TO_RECYCLE:
                        moveToRecycleController.doGet(request, response, userURI);
                        break;
                    case FJUrl.PIN_THREAD:
                        pinThreadController.doGet(request, response, userURI);
                        break;
                    default:
                        page404.doGet(request, response, webappName);
                        break;
                }
            }else{
                page404.doGet(request, response, webappName);
            }
        }else{
            page404.doGet(request, response, webappName);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        // removing doubled slashes
        path = path.replace("//", "/");
        if (path != null && !path.isEmpty()) {
            String[] pathParts = path.split("/");
            String userURI = getUserURI(pathParts);
            if (isCorrectUserURI(userURI)) {
                String controllerName = getControllerName(pathParts);
                if (!webappName.isEmpty()) {
                    userURI = webappName + "/" + userURI;
                }
            }
        }
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
        int controllerPosition = webappName.isEmpty() ? 2 : 3;
        if (!isStaticResource(pathParts)){
            if (pathParts.length == controllerPosition){
                controllerName = FJUrl.INDEX;
            }else {
                controllerName = pathParts[controllerPosition];
            }
        }else{
            controllerName = pathParts[controllerPosition - 1];
        }
        return controllerName;
    }

    private boolean isStaticResource(String[] pathParts){
        boolean result = false;
        int minimalSize = webappName.isEmpty() ? 2 : 3;
        if (pathParts.length > minimalSize){
            result = pathParts[minimalSize - 1].equals(FJUrl.STATIC);
        }
        return result;
    }

    private boolean isCorrectUserURI(String partPath){
        //TODO it is temporary stub!!
        return partPath.equals("forum") || partPath.equals(FJUrl.STATIC);
    }
}
