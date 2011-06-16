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
package ua.com.diletant.forum.db.entity;

import static ua.com.diletant.forum.tool.PHP.*;
import java.sql.*;

import ua.com.diletant.forum.exception.InvalidKeyException;
import ua.com.diletant.forum.tool.*;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class Thread{

   /**
    * Локализация
    */
   private LocaleString locale;

   /**
    * Тип прикрепления
    */
   private int str_dock;

   /**
    * Заголовок
    */
   private String str_head;

   /**
    * Автор
    */
   private String str_nick;

   /**
    * Время открытия
    */
   private String str_reg;

   /**
    * id ветки
    */
   private String str_id;

   /**
    * время последнего поста
    */
   private String str_lpt;

   /**
    * id Автора последнего поста
    */
   private String str_lpus;

   /**
    * Ник автора последнего поста
    */
   private String str_lpn;

   /**
    * Количество постов в ветке
    */
   private int str_pcount;

   /**
    * Количество просмотров участников
    */
   private String str_snid;

   /**
    * Количество просмотров полное
    */
   private String str_snall;

   /**
    * Id последнего поста в ветке
    */
   private Long idLastPost;

   /**
    * Тип ветки
    */
   private int str_type;

   /**
    * Папка
    */
   private String str_folder;

   /**
    * Дизайн
    */
   private int disain;

   /**
    * Авторизван ли посетитель
    */
   private boolean login;

   /**
    * Количество постов на странице
    */
   private int pt;

   /**
    * Текущая страница
    */
   private int pg;

   /**
    * номер позиции
    */
   private int i;

   /**
    * Текущий пользователь
    */
   private User currentUser = null;

   /**
    * Возвращает авторизван ли посетитель
    *
    * @return unknown
    */
   private boolean isLogin(){
      return this.login;
   }


   /**
    * Конструктор
    *
    * @param unknown_type $arrFetch
    * @param LocaleString $locale
    * @throws SQLException 
    */
   public Thread(ResultSet arrFetch, LocaleString locale, int disain, boolean isLogin, int pg, int pt, int i, User currentUser) throws SQLException{
      // Присваиваем локализованные сообщения
      this.locale = locale;
      //
      this.str_dock = arrFetch.getInt("dock");
      //
      this.str_head = arrFetch.getString("head");
      //
      this.str_nick = arrFetch.getString("nick");
      //
      this.str_reg = arrFetch.getString("reg_");
      //
      this.str_id = arrFetch.getString("id");
      //
      this.str_lpt = arrFetch.getString("lposttime_");
      //
      this.str_lpus = arrFetch.getString("lpostuser");
      //
      this.str_lpn = arrFetch.getString("lpostnick");
      //
      this.str_pcount = arrFetch.getInt("npost")-1;
      //
      this.str_snid = arrFetch.getString("seenid");
      //
      this.str_snall = arrFetch.getString("seenall");
      //
      this.idLastPost = arrFetch.getLong("id_last_post");
      //
      this.str_type = arrFetch.getInt("type");
      //
      this.str_folder = arrFetch.getString("_flname");
      //
      this.disain = disain;
      //
      this.pg = pg;
      //
      this.pt = pt;
      //
      this.login = isLogin;
      //
      this.i = i;
   }

   public String toString(){
      String result = "";
      if (this.disain == 1) { 
         result += "<tr class=trees >";
      }else {
         result += "<tr class=matras>";   
      }
      // Картинки
      // Пиктограммка опроса
      result += "<td width='10' align='center' style='padding:0px 5px 0px 5px'>";
      if (this.str_type == 1 || this.str_type == 2){
         result += "<img border='0' src='smiles/quest.gif'>";
      }else{
         if (this.str_dock==5){
            result += "<img border='0' src='smiles/icon4.gif'>";
         }else if(this.str_dock==10) {
            result+="<img border='0' src='picts/f_pinned.gif'>";
         }else {
            result+="<img border='0' src='smiles/icon1.gif'>";
         }
      }
      result+="</td>";
      result+="<td width='1'></td>";
      // Тема
      result+="<td><p>";
      String str_head = htmlspecialchars(stripslashes(this.str_head));
      // Добавляем смайлики
      str_head = Diletant.fd_head(str_head);
      // Опрос? Добавляем "метку"
      try {
         if (this.str_type==1 || this.str_type==2){
            str_head="<b>" +this.locale.getString("mess9")+ "</b> " +str_head;
         }
         // Подписываем прикрепленные
         switch (this.str_dock){
         case 10:
            result+="<font class=trforum><b>" +this.locale.getString("mess7")+ " </b><a href='tema.php?id=" +this.str_id+ "'>" +str_head+ "</a></font>";
            break;
         case 5:
            result+="<font class=trforum><b>" +this.locale.getString("mess8")+ " </b><a href='tema.php?id=" +this.str_id+ "'>" +str_head+ "</a></font>";
            break;
         case 3:
            result+="<font class=trforum><b>" +this.locale.getString("mess163")+ " </b><a href='tema.php?id=" +this.str_id+ "'>" +str_head+ "</a></font>";
            break;
         case 0:
            result+="<font class=trforum><a href='tema.php?id=" +this.str_id+ "'>" +str_head+ "</a></font>";
            break;
         }
         // Cсылки на страницы в ветке
         if (this.str_pcount+1>this.pt) {
            result+="<br><font size=1>" +this.locale.getString("mess10")+ ":&nbsp";
            int k1=0;
            int k2=0;
            for (int k=1; k<=ceil((this.str_pcount+1)/this.pt); k++) {
               k1=k1+1;
               if (k1==10){
                  result+="<a href='tema.php?page=" +k+ "&id=" +this.str_id+ "'>" +k+ "</a>";
                  if (k != ceil((this.str_pcount+1)/this.pt)) result+=",&nbsp;&nbsp;";
                  k1=0;
                  k2=k2+1;
               }
               if (k==1){
                  result+="<a href='tema.php?page=" +k+ "&id=" +this.str_id+ "'>" +k+ "</a>,&nbsp;&nbsp;";
               }
               if ((ceil((this.str_pcount+1)/this.pt)-k2*10)< 10 && (k-k2*10) != 0 && k!=1){
                  result+="<a href='tema.php?page=" +k+ "&id=" +this.str_id+ "'>" +k+ "</a>";
                  if (k != ceil((this.str_pcount+1)/this.pt)) result+=",&nbsp;&nbsp;";
               }

            }
            result+="</font>";
         }
         result+="</p></td>";
         // Количество постов
         result+="<td width='20' align='center' valign='middle'><span class='mnuforum' style='{color: purple}'>" +this.str_pcount;
         result+="</span><span id='posts" +this.str_id+ "' class='mnuforum' style='{color: red}'>&nbsp</span></td>";
         // кол-во просмотров
         result+="<td width='80' align='center' valign='middle'>";
         // Количество просмотров участников
         result+="<div class='mnuforum'><font size='1' color='green'>" + this.str_snid + "</font><br>";
         // Количество просмотров всего
         result+="<font size='1' color='purple'>" + this.str_snall + "</font></div></td>";
         // Автор
         result+="<td width='120' align='center' valign='middle'><div class='trforum'><font size='1'>" +htmlspecialchars(this.str_nick)+ "</font></div>";
         // Время создания
         result+="</td>";
         // Автор последнего поста
         result+="<td width='120' align=center><div class='mnuforum'><font size='1'>" +htmlspecialchars(this.str_lpn)+ "</font></div>";
         // Время последнего поста
         result+="<div class='mnuforum'><a href='tema.php?id=" + this.str_id + "&end=1#end' rel='nofollow'><font size='1'>" + substr(this.str_lpt, 0, 5) + "&nbsp;" + substr(this.str_lpt, 6, 5) + "</font></a></div>";
         result+="</td>";
         // Папка
         result+="<td align='center' valign='middle'>";
         result+="<div class='mnuforum'><font size='1'>" +this.str_folder+ "</font></div>";
         result+="</td>";
         // Флажок (только для зарегистрированых)
         if (this.isLogin()){
            result+="<td align='center' valign='middle'>";
            result+="<input type='checkbox' id='ch" +this.i+ "' name='" +this.i+ "' value='" +this.str_id+ "'>";
            result+="</td>";
            result+="<td style='padding:0px 5px 0px 5px' align='right'>";
            result+="<a href='delone.php?id=" +this.str_id+ "&usr=" +String.valueOf(currentUser.getId())+ "&page=" +this.pg+ "'><img border='0' src='picts/del1.gif'></a>";
            result+="</td>";
         }
      } catch (InvalidKeyException e) {
         // TODO Доделать!
         e.printStackTrace();
      }
      return result;
   }

}
