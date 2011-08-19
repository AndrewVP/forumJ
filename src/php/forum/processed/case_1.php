<?
         // Выбираем список Игнорируемых
         $sql_ignor="
         SELECT
            ignor.id,
            ignor.type,
            DATE_FORMAT(ignor.begin, '%d.%m.%y %H:%i') as begin_,
            DATE_FORMAT(ignor.end, '%d.%m.%y %H:%i') as end_,
            users.nick
         FROM
            ignor
            LEFT JOIN users on ignor.ignor=users.id
         WHERE 
            ignor.user=".$_SESSION['idu']."
            AND end>now()
         ORDER BY
            end DESC";
         $res1=fd_query($sql_ignor, $conn, "");
         // Есть игнор?
         $numr=mysql_num_rows($res1);
         if (!$numr) {
            // Нет.
            echo "<span class=mnuprof>".$_mess25."</span>";
         }
         else {
            // Да
            // Таблица Игнора
            echo "<div class=mnuprof align='CENTER'><b>".$_mess48."</b></div>";
            echo "<table class=control>";
            echo "<tr class=heads>";
            echo "<th class='internal'>";
            echo "<span class=mnuprof>№</span>";
            echo "</th>";
            echo "<th class='internal'>";
            echo "<span class=mnuprof>".$_mess44."</span>";
            echo "</th>";
            echo "<th class='internal'>";
            echo "<span class=mnuprof>".$_mess45."</span>";
            echo "</th>";
            echo "<th class='internal'>";
            echo "<span class=mnuprof>".$_mess46."</span>";
            echo "</th>";
            echo "<th class='internal'>";
            echo "<span class=mnuprof>".$_mess53."</span>";
            echo "</th>";
            echo "<th class='internal'>";
            echo "<span class=mnuprof>".$_mess47."</span>";
            echo "</th>";
            echo "</tr>";
            for ($x1=0; $x1<$numr; $x1++) {
               // id Записи
               $str_id=mysql_result($res1, $x1, 'id');
               // Тип игнора
               $str_type=mysql_result($res1, $x1, 'type');
               // Игнорируемый
               $str_nick=mysql_result($res1, $x1, 'nick');
               // Начал
               $str_begin=mysql_result($res1, $x1, 'begin_');
               // Конец
               $str_end=mysql_result($res1, $x1, 'end_');
               // Выводим спи
               echo "<tr>";
               // Номер
               echo "<td class='internal'>";
               echo "<span class=mnuprof>".($x1+1)."</span>";
               echo "</td>";
               // Игнорируемый
               echo "<td class='internal'>";
               echo "<span class=mnuprof>".$str_nick."</span>";
               echo "</td>";
               // Начал
               echo "<td class='internal'>";
               echo "<span class=mnuprof>".$str_begin."</span>";
               echo "</td>";
               // Конец
               echo "<td class='internal'>";
               echo "<span class=mnuprof>".$str_end."</span>";
               echo "</td>";
               // Ветки игнорируются?
               echo "<td class='internal' align='CENTER'>";
               $str_tema=$_mess26;
               if (!$str_type) $str_tema=$_mess27;
               echo "<span class=mnuprof>".$str_tema."</span>";
               echo "</td>";
               // Изменение
               echo "<td class='internal'>";
               echo "<form method='POST' action='amn.php' class=frmsmall>";
               echo "<select size='1' name='D'>";
               echo "<option selected value='01'><span class=mnuprof>1</span></option>";
               for ($xo1=2; $xo1<32; $xo1++){
                  $dd="";
                  if ($xo1<10) $dd="0";
                  echo "<option value='".$dd.$xo1."'><span class=mnuprof>$xo1</span></option>";
               }
               echo "</select>&nbsp;";
               echo "<select size='1' name='MTH'>";
               echo "<option selected value='01'><span class=mnuprof>$_mess32</span></option>";
               echo "<option value='02'><span class=mnuprof>$_mess33</span></option>";
               echo "<option value='03'><span class=mnuprof>$_mess34</span></option>";
               echo "<option value='04'><span class=mnuprof>$_mess35</span></option>";
               echo "<option value='05'><span class=mnuprof>$_mess36</span></option>";
               echo "<option value='06'><span class=mnuprof>$_mess37</span></option>";
               echo "<option value='07'><span class=mnuprof>$_mess38</span></option>";
               echo "<option value='08'><span class=mnuprof>$_mess39</span></option>";
               echo "<option value='09'><span class=mnuprof>$_mess40</span></option>";
               echo "<option value='10'><span class=mnuprof>$_mess41</span></option>";
               echo "<option value='11'><span class=mnuprof>$_mess42</span></option>";
               echo "<option value='12'><span class=mnuprof>$_mess43</span></option>";
               echo "</select>&nbsp;";
               echo "<select size='1' name='Y'>";
               echo "<option class=mnuprof selected value='2006'>2006</option>";
               echo "<option class=mnuprof value='2007'>2007</option>";
               echo "<option class=mnuprof value='2008'>2008</option>";
               echo "<option class=mnuprof value='2009'>2009</option>";
               echo "</select>&nbsp;";
               echo "<select size='1' name='H'>";
               echo "<option class=mnuprof selected value='00'>0</option>";
               for ($xo2=1; $xo2<24; $xo2++){
                  $dd="";
                  if ($xo2<10) $dd="0";
                  echo "<option class=mnuprof value='".$dd.$xo2."'>$xo2</option>";
               }
               echo "</select>&nbsp;";
               echo "<select size='1' name='M'>";
               echo "<option class=mnuprof selected value='00'>0</option>";
               for ($xo3=1; $xo3<60; $xo3++){
                  $dd="";
                  if ($xo2<10) $dd="0";
                  echo "<option class=mnuprof value='".$dd.$xo3."'>$xo3</option>";
               }
               echo "</select>&nbsp;";
               echo '<input class=mnuprof type="submit" value="'.$_mess49.'" name="B1">';
               echo '<br><input type="checkbox" name="C1" value="ON"> <span class=mnuprof>'.$_mess52."</span>";
               // Прередаем нужные параметры...
               // id Записи
               echo "<input type=hidden name=\"IDZ\" size=\"20\" value=\"".$str_id."\">";
               // Автор
               echo "<input type=hidden name=\"IDU\" size=\"20\" value=\"".$_SESSION['idu']."\">";
               // пароль автора
               if (isset($_SESSION['pass2'])) {
                  // кука
                  echo "<input type=hidden name=\"PS2\" size=\"20\" value=\"".$_SESSION['pass2']."\">";
               }
               else {
                  // не кука
                  echo "<input type=hidden name=\"PS1\" size=\"20\" value=\"".$_SESSION['pass1']."\">";
               }
               echo "</form>";
               echo "</td>";
   
               echo "</tr>";
            }
            echo "</table>";
         }
?>