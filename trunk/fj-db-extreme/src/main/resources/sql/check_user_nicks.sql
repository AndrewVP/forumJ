SELECT 
 nick
FROM
 users
WHERE 
UPPER(nick) in (@@NICKS@@)
