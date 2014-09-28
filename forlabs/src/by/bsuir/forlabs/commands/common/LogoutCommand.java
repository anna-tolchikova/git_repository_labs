package by.bsuir.forlabs.commands.common;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.resourcesmanagers.RoutingManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * command for processing user logout
 */

public class LogoutCommand implements Command {

    private final static Logger log = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");

        String page = RoutingManager.getProperty("path.page.login");
        // уничтожение сессии
        request.getSession().invalidate();
        return page;
    }
}
