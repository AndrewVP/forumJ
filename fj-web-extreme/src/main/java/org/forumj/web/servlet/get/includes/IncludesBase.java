/*
 */
package org.forumj.web.servlet.get.includes;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.forumj.web.servlet.FJServlet;


/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
public class IncludesBase extends FJServlet {
   
   private String contentType = null;
   
   private String realPath = null;
   
   private Map<String, List<byte[]>> cache = new HashMap<String, List<byte[]>>();
   
   private Object cacheMonitor = new Object(); 

   /**
    * {@inheritDoc}
    */
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      if (realPath == null){
         realPath = req.getServletContext().getRealPath("/");
      }
      resp.setContentType(contentType);
      String fileKey = req.getRequestURI().substring(req.getRequestURI().split("/")[1].length() + 1);
      List<byte[]> resource = cache.get(fileKey);
      if (resource == null){
         synchronized (cacheMonitor) {
            resource = cache.get(fileKey);
            if (resource == null){
               String filePath = realPath + "WEB-INF/" + fileKey;
               resource = getFileAsArray(filePath);
               cache.put(fileKey, resource);
            }
         }
      }
      OutputStream out = resp.getOutputStream();
      for (int i = 0; i < resource.size(); i++) {
         byte[] potion = resource.get(i);
         out.write(potion, 0 , potion.length);
      }
   }

   protected void setContentType(String contentType) {
      this.contentType = contentType;
   }
}
