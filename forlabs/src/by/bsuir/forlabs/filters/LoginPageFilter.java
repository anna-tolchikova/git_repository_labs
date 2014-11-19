package by.bsuir.forlabs.filters;

import by.bsuir.forlabs.resourcesmanagers.RoutingManager;
import by.bsuir.forlabs.subjects.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * map on /site/login
 * is called after AuthorisedUserFilter
 * while login page is requested, checks if user is logged on and redirects user to its homepage
 */

public class LoginPageFilter implements Filter {

    private static Logger log = Logger.getLogger(LoginPageFilter.class);

    private static final int ADMIN_ROLE_CODE = 1;
    private static final int CLIENT_ROLE_CODE = 2;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.info("works");
        boolean hasAccessRights = false;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse  response = (HttpServletResponse)servletResponse;
        User user ;

        String page = null;

        if((user =  (User)request.getSession().getAttribute("user")) != null ) {
            if (user.getIdRole() == ADMIN_ROLE_CODE) {
                page = RoutingManager.getProperty("path.page.admin.home");
            }

            if (user.getIdRole() == CLIENT_ROLE_CODE) {
                page = RoutingManager.getProperty("path.page.client.home");
            }

            response.sendRedirect(page);
        }
        else {
            log.info("user is not authorised");
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
