package ua.com.diletant.forum.web.servlet.tool;

import java.io.*;

import javax.servlet.http.*;

public class FJServletTools {

   public static Cookie getCookie(Cookie[] cookies, String name){
      if(name == null || "".equals(name.trim())){
         throw new RuntimeException("Cookie name can't be empty!");
      }
      if(cookies == null){
         return null;
      }
      Cookie result = null;
      for (int i = 0; i < cookies.length; i++) {
         if(cookies[i].getName() != null && cookies[i].getName().equals(name)){
            result = cookies[i];
         }
      }
      return result;
   }
   
   public static void setcookie(HttpServletResponse response, String name, String value, int expire, String path, String domain){
      Cookie cookie = new Cookie(name, value);
      cookie.setDomain(domain);
      cookie.setPath(path);
      cookie.setMaxAge(expire);
      response.addCookie(cookie);
   }
   
   public static String loadResource(String path) throws IOException{
      ClassLoader classLoader = FJServletTools.class.getClassLoader();
      InputStream stream = classLoader.getResourceAsStream(path);
      BufferedReader br = new BufferedReader(new InputStreamReader(stream));
      StringBuffer result = new StringBuffer();
      while(br.ready()){
         result.append(br.readLine());
      }
      return result.toString();
   }
}
