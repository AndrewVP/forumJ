   group by
     thread
   order by
     maxd desc
  ) AS p1
  left join posts AS p2 ON p1.maxd = p2.id
  left join titles AS t1 on p1.thread = t1.id
  left join users AS u1 on t1.auth = u1.id
  left join users AS u2 on p2.author = u2.id
  left join fdtranzit f1 on p1.thread = f1.title AND f1.user = ?
  left join fdfolders f2 on  f1.folder = f2.id
