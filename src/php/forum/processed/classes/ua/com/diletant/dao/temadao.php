<?php
include_once 'classes/ua/com/diletant/business/post.php';
include_once 'classes/ua/com/diletant/sql/result/queryresult.php';
include_once 'classes/ua/com/diletant/sql/result/selectqueryresult.php';
include_once 'classes/ua/com/diletant/dao/dao.php';

/*
 * DAO - класс для страницы tema.php
 * (просмотр ветки)
 */
class TemaDao extends Dao {

	/**
	 * id Ветки
	 *
	 * @var
	 */
	private $id;

	/**
	 * id Юзера
	 */
	private $idUser;

	/**
	 * Массив с игнорами
	 *
	 * @var unknown_type
	 */
	private $arrIgnorId;

	/**
	 * Конструктор
	 *
	 * @param IConnection $conection
	 */
	public function __construct(IConnection $conection, $id, $idUser){
		$this->connection = $conection;
		$this->setId($id);
		$this->setIdUser($idUser);
		$this->arrIgnorId = $this->getIgnorArray();
	}

	/**
	 * Устанавливает id ветки
	 *
	 * @param $id
	 */
	private function  setId($id){
		$this->id = $id;
	}

	/**
	 * Возвращает id ветки
	 *
	 * @return
	 */
	private function getId(){
		return $this->id;
	}

