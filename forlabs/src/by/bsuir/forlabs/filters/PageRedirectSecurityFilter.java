package by.bsuir.forlabs.filters;


/* фильтр, перенаправляющий все прямые обращения к jsp-страницам на error404.jsp*/

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PageRedirectSecurityFilter implements Filter {

    private static Logger log = Logger.getLogger(PageRedirectSecurityFilter.class);
    private String indexPath;

    public void init(FilterConfig fConfig) throws ServletException {
        indexPath = fConfig.getInitParameter("ERROR_PATH");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // переход на заданную страницу
        log.info("Redirect filter works. " +
                "From: " + ((HttpServletRequest) request).getRequestURI() +
                "\nContextPath: " + httpRequest.getContextPath()
        );
        if (request.getParameter("command") == null || request.getParameter("command").isEmpty()){
            log.info("\t with redirect");
            httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);  // почему был бесконечный вызов при 404 + фильтр отображался на страницу error404.jsp
        }
        else {
            log.info("\t without redirect");
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }

}