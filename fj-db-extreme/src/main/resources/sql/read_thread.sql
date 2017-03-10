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
 , t.closed
 ,count(b.id) as npost 
FROM 
 titles t
 , posts b
WHERE 
 t.id = ?
 AND t.id = b.thread
GROUP BY
 t.auth, t.head, t.reg, t.lposttime, t.lpostuser, t.lpostnick, t.id_last_post, t.seenid, t.seenall, t.dock, t.type, t.folder, t.closed
