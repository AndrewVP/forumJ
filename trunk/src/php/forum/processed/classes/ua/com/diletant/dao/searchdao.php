<?php
include_once 'classes/ua/com/diletant/business/post.php';
include_once 'classes/ua/com/diletant/dao/dao.php';

/**
 * DAO - класс для поиска
 *
 */
class SearchDao extends Dao{

	static private $SEARCH_TABLE_NAME = "srchtbl";

	static private $IN_TOP_BODY = 0;
	static private $IN_TOP_QUEST_BODY = 1;
	static private $NOT_IN_TOP_BODY = 2;
	static private $NOT_IN_TOP_QUEST_BODY = 3;
	static private $IN_TOP_HEAD = 4;
	static private $IN_TOP_QUEST_HEAD = 5;
	static private $NOT_IN_TOP_HEAD = 6;
	static private $NOT_IN_TOP_QUEST_HEAD = 7;
	static private $USER_NICK = 8;

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
	public function __construct(IConnection $conection, $idUser){
		$this->connection = $conection;
		$this->setIdUser($idUser);
//		$this->arrIgnorId = $this->getIgnorArray();
	}


	public function addHead2Search($headText, $idPost, $postDate, $idThread){
		$this->processSearchInfo($this->getSearchArray($headText), $idPost, $postDate, SearchDao::$NOT_IN_TOP_HEAD, $idThread);
	}
	
	public function addBody2Search($bodyText, $idPost, $postDate, $idThread){
		$this->processSearchInfo($this->getSearchArray($bodyText), $idPost, $postDate, SearchDao::$NOT_IN_TOP_BODY, $idThread);
	} 
	
	public function addTopic2Search($headText, $idPost, $postDate, $idThread){
		$this->processSearchInfo($this->getSearchArray($headText), $idPost, $postDate, SearchDao::$IN_TOP_HEAD, $idThread);
	}
	
	public function addTopBody2Search($bodyText, $idPost, $postDate, $idThread){
		$this->processSearchInfo($this->getSearchArray($bodyText), $idPost, $postDate, SearchDao::$IN_TOP_BODY, $idThread);
	}
	
	public function addQuestTopic2Search($headText, $idPost, $postDate, $idThread){
		$this->processSearchInfo($this->getSearchArray($headText), $idPost, $postDate, SearchDao::$IN_TOP_QUEST, $idThread);
	}
	
	public function addQuestTopBody2Search($bodyText, $idPost, $postDate, $idThread){
		$this->processSearchInfo($this->getSearchArray($bodyText), $idPost, $postDate, SearchDao::$IN_TOP_QUEST_BODY, $idThread);
	}
	
	public function addQuestHead2Search($headText, $idPost, $postDate, $idThread){
		$this->processSearchInfo($this->getSearchArray($headText), $idPost, $postDate, SearchDao::$NOT_IN_TOP_QUEST_HEAD, $idThread);
	}
	
	public function addQuestBody2Search($bodyText, $idPost, $postDate, $idThread){
		$this->processSearchInfo($this->getSearchArray($bodyText), $idPost, $postDate, SearchDao::$NOT_IN_TOP_QUEST_BODY, $idThread);
	}

	public function addUser2Search($idUser, $idPost, $postDate, $idThread){
		$idS = array();
		$idS[] = $idUser;
		$this->addSearchInfo($idPost, $postDate, SearchDao::$USER_NICK, $idS, $idThread);
	}
	

