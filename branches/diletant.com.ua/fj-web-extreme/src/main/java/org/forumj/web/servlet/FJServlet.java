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

import java.net.*;

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
      String result = null;
//      String[] arrIp = ip.split("\\.");
//      byte[] ipAddr = new byte[4];
//      for (int i = 0; i < arrIp.length; i++) {
//         ipAddr[i] = Byte.valueOf(arrIp[i]);
//      }
//      result = InetAddress.getByAddress(ipAddr).getCanonicalHostName();
      return ip;
   }
   
}
