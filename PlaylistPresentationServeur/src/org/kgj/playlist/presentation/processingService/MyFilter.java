package org.kgj.playlist.presentation.processingService;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * The purpose of this filter is to prevent users who are not logged in
 * from accessing confidential website areas. 
 */
public class MyFilter implements Filter {

    /**
     * @see MyFilter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    /**
     * @see MyFilter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("connected") == null || session.getAttribute("connected") != "0") {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }


    /**
     * @see MyFilter#destroy()
     */
    @Override
    public void destroy() {}

}