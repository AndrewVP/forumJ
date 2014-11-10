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
package org.forumj.web.requesthandler.handler;

import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.http.*;

import org.forumj.common.FJUrl;
import org.forumj.common.exception.FJWebException;
import org.forumj.tool.LocaleString;
import org.forumj.web.requesthandler.BaseHandler;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class GetLoginHandler extends BaseHandler{

    @Override
    protected void doHandle(AsyncContext context) throws FJWebException {
        try{
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            StringBuffer buffer = new StringBuffer();
            HttpSession session = request.getSession();
            String gid = request.getParameter("id");
            gid = (gid == null || "".equalsIgnoreCase(gid.trim())) ? "1" : gid;
            LocaleString locale = (LocaleString) session.getAttribute("locale");
            buffer.append("<form  action='" + FJUrl.DO_LOGIN + "' method='post'>");
            buffer.append("<table><tr><td><p>");
            // Определяем, откуда мы сюда попали?
            switch (Integer.valueOf(gid)){
               // Нажали на ссылку Вход
               case 1:
                  buffer.append("Авторизуйтесь, пожалуйста");
                  break;
               case 4:
                  buffer.append("<b>Авторизуйтесь, пожалуйста</b>");;
                  break;
                  // Попытались предложить тему незарегистрировавшись
               case 2:
                  buffer.append("Предлагать темы для обсуждения могут только зарегистрированные посетители!");
                  break;
                  // Попытались ответить незарегистрировавшись
               case 3:
                  buffer.append("Добавлять свое мнение могут только зарегистрированные посетители!");
                  break;
               case 5:
                  buffer.append("<b>Пардон, мы таких не знаем! :)</b>");;
                  break;
               case 6:
                  buffer.append("<b>Вы не угадали пароль! :)</b>");;
                  break;
               case 7:
                  buffer.append("<b>В связи с усложнением системы идентификации участников форума прошу вас пройти процедуру дооформления :) необходимо добавить еще один идентификатор, который вы введете только ОДИН раз в, дальнейшем это не позволит хацкерам подделать вашу куку.</b>");;
                  break;
               case 8:
                  buffer.append("В связи с усложнением системы идентификации участников форума прошу вас пройти процедуру дооформления :) необходимо добавить еще один идентификатор, который вы введете только ОДИН раз в, дальнейшем это не позволит хацкерам подделать вашу куку.<br> <b>Идентификаторы не совпадают</b>");;
                  break;
               case 9:
                  buffer.append("Проводить опросы могут только зарегистрированные пользователи");
                  break;
               case 10:
                  buffer.append("Ваш аккаунт не активирован");
                  break;
            }
            buffer.append("</p></td></tr>");
            // С любезностями закончили
            // Запрашиваем Ник
            buffer.append("<tr><td><table><tr><td>");
            buffer.append("Ник</td>");
            buffer.append("<td><input type='text' name='T1' size='20'></td>");
            buffer.append("</tr>");
            // Пароль
            buffer.append("<tr>");
            buffer.append("<td>Пароль</td>");
            buffer.append("<td><input type=password name='T2' size='20'>");
            buffer.append("</td>");
            buffer.append("</tr>");
            // Кнопки
            buffer.append("<tr>");
            buffer.append("<td>");
            buffer.append("<input type='submit' value='Отправить' name='B1'>");
            buffer.append("<input type='reset' value='Отменить' name='B2'>");
            buffer.append("</td></tr></table></td></tr></table></form>");
            // Форма закончилась
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(buffer.toString());
        }catch (Throwable e){
            throw new FJWebException(e);
        }
    }

}
