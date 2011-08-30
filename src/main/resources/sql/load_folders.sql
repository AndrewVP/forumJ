SELECT 
 fdfolders.id
 , fdfolders.flname
 , fdfolders.d_cr
 , user
FROM
 fdvtranzit
 LEFT JOIN fdfolders on fdvtranzit.folder = fdfolders.id
WHERE
 (fdvtranzit.user = ?   
 OR fdvtranzit.user = 0)
 AND fdvtranzit.view = ?
ORDER BY
 fdvtranzit.id
