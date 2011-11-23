SELECT 
 id
 , name 
FROM 
 fdviews 
WHERE 
 user = 0 
 OR user = ? 
ORDER BY 
 id 