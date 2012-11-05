/**
 * 
 */
package org.forumj.checkip.connector.stopforumspamcom;

import java.io.IOException;

import javax.xml.parsers.*;

import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.forumj.checkip.connector.*;

/**
 * @author andrew
 *
 */
public class StopforumspamComConnector implements SourceConnector {

   public String getSourceName() {
      return "http://www.stopforumspam.com";
   }

   public Boolean isListedAsSpammer(String ip) throws ConnectorException {
      HttpGet httpGet = new HttpGet("http://www.stopforumspam.com/api?ip=" + ip);
      HttpEntity entity = null;
      Boolean result = false;
      try {
         DefaultHttpClient httpclient = new DefaultHttpClient();
         HttpResponse response = httpclient.execute(httpGet);
         entity = response.getEntity();
         SAXParserFactory spf = SAXParserFactory.newInstance();
         SaxHandler handler = new SaxHandler();
         SAXParser parser = spf.newSAXParser();
         parser.parse(entity.getContent(), handler);
         IpResponseContent content = handler.getContent();
         if (content != null && content.isSuccess()){
            result = content.isSpammer();
         }
      } catch (Throwable e) {
         throw new ConnectorException(e);
      } finally {
         try {
            EntityUtils.consume(entity);
         } catch (IOException e) {
            throw new ConnectorException(e);
         }
         httpGet.releaseConnection();
      }
      return result;
   }

}
