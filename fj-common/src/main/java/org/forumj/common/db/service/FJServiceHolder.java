/**
 * 
 */
package org.forumj.common.db.service;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.forumj.common.config.FJConfiguration;
import org.forumj.common.db.entity.Image;

/**
 * @author andrew
 *
 */
public class FJServiceHolder {

   private static Logger logger = LogManager.getLogger("org.forumj.service");
   
   private static CountService countService = null;

   private static FolderService folderService = null;

   private static IgnorService ignorService = null;

   private static IndexService indexService = null;
   
   private static InterfaceService interfaceService = null;

   private static MailService mailService = null;
   
   private static SubscribeService subscribeService = null;

   private static QuestService questService = null;
   
   private static UserService userService = null;

   private static ThreadService threadService = null;

   private static VoiceService voiceService = null;
   
   private static PostService postService = null;

   private static ActionService actionService = null;
   
   private static FJIpAddressService ipAddressService = null;

   private static ImageService imageService = null;

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
         threadService = (ThreadService) Class.forName(FJConfiguration.getConfig().getString("service.threadService.class")).newInstance();
         voiceService = (VoiceService) Class.forName(FJConfiguration.getConfig().getString("service.voiceService.class")).newInstance();
         postService = (PostService) Class.forName(FJConfiguration.getConfig().getString("service.postService.class")).newInstance();
         actionService = (ActionService) Class.forName(FJConfiguration.getConfig().getString("service.actionService.class")).newInstance();
         ipAddressService = (FJIpAddressService) Class.forName(FJConfiguration.getConfig().getString("service.ipAddressService.class")).newInstance();
         imageService = (ImageService) Class.forName(FJConfiguration.getConfig().getString("service.imageService.class")).newInstance();
      } catch (Exception e) {
         logger.error(e.getMessage(), e);
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

   public static ThreadService getThreadService(){
      return threadService;
   }
   
   public static VoiceService getVoiceService(){
      return voiceService;
   }
   
   public static PostService getPostService(){
      return postService;
   }

   public static ActionService getActionService(){
      return actionService;
   }

   public static FJIpAddressService getIpAddressService() {
      return ipAddressService;
   }

   public static ImageService getImageService() {
      return imageService;
   }
}
