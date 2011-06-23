<?php
include_once 'classes/ua/com/diletant/business/thread.php';
include_once 'classes/ua/com/diletant/dao/dao.php';

/*
 * DAO - класс для страницы index.php
 * (список веток)
 */
class IndexDao extends Dao {

	/**
	 * id Юзера
	 */
	private $idUser;

	/**
	 * Количество веток
	 */
	private $threadCount;

	/**
	 * Массив с игнорами
	 *
	 * @var unknown_type
	 */
	private $arrIgnorId = array();

	/**
	 * Строка id для индикаторов
	 *
	 * @var unknown_type
	 */
	private $indctrIds = "";

	/**
	 * Конструктор
	 *
	 * @param IConnection $conection
	 */
	public function __construct(IConnection $conection, $idUser){
		$this->connection = $conection;
		$this->idUser =  $idUser;
		$this->arrIgnorId = $this->getIgnorArray();
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
	 * Возвращает количество веток
	 *
	 * @return unknown
	 */
	public function getThreadCount(){
		return $this->threadCount;
	}

	/**
	 * Возвращает игнор текущего юзера
	 *
	 * @return unknown
	 */
	public function getArrIgnorId(){
		return $this->arrIgnorId;
	}

	/**
	 * Возвращает строку id для индикаторов
	 *
	 * @return unknown
	 */
	public function getIndctrIds(){
		return $this->indctrIds;
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
	 * Возвращает id последнего поста в форуме
	 *
	 * @return unknown
	 */
	public function getMaxPostId(){
	   $query="SELECT
	                max(id) as mx
	             FROM body";
      try{
		   $result = $this->connection->doQuery(new QueryResult($query));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($result);
      $m_xb = mysql_result($result->getResult(), 0, 'mx');
   	$result->__destruct();
   	return $m_xb;
	}

	/**
	 * Возвращает id последней ветки в форуме
	 *
	 * @return unknown
	 */
	public function getMaxThreadId(){
	   $query="SELECT
	                max(id) as mx
	             FROM titles";
      try{
		   $result = $this->connection->doQuery(new QueryResult($query));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($result);
      $m_xb = mysql_result($result->getResult(), 0, 'mx');
   	$result->__destruct();
   	return $m_xb;
	}

	public function getThreads($viewId, $threadCountPerPage, $nfirstpost, LocaleString $locale, $isLogin, $pg, $pt){
	   $sql_views="SELECT
	                  folder
	               FROM
	                  fdvtranzit
	               WHERE
	                  (user=".$this->getIdUser()." OR user=0)
	                  AND view=".$viewId."
	                  ";
      try{
		   $rslt_folders = $this->connection->doQuery(new QueryResult($sql_views));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($rslt_folders);
      $xRow=0;
	   $isForum=0;
	   $folders="(";
	   while ($row=mysql_fetch_row($rslt_folders->getResult())){
	   	if ($row[0]==1){
			   $isForum=1;
	   	}else{
	   		$folders.=" ".$row[0].",";
	   	}
	   		$xRow++;
	   }
	   $rslt_folders->__destruct();
	   if ($xRow==1 and $isForum){
	   	/*есть только форум*/
	   }else if($xRow == 0){
	   	// TODO ошибку надо отловить!
	   	$folders = "(1)";
	   	$isForum = 1;
	   }else{
	   	/*другое*/
		   $folders=substr($folders, 0, strlen($folders)-1).")";
	   }
	   /*выбираем минусы игнора*/
	   if (sizeof($this->getArrIgnorId()) > 0){
		   $ignored = "(".implode(", ", $this->getArrIgnorId()).")";
	   }
	   $where = "";
	   if (isset($ignored)){
	   	$where = "WHERE titles.auth NOT IN ".$ignored." ";
	   }
	   if ($isForum){
	   	/*Есть форум*/
	   	if ($xRow==1){
	   		/*Есть только форум*/
	   		/*Определяем минусы - все перемещенное*/
	   		$sqlMoved="
	   		SELECT
	   			title
	   		FROM
	   			fdtranzit
	   		WHERE
	   			user=".$this->getIdUser()."
	   		";
		      try{
		   		$rsltMoved = $this->connection->doQuery(new QueryResult($sqlMoved));
		      }catch (MySQLQueryException $e){
		      	$this->onDatabaseError($e);
		      }
      		$this->onQuery($rsltMoved);
		      if (mysql_num_rows($rsltMoved->getResult())!=0){
	         	/*перемещения есть*/
	            $moved="(";
	            while ($row=mysql_fetch_row($rsltMoved->getResult())){
	           		$moved.=" ".$row[0].",";
	            }
	            $rsltMoved->__destruct();
	      	   $moved=substr($moved, 0, strlen($moved)-1).")";
	         }
	         /*Собираем запросы*/
	         if (isset($moved)){
		         if (isset($ignored)){
		         	$where.="AND titles.id NOT IN ".$moved." ";
		         }else{
		         	$where="WHERE titles.id NOT IN ".$moved." ";
		         }
	         }
	         $folderName="'Форум' as _flname, ";
	         $join="";
	   	}else{
	   		/*кроме форума есть что-то еще*/
	   		/*Находим минусы - перемещенные в другие папки*/
	   		$sqlMoved="
	   		SELECT
	   			title
	   		FROM
	   			fdtranzit
	   		WHERE
	   			user=".$this->getIdUser()."
	   			AND folder NOT IN ".$folders."
	   		";
		      try{
		   		$rsltMoved = $this->connection->doQuery(new QueryResult($sqlMoved));
		      }catch (MySQLQueryException $e){
		      	$this->onDatabaseError($e);
		      }
		      $this->onQuery($rsltMoved);
		      if (mysql_num_rows($rsltMoved->getResult())!=0){
	         	/*перемещения есть*/
	            $moved="(";
	            while ($row=mysql_fetch_row($rsltMoved->getResult())){
	           		$moved.=" ".$row[0].",";
	            }
	            $rsltMoved->__destruct();
	      	   $moved=substr($moved, 0, strlen($moved)-1).")";
	         }
	         /*Собираем запросы*/
	         if (isset($moved)){
		         if (isset($ignored)){
		         	$where.="AND titles.id NOT IN ".$moved." ";
		         }else{
		         	$where="WHERE titles.id NOT IN ".$moved." ";
		         }
	         }
	         $folderName="IF (ISNULL(fdfolders.flname), 'Форум', fdfolders.flname) as _flname, ";
	// Временная таблица
			$sqlTmpJoinTable="
				CREATE TEMPORARY TABLE fdutranzit LIKE fdtranzit";
			$sqlTmpJoinTableInsert="
				INSERT INTO fdutranzit (title, folder)
					SELECT
						fdtranzit.title,
						fdtranzit.folder
					FROM
						fdtranzit
					WHERE
						fdtranzit.user=".$this->getIdUser().";
			";
		      $join="
		      LEFT JOIN fdutranzit on titles.id=fdutranzit.title
		      LEFT JOIN fdfolders ON fdutranzit.folder=fdfolders.id
		      ";
	   	}
	   }else{
	   	/*форума в интерфейсе нет*/
			/*Определяем плюсы - все перемещенное в папки*/
			$sqlMoved="
			SELECT
				title
			FROM
				fdtranzit
			WHERE
				user=".$this->getIdUser()."
	 			AND folder IN ".$folders."
			";
	      try{
				$rsltMoved=$this->connection->doQuery(new QueryResult($sqlMoved));
	      }catch (MySQLQueryException $e){
	      	$this->onDatabaseError($e);
	      }
	      $this->onQuery($rsltMoved);
			if (mysql_num_rows($rsltMoved->getResult())!=0){
	      	/*перемещения есть*/
	         $moved="(";
	         while ($row=mysql_fetch_row($rsltMoved->getResult())){
	        		$moved.=" ".$row[0].",";
	         }
	         $rsltMoved->__destruct();
	   	   $moved=substr($moved, 0, strlen($moved)-1).")";
	      }
	      /*Собираем запросы*/
	      if (isset($moved)){
	         if (isset($ignored)){
	         	$where.="AND titles.id IN ".$moved." ";
	         }else{
	         	$where="WHERE titles.id IN ".$moved." ";
	         }
	      }
	      $folderName="IF (ISNULL(fdfolders.flname), 'Форум', fdfolders.flname) as _flname, ";
	// Временная таблица
		$sqlTmpJoinTable="
			CREATE TEMPORARY TABLE fdutranzit LIKE fdtranzit";
		$sqlTmpJoinTableInsert="
			INSERT INTO fdutranzit (title, folder)
				SELECT
					fdtranzit.title,
					fdtranzit.folder
				FROM
					fdtranzit
				WHERE
					fdtranzit.user=".$this->getIdUser().";
		";
	      $join="
	      LEFT JOIN fdutranzit on titles.id=fdutranzit.title
	      LEFT JOIN fdfolders ON fdutranzit.folder=fdfolders.id
	      ";
	   }
	   $sql_main="
	   SELECT
	   	titles.id,
	   	titles.dock,
	   	DATE_FORMAT(DATE_ADD(DATE_ADD(titles.lposttime,INTERVAL 0 HOUR), INTERVAL 0 MINUTE), '%d.%m %H:%i') as lposttime_,
	   	titles.type,
	   	titles.npost,
	   	titles.seenid,
	   	titles.seenall,
	   	DATE_FORMAT(titles.reg, '%d.%m %H:%i') as reg_,
	   	titles.head,
	   	titles.lpostuser,
	   	titles.lpostnick,
	   	titles.id_last_post,
	      ".$folderName."
	   	users.nick
	   FROM
			titles force index(id_3)
	      LEFT JOIN users ON titles.auth=users.id
	      ".$join."
	   ".$where."
	   ORDER BY
	      titles.dock desc,
	      titles.lposttime desc
	   LIMIT
	      ".$nfirstpost.", ".$threadCountPerPage."
	   ";
	   $sql_count="
	   SELECT
	      COUNT(id) as kolvo
	   FROM
	      titles
	      ".$where.";
	   ";
		// Добавляем временную таблицу
      try{
			if (isset($sqlTmpJoinTable)){
			 	$res1 = $this->connection->doQuery(new QueryResult($sqlTmpJoinTable));
      		$this->onQuery($res1);
			 	$res1->__destruct();
				$res1 = $this->connection->doQuery(new QueryResult($sqlTmpJoinTableInsert));
      		$this->onQuery($res1);
			 	$res1->__destruct();
			}
			$res1 = $this->connection->doQuery(new QueryResult($sql_main));
      	$this->onQuery($res1);
		   $zcount=$this->connection->doQuery(new QueryResult($sql_count));
      	$this->onQuery($zcount);
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
	   $count=mysql_result($zcount->getResult(), 0, 'kolvo');
	   $zcount->__destruct();
	   if (isset($sql_count2)){
	      try{
		      $zcount2=$this->connection->doQuery(new QueryResult($sql_count2));
	      }catch (MySQLQueryException $e){
	      	$this->onDatabaseError($e);
	      }
		   $count=$count + mysql_result($zcount2->getResult(), 0, 'kolvo');
      	$this->onQuery($zcount2);
		   $zcount2->_destruct();
	   }
	   $this->threadCount = $count;
	   $result = array();
	   $disain = -1;
	   $i = 0;
	   while ($row = mysql_fetch_assoc($res1->getResult())){
			if ($row['id_last_post'] == 0){
				$query="SELECT
							MAX(id) as id_post
						FROM
							body
						WHERE
							head=".$row['id'];
		      try{
			      $res=$this->connection->doQuery(new QueryResult($query));
		      }catch (MySQLQueryException $e){
		      	$this->onDatabaseError($e);
		      }
		      $this->onQuery($res);
		      $row['id_last_post'] = mysql_result($res->getResult(), 0, 'id_post');
		      $res->__destruct();
				$query="UPDATE
							titles
						SET
							id_last_post=".$row['id_last_post']."
						WHERE
							id=".$row['id'];
		      try{
			      $res=$this->connection->doQuery(new QueryResult($query));
		      }catch (MySQLQueryException $e){
		      	$this->onDatabaseError($e);
		      }
		      $this->onQuery($res);
		      $res->__destruct();
			}
			$this->indctrIds.=";".$row['id'].",".$row['id_last_post'];
	   	$result[] = new Thread($row, $locale, $disain, $isLogin, $pg, $pt, $i++);
	   	$disain = $disain * -1;
	   }
	   $res1->__destruct();
	   return $result;
	}

	public function getNewMailCount($idUser){
      $sql_newmail="
      SELECT
         COUNT(*) as nmail
      FROM
         fdmail
      WHERE
         rcvr=".$_SESSION['idu']." AND
         d_rcv IS NULL
      ";
      try{
	      $rslt_newmail = $this->connection->doQuery(new QueryResult($sql_newmail));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($rslt_newmail);
      $mailCount = mysql_result($rslt_newmail->getResult(), 0, 'nmail');
      $rslt_newmail->__destruct();
      return $mailCount;
	}

	public function getCurrentViewName($idView){
      $sql_vname="
      SELECT
         name
      FROM
         fdviews
      WHERE
         id=".$idView;
      try{
	      $rslt_vname = $this->connection->doQuery(new QueryResult($sql_vname));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($rslt_vname);
      $result = mysql_result($rslt_vname->getResult(), 0, 'name');
      $rslt_vname->__destruct();
      return $result;
	}

	public function getViewsArray($idUser){
	   $sql_views="
	   SELECT
	      id,
	      name
	   FROM
	      fdviews
	   WHERE
	      user=0
	      OR user=".$idUser."
	   ORDER BY
	      id
	   ";
      try{
		   $rslt_views = $this->connection->doQuery(new QueryResult($sql_views));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($rslt_views);
      $result = array();
	   while ($result[] = mysql_fetch_assoc($rslt_views->getResult())){}
      $rslt_views->__destruct();
      return $result;
	}

	public function getFoldersArray($idUser){
	   $sql_views="
      SELECT
         id,
         flname
      FROM
         fdfolders
      WHERE
         user=0
         OR user=".$idUser."
      ORDER BY
         id
      ";
      try{
		   $rslt_views = $this->connection->doQuery(new QueryResult($sql_views));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($rslt_views);
      $result = array();
	   while ($result[] = mysql_fetch_assoc($rslt_views->getResult())){}
      $rslt_views->__destruct();
      return $result;
	}

	public function getUsersArray(){
	   $query33="SELECT
	              DISTINCT users.nick
	           FROM fd_action
	           LEFT JOIN users on fd_action.fd_user=users.id
	           WHERE (fd_action.fd_user<>0) and (fd_action.fd_time >now() - INTERVAL 20 MINUTE)
	           and (fd_action.fd_user<>95) and (users.ban=0)
	           ORDER BY users.nick";
      try{
		   $rslt_views = $this->connection->doQuery(new QueryResult($query33));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($rslt_views);
      $result = array();
	   while ($result[] = mysql_fetch_assoc($rslt_views->getResult())){}
      $rslt_views->__destruct();
      return $result;
	}

	public function getGuestCount(){
	   $query34="" .
	   		"SELECT" .
	   		"	count(DISTINCT fd_ip) as nick " .
	   		"FROM " .
	   		"	fd_action " .
	   		"WHERE " .
	   		"	(fd_user=0) " .
	   		"	AND (fd_time > now() - INTERVAL 20 MINUTE) ";
      try{
		   $res134 = $this->connection->doQuery(new QueryResult($query34));
      }catch (MySQLQueryException $e){
      	$this->onDatabaseError($e);
      }
      $this->onQuery($res134);
      $result = mysql_result($res134->getResult(), 0, 'nick');
      $res134->__destruct();
      return $result;
	}
}
?>