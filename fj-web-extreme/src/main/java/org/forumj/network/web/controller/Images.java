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

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.forumj.network.web.FJUrl;
import org.forumj.common.config.FJConfiguration;
import org.forumj.common.db.entity.Image;
import org.forumj.common.db.service.FJServiceHolder;
import org.forumj.common.db.service.ImageService;
import org.forumj.image.ImageTools;
import org.forumj.network.web.resources.ResourcesCache;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class Images{

   private Logger logger = LogManager.getLogger("org.forumj.web.servlet");


   private String realPath = null;

   private ResourcesCache cache = ResourcesCache.getInstance(); 
   
   private Date dateHeader = new Date();

   private String avatarsContextDir;
   private String imagesContextDir;
   private String fjHomeDir;

   public void init(String realPath) throws ServletException {
      try {
         avatarsContextDir = FJConfiguration.getConfig().getString(FJConfiguration.AVATARS_CONTEXT_DIR);
         imagesContextDir = FJConfiguration.getConfig().getString(FJConfiguration.IMAGES_CONTEXT_DIR);
         fjHomeDir = FJConfiguration.getConfig().getString(FJConfiguration.HOME_DIR);
         this.realPath = realPath;
      }catch (Exception e){
         throw new ServletException(e);
      }
   }

   public void doGet(HttpServletRequest req, HttpServletResponse resp, String webappName) throws Exception {
      String uri = req.getRequestURI();
      while (uri.startsWith("/")){
         uri = uri.substring(1);
      }
      if (webappName != null && !webappName.isEmpty()){
         if (uri.startsWith(webappName)){
            uri = uri.substring(webappName.length());
         }
      }

      String photoExt = null;
      // TODO remake it
      if (uri != null){
         String[] split = uri.split("\\.");
         if (split.length > 1){
            photoExt = split[1];
         }
      }
      resp.setDateHeader("Last-Modified", dateHeader.getTime());
      resp.setDateHeader("Expires", dateHeader.getTime() + 600000000);
      resp.setHeader("max-age", "600000");
      resp.setHeader("Cache-Control", "private");
      String fileKey = uri.substring(uri.split("/")[1].length() + 1);
      String filePath = null;
      Long imageId = null;
      Image image = null;
      ImageService imageService = FJServiceHolder.getImageService();
      if (fileKey.startsWith("/" + avatarsContextDir)){
         filePath = fjHomeDir + File.separator + fileKey;
      }else if (fileKey.startsWith("/" + FJUrl.PHOTO)){
         //TODO make Constant
         String idParameter = req.getParameter("id");
         imageId = Long.valueOf(idParameter);
         image = imageService.getImage(imageId);
         filePath = image.getPath();
         photoExt = image.getExtension();
      }else{
         filePath = realPath + "img" + fileKey;
      }
      String mimeType = "image/" + photoExt.toLowerCase();
      resp.setContentType(mimeType);
      List<byte[]> resource = cache.get(fileKey);
      if (resource == null){
         resource = getFileAsArray(filePath);
         if (resource.size() != 0){
            cache.put(fileKey, resource);
         }else if (fileKey.startsWith("/photo")){
            // probably home dir was moved
            String pathToImage = ImageTools.makePath(imageId, fjHomeDir + File.separator + imagesContextDir);
            filePath = ImageTools.makeImageName(imageId, pathToImage, photoExt);
            resource = getFileAsArray(filePath);
            if (resource.size() != 0){
               cache.put(fileKey, resource);
               image.setPath(filePath);
               imageService.update(image);
            }else{
               //TODO Make "missed image"
            }
         }
      }
      OutputStream out = resp.getOutputStream();
      for (byte[] potion : resource) {
         out.write(potion);
      }
   }

   protected List<byte[]> getFileAsArray(String fileName) throws IOException {
      List<byte[]> result = new LinkedList<>();
      File file = new File(fileName);
      if (file.exists()){
         try (InputStream in = new FileInputStream(file);) {
            final byte[] chars = new byte[1024];
            int read;
            byte[] realChars;
            while ((read = in.read(chars)) > 0) {
               realChars = Arrays.copyOf(chars, read);
               result.add(realChars);
            }
         }
      }
      return result;
   }
}
