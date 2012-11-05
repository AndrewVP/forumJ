SELECT 
 us.mail
 , us.lang
FROM 
 fd_subscribe  fsb
 , users us
WHERE 
 fsb.title = ?
 AND fsb.user = us.id
 AND us.mail <> ''
 AND fsb.user NOT IN (SELECT user FROM ignor WHERE ignor = ? AND end > now() ORDER BY user)