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
package org.forumj.dbextreme.db.service;

import org.forumj.dbextreme.db.dao.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJService {
   
   private static final FJForumDao forumDao = new FJForumDao();

   private static final FJPostDao postDao = new FJPostDao();

   private static final FJThreadDao threadDao = new FJThreadDao();

   private static final FJMailDao mailDao = new FJMailDao();

   private static final FJInterfaceDao interfaceDao = new FJInterfaceDao();

   private static final FJFolderDao folderDao = new FJFolderDao();
   
   private static final FJActionDao actionDao = new FJActionDao();

   private static final FJIgnorDao ignorDao = new FJIgnorDao();

   private static final FJSubscribeDao subscribeDao = new FJSubscribeDao();

   private static final FJVoiceDao voiceDao = new FJVoiceDao();

   private static final FJUserDao userDao = new FJUserDao();

   private static final FJQuestNodeDao questNodeDao = new FJQuestNodeDao();
   
   private static final IpAddressDaoImpl ipAddressDao = new IpAddressDaoImpl();

   private static final ImageDao imageDao = new ImageDao();

   private static final RequestDaoImpl requestDao = new RequestDaoImpl();

   private static final HttpHeaderNameDao httpHeaderNameDao = new HttpHeaderNameDao();

   private static final HttpHeaderDao httpHeaderDao = new HttpHeaderDao();

   private static final HttpCookieNameDao httpCookieNameDao = new HttpCookieNameDao();

   private static final HttpCookieDao httpCookieDao = new HttpCookieDao();

   public static IpAddressDaoImpl getIpAddressDao() {
      return ipAddressDao;
   }

   public static HttpHeaderNameDao getHttpHeaderNameDao() {
      return httpHeaderNameDao;
   }

   public static HttpHeaderDao getHttpHeaderDao() {
      return httpHeaderDao;
   }

   public static HttpCookieNameDao getHttpCookieNameDao() {
      return httpCookieNameDao;
   }

   public static HttpCookieDao getHttpCookieDao() {
      return httpCookieDao;
   }

   /**
    * @return the questnodedao
    */
   public static FJQuestNodeDao getQuestNodeDao() {
      return questNodeDao;
   }

   /**
    * @return the userdao
    */
   public static FJUserDao getUserDao() {
      return userDao;
   }

   /**
    * @return the voicedao
    */
   public static FJVoiceDao getVoiceDao() {
      return voiceDao;
   }

   /**
    * @return the subscribedao
    */
   public static FJSubscribeDao getSubscribedao() {
      return subscribeDao;
   }

   /**
    * @return the ignordao
    */
   public static FJIgnorDao getIgnorDao() {
      return ignorDao;
   }

   /**
    * @return the actiondao
    */
   public static FJActionDao getActiondao() {
      return actionDao;
   }

   /**
    * @return the folderdao
    */
   public static FJFolderDao getFolderDao() {
      return folderDao;
   }

   /**
    * @return the interfacedao
    */
   public static FJInterfaceDao getInterfaceDao() {
      return interfaceDao;
   }

   /**
    * @return the maildao
    */
   public static FJMailDao getMailDao() {
      return mailDao;
   }

   /**
    * @return the threaddao
    */
   public static FJThreadDao getThreadDao() {
      return threadDao;
   }

   /**
    * @return the postdao
    */
   public static FJPostDao getPostDao() {
      return postDao;
   }

   /**
    * @return the forumdao
    */
   public static FJForumDao getForumDao() {
      return forumDao;
   }

   public static ImageDao getImageDao() {
      return imageDao;
   }

   public static RequestDaoImpl getRequestDao() {
      return requestDao;
   }
}
