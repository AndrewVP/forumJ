/**
 * 
 */
package org.forumj.network.web.filter;

import static org.forumj.common.FJServletName.*;
import static org.forumj.tool.Diletant.errorOut;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import net.sf.jmimemagic.Magic;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.forumj.common.config.FJConfiguration;
import org.forumj.web.request.RequestWrapper;
import org.forumj.web.tool.*;

/**
 * @author Andrew V. Pogrebnyak
 *
 */
public class FileUploadFilter{

    private int maxUploadMemorySize = 10000;

    private int maxUploadRequestSize = 100000;

    private File uploadTmpdir = null;

    public void init() throws Exception{
        maxUploadMemorySize = FJConfiguration.getConfig().getInt("maxUploadMemorySize");
        maxUploadRequestSize = FJConfiguration.getConfig().getInt("maxUploadRequestSize");
        String uploadTmpdirPath = FJConfiguration.getConfig().getString("uploadTmpdir");
        uploadTmpdir = new File(uploadTmpdirPath);
        if (!uploadTmpdir.exists()){
            uploadTmpdir.mkdirs();
        }
    }

    public void doFilter(ServletRequest req, ServletResponse resp, String webapp, String userURI, FilterChain chain) throws Exception{
        req.setCharacterEncoding("UTF-8");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        ValidationErrors errors;
        if (request.getAttribute(ValidationErrors.class.getName()) != null){
        	errors = (ValidationErrors) request.getAttribute(ValidationErrors.class.getName());
        }else{
        	errors = new ValidationErrors();
        }
        request.setAttribute(ValidationErrors.class.getName(), errors);
        RequestWrapper wrappedRequest = new RequestWrapper(request);
        try {
            if(ServletFileUpload.isMultipartContent(request)){
                // Create a factory for disk-based file items
                FileItemFactory factory = new DiskFileItemFactory(maxUploadMemorySize, uploadTmpdir);
                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax(maxUploadRequestSize);
                // Parse the request
                @SuppressWarnings("unchecked")
                List<FileItem> items = upload.parseRequest(request);
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        processFormField(item, wrappedRequest);
                    } else {
                        if(!processUploadedFile(item, wrappedRequest)){
                        	errors.addError(ErrorCode.FILE_IS_NOT_IMAGE);
                        }
                    }
                }            
                chain.doFilter(wrappedRequest, response, webapp, userURI);
            }else{
                response.sendRedirect(request.getContextPath() + "/");
            }
        } catch (SizeLimitExceededException e) {
        	errors.addError(ErrorCode.REQUEST_IS_TO_BIG);
            chain.doFilter(wrappedRequest, response, webapp, userURI);
        }
    }

    /**
     * @param item
     */
    private boolean processUploadedFile(FileItem item, RequestWrapper request){
    	boolean isImage = true;
    	DiskFileItem fileItem = (DiskFileItem)item;
    	String mimeType = null;
		try {
			mimeType = Magic.getMagicMatch(item.get(), false).getMimeType();
		} catch (Exception e) {
			isImage = false;
		}
    	if (mimeType == null || !mimeType.startsWith("image")) {
    		isImage = false;
    	}   
    	if(isImage){
    		//TODO Name not valid with specification
    		request.setAttribute("avatar", item);
    	}else{
    		fileItem.delete();
    	}
    	return isImage;
    }

    /**
     * @param item
     */
    private void processFormField(FileItem item, RequestWrapper request) {
        String name = item.getFieldName();
        String value = item.getString();
        request.addOrReplaceParameter(name, value);
    }
}
