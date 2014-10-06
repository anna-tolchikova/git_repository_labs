package by.bsuir.forlabs.commands.admin;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.logic.admin.ChangeStatusLogic;
import by.bsuir.forlabs.resourcesmanagers.RoutingManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.bsuir.forlabs.utilits.ExceptionsPrintWrapper.printException;

/**
 * command for processing changing locale
 */

public class ApproveRequestCommand implements Command {

    private final static Logger log = Logger.getLogger(ApproveRequestCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");

        String page = null;

        String id = request.getParameter("id");
        if (!("null").equals(id)) {
            log.info("request has parameter id = " + id);

            try {
                ChangeStatusLogic.approveRequest(Integer.parseInt(id));
            } catch (LogicalException e) {
                request.getSession().setAttribute("commandError", 1);
                printException(e);
            }

            page = RoutingManager.getProperty("path.page.admin.application");
            page += "?id=" + id;
        }

        return page;
    }
}
