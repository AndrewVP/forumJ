package org.forumj.network.web;

/**
 * Created by Andrew on 19/04/2017.
 */

import org.forumj.common.FJUrl;
import org.forumj.network.web.controller.RootController;

import javax.annotation.PostConstruct;

/**
 * /
 * /robots.txt
 * /webapp/user
 * /webapp/user/
 * /webapp/user/?name=value&name2=value2
 * /webapp/user?name=value&name2=value2
 * /webapp/user/index/
 * /webapp/user/index
 * /webapp/user/index/?name=value&name2=value2
 * /webapp/user/index?name=value&name2=value2
 * /00/photo/00/00/..../00/01.jpeg
 * /00/photo/00/00/..../00/01.jpeg?id=1
 * /00/avatar/00/00/..../00/01.jpeg
 * /00/avatar/00/00/..../00/01.jpeg?id=1
 * /00/css/style.css
 * /00/css/style.css?key=111
 * /00/js/style.css
 * /00/js/style.css?key=111
 */
public class URL{
    private String url;
    private String webapp;
    private boolean staticResource;
    private boolean rootResource;
    private String userName = "";
    private String controller;
    private String path;
    private String name;
    private String query;

    private URL (String url, String webapp){
        this.url = url;
        this.webapp = webapp;
    }

    public static URL create(String url, String webapp){
        URL result = new URL(url, webapp);
        result.parse();
        return result;
    }

    public String getUrl() {
        return url;
    }

    public String getWebapp() {
        return webapp;
    }

    public boolean isStaticResource() {
        return staticResource;
    }

    public boolean isRootResource() {
        return rootResource;
    }

    public String getUserName() {
        return userName;
    }

    public String getController() {
        return controller;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String getQuery() {
        return query;
    }

    public String getUserURI(){
        return webapp == null || webapp.isEmpty() ? userName : webapp + "/" + userName;
    }

    /**
     *  1. Extract query
     *  2. Cut ending slash
     *  3. Cut starting slash
     *  4. Cut webapp name
     *  5. Detect static resource (/00/)
     *    5.1. Extract resource type (photo, css, etc.)
     *    5.2. Extract resource name
     *    5.3. Extract resource path
     *  6. Detect root resources (/robots.txt)
     *    6.1. Extract resource name
     *  7. Extract user name
     *  8. Extract controller name
     */
    private void parse(){
        String tmpUrl = extractQuery(this.url);
        tmpUrl = cutStartSlashes(tmpUrl);
        tmpUrl = cutEndSlashes(tmpUrl);
        tmpUrl = cutWebappName(tmpUrl);
        staticResource = detectStaticResource(tmpUrl);
        rootResource = detectRootResource(tmpUrl);
        if (staticResource){
            tmpUrl = extractStaticResource(tmpUrl);
            tmpUrl = extractStaticResourceType(tmpUrl);
            tmpUrl = extractStaticResourceName(tmpUrl);
            tmpUrl =  extractStaticResourcePath(tmpUrl);
        }else if (rootResource){
            tmpUrl = extractRootResourceName(tmpUrl);
        }else{
            tmpUrl = extractUserName(tmpUrl);
            tmpUrl = extractControllerName(tmpUrl);
        }
    }

    private String extractQuery(String url){
        String[] result = url.split("\\?");
        if (result.length > 1){
            this.query = result[1];
        }
        return result[0];
    }

    private String cutStartSlashes(String url){
        while (url.startsWith("/")){
            url = url.substring(1);
        }
        return url;
    }
    private String cutEndSlashes(String url){
        while (url.endsWith("/")){
            url = url.substring(0, url.length() - 1);
        }
        return url;
    }

    private String cutWebappName(String url){
        if (webapp != null && !webapp.isEmpty()){
            if (url.startsWith(webapp)){
                url = url.substring(webapp.length());
                url = cutStartSlashes(url);
            }
        }
        return url;
    }

    private boolean detectStaticResource(String url){
        return url.startsWith(FJUrl.STATIC)
                // old url's
                // TODO migrate these url's
                || url.startsWith("forum/" + FJUrl.PHOTO);
    }

    private String extractStaticResource(String url){
        if (url.startsWith(FJUrl.STATIC)){
            url = url.substring(FJUrl.STATIC.length());
            url = cutStartSlashes(url);
        }else if (url.startsWith("forum/" + FJUrl.PHOTO)){
            // old url's
            // TODO migrate these url's
            url = url.substring(("forum/").length());
        }
        return url;
    }

    private String extractStaticResourceType(String url){
        String[] extracted = extractStartingPart(url);
        controller = extracted[0];
        url = extracted[1];
        return url;
    }

    private String extractStaticResourceName(String url){
        int slashPosition = url.lastIndexOf('/');
        if (slashPosition == -1) {
            name = url;
        }else {
            name = url.substring(slashPosition + 1);
            url = url.substring(0, slashPosition);
            url = cutEndSlashes(url);
        }
        return url;
    }

    private String extractStaticResourcePath(String url){
        url = cutStartSlashes(url);
        url = cutEndSlashes(url);
        return url;
    }

    private boolean detectRootResource(String url){
        boolean result = false;
        for (String rootResource: RootController.rootResources){
            result |= rootResource.equalsIgnoreCase(url);
        }
        if (result) controller = FJUrl.ROOT;
        return result;
    }

    private String extractRootResourceName(String url){
        name = url;
        return "";
    }

    private String extractUserName(String url){
        String[] extracted = extractStartingPart(url);
        userName = extracted[0];
        url = extracted[1];
        return url;
    }

    private String extractControllerName(String url){
        if (url.length() == 0){
            controller = FJUrl.INDEX;
        }else{
            String[] extracted = extractStartingPart(url);
            controller = extracted[0];
            url = extracted[1];
        }
        return url;
    }

    private String[] extractStartingPart(String url){
        String[] result = {"", ""};
        int slashPosition = url.indexOf('/');
        if (slashPosition == -1) {
            result[0] = url;
        }else {
            result[0] = url.substring(0, slashPosition);
            result[1] = url.substring(slashPosition + 1);
            result[1] = cutStartSlashes(result[1]);
        }
        return result;
    }

    @Override
    public String toString() {
        return this.url;
    }
}
