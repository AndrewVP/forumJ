package org.forumj.network.web.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrew on 04/05/2017.
 */
public class ResourcesBuilder {

    private static StringBuilder styleCSS;
    private static StringBuilder bootstrapMinCSS;
    private static StringBuilder bootstrapThemeMinCSS;
    private static StringBuilder html5shivMinJS;
    private static StringBuilder respondMinJS;
    private static StringBuilder robotsTXT;
    private static StringBuilder jquery_3_2_1_minJS;
    private static StringBuilder bootstrapMinJS;
    private static List<byte[]> faviconICO;

    public static StringBuilder getStyleCSS(String webapp) throws IOException {
        if (styleCSS == null){
            final String mappedWebapp = webapp.isEmpty() ? "" : "/" + webapp;
            styleCSS = new StringBuilder("<style type='text/css'>\n");
            styleCSS.append(loadTxtResource("/css/style.css", line -> {return line.replace("@@WEBAPP@@", mappedWebapp);}));
            styleCSS.append("</style>\n");
        }
        return styleCSS;
    }

    public static StringBuilder getRobotsTXT() throws IOException {
        if (robotsTXT == null){
            robotsTXT = new StringBuilder();
            robotsTXT.append(loadTxtResource("/root/robots.txt", line -> {return line;}));
        }
        return robotsTXT;
    }

    public static StringBuilder getBootstrapMinCSS() throws IOException {
        if (bootstrapMinCSS == null){
            bootstrapMinCSS = new StringBuilder();
            bootstrapMinCSS.append(loadTxtResource("/css/bootstrap.min.css", line -> {return line;}));
        }
        return bootstrapMinCSS;
    }

    public static StringBuilder getBootstrapMinJS() throws IOException {
        if (bootstrapMinJS == null){
            bootstrapMinJS = new StringBuilder();
            bootstrapMinJS.append(loadTxtResource("/js/bootstrap.min.js", line -> {return line;}));
        }
        return bootstrapMinJS;
    }

    public static StringBuilder getRespondMinJS() throws IOException {
        if (respondMinJS == null){
            respondMinJS = new StringBuilder();
            respondMinJS.append(loadTxtResource("/js/respond.min.js", line -> {return line;}));
        }
        return respondMinJS;
    }

    public static StringBuilder getBootstrapThemeMinCSS() throws IOException {
        if (bootstrapThemeMinCSS == null){
            bootstrapThemeMinCSS = new StringBuilder();
            bootstrapThemeMinCSS.append(loadTxtResource("/css/bootstrap-theme.min.css", line -> {return line;}));
        }
        return bootstrapThemeMinCSS;
    }

    public static StringBuilder getHtml5shivMinJS() throws IOException {
        if (html5shivMinJS == null){
            html5shivMinJS = new StringBuilder();
            html5shivMinJS.append(loadTxtResource("/js/html5shiv.min.js", line -> {return line;}));
        }
        return html5shivMinJS;
    }

    public static StringBuilder getJquery_3_2_1_minJS() throws IOException {
        if (jquery_3_2_1_minJS == null){
            jquery_3_2_1_minJS = new StringBuilder();
            jquery_3_2_1_minJS.append(loadTxtResource("/js/jquery-3.2.1.min.js", line -> {return line;}));
        }
        return jquery_3_2_1_minJS;
    }

    public static List<byte[]> getFaviconICO() throws IOException {
        if (faviconICO == null){
            faviconICO = loadFileAsArray("/root/favicon.ico");
        }
        return faviconICO;
    }

    private static StringBuilder loadTxtResource(String path, Lambda lambda) throws IOException {
        StringBuilder result = new StringBuilder();
        ClassLoader classLoader = ResourcesBuilder.class.getClassLoader();
        try(InputStream stream = classLoader.getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));){
            while(br.ready()){
                result.append(lambda.invoke(br.readLine()));
                result.append("\n");
            }
        }
        return result;
    }

    private static List<byte[]> loadFileAsArray(String path) throws IOException {
        List<byte[]> result = new LinkedList<>();
        ClassLoader classLoader = ResourcesBuilder.class.getClassLoader();
        try (InputStream stream = classLoader.getResourceAsStream(path);) {
            byte[] chars = new byte[1024];
            int read;
            byte[] realChars;
            while ((read = stream.read(chars)) > 0) {
                realChars = Arrays.copyOf(chars, read);
                result.add(realChars);
            }
        }
        return result;
    }


    private interface Lambda{
        String invoke(String line);
    }

}
