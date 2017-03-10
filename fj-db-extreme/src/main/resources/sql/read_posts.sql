SELECT
 posts.id,
 posts.type,
 posts.thread,
 posts.author,
 posts.created,
 posts.state,
 posts.reply_to,
 posts.title,
 posts.ip,
 posts.domen,
 posts.edited_times,
 posts.edited,
 posts.post,
 users.nick,
 users.avatar,
 users.s_avatar,
 users.ok_avatar,
 users.v_avatars,
 users.h_ip,
 users.city,
 users.scity,
 users.country,
 users.scountry,
 users.footer,
 titles.type AS thread_type
FROM 
 (posts LEFT JOIN users ON posts.author = users.id)
  LEFT JOIN titles ON posts.thread = titles.id
WHERE
 thread = ?
ORDER BY 
 id ASC
LIMIT ?, ?