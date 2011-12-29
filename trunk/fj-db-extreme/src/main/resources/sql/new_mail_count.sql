SELECT 
 COUNT(*) AS nmail 
FROM 
 fdmail 
WHERE 
 rcvr = ? 
 AND d_rcv IS NULL 