SELECT DISTINCT 
 `users`.`nick` AS `nicks`
FROM
 `request`
 LEFT JOIN `users` on `request`.`userId` = `users`.`id`
WHERE 
 (`request`.`userId` <> 0)
 AND (`request`.`time` > UNIX_TIMESTAMP(now() - INTERVAL 2 MINUTE))
 AND (`users`.`ban` = 0)
ORDER BY 
 `users`.`nick`;
