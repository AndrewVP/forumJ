SELECT folder
FROM
 fdvtranzit
WHERE
 (user = ? OR user = 0) AND view = ?