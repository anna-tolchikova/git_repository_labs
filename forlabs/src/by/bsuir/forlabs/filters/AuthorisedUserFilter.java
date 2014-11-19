package by.bsuir.forlabs.filters;

import by.bsuir.forlabs.resourcesmanagers.RoutingManager;
import by.bsuir.forlabs.subjects.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * map on /site/*
 * called after EncodingFilter
 * while some page is requested, checks if user is authorised or requests login page or method='post'
 * and allow access or send him to login page
 */
public class AuthorisedUserFilter implements Filter {

    private static Logger log = Logger.getLogger(AuthorisedUserFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.info("works");
        User user;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse  response = (HttpServletResponse)servletResponse;
        String  page = null;
        if((user =  (User)request.getSession().getAttribute("user")) != null
                || RoutingManager.getProperty("path.page.login").equals(request.getRequestURI())
                || request.getPathInfo() == null) {
//            log.info("user is authorised or requests login page or method='POST'");
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            request.getSession().setAttribute("notAuthorised", 1);
            response.sendRedirect(RoutingManager.getProperty("path.page.login"));
        }
    }

    @Override
    public void destroy() {

    }
}
