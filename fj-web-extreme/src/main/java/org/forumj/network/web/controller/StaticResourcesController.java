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
import org.forumj.network.web.URL;
import org.forumj.network.web.resources.ResourcesBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class StaticResourcesController {

    private static String bootstrapMinCSS = "bootstrap.min.css";
    private static String bootstrapThemeMinCSS = "bootstrap-theme.min.css";
    private static String html5shivMinJS = "html5shiv.min.js";
    private static String respondMinJS = "respond.min.js";
    private static String jquery_3_2_1_minJS = "jquery-3.2.1.min.js";
    private static String bootstrapMinJS = "bootstrap.min.js";


    public void doGet(HttpServletRequest request, HttpServletResponse response, String webapp, URL url) throws Exception {
        String resource = url.getName();
        if (resource.endsWith(bootstrapMinCSS)){
            response.setContentType("text/css; charset=UTF-8");
            StringBuilder robots = ResourcesBuilder.getBootstrapMinCSS();
            PrintWriter writer = response.getWriter();
            String out = robots.toString();
            writer.write(out);
        }else if (resource.endsWith(bootstrapThemeMinCSS)){
            response.setContentType("text/css; charset=UTF-8");
            StringBuilder robots = ResourcesBuilder.getBootstrapThemeMinCSS();
            PrintWriter writer = response.getWriter();
            String out = robots.toString();
            writer.write(out);
        }else if (resource.endsWith(html5shivMinJS)){
            response.setContentType("text/javascript; charset=UTF-8");
            StringBuilder robots = ResourcesBuilder.getHtml5shivMinJS();
            PrintWriter writer = response.getWriter();
            String out = robots.toString();
            writer.write(out);
        }else if (resource.endsWith(respondMinJS)){
            response.setContentType("text/javascript; charset=UTF-8");
            StringBuilder robots = ResourcesBuilder.getRespondMinJS();
            PrintWriter writer = response.getWriter();
            String out = robots.toString();
            writer.write(out);
        }else if (resource.endsWith(jquery_3_2_1_minJS)){
            response.setContentType("text/javascript; charset=UTF-8");
            StringBuilder robots = ResourcesBuilder.getJquery_3_2_1_minJS();
            PrintWriter writer = response.getWriter();
            String out = robots.toString();
            writer.write(out);
        }else if (resource.endsWith(bootstrapMinJS)){
            response.setContentType("text/javascript; charset=UTF-8");
            StringBuilder robots = ResourcesBuilder.getBootstrapMinJS();
            PrintWriter writer = response.getWriter();
            String out = robots.toString();
            writer.write(out);
        }else{
            response.sendRedirect(new StringBuilder("/").append(webapp).append("/").append(FJUrl.DEFAULT_USER).append("/").append(FJUrl.PAGE_404).toString());
        }
    }

}
