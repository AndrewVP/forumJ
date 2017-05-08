package org.forumj.network.web.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.forumj.network.web.FJUrl;
import org.forumj.common.config.FJConfiguration;
import org.forumj.network.web.URL;
import org.forumj.network.web.controller.*;
import org.forumj.network.web.controller.filter.*;
import org.forumj.network.web.controller.post.*;
import org.forumj.network.web.controller.validator.ThreadParametersValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.forumj.network.web.FJServletTools.errorOut;

/**
 * Created by Andrew on 16/03/2017.
 */
@WebServlet(urlPatterns = {"/"})
public class Dispatcher extends HttpServlet {

    private Logger logger = LogManager.getLogger(getClass().getCanonicalName());

    private String webappName;
    private String realPath = null;

    // GET controllers
    private RootController rootController = new RootController();
    private Page404 page404 = new Page404();
    private Page500 page500 = new Page500();
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
    private LoginFilter loginFilter = new LoginFilter();
    private LocaleResolver localeResolver = new LocaleResolver();
    private RestrictUnloginedUsersFilter restrictUnloginedUsersFilter = new RestrictUnloginedUsersFilter();

    //Validators
    private ThreadParametersValidator threadParametersValidator = new ThreadParametersValidator();

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
            String path = request.getRequestURI();
            URL url = URL.create(path, webappName);
            if (path != null && !path.isEmpty()) {
                if (isCorrectUserURI(url)) {
                    String controllerName = url.getController();
                    switch (controllerName){
                        case FJUrl.ROOT :
                            rootController.doGet(request, response, webappName, url.getUserURI());
                            break;
                        case FJUrl.INDEX :
                            exitFilter.doFilter(request, response, webappName, url.getUserURI(), controllerName, false, (req, resp, webapp, uri) -> {
                                loginFilter.doFilter(req, resp, webapp, uri, controllerName, (req1, resp1, webapp1, uri1) -> {
                                    localeResolver.doFilter(req1, resp1, webapp1, uri1, (req2, resp2, webapp2, uri2) -> {
                                        pageGroupIndex.doGet(req2, resp2, webapp2, uri2);
                                    });
                                });
                            });
                            break;
                        case FJUrl.VIEW_THREAD:
                        case FJUrl.VIEW_THREAD_OLD: // for old external links
                            exitFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.VIEW_THREAD, false, (req, resp, webapp, uri) -> {
                                loginFilter.doFilter(req, resp, webapp, uri, FJUrl.VIEW_THREAD, (req1, resp1, webapp1, uri1) -> {
                                    localeResolver.doFilter(req1, resp1, webapp1, uri1, (req2, resp2, webapp2, uri2) -> {
                                        threadParametersValidator.doFilter(req2, resp2, webapp2, uri2, (req3, resp3, webapp3, uri3) ->{
                                            pageGroupThread.doGet(req3, resp3, webapp3, uri3);
                                        });
                                    });
                                });
                            });
                            break;
                        case FJUrl.STATIC:
                        case FJUrl.PICTS:
                        case FJUrl.IMAGES:
                        case FJUrl.SMILES:
                        case FJUrl.BANNER:
                        case FJUrl.SKIN:
                        case FJUrl.AVATARS:
                        case FJUrl.PHOTO: // backward compatibility
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), controllerName, (req, resp, webapp, uri) -> {
                                imagesController.doGet(req, resp, webapp);
                            });
                            break;
                        case FJUrl.NEW_THREAD:
                            exitFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, true, (req, resp, webapp, uri) -> {
                                loginFilter.doFilter(req, resp, webapp, uri, controllerName, (req1, resp1, webapp1, uri1) -> {
                                    localeResolver.doFilter(req1, resp1, webapp1, uri1, (req2, resp2, webapp2, uri2) -> {
                                        restrictUnloginedUsersFilter.doFilter(req2, resp2, webapp2, uri2, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                            newThreadController.doGet(req3, resp3, webapp3, uri3);
                                        });
                                    });
                                });
                            });
                            break;
                        case FJUrl.SETTINGS:
                            exitFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, true, (req, resp, webapp, uri) -> {
                                loginFilter.doFilter(req, resp, webapp, uri, controllerName, (req1, resp1, webapp1, uri1) -> {
                                    localeResolver.doFilter(req1, resp1, webapp1, uri1, (req2, resp2, webapp2, uri2) -> {
                                        restrictUnloginedUsersFilter.doFilter(req2, resp2, webapp2, uri2, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                            settingsController.doGet(req3, resp3, webapp3, uri3);
                                        });
                                    });
                                });
                            });
                            break;
                        case FJUrl.MESSAGE:
                            exitFilter.doFilter(request, response, webappName, url.getUserURI(), controllerName, false, (req, resp, webapp, uri) -> {
                                loginFilter.doFilter(req, resp, webapp, uri, controllerName, (req1, resp1, webapp1, uri1) -> {
                                    localeResolver.doFilter(req1, resp1, webapp1, uri1, (req2, resp2, webapp2, uri2) -> {
                                        messageController.doGet(req2, resp2, webapp2, uri2);
                                    });
                                });
                            });
                            break;
                        case FJUrl.LOGIN:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), controllerName, (req1, resp1, webapp1, uri1) -> {
                                localeResolver.doFilter(req1, resp1, webapp1, uri1, (req2, resp2, webapp2, uri2) -> {
                                    loginController.doGet(req2, resp2, webapp2, uri2);
                                });
                            });
                            break;
                        case FJUrl.NEW_QUESTION:
                            exitFilter.doFilter(request, response, webappName, url.getUserURI(), controllerName, true, (req, resp, webapp, uri) -> {
                                loginFilter.doFilter(req, resp, webapp, uri, controllerName, (req1, resp1, webapp1, uri1) -> {
                                    localeResolver.doFilter(req1, resp1, webapp1, uri1, (req2, resp2, webapp2, uri2) -> {
                                        restrictUnloginedUsersFilter.doFilter(req2, resp2, webapp2, uri2, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                            newQuestionController.doGet(req3, resp3, webapp3, uri3);
                                        });
                                    });
                                });
                            });
                            break;
                        case FJUrl.REGISTRATION:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), controllerName, (req1, resp1, webapp1, uri1) -> {
                                localeResolver.doFilter(req1, resp1, webapp1, uri1, (req2, resp2, webapp2, uri2) -> {
                                    registrationController.doGet(req2, resp2, webapp2, uri2);
                                });
                            });
                            break;
                        case FJUrl.ACTIVATE_USER:
                            confirmEmailController.doGet(request, response, url.getUserURI());
                            break;
                        case FJUrl.ADD_IGNOR:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), controllerName, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    addIgnorController.doGet(req3, resp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.APPROVE_USER:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), controllerName, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    approveUserController.doGet(req3, resp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.BAN:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), controllerName, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    banController.doGet(req3, resp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.CLOSE_THREAD:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), controllerName, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    closeThreadController.doGet(req3, resp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.MOVE_THREAD_TO_RECYCLE:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), controllerName, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    moveToRecycleController.doGet(req3, resp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.PIN_THREAD:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), controllerName, (req, resp, webapp, uri) -> {
                                    restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                            pinThreadController.doGet(req3, resp3, uri3);
                                    });
                            });
                            break;
                        case FJUrl.PAGE_404:
                        default:
                            System.out.println("wrong url: " + url);
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
            logger.error(e);
            page500.doGet(request, response, webappName, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = request.getRequestURI();
            URL url = URL.create(path, webappName);
            if (path != null && !path.isEmpty()) {
                if (isCorrectUserURI(url)) {
                    String controllerName = url.getController();
                    switch (controllerName) {
                        case FJUrl.ADD_POST:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                localeResolver.doFilter(req, resp, webapp, uri, (req2, resp2, webapp2, uri2) -> {
                                    restrictUnloginedUsersFilter.doFilter(req2, resp2, webapp2, uri2, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                        addPostController.doPost(req3, resp3, webapp3, uri3);
                                    });
                                });
                            });
                            break;
                        case FJUrl.ADD_THREAD:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                localeResolver.doFilter(req, resp, webapp, uri, (req2, resp2, webapp2, uri2) -> {
                                    restrictUnloginedUsersFilter.doFilter(req2, resp2, webapp2, uri2, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                        addThreadController.doPost(req3, resp3, webapp3, uri3);
                                    });
                                });
                            });
                            break;
                        case FJUrl.DO_LOGIN:
                            doLoginController.doPost(request, response, webappName, url.getUserURI());
                            break;
                        case FJUrl.ADD_QUESTION:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                localeResolver.doFilter(req, resp, webapp, uri, (req2, resp2, webapp2, uri2) -> {
                                    restrictUnloginedUsersFilter.doFilter(req2, resp2, webapp2, uri2, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                        addQuestionController.doPost(req3, resp3, webapp3, uri3);
                                    });
                                });
                            });
                            break;
                        case FJUrl.ADD_SUBSCRIBE:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    addSubscribeController.doPost(req3, resp3, webapp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.UPDATE_IGNORING:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    updateIgnorController.doPost(req3, resp3, webapp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.SET_DEFAULT_VIEW:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    setDefaultViewController.doPost(req3, resp3, webapp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.FOLDER_TOOLS:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    folderToolsController.doPost(req3, resp3, webapp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.DELETE_MAIL:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    deleteMailController.doPost(req3, resp3, webapp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.DELETE_SUBSCRIBE:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    deleteOneSubscribeController.doPost(req3, resp3, webapp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.DELETE_ONE_SUBSCRIBE_BY_EMAIL:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    deleteOneSubscribeFromMailController.doPost(req3, resp3, webapp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.DELETE_SUBSCRIBES:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    deleteSubscribesController.doPost(req3, resp3, webapp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.DELETE_FOLDER_FROM_VIEW:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    removeFolderFromViewController.doPost(req3, resp3, webapp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.DELETE_VIEW:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    deleteViewController.doPost(req3, resp3, webapp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.DELETE_VOICE:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    deleteVoiceController.doPost(req3, resp3, webapp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.DO_REGISTRATION:
                            localeResolver.doFilter(request, response, webappName, url.getUserURI(), (req2, resp2, webapp2, uri2) -> {
                                doRegistrationController.doPost(req2, resp2, webapp2, uri2);
                            });
                            break;
                        case FJUrl.MOVE_TITLE:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    moveThreadController.doPost(req3, resp3, webapp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.NEW_FOLDER:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    createFolderController.doPost(req3, resp3, webapp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.NEW_VIEW:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    createViewController.doPost(req3, resp3, webapp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.PING:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                pingController.doPost(req, resp, webapp, uri);
                            });
                            break;
                        case FJUrl.POST:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    commandHandler.doPost(req3, resp3, webapp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.POST_IMAGE:
                            fileUploadFilter.doFilter(request, response, webappName, url.getUserURI(), (req, resp, webapp, uri) -> {
                                loginFilter.doFilter(req, resp, webapp, uri, FJUrl.INDEX, (req1, resp1, webapp1, uri1) -> {
                                    restrictUnloginedUsersFilter.doFilter(req1, resp1, webapp1, uri1, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                        addImageController.doPost(req3, resp3, webapp3, uri3);
                                    });
                                });
                            });
                            break;
                        case FJUrl.SEND_PIVATE_MESSAGE:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                localeResolver.doFilter(req, resp, webapp, uri, (req2, resp2, webapp2, uri2) -> {
                                    restrictUnloginedUsersFilter.doFilter(req2, resp2, webapp2, uri2, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                        sendPrivateMessagController.doPost(req3, resp3, webapp3, uri3);
                                    });
                                });
                            });
                            break;
                        case FJUrl.SET_AVATAR:
                            fileUploadFilter.doFilter(request, response, webappName, url.getUserURI(), (req, resp, webapp, uri) -> {
                                loginFilter.doFilter(req, resp, webapp, uri, FJUrl.INDEX, (req1, resp1, webapp1, uri1) -> {
                                    restrictUnloginedUsersFilter.doFilter(req1, resp1, webapp1, uri1, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                        setAvatarController.doPost(req3, resp3, webapp3, uri3);
                                    });
                                });
                            });
                            break;
                        case FJUrl.SET_FOOTER:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    setFooterController.doPost(req3, resp3, webapp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.SET_LOCATION:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    setLocationController.doPost(req3, resp3, webapp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.SELECT_VIEW:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    changeViewController.doPost(req3, resp3, webapp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.ADD_VOTE:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    addCustomAnswerController.doPost(req3, resp3, webapp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.V_AVATAR:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    setViewAvatarController.doPost(req3, resp3, webapp3, uri3);
                                });
                            });
                            break;
                        case FJUrl.VOICE:
                            loginFilter.doFilter(request, response, webappName, url.getUserURI(), FJUrl.INDEX, (req, resp, webapp, uri) -> {
                                restrictUnloginedUsersFilter.doFilter(req, resp, webapp, uri, FJUrl.LOGIN, (req3, resp3, webapp3, uri3) -> {
                                    addVoteController.doPost(req3, resp3, webapp3, uri3);
                                });
                            });
                            break;
                        default:
                            System.out.println("wrong url: " + url);
                            page404.doGet(request, response, webappName);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e);
            page500.doGet(request, response, webappName, e);
        }
    }

    private boolean isCorrectUserURI(URL url){
        //TODO it is temporary stub!!
        return url.isStaticResource() || url.isRootResource() || url.getUserName().equals(FJUrl.DEFAULT_USER);
    }
}
