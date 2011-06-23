package org.forumj.web.servlet.tool;

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

   public static StringBuffer loadResource(String path) throws IOException{
      ClassLoader classLoader = FJServletTools.class.getClassLoader();
      InputStream stream = classLoader.getResourceAsStream(path);
      BufferedReader br = new BufferedReader(new InputStreamReader(stream));
      StringBuffer result = new StringBuffer();
      while(br.ready()){
         result.append(br.readLine());
      }
      return result;
   }
   
   public static StringBuffer loadJavaScript(String path) throws IOException{
      StringBuffer result = new StringBuffer();
      result.append("<script type='text/javascript'>");
      result.append("// <!--\n");
      result.append(loadResource(path));
      result.append("\n// -->");
      result.append("</script>");
      return result;
   }

   public static StringBuffer loadCSS(String path) throws IOException{
      StringBuffer result = new StringBuffer();
      result.append("<style type='text/css'>");
      result.append(loadResource(path));
      result.append("</style>");
      return result;
   }
   
   public static StringBuffer post_submit(String mess128) throws IOException{
      StringBuffer result = new StringBuffer();
      result.append("<script type='text/javascript'>");
      result.append("// <!--\n");
      result.append("function post_submit(comand){");
      result.append("if (document.post.NHEAD.value.replace(/(^\\s*)|(\\s*$)/g, \"\").length==0){");
      result.append("alert('" + mess128 + "');");
      result.append("");
      result.append("}else if (document.post.A2.value.replace(/(^\\s*)|(\\s*$)/g, \"\").length==0){");
      result.append("alert('" + mess128 + "');");
      result.append("}else{");
      result.append("document.post.comand.value=comand;");
      result.append("document.post.submit();}}");
      result.append("\n// -->");
      result.append("</script>");
      return result;
   }
   
   public static boolean isRobot(HttpServletRequest request){
      String uas = request.getHeader("user-agent");
      if (uas.contains("StackRambler") ||
            uas.contains("Googlebot") ||
            uas.contains("Yandex") ||
            uas.contains("msnbot") ||
            uas.contains("Jyxobot") ||
            uas.contains("Slurp")){
         return true;
      }
      return false;

   }
}
