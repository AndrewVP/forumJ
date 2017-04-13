package org.forumj.network.web.filter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Andrew on 13/04/2017.
 */
public interface FilterChain {
    void doFilter(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws Exception;
}
