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
package org.forumj.web.servlet.post;

import static org.forumj.tool.Diletant.*;
import static org.forumj.tool.FJServletTools.*;
import static org.forumj.tool.PHP.*;
import static org.forumj.web.servlet.tool.FJServletTools.*;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.forumj.db.entity.User;
import org.forumj.tool.Time;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebServlet(urlPatterns = {"/new.php"}, name="new")
public class New extends HttpServlet {

   private static final long serialVersionUID = -1256025984434277731L;
   
   /**
    * {@inheritDoc}
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //
      String $n0=request.getParameter("IDU");
      String password = request.getParameter("PS1");
      String head = request.getParameter("NHEAD");
      String body = request.getParameter("A2");
      boolean firstPassword = true;
      if (password == null){
         password = request.getParameter("PS2");
         firstPassword = false;
      }
      User user = fd_guard(Long.valueOf($n0), password, firstPassword);
      if (user != null && !user.isBanned()){
      // Все нормально
      // Может пустая??
         if (!("".equals(head.trim()) || "".equals(body.trim()))) {
      // Не пустая
            /*Просмотр?*/
            Time $threadTime = new Time(new Date().getTime());
            String $rgtime = $threadTime.toString("Y-m-d H:i:s");
            $str_ip=$_SERVER['REMOTE_ADDR'];
            $str_dom=gethostbyaddr($str_ip);
            if (isset($_POST['comand']) && $_POST['comand']=="view"){
               include("new_view.php");
            }else{
               include("new_write.php");
            }
         }else{
            // Пустая
            echo "<html>";
            echo "<head>";
            echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
            echo "<meta http-equiv='Refresh' content='5; url=index.php'>";
         // Стили
            echo "<title>";
            echo "Мы не во всем Дилетанты!";
            echo "</title>";
            echo "</head>";
         // Цвет фона страницы
            echo "<body bgcolor=#EFEFEF>";
            echo "<font size='5'><b>Шо, думаешь написано Дилетант, так тут все просто? Писать надо не только пробелами!</b></font>";
            echo "</body>";
            echo "</html>";
            mysql_close($conn);
         }
      }else{
      // Вошли незарегистрировавшись
         echo "<html>";
         echo "<head>";
         echo "<meta http-equiv='content-type' content='text/html; charset=windows-1251'>";
         echo "<meta http-equiv='Refresh' content='5; url=auth.php?id=4.php'>";
      // Стили
         echo "<title>";
         echo "Мы не во всем Дилетанты!";
         echo "</title>";
         echo "</head>";
      // Цвет фона страницы
         echo "<body bgcolor=#EFEFEF>";
         echo "<font size='5'><b>Входить надо как все нормальные люди!</b></font>";
         echo "</body>";
         echo "</html>";
         mysql_close($conn);
      }   }

}
