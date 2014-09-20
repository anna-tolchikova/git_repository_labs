package by.bsuir.forlabs.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ResourcesFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        if (request.getPathInfo().startsWith("/jsp")) {
            filterChain.doFilter(servletRequest, servletResponse );
        }
        else {
            //request.getRequestDispatcher("").forward();
        }
    }

    @Override
    public void destroy() {

    }
}
