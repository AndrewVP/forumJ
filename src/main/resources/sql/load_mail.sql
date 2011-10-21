SELECT
 fdmail.id
 , fdmail.head
 , fdmail.body
 , fdmail.d_cr
 , fdmail.d_snt
 , fdmail.d_rcv
 , fdmail.d_read
 , fdmail.del_r
 , fdmail.del_s
 , fdmail.sndr
 , fdmail.rcvr
 , senders.nick AS sndr_nick
 , receivers.nick AS rcvr_nick
FROM
 ((fdmail
 LEFT JOIN users AS senders ON fdmail.sndr = senders.id)
 LEFT JOIN users AS receivers ON fdmail.rcvr = receivers.id)
 
WHERE
 (fdmail.rcvr = ? OR fdmail.sndr = ?)
 AND fdmail.del_r <> 1 
 AND fdmail.id = ?
