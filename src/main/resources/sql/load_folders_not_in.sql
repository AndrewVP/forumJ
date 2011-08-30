SELECT 
 id
 , flname
 , d_cr
 , user
FROM
 fdfolders
WHERE
 (user = ? OR user = 0)
 AND id NOT IN
 
 (SELECT 
  folder
 FROM
  fdvtranzit
 WHERE
  (fdvtranzit.user = ? OR fdvtranzit.user = 0)
  AND fdvtranzit.view = ?
 ORDER BY
  fdvtranzit.id)
  
ORDER BY
 id    
