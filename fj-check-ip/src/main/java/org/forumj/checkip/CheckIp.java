/**
 * 
 */
package org.forumj.checkip;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.xml.parsers.*;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.forumj.common.db.entity.IFJIpAddress;
import org.forumj.common.db.service.FJServiceHolder;
import org.xml.sax.SAXException;

/**
 * @author andrew
 *
 */
public class CheckIp {

   public static boolean isSpammerIp(String ip) throws ConfigurationException, IOException, SQLException, ParserConfigurationException, SAXException{
      boolean result = false;
      if (ip != null){
         Boolean checkedAndSpammer = FJServiceHolder.getIpAddressService().isSpammer(ip);
         if (checkedAndSpammer == null){
            checkedAndSpammer = checkIsSpammerIp(ip);
         }
         result = checkedAndSpammer != null && checkedAndSpammer;
      }
      return result;
   }

   private static boolean checkIsSpammerIp(String ip) throws ClientProtocolException, IOException, ConfigurationException, SQLException, ParserConfigurationException, SAXException{
      boolean result = false; 
      DefaultHttpClient httpclient = new DefaultHttpClient();
      HttpGet httpGet = new HttpGet("http://www.stopforumspam.com/api?ip=" + ip);
      HttpResponse response = httpclient.execute(httpGet);
      HttpEntity entity = null;
      try {
         entity = response.getEntity();
         SAXParserFactory spf = SAXParserFactory.newInstance();
         SaxHandler handler = new SaxHandler();
         SAXParser parser = spf.newSAXParser();
         parser.parse(entity.getContent(), handler);
         IpResponseContent content = handler.getContent();
         if (content != null && content.isSuccess()){
            IFJIpAddress ipAddress = FJServiceHolder.getIpAddressService().getIpAddressObject();
            ipAddress.setIp(ip);
            ipAddress.setLastCheck(new Date());
            ipAddress.setSource("http://www.stopforumspam.com");
            ipAddress.setSpammer(content.isSpammer());
            FJServiceHolder.getIpAddressService().createIpAddresscreate(ipAddress);
         }
         result = content != null && content.isSuccess() && content.isSpammer();
      } finally {
         EntityUtils.consume(entity);
         httpGet.releaseConnection();
      }
      return result;
   }
}
