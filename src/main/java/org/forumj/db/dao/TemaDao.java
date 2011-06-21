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
      int result = 0;
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
   public List<Post> getPostsList(int $str_fd_timezone_hr, int $str_fd_timezone_mn, long $nfirstpost, int $count, LocaleString $locale, int $pg, boolean $lastPost){
      int xx
      String query="SELECT * FROM body WHERE body.head=" +  this.getId() + " ORDER BY body.id ASC LIMIT " + $nfirstpost + ", " +  $count;
      List<Post> $result = new ArrayList<Post>();
      boolean $isLastPost = false;
      boolean $isQuest = false;
      boolean $isAdminForvard = false;
      boolean $isUserCanAddAnswer = false;
      boolean $hasIgnor = this.getArrIgnorId().size()>0;
      boolean $isFirst;
      int $nPost = 0;
      List<Long> $bodiesId = new ArrayList<Long>();
      List<Long> $headsId = new ArrayList<Long>();
      Long $lastPostId = null;
      Connection conn = null;
      Statement st = null;
      int result = 0;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         if (rs.next()){
            $isFirst = $pg == 1 && ++$nPost == 1;
            $lastPostId = rs.getLong("id");
            Post post = new Post(idThread, idPost, locale, idWordsForms, res_ignor, has_ignor, is_found, isAdminForvard, isQuest, isUserCanAddAnswer, pg, isFirst, currentUser)
            $result[$row['id']] = new Post($row,
                                 $locale,
                                 NULL,
                                 this.arrIgnorId(),
                                 $hasIgnor,
                                 0,
                                 $isAdminForvard,
                                 $isQuest,
                                 $isUserCanAddAnswer,
                                 $pg,
                                 this.connection,
                                 $isFirst);
            if (isset($bodiesId[$row['table_post']])){
               $bodiesId[$row['table_post']] .= ", " + $row['id'];
            }else{
               $bodiesId[$row['table_post']] = $row['id'];
            }
            if (isset($headsId[$row['table_head']])){
               $headsId[$row['table_head']] .= ", " + $row['id'];
            }else{
               $headsId[$row['table_head']] = $row['id'];
            }
         }
         if ($lastPost){
            $result[$lastPostId].setLastPost();
         }
         $resultSet.__destruct();
         //Загружаем заголовки постов
         foreach ($headsId as $table => $ids) {
            $sqlHead = "SELECT "+
                   + $table + " + id, "+
                   + $table + " + ip, "+
                   + $table + " + auth, "+
                   + $table + " + domen, "+
                   + $table + " + tilte, "+
                   + $table + " + fd_post_time as post_time, "+
                   + $table + " + nred, "+
                   + $table + " + fd_post_edit_time as post_edit_time, "+
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
                    "(" + $table + 
                    "LEFT JOIN users ON " + $table + " + auth = users.id) "+
                    "LEFT JOIN titles ON " + $table + " + thread_id = titles.id "+
                  "WHERE "+
                  + $table + " + id IN (" + $ids + ") ";
            try{
               $resultSetHead = this.connection.doQuery(new QueryResult($sqlHead));
            }catch (MySQLQueryException $e){
               this.onDatabaseError($e);
            }
            this.onQuery($resultSetHead);
            while ($row = mysql_fetch_assoc($resultSetHead.getResult())){
               $isQuest = ($row['type'] == 1 || $row['type'] == 2);
               if ($result[$row['id']].isFirst() && $isQuest){
                  $result[$row['id']].setQuest();
                  $questNodes = this.getQuestNodes();
                  $result[$row['id']].setAnswerAmount($questNodes.getNumRows());
                  $result[$row['id']].setVoicesAmount(this.getVoicesAmount());
                  $arrNodes = array();
                  while ($node = mysql_fetch_assoc($questNodes.getResult())){
                     $arrNodes[] = $node;
                  }
                  $result[$row['id']].setNodes($arrNodes);
                  $result[$row['id']].setQuestion($questNodes.get('node'));
                  $result[$row['id']].setUserVote(this.isUserVote());
               }
               $result[$row['id']].loadHeads($row, $str_fd_timezone_hr,$str_fd_timezone_mn);
            }
            $resultSetHead.__destruct();
         }
         //Загружаем посты
         foreach ($bodiesId as $table => $ids) {
            $sqlBody = "SELECT id, body FROM " + $table + " WHERE id IN (" + $ids + ")";
            try{
               $resultSetBody = this.connection.doQuery(new QueryResult($sqlBody));
            }catch (MySQLQueryException $e){
               this.onDatabaseError($e);
            }
            this.onQuery($resultSetBody);
            while ($row = mysql_fetch_assoc($resultSetBody.getResult())){
               $result[$row['id']].setBody($row['body']);
            }
            $resultSetBody.__destruct();
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
      return $result;
   }

   /**
    * Возвращает количество постов в ветке
    */
   public function getPostsCountInThread($idMax){
      $addQuery = "";
      if ($idMax != ""){
         $addQuery = " AND id < " +  $idMax;
      }
      String query = "SELECT count(id) as kolvo FROM body WHERE head=" +  this.getId() . $addQuery;
      try{
         $result = this.connection.doQuery(new QueryResult($query));
      }catch (MySQLQueryException $e){
         this.onDatabaseError($e);
      }
      this.onQuery($result);
      $count = mysql_result($result.getResult(), 0, 'kolvo');
      $result.__destruct();
      return $count;
   }

   /**
    * Возвращает игнор пользователя
    * TODO Этот метод должен быть у пользователя
    * @param unknown_type $idUser
    * @return unknown
    */
   private List<Long> getIgnorArray(){
      String query = "SELECT ignor FROM ignor WHERE user=" +  this.getIdUser()  + " AND end > NOW()";
      try{
         $ignorRS = this.connection.doQuery(new QueryResult($query));
      }catch (MySQLQueryException $e){
         this.onDatabaseError($e);
      }
      this.onQuery($ignorRS);
      $result = array();
      while ($row = mysql_fetch_assoc($ignorRS.getResult())){
         $result[] = $row['ignor'];
      }
      $ignorRS.__destruct();
      return $result;
   }

   /**
    * Возвращает id последнего поста в ветке
    */
   public function getMaxId(){
      String query = "SELECT MAX(id) as mx FROM body WHERE head=" +  this.getId();
      try{
         $result = this.connection.doQuery(new QueryResult($query));
      }catch (MySQLQueryException $e){
         this.onDatabaseError($e);
      }
      this.onQuery($result);
      $str_maxp = mysql_result($result.getResult(), 0, 'mx');
      $result.__destruct();
      return $str_maxp;
   }

   /**
    * Возвращает подписан ли посетитель на ветку
    *
    * @param unknown_type $idUser
    * @return unknown
    */
   public function isUserSubscribed($idUser){
      String query = "SELECT id FROM fd_subscribe WHERE user=" +  $idUser  + " AND title=" +  this.getId();
      try{
         $result = this.connection.doQuery(new QueryResult($query));
      }catch (MySQLQueryException $e){
         this.onDatabaseError($e);
      }
      this.onQuery($result);
      $isSubscribed = mysql_num_rows($result.getResult()) > 0;
      $result.__destruct();
      return $isSubscribed;
   }

   /**
    * Возвращает пост по его id
    *
    * @param $postId
    */
   public function getPost($postId){
   String query="SELECT body.table_post, body.table_head FROM body WHERE body.id=" + $postId;
      try{
         $resultSet = this.connection.doQuery(new QueryResult($query));
      }catch (MySQLQueryException $e){
         this.onDatabaseError($e);
      }
      this.onQuery($resultSet);
      $result = array();
      $result = mysql_fetch_assoc($resultSet.getResult());
 query="SELECT " +
         "users.nick, " +
         $result['table_head'] + ".tilte, " +
         $result['table_head'] + ".auth " +
      "FROM " +
         "(" + $result['table_head'] + 
         "LEFT JOIN users ON " + $result['table_head'] + ".auth = users.id) " +
         "LEFT JOIN titles ON " + $result['table_head'] + ".thread_id = titles.id " +
      "WHERE " + $result['table_head'] + ".id=" + $postId;
      try{
         $resultSet = this.connection.doQuery(new QueryResult($query));
      }catch (MySQLQueryException $e){
         this.onDatabaseError($e);
      }
      this.onQuery($resultSet);
      $result['tilte'] = mysql_result($resultSet.getResult(), 0, 'tilte');
      $result['nick'] = mysql_result($resultSet.getResult(), 0, 'nick');
      $result['auth'] = mysql_result($resultSet.getResult(), 0, 'auth');
      $resultSet.__destruct();
      $result['id'] = $postId;
      $sql_body = "SELECT body FROM " + $result['table_post'] + " WHERE id=" + $postId;
      try{
         $res_body = this.connection.doQuery(new QueryResult($sql_body));
      }catch (MySQLQueryException $e){
         this.onDatabaseError($e);
      }
      this.onQuery($res_body);
      $result['body'] = mysql_result($res_body.getResult(), 0, 'body');
      $res_body.__destruct();
      return $result;
   }

   /**
    * Возвращает, является ли ветка опросом
    *
    * @param unknown_type $id
    * @return unknown
    */
   public function isQuest($id){
      $sqlQuest="SELECT type FROM titles WHERE id=" +  this.getId();
      try{
         $resQuest = this.connection.doQuery(new QueryResult($sqlQuest));
      }catch (MySQLQueryException $e){
         this.onDatabaseError($e);
      }
      this.onQuery($resQuest);
      $type = mysql_result($resQuest.getResult(), 0, 'type');
      $resQuest.__destruct();
      return $type == 1 || $type == 2;
   }

   /**
    * Возвращает результат запроса с вариантами ответов
    */
   public function getQuestNodes(){
      String query="SELECT quest.id, quest.node, quest.user, quest.gol, quest.type, users.nick FROM quest LEFT JOIN users ON quest.user=users.id WHERE quest.head=" + this.getId() + " ORDER BY numb";
      try{
         $result = this.connection.doQuery(new SelectQueryResult($query));
      }catch (MySQLQueryException $e){
         this.onDatabaseError($e);
      }
      this.onQuery($result);
      return $result;
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
