/**
 * 
 */
package org.forumj.checkip.connector.blacklistmyipms;

import java.io.*;

import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.forumj.checkip.connector.*;

/**
 * @author andrew
 *
 */
public class BlacklistMyipMsConnector implements SourceConnector {

   public String getSourceName() {
      return "http://blacklist.myip.ms";
   }

   public Boolean isListedAsSpammer(String ip) throws ConnectorException {
      HttpGet httpGet = new HttpGet("http://blacklist.myip.ms/" + ip + "/");
      HttpEntity entity = null;
      Boolean result = false;
      Reader reader = null;
      try {
         DefaultHttpClient httpclient = new DefaultHttpClient();
         HttpResponse response = httpclient.execute(httpGet);
         entity = response.getEntity();
         InputStream content = entity.getContent();
         final char[] chars = new char[1024];
         reader = new InputStreamReader(content, "UTF-8");
         try {
            while (reader.read(chars) > -1) {
               String line = String.valueOf(chars);
               if (line.contains("<input type='hidden' name='blacklist_test_results' value='listed'>")){
                  result = true;
                  break;
               }
            }
         } finally {
         }
      } catch (Throwable e) {
         throw new ConnectorException(e);
      } finally {
         try {
            if (reader != null) {
               reader.close();
            }
            EntityUtils.consume(entity);
         } catch (IOException e) {
            throw new ConnectorException(e);
         }
         httpGet.releaseConnection();
      }
      return result;
   }
}
