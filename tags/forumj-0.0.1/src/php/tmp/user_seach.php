<?php
   include("query.php");
   include('setup.php');
   $queryMaxId="
	SELECT 
		max(idpost) as nn
	FROM 
		fd_post_user
	";
   $resMax=fd_query($queryMaxId, $conn,"5");
   $maxId=mysql_result($resMax, 0, 'nn');
   $maxIdEnd=$maxId+10000;
   $queryMoveUser="
		INSERT INTO
			fd_post_user 
			(iduser, idpost, fd_date, fd_open) 
		SELECT 
			fd_search_user.id, 
			body.id, 
			body.reg, 
			0 
		FROM 
			`body` 
		LEFT JOIN
			fd_search_user ON body.auth=fd_search_user.iduser
		WHERE
		body.id > $maxId AND body.id < $maxIdEnd 
	";
?>