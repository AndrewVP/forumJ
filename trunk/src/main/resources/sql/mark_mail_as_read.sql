UPDATE
 fdmail
SET d_read=now()
WHERE
 rcvr =? 
 AND id = ?
 AND del_r<>1 
 AND d_snt IS NOT NULL
 AND d_rcv IS NULL
