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
public class FJPostBody implements IFJPostBody {
   
   private Long id = null;
   
   private Long postId = null;
   
   private String body = null;

   /**
    * @return the id
    */
   public Long getId() {
      return id;
   }

   /**
    * @param id the id to set
    */
   public void setId(Long id) {
      this.id = id;
   }

   /**
    * @return the postId
    */
   public Long getPostId() {
      return postId;
   }

   /**
    * @param postId the postId to set
    */
   public void setPostId(Long postId) {
      this.postId = postId;
   }

   /**
    * @return the body
    */
   public String getBody() {
      return body;
   }

   /**
    * @param body the body to set
    */
   public void setBody(String body) {
      this.body = body;
   }
   
}
