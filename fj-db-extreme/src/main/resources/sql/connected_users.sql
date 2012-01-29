SELECT DISTINCT 
 users.nick AS nicks
FROM
 fd_action
 LEFT JOIN users on fd_action.fd_user = users.id
WHERE 
 (fd_action.fd_user <> 0) 
 AND (fd_action.fd_time > now() - INTERVAL 2 MINUTE)
 AND (users.ban = 0)
ORDER BY 
 users.nick;
