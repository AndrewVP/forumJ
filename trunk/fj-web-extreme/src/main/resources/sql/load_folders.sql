SELECT 
 id
 , flname
 , user
 , d_cr
FROM
 fdfolders
WHERE
 user = ? OR user=0
ORDER BY
 id    
