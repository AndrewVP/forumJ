SELECT 
 id
 , name
 , d_cr
 , user
FROM
 fdviews
WHERE
 user = ?   
 OR user = 0
ORDER BY
 id    
