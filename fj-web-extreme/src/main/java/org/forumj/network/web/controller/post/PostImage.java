/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.network.web.controller.post;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.forumj.network.web.FJUrl;
import org.forumj.network.web.HttpParameters;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.entity.ImageType;
import org.forumj.common.db.service.FJServiceHolder;
import org.forumj.common.db.service.ImageService;
import org.forumj.network.web.controller.validator.ErrorCode;
import org.forumj.network.web.controller.validator.ValidationErrors;
import org.forumj.network.web.FJServletTools;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class PostImage{

	public void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws Exception{
		HttpSession session = request.getSession();
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
			StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.SETTINGS).append("?id=16").append(errCodes);
			response.sendRedirect(url.toString());
		}else{
			IUser user = (IUser) session.getAttribute(HttpParameters.USER);
			if (user != null && !user.isBanned() && user.isLogined()){
				FileItem imageFile = (FileItem) request.getAttribute(HttpParameters.AVATAR);
				String album = request.getParameter(HttpParameters.ALBUM);
				ImageService imageService = FJServiceHolder.getImageService();
				Long albumId = album == null ? 0 : Long.valueOf(album);
				imageService.create((DiskFileItem) imageFile, user, albumId, ImageType.ORIGINAL);
				//TODO Magic integer!
				StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.SETTINGS).append("?id=16");
				response.sendRedirect(url.toString());
			}else{
				// Session expired
				StringBuilder exit = new StringBuilder("/").append(userURI).append("/").append(FJUrl.INDEX);
				response.sendRedirect(exit.toString());
			}
		}
	}

}
