package by.bsuir.forlabs.commands;

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

        page = request.getRequestURI();

        // если меняем локаль на странице конкретной заявки или автомобиля (есть параметр id), то нужно вновь попасть на нее
        // page не устанавливатся через request.getRequestURI() + "?" + request.getQueryString() т.к. весь запрос повторять не стоит
        if (request.getParameter("id") != null) {
            log.info("request has parameter id = " + request.getParameter("id"));
            page += "?id=" + request.getParameter("id");               // 2 ой вариант - добавить параметр к uri
        }

        return page;
    }
}
