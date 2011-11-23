SELECT 
 id, 
 flname 
FROM 
 fdfolders 
WHERE 
 user = 0 
 OR user = ? 
ORDER BY id 