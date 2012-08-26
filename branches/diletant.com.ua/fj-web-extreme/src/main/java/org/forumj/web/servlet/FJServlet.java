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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
public class FJServlet extends HttpServlet {
   
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
   
}
