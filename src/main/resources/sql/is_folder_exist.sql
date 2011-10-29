SELECT 
 id
FROM
 fdfolders
WHERE
 (user = ? OR user=0)
 AND flname = ?
ORDER BY
 id    
