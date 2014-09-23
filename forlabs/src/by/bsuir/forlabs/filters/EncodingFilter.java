package by.bsuir.forlabs.filters;

/**
 * map on site/*
 * is called first
 * corrects request and response encoding
 */

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private static Logger log = Logger.getLogger(EncodingFilter.class);
    private static int reqCount;

    private String code;

    public void init(FilterConfig fConfig) throws ServletException {
        code = fConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        log.info("Encoding filter requests count = " + ++reqCount);
        String codeRequest = request.getCharacterEncoding();  // установка кодировки из параметров фильтра, если не установлена

        if (code != null && !code.equalsIgnoreCase(codeRequest)) {

            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);

        }
        chain.doFilter(request, response);

    }

    public void destroy() {
        code = null;
    }
}
