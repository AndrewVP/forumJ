/*
 * Copyright Andrew V. Pogrebnyak
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ua.com.diletant.forum.web.servlet;

import static ua.com.diletant.forum.tool.PHP.*;
import static ua.com.diletant.forum.tool.Diletant.*;

import java.io.*;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ua.com.diletant.forum.tool.LocaleString;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebServlet("/index.php")
public class Index extends HttpServlet {

   private static final long serialVersionUID = 1828936989822948738L;

   /**
    * {@inheritDoc}
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      HttpSession session = request.getSession();
      String $defLang = (String) session.getAttribute("lang");
      if ($defLang == null){
         $defLang = "ua"; 
      }
      int xx
      //Предотвращаем кеширование
      cache(response);
      // Функции   
      // номер страницы
      String $pg = request.getParameter("page");
      if ($pg == null){
         $pg = "1";
      }
      // Загружаем локализацию
      LocaleString $locale = new LocaleString($defLang, null, "ua");
      String where="index.php?page=" + $pg + "&lang=" + $defLang;
      // Авторизован?
      // Соединяемся с БД
      // Подхватываем куку
      String $location="Location: index.php";
      include("cookie.php");
      // нажато "выйти"
      include("exit.php");
      if (isset($_SESSION['idu'])){
         $_idu=$_SESSION['idu'];
      }
      else {
      // Если не авторизован - интерфейс модератора
         $_idu=95;
      }
      $connection = new ConnectionMySQL($host, $user, $pass, $data, 1, "index");
      $dao = new IndexDao($connection, $_idu);  
      // Собираем статистику
      $action=1;
      include("stat.php");
      echo "<!doctype html public \"-//W3C//DTD HTML 4.01 Transitional//EN\">\"";
      echo "<html>";
      echo "<head>";
      echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
      // Стили
      include('style.php');
      // Скрипты (флажки)
      include('jsmain_chek.php');
      $m_xb = $dao->getMaxPostId();
      $m_xt = $dao->getMaxThreadId();
      echo "<script language='javascript' type='text/javascript'>";
      echo "// <!-- \n";
      echo "var m_xb=".$m_xb.";";
      echo "var m_xt=".$m_xt.";";
      echo "// -->";
      echo "</script>";
      include('js/indicator.php');
      // Скрипты (submit формы интерфейсов)
      include('jsview_ok.php');
      echo '<link rel="icon" href="/favicon.ico" type="image/x-icon">';
      echo '<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">';
      echo "<title>";
      echo $locale->getString("MSG_MAIN_TITLE");
      echo "</title>";
      echo "</head>";
      // Цвет фона страницы
      echo "<body class='mainBodyBG'>";
      // Снег
//      include("js/snow.php");
      // Главная таблица
      echo "<table border='0' id=t1 style='border-collapse: collapse' width='100%'>";
      echo "<tr><td>";
      echo "<table border='0' style='border-collapse: collapse' width='100%'>";
      // Таблица с лого и верхним баннером
      include("logo.php");
      // соединяемся и определяем кол-во страниц
      $nfirstpost=($pg-1)*$_SESSION['pp'];
      $limit=$pg*$_SESSION['pp'];
      // Интерфейс по умолчанию
      if (!isset($_SESSION['view'])) $_SESSION['view']=$_SESSION['def_view'];
//      if (!isset($_SESSION['idu'])) $_SESSION['view']=$_SESSION['def_view'];
      $threads = $dao->getThreads($_SESSION['view'], $_SESSION['pp'], $nfirstpost, $locale, isset($_SESSION['idu']), $pg, $_SESSION['pt']);
      $count = $dao->getThreadCount();
      // кол-во страниц с заголовками
      $cou_p=ceil($count/$_SESSION['pp'])+1;
      // Проверяем наличие почты
      $str_nmail="";
      if (isset($_SESSION['idu'])) {
         $mailCount = $dao->getNewMailCount($_SESSION['idu']);
         if ($mailCount > 0) $str_nmail="<a class=hdforum href='control.php?id=2' rel='nofollow'><font color=red>".$locale->getString("mess66")." ".$mailCount." ".$locale->getString("mess67")."</font></a>";
      }
      // Таблица главных ссылок
      echo "<tr>";
      echo "<td width='100%'>";
      echo "<table id='t2' width='100%'>";
      /*Главное меню*/
      include("menu.php");
      // Интерфейс
      // Имя текущего
      if (!isset($_SESSION['vname'])){
         $_SESSION['vname'] = $dao->getCurrentViewName($_SESSION['view']);
      }
      $views = $dao->getViewsArray($_idu);
      echo "<tr><td>";
   }
   
   private void cache(HttpServletResponse response){
      response.setHeader("Expires", "Mon, 26 Jul 1991 05:00:00 GMT");
      response.setHeader("Last-Modified", new Date().toString());
      response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
      response.setHeader("Cache-Control", "post-check=0, pre-check=0");
      response.setHeader("Pragma", "no-cache");
   }
}
