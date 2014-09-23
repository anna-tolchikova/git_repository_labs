package by.bsuir.forlabs.commands;

import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.logic.LoginLogic;
import by.bsuir.forlabs.resourcesmanagers.MessageManager;
import by.bsuir.forlabs.resourcesmanagers.RoutingManager;
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
    private static final int ADMIN_ROLE_CODE = 1;
    private static final int CLIENT_ROLE_CODE = 2;

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);

        try {
            User user;

            // check if user is logged on

            if ((user =  (User)request.getSession().getAttribute("user")) == null) {
                if ( (user = LoginLogic.checkLogin(login, pass)) == null){
                    request.getSession().setAttribute("errorLoginPassMessage",
                            new MessageManager((Locale) request.getSession().getAttribute("localeObj")).getProperty("message.loginerror"));
                    page = RoutingManager.getProperty("path.page.login");
                }

            }
            if (user != null) {
                request.getSession().setAttribute("user", user);
                if (user.getIdRole() == ADMIN_ROLE_CODE) {

                    page = RoutingManager.getProperty("path.page.admin.home");
                }
                if (user.getIdRole() == CLIENT_ROLE_CODE) {
                    page = RoutingManager.getProperty("path.page.client.home");
                }
            }
        } catch (LogicalException e) {
            printEx(e);
        }
        return page;
    }
}
