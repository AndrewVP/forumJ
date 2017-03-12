select distinct(thread)
from posts
WHERE
 author NOT IN (SELECT ignor FROM ignor WHERE user = ? AND end > now())
 AND
 thread NOT IN (select title from fdtranzit where user = ?)
 AND
 thread NOT IN (select id from titles where dock !=0)
order by
 created desc
limit ?, ?;