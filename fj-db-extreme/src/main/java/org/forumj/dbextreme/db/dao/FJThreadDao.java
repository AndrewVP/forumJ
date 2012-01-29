/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.dbextreme.db.dao;

import static org.forumj.common.db.entity.IFJThread.*;
import static org.forumj.dbextreme.db.dao.tool.QueryBuilder.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.*;
import org.forumj.common.exception.DBException;
import org.forumj.dbextreme.db.entity.*;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJThreadDao extends FJDao {

   public void create(IFJThread thread, IFJPost post) throws IOException, DBException, SQLException, ConfigurationException{
      String createThreadQuery = getCreateThreadQuery(); 
      Connection conn = null;
      PreparedStatement st = null;
      boolean error = true;
      try {
         conn = getConnection();
         conn.setAutoCommit(false);
         st = conn.prepareStatement(createThreadQuery, new String[]{"id"});
         st.setLong(1, thread.getAuthId());
         st.setString(2, thread.getHead());
         Date date = new Date();
         st.setDate(3, new java.sql.Date(date.getTime()));
         st.setDate(4, new java.sql.Date(date.getTime()));
         st.setString(5, thread.getNick());
         st.setInt(6, thread.getType());
         st.executeUpdate();
         ResultSet idRs = st.getGeneratedKeys();
         if (idRs.next()){
            Long threadId = idRs.getLong(1);
            thread.setId(threadId);
            post.setThreadId(threadId);
            post.getHead().setThreadId(threadId);
            post.getHead().setCreateTime(date.getTime());
            FJPostDao postDao = new FJPostDao();
            Long postId = postDao.create(post, conn, false);
            thread.setLastPostId(postId);
            thread.setLastPostAuthId(post.getHead().getAuth());
            thread.setLastPostTime(new Date(post.getHead().getCreateTime()));
            thread.setLastPostNick(post.getHead().getAuthor().getNick());
            update(thread, conn);
            if (thread instanceof FJQuestionThread){
               FJQuestNodeDao answersDao = new FJQuestNodeDao();
               FJQuestionThread questionThread = (FJQuestionThread) thread;
               List<IQuestNode> answers = questionThread.getAnswers();
               QuestNode question = new QuestNode();
               question.setNode(questionThread.getQuestion());
               question.setGol(0);
               question.setHead(threadId);
               question.setNumb(0);
               question.setType(0);
               question.setUserId((long) 0);
               answersDao.create(question, conn);
               for(int answerIndex = 0; answerIndex < answers.size(); answerIndex++){
                  IQuestNode answer = answers.get(answerIndex);
                  answer.setHead(threadId);
                  answersDao.create(answer, conn);
               }
            }
         }else{
            throw new DBException("Thread wasn't created");
         }
         error = false;
      }finally{
         writeFinally(conn, st, error);
      }
   }

   public void update(FJThread thread) throws IOException, DBException, ConfigurationException, SQLException{
      Connection conn = null;
      try {
         conn = getConnection();
         update(thread, conn);
      }finally{
         readFinally(conn, null);
      }
   }
   
   public FJThread read(Long id) throws ConfigurationException, SQLException, IOException{
      FJThread thread = null;
      String readThreadQuery = getReadThreadQuery(); 
      PreparedStatement st = null;
      Connection conn = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(readThreadQuery);
         st.setLong(1, id);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            thread = new FJThread();
            thread.setId(id);
            thread.setAuthId(rs.getLong(AUTH_FIELD_NAME));
            thread.setHead(rs.getString(HEAD_FIELD_NAME));
            thread.setRegDate(rs.getDate(REGISTRATION_DATE_FIELD_NAME));
            thread.setLastPostTime(rs.getDate(LAST_POST_DATE_FIELD_NAME));
            thread.setLastPostAuthId(rs.getLong(LAST_POST_USER_ID_FIELD_NAME));
            thread.setLastPostNick(rs.getString(LAST_POST_USER_NICK_FIELD_NAME));
            thread.setLastPostId(rs.getLong(LAST_POST_ID_FIELD_NAME));
            thread.setSnid(rs.getInt(SEEN_ID_FIELD_NAME));
            thread.setSnall(rs.getInt(SEEN_ALL_FIELD_NAME));
            thread.setDock(rs.getInt(DOCK_FIELD_NAME));
            thread.setType(rs.getInt(TYPE_FIELD_NAME));
            thread.setFolderId(rs.getLong(FOLDER_ID_FIELD_NAME));
            thread.setPcount(rs.getInt(POSTS_COUNT_FIELD_NAME));
         }
      }finally{
         readFinally(conn, st);
      }
      return thread;
   }
   
   public void update(IFJThread thread, Connection conn) throws IOException, SQLException{
      String updateThreadQuery = getUpdateThreadQuery(); 
      PreparedStatement st = null;
      try {
         st = conn.prepareStatement(updateThreadQuery);
         st.setString(1, thread.getHead());
         st.setTimestamp(2, new java.sql.Timestamp(thread.getLastPostTime().getTime()));
         st.setLong(3, thread.getLastPostAuthId());
         st.setString(4, thread.getLastPostNick());
         st.setLong(5, thread.getLastPostId());
         st.setInt(6, thread.getDock());
         st.setLong(7, thread.getFolderId());
         st.setInt(8, thread.getPcount());
         st.setLong(9, thread.getId());
         st.executeUpdate();
      }finally{
         readFinally(null, st);
      }
   }

   public long getAddedThreadsAmount(long lastThreadId) throws SQLException, ConfigurationException, IOException{
      long result = 0;
      String query = getAddedThreadsAmountQuery();
      PreparedStatement st = null;
      Connection conn = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, lastThreadId);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = rs.getLong("mx");
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }

   /**
    * Возвращает количество постов в ветке
    * @throws SQLException 
    * @throws ConfigurationException 
    * @throws IOException 
    */
   public Integer getPostsCountInThread(Long threadId, Long idMax) throws ConfigurationException, SQLException, IOException{
      Integer result = null;
      String query = getPostsCountInThreadQuery();
      PreparedStatement st = null;
      Connection conn = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, threadId);
         st.setLong(2, idMax);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = rs.getInt("kolvo");
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
   
   /**
    * Записывает состояние счетчиков просмотров ветки
    *
    * @param $isLogin Авторизован ли посетитель
    * @throws SQLException 
    * @throws ConfigurationException 
    * @throws IOException 
    */
   public void setSeen(IUser user, Long threadId) throws ConfigurationException, SQLException, IOException{
      String query = "";
      if (user.isLogined()){
         query = getSeenByUserQuery();
      }else{
         query = getSeenByGuestQuery();
      }
      PreparedStatement st = null;
      Connection conn = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query);
         st.setLong(1, threadId);
         st.executeUpdate();
      }finally{
         readFinally(conn, st);
      }
   }

   public FJThreads getThreads(Long viewId, long nfirstpost, IUser user, List<IIgnor> ignorList) throws SQLException, ConfigurationException{
      FJThreads result = new FJThreads();
      String sql_views="SELECT folder FROM fdvtranzit WHERE (user=" + user.getId() + " OR user=0) AND view=" + viewId;
      Connection conn = null;
      Statement st = null;
      int xRow=0;
      int isForum=0;
      String folders="(";
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql_views);
         while (rs.next()){
            Long folder = rs.getLong("folder"); 
            if (folder.longValue() == 1){
               isForum = 1;
            }else{
               folders+=" "+folder.toString()+",";
            }
            xRow++;
         }
         if (xRow == 1 && isForum == 1){
            /*есть только форум*/
         }else if(xRow == 0){
            // TODO ошибку надо отловить!
            folders = "(1)";
            isForum = 1;
         }else{
            /*другое*/
            folders=folders.substring(0, folders.length()-1)+")";
         }
         String ignored = null;
         /*выбираем минусы игнора*/
         if (ignorList.size() > 0){
            ignored = "(";
            for (int ignorIndex = 0; ignorIndex < ignorList.size(); ignorIndex++) {
               ignored += ignorList.get(ignorIndex).getUser().getId();
               if (ignorIndex < ignorList.size() -1){
                  ignored += ", ";
               }
            }
            ignored += ")";
         }
         String where = "";
         if (ignored != null){
            where = " WHERE titles.auth NOT IN " + ignored + " ";
         }
         String join=null;
         String sqlTmpJoinTable = null;
         String sqlTmpJoinTableInsert = null;
         String folderName = null;
         if (isForum > 0){
            /*Есть форум*/
            if (xRow == 1){
               /*Есть только форум*/
               /*Определяем минусы - все перемещенное*/
               String sqlMoved=" SELECT title FROM fdtranzit WHERE user="+user.getId() + " ";
               rs = st.executeQuery(sqlMoved);
               String moved = null;
               while (rs.next()){
                  /*перемещения есть*/
                  if (moved == null){
                     moved="(";
                  }
                  moved+=" "+rs.getLong("title")+",";
               }
               if (moved != null){
                  moved=moved.substring(0, moved.length()-1)+")";
               }
               /*Собираем запросы*/
               if (moved != null){
                  if (ignored != null){
                     where+=" AND titles.id NOT IN "+moved+" ";
                  }else{
                     where=" WHERE titles.id NOT IN "+moved+" ";
                  }
               }
               folderName="'Форум' as _flname, ";
               join="";
            }else{
               /*кроме форума есть что-то еще*/
               /*Находим минусы - перемещенные в другие папки*/
               String sqlMoved=" SELECT title FROM fdtranzit WHERE user="+user.getId()+" AND folder NOT IN "+folders + " ";
               rs = st.executeQuery(sqlMoved);
               String moved = null;
               while (rs.next()){
                  /*перемещения есть*/
                  if (moved == null){
                     moved="(";
                  }
                  moved+=" "+rs.getLong("title")+",";
               }
               if (moved != null){
                  moved=moved.substring(0, moved.length()-1)+")";
               }
               /*Собираем запросы*/
               if (moved != null){
                  if (ignored != null){
                     where+=" AND titles.id NOT IN "+moved+" ";
                  }else{
                     where=" WHERE titles.id NOT IN "+moved+" ";
                  }
               }
               folderName="IF (ISNULL(fdfolders.flname), 'Форум', fdfolders.flname) as _flname, ";
               // Временная таблица
               sqlTmpJoinTable="CREATE TEMPORARY TABLE fdutranzit LIKE fdtranzit";
               sqlTmpJoinTableInsert="INSERT INTO fdutranzit (title, folder) SELECT fdtranzit.title, fdtranzit.folder FROM fdtranzit WHERE fdtranzit.user=" + user.getId() + ";";
               join=" LEFT JOIN fdutranzit on titles.id=fdutranzit.title LEFT JOIN fdfolders ON fdutranzit.folder=fdfolders.id ";
            }
         }else{
            /*форума в интерфейсе нет*/
            /*Определяем плюсы - все перемещенное в папки*/
            String sqlMoved="SELECT title FROM fdtranzit WHERE user=" + user.getId() + " AND folder IN " + folders + " ";
            rs = st.executeQuery(sqlMoved);
            String moved = null;
            while (rs.next()){
               /*перемещения есть*/
               if (moved == null){
                  moved="(";
               }
               moved+=" "+rs.getLong("title")+",";
            }
            if (moved != null){
               moved=moved.substring(0, moved.length()-1)+")";
            }
            /*Собираем запросы*/
            if (moved != null){
               if (ignored != null){
                  where+=" AND titles.id NOT IN "+moved+" ";
               }else{
                  where=" WHERE titles.id IN "+moved+" ";
               }
            }else{
               //Ничего нет
               if (ignored != null){
                  where+=" AND 0=1";
               }else{
                  where=" WHERE 0=1";
               }
               
            }
            folderName="IF (ISNULL(fdfolders.flname), 'Форум', fdfolders.flname) as _flname, ";
            // Временная таблица
            sqlTmpJoinTable="CREATE TEMPORARY TABLE fdutranzit LIKE fdtranzit";
            sqlTmpJoinTableInsert="INSERT INTO fdutranzit (title, folder) SELECT fdtranzit.title, fdtranzit.folder FROM fdtranzit WHERE fdtranzit.user=" + user.getId() + ";";
            join="LEFT JOIN fdutranzit on titles.id=fdutranzit.title LEFT JOIN fdfolders ON fdutranzit.folder=fdfolders.id ";
         }
         String sql_main="SELECT "+
         "titles.id, "+
         "titles.dock, "+
         "DATE_ADD(DATE_ADD(titles.lposttime,INTERVAL 0 HOUR), INTERVAL 0 MINUTE) as lposttime_, "+
         "titles.type, "+
         "titles.npost, "+
         "titles.seenid, "+
         "titles.seenall, "+
         "DATE_FORMAT(titles.reg, '%d.%m %H:%i') as reg_, "+
         "titles.head, "+
         "titles.lpostuser, "+
         "titles.lpostnick, "+
         "titles.id_last_post, "+
         folderName + 
         "users.nick "+
         "FROM "+
         "titles force index(titles0001) "+
         "LEFT JOIN users ON titles.auth=users.id "+
         join + 
         where + 
         " ORDER BY "+
         "titles.dock desc, "+
         "titles.lposttime desc "+
         "LIMIT " + nfirstpost + ", " + user.getPt() + " ";

         String sql_count="SELECT "+
         "COUNT(id) as kolvo "+
         "FROM "+
         "titles " + where + ";";
         // Добавляем временную таблицу
         if (sqlTmpJoinTable != null){
            String query = "DROP TEMPORARY TABLE IF EXISTS fdutranzit;";
            st.executeUpdate(query);
            st.executeUpdate(sqlTmpJoinTable);
            st.executeUpdate(sqlTmpJoinTableInsert);
         }
         rs = st.executeQuery(sql_count);
         long count = 0;
         if (rs.next()){
            count = rs.getLong("kolvo");
         }
         rs = st.executeQuery(sql_main);
         result.setThreadCount(count);
         int disain = -1;
         Statement st1 = conn.createStatement();
         ResultSet rs1;
         StringBuffer indctrIds = new StringBuffer();
         while (rs.next()){
            Long id = rs.getLong("id");
            Long idLastPost = rs.getLong("id_last_post"); 
            if (idLastPost.longValue() == 0){
               String query="SELECT MAX(id) as id_post FROM body WHERE head=" + id.toString();
               rs1 = st1.executeQuery(query);
               if (rs1.next()){
                  idLastPost = rs1.getLong("id_post");
               }
               query="UPDATE titles SET id_last_post=" + idLastPost.toString() + " WHERE id=" + id.toString();
               st1.executeUpdate(query);
            }
            indctrIds.append(";" + id.toString() + "," + idLastPost.toString());
            FJThread thr = new FJThread();
            thr.setDisain(disain);
            thr.setId(id);
            thr.setDock(rs.getInt("dock"));
            thr.setLastPostTime(rs.getTimestamp("lposttime_"));
            thr.setHead(rs.getString("head"));
            thr.setNick(rs.getString("nick"));
            thr.setLastPostNick(rs.getString("lpostnick"));
            thr.setPcount(rs.getInt("npost")-1);
            thr.setSnid(rs.getInt("seenid"));
            thr.setSnall(rs.getInt("seenall"));
            thr.setType(rs.getInt("type"));
            thr.setFolder(rs.getString("_flname"));
            result.getThreads().add(thr);
            disain = disain * -1;
         }
         result.setIndctrIds(indctrIds.toString());
      }finally{
         readFinally(conn, st);
      }
      return result;
   }

   /**
    * Возвращает id последней ветки в форуме
    *
    * @return unknown
    * @throws SQLException 
    * @throws ConfigurationException 
    * @throws IOException 
    */
   public Long getMaxThreadId() throws ConfigurationException, SQLException, IOException{
      Long result = 0l;
      String query = getLastThreadIdQuery();
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         if (rs.next()){
            result = rs.getLong("mx");
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
}
