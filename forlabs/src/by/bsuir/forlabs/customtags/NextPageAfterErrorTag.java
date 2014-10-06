package by.bsuir.forlabs.customtags;

import by.bsuir.forlabs.resourcesmanagers.RoutingManager;
import by.bsuir.forlabs.subjects.User;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

import static by.bsuir.forlabs.utilits.ExceptionsPrintWrapper.printException;

public class NextPageAfterErrorTag extends TagSupport {

    private final static Logger log = Logger.getLogger(NextPageAfterErrorTag.class);

    private static final int ADMIN_ROLE_CODE = 1;
    private static final int CLIENT_ROLE_CODE = 2;


    @Override
    public int doStartTag() throws JspException {
        User user = null;
        JspWriter out = pageContext.getOut();
        try {
            if ((user =  (User)pageContext.getSession().getAttribute("user")) != null) {
                if (user.getIdRole() == ADMIN_ROLE_CODE) {
                    out.write("<a href='" + RoutingManager.getProperty("path.page.admin.home") + "'>");
                }
                if (user.getIdRole() == CLIENT_ROLE_CODE) {
                    out.write(RoutingManager.getProperty("path.page.client.home"));
                }
            }
            else {
                out.write(RoutingManager.getProperty("path.page.login"));
            }
        } catch (IOException e) {
            log.error(e);
            printException(e);
        }

        return SKIP_BODY;
    }
}
