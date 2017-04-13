package org.forumj.network.web.servlet;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.forumj.common.FJServletName;
import org.forumj.common.FJUrl;
import org.forumj.common.config.FJConfiguration;
import org.forumj.network.web.controller.*;
import org.forumj.network.web.controller.post.*;
import org.forumj.network.web.filter.ExitFilter;
import org.forumj.network.web.filter.FileUploadFilter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.forumj.tool.Diletant.errorOut;

/**
 * Created by Andrew on 16/03/2017.
 */
@WebServlet(urlPatterns = {"/"}, name = FJServletName.INDEX)
public class Dispatcher extends HttpServlet {

    private Logger logger = LogManager.getLogger("org.forumj.web.filter");

    private String webappName;
    private String realPath = null;

    // GET controllers
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

    //POST controllers
    private Write addPostController = new Write();
    private New addThreadController = new New();
    private Submit doLoginController = new Submit();
    private Quest addQuestionController = new Quest();
    private AddSubscribe addSubscribeController = new AddSubscribe();
    private Amn updateIgnorController = new Amn();
    private Defview setDefaultViewController = new Defview();
    private DelFolder folderToolsController = new DelFolder();
    private DelMail deleteMailController = new DelMail();
    private DelOneSubs deleteOneSubscribeController = new DelOneSubs();
    private DelOneSubsByMail deleteOneSubscribeFromMailController = new DelOneSubsByMail();
    private DelSubs deleteSubscribesController = new DelSubs();
    private DelVFolder removeFolderFromViewController = new DelVFolder();
    private DelView deleteViewController = new DelView();
    private DelVoice deleteVoiceController = new DelVoice();
    private InsNew doRegistrationController = new InsNew();
    private MoveTitle moveThreadController = new MoveTitle();
    private NewFolder createFolderController = new NewFolder();
    private NewView createViewController = new NewView();
    private Ping pingController = new Ping();
    private Post commandHandler = new Post();
    private PostImage addImageController = new PostImage();
    private Send sendPrivateMessagController = new Send();
    private SetAvatar setAvatarController = new SetAvatar();
    private SetFooter setFooterController = new SetFooter();
    private SetLocation setLocationController = new SetLocation();
    private SlctView changeViewController = new SlctView();
    private UserVoice addCustomAnswerController = new UserVoice();
    private VAvatar setViewAvatarController = new VAvatar();
    private Voice addVoteController = new Voice();

    //Filters
    private FileUploadFilter fileUploadFilter = new FileUploadFilter();
    private ExitFilter exitFilter = new ExitFilter();


    @Override
    public void init() throws ServletException {
        super.init();
        try {
            realPath = getServletContext().getRealPath("/");
            imagesController.init(realPath);
            setAvatarController.init();
            fileUploadFilter.init();
            webappName = FJConfiguration.getAppName();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
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
                            exitFilter.doFilter(request, response, webappName, userURI, controllerName, false, (req, resp, webapp, uri) -> {
                                pageGroupIndex.doGet(req, resp, webapp, uri);
                            });
                            break;
                        case FJUrl.VIEW_THREAD:
                            exitFilter.doFilter(request, response, webappName, userURI, controllerName, false, (req, resp, webapp, uri) -> {
                                pageGroupThread.doGet(req, resp, webapp, uri);
                            });
                        case FJUrl.VIEW_THREAD_OLD:
                            exitFilter.doFilter(request, response, webappName, userURI, FJUrl.VIEW_THREAD, false, (req, resp, webapp, uri) -> {
                                pageGroupThread.doGet(req, resp, webapp, uri);
                            });
                            break;
                        case FJUrl.STATIC:
                        case FJUrl.PHOTO: // backward compatibility
                            imagesController.doGet(request, response);
                            break;
                        case FJUrl.NEW_THREAD:
                            exitFilter.doFilter(request, response, webappName, userURI, FJUrl.INDEX, true, (req, resp, webapp, uri) -> {
                                newThreadController.doGet(req, resp, webapp, uri);
                            });
                            break;
                        case FJUrl.SETTINGS:
                            exitFilter.doFilter(request, response, webappName, userURI, FJUrl.INDEX, true, (req, resp, webapp, uri) -> {
                                settingsController.doGet(req, resp, webapp, uri);
                            });
                            break;
                        case FJUrl.MESSAGE:
                            exitFilter.doFilter(request, response, webappName, userURI, controllerName, false, (req, resp, webapp, uri) -> {
                                messageController.doGet(req, resp, webapp, uri);
                            });
                            break;
                        case FJUrl.LOGIN:
                            loginController.doGet(request, response, webappName, userURI);
                            break;
                        case FJUrl.NEW_QUESTION:
                            exitFilter.doFilter(request, response, webappName, userURI, controllerName, true, (req, resp, webapp, uri) -> {
                                pageGroupThread.doGet(req, resp, webapp, uri);
                            });
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
        } catch (Exception e) {
            e.printStackTrace();
            StringBuffer buffer = new StringBuffer();
            buffer = new StringBuffer();
            buffer.append(errorOut(e));
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            String out = buffer.toString();
            writer.write(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
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
                    switch (controllerName) {
                        case FJUrl.ADD_POST:
                            addPostController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.ADD_THREAD:
                            addThreadController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.DO_LOGIN:
                            doLoginController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.ADD_QUESTION:
                            addQuestionController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.ADD_SUBSCRIBE:
                            addSubscribeController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.UPDATE_IGNORING:
                            updateIgnorController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.SET_DEFAULT_VIEW:
                            setDefaultViewController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.FOLDER_TOOLS:
                            folderToolsController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.DELETE_MAIL:
                            deleteMailController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.DELETE_SUBSCRIBE:
                            deleteOneSubscribeController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.DELETE_ONE_SUBSCRIBE_BY_EMAIL:
                            deleteOneSubscribeFromMailController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.DELETE_SUBSCRIBES:
                            deleteSubscribesController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.DELETE_FOLDER_FROM_VIEW:
                            removeFolderFromViewController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.DELETE_VIEW:
                            deleteViewController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.DELETE_VOICE:
                            deleteVoiceController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.DO_REGISTRATION:
                            doRegistrationController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.MOVE_TITLE:
                            moveThreadController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.NEW_FOLDER:
                            createFolderController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.NEW_VIEW:
                            createViewController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.PING:
                            pingController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.POST:
                            commandHandler.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.POST_IMAGE:
                            fileUploadFilter.doFilter(request, response, webappName, userURI, (req, resp, webapp, uri) -> {
                                addImageController.doPost(req, resp, webapp, uri);
                            });
                            break;
                        case FJUrl.SEND_PIVATE_MESSAGE:
                            sendPrivateMessagController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.SET_AVATAR:
                                fileUploadFilter.doFilter(request, response, webappName, userURI, (req, resp, webapp, uri) -> {
                                    setAvatarController.doPost(req, resp, webapp, uri);
                                });
                            break;
                        case FJUrl.SET_FOOTER:
                            setFooterController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.SET_LOCATION:
                            setLocationController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.SELECT_VIEW:
                            changeViewController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.ADD_VOTE:
                            addCustomAnswerController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.V_AVATAR:
                            setViewAvatarController.doPost(request, response, webappName, userURI);
                            break;
                        case FJUrl.VOICE:
                            addVoteController.doPost(request, response, webappName, userURI);
                            break;
                        default:
                            page404.doGet(request, response, webappName);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            StringBuffer buffer = new StringBuffer();
            buffer = new StringBuffer();
            buffer.append(errorOut(e));
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            String out = buffer.toString();
            writer.write(out);
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
