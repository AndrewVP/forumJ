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
package org.forumj.email;

import static org.forumj.tool.Diletant.*;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.FJUrl;
import org.forumj.common.HttpParameters;
import org.forumj.common.config.FJConfiguration;
import org.forumj.common.db.entity.*;
import org.forumj.common.db.service.*;
import org.forumj.common.exception.InvalidKeyException;
import org.forumj.common.tool.*;
import org.forumj.common.web.Locale;
import org.forumj.tool.LocaleString;

import com.tecnick.htmlutils.htmlentities.HTMLEntities;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJEMail {

   public static void sendSuscribedPost(IFJPost post, IUser author) throws InvalidKeyException, AddressException, ConfigurationException, MessagingException, SQLException, IOException{
      SubscribeService subscribeService = FJServiceHolder.getSubscribeService();
      List<IUser> subscribers = subscribeService.getSubscribedUsers(post.getThreadId(), author.getId());
      Map<Locale, String> posts = new HashMap<Locale, String>();
      String postString = "";
      Locale localeName = null;
      LocaleString locale = null;
      for (IUser user : subscribers) {
         String mail = user.getEmail();
         if (mail != null && !mail.trim().isEmpty()){
            Locale userLocale = user.getLanguge();
            if (localeName == null){
               localeName = userLocale;
               locale = new LocaleString(localeName, "messages", localeName);
               postString = prepareSubscribeMail(post, author, locale);
            }else if (userLocale != localeName){
               postString = posts.get(userLocale);
               if (postString == null){
                  localeName = userLocale;
                  locale = new LocaleString(localeName, "messages", localeName);
                  postString = prepareSubscribeMail(post, author, locale);
                  posts.put(localeName, postString);
               }
            }
            sendMail(mail, FJConfiguration.getConfig().getString("mail.from.subscribe"), FJConfiguration.getConfig().getString("mail.smtp.host"), locale.getString("MSG_SUBSCRIBE_HEADER"), postString);
         }
      }

      
   }
   
   private static String prepareSubscribeMail(IFJPost post, IUser author, LocaleString locale) throws InvalidKeyException{
      StringBuffer buffer = new StringBuffer();
      Time postTime = new Time(post.getHead().getCreateTime());
      buffer.append("<html><head><meta http-equiv='content-type' content='text/html; charset=UTF-8'></head><body style='background-color:#EFEFEF;'><table>");
      buffer.append("<tr>");
      buffer.append("<td style='border-width:0px; padding: 5px 3px 5px 3px; background-color:#D1D7DC; width: 100%'>");
      buffer.append("<b>&nbsp;&nbsp;" + fd_head_for_mail(HTMLEntities.htmlentities(removeSlashes(post.getHead().getTitle()))) + "</b>");
      buffer.append("</td></tr>");
      buffer.append("<tr><td>");
      buffer.append("<span style='font-family: Verdana; font-size: 10pt; color: #000000; font-weight:bold'>" + HtmlChars.convertHtmlSymbols(author.getNick()) + "</span>&nbsp;•");
      buffer.append("&nbsp;<img border='0' src='http://www.diletant.com.ua/forum/smiles/icon_minipost.gif'>&nbsp;<span style='font-family: Verdana; font-size: 8pt; color: #000000'>" + postTime.toString("dd.MM.yyyy HH:mm") + "</span>&nbsp;");
      buffer.append("</td></tr>");
      buffer.append("<tr><td>");
      buffer.append("<table width='100%'><tr><td valign=top style='background-color:#e1e3e5'>");
      buffer.append("<table style='table-layout:fixed;' width='170'><tr><td valign=top>");
      buffer.append("<div style='padding:10px;'>");
      //avatar
      if (author.getAvatarApproved() && author.getAvatar() != null && !author.getAvatar().trim().isEmpty() && author.getShowAvatar()){
         if (author.getAvatar().startsWith("http://")){
            buffer.append("<img border='0' src='" + author.getAvatar() + "'>");
         }else{
            buffer.append("<img border='0' src='http://www.diletant.com.ua/forum/" + author.getAvatar() + "'>");
         }
      }else{
         buffer.append("<img border='0' src='http://www.diletant.com.ua/forum/smiles/no_avatar.gif'>");
      }
      buffer.append("</div>");
      buffer.append("<span style='font-family: Verdana; font-size: 8pt; color: #000000'><u>" + locale.getString("mess111") + "</u></span><br>");
      //country
      if (!author.getShowCountry() || author.getCountry() == null || author.getCountry().isEmpty()){
         buffer.append("<span style='font-family: Verdana; font-size: 8pt; color: #000000'>" + locale.getString("mess114") + "</span><br>");
      }else{
         buffer.append("<span style='font-family: Verdana; font-size: 8pt; color: #000000'>" + HtmlChars.convertHtmlSymbols(author.getCountry()) + "</span><br>");
      }
      buffer.append("<span style='font-family: Verdana; font-size: 8pt; color: #000000'><u>" + locale.getString("mess112") + "</u></span><br>");
      if (!author.getShowCity() || author.getCity() == null || author.getCity().isEmpty()){
         buffer.append("<span style='font-family: Verdana; font-size: 8pt; color: #000000'>" + locale.getString("mess114") + "</span><br>");
      }else{
         buffer.append("<span style='font-family: Verdana; font-size: 8pt; color: #000000'>" + HtmlChars.convertHtmlSymbols(author.getCity()) + "</span><br>");
      }
      buffer.append("</td></tr></table>");
      buffer.append("</td><td valign='top' width='100%'>");
      buffer.append("<table width='100%'>");
      buffer.append("<tr><td>");
      buffer.append("<p style='font-family: Verdana; font-size: 10pt; color: #000000'>");
      buffer.append(fd_body_for_mail(HtmlChars.convertHtmlSymbols(removeSlashes(post.getBody().getBody()))));
      buffer.append("</p>");
      buffer.append("</td></tr>");
      buffer.append("</table></td></tr>");
      buffer.append("<tr><td style='background-color:#e1e3e5' colspan=2></td></tr>");
      buffer.append("<tr><td style='background-color:#e1e3e5'></td><td>");
      buffer.append("<p style='font-family: Verdana; font-size: 10pt; color: #000000'>");
      buffer.append(fd_body_for_mail(HtmlChars.convertHtmlSymbols(removeSlashes(author.getFooter()))));
      buffer.append("</p>");
      buffer.append("</td></tr>");
      buffer.append("<tr><td align='RIGHT' width='100%' colspan=2>");
      if (post.getHead().getNred()>0){
         Time postEditTime = new Time(post.getHead().getEditTime());
         buffer.append("<table style='background-color:#e1e3e5' width='100%'>");
         buffer.append("<tr><td align='LEFT'>");
         buffer.append("<span style='font-family: Verdana; font-size: 8pt; color: #000000'>");
         buffer.append(locale.getString("mess50"));
         buffer.append("&nbsp;");
         buffer.append(post.getHead().getNred());
         buffer.append("&nbsp;");
         buffer.append(locale.getString("mess51"));
         buffer.append("&nbsp;");
         buffer.append(postEditTime.toString("dd.MM.yyyy HH:mm"));
         buffer.append("</span>");
      }
      else {
         buffer.append("<table style='background-color:#e1e3e5'>");
         buffer.append("<tr><td align='LEFT'>");
         buffer.append(" ");
      }
      buffer.append("</td>");
      buffer.append("</tr></table>");
      buffer.append("</td></tr>");
      buffer.append("</table>");
      buffer.append("</div>");
      buffer.append("</td></tr>");
      buffer.append("</table><body></html>");
      return buffer.toString();
   }

   public static void sendActivateMail(IUser user, LocaleString locale) throws InvalidKeyException, AddressException, ConfigurationException, MessagingException{
      String mail = user.getEmail();
      String postString = prepareActivateMail(user, locale);
      sendMail(mail, FJConfiguration.getConfig().getString("mail.from.registration"), FJConfiguration.getConfig().getString("mail.smtp.host"), locale.getString("MSG_ACTIVATE_HEADER"), postString);
      String adminMail1 = FJConfiguration.getConfig().getString("mail.admin.address.1");
      if (adminMail1 != null && !adminMail1.isEmpty()){
         sendMail(adminMail1, FJConfiguration.getConfig().getString("mail.from.registration"), FJConfiguration.getConfig().getString("mail.smtp.host"), locale.getString("MSG_ACTIVATE_HEADER"), postString);
      }
      String adminMail2 = FJConfiguration.getConfig().getString("mail.admin.address.2");
      if (adminMail2 != null && !adminMail2.isEmpty()){
         sendMail(adminMail2, FJConfiguration.getConfig().getString("mail.from.registration"), FJConfiguration.getConfig().getString("mail.smtp.host"), locale.getString("MSG_ACTIVATE_HEADER"), postString);
      }
      String adminMail3 = FJConfiguration.getConfig().getString("mail.admin.address.3");
      if (adminMail3 != null && !adminMail3.isEmpty()){
         sendMail(adminMail3, FJConfiguration.getConfig().getString("mail.from.registration"), FJConfiguration.getConfig().getString("mail.smtp.host"), locale.getString("MSG_ACTIVATE_HEADER"), postString);
      }
   }
   
   public static void sendApproveMail(IUser user, LocaleString locale) throws InvalidKeyException, AddressException, ConfigurationException, MessagingException{
      String mail = user.getEmail();
      String postString = prepareApproveMail(user, locale);
      String adminMail1 = FJConfiguration.getConfig().getString("mail.admin.address.1");
      if (adminMail1 != null && !adminMail1.isEmpty()){
         sendMail(adminMail1, FJConfiguration.getConfig().getString("mail.from.registration"), FJConfiguration.getConfig().getString("mail.smtp.host"), locale.getString("MSG_APPROVE_HEADER"), postString);
      }
      String adminMail2 = FJConfiguration.getConfig().getString("mail.admin.address.2");
      if (adminMail2 != null && !adminMail2.isEmpty()){
         sendMail(adminMail2, FJConfiguration.getConfig().getString("mail.from.registration"), FJConfiguration.getConfig().getString("mail.smtp.host"), locale.getString("MSG_APPROVE_HEADER"), postString);
      }
      String adminMail3 = FJConfiguration.getConfig().getString("mail.admin.address.3");
      if (adminMail3 != null && !adminMail3.isEmpty()){
         sendMail(adminMail3, FJConfiguration.getConfig().getString("mail.from.registration"), FJConfiguration.getConfig().getString("mail.smtp.host"), locale.getString("MSG_APPROVE_HEADER"), postString);
      }
   }

   public static void sendApprovedMail(IUser user, LocaleString locale) throws InvalidKeyException, AddressException, ConfigurationException, MessagingException{
      String mail = user.getEmail();
      String postString = prepareApprovedMail(user, locale);
      sendMail(mail, FJConfiguration.getConfig().getString("mail.from.registration"), FJConfiguration.getConfig().getString("mail.smtp.host"), locale.getString("MSG_ACCOUNT_APPROVED"), postString);
   }

   private static String prepareActivateMail(IUser user, LocaleString locale) throws InvalidKeyException{
      StringBuffer buffer = new StringBuffer();
      buffer.append("<html><head><meta http-equiv='content-type' content='text/html; charset=UTF-8'></head><body style='background-color:#EFEFEF;'><table>");
      buffer.append("<tr><td>");
      buffer.append(locale.getString("MSG_REGISTERED"));
      buffer.append("<br/>");
      buffer.append(locale.getString("MSG_CLICK"));
      buffer.append(":&nbsp;<a href='http://www.diletant.com.ua/forum/");
      buffer.append(FJUrl.ACTIVATE_USER);
      buffer.append("?");
      buffer.append(HttpParameters.USER_ID);
      buffer.append("=");
      buffer.append(user.getId());
      buffer.append("&");
      buffer.append(HttpParameters.ACTIVATE_EMAIL_CODE);
      buffer.append("=");
      buffer.append(user.getActivateCode());
      buffer.append("'>");
      buffer.append(locale.getString("MSG_ACTIVATE"));
      buffer.append(":&nbsp;");
      buffer.append(user.getNick());
      buffer.append("</a>");
      buffer.append("</td><tr></table><body></html>");
      return buffer.toString();
   }
   
   private static String prepareApproveMail(IUser user, LocaleString locale) throws InvalidKeyException{
      StringBuffer buffer = new StringBuffer();
      buffer.append("<html><head><meta http-equiv='content-type' content='text/html; charset=UTF-8'></head><body style='background-color:#EFEFEF;'><table>");
      buffer.append("<tr><td>");
      buffer.append(user.getNick());
      buffer.append(":&nbsp;<a href='http://www.diletant.com.ua/forum/");
      buffer.append(FJUrl.SETTINGS);
      buffer.append("?");
      buffer.append(HttpParameters.ID);
      //TODO Magic integer
      buffer.append("=15'>");
      buffer.append(locale.getString("MSG_UNAPPROVED_USERS"));
      buffer.append("</a>");
      buffer.append("</td><tr></table><body></html>");
      return buffer.toString();
   }

   private static String prepareApprovedMail(IUser user, LocaleString locale) throws InvalidKeyException{
      StringBuffer buffer = new StringBuffer();
      buffer.append("<html><head><meta http-equiv='content-type' content='text/html; charset=UTF-8'></head><body style='background-color:#EFEFEF;'><table>");
      buffer.append("<tr><td>");
      buffer.append(locale.getString("MSG_ACCOUNT_APPROVED"));
      buffer.append("</td><tr></table><body></html>");
      return buffer.toString();
   }

   public static void sendMail(String to, String from, String host, String subject, String text) throws ConfigurationException, AddressException, MessagingException{
      Properties props = new Properties();
      props.put("mail.smtp.host", host);
      String mailDebug = FJConfiguration.getConfig().getString("mail.debug");
      props.put("mail.debug", mailDebug == null ? "false" : mailDebug);
      Session session = Session.getInstance(props);
      Message msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress(from));
      InternetAddress[] address = {new InternetAddress(to)};
      msg.setRecipients(Message.RecipientType.TO, address);
      msg.addHeader("charset", "UTF-8");
      msg.setSubject(subject);
      msg.setSentDate(new Date());
      msg.setDataHandler(new DataHandler(new HTMLDataSource(text)));
      Transport.send(msg);
   }

   static class HTMLDataSource implements DataSource {
      private String html;

      public HTMLDataSource(String htmlString) {
          html = htmlString;
      }

      public InputStream getInputStream() throws IOException {
          if (html == null) throw new IOException("Null HTML");
          return new ByteArrayInputStream(html.getBytes());
      }

      public OutputStream getOutputStream() throws IOException {
          throw new IOException("This DataHandler cannot write HTML");
      }

      public String getContentType() {
          return "text/html";
      }

      public String getName() {
          return "JAF text/html dataSource to send e-mail only";
      }
  }   
}
