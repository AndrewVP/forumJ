<?   
$str_id=$n0;
/*Добавляем заголовок*/
$str_type=1;
if (isset($_POST['US'])) $str_type=2;
$query="
INSERT INTO
   titles 
(
   auth, 
   head, 
   reg, 
   lposttime, 
   lpostnick, 
   type
) 
VALUES
(
   '$str_id', 
   '$str_head', 
   '$rgtime', 
   '$rgtime', 
   '".$_POST['AUT']."', 
   '$str_type'
)
";
fd_query($query, $conn, "");
$str_idd=mysql_insert_id();
$str_body=mysql_real_escape_string($_POST['A2']);
/*Добавляем Сообщение*/
	$queryPostTbl = "
		SELECT
			curret_body_table,
			current_body_head_table
		FROM
			fd_setup
	";
	$rsltPostTbl = fd_query($queryPostTbl, $conn, "");
	$currentBodyTable = mysql_result($rsltPostTbl, 0, 'curret_body_table');
	$currentHeadTable = mysql_result($rsltPostTbl, 0, 'current_body_head_table');
	$query="
		INSERT INTO 
		   body 
		(
		   head,
		   table_post,
		   table_head
		) 
		VALUES 
		(
		   '$str_idd', 
		   '$currentBodyTable',
		   '$currentHeadTable'
		)
		";
$result=fd_query($query, $conn, "");
$idPost=mysql_insert_id();
	$query="
		INSERT INTO 
		   ".$currentHeadTable." 
		(
			id,
			id_post,
		   auth, 
		   thread_id,
		   tilte, 
		   fd_post_time, 
		   ip, 
		   domen
		) 
		VALUES 
		(
			'$idPost',
			'$idPost',
		   '$str_id', 
		   '$str_idd', 
		   '$str_head', 
		   ".$questTime->getTimestamp().", 
		   '$str_ip', 
		   '$str_dom'
		)
		";
$result=fd_query($query, $conn, "");
	$queryInsBody = "
		INSERT INTO 
			".$currentBodyTable." 
				(id, id_post, body) 
			VALUES 
				('$idPost', '$idPost', '$str_body')
			";
	$rsltInsBody = fd_query($queryInsBody, $conn, "");
/*Добавляем вопрос*/
$str_quest=mysql_real_escape_string($_POST['Q']);
		$query="UPDATE 
					titles 
				SET 
					id_last_post=".$idPost."  
				WHERE 
					id=".$str_idd;
      $result=fd_query($query, $conn, ""); 
$query="
INSERT INTO 
   quest 
(
   numb, 
   head, 
   node
) 
VALUES 
( 
   0, 
   '$str_idd', 
   '$str_quest'
)
";
$result=fd_query($query, $conn, "");
// Добавляем варианты
$x2=1;
$x1=1;
while (isset($_POST['P'.$x1])){
    if (trim($_POST['P'.$x1])<>""){
          $str_node=mysql_real_escape_string($_POST['P'.$x1]);
          $query="
          INSERT INTO 
             quest 
          (
             numb, 
             head, 
             node, 
             user
          ) 
          VALUES 
          ( 
             '$x2', 
             '$str_idd', 
             '$str_node', 
             '$str_id'
          )";
          $result=fd_query($query, $conn, "");
          $x2=$x2+1;
   }
          $x1+=1;
}
/* Записываем данные для поиска*/
/* Автор*/
//	fd_new_search_user($str_id, $_POST['AUT'], $idPost, $questTime, $conn);			
	if ($is_search){
	/* Заголовок*/
		addQuestTopic2Search(stripslashes($str_head), $idPost, $rgtime, $conn, "", "");	
	/* Тело*/
		addQuestTopBody2Search(stripslashes($str_body),$idPost, $rgtime, $conn, "", "");
	}
mysql_close($conn);
// Отправляем в форум
?>
<html>
   <head>
      <meta http-equiv='Refresh' content='0; url=index.php'>
      <title></title>
   </head>
   <body>
   </body>
</html>