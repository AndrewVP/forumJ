<?
function fd_new_search_user($userId, $userNick, $idPost,Time $timePost, $searchConn){
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

function fd_delete_post_data($idPost, $searchConn, $param2, $param3){
	$sqlDeleteHeadData="
			DELETE FROM
				fd_post_words
			WHERE 
				fd_id_post=".$idPost."
		";
	$resultDeleteHeadData = fd_query($sqlDeleteHeadData, $searchConn, "");
}

function fdGetSearchArray($headText){
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
	array_walk($result, "fd_strtolower");
	$result=array_values(array_unique($result));
	return $result;
}

function fd_strtolower(&$element, $key){
	$element = strtolower(trim($element));
}
function fdProcessSearchInfo($wordsArray, $idPost, $postDate, $conn, $state){
	if (!isBlankAray($wordsArray)){
		// Получаем стоп-слова
		$stopWords = fd_getStopWords($conn, "", "");
		// Удаляем их
		$wordsArray_=array_values(array_diff($wordsArray, $stopWords));
		// Определяем новые словоформы
		$newWords=fdGetNewWords($wordsArray_, $conn, "fd_search_word_forms", "");
		// Если есть новые слова
		if (count($newWords)>0 && !isBlankAray($newWords)){
//		echo "<br/>++++++++++++++++++++++<br/>";
			// Добавляем новые слова
			fdAddNewWords($newWords, $conn, "", "");
			// Добавляем новые словоформы
			fdAddNewWordForms($newWords, $conn, "", "");
			// Добавляем связь между ними
			fdGetIdsWords($newWords, $conn, "", "");
			fdAddNewWordsRelation(fdGetIdsWords($newWords, $conn, "", ""), $conn, "", "");
		}
		// Определяем айдишники
		$idS=fdGetIds($wordsArray, $conn, '', '');
		// добавляем поисковую информацию
		if (is_array($idS)){
			$sqlInsertSearch="
				INSERT INTO 
					fd_post_words (fd_id_word_form, fd_id_post, fd_date, fd_state) 
				VALUES
						";
			$sqlPart4="";
			$sqlPart4="('".implode("',".$idPost.",'".$postDate."',".$state."),('", $idS)."',".$idPost.",'".$postDate."',".$state.")";
			
			fd_query($sqlInsertSearch.$sqlPart4, $conn, "");
		}
	}
}

function fd_getStopWords($conn, $var1, $var2){
	$sqlStopWords="
	SELECT 
		id
	FROM
		fd_searchstopwords 
	";
	$rsltStopWords=fd_query($sqlStopWords,$conn,"");
	while($row=mysql_fetch_row($rsltStopWords)) {
		$stopWords[]=$row[0];
	}
	return $stopWords;
}

function fdGetNewWords($wordsArray, $conn, $table, $var2){
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
	$resultSet=fd_query($sqlSearch, $conn, "");
	// $existWords[] - слова, которые есть
	while($row=mysql_fetch_row($resultSet)) {
		$existWords[]=$row[0];
	}
	if (isset($existWords)){
		$newWords=array_diff($wordsArray, $existWords);
	}else{
		$newWords=$wordsArray;
	}
	return array_values($newWords); 
}

function fdGetPostTime($idPost, $conn, $var1, $var2){
	$queryTime="
	SELECT
		reg
	FROM
		body
	WHERE
		id=".$idPost."
	";
	$rsltTime = fd_query($queryTime, $conn, "");
	return mysql_result($rsltTime, 0, 'reg');
}

function fdGetIds($wordsArray, $conn, $var1, $var2){
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
	$resultSet=fd_query($sqlSearch, $conn, "");
	while($row=mysql_fetch_row($resultSet)) {
		$ids[]=$row[0];
	}
	if (!isset($ids)){
//		$newWords=array_diff($wordsArray, $existWords);
		$ids = " ";
	}else{
		$ids = array_values($ids);
	}
	return ($ids); 
}

function fdGetIdsWords($wordsArray, $conn, $var1, $var2){
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
	$resultSet=fd_query($sqlSearch, $conn, "");
	while($row=mysql_fetch_row($resultSet)) {
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
		$resultSet=fd_query($sqlSearch, $conn, "");
		while($row=mysql_fetch_row($resultSet)) {
			$ids[2][array_search($row[1], $ids[1])]=$row[0]; 
		}
	}
	return ($ids); 
}

function fdAddNewWords($wordsArray, $conn, $var1, $var2){
	// Добавляем новые слова
	if (count($wordsArray)>0 && !isBlankAray($wordsArray)){
		$sqlInsertWords="
			INSERT INTO 
				fd_search_words (fd_word) 
			VALUES
			";
		$sqlPart2="('".implode("'),('", $wordsArray)."')";
		if (!fd_new_query($sqlInsertWords.$sqlPart2, $conn, "", "", "", "")){
			fdAddNewWords(fdGetNewWords($wordsArray, $conn, "fd_search_words", ""), $conn, $var1, $var2);
		}
	}
}

function fdAddNewWordForms($wordsArray, $conn, $var1, $var2){
	if (count($wordsArray)>0 && !isBlankAray($wordsArray)){
		$sqlInsertWordsForms="
			INSERT INTO 
				fd_search_word_forms (fd_word) 
			VALUES
			";
		$sqlPart3="('".implode("'),('", $wordsArray)."')";
		if(!fd_new_query($sqlInsertWordsForms.$sqlPart3, $conn, "", "", "", "")){
			fdAddNewWordForms(fdGetNewWords($wordsArray, $conn, "fd_search_word_forms", ""), $conn, $var1, $var2);
		}
	}
}

function fdAddNewWordsRelation($arrIds, $conn, $var3, $var4){
	// Добавляем связь между новыми словами и словоформами
	$sqlPart="";
	for ($x1=0; $x1<sizeof($arrIds[0]); $x1++){
		if ($sqlPart!=""){
			$sqlPart .= ", ";
		}
		$sqlPart .= "(".$arrIds[0][$x1].", ".$arrIds[2][$x1].")";
	}
	$sqlInsertRelation = "
	INSERT INTO
		fd_words_relations (id_parent, id_child)
	VALUES
		".$sqlPart."
		
	";
	fd_query($sqlInsertRelation, $conn, "");
}

function isBlankAray($arr){
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

function addHead2Search($headText, $idPost, $postDate, $conn, $var1, $var2){
	fdProcessSearchInfo(fdGetSearchArray($headText), $idPost, $postDate, $conn, NOT_IN_TOP_HEAD);
}

function addBody2Search($bodyText, $idPost, $postDate, $conn, $var1, $var2){
	fdProcessSearchInfo(fdGetSearchArray($bodyText), $idPost, $postDate, $conn, NOT_IN_TOP_BODY);
} 

function addTopic2Search($headText, $idPost, $postDate, $conn, $var1, $var2){
	fdProcessSearchInfo(fdGetSearchArray($headText), $idPost, $postDate, $conn, IN_TOP_HEAD);
}

function addTopBody2Search($bodyText, $idPost, $postDate, $conn, $var1, $var2){
	fdProcessSearchInfo(fdGetSearchArray($bodyText), $idPost, $postDate, $conn, IN_TOP_BODY);
}

function addQuestTopic2Search($headText, $idPost, $postDate, $conn, $var1, $var2){
	fdProcessSearchInfo(fdGetSearchArray($headText), $idPost, $postDate, $conn, IN_TOP_QUEST);
}

function addQuestTopBody2Search($bodyText, $idPost, $postDate, $conn, $var1, $var2){
	fdProcessSearchInfo(fdGetSearchArray($bodyText), $idPost, $postDate, $conn, IN_TOP_QUEST_BODY);
}

function addQuestHead2Search($headText, $idPost, $postDate, $conn, $var1, $var2){
	fdProcessSearchInfo(fdGetSearchArray($headText), $idPost, $postDate, $conn, NOT_IN_TOP_QUEST_HEAD);
}

function addQuestBody2Search($bodyText, $idPost, $postDate, $conn, $var1, $var2){
	fdProcessSearchInfo(fdGetSearchArray($bodyText), $idPost, $postDate, $conn, NOT_IN_TOP_QUEST_BODY);
}


?>