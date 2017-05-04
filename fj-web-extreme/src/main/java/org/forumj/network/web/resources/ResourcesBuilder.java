package org.forumj.network.web.resources;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrew on 04/05/2017.
 */
public class ResourcesBuilder {

    private static StringBuilder styleCSS;
    private static StringBuilder robotsTXT;
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
