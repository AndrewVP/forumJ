SELECT
 count(id) AS mx
FROM body
WHERE id > ?
 AND head = ?
