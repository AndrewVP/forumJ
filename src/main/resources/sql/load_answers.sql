SELECT 
quest.id
, quest.node
, quest.numb
,quest.user
, quest.gol
, quest.type
, users.nick 
FROM 
quest LEFT JOIN users ON quest.user=users.id 
WHERE quest.head = ? 
ORDER BY numb