<?
// ��������� ����� �����
session_start();
/*������� ������*/
// ������
include_once 'classes/ua/com/diletant/util/time.php';
include("search_engine.php");
define("IN_HEAD_QUEST", 2, true);
define("IN_BODY_QUEST", 3, true);
define("TOP_POST_QUEST", 2, true);
define("NOT_TOP_POST_QUEST", 3, true);
include("query.php");
/* ������� ��������� �����*/
//������������� �����������
include("cache.php");
// �����������
include('setup.php');
// �����������
include('guard.php');
// �������� ����������
$action=13;
include("stat.php");
//
include("lang.php");
$n0=stripslashes($_POST['IDU']);
$n1=fd_nick($n0, $conn);
// ����� ���� ������ ���������������������?
if ($n1!="0"){
	// ��� ���������
	// ����� ������??
	if (!(trim($_POST['A2'])=="" or trim($_POST['T'])=="")) {
		// �� ������
		// ������� ����?
		if (!(trim($_POST['P1'])=="" or trim($_POST['P2'])=="" or trim($_POST['Q'])=="")){
			// ������� ���
	   	$questTime = new Time(time());
	      $rgtime = $questTime->toString("Y-m-d H:i:s");
			/*ip �����*/
			$str_ip=$_SERVER['REMOTE_ADDR'];
			$str_dom=gethostbyaddr($str_ip);
			/*���������*/
			$str_head=mysql_real_escape_string($_POST['T']);
			if (isset($_POST['comand']) && $_POST['comand']=="view"){
				include("quest_view.php");
			}else{
				include('quest_add.php');
			}
		}else{
			// ��� ��������
			?>
            <html>
               <head>
                  <meta http-equiv='content-type' content='text/html; charset=windows-1251'>
                  <meta http-equiv='Refresh' content='5; url=index.php'>
                  <title>
                     �� �� �� ���� ���������!
                  </title>
               </head>
               <body bgcolor=#EFEFEF>
                  <font size='5'><b>���� ���������� ���� �� ��� ��������!</b></font>
               </body>
            </html>
         <?   
            mysql_close($conn);
         }
      }else{
   // ������
      ?>
         <html>
            <head>
               <meta http-equiv='content-type' content='text/html; charset=windows-1251'>
               <meta http-equiv='Refresh' content='5; url=index.php'>
               <title>
                  �� �� �� ���� ���������!
               </title>
            </head>
            <body bgcolor=#EFEFEF>
               <font size='5'><b>��, ������� �������� ��������, ��� ��� ��� ������? ������ ���� �� ������ ���������!</b></font>
            </body>
         </html>
      <?
         mysql_close($conn);
      }
   }else{
   //����� ���������������������
   ?>
      <html>
         <head>
            <meta http-equiv='content-type' content='text/html; charset=windows-1251'>
            <meta http-equiv='Refresh' content='5; url=auth.php?id=4.php'>
            <title>
               �� �� �� ���� ���������!
            </title>
         </head>
         <body bgcolor=#EFEFEF>
            <font size='5'><b>������� ���� ��� ��� ���������� ����!</b></font>
         </body>
      </html>
   <?
      mysql_close($conn);
   }
?>