select count(id) as kolvo
from titles
WHERE
 auth NOT IN (SELECT ignor FROM ignor WHERE user = ? AND end > now())
 AND
 id NOT IN (select title from fdtranzit where user = ?)
