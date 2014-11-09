<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script language='javascript' type='text/javascript'>// <!-- 
var FORUM_PAGES=2;
// --></script>
<div id='interfaces'>
    <div>
        <table class='control'>
            <tr>
                <td class='leftTop'></td>
                <td class='top' colspan='3'></td>
                <td class='rightTop'></td>
            </tr>
            <tr class='heads'>
                <td class='left'></td>
                <td class='bg2' align='left'><span class='mnuforum'>Поточний інтерфейс:</span><span class='nik'>Все вместе, кроме корзины</span></td>
                <td class='bg2' align='right'><form method='post' name='view_form' action='slctview' class='frmsmall'>
                        <span class='mnuforum'>Переключити інтерфейс на:</span><select class='mnuforumSm' size='1pt' name='VIEW'><option selected class='mnuprof' value='1'>Форум</option>
                            <option class='mnuprof' value='2'>Избранное</option>
                            <option class='mnuprof' value='3'>Все вместе, кроме корзины</option>
                            <option class='mnuprof' value='4'>Все вместе</option>
                            <option class='mnuprof' value='5'>Корзина</option></select>
                    </form></td>
                <td class='bg2' align='right'><table id='view_ok_table' class='bttn1' onclick='document.view_form.submit();'>
                        <tr>
                            <td class='bttn1LeftTop'></td>
                            <td class='bttn1Top'></td>
                            <td class='bttn1RightTop'></td>
                        </tr>
                        <tr>
                            <td class='bttn1Left'></td>
                            <td class='bttn1Bg'>OK</td>
                            <td class='bttn1Right'></td>
                        </tr>
                        <tr>
                            <td class='bttn1LeftBtm'></td>
                            <td class='bttn1Btm'></td>
                            <td class='bttn1RightBtm'></td>
                        </tr>
                    </table></td>
                <td class='right'></td>
            </tr>
            <tr>
                <td class='leftBtm'></td>
                <td class='btm' colspan='3'></td>
                <td class='rightBtm'></td>
            </tr>
        </table>
    </div>
    <div>
        <div>
            <table width='100%'>
                <tr>
                    <td colspan='3'><p>
                            <font face='Arial' color='red' size='3'><span style='text-decoration: none'><b>УВАГА!!! Неавторизованим відвідувачам недоступні всі переваги інтерфейсу форуму!<br>Зареєструйтеся!
                                </b></span></font>
                        </p></td>
                    <td style='text-align: right;'><span class='posthead'>Сторінка сформована за<br> 0,386 сек
                    </span></td>
                </tr>
                <tr>
                    <td style='padding: 2px'><font class='page'><b>Сторінка:&nbsp;</b></font><font class='pagecurrent'><b>1</b></font><font class='page' style='margin-left: 5px;'><b>Загалом сторінок:&nbsp;1</b></font></td>
                    <td align='right'><form name='str' method='get' class='frmsmall' action='index'>
                            <font class='page' style='margin-right: 4px;'><b>Перейти до сторінки:</b></font><input class='mnuforumSm' style='padding: 2px' type="text" size='5' name='page'>
                        </form></td>
                    <td><table id='page_ok_table' class='bttn1' onclick='document.str.submit();'>
                            <tr>
                                <td class='bttn1LeftTop'></td>
                                <td class='bttn1Top'></td>
                                <td class='bttn1RightTop'></td>
                            </tr>
                            <tr>
                                <td class='bttn1Left'></td>
                                <td class='bttn1Bg'>OK</td>
                                <td class='bttn1Right'></td>
                            </tr>
                            <tr>
                                <td class='bttn1LeftBtm'></td>
                                <td class='bttn1Btm'></td>
                                <td class='bttn1RightBtm'></td>
                            </tr>
                        </table></td>
                    <td style='text-align: right;'><span class='posthead'>Нових тем:&nbsp;</span><span class='posthead' id='indicatorb' style='color: red'>&nbsp;</span><br />
                    <span class='posthead'>Нових повідомлень:&nbsp;</span><span class='posthead' id='indicatort' style='color: red'>&nbsp;</span></td>
                </tr>
            </table>
        </div>
        <div>
            <div style='height: 400px'>
                <table class='content'>
                    <tr>
                        <td class='internal' align='left' colspan='3'><span class='hdforum2'>Тема: </span></td>
                        <td class='internal' align='center'><span class='hdforum2'>Відп.</span></td>
                        <td class='internal' align='center'><span class='hdforum2'>Перегл.</span></td>
                        <td class='internal' align='center'><span class='hdforum2'>Запропонована</span></td>
                        <td class='internal' align='center'><span class='hdforum2'>Останнє</span></td>
                        <td class='internal' align='center'><span class='hdforum2'>Тека</span></td>
                    </tr>
                    <tr class='matras'>
                        <td width='10' align='center' style='padding: 0px 5px 0px 5px'><img border='0' src='smiles/icon4.gif'></td>
                        <td width='1'></td>
                        <td><p>
                                <font class='trforum'><b>Об'ява. </b><a href='tema?id=3' onclick='threadClick(3);return false;'>dsdsd</a></font>
                            </p></td>
                        <td width='20' align='center' valign='middle'><span class='mnuforum' style='color: purple'>1</span><span id='posts3' class='mnuforum' style='color: red'>&nbsp</span></td>
                        <td width='80' align='center' valign='middle'><div class='mnuforum'>
                                <font size='1pt' color='green'>3</font><br>
                                <font size='1pt' color='purple'>4</font>
                            </div></td>
                        <td width='120' align='center' valign='middle'><div class='trforum'>
                                <font size='1pt'>Дилетант</font>
                            </div></td>
                        <td width='120' align=center><div class='mnuforum'>
                                <font size='1pt'>Дилетант</font>
                            </div>
                            <div class='mnuforum'>
                                <a href='tema?id=3&end=1#end' rel='nofollow'><font size='1pt'>09.01.13 17:54</font></a>
                            </div></td>
                        <td align='center' valign='middle'><div class='mnuforum'>
                                <font size='1pt'>Форум</font>
                            </div></td>
                    </tr>
                </table>
            </div>
            <table border='0' style='border-collapse: collapse' width='100%'>
                <tr>
                    <td colspan='4' style='padding: 2px'><font class='page'><b>Сторінка:&nbsp;</b></font><font class='pagecurrent'><b>1</b></font><font class='page' style='margin-left: 5px;'><b>Загалом сторінок:&nbsp;1</b></font></td>
                </tr>
            </table>
        </div>
    </div>
</div>