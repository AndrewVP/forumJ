SELECT
 COUNT(DISTINCT `ipId`, `uasId`) AS guests
FROM
 `request`
WHERE
 `userId` < 1
 AND (`time` > UNIX_TIMESTAMP(NOW() - INTERVAL 2 MINUTE))
 AND `botId` = 0
