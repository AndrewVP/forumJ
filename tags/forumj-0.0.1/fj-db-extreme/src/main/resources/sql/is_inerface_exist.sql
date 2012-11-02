SELECT 
 id
FROM
 fdviews
WHERE
 (user = ? OR user=0)
 AND name = ?
