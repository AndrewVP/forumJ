SELECT
 COUNT(id) AS kolvo 
FROM 
 body 
WHERE 
 head = ?
 AND id < ?