<?
         // Выбираем список подписаных веток
         $sql_subs="
         SELECT 
            fd_subscribe.id,
            fd_subscribe.title,
            titles.head
         FROM
            fd_subscribe
            LEFT JOIN titles ON fd_subscribe.title=titles.id
         WHERE
            fd_subscribe.user=".$_SESSION['idu']."   
            AND fd_subscribe.act=1
         ORDER BY
            fd_subscribe.id DESC    
         ";     
         $rslt_subs=fd_query($sql_subs, $conn, "");
         $subs_num=mysql_num_rows($rslt_subs);
         echo "<div class=mnuprof align='CENTER'><b>".$_mess87."</b></div>";
         echo "<form method='POST' class=content action='delsubs.php?id=8'>";
         echo "<table class='control'><tr class=heads>";
         // Заголовки таблицы
         // Тема ветки
         echo "<th class='internal'><div class=tbtext>".$_mess59."</div></th>";
         // Птичка
         echo "<th class='internal' width='20'></th>";
         echo "</tr>";
         for ($xf1=0; $xf1<$subs_num; $xf1++){ 
            // Тема ветки
            $str_head=mysql_result($rslt_subs, $xf1, 'head');
            // id подписки
            $str_id=mysql_result($rslt_subs, $xf1, 'id');
            // id ветки
            $str_title=mysql_result($rslt_subs, $xf1, 'title');
            // Ветка
            echo "<tr>";
            echo "<td class='internal'><div class=tbtext>";
            echo "<a href='tema.php?id=".$str_title."&end=1#end'>".$str_head."</a>";
            echo "</div></td>";
            // Флажок.
            echo "<td class='internal'>";
            echo "<div align='center' class=tbtext>";
            echo '<input type="checkbox" name="'.$xf1.'" value="'.$str_id.'">';
            echo "</div>";
            echo "</td>";       
            echo "</tr>";
         }
         // Сервис (пока только отписка)
         echo "<tr>";
         echo "<td colspan=2 class='internal' align='right'>"; 
         echo "<span class=tbtextnread>".$_mess69."&nbsp;&nbsp;</span>";
         echo "<select size='1' name='ACT'>";
         echo "<option selected value='del'><span class=mnuprof>".$_mess88."&nbsp;&nbsp;</span></option>";
         echo "</select>&nbsp;";
         echo '<input type="hidden" value="'.$subs_num.'" name="NRW">';
         // Прередаем нужные пераметры...
         // Автор
         echo "<input type=hidden name='IDU' value='".$_SESSION['idu']."'>";
         echo "<input type=hidden name='AUT' value='".$_SESSION['autor']."'>";
         // пароль автора
         if (isset($_SESSION['pass2'])) {
            // кука
            echo "<input type=hidden name='PS2' value='".$_SESSION['pass2']."'>";
         }
         else
         {
            // не кука
            echo "<input type=hidden name='PS1' value='".$_SESSION['pass1']."'>";
         }
         echo '<input value="OK" type="submit">';
         echo "</td>";
         echo "</tr>";
         echo "</table>";
         echo "</form>";  
         
?>