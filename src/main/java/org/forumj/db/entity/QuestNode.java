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
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class QuestNode {

   public QuestNode() {
      super();
   }

   public QuestNode(Integer numb, String node, Long userId) {
      super();
      this.gol = 0;
      this.type = 0;
      this.numb = numb;
      this.node = node;
      this.userId = userId;
   }

   private Long id;
   
   private Long head;
   
   private Integer numb;
   
   private Integer gol;
   
   private Integer type;
   
   private Long userId;
   
   private String userNick;

   private String node;
   
   
   /**
    * @return the node
    */
   public String getNode() {
      return node;
   }

   /**
    * @param node the node to set
    */
   public void setNode(String node) {
      this.node = node;
   }

   /**
    * @return the userNick
    */
   public String getUserNick() {
      return userNick;
   }

   /**
    * @param userNick the userNick to set
    */
   public void setUserNick(String userNick) {
      this.userNick = userNick;
   }

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
    * @return the head
    */
   public Long getHead() {
      return head;
   }

   /**
    * @param head the head to set
    */
   public void setHead(Long head) {
      this.head = head;
   }

   /**
    * @return the numb
    */
   public Integer getNumb() {
      return numb;
   }

   /**
    * @param numb the numb to set
    */
   public void setNumb(Integer numb) {
      this.numb = numb;
   }

   /**
    * @return the gol
    */
   public Integer getGol() {
      return gol;
   }

   /**
    * @param gol the gol to set
    */
   public void setGol(Integer gol) {
      this.gol = gol;
   }

   /**
    * @return the type
    */
   public Integer getType() {
      return type;
   }

   /**
    * @param type the type to set
    */
   public void setType(Integer type) {
      this.type = type;
   }

   /**
    * @return the userId
    */
   public Long getUserId() {
      return userId;
   }

   /**
    * @param userId the userId to set
    */
   public void setUserId(Long userId) {
      this.userId = userId;
   }

}
