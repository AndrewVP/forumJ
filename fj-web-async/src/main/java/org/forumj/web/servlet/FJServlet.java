/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.web.servlet;

import java.io.*;
import java.net.UnknownHostException;
import java.util.*;

import javax.servlet.http.*;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
public class FJServlet extends HttpServlet {
   
   private String ukr;
   private String rus;
   private String exitUrl;

   protected boolean isEmptyParameter(String parameter){
      return (parameter == null || "".equals(parameter));
   }

   protected String gethostbyaddr(String ip) throws UnknownHostException{
//      String result = null;
//      String[] arrIp = ip.split("\\.");
//      byte[] ipAddr = new byte[4];
//      for (int i = 0; i < arrIp.length; i++) {
//         ipAddr[i] = Byte.valueOf(arrIp[i]);
//      }
//      result = InetAddress.getByAddress(ipAddr).getCanonicalHostName();
      return ip;
   }

   protected List<byte[]> getFileAsArray(String fileName) throws IOException {
	      List<byte[]> result = new ArrayList<byte[]>();
	      File file = new File(fileName);
	      if (file.exists()){
	         InputStream in = null;
	         Reader reader = null;
	         try {
	            in = new FileInputStream(file);
	            final byte[] chars = new byte[1024];
	            int read;
	            in = new FileInputStream(file);
	            while ((read = in.read(chars)) > -1) {
	               final byte[] realChars = new byte[read];
	               for (int i = 0; i < read; i++) {
	                  realChars[i] = chars[i];
	               }
	               result.add(realChars);
	            }
	         } finally {
	            if (reader != null) {
	               reader.close();
	            }
	            if (in != null) {
	               in.close();
	            }
	         }
	      }
	      return result;
	   }

   protected void generateLangLinks(HttpServletRequest request){
      Enumeration<String> parameters = request.getParameterNames();
      boolean first = true;
      String query = "";
      while (parameters.hasMoreElements()){
         String parameterName = parameters.nextElement();
         if (!parameterName.equalsIgnoreCase("lang") && !parameterName.equalsIgnoreCase("exit")){
            if(first){
               query = "?";
               first = false;
            }else{
               query += "&";
            }
            query += parameterName + "=" + request.getParameter(parameterName);  
         }
      }
      exitUrl = request.getContextPath() + "/" + request.getRequestURI().split("/")[request.getRequestURI().split("/").length-1] + (query == null || "".equalsIgnoreCase(query.trim()) ? "?exit=0" : query.trim() + "&exit=0");
      ukr = request.getContextPath() + "/" + request.getRequestURI().split("/")[request.getRequestURI().split("/").length-1] + ("".equalsIgnoreCase(query.trim()) ? "?lang=ua" : query.trim() + "&lang=ua");
      rus = request.getContextPath() + "/" + request.getRequestURI().split("/")[request.getRequestURI().split("/").length-1] + ("".equalsIgnoreCase(query.trim()) ? "?lang=ru" : query.trim() + "&lang=ru");
   }

   protected String getUkr() {
      return ukr;
   }

   protected String getRus() {
      return rus;
   }

   protected String getExitUrl() {
      return exitUrl;
   }
   
}
