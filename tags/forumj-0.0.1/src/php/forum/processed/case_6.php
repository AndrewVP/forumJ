<?
         // �������� ������ �����������
         $sql_views="
         SELECT 
            id,
            name,
            user
         FROM
            fdviews
         WHERE
            user=".$_SESSION['idu']."   
            OR user=0
         ORDER BY
            id    
         ";     
         $rslt_views=fd_query($sql_views, $conn,"");
         $views_num=mysql_num_rows($rslt_views);  
         // "������ ����� �����������"
         echo "<div class=mnuprof align='CENTER'><b>".$_mess76."</b></div>";
         // ��������� �� ���������
         // 
         echo "<form method='POST' class=content action='defview.php'>";
         echo "<span class=tbtext><b>".$_mess84."</b></span>";
         echo "<select size='1' name='DVIEW'>";
         for ($dv=0; $dv< $views_num; $dv++){
            $_add="";
            if (mysql_result($rslt_views, $dv, 'id')==$_SESSION['def_view']) $_add="selected ";
            echo "<option ".$_add."value='".mysql_result($rslt_views, $dv, 'id')."'><span class=mnuprof>".mysql_result($rslt_views, $dv, 'name')."</span></option>";
         }
         echo "</select>&nbsp;";
         echo '<input type="submit" value="'.$_mess85.'" >';
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
         
         echo "<form method='POST' class=content action='delview.php?'>";
         echo "<table class='control'><tr class=heads>";
         // ��������� �������
         // ��� ����������
         echo "<th class='internal'><div class=tbtext>".$_mess77."</div></th>";
         // ������
         echo "<th class='internal' width='20'></th>";
         echo "</tr>";
         for ($xv1=0; $xv1<$views_num; $xv1++){ 
            // ��� ����������
            $str_name=mysql_result($rslt_views, $xv1, 'name');
            // id �����������
            $str_id=mysql_result($rslt_views, $xv1, 'id');
            // �������� �������� ���������
            if (isset($_GET['view']) and $_GET['view']==$str_id) $str_name="<b>".$str_name."</b>";
            // ����� ����������
            $str_user=mysql_result($rslt_views, $xv1, 'user');
            echo "<tr>";
            // ���������
            echo "<td class='internal'><div class=tbtext>";
            echo "<a href=control.php?id=6&view=".$str_id.">".$str_name."</a>";
            echo "</div></td>";
            // ������.
            echo "<td class='internal'>";
            if ($str_user){
               echo "<div align='center' class=tbtext>";
               echo '<input type="checkbox" name="'.$xv1.'" value="'.$str_id.'">';
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
         echo '<input type="hidden" value="'.$views_num.'" name="NRW">';
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
         echo "<form method='POST' class=content action='newview.php'>";
         echo "<span class=tbtext>".$_mess77.":&nbsp;</span>"; 
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
         // ��������� ����������
         if (isset($_GET['view']) and trim($_GET['view'])<>'') {
            // ��� ����������
            $sql_vname="
            SELECT
               name
            FROM
               fdviews
            WHERE
               id=".$_GET['view']."      
            ";
            $rslt_vname=fd_query($sql_vname, $conn, "");
            $str_vname=mysql_result($rslt_vname, 0, 'name');
            // �������� ������ ����� � ����������
            $sql_vfolders="
            SELECT 
               fdfolders.id,
               fdfolders.flname,
               fdvtranzit.user
            FROM
               fdvtranzit
               LEFT JOIN fdfolders on fdvtranzit.folder=fdfolders.id
            WHERE
               (fdvtranzit.user=".$_SESSION['idu']."   
               OR fdvtranzit.user=0)
               AND fdvtranzit.view=".trim($_GET['view'])."
            ORDER BY
               fdvtranzit.id
               ";     
            $rslt_vfolders=fd_query($sql_vfolders, $conn, "");
            $vfolders_num=mysql_num_rows($rslt_vfolders);
            echo "<div class=mnuprof align='CENTER'><b>".$_mess78."<u>".$str_vname."</u></b></div>";
            echo "<form method='POST' class=content action='delvfolder.php'>";
            echo "<table class='control'><tr class=heads>";
            // ��������� �������
            // ��� �����
            echo "<th class='internal'><div class=tbtext>".$_mess74."</div></th>";
            // ������
            echo "<th class='internal' width='20'></th>";
            echo "</tr>";
            for ($xvf1=0; $xvf1 < $vfolders_num; $xvf1++){ 
               // ��� �����
               $str_name=mysql_result($rslt_vfolders, $xvf1, 'flname');
               // id �����
               $str_id=mysql_result($rslt_vfolders, $xvf1, 'id');
               // ����� �����
               $str_user=mysql_result($rslt_vfolders, $xvf1, 'user');
               // �����
               echo "<tr>";
               echo "<td class='internal'><div class=tbtext>";
               echo $str_name;
               echo "</div></td>";
               // ������.
               echo "<td class='internal'>";
               if ($str_user){
                  echo "<div align='center' class=tbtext>";
                  echo '<input type="checkbox" name="'.$xvf1.'" value="'.$str_id.'">';
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
            echo '<input type="hidden" value="'.$vfolders_num.'" name="NRW">';
            // id ����������
            echo "<input type=hidden name='IDVW' value='".trim($_GET['view'])."'>";
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
            // �������� ������ �����
            $sql_folders="
            SELECT 
               id,
               flname,
               user
            FROM
               fdfolders
            WHERE
               (user=".$_SESSION['idu']."   
               OR user=0)
               AND id NOT IN (
                              SELECT 
                                 folder
                              FROM
                                 fdvtranzit
                              WHERE
                                 (fdvtranzit.user=".$_SESSION['idu']."   
                                 OR fdvtranzit.user=0)
                                 AND fdvtranzit.view=".trim($_GET['view'])."
                              ORDER BY
                                 fdvtranzit.id
                             )
            ORDER BY
               id    
            ";     
            $rslt_folders=fd_query($sql_folders, $conn, "");
            $folders_num=mysql_num_rows($rslt_folders);
            echo "<div class=mnuprof align='CENTER'><b>".$_mess73."</b></div>";
            echo "<form method='POST' class=content action='delfolder.php?id=6&view=".$_GET['view']."'>";
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
               echo "<div align='center' class=tbtext>";
               echo '<input type="checkbox" name="'.$xf1.'" value="'.$str_id.'">';
               echo "</div>";
               echo "</td>";       
               echo "</tr>";
            }
            // ������: ���������� � ��������� 
            echo "<tr>";
            echo "<td colspan=2 class='internal' align='right'>"; 
            echo "<span class=tbtextnread>".$_mess69."&nbsp;&nbsp;</span>";
            echo "<select size='1' name='ACT'>";
            echo "<option selected value='add'><span class=mnuprof>".$_mess79.$str_vname."&nbsp;&nbsp;</span></option>";
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
            echo "<form method='POST' class=content action='newfolder.php?id=6&view=".$_GET['view']."'>";
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
         }
?>