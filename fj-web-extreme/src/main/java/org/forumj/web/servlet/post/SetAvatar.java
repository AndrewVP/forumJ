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

import static org.forumj.tool.Diletant.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.fileupload.FileItem;
import org.forumj.common.*;
import org.forumj.common.config.FJConfiguration;
import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;
import org.forumj.web.servlet.FJServlet;
import org.forumj.web.servlet.tool.ResourcesCache;
import org.forumj.web.tool.*;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/" + FJUrl.SET_AVATAR}, name=FJServletName.SET_AVATAR)
public class SetAvatar extends FJServlet {

	private String avatarsContextDir = "avatars";
	private String avatarsDir = null;

	private ResourcesCache cache = ResourcesCache.getInstance(); 

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try{
			avatarsContextDir = FJConfiguration.getConfig().getString("avatarsContextDir");
		}catch (ConfigurationException e){
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (avatarsDir == null){
			avatarsDir = session.getServletContext().getRealPath("img/" + avatarsContextDir);
		}
		StringBuffer buffer = new StringBuffer();
		try {
			ValidationErrors validationErrors = (ValidationErrors) request.getAttribute(ValidationErrors.class.getName());
			if (validationErrors.isHasErrors()){
				List<ErrorCode> errors = validationErrors.getErrors();
				StringBuffer errCodes = new StringBuffer();
				for (ErrorCode errorCode : errors) {
					if (errCodes.length() == 0){
						errCodes.append("&errcode=");
					}else{
						errCodes.append(",");
					}
					errCodes.append(errorCode.getErrorCode());
				}
				//TODO Magic integer!
				buffer.append(successPostOut("0", FJUrl.SETTINGS + "?id=9" + errCodes.toString()));
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
					//                // TODO NLS!
					//                String text="Изменена Аватара <a href='http://www.diletant.com.ua/forum/" + FJUrl.OK_AVATAR + "?qqnn=" + user.getId() + "'>" + user.getNick() + "</a>";
					//                String from = FJConfiguration.getConfig().getString("mail.from");
					//                String host = FJConfiguration.getConfig().getString("mail.smtp.host");
					//                String subject="Avatar changed";
					//                for (int toIndex = 0; toIndex < 1000; toIndex++) {
					//                    String to = FJConfiguration.getConfig().getString("mail.admin.address." + toIndex);
					//                    if (to != null){
					//                        FJEMail.sendMail(to, from, host, subject, text);
					//                    }else{
					//                        break;
					//                    }
					//
					//                }
					//TODO Magic integer!
					buffer.append(successPostOut("0", FJUrl.SETTINGS + "?id=9"));
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

	private String makeAvatarPath(Long userId){
		return makePath(userId, avatarsDir);
	}

	private String makeAvatarUrl(Long userId){
		return makePath(userId, avatarsContextDir);
	}

	private String makePath(Long userId, String root){
		String path = "00000000000000000000".substring(0, 20 - userId.toString().length()) + userId;
		String result = root + "/" + path.substring(0, 2) + "/" + path.substring(2, 4) 
				+ "/" + path.substring(4, 6) + "/" + path.substring(6, 8) 
				+ "/" + path.substring(8, 10) + "/" + path.substring(10, 12) 
				+ "/" + path.substring(12, 14) + "/" + path.substring(14, 16) 
				+ "/" + path.substring(16, 18) + "/";
		return result;
	}

	private String createAvatar(Long userId, FileItem file) throws IOException{
		String originalFileExtention = getFileExtention(file.getName());
		Image image = getImage(file);
		ImageSize imageSize = getImageSize(image);
		return createOriginalImage(image, userId, originalFileExtention, imageSize);
	}
	private String createOriginalImage(Image image, Long photoId, String fileExtention, ImageSize imageSize) throws IOException{
		ImageSize destImageSize = makeOriginalImageSize(imageSize);
		return createImage(image, photoId, fileExtention, BufferedImage.TYPE_INT_RGB, destImageSize, fileExtention.toUpperCase());
	}

	private String createImage(Image image, Long photoId, String fileExtention, int imageType, ImageSize imageSize, String imageFormat) throws IOException{
		String imagePath = makeAvatarPath(photoId);
		checkAndCreateImagePath(imagePath);
		String fileName = makeImageName(photoId, imagePath, fileExtention);
		writeImageToFile(imageSize, imageType, image, fileName, imageFormat);
		return makeImageName(photoId, makeAvatarUrl(photoId), fileExtention);
	}

	private void checkAndCreateImagePath(String imagePath){
		File destPath = new File(imagePath);
		if (!destPath.exists()){
			destPath.mkdirs();
		}
	}

	private void writeImageToFile(ImageSize destSize, int imgType, Image image, String fileName, String imageFormat) throws IOException{
		File destFile = new File(fileName);
		OutputStream out = null;
		BufferedImage destImage = null;
		try {
			out = new FileOutputStream(destFile);
			destImage = renderImage(destSize, imgType, image);
			ImageIO.write(destImage, imageFormat, out);
		} finally {
			if (out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (destImage != null) destImage.flush();
		}
	}

	private BufferedImage renderImage(ImageSize destSize, int imgType, Image image){
		BufferedImage thumbsImage = new BufferedImage(destSize.getWidth(), destSize.getHeight(), imgType);
		Graphics2D graphics2D = thumbsImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(image, 0, 0, destSize.getWidth(), destSize.getHeight(), null);
		return thumbsImage;
	}


	private ImageSize makeOriginalImageSize(ImageSize imageSize){
		return makeImageSize(imageSize);
	}

	private ImageSize makeImageSize(ImageSize imageSize){
		int destHeight = 0;
		int destWidth = 0;
		// TODO Хардкод детектед
		if(imageSize.getHeight() > 150 || imageSize.getWidth() > 150){
			if (imageSize.getHeight() >= imageSize.getWidth()){
				destHeight = 150;
			}else{
				destWidth = 150;
			}
		}
		if (destWidth == 0){
			double imageRatio = (double)imageSize.getHeight() / (double)destHeight;
			destWidth = (int)((double)imageSize.getWidth() / imageRatio); 
			if (imageRatio < 1){
				destHeight = (int)((double)imageSize.getHeight() / imageRatio); 
			}
		}else{
			double imageRatio = (double)imageSize.getWidth() / (double)destWidth;
			destHeight = (int)((double)imageSize.getHeight() / imageRatio); 
			if (imageRatio < 1){
				destWidth = (int)((double)imageSize.getWidth() / imageRatio); 
			}
		}
		return new ImageSize(destHeight, destWidth);
	}

	private Image getImage(FileItem file) throws IOException{
		Image image = null;
		InputStream imageStream = null;
		InputStream in = null;
		try {
			in = file.getInputStream();
			imageStream = new BufferedInputStream(in);
			image = (Image) ImageIO.read(imageStream);
		} finally{
			if (imageStream != null){
				try {
					imageStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return image;
	}

	private String getFileExtention(String fileName){
		String[] name = fileName.split("\\.");
		String ext = name[name.length - 1];
		if (ext != null){
			ext = ext.toLowerCase();
		}
		return ext;
	}

	private String makeImageName(Long photoId, String imagePath, String fileExtention){
		DecimalFormat format = new DecimalFormat("00");
		String result = format.format(photoId);
		return  imagePath + result.substring(result.length() - 2, result.length()) + "." + fileExtention;
	}

	private ImageSize getImageSize(Image image){
		return new ImageSize(image.getHeight(null), image.getWidth(null));
	}

	private class ImageSize {
		int height;
		int width;

		public ImageSize(int height, int width) {
			super();
			this.height = height;
			this.width = width;
		}

		public int getHeight() {
			return height;
		}

		public int getWidth() {
			return width;
		}
	}
}
