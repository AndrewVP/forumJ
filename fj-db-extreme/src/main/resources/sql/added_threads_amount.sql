SELECT
 count(id) AS mx
FROM titles
WHERE id > ?
 AND auth NOT IN (SELECT ignor FROM ignor WHERE user = ? AND end > now())
