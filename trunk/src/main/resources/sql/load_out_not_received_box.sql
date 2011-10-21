SELECT
 fdmail.id
 , fdmail.head
 , fdmail.body
 , fdmail.d_cr
 , fdmail.d_snt
 , fdmail.d_cr
 , fdmail.d_rcv
 , fdmail.d_read
 , fdmail.del_r
 , fdmail.del_s
 , fdmail.sndr
 , users.nick
FROM
 fdmail
 LEFT JOIN users ON fdmail.rcvr = users.id
WHERE
 fdmail.sndr= ?
 AND fdmail.del_r <> 1 
 AND fdmail.d_rcv IS NULL
ORDER BY
 fdmail.d_snt DESC
