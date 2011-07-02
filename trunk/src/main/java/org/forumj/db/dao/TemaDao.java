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

import java.sql.*;
import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.db.entity.*;
import org.forumj.exception.DBException;
import org.forumj.tool.LocaleString;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class TemaDao extends FJDao {

   /**
    * id Ветки
    *
    * @var
    */
   private Long id;

   /**
    * id Юзера
    */
   private User user;

   /**
    * Массив с игнорами
    *
    * @var unknown_type
    */
   private List<Long> arrIgnorId;

   /**
    * Конструктор
    *
    * @param IConnection $conection
    */
   public TemaDao(Long id, User user){
      this.id = id;
      this.user = user;
      this.arrIgnorId = this.getIgnorArray();
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
    * @return the user
    */
   public User getUser() {
      return user;
   }

   /**
    * @param user the user to set
    */
   public void setUser(User user) {
      this.user = user;
   }

   /**
    * @return the arrIgnorId
    */
   public List<Long> getArrIgnorId() {
      return arrIgnorId;
   }

   /**
    * @param arrIgnorId the arrIgnorId to set
    */
   public void setArrIgnorId(List<Long> arrIgnorId) {
      this.arrIgnorId = arrIgnorId;
   }

   /**
    * Записывает состояние счетчиков просмотров ветки
    *
    * @param $isLogin Авторизован ли посетитель
    */
   public void setSeen(){
      String query = "";
      if (user.isLogined()){
         query = "UPDATE titles SET seenid=seenid + 1, seenall=seenall+1 WHERE id=" +  this.getId();
      }else{
         query = "UPDATE titles SET seenall=seenall+1 WHERE id=" +  this.getId();
      }
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         st.executeUpdate(query);
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
   }

   /**
    * Возвращает заголовок ветки
    */
   public String getTitle(){
      String query="SELECT head FROM titles WHERE id=" +  this.getId();
      String result = null;
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         if (rs.next()){
            result = rs.getString("head");
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
    * Возвращает список постов на странице темы
    *
    * @param unknown_type $str_fd_timezone_hr
    * @param unknown_type $str_fd_timezone_mn
    * @param unknown_type $nfirstpost
    * @param unknown_type $count
    * @param LocaleString $locale
    * @param unknown_type $pg
    * @param unknown_type $lastPost
    * @return unknown
    */
   public List<Post> getPostsList(int timezone_hr, int timezone_mn, long nfirstpost, int count, LocaleString locale, int page, boolean lastPost){
      String query="SELECT * FROM body WHERE body.head=" +  this.getId() + " ORDER BY body.id ASC LIMIT " + nfirstpost + ", " +  count;
      List<Post> result = new ArrayList<Post>();
      Map<Long, Post> postsMap = new HashMap<Long, Post>();
      boolean isQuest = false;
      boolean isAdminForvard = false;
      boolean isUserCanAddAnswer = false;
      boolean hasIgnor = this.getArrIgnorId().size()>0;
      boolean isFirst;
      int nPost = 0;
      Map<String, String> bodiesId = new HashMap<String, String>();
      Map<String, String> headsId = new HashMap<String, String>();
      Long lastPostId = null;
      Connection conn = null;
      Statement st = null;
      QuestNodeDao questDao = new QuestNodeDao();
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         while (rs.next()){
            isFirst = page == 1 && ++nPost == 1;
            lastPostId = rs.getLong("id");
            Post post = new Post(this.getId(), lastPostId, locale, null, getArrIgnorId(), hasIgnor, 0, isAdminForvard, isQuest, isUserCanAddAnswer, page, isFirst, getUser());
            if (lastPost){
               post.setLastPost();
            }
            result.add(post);
            postsMap.put(lastPostId, post);
            String tablePost = rs.getString("table_post");
            String tablePostIds = bodiesId.get(tablePost);
            if (tablePostIds != null){
               tablePostIds += ", " + lastPostId.toString();
            }else{
               tablePostIds = lastPostId.toString();
            }
            bodiesId.put(tablePost, tablePostIds);
            String tableHead = rs.getString("table_head");
            String tableHeadIds = headsId.get(tableHead);
            if (tableHeadIds != null){
               tableHeadIds += ", " + lastPostId.toString();
            }else{
               tableHeadIds = lastPostId.toString();
            }
            headsId.put(tableHead, tableHeadIds);
         }
         //Загружаем заголовки постов
         for (Iterator<Entry<String, String>> iterator = headsId.entrySet().iterator(); iterator.hasNext();) {
            Entry<String, String> entry = iterator.next();
            String table = entry.getKey();
            String ids = entry.getValue();
            query = "SELECT "+
            table + ".id, "+
            table + ".ip, "+
            table + ".auth, "+
            table + ".domen, "+
            table + ".tilte, "+
            table + ".fd_post_time as post_time, "+
            table + ".nred, "+
            table + ".fd_post_edit_time as post_edit_time, "+
            "users.nick, "+
            "users.avatar, "+
            "users.s_avatar, "+
            "users.ok_avatar, "+
            "users.v_avatars, "+
            "users.h_ip, "+
            "users.city, "+
            "users.scity, "+
            "users.country, "+
            "users.scountry, "+
            "users.footer, "+
            "titles.head, "+
            "titles.type "+
            "FROM "+
            "(" + table + 
            " LEFT JOIN users ON " + table + ".auth = users.id) "+
            " LEFT JOIN titles ON " + table + ".thread_id = titles.id "+
            " WHERE " + table + ".id IN (" + ids + ") ";
            rs = st.executeQuery(query);
            while (rs.next()){
               Long postId = rs.getLong("id");
               int type = rs.getInt("type");
               isQuest = (type == 1 || type == 2);
               Post post = postsMap.get(postId);
               if (post.isFirst() && isQuest){
                  post.setQuest();

                  List<QuestNode> questNodes = questDao.loadNodes(getId());
                  post.setAnswerAmount(questNodes.size());
                  post.setVoicesAmount(this.getVoicesAmount());
                  post.setNodes(questNodes);
                  post.setQuestion(questNodes.get(0).getNode());
                  post.setUserVote(this.isUserVote());
               }
               post.setNick(rs.getString("nick"));
               post.setIdu(rs.getLong("auth"));
               post.setIp(rs.getString("ip"));
               post.setAvatar(rs.getString("avatar"));
               post.setShowAvatar(rs.getInt("users.s_avatar") == 1);
               post.setShowAvatarApproved(rs.getInt("ok_avatar") == 1);
               post.setWantSeeAvatars(rs.getInt("v_avatars") == 1);
               post.setCountry(rs.getString("country"));
               post.setShowCountry(rs.getInt("scountry") == 1);
               post.setPostTime(rs.getLong("post_time"), timezone_hr,timezone_mn);
               post.setCity(rs.getString("city"));
               post.setShowCity(rs.getInt("scity") == 1);
               post.setPostFooter(rs.getString("footer"));
               post.setDomen(rs.getString("domen"));
               post.setHead(rs.getString("tilte"));
            }
         }
         //Загружаем посты
         for (Iterator<Entry<String, String>> iterator = bodiesId.entrySet().iterator(); iterator.hasNext();) {
            Entry<String, String> entry = iterator.next();
            String table = entry.getKey();
            String ids = entry.getValue();
            query = "SELECT id, body FROM " + table + " WHERE id IN (" + ids + ")";
            rs = st.executeQuery(query);
            while (rs.next()){
               Long postId = rs.getLong("id");
               Post post = postsMap.get(postId);
               post.setBody(rs.getString("body"));
            }
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
    * Возвращает количество постов в ветке
    */
   public Integer getPostsCountInThread(Long idMax){
      Integer result = null;
      String addQuery = "";
      if (idMax != null){
         addQuery = " AND id < " +  idMax;
      }
      String query = "SELECT count(id) as kolvo FROM body WHERE head=" +  this.getId() + addQuery;
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         if (rs.next()){
            result = rs.getInt("kolvo");
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
    * Возвращает игнор пользователя
    * TODO Этот метод должен быть у пользователя
    * @param unknown_type $idUser
    * @return unknown
    */
   private List<Long> getIgnorArray(){
      List<Long> result = new ArrayList<Long>();
      String query = "SELECT ignor FROM ignor WHERE user=" + this.getUser().getId() + " and end > now()";
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
    * Возвращает id последнего поста в ветке
    */
   public Integer getMaxId(){
      Integer result = null;
      String query = "SELECT MAX(id) as mx FROM body WHERE head=" +  this.getId();
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         if (rs.next()){
            result = rs.getInt("mx");
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
    * Возвращает подписан ли посетитель на ветку
    *
    * @param unknown_type $idUser
    * @return unknown
    */
   public Boolean isUserSubscribed(Long idUser){
      String query = "SELECT id FROM fd_subscribe WHERE user=" +  idUser  + " AND title=" +  this.getId();
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         if (rs.next()){
            return true;
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
      return false;
   }

   /**
    * Возвращает пост по его id
    *
    * @param postId
    */
   public Post getPost(Long postId){
      Post result = null;
      String query="SELECT body.table_post, body.table_head FROM body WHERE body.id=" + postId;
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         if (rs.next()){
            String tableHead = rs.getString("table_head");
            String tablePost = rs.getString("table_post");
            query="SELECT " +
            "users.nick, " +
            tableHead + ".tilte, " +
            tableHead + ".auth, " +
            tableHead + ".thread_id " +
            "FROM " +
            "(" + tableHead + 
            " LEFT JOIN users ON " + tableHead + ".auth = users.id) " +
            " LEFT JOIN titles ON " + tableHead + ".thread_id = titles.id " +
            " WHERE " + tableHead + ".id=" + postId;
            rs = st.executeQuery(query);
            if (rs.next()){
               result = new Post(postId);
               result.setHead(rs.getString("tilte"));
               result.setIdu(rs.getLong("auth"));
               result.setNick(rs.getString("nick"));
               result.setTablePost(tablePost);
               result.setTableHead(tableHead);
               result.setIdThread(rs.getLong("thread_id"));
            }
            query = "SELECT body FROM " + tablePost + " WHERE id=" + postId;
            rs = st.executeQuery(query);
            if (rs.next()){
               result.setBody(rs.getString("body"));
            }
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
    * Возвращает, является ли ветка опросом
    *
    * @param unknown_type $id
    * @return unknown
    */
   public Boolean isQuest(){
      String query="SELECT type FROM titles WHERE id=" +  this.getId();
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         if (rs.next()){
            Integer type = rs.getInt("type");
            return type == 1 || type == 2;
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
      return false;
   }

   /**
    * Возвращает, есть ли уже голос текущего юзера
    * в опросе
    */
   public boolean isUserVote(){
      String query="SELECT user FROM voice WHERE head=" + this.getId() + " AND user=" + this.getUser().getId().toString();
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         if (rs.next()){
            return true;
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
      return false;
   }

   /**
    * Возвращает количество проголосовавших
    * в опросе
    */
   public int getVoicesAmount(){
      String query = "SELECT COUNT(id) AS nvcs FROM voice WHERE head=" + this.getId();
      Connection conn = null;
      Statement st = null;
      int result = 0;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         if (rs.next()){
            result = rs.getInt("nvcs");
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
