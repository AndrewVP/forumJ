/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
  */
package org.forumj.db.dao;

import static org.forumj.common.db.entity.IFJMail.*;
import static org.forumj.db.dao.tool.QueryBuilder.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.*;
import org.forumj.db.entity.*;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJMailDao extends FJDao {

   public void receiveMail(Long userId) throws ConfigurationException, SQLException, IOException{
      String receiveMailQuery = getReceiveMailQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         conn.setAutoCommit(false);
         st = conn.prepareStatement(receiveMailQuery);
         st.setLong(1, userId);
         st.executeUpdate();
      }finally{
         readFinally(conn, st);
      }
   }
   
   public void markMailAsRead(Long userId, Long mailId) throws ConfigurationException, SQLException, IOException{
      String markMailAsReadQuery = getMarkMailAsReadQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         conn.setAutoCommit(false);
         st = conn.prepareStatement(markMailAsReadQuery);
         st.setLong(1, userId);
         st.setLong(2, mailId);
         st.executeUpdate();
      }finally{
         readFinally(conn, st);
      }
   }
   
   public List<IFJMail> loadInbox(IUser user) throws IOException, ConfigurationException, SQLException{
      String query = getLoadInboxQuery();
      List<IFJMail> result = loadMails(user, query, true);
      return result;
   }
   
   public List<IFJMail> loadOutNotReceivedBox(IUser user) throws IOException, ConfigurationException, SQLException{
      String query = getLoadOutNotReceivedBoxQuery();
      List<IFJMail> result = loadMails(user, query, false);
      return result;
   }

   public List<IFJMail> loadOutReceivedBox(IUser user) throws IOException, ConfigurationException, SQLException{
      String query = getLoadOutReceivedBoxQuery();
      List<IFJMail> result = loadMails(user, query, false);
      return result;
   }
   
   public List<IFJMail> loadDraftBox(IUser user) throws IOException, ConfigurationException, SQLException{
      String query = getLoadDraftBoxQuery();
      List<IFJMail> result = loadMails(user, query, false);
      return result;
   }
   
   public List<IFJMail> loadMails(IUser user, String query, boolean isInbox) throws IOException, ConfigurationException, SQLException{
      List<IFJMail> result = new ArrayList<IFJMail>();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, user.getId());
         ResultSet rs = st.executeQuery();
         while (rs.next()){
            FJMail mail = new FJMail();
            mail.setId(rs.getLong(ID_FIELD_NAME));
            mail.setCreateDate(rs.getDate(DATE_CREATE_FIELD_NAME));
            mail.setSentDate(rs.getDate(DATE_SENT_FIELD_NAME));
            mail.setReceiveDate(rs.getDate(DATE_RECEIVED_FIELD_NAME));
            mail.setReadDate(rs.getDate(DATE_READ_FIELD_NAME));
            mail.setReadDate(rs.getDate(DATE_READ_FIELD_NAME));
            if (isInbox){
               mail.setReceiver(user);
               mail.setSender(new User(rs.getLong(SENDER_ID_FIELD_NAME), rs.getString(IUser.NICK_FIELD_NAME)));
            }else{
               mail.setSender(user);
               mail.setReceiver(new User(rs.getLong(SENDER_ID_FIELD_NAME), rs.getString(IUser.NICK_FIELD_NAME)));
            }
            mail.setSubject(rs.getString(SUBJECT_FIELD_NAME));
            mail.setBody(rs.getString(BODY_FIELD_NAME));
            mail.setDeletedByReceiver(rs.getInt(DELETED_RECEIVER_FIELD_NAME));
            mail.setDeletedBySender(rs.getInt(DELETED_BY_SENDER_FIELD_NAME));
            result.add(mail);
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
   
   public IFJMail loadMail(IUser user, Long mailId, boolean userIsSender) throws IOException, ConfigurationException, SQLException{
      FJMail result = null;
      String loadMailQuery = getLoadMailQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(loadMailQuery);
         st.setLong(1, user.getId());
         st.setLong(2, user.getId());
         st.setLong(3, mailId);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = new FJMail();
            result.setId(rs.getLong(ID_FIELD_NAME));
            result.setCreateDate(rs.getDate(DATE_CREATE_FIELD_NAME));
            result.setSentDate(rs.getDate(DATE_SENT_FIELD_NAME));
            result.setReceiveDate(rs.getDate(DATE_RECEIVED_FIELD_NAME));
            result.setReadDate(rs.getDate(DATE_READ_FIELD_NAME));
            result.setReadDate(rs.getDate(DATE_READ_FIELD_NAME));
            if (userIsSender){
               result.setReceiver(new User(rs.getLong(RECEIVER_ID_FIELD_NAME), rs.getString(RECEIVER_NICK_FIELD_NAME)));
               result.setSender(user);
            }else{
               result.setReceiver(user);
               result.setSender(new User(rs.getLong(SENDER_ID_FIELD_NAME), rs.getString(SENDER_NICK_FIELD_NAME)));
            }
            result.setSubject(rs.getString(SUBJECT_FIELD_NAME));
            result.setBody(rs.getString(BODY_FIELD_NAME));
            result.setDeletedByReceiver(rs.getInt(DELETED_RECEIVER_FIELD_NAME));
            result.setDeletedBySender(rs.getInt(DELETED_BY_SENDER_FIELD_NAME));
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
   
   public void deleteFromOutbox(Long mailId, IUser user) throws ConfigurationException, SQLException, IOException{
      String query = getDeleteFromOutboxQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, mailId);
         st.setLong(2, user.getId());
         st.executeUpdate();
      }finally{
         readFinally(conn, st);
      }
   }

   public void deleteFromInbox(Long mailId, IUser user) throws ConfigurationException, SQLException, IOException{
      String query = getDeleteFromInboxQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, mailId);
         st.setLong(2, user.getId());
         st.executeUpdate();
      }finally{
         readFinally(conn, st);
      }
   }
   
   public void create(IFJMail mail) throws SQLException, ConfigurationException, IOException{
      String query = getCreateMailQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, mail.getSender().getId());
         st.setLong(2, mail.getReceiver().getId());
         st.setString(3, mail.getSubject());
         st.setString(4, mail.getBody());
         st.executeUpdate();
      }finally{
         readFinally(conn, st);
      }
   }

   public int getNewMailCount(Long idUser) throws ConfigurationException, SQLException, IOException{
      int result = 0;
      String query = getCreateMailQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, idUser);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = rs.getInt("nmail");
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
}
