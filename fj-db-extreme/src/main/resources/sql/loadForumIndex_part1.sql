select
  p1.thread
  , t1.dock
  , p2.created
  , t1.type
  , t1.npost
  , t1.seenid
  , t1.seenall
  , DATE_FORMAT(t1.reg, '%d.%m.%Y %H:%i') as reg_
  , t1.head
  , p2.author
  , u2.nick as lpostnick
  , p1.maxd
  , t1.closed
  , t1.auth
  , f2.flname
  , u1.nick
FROM
  (select max(id) as maxd, thread
   from posts
   WHERE
     author NOT IN (SELECT ignor FROM ignor WHERE user = ? AND end > now())
