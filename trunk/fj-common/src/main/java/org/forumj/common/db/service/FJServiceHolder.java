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

   private static ThreadService threadService = null;

   private static VoiceService voiceService = null;
   
   private static PostService postService = null;

   private static ActionService actionService = null;
   
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
}
