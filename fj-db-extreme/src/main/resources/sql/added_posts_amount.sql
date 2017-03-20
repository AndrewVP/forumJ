SELECT
 count(id) AS mx
FROM posts
WHERE id > ?
AND author NOT IN (SELECT ignor FROM ignor WHERE user = ? AND end > now())
