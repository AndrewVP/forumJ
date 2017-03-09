/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.web.servlet.post;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.forumj.common.FJServletName;
import org.forumj.common.FJUrl;
import org.forumj.common.HttpParameters;
import org.forumj.common.config.FJConfiguration;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.entity.Image;
import org.forumj.common.db.entity.ImageType;
import org.forumj.common.db.service.FJServiceHolder;
import org.forumj.common.db.service.ImageService;
import org.forumj.image.ImageTools;
import org.forumj.web.servlet.FJServlet;
import org.forumj.web.servlet.tool.ResourcesCache;
import org.forumj.web.tool.ErrorCode;
import org.forumj.web.tool.ValidationErrors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

import static org.forumj.tool.Diletant.*;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.POST_IMAGE}, name=FJServletName.POST_IMAGE)
public class PostImage extends FJServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		StringBuffer buffer = new StringBuffer();
		try {
			ValidationErrors validationErrors = (ValidationErrors) request.getAttribute(ValidationErrors.class.getName());
			if (validationErrors.isHasErrors()){
				List<ErrorCode> errors = validationErrors.getErrors();
				StringBuffer errCodes = new StringBuffer();
				for (ErrorCode errorCode : errors) {
					if (errCodes.length() == 0){
						errCodes.append("&amp;");
						errCodes.append(HttpParameters.ERROR_CODE);
						errCodes.append("=");
					}else{
						errCodes.append(",");
					}
					errCodes.append(errorCode.getErrorCode());
				}
				//TODO Magic integer!
				buffer.append(successPostOut("0", FJUrl.SETTINGS + "?id=16" + errCodes.toString()));
			}else{
				IUser user = (IUser) session.getAttribute(HttpParameters.USER);
				if (user != null && !user.isBanned() && user.isLogined()){
					FileItem imageFile = (FileItem) request.getAttribute(HttpParameters.AVATAR);
					String album = request.getParameter(HttpParameters.ALBUM);
					ImageService imageService = FJServiceHolder.getImageService();
					Long albumId = album == null ? 0 : Long.valueOf(album);
					imageService.create((DiskFileItem) imageFile, user, albumId, ImageType.ORIGINAL);
					//TODO Magic integer!
					buffer.append(successPostOut("0", FJUrl.SETTINGS + "?id=16"));
				}else{
					// Вошли незарегистрировавшись
					buffer.append(unRegisteredPostOut());
				}
			}
		} catch (Throwable e) {
			buffer = new StringBuffer();
			buffer.append(errorOut(e));
			e.printStackTrace();
		}
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(buffer.toString());
	}

}
