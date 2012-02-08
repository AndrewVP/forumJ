SELECT 
 t.auth
 , t.head
 , t.reg
 , t.lposttime
 , t.lpostuser
 , t.lpostnick
 , t.id_last_post
 , t.seenid
 , t.seenall
 , t.dock
 , t.type
 , t.folder
 ,count(b.id) as npost 
FROM 
 titles t
 , body b
WHERE 
 t.id = ?
 and t.id = b.head
group by
 t.auth, t.head, t.reg, t.lposttime, t.lpostuser, t.lpostnick, t.id_last_post, t.seenid, t.seenall, t.dock, t.type, t.folder
