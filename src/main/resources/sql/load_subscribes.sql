SELECT 
 fd_subscribe.id
 , fd_subscribe.title
 , fd_subscribe.d_start
 , fd_subscribe.d_end
 , fd_subscribe.kod
 , fd_subscribe.type
 , titles.head
FROM
 fd_subscribe
 LEFT JOIN titles ON fd_subscribe.title = titles.id
WHERE
 fd_subscribe.user = ? 
 AND fd_subscribe.act = ?
ORDER BY
 fd_subscribe.id DESC    
