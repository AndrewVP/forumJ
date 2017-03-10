SELECT
 COUNT(id) AS kolvo 
FROM 
 posts
WHERE 
 thread = ?
 AND id < ?