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

import static org.forumj.tool.Diletant.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.forumj.common.*;
import org.forumj.common.config.FJConfiguration;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;
import org.forumj.image.ImageSize;
import org.forumj.image.ImageTools;
import org.forumj.web.servlet.tool.ResourcesCache;
import org.forumj.web.tool.*;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class SetAvatar{

	private String avatarsContextDir = "avatars";
	private String avatarsDir = null;
	private int avatarMaxHeight=150;
	private int avatarMaxWidth=150;


	private ResourcesCache cache = ResourcesCache.getInstance(); 

	public void init(ServletConfig config) throws ServletException {
		try{
			avatarsContextDir = FJConfiguration.getConfig().getString(FJConfiguration.AVATARS_CONTEXT_DIR, avatarsContextDir);
			avatarsDir = FJConfiguration.getConfig().getString(FJConfiguration.HOME_DIR ) + File.separator + avatarsContextDir;
			avatarMaxHeight = FJConfiguration.getConfig().getInt(FJConfiguration.AVATAR_HEIGHT, avatarMaxHeight);
			avatarMaxWidth = FJConfiguration.getConfig().getInt(FJConfiguration.AVATAR_WIDTH, avatarMaxWidth);
		}catch (ConfigurationException e){
			throw new ServletException(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws ServletException, IOException {
		HttpSession session = request.getSession();
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
				StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.SETTINGS).append("?id=9").append(errCodes);
				response.sendRedirect(url.toString());
			}else{
				FileItem avatar = (FileItem) request.getAttribute("avatar");
				String sAvatarParameter = request.getParameter("s_avatar");
				boolean sAvatar = sAvatarParameter != null; 
				IUser user = (IUser) session.getAttribute("user");
				if (user != null && !user.isBanned() && user.isLogined()){
					String avatarUrl = createAvatar(user.getId(), avatar);
					user.setAvatar(avatarUrl);
					user.setAvatarApproved(true);
					user.setShowAvatar(sAvatar);
					UserService userService = FJServiceHolder.getUserService();
					userService.update(user);
					cache.remove("/" + avatarUrl);
					//TODO Magic integer!
					StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.SETTINGS).append("?id=9");
					response.sendRedirect(url.toString());
				}else{
					// Session expired
					StringBuilder exit = new StringBuilder("/").append(userURI).append("/").append(FJUrl.INDEX);
					response.sendRedirect(exit.toString());
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
			StringBuffer buffer = new StringBuffer();
			buffer.append(errorOut(e));
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().write(buffer.toString());
		}
	}

	private String makeAvatarPath(Long userId){
		return ImageTools.makePath(userId, avatarsDir);
	}

	private String makeAvatarUrl(Long userId){
		return ImageTools.makePath(userId, avatarsContextDir);
	}

	private String createAvatar(Long userId, FileItem file) throws Exception{
		String originalFileExtension = ImageTools.getFileExtension(file.getName());
		Image image = ImageTools.getImage(file);
		ImageSize imageSize = ImageTools.getImageSize(image);
		ImageSize destImageSize = fitImageToAvatarSize(imageSize);
		if (destImageSize.getHeight() == 0 && destImageSize.getWidth() == 0){
		    return moveUploadedImage(userId, originalFileExtension, file);
		}else{
		    return createOriginalImage(image, userId, originalFileExtension, destImageSize);
		}
	}

	private String createOriginalImage(Image image, Long photoId, String fileExtension, ImageSize imageSize) throws IOException{
		return createImage(image, photoId, fileExtension, BufferedImage.TYPE_INT_RGB, imageSize, fileExtension.toUpperCase());
	}
	
	private String moveUploadedImage(Long photoId, String fileExtension, FileItem file) throws Exception{
        String imagePath = makeAvatarPath(photoId);
		ImageTools.checkAndCreateImagePath(imagePath);
        String fileName = ImageTools.makeImageName(photoId, imagePath, fileExtension);
        File destFile = new File(fileName);
        DiskFileItem item = (DiskFileItem) file;
        item.write(destFile);
        return ImageTools.makeImageName(photoId, makeAvatarUrl(photoId), fileExtension);
	}

	private String createImage(Image image, Long photoId, String fileExtension, int imageType, ImageSize imageSize, String imageFormat) throws IOException{
		String imagePath = makeAvatarPath(photoId);
		ImageTools.checkAndCreateImagePath(imagePath);
		String fileName = ImageTools.makeImageName(photoId, imagePath, fileExtension);
		ImageTools.writeImageToFile(imageSize, imageType, image, fileName, imageFormat);
		return ImageTools.makeImageName(photoId, makeAvatarUrl(photoId), fileExtension);
	}

	private ImageSize fitImageToAvatarSize(ImageSize imageSize){
		return ImageTools.fitImageToSize(imageSize, avatarMaxWidth, avatarMaxHeight);
	}


}
