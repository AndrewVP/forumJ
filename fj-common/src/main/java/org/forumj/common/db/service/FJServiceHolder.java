/**
 * 
 */
package org.forumj.common.db.service;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.config.FJConfiguration;

/**
 * @author andrew
 *
 */
public class FJServiceHolder {
   
   private static CountService countService = null;

   private static FolderService folderService = null;

   private static IgnorService ignorService = null;

   private static IndexService indexService = null;
   
   private static InterfaceService interfaceService = null;

   private static MailService mailService = null;
   
   private static SubscribeService subscribeService = null;
   
   @SuppressWarnings("unused")
   private FJServiceHolder instance = new FJServiceHolder();
   
   private FJServiceHolder() throws ConfigurationException, InstantiationException, IllegalAccessException, ClassNotFoundException{
      String countServiceClassName = FJConfiguration.getConfig().getString("service.countService.class");
      countService = (CountService) Class.forName(countServiceClassName).newInstance();
      String folderServiceClassName = FJConfiguration.getConfig().getString("service.folderService.class");
      folderService = (FolderService) Class.forName(folderServiceClassName).newInstance();
      String ignorServiceClassName = FJConfiguration.getConfig().getString("service.ignorService.class");
      ignorService = (IgnorService) Class.forName(ignorServiceClassName).newInstance();
      String indexServiceClassName = FJConfiguration.getConfig().getString("service.indexService.class");
      indexService = (IndexService) Class.forName(indexServiceClassName).newInstance();
      String interfaceServiceClassName = FJConfiguration.getConfig().getString("service.interfaceService.class");
      interfaceService = (InterfaceService) Class.forName(interfaceServiceClassName).newInstance();
      String mailServiceClassName = FJConfiguration.getConfig().getString("service.mailService.class");
      mailService = (MailService) Class.forName(mailServiceClassName).newInstance();

      String subscribeServiceClassName = FJConfiguration.getConfig().getString("service.subscribeService.class");
      subscribeService = (SubscribeService) Class.forName(subscribeServiceClassName).newInstance();
   }
   
   public static CountService getCountService(){
      return countService;
   }
   
   public static FolderService getFolderService(){
      return folderService;
   }
   
   public static IgnorService getIgnorService(){
      return ignorService;
   }
   
   public static IndexService getIndexService(){
      return indexService;
   }
   
   public static InterfaceService getInterfaceService(){
      return interfaceService;
   }
   
   public static MailService getMailService(){
      return mailService;
   }
   
   public static SubscribeService getSubscribeService(){
      return subscribeService;
   }
}
