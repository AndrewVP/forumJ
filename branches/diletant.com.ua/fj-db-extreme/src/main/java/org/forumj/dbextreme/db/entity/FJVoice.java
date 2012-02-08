/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.dbextreme.db.entity;

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

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
      result = prime * result + ((threadId == null) ? 0 : threadId.hashCode());
      result = prime * result + ((userId == null) ? 0 : userId.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      FJVoice other = (FJVoice) obj;
      if (id == null) {
         if (other.id != null) {
            return false;
         }
      } else if (!id.equals(other.id)) {
         return false;
      }
      if (nodeId == null) {
         if (other.nodeId != null) {
            return false;
         }
      } else if (!nodeId.equals(other.nodeId)) {
         return false;
      }
      if (threadId == null) {
         if (other.threadId != null) {
            return false;
         }
      } else if (!threadId.equals(other.threadId)) {
         return false;
      }
      if (userId == null) {
         if (other.userId != null) {
            return false;
         }
      } else if (!userId.equals(other.userId)) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("FJVoice [id=");
      builder.append(id); 
      builder.append(", threadId=");
      builder.append(threadId);
      builder.append(", nodeId=");
      builder.append(nodeId);
      builder.append(", userId=");
      builder.append(userId);
      builder.append("]");
      return builder.toString();
   }

   
}
