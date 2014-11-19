package by.bsuir.forlabs.customtags;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

import static by.bsuir.forlabs.utilits.ExceptionsPrintWrapper.printException;

/**
 * tag sets on locale change form required hidden field "from" - current page for return
 * and depends on current page adds or not hidden field "id"
 */

public class LocaleFormTag extends TagSupport{

    private final static Logger log = Logger.getLogger(LocaleFormTag.class);

    @Override
    public int  doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();

//        String formParameterFrom = (String) pageContext.getRequest().getAttribute("javax.servlet.forward.request_uri");
        String referer = ((HttpServletRequest)pageContext.getRequest()).getHeader("Referer");
        log.info("referer = " + referer);
        String from = referer.substring("http://localhost:7075".length());
        log.info("from = " + from);

        try {
            out.write("<input type=\"hidden\" name=\"from\" value=\"" + from + "\">");
//            if((formParameterId = pageContext.getRequest().getParameter("id")) != null) {
//                out.write("<input type=\"hidden\" name=\"id\" value=\"" + formParameterId + "\">");
//            }
        } catch (IOException e) {
            printException(e);
        }

        return SKIP_BODY;
    }
}
