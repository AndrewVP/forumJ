<?
// ����������� ������ �����
session_start();
   include("query.php");
//������������� �����������
include("cache.php");
if (isset($_GET['id'])) $gid=stripslashes($_GET['id']);
// ���� ����� ����� ����������? 5 - logout!
if ($gid<5 or $gid>8) $_SESSION['where']=$gid;
include('setup.php');
// ����������� � �����
// �������� ����������
$action=11;
include("stat.php");
//
 mysql_close($conn);
// ����� ���� ����������? �� ��������� - ���������� :)
include("lang.php");
?>
<html>
   <head>
      <meta http-equiv='content-type' content='text/html; charset=windows-1251'>
      <?
      //�����
      include('style.php');
      ?>
      <title>
         �� ����������� ����� ������������! :)
      </title>
   </head>
   <?
   // ���� ���� ��������
   ?>
   <body bgcolor=#EFEFEF>
      <?// ������� �������?>
      <table border='0' style='{border-collapse: collapse;}' width='100%'>
         <?
         // ������� � ���� � ������� ��������
         include("logo.php");
         // ������� ������
         include("menu.php");
         // ����� �����������
         ?>
         <tr>
             <td width='100%'>
<!--               <form  action='' method='POST'> -->
                  <table>
                     <tr>
                        <td>
                           <p>
                           <font color='red'>��������, �� ����������� �������� ���������</color>
                              <?
                              // ����������, ������ �� ���� ������?
                              switch ($gid){
                                 // ������ �� ������ �����������
                                 case 1:
                                    //echo("�����������������, ����������");
                                    break;
                                 case 4:
                                    echo("�����������������, ����������");
                                    break;
                                 // ���������� ���������� ���� ���������������������
                                 case 2:
                                    echo("���������� ���� ��� ���������� ����� ������ ������������������ ����������!");
                                    break;
                                 // ���������� �������� ���������������������
                                 case 3:
                                    echo("��������� ���� ������ ����� ������ ������������������ ����������!");
                                    break;
                                 case 5:
                                    echo("<b>��������, �� ".$_SESSION['nick']." ��� ���������������...</b>");
                                    unset($_SESSION['nick']);
                                    break;
                                 case 6:
                                    echo("<b>�� ������ �� ������ ��������� �����, ������� ��� �������� ������� ������� � ���� ��� :)</b>");
                                 // �� ������ ������ :)
                                    unset($_SESSION['nick']);
                                    break;
                                 case 7:
                                    echo("�� ��������� ������");
                                    break;
                                 case 8:
                                    echo("�� ��������� E-mail");
                                    break;
                                 case 9:
                                    echo("�� ��������� ��������������");
                                    break;
                                 case 10:
                                    echo("�� ����������� ������ ������! :)");
                                    break;
                                 case 11:
                                    echo("�� ����������� ������ ��������������! :)");
                                    break;
                                 case 12:
                                    echo("<b>���������� � ����� �������� ������ ��� ���������������! :)</b>");
                                    break;
                              }
                              ?>
                           </p>
                        </td>
                     </tr>
                     <?
                     // � ������������ ���������
                     // ����������� ���
                     ?>
                     <tr>
                        <td>
                           <table>
                              <tr>
                                 <td>
                                    ���*
                                 </td>
                                 <td>
                                    <input type='text' name='R1' size='20'>
                                 </td>
                              </tr>
                              <?
                              // ������
                              ?>
                              <tr>
                                 <td>
                                    ������*
                                 </td>
                                 <td>
                                    <input type=password name='R2' size='20'>
                                 </td>
                                 <td>
                                    ��������� ������*
                                 </td>
                                 <td>
                                    <input type=password name='R22' size='20'>
                                 </td>
                              </tr>
                              <?
                              // ����
                              ?>
                              <tr>
                                 <td>
                                    E-Mail*
                                 </td>
                                 <td>
                                    <input type=text name='R33' size='20'>
                                 </td>
                                 <td>
                                    ��������� E-Mail*
                                 </td>
                                 <td>
                                    <input type=text name='R3' size='20'>
                                 </td>
                              </tr>
                              <?
                              // �������������
                              ?>
                              <tr>
                                 <td>
                                    �������������*
                                 </td>
                                 <td>
                                    <input type=password name='R4' size='20'>
                                 </td>
                              </tr>
                              <tr>
                                 <td colspan='4'>
                                    ������������� �������� ���� ��� � ������ ����� �� ������������ � �� ��������, ������������ ��� ������ � '���������' ����� ���
                                 </td>
                              </tr>
                              <?
                              // ������
                              ?>
                              <tr>
                                 <td>
                                    <input type='submit' value='���������' name='B1' disabled>
                                    <input type='reset' value='��������' name='B2'>
                                 </td>
                              </tr>
                           </table>
                        </td>
                     </tr>
                  </table>
<!--               </form> -->
            </td>
         </tr>
         <?
         // ����� �����������
         // ������� ������
            include("menu.php");
         // ������ �����, �������� � ��������.
            include("end.php");
         ?>
   </body>
</html>