<?
         // �������� ������ �����
         $sql_folders="
         SELECT 
            id,
            flname,
            user
         FROM
            fdfolders
         WHERE
            user=".$_SESSION['idu']."   
            OR user=0
         ORDER BY
            id    
         ";     
         $rslt_folders=fd_query($sql_folders, $conn, "");
         $folders_num=mysql_num_rows($rslt_folders);
         echo "<div class=mnuprof align='CENTER'><b>".$_mess73."</b></div>";
         echo "<form method='POST' class=content action='delfolder.php?id=7'>";
         echo "<table class='control'><tr class=heads>";
         // ��������� �������
         // ��� �����
         echo "<th class='internal'><div class=tbtext>".$_mess74."</div></th>";
         // ������
         echo "<th class='internal' width='20'></th>";
         echo "</tr>";
         for ($xf1=0; $xf1<$folders_num; $xf1++){ 
            // ��� �����
            $str_name=mysql_result($rslt_folders, $xf1, 'flname');
            // id �����
            $str_id=mysql_result($rslt_folders, $xf1, 'id');
            // ����� �����
            $str_user=mysql_result($rslt_folders, $xf1, 'user');
            // �����
            echo "<tr>";
            echo "<td class='internal'><div class=tbtext>";
            echo $str_name;
            echo "</div></td>";
            // ������.
            echo "<td class='internal'>";
            if ($str_user){
               echo "<div align='center' class=tbtext>";
               echo '<input type="checkbox" name="'.$xf1.'" value="'.$str_id.'">';
               echo "</div>";
            }
            echo "</td>";       
            echo "</tr>";
         }
         // ������ (���� ������ ��������)
         echo "<tr>";
         echo "<td colspan=2 class='internal' align='right'>"; 
         echo "<span class=tbtextnread>".$_mess69."&nbsp;&nbsp;</span>";
         echo "<select size='1' name='ACT'>";
         echo "<option selected value='del'><span class=mnuprof>".$_mess70."&nbsp;&nbsp;</span></option>";
         echo "</select>&nbsp;";
         echo '<input type="hidden" value="'.$folders_num.'" name="NRW">';
         // ��������� ������ ���������...
         // �����
         echo "<input type=hidden name='IDU' value='".$_SESSION['idu']."'>";
         echo "<input type=hidden name='AUT' value='".$_SESSION['autor']."'>";
         // ������ ������
         if (isset($_SESSION['pass2'])) {
            // ����
            echo "<input type=hidden name='PS2' value='".$_SESSION['pass2']."'>";
         }
         else
         {
            // �� ����
            echo "<input type=hidden name='PS1' value='".$_SESSION['pass1']."'>";
         }
         echo '<input value="OK" type="submit">';
         echo "</td>";
         echo "</tr>";
         echo "</table>";
         echo "</form>";  
         // ���������� ����� �����
         echo "<form method='POST' class=content action='newfolder.php?id=7'>";
         echo "<span class=tbtext>".$_mess74.":&nbsp;</span>"; 
         echo "<input type='text' size=50 name='FOLD'>";
         echo "&nbsp;<input type='submit' value='".$_mess75."' >";
         // ��������� ������ ���������...
         // �����
         echo "<input type=hidden name='IDU' value='".$_SESSION['idu']."'>";
         echo "<input type=hidden name='AUT' value='".$_SESSION['autor']."'>";
         // ������ ������
         if (isset($_SESSION['pass2'])) {
            // ����
            echo "<input type=hidden name='PS2' value='".$_SESSION['pass2']."'>";
         }
         else
         {
            // �� ����
            echo "<input type=hidden name='PS1' value='".$_SESSION['pass1']."'>";
         }
         echo "</form>";  
         
?>