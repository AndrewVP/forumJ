/**
 * 
 */
package org.forumj.web.filter;

import static org.forumj.common.FJServletName.*;
import static org.forumj.tool.Diletant.*;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.forumj.common.config.FJConfiguration;
import org.forumj.web.request.RequestWrapper;

/**
 * @author Andrew V. Pogrebnyak
 *
 */
@WebFilter(servletNames={SET_AVATAR})
public class AAAAABFileUploadFilter implements Filter{

    private int maxUploadMemorySize = 10000;

    private File uploadTmpdir = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try{
            maxUploadMemorySize = FJConfiguration.getConfig().getInt("maxUploadMemorySize");
            String uploadTmpdirPath = FJConfiguration.getConfig().getString("uploadTmpdir");
            uploadTmpdir = new File(uploadTmpdirPath);
            if (!uploadTmpdir.exists()){
                uploadTmpdir.mkdirs();
            }
        }catch (ConfigurationException e){
            throw new ServletException(e);
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(true);
        try {
            if(ServletFileUpload.isMultipartContent(request)){
                // Create a factory for disk-based file items
                FileItemFactory factory = new DiskFileItemFactory(maxUploadMemorySize, uploadTmpdir);
                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);
                // Parse the request
                @SuppressWarnings("unchecked")
                List<FileItem> items = upload.parseRequest(request);
                RequestWrapper wrappedRequest = new RequestWrapper(request);
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        processFormField(item, wrappedRequest);
                    } else {
                        processUploadedFile(item, wrappedRequest);
                    }
                }            
                chain.doFilter(wrappedRequest, resp);
            }else{
                response.sendRedirect(request.getContextPath() + "/");
            }
        } catch (Throwable e) {
            e.printStackTrace();
            StringBuffer buffer = new StringBuffer();
            buffer.append(errorOut(e));
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            String out = buffer.toString();
            writer.write(out);
        }
    }

    /**
     * @param item
     */
    private void processUploadedFile(FileItem item, RequestWrapper request){
        //TODO Name not valid with specification
        request.setAttribute("avatar", item);
    }

    /**
     * @param item
     */
    private void processFormField(FileItem item, RequestWrapper request) {
        String name = item.getFieldName();
        String value = item.getString();
        request.addOrReplaceParameter(name, value);
    }

    @Override
    public void destroy() {}

}
