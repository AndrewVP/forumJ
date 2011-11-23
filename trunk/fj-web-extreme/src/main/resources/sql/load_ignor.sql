SELECT
 ignor.id
 , ignor.type
 , ignor.begin
 , ignor.end
 , ignor.ignor
 , users.nick
FROM
 ignor
LEFT JOIN users ON ignor.ignor = users.id
WHERE 
 ignor.user = ?
 AND end>NOW()
ORDER BY
 end DESC