	/**
	 * Возвращает список постов по списку id
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
	public function getPostsList($idPosts, 
											$str_fd_timezone_hr, 
											$str_fd_timezone_mn, 
											LocaleString $locale, 
											$pg, 
											$is_found,
											$idWordsForms){
	   $query="SELECT
	              body.ip,
	              body.auth,
	              body.domen,
	              body.tilte,
	              body.head as t_id,
	              body.fd_post_time as post_time,
	              body.id,
	              body.nred,
	              body.fd_post_edit_time as post_edit_time,
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
	              body.id_post,
	              body.table_post,
	              titles.head,
	              titles.type
	           FROM
	              (body
	              LEFT JOIN users ON body.auth = users.id)
	              LEFT JOIN titles ON body.head = titles.id
	           WHERE 
					  body.id IN (".$idPosts.")  
	           ORDER BY 
	              body.id ASC";
      try{
		   $resultSet = $this->connection->doQuery(new QueryResult($query));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($resultSet);
	   $result = array();
	   $isAdminForvard = false;
	   $isUserCanAddAnswer = false;
	   $hasIgnor = sizeof($this->arrIgnorId()) != 0;
	   $nPost = 0;
	   $bodiesId = array();
	   while ($row = mysql_fetch_assoc($resultSet->getResult())){
	   	$result[$row['id']] = new Post($row, 
	   								$locale, 
	   								$idWordsForms, 
	   								$this->arrIgnorId(), 
	   								false, 
	   								$is_found, 
	   								$isAdminForvard, 
	   								false, 
	   								$isUserCanAddAnswer,
	   								$pg,
	   								$this->connection,
	   								$str_fd_timezone_hr, 
	   								$str_fd_timezone_mn);
	   	if (isset($bodiesId[$row['table_post']])){
	   		$bodiesId[$row['table_post']] .= ", ".$row['id_post'];							
	   	}else{
	   		$bodiesId[$row['table_post']] = $row['id_post'];
	   	}
	   }
		$resultSet->__destruct();
		foreach ($bodiesId as $table => $ids) {
			$sqlBody = "
				SELECT 
					id_post,
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
	   	while ($row = mysql_fetch_assoc($resultSetBody->getResult)){
		   	$result[$row['id_post']]->setBody($row['body']);
		   }
			$resultSetBody->__destruct();
		}
	   return $result; 
	}
	
	/**
	 * Возвращает количество постов в поиске
	 */
	public function getPostsCountInSearch($idPosts){
	   $query="SELECT
	              COUNT(ip) as mx
	           FROM
	              body
	           WHERE 
					  body.id IN (".$idPosts.")";
      try{
		   $result = $this->connection->doQuery(new QueryResult($query));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($result);
		$count = mysql_result($result->getResult(), 0, 'mx');
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
      while ($row = mysql_fetch_assoc($ignorRS->getRsult())){
      	$result[] = $row['ignor'];
      }
		$ignorRS->__destruct();
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
	 * Возвращает имя текущей таблицы поиска
	 *
	 * @return unknown
	 */
	public function getCurrentSearchTable(){
		$date = new Time(time());
		return SearchDao::$SEARCH_TABLE_NAME.$date->toString("_m_Y");
	}
	
	private function createCurrentSearchTable(){
		$sql = "CREATE TABLE IF NOT EXISTS `".$this->getCurrentSearchTable()."` (".
			  "`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,".
			  "`fd_id_word_form` INT UNSIGNED NOT NULL DEFAULT '0',".
			  "`fd_id_post` INT UNSIGNED NOT NULL DEFAULT '0',".
			  "`thread_id` INT UNSIGNED NOT NULL DEFAULT '0',".
			  "`fd_state` SMALLINT NOT NULL DEFAULT '0',".
			  "`fd_post_date` INT UNSIGNED NOT NULL DEFAULT '0',".
			  "PRIMARY KEY  (`id`)".
			") ENGINE=MyISAM DEFAULT CHARSET=cp1251;";
      try{
			$result = $this->connection->doQuery(new QueryResult($sql));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($result);
	}

	/**
	 * Обрабатывает массив слов из поста
	 *
	 * @param unknown_type $wordsArray
	 * @param unknown_type $idPost
	 * @param unknown_type $postDate
	 * @param unknown_type $conn
	 * @param unknown_type $state
	 */
	private function processSearchInfo($wordsArray, $idPost, Time $postDate, $state, $idThread){
		if (!$this->isBlankAray($wordsArray)){
			// Получаем стоп-слова
			$stopWords = $this->getStopWords();
			// Удаляем их
			$wordsArray_=array_values(array_diff($wordsArray, $stopWords));
			// Определяем новые словоформы
			$newWords=$this->getNewWords($wordsArray_, "fd_search_word_forms");
			// Если есть новые слова
			if (count($newWords)>0 && !$this->isBlankAray($newWords)){
				// Добавляем новые слова
				$this->addNewWords($newWords);
				// Добавляем новые словоформы
				$this->addNewWordForms($newWords);
				// Добавляем связь между ними
				$this->addNewWordsRelation($this->getIdsWords($newWords));
			}
			// Определяем айдишники
			$idS=$this->getIds($wordsArray);
			// добавляем поисковую информацию
			if (is_array($idS)){
				$this->addSearchInfo($idPost, $postDate, $state, $idS, $idThread);
			}
		}
	}
	
	private function addSearchInfo($idPost, $postDate, $state, $idS, $idThread){
		$sqlInsertSearch="
			INSERT INTO 
				".$this->getCurrentSearchTable()." (fd_id_word_form, fd_id_post, thread_id, fd_post_date, fd_state) 
			VALUES
					";
		$sqlPart4="";
		$sqlPart4="('".implode("',".$idPost.",".$idThread.",".$postDate->getTimestamp().",".$state."),('", $idS)."',".$idPost.",".$idThread.",".$postDate->getTimestamp().",".$state.")";
		$result = false;
      try{
			$result = $this->connection->doQuery(new QueryResult($sqlInsertSearch.$sqlPart4));
      }catch (MySQLQueryException $e){
	      if ($e->getCode() != 1146){
	      	// Просто ошибка БД
	      	$this->onDatabaseError($e);
	      }else{
	      	// Надо создать таблицу
	      	$this->createCurrentSearchTable();
	      	// Вызываем повторно
				$this->addSearchInfo($idPost, $postDate, $state, $idS, $idThread);
	      }
      }
      if ($result){
	      $this->onQuery($result);
	      $result->__destruct();
      }
	}
	
	/**
	 * Проверяет массив, содержит ли он что-нибудь
	 *
	 * @param unknown_type $arr
	 * @return unknown
	 */
	private function isBlankAray($arr){
		if (sizeof($arr)==1){
			if (trim($arr[0])==""){
				return true;
			}else{
				return false;
			}
		}elseif(sizeof($arr)==0){
				return true;
		}else{
			return false;
			
		}
	}
	
	/**
	 * Возвращает массив стоп-слов
	 *
	 * @param unknown_type $conn
	 * @param unknown_type $var1
	 * @param unknown_type $var2
	 * @return unknown
	 */
	function getStopWords(){
		$query = "
		SELECT 
			id
		FROM
			fd_searchstopwords 
		";
      try{
			$result = $this->connection->doQuery(new QueryResult($query));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($result);
		$stopWords = array();
		while($row=mysql_fetch_row($result->getResult())) {
			$stopWords[]=$row[0];
		}
		$result->__destruct();
		return $stopWords;
	}
	
	/**
	 * Выделяет из массива новые слова
	 *
	 * @param unknown_type $wordsArray
	 * @param unknown_type $conn
	 * @param unknown_type $table
	 * @param unknown_type $var2
	 * @return unknown
	 */
	private function getNewWords($wordsArray, $table){
		// Определяем новые слова
		// Выбираем те, которые есть
		$sqlPart1=" fd_word='".implode("' OR fd_word='", $wordsArray)."'";
		$sqlSearch="
			SELECT
				fd_word
			FROM
				".$table."
			WHERE
				".$sqlPart1."
			";
      try{
			$resultSet = $this->connection->doQuery(new QueryResult($sqlSearch));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($resultSet);
		// $existWords[] - слова, которые есть
		while($row = mysql_fetch_row($resultSet->getResult())) {
			$existWords[]=$row[0];
		}
		$resultSet->__destruct();
		if (isset($existWords)){
			$newWords = array_diff($wordsArray, $existWords);
		}else{
			$newWords = $wordsArray;
		}
		return array_values($newWords); 
	}
	
	/**
	 * Добавляет новые слова
	 *
	 * @param unknown_type $wordsArray
	 * @param unknown_type $conn
	 * @param unknown_type $var1
	 * @param unknown_type $var2
	 */
	private function addNewWords($wordsArray){
		// Добавляем новые слова
		if (count($wordsArray)>0 && !$this->isBlankAray($wordsArray)){
			$sqlInsertWords="
				INSERT INTO 
					fd_search_words (fd_word) 
				VALUES
				";
			$sqlPart2="('".implode("'),('", $wordsArray)."')";
			$resultSet = false;
	      try{
				$resultSet = $this->connection->doQuery(new QueryResult($sqlInsertWords.$sqlPart2));
	      }catch (MySQLQueryException $e){
	      	if ($e->getCode() != 1062){
	      		// просто ошибка
		      	$this->onDatabaseError($e);
	      	}else{
	      		// слова уже есть
	      		$this->addNewWords($this->getNewWords($wordsArray, "fd_search_words"));
	      	}
	      }
	      if ($resultSet){
		      $this->onQuery($resultSet);
	      }
		}
	}

	private function getSearchArray($headText){
		// Удаляем гиперссылки
		$result= preg_replace("/http:\/(\/[^ ]+)+/", " ", $headText);
		$breakArray[]='\\r';
		$breakArray[]='\\n';
		$breakArray[]='rn';
		$breakArray[]=',';
		$breakArray[]='.';
		$breakArray[]=';';
		$breakArray[]=':';
		$breakArray[]='"';
		$breakArray[]='\'';
		$breakArray[]='!';
		$breakArray[]='?';
		$breakArray[]='+';
		$breakArray[]='=';
		$breakArray[]='`';
		$breakArray[]='~';
		$breakArray[]='/';
		$breakArray[]='\\';
		$breakArray[]='{';
		$breakArray[]='}';
		$breakArray[]='(';
		$breakArray[]=')';
		$breakArray[]='[';
		$breakArray[]=']';
		$breakArray[]='<';
		$breakArray[]='>';
		$breakArray[]=chr(10);
		$breakArray[]=chr(13);
		$breakArray[]='»';
		$breakArray[]='«';
		$breakArray[]='”';
		$breakArray[]='“';
		$breakArray[]='*';
		$breakArray[]='-';
		$breakArray[]='%';
		$result = str_replace($breakArray, " ", $result);
		$result= preg_replace("/[ ]+/", " ", $result);
		$result=split(" ",trim($result));
		array_walk($result, "SearchDao::strToLower");
		$result=array_values(array_unique($result));
		return $result;
	}

	private static function strToLower(&$element, $key){
		$element = strtolower(trim($element));
	}

	private function addNewWordForms($wordsArray){
		if (count($wordsArray)>0 && !$this->isBlankAray($wordsArray)){
			$sqlInsertWordsForms="
				INSERT INTO 
					fd_search_word_forms (fd_word) 
				VALUES
				";
			$sqlPart3="('".implode("'),('", $wordsArray)."')";
/*
			if(!fd_new_query($sqlInsertWordsForms.$sqlPart3, $conn, "", "", "", "")){
				fdAddNewWordForms(fdGetNewWords($wordsArray, $conn, "fd_search_word_forms", ""), $conn, $var1, $var2);
			}
*/
			$resultSet = false;
	      try{
				$resultSet = $this->connection->doQuery(new QueryResult($sqlInsertWordsForms.$sqlPart3));
	      }catch (MySQLQueryException $e){
	      	if ($e->getCode() != 1062){
	      		// просто ошибка
		      	$this->onDatabaseError($e);
	      	}else{
	      		// словоформы уже есть
	      		$this->addNewWordForms($this->getNewWords($wordsArray, "fd_search_word_forms"));
	      	}
	      }
	      if ($resultSet){
		      $this->onQuery($resultSet);
	      }
		}
	}
	
	private function getIdsWords($wordsArray){
		// Выбираем
		$sqlPart1=" fd_word='".implode("' OR fd_word='", $wordsArray)."'";
		$sqlSearch="
			SELECT
				id, 
				fd_word
			FROM
				fd_search_words
			WHERE
				".$sqlPart1."
			ORDER BY
				fd_word
			";
      try{
			$resultSet = $this->connection->doQuery(new QueryResult($sqlSearch));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($resultSet);
		while($row=mysql_fetch_row($resultSet->getResult())) {
			$ids[0][]=$row[0];
			$ids[1][]=$row[1];
		}
		// Вряд ли это нужно
		if (!isset($ids)){
			$ids = " ";
		}else{
			$ids = array_values($ids);
			$sqlPart1=" fd_word='".implode("' OR fd_word='", $wordsArray)."'";
			$sqlSearch="
				SELECT
					id, 
					fd_word
				FROM
					fd_search_word_forms
				WHERE
					".$sqlPart1."
				ORDER BY
					fd_word
				";
	      try{
				$resultSet = $this->connection->doQuery(new QueryResult($sqlSearch));
	      }catch (MySQLQueryException $e){
	      	$this->onDatabaseError($e);
	      }
	      $this->onQuery($resultSet);
			while($row=mysql_fetch_row($resultSet->getResult())) {
				$ids[2][array_search($row[1], $ids[1])]=$row[0]; 
			}
		}
		return ($ids); 
	}

	private function addNewWordsRelation($arrIds){
		// Добавляем связь между новыми словами и словоформами
		$sqlPart="";
		for ($x1=0; $x1<sizeof($arrIds[0]); $x1++){
			if ($sqlPart!=""){
				$sqlPart .= ", ";
			}
			$sqlPart .= "(".$arrIds[0][$x1].", ".$arrIds[2][$x1].")";
		}
		$query = "
		INSERT INTO
			fd_words_relations (id_parent, id_child)
		VALUES
			".$sqlPart."
			
		";
      try{
			$result = $this->connection->doQuery(new QueryResult($query));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($result);
	}

	private function getIds($wordsArray){
		// Выбираем
		$sqlPart1=" fd_word='".implode("' OR fd_word='", $wordsArray)."'";
		$sqlSearch="
			SELECT
				id
			FROM
				fd_search_word_forms
			WHERE
				".$sqlPart1."
			";
      try{
			$resultSet = $this->connection->doQuery(new QueryResult($sqlSearch));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($resultSet);
		while($row=mysql_fetch_row($resultSet->getResult())) {
			$ids[]=$row[0];
		}
		$resultSet->__destruct();
		if (!isset($ids)){
	//		$newWords=array_diff($wordsArray, $existWords);
			$ids = " ";
		}else{
			$ids = array_values($ids);
		}
		return ($ids); 
	}

	public function addUserInfo($userId, $userNick, $idPost, Time $timePost){
		$sqlSearchUser="
			SELECT 
				id
			FROM
				fd_search_user
			WHERE
				nick='$userNick' 
			";
		$resultSearchUser=fd_query($sqlSearchUser, $searchConn, "");
		if (mysql_num_rows($resultSearchUser)!=0){
			$idUser=mysql_result($resultSearchUser, 0, 'id');
		}else{
			$sqlNewUser="
				INSERT INTO 
					fd_search_user
					(nick, fd_open, iduser)
				VALUES
					('$userNick', 0, '$userId')
				";
			$resultNewUser=fd_query($sqlNewUser, $searchConn, "");
			$idUser=mysql_insert_id();
		}
		$sqlSearchData="
			INSERT INTO 
				fd_post_user
				(iduser, idpost, fd_post_date, fd_open)
			VALUES
				($idUser, $idPost, ".$timePost->getTimestamp().", 0)
			";
		$resultSearchData=fd_query($sqlSearchData, $searchConn, "");
	}
}
?>