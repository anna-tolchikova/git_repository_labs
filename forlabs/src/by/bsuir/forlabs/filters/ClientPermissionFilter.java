package by.bsuir.forlabs.filters;

import by.bsuir.forlabs.resourcesmanagers.RoutingManager;
import by.bsuir.forlabs.subjects.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * map on /site/client
 * is called after AdminPermissionFilter
 * checks if user has client permission for accessing client's pages
 *
 */
public class ClientPermissionFilter implements Filter {

    private static Logger log = Logger.getLogger(ClientPermissionFilter.class);

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
        User user =  (User)request.getSession().getAttribute("user");

        if (user.getIdRole() == CLIENT_ROLE_CODE) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            response.sendRedirect(RoutingManager.getProperty("path.page.error403"));
        }
    }

    @Override
    public void destroy() {

    }
}
