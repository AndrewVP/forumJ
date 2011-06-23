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
package org.forumj.web.servlet.get;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebServlet(urlPatterns = {"/picts/*", "/images/*", "/skin/*", "/banner/*", "/smiles/*"}, name="picts")
public class Images extends HttpServlet {

   private static final long serialVersionUID = -8810949466796099480L;

   String realPath = null;

   /**
    * {@inheritDoc}
    */
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      if (realPath == null){
         realPath = req.getServletContext().getRealPath("/");
      }
      String photoExt = req.getPathInfo().split("\\.")[1];
      String mimeType = "image/" + photoExt.toLowerCase();
      resp.setContentType(mimeType);
      String filePath = realPath + "img" + req.getRequestURI().substring(req.getRequestURI().split("/")[1].length() + 1);
      File file = new File(filePath);
      if (file.exists()){
         resp.setContentLength((int)file.length());
         FileInputStream in = new FileInputStream(file);
         OutputStream out = resp.getOutputStream();
         byte[] buf = new byte[1024];
         int count = 0;
         while ((count = in.read(buf)) >= 0) {
            out.write(buf, 0, count);
         }
         in.close();
         out.close();
      }
   }
}
