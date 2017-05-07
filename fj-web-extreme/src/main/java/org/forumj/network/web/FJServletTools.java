package org.forumj.network.web;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.*;

import com.tecnick.htmlutils.htmlentities.HTMLEntities;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.QuotedPrintableCodec;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.exception.InvalidKeyException;
import org.forumj.network.web.resources.ResourcesCache;
import org.forumj.network.web.resources.LocaleString;

public class FJServletTools {

   private static ResourcesCache cache = ResourcesCache.getInstance();


   public static Cookie getCookie(Cookie[] cookies, String name, String path){
      if(name == null || "".equals(name.trim())){
         throw new RuntimeException("Cookie name can't be empty!");
      }
      if(cookies == null){
         return null;
      }
      Cookie result = null;
      for (int i = 0; i < cookies.length; i++) {
         Cookie coockie = cookies[i];
         if(coockie.getName() != null
                 && coockie.getName().equals(name)
                 && coockie.getPath() != null
                 && coockie.getPath().equals(path)
                 ){
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
      StringBuffer result = cache.getTxt(path);
      if (result == null){
         result = loadTxtResource(path);
         cache.putTxt(path, result);
      }
      return result;
   }

   private static StringBuffer loadTxtResource(String path) throws IOException{
      StringBuffer result = new StringBuffer();
      ClassLoader classLoader = FJServletTools.class.getClassLoader();
      try(InputStream stream = classLoader.getResourceAsStream(path);){
         BufferedReader br = new BufferedReader(new InputStreamReader(stream));
         while(br.ready()){
            result.append(br.readLine());
            result.append("\n");
         }
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

   public static void cache(HttpServletResponse response){
      response.setHeader("Expires", "Mon, 26 Jul 1991 05:00:00 GMT");
      response.setHeader("Last-Modified", new Date().toString());
      response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
      response.setHeader("Cache-Control", "post-check=0, pre-check=0");
      response.setHeader("Pragma", "no-cache");
   }

   public static StringBuffer menu(HttpServletRequest request, IUser user, LocaleString locale, boolean index, String webapp, String userURI) throws InvalidKeyException{
      StringBuffer buffer = new StringBuffer();
      Map<String, String[]> parametersMap = request.getParameterMap();
      String queryString = parametersMap.entrySet().stream()
              .filter(entry -> !entry.getKey().equals("lang"))
              .map(entry -> new StringBuilder(entry.getKey()).append("=").append(entry.getValue()[0]).toString())
              .collect(Collectors.joining("&"));


      StringBuilder ukr = new StringBuilder(request.getRequestURI()).append("?").append(queryString).append(!queryString.isEmpty() ? "&lang=ua" : "lang=ua");
      StringBuilder rus = new StringBuilder(request.getRequestURI()).append("?").append(queryString).append(!queryString.isEmpty() ? "&lang=ru" : "lang=ru");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<table class='control'>");
      buffer.append("<tr>");
      buffer.append("<td class='leftTop'></td>");
      buffer.append("<td class='top' colspan='2'></td>");
      buffer.append("<td class='rightTop'></td>");
      buffer.append("</tr>");
      buffer.append("<tr class='heads'>");
      buffer.append("<td class='left'></td>");
      /*Логин есть?*/
      if (!user.isLogined()){
         /*
                  Если Гость
                  Авторизуемся id: код (1-Просто вход с "темы"), gid: номер темы, pg: номер страницы
          */
         buffer.append("<td class=bg align='LEFT'>");
         if(!index){
            /*Список тем*/
            buffer.append("<img src='");
            buffer.append("/");
            if(!webapp.isEmpty()){
               buffer.append(webapp).append("/");
            }
            buffer.append(FJUrl.STATIC).append("/");
            buffer.append(FJUrl.PICTS);

            buffer.append("/index.gif' border='0' class='menuImg'>");
            buffer.append("<a class='mnuforumSm' href='" + "/" + userURI + "/" + FJUrl.INDEX + "'>");
            buffer.append(locale.getString("mess135"));
            buffer.append("</a>");
         }
         /*Новая тема*/
         buffer.append("<img src='");
         buffer.append("/");
         if(!webapp.isEmpty()){
            buffer.append(webapp).append("/");
         }
         buffer.append(FJUrl.STATIC).append("/");
         buffer.append(FJUrl.PICTS);

         buffer.append("/new_top.gif' border='0' class='menuImg'>");
         buffer.append("<a class='mnuforumSm' href='" + "/" + userURI + "/" + FJUrl.LOGIN + "' rel='nofollow'>");
         buffer.append(locale.getString("mess4"));
         buffer.append("</a>");
         /*Новый опрос*/
         buffer.append("<img src='");
         buffer.append("/");
         if(!webapp.isEmpty()){
            buffer.append(webapp).append("/");
         }
         buffer.append(FJUrl.STATIC).append("/");
         buffer.append(FJUrl.PICTS);

         buffer.append("/new_quest.gif' border='0' class='menuImg'>");
         buffer.append("<a class='mnuforumSm' href='" + "/" + userURI + "/" + FJUrl.LOGIN + "' rel='nofollow'>");
         buffer.append(locale.getString("mess3"));
         buffer.append("</a>");
/*
         //
         buffer.append("<img src=");
            buffer.append("/");
            if(!webapp.isEmpty()){
               buffer.append(webapp).append("/");
            }
            buffer.append(FJUrl.STATIC).append("/");
            buffer.append(FJUrl.PICTS);

            buffer.append("'/new_search.gif' border='0' class='menuImg'>");
         buffer.append("<a class='mnuforumSm' href='search.php' rel='nofollow'>");
         buffer.append(locale.getString("mess30"));
         buffer.append("</a>");
*/
         /*Вход*/
         buffer.append("<img src='");
         buffer.append("/");
         if(!webapp.isEmpty()){
            buffer.append(webapp).append("/");
         }
         buffer.append(FJUrl.STATIC).append("/");
         buffer.append(FJUrl.PICTS);

         buffer.append("/key_add.gif' border='0' class='menuImg'>");
         buffer.append("<a class='mnuforumSm' href='" + "/" + userURI + "/" + FJUrl.LOGIN + "?id=1' rel='nofollow'>");
         buffer.append(locale.getString("mess1"));
         buffer.append("</a>");
         /*Регистрация*/
         buffer.append("<img src='");
         buffer.append("/");
         if(!webapp.isEmpty()){
            buffer.append(webapp).append("/");
         }
         buffer.append(FJUrl.STATIC).append("/");
         buffer.append(FJUrl.PICTS);

         buffer.append("/new_user.gif' border='0' class='menuImg'>");
         buffer.append("<a class='mnuforumSm' href='" + "/" + userURI + "/" + FJUrl.REGISTRATION + "?id=1' rel='nofollow'>");
         buffer.append(locale.getString("mess2"));
         buffer.append("</a>");
         buffer.append("</td>");
         buffer.append("<td class=bg align='right'>");
         /*Укр. интерфейс*/
         buffer.append("<a class='mnuforumSm' href='").append(ukr).append("' rel='nofollow'>");
         buffer.append("Українська");
         buffer.append("</a>");
         buffer.append("•");
         /*Рус. интерфейс*/
         buffer.append("<a class='mnuforumSm' href='").append(rus).append("' rel='nofollow'>");
         buffer.append("Русский");
         buffer.append("</a>");
         buffer.append("</td>");
      }else{
         /*Если логин есть*/
         buffer.append("<td class=bg align='LEFT'>");
         /*Ник*/
         buffer.append("<img src='");
         buffer.append("/");
         if(!webapp.isEmpty()){
            buffer.append(webapp).append("/");
         }
         buffer.append(FJUrl.STATIC).append("/");
         buffer.append(FJUrl.PICTS);

         buffer.append("/nick.gif' border='0' class='menuImg'>");
         buffer.append("<span class=nik>");
         buffer.append(HTMLEntities.htmlentities(user.getNick()).replace("\\", ""));
         buffer.append("</span>");
         if(!index){
            /*Список тем*/
            buffer.append("<img src='");
            buffer.append("/");
            if(!webapp.isEmpty()){
               buffer.append(webapp).append("/");
            }
            buffer.append(FJUrl.STATIC).append("/");
            buffer.append(FJUrl.PICTS);

            buffer.append("/index.gif' border='0' class='menuImg'>");
            buffer.append("<a class='mnuforumSm' href='" + "/" + userURI + "/" + FJUrl.INDEX + "'>");
            buffer.append(locale.getString("mess135"));
            buffer.append("</a>");
         }
         /*Новая тема*/
         buffer.append("<img src='");
         buffer.append("/");
         if(!webapp.isEmpty()){
            buffer.append(webapp).append("/");
         }
         buffer.append(FJUrl.STATIC).append("/");
         buffer.append(FJUrl.PICTS);

         buffer.append("/new_top.gif' border='0' class='menuImg'>");
         buffer.append("<a class='mnuforumSm' href='" + "/" + userURI + "/" + FJUrl.NEW_THREAD + "' rel='nofollow'>");
         buffer.append(locale.getString("mess4"));
         buffer.append("</a>");
         /*Новый опрос*/
         buffer.append("<img src='");
         buffer.append("/");
         if(!webapp.isEmpty()){
            buffer.append(webapp).append("/");
         }
         buffer.append(FJUrl.STATIC).append("/");
         buffer.append(FJUrl.PICTS);

         buffer.append("/new_quest.gif' border='0' class='menuImg'>");
         buffer.append("<a class='mnuforumSm' href='" + "/" + userURI + "/" + FJUrl.NEW_QUESTION + "' rel='nofollow'>");
         buffer.append(locale.getString("mess3"));
         buffer.append("</a>");
/*
         //Поиск
         buffer.append("<img src='");
         buffer.append("/");
         if(!webapp.isEmpty()){
            buffer.append(webapp).append("/");
         }
         buffer.append(FJUrl.STATIC).append("/");
         buffer.append(FJUrl.PICTS);

         buffer.append("/new_search.gif' border='0' class='menuImg'>");
         buffer.append("<a class='mnuforumSm' href='search.php' rel='nofollow'>");
         buffer.append(locale.getString("mess30"));
         buffer.append("</a>");
*/
         /* Личные настройки*/
         buffer.append("<img src='");
         buffer.append("/");
         if(!webapp.isEmpty()){
            buffer.append(webapp).append("/");
         }
         buffer.append(FJUrl.STATIC).append("/");
         buffer.append(FJUrl.PICTS);

         buffer.append("/profile.gif' border='0' class='menuImg'>");
         buffer.append("<a class='mnuforumSm' href='" + "/" + userURI + "/" + FJUrl.SETTINGS + "' rel='nofollow'>");
         buffer.append(locale.getString("mess31"));
         buffer.append("</a>");
         /* Переписка*/
         buffer.append("<img src='");
         buffer.append("/");
         if(!webapp.isEmpty()){
            buffer.append(webapp).append("/");
         }
         buffer.append(FJUrl.STATIC).append("/");
         buffer.append(FJUrl.PICTS);

         buffer.append("/email.gif' border='0' class='menuImg'>");
         buffer.append("<a class='mnuforumSm' href='" + "/" + userURI + "/" + FJUrl.SETTINGS + "?id=2' rel='nofollow'>");
         buffer.append(locale.getString("mess23"));
         buffer.append("</a>");
         //Photoalbum
         buffer.append("<img src='");
         buffer.append("/");
         if(!webapp.isEmpty()){
            buffer.append(webapp).append("/");
         }
         buffer.append(FJUrl.STATIC).append("/");
         buffer.append(FJUrl.PICTS);

         buffer.append("/pfotoalbum.png' border='0' class='menuImg'>");
         buffer.append("<a class='mnuforumSm' href='" + "/" + userURI + "/" + FJUrl.SETTINGS + "?id=16' rel='nofollow'>");
         buffer.append(locale.getString("MSG_PHOTOALBUM"));
         buffer.append("</a>");
         /*Выход*/
         StringBuilder exitUrl = new StringBuilder(request.getRequestURI()).append("?").append(queryString).append(!queryString.isEmpty() ? "&exit=0" : "exit=0");
         buffer.append("<img src='");
         buffer.append("/");
         if(!webapp.isEmpty()){
            buffer.append(webapp).append("/");
         }
         buffer.append(FJUrl.STATIC).append("/");
         buffer.append(FJUrl.PICTS);

         buffer.append("/key_delete.gif' border='0' class='menuImg'>");
         buffer.append("<a class='mnuforumSm' href='" + exitUrl + "' rel='nofollow'>");
         buffer.append(locale.getString("mess6"));
         buffer.append("</a>");
         buffer.append("</td>");
         /* Укр. интерфейс*/
         buffer.append("<td class=bg align='right'>");
         buffer.append("<a class='mnuforumSm' href='").append(ukr).append("' rel='nofollow'>");
         buffer.append("Українська");
         buffer.append("</a>");
         buffer.append("•");
         /* Рус. интерфейс*/
         buffer.append("<a class='mnuforumSm' href='").append(rus).append("' rel='nofollow'>");
         buffer.append("Русский");
         buffer.append("</a>");
         buffer.append("</td>");
      }
      buffer.append("<td class=right></td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td class=leftBtm></td>");
      buffer.append("<td class=btm colspan=2></td>");
      buffer.append("<td class=rightBtm></td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</td>");
      buffer.append("</tr>");
      return buffer;
   }

   public static StringBuffer logo(String webapp){
      StringBuffer buffer = new StringBuffer();
      buffer.append("<tr>");
      buffer.append("<td width='100%'>");
      buffer.append("<table border='0' width='100%' style='border-collapse: collapse'>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<a href='/'><img border='0' src='");
      buffer.append("/");
      if(!webapp.isEmpty()){
         buffer.append(webapp).append("/");
      }
      buffer.append(FJUrl.STATIC).append("/");
      buffer.append(FJUrl.IMAGES);

      buffer.append("/all/title.gif'></a><br>");
      buffer.append("</td>");
      buffer.append("<td align=center>");
      buffer.append("<a href='http://www.peoplesproject.com' target=\"_blank\" title='Народний проект. Всеукраїнський центр волонтерів'><img src='");
      buffer.append("/");
      if(!webapp.isEmpty()){
         buffer.append(webapp).append("/");
      }
      buffer.append(FJUrl.STATIC).append("/");
      buffer.append(FJUrl.BANNER);

      buffer.append("/narodny.gif'></a>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table></td></tr>");
      return buffer;
   }

   public static StringBuffer footer(String webapp){
      StringBuffer buffer = new StringBuffer();
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<table width='100%'>");
      buffer.append("<tr>");
      buffer.append("<td align=\"center\">");
      buffer.append("<a href='http://www.peoplesproject.com' target=\"_blank\" title='Народний проект. Всеукраїнський центр волонтерів'><img src='");
      buffer.append("/");
      if(!webapp.isEmpty()){
         buffer.append(webapp).append("/");
      }
      buffer.append(FJUrl.STATIC).append("/");
      buffer.append(FJUrl.BANNER);

      buffer.append("/narodny.gif'></a>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td align='center' width='100%'>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("<table>");
      buffer.append("<tr>");
      buffer.append("<td>");
      buffer.append("</td>");
      buffer.append("</tr>");
      buffer.append("</table>");
      buffer.append("</td>");
      buffer.append("</tr>");
      return buffer;
   }

   public static String fd_head(String result, String webapp){
      result = result.replace("[span class='found']", "<span class='found'>");
      result = result.replace("[/span]", "</span>");
      result = fd_smiles(result, false, webapp);
      result = fd_cenz(result);
      return result;
   }

   public static String fd_head_for_mail(String result, String webapp){
      result = fd_smiles(result, true, webapp);
      result = fd_cenz(result);
      return result;
   }

   public static String removeSlashes(String text){
      while (text.contains("\\\"")){
         text = text.replace("\\\"", "\"");
      }
      return text;
   }

   public static String fd_smiles(String tmptxt, boolean forMail, String webapp) {
      String add = forMail ? "http://www.diletant.com.ua/" : "/";
      webapp = webapp.isEmpty() ? "" : webapp + "/";
      add += webapp;
      tmptxt = tmptxt.replace(":)",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/smile_.gif'>"));
      tmptxt = tmptxt.replace(":(",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/sad.gif'>"));
      tmptxt=tmptxt.replace(":D",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/biggrin.gif'>"));
      tmptxt=tmptxt.replace(":[russian]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/russian.gif'>"));
      tmptxt=tmptxt.replace(":[pioners]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/take_example.gif'>"));
      tmptxt=tmptxt.replace(":[beer]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/drinks.gif'>"));
      tmptxt=tmptxt.replace(":[no-no]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/acute.gif'>"));
      tmptxt=tmptxt.replace(":[nea]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/nea.gif'>"));
      tmptxt=tmptxt.replace(":[babruysk]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/to_babruysk.gif'>"));
      tmptxt=tmptxt.replace(":[ohi]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/girl_sigh.gif'>"));
      tmptxt=tmptxt.replace(":[klizma]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/girl_hospital.gif'>"));
      tmptxt=tmptxt.replace(":[king]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/king2.gif'>"));
      tmptxt=tmptxt.replace(":g)",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/girl_smile.gif'>"));
      tmptxt=tmptxt.replace(":g(",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/girl_sad.gif'>"));
      tmptxt=tmptxt.replace(":[blum]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/girl_blum.gif'>"));
      tmptxt=tmptxt.replace(":[ghaha]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/girl_haha.gif'>"));
      tmptxt=tmptxt.replace(":[gwacko]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/girl_wacko.gif'>"));
      tmptxt=tmptxt.replace(":[gmad]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/girl_mad.gif'>"));
      tmptxt=tmptxt.replace(":[ghide]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/girl_hide.gif'>"));
      tmptxt=tmptxt.replace(":[glove]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/girl_in_love.gif'>"));
      tmptxt=tmptxt.replace(":[gfish]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/girl_prepare_fish.gif'>"));
      tmptxt=tmptxt.replace(":[gcrazy]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/girl_crazy.gif'>"));
      tmptxt=tmptxt.replace(":[mblum]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/blum3.gif'>"));
      tmptxt=tmptxt.replace(":[toclue]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/to_clue.gif'>"));
      tmptxt=tmptxt.replace(":[snooks]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/snooks.gif'>"));
      tmptxt=tmptxt.replace(":[scare]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/scare.gif'>"));
      tmptxt=tmptxt.replace(":[scare2]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/scare2.gif'>"));
      tmptxt=tmptxt.replace(":[gwerewolf]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/girl_werewolf.gif'>"));
      tmptxt=tmptxt.replace(":[gdevil]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/girl_devil.gif'>"));
      tmptxt=tmptxt.replace(":[friends]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/friends.gif'>"));
      tmptxt=tmptxt.replace(":[taunt]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/taunt.gif'>"));
      tmptxt=tmptxt.replace(":[offtopic]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/offtopic.gif'>"));
      tmptxt=tmptxt.replace(":[queen]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/queen.gif'>"));
      tmptxt=tmptxt.replace(":[butcher]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/butcher.gif'>"));
      tmptxt=tmptxt.replace(":[rtfm]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/rtfm.gif'>"));
      tmptxt=tmptxt.replace(":[shok]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/shok.gif'>"));
      tmptxt=tmptxt.replace(":[kr2]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/KidRock_02.gif'>"));
      tmptxt=tmptxt.replace(":[kr5]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/KidRock_05.gif'>"));
      tmptxt=tmptxt.replace(":[kr7]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/KidRock_07.gif'>"));
      tmptxt=tmptxt.replace(":[kr4]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/KidRock_04.gif'>"));
      tmptxt=tmptxt.replace(":[whistle]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/whistle.gif'>"));
      tmptxt=tmptxt.replace(":[whatever]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/whatever_01.gif'>"));
      tmptxt=tmptxt.replace(":[vinsent]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/vinsent.gif'>"));
      tmptxt=tmptxt.replace(":[victory]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/victory.gif'>"));
      tmptxt=tmptxt.replace(":[triniti]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/triniti.gif'>"));
      tmptxt=tmptxt.replace(":[tommy]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/tommy.gif'>"));
      tmptxt=tmptxt.replace(":[to_keep_order]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/to_keep_order.gif'>"));
      tmptxt=tmptxt.replace(":[tease]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/tease.gif'>"));
      tmptxt=tmptxt.replace(":[suicide]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/suicide.gif'>"));
      tmptxt=tmptxt.replace(":[spruce_up]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/spruce_up.gif'>"));
      tmptxt=tmptxt.replace(":[slow]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/slow.gif'>"));
      tmptxt=tmptxt.replace(":[skull]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/skull.gif'>"));
      tmptxt=tmptxt.replace(":[rofl]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/rofl.gif'>"));
      tmptxt=tmptxt.replace(":[read]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/read.gif'>"));
      tmptxt=tmptxt.replace(":[rabbi]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/rabbi.gif'>"));
      tmptxt=tmptxt.replace(":[punish]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/punish.gif'>"));
      tmptxt=tmptxt.replace(":[pooh_door]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/pooh_door.gif'>"));
      tmptxt=tmptxt.replace(":[pioneer]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/pioneer.gif'>"));
      tmptxt=tmptxt.replace(":[ok]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/ok.gif'>"));
      tmptxt=tmptxt.replace(":[new_russian]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/new_russian.gif'>"));
      tmptxt=tmptxt.replace(":[moil]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/moil.gif'>"));
      tmptxt=tmptxt.replace(":[lazy2]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/lazy2.gif'>"));
      tmptxt=tmptxt.replace(":[jc]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/Just_Cuz_11.gif'>"));
      tmptxt=tmptxt.replace(":[hi]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/hi.gif'>"));
      tmptxt=tmptxt.replace(":[help]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/help.gif'>"));
      tmptxt=tmptxt.replace(":[heat]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/heat.gif'>"));
      tmptxt=tmptxt.replace(":[good]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/good.gif'>"));
      tmptxt=tmptxt.replace(":[fuck]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/fuck.gif'>"));
      tmptxt=tmptxt.replace(":[fool]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/fool.gif'>"));
      tmptxt=tmptxt.replace(":[flirt]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/flirt.gif'>"));
      tmptxt=tmptxt.replace(":[dntknw]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/dntknw.gif'>"));
      tmptxt=tmptxt.replace(":[dance2]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/dance2.gif'>"));
      tmptxt=tmptxt.replace(":[brunette]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/brunette.gif'>"));
      tmptxt=tmptxt.replace(":[angel]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/angel.gif'>"));
      tmptxt=tmptxt.replace(":[aleksey_01]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/aleksey_01.gif'>"));
      tmptxt=tmptxt.replace(":[girl_cray2]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/girl_cray2.gif'>"));
      tmptxt=tmptxt.replace(":[girl_cray3]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/girl_cray3.gif'>"));
      tmptxt=tmptxt.replace(":[girl_impossible]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/girl_impossible.gif'>"));
      tmptxt=tmptxt.replace(":[girl_wink]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/girl_wink.gif'>"));
      tmptxt=tmptxt.replace(":[girl_dance]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/girl_dance.gif'>"));
      tmptxt=tmptxt.replace(":[snoozer_18]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/snoozer_18.gif'>"));
      tmptxt=tmptxt.replace(":[drag_10]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/drag_10.gif'>"));
      tmptxt=tmptxt.replace(":[Koshechka_09]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/Koshechka_09.gif'>"));
      tmptxt=tmptxt.replace(":[Koshechka_11]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/Koshechka_11.gif'>"));
      tmptxt=tmptxt.replace(":[libelle_1]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/libelle_1.gif'>"));
      tmptxt=tmptxt.replace(":[connie_6]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/connie_6.gif'>"));
      tmptxt=tmptxt.replace(":[connie_1]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/connie_1.gif'>"));
      tmptxt=tmptxt.replace(":[aftar]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/aftar.gif'>"));
      tmptxt=tmptxt.replace(":[party]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/party.gif'>"));
      tmptxt=tmptxt.replace(":[smoke]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/smoke.gif'>"));
      tmptxt=tmptxt.replace(":[feminist]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/feminist.gif'>"));
      tmptxt=tmptxt.replace(":[spam_light]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/spam_light.gif'>"));
      tmptxt=tmptxt.replace(":[laie_32]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/laie_32.gif'>"));
      tmptxt=tmptxt.replace(":[laie_44]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/laie_44.gif'>"));
      tmptxt=tmptxt.replace(":[laie_48]",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/laie_48.gif'>"));
//      tmptxt=tmptxt.replace(";)",new StringBuilder("<img border='0' src='").append(add).append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/wink3.gif'>"));

      return tmptxt;
   }

   public static String fd_cenz(String result) {
      //TODO Доделать
      // маты
      //
      //   /*хуй*/
      //      $pt[0]="/([а-яА-ЯiIіІєЄїЇ]*[а-пс-фц-яА-ПС-ФЦ-ЯiIіІєЄїЇ][а-яА-ЯiIіІєЄїЇ][хХxX]+[yYуУ]+[eEеЕяЯИийЙ]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //      $pt[1]="/([а-яА-ЯiIіІєЄїЇ]*[рРХх][б-тф-яБ-ТФ-ЯiIіІєЄїЇ][хХxX]+[yYуУ]+[eEеЕяЯИийЙ]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //      $pt[2]="/(\b[а-яА-ЯiIіІєЄїЇ][хХxX]+[yYуУ]+[eEеЕяЯИийЙ]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //      $pt[3]="/(\b[хХxX]+[yYуУ]+[eEеЕяЯИийЙ]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*отсос*/
      //      $pt[4]="/(\b[OoОо0]+[Тт]+[CcСс]+[OoОо0]+[CcСс]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*ебал*/
      //      $pt[5]="/([а-яА-ЯiIіІєЄїЇ]*[а-км-яА-КМ-ЯiIіІєЄїЇ][ЇїЁёЕе]+[Бб6]+[AaАаOoОо0]+[НнНlL]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //      $pt[6]="/([а-яА-ЯiIіІєЄїЇ]*[а-км-яА-ГЕ-КМ-ЯiIіІєЄїЇ][ЇїЁёЕе]+[Бб6]+[AaАа]+[Лл]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //      $pt[7]="/(\b[ЇїЁёЕе]+[Бб6]+[AaАа]+[Лл]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*ебись*/
      //      $pt[8]="/([а-яА-ЯiIіІєЄїЇ]*[ЁёЕе]+[Бб6]+[Ии]+[CcСс]+[Ьь]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*кацап*/
      //      $pt[9]="/([а-яА-ЯiIіІєЄїЇ]*[KkКк]+[AaАаOoОо]+[Цц]+[AaАа]+[Ппn]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*хохол*/
      //      $pt[10]="/([а-яА-ЯiIіІєЄїЇ]*[хХxX]+[aAаАOoОо0]+[хХxX]+[OoОо0]*[ЛлlL]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //      $pt[30]="/(\b[хХxXhH]+[kKкК]+[аАaAоОoO0]+[-.]+[а-яА-Яa-zA-Z0-9їЇіІєЄ]*)/";
      //      $pt[31]="/(\b[kKкК]+[аАaAоОoO0]+[kKкК]+[-]+[лЛlL]+[а-яА-Яa-zA-Z0-9їЇіІєЄ]*)/";
      //
      //   /*майданут*/
      //      $pt[11]="/([а-яА-ЯiIіІєЄїЇ]*[MМм]+[AaАа]+[ИийЙ]+[ДдdD]+[AaАа]+[HНн]+[яЯyYуУ]+[Тт]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*пизд*/
      //      $pt[12]="/([а-яА-ЯiIіІєЄїЇ]*[Пп]+[IiІіИийЙ]+[ЗзzZ]+[ДдdD]+[a-zA-Zа-яА-ЯiIіІєЄїЇ]*)/";
      //   /*залуп*/
      //      $pt[13]="/([а-яА-ЯiIіІєЄїЇ]*[Зз]+[AaАа]+[ЛлlL]+[УуYy]+[Пп]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*уебок*/
      //      $pt[14]="/(\b[уУyYuU]?[ЁёЕеEe]+[Бб6]+[OoОо0]?[KkКкТтT]+[а-яА-ЯiIіІєЄїЇ]*)/";
      ////      $pt[14]="/(\b[а-цш-яА-ЦШ-ЯiIіІєЄїЇ]*[а-км-о-цш-яА-КМ-О-ЦШ-ЯiIіІєЄїЇ][ЁёЕе]+[Бб6]+[OoОо0]+[KkКкТтT]+[а-яА-ЯiIіІєЄїЇ]*)/";
      ////      $pt[14]="/(\b[а-цш-яА-ЦШ-ЯiIіІєЄїЇ]*[ЁёЕе]+[Бб6]+[OoОо0]+[KkКкТтT]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*ебищ*/
      //      $pt[15]="/(\b[а-цш-яА-ЦШ-ЯiIіІєЄїЇ]*[ЁёЕе]+[Бб6]+[иИ]+[шШщЩ]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*пидор*/
      //      $pt[16]="/([а-яА-ЯiIіІєЄїЇ]*[Пп]+[іІИийЙ]+[ДдdD]+[ЕеEeОоOo0AaАа]+[PpРр]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*блядь*/
      ////      $pt[17]="/([а-яА-ЯiIіІєЄїЇ]*[Бб6]+[Ии]*[ЛлlL]+[Яя]+[ДдdD]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //      $pt[29]="/(\b[Бб6]+[Ии]*[ЛлlL]+[Яя]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //
      ////      $pt[17]="/([а-яА-ЯiIіІєЄїЇ]*[СсCc]+[Пп]+[ЕеEe]+[PpРр]+[МмM]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*клитор*/
      //      $pt[18]="/([а-яА-ЯiIіІєЄїЇ]*[KkКк]+[ЛлlL]+[Ии]+[Тт]+[ОоOo0]+[PpРр]+[а-яА-ЯiIіІєЄїЇ]*)/";
      //   /*ебут*/
      //      $pt[19]="/([а-яА-ЯiIіІєЄїЇ]*[а-яА-ЯiIіІєЄїЇ][а-пс-яА-ПС-ЯiIіІєЄїЇ][ЁёЕе]+[Бб6]+[yYуУ]+[Тт][^Тт])/";
      //      $pt[20]="/([а-яА-ЯiIіІєЄїЇ]*[а-вд-яА-ВД-ЯiIіІєЄїЇ][Рр][ЁёЕе]+[Бб6]+[yYуУ]+[Тт][^Тт])/";
      //      $pt[21]="/(\b[а-яА-ЯiIіІєЄїЇ][ЁёЕе]+[Бб6]+[yYуУ]+[Тт][^Тт])/";
      //      $pt[22]="/(\b[ЁёЕе]+[Бб6]+[yYуУ]+[Тт][^Тт])/";
      //   /*жид*/
      //      $pt[23]="/(\b[жЖ]+[.,-_ ;:\"'`~!@#$%^&*()]?[1iIіІиИыЫuU]+[.,-_ ;:\"'`~!@#$%^&*()]?[дДdDgG] )/";
      //      $pt[24]="/(\b[жЖ]+[.,-_ ;:\"'`~!@#$%^&*()]?[1iIіІиИыЫuU]+[.,-_ ;:\"'`~!@#$%^&*()]?[дДdDgG]+[.,-_ ;:\"'`~!@#$%^&*()]?[а-яА-Яa-zA-Z0-9їЇіІєЄ] )/";
      //      $pt[25]="/(\b[жЖ]+[.,-_;:\"'`~!@#$%^&*()]?[1iIіІиИыЫuU]+[.,-_ ;:\"'`~!@#$%^&*()]?[дДdDgG]+[.,-_ ;:\"'`~!@#$%^&*()]?[а-ил-яА-ИЛ-Яa-zA-Z0-9їЇіІєЄ][а-яА-Яa-zA-Z0-9їЇіІєЄ]+)/";
      //      $pt[26]="/([а-нп-яА-ЕЗ-НП-Яa-zA-Z0-9їЇіІєЄ]+[жЖ]+[.,-_;:\"'`~!@#$%^&*()]?[iIіІиИыЫuU]+[.,-_;:\"'`~!@#$%^&*()]?[дДdD]+[.,-_ ;:\"'`~!@#$%^&*()]?[а-яА-Яa-zA-Z0-9їЇіІєЄ]*)/";
      //      $pt[27]="/(\b[жЖ]+[уУ]+[дДdD]+[а-яА-Яa-zA-Z0-9їЇіІєЄ]*)/";
      //      $pt[28]="/(\]\|\[+[iIіІиИыЫ]+[дДdD]+[а-яА-Яa-zA-Z0-9їЇіІєЄ]*)/";
      //
      //
      //      if (strpos(" " + $_SERVER['PHP_SELF'], "index.php")){
      //         $rp="<font color=red>***</font>";
      //      }
      //      else{
      //         $rp="<font color=red><a title='\\1'>***</a></font>";
      //      }
      ////      $rp="\\1";
      //
      //      $_result=preg_replace($pt, $rp, $_result);
      return result;
   }

   public static String fd_body(String result, String webapp) {
      result=fd_bbcode(result);
      result=fd_smiles(result, false, webapp);
      result=fd_cenz(result);
      return result;
   }

   public static String fd_body_for_mail(String result, String webapp) {
      result=fd_bbcode_for_mail(result);
      result=fd_smiles(result, true, webapp);
      result=fd_cenz(result);
      return result;
   }

   public static String fd_bbcode(String body) {
      String result = body.replace("\r\n","<br />").replace("\\", "");
      result = parce(result, "[span class='found']", "[/span]", "<span class='found'>", "</span>");
      // [b] [/b] <b> </b>
      result = parce(result, "[b]", "[/b]", "<b>", "</b>");
      // [i] [/i] <i> </i>
      result = parce(result, "[i]", "[/i]", "<i>", "</i>");
      // [u] [/u] <u> </u>
      result = parce(result, "[u]", "[/u]", "<u>", "</u>");
      // [s] [/s] <s> </s>
      result = parce(result, "[s]", "[/s]", "<s>", "</s>");
      // [size=1] [/size]
      result = parce(result, "[size=1]", "[/size]", "<span class='post1'>", "</span>");
      // [size=2] [/size]
      result = parce(result, "[size=2]", "[/size]", "<span class='post2'>", "</span>");
      // [size=3] [/size]
      result = parce(result, "[size=3]", "[/size]", "<span class='post3'>", "</span>");
      // [size=4] [/size]
      result = parce(result, "[size=4]", "[/size]", "<span class='post4'>", "</span>");
      // [size=5] [/size]
      result = parce(result, "[size=5]", "[/size]", "<span class='post5'>", "</span>");
      // [color=red] [/color]
      result = parce(result, "[color=red]", "[/color]", "<font color='red'>", "</font>");
      // [color=green] [/color]
      result = parce(result, "[color=green]", "[/color]", "<font color='green'>", "</font>");
      // [color=blue] [/color]
      result = parce(result, "[color=blue]", "[/color]", "<font color='blue'>", "</font>");
      // [color=yellow] [/color]
      result = parce(result, "[color=yellow]", "[/color]", "<font color='yellow'>", "</font>");
      // [color=purple] [/color]
      result = parce(result, "[color=purple]", "[/color]", "<font color='purple'>", "</font>");
      // [color=orange] [/color]
      result = parce(result, "[color=orange]", "[/color]", "<font color='orange'>", "</font>");
      // [color=teal] [/color]
      result = parce(result, "[color=teal]", "[/color]", "<font color='teal'>", "</font>");
      // [color=gray] [/color]
      result = parce(result, "[color=gray]", "[/color]", "<font color='gray'>", "</font>");
      // [color=brown] [/color]
      result = parce(result, "[color=brown]", "[/color]", "<font color='brown'>", "</font>");
      // [quote] [/quote]
      result = parce(result, "[quote]", "[/quote]", "<table align='center' width='90%'><tr><td class='tdquote'><span class='quote'>", " </span></td></tr></table>");
      // [img] [/img] (<img border='0' src=') ('>)
      result = fd_href(result);
      result = parce(result, "[img]", "[/img]", "<img border='0' src='", "'>");
      result = parce(result, "[url]", "[/url]", "<a href='", "'>");
      // youtube
      result = parce(result, "[youtube]", "[/youtube]", "<iframe width='640' height='390' frameborder='0' allowfullscreen='' src='http://www.youtube.com/embed/", "'></iframe>");
      return result;
   }

   public static String fd_bbcode_for_mail(String body) {
      String result = body.replace("\r\n","<br />").replace("\\", "");
      // [b] [/b] <b> </b>
      result = parce(result, "[b]", "[/b]", "<b>", "</b>");
      // [i] [/i] <i> </i>
      result = parce(result, "[i]", "[/i]", "<i>", "</i>");
      // [u] [/u] <u> </u>
      result = parce(result, "[u]", "[/u]", "<u>", "</u>");
      // [s] [/s] <s> </s>
      result = parce(result, "[s]", "[/s]", "<s>", "</s>");
      // [size=1] [/size]
      result = parce(result, "[size=1]", "[/size]", "<span style='font-family: Verdana; font-size: 7pt'>", "</span>");
      // [size=2] [/size]
      result = parce(result, "[size=2]", "[/size]", "<span style='font-family: Verdana; font-size: 8pt'>", "</span>");
      // [size=3] [/size]
      result = parce(result, "[size=3]", "[/size]", "<span style='font-family: Verdana; font-size: 10pt'>", "</span>");
      // [size=4] [/size]
      result = parce(result, "[size=4]", "[/size]", "<span style='font-family: Verdana; font-size: 11pt'>", "</span>");
      // [size=5] [/size]
      result = parce(result, "[size=5]", "[/size]", "<span style='font-family: Verdana; font-size: 12pt'>", "</span>");
      // [color=red] [/color]
      result = parce(result, "[color=red]", "[/color]", "<font color='red'>", "</font>");
      // [color=green] [/color]
      result = parce(result, "[color=green]", "[/color]", "<font color='green'>", "</font>");
      // [color=blue] [/color]
      result = parce(result, "[color=blue]", "[/color]", "<font color='blue'>", "</font>");
      // [color=yellow] [/color]
      result = parce(result, "[color=yellow]", "[/color]", "<font color='yellow'>", "</font>");
      // [color=purple] [/color]
      result = parce(result, "[color=purple]", "[/color]", "<font color='purple'>", "</font>");
      // [color=orange] [/color]
      result = parce(result, "[color=orange]", "[/color]", "<font color='orange'>", "</font>");
      // [color=teal] [/color]
      result = parce(result, "[color=teal]", "[/color]", "<font color='teal'>", "</font>");
      // [color=gray] [/color]
      result = parce(result, "[color=gray]", "[/color]", "<font color='gray'>", "</font>");
      // [color=brown] [/color]
      result = parce(result, "[color=brown]", "[/color]", "<font color='brown'>", "</font>");
      // [quote] [/quote]
      result = parce(result, "[quote]", "[/quote]", "<table align='center' width='90%'><tr><td style='width:100%; border-style:ridge; border-width:2px; border-collapse: collapse; background-color:#f7f7f7; border-color:#f1f7fC'><span style='font-family: Verdana; font-size: 8pt; color: #000000'>", " </span></td></tr></table>");
      // [img] [/img] (<img border='0' src=') ('>)
      result = fd_href(result);
      result = parce(result, "[img]", "[/img]", "<img border='0' src='", "'>");
      result = parce(result, "[url]", "[/url]", "<a href='", "'></a>");
      // youtube
      result = parce(result, "[youtube]", "[/youtube]", "<a href='http://www.youtube.com/watch?v=", "'>www.youtube.com</a>");
      return result;
   }

   private static String parce(String source, String startCode, String endCode, String startReplace, String endReplace){
      StringBuffer result = new StringBuffer();
      int startCodePosition = source.indexOf(startCode);
      int endCodePosition = source.indexOf(endCode);
      if (startCodePosition > -1 && endCodePosition > startCodePosition){
         result.append(source.substring(0, startCodePosition));
         result.append(startReplace + source.substring(startCodePosition + startCode.length(), endCodePosition) + endReplace);
         result.append(source.substring(endCodePosition + endCode.length()));
      }else{
         return source;
      }
      return parce(result.toString(), startCode, endCode, startReplace, endReplace);
   }

   public static String fd_href(String href_head){
      String postbody;
      try {
         postbody = href_head.replace("<br", " <br") + " ";
         int pos=0;
         while (postbody.indexOf("http://", pos) > -1) {
            int npos = postbody.indexOf("http://", pos);
            int epos = postbody.indexOf(" ", npos);
            int slpos = postbody.indexOf("/", npos + 8);
            if (npos < 5 || !postbody.substring(npos-5, npos).equals("[img]")){
            	StringBuffer result = new StringBuffer();
            	result.append(postbody.substring(0, npos));
            	result.append("<a href='");
            	result.append(postbody.substring(npos, epos));
            	result.append("'><span class='nick'>");
            	if (slpos == -1 || slpos > epos){
            		slpos = epos;
            	}
            	result.append(postbody.substring(npos + 7, slpos));
            	result.append("</span></a>");
            	result.append(postbody.substring(epos));
            	postbody= result.toString();
            }
            pos=epos;
         }
      } catch (Exception e) {
         System.out.println(href_head);
         e.printStackTrace();
         postbody = href_head;
      }
      return postbody;
   }

   public static StringBuffer fd_button(String mess, String  onClick, String name, String numb){
      StringBuffer result = new StringBuffer();
      result.append("<table id='" + name + "_table' class='bttn" + numb + "' onclick='" + onClick + "'>");
      result.append("<tr>");
      result.append("<td class='bttn" + numb + "LeftTop'></td>");
      result.append("<td class='bttn" + numb + "Top'></td>");
      result.append("<td class='bttn" + numb + "RightTop'></td>");
      result.append("</tr>");
      result.append("<tr>");
      result.append("<td class='bttn" + numb + "Left'></td>");
      result.append("<td class='bttn" + numb + "Bg'>" + mess + "</td>");
      result.append("<td class='bttn" + numb + "Right'></td>");
      result.append("</tr>");
      result.append("<tr>");
      result.append("<td class='bttn" + numb + "LeftBtm'></td>");
      result.append("<td class='bttn" + numb + "Btm'></td>");
      result.append("<td class='bttn" + numb + "RightBtm'></td>");
      result.append("</tr>");
      result.append("</table>");
      return result;
   }

   public static StringBuffer fd_form_add(IUser user) {
      StringBuffer result = new StringBuffer();
      //Команда для обработки
      result.append("<input type='hidden' name='comand' />");
      // Автор
      result.append("<input type=hidden name='IDU' value='");
      result.append(user.getId());
      result.append("'>");
      result.append("<input type=hidden name='AUT' value='");
      result.append(user.getNick());
      result.append("'>");
      // пароль автора
      if (user.getPass2() != null) {
         // кука
         result.append("<input type=hidden name='PS2' value='");
         result.append(user.getPass2());
         result.append("'>");
      }else{
         // не кука
         result.append("<input type=hidden name='PS1' value='");
         result.append(user.getPass());
         result.append("'>");
      }
      return result;
   }

   public static StringBuffer fd_input(String name, String value, String size, String numb){
      StringBuffer input= new StringBuffer();
      input.append("<table id='" + name + "_table' class='input" + numb + "'>");
      input.append("<tr>");
      input.append("<td class='input" + numb + "LeftTop'></td>");
      input.append("<td class='input" + numb + "Top'></td>");
      input.append("<td class='input" + numb + "RightTop'></td>");
      input.append("</tr>");
      input.append("<tr>");
      input.append("<td class='input" + numb + "Left'></td>");
      input.append("<td class='input" + numb + "Bg'><input class='input" + numb + "' type=text name='" + name + "' size='" + size + "' value='" + value + "' maxlength='120'></td>");
      input.append("<td class='input" + numb + "Right'></td>");
      input.append("</tr>");
      input.append("<tr>");
      input.append("<td class='input" + numb + "LeftBtm'></td>");
      input.append("<td class='input" + numb + "Btm'></td>");
      input.append("<td class='input" + numb + "RightBtm'></td>");
      input.append("</tr>");
      input.append("</table>");
      return input;
   }

   public static int fd_timezone_hr(int id_timezone){
      int result = 0;
      switch(id_timezone) {
         case 1:
            // (GMT - 12:00)
            result = -14;
            break;
         case 2:
            // (GMT - 11:00)
            result = -13;
            break;
         case 3:
            // (GMT - 10:00)
            result = -12;
            break;
         case 4:
            // (GMT - 9:00)
            result = -11;
            break;
         case 5:
            // (GMT - 8:00)
            result = -10;
            break;
         case 6:
            // (GMT - 7:00)
            result = -9;
            break;
         case 7:
            // (GMT - 6:00)
            result = -8;
            break;
         case 8:
            // (GMT - 5:00)
            result = -7;
            break;
         case 9:
            // (GMT - 4:00)
            result = -6;
            break;
         case 10:
            // (GMT - 3:30)
            result = -5;
            break;
         case 11:
            // (GMT - 3:00)
            result = -5;
            break;
         case 12:
            // (GMT - 2:00)
            result = -4;
            break;
         case 13:
            // (GMT - 1:00)
            result = -3;
            break;
         case 14:
            // (GMT)
            result = -2;
            break;
         case 15:
            // (GMT + 1:00)
            result = -1;
            break;
         case 16:
            // (GMT + 2:00)
            result = 0;
            break;
         case 17:
            // (GMT + 3:00)
            result = 1;
            break;
         case 18:
            // (GMT + 3:30)
            result = +1;
            break;
         case 19:
            // (GMT + 4:00)
            result = 2;
            break;
         case 20:
            // (GMT + 4:30)
            result = 2;
            break;
         case 21:
            // (GMT + 5:00)
            result = 3;
            break;
         case 22:
            // (GMT + 5:30)
            result = 3;
            break;
         case 23:
            // (GMT + 6:00)
            result = 4;
            break;
         case 24:
            // (GMT + 7:00)
            result = 5;
            break;
         case 25:
            // (GMT + 8:00)
            result = 6;
            break;
         case 26:
            // (GMT + 9:00)
            result = 7;
            break;
         case 27:
            // (GMT + 9:30)
            result = 7;
            break;
         case 28:
            // (GMT + 10:00)
            result = 8;
            break;
         case 29:
            // (GMT + 11:00)
            result = 9;
            break;
         case 30:
            // (GMT + 12:00)
            result = 10;
            break;
      }
      return result;

   }

   public static int fd_timezone_mn(int id_timezone){
      int result = 0;
      switch(id_timezone) {
         case 10:
            // (GMT - 3:30)
            result = -30;
            break;
         case 18:
            // (GMT + 3:30)
            result = 30;
            break;
         case 20:
            // (GMT + 4:30)
            result = 30;
            break;
         case 22:
            // (GMT + 5:30)
            result = 30;
            break;
         case 27:
            // (GMT + 9:30)
            result = 30;
            break;
         default:
            result = 0;
      }
      return result;
   }

   public static StringBuffer smiles_add(String mess11, String webapp){
      StringBuffer result = new StringBuffer();
      result.append("<table>");
      result.append("<tr>");
      result.append("<td id='td1'>");
      /*Первая строчка Мальчики*/
      result.append("<a href='#' onclick=\"smile(':)'); return false;\" rel='nofollow'>");
      result.append("<img border='0' src='/");
      if(!webapp.isEmpty()){
         result.append(webapp).append("/");
      }
      result.append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/smile_.gif'>");
      result.append("</a>");
      result.append("<a href='#' onclick=\"smile(':('); return false;\" rel='nofollow'>");
      result.append("<img border='0' src='/");
      if(!webapp.isEmpty()){
         result.append(webapp).append("/");
      }
      result.append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/sad.gif'>");
      result.append("</a>");
      result.append("<a href='#' onclick=\"smile(';)'); return false;\" rel='nofollow'>");
      result.append("<img border='0' src='/");
      if(!webapp.isEmpty()){
         result.append(webapp).append("/");
      }
      result.append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/wink3.gif'>");
      result.append("</a>");
      result.append("</td>");
      result.append("</tr>");
      /*Вторая строчка Девочки*/
      result.append("<tr>");
      result.append("<td id='td2'>");
      result.append("<a href='#' onclick=\"smile(':g)'); return false;\" rel='nofollow'><img border='0' src='/");
      if(!webapp.isEmpty()){
         result.append(webapp).append("/");
      }
      result.append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/girl_smile.gif'></a>");
      result.append("<a href='#' onclick=\"smile(':g('); return false;\" rel='nofollow'><img border='0' src='/");
      if(!webapp.isEmpty()){
         result.append(webapp).append("/");
      }
      result.append(FJUrl.STATIC).append("/").append(FJUrl.SMILES).append("/girl_sad.gif'></a>");
      result.append("</td>");
      result.append("</tr>");
      /*Третья строчка Остальное*/
      result.append("<tr>");
      result.append("<td id='td3'></td>");
      result.append("</tr>");
      result.append("<tr>");
      result.append("<td align=center>");
      /* Кнопка "Показать все"*/
      result.append(fd_button(mess11,"viewsml();","btn1", "1"));
      result.append("<br><br><br>");
      result.append("</td>");
      result.append("</tr>");
      result.append("</table>");
      return result;
   }

   public static StringBuffer autotags_add(String webapp){
      StringBuffer result = new StringBuffer();
      StringBuilder buffer = new StringBuilder("/");
      if(!webapp.isEmpty()){
         buffer.append(webapp).append("/");
      }
      buffer.append(FJUrl.STATIC).append("/");
      result.append("<img src='").append(buffer).append("skin/standart/picts/b.gif' onclick=\"InsertTags('[b]','[/b]')\" alt='Полужирный текст'>");
      result.append("<img src='").append(buffer).append("skin/standart/picts/i.gif' onclick=\"InsertTags('[i]','[/i]')\" alt='Курсивный текст'>");
      result.append("<img src='").append(buffer).append("skin/standart/picts/u.gif' onclick=\"InsertTags('[u]','[/u]')\" alt='Подчеркнутый текст'>");
      result.append("<img src='").append(buffer).append("skin/standart/picts/s.gif' onclick=\"InsertTags('[s]','[/s]')\" alt='Зачеркнутый текст'>");
      result.append("&nbsp;");
      result.append("<img src='").append(buffer).append("skin/standart/picts/1.gif' onclick=\"InsertTags('[size=1]','[/size]')\" alt='Размер шрифта 1'>");
      result.append("<img src='").append(buffer).append("skin/standart/picts/2.gif' onclick=\"InsertTags('[size=2]','[/size]')\" alt='Размер шрифта 2'>");
      result.append("<img src='").append(buffer).append("skin/standart/picts/3.gif' onclick=\"InsertTags('[size=3]','[/size]')\" alt='Размер шрифта 3'>");
      result.append("<img src='").append(buffer).append("skin/standart/picts/4.gif' onclick=\"InsertTags('[size=4]','[/size]')\" alt='Размер шрифта 4'>");
      result.append("<img src='").append(buffer).append("skin/standart/picts/5.gif' onclick=\"InsertTags('[size=5]','[/size]')\" alt='Размер шрифта 5'>");
      result.append("&nbsp;");
      result.append("<img src='").append(buffer).append("skin/standart/picts/img.gif' onclick=\"InsertTags('[img]','[/img]')\" alt='Вставить картинку'>");
      result.append("<img src='").append(buffer).append("skin/standart/picts/youtube.gif' onclick=\"InsertTags('[youtube]','[/youtube]')\" alt='Вставить видео youtube'>");
      result.append("<img src='").append(buffer).append("skin/standart/picts/quote.gif' onclick=\"InsertTags('[quote]','[/quote]')\" alt='Добавить рамку'>");
      result.append("<br>");
      result.append("<SELECT style='margin-top:1px; font:11px verdana; border: solid 1px black;' name=fcolor  onchange=\"javascript: InsertTags('[color=' + document.post.fcolor.options[document.post.fcolor.selectedIndex].value + ']', '[/color]'); document.post.fcolor.selectedIndex=0;\">");
      result.append("<OPTION style='color:black' value='black'>Black</OPTION>");
      result.append("<OPTION style='color:red' value='red'>Red</OPTION>");
      result.append("<OPTION style='color:green' value='green'>Green</OPTION>");
      result.append("<OPTION style='color:blue' value='blue'>Blue</OPTION>");
      result.append("<OPTION style='color:yellow' value='yellow'>Yellow</OPTION>");
      result.append("<OPTION style='color:purple' value='purple'>Purple</OPTION>");
      result.append("<OPTION style='color:orange' value='orange'>Orange</OPTION>");
      result.append("<OPTION style='color:teal' value='teal'>Teal</OPTION>");
      result.append("<OPTION style='color:brown' value='brown'>Brown</OPTION>");
      result.append("<OPTION style='color:gray' value='gray'>Gray</OPTION>");
      result.append("</SELECT>");
      return result;
   }

   public static StringBuffer errorOut(Throwable e){
      StringBuffer buffer = new StringBuffer();
      buffer.append("<html>");
      buffer.append("<head>");
      buffer.append("<meta http-equiv='content-type' content='text/html; charset=utf-8'>");
      buffer.append("<title>");
      buffer.append("Error");
      buffer.append("</title>");
      buffer.append("</head>");
      // Цвет фона страницы
      buffer.append("<body bgcolor=#EFEFEF>");
      buffer.append("<b>An error occurred:</b><br />");
      buffer.append("<b>" + e.getMessage() + "</b><br />");
      StackTraceElement[] elements = e.getStackTrace();
      for (int i = 0; i < elements.length; i++) {
         StackTraceElement element = elements[i];
         buffer.append(element.toString() + "<br />");
      }
      buffer.append("</body>");
      buffer.append("</html>");
      return buffer;
   }

   public static StringBuffer twoAnswersReminedOut(){
      StringBuffer buffer = new StringBuffer();
      buffer.append("<html>");
      buffer.append("<head>");
      buffer.append("<meta http-equiv='content-type' content='text/html; charset=windows-1251'>");
      buffer.append("<meta http-equiv='Refresh' content='5; url=" + FJUrl.INDEX + "'>");
      buffer.append("<title>");
      buffer.append("Мы не во всем Дилетанты!");
      buffer.append("</title>");
      buffer.append("</head>");
      // Цвет фона страницы
      buffer.append("<body bgcolor=#EFEFEF>");
      buffer.append("<font size='5'><b>Надо предложить хотя бы два варианта ответов!</b></font>");
      buffer.append("</body>");
      buffer.append("</html>");
      return buffer;
   }

   public static StringBuffer successPostOut(String seconds, String url){
      StringBuffer buffer = new StringBuffer();
      buffer.append("<html>");
      buffer.append("<head>");
      buffer.append("<meta http-equiv='Refresh' content='" + seconds + "; url=" + url + "'>");
      buffer.append("<title>");
      buffer.append("</title>");
      buffer.append("</head>");
      buffer.append("<body>");
      buffer.append("</body>");
      buffer.append("</html>");
      return buffer;
   }

   public static String dateToString(Date date, String pattern){
      SimpleDateFormat sdf = new SimpleDateFormat(pattern);
      return sdf.format(date);
   }
}
