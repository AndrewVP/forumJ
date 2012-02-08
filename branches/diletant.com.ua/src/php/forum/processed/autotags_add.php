<img src="skin/standart/picts/b.gif" onclick="InsertTags('[b]','[/b]')" alt="Полужирный текст">
<img src="skin/standart/picts/i.gif" onclick="InsertTags('[i]','[/i]')" alt="Курсивный текс">
<img src="skin/standart/picts/u.gif" onclick="InsertTags('[u]','[/u]')" alt="Подчеркнутый текст">
&nbsp;
<img src="skin/standart/picts/1.gif" onclick="InsertTags('[size=1]','[/size]')" alt="Размер шрифта 1">
<img src="skin/standart/picts/2.gif" onclick="InsertTags('[size=2]','[/size]')" alt="Размер шрифта 2">
<img src="skin/standart/picts/3.gif" onclick="InsertTags('[size=3]','[/size]')" alt="Размер шрифта 3">
<img src="skin/standart/picts/4.gif" onclick="InsertTags('[size=4]','[/size]')" alt="Размер шрифта 4">
<img src="skin/standart/picts/5.gif" onclick="InsertTags('[size=5]','[/size]')" alt="Размер шрифта 5">
&nbsp;
<img src="skin/standart/picts/img.gif" onclick="InsertTags('[img]','[/img]')" alt="Вставить картинку">
<img src="skin/standart/picts/quote.gif" onclick="InsertTags('[quote]','[/quote]')" alt="Добавить рамку">
<br>
<SELECT style='margin-top:1px; font:11px verdana; border: solid 1px black;' name=fcolor  onchange="javascript: InsertTags('[color=' + document.post.fcolor.options[document.post.fcolor.selectedIndex].value + ']', '[/color]'); document.post.fcolor.selectedIndex=0">
   <OPTION style='color:black' value='black'>Black</OPTION>
   <OPTION style='color:red' value='red'>Red</OPTION>
   <OPTION style='color:green' value='green'>Green</OPTION>
   <OPTION style='color:blue' value='blue'>Blue</OPTION>
   <OPTION style='color:yellow' value='yellow'>Yellow</OPTION>
   <OPTION style='color:purple' value='purple'>Purple</OPTION>
   <OPTION style='color:orange' value='orange'>Orange</OPTION>
   <OPTION style='color:teal' value='teal'>Teal</OPTION>
   <OPTION style='color:brown' value='brown'>Brown</OPTION>
   <OPTION style='color:gray' value='gray'>Gray</OPTION>
</SELECT>
