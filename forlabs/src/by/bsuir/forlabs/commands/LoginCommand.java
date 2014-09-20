package by.bsuir.forlabs.commands;

import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.logic.LoginLogic;
import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import by.bsuir.forlabs.resourcesmanagers.MessageManager;
import by.bsuir.forlabs.subjects.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

import static by.bsuir.forlabs.utilits.ExceptionPrinter.printEx;

/**
 * command for processing user logging
 */

public class LoginCommand implements Command {

    private static final Logger log = Logger.getLogger(LoginCommand.class);

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);

        try {
            User user;
            if ((user = LoginLogic.checkLogin(login, pass)) != null) {
                request.getSession().setAttribute("user", user);
                if (user.getIdRole() == 1) {
                    page = ConfigurationManager.getProperty("path.page.admin.home");
                }
                if (user.getIdRole() == 2) {
                    page = ConfigurationManager.getProperty("path.page.client.home");
                }
            } else {
                request.getSession().setAttribute("errorLoginPassMessage",
                        new MessageManager((Locale) request.getSession().getAttribute("localeObj")).getProperty("message.loginerror"));
                page = ConfigurationManager.getProperty("path.page.login");
            }
        } catch (LogicalException e) {
            printEx(e);
        }
        return page;
    }
}
