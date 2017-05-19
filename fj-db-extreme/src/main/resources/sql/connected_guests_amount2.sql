SELECT
 COUNT(DISTINCT `ipId`) AS guests
FROM
 `request`
WHERE
 `userId` < 1
 AND (`time` > UNIX_TIMESTAMP(NOW() - INTERVAL 2 MINUTE))
