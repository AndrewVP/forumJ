/*
 * Copyright Andrew V. Pogrebnyak
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.forumj.network.web.controller;

import org.forumj.network.web.FJUrl;
import org.forumj.network.web.resources.ResourcesBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class RootController {

    private static String ROBOTS_TXT = "robots.txt";
    private static String FAVICON_ICO = "favicon.ico";
    private static String INDEX_HTML = "index.html";
    public static String[] rootResources = {ROBOTS_TXT, FAVICON_ICO, INDEX_HTML};

    public void doGet(HttpServletRequest request, HttpServletResponse response, String webapp, String userUri) throws Exception {
        String resource = request.getRequestURI();
        if (resource.endsWith(ROBOTS_TXT)){
            response.setContentType("text/plain; charset=UTF-8");
            StringBuilder robots = ResourcesBuilder.getRobotsTXT();
            PrintWriter writer = response.getWriter();
            String out = robots.toString();
            writer.write(out);
        }else if (resource.endsWith(FAVICON_ICO)){
            List<byte[]> icon = ResourcesBuilder.getFaviconICO();
            response.setContentType("image/vnd.microsoft.icon");
            OutputStream outputStream = response.getOutputStream();
            for (byte[] potion : icon) {
                outputStream.write(potion);
            }
        }else if (resource.endsWith(INDEX_HTML) || resource.endsWith("/")){
            //TODO - empty user, empty root. Now is stub to forum
            response.sendRedirect(new StringBuilder("/").append(webapp).append("/").append(FJUrl.DEFAULT_USER).toString());
        }else{
            response.sendRedirect(new StringBuilder("/").append(webapp).append("/").append(FJUrl.DEFAULT_USER).append("/").append(FJUrl.PAGE_404).toString());
        }
    }

}
