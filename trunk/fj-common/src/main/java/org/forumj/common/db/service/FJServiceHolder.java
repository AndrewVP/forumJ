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

   private static QuestService questService = null;
   
   private static UserService userService = null;

   private static TemaService temaService = null;
   
   static{
      try {
         countService = (CountService) Class.forName(FJConfiguration.getConfig().getString("service.countService.class")).newInstance();
         folderService = (FolderService) Class.forName(FJConfiguration.getConfig().getString("service.folderService.class")).newInstance();
         ignorService = (IgnorService) Class.forName(FJConfiguration.getConfig().getString("service.ignorService.class")).newInstance();
         indexService = (IndexService) Class.forName(FJConfiguration.getConfig().getString("service.indexService.class")).newInstance();
         interfaceService = (InterfaceService) Class.forName(FJConfiguration.getConfig().getString("service.interfaceService.class")).newInstance();
         mailService = (MailService) Class.forName(FJConfiguration.getConfig().getString("service.mailService.class")).newInstance();
         subscribeService = (SubscribeService) Class.forName(FJConfiguration.getConfig().getString("service.subscribeService.class")).newInstance();
         questService = (QuestService) Class.forName(FJConfiguration.getConfig().getString("service.questService.class")).newInstance();
         userService = (UserService) Class.forName(FJConfiguration.getConfig().getString("service.userService.class")).newInstance();
         temaService = (TemaService) Class.forName(FJConfiguration.getConfig().getString("service.temaService.class")).newInstance();
      } catch (ConfigurationException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (InstantiationException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
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

   public static QuestService getQuestService(){
      return questService;
   }
   
   public static UserService getUserService(){
      return userService;
   }

   public static TemaService getTemaService(){
      return temaService;
   }
}
