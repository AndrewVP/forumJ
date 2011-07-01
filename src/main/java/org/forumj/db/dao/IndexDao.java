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
package org.forumj.db.dao;

import static org.forumj.tool.PHP.*;

import java.sql.*;
import java.util.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.db.entity.*;
import org.forumj.exception.DBException;
import org.forumj.tool.LocaleString;


/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class IndexDao extends FJDao {

   /**
    * Юзер
    */
   private User user;

   /**
    * Количество веток
    */
   private int threadCount;

   /**
    * Массив с игнорами
    *
    * @var unknown_type
    */
   private List<Long> arrIgnorId = null;

   /**
    * Строка id для индикаторов
    *
    * @var unknown_type
    */
   private String indctrIds = "";

   /**
    * Конструктор
    */
   public IndexDao(User user){
      super();
      this.user =  user;
      this.arrIgnorId = this.getIgnorArray();
   }

   /**
    * Возвращает id Юзера
    *
    * @return unknown
    */
   private Long getIdUser(){
      return this.user.getId();
   }

   /**
    * Возвращает количество веток
    *
    * @return unknown
    */
   public int getThreadCount(){
      return this.threadCount;
   }

   /**
    * Возвращает игнор текущего юзера
    *
    * @return unknown
    */
   public List<Long> getArrIgnorId(){
      return this.arrIgnorId;
   }

   /**
    * Возвращает строку id для индикаторов
    *
    * @return unknown
    */
   public String getIndctrIds(){
      return this.indctrIds;
   }

   /**
    * Возвращает игнор пользователя
    * TODO Этот метод должен быть у пользователя
    * @param unknown_type idUser
    * @return unknown
    */
   private List<Long> getIgnorArray(){
      List<Long> result = new ArrayList<Long>();
      String query = "SELECT ignor FROM ignor WHERE user=" + this.getIdUser() + " and end > now()";
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         while (rs.next()){
            result.add(rs.getLong("ignor"));
         }
      } catch (ConfigurationException e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      } catch (SQLException e) {
         DBException ex = new DBException(e);
         onDatabaseError(ex);
         e.printStackTrace();
         throw new RuntimeException(e);
      }finally{
         try {
            if (st != null){
               st.close();
            }
            if (conn != null){
               conn.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return result;
   }

   /**
    * Возвращает id последнего поста в форуме
    *
    * @return unknown
    */
   public Long getMaxPostId(){
      Long result = 0l;
      String query="SELECT max(id) as mx FROM body";
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         if (rs.next()){
            result = rs.getLong("mx");
         }
      } catch (ConfigurationException e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      } catch (SQLException e) {
         DBException ex = new DBException(e);
         onDatabaseError(ex);
         e.printStackTrace();
         throw new RuntimeException(e);
      }finally{
         try {
            if (st != null){
               st.close();
            }
            if (conn != null){
               conn.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return result;
   }

   /**
    * Возвращает id последней ветки в форуме
    *
    * @return unknown
    */
   public Long getMaxThreadId(){
      Long result = 0l;
      String query="SELECT max(id) as mx FROM titles";
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         if (rs.next()){
            result = rs.getLong("mx");
         }
      } catch (ConfigurationException e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      } catch (SQLException e) {
         DBException ex = new DBException(e);
         onDatabaseError(ex);
         e.printStackTrace();
         throw new RuntimeException(e);
      }finally{
         try {
            if (st != null){
               st.close();
            }
            if (conn != null){
               conn.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return result;
   }

   public List<FJThread> getThreads(Long viewId, int threadCountPerPage, long nfirstpost, LocaleString locale, Boolean isLogin, int pg, int pt){
      List<FJThread> result = new ArrayList<FJThread>();
      String sql_views="SELECT folder FROM fdvtranzit WHERE (user=" + this.getIdUser() + " OR user=0) AND view=" + viewId;
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
            folders=substr(folders, 0, strlen(folders)-1)+")";
         }
         String ignored = null;
         /*выбираем минусы игнора*/
         if (this.getArrIgnorId().size() > 0){
            ignored = "("+implode(", ", this.getArrIgnorId())+")";
         }
         String where = "";
         if (isset(ignored)){
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
               String sqlMoved=" SELECT title FROM fdtranzit WHERE user="+this.getIdUser() + " ";
               rs = st.executeQuery(sqlMoved);
               String moved = null;
               while (rs.next()){
                  /*перемещения есть*/
                  if (moved == null){
                     moved="(";
                  }
                  moved+=" "+rs.getLong("title")+",";
               }
               moved=substr(moved, 0, strlen(moved)-1)+")";
               /*Собираем запросы*/
               if (isset(moved)){
                  if (isset(ignored)){
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
               String sqlMoved=" SELECT title FROM fdtranzit WHERE user="+this.getIdUser()+" AND folder NOT IN "+folders + " ";
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
                  moved=substr(moved, 0, strlen(moved)-1)+")";
               }
               /*Собираем запросы*/
               if (isset(moved)){
                  if (isset(ignored)){
                     where+=" AND titles.id NOT IN "+moved+" ";
                  }else{
                     where=" WHERE titles.id NOT IN "+moved+" ";
                  }
               }
               folderName="IF (ISNULL(fdfolders.flname), 'Форум', fdfolders.flname) as _flname, ";
               // Временная таблица
               sqlTmpJoinTable="CREATE TEMPORARY TABLE fdutranzit LIKE fdtranzit";
               sqlTmpJoinTableInsert="INSERT INTO fdutranzit (title, folder) SELECT fdtranzit.title, fdtranzit.folder FROM fdtranzit WHERE fdtranzit.user=" + this.getIdUser() + ";";
               join=" LEFT JOIN fdutranzit on titles.id=fdutranzit.title LEFT JOIN fdfolders ON fdutranzit.folder=fdfolders.id ";
            }
         }else{
            /*форума в интерфейсе нет*/
            /*Определяем плюсы - все перемещенное в папки*/
            String sqlMoved="SELECT title FROM fdtranzit WHERE user=" + this.getIdUser() + " AND folder IN " + folders + " ";
            rs = st.executeQuery(sqlMoved);
            String moved = null;
            while (rs.next()){
               /*перемещения есть*/
               if (moved == null){
                  moved="(";
               }
               moved+=" "+rs.getLong("title")+",";
            }
            moved=substr(moved, 0, strlen(moved)-1)+")";
            /*Собираем запросы*/
            if (isset(moved)){
               if (isset(ignored)){
                  where+=" AND titles.id NOT IN "+moved+" ";
               }else{
                  where=" WHERE titles.id NOT IN "+moved+" ";
               }
            }
            folderName="IF (ISNULL(fdfolders.flname), 'Форум', fdfolders.flname) as _flname, ";
            // Временная таблица
            sqlTmpJoinTable="CREATE TEMPORARY TABLE fdutranzit LIKE fdtranzit";
            sqlTmpJoinTableInsert="INSERT INTO fdutranzit (title, folder) SELECT fdtranzit.title, fdtranzit.folder FROM fdtranzit WHERE fdtranzit.user=" + this.getIdUser() + ";";
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
         "LIMIT " + nfirstpost + ", " + threadCountPerPage + " ";

         String sql_count="SELECT "+
         "COUNT(id) as kolvo "+
         "FROM "+
         "titles " + where + ";";
         // Добавляем временную таблицу
         if (isset(sqlTmpJoinTable)){
            String query = "DROP TEMPORARY TABLE IF EXISTS fdutranzit;";
            st.executeUpdate(query);
            st.executeUpdate(sqlTmpJoinTable);
            st.executeUpdate(sqlTmpJoinTableInsert);
         }
         rs = st.executeQuery(sql_count);
         int count = 0;
         if (rs.next()){
            count = rs.getInt("kolvo");
         }
         rs = st.executeQuery(sql_main) ;
         this.threadCount = count;
         int disain = -1;
         int i = 0;
         Statement st1 = conn.createStatement();
         ResultSet rs1;
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
            this.indctrIds+=";" + id.toString() + "," + idLastPost.toString();
            FJThread thr = new FJThread();
            thr.setLocale(locale);
            thr.setDisain(disain);
            thr.setCurrentUser(user);
            thr.setId(id);
            thr.setDock(rs.getInt("dock"));
            thr.setLastPostTime(rs.getDate("lposttime_"));
            thr.setHead(rs.getString("head"));
            thr.setNick(rs.getString("nick"));
            thr.setLastPostNick(rs.getString("lpostnick"));
            thr.setPcount(rs.getInt("npost")-1);
            thr.setSnid(rs.getInt("seenid"));
            thr.setSnall(rs.getInt("seenall"));
            thr.setType(rs.getInt("type"));
            thr.setFolder(rs.getString("_flname"));
            thr.setPg(pg);
            thr.setPt(pt);
            thr.setI(i);
            result.add(thr);
            disain = disain * -1;
         }
      } catch (ConfigurationException e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      } catch (SQLException e) {
         DBException ex = new DBException(e);
         onDatabaseError(ex);
         e.printStackTrace();
         throw new RuntimeException(e);
      }finally{
         try {
            if (st != null){
               st.close();
            }
            if (conn != null){
               conn.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return result;
   }

   public int getNewMailCount(Long idUser){
      int result = 0;
      String sql_newmail="SELECT COUNT(*) as nmail FROM fdmail WHERE rcvr=" + idUser + " AND d_rcv IS NULL ";
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql_newmail);
         if (rs.next()){
            result = rs.getInt("nmail");
         }
      } catch (ConfigurationException e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      } catch (SQLException e) {
         DBException ex = new DBException(e);
         onDatabaseError(ex);
         e.printStackTrace();
         throw new RuntimeException(e);
      }finally{
         try {
            if (st != null){
               st.close();
            }
            if (conn != null){
               conn.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return result;
   }

   public String getCurrentViewName(Long idView){
      String result = null;
      String sql_vname="SELECT name FROM fdviews WHERE id=" + idView.toString();
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql_vname);
         if (rs.next()){
            result = rs.getString("name");
         }
      } catch (ConfigurationException e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      } catch (SQLException e) {
         DBException ex = new DBException(e);
         onDatabaseError(ex);
         e.printStackTrace();
         throw new RuntimeException(e);
      }finally{
         try {
            if (st != null){
               st.close();
            }
            if (conn != null){
               conn.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return result;
   }

   public List<Map<String, Object>> getViewsArray(Long idUser){
      List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
      String sql_views="SELECT id, name FROM fdviews WHERE user=0 OR user=" + idUser.toString() + " ORDER BY id ";
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql_views);
         while (rs.next()){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", rs.getLong("id")) ;
            map.put("name", rs.getString("name")) ;
            result.add(map);
         }
      } catch (ConfigurationException e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      } catch (SQLException e) {
         DBException ex = new DBException(e);
         onDatabaseError(ex);
         e.printStackTrace();
         throw new RuntimeException(e);
      }finally{
         try {
            if (st != null){
               st.close();
            }
            if (conn != null){
               conn.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return result;
   }

   public List<Map<String, Object>> getFoldersArray(Long idUser){
      List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
      String sql_views="SELECT id, flname FROM fdfolders WHERE user=0 OR user=" + idUser.toString() + " ORDER BY id ";
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql_views);
         while (rs.next()){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", rs.getLong("id")) ;
            map.put("flname", rs.getString("flname")) ;
            result.add(map);
         }
      } catch (ConfigurationException e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      } catch (SQLException e) {
         DBException ex = new DBException(e);
         onDatabaseError(ex);
         e.printStackTrace();
         throw new RuntimeException(e);
      }finally{
         try {
            if (st != null){
               st.close();
            }
            if (conn != null){
               conn.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return result;
   }

   public List<Map<String, Object>> getUsersArray(){
      List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
      String query33="SELECT "+
      "DISTINCT users.nick AS nicks "+
      "FROM fd_action "+
      "LEFT JOIN users on fd_action.fd_user=users.id "+
      "WHERE (fd_action.fd_user<>0) and (fd_action.fd_time >now() - INTERVAL 20 MINUTE) "+
      "and (fd_action.fd_user<>95) and (users.ban=0) "+
      "ORDER BY users.nick";
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query33);
         while (rs.next()){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("nick", rs.getString("nicks")) ;
            result.add(map);
         }
      } catch (ConfigurationException e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      } catch (SQLException e) {
         DBException ex = new DBException(e);
         onDatabaseError(ex);
         e.printStackTrace();
         throw new RuntimeException(e);
      }finally{
         try {
            if (st != null){
               st.close();
            }
            if (conn != null){
               conn.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return result;
   }

   public int getGuestCount(){
      Integer result = 0;
      String query34="" +
      "SELECT" +
      "  count(DISTINCT fd_ip) as guests " +
      "FROM " +
      "  fd_action " +
      "WHERE " +
      "  (fd_user=0) " +
      "  AND (fd_time > now() - INTERVAL 20 MINUTE) ";
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query34);
         if (rs.next()){
            result = rs.getInt("guests");
         }
      } catch (ConfigurationException e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      } catch (SQLException e) {
         DBException ex = new DBException(e);
         onDatabaseError(ex);
         e.printStackTrace();
         throw new RuntimeException(e);
      }finally{
         try {
            if (st != null){
               st.close();
            }
            if (conn != null){
               conn.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return result;
   }
}
