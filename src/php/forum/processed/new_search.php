<?
//Предотвращаем кеширование
   include("cache.php"); 
   include("query.php");
	include("search_engine.php");
   // Соединяемся с БД
   include("setup.php");
// Собираем статистику
   $action=43;
   include("stat.php");
   $sqlSS="
   SELECT
   	*
   FROM
   	ss
   ";
   $resSS=fd_query($sqlSS, $conn, "");
	$ssDate=mysql_result($resSS, 0, "date_post");
	$idPost=mysql_result($resSS, 0, "id_post");
	$sqlGetPostInfo="
	SELECT
		body.tilte,
		body.body,
		body.reg,
		titles.type,
		body.head
	FROM 
		body
		LEFT JOIN titles ON body.head=titles.id
	WHERE body.id=".$idPost."
	";
	$resGetPostInfo=fd_query($sqlGetPostInfo, $conn, "");
	if (mysql_num_rows($resGetPostInfo)>0){
		
		$str_head=mysql_result($resGetPostInfo, 0, "tilte");
		$str_body=mysql_result($resGetPostInfo, 0, "body");
		$lptime=mysql_result($resGetPostInfo, 0, "reg");
		$postType=mysql_result($resGetPostInfo, 0, "type");
		$postThread=mysql_result($resGetPostInfo, 0, "head");
		$sqlIsTop="
		SELECT
	      id
	   FROM
	   	body
	   WHERE
	   	head=".$postThread."
	   ORDER BY id
	   LIMIT 1";
		$resIsTop=fd_query($sqlIsTop, $conn, "");
		$str_first=mysql_result($resIsTop, 0, 'id');
		$inTop=false;
		if ($str_first==$idPost){$inTop=true;}
		fd_delete_post_data($idPost, $conn, "", "");
		if ($postType==1 || $postType==2){
			if ($inTop){
				/* Заголовок*/
				addQuestTopic2Search(stripslashes($str_head), $idPost, $lptime, $conn, "", "");
				/* Тело*/
				addQuestTopBody2Search(stripslashes($str_body),$idPost, $lptime, $conn, "", "");
						}else{
				/* Заголовок*/
				addQuestHead2Search(stripslashes($str_head), $idPost, $lptime, $conn, "", "");
				/* Тело*/
				addQuestBody2Search(stripslashes($str_body),$idPost, $lptime, $conn, "", "");
			}
		}else{
			if ($inTop){
				/* Заголовок*/
					addTopic2Search(stripslashes($str_head), $idPost, $lptime, $conn, "", "");	
				/* Тело*/
					addTopBody2Search(stripslashes($str_body),$idPost, $lptime, $conn, "", "");
			}else{
				/* Заголовок*/
					addHead2Search(stripslashes($str_head), $idPost, $lptime, $conn, "", "");	
				/* Тело*/
					addBody2Search(stripslashes($str_body),$idPost, $lptime, $conn, "", "");
			}
		}
	   $sqlSsEnd="
	   UPDATE
	   	ss
	   set id_post=id_post-1, date_post='".$lptime."'
	   
	   ";
	}else{
	   $sqlSsEnd="
	   UPDATE
	   	ss
	   set id_post=id_post-1
	   
	   ";
	}
   $resSsEnd=fd_query($sqlSsEnd, $conn, "");
   mysql_close($conn);
   echo $idPost;
	
?>