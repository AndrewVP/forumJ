package org.forumj.web.servlet.tool;

import java.io.*;

import javax.servlet.http.*;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.QuotedPrintableCodec;
import org.forumj.common.exception.InvalidKeyException;
import org.forumj.tool.LocaleString;

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

   public static void setcookie(HttpServletResponse response, String name, String value, int expire, String path, String domain) throws EncoderException{
      QuotedPrintableCodec codec = new QuotedPrintableCodec();
      String qpValue = value != null ? codec.encode(value) : null;
      Cookie cookie = new Cookie(name, qpValue);
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
         result.append(br.readLine() + "\n");
      }
      return result;
   }
   
   public static StringBuffer loadJavaScript(String path) throws IOException{
      StringBuffer result = new StringBuffer();
      result.append("<script type='text/javascript'>");
      result.append("\n// <!--\n");
      result.append(loadResource(path));
      result.append("\n// -->\n");
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

   public static StringBuffer quest_submit(LocaleString locale) throws IOException, InvalidKeyException{
      StringBuffer result = new StringBuffer();
      result.append("<script type='text/javascript'>");
      result.append("// <!--\n");
      result.append("function quest_submit(comand){");
      result.append("if (document.post.T.value.replace(/(^\\s*)|(\\s*$)/g, \"\").length==0){");
      result.append("alert('" + locale.getString("mess128") + "');");
      result.append("}else if (document.post.Q.value.replace(/(^\\s*)|(\\s*$)/g, \"\").length==0){");
      result.append("alert('" + locale.getString("mess130") + "');");
      result.append("}else if (document.post.A2.value.replace(/(^\\s*)|(\\s*$)/g, \"\").length==0){");
      result.append("alert('" + locale.getString("mess129") + "');");
      result.append("}else{");
      result.append("var x1=1;");
      result.append("var x2=0;");
      result.append("while (document.getElementById('P'+x1.toFixed(0))!=null){");
      result.append("if (document.getElementById('P'+x1.toFixed(0)).value.replace(/(^\\s*)|(\\s*$)/g, \"\").length!=0)");
      result.append("{");
      result.append("x2+=1;");
      result.append("}");
      result.append("x1+=1;");
      result.append("}");
      result.append("if (x2<2)");
            result.append("{");
         result.append("alert('" + locale.getString("mess131") + "');");
         result.append("}else{");
         result.append("document.post.comand.value=comand;");
         result.append("document.post.submit();");
         result.append("}");
         result.append("}");
         result.append("}");
      result.append("\n// -->");
      result.append("</script>");
      return result;
   }
   
   public static StringBuffer send_submit(LocaleString locale) throws IOException, InvalidKeyException{
      StringBuffer result = new StringBuffer();
      result.append("<script type='text/javascript'>");
      result.append("// <!--\n");
      result.append("function send_submit(comand){");
      result.append("if (document.post.RCVR.value.replace(/(^\\s*)|(\\s*$)/g, \"\").length==0){");
      result.append("alert('" + locale.getString("mess132") + "');");
      result.append("}else if (document.post.NHEAD.value.replace(/(^\\s*)|(\\s*$)/g, \"\").length==0){");
      result.append("alert('" + locale.getString("mess128") + "');");
      result.append("}else if (document.post.A2.value.replace(/(^\\s*)|(\\s*$)/g, \"\").length==0){");
      result.append("alert('" + locale.getString("mess129") + "');");
      result.append("}else{");
      result.append("document.post.comand.value=comand;");
      result.append("document.post.submit();}}");
      result.append("\n// -->");
      result.append("</script>");
      return result;
   }
   
   public static StringBuffer new_submit(String mess128) throws IOException{
      StringBuffer result = new StringBuffer();
      result.append("<script type='text/javascript'>");
      result.append("// <!--\n");
      result.append("function new_submit(comand){");
      result.append("if (document.post.NHEAD.value.replace(/(^\\s*)|(\\s*$)/g, \"\").length==0){");
      result.append("alert('" + mess128 + "');");
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
      if (uas != null){
          if (uas.contains("StackRambler") ||
                  uas.contains("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36") ||
                  uas.contains("bingbot") ||
                  uas.contains("DotBot") ||
                  uas.contains("Speedy Spider") ||
                  uas.contains("Exabot") ||
                  uas.contains("METASpider") ||
                  uas.contains("Googlebot") ||
                  uas.contains("Yandex") ||
                  uas.contains("msnbot") ||
                  uas.contains("Jyxobot") ||
                  uas.contains("METASpider") ||
                  uas.contains("bingbot") ||
                  uas.contains("Ezooms") ||
                  uas.contains("AhrefsBot") ||
                  uas.contains("Mail.RU_Bot") ||
                  uas.contains("MJ12bot") ||
                  uas.contains("Mail.Ru") ||
                  uas.contains("AportCatalogRobot") ||
                  uas.contains("MJ12bot") ||
                  uas.contains("linkdex.com") ||
                  uas.contains("ia_archiver") ||
                  uas.contains("Ralocobot") ||
                  uas.contains("TurnitinBot") ||
                  uas.contains("Search17Bot") ||
                  uas.contains("SiteBot") ||
                  uas.contains("Snoopy") ||
                  uas.contains("HuaweiSymantecSpider") ||
                  uas.contains("vBSEO") ||
                  uas.contains("pirst") ||
                  uas.contains("GarlikCrawler") ||
                  uas.contains("NetcraftSurveyAgent") ||
                  uas.contains("Synapse") ||
                  uas.contains("DLE_Spider") ||
                  uas.contains("MLBot") ||
                  uas.contains("Seznam screenshot-generator") ||
                  uas.contains("rootlink") ||
                  uas.contains("Wget") ||
                  uas.contains("curl") ||
                  uas.contains("python-requests") ||
                  uas.contains("Slurp")){
              return true;
          }
      }
      return false;
   }
   
}
