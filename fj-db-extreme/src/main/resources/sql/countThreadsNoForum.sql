select count(id) as kolvo
from titles
WHERE
 auth NOT IN (SELECT ignor FROM ignor WHERE user = ? AND end > now())
 AND
 id IN (SELECT folder FROM fdvtranzit WHERE (user = ? OR user = 0) AND view = ?)
