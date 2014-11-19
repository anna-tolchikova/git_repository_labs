package by.bsuir.forlabs.commands.common;

import by.bsuir.forlabs.commands.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * command for processing changing locale
 */

public class ChangeLocaleCommand implements Command {

    private final static Logger log = Logger.getLogger(ChangeLocaleCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");

        String page = null;
        String localeStr =request.getParameter("localeStrI");
        String[] localeParts = localeStr.split("_");
        if (localeParts.length != 2) {
            throw new IllegalArgumentException("Request parameter \"localeStrI\" has mask language_country");
        }
        HttpSession session = request.getSession();
        session.setAttribute("localeStr", localeStr);
        Locale localeObj = new Locale(localeParts[0], localeParts[1]);
        session.setAttribute("localeObj", localeObj);
        page = (String) request.getParameter("from");
        return page;
    }
}
