SELECT
 @@TABLE@@.id,
 @@TABLE@@.ip,
 @@TABLE@@.auth,
 @@TABLE@@.domen,
 @@TABLE@@.tilte,
 @@TABLE@@.fd_post_time as post_time,
 @@TABLE@@.nred,
 @@TABLE@@.fd_post_edit_time as post_edit_time,
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
 titles.head,
 titles.type
FROM
 (@@TABLE@@ 
  LEFT JOIN users ON @@TABLE@@.auth = users.id)
  LEFT JOIN titles ON @@TABLE@@.thread_id = titles.id
  WHERE @@TABLE@@.id IN
