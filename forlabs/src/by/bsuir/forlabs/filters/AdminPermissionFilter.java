package by.bsuir.forlabs.filters;

import by.bsuir.forlabs.resourcesmanagers.RoutingManager;
import by.bsuir.forlabs.subjects.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * map on /site/admin
 * is called after LoginPageFilter
 * checks if user has administrator permission for accessing administrator's pages
 */
public class AdminPermissionFilter implements Filter {

    private static Logger log = Logger.getLogger(AdminPermissionFilter.class);

    private static final int ADMIN_ROLE_CODE = 1;

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

        if (user.getIdRole() == ADMIN_ROLE_CODE) {
                log.info("user has admin rights");
                filterChain.doFilter(servletRequest, servletResponse);
            }
        else {
            log.info("user has not admin rights");

            request.getSession().setAttribute("noRights", 1);
            response.sendRedirect(RoutingManager.getProperty("path.page.error404"));
        }
    }

    @Override
    public void destroy() {

    }
}
