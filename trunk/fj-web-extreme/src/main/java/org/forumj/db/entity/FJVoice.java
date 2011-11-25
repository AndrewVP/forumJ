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

import org.forumj.common.db.entity.IFJVoice;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJVoice implements IFJVoice {
   
   private Long id = null;
   
   private Long threadId = null;
   
   private Long nodeId = null;
   
   private Long userId = null;

   /* (non-Javadoc)
    * @see org.forumj.db.entity.IFJVoice#getId()
    */
   public Long getId() {
      return id;
   }

   /* (non-Javadoc)
    * @see org.forumj.db.entity.IFJVoice#setId(java.lang.Long)
    */
   public void setId(Long id) {
      this.id = id;
   }

   /* (non-Javadoc)
    * @see org.forumj.db.entity.IFJVoice#getThreadId()
    */
   public Long getThreadId() {
      return threadId;
   }

   /* (non-Javadoc)
    * @see org.forumj.db.entity.IFJVoice#setThreadId(java.lang.Long)
    */
   public void setThreadId(Long threadId) {
      this.threadId = threadId;
   }

   /* (non-Javadoc)
    * @see org.forumj.db.entity.IFJVoice#getNodeId()
    */
   public Long getNodeId() {
      return nodeId;
   }

   /* (non-Javadoc)
    * @see org.forumj.db.entity.IFJVoice#setNodeId(java.lang.Long)
    */
   public void setNodeId(Long nodeId) {
      this.nodeId = nodeId;
   }

   /* (non-Javadoc)
    * @see org.forumj.db.entity.IFJVoice#getUserId()
    */
   public Long getUserId() {
      return userId;
   }

   /* (non-Javadoc)
    * @see org.forumj.db.entity.IFJVoice#setUserId(java.lang.Long)
    */
   public void setUserId(Long userId) {
      this.userId = userId;
   }

}
