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

import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
public class FJServlet{

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
		   try(InputStream in = new FileInputStream(file);) {
			   final byte[] chars = new byte[1024];
			   int read;
			   do  {
				   read = in.read(chars);
				   if (read > 0){
					   final byte[] realChars;
					   if (read == 1024){
						   realChars = chars.clone();
					   }else{
						   realChars = Arrays.copyOf(chars, read);
					   }
					   result.add(realChars);
				   }
			   }while(read > -1);
		   }
	   }
	   return result;
   }

    protected void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws ServletException, IOException {

    }


}
