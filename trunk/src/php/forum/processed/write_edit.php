<?
/* ����������� ������ ����*/
$query="UPDATE
           ".$_POST['IDTblHead']."
        SET
           fd_post_edit_time = ".$postTime->getTimestamp().",
           nred = nred+1,
           tilte = '$str_head'
        WHERE id=".$_POST['IDHead'];
$result=fd_query($query, $conn, "");
$query="UPDATE
           ".$_POST['IDTbl']."
        SET
           body='$str_body'
        WHERE id=".$_POST['IDPst'];
$result=fd_query($query, $conn, "");
// ����� ������ � ����?
$sql_nfirst="SELECT
           id
        FROM
           body
        WHERE
           head=".$str_idd."
        ORDER BY id
        LIMIT 1";
$result1=fd_query($sql_nfirst, $conn, "");
$str_first=mysql_result($result1, 0, 'id');
$inTop=false;
if ($str_first==$_POST['IDB']){
	/* ������, �������������� ��������� � ������� titles*/
	$sql_head="UPDATE
                 titles
              SET
                 head='".$str_head."'
              WHERE id=".$str_idd;
	$result2=fd_query($sql_head, $conn, "");
	$inTop=true;
}
/* ���������� ������ ��� ������*/
/*������� ������ ������*/
	$idPost=$_POST['IDB'];
//	fd_delete_post_data($idPost, $conn, "", "");
	if ($is_search){
		$lptime = fdGetPostTime($idPost, $conn, "", "");
	if (isset($_POST['ISQUEST'])){
		if ($inTop){
			/* ���������*/
			addQuestTopic2Search(stripslashes($str_head), $idPost, $lptime, $conn, "", "");
			/* ����*/
			addQuestTopBody2Search(stripslashes($str_body),$idPost, $lptime, $conn, "", "");
					}else{
			/* ���������*/
			addQuestHead2Search(stripslashes($str_head), $idPost, $lptime, $conn, "", "");
			/* ����*/
			addQuestBody2Search(stripslashes($str_body),$idPost, $lptime, $conn, "", "");
		}
	}else{
		if ($inTop){
			/* ���������*/
				addTopic2Search(stripslashes($str_head), $idPost, $lptime, $conn, "", "");	
			/* ����*/
				addTopBody2Search(stripslashes($str_body),$idPost, $lptime, $conn, "", "");
		}else{
			/* ���������*/
				addHead2Search(stripslashes($str_head), $idPost, $lptime, $conn, "", "");	
			/* ����*/
				addBody2Search(stripslashes($str_body),$idPost, $lptime, $conn, "", "");
		}
	}
	}
	
unset($_SESSION['edit']);
?>