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
package org.forumj.db.entity;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public interface IFJThread {
   
   public static final String AUTH_FIELD_NAME = "auth";
   public static final String HEAD_FIELD_NAME = "head";
   public static final String REGISTRATION_DATE_FIELD_NAME = "reg";
   public static final String LAST_POST_DATE_FIELD_NAME = "lposttime";
   public static final String LAST_POST_USER_ID_FIELD_NAME = "lpostuser";
   public static final String LAST_POST_USER_NICK_FIELD_NAME = "lpostnick";
   public static final String LAST_POST_ID_FIELD_NAME = "id_last_post";
   public static final String SEEN_ID_FIELD_NAME = "seenid";
   public static final String SEEN_ALL_FIELD_NAME = "seenall";
   public static final String DOCK_FIELD_NAME = "dock";
   public static final String TYPE_FIELD_NAME = "type";
   public static final String FOLDER_ID_FIELD_NAME = "folder";
   public static final String POSTS_COUNT_FIELD_NAME = "npost";

}