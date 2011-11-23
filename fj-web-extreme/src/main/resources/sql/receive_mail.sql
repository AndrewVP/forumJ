UPDATE
 fdmail
SET d_rcv=now()
WHERE
 rcvr =? 
 AND d_rcv IS NULL
