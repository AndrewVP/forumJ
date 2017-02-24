/**
 * 
 */
package org.forumj.checkip;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.checkip.connector.*;
import org.forumj.checkip.connector.blacklistmyipms.BlacklistMyipMsConnector;
import org.forumj.checkip.connector.stopforumspamcom.StopforumspamComConnector;
import org.forumj.common.db.entity.IFJIpAddress;
import org.forumj.common.db.service.FJServiceHolder;

/**
 * @author andrew
 *
 */
public class CheckIp {

   private static Map<String, Boolean> cache = new HashMap<String, Boolean>();

   private static Object cacheMonitor = new Object();

   private static List<SourceConnector> connectors = new ArrayList<SourceConnector>();

   static{
      connectors.add(new StopforumspamComConnector());
      connectors.add(new BlacklistMyipMsConnector());
   }

   public static boolean isSpammerIp(String ip) throws ConfigurationException, IOException, SQLException{
      return false;
/*
      Boolean result = null;
      if (ip != null){
         result = cache.get(ip);
         if (result == null){
            Boolean checkedAndSpammer = FJServiceHolder.getIpAddressService().isSpammer(ip);
            if (checkedAndSpammer == null){
               try {
                  checkedAndSpammer = checkIsSpammerIp(ip);
               } catch (ConnectorException e) {
                  // TODO Ошибки коннекторов надо писать в лог
                  e.printStackTrace();
               }
            }else{
               addIpAddressToCache(ip, checkedAndSpammer);
            }
            result = checkedAndSpammer != null && checkedAndSpammer;
         }
      }
      return result;
*/
   }

   private static boolean checkIsSpammerIp(String ip) throws ConnectorException, ConfigurationException, SQLException, IOException{
      Boolean result = false;
      SourceConnector connector = null;
      for (Iterator<SourceConnector> iterator = connectors.iterator(); iterator.hasNext();) {
         connector = iterator.next();
         if (connector.isListedAsSpammer(ip)){
            result = true;
            break;
         }
      }
      addIp(ip, result ? connector.getSourceName() : "", result);
      return result;
   }
   
   private static void addIp(String ip, String sourceName, boolean spammer) throws ConfigurationException, SQLException, IOException{
      IFJIpAddress ipAddress = FJServiceHolder.getIpAddressService().getIpAddressObject();
      ipAddress.setIp(ip);
      ipAddress.setLastCheck(new Date());
      ipAddress.setSource(sourceName);
      ipAddress.setSpammer(spammer);
      FJServiceHolder.getIpAddressService().createIpAddress(ipAddress);
      addIpAddressToCache(ip, spammer);
   }

   private static void addIpAddressToCache(String ip, Boolean spammer){
      synchronized (cacheMonitor) {
         if (cache.get(ip) == null){
            cache.put(ip, spammer);
         }
      }
   }
}
