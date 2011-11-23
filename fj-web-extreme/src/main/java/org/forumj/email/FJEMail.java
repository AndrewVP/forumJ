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

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.FJConfiguration;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJEMail {

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
      msg.addHeader("Content-type", "text/html");
      msg.addHeader("charset", "UTF-8");
      msg.setSubject(subject);
      msg.setSentDate(new Date());
      msg.setText(text);
      Transport.send(msg);
   }
}