	/**
	 * Записывает состояние счетчиков просмотров ветки
	 *
	 * @param $isLogin Авторизован ли посетитель
	 */
	public function setSeen($isLogin){
		$query = "
		SELECT
			seenid,
			seenall
		FROM
			titles
		WHERE id=". $this->getId();
      try{
			$result = $this->connection->doQuery(new QueryResult($query));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($result);
		$seenId = mysql_result($result->getResult(), 0, 'seenid');
		$seenAll = mysql_result($result->getResult(), 0, 'seenall');
		$result->__destruct();
		if ($isLogin){
			$query = "
         UPDATE
         	titles
         SET
         	seenid=". ($seenId + 1) .",
            seenall=". ($seenAll + 1) ."
         WHERE id=". $this->getId();
		}else{
			$query = "
         UPDATE
         	titles
         SET
            seenall=". ($seenAll + 1) ."
         WHERE id=". $this->getId();
		}
      try{
			$result = $this->connection->doQuery(new QueryResult($query));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($result);
	}

	/**
	 * Возвращает заголовок ветки
	 */
	public function getTitle(){
		$sql_head="
	   SELECT
	      head
	   FROM
	      titles
	   WHERE
	      id=". $this->getId();
      try{
		   $res_head = $this->connection->doQuery(new QueryResult($sql_head));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($res_head);
      $title=mysql_result($res_head->getResult(), 0, 'head');
		$res_head->__destruct();
		return $title;
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
	public function getPostsList($str_fd_timezone_hr, $str_fd_timezone_mn, $nfirstpost, $count, LocaleString $locale, $pg, $lastPost){
	   $query="SELECT *
	           FROM
	              body
	           WHERE body.head=". $this->getId() ."
	           ORDER BY body.id ASC
	           LIMIT ".$nfirstpost.", ". $count;
      try{
		   $resultSet = $this->connection->doQuery(new QueryResult($query));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($resultSet);
      $result = array();
	   $isLastPost = false;
	   $isQuest = false;
	   $isAdminForvard = false;
	   $isUserCanAddAnswer = false;
	   $hasIgnor = sizeof($this->arrIgnorId()) != 0;
	   $nPost = 0;
	   $bodiesId = array();
	   $headsId = array();
	   $lastPostId = "";
	   while ($row = mysql_fetch_assoc($resultSet->getResult())){
	   	$isFirst = $pg == 1 && ++$nPost == 1;
	   	$lastPostId = $row['id'];
	   	$result[$row['id']] = new Post($row,
	   								$locale,
	   								NULL,
	   								$this->arrIgnorId(),
	   								$hasIgnor,
	   								0,
	   								$isAdminForvard,
	   								$isQuest,
	   								$isUserCanAddAnswer,
	   								$pg,
	   								$this->connection,
	   								$isFirst);
	   	if (isset($bodiesId[$row['table_post']])){
	   		$bodiesId[$row['table_post']] .= ", ".$row['id'];
	   	}else{
	   		$bodiesId[$row['table_post']] = $row['id'];
	   	}
	   	if (isset($headsId[$row['table_head']])){
	   		$headsId[$row['table_head']] .= ", ".$row['id'];
	   	}else{
	   		$headsId[$row['table_head']] = $row['id'];
	   	}
	   }
	   if ($lastPost){
	   	$result[$lastPostId]->setLastPost();
	   }
		$resultSet->__destruct();
		//Загружаем заголовки постов
		foreach ($headsId as $table => $ids) {
			$sqlHead = "
				SELECT
					".$table.".id,
	            ".$table.".ip,
	            ".$table.".auth,
	            ".$table.".domen,
	            ".$table.".tilte,
	            ".$table.".fd_post_time as post_time,
	            ".$table.".nred,
	            ".$table.".fd_post_edit_time as post_edit_time,
	            users.nick,
	            users.avatar,
	            users.s_avatar,
	            users.ok_avatar,
	            users.v_avatars,
	            users.h_ip,
	            users.city,
	            users.scity,
	            users.country,
	            users.scountry,
	            users.footer,
	            titles.head,
	            titles.type
	         FROM
	              (".$table."
	              LEFT JOIN users ON ".$table.".auth = users.id)
	              LEFT JOIN titles ON ".$table.".thread_id = titles.id
					WHERE
					".$table.".id IN (".$ids.")
			";
	      try{
		   	$resultSetHead = $this->connection->doQuery(new QueryResult($sqlHead));
	      }catch (MySQLQueryException $e){
	      	$this->onDatabaseError($e);
	      }
      	$this->onQuery($resultSetHead);
	      while ($row = mysql_fetch_assoc($resultSetHead->getResult())){
	   		$isQuest = ($row['type'] == 1 || $row['type'] == 2);
	   		if ($result[$row['id']]->isFirst() && $isQuest){
	   			$result[$row['id']]->setQuest();
	   			$questNodes = $this->getQuestNodes();
	   			$result[$row['id']]->setAnswerAmount($questNodes->getNumRows());
	   			$result[$row['id']]->setVoicesAmount($this->getVoicesAmount());
	   			$arrNodes = array();
			      while ($node = mysql_fetch_assoc($questNodes->getResult())){
			      	$arrNodes[] = $node;
			      }
	   			$result[$row['id']]->setNodes($arrNodes);
	   			$result[$row['id']]->setQuestion($questNodes->get('node'));
	   			$result[$row['id']]->setUserVote($this->isUserVote());
	   		}
		   	$result[$row['id']]->loadHeads($row, $str_fd_timezone_hr,$str_fd_timezone_mn);
		   }
			$resultSetHead->__destruct();
		}
		//Загружаем посты
		foreach ($bodiesId as $table => $ids) {
			$sqlBody = "
				SELECT
					id,
					body
				FROM
					".$table."
				WHERE
					id IN (".$ids.")
			";
	      try{
		   	$resultSetBody = $this->connection->doQuery(new QueryResult($sqlBody));
	      }catch (MySQLQueryException $e){
	      	$this->onDatabaseError($e);
	      }
      	$this->onQuery($resultSetBody);
	      while ($row = mysql_fetch_assoc($resultSetBody->getResult())){
		   	$result[$row['id']]->setBody($row['body']);
		   }
			$resultSetBody->__destruct();
		}
	   return $result;
	}

	/**
	 * Возвращает количество постов в ветке
	 */
	public function getPostsCountInThread($idMax){
		$addQuery = "";
		if ($idMax != ""){
			$addQuery = " AND id < ". $idMax;
		}
		$query = "
			SELECT
				count(id) as kolvo
			FROM
				body
			WHERE
				head=". $this->getId() . $addQuery;
      try{
			$result = $this->connection->doQuery(new QueryResult($query));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($result);
      $count = mysql_result($result->getResult(), 0, 'kolvo');
		$result->__destruct();
	   return $count;
	}

	/**
	 * Возвращает игнор пользователя
	 * TODO Этот метод должен быть у пользователя
	 * @param unknown_type $idUser
	 * @return unknown
	 */
	private function getIgnorArray(){
      $query = "
      	SELECT
         	ignor
         FROM
         	ignor
         WHERE
         	user=". $this->getIdUser() ."
         	and end > now()";
      try{
	      $ignorRS = $this->connection->doQuery(new QueryResult($query));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($ignorRS);
      $result = array();
      while ($row = mysql_fetch_assoc($ignorRS->getResult())){
      	$result[] = $row['ignor'];
      }
		$ignorRS->__destruct();
      return $result;
	}

	/**
	 * Возвращает id последнего поста в ветке
	 */
	public function getMaxId(){
	   $query = "
	   SELECT
	      MAX(id) as mx
	   FROM
	      body
	   WHERE
	      head=". $this->getId() ."
	   ";
      try{
		   $result = $this->connection->doQuery(new QueryResult($query));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($result);
      $str_maxp = mysql_result($result->getResult(), 0, 'mx');
		$result->__destruct();
		return $str_maxp;
	}

	/**
	 * Возвращает подписан ли посетитель на ветку
	 *
	 * @param unknown_type $idUser
	 * @return unknown
	 */
	public function isUserSubscribed($idUser){
      $query = "
	      SELECT
	         id
	      FROM
	         fd_subscribe
	      WHERE
	      user=". $idUser ."
	      AND title=". $this->getId();
      try{
	      $result = $this->connection->doQuery(new QueryResult($query));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($result);
      $isSubscribed = mysql_num_rows($result->getResult()) > 0;
		$result->__destruct();
      return $isSubscribed;
	}

	/**
	 * Возвращает пост по его id
	 *
	 * @param $postId
	 */
	public function getPost($postId){
   $query="
   	SELECT
         body.table_post,
         body.table_head
      FROM
      	body
      WHERE
      	body.id=".$postId;
      try{
			$resultSet = $this->connection->doQuery(new QueryResult($query));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($resultSet);
      $result = array();
		$result = mysql_fetch_assoc($resultSet->getResult());
   $query="
   	SELECT
      	users.nick,
         ".$result['table_head'].".tilte,
         ".$result['table_head'].".auth
      FROM
      	(".$result['table_head']."
      	LEFT JOIN users ON ".$result['table_head'].".auth = users.id)
         LEFT JOIN titles ON ".$result['table_head'].".thread_id = titles.id
      WHERE
      	".$result['table_head'].".id=".$postId;
      try{
			$resultSet = $this->connection->doQuery(new QueryResult($query));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($resultSet);
      $result['tilte'] = mysql_result($resultSet->getResult(), 0, 'tilte');
		$result['nick'] = mysql_result($resultSet->getResult(), 0, 'nick');
		$result['auth'] = mysql_result($resultSet->getResult(), 0, 'auth');
		$resultSet->__destruct();
		$result['id'] = $postId;
		$sql_body = "
	   SELECT
	      body
	   FROM
	      ".$result['table_post']."
	   WHERE
	      id=".$postId;
      try{
		   $res_body = $this->connection->doQuery(new QueryResult($sql_body));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($res_body);
      $result['body'] = mysql_result($res_body->getResult(), 0, 'body');
		$res_body->__destruct();
		return $result;
	}

	/**
	 * Возвращает id Юзера
	 *
	 * @return unknown
	 */
	private function getIdUser(){
		return $this->idUser;
	}

	/**
	 * Устанавливает id Юзера
	 *
	 * @param unknown_type $idUser
	 */
	private function setIdUser($idUser){
		$this->idUser = $idUser;
	}

	/**
	 * Возвращает игнор текущего юзера
	 *
	 * @return unknown
	 */
	public function arrIgnorId(){
		return $this->arrIgnorId;
	}

	/**
	 * Возвращает, является ли ветка опросом
	 *
	 * @param unknown_type $id
	 * @return unknown
	 */
	public function isQuest($id){
		$sqlQuest="
	   SELECT
	      type
	   FROM
	      titles
	   WHERE
	      id=". $this->getId();
      try{
		   $resQuest = $this->connection->doQuery(new QueryResult($sqlQuest));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($resQuest);
      $type = mysql_result($resQuest->getResult(), 0, 'type');
		$resQuest->__destruct();
		return $type == 1 || $type == 2;
	}

	/**
	 * Возвращает результат запроса с вариантами ответов
	 */
	public function getQuestNodes(){
		$query="
	      SELECT
	         quest.id, 
	         quest.node, 
	         quest.user, 
	         quest.gol, 
	         quest.type, 
	         users.nick 
	      FROM 
	      quest 
	      left join users on quest.user=users.id 
	      WHERE 
	         quest.head=".$this->getId()." 
	      ORDER BY 
	         numb";
      try{
		   $result = $this->connection->doQuery(new SelectQueryResult($query));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($result);
      return $result;
	}
	
	/**
	 * Возвращает, есть ли уже голос текущего юзера
	 * в опросе
	 */
	public function isUserVote(){
		$query="
	         SELECT 
	            user 
	         FROM 
	            voice 
	         WHERE 
	            head=".$this->getId()." 
	            AND user=".$this->getIdUser();
      try{
			$result = $this->connection->doQuery(new SelectQueryResult($query));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($result);
		return $result->getNumRows() > 0;
	}

	/**
	 * Возвращает количество проголосовавших
	 * в опросе
	 */
	public function getVoicesAmount(){
		$query = "
	      SELECT 
	         COUNT(id) AS nvcs 
	      FROM 
	         voice 
	      WHERE 
	         head=".$this->getId();
      try{
			$result = $this->connection->doQuery(new SelectQueryResult($query));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
		return $result->get('nvcs');
	}
}
?>