package by.bsuir.forlabs.commands;

import by.bsuir.forlabs.resourcesmanagers.RoutingManager;

import javax.servlet.http.HttpServletRequest;

/**
 * command for processing user logout
 */

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page = RoutingManager.getProperty("path.page.login");
        // уничтожение сессии
        request.getSession().invalidate();
        return page;
    }
}
