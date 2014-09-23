package by.bsuir.forlabs.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * command for processing changing locale
 */

public class ChangeLocaleCommand implements Command {


    @Override
    public String execute(HttpServletRequest request) {

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

        //System.out.println("locale" + request.getParameter("locale"));
        return (String)request.getSession().getAttribute("from");
    }
}
