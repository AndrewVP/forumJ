/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.db.entity;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public interface IFJPostHead {
   
   public static final String ID_FIELD_NAME = "id";
   public static final String ATHOR_ID_FIELD_NAME = "auth";
   public static final String TITLE_FIELD_NAME = "tilte";
   public static final String ATHOR_IP_FIELD_NAME = "ip";
   public static final String ATHOR_DOMEN_FIELD_NAME = "domen";
   public static final String OUTD_FIELD_NAME = "outd";
   public static final String NUMBER_OF_EDITS_FIELD_NAME = "nred";
   public static final String CREATIN_DATE_FIELD_NAME = "fd_post_time";
   public static final String LAST_EDIT_DATE_FIELD_NAME = "fd_post_edit_time";
   public static final String POST_ID_FIELD_NAME = "id_post";
   public static final String THREAD_ID_FIELD_NAME = "thread_id";
}