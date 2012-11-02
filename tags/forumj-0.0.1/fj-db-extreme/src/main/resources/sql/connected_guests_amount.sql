SELECT
 COUNT(DISTINCT fd_ip) AS guests
FROM
 fd_action
WHERE
 fd_user = 0
 AND (fd_time > NOW() - INTERVAL 2 MINUTE)
